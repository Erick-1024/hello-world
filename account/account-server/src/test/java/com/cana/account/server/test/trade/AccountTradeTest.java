package com.cana.account.server.test.trade;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.service.IAccountTradeService;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.AccountTradeRecordBasicInfo;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class AccountTradeTest {

    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountTableLockMapper accountTableLockMapper;

    @Resource
    private IAccountTradeService accountTradeService;
    
    @Resource
    private IAccountAuthorityTransactionService accountAuthorityTransactionService;

    @Test
    public void createTransferTradeTest() {
        TransferFundRequestDTO transferFundRequest = new TransferFundRequestDTO();
        transferFundRequest.setUserId(factorId);
        transferFundRequest.setAccountNo("3110210003631007234");
        transferFundRequest.setReceiveAccountNo("3110210003631007742");
        transferFundRequest.setAmount("1");
        
        accountTradeService.transferFund(transferFundRequest);
    }
    @Test
    public void auditTransferTradeTest() {
        AccountTradeAuditResult tradeAuditResult = new AccountTradeAuditResult();
        tradeAuditResult.setAuditStatus(AccountTradeApplyStatus.ACCEPTED);
        tradeAuditResult.setAccountTradeApplyId("151218145131501");
        tradeAuditResult.setAuditUserId(finaceId);
        accountTradeService.auditTransferFund(tradeAuditResult);
    }

    @Test
    public void createWithdrawTradeTest() {
        WithdrawFundRequestDTO withdrawFundRequest = new WithdrawFundRequestDTO();
        withdrawFundRequest.setUserId(factorId);
        withdrawFundRequest.setAccountNo("3110210003631007234");
        withdrawFundRequest.setAmount("1");;
        withdrawFundRequest.setReceiveAccountNo("3110210003631007232");
        withdrawFundRequest.setReceiveBankName("渣打银行");
        accountTradeService.withdrawFund(withdrawFundRequest);
    }
    @Test
    public void auditWithdrawTradeTest() {
        AccountTradeAuditResult tradeAuditResult = new AccountTradeAuditResult();
        tradeAuditResult.setAuditStatus(AccountTradeApplyStatus.ACCEPTED);
        tradeAuditResult.setAccountTradeApplyId("160106102158301");
        tradeAuditResult.setAuditUserId(finaceId);
        accountTradeService.auditWithdrawFund(tradeAuditResult);
    }
    
    @Test
    public void deductTradeTest() {
        DeductFundRequestDTO request = new DeductFundRequestDTO();
        request.setBusinessSeq(AccountIDGenerator.generateBusinessSeq());
        request.setAmount(500);
        request.setTransferOutAccountNo("3110210003631007234");
        request.setTransferInAccountNo("3110210003631007413");
        AccountTradeStatus status = accountTradeService.deductFund(request);
        System.out.println(status);
    }

    @Test
    public void testQueryRecord() {
    	AccountTradeRecordBasicInfo tradeRecordBasicInfo = accountTradeService.queryTradeRecordBasicInfo("16040717512800402");
    	System.out.println(new Gson().toJson(tradeRecordBasicInfo));
    }

    @Test
    public void tradeRuleTest() {
        User customer = userMapper.selectByPrimaryKey("201512211517");
        Account outAccount = accountTableLockMapper.lockAccountByAccountNo("3110210003631007672");
        Account inAccount = accountTableLockMapper.lockAccountByAccountNo("3110210003631007642");
        accountAuthorityTransactionService.checkTransferAuthority(customer, outAccount, inAccount);
    }
    
    static String factorId = "201511300000";
    static String finaceId = "201511300001";
}
