import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.travelzen.framework.dao.mybatis.mapper.IBatchSequenceMapper;
@ContextConfiguration("classpath:spring/framework-dao-base-*.xml")
public class MySqlSpringTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IBatchSequenceMapper mapper;
	
	@Test
	public void test() {
		assertNotNull(mapper);
		System.out.println(mapper.getNextSeq("1", 100));
	}

}
