import java.net.URL;

import javax.annotation.Resource;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.CacheAwareContextLoaderDelegate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.travelzen.framework.dao.rdbms.SequenceGenerator;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;

@ContextConfiguration("classpath:spring/framework-dao-base-*.xml")
public class SequenceGeneratorTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	private static final Logger logger = LoggerFactory.getLogger(CacheAwareContextLoaderDelegate.class);
	
	@PerfTest(invocations = 200000, threads = 1)
	@Test
	public void getNexSeq() throws Exception{
		LoggerContext loggerContext = ((ch.qos.logback.classic.Logger)logger).getLoggerContext();
        URL mainURL = ConfigurationWatchListUtil.getMainWatchURL(loggerContext);
        System.out.println(mainURL);
		System.out.println("next_seq:" + seqGen.getNextSeq("1"));
	}

}
