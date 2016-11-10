package com.cana.account.api;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.account.dto.AccountAgentCreateDTO;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.dto.AccountApplyDTO;
import com.cana.vbam.common.account.dto.AccountApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountBalancesAndNumberDTO;
import com.cana.vbam.common.account.dto.AccountCustomerTradeRecordQueryDTO;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeResultDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.dto.AccountSelfCreateDTO;
import com.cana.vbam.common.account.dto.AccountSupervisionCreateDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.AccountTradeRecordBasicInfo;
import com.cana.vbam.common.account.dto.AccountTradeRecordCriteria;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.dto.BankAccountGroupDTO;
import com.cana.vbam.common.account.dto.BankBranchInfoDTO;
import com.cana.vbam.common.account.dto.BranchNameQueryCriteria;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.travelzen.framework.core.exception.WebException;

public interface IAccountApi {

	/**
	 * 查询开户申请列表
	 */
	public PageResult<AccountApplyDTO> queryAccountApplys(AccountApplyQueryCriteria accountApplyQueryCriteria);

	/**
	 * 获取开户申请详情信息
	 */
	public AccountApplyDTO getAccountApply(String accountApplyId);

	/**
	 * 检查该申请是否是待审核状态
	 * @param accountApplyId
	 */
	public void checkAccountApplyStatus(String accountApplyId);
	
	/**
	 * 判断融资客户的买方名称是否可否（不能重名，包括该融资客户已经存在的买家）
	 * @param finaceName
	 * @param buyerNames
	 * @return 返回错误类型（IllegalBuyerType）和对应的买家名称
	 */
	public Map<String, List<String>> checkInvalidBuyerNames(String finaceName, List<String> buyerNames);

	/**
	 * 主动开户
	 */
	public List<String> createAccountBySelf(AccountSelfCreateDTO accountSelfCreateDTO);
	
	/**
	 * 代开户
	 */
	public void createAccountByAgent(AccountAgentCreateDTO accountAgentCreateDTO);

    /**
     * 审核
     */
    public void auditAccountApply(AccountApplyAuditDetail auditDetail);
	
	/**
	 * 查询账户列表
	 * @param customerId 当前登录客户
	 */
	public PageResult<AccountDTO> queryAccounts(String customerId,
	        AccountQueryCriteria accountQueryCriteria);

	/**
	 * 查询融资管理还款账户，账户类型是一般，账户状态是正常
	 * @param factorId，保理商ID
	 * @param finaceName，融资商企业名称
	 */
	public List<AccountDTO> queryRepaymentAccounts(String factorId, String finaceName);
	
	/**
	 * 根据Id查询账户详情
	 * @param customerId 当前登录客户
	 */
	public AccountDTO getAccount(String customerId, String accountId);
    /**
     * 通过账号查询账户详情，且账户的拥有者必须是当前客户，否则返回空
     * @param customerId 当前登录客户
     */
    public AccountDTO getOwnAccountByNo(String customerId, String accountNo);
    
    /**
     * 通过客户名和账号查找账户信息
     */
    public AccountDTO getOwnAccountByNameAndNo(String customerName,String accountNo);
    
    /**
     * 通过账号查询账户列表
     * 此接口不校验 customerId 与账号的关系
     */
    public List<AccountDTO> getAccountByNos(String customerId, String... accountNos);

    /**
     * 新建监管关系且不需要对方审核
     * @param applyUserId 当前申请的员工Id
     * @param accountNo 监管账号，必须是一般正常非监管账户，且属于当前申请客户
     * @param supervisionCustomerId 监管企业客户ID
     * @return 是否成功
     * @throws WebException 不成功时抛异常
     */
    public boolean createSupervisionWithoutAudit(String applyUserId, String accountNo, String supervisionCustomerId);

