package com.cana.vbam.front.biz.controller.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IRoleApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountAgentCreateDTO;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.dto.AccountApplyDTO;
import com.cana.vbam.common.account.dto.AccountApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountSelfCreateDTO;
import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.dto.AjaxResponse;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.ChineseEncodorUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelIEUtil;

@Controller
@RequestMapping(value = "/account/apply")
public class AccountApplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountApplyController.class);
	
	private static final Gson gson = new Gson();
	
	@Resource
	private IAccountApi accountApi;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IRoleApi roleApi;
	
	/**
	 * 审核列表 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String listApply(Model model){
		List<UserType> userTypes = Lists.newArrayList();
		for(UserType userType : UserType.nonIndividualUserTypes()){
			if(userType != UserType.FINACE)
				continue;
			userTypes.add(userType);
		}
		model.addAttribute("userTypes", userTypes);
		return "page/account/apply/list";
	}
	
	/**
	 * 根据查询条件查询 审核列表
	 */
	@RequestMapping(value="/searchList")
	@ResponseBody
	public PageResult<AccountApplyDTO> listApplyResult(AccountApplyQueryCriteria accountApplyQueryCriteria){
		PageResult<AccountApplyDTO> result = accountApi.queryAccountApplys(accountApplyQueryCriteria);
		return result;
	}
	
	/**
	 * 审核列表--待审核
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listPendingAudit")
	public String listPendingAudit(Model model){
		List<UserType> userTypes = Lists.newArrayList();
		for(UserType userType : UserType.nonIndividualUserTypes()){
			if(userType != UserType.FINACE)
				continue;
			userTypes.add(userType);
		}
		model.addAttribute("userTypes", userTypes);
		return "page/account/apply/listPendingAudit";
	}
	
	/**
	 * 审核列表--已审核
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listHavingAudit")
	public String listHavingAudit(Model model){
		List<UserType> userTypes = Lists.newArrayList();
		for(UserType userType : UserType.nonIndividualUserTypes()){
			if(userType != UserType.FINACE)
				continue;
			userTypes.add(userType);
		}
		model.addAttribute("userTypes", userTypes);
		return "page/account/apply/listHavingAudit";
	}
	
	/**
	 * 申请详情页面
	 * @param applyId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String applyDetail(@RequestParam String applyId,
	        @RequestParam String curSubMenu, Model model){
		AccountApplyDTO accountApplyDTO = accountApi.getAccountApply(applyId);
		model.addAttribute("accountApplyDTO",accountApplyDTO);
		model.addAttribute("pageType","pageDetail");
		model.addAttribute("lastMenu", curSubMenu);
		return "page/account/apply/detail";
	}
	
	/**
	 * 申请审核页面
	 * @param accountId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/review",method=RequestMethod.GET)
	public String review(@RequestParam String applyId,
	        @RequestParam String curSubMenu, Model model) throws Exception {
		AccountApplyDTO accountApplyDTO = accountApi.getAccountApply(applyId);
		accountApi.checkAccountApplyStatus(applyId);
		if(accountApplyDTO.isShowRoles()){
			Map<String, String> roleNames = roleApi.queryRolesIdAndName(SecurityContextUtils.getUserDTOFromSession().getId(), RoleType.LEVEL1, accountApplyDTO.getUserType());
			model.addAttribute("roles",roleNames);
		}
		model.addAttribute("accountApplyDTO", accountApplyDTO);
		model.addAttribute("pageType","pageReview");
		model.addAttribute("lastMenu", curSubMenu);
		return "page/account/apply/detail";
	}
	
	@RequestMapping(value="/redirect",method=RequestMethod.GET)
    public String redirect(@RequestParam String applyId) {
        AccountApplyDTO accountApplyDTO = accountApi.getAccountApply(applyId);
        boolean isPendingAudit = AccountApplyStatus.PENDINGAUDIT.equals(accountApplyDTO.getApplyStatus());
        boolean applyReviewAuth = SecurityContextUtils.authorizeUrl("/account/apply/review");
        boolean isReviewPage = isPendingAudit && applyReviewAuth;

        String url = "redirect:/account/apply/";
        if (isReviewPage) {
            url += "review";
            String curSubMenu = "";
            if (SecurityContextUtils.authorizePermKey("AM_APPLY")
                    && SecurityContextUtils.authorizePermKey("AM_APPLY_REVIEW")) {
                curSubMenu = "审核列表";
            } else if (SecurityContextUtils.authorizePermKey("AM_PENDING_AUDIT")
                    && SecurityContextUtils.authorizePermKey("AM_PENDING_AUDIT_REVIEW")) {
                curSubMenu = "待审核列表";
            } else {
                throw WebException.instance("无访问权限");
            }
            return url + "?applyId=" + applyId + "&curSubMenu=" + ChineseEncodorUtil.decodeStr(curSubMenu);
        } else {
            url += "detail";
            String curSubMenu = "";
            if (SecurityContextUtils.authorizePermKey("AM_APPLY")
                    && SecurityContextUtils.authorizePermKey("AM_APPLY_DETAIL")) {
                curSubMenu = "审核列表";
            } else if (SecurityContextUtils.authorizePermKey("AM_PENDING_AUDIT")
                    && SecurityContextUtils.authorizePermKey("AM_PENDING_AUDIT_DETAIL")) {
                curSubMenu = "待审核列表";
            } else if (SecurityContextUtils.authorizePermKey("AM_HAVING_AUDIT")
                    && SecurityContextUtils.authorizePermKey("AM_HAVING_AUDIT_DETAIL")) {
                curSubMenu = "已审核列表";
            } else {
                throw WebException.instance("无访问权限");
            }
            return url + "?applyId=" + applyId + "&curSubMenu=" + ChineseEncodorUtil.decodeStr(curSubMenu);
        }
    }
	
	@RequestMapping(value="/checkAccountExist")
	@ResponseBody
	public AjaxResponse checkAccountExist(String supervisorAccountNo, String financeName) {
		AccountDTO account = null;
		String msg = null;
		if (StringUtils.isBlank(supervisorAccountNo)) {
			return AjaxResponse.fail("账号不能为空");
		}
		String userId = SecurityContextUtils.getCustomerId();
		try {
			if (StringUtils.isNotBlank(financeName)) {
				account = accountApi.getOwnAccountByNameAndNo(financeName, supervisorAccountNo);
				msg = checkAccountStatus(UserType.FINACE, account);
			} else {
				account = accountApi.getOwnAccountByNo(userId, supervisorAccountNo);
				msg = checkAccountStatus(UserType.FACTOR, account);
			}
		} catch (WebException e) {
			return AjaxResponse.fail(e.getMessage());
		} catch (Exception e) {
		    return AjaxResponse.fail("未知错误");
		}
		if (msg != null){
			return AjaxResponse.fail(msg);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("accountNo", account.getAccountNo());
		map.put("accountName", account.getAccountName());
		return AjaxResponse.success("验证通过", map);
	}
	
	private String checkAccountStatus(UserType userType, AccountDTO account) {
		if (account == null) {
			if (userType == UserType.FACTOR) {
				return "资金方没有该账号";
			} else if (userType == UserType.FINACE) {
				return "融资商没有该账号";
			}
		}
		if (AccountType.GENERAL != account.getAccountType()) {
			return "必须是一般账户";
		}
		if (AccountSupervisionStatus.NO_SUPERVISION != account.getSupervisionStatus()) {
			return "必须是未监管的账户";
		}
		if (AccountStatus.NORMAL != account.getAccountStatus()) {
			return "必须是正常状态的账户";
		}
		return null;
	}
	
	/**
	 * 审核--点击“提交”
	 * @param accountDetailDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/review",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse confirmReview(AccountApplyAuditDetail accountApplyAuditDetail,Model model) {
		Map<String, Object> result = Maps.newHashMap();
    	try{
		    accountApplyAuditDetail.setAuditorId(SecurityContextUtils.getUserDTOFromSession().getId());
			accountApi.auditAccountApply(accountApplyAuditDetail);
			return AjaxResponse.success("开户申请审核成功", result);
    	} catch (WebException e) {
			result.put("result", AccountTradeStatus.TRADE_FAIL);
			result.put("message", e.getMessage());
			return AjaxResponse.fail(e.getMessage());
		}
	}
	
	/**
	 * 申请账户页面 account/apply
	 */
	@RequestMapping(value = "self", method=RequestMethod.GET)
	public String gotoCreateAccountSelf(Model model) throws Exception{
		String userId = SecurityContextUtils.getCustomerId();
		
		CustomerDetailDTO customerDTO= userApi.queryCustomerDetail(userId);
		model.addAttribute("customerDTO", customerDTO);
		model.addAttribute("accountType", AccountType.GENERAL.name());
		model.addAttribute("accountMaxNumber", AccountConsts.selfAccountMaxNumber);
		model.addAttribute("excelTemplateId", AccountConsts.buyer_name_excel_template);
		return "page/account/apply/selfApply"; 
	}
	
	/**
	 * 申请账户--点击“立即开户”
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/self", method=RequestMethod.POST)
	public AjaxResponse createAccountSelf(String accountNumber, String[] buyerNames) throws Exception {
		logger.info("主动开户请求,accountNumber:{},BuyerNames:{}",accountNumber,new Gson().toJson(buyerNames));
		String customerId = SecurityContextUtils.getCustomerId();
		AccountSelfCreateDTO info = newAccountSelfCreateDTO(accountNumber, buyerNames);
		info.setCustomerId(customerId);
		try {
			List<String> accountNos = accountApi.createAccountBySelf(info);
			return AjaxResponse.success(accountNos);
		} catch (WebException e) {
			logger.error("主动开户异常", e);
			return AjaxResponse.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("主动开户异常", e);
			return AjaxResponse.fail("系统异常");
		}
	}
	
	// 如果accountNumber不为空，视为单一账户开户
	private AccountSelfCreateDTO newAccountSelfCreateDTO(String accountNumber, String[] buyerNames) throws Exception {
		AccountSelfCreateDTO info = new AccountSelfCreateDTO();
		if (ArrayUtils.isNotEmpty(buyerNames)) {
			info.setAccountNumber(buyerNames.length);
			info.setBuyerNames(Arrays.asList(buyerNames));
		} else if (StringUtils.isNotBlank(accountNumber)) {
			info.setAccountNumber(Integer.valueOf(accountNumber));
		} else {
			logger.error("主动开户失败，传入参数非法！");
			throw WebException.instance("传入参数非法！");
		}
		return info;
	}
	
	/**
	 * 验证手动输入的买方名称
	 * 
	 * @param buyerNames
	 */
	@ResponseBody
	@RequestMapping(value = "/validateBuyerName", method=RequestMethod.POST)
	public Map<String,Object> checkInvalidBuyerNames(String[] buyerNames)throws Exception{
		List<String> listNames = Lists.newArrayList();
		if(ArrayUtils.isNotEmpty(buyerNames)){
			listNames = new ArrayList<String>(Arrays.asList(buyerNames));
		}
		String userId = SecurityContextUtils.getCustomerId();
		String companyName = userApi.queryCustomerDetail(userId).getCompanyName();
		Map<String,List<String>> invalid = accountApi.checkInvalidBuyerNames(companyName, listNames);
		Map<String,Object> result = Maps.newHashMap();
		result.put("invalid", invalid);
		return result;
	}
	
	/**
	 * 验证导入的买方名称Excel，该action为了前端验证
	 * 
	 * @param excel
	 */
	@RequestMapping(value = "/validateBuyerExcel", method=RequestMethod.POST)
	public void checkInvalidBuyerNamesWithExcel(MultipartFile excel,HttpServletResponse httpServletResponse)throws Exception{
		String userId = SecurityContextUtils.getCustomerId();
		String companyName = userApi.queryCustomerDetail(userId).getCompanyName();
		String fileName = excel.getOriginalFilename();
		List<String> buyerNames = ExcelIEUtil.importFromExcelOnlyOneColumn(String.class, excel.getInputStream(),fileName,1);
		Map<String,List<String>> invalid = accountApi.checkInvalidBuyerNames(companyName, buyerNames);
		Map<String,Object> result = Maps.newHashMap();
		result.put("invalid", invalid);
		result.put("excel", buyerNames);
		httpServletResponse.setContentType("text/html");
		httpServletResponse.getWriter().write(new Gson().toJson(AjaxResponse.success(result)));
	}
	
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/checkFinanceName", method=RequestMethod.POST)
	public AjaxResponse checkFinanceName(String financeName) {
		CustomerDetailDTO user = userApi.queryCustomerByCompanyName(financeName, UserType.FINACE);
		if (user != null && UserType.FINACE != user.getUserType()) {
			logger.error("该企业不是融资商!");
			return AjaxResponse.fail("该企业不是融资商!");
		}
		return AjaxResponse.success("");
	}
	
	/**
	 * 代申请账户页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/agent", method=RequestMethod.GET)
	public String gotoAgentApply(Model model) {
		List<UserType> userTypes = Lists.newArrayList();
		for(UserType userType : UserType.nonIndividualUserTypes()){
			if(userType != UserType.FINACE)
				continue;
			userTypes.add(userType);
		}
		model.addAttribute("userTypes", userTypes);
		model.addAttribute("accountMaxNumber", AccountConsts.agentAccountMaxNumber);
		model.addAttribute("excelTemplateId", AccountConsts.buyer_name_excel_template);
		model.addAttribute("authorizationLetterTemplateId", AccountConsts.authorization_letter_template);
		model.addAttribute("finaceApplyFilesId", AccountConsts.finace_apply_files);
		return "page/account/apply/agentApply";
	}
	
	/**
	 * 代申请账户--点击“提交申请”
	 * @param agentDTO
	 * @return
	 */
	@RequestMapping(value="/agent", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse agent(AccountAgentCreateDTO agentDTO, Model model) {
		logger.info(gson.toJson(agentDTO));
		String userId = SecurityContextUtils.getCustomerId();
		if (StringUtils.isNoneBlank(agentDTO.getSupervisorAccountNo())) {
			checkAccountExist(agentDTO.getSupervisorAccountNo(), agentDTO.getCompanyName());
		}
		// 如果建立的是多个账户，前端没有传入accountNumber，需要手动添加
		if (agentDTO.getBuyerNames() != null)
			agentDTO.setAccountNumber(agentDTO.getBuyerNames().size());
		try {
			agentDTO.setAgentCompanyId(userId);
			accountApi.createAccountByAgent(agentDTO);
			return AjaxResponse.success();
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return AjaxResponse.fail(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return AjaxResponse.fail("系统异常");
		}
	}
	
	@RequestMapping(value="/result/{action}")
	public ModelAndView result(@PathVariable String action,String[] accountNos) {
		ModelAndView mv = new ModelAndView("page/account/result");
		if ("agent".equalsIgnoreCase(action)) {
			mv.addObject("action", "代申请账户");
			mv.addObject("message", "代申请账户已提交审核，请耐心等候");
			mv.addObject("next", "返回");
			mv.addObject("nextUrl", "../agent");
		} else if ("self".equalsIgnoreCase(action)) {
			List<AccountDTO> accounts = queryAccountInfo(accountNos);
			mv.addObject("accounts", accounts);
			mv.addObject("action", "申请账户");
			mv.addObject("message", "已成功开户");
			mv.addObject("next", "返回");
			mv.addObject("nextUrl", "../self");
		} else {
			throw WebException.instance("404.您访问的页面不存在");
		}
		return mv;
	}
	
	private List<AccountDTO> queryAccountInfo(String... accountNos){
		if(ArrayUtils.isEmpty(accountNos)){
			return Lists.newArrayList();
		}
		String customerId = SecurityContextUtils.getCustomerId();
		List<AccountDTO> accounts = accountApi.getAccountByNos(customerId, accountNos);
		return accounts;
	}
}
