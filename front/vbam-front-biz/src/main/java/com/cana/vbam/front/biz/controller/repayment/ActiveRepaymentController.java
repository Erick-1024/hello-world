package com.cana.vbam.front.biz.controller.repayment;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.IActiveRepaymentApi;
import com.cana.repayment.api.ILoanInfoApi;
import com.cana.repayment.api.IRepaymentCalcApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.PrepareRepaymentMergeResult;
import com.cana.vbam.common.repayment.dto.QueryRepaymentAndExpenseRequestDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanActiveRepaymentExportExcelBean;
import com.cana.vbam.common.repayment.dto.RepaymentPlanSearchCriteriaDTO;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentStatus;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.ExcelUtils;
/**
 * 主动还款controller
 * @author dev5
 *
 */
@Controller
@RequestMapping(value = "/repayment/active")
public class ActiveRepaymentController {
	
	private static final Logger LGR = LoggerFactory.getLogger(ActiveRepaymentController.class);
	
	@Resource
	private IAccountApi accountApi;
	
	@Resource
	private IActiveRepaymentApi activeRepaymentApi;
	
	@Resource
	private ILoanInfoApi loanInfoApi;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IRepaymentCalcApi repaymentCalcApi;
	/**
	 * 跳转到主动还款还款计划列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoActiveRepaymentPlan")
	public String gotoActiveRepaymentPlan(Model model)
	{
		try
		{
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			model.addAttribute("userType",userSessionDTO.getUserType().name());
			LGR.info("主动还款还款计划列表页面。");
		} catch (Exception e)
		{
			LGR.error("获取用户信息失败！",e);
			throw WebException.instance("获取用户信息失败！");
		}
		return "/page/repayment/active/activeRepaymentPlan";
	}
	
	/**
	 * 跳转到主动还款逾期列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoOverdueRepaymentPlan")
	public String gotoOverdueRepaymentPlan(Model model)
	{
		try
		{
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			model.addAttribute("userType",userSessionDTO.getUserType().name());
			LGR.info("主动还款逾期列表页面。");
		} catch (Exception e)
		{
			LGR.error("获取用户信息失败！",e);
			throw WebException.instance("获取用户信息失败！");
		}
		return "/page/repayment/active/overdueRepaymentPlan";
	}
	
	/**
	 * 跳转到主动还款7日还款计划列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/goto7DaysRepaymentPlan")
	public String goto7DaysRepaymentPlan(Model model)
	{
		try
		{
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			model.addAttribute("userType",userSessionDTO.getUserType().name());
			LGR.info("主动还款7日还款计划列表页面。");
		} catch (Exception e)
		{
			LGR.error("获取用户信息失败！",e);
			throw WebException.instance("获取用户信息失败！");
		}
		return "/page/repayment/active/repaymentPlansWithn7days";
	}
	
	/**
	 * 获取7日内的还款计划
	 * @return
	 */
	@RequestMapping(value="/getRepaymentPlansWithn7Days")
	@ResponseBody
	public ListResult<RepaymentPlanActiveRepaymentDTO> getRepaymentPlansWithn7Days(RepaymentPlanSearchCriteriaDTO searchDto){
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<RepaymentPlanActiveRepaymentDTO>();
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			if (UserType.FINACE == userSessionDTO.getUserType()) {
				searchDto.setFinanceId(SecurityContextUtils.getCustomerId());
			}else if (UserType.FACTOR == userSessionDTO.getUserType()) {
				searchDto.setFactorId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CORECOMPANY == userSessionDTO.getUserType()){
				searchDto.setCoreCompanyId(SecurityContextUtils.getCustomerId());
			}
			else if(UserType.CANA == userSessionDTO.getUserType()) {
			}else {
				throw WebException.instance("非法类型的用户。");
			}
			result = activeRepaymentApi.getRepaymentPlansWithn7Days(searchDto, userSessionDTO.getUserType()); 
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询7日还款计划列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询7日还款计划列表失败,原因："+e.getMessage());
			LGR.error("查询7日还款计划列表失败", e);
		}
		return result;
	}
	
	/**
	 * 跳转至主动还款调账页面
	 * @param loanInfoId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAdjustment")
	public String gotoAdjustmentPage(@RequestParam String loanInfoId,Model model) throws Exception{
		String redisKey = activeRepaymentApi.getPlanAndExpenseToRedis(SecurityContextUtils.getOperatorId(), loanInfoId, SecurityContextUtils.getCustomerId());
		model.addAttribute("loanInfoElements",loanInfoApi.getLoanInfoElements(loanInfoId));
		model.addAttribute("accountTotalMoney", activeRepaymentApi.getCurrentRepaymentNum(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId())));
		model.addAttribute("redisKey", redisKey);
		model.addAttribute("loanInfoId", loanInfoId);
		return "/page/repayment/active/activeRepaymentAdjustment";
	}
	
	@RequestMapping("/submitValidate")
	@ResponseBody
	public ObjectResult<String> submitValidate(String redisKey){
		ObjectResult<String> result = new ObjectResult<String>();
		try {
			activeRepaymentApi.submitValidate(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()));
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage(e.getMessage());
			LGR.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 提交通过校验计划&费用到数据库
	 * @param redisKey
	 * @param loanInfoId
	 * @param flag
	 * @param changeType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savePlanAndExpense")
	public String savePlanAndExpenseSummaryAndDetail(String redisKey,String loanInfoId, String flag,String changeType, Model model) throws Exception{
		activeRepaymentApi.saveRepaymentPlanAndExpenseToDB(RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,SecurityContextUtils.getOperatorId()),
				loanInfoId, SecurityContextUtils.getOperatorId(), flag, changeType);
		model.addAttribute("loanInfoId", loanInfoId);
		return "redirect:/repayment/adjustment/gotoAdjustmentResult";
	}
	
	/**
	 * 获取7日内的费用列表
	 * @param searchDto
	 * @return
	 */
	@RequestMapping(value="/getRepaymentExpenseWithn7Days")
	@ResponseBody
	public ListResult<RepaymentExpenseActiveRepaymentDTO> getRepaymentExpenseWithn7Days(RepaymentPlanSearchCriteriaDTO searchDto){
		ListResult<RepaymentExpenseActiveRepaymentDTO> result = new ListResult<RepaymentExpenseActiveRepaymentDTO>();
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			if (UserType.FINACE == userSessionDTO.getUserType()) {
				searchDto.setFinanceId(SecurityContextUtils.getCustomerId());
			}else if (UserType.FACTOR == userSessionDTO.getUserType()) {
				searchDto.setFactorId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CORECOMPANY == userSessionDTO.getUserType()){
				searchDto.setCoreCompanyId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CANA == userSessionDTO.getUserType()) {
			}else {
				throw WebException.instance("非法类型的用户。");
			}
			result = activeRepaymentApi.getRepaymentExpenseWithn7Days(searchDto, userSessionDTO.getUserType()); 
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询7日费用列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询7日费用列表失败,原因："+e.getMessage());
			LGR.error("查询7日费用列表失败", e);
		}
		return result;
	}
	
	/**
	 * 查询逾期还款计划
	 * @param searchDto
	 * @return
	 */
	@RequestMapping(value="/getOverdueRepaymentPlan")
	@ResponseBody
	public ListResult<RepaymentPlanActiveRepaymentDTO> getOverdueRepaymentPlan(RepaymentPlanSearchCriteriaDTO searchDto){
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<>();
		try {
			if (UserType.FINACE == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
				searchDto.setFinanceId(SecurityContextUtils.getCustomerId());
			}else if (UserType.FACTOR == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
				searchDto.setFactorId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CORECOMPANY == SecurityContextUtils.getUserDTOFromSession().getUserType()){
				searchDto.setCoreCompanyId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CANA == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
			}else {
				throw WebException.instance("非法类型的用户。");
			}
			QueryRepaymentAndExpenseRequestDTO queryDTO = new QueryRepaymentAndExpenseRequestDTO();
			BeanUtils.copyProperties(searchDto, queryDTO);
			queryDTO.setSettleStatus(SettleStatus.UNSETTLE.name());
			queryDTO.setOverdue(Boolean.toString(true));
			queryDTO.setPage(searchDto.getPage());
			queryDTO.setPageSize(searchDto.getPageSize());
			result = activeRepaymentApi.getOverdueRepaymentPlan(queryDTO, SecurityContextUtils.getUserDTOFromSession().getUserType());
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询逾期还款计划成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询逾期还款计划失败,原因："+e.getMessage());
			LGR.error("查询逾期还款计划失败", e);
		}
		return result;
	}
	
	/**
	 * 查询逾期费用列表
	 * @param searchDto
	 * @return
	 */
	@RequestMapping(value="/getOverdueRepaymentExpense")
	@ResponseBody
	public ListResult<RepaymentExpenseActiveRepaymentDTO> getOverdueRepaymentExpense(RepaymentPlanSearchCriteriaDTO searchDto){
		ListResult<RepaymentExpenseActiveRepaymentDTO> result = new ListResult<>();
		try {
			if (UserType.FINACE == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
				searchDto.setFinanceId(SecurityContextUtils.getCustomerId());
			}else if (UserType.FACTOR == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
				searchDto.setFactorId(SecurityContextUtils.getCustomerId());
			} else if(UserType.CORECOMPANY == SecurityContextUtils.getUserDTOFromSession().getUserType()){
				searchDto.setCoreCompanyId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CANA == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
			}else {
				throw WebException.instance("非法类型的用户。");
			} 
			QueryRepaymentAndExpenseRequestDTO queryDTO = new QueryRepaymentAndExpenseRequestDTO();
			BeanUtils.copyProperties(searchDto, queryDTO);
			queryDTO.setSettleStatus(SettleStatus.UNSETTLE.name());
			queryDTO.setOverdue(Boolean.toString(true));
			queryDTO.setPage(searchDto.getPage());
			queryDTO.setPageSize(searchDto.getPageSize());
			result = activeRepaymentApi.getOverdueRepaymentExpense(queryDTO, SecurityContextUtils.getUserDTOFromSession().getUserType());
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询逾期费用列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询逾期费用列表失败,原因："+e.getMessage());
			LGR.error("查询逾期费用列表失败", e);
		}
		return result;
	}
	
	/**
	 * 根据查询条件来查询还款计划列表
	 * @param searchDto
	 * @return
	 */
	@RequestMapping(value="/queryRepaymentPlansBySearchCondition")
	@ResponseBody
	public ListResult<RepaymentPlanActiveRepaymentDTO> queryRepaymentPlansBySearchCondition(RepaymentPlanSearchCriteriaDTO searchDto){
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<RepaymentPlanActiveRepaymentDTO>();
		try {
			QueryRepaymentAndExpenseRequestDTO queryDTO = convertQueryParam(searchDto);
			queryDTO.setPage(searchDto.getPage());
			queryDTO.setPageSize(searchDto.getPageSize());
			result = activeRepaymentApi.queryRepaymentPlansBySearchCondition(queryDTO); 
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询还款计划列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询还款计划列表失败,原因："+e.getMessage());
			LGR.error("查询还款计划列表失败", e);
		}
		return result;
	}

	/**
	 * 根据查询条件来导出还款计划列表
	 */
	@RequestMapping(value="/exportRepaymentPlansBySearchCondition")
	public void exportRepaymentPlansBySearchCondition(RepaymentPlanSearchCriteriaDTO searchDto,
			HttpServletRequest request, HttpServletResponse response){
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<RepaymentPlanActiveRepaymentDTO>();
		try {
			QueryRepaymentAndExpenseRequestDTO queryDTO = convertQueryParam(searchDto);
			queryDTO.setPage(1);
			queryDTO.setPageSize(10000);
			result = activeRepaymentApi.queryRepaymentPlansBySearchCondition(queryDTO); 
		} catch (Exception e) {
			LGR.error("查询还款计划列表失败", e);
			throw WebException.instance("导出Excel失败");
		}

		try {
			String userAgent = request.getHeader("User-Agent").toUpperCase();
			String excelTitle = "还款计划列表";
			if(StringUtils.isNotBlank(searchDto.getBeginRepaymentDate()))
				excelTitle += "_" + searchDto.getBeginRepaymentDate();
			if (StringUtils.isNotBlank(searchDto.getEndRepaymentDate()))
				excelTitle += "_" + searchDto.getEndRepaymentDate();
			String fileName = excelTitle+".xls";
			if (userAgent.contains("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
			response.setCharacterEncoding("utf-8");

			OutputStream out = response.getOutputStream();
			String[] excelHeaders = {"序号", "平台流水号", "业务产品", "资金方", "融资客户", "核心企业",
					"放款编号", "期数", "融资金额", "融资余额", "收益计算日", "收益分配日", "还款日",
					"应还本金", "今日应还收益", "到期应还收益", "到期应还服务费", "展期费用",
					"已还本金", "已还收益", "已还服务费", "已还展期费用",
					"逾期本金", "逾期收益", "逾期服务费", "逾期展期费用", "结算状态"};
			List<RepaymentPlanActiveRepaymentExportExcelBean> excelDatas = Lists.newArrayList();
			if (CollectionUtils.isNotEmpty(result.getData()))
				for (RepaymentPlanActiveRepaymentDTO item : result.getData()) {
					RepaymentPlanActiveRepaymentExportExcelBean excelBean = new RepaymentPlanActiveRepaymentExportExcelBean();
					BeanUtils.copyProperties(item, excelBean);
					excelDatas.add(excelBean);
				}
			ExcelUtils.exportExcel(excelTitle, excelHeaders, excelDatas, out, true);
		} catch (Exception e) {
			LGR.error("生成还款计划Excel失败", e);
			throw WebException.instance("生成还款计划Excel失败");
		}
		
	}

	private QueryRepaymentAndExpenseRequestDTO convertQueryParam(RepaymentPlanSearchCriteriaDTO searchDto) {
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if (UserType.FINACE == userSessionDTO.getUserType()) {
			searchDto.setFinanceId(SecurityContextUtils.getCustomerId());
		}else if (UserType.FACTOR == userSessionDTO.getUserType()) {
			searchDto.setFactorId(SecurityContextUtils.getCustomerId());
		}else if(UserType.CORECOMPANY == SecurityContextUtils.getUserDTOFromSession().getUserType()){
			searchDto.setCoreCompanyId(SecurityContextUtils.getCustomerId());
		}else if(UserType.CANA == SecurityContextUtils.getUserDTOFromSession().getUserType()) {
		}else {
			throw WebException.instance("非法类型的用户。");
		} 
		//查询逾期的话重新设置搜索条件
		QueryRepaymentAndExpenseRequestDTO queryDTO = new QueryRepaymentAndExpenseRequestDTO();
		BeanUtils.copyProperties(searchDto, queryDTO);
		if("OVERDUE".equals(searchDto.getSettleStatus())){
			queryDTO.setSettleStatus(SettleStatus.UNSETTLE.name());
			queryDTO.setOverdue(Boolean.toString(true));
		}
		return queryDTO;
	}
	
	
	/**
	 * 根据查询条件来查询费用列表
	 * @param searchDto
	 * @return
	 */
	@RequestMapping(value="/queryRepaymentExpensesBySearchCondition")
	@ResponseBody
	public ListResult<RepaymentExpenseActiveRepaymentDTO> queryRepaymentExpensesBySearchCondition(RepaymentPlanSearchCriteriaDTO searchDto){
		ListResult<RepaymentExpenseActiveRepaymentDTO> result = new ListResult<>();
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			searchDto.setUserType(userSessionDTO.getUserType().name());
			if (UserType.FINACE == userSessionDTO.getUserType()) {
				searchDto.setFinanceId(SecurityContextUtils.getCustomerId());
			}else if (UserType.FACTOR == userSessionDTO.getUserType()) {
				searchDto.setFactorId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CORECOMPANY == userSessionDTO.getUserType()){
				searchDto.setCoreCompanyId(SecurityContextUtils.getCustomerId());
			}else if(UserType.CANA == userSessionDTO.getUserType()) {
			}else {
				throw WebException.instance("非法类型的用户。");
			} 
			//查询逾期的话重新设置搜索条件
			QueryRepaymentAndExpenseRequestDTO queryDTO = new QueryRepaymentAndExpenseRequestDTO();
			BeanUtils.copyProperties(searchDto, queryDTO);
			if("OVERDUE".equals(searchDto.getSettleStatus())){
				queryDTO.setSettleStatus(SettleStatus.UNSETTLE.name());
				queryDTO.setOverdue(Boolean.toString(true));
				queryDTO.setEndRepaymentDate(DateTime.now().toString("yyyy-MM-dd"));
			}
			queryDTO.setPage(searchDto.getPage());
			queryDTO.setPageSize(searchDto.getPageSize());
			result = activeRepaymentApi.queryRepaymentExpensesBySearchCondition(queryDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
			LGR.info("查询费用列表成功");
		} catch (Exception e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询费用列表失败,原因："+e.getMessage());
			LGR.error("查询费用列表失败", e);
		}
		return result;
	}
	
	/**
	 * 手工录入的还款计划下的主动还款
	 * @param model
	 * @param loanInfoId
	 * @param activeRepaymentType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showActiveRepaymentPage")
	public String showActiveRepaymentPage(Model model, @RequestParam String loanInfoId, ActiveRepaymentType activeRepaymentType) throws Exception {
		LoanInfoRedisDTO loanInfo= loanInfoApi.queryLoanInfodetailFromDB(loanInfoId);
		checkActiveRepaymentPermission(loanInfo);
		model.addAttribute("autoRepaymentPlans", false);
		model.addAttribute("loanInfoId", loanInfoId);
		model.addAttribute("maxAccountTotalMoney", activeRepaymentApi.getMaxAccountTotalMoney(loanInfoId, activeRepaymentType));
		model.addAttribute("accountMap", convertList2Map(loanInfo.getFactorId(), loanInfo.getFinanceCompany()));
		model.addAttribute("activeRepaymentType", activeRepaymentType);
		return "page/repayment/active/activeRepayment";
	}
	
	private Map<String, String> convertList2Map(String factorId, String finaceName) {
		Map<String, String> accountMap = new HashMap<String, String>();
		List<AccountDTO> RepaymentaccountDTOs = accountApi.queryRepaymentAccounts(factorId, finaceName);
		if (CollectionUtils.isNotEmpty(RepaymentaccountDTOs)) {
			for (AccountDTO accountDTO : RepaymentaccountDTOs) {
				accountMap.put(AccountNoUtil.formatBankAccountNo(accountDTO.getAccountNo()), accountDTO.getSupervisionStatusDesc()); 
			}
		}

		AccountQueryCriteria criteria = new AccountQueryCriteria();
		criteria.setPageSize(Integer.MAX_VALUE);
		criteria.setAccountType(AccountType.GENERAL);
		criteria.setAccountStatus(AccountStatus.NORMAL);
		criteria.setSupervisoryStatus(AccountSupervisionStatus.NO_SUPERVISION);
		List<AccountDTO> accountDTOs = accountApi.queryAccounts(SecurityContextUtils.getCustomerId(), criteria).getData();
		if (CollectionUtils.isNotEmpty(accountDTOs)) {
			for (AccountDTO accountDTO : accountDTOs) {
				accountMap.put(AccountNoUtil.formatBankAccountNo(accountDTO.getAccountNo()), accountDTO.getSupervisionStatusDesc()); 
			}
		}
		
		return accountMap;
	}
	
	@RequestMapping(value = "/queryAccountBalance")
	@ResponseBody
	public ObjectResult<String> queryAccountBalance(String accountNo) {
		try {
			List<BankAccountBalanceDataDTO> bankAccountBalanceDataDTOs = accountApi.queryAccountBalance(SecurityContextUtils.getCustomerId(), AccountNoUtil.parseBankAccountNo(accountNo));
			if(CollectionUtils.isEmpty(bankAccountBalanceDataDTOs))
				return ObjectResult.fail("查询余额失败");
			return ObjectResult.success("查询余额成功", MoneyArithUtil.convertMoneyToString(bankAccountBalanceDataDTOs.get(0).getAvailableBalance()));
		} catch (Exception e) {
			return ObjectResult.fail("查询余额失败");
		}
	}
	
	@RequestMapping(value = "/activeRepayment")
	@ResponseBody
	public ObjectResult<Void> activeRepayment(@RequestParam String loanInfoId, ActiveRepaymentType activeRepaymentType, String accountNo, String amount, String payPsw) throws Exception {
		if(StringUtils.isBlank(accountNo))
			return ObjectResult.fail("银行帐号不能为空");
		if(StringUtils.isBlank(amount))
			return ObjectResult.fail("转账金额不能为空");
		if(StringUtils.isBlank(payPsw))
			return ObjectResult.fail("支付密码不能为空");
		if(!userApi.isSetPayPassword(SecurityContextUtils.getCustomerId()))
			return ObjectResult.fail("请先设置支付密码，企业信息 --> 账户信息");
		if(!userApi.validatePayPwd(SecurityContextUtils.getCustomerId(), PasswordEncoderUtil.encrypt(payPsw)))
			return ObjectResult.fail("支付密码不正确");
		List<BankAccountBalanceDataDTO> bankAccountBalanceDataDTOs = accountApi.queryAccountBalance(SecurityContextUtils.getCustomerId(), AccountNoUtil.parseBankAccountNo(accountNo));
		if(CollectionUtils.isEmpty(bankAccountBalanceDataDTOs))
			return ObjectResult.fail("获取余额失败");
		if(MoneyUtil.yuan2Cent(amount) > bankAccountBalanceDataDTOs.get(0).getAvailableBalance())
			return ObjectResult.fail("转账金额不能大于账户余额");
		
		LoanInfoRedisDTO loanInfo= loanInfoApi.queryLoanInfodetailFromDB(loanInfoId);
		
		if(activeRepaymentType!=null){
			if(ActiveRepaymentStatus.already_active_repayment.name().equals(loanInfo.getActiveRepaymentStatus()))
				return ObjectResult.fail("该还款计划已主动还款，请耐心等待资金方调整");
		}
		Transaction transaction = Cat.newTransaction("repayment", "active-repayment-get-trade-status");
		try{
			if(activeRepaymentType!=null){
				LGR.info("手工录入的还款计划下的【主动还款】");
				activeRepaymentApi.updateOnActiveRepaymentSuccess(loanInfoId, amount, activeRepaymentType, accountNo);
			}else{
				LGR.info("主动生成的还款计划下的【主动还款】");
				repaymentCalcApi.repayment(accountNo,loanInfoId,amount);
			}
			transaction.setStatus(Transaction.SUCCESS);
			Cat.logMetricForCount("active-repayment-get-trade-status_success");
			return ObjectResult.success("转账成功");
		} catch (WebException e) {
			Cat.getProducer().logError(e);
			transaction.setStatus(e);
			Cat.logMetricForCount("active-repayment-get-trade-status_error");
			return ObjectResult.fail(e.getMessage());
		} finally{
			transaction.complete();
		}
	}
	
	@RequestMapping(value = "/showActiveRepaymentSuccessPage")
	public String showActiveRepaymentSuccessPage(@RequestParam String loanInfoId,String autoRepaymentPlans, Model model) {
		model.addAttribute("loanInfoId", loanInfoId);
		model.addAttribute("autoRepaymentPlans", autoRepaymentPlans);
		return "page/repayment/active/activeRepaymentSuccess";
	}
	
	/**
	 * 自动生成的还款计划下的主动还款
	 * @param model
	 * @param loanInfoId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/gotoActiveRepayment")
	public String gotoActiveRepaymentPage(Model model, @RequestParam String loanInfoId) throws Exception {
		LoanInfoRedisDTO loanInfo= loanInfoApi.queryLoanInfodetailFromDB(loanInfoId);
		checkActiveRepaymentPermission(loanInfo);
		model.addAttribute("autoRepaymentPlans", true);
		model.addAttribute("loanInfoId", loanInfoId);
		model.addAttribute("minAccountTotalMoney", repaymentCalcApi.calcMinimumRepaymentAmount(loanInfoId));
		model.addAttribute("maxAccountTotalMoney", repaymentCalcApi.calcMaximumRepaymentAmount(loanInfoId));
		model.addAttribute("accountMap", convertList2Map(loanInfo.getFactorId(), loanInfo.getFinanceCompany()));
//		model.addAttribute("activeRepaymentType", ActiveRepaymentType.mutiple_plan);
		return "page/repayment/active/activeRepayment";
	}
	
	private void checkActiveRepaymentPermission(LoanInfoRedisDTO loanInfo){
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		if(!userId.equals(loanInfo.getFinanceId())){
			LGR.info("当前用户id为{},放款信息id为{},对应的融资客户id为{}",userId,loanInfo.getId(),loanInfo.getFinanceId());
			throw WebException.instance("不存在该放款信息");
		}
		if(SettleStatus.SETTLED.name().equals(loanInfo.getSettleStatus()))
			throw WebException.instance("该放款信息已结清");
		if(ActiveRepaymentStatus.already_active_repayment.name().equals(loanInfo.getActiveRepaymentStatus()))
			throw WebException.instance("该还款计划已主动还款，请耐心等待资金方调整");
	}
	
	/**
	 * 自动生成的还款计划下的主动还款的拆分金额
	 * @param loanInfoId
	 * @param repaymentAmount
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/prepareRepayment")
	@ResponseBody
	public ObjectResult<PrepareRepaymentMergeResult> prepareRepayment(@RequestParam String loanInfoId, String repaymentAmount){
		PrepareRepaymentMergeResult result = new PrepareRepaymentMergeResult();
		try{
			result = repaymentCalcApi.prepareRepaymentMerge(loanInfoId, MoneyUtil.yuan2Cent(repaymentAmount));
		}catch(NumberFormatException e){
			return ObjectResult.fail("转账金额格式不正确");
		}catch(Exception e){
			return ObjectResult.fail(e.getMessage());
		}
		return ObjectResult.success("拆分还款金额成功",result);
	}
	
}
