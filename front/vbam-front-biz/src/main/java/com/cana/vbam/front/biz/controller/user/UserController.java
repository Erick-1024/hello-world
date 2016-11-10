package com.cana.vbam.front.biz.controller.user;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IAssetApi;
import com.cana.member.api.IRoleApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.vbam.common.account.dto.AccountBalancesAndNumberDTO;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractListReqDTO;
import com.cana.vbam.common.dto.AjaxResponse;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.dto.role.RoleSearchResultDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchResultListDTO;
import com.cana.vbam.common.member.dto.user.CompanyInfoDTO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.CustomerReviewDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchResultDTO;
import com.cana.vbam.common.member.dto.user.EmployeeRegisterDTO;
import com.cana.vbam.common.member.dto.user.EmployeeSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.EmployeeSearchResultDTO;
import com.cana.vbam.common.member.dto.user.PersonalDetailDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.AccountActivateStatus;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.FinanceInfo4CoreCompanyDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FactorDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FinanceDTO;
import com.cana.vbam.common.utils.ChineseEncodorUtil;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.cana.vbam.common.utils.RegexRule;
import com.cana.vbam.front.biz.vo.repayment.FinanceInfo4CoreCompanyVO;
import com.cana.vbam.front.biz.vo.repayment.FinanceInfo4FactorVO;
import com.cana.vbam.front.biz.vo.repayment.FinanceInfo4FinanceVO;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

@Controller
@RequestMapping()
public class UserController {
			
	@Resource
	private IUserApi userApi;

	@Resource
	private IRoleApi roleApi;

	@Resource
	private IAccountApi accountApi;

	@Resource
	private IFinanceReportApi financeApi;
	
	@Resource
	private IAssetApi assetApi;