    /**
     * 新建监管关系申请
     * @param userId 当前申请的员工Id
     * @param supervisionCreateDTO 创建监管关系的参数
     * @return 新建监管申请ID（accountSupervisionApplyId）
     * @throws WebException 新建申请不成功时抛异常
     */
    public String createSupervision(String userId, AccountSupervisionCreateDTO supervisionCreateDTO);
    /**
     * 解除监管关系申请
     * @param userId 当前申请解除的员工Id
     * @param accountIds 需要解除的监管账户ID列表，如果包含主账户，则解除所有监管账户
     * @return 解除监管申请ID（accountSupervisionApplyId）
     * @throws WebException 解除申请不成功时抛异常
     */
    public String removeSupervision(String userId, List<String> accountIds);

    /**
     * 获取监管申请详情
     * @param userId，当前登录员工ID
     * @param supervisionApplyId，监管申请ID
     */
    public AccountTradeApplyDTO getSupervisionApply(String userId, String supervisionApplyId);

    /**
     * 获取交易申请，现在交易申请包括转帐和提现申请
     * @param userId
     * @param applyId
     * @return
     */
    public AccountTradeApplyDTO getTradeApply(String userId,String applyId);
    
    /**
     * 审核新建或解除监管关系申请
     * @param auditUserId 当前登录员工
     * @param accountSupervisionApplyId 监管关系申请ID
     * @param isAgree 是否通过
     * @param message  审核意见
     * @return 返回是否审核成功
     * @throws WebException 审核失败时抛异常
     */
    public boolean auditSupervision(String auditUserId,
            String accountSupervisionApplyId, boolean isAgree,
            String message);
    
    /**
     * 根据账户Id查询账户的收支明细
     * @param customerId
     * @param accountId
     * @return
     */
    public PageResult<AccountTradeRecordDTO> getTradeRecord(String customerId, AccountTradeRecordCriteria accountTradeRecordCriteria);
    
    /**
     * 账户冻结
     */
    public boolean accountFreeze(String userId, String customerId, String accountId);
    
    /**
     * 账户解冻
     */
    public boolean accountUnfreeze(String userId, String customerId, String accountId);
    
    /**
     * 设为默认账户
     */
    public boolean accountSetDefaultRepayment(String customerId, String accountId);
    
    
    /**
     * 查询 流水明细
     * @param customerId
     * @param accountTradeRecordCriteria
     * @return
     */
    public PageResult<AccountTradeRecordDTO> queryTradeRecords(String customerId, AccountTradeRecordCriteria criteria);

    /**
     * 转账
     * @param transferFundRequest
     * @return
     */
    public AccountTradeStatus transferFund(TransferFundRequestDTO transferFundRequest);

    /**
     * 提现
     * @param withdrawFundRequest
     * @return
     */
    public AccountTradeStatus withdrawFund(WithdrawFundRequestDTO withdrawFundRequest);
    
    /**
     * 提现(韵达使用)
     * @param withdrawFundRequest
     * @return
     */
    public AccountTradeStatus withdrawFundForYundaEx(WithdrawFundRequestDTO withdrawFundRequest);
    
    /**
     * 审核转帐
     * @param auditResult
     */
    public void auditTransferFund(AccountTradeAuditResult auditResult);
    
    /**
     * 审核提现
     * @param auditResult
     */
    public void auditWithdrawFund(AccountTradeAuditResult auditResult);
    
    /**
     * 查询账户余额
     * @param customerId
     * @param accountNos 
     * @return accountNo为键
     */
    public List<BankAccountBalanceDataDTO> queryAccountBalance(String customerId,String... accountNos);

    /**
     * 根据条件查询审核列表
     * @param userId，当前请求员工id
     * @param criteria
     */
    public PageResult<AccountTradeApplyDTO> queryTradeApplys(String userId, AccountTradeApplyQueryCriteria criteria);
    
    /**
     * 验证对账号是否有交易的权限
     * @param customerId
     * @param accountNo
     * @param types
     */
    public void checkAccountTradeAble(String customerId,String accountNo, AccountTradeType... types);
    
