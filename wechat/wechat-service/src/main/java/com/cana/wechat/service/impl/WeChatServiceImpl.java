package com.cana.wechat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.account.service.utils.BankgateHelperUtil;
import com.cana.bankgate.api.BankgateApi;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.wechat.account.AccountWechatDTO;
import com.cana.vbam.common.wechat.account.AccountWechatDetailDTO;
import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.cana.vbam.common.wechat.member.user.CustomerWechatDetailDTO;
import com.cana.wechat.dao.mapper.TradeRecordCustomMapper;
import com.cana.wechat.service.IWeChatService;
import com.cana.wechat.service.converter.WeChatConverter;
import com.cana.wechat.service.converters.WechatAccountConverter;
import com.cana.wechat.service.converters.WechatCustomerConverter;
import com.travelzen.framework.core.util.MoneyUtil;

@Service
public class WeChatServiceImpl implements IWeChatService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IUserApi userApi;
	
//	@Resource
//	private IAccountApi accountApi;
	
	@Resource
	private IAccountTransactionService accountTransactionServiceImpl;
	
	@Resource(name = "bankgateApi")
	private BankgateApi bankgateApi;
	
	@Resource
	private TradeRecordCustomMapper tradeRecordCustomMapper;
	
	@Resource
	private WeChatConverter weChatConverter;
	
	@Override
	public String sayHello(String name) {
		return "hello" + name;
	}

	@Override
	public CustomerWechatDetailDTO queryCustomerDetail(String customerId) {
		CustomerDetailDTO queryCustomerDetail = userApi.queryCustomerDetail(customerId);
		CustomerWechatDetailDTO customerWechatDetailDTO = WechatCustomerConverter.converterCustomerDetail2WechatDetail(queryCustomerDetail);
		return customerWechatDetailDTO;
	}

	@Override
	public boolean modifyContactsInfo(String userId, String contactName, String jobTitle, String mobileNum,
			String mail) throws Exception {
		return userApi.modifyContactsInfo(userId, contactName, jobTitle, mobileNum, mail);
	}

	@Override
	public AccountWechatDTO getAccountInfo(String customerId){
		AccountWechatDTO accountWechatDTO = new AccountWechatDTO();
		// 查询该用户下所有账户信息
		List<AccountDTO> accountDTOs = accountTransactionServiceImpl.queryAccountsByCustomerId(customerId);
		Long accountBalanceTotal = 0l;
		Long NoSupervisionAccountBalanceTotal = 0l;
		int NoSupervisionAccountNumber = 0;
		Long HaveSupervisionAccountBalanceTotal = 0l;
		int HaveSupervisionAccountNumbe = 0;
		List<AccountWechatDetailDTO> AccountDetailDTOs = new ArrayList<>();
		for(AccountDTO accountDTO : accountDTOs){
			AccountDTO account = accountTransactionServiceImpl.getAccount(customerId, accountDTO.getAccountId());
			BankAccountBalanceDataDTO accountBalanceData = queryAccountBalanceByWechat(customerId, accountDTO.getAccountNo());
			if(null != accountBalanceData){
				// 账户总金额只显示用户自己的账户金额
				if(customerId.equals(account.getCompanyId())){
					accountBalanceTotal += accountBalanceData.getAvailableBalance();
				}
				if(account.getSupervisionStatus().equals(AccountSupervisionStatus.NO_SUPERVISION)){
					NoSupervisionAccountBalanceTotal += accountBalanceData.getAvailableBalance();
					NoSupervisionAccountNumber ++;
				}
				if(account.getSupervisionStatus().equals(AccountSupervisionStatus.HAVE_SUPERVISION)){
					HaveSupervisionAccountBalanceTotal += accountBalanceData.getAvailableBalance();
					HaveSupervisionAccountNumbe ++;
				}
				AccountWechatDetailDTO accountDetailDTO = WechatAccountConverter.converterAccountDTO2WechatDetail(account);
				accountDetailDTO.setAccountBalance(MoneyUtil.cent2Yuan(accountBalanceData.getAvailableBalance()));
				AccountDetailDTOs.add(accountDetailDTO);
			}else{
				if(account.getSupervisionStatus().equals(AccountSupervisionStatus.NO_SUPERVISION)){
					NoSupervisionAccountNumber ++;
				}
				if(account.getSupervisionStatus().equals(AccountSupervisionStatus.HAVE_SUPERVISION)){
					HaveSupervisionAccountNumbe ++;
				}
				AccountWechatDetailDTO accountDetailDTO = WechatAccountConverter.converterAccountDTO2WechatDetail(account);
				// 账户余额查询失败 返回余额0元
				accountDetailDTO.setAccountBalance(MoneyUtil.cent2Yuan(0l));
				AccountDetailDTOs.add(accountDetailDTO);
			}
		}
		accountWechatDTO.setAccountBalanceTotal(MoneyUtil.cent2Yuan(accountBalanceTotal));
		accountWechatDTO.setNoSupervisionAccountBalanceTotal(MoneyUtil.cent2Yuan(NoSupervisionAccountBalanceTotal));
		accountWechatDTO.setNoSupervisionAccountNumber(NoSupervisionAccountNumber);
		accountWechatDTO.setHaveSupervisionAccountBalanceTotal(MoneyUtil.cent2Yuan(HaveSupervisionAccountBalanceTotal));
		accountWechatDTO.setHaveSupervisionAccountNumbe(HaveSupervisionAccountNumbe);
		accountWechatDTO.setAccountDetailDTOs(AccountDetailDTOs);
		return accountWechatDTO;
	}
	
	private BankAccountBalanceDataDTO queryAccountBalanceByWechat(String customerId, String accountNo) {
		BankAccountBalanceResultDTO bankAccountBalanceResultDTO = bankgateApi.queryAccountBalanceByWechat(customerId, accountNo);
		AccountTradeStatus status = BankgateHelperUtil.parseStatus(bankAccountBalanceResultDTO.getStatus());
		if (status == AccountTradeStatus.TRADE_SUCCESS && CollectionUtils.isNotEmpty(bankAccountBalanceResultDTO.getBankAccountBalanceDatas())) {
			BankAccountBalanceDataDTO bankData = bankAccountBalanceResultDTO.getBankAccountBalanceDatas().get(0);
			return bankData;
		}
		logger.error("微信端网关查询账户余额失败！账号：{}，错误原因：{}", accountNo, bankAccountBalanceResultDTO.getStatusText());
		return null;
	}

	/**
	 * 流水明细 查询条件转换
	 * @param customer
	 * @param criteria
	 * @return
	 */
	private void updateTradeRecordRequest(CustomerDetailDTO user,TradeRecordRequest request){
		request.setPageSize(request.getPageSize() < 1 ? 10 : request.getPageSize());
		
		List<AccountTradeType> accountTradeTypes = new ArrayList<>(Arrays.asList(AccountTradeType.TRANSFER_FUND,AccountTradeType.WITHDRAW_FUND));
    	request.setTradeTypes(accountTradeTypes);
    	
		if (UserType.FACTOR.equals(user.getUserType())) 
			request.setFactorId(request.getCustomerId());
		if (UserType.FINACE.equals(user.getUserType())) 
			request.setFinaceId(request.getCustomerId());
		if (UserType.CORECOMPANY.equals(user.getUserType()))
			request.setCoreCompanyId(request.getCustomerId());
	}

	@Override
	public void updateContactName(String userId, String contactName) {
		userApi.updateContactName(userId, contactName);
	}

	@Override
	public void updateContactTel(String userId, String mobileNum) {
		userApi.updateContactTel(userId, mobileNum);
	}

	@Override
	public void updateContactMail(String userId, String mail) {
		userApi.updateContactMail(userId, mail);
	}

	@Override
	public void updateContactJobTitle(String userId, String jobTitle) {
		userApi.updateJobTitle(userId, jobTitle);
	}
}
