package com.cana.repayment.api;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.QueryRepaymentAndExpenseRequestDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanSearchCriteriaDTO;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentType;


/**
 * 主动还款接口
 * @author xiong.li
 *
 */
public interface IActiveRepaymentApi {
	
	public String getPlanAndExpenseToRedis(String operatorId,String loanInfoId, String masterId) throws Exception;
	
	/**
	 * 调账提交前验证
	 * @param redisKey
	 */
	public void submitValidate(String redisKey);
	
	public String getMaxAccountTotalMoney(String loanInfoId, ActiveRepaymentType activeRepaymentType);
	
	/**
	 * 保存调账后的计划和费用数据到数据库
	 * @param redisKey
	 * @param loanInfoId
	 * @param operatorId
	 * @param flag
	 * @param changeType
	 * @throws Exception
	 */
	public void saveRepaymentPlanAndExpenseToDB(String redisKey,String loanInfoId, String operatorId, String flag,String changeType) throws Exception;

	public String getTransferInAccountNo(String loanInfoId);
	
	public void updateOnActiveRepaymentSuccess(String loanInfoId, String amount, ActiveRepaymentType activeRepaymentType, String accountNo);

	/**
	 * 获取还款日为当前日+7天的还款计划
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentPlanActiveRepaymentDTO> getRepaymentPlansWithn7Days(RepaymentPlanSearchCriteriaDTO searchDto, UserType userType) throws Exception;
	
	/**
	 * 获取还款日为当前日+7天的费用列表
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentExpenseActiveRepaymentDTO> getRepaymentExpenseWithn7Days(RepaymentPlanSearchCriteriaDTO searchDto, UserType userType)throws Exception;
	

	/**
	 * 查询逾期的还款计划
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentPlanActiveRepaymentDTO> getOverdueRepaymentPlan(QueryRepaymentAndExpenseRequestDTO queryDTO, UserType userType) throws Exception;
	
	/**
	 * 查询逾期的费用列表
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentExpenseActiveRepaymentDTO> getOverdueRepaymentExpense(QueryRepaymentAndExpenseRequestDTO queryDTO, UserType userType)throws Exception;

	/**
	 * 根据查询条件查询费用列表
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentExpenseActiveRepaymentDTO> queryRepaymentExpensesBySearchCondition(QueryRepaymentAndExpenseRequestDTO queryDTO)throws Exception;
	
	/**
	 * 根据查询条件来查询还款计划列表
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentPlanActiveRepaymentDTO> queryRepaymentPlansBySearchCondition(QueryRepaymentAndExpenseRequestDTO queryDTO)throws Exception;

	/**
	 * 获取本次还款金额
	 * @param redisKey
	 * @return
	 */
	public String getCurrentRepaymentNum(String redisKey) throws Exception;
}
