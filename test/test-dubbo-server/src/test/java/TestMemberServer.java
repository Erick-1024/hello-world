

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.gson.Gson;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class TestMemberServer extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private IMemberQueryApi memberQueryApi;

	
	@Test
	public void queryUserTest() throws Exception{
		UserVo userVo = memberQueryApi.findUserById("201604180021");
		System.out.println(new Gson().toJson(userVo));
	}
	
}
