package com.cana.member.server.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.member.api.IUserApi;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.member.dao.po.UserExample;
import com.cana.member.service.transaction.IUserTransactionService;
import com.cana.vbam.common.credit.dto.apply.CustomerApply4MemberDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.dto.user.CompanyInfoDTO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.CustomerRegisterDTO;
import com.cana.vbam.common.member.dto.user.CustomerReviewDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchResultDTO;
import com.cana.vbam.common.member.dto.user.EmployeeRegisterDTO;
import com.cana.vbam.common.member.dto.user.EmployeeSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.EmployeeSearchResultDTO;
import com.cana.vbam.common.member.dto.user.PersonalDetailDTO;
import com.cana.vbam.common.member.dto.user.UserActivationDTO;
import com.cana.vbam.common.member.dto.user.UserRoleListUpdateDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.cana.vbam.common.member.enums.user.AccountActivateStatus;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.RegexRule;
import com.cana.vbam.common.vj.dto.ApplyCreditRequest;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;
import com.dianping.cat.Cat;
import com.travelzen.framework.core.exception.WebException;

public class UserApiImpl implements IUserApi {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private IUserTransactionService userTransactionServiceImpl;
	
	@Override
	public String addCustomer(CustomerRegisterDTO customerRegisterDTO) throws Exception {
		if(!verifyRegister(customerRegisterDTO)){
			throw WebException.instance("填写的信息有误");
		}
		return userTransactionServiceImpl.addCustomer(customerRegisterDTO);
	}
	
	private boolean verifyRegister(CustomerRegisterDTO customerRegisterDTO) {
		if(null != customerRegisterDTO){
			if(StringUtils.isBlank(customerRegisterDTO.getUsername()) && Pattern.matches("[0-9|a-z|A-Z|-|_]{6,20}", customerRegisterDTO.getUsername()))
				return false;
			if(StringUtils.isBlank(customerRegisterDTO.getCompanyName()))
				return false;
			if(StringUtils.isBlank(customerRegisterDTO.getOrganizationCode()))
				return false;
			if(StringUtils.isBlank(customerRegisterDTO.getBusinessLicenceCode()))
				return false;
			if(StringUtils.isBlank(customerRegisterDTO.getContactName()))
				return false;
			if(StringUtils.isBlank(customerRegisterDTO.getContactMail()) && Pattern.matches("^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$", customerRegisterDTO.getContactMail()))
				return false;
			if(StringUtils.isBlank(customerRegisterDTO.getContactTel()) && Pattern.matches("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$", customerRegisterDTO.getContactTel()))
				return false;
			if(customerRegisterDTO.getUserType() != null)
				return true;
		}
		return false;
	}

