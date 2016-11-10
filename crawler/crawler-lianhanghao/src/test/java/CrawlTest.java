import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;
import org.codehaus.janino.UnicodeUnescapeReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.CollectionUtils;

import com.cana.crawler.dao.mongo.dao.ILianhanghaoBaseDataDao;
import com.cana.crawler.dao.mongo.dao.ILianhanghaoCompleteDataDao;
import com.cana.crawler.dao.mongo.entity.LianhanghaoBaseData;
import com.cana.crawler.dao.mongo.entity.LianhanghaoCompleteData;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@ContextConfiguration(locations = { "classpath*:spring/crawler-dao*.xml" })
public class CrawlTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private ILianhanghaoBaseDataDao<LianhanghaoBaseData, ObjectId> baseDataDao;
	
	@Resource
	private ILianhanghaoCompleteDataDao<LianhanghaoCompleteData, ObjectId> completeDataDao;
	
	private static WebClient webClient;
	
	@BeforeClass
	public static void initWebClient() throws Exception{
		webClient = new WebClient(BrowserVersion.FIREFOX_24);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		CookieManager cookieManager = webClient.getCookieManager();
		cookieManager.setCookiesEnabled(true);
	}

//	@Test
	public void crawlBaseData() throws Exception{
		
		Map<String, List<City>> citiesKeyedByProvinceCode = new HashMap<>();
		
		List<LianhanghaoBaseData> bankAndProvinceDataList = getBankAndProvince();
		
		for(LianhanghaoBaseData  bankAndProvinceData : bankAndProvinceDataList){
			List<City> cities = citiesKeyedByProvinceCode.get(bankAndProvinceData.getProvinceCode());
			if(cities == null){
				cities = getCitiesByProvinceCode(bankAndProvinceData.getProvinceCode());
				citiesKeyedByProvinceCode.put(bankAndProvinceData.getProvinceCode(), cities);
			}
			for(City city : cities){
				LianhanghaoBaseData baseData = new LianhanghaoBaseData();
				baseData.setBankCode(bankAndProvinceData.getBankCode());
				baseData.setBankName(bankAndProvinceData.getBankName());
				baseData.setProvinceCode(bankAndProvinceData.getProvinceCode());
				baseData.setProvince(bankAndProvinceData.getProvince());
				baseData.setCityCode(city.getId());
				baseData.setCity(city.getName());
				baseDataDao.create(baseData);
			}
		}
		
		
	}
	
	@Test
	public void crawlLianhanghao() throws Exception{
		for(LianhanghaoBaseData baseData : baseDataDao.getAll()){
			if(baseData.isCrawlComplete())
				continue;
			for(int i = 1; ; i++){
				String url = String.format("http://www.lianhanghao.com/index.php?bank=%s&key=&province=%s&city=%s&page=%s", baseData.getBankCode(), baseData.getProvinceCode(), baseData.getCityCode(), i);
				HtmlPage page = webClient.getPage(url);
				HtmlTable table = (HtmlTable) page.getByXPath( "//table[@class='t2']").get(0);
				for(HtmlTableRow row :  table.getBodies().get(0).getRows()){
					String lianhanghao = StringUtils.trimToEmpty(row.getCell(1).getTextContent());
					String branchName = StringUtils.trimToEmpty(row.getCell(2).getTextContent());
					System.out.println(lianhanghao + ":" + branchName);
					LianhanghaoCompleteData completeData = completeDataDao.getByLianhanghao(lianhanghao);
					if(completeData != null)
						continue;
					completeData = new LianhanghaoCompleteData();
					completeData.setBankCode(baseData.getBankCode());
					completeData.setProvinceCode(baseData.getProvinceCode());
					completeData.setCityCode(baseData.getCityCode());
					completeData.setBankName(baseData.getBankName());
					completeData.setProvince(baseData.getProvince());
					completeData.setCity(baseData.getCity());
					completeData.setLianhanghao(lianhanghao);
					completeData.setBranchName(branchName);
					completeDataDao.create(completeData);
				}
				
				if(CollectionUtils.isEmpty(page.getByXPath( "//div[@class='pager']")))
					break;
				
				HtmlDivision pager = (HtmlDivision) page.getByXPath( "//div[@class='pager']").get(0);
				String pagerFirstLine = pager.getTextContent().split("\n")[0];
				Pattern pattern = Pattern.compile(".*当前(\\d+?)/(\\d+?)页.*"); 
				Matcher matcher = pattern.matcher(pagerFirstLine);
				if(matcher.find()){
					int currentPage = Integer.parseInt(matcher.group(1));
					int totalPage = Integer.parseInt(matcher.group(2));
					if(currentPage >= totalPage)
						break;
				}else throw new Exception("未定位到当前页数");
				Thread.sleep(new Random().nextInt(1000) + 1000);
			}
			baseData.setCrawlComplete(true);
			baseDataDao.createOrReplace(baseData);
		}
	}
	
	private List<City> getCitiesByProvinceCode(String provinceCode) throws Exception{
		HtmlPage page = webClient.getPage("http://www.lianhanghao.com/area.php?act=ajax&id=" + provinceCode);
		String content = page.getWebResponse().getContentAsString();
		UnicodeUnescapeReader uur = new UnicodeUnescapeReader(new StringReader(content));
		String unescapedContent = IOUtils.toString(uur);
		System.out.println("cities:" + unescapedContent);
		Map<String, List<City>> map = new Gson().fromJson(unescapedContent, new TypeToken<Map<String, List<City>>>() { }.getType());
		return map.get("city");
	}

	public List<LianhanghaoBaseData> getBankAndProvince() throws Exception{
		
		List<LianhanghaoBaseData> result = new ArrayList<>();
		
		HtmlPage page = webClient.getPage("http://www.lianhanghao.com/");
		
	    for(Pair<String, String> bankPair : getSelectOptions(page, "bank")){
	    	for(Pair<String, String> provincePair : getSelectOptions(page, "province")){
	    		LianhanghaoBaseData data = new LianhanghaoBaseData();
	    		data.setBankCode(bankPair.getLeft());
	    		data.setBankName(bankPair.getRight());
	    		data.setProvinceCode(provincePair.getLeft());
	    		data.setProvince(provincePair.getRight());
	    		result.add(data);
	    	}
	    }
		
		return result;
		
	}
	
	private List<Pair<String, String>> getSelectOptions(HtmlPage page, String elementId){
		
		List<Pair<String, String>> options = new ArrayList<>();
		
		HtmlSelect selectElement = page.getHtmlElementById(elementId);
		
		for(HtmlOption option : selectElement.getOptions()){
			String bankCode = StringUtils.trimToEmpty(option.getValueAttribute());
			String bankName = StringUtils.trimToEmpty(option.getText());
			if(!bankName.contains("请选择"))
				options.add(Pair.of(bankCode, bankName));
		}
		
		return options;
	}
	
	private static class City{
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}

}
