import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:spring/spring_quartz.xml" })
public class TestQuartz extends AbstractJUnit4SpringContextTests {
	
	@Test
	public void test() throws InterruptedException{
		Thread.sleep( 24 * 3600 * 1000);
	}

}
