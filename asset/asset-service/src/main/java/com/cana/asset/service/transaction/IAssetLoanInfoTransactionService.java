package com.cana.asset.service.transaction;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.cana.vbam.common.asset.loan.dto.AssetPaidPlanRequest;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

/**
 * 放款
 * @author XuMeng
 *
 */
public interface IAssetLoanInfoTransactionService {

	/**
	 * 根据业务合同号，找出存在放款的业务合同号。
	 * 当参数为空时，则返回空的Set集合
	 */
	public Set<String> pickContractNosHasLoan(Set<String> contractNos);

	/**
	 * 检查某一个合同号是否存在放款
	 * 如果存在放款，则返回true
	 */
	public boolean checkContractNoHasLoan(String contractNo);

	/**
	 * 删除某一笔没有发生还款的放款信息
	 * 
	 * 删除内容包括：
	 * 放款、还款计划、费用、应收帐款、恢复额度
	 * @param userVo 当前登录用户
	 * @param loanInfoId 放款ID
	 */
	public void deleteLoanById(UserVo userVo, String loanInfoId);

	/**
	 * 创建放款
	 * @param userVo 创建员工，仅支持保理商客户员工
	 */
	public String createAssetLoan(UserVo userVo, EditAssetLoanRequest request);

	/**
	 * 为恒顺写的创建放款接口
	 * @param factorVo 放款所属保理商，仅支持保理商客户员工
	 * @param request 新增放款的参数，除了loanInfoId,expenses,plans均不可为空
	 * @return 当左值为 ReturnCode.SUCCESS 时，右值为 放款编号。当左值为 ReturnCode.FINANCE_AMOUNT_EXCEED 表示额度不足，右值可忽略。
	 * @throws 抛出异常可认为存在开发bug
	 */
	public Pair<ReturnCode, String> createAssetLoanForHomsom(UserVo factorVo, EditAssetLoanRequest request);

	/**
	 * 修改放款
	 * 可以任意修改没有还款冲销的还款，对于有还款冲销且未结清的，只允许修改逾期费和结清状态
	 * @param userVo 修改员工，仅支持保理商客户员工
	 */
	public String updateAssetLoan(UserVo userVo, EditAssetLoanRequest request);

	/**
	 * 检查导入的放款是否符合要求
	 * @param userVo 创建员工，仅支持保理商客户员工
	 * @param request 业务实现中会忽略该字段中的还款计划字段
	 */
	public void checkImportAssetLoanInfoRequest(UserVo userVo, EditAssetLoanRequest request);

	/**
	 * 检查导入的还款计划是否符合要求
	 * @param userVo 创建员工，仅支持保理商客户员工
	 * @param request 放款编号、业务合同号、放款日、到期日、融资金额 不能为空且需要与数据库中已存在放款的信息一致，还款计划列表字段不能为空
	 */
	public void checkImportAssetLoanPlanRequest(UserVo userVo, EditAssetLoanRequest request);

	/**
	 * 导入放款
	 * @param userVo 创建员工，仅支持保理商客户员工
	 * @param request 同checkImportAssetLoanInfoRequest接口文档一致
	 */
	public void importAssetLoanInfo(UserVo userVo, List<EditAssetLoanRequest> request);

	/**
	 * 导入还款计划
	 * @param userVo 修改员工，仅支持保理商客户员工
	 * @param request 同checkImportAssetLoanPlanRequest接口文档一致
	 */
	public void importAssetLoanPlan(UserVo userVo, List<EditAssetLoanRequest> request);

	/**
	 * 冲销一笔放款中的第一个未结清状态的还款计划
	 * 冲销后已还金额不能大于应还金额
	 * @param userVo 当前还款冲销的员工，仅支持该放款的保理客户员工
	 * @param request 还款冲销请求对象
	 * @return 是否冲销成功
	 * @throws WebException
	 */
	public boolean paidAssetLoanPlan(UserVo userVo, AssetPaidPlanRequest request);

}
