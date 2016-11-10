package com.cana.account.service;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.account.dto.AccountPrintCodeDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeResultDTO;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.AccountTradeRecordBasicInfo;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;

/**
 * 账户交易相关业务处理
 * @author XuMeng
 *
 */
public interface IAccountTradeService {

    public AccountTradeStatus transferFund(TransferFundRequestDTO transferFundRequest);

    public AccountTradeStatus withdrawFund(WithdrawFundRequestDTO withdrawFundRequest);

    /**
     * 提现(韵达用)
     * @param withdrawFundRequest
     * @return
     */
    public AccountTradeStatus withdrawFundForYundaEx(WithdrawFundRequestDTO withdrawFundRequest);

    public AccountTradeStatus deductFund(DeductFundRequestDTO request);
    public AccountTradeStatus transferFundForCredit(TransferFundForCreditRequestDTO request);
    
    public void auditTransferFund(AccountTradeAuditResult tradeAuditResult);

    public void auditWithdrawFund(AccountTradeAuditResult tradeAuditResult);
    
    /**
     * 查询账户余额
     * @param accountNo
     * @return
     */
    public  List<BankAccountBalanceDataDTO>  queryAccountBalance(String customerId,String... accountNoStrings);
    
    /**
     * 验证账号是否可交易
     * @param customerId
     * @param accountNo
     * @param types
     */
    public void checkAccountTradeAble(String customerId, String accountNo, AccountTradeType... types);
    
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
     * 重新发起提现请求
     * @param tradeRecordId
     */
    public void relaunchingWithdrawOperate(String tradeRecordId) throws Exception;

    public AccountTradeRecordBasicInfo queryTradeRecordBasicInfo(String business);

    /**
     * 打印码列表
     * @param codeDTO
     * @return
     * @throws Exception 
     */
	public List<AccountPrintCodeResultDTO> queryBankTradeDetailNonLogin(AccountPrintCodeDTO codeDTO) throws Exception;
}
