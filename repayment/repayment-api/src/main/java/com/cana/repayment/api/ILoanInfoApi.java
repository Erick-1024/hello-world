package com.cana.repayment.api;

import java.util.List;

import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.repayment.dto.LoanInfoElementsDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisIntegration;
import com.cana.vbam.common.repayment.dto.LoanInfoSearchCriteriaDTO;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.InputMethod;

public interface ILoanInfoApi {
	/**
	 * 选择放款信息录入模式，返回redisKey
	 * @param businessMode
	 * @param inputMethod
	 * @return key
	 * @throws Exception
	 */
	public String generateRecordToRedis(BusinessMode businessMode, InputMethod inputMethod,String operatorId);
	
	/**
	 * 删除redis的记录
	 * @param key
	 * @throws Exception
	 */
	public void deleteRecordFromRedis(String key) throws Exception;
	
	/**
	 * 批量保存到redis
	 * @param masterId
	 * @param key
	 * @param loanInfoRedisDTOs
	 * @throws Exception
	 */
	public void batchSaveToRedis(String masterId, String key, List<LoanInfoRedisDTO> loanInfoRedisDTOs) throws Exception;
	
	/**
	 * 单个保存到redis
	 * @param masterId
	 * @param key
	 * @param loanInfoRedisDTO
	 * @throws Exception
	 */
	public void singleSaveToRedis(String masterId, String key, LoanInfoRedisDTO loanInfoRedisDTO) throws Exception;
	
	/**
	 * 查询放款信息编号是否存在
	 * @param loanInfoNo
	 * @param factorId
	 * @return
	 */
	public boolean isLoanNoExist(String loanNo, String factorId);
	
	/**
	 * 查询redis中的放款信息
	 * @param rediskey
	 * @return
	 */
	public ListResult<LoanInfoRedisDTO> queryLoanInfoFromRedis(String key, String status, int page, int pageSize);
	
	/**
	 * 查询redis中的记录
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public LoanInfoRedisIntegration queryLoanInfoRedisIntegrationFromRedis(String key)throws Exception;

	/**
	 * 查询redis中的记录
	 * @param key
	 * @param loanNo
	 * @throws Exception
	 */
	public LoanInfoRedisDTO queryOneLoanInfoFromRedis(String key, String loanNo)throws Exception;
	
	/**
	 * 删除redis中的记录
	 * @param key
	 * @param loanNo
	 * @throws Exception
	 */
	public void deleteLoanInfoFromRedis(String key, String loanNo)throws Exception;
	
	/**
	 * 修改redis中的记录
	 * @param masterId
	 * @param key
	 * @param loanNo
	 * @throws Exception
	 */
	public void modifyLoanInfoFromRedis(String masterId, String key, LoanInfoRedisDTO loanInfoRedisDTO)throws Exception;
	
	/**
	 * 将redis中通过的放款信息存入数据库
	 * @param loanInfoRedisDTOs
	 * @param factorId
	 * @param businessMode
	 * @param inputMethod
	 * @throws Exception
	 */
	public void saveLoanInfoListToDB(LoanInfoRedisIntegration loanInfoRedisIntegration,String factorId) throws Exception;
	
	/** 
	 * 按条件查询未拥有还款计划的还款信息
	 * @param loanInfoSearchCriteriaDTO
	 * @param masterId
	 * @return
	 * @throws Exception
	 */
	public ListResult<LoanInfoRedisDTO> queryLoanInfoListFromDB(String masterId, LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO)throws Exception;
	
	/**
	 * 按监管关系id查询放款信息
	 * @param page
	 * @param pageSize
	 * @param accountSupervisionId
	 * @return
	 * @throws Exception
	 */
	public ListResult<LoanInfoRedisDTO> queryLoanInfoListByAccountSupervisionId(int page, int pageSize, String accountSupervisionId)throws Exception;
	
	/**
	 * 按监管关系id查询融资余额总和
	 * @param accountSupervisionId
	 * @return
	 */
	public Long queryTotalFinanceBalance(String accountSupervisionId);
	
	/**
	 * 按条件查询放款款信息
	 * 分为3种用户类型 CANA，FACTOR,FINACE
	 * @param masterId
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 * @throws Exception
	 */
	public ListResult<LoanInfoRedisDTO> queryLoanInfoList(String masterId,LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO)throws Exception;
	/**
	 * 按id查询放款信息详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public LoanInfoRedisDTO queryLoanInfodetailFromDB(String id)throws Exception;
	
	/**
	 * 查询所有的监管账户
	 * @param factorId，保理商ID
	 * @param finaceName，融资商企业名称
	 */
	public List<AccountDTO> queryRepaymentAccounts(String factorId, String finaceName);
	
	/**
	 * 获取默认的监管账户
	 * @param factorId
	 * @param finaceName
	 * @return
	 */
	public AccountDTO getDefaultAccount(String factorId, String finaceName);
	
	/**
	 * 更新redis中放款信息的还款帐号
	 * @param redisKey
	 * @param loanInfoRedisDTO
	 * @throws Exception
	 */
	public void updateLoanInfoAccountNo(String redisKey,LoanInfoRedisDTO loanInfoRedisDTO)throws Exception;
	
	/**
	 * 根据放款信息Id获取放款要素
	 * @param loanId
	 * @return
	 * @throws Exception
	 */
	public LoanInfoElementsDTO getLoanInfoElements(String loanId)throws Exception;
	
	/**
	 * 跟新数据库中的某条放款信息的还款帐号
	 * @param loanInfoRedisDTO
	 * @throws Exception
	 */
	public void updateAccountNoToDB(LoanInfoRedisDTO loanInfoRedisDTO)throws Exception;
	
	/**
	 * 主动还款权限按钮
	 * @param loanInfoId
	 * @return
	 */
	public boolean accessToActiveRepayment(String loanInfoId);
	
}
