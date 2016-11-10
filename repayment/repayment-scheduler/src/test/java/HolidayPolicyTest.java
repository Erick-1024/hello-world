

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.repayment.service.transaction.IHolidayPolicyTransactionService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/repayment-scheduler-*.xml")
public class HolidayPolicyTest {

	@Resource
	private IHolidayPolicyTransactionService holidayPolicyTransactionService;
	@Resource
	private IAssetProjectManageApi assetProjectManageApi;

	@Test
	public void test() {
		List<String> projectIds = assetProjectManageApi.getUseHolidayPolicyProjectIds();
		System.out.println(projectIds);

		List<String> loanInfoIds = holidayPolicyTransactionService.getAllEffectedLoanInfoIds(
				Sets.newHashSet("2016-09-21", "2016-09-22"),
				Lists.newArrayList("travelzen_finance", "yundaex_project_id"));
		System.out.println(loanInfoIds);
	}

	@Test
	public void testUpdateExtension() {
		Map<String, Integer> effectDateAndExtensionDaysMap = Maps.newHashMap();
		effectDateAndExtensionDaysMap.put("2017-01-21", 2);
		holidayPolicyTransactionService.updateExtensionDays("2016092120201", effectDateAndExtensionDaysMap);
	}

}
