import java.io.FileWriter;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.crawler.dao.mongo.dao.ILianhanghaoCompleteDataDao;
import com.cana.crawler.dao.mongo.entity.LianhanghaoCompleteData;

@ContextConfiguration(locations = { "classpath*:spring/crawler-dao*.xml" })
public class GenerateSqlTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private ILianhanghaoCompleteDataDao<LianhanghaoCompleteData, ObjectId> completeDataDao;
	
	@Test
	public void generate() throws Exception{
		FileWriter writer = new FileWriter("/tmp/sql");
		for(LianhanghaoCompleteData data: completeDataDao.getAll()){
			writer.write(String.format("insert into common_bank_branch_info(id, lian_hang_no, bank_name, province, city, branch_name)values('%s','%s', '%s', '%s', '%s', '%s');\n"
					                   , data.getLianhanghao(), data.getLianhanghao(), data.getBankName(), data.getProvince(), data.getCity(), data.getBranchName()));
		}
		writer.close();
	}
	

}
