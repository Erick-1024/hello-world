import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.crawler.dao.mongo.dao.IShixinCourtBlackListDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourtBlackList;
import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/crawler-shixincourt-server*.xml" })
public class TestCrawlShixinCourtFromBaidu  extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private IShixinCourtBlackListDao<ShixinCourtBlackList, ObjectId> dao;

	@Test
	public void crawl() throws Exception{
		WebDriver driver = null;
		try{
			driver = new ChromeDriver();
			driver.get("https://www.baidu.com");
			WebElement queryInputElement = driver.findElement(By.id("kw"));
			queryInputElement.sendKeys("失信执行");
			WebElement submitElement = driver.findElement(By.id("su"));
			submitElement.click();
			Thread.sleep(2 * 1000);
			while(true){
				driver.switchTo().defaultContent();
				List<WebElement> rows = driver.findElements(By.className("op_trust_btn_list"));
				for(WebElement row : rows){
					String code = StringUtils.trimToEmpty(row.findElement(By.className("op_trust_papers")).getText());
					String name = StringUtils.trimToEmpty(row.findElement(By.className("op_trust_name")).getText());
					
					ShixinCourtBlackList item = dao.findByCodeAndName(code, name);
					if(item != null)
						continue;
					
					CourtExecutionSubject subject = null;
					
					if(code.contains("****"))
						subject = CourtExecutionSubject.individual;
					else subject =CourtExecutionSubject.company;
					
					item = new ShixinCourtBlackList();
					item.setSubject(subject);
					item.setCode(code);
					item.setName(name);
					item.setCreateDate(new DateTime());
					dao.create(item);
				}
				WebElement nextPageElement = driver.findElement(By.className("op_trust_page_next"));
				nextPageElement.click();
				Thread.sleep(2 * 1000);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}finally{
//			driver.close();
		}
	}

}
