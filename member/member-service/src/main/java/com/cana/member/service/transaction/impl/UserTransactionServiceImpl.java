package com.cana.member.service.transaction.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.member.dao.mapper.MemberTableLockMapper;
import com.cana.member.dao.mapper.QueryUserForReportMapper;
import com.cana.member.dao.mapper.gen.AuditMapper;
import com.cana.member.dao.mapper.gen.RoleMapper;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.Audit;
import com.cana.member.dao.po.AuditExample;
import com.cana.member.dao.po.Role;
import com.cana.member.dao.po.User;
import com.cana.member.dao.po.UserExample;
import com.cana.member.dao.po.UserExample.Criteria;
import com.cana.member.service.transaction.IRoleService;
import com.cana.member.service.transaction.IUserTransactionService;
import com.cana.message.client.message.MessageClient;
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
import com.cana.vbam.common.member.dto.user.RoleDTO;
import com.cana.vbam.common.member.dto.user.UserActivationDTO;
import com.cana.vbam.common.member.dto.user.UserRoleListUpdateDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.cana.vbam.common.member.enums.user.AccountActivateStatus;
import com.cana.vbam.common.member.enums.user.AccountAuditResult;
import com.cana.vbam.common.member.enums.user.AccountAuditStatus;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.cana.vbam.common.utils.WebEnv;
import com.cana.vbam.common.vj.dto.ApplyCreditRequest;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;
import com.dianping.cat.Cat;
import com.google.common.collect.Lists;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class UserTransactionServiceImpl implements IUserTransactionService {
	
	private static final Logger LGR = LoggerFactory.getLogger(UserTransactionServiceImpl.class);

	@Resource
	private UserMapper userMapper;

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private AuditMapper auditMapper;
	
	@Resource
	private MemberTableLockMapper tablelockMapper;

	@Resource
	private IRoleService roleServiceImpl;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource(name = "mailRabbitTemplate")
	private RabbitTemplate mailRabbitTemplate;
	
	@Resource
	private MessageClient messageClient;
	
	@Resource
	private QueryUserForReportMapper queryUserForReportMapper;

	@Override
	public String addCustomer(CustomerRegisterDTO customerRegisterDTO) throws Exception {
		User user = convertCustomerRegisterDTOToUser(customerRegisterDTO);
		userMapper.insertSelective(user);
		sendRegisterNotification(user);
		return user.getId();
	}
	
	private void sendRegisterNotification(User user) {
		NotificationMessageDTO message = new NotificationMessageDTO();
		message.setType(NotificationType.CUSTOMER_REGISTRY);
		message.setReceiveCustomerId(Constants.CANA_CUSTOMER_ID);
		message.setContent("企业用户"+user.getCompanyName()+"提交注册开户申请。");
		message.setDetailURL("/customer/notification/redirect?customerId="+user.getId());
		messageClient.sendNotification(message);
	}


	/**
	 * 转换convertCustomerRegisterDTOToUser成User
	 * 
	 * @param customerRegisterDTO
	 * @return
	 * @throws Exception
	 */
	private User convertCustomerRegisterDTOToUser(CustomerRegisterDTO customerRegisterDTO) throws Exception {
		User user = new User();
		BeanUtils.copyProperties((CustomerRegisterDTO) StringUtil.trimObjectFields(customerRegisterDTO), user);
		user.setUserStatus(UserStatus.PENDINGAUDIT.name());
		if (null != customerRegisterDTO.getUserType()) {
			user.setUserType(customerRegisterDTO.getUserType().name());
		} else {
			LGR.error("获取用户类型失败，注册用户失败");
			throw WebException.instance("注册用户出错，请重新注册");
		}
		if(checkUsernameExist(customerRegisterDTO.getUsername())){
			LGR.error("重复提交注册用户");
			throw WebException.instance("重复提交注册用户");
		}
		user.setId(generateUserId());
		user.setCreateTime(new Date());
		return user;
	}

	@Override
	public boolean addEmployee(EmployeeRegisterDTO employeeRegisterDTO, String currentOperatorId) throws Exception {
		employeeRegisterDTO.setMasterId(getMasterId(currentOperatorId));
		User user = convertEmployeeRegisterDTOToUser(employeeRegisterDTO);
		int index = 0;
		StringBuilder sb = new StringBuilder();
		for(String roleId : employeeRegisterDTO.getRoleIdList()){
			if(index == 0){
				sb.append(roleId);
			}else{
				sb.append(Constants.ROLE_SPILT_SIMBOL + roleId);
			}
			index++;
		}
		user.setRoleId(sb.toString());
		int count = userMapper.insertSelective(user);
		mailRabbitTemplate.convertAndSend(generateMailMessage(user));
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 转换EmployeeRegisterDTO成User
	 * 
	 * @param employeeRegisterDTO
	 * @return
	 */
	private User convertEmployeeRegisterDTOToUser(EmployeeRegisterDTO employeeRegisterDTO) {
		User user = new User();
		BeanUtils.copyProperties((EmployeeRegisterDTO) StringUtil.trimObjectFields(employeeRegisterDTO), user);
		if (null != employeeRegisterDTO.getUserStatus()) {
			user.setUserStatus(employeeRegisterDTO.getUserStatus().name());
		} else {
			user.setUserStatus(UserStatus.PENDINGACTIVATE.name());
		}
		user.setSecurityCode(generateSecurityCode());
		user.setSecurityCodeExpirationTime(new DateTime().plusDays(Integer.parseInt(
				TopsConfReader.getConfContent("properties/mail.properties", "mail.validity.customer", ConfScope.R)))
				.toDate());
		user.setCreateTime(new Date());
		return user;
	}

	@Override
	public boolean checkUsernameExist(String username) throws Exception {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(StringUtil.trim(username))
				.andUserStatusNotIn(Arrays.asList(UserStatus.REJECTED.name(), UserStatus.DELETED.name()));
		List<User> users = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users))
			return false;
		return true;
	}

	@Override
	public boolean checkCompanyNameExist(String companyName, UserType userType) throws Exception {
		UserExample example = new UserExample();
		example.createCriteria().andCompanyNameEqualTo(StringUtil.trim(companyName))
				.andUserStatusNotEqualTo(UserStatus.REJECTED.name())
				.andUserTypeEqualTo(userType.name());
		List<User> users = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users))
			return false;
		return true;
	}

	@Override
	public CustomerReviewDTO gotoReview(String customerId) throws Exception {
		User user = userMapper.selectByPrimaryKey(customerId);
		if (!UserStatus.PENDINGAUDIT.name().equals(user.getUserStatus())) {
			throw WebException.instance("账号不能审核");
		}
		return convertUserToCustomerReviewDTO(user);
	}

	/**
	 * 转换User成CustomerReviewDTO
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private CustomerReviewDTO convertUserToCustomerReviewDTO(User user) throws Exception {
		CustomerReviewDTO customerReviewDTO = new CustomerReviewDTO();
		BeanUtils.copyProperties(user, customerReviewDTO);
		customerReviewDTO.setUserType(UserType.valueOf(user.getUserType()));
		customerReviewDTO.setUserStatus(UserStatus.valueOf(user.getUserStatus()));
		if (StringUtils.isBlank(customerReviewDTO.getAgentCompany())) {
			customerReviewDTO.setAgentCompany(user.getCompanyName());
		}
		return customerReviewDTO;
	}

	@Override
	public void review(CustomerDetailDTO customerDetailDTO) throws Exception {
		User user = tablelockMapper.lockMemberUserById(customerDetailDTO.getId());
		if (null == user) {
			throw WebException.instance("该用户不存在，审核失败。");
		}
		if(CollectionUtils.isEmpty(customerDetailDTO.getRoleDTOList()) || StringUtils.isBlank(customerDetailDTO.getRoleDTOList().get(0).getRoleId())){
			throw WebException.instance("角色不能为空，请先选择角色或创建角色");
		}
		Audit audit = new Audit();
		audit.setId(generateAuditId());
		audit.setAuditorId(customerDetailDTO.getAuditorId());
		audit.setAuditMessage(customerDetailDTO.getAuditMessage());
		audit.setAuditStatus(customerDetailDTO.getIntUserAuditStatus());
		audit.setCustomerId(customerDetailDTO.getId());
		audit.setCreateTime(new Date());
		auditMapper.insertSelective(audit);
		if (customerDetailDTO.isUserAuditPassed()) {
			user.setUserStatus(UserStatus.PENDINGACTIVATE.name());
			user.setRoleId(convertRoleListToStr(customerDetailDTO.getRoleDTOList()));
			user.setSecurityCode(generateSecurityCode());
			user.setSecurityCodeExpirationTime(new DateTime().plusDays(Integer.parseInt(
					TopsConfReader.getConfContent("properties/mail.properties", "mail.validity.customer", ConfScope.R)))
					.toDate());
		} else {
			user.setUserStatus(UserStatus.REJECTED.name());
		}
		user.setUpateTime(new Date());
		userMapper.updateByPrimaryKeySelective(user);
		mailRabbitTemplate.convertAndSend(generateMailMessage(user));
	}

	@Override
	public boolean resendEmail(String userId) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userId);
		if (UserStatus.PENDINGACTIVATE.name().equals(user.getUserStatus())) {
			user.setSecurityCode(generateSecurityCode());
			user.setSecurityCodeExpirationTime(new DateTime().plusDays(Integer.parseInt(
					TopsConfReader.getConfContent("properties/mail.properties", "mail.validity.admin", ConfScope.R)))
					.toDate());
			userMapper.updateByPrimaryKeySelective(user);
			mailRabbitTemplate.convertAndSend(generateMailMessage(user));
		}
		Cat.logMetricForCount("Resend_Email_Success");
		return true;
	}

	public String generateActivacationURL(String userId) throws Exception {
		if (StringUtils.isBlank(userId))
			throw WebException.instance("参数不能为空");
		User user = tablelockMapper.lockMemberUserById(userId);
		if (user == null)
			throw WebException.instance("用户不存在");
		if (UserStatus.PENDINGACTIVATE.name().equals(user.getUserStatus())) {
			if (StringUtils.isBlank(user.getSecurityCode())
					|| new DateTime(user.getSecurityCodeExpirationTime()).isBeforeNow()) {
				user.setSecurityCode(generateSecurityCode());
				user.setSecurityCodeExpirationTime(new DateTime().plusDays(Integer.parseInt(TopsConfReader
						.getConfContent("properties/mail.properties", "mail.validity.admin", ConfScope.R))).toDate());
				userMapper.updateByPrimaryKeySelective(user);
			}
			return generateActivacationURL(user.getId(), user.getSecurityCode());
		}
		if (UserStatus.ACTIVATED.name().equals(user.getUserStatus()))
			return null;
		throw WebException.instance("用户状态不合法");
	}

	/**
	 * 拼装激活邮件
	 * 
	 * @param user
	 * @return
	 */
	private MailMessageDTO generateMailMessage(User user) {
		MailMessageDTO mail = new MailMessageDTO();
		mail.setContentType(MailContentType.HTML);
		mail.setSubject("CANA用户激活");
		String content = "";
		if (UserStatus.PENDINGACTIVATE.name().equals(user.getUserStatus()) && StringUtils.isBlank(user.getMasterId())) {
			mail.setReceiver(user.getContactMail());
			content += "尊敬的"+ user.getCompanyName() + "用户:<br>&nbsp;&nbsp;&nbsp;&nbsp;您在CANA平台的注册申请已通过审核，";
			if(StringUtils.isNotBlank(user.getUsername()))
				content	+= "用户名:" + user.getUsername() + "，";
			content	+= "点击以下链接激活 【CANA金融】<br>" + "激活链接：&nbsp;<a href=" + generateActivacationURL(user.getId(), user.getSecurityCode()) + " "
					+ "target='_blank'>" + generateActivacationURL(user.getId(), user.getSecurityCode()) + "</a>";
		} else if (StringUtils.isNotBlank(user.getMasterId())) {
			User masterUser = userMapper.selectByPrimaryKey(user.getMasterId());
			mail.setReceiver(user.getEmployeeMail());
			content += "尊敬的"+ user.getRealName() +":<br>&nbsp;&nbsp;&nbsp;&nbsp;您隶属于" + masterUser.getCompanyName()
					+ "项下的子账号已生成，平台用户编号：" + user.getId() + "，请及时激活账户 【CANA金融】<br>"
					+ "激活链接：&nbsp;<a href=" + generateActivacationURL(user.getId(), user.getSecurityCode()) + " "
					+ "target='_blank'>" + generateActivacationURL(user.getId(), user.getSecurityCode()) + "</a>";
		} else {
			mail.setReceiver(user.getContactMail());
			content += "尊敬的"+ user.getCompanyName() + "用户:<br>&nbsp;&nbsp;&nbsp;&nbsp;您在CANA平台的注册申请未通过审核，"
					+ "请核实资料后重新申请 【CANA金融】";
		}
		mail.setContent(content);
		return mail;
	}

	@Override
	public UserActivationDTO goToActivation(String userId, String securityCode) throws Exception {
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw WebException.instance("该用户不存在，激活失败。");
		}
		if (!UserStatus.PENDINGACTIVATE.name().equals(user.getUserStatus())) {
			throw WebException.instance("该用户可能已经激活，请直接登录。");
		}
		if (StringUtils.isBlank(user.getSecurityCode())) {
			throw WebException.instance("激活链接已失效，请联系管理员重发激活邮件，激活失败。");
		}
		if (!securityCode.equals(user.getSecurityCode())) {
			throw WebException.instance("激活码错误，激活失败。");
		}
		if (new Date().compareTo(user.getSecurityCodeExpirationTime()) > 0) {
			throw WebException.instance("激活码已过期，联系管理员重发激活邮件，激活失败。");
		}
		return convertUserToUserDTO(user);
	}

	@Override
	public UserActivationDTO activate(UserActivationDTO userActivationDTO) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userActivationDTO.getId());
		if (!StringUtils.equals(UserStatus.PENDINGACTIVATE.name(), user.getUserStatus())) {
			throw WebException.instance("该用户非未激活状态，激活失败。");
		}
		if (!StringUtils.equals(user.getSecurityCode(), userActivationDTO.getSecurityCode())) {
			throw WebException.instance("激活码不正确，激活失败。");
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			if(StringUtils.isBlank(userActivationDTO.getUsername())){
				throw WebException.instance("用户名不能为空");
			}
			user.setUsername(userActivationDTO.getUsername());
		}
		user.setPassword(userActivationDTO.getPassword());
		user.setUserStatus(UserStatus.ACTIVATED.name());
		user.setSecurityCode("");
		userMapper.updateByPrimaryKeySelective(user);
		UserActivationDTO userDTO = new UserActivationDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setRoleId(user.getRoleId());
		return userDTO;
	}

	@Override
	public UserSessionDTO getUserSession(String username) throws Exception {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username).andUserStatusEqualTo(UserStatus.ACTIVATED.name());
		List<User> users = userMapper.selectByExampleWithBLOBs(example);
		return convertUserToUserSessionDTO(users);
	}

	/**
	 * 转换User成UserDTO
	 * 
	 * @param user
	 * @return
	 */
	private UserActivationDTO convertUserToUserDTO(User user) {
		if (user != null) {
			UserActivationDTO userDTO = new UserActivationDTO();
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
		}
		return null;
	}

	/**
	 * 转换List<user>成UserSessionDTO
	 * 
	 * @param users
	 * @return
	 */
	private UserSessionDTO convertUserToUserSessionDTO(List<User> users) {
		if (!CollectionUtils.isEmpty(users)) {
			User user = users.get(0);
			UserSessionDTO userSessionDTO = new UserSessionDTO();
			BeanUtils.copyProperties(user, userSessionDTO);
			userSessionDTO.setUserStatus(UserStatus.valueOf(user.getUserStatus()));
			if(StringUtils.isBlank(user.getMasterId())){
				userSessionDTO.setUserType(UserType.valueOf(user.getUserType()));
			}else {
				User masterUser = userMapper.selectByPrimaryKey(user.getMasterId());
				userSessionDTO.setUserType(UserType.valueOf(masterUser.getUserType()));
			}
			List<String> roleIdList = Lists.newArrayList();
			String[] roles = user.getRoleId().split(Constants.ROLE_SPILT_SIMBOL);
			for(String roleId:roles){
				roleIdList.add(roleId);
			}
			userSessionDTO.setRoleIdList(roleIdList);
			String guideStatus = user.getGuideStatus();
			if (StringUtils.isNotBlank(guideStatus)) {
				for(String status : guideStatus.split(",")){
					if(StringUtils.isNotBlank(status))
						userSessionDTO.addGuideStatus(UserGuideStatus.valueOf(user.getGuideStatus()));
				}
			}
			return userSessionDTO;
		}
		return null;
	}

	/**
	 * 生成审核id
	 * 
	 * @return
	 * @throws Exception
	 */
	private String generateAuditId() throws Exception {
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_AUDIT_ID, 3);
	}

	@Override
	public String generateUserId() throws Exception {
		return DateTimeUtil.date8() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_USER_ID, 4);
	}

	@Override
	public boolean upateUserLoginInfo(String id, String token, boolean signIn, String signIp, Date signTime)
			throws Exception {
		User user = tablelockMapper.lockMemberUserById(id);
		if (null == user) {

			return false;
		}
		user.setToken(token);
		user.setSignedin(signIn);
		if (StringUtils.isNotBlank(signIp)) {
			user.setSigninIp(signIp);
		}
		if (null != signTime) {
			user.setSigninTime(signTime);
		}
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public ListResult<CustomerSearchResultDTO> queryCustomerList(CustomerSearchCriteriaDTO customerSearchCriteriaDTO) throws Exception {
		long begainTime = System.currentTimeMillis();
		UserExample example = convertQueryCustomerCriteria(customerSearchCriteriaDTO);
		ListResult<CustomerSearchResultDTO> result = convertQueryCustomerResult(userMapper.selectByExample(example), userMapper.countByExample(example));
		Cat.logMetricForDuration("Query_Customer_List_Time", System.currentTimeMillis()-begainTime);
		return result;
	}

	@Override
	public CustomerDetailDTO queryCustomerDetail(String customerId){
		User user = userMapper.selectByPrimaryKey(customerId);
		return convertUserToCustomerDetailDTO(user);
	}
	
	@Override
	public CustomerDetailDTO queryCustomerByCompanyName(String companyName, UserType userType) {
		
		long begainTime = System.currentTimeMillis();
		UserExample example = new UserExample();
		example.createCriteria().andCompanyNameEqualTo(companyName)
				.andUserStatusIn(Lists.newArrayList(UserStatus.ACTIVATED.name(), UserStatus.PENDINGACTIVATE.name()))
				.andUserTypeEqualTo(userType.name());
		List<User> users = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users)) {
			Cat.logMetricForCount("Query_Customer_By_Company_Name_Fail");
			return null;
		}
		Cat.logMetricForDuration("Query_Customer_By_Company_Name_Time", System.currentTimeMillis()-begainTime);
		Cat.logMetricForCount("Query_Customer_By_Company_Name_Success");
		return convertUserToCustomerDetailDTO(users.get(0));
	}

	/**
	 * 转换User成CustomerDetailDTO
	 * 
	 * @param user
	 * @return
	 */
	private CustomerDetailDTO convertUserToCustomerDetailDTO(User user) {
		CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
		BeanUtils.copyProperties(user, customerDetailDTO);
		customerDetailDTO.setUserType(UserType.valueOf(user.getUserType()));
		customerDetailDTO.setUserStatus(UserStatus.valueOf(user.getUserStatus()));
		if (StringUtils.isNotBlank(user.getGuideStatus())) {
			String guideStatus = user.getGuideStatus();
			for(String status : guideStatus.split(",")){
				if(StringUtils.isNotBlank(status))
					customerDetailDTO.addGuideStatus(UserGuideStatus.valueOf(status));
			}
		}
		if (!StringUtils.isBlank(user.getRoleId())) {
			String[] roleList = user.getRoleId().split(Constants.ROLE_SPILT_SIMBOL);
			List<RoleDTO> roleDTOList = Lists.newArrayList();
			for(String roleId:roleList){
				Role role = roleMapper.selectByPrimaryKey(roleId);
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(role.getId());
				roleDTO.setRoleName(role.getRoleName());
				roleDTOList.add(roleDTO);
			}
			customerDetailDTO.setRoleDTOList(roleDTOList);
		}
		if (StringUtils.isBlank(customerDetailDTO.getAgentCompany())) {
			customerDetailDTO.setAgentCompany(user.getCompanyName());
		}
		if (!UserStatus.PENDINGAUDIT.name().equals(user.getUserStatus())) {
			AuditExample example = new AuditExample();
			example.createCriteria().andCustomerIdEqualTo(user.getId());
			List<Audit> audits = auditMapper.selectByExample(example);
			if (CollectionUtils.isNotEmpty(audits) && null != audits.get(0).getAuditStatus()) {
				int auditResult = audits.get(0).getAuditStatus();
				customerDetailDTO.setIntUserAuditStatus(auditResult);
				customerDetailDTO.setAuditResult(customerDetailDTO.isUserAuditPassed());
				customerDetailDTO.setAuditMessage(audits.get(0).getAuditMessage());
			}else{
				LGR.info("未找到该用户的审核结果");
			}
			
			return customerDetailDTO;
		}
		return customerDetailDTO;
	}

	/**
	 * 转换企业客户搜索条件
	 * 
	 * @param customerSearchCriteriaDTO
	 * @return
	 * @throws Exception
	 */
	private UserExample convertQueryCustomerCriteria(CustomerSearchCriteriaDTO customerSearchCriteriaDTO)
			throws Exception {
		UserExample example = new UserExample();
		example.setLimitStart((customerSearchCriteriaDTO.getPage() - 1) * customerSearchCriteriaDTO.getPageSize());
		example.setLimitEnd(customerSearchCriteriaDTO.getPageSize());
		example.setOrderByClause("-id");
		Criteria criteria = example.createCriteria();
//		criteria.andUserTypeNotIn(Arrays.asList(UserType.CANA.name(), UserType.INDIVIDUAL.name()));
		if(CollectionUtils.isNotEmpty(customerSearchCriteriaDTO.getUserTypeWithInList())){
			criteria.andUserTypeIn(customerSearchCriteriaDTO.getUserTypeWithInList());
		}
		if(CollectionUtils.isNotEmpty(customerSearchCriteriaDTO.getUserTypeWithOutList())){
			criteria.andUserTypeNotIn(customerSearchCriteriaDTO.getUserTypeWithOutList());
		}
		if(CollectionUtils.isNotEmpty(customerSearchCriteriaDTO.getUserStatusWithInList())){
			criteria.andUserStatusIn(customerSearchCriteriaDTO.getUserStatusWithInList());
		}
		if(CollectionUtils.isNotEmpty(customerSearchCriteriaDTO.getUserStatusWithOutList())){
			criteria.andUserStatusNotIn(customerSearchCriteriaDTO.getUserStatusWithOutList());
		}
		if (null != customerSearchCriteriaDTO.getUserType())
			criteria.andUserTypeEqualTo(customerSearchCriteriaDTO.getUserType().name());
		if (!StringUtils.isBlank(customerSearchCriteriaDTO.getCompanyName()))
			criteria.andCompanyNameLike("%" + customerSearchCriteriaDTO.getCompanyName() + "%");
		if (!StringUtils.isBlank(customerSearchCriteriaDTO.getUsername()))
			criteria.andUsernameLike("%" + customerSearchCriteriaDTO.getUsername() + "%");
		if (!StringUtils.isBlank(customerSearchCriteriaDTO.getBeginTime()))
			criteria.andCreateTimeGreaterThanOrEqualTo(
					new SimpleDateFormat("yyyy-MM-dd").parse(customerSearchCriteriaDTO.getBeginTime()));
		if (!StringUtils.isBlank(customerSearchCriteriaDTO.getEndTime()))
			criteria.andCreateTimeLessThan(new DateTime((customerSearchCriteriaDTO.getEndTime())).plusDays(1).toDate());
//		criteria.andUserStatusIn(Arrays.asList(UserStatus.PENDINGAUDIT.name(), UserStatus.ACTIVATED.name(),
//				UserStatus.PENDINGACTIVATE.name(), UserStatus.REJECTED.name()));
		if (null != customerSearchCriteriaDTO.getAccountActivateStatus()) {
			switch (customerSearchCriteriaDTO.getAccountActivateStatus()) {
			case ACTIVATED:
				criteria.andUserStatusIn(Arrays.asList(UserStatus.ACTIVATED.name()));
				break;

			case UNACTIVATE:
				criteria.andUserStatusIn(Arrays.asList(UserStatus.PENDINGACTIVATE.name()));
				break;
			}
		}
		if (null != customerSearchCriteriaDTO.getAccountAuditResult()) {
			switch (customerSearchCriteriaDTO.getAccountAuditResult()) {
			case REJECTED:
				criteria.andUserStatusIn(Arrays.asList(UserStatus.REJECTED.name()));
				break;

			case PASSAUDIT:
				criteria.andUserStatusIn(Arrays.asList(UserStatus.ACTIVATED.name(), UserStatus.PENDINGACTIVATE.name()));
				break;
			}
		}
		if (null != customerSearchCriteriaDTO.getAccountAuditStatus()) {
			switch (customerSearchCriteriaDTO.getAccountAuditStatus()) {
			case PENDINGAUDIT:
				criteria.andUserStatusIn(Arrays.asList(UserStatus.PENDINGAUDIT.name()));
				break;

			case HAVINGAUDIT:
				criteria.andUserStatusIn(Arrays.asList(UserStatus.ACTIVATED.name(), UserStatus.PENDINGACTIVATE.name(),
						UserStatus.REJECTED.name()));
				break;
			}
		}
		if(CollectionUtils.isNotEmpty(customerSearchCriteriaDTO.getCustomerIds()))
			criteria.andIdIn(customerSearchCriteriaDTO.getCustomerIds());
		return example;
	}

	/**
	 * 转换企业客户搜索结果
	 * 
	 * @param users
	 * @return
	 */
	private ListResult<CustomerSearchResultDTO> convertQueryCustomerResult(List<User> users, int totalNum) {
		if (CollectionUtils.isEmpty(users)) {
			return ListResult.success("查询企业客户成功");
		}
		List<CustomerSearchResultDTO> customerSearchResultDTOs = new ArrayList<CustomerSearchResultDTO>(users.size());
		for (int i = 0; i < users.size(); i++) {
			CustomerSearchResultDTO customerSearchResultDTO = new CustomerSearchResultDTO();
			BeanUtils.copyProperties(users.get(i), customerSearchResultDTO);
			customerSearchResultDTO.setUserType(UserType.valueOf(users.get(i).getUserType()));
			if (UserStatus.ACTIVATED.name().equals(users.get(i).getUserStatus())) {
				customerSearchResultDTO.setAccountActivateStatus(AccountActivateStatus.ACTIVATED);
				customerSearchResultDTO.setAccountAuditResult(AccountAuditResult.PASSAUDIT);
				customerSearchResultDTO.setAccountAuditStatus(AccountAuditStatus.HAVINGAUDIT);
			} else if (UserStatus.PENDINGACTIVATE.name().equals(users.get(i).getUserStatus())) {
				customerSearchResultDTO.setAccountActivateStatus(AccountActivateStatus.UNACTIVATE);
				customerSearchResultDTO.setAccountAuditResult(AccountAuditResult.PASSAUDIT);
				customerSearchResultDTO.setAccountAuditStatus(AccountAuditStatus.HAVINGAUDIT);
			} else if (UserStatus.REJECTED.name().equals(users.get(i).getUserStatus())) {
				customerSearchResultDTO.setAccountAuditResult(AccountAuditResult.REJECTED);
				customerSearchResultDTO.setAccountAuditStatus(AccountAuditStatus.HAVINGAUDIT);
			} else if (UserStatus.PENDINGAUDIT.name().equals(users.get(i).getUserStatus())) {
				customerSearchResultDTO.setAccountAuditStatus(AccountAuditStatus.PENDINGAUDIT);
			}
			AuditExample example = new AuditExample();
			example.createCriteria().andCustomerIdEqualTo(users.get(i).getId());
			List<Audit> audits = auditMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(audits)) {
				customerSearchResultDTO
						.setAuditor(userMapper.selectByPrimaryKey(audits.get(0).getAuditorId()).getUsername());
			}
			customerSearchResultDTOs.add(customerSearchResultDTO);
		}
		return ListResult.success("查询企业客户成功", customerSearchResultDTOs, totalNum);
	}

	@Override
	public ListResult<EmployeeSearchResultDTO> queryEmployeeList(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO, String currentOperatorId)
			throws Exception {
		long begainTime = System.currentTimeMillis();
		UserExample userExample = convertQueryEmployeeCriteria(employeeSearchCriteriaDTO, currentOperatorId);
		ListResult<EmployeeSearchResultDTO> result = convertQueryEmployeeResult(userMapper.selectByExample(userExample), userMapper.countByExample(userExample));
		Cat.logMetricForDuration("Query_Employee_List_Time", System.currentTimeMillis()-begainTime);
		return result;
	}

	/**
	 * 转换员工搜索条件
	 * 
	 * @param employeeSearchCriteriaDTO
	 * @param currentOperatorId
	 * @return
	 */
	private UserExample convertQueryEmployeeCriteria(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO,
			String currentOperatorId) {
		UserExample example = new UserExample();
		example.setLimitStart((employeeSearchCriteriaDTO.getPage() - 1) * employeeSearchCriteriaDTO.getPageSize());
		example.setLimitEnd(employeeSearchCriteriaDTO.getPageSize());
		example.setOrderByClause("create_time desc");
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isBlank(employeeSearchCriteriaDTO.getUsername())) {
			criteria.andUsernameLike("%" + employeeSearchCriteriaDTO.getUsername() + "%");
		}
		if (!StringUtils.isBlank(employeeSearchCriteriaDTO.getRealName())) {
			criteria.andRealNameLike("%" + employeeSearchCriteriaDTO.getRealName() + "%");
		}
		if (null != employeeSearchCriteriaDTO.getAccountActivateStatus()) {
			switch (employeeSearchCriteriaDTO.getAccountActivateStatus()) {
			case ACTIVATED:
				criteria.andUserStatusEqualTo(UserStatus.ACTIVATED.name());
				break;
			default:
				criteria.andUserStatusEqualTo(UserStatus.PENDINGACTIVATE.name());
				break;
			}
		} else {
			criteria.andUserStatusNotEqualTo(UserStatus.DELETED.name());
		}
		if (StringUtils.isNoneBlank(employeeSearchCriteriaDTO.getRoleId())) {
			criteria.andRoleIdEqualTo(employeeSearchCriteriaDTO.getRoleId());
		}
		if (null != getMasterId(currentOperatorId)) {
			criteria.andMasterIdEqualTo(getMasterId(currentOperatorId));
		}
		return example;
	}

	/**
	 * 转换员工搜索结果
	 * 
	 * @param users
	 * @return
	 */
	private ListResult<EmployeeSearchResultDTO> convertQueryEmployeeResult(List<User> users, int totalNum) {
		if (CollectionUtils.isEmpty(users)) {
			return ListResult.success("查询员工成功");
		}
		List<EmployeeSearchResultDTO> employeeSearchResultDTOs = new ArrayList<EmployeeSearchResultDTO>(users.size());
		for (int i = 0; i < users.size(); i++) {
			EmployeeSearchResultDTO employeeSearchResultDTO = new EmployeeSearchResultDTO();
			BeanUtils.copyProperties(users.get(i), employeeSearchResultDTO);
			if (UserStatus.ACTIVATED.name().equals(users.get(i).getUserStatus())) {
				employeeSearchResultDTO.setAccountActivateStatus(AccountActivateStatus.ACTIVATED);
			} else {
				employeeSearchResultDTO.setAccountActivateStatus(AccountActivateStatus.UNACTIVATE);
			}
			if (StringUtils.isNotBlank(users.get(i).getRoleId())) {
				String[] roleIdList = users.get(i).getRoleId().split(Constants.ROLE_SPILT_SIMBOL);
				List <RoleDTO> roleDTOList = Lists.newArrayList();
				for(String roleId:roleIdList){
					Role role = roleMapper.selectByPrimaryKey(roleId);
					RoleDTO roleDTO = new RoleDTO();
					roleDTO.setRoleId(role.getId());
					roleDTO.setRoleName(role.getRoleName());
					roleDTOList.add(roleDTO);
				}
				employeeSearchResultDTO.setRoleDTOList(roleDTOList);
			}
			employeeSearchResultDTOs.add(employeeSearchResultDTO);
		}
		return ListResult.success("查询员工成功", employeeSearchResultDTOs, totalNum);
	}

	/**
	 * 获取当前用户的masterId
	 * 
	 * @param currentOperatorId
	 * @return
	 */
	private String getMasterId(String currentOperatorId) {
		String masterId = null;
		if (null != currentOperatorId) {
			User currentOperator = userMapper.selectByPrimaryKey(currentOperatorId);
			if (null == currentOperator.getMasterId()) {
				masterId = currentOperatorId;
			} else {
				masterId = currentOperator.getMasterId();
			}
		}
		return masterId;
	}

	@Override
	public EmployeeSearchResultDTO queryEmployeeDetail(String employeeId) throws Exception {
		User user = userMapper.selectByPrimaryKey(employeeId);
		if (null == user) {
			return null;
		}
		return convertUserToEmployeeSearchResultDTO(user);
	}

	/**
	 * 转换User成EmployeeSearchResultDTO
	 * 
	 * @param user
	 * @return
	 */
	private EmployeeSearchResultDTO convertUserToEmployeeSearchResultDTO(User user) {
		EmployeeSearchResultDTO employeeSearchResultDTO = new EmployeeSearchResultDTO();
		BeanUtils.copyProperties(user, employeeSearchResultDTO);
		if (UserStatus.ACTIVATED.name().equals(user.getUserStatus())) {
			employeeSearchResultDTO.setAccountActivateStatus(AccountActivateStatus.ACTIVATED);
		} else {
			employeeSearchResultDTO.setAccountActivateStatus(AccountActivateStatus.UNACTIVATE);
		}
		if (!StringUtils.isBlank(user.getRoleId())) {
			String[] roleIdList = user.getRoleId().split(Constants.ROLE_SPILT_SIMBOL);
			List<RoleDTO> roleDTOList = Lists.newArrayList();
			for(String roleId:roleIdList){
				Role role = roleMapper.selectByPrimaryKey(roleId);
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(role.getId());
				roleDTO.setRoleName(role.getRoleName());
				roleDTOList.add(roleDTO);
			}
			employeeSearchResultDTO.setRoleDTOList(roleDTOList);
		}
		return employeeSearchResultDTO;
	}

	@Override
	public boolean modifyEmployee(EmployeeRegisterDTO employeeRegisterDTO) throws Exception {
		User user = tablelockMapper.lockMemberUserById(employeeRegisterDTO.getId());
		BeanUtils.copyProperties(employeeRegisterDTO, user);
		int index = 0;
		StringBuilder sb = new StringBuilder();
		for(String roleId:employeeRegisterDTO.getRoleIdList()){
			if(index == 0){
				sb.append(roleId);
			}else{
				sb.append(Constants.ROLE_SPILT_SIMBOL + roleId);
			}
			index++;
		}
		user.setRoleId(sb.toString());
		int count = userMapper.updateByPrimaryKeySelective(user);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 生成安全码
	 * 
	 * @return activeCode
	 */
	private String generateSecurityCode() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	/**
	 * 生成激活URL
	 * 
	 * @param userId
	 * @param activeCode
	 * @return
	 */
	private String generateActivacationURL(String userId, String securityCode) {
		if (StringUtils.isBlank(userId)) {
			throw WebException.instance("用户Id为空，生成URL失败");
		}
		return WebEnv.getVBAMPlatformPath() + "facade/userActivation/goToActive" + "?userId=" + userId
				+ "&securityCode=" + securityCode;
	}

	@Override
	public boolean deleteEmployee(String employeeId) throws Exception {
		User user = tablelockMapper.lockMemberUserById(employeeId);
		user.setUserStatus(UserStatus.DELETED.name());
		int count = userMapper.updateByPrimaryKey(user);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean resetPassword(String userId) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userId);
		String newPwd = generateSecurityCode();
		String emailAdress = StringUtils.isBlank(user.getMasterId()) ? user.getContactMail() : user.getEmployeeMail();
		MailMessageDTO mail = new MailMessageDTO();
		mail.setSubject("重置密码提醒");
		mail.setReceiver(emailAdress);
		mail.setContent("您的密码已经重置为:" + newPwd + ",请使用新密码登录。【CANA金融】");
		mail.setContentType(MailContentType.HTML);
		mailRabbitTemplate.convertAndSend(mail);
		user.setPassword(PasswordEncoderUtil.encrypt(newPwd));
		int count = userMapper.updateByPrimaryKeySelective(user);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRoleOfUser(UserUpdateDTO userUpdateDTO) throws Exception {
		String id = userUpdateDTO.getId();
		String roleId = userUpdateDTO.getRoleId();
		StringUtil.trimObjectFields(userUpdateDTO);
		if (StringUtils.isAnyBlank(id, roleId))
			throw WebException.instance("用户Id或roleId不能为空！");
		User user = tablelockMapper.lockMemberUserById(id);
		if (user == null)
			throw WebException.instance("更新的用户不存在");
		if(roleId.equals(user.getRoleId()))
			return true;
		BeanUtils.copyProperties(userUpdateDTO, user);
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public boolean updateRoleListOfUser(UserRoleListUpdateDTO userUpdateDTO) throws Exception {
		String id = userUpdateDTO.getId();
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for(String roleId:userUpdateDTO.getRoleIdList()){
			if(index == 0){
				sb.append(roleId);
			}else{
				sb.append(Constants.ROLE_SPILT_SIMBOL + roleId);
			}
			index ++;
		}
		StringUtil.trimObjectFields(userUpdateDTO);
		if (StringUtils.isAnyBlank(id, sb.toString()))
			throw WebException.instance("用户Id或roleId不能为空！");
		User user = tablelockMapper.lockMemberUserById(id);
		if (user == null)
			throw WebException.instance("更新的用户不存在");
		if(StringUtils.equals(sb.toString(), user.getRoleId()))
			return true;
		BeanUtils.copyProperties(userUpdateDTO, user);
		user.setRoleId(sb.toString());
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public String getFinanceIdByName(String financeCompany, UserType userType) throws Exception {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andCompanyNameEqualTo(financeCompany)
			.andUserTypeEqualTo(userType.name());
		List<User> users = userMapper.selectByExample(userExample);
		if(CollectionUtils.isEmpty(users))
			return "";
		for(User user : users)
		{
			if(UserStatus.ACTIVATED == UserStatus.valueOf(user.getUserStatus()))
				return user.getId();
		}
		return "";
	}

	@Override
	public boolean modifyLoginPassword(String userId, String oldPwd, String newPwd) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userId);
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdEqualTo(userId);
		criteria.andPasswordEqualTo(oldPwd);
		int oldCount = userMapper.countByExample(userExample);
		if (oldCount == 0)
			throw WebException.instance("原密码输入有误");
		user.setPassword(newPwd);
		int newCount = userMapper.updateByPrimaryKey(user);
		if (newCount > 0)
			return true;
		return false;
	}

	@Override
	public CompanyInfoDTO getCompanyInfo(UserSessionDTO sessionDTO) throws Exception {
		String userId = sessionDTO.getId();
		String masterId = StringUtils.isBlank(sessionDTO.getMasterId()) ? userId : sessionDTO.getMasterId();
		CustomerDetailDTO customerDetail = queryCustomerDetail(masterId);
		CompanyInfoDTO companyInfoDTO = convertPersonalCenterDTO(customerDetail);
		return companyInfoDTO;
	}
	

	/**
	 * 将企业、联系人和融资信息整合
	 * 
	 * @param customerDetail
	 * @return
	 */
	private CompanyInfoDTO convertPersonalCenterDTO(CustomerDetailDTO customerDetail) {
		CompanyInfoDTO companyInfoDTO = new CompanyInfoDTO();
		companyInfoDTO.setId(customerDetail.getId());
		companyInfoDTO.setOrganizationCode(customerDetail.getOrganizationCode());
		companyInfoDTO.setOrganizationCodeCertificateMediaId(customerDetail.getOrganizationCodeCertificateMediaId());
		companyInfoDTO.setBusinessLicenceCode(customerDetail.getBusinessLicenceCode());
		companyInfoDTO.setBusinessLicenceMediaId(customerDetail.getBusinessLicenceMediaId());
		companyInfoDTO.setTaxRegistrationCertificateCode(customerDetail.getTaxRegistrationCertificateCode());
		companyInfoDTO.setTaxRegistrationCertificateMediaId(customerDetail.getTaxRegistrationCertificateMediaId());
		companyInfoDTO.setUserType(customerDetail.getUserType());
//		companyInfoDTO.setRoleId(roleIdListConvert(customerDetail.getRoleId()));
		companyInfoDTO.setRoleDTOlist(customerDetail.getRoleDTOList());
		companyInfoDTO.setContactName(customerDetail.getContactName());
		companyInfoDTO.setContactMail(customerDetail.getContactMail());
		companyInfoDTO.setContactTel(customerDetail.getContactTel());
		companyInfoDTO.setJobTitle(customerDetail.getJobTitle());
		companyInfoDTO.setContactIdentityCardFrontMediaId(customerDetail.getContactIdentityCardFrontMediaId());
		companyInfoDTO.setContactIdentityCardBackMediaId(customerDetail.getContactIdentityCardBackMediaId());
		return companyInfoDTO;
	}

//	private List<String> convertRoleStrToList(String roleIdStr){
//		List<String> roleIdList = Lists.newArrayList();
//		String[] roleIdTemp = roleIdStr.split(Constants.ROLE_SPILT_SIMBOL);
//		for(String roleId:roleIdTemp){
//			roleIdList.add(roleId);
//		}
//		return roleIdList;
//	}

	private String convertRoleListToStr(List<RoleDTO> roleDTOList){
		StringBuilder sb = new StringBuilder();
		int index = 0 ;
		for(RoleDTO roleDTO: roleDTOList){
			if(index == 0){
				sb.append(roleDTO.getRoleId());
			}else{
				sb.append(Constants.ROLE_SPILT_SIMBOL + roleDTO.getRoleId());
			}
		}
		return sb.toString();
	}
	
	@Override
	public boolean isSetPayPassword(String masterId) throws Exception {
		User user = userMapper.selectByPrimaryKey(masterId);
		if (user == null) {
			throw WebException.instance("该企业用户不存在");
		}
		if (StringUtils.isNoneBlank(user.getPayPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isloginPwd(String userId, String loginPwd, int flag) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userId);
		if (flag == 1 || flag == 2) {
			criteria.andPasswordEqualTo(loginPwd);
		} else {
			criteria.andPayPasswordEqualTo(loginPwd);
		}
		int count = userMapper.countByExample(example);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean setPayPwd(String userId, String oldPwd, String newPwd) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userId);
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdEqualTo(userId);
		criteria.andPasswordEqualTo(oldPwd);
		int oldCount = userMapper.countByExample(userExample);
		if (oldCount == 0)
			throw WebException.instance("原密码输入有误");
		user.setPayPassword(newPwd);
		int newCount = userMapper.updateByPrimaryKey(user);
		if (newCount > 0)
			return true;
		return false;
	}

	@Override
	public boolean modifyPayPwd(String userId, String oldPwd, String newPwd) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userId);
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdEqualTo(userId);
		criteria.andPayPasswordEqualTo(oldPwd);
		int oldCount = userMapper.countByExample(userExample);
		if (oldCount == 0)
			throw WebException.instance("原密码输入有误");
		user.setPayPassword(newPwd);
		int newCount = userMapper.updateByPrimaryKey(user);
		if (newCount > 0)
			return true;
		return false;
	}

	@Override
	public boolean modifyContactsInfo(String userId, String contactName,String jobTitle, String mobileNum, String mail) throws Exception {
		User user=tablelockMapper.lockMemberUserById(userId);
		if(StringUtils.isNotBlank(contactName))
			user.setContactName(contactName);
		if(StringUtils.isNotBlank(jobTitle))
			user.setJobTitle(jobTitle);
		if(StringUtils.isNotBlank(mobileNum))
			user.setContactTel(mobileNum);
		if(StringUtils.isNotBlank(mail))
			user.setContactMail(mail);
		int count=userMapper.updateByPrimaryKey(user);
		if(count>0)
			return true;
		return false;
	}

	@Override
	public boolean modifyMediaId(String userId,String orgMediaId,String busMediaId,String taxMediaId) throws Exception {
		User user=tablelockMapper.lockMemberUserById(userId);
		if(StringUtils.isNotBlank(orgMediaId))
			user.setOrganizationCodeCertificateMediaId(orgMediaId);
		if(StringUtils.isNotBlank(busMediaId))
			user.setBusinessLicenceMediaId(busMediaId);
		if(StringUtils.isNotBlank(taxMediaId))
			user.setTaxRegistrationCertificateMediaId(taxMediaId);
		int count=userMapper.updateByPrimaryKey(user);
		if(count>0)
			return true;
		return false;
	}

	@Override
	public boolean validatePayPwd(String masterId, String payPwd) throws Exception {
		UserExample userExample=new UserExample();
		Criteria criteria=userExample.createCriteria();
		criteria.andIdEqualTo(masterId);
		criteria.andPayPasswordEqualTo(payPwd);
		int count=userMapper.countByExample(userExample);
		if(count>0)
			return true;
		return false;
	}

    @Override
    public boolean updateUserGuideStatus(String masterId, UserGuideStatus oldGuideStatus,
            UserGuideStatus guideStatus) throws Exception {
        if (StringUtils.isBlank(masterId) || guideStatus == null)
            throw WebException.instance("参数不正确");
        User customer = tablelockMapper.lockMemberUserById(masterId);
        if (customer == null)
            throw WebException.instance("客户不存在");
        
        String userGuideStatus = customer.getGuideStatus();
        if (StringUtils.isBlank(userGuideStatus) && oldGuideStatus != null){
        	LGR.error("客户引导状态不正确, 状态为空，而参数原状态不为空！");
        	throw WebException.instance("客户引导状态不正确");
        }
        if (StringUtils.isNotBlank(userGuideStatus) && userGuideStatus.contains(UserGuideStatus.COMFIRMED_RULE.name())){
        	LGR.error("客户引导状态不正确，用户状态包含保理商最终状态！");
        	throw WebException.instance("客户引导状态不正确");
        }
       
        if(oldGuideStatus == null){
        	if(StringUtils.isBlank(userGuideStatus)){
        		customer.setGuideStatus(guideStatus.name());
        	}else{
        		customer.setGuideStatus(userGuideStatus + ","+guideStatus.name());
        	}
        }else{
        	Set<String> newStatus = new HashSet<String>();
         	String[] statusArry = userGuideStatus.split(",");
         	for(String status : statusArry){
         		if(StringUtils.isNotBlank(status)){
         			newStatus.add(status);
         		}
         	}
         	if(newStatus.contains(oldGuideStatus.name())){
         		newStatus.remove(oldGuideStatus.name());
         		newStatus.add(guideStatus.name());
         		
         	}else{
         		LGR.error("客户引导状态不正确，用户状态不包含参数原状态！");
             	throw WebException.instance("客户引导状态不正确");
        	}             
        	customer.setGuideStatus(set2StringWithSeg(newStatus, ","));
        }
        userMapper.updateByPrimaryKeySelective(customer);
        return true;
    }

	private String set2StringWithSeg(Set<String> set, String segment){
		StringBuilder setStr = new StringBuilder();
		Iterator<String> iterator = set.iterator();
 		while(iterator.hasNext()){
 			if(setStr.length() > 0){
 				setStr.append(segment);
 			}
 			setStr.append(iterator.next());
 		}
 		return setStr.toString();
	}
	
	@Override
	public PersonalDetailDTO queryPersonalDetailInfo(String id) throws Exception {
		User user=userMapper.selectByPrimaryKey(id);
		if(user==null)
			throw WebException.instance("该用户不存在");
		return convertUser2PersonalDetailDTO(user);
	}

	private PersonalDetailDTO convertUser2PersonalDetailDTO(User user) {
		PersonalDetailDTO ped=new PersonalDetailDTO();
		BeanUtils.copyProperties(user, ped);
		if(StringUtils.isBlank(user.getRealName()))
			ped.setRealName(user.getContactName());
		if(StringUtils.isBlank(user.getEmployeeJobTitle()))
			ped.setEmployeeJobTitle(user.getJobTitle());
		if(StringUtils.isBlank(user.getEmployeeMail()))
			ped.setEmployeeMail(user.getContactMail());
		if(StringUtils.isBlank(user.getEmployeeTel()))
			ped.setEmployeeTel(user.getContactTel());
		List<RoleDTO> roleDTOList = Lists.newArrayList();
		for(String roleId:user.getRoleId().split(Constants.ROLE_SPILT_SIMBOL)){
			Role role=roleMapper.selectByPrimaryKey(roleId);
			if(null != role){
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(role.getId());
				roleDTO.setRoleName(role.getRoleName());
				roleDTOList.add(roleDTO);
			}
		}
		ped.setRoleDTOList(roleDTOList);
		return ped;
	}

	@Override
	public boolean modifyPersonalInfo(String userId, String contactName, String jobTitle, String mobileNum, String mail)
			throws Exception {
		User user=tablelockMapper.lockMemberUserById(userId);
		if(StringUtils.isNotBlank(contactName))
			user.setRealName(contactName);
		if(StringUtils.isNotBlank(jobTitle))
			user.setEmployeeJobTitle(jobTitle);
		if(StringUtils.isNotBlank(mobileNum))
			user.setEmployeeTel(mobileNum);
		if(StringUtils.isNotBlank(mail))
			user.setEmployeeMail(mail);
		userMapper.updateByPrimaryKey(user);
		return true;
	}

	@Override
	public boolean resetPayPassword(String userId) throws Exception {
		User user = tablelockMapper.lockMemberUserById(userId);
		String newPwd = generateSecurityCode();
		MailMessageDTO mail = new MailMessageDTO();
		mail.setSubject("重置支付密码提醒");
		mail.setReceiver(user.getContactMail());
		mail.setContent("您的支付密码已经重置为:" + newPwd + ",请使用新支付密码。【CANA金融】");
		mail.setContentType(MailContentType.HTML);
		mailRabbitTemplate.convertAndSend(mail);
		user.setPayPassword(PasswordEncoderUtil.encrypt(newPwd));
		int count = userMapper.updateByPrimaryKeySelective(user);
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean createCustomerByCredit(CustomerApply4MemberDTO customerApply4MemberDTO, String taskId) {
		if(vbamCommonServiceImpl.getProperties("CreditCreateUser:" + taskId) == null) {
			String userId = customerApply4MemberDTO.getUserId();
			User oldUser = userMapper.selectByPrimaryKey(userId);
			if(oldUser == null) {
				User user = new User();
				user.setId(userId);
				user.setCompanyName(customerApply4MemberDTO.getCompanyName());
				user.setOrganizationCode(customerApply4MemberDTO.getOrganizationNo());
				user.setOrganizationCodeCertificateMediaId(customerApply4MemberDTO.getOrganizationMediaId());
				user.setBusinessLicenceCode(customerApply4MemberDTO.getBusinessLicenceNo());
				user.setBusinessLicenceMediaId(customerApply4MemberDTO.getBusinessLicenceMediaId());
				user.setContactName(customerApply4MemberDTO.getContactName());
				user.setContactTel(customerApply4MemberDTO.getPhoneNumber());
				user.setContactMail(customerApply4MemberDTO.getEmail());
				user.setUserType(UserType.FINACE.name());
				user.setUserStatus(UserStatus.PENDINGACTIVATE.name());
				if (StringUtils.isNotBlank(customerApply4MemberDTO.getFinanceRoleId())) {
					user.setRoleId(customerApply4MemberDTO.getFinanceRoleId());
				} else {
					String financeDefaultRoleId = TopsConfReader.getConfContent("properties/member-common-global.properties", "finace.default.role.id", ConfScope.R);
					user.setRoleId(financeDefaultRoleId);
				}
				user.setCreateTime(new Date());
				user.setUpateTime(user.getCreateTime());
				user.setTaxRegistrationCertificateCode(customerApply4MemberDTO.getTaxRegistrationCertificateNo());
				user.setTaxRegistrationCertificateMediaId(customerApply4MemberDTO.getTaxRegistrationCertificateMediaId());
				user.setGuideStatus(UserGuideStatus.NEED_GENERATE_CONTRACT.name());
				if (customerApply4MemberDTO.isIndividual())
					user.setIdentityCardNo(customerApply4MemberDTO.getBusinessLicenceNo());
				user.setLegalPerson(customerApply4MemberDTO.getLegalPerson());
				userMapper.insert(user);
				insertMemberAudit(customerApply4MemberDTO.getAuditorId(), user.getId());
			} else {
				oldUser.setOrganizationCode(customerApply4MemberDTO.getOrganizationNo());
				oldUser.setOrganizationCodeCertificateMediaId(customerApply4MemberDTO.getOrganizationMediaId());
				oldUser.setBusinessLicenceCode(customerApply4MemberDTO.getBusinessLicenceNo());
				oldUser.setBusinessLicenceMediaId(customerApply4MemberDTO.getBusinessLicenceMediaId());
				oldUser.setContactName(customerApply4MemberDTO.getContactName());
				oldUser.setContactTel(customerApply4MemberDTO.getPhoneNumber());
				oldUser.setContactMail(customerApply4MemberDTO.getEmail());
				if(UserStatus.PENDINGAUDIT.name().equals(oldUser.getUserStatus())) {
					insertMemberAudit(customerApply4MemberDTO.getAuditorId(), oldUser.getId());
					oldUser.setUserStatus(UserStatus.PENDINGACTIVATE.name());
				}
				if (StringUtils.isNotBlank(customerApply4MemberDTO.getFinanceRoleId())) {
					oldUser.setRoleId(customerApply4MemberDTO.getFinanceRoleId());
				}
				oldUser.setUpateTime(new Date());
				oldUser.setTaxRegistrationCertificateCode(customerApply4MemberDTO.getTaxRegistrationCertificateNo());
				oldUser.setTaxRegistrationCertificateMediaId(customerApply4MemberDTO.getTaxRegistrationCertificateMediaId());
				oldUser.setGuideStatus(UserGuideStatus.NEED_GENERATE_CONTRACT.name());
				oldUser.setLegalPerson(customerApply4MemberDTO.getLegalPerson());
				userMapper.updateByPrimaryKeySelective(oldUser);
			}
			vbamCommonServiceImpl.addProperties("CreditCreateUser:" + taskId, "true");
		}
		return true;
	}
	
	private void insertMemberAudit(String auditorId, String customerId) {
		Audit audit = new Audit();
		try {
			audit.setId(generateAuditId());
		} catch (Exception e) {
			throw WebException.instance("无法生成用户审核ID");
		}
		audit.setAuditorId(auditorId);
		audit.setCreateTime(new Date());
		audit.setUpdateTime(audit.getCreateTime());
		audit.setAuditStatus(63);
		audit.setCustomerId(customerId);
		auditMapper.insert(audit);
	}
	
	@Override
	public List<User> getAllFactorAndFinanceUsers() {
	    return queryUserForReportMapper.getAllFactorAndFinanceUsers();
	}

	@Override
	public void updateCustomerCertDN(String userId, String certSubjectDN) {
		User user = tablelockMapper.lockMemberUserById(userId);
		user.setCertSubjectDn(certSubjectDN);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public String queryOrGenerateUserId(String companyName, String individualIdentityCardNo) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserTypeEqualTo(UserType.FINACE.name())
			.andCompanyNameEqualTo(companyName).andUserStatusNotIn(Arrays.asList(UserStatus.REJECTED.name(), UserStatus.DELETED.name()));
		if (StringUtils.isNotBlank(individualIdentityCardNo))
			criteria.andIdentityCardNoEqualTo(individualIdentityCardNo);

		List<User> oldUsers = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(oldUsers))
			try {
				return generateUserId();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		else
			return oldUsers.get(0).getId();
	}

	@Override
	public boolean createYundaexCustomerByCredit(YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO,
			String taskId) {
		if(vbamCommonServiceImpl.getProperties("YundaexCreditCreateUser:" + taskId) == null) {
			String userId = ydCustomerApply4MemberUserDTO.getUserId();
			User oldUser = userMapper.selectByPrimaryKey(userId);
			if(oldUser == null) {
				User user = new User();
				user.setId(userId);
				user.setCompanyName(ydCustomerApply4MemberUserDTO.getStationName());
				user.setOrganizationCode(ydCustomerApply4MemberUserDTO.getOrganizationNo());
				user.setOrganizationCodeCertificateMediaId(ydCustomerApply4MemberUserDTO.getOrganizationMediaId());
				user.setBusinessLicenceCode(ydCustomerApply4MemberUserDTO.getBusinessLicenceNo());
				user.setBusinessLicenceMediaId(ydCustomerApply4MemberUserDTO.getBusinessLicenceMediaId());
				user.setContactName(ydCustomerApply4MemberUserDTO.getCustName());
				user.setContactTel(ydCustomerApply4MemberUserDTO.getCustPhone());
				user.setContactMail(ydCustomerApply4MemberUserDTO.getCustEmail());
				user.setUserType(UserType.FINACE.name());
				user.setUserStatus(UserStatus.PENDINGACTIVATE.name());
				String financeDefaultRoleId = TopsConfReader.getConfContent("properties/member-common-global.properties", "finace.default.role.id", ConfScope.R);
				user.setRoleId(financeDefaultRoleId);
				user.setCreateTime(new Date());
				user.setUpateTime(user.getCreateTime());
				user.setTaxRegistrationCertificateCode(ydCustomerApply4MemberUserDTO.getTaxRegistrationCertificateNo());
				user.setTaxRegistrationCertificateMediaId(ydCustomerApply4MemberUserDTO.getTaxRegistrationCertificateMediaId());
				user.setLegalPersonIdentityCardFrontMediaId(ydCustomerApply4MemberUserDTO.getLegalIdnoFrontMediaId());
				user.setLegalPersonIdentityCardBackMediaId(ydCustomerApply4MemberUserDTO.getLegalIdnoFrontMediaId());
				user.setGuideStatus(UserGuideStatus.NEED_GENERATE_CONTRACT_YUNDAEX.name());
				userMapper.insert(user);
				insertMemberAudit(ydCustomerApply4MemberUserDTO.getAuditorId(), user.getId());
			} else {
				oldUser.setOrganizationCode(ydCustomerApply4MemberUserDTO.getOrganizationNo());
				oldUser.setOrganizationCodeCertificateMediaId(ydCustomerApply4MemberUserDTO.getOrganizationMediaId());
				oldUser.setBusinessLicenceCode(ydCustomerApply4MemberUserDTO.getBusinessLicenceNo());
				oldUser.setBusinessLicenceMediaId(ydCustomerApply4MemberUserDTO.getBusinessLicenceMediaId());
				oldUser.setContactName(ydCustomerApply4MemberUserDTO.getCustName());
				oldUser.setContactTel(ydCustomerApply4MemberUserDTO.getCustPhone());
				oldUser.setContactMail(ydCustomerApply4MemberUserDTO.getCustEmail());
				if(oldUser.getUserStatus() == UserStatus.PENDINGAUDIT.name()) {
					insertMemberAudit(ydCustomerApply4MemberUserDTO.getAuditorId(), oldUser.getId());
					oldUser.setUserStatus(UserStatus.PENDINGACTIVATE.name());
				}
				oldUser.setUpateTime(new Date());
				oldUser.setTaxRegistrationCertificateCode(ydCustomerApply4MemberUserDTO.getTaxRegistrationCertificateNo());
				oldUser.setTaxRegistrationCertificateMediaId(ydCustomerApply4MemberUserDTO.getTaxRegistrationCertificateMediaId());
				oldUser.setLegalPersonIdentityCardFrontMediaId(ydCustomerApply4MemberUserDTO.getLegalIdnoFrontMediaId());
				oldUser.setLegalPersonIdentityCardBackMediaId(ydCustomerApply4MemberUserDTO.getLegalIdnoFrontMediaId());
				oldUser.setGuideStatus(UserGuideStatus.NEED_GENERATE_CONTRACT_YUNDAEX.name());
				userMapper.updateByPrimaryKeySelective(oldUser);
			}
			vbamCommonServiceImpl.addProperties("YundaexCreditCreateUser:" + taskId, "true");
		}
		return true;
	}

	@Override
	public CustomerDetailDTO createIndividualUser4VJ(ApplyCreditRequest applyRequest) throws Exception{
		
		CustomerDetailDTO userDetail = queryIndividualUser(applyRequest.getCustomerName(), applyRequest.getIdentityCardNo());
		
		// 客户已经存在并且关键信息没有发生改变
		if(userDetail !=null && applyRequest.getMobileNo().equals(applyRequest.getMobileNo()))
			return userDetail;
		
		// 客户已经存在，关键信息发生了改变
		if(userDetail !=null && !applyRequest.getMobileNo().equals(applyRequest.getMobileNo())){
			User user = new User();
			user.setId(userDetail.getId());
			user.setContactTel(applyRequest.getMobileNo());
			userMapper.updateByPrimaryKeySelective(user);
			userDetail.setContactTel(applyRequest.getMobileNo());
			return userDetail;
		}
		
		User user = new User();
		user.setId(generateUserId());
		user.setCompanyName(applyRequest.getCustomerName());
		user.setContactTel(applyRequest.getMobileNo());
		user.setIdentityCardNo(applyRequest.getIdentityCardNo());
		user.setUserType(UserType.INDIVIDUAL.name());
		user.setUserStatus(UserStatus.ACTIVATED.name());
		insertMemberAudit("cana-user", user.getId());
		userMapper.insertSelective(user);
		
		return convertIndividualUserToCustomerDetailDTO(user);
	}

	@Override
	public CustomerDetailDTO queryIndividualUser(String name, String identityCardNo) {

		name = StringUtils.trimToEmpty(name);
		identityCardNo = StringUtils.trimToEmpty(identityCardNo);

		UserExample example = new UserExample();
		example.createCriteria().andCompanyNameEqualTo(name)
								.andUserStatusIn(Lists.newArrayList(UserStatus.ACTIVATED.name()))
								.andUserTypeEqualTo(UserType.INDIVIDUAL.name())
								.andIdentityCardNoEqualTo(identityCardNo);

		List<User> users = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users))
			return null;
		
		return convertIndividualUserToCustomerDetailDTO(users.get(0));
	}
	
	private CustomerDetailDTO convertIndividualUserToCustomerDetailDTO(User user){
		CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
		BeanUtils.copyProperties(user, customerDetailDTO);
		customerDetailDTO.setUserType(UserType.valueOf(user.getUserType()));
		customerDetailDTO.setUserStatus(UserStatus.valueOf(user.getUserStatus()));
		return customerDetailDTO;
	}

	@Override
	public void updateContactTel(String memberId, String contactTel) {
		User user = new User();
		user.setId(memberId);
		user.setContactTel(contactTel);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateContactName(String memberId, String contactName) {
		User user = new User();
		user.setId(memberId);
		user.setContactName(contactName);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateContactMail(String memberId, String contactMail) {
		User user = new User();
		user.setId(memberId);
		user.setContactMail(contactMail);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateJobTitle(String memberId, String jobTitle) {
		User user = new User();
		user.setId(memberId);
		user.setJobTitle(jobTitle);
		userMapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public String queryUserIdByCompanyName(String companyName) {
		UserExample example = new UserExample();
		example.createCriteria()
			.andUserTypeEqualTo(UserType.FINACE.name())
			.andCompanyNameEqualTo(companyName).andUserStatusNotIn(Arrays.asList(UserStatus.REJECTED.name(), UserStatus.DELETED.name()));
		List<User> oldUsers = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(oldUsers))
			return null;
		else
			return oldUsers.get(0).getId();
	}

	@Override
	public void updateUserPermissions(String userId, String permissions) {
		User user = tablelockMapper.lockMemberUserById(userId);
		if(null == user)
			throw WebException.instance("参数有误");
//		if(StringUtils.isNotBlank(user.getMasterId())){
//			User master = userMapper.selectByPrimaryKey(user.getMasterId());
//			//修改者的权限list
//			List<String> changerPermissionList = Lists.newArrayList();
//			Set<String> changerPermissionSet = Sets.newHashSet();
//			for(String changeRoleId:master.getPermissions()){
//				 .addAll(getPermissionByRole(changeRoleId));
//			}
//			changerPermissionList.addAll(changerPermissionSet);
//			//遍历被修改的角色的权限list，比对修改者的权限list，将修改者没有的权限拿出来保存起来。
//			for(PermissionDTO permissionDTO : oldPermissionList){
//				if(!changerPermissionList.contains(permissionDTO))
//					newPermissions += ";" + permissionDTO.getId();
//			}
//		}
		user.setPermissions(permissions);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
    public CustomerDetailDTO checkCustomerIsValid(String customerId) {
        if (StringUtils.isBlank(customerId))
            throw WebException.instance("客户Id不能为空");
        User customer = userMapper.selectByPrimaryKey(customerId);
        if (customer == null)
            throw WebException.instance("客户不存在");
        if (!EnumUtils.isValidEnum(UserType.class, customer.getUserType()))
            throw WebException.instance(customer.getId()+"的客户类型为"+customer.getUserType()+"客户类型无效");
        if (!EnumUtils.isValidEnum(UserStatus.class, customer.getUserStatus()))
            throw WebException.instance("客户状态无效");
        if (UserStatus.DELETED.name().equals(customer.getUserStatus())
                || UserStatus.REJECTED.name().equals(customer.getUserStatus())
                || UserStatus.PENDINGAUDIT.name().equals(customer.getUserStatus()))
            throw WebException.instance("客户状态无效");
        return convertUserToCustomerDetailDTO(customer);
    }

}
