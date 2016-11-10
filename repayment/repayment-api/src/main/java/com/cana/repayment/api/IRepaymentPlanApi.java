package com.cana.repayment.api;

import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.dto.LoanInfoSearchCriteriaDTO;
import com.cana.vbam.common.repayment.dto.RepaymentDetailsHistoryDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseDBDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanDBDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanInfoRedisIntegration;
import com.cana.vbam.common.repayment.dto.RepaymentPlanRedisDTO;

public interface IRepaymentPlanApi {
	
	/**
	 * 获取还款信息以及还款费用集合
	 * @param redisKey
	 * @return
	 * @throws Exception
	 */
	public RepaymentPlanInfoRedisIntegration getRepaymentPlanInfoRedisIntegration(String redisKey) throws Exception;

	/**
	 * 选择放款信息录入模式，返回redisKey
	 * @param businessMode
	 * @param inputMethod
	 * @param loanInfoId
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	public String saveRepaymentPlanRedisWithModeAndMethod(String businessMode, String inputMethod, String loanInfoId, String operatorId) throws Exception;
	
	/**
	 * 单行还款计划保存到redis中
	 * @param redisKey
	 * @param repaymentPlanRedisDTO
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public void saveRepaymentPlanRedisDTOSingleLine(String redisKey,RepaymentPlanRedisDTO repaymentPlanRedisDTO,String masterId, String id) throws Exception;
	
	/**
	 * 单行还款费用保存到redis中
	 * @param redisKey
	 * @param repaymentExpenseRedisDTO
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public void saveRepaymentExpenseRedisDTOSingleLine(String redisKey,RepaymentExpenseRedisDTO repaymentExpenseRedisDTO,String masterId) throws Exception;
	
	/**
	 * Excel导入还款信息和还款费用保存到redis中
	 * @param redisKey
	 * @param repaymentPlanRedisDTOList
	 * @param repaymentExpenseRedisDTOList
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public void saveRepaymentPlanInfoRedisIntegration(String redisKey,List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList,List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOList,String masterId) throws Exception;
	
	/**
	 * 获取单行还款计划信息
	 * @param redisKey
	 * @param loanNo
	 * @return
	 * @throws Exception
	 */
	public RepaymentPlanRedisDTO queryRepaymentPlanRedisDTOSingleLine(String redisKey, String id) throws Exception;
	
	/**
	 * 获取单行还款费用信息
	 * @param redisKey
	 * @param loanNo
	 * @return
	 * @throws Exception
	 */
	public RepaymentExpenseRedisDTO queryRepaymentExpenseRedisDTOSingleLine(String redisKey, String id) throws Exception;
	
	/**
	 * 单行更新还款计划信息
	 * @param redisKey
	 * @param repaymentPlanRedisDTO
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public void updateRepaymentPlanRedisDTOSingleLine(String redisKey,RepaymentPlanRedisDTO repaymentPlanRedisDTO,String masterId,String id) throws Exception;
	
	/**
	 * 单行更新还款费用信息
	 * @param redisKey
	 * @param repaymentExpenseRedisDTO
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public void updateRepaymentExpenseRedisDTOSingleLine(String redisKey,RepaymentExpenseRedisDTO repaymentExpenseRedisDTO,String masterId) throws Exception;
	
	/**
	 * 单行删除还款计划信息
	 * @param redisKey
	 * @param loanNo
	 * @return
	 * @throws Exception
	 */
	public void deleteRepaymentPlanRedisDTOSingleLine(String redisKey, String id) throws Exception;
	
	/**
	 * 单行删除还款费用信息
	 * @param redisKey
	 * @param loanNo
	 * @return
	 * @throws Exception
	 */
	public void deleteRepaymentExpenseRedisDTOSingleLine(String redisKey, String id) throws Exception;
	
	/**
	 * 保存已通过检查数据到mysql中
	 * @param redisKey
	 * @throws Exception
	 */
	public void saveRepaymentPlanAndExpense(String redisKey,String masterId,String loanInfoIdForSave) throws Exception;
	
	/**
	 * 获取保存在数据库中的还款计划数据
	 * @param loanNo
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public List<RepaymentPlanRedisDTO> queryRepaymentPlanExist(String id) throws Exception;
	
	/**
	 * 获取保存在数据库中的还款费用数据
	 * @param loanNo
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public List<RepaymentExpenseRedisDTO> queryRepaymentExpenseRedisDTOExist(String id) throws Exception;
	
	/**
	 * 根据放款信息Id从数据库中获取还款计划
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentPlanDBDTO> queryRepaymentPlanFromDB(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO)throws Exception;
	
	/**
	 * 根据放款信息Id从数据库中获取费用列表
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 * @throws Exception
	 */
	public ListResult<RepaymentExpenseDBDTO> queryExpenseListFromDB(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO)throws Exception;
	
	/**
	 * 获取还款详情弹窗所需的信息
	 * @param repaymentPlanId
	 * @return
	 * @throws Exception
	 */
	public ObjectResult<RepaymentPlanDBDTO> queryRepaymentDetails(String repaymentPlanId)throws Exception;
	
	/**
	 * 根据放款信息Id从数据库中获取历史还款明细列表
	 * @param loanId
	 * @return
	 */
	public ListResult<RepaymentDetailsHistoryDTO> queryRepaymentDetailsHistory(String loanId)throws Exception;
	
	/**
	 * 获取当期增加的期数
	 * @param redisKey
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ObjectResult<String> getRepaymentPeriod(String redisKey, String id)throws Exception;
	
	
	/**
	 * 清空Redis以及判定该放款信息是否是当前用户可操作的
	 * @param redisKey
	 * @param id
	 * @throws Exception
	 */
	public void prepareForManualInput(String redisKey, String id, String operatorId) throws Exception;
	
	/**
	 * 根据还款汇总记录id获取还款明细
	 * @param repaymentSingleCollectId
	 * @return
	 */
	public String getAmountDetails(String repaymentSingleCollectId);
}