	@RequestMapping(value = "welcome")
	public String welcome() {
		return "page/welcome";
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/customer/list")
	public String listCustomer(Model model) {
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "/page/customer/list";
	}

	@RequestMapping(value = "/customer/listAll")
	public String listAllCustomer(Model model) {
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/customer/listAll";
	}

	@RequestMapping(value = "/customer/listPendingAudit")
	public String listPendingAuditCustomer(Model model) {
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/customer/listPendingAudit";
	}

	@RequestMapping(value = "/customer/listHavingAudit")
	public String listHavingAuditCustomer(Model model) {
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/customer/listHavingAudit";
	}

	@RequestMapping(value = "/customer/listActivated")
	public String listActivatedCustomer(Model model) {
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/customer/listActivated";
	}

	@RequestMapping(value = "/customer/result")
	@ResponseBody
	public ListResult<CustomerSearchResultDTO> listCustomerResult(CustomerSearchCriteriaDTO customerSearchCriteriaDTO) throws Exception {
		customerSearchCriteriaDTO.setUserTypeWithOutList(Lists.newArrayList(UserType.CANA.name(), UserType.INDIVIDUAL.name()));
		customerSearchCriteriaDTO.setUserStatusWithInList(Lists.newArrayList(Arrays.asList(UserStatus.PENDINGAUDIT.name(), UserStatus.ACTIVATED.name(),
				UserStatus.PENDINGACTIVATE.name(), UserStatus.REJECTED.name())));
		return userApi.queryCustomerList(customerSearchCriteriaDTO);
	}

	@RequestMapping(value = "/customer/gotoReview")
	public String gotoReview(@RequestParam String customerId, @RequestParam String curSubMenu, Model model)
			throws Exception {
		CustomerReviewDTO customerReviewDTO = userApi.gotoReview(customerId);
		customerReviewDTO.setRoleNames(roleApi.queryRolesIdAndName(SecurityContextUtils.getUserDTOFromSession().getId(),
				RoleType.LEVEL1, customerReviewDTO.getUserType()));
		model.addAttribute("customerReviewDTO", customerReviewDTO);
		model.addAttribute("roles", customerReviewDTO.getRoleNames());
		model.addAttribute("lastMenu", curSubMenu);
		return "page/customer/review";
	}

	@RequestMapping(value = "/customer/review")
	@ResponseBody
	public ObjectResult<String> review(@RequestBody CustomerDetailDTO customerDetailDTO) throws Exception {
		try{
			customerDetailDTO.setAuditorId(SecurityContextUtils.getUserDTOFromSession().getId());
			userApi.review(customerDetailDTO);
		}catch(WebException e){
			logger.error("审核失败", e);
			ObjectResult.fail("审核失败");
		}
		return ObjectResult.success("审核成功");
	}

	@RequestMapping(value = "/customer/notification/redirect")
	public String redirectCustomerNotification(@RequestParam String customerId, Model model) {
		CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(customerId);
		if (UserStatus.PENDINGAUDIT == customerDetailDTO.getUserStatus()) {
			if (SecurityContextUtils.authorizePermKey("UM_TOTAL_AUDIT")) {
				return "redirect:/customer/gotoReview?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("全部列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_UNAUDIT_AUDIT")) {
				return "redirect:/customer/gotoReview?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("待审核列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_TOTAL_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("全部列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_UNAUDIT_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("待审核列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_AUDITED_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("已审核列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_NORMAL_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("正式用户列表");
			} else {
				throw WebException.instance("没有审核或详情权限");
			}
		} else {
			if (SecurityContextUtils.authorizePermKey("UM_TOTAL_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("全部列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_UNAUDIT_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("待审核列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_AUDITED_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("已审核列表");
			} else if (SecurityContextUtils.authorizePermKey("UM_NORMAL_DETAIL")) {
				return "redirect:/customer/customerDetail?customerId=" + customerId + "&curSubMenu="
						+ ChineseEncodorUtil.decodeStr("正式用户列表");
			} else {
				throw WebException.instance("没有详情权限");
			}
		}
	}

	@RequestMapping(value = "/customer/customerDetail")
	public String customerDetail(@RequestParam String customerId, @RequestParam String curSubMenu, Model model)
			throws Exception {
		CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(customerId);
		model.addAttribute("customerDetailDTO", customerDetailDTO);
		model.addAttribute("lastMenu", curSubMenu);
		return "page/customer/detail";
	}

	@RequestMapping(value = "/user/company")
	public String showCompany(Model model) throws Exception {
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId()
				: userSessionDTO.getMasterId();
		FinanceInfo4FinanceDTO finance;
		FinanceInfo4FactorDTO factor;
		FinanceInfo4CoreCompanyDTO coreCompany;
		if (userSessionDTO.getUserType() == UserType.FACTOR) {
			factor = financeApi.getFinanceInfo4Factor(masterId);
			model.addAttribute("factor", convertFactorDTO2VO(factor));
		} else if(userSessionDTO.getUserType() == UserType.CORECOMPANY){
			coreCompany = financeApi.getFinanceInfo4CoreCompany(masterId);
			model.addAttribute("coreCompany", convertCoreCompanyDTO2VO(coreCompany));
		} else if(userSessionDTO.getUserType() == UserType.FINACE){
			String companyName = userApi.queryCustomerDetail(masterId).getCompanyName();
			finance = financeApi.getFinanceInfo4Finance(companyName);
			model.addAttribute("finance", convertFinanceDTO2VO(finance));
		}
		model.addAttribute("userType", userSessionDTO.getUserType());
		if (userApi.isSetPayPassword(masterId))
			model.addAttribute("setPayPwd", "YES");
		CompanyInfoDTO companyInfoDTO = userApi.getCompanyInfo(userSessionDTO);
		model.addAttribute("companyInfo", companyInfoDTO);
		return "page/customer/center/mainCenter";
	}

	private Object convertFinanceDTO2VO(FinanceInfo4FinanceDTO finance) {
		FinanceInfo4FinanceVO vo = new FinanceInfo4FinanceVO();
		vo.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(finance.getFinanceBalance())));
		vo.setLoanInfoNum(finance.getLoanInfoNum());
		vo.setOverdueNum(finance.getOverdueNum());
		vo.setToPayAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(finance.getToPayAmount())));
		vo.setTotalOverdueAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(finance.getTotalOverdueAmount())));
		return vo;
	}

	private Object convertCoreCompanyDTO2VO(FinanceInfo4CoreCompanyDTO coreCompany) {
		FinanceInfo4CoreCompanyVO vo = new FinanceInfo4CoreCompanyVO();
		vo.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(coreCompany.getFinanceBalance())));
		vo.setLoanInfoNum(coreCompany.getLoanInfoNum());
		vo.setOverdueNum(coreCompany.getOverdueNum());
		vo.setToPayAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(coreCompany.getToPayAmount())));
		vo.setTotalOverdueAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(coreCompany.getTotalOverdueAmount())));
		return vo;
	}

	private FinanceInfo4FactorVO convertFactorDTO2VO(FinanceInfo4FactorDTO factor) {
		FinanceInfo4FactorVO vo = new FinanceInfo4FactorVO();
		vo.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(factor.getFinanceBalance())));
		vo.setLoanInfoNum(factor.getLoanInfoNum());
		vo.setOverdueNum(factor.getOverdueNum());
		vo.setTotalOverdueAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(factor.getTotalOverdueAmount())));
		return vo;
	}

	@RequestMapping(value = "/user/individual")
	public String showIndividual(Model model) throws Exception {
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId()
				: userSessionDTO.getMasterId();
		if (userApi.isSetPayPassword(masterId))
			model.addAttribute("setPayPwd", "YES");
		PersonalDetailDTO individualInfoDTO = userApi.queryPersonalDetailInfo(userSessionDTO.getId());
		model.addAttribute("individualInfo", individualInfoDTO);
		return "page/customer/center/personal_main";
	}

	@RequestMapping(value = "/employee/gotoAdd")
	@ResponseBody
	public AjaxResponse gotoAddEmployee() {
		AjaxResponse response = new AjaxResponse();
		String userId = null;
		try {
			userId = userApi.generateUserId();
			response.setStatus(AjaxResponseStatus.SUCCESS);
			response.setData(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage(e.getMessage());
		}
		if (StringUtils.isBlank(userId)) {
			logger.error("用户Id获取失败", AjaxResponseStatus.FAILED);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage("用户Id获取失败");
		}
		return response;
	}

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse addEmployee(@RequestBody EmployeeRegisterDTO employeeRegisterDTO) {
		AjaxResponse response = new AjaxResponse();
		boolean flag = true;
		try {
			flag = userApi.addEmployee(employeeRegisterDTO, SecurityContextUtils.getUserDTOFromSession().getId());
			response.setStatus(AjaxResponseStatus.SUCCESS);
			response.setData(flag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage(e.getMessage());
		}
		if (!flag) {
			logger.error("新增员工失败", AjaxResponseStatus.FAILED);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage("新增员工失败");
		}
		return response;
	}

	@RequestMapping(value = "/employee/list")
	public String listEmployee(Model model) throws Exception {
		Map<String, String> roleList = roleApi.queryRolesIdAndName(SecurityContextUtils.getUserDTOFromSession().getId(),
				RoleType.LEVEL2, null);
		model.addAttribute("rolesIdAndName", roleList);
		model.addAttribute("accountActivateStatus", AccountActivateStatus.values());
		return "page/employee/search";
	}

	@RequestMapping(value = "/employee/result")
	@ResponseBody
	public ListResult<EmployeeSearchResultDTO> listEmployeeResult(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO) {
		ListResult<EmployeeSearchResultDTO> result = new ListResult<>();
		try {
			result = userApi.queryEmployeeList(employeeSearchCriteriaDTO,
					SecurityContextUtils.getUserDTOFromSession().getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = ListResult.fail(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/employee/employeeDetail")
	@ResponseBody
	public AjaxResponse employeeDetail(@RequestParam String employeeId) {
		AjaxResponse response = new AjaxResponse();
		EmployeeSearchResultDTO detail = null;
		try {
			detail = userApi.queryEmployeeDetail(employeeId);
			response.setStatus(AjaxResponseStatus.SUCCESS);
			response.setData(detail);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage(e.getMessage());
		}
		if (detail == null) {
			logger.error("获取用户信息失败", AjaxResponseStatus.FAILED);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage("获取用户详细信息失败");
		}
		return response;
	}

	@RequestMapping(value = "/employee/gotoModify")
	public String gotoModify(@RequestParam String employeeId, Model model) throws Exception {
		EmployeeSearchResultDTO employeeSearchResultDTO = userApi.queryEmployeeDetail(employeeId);
		model.addAttribute("employeeSearchResultDTO", employeeSearchResultDTO);
		model.addAttribute("roles", roleApi.queryRolesIdAndName(SecurityContextUtils.getUserDTOFromSession().getId(),
				RoleType.LEVEL2, null));
		return "page/employee/modify";
	}

	@RequestMapping(value = "/employee/modify")
	@ResponseBody
	public AjaxResponse modifyEmployee(@RequestBody EmployeeRegisterDTO employeeRegisterDTO) {
		AjaxResponse response = new AjaxResponse();
		boolean flag = true;
		try {
			flag = userApi.modifyEmployee(employeeRegisterDTO);
			response.setStatus(AjaxResponseStatus.SUCCESS);
			response.setData(flag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/employee/delete")
	@ResponseBody
	public AjaxResponse deleteEmployee(@RequestParam String employeeId) {
		AjaxResponse response = new AjaxResponse();
		boolean flag = true;
		try {
			flag = userApi.deleteEmployee(employeeId);
			response.setStatus(AjaxResponseStatus.SUCCESS);
			response.setData(flag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage(e.getMessage());
		}
		if (!flag) {
			logger.error("删除员工失败", AjaxResponseStatus.FAILED);
			response.setStatus(AjaxResponseStatus.FAILED);
			response.setMessage("删除员工失败");
		}
		return response;
	}

	@RequestMapping(value = "/{requestPath}/resend")
	@ResponseBody
	public AjaxResponse resendEmail(@PathVariable("requestPath") String requestPath, @RequestParam String userId) {
		AjaxResponse response = new AjaxResponse();
		boolean flag = true;
		if ("employee".equals(requestPath) || "customer".equals(requestPath)) {
			try {
				flag = userApi.resendEmail(userId);
				response.setStatus(AjaxResponseStatus.SUCCESS);
				response.setData(flag);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				response.setStatus(AjaxResponseStatus.FAILED);
				response.setMessage(e.getMessage());
			}
			if (!flag) {
				logger.error("重发邮件失败", AjaxResponseStatus.FAILED);
				response.setStatus(AjaxResponseStatus.FAILED);
				response.setMessage("重发邮件失败");
			}
		}
		return response;
	}

	@RequestMapping(value = "/{requestPath}/restPassword")
	@ResponseBody
	public AjaxResponse restPassword(@PathVariable("requestPath") String requestPath, @RequestParam String userId) {
		AjaxResponse response = new AjaxResponse();
		boolean flag = true;
		if ("employee".equals(requestPath) || "customer".equals(requestPath)) {
			try {
				flag = userApi.resetPassword(userId);
				response.setStatus(AjaxResponseStatus.SUCCESS);
				response.setData(flag);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				response.setStatus(AjaxResponseStatus.FAILED);
				response.setMessage(e.getMessage());
			}
			if (!flag) {
				logger.error("重置密码失败", AjaxResponseStatus.FAILED);
				response.setStatus(AjaxResponseStatus.FAILED);
				response.setMessage("重置密码失败");
			}
		}
		return response;
	}

	
	@RequestMapping(value = "/{requestPath}/gotoEditPermissions")
	public String gotoEditPermissions(@PathVariable("requestPath") String requestPath, @RequestParam String userId, Model model) throws Exception {
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if ("customer".equals(requestPath)) {
			CustomerDetailDTO customerDTO = userApi.queryCustomerDetail(userId);
			if(null == customerDTO){
				throw WebException.instance("该企业不存在");
			}
			if(UserType.CANA != userSessionDTO.getUserType())
				throw WebException.instance("您没有权限访问该页面！");
		} else if("employee".equals(requestPath)){
			EmployeeSearchResultDTO employeeDTO = userApi.queryEmployeeDetail(userId);
			if(null == employeeDTO){
				throw WebException.instance("该员工不存在");
			}
			if(UserType.CANA != userSessionDTO.getUserType()){
				if(!userSessionDTO.getId().equals(employeeDTO.getMasterId()))
					throw WebException.instance("您没有权限访问该页面！");
			}
		}else{
			throw WebException.instance("访问的页面不存在");
		}
		model.addAttribute("userId", userId);
		model.addAttribute("type", requestPath);
		return "page/permission/editUserPermissions";
	}
	
	@RequestMapping(value = "/{requestPath}/editPermissions")
	@ResponseBody
	public ObjectResult<String> editPermissions(@PathVariable("requestPath") String requestPath, @RequestParam String userId,
			@RequestParam String permissions){
		try{
			if ("employee".equals(requestPath) || "customer".equals(requestPath)) {
				userApi.updateUserPermissions(userId, permissions);
			}else{
				throw WebException.instance("请求有误，请刷新页面");
			}
		}catch (WebException e) {
			logger.error(e.getMessage(), e);
			ObjectResult.fail(e.getMessage());
		}
		return ObjectResult.success("个性权限设置成功");
	}
	
	@RequestMapping(value = "/user/modifyLoginPwd")
	@ResponseBody
	public AjaxResponse modifyLoginPwd(String userId, String oldPwd, String newPwd, String flag) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		if (!Pattern.matches(RegexRule.PASSWORD, newPwd)) {
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("新密码格式有误");
		}
		boolean isSet;
		int temp = Integer.parseInt(flag);
		try {
			isSet = userApi.modifyLoginPassword(userId, PasswordEncoderUtil.encrypt(oldPwd),
						PasswordEncoderUtil.encrypt(newPwd));
			if (isSet) {
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
			} else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("登录密码修改失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("登录密码修改异常");
		}
		return ajaxResponse;
	}
	@RequestMapping(value = "/user/setPayPwd")
	@ResponseBody
	public AjaxResponse setPayPwd(String userId, String oldPwd, String newPwd, String flag) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		if (!Pattern.matches(RegexRule.PASSWORD, newPwd)) {
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("新密码格式有误");
		}
		boolean isSet;
		try {
				isSet = userApi.setPayPwd(userId, PasswordEncoderUtil.encrypt(oldPwd),
						PasswordEncoderUtil.encrypt(newPwd));
			if (isSet) {
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
			} else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("支付密码设置失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("支付密码设置异常");
		}
		return ajaxResponse;
	}
	@RequestMapping(value = "/user/modifyPayPwd")
	@ResponseBody
	public AjaxResponse modifyPayPwd(String userId, String oldPwd, String newPwd, String flag) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		if (!Pattern.matches(RegexRule.PASSWORD, newPwd)) {
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("新密码格式有误");
		}
		boolean isSet;
		int temp = Integer.parseInt(flag);
		try {
				isSet = userApi.modifyPayPwd(userId, PasswordEncoderUtil.encrypt(oldPwd),
						PasswordEncoderUtil.encrypt(newPwd));
			if (isSet) {
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
			} else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("支付密码修改失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("支付密码修改异常");
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/user/isloginPwd")
	@ResponseBody
	public AjaxResponse isLoginPwd(String userId, String loginPwd, String flag) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		try {
			if (userApi.isloginPwd(userId, PasswordEncoderUtil.encrypt(loginPwd), Integer.parseInt(flag)))
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
			else
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
		} catch (Exception e) {
			logger.error("判断登陆密码是否正确异常：" + e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
		}
		return ajaxResponse;
	}

	/**
	 * 跳转到企业类型角色详情页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/gotoCompanyRoleDetails")
	public String gotoCompanyRoleDetails(@RequestParam String roleId, @RequestParam String type, Model model)
			throws Exception {
		if(StringUtils.isBlank(roleId)){
			throw WebException.instance("角色参数为空");
		}
		RoleSearchResultListDTO roleList = new RoleSearchResultListDTO();
		List<RoleSearchResultDTO> roleSearchList = Lists.newArrayList();
		for(String roleIdStr:roleId.split(Constants.ROLE_SPILT_SIMBOL)){
			RoleSearchResultDTO roleSearchResultDTO = roleApi.getRoleById(roleIdStr);
			roleSearchList.add(roleSearchResultDTO);
		}
		roleList.setRoleSearchResultDTOs(roleSearchList);
		model.addAttribute("type", type);
		model.addAttribute("role", roleList);
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/customer/center/company_role_detail";
	}

	@RequestMapping(value = "/user/modifyContactInfo")
	@ResponseBody
	public AjaxResponse modifyContactsInfo(String userId, String contactName, String jobTitle, String mobileNum,
			String mail) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		try {
			if (userApi.modifyContactsInfo(SecurityContextUtils.getOperatorId(), contactName, jobTitle, mobileNum, mail)) {
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
				ajaxResponse.setMessage("修改联系人信息成功");
			} else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("修改联系人信息失败");
			}
		} catch (Exception e) {
			logger.error("修改联系人信息：" + e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage(e.getMessage());
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/user/modifyPersonalInfo")
	@ResponseBody
	public AjaxResponse modifyPersonalInfo(String userId, String contactName, String jobTitle, String mobileNum,
			String mail) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		try {
			if (userApi.modifyPersonalInfo(userId, contactName, jobTitle, mobileNum, mail)) {
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
				ajaxResponse.setMessage("基本信息修改成功");
			} else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("个人基本信息修改失败");
			}
		} catch (Exception e) {
			logger.error("修改个人基本信息异常：" + e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("个人基本信息修改异常");
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/user/modifyMedia")
	@ResponseBody
	public AjaxResponse modifyMediaId(String userId, String orgMediaId, String busMediaId, String taxMediaId) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		try {
			if (userApi.modifyMediaId(userId, orgMediaId, busMediaId, taxMediaId))
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
			else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("修改证件信息失败");
			}
		} catch (Exception e) {
			logger.error("修改证件信息：" + e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage("修改证件信息异常");
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/user/resetPayPassword")
	@ResponseBody
	public AjaxResponse resetPayPassword(String userId) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		try {
			if (userApi.resetPayPassword(userId)) {
				ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
			} else {
				ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
				ajaxResponse.setMessage("重置支付密码失败");
			}
		} catch (Exception e) {
			logger.error("重置支付密码失败：" + e.getMessage());
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
			ajaxResponse.setMessage(e.getMessage());
		}
		return ajaxResponse;
	}

	@RequestMapping(value="/user/accountInfo")
	@ResponseBody
	public AjaxResponse getAccountInfo(String userId){
		AjaxResponse ajaxResponse=new AjaxResponse();
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		String masterId=StringUtils.isBlank(userSessionDTO.getMasterId())?userSessionDTO.getId():userSessionDTO.getMasterId();
		try{
			AccountBalancesAndNumberDTO account=accountApi.getAccountBalancesAndNumber(masterId);
			ajaxResponse.setData(account);
			ajaxResponse.setStatus(AjaxResponseStatus.SUCCESS);
		}catch(Exception e){
			logger.error("获取账户信息异常"+e.getMessage());
			ajaxResponse.setMessage("获取账户信息异常");
			ajaxResponse.setStatus(AjaxResponseStatus.FAILED);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value="/user/contract",method=RequestMethod.POST)
	@ResponseBody
	public ListResult<ContractInfoDTO> getContractList(ContractListReqDTO request){
	    try{
	    	UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			String masterId=StringUtils.isBlank(userSessionDTO.getMasterId())?userSessionDTO.getId():userSessionDTO.getMasterId();
	        ListResult<ContractInfoDTO> result=assetApi.getCompanyContracts(request,masterId);
	        return result;
	    }catch(Exception e){
	        logger.info("获取合同列表异常"+e);
	        return ListResult.fail("获取合同列表出错");
	    }
	}

}
