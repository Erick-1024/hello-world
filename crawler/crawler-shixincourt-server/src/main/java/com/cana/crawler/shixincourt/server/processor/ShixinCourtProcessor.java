package com.cana.crawler.shixincourt.server.processor;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.crawler.common.util.IYunSuValidCode;
import com.cana.crawler.common.util.YunSuValidCode;
import com.cana.crawler.dao.mongo.entity.ShixinCourt;
import com.cana.crawler.dao.mongo.entity.ShixinCourtItem;
import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class ShixinCourtProcessor {
	
	private IYunSuValidCode yunsuValidCode = SpringApplicationContext.getApplicationContext().getBean(IYunSuValidCode.class);

	private final String BASE_URL = "http://shixin.court.gov.cn/";
	
	private Logger logger = LoggerFactory.getLogger(ShixinCourtProcessor.class);

	/**
	 * 从网站上爬取
	 * @param request
	 * @return
	 */
	public ShixinCourt doCrawl(GetShixinCourtRequest request) throws Exception{
		ShixinCourt shixinCourt = initShixinCourt(request);
		WebClient webClient = null;
		try{
			webClient = initWebClient();
			String validCode = getFirstValidCode(webClient);
			HtmlPage page = getFirstPage(webClient, validCode, shixinCourt);
			analyzeFirstPage(webClient, validCode, shixinCourt, page);
			return shixinCourt;
		}finally{
			if(webClient != null)
				webClient.closeAllWindows();
		}
		
	}

	/**
	 * 分析首页返回的内容
	 * @param webClient
	 * @param validCode
	 * @param shixinCourt
	 * @return 如果详情爬取无异常，返回true，否则返回false
	 */
	private boolean analyzeFirstPage(WebClient webClient, String validCode, ShixinCourt shixinCourt, HtmlPage page) throws Exception{
		
		Pattern pattern = Pattern.compile("共(\\d+)条"); 
		Matcher matcher = pattern.matcher(page.asText());
		if(matcher.find()){
			shixinCourt.setCount(Integer.parseInt(matcher.group(1)));
		}else throw new Exception("首次查询失败");
		
		if(shixinCourt.getCount() != 0){
			return crawlDetails(webClient, validCode, shixinCourt, page);
		}else return true;
	}

	private boolean crawlDetails(WebClient webClient, String validCode, ShixinCourt shixinCourt, HtmlPage page) {
		try{
			for(HtmlAnchor anchor : page.getAnchors()){
				if("查看".equals(anchor.asText())){
					String id = anchor.getAttribute("id");
					TextPage textPage = webClient.getPage(BASE_URL + "findDetai?id=" + id + "&pCode=" + validCode);
					shixinCourt.getItems().add(analyzeDetail(textPage.getContent()));
				}
			}
			return true;
		}catch(Throwable thr){
			return false;
		}
	}

	private ShixinCourtItem analyzeDetail(String content) throws Exception{
		Map<String, String> map = new Gson().fromJson(content, new TypeToken<Map<String, String>>(){}.getType());
		ShixinCourtItem item = new ShixinCourtItem();
		item.setCaseCode(map.get("caseCode"));
		item.setCourtName(map.get("courtName"));
		item.setDisruptTypeName(map.get("disruptTypeName"));
		item.setDuty(map.get("duty"));
		item.setGistId(map.get("gistId"));
		item.setName(map.get("iname"));
		item.setPerformance(map.get("performance"));
		item.setPublishDate(extractDate10(map.get("publishDate")));
		item.setRegDate(extractDate10(map.get("regDate")));
		item.setRawText(content);
		return item;
	}
	
	private String extractDate10(String text){
		Pattern pattern = Pattern.compile("(\\d{4})年(\\d{2})月(\\d{2})日"); 
		Matcher matcher = pattern.matcher(text);
		if(matcher.matches()){
			return matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
		}else return text;
	}

	/**
	 * 请求首页
	 * @param webClient
	 * @param validCode
	 * @param shixinCourt
	 * @return
	 * @throws Exception
	 */
	private HtmlPage getFirstPage(WebClient webClient, String validCode, ShixinCourt shixinCourt) throws Exception{
		WebRequest requestSettings = new WebRequest(new URL(BASE_URL + "findd"), HttpMethod.POST);
		requestSettings.setAdditionalHeader("DNT", "1");
		requestSettings.setAdditionalHeader("Referer", "http://shixin.court.gov.cn/");
		requestSettings.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		String encodedName = "";
		String encodedCode = "";
		if(StringUtils.isNotBlank(shixinCourt.getName())){
			encodedName = URLEncoder.encode(shixinCourt.getName(), "utf-8");
		}
		if(StringUtils.isNotBlank(shixinCourt.getCode())){
			encodedCode = URLEncoder.encode(shixinCourt.getCode(), "utf-8");
		}
		requestSettings.setRequestBody("pName=" + encodedName + "&pCardNum=" + encodedCode + "&pProvince=0&pCode=" + validCode);
		HtmlPage page = webClient.getPage(requestSettings);
		return page;
	}

	private String getFirstValidCode(WebClient webClient) throws Exception{
		HtmlPage page = webClient.getPage(BASE_URL);
		HtmlImage image = page.<HtmlImage>getHtmlElementById("captchaImg");
		String captchaImggePath = "/tmp/" + RandomStringUtils.randomAlphanumeric(10) + ".jpg";
		File imageFile = new File(captchaImggePath);
		image.saveAs(imageFile);
		return yunsuValidCode.createByPost(YunSuValidCode.TYPEID_DIGITS_4, captchaImggePath);
	}

	private WebClient initWebClient() throws Exception{
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		CookieManager cookieManager = webClient.getCookieManager();
		cookieManager.setCookiesEnabled(true);
		return webClient;
	}

	private ShixinCourt initShixinCourt(GetShixinCourtRequest request) {
		ShixinCourt shixinCourt = new ShixinCourt();
		shixinCourt.setSubject(request.getSubject());
		shixinCourt.setCode(request.getCode());
		shixinCourt.setName(request.getName());
		shixinCourt.setCreateDate(new DateTime());
		return shixinCourt;
	}
	
	public void crawlNewBlackList(final List<Pair<String,String>> individualBlackList, final List<Pair<String,String>> companyBlackList) throws Exception{
		WebClient webClient = null;
		try{
			webClient = initWebClient();
			webClient.getPage(BASE_URL);
			HtmlPage page  = webClient.getPage(BASE_URL + "index_publish_new.jsp");
			logger.info("blackList:" + page.asText());
			for(String line : page.asText().split("\\n")){
				String parts[] = line.split("\\s+");
				if(parts.length == 2){
					if(parts[1].contains("****")){
						individualBlackList.add(Pair.of(parts[0], parts[1]));
					}else{
						companyBlackList.add(Pair.of(parts[0], parts[1]));
					}
				}
			}
		}finally{
			if(webClient != null)
				webClient.closeAllWindows();
		}
	}

}
