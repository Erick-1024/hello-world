package com.cana.member.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.vj.dto.ApplyCreditRequest;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;

public interface IUserApi {
	/**
	 * 新增企业客户
	 * @param customerRegisterDTO
	 */
	public String addCustomer(CustomerRegisterDTO customerRegisterDTO)throws Exception;
	
	/**
	 * 新增企业员工
	 * @param employeeRegisterDTO
	 * @param currentOperatorId
	 */
	public boolean addEmployee(EmployeeRegisterDTO employeeRegisterDTO, String currentOperatorId)throws Exception;
	
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return boolean
	 */
	public boolean checkUsernameExist(String username)throws Exception;
	
	/**
	 * 检查公司名称是否存在
	 * @param companyName
	 * @return boolean
	 */
	public boolean checkCompanyNameExist(String companyName, UserType userType)throws Exception;
	
	/**
	 * 返回审核页面
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public CustomerReviewDTO gotoReview(String customerId)throws Exception;
	
	/**
	 * 审核
	 * @param userId
	 */
	public void review(CustomerDetailDTO customerDetailDTO)throws Exception;
	
	/**
	 * 重发邮件
	 * @param userId
	 * @throws Exception
	 */
	public boolean resendEmail(String userId)throws Exception;

	/**
     * 生成激活url，如果已存在的激活链接还未失效，则返回已存在的激活链接
     * 
     * 如果参数不合法，或者用户不存在，则抛异常。
     * 
     * 如果用户已经激活，则返回null
     */
    public String generateActivacationURL(String userId) throws Exception;
	
	/**
	 * 激活账号
	 * @param username
	 */
	public UserActivationDTO goToActivation(String userId,String securityCode)throws Exception;
	
	/**
	 * 激活账号
	 * @param userActivationDTO
	 * @throws Exception
	 */
	public UserActivationDTO activate(UserActivationDTO userActivationDTO)throws Exception;
	
	/**
	 * 获取用户session
	 * @param username
	 * @return UserSessionDTO
	 */
	public UserSessionDTO getUserSession(String username)throws Exception;
	
	/**
	 * 生成用户id
	 * @return
	 * @throws Exception
	 */
	public String generateUserId() throws Exception;

	/**
	 * 更新用户登录信息
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserLoginInfo(String id, String token, boolean signIn, String signIp, Date signTime) throws Exception;
	
	/**
	 * 查询企业客户
	 * @param CustomerSearchCriteriaDTO
	 * @return
	 * @throws Exception
	 */
	public ListResult<CustomerSearchResultDTO> queryCustomerList(CustomerSearchCriteriaDTO customerSearchCriteriaDTO)throws Exception;
	
	/**
	 * 查询企业客户的详情
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public CustomerDetailDTO queryCustomerDetail(String customerId);
	
	/**
	 * 根据公司名称获取待激活或者已激活的客户
	 */
	public CustomerDetailDTO queryCustomerByCompanyName(String companyName, UserType userType);
	
	/**
	 * 查询员工
	 * @param employeeSearchCriteriaDTO
	 * @return
	 * @throws Exception
	 */
	public ListResult<EmployeeSearchResultDTO> queryEmployeeList(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO,String currentOperatorId)throws Exception;

	/**
	 * 查询员工详情
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public EmployeeSearchResultDTO queryEmployeeDetail(String employeeId)throws Exception;
	
	/**
	 * 修改员工
	 * @param employeeRegisterDTO
	 * @throws Exception
	 */
	public boolean modifyEmployee(EmployeeRegisterDTO employeeRegisterDTO)throws Exception;
	
	/**
	 * 删除员工
	 * @param employeeId
	 * @throws Exception
	 */
	public boolean deleteEmployee(String employeeId)throws Exception;
	
	/**
	 * 重置密码
	 * 
	 * @param 用户id
	 * @throws Exception
	 */
	public boolean resetPassword(String userId)throws Exception;
	
	/**
	 * 更新用户所分配的角色。
	 * @param userUpdateDTO
	 * @return
	 * @throws Exception
	 */
	public boolean updateRoleOfUser(UserUpdateDTO userUpdateDTO)throws Exception;

	/**
	 * 更新用户所分配的角色列表。
	 * @param userUpdateDTO
	 * @return
	 * @throws Exception
	 */
	public boolean updateRoleListOfUser(UserRoleListUpdateDTO userUpdateDTO)throws Exception;
	
	/**
	 * 根据融资客户名获取融资客户Id
	 * @param financeCompany
	 * @return
	 * @throws Exception
	 */
	public String getFinanceIdByName(String financeCompany, UserType userType)throws Exception;
	
