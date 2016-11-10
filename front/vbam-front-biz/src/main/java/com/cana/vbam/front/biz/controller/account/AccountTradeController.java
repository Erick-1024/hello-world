/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.front.biz.controller.account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IRoleApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.ILoanInfoApi;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountFundInfoDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.AccountTradeRecordCriteria;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.dto.BankBranchInfoDTO;
import com.cana.vbam.common.account.dto.BranchNameQueryCriteria;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.cana.vbam.common.utils.WebEnv;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
@Controller
@RequestMapping(value = "/account/trade")
public class AccountTradeController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IUserApi userApi;
	@Resource
	private IRoleApi roleApi;
	@Resource
	private ILoanInfoApi loanInfoApi;
	
	@RequestMapping(value = "/transferFund", method= {RequestMethod.GET})
	public ModelAndView transferFundView(ModelAndView mv) throws Exception{
		mv.setViewName("page/account/trade/transferFund");
		mv.addObject("accountTradeType", AccountTradeType.TRANSFER_FUND);
		return mv;
	}
	
	@RequestMapping(value = "/transferFund", method= {RequestMethod.POST})
	public @ResponseBody ObjectResult<?> transferFund(String verifyCode, TransferFundRequestDTO transfer) {
		if (!checkVerifyCode(verifyCode)){
			return ObjectResult.fail("验证码不正确!");
		}
		if(StringUtils.isEmpty(transfer.getAccountNo())){
			return ObjectResult.fail("转出账号不能为空");
		}
		if(StringUtils.isEmpty(transfer.getReceiveAccountNo())){
			return ObjectResult.fail("收款账号不能为空");
		}
	    if(transfer.getAccountNo().equals(transfer.getReceiveAccountNo())){
	    	return ObjectResult.fail("转出账号和收款账号不能相同");
	    }
		if(StringUtils.isBlank(transfer.getPayPassword())){
			logger.error("支付密码不能为空");
			return ObjectResult.fail("支付密码不能为空");
		}
		ObjectResult<?> exist = checkReceiveAccountAndName(transfer.getReceiveAccountNo(),transfer.getReceiveAccountName());
		if(exist.getStatus() == AjaxResponseStatus.FAILED){
			return exist;
		}
		String customerId = SecurityContextUtils.getCustomerId();
		try {
			Boolean payPass = userApi.validatePayPwd(customerId, PasswordEncoderUtil.encrypt(transfer.getPayPassword()));
			if(!payPass){
				return ObjectResult.fail("支付密码不正确");
			}
			transfer.setUserId(customerId);
			AccountTradeStatus status = accountApi.transferFund(transfer);
			if(AccountTradeStatus.TRADE_FAIL == status){
				return ObjectResult.fail("交易失败，请咨询客服");
			}else{
				Map<String, Object> result = Maps.newHashMap();
				result.put("result", status);
				return ObjectResult.success("转账成功", result);
			}
		} catch (WebException e) {
            logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail("系统错误");
		}
	}
	
	@RequestMapping(value = "/withdrawFund", method= {RequestMethod.GET})
	public ModelAndView withdrawFundView(ModelAndView mv) throws Exception {
		mv.addObject("accountTradeType", AccountTradeType.WITHDRAW_FUND);
		mv.setViewName("page/account/trade/withdrawFund");
		return mv;
	}
	
	@RequestMapping(value = "/withdrawFund", method= {RequestMethod.POST})
	public @ResponseBody ObjectResult<?> withdrawFund(String verifyCode, WithdrawFundRequestDTO withdraw) {
		if (!checkVerifyCode(verifyCode)) {
			return ObjectResult.fail("验证码不正确!");
		}
		if (StringUtils.isBlank(withdraw.getPayPassword())) {
			logger.error("支付密码不能为空");
			return ObjectResult.fail("支付密码不能为空");
		}
		String msg = checkReceiveAccountNameMsg(withdraw.getReceiveBankName());
		if (msg != null) {
			return ObjectResult.fail(msg);
		}
//		if (!AccountNoUtil.luhnBankAccountNoPass(withdraw.getReceiveAccountNo())) {
//			return ObjectResult.fail("账号不存在");
//		}
		String customerId = SecurityContextUtils.getCustomerId();
		try {
			Boolean payPass = userApi.validatePayPwd(customerId, PasswordEncoderUtil.encrypt(withdraw.getPayPassword()));
			if (!payPass) {
				return ObjectResult.fail("支付密码不正确");
			}
			withdraw.setUserId(customerId);
			AccountTradeStatus status = accountApi.withdrawFund(withdraw);
			if (AccountTradeStatus.TRADE_FAIL == status) {
				return ObjectResult.fail("交易失败，请咨询客服");
			} else {
				Map<String, Object> result = Maps.newHashMap();
				result.put("result", status);
				return ObjectResult.success("提现成功", result);
			}
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail("系统错误");
		}
	}
	
	/**
	 * 获取当前用户是否设置支付密码
	 * @return
	 */
	@RequestMapping(value = "/checkSetPayPassword", method= {RequestMethod.POST})
	public @ResponseBody ObjectResult<?> checkSetPayPassword() {
		Map<String, Object> result = Maps.newHashMap();
		try {
			String isSetPayPassword = userApi.isSetPayPassword(SecurityContextUtils.getCustomerId())?"true":"false";
			result.put("isSetPayPassword", isSetPayPassword);
			return ObjectResult.success("成功获取当前用户是否设置支付密码", result);
		} catch (WebException e) {
            logger.error(e.getMessage(), e);
            return ObjectResult.fail(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ObjectResult.fail("系统错误");
        }
	}
	
	@RequestMapping(value = "/checkAccountTradeAble")
	public @ResponseBody ObjectResult<?> checkAccountTradeAble(String accountNo, AccountTradeType tradeType) {
		if (StringUtils.isBlank(accountNo) || tradeType == null) {
			logger.info("参数非法!交易账号和交易类型不能为空!");
			return ObjectResult.fail("参数非法!交易账号不能为空!");
		}
		String customerId = SecurityContextUtils.getCustomerId();
		try{
			accountApi.checkAccountTradeAble(customerId, accountNo, tradeType);
			return queryAccountFundInfo(customerId, accountNo);
		}catch(WebException e){
			logger.error("",e);
			return ObjectResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error("",e);
			return ObjectResult.fail("系统错误");
		}
	}
	
	@RequestMapping(value = "/checkReceiveAccountAndName")
	public @ResponseBody ObjectResult<?> checkReceiveAccountAndName(String receiveAccountNo, String receiveAccountName) {
		AccountDTO account = null;
		try{
			account = accountApi.getOwnAccountByNameAndNo(receiveAccountName, receiveAccountNo);
			if(account == null)return ObjectResult.fail("账号和账户名不匹配");
			return ObjectResult.success();
		}catch(WebException e){
			logger.error("账号和账户名不匹配", e);
			return ObjectResult.fail("账号和账户名不匹配");
		}catch(Exception e){
			logger.error("系统异常", e);
			return ObjectResult.fail("系统异常");
		}
	}
	
	@RequestMapping(value = "/checkReceiveAccountName")
	public @ResponseBody ObjectResult<?> checkReceiveBankAccountName(String receiveAccountName) {
		String msg = checkReceiveAccountNameMsg(receiveAccountName);
		if(msg == null){
			return ObjectResult.success("收款行名称验证通过");
		}else{
			return ObjectResult.fail(msg);
		}
	}
	
	private String checkReceiveAccountNameMsg(String receiveAccountName) {
		List<String> banks = AccountConsts.withdraw_bank_limit;
		if (StringUtils.isBlank(receiveAccountName)) {
			logger.info("收款行名称不能为空");
			return "收款行名称不能为空";
		}
//		if (receiveAccountName.indexOf("支行") == -1) {
//			return "收款行名称必须精确到支行";
//		}
		for (String bank : banks) {
			if (receiveAccountName.indexOf(bank) != -1) {
				return null;
			}
		}
		return "暂不支持该行提现";
	}
	
	/**
	 * 根据银行账号 查询账户余额
	 * @param accountNoString
	 * @return
	 */
	@RequestMapping(value = "/queryAccountBalance")
	public @ResponseBody ObjectResult<?> queryAccountBalance(String accountNoString) {
		String[] accountNos = accountNoString.split(";");
		if(accountNos.length == 0){
			logger.info("银行账号不能为空!");
			return ObjectResult.fail("银行账号不能为空!");
		}
		String customerId = SecurityContextUtils.getCustomerId();
		try{
			Map<String, Object> result = Maps.newHashMap();
			String[] accountBalances = queryAccountBalance(customerId, accountNos);
			result.put("accountBalances", accountBalances);
			return ObjectResult.success("查询成功", result);
		}catch (WebException e) {
			logger.error("查询余额失败!", e);
			return ObjectResult.fail("查询余额失败!");
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ObjectResult.fail("系统错误");
        }
	}
	
	/**
	 * 根据监管关系id 查询融资余额
	 * @param accountSupervisionId
	 * @return
	 */
	@RequestMapping(value = "/queryFinaceBalance")
	@ResponseBody
	public String queryFinaceBalance(String accountSupervisionId) {
		Long finaceBalance = loanInfoApi.queryTotalFinanceBalance(accountSupervisionId);
		return MoneyUtil.cent2Yuan(finaceBalance);
	}
	
	
	@RequestMapping(value = {"/audit","/detail"}, method= {RequestMethod.GET})
	public ModelAndView auditTradeView(String tradeApplyId, ModelAndView mv) {
		String path = MemberAuthUtils.getRequest().getRequestURI();
		if (StringUtils.isBlank(tradeApplyId)) {
			logger.error("申请ID为空，数据非法!");
			throw WebException.instance("申请ID为空，数据非法!");
		}
		String userId = SecurityContextUtils.getCustomerId();
		AccountTradeApplyDTO applyDTO = accountApi.getTradeApply(userId, tradeApplyId);
		if (path.contains("/audit")) {
			mv.addObject("showAuditButtons", true);
		} else if (path.contains("/detail")) {
            mv.addObject("showAuditButtons", false);
		}
		boolean isPendingAudit = AccountTradeApplyStatus.PENDINGAUDIT.equals(applyDTO.getApplyStatus());
		mv.addObject("isPendingAudit",isPendingAudit);
		mv.addObject("applyByCurrentCustomer", applyDTO.getApplyCompanyId().equals(SecurityContextUtils.getCustomerId()));
		mv.addObject("title", applyDTO.getApplyType().desc());
		//一般 监管
		if(AccountType.GENERAL.equals(applyDTO.getAccountType()) && AccountSupervisionStatus.HAVE_SUPERVISION.equals(applyDTO.getSupervisionStatus())){          
			String finaceBalance = queryFinaceBalance(applyDTO.getAccountSupervisionId());
			applyDTO.setFinaceBalance(MoneyUtil.formatMoney(finaceBalance));
		}
		mv.addObject("applyDTO", formatApplyDTO(applyDTO));
		mv.setViewName("page/account/trade/auditTradeFund");
		return mv;
	}

	private AccountTradeApplyDTO formatApplyDTO(AccountTradeApplyDTO applyDTO) {
	    applyDTO.setAccountNo(AccountNoUtil.formatBankAccountNo(applyDTO.getAccountNo()));
        applyDTO.setOppositeAccountNo(AccountNoUtil.formatBankAccountNo(applyDTO.getOppositeAccountNo()));
        applyDTO.setAmount(MoneyUtil.formatMoney(applyDTO.getAmount()));
        applyDTO.setCharge(MoneyUtil.formatMoney(applyDTO.getCharge()));
        return applyDTO;
	}
	
	@RequestMapping(value = {"/audit"}, method= {RequestMethod.POST})
	public String auditTradeFund(AccountTradeApplyType applyType, AccountTradeAuditResult result) {
		if (StringUtils.isBlank(result.getAccountTradeApplyId())) {
			logger.info("交易申请号不能为空!");
			throw WebException.instance("交易申请号不能为空!");
		}
		result.setAuditUserId(SecurityContextUtils.getOperatorId());
		if (applyType == AccountTradeApplyType.TRANSFER_FUND) {
			accountApi.auditTransferFund(result);
		} else if (applyType == AccountTradeApplyType.WITHDRAW_FUND) {
			accountApi.auditWithdrawFund(result);
		} else {
			logger.info("传入参数非法!交易申请类型不匹配或为空!");
			throw WebException.instance("传入参数非法!");
		}
		return "redirect:/account/trade/detail?tradeApplyId=" + result.getAccountTradeApplyId();
	}
	
	/**
	 * 账户收支明细
	 * @param accountId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/tradeRecord")
	@ResponseBody
	public PageResult<AccountTradeRecordDTO> searchTradeRecord(AccountTradeRecordCriteria accountTradeRecordCriteria){
		try{
			PageResult<AccountTradeRecordDTO> result = accountApi.getTradeRecord(SecurityContextUtils.getCustomerId(), accountTradeRecordCriteria);
			return result;
		}catch(WebException e){
			logger.error("查询账户交易记录",e);
			return new PageResult<AccountTradeRecordDTO>();
		}catch(Exception e){
			logger.error("查询账户交易记录",e);
			return new PageResult<AccountTradeRecordDTO>();
		}
		
	}
	
	/**
	 * 账户放款信息
	 * @param accountSupervisionId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/loan")
	@ResponseBody
	public ListResult<LoanInfoRedisDTO> searchLoan(@RequestParam String accountSupervisionId, @RequestParam int page,@RequestParam int pageSize){
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		try{
			result = loanInfoApi.queryLoanInfoListByAccountSupervisionId(page, pageSize, accountSupervisionId);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		}catch (Exception e) {
		    logger.error(e.getMessage(), e);
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询放款信息失败");
		}
		return result;
	}
	
	/**
	 * 交易流水明细
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listTradeRecord")
	public String listTradeRecord(Model model) throws Exception{
		List<AccountTradeType> accountTradeTypeList = Lists.newArrayList();
		for(AccountTradeType accountTradeType : AccountTradeType.values()){
			if(accountTradeType == AccountTradeType.TRANSFER_FUND || accountTradeType == AccountTradeType.WITHDRAW_FUND)
				accountTradeTypeList.add(accountTradeType);
		}
		model.addAttribute("accountTradeTypeList",accountTradeTypeList);
		List<AccountType> accountTypeList = Arrays.asList(AccountType.values());
		model.addAttribute("accountTypeList",accountTypeList);
		CustomerDetailDTO customerDTO= userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
		UserType userType = customerDTO.getUserType();
		model.addAttribute("userType",userType);
		return "page/account/trade/listTradeRecord";
	}
	
	/**
	 * 根据查询条件 查询交易流水明细
	 * @param accountQueryCriteria
	 * @return
	 */
	@RequestMapping(value="/searchTradeRecordList")
	@ResponseBody
	public PageResult<AccountTradeRecordDTO> searchTradeRecordList(AccountTradeRecordCriteria accountTradeRecordCriteria){
		String customerId = SecurityContextUtils.getCustomerId();
		PageResult<AccountTradeRecordDTO> result = null;
		try{
		  result  = accountApi.queryTradeRecords(customerId, accountTradeRecordCriteria);
		}catch(WebException e){
		  logger.error("",e);
		}catch(Exception e){
		  logger.error("",e);
		}
		return result != null ? result:new PageResult<AccountTradeRecordDTO>();
	}

	@RequestMapping(value="/queryCities")
	@ResponseBody
	public ListResult<String> queryCities(String province){
		ListResult<String> result = new ListResult<>();
		try{
			List<String> cityList = accountApi.getCitiesByProvince(province);
			result.setData(cityList);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			return result;
		}catch(WebException e){
			logger.error("错误原因",e.getMessage());
			logger.error("",e);
			result.setStatus(AjaxResponseStatus.FAILED);
			return result;
		}catch(Exception e){
			logger.error("",e);
			return result;
		}
	}

	@RequestMapping(value="/queryBranches")
	@ResponseBody
	public ListResult<BankBranchInfoDTO> queryBranches(BranchNameQueryCriteria queryCriteria){
		try{
			return accountApi.queryBranchInfo(queryCriteria);
		}catch(WebException e){
			logger.error("错误原因",e.getMessage());
			logger.error("",e);
			return ListResult.fail(e.getMessage());
		}catch(Exception e){
			logger.error("",e);
			return ListResult.fail("未知异常");
		}
	}
	
	@RequestMapping(value="/result/{action}/{status}")
	public ModelAndView result(@PathVariable String action, @PathVariable String status) {
		ModelAndView mv = new ModelAndView("page/account/result");
		if (AccountTradeType.TRANSFER_FUND.name().equalsIgnoreCase(action)) {
			mv.addObject("action", "转账");
			if (AccountTradeStatus.TRADE_SUCCESS.name().equalsIgnoreCase(status)) {
				mv.addObject("next", "查看明细");
				mv.addObject("nextUrl", WebEnv.getVBAMPlatformPath() + "account/trade/listTradeRecord");
				mv.addObject("message", "您的转账申请已成功");
			} else if (AccountTradeStatus.TRADE_HANDLING.name().equalsIgnoreCase(status)) {
				mv.addObject("next", "返回");
				mv.addObject("nextUrl", WebEnv.getVBAMPlatformPath() + "account/trade/transferFund");
				mv.addObject("message", "您的转账申请已提交，请耐心等候");
			} else {
				throw WebException.instance("404.您访问的页面不存在");
			}
		} else if (AccountTradeType.WITHDRAW_FUND.name().equalsIgnoreCase(action)) {
			mv.addObject("action", "提现");
			if (AccountTradeStatus.TRADE_SUCCESS.name().equalsIgnoreCase(status)) {
				mv.addObject("next", "查看明细");
				mv.addObject("nextUrl", WebEnv.getVBAMPlatformPath() + "account/trade/listTradeRecord");
				mv.addObject("message", "您的提现申请已成功");
			} else if (AccountTradeStatus.TRADE_HANDLING.name().equalsIgnoreCase(status)) {
				mv.addObject("next", "返回");
				mv.addObject("nextUrl", WebEnv.getVBAMPlatformPath() + "account/trade/withdrawFund");
				mv.addObject("message", "您的提现申请已提交，请耐心等候");
			} else {
				throw WebException.instance("404.您访问的页面不存在");
			}
		} else {
			throw WebException.instance("404.您访问的页面不存在");
		}
		return mv;
	}
	
	private String[] queryAccountBalance(String customerId, String... accountNo) {
			List<BankAccountBalanceDataDTO> data = accountApi.queryAccountBalance(customerId, accountNo);
			String[] accountBalances =  new String[data.size()];
			for(int i=0; i<data.size(); i++){
				String accountBalance = MoneyUtil.cent2Yuan(data.get(i).getAvailableBalance());
				accountBalances[i] = accountBalance;
			}
			return accountBalances;
	}
	
	private ObjectResult<?> queryAccountFundInfo(String customerId, String accountNo) {
		AccountFundInfoDTO fundInfo = new AccountFundInfoDTO();
		List<AccountDTO> accounts = accountApi.getAccountByNos(customerId,accountNo);
		if(CollectionUtils.isEmpty(accounts)){
			return ObjectResult.fail("账号信息不存在");
		}
		AccountDTO account = accounts.get(0);
		fundInfo.setAccountNo(accountNo);
		fundInfo.setAccountName(account.getAccountName());
		fundInfo.setAccountSupervisionStatus(account.getSupervisionStatus());
		fundInfo.setAccountAccumulationStatus(account.getAccumulationStatus());
		fundInfo.setAccountType(account.getAccountType());
		fundInfo.setAccountSupervisionStatusDesc(account.getSupervisionStatus().desc());
		fundInfo.setAccountAccumulationStatusDesc(account.getAccumulationStatus().desc());
		fundInfo.setAccountTypeDesc(account.getAccountType().desc());
		fundInfo.setAccountSupervisionId(account.getAccountSupervisionId());
		fundInfo.setCommissionCharge("0.00");// 未填写转帐金额手续费为0.00
		//一般 监管
		if(AccountType.GENERAL.equals(account.getAccountType()) && AccountSupervisionStatus.HAVE_SUPERVISION.equals(account.getSupervisionStatus())){          
			String finaceBalance = queryFinaceBalance(account.getAccountSupervisionId());
			fundInfo.setFinanceBalance(finaceBalance);
		}
		return ObjectResult.success("查询成功", fundInfo);
	}
	
	private boolean checkVerifyCode(String verifyCode) {
		return true;
		/*if(StringUtils.isBlank(verifyCode)) return false;
		HttpSession session = MemberAuthUtils.getRequest().getSession();
		String code = (String) session.getAttribute("CAPTCHA_SESSION_KEY");
		return verifyCode.equals(code) ? true : false;*/
	}
		
	/**
	 * 查询账户余额和专用账户总余额（归集情况下存在），并计算资金覆盖率（一般，监管情况下存在）
	 * @param accountNo,accountAccumulationStatus,accountType,accountSupervisionStatus,finaceBalance
	 * @return
	 * TangYihong
	 */
	@RequestMapping(value = "/updateFundInfo")
	@ResponseBody
	public ObjectResult<?> updateFundInfo(String accountNo,String finaceBalance) {
		Long totalBalances = 0L;//单位：分
		AccountFundInfoDTO fundInfo = new AccountFundInfoDTO();
		try {
			String customerId = SecurityContextUtils.getCustomerId();
			List<AccountDTO> accounts = accountApi.getAccountByNos(customerId,accountNo);
			if(CollectionUtils.isEmpty(accounts)){
				return ObjectResult.fail("账号信息不存在");
			}
			AccountDTO account = accounts.get(0);
			String[] accountBalances = queryAccountBalance(customerId,accountNo);
			fundInfo.setAccountBalance(accountBalances[0]);
			totalBalances += MoneyUtil.yuan2Cent(accountBalances[0]);
			//一般 监管
			if(AccountType.GENERAL.equals(account.getAccountType()) && AccountSupervisionStatus.HAVE_SUPERVISION.equals(account.getSupervisionStatus())){          
//				String finaceBalance = queryFinaceBalance(account.getAccountSupervisionId());
//				fundInfo.setFinanceBalance(finaceBalance);
				//归集 --专用账户余额信息
				if(AccountAccumulationStatus.HAVE_ACCUMULATION.equals(account.getAccumulationStatus())){
					List<AccountDTO> specialAccounts = account.getSpecialAccounts();
					Long specialBalances = calculateSpecialBalances(customerId,specialAccounts);
					totalBalances += specialBalances;
				}
				fundInfo.setFundCoverRatio(StringUtils.isNotBlank(finaceBalance)?calculateFundCoverage(totalBalances,MoneyUtil.yuan2Cent(finaceBalance)):"");
			}
			return ObjectResult.success("查询成功", fundInfo);
		} catch (WebException e) {
			logger.error("查询余额失败!", e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
            logger.error("查询余额失败!", e);
            return ObjectResult.fail("查询余额失败!");
        }
	}
	
	@RequestMapping(value = "/relaunchingWithdrawOperate", method= {RequestMethod.POST})
	@ResponseBody
	public ObjectResult<?> relaunchingWithdrawOperate(String tradeRecordId) {
		try{
			accountApi.relaunchingWithdrawOperate(tradeRecordId);
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
		return ObjectResult.success();
	}

	/**
	 * 计算专用账户总余额
	 * @return
	 * TangYihong
	 */
	private Long calculateSpecialBalances(String customerId,List<AccountDTO> specialAccounts){
	    if (CollectionUtils.isEmpty(specialAccounts))
	        return 0l;
		String[] accountNos = new String[specialAccounts.size()];
		for(int i=0; i<specialAccounts.size(); i++)
			accountNos[i]= specialAccounts.get(i).getAccountNo();
			
		List<BankAccountBalanceDataDTO> specialAccountDatas = accountApi.queryAccountBalance(customerId, accountNos);
		Long specialBalances = 0L;
		for(BankAccountBalanceDataDTO specialAccountData : specialAccountDatas)
			specialBalances += specialAccountData.getAvailableBalance();
		return specialBalances;
	}
	
	/**
	 * 计算资金覆盖率
	 * TangYihong
	 */
	private String calculateFundCoverage(Long totalBalance, Long finaceBalance) {
		String fundCoverage = "";
		
		if(finaceBalance!=0){
			BigDecimal value = new BigDecimal(totalBalance);
			BigDecimal divisor = new BigDecimal(finaceBalance);
			fundCoverage = value.divide(divisor, 4, RoundingMode.HALF_UP).toString();
		}
		return fundCoverage;
	}
}