    /**
     * 账扣，请求参数不可为空，
     * @param 交易流水号，生成规则：DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.BUSINESS_SEQ, 5)
     * @param 转出账号，必须是一般、监管账号
     * @param 转入账号，必须是转出账号中监管的保理商的一般账号，2016-4-27去掉了必须是非监管的限制条件
     * @param 转入金额，单位人民币分，必需为正数
     * @return 返回交易状态，其中如果请求银行接口出现错误时，返回银行接口请求中状态
     * @throws WebException 若参数不满足要求，则抛出此异常
     */
    public AccountTradeStatus deductFund(DeductFundRequestDTO request);

    /**
     * 强制转账接口，此接口不校验操作权限以及账户的状态等
     * @param 交易流水号，需保证全局唯一，生成规则：DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.BUSINESS_SEQ, 5)
     * @throws WebException 若参数不满足要求，则抛出此异常，参数要求如下：
     * <p> 金额必须大于0；交易流水不能已存在；转出账号和转入账号必须是凯拿账户管理模块必须存在的虚拟银行账号。
     */
    public AccountTradeStatus transferFundForCredit(TransferFundForCreditRequestDTO request);

    /**
     * 通过保理商ID返回当前所有存在监管关系的融资商
     * 返回数据中只有id和companyName两个字段有值
     */
    public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId);
    
    /**
     * 获取给定用户的账户余额和数量
     * @param customerId
     * @return
     */
    public AccountBalancesAndNumberDTO getAccountBalancesAndNumber(String customerId);

    /**
     * 设置保理商回款账户，如果账户已经是回款账户了，则返回true
     * 若校验参数失败，则抛WebException异常
     */
    public boolean setFactorTransferInAccount(String factorId, String accountNo);
    
    /**
     * 分页获取属于自己的主账号和附属账号组集合
     */
    public List<AccountGroupDTO> getOwnAccountGroups(String customerId);
    
    /**
     * 分页获取不属于自己但被自己监管的别人的主账号和附属账号组集合 
     */
    public List<AccountGroupDTO> getOtherSupervisionAccountGroups(String customerId);
    
    /**
     * 获取银行登录用户名为KEY,主账号集合为VALUE，该接口是为了方便后期维护，目前只在报表用到
     */
    public List<BankAccountGroupDTO> getBankAccountGroups();
    
    /**
     * 查询客户的交易记录
     */
    public List<AccountTradeRecordDTO> getCustomerTradeRecord(AccountCustomerTradeRecordQueryDTO query);
    
    /**
     * 批量查询转账状态
     * @param businessSeqs 业务流水号
     * @return
     */
    public Map<String, String> batchQueryTradeStatus(List<String> businessSeqs);
    
    /**
     * 查询转账状态
     * @param business 业务流水号
     * @return
     */
    public String queryTradeStatus(String business);

    /**
     * 查询转账状态
     * @param business 业务流水号
     */
    public AccountTradeRecordBasicInfo queryTradeRecordBasicInfo(String business);
    
    public String generateBusinessSeq();
    
    /**
     * 按省份查询城市列表
     * @param province
     * @return
     */
    public List<String> getCitiesByProvince(String province);
    
    /**
     * 查询所有符合条件的支行信息
     * @param queryCriteria
     * @return
     */
    public ListResult<BankBranchInfoDTO> queryBranchInfo(BranchNameQueryCriteria queryCriteria);
    
    /**
     * 重新发起提现请求
     * @param tradeRecordId
     */
    public void relaunchingWithdrawOperate(String tradeRecordId) throws Exception;

    /**
     * 打印列表
     * @param codeDTO
     * @return
     * @throws Exception 
     */
	public List<AccountPrintCodeResultDTO> queryAccountPrintCode(AccountPrintCodeDTO codeDTO) throws Exception;
    
}