	@Override
	public boolean addEmployee(EmployeeRegisterDTO employeeRegisterDTO, String currentOperatorId) throws Exception {
		if(employeeRegisterDTO == null){
			throw WebException.instance("员工信息不能为空");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getId())){
			throw WebException.instance("员工id不能为空");
		}
		if(userTransactionServiceImpl.queryEmployeeDetail(employeeRegisterDTO.getId())!=null){
			throw WebException.instance("员工id已存在");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getEmployeeMail())){
			throw WebException.instance("员工邮箱不能为空");
		}
		if(CollectionUtils.isEmpty(employeeRegisterDTO.getRoleIdList())){
			throw WebException.instance("员工角色不能为空");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getRealName())){
			throw WebException.instance("员工姓名不能为空");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getEmployeeTel())){
			throw WebException.instance("员工电话不能为空");
		}
		Pattern p = Pattern.compile("^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$");
        if(!p.matcher(employeeRegisterDTO.getEmployeeMail()).find()){
        	throw WebException.instance("员工邮箱格式不合法");
        }
        p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$");
        if(!p.matcher(employeeRegisterDTO.getEmployeeTel()).find()){
        	throw WebException.instance("员工联系电话格式不合法");
        }
		return userTransactionServiceImpl.addEmployee(employeeRegisterDTO, currentOperatorId);
	}

	@Override
	public boolean checkUsernameExist(String username) throws Exception {
		return userTransactionServiceImpl.checkUsernameExist(username);
	}

	@Override
	public boolean checkCompanyNameExist(String companyName, UserType userType) throws Exception {
		return userTransactionServiceImpl.checkCompanyNameExist(companyName, userType);
	}

	@Override
	public CustomerReviewDTO gotoReview(String customerId)throws Exception{
		return userTransactionServiceImpl.gotoReview(customerId);
	}
	
	@Override
	public void review(CustomerDetailDTO customerDetailDTO) throws Exception {
		userTransactionServiceImpl.review(customerDetailDTO);
	}
	
	@Override
	public boolean resendEmail(String userId)throws Exception{
		if(StringUtils.isBlank(userId)){
			Cat.logMetricForCount("Resend_Email_Fail");
			throw WebException.instance("员工Id不能为空");
		}
		EmployeeSearchResultDTO user = userTransactionServiceImpl.queryEmployeeDetail(userId);
		if(user==null){
			Cat.logMetricForCount("Resend_Email_Fail");
			throw WebException.instance("该员工不存在");
		}
		if(StringUtils.endsWith(AccountActivateStatus.ACTIVATED.name(), user.getAccountActivateStatus().name())){
			Cat.logMetricForCount("Resend_Email_Fail");
			throw WebException.instance("该员工账户已激活，不能重发邮件");
		}
		return userTransactionServiceImpl.resendEmail(userId);
	}
	/**
     * 生成激活url
     */
    public String generateActivacationURL(String userId) throws Exception {
        return userTransactionServiceImpl.generateActivacationURL(userId);
    }

	@Override
	public UserActivationDTO goToActivation(String userId,String securityCode) throws Exception {
		return userTransactionServiceImpl.goToActivation(userId, securityCode);
	}
	
	@Override
	public UserActivationDTO activate(UserActivationDTO userActivationDTO) throws Exception{
		return userTransactionServiceImpl.activate(userActivationDTO);
	}

	@Override
	public UserSessionDTO getUserSession(String username) throws Exception {
		return userTransactionServiceImpl.getUserSession(username);
	}

	@Override
	public String generateUserId() throws Exception {
		return userTransactionServiceImpl.generateUserId();
	}

	public boolean updateUserLoginInfo(String id, String token, boolean signIn, String signIp, Date signTime)
			throws Exception {
		Cat.logMetricForCount("Update_User_Login_Info");
		return userTransactionServiceImpl.upateUserLoginInfo(id, token, signIn, signIp, signTime);
	}

	@Override
	public ListResult<CustomerSearchResultDTO> queryCustomerList(CustomerSearchCriteriaDTO customerSearchCriteriaDTO)
			throws Exception {
		return userTransactionServiceImpl.queryCustomerList(customerSearchCriteriaDTO);
	}

	@Override
	public CustomerDetailDTO queryCustomerDetail(String customerId){
		return userTransactionServiceImpl.queryCustomerDetail(customerId);
	}
	
	@Override
    public CustomerDetailDTO queryCustomerByCompanyName(String companyName, UserType userType) {
        return userTransactionServiceImpl.queryCustomerByCompanyName(companyName, userType);
    }
	
	public ListResult<EmployeeSearchResultDTO> queryEmployeeList(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO,String currentOperatorId)throws Exception{
		if( employeeSearchCriteriaDTO == null ){
			throw WebException.instance("搜索条件不能为null");
		}
		if(StringUtils.isBlank(currentOperatorId)){
			throw WebException.instance("当前登录的用户不能为空");
		}
		return userTransactionServiceImpl.queryEmployeeList(employeeSearchCriteriaDTO, currentOperatorId);
	}

	@Override
	public EmployeeSearchResultDTO queryEmployeeDetail(String employeeId) throws Exception {
		if(StringUtils.isBlank(employeeId)){
			throw WebException.instance("员工Id不能为空");
		}
		return userTransactionServiceImpl.queryEmployeeDetail(employeeId);
	}

	@Override
	public boolean modifyEmployee(EmployeeRegisterDTO employeeRegisterDTO) throws Exception {
		if(employeeRegisterDTO == null){
			throw WebException.instance("员工信息不能为空");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getId())){
			throw WebException.instance("员工id不能为空");
		}
		if(userTransactionServiceImpl.queryEmployeeDetail(employeeRegisterDTO.getId())==null){
			throw WebException.instance("该员工不存在");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getEmployeeMail())){
			throw WebException.instance("员工邮箱不能为空");
		}
		if(CollectionUtils.isEmpty(employeeRegisterDTO.getRoleIdList())){
			throw WebException.instance("员工角色不能为空");
		}
		if(StringUtils.isBlank(employeeRegisterDTO.getEmployeeTel())){
			throw WebException.instance("员工电话不能为空");
		}
		Pattern p = Pattern.compile("^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$");
        if(!p.matcher(employeeRegisterDTO.getEmployeeMail()).find()){
        	throw WebException.instance("员工邮箱不合法");
        }
        p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$");
        if(!p.matcher(employeeRegisterDTO.getEmployeeTel()).find()){
        	throw WebException.instance("员工联系电话不合法");
        }
		return userTransactionServiceImpl.modifyEmployee(employeeRegisterDTO);
	}

	@Override
	public boolean deleteEmployee(String employeeId) throws Exception {
		if(StringUtils.isBlank(employeeId)){
			throw WebException.instance("员工id不能为空");
		}
		if(userTransactionServiceImpl.queryEmployeeDetail(employeeId)==null){
			throw WebException.instance("该员工不存在");
		}
		return userTransactionServiceImpl.deleteEmployee(employeeId);
	}

	@Override
	public boolean resetPassword(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("员工Id不能为空");
		}
		EmployeeSearchResultDTO user = userTransactionServiceImpl.queryEmployeeDetail(userId);
		if(user==null){
			throw WebException.instance("该员工不存在");
		}
		if(StringUtils.endsWith(AccountActivateStatus.UNACTIVATE.name(), user.getAccountActivateStatus().name())){
			throw WebException.instance("该员工账户未激活，不能重置密码");
		}
		return userTransactionServiceImpl.resetPassword(userId);
	}

	@Override
	public boolean updateRoleOfUser(UserUpdateDTO userUpdateDTO) throws Exception {
		return userTransactionServiceImpl.updateRoleOfUser(userUpdateDTO);
	}

	@Override
	public boolean updateRoleListOfUser(UserRoleListUpdateDTO userUpdateDTO) throws Exception {
		return userTransactionServiceImpl.updateRoleListOfUser(userUpdateDTO);
	}

	@Override
	public String getFinanceIdByName(String financeCompany, UserType userType) throws Exception
	{
		return userTransactionServiceImpl.getFinanceIdByName(financeCompany, userType);
	}

	@Override
	public boolean modifyLoginPassword(String userId, String oldPwd, String newPwd) throws Exception {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("用户Id不能为空");
		}
		if(StringUtils.isAnyBlank(oldPwd,newPwd)){
			throw WebException.instance("新密码或旧密码不能为空");
		}
		return userTransactionServiceImpl.modifyLoginPassword(userId, oldPwd, newPwd);
	}

	@Override
	public CompanyInfoDTO getCompanyInfo(UserSessionDTO sessionDTO) throws Exception {
		return userTransactionServiceImpl.getCompanyInfo(sessionDTO);
	}

	@Override
	public boolean isSetPayPassword(String masterId) throws Exception {
		if(StringUtils.isBlank(masterId))
			throw WebException.instance("企业Id不能为空");
		return userTransactionServiceImpl.isSetPayPassword(masterId);
	}

	@Override
	public boolean isloginPwd(String userId, String loginPwd,int flag) throws Exception {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("用户Id不能为空");
		}
		return userTransactionServiceImpl.isloginPwd(userId, loginPwd,flag);
	}

	@Override
	public boolean setPayPwd(String userId, String oldPwd, String newPwd) throws Exception {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("用户Id不能为空");
		}
		if(StringUtils.isAnyBlank(oldPwd,newPwd)){
			throw WebException.instance("新密码或旧密码不能为空");
		}
		return userTransactionServiceImpl.setPayPwd(userId, oldPwd, newPwd);
	}

	@Override
	public boolean modifyPayPwd(String userId, String oldPwd, String newPwd) throws Exception {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("用户Id不能为空");
		}
		if(StringUtils.isAnyBlank(oldPwd,newPwd)){
			throw WebException.instance("新密码或旧密码不能为空");
		}
		return userTransactionServiceImpl.modifyPayPwd(userId, oldPwd, newPwd);
	}

	@Override
	public boolean modifyContactsInfo(String userId, String contactName,String jobTitle, String mobileNum, String mail) throws Exception {
		if(StringUtils.isBlank(userId))
			throw WebException.instance("用户Id不能为空");
		if(StringUtils.isNotBlank(mobileNum)&&!Pattern.matches(RegexRule.TEL, mobileNum))
			throw WebException.instance("手机号码格式有误");
		if(StringUtils.isNotBlank(mail)&&!Pattern.matches(RegexRule.EMAIL, mail))
			throw WebException.instance("邮箱格式有误");
		return userTransactionServiceImpl.modifyContactsInfo(userId, contactName,jobTitle, mobileNum, mail);
	}

	@Override
	public boolean modifyMediaId(String userId, String orgMediaId,String busMediaId,String taxMediaId) throws Exception {
		if(StringUtils.isBlank(userId))
			throw WebException.instance("用户Id不能为空");
		return userTransactionServiceImpl.modifyMediaId(userId, orgMediaId, busMediaId, taxMediaId);
	}

	@Override
	public boolean validatePayPwd(String masterId, String payPwd) throws Exception {
		if(StringUtils.isBlank(masterId))
			throw WebException.instance("企业Id不能为空");
		if(StringUtils.isBlank(payPwd))
			throw WebException.instance("支付密码不能为空");
		return userTransactionServiceImpl.validatePayPwd(masterId, payPwd);
	}

    @Override
    public boolean updateUserGuideStatus(String masterId, UserGuideStatus oldGuideStatus,
            UserGuideStatus guideStatus) throws Exception {
        return userTransactionServiceImpl.updateUserGuideStatus(masterId, oldGuideStatus, guideStatus);
    }

	@Override
	public PersonalDetailDTO queryPersonalDetailInfo(String id) throws Exception {
		if(StringUtils.isBlank(id))
			throw WebException.instance("id不能为空");
		return userTransactionServiceImpl.queryPersonalDetailInfo(id);
	}

	@Override
	public boolean modifyPersonalInfo(String userId, String contactName, String jobTitle, String mobileNum, String mail)
			throws Exception {
		if(StringUtils.isBlank(userId))
			throw WebException.instance("用户Id不能为空");
		if(StringUtils.isNotBlank(mobileNum)&&!Pattern.matches(RegexRule.TEL, mobileNum))
			throw WebException.instance("手机号码格式有误");
		if(StringUtils.isNotBlank(mail)&&!Pattern.matches(RegexRule.EMAIL, mail))
			throw WebException.instance("邮箱格式有误");
		return userTransactionServiceImpl.modifyPersonalInfo(userId, contactName, jobTitle, mobileNum, mail);
	}

	@Override
	public boolean resetPayPassword(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("员工Id不能为空");
		}
		EmployeeSearchResultDTO user = userTransactionServiceImpl.queryEmployeeDetail(userId);
		if(user == null){
			throw WebException.instance("该员工不存在");
		}
		if(StringUtils.endsWith(AccountActivateStatus.UNACTIVATE.name(), user.getAccountActivateStatus().name())){
			throw WebException.instance("该账户尚未激活，暂无法重置");
		}
		if(!isSetPayPassword(userId)){
			throw WebException.instance("该账户尚未设置支付密码，暂无法重置！");
		}
		return userTransactionServiceImpl.resetPayPassword(userId);
	}
	
	@Override
	public List<String> getCustomerIds(int page, int pageSize){
		UserExample example = new UserExample();
		example.setLimitStart((page - 1) * pageSize);
		example.setLimitEnd(pageSize);
		example.setOrderByClause("-id");
		example.createCriteria().andMasterIdIsNull().andUserStatusEqualTo(UserStatus.ACTIVATED.name());
		List<User> users = userMapper.selectByExample(example);
		List<String> customerIds = new ArrayList<>();
		for (User user : users) {
			customerIds.add(user.getId());
		}
		return customerIds;
	}
	
	@Override
	public Map<String, List<String>> getAllUserIds(){
		UserExample example = new UserExample();
		example.createCriteria().andMasterIdIsNull().andUserStatusEqualTo(UserStatus.ACTIVATED.name());
		List<User> users = userMapper.selectByExample(example);
		List<String> factors = new ArrayList<>();
		List<String> finances = new ArrayList<>();
		List<String> coreCompany = new ArrayList<>();
		List<String> cana = new ArrayList<>();
		for (User user : users) {
			switch (UserType.valueOf(user.getUserType())) {
			case CANA:
				cana.add(user.getId());
				break;
			case FACTOR:
				factors.add(user.getId());
				break;
			case FINACE:
				finances.add(user.getId());
				break;
			case CORECOMPANY:
				coreCompany.add(user.getId());
				break;
			default:
				break;
			}
		}
		Map<String, List<String>> allUserIds = new HashMap<String, List<String>>();
		allUserIds.put(UserType.CANA.name(), cana);
		allUserIds.put(UserType.FACTOR.name(), factors);
		allUserIds.put(UserType.FINACE.name(), finances);
		allUserIds.put(UserType.CORECOMPANY.name(), coreCompany);
		return allUserIds;
	}

	@Override
	public String getCanaId() {
		UserExample example = new UserExample();
		example.createCriteria().andMasterIdIsNull().andUserStatusEqualTo(UserStatus.ACTIVATED.name()).andUserTypeEqualTo(UserType.CANA.name());
		List<User> users = userMapper.selectByExample(example);
		return users.get(0).getId();
	}

	@Override
	public boolean createCustomerByCredit(CustomerApply4MemberDTO customerApply4MemberDTO, String taskId) {
		return userTransactionServiceImpl.createCustomerByCredit(customerApply4MemberDTO, taskId);
	}

	@Override
	public void updateCustomerCertDN(String userId, String certSubjectDN){
		userTransactionServiceImpl.updateCustomerCertDN(userId, certSubjectDN);
	}

	@Override
	public String queyCusomerCertDN(String userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user.getCertSubjectDn();
	}

	@Override
	public String queryOrGenerateUserId(String companyName) {
		return userTransactionServiceImpl.queryOrGenerateUserId(companyName, null);
	}
	@Override
	public String queryOrGenerateUserId(String companyName, String individualIdentityCardNo) {
		return userTransactionServiceImpl.queryOrGenerateUserId(companyName, individualIdentityCardNo);
	}

	@Override
	public boolean createYundaexCustomerByCredit(YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO,
			String taskId) {
		return userTransactionServiceImpl.createYundaexCustomerByCredit(ydCustomerApply4MemberUserDTO, taskId);
	}	

	public CustomerDetailDTO createIndividualUser4VJ(ApplyCreditRequest applyRequest) throws Exception{
		return userTransactionServiceImpl.createIndividualUser4VJ(applyRequest);
	}

	@Override
	public CustomerDetailDTO queryIndividualUser(String name, String identityCardNo) {
		return userTransactionServiceImpl.queryIndividualUser(name, identityCardNo);
	}

	@Override
	public void updateContactName(String memberId, String contactName) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("用户Id不能为空");
		userTransactionServiceImpl.updateContactName(memberId, contactName);
	}
	
	@Override
	public void updateContactTel(String memberId, String contactTel) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("用户Id不能为空");
		if(StringUtils.isNotBlank(contactTel)&&!Pattern.matches(RegexRule.TEL, contactTel))
			throw WebException.instance("手机号码格式有误");
		userTransactionServiceImpl.updateContactTel(memberId, contactTel);
	}
	

	@Override
	public void updateContactMail(String memberId, String contactMail) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("用户Id不能为空");
		if(StringUtils.isNotBlank(contactMail)&&!Pattern.matches(RegexRule.EMAIL, contactMail))
			throw WebException.instance("邮箱格式有误");
		userTransactionServiceImpl.updateContactMail(memberId, contactMail);
	}

	@Override
	public void updateJobTitle(String memberId, String jobTitle) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("用户Id不能为空");
		userTransactionServiceImpl.updateJobTitle(memberId, jobTitle);
	}

	@Override
	public String queryUserIdByCompanyName(String companyName) {
		return userTransactionServiceImpl.queryUserIdByCompanyName(companyName);
	}
	@Override
	public void updateUserPermissions(String userId, String permissions) {
		userTransactionServiceImpl.updateUserPermissions(userId, permissions);
	}

	@Override
	public CustomerDetailDTO checkCustomerIsValid(String customerId) {
		return userTransactionServiceImpl.checkCustomerIsValid(customerId);
	}

}
