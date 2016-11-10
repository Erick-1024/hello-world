package com.cana.repayment.service.transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 节假日政策业务类
 * @author XuMeng
 *
 */
public interface IHolidayPolicyTransactionService {

	/**
	 * 获取还款日在effectDate10s中的还款计划的放款ID列表
	 * @param effectDate10s 受影响的还款日集合
	 * @param useHolidayPolicyProjects 使用节假日政策的项目ID
	 * @return 放款编号列表
	 */
	public List<String> getAllEffectedLoanInfoIds(Set<String> effectDate10s, List<String> useHolidayPolicyProjectIds);

	/**
	 * 更新某一笔放款中，受影响的还款计划的展期天数
	 * <p> 1. lock loanInfoId
	 * <p> 2. load plans
	 * <p> 3. 找出还款日在 effectDate10s 中的还款计划，检查展期天数是否需要改变，更新展期天数并创建快照
	 * @param loanInfoId 放款ID
	 * @param effectDateAndExtensionDaysMap 受节假日变更影响的还款日列表，以及每个还款日对应的节假日因素展期天数
	 */
	public void updateExtensionDays(String loanInfoId, Map<String, Integer> effectDateAndExtensionDaysMap);
}
