package com.cana.repayment.api;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleDistributeDetailDTO;

public interface IRepaymentPlanAdjustmentApi {
	
	public ListResult<PaidRepaymentPlanRedisDTO> queryPaidRepaymentPlan(String redisKey,int page, int pageSize) throws Exception;
	
	public ListResult<AccountRepaymentPlanRedisDTO> queryAccountRepaymentPlan(String redisKey,int page, int pageSize) throws Exception;
	
	public ListResult<PaidRepaymentExpenseRedisDTO> queryPaidRepaymentExpense(String redisKey,int page, int pageSize) throws Exception;
	
	public void paidRepaymentPlanAdjustment(String redisKey,PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO) throws Exception;
	
	public void accountRepaymentPlanEdit(String redisKey,AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO) throws Exception;
	
	public String getPlanAndExpenseToRedis(String operatorId,String loanInfoId, String masterId) throws Exception;
	
	public PaidRepaymentPlanRedisDTO queryPaidRepaymentIncrementFromPlan(String redisKey, String planId) throws Exception;
	
	public AccountRepaymentPlanRedisDTO queryAccountRepaymentPlanFromRedis(String redisKey, String planId) throws Exception;
	
	public void calculateRepaymentExpense(String redisKey,String expenseId, String paidAmount,String repaymentDate) throws Exception;
	
	public void accountRepaymentExpenseEdit(String redisKey,String expenseId, String accountExpenseAmount) throws Exception; 
	
	public ObjectResult<String> autoAllocationCharge(String redisKey, String key,String charge) throws Exception;
	
	public void saveRepaymentSummaryAndDetailToDB(String redisKey,String loanInfoId, String operator, String flag,String changeType) throws Exception;
	
	public ListResult<RepaymentSingleDistributeDetailDTO> queryHistoryRepaymentDetailFromDB(String planId) throws Exception;
	
	public void submitValidate(String redisKey) throws Exception;
	
	public void redirectValidate(String redisKey) throws Exception;
}
