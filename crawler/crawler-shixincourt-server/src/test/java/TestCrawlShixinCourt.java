import java.io.File;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.Test;

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

public class TestCrawlShixinCourt {

	@Test
	public void crawl() throws Exception{
		try{
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			CookieManager cookieManager = webClient.getCookieManager();
			cookieManager.setCookiesEnabled(true);
			HtmlPage page = webClient.getPage("http://shixin.court.gov.cn/");
			System.out.println(page.asText());
			System.out.println(cookieManager.getCookies());
			HtmlImage image = page.<HtmlImage>getHtmlElementById("captchaImg");
			File imageFile = new File("/tmp/captchaImg.jpg");
			image.saveAs(imageFile);
			
//			UnexpectedPage page1 = webClient.getPage("http://shixin.court.gov.cn/image.jsp?date=Wed%20May%2025%202016%2017:41:36%20GMT+0800%20(CST)");
//			FileOutputStream output = new FileOutputStream("/tmp/captchaImg2.jpg");
//			IOUtils.copy(page1.getInputStream(), output);
//			output.close();
		    String pCode = "abc";	
			WebRequest requestSettings = new WebRequest(new URL("http://shixin.court.gov.cn/findd"), HttpMethod.POST);
//			List<NameValuePair> params = new ArrayList<>();
//			params.add(new NameValuePair("pCode", pCode));
//			params.add(new NameValuePair("pName", URLEncoder.encode("朱莉", "utf-8")));
//			params.add(new NameValuePair("pProvince", "0"));
//			requestSettings.setRequestParameters(params);
			requestSettings.setAdditionalHeader("DNT", "1");
			requestSettings.setAdditionalHeader("Referer", "http://shixin.court.gov.cn/");
			requestSettings.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			requestSettings.setRequestBody("pName=" + URLEncoder.encode("朱莉", "utf-8") + "&pCardNum=&pProvince=0&pCode=" + pCode);
			page = webClient.getPage(requestSettings);
			System.out.println(page.asText());
			for(HtmlAnchor anchor : page.getAnchors()){
				if("查看".equals(anchor.asText())){
					String id = anchor.getAttribute("id");
					TextPage textPage = webClient.getPage("http://shixin.court.gov.cn/findDetai?id=" + id + "&pCode=" + pCode);
					System.out.println(textPage.getContent());
				}
				System.out.println(anchor.asText());
			}
			
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void crawlNewBlackList() throws Exception{
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		CookieManager cookieManager = webClient.getCookieManager();
		cookieManager.setCookiesEnabled(true);
		HtmlPage page = webClient.getPage("http://shixin.court.gov.cn/");
		page = webClient.getPage("http://shixin.court.gov.cn/index_publish_new.jsp");
		System.out.println(page.asText());
		for(String line : page.asText().split("\\n")){
			String parts[] = line.split("\\s+");
			if(parts.length == 2){
				if(parts[1].contains("****")){
					System.out.println(String.format("个人_%s_%s", parts[0], parts[1]));
				}else{
					System.out.println(String.format("企业_%s_%s", parts[0], parts[1]));
				}
			}
		}
	}

}
