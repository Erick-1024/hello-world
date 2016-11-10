
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectListRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.dto.ListResult;
import com.google.gson.Gson;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class TestAssetProjectManageServer extends AbstractJUnit4SpringContextTests {
	@Resource
	private IAssetProjectManageApi assetProjectManageApi;

	
	//项目管理列表查询测试
	@Test
	public void queryProjectListTest() throws Exception {
		ProjectListRequestDTO request = new ProjectListRequestDTO();
		// request.setName();
		// request.setCoreCompanyName(coreCompanyName);
		// request.setType(type);
		// request.setCoreIndustry(coreIndustry);
		 request.setPage(1);
		 request.setPageSize(5);
//		201603280001
		ListResult<ProjectListResponseDTO> projectList = assetProjectManageApi.getProjectList("cana-baoli", request);
		System.out.println(new Gson().toJson(projectList));
	}
	
	
	//测试项目管理项目详情查询
	@Test
	public void queryprojectDetail()throws Exception{
		String userId ="cana-baoli";
//		String projectId ="160525195352101";
		String projectId ="160526101805201";
		
		ProjectDTO projectDetail = assetProjectManageApi.getProjectDetail(userId, projectId);
		System.out.println("查询项目详情测试");
		System.out.println(new Gson().toJson(projectDetail));
		
	}
	
	@Test
	public void getProjectInfo() throws Exception{
		System.out.println(new Gson().toJson(assetProjectManageApi.getProjectInfo("vj")));
	}
	
}