	/**
	 * 修改个人登录密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public boolean modifyLoginPassword(String userId,String oldPwd,String newPwd)throws Exception;
	
	/**
	 * 获取企业相关数据
	 * @return
	 * @throws Exception
	 */
	public CompanyInfoDTO getCompanyInfo(UserSessionDTO sessionDTO) throws Exception;
	/**
	 * 是否设置支付密码
	 * @param masterId
	 * @return true : 已设置支付密码 false:未设置支付密码
	 * @throws Exception
	 */
	public boolean isSetPayPassword(String masterId)throws Exception;
	/**
	 * 判断登陆密码是否正确
	 * @param userId
	 * @param loginPwd
	 * @return
	 * @throws Exception
	 */
	public boolean isloginPwd(String userId,String loginPwd,int flag)throws Exception;
	/**
	 * 设置支付密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public boolean setPayPwd(String userId,String oldPwd,String newPwd)throws Exception;
	/**
	 * 修改支付密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public boolean modifyPayPwd(String userId,String oldPwd,String newPwd)throws Exception;
	/**
	 * 修改联系人信息
	 * @return
	 * @throws Exception
	 */
	public boolean modifyContactsInfo(String userId,String contactName,String jobTitle,String mobileNum,String mail)throws Exception;
	/**
	 * 修改个人基本信息
	 */
	public boolean modifyPersonalInfo(String userId,String contactName,String jobTitle,String mobileNum,String mail)throws Exception;
	/**
	 * 修改证件信心
	 * @param userId
	 * @param mdeiaId
	 * @return
	 * @throws Exception
	 */
	public boolean modifyMediaId(String userId,String orgMediaId,String busMediaId,String taxMediaId)throws Exception;
	/**
	 * 判断支付密码是否正确
	 * @param masterId 企业ID
	 * @param payPwd 已加密
	 * @return
	 * @throws Exception
	 */
	public boolean validatePayPwd(String masterId,String payPwd)throws Exception;

	/**
	 * 更新客户引导状态
	 */
	public boolean updateUserGuideStatus(String masterId, UserGuideStatus oldGuideStatus, UserGuideStatus guideStatus) throws Exception;

	/**
	 * 获取个人基本信息
	 */
	public PersonalDetailDTO queryPersonalDetailInfo(String id)throws Exception;
	
	/**
	 * 重置支付密码
	 * @param id
	 * @throws Exception
	 */
	public boolean resetPayPassword(String id) throws Exception;
	
	/**
	 * 获取所有的企业客户id，包括cana
	 * @param customerSearchCriteriaDTO
	 * @return
	 */
	public List<String> getCustomerIds(int page, int tpageSize);
	
	/**
	 * 获取所有企业客户id,并且区分cana和普通用户<br>
	 * cana的key   是 "CANA"<br>
	 * 保理商的key是 "FACTOR"<br>
	 * 融资客户的key是 "FINANCE"<br>
	 * @return
	 */
	public Map<String, List<String>> getAllUserIds();
	
	/**
	 * 获取cana的id
	 * @return
	 */
	public String getCanaId();
	
	/**
	 * 新建企业用户、如果用户已存在则更新（授信系统专用）
	 * @param customerApply4MemberDTO 创建用户时的必要信息
	 * @param 重试任务ID
	 * @return 返回是否创建成功
	 */
	public boolean createCustomerByCredit(CustomerApply4MemberDTO customerApply4MemberDTO, String taskId);
	
	/**
	 * 更新用户证书DN信息
	 */
	public void updateCustomerCertDN(String userId, String certSubjectDN);
	
	/**
	 * 获取用户证书DN信息
	 * @param userId
	 * @return
	 */
	public String queyCusomerCertDN(String userId);

	/**
	 * 获取融资客户ID如果不存在则生成一个用户ID
	 * @param companyName 公司名称
	 * @return 用户ID
	 */
	public String queryOrGenerateUserId(String companyName);

	/**
	 * 获取融资客户ID如果不存在则生成一个用户ID
	 * @param companyName 公司名称，当individualIdentityCardNo有值时，companyName为个人姓名
	 * @return 用户ID
	 */
	public String queryOrGenerateUserId(String companyName, String individualIdentityCardNo);

	/**
	 * 根据company获取用户ID
	 * @param companyName
	 * @return
	 */
	public String queryUserIdByCompanyName(String companyName);
	
	/**
	 * 韵达项目-客户申请成功，创建用户
	 * @param fromJson
	 * @param taskId
	 * @return
	 */
	public boolean createYundaexCustomerByCredit(YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO, String taskId);
	
	/**
	 * 为VJ项目创建个人用户，用户状态为已激活，如果该用户之前已经存在，返回false
	 * @param applyRequest
	 * @return
	 */
	public CustomerDetailDTO createIndividualUser4VJ(ApplyCreditRequest applyRequest) throws Exception;
	
	
	/**
	 * 根据姓名+身份证号查询个人用户
	 * @param name
	 * @param identityCardNo
	 * @return
	 */
	public CustomerDetailDTO queryIndividualUser(String name, String identityCardNo);

	/**
	 * 更新用户的联系人
	 * @param canaCustomerId
	 * @param contactName
	 */
	public void updateContactName(String memberId, String contactName);
	/**
	 * 更新用户的手机号
	 * @param canaCustomerId
	 * @param mobileNo
	 */
	public void updateContactTel(String memberId, String contactTel);
	/**
	 * 更新用户的邮箱号
	 * @param canaCustomerId
	 * @param contactMail
	 */
	public void updateContactMail(String memberId, String contactMail);
	/**
	 * 更新用户的职称
	 * @param canaCustomerId
	 * @param jobTitle
	 */
	public void updateJobTitle(String memberId, String jobTitle);
	
	/**
	 * 更新个性权限
	 * @param userId
	 * @param permissions
	 */
	public void updateUserPermissions(String userId, String permissions);
	
	/**
	 * 根据用户id检查用户是否有效
	 * @param customerId
	 * @return
	 */
	public CustomerDetailDTO checkCustomerIsValid(String customerId);
}
