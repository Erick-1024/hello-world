package com.cana.member.service.transaction;

import java.util.Date;
import java.util.List;

import com.cana.member.dao.po.User;
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

public interface IUserTransactionService {

    /**
     * 新增企业客户
     * 
     * @param customerRegisterDTO
     */
    public String addCustomer(CustomerRegisterDTO customerRegisterDTO) throws Exception;

    /**
     * 新增企业员工
     * 
     * @param employeeRegisterDTO
     * @param currentOperatorId
     */
    public boolean addEmployee(EmployeeRegisterDTO employeeRegisterDTO, String currentOperatorId) throws Exception;

    /**
     * 检查用户名是否存在
     * 
     * @param username
     * @return boolean
     */
    public boolean checkUsernameExist(String username) throws Exception;

    /**
     * 检查公司名称是否存在
     * 
     * @param companyName
     * @return boolean
     */
    public boolean checkCompanyNameExist(String companyName, UserType userType) throws Exception;

    /**
     * 返回审核页面
     * 
     * @param customerId
     * @return
     * @throws Exception
     */
    public CustomerReviewDTO gotoReview(String customerId) throws Exception;

    /**
     * 审核
     * 
     * @param userId
     */
    public void review(CustomerDetailDTO customerDetailDTO) throws Exception;

    /**
     * 重发邮件
     * 
     * @param userId
     * @throws Exception
     */
    public boolean resendEmail(String userId) throws Exception;

    /**
     * 生成激活url
     */
    public String generateActivacationURL(String userId) throws Exception;

    /**
     * 返回激活页面激活
     * 
     * @param userId
     * @param securityCode
     */
    public UserActivationDTO goToActivation(String userId, String securityCode) throws Exception;

    /**
     * 激活账号
     * 
     * @param userActivationDTO
     * @throws Exception
     */
    public UserActivationDTO activate(UserActivationDTO userActivationDTO) throws Exception;

    /**
     * 获取用户session
     * 
     * @param username
     * @return UserSessionDTO
     */
    public UserSessionDTO getUserSession(String username) throws Exception;

    /**
     * 生成用户id
     * 
     * @return
     * @throws Exception
     */
    public String generateUserId() throws Exception;

    /**
     * 
     * 更新用户登录信息
     * 
     * @param id
     * @param token
     * @param signIn
     * @param signIp
     * @param signTime
     * @return
     * @throws Exception
     */
    public boolean upateUserLoginInfo(String id, String token, boolean signIn, String signIp, Date signTime)
	    throws Exception;

    /**
     * 查询企业客户
     * 
     * @param CustomerSearchCriteriaDTO
     * @return
     * @throws Exception
     */
    public ListResult<CustomerSearchResultDTO> queryCustomerList(CustomerSearchCriteriaDTO customerSearchCriteriaDTO)
	    throws Exception;

    /**
     * 查询企业客户的详情
     * 
     * @param customerId
     * @return
     * @throws Exception
     */
    public CustomerDetailDTO queryCustomerDetail(String customerId);

    public CustomerDetailDTO queryCustomerByCompanyName(String companyName, UserType userType);

    /**
     * 查询员工
     * 
     * @param employeeSearchCriteriaDTO
     * @return
     * @throws Exception
     */
    public ListResult<EmployeeSearchResultDTO> queryEmployeeList(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO,
	    String currentOperatorId) throws Exception;

    /**
     * 查询员工详情
     * 
     * @param employeeId
     * @return
     * @throws Exception
     */
    public EmployeeSearchResultDTO queryEmployeeDetail(String employeeId) throws Exception;

    /**
     * 修改员工
     * 
     * @param employeeRegisterDTO
     * @throws Exception
     */
    public boolean modifyEmployee(EmployeeRegisterDTO employeeRegisterDTO) throws Exception;

    /**
     * 删除员工
     * 
     * @param employeeId
     * @throws Exception
     */
    public boolean deleteEmployee(String employeeId) throws Exception;

    /**
     * 重置密码
     * 
     * @param 用户id
     * @throws Exception
     */
    public boolean resetPassword(String userId) throws Exception;

    /**
     * 更新用户所分配的角色。
     * 
     * @param userUpdateDTO
     * @return 返回是否更新成功
     * @throws Exception
     *
     */
    public boolean updateRoleOfUser(UserUpdateDTO userUpdateDTO) throws Exception;

    /**
     * 更新用户所分配的角色列表。
     * 
     * @param userUpdateDTO
     * @return 返回是否更新成功
     * @throws Exception
     *
     */
    public boolean updateRoleListOfUser(UserRoleListUpdateDTO userUpdateDTO) throws Exception;

    /**
     * 根据融资客户名获取融资客户Id
     * 
     * @param financeCompany
     * @return
     * @throws Exception
     */
    public String getFinanceIdByName(String financeCompany, UserType userType) throws Exception;

    /**
     * 修改个人登录密码
     * 
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     * @throws Exception
     */
    public boolean modifyLoginPassword(String userId, String oldPwd, String newPwd) throws Exception;

    /**
     * 获取企业基本信息
     * 
     * @param sessionDTO
     * @return
     * @throws Exception
     */
    public CompanyInfoDTO getCompanyInfo(UserSessionDTO sessionDTO) throws Exception;

    /**
     * 判断企业是否设置支付密码
     * 
     * @param masterId
     * @return
     * @throws Exception
     */
    public boolean isSetPayPassword(String masterId) throws Exception;

    /**
     * 判断是密码是否正确
     * 
     * @param userId
     * @param loginPwd
     * @param flag
     * @return
     * @throws Exception
     */
    public boolean isloginPwd(String userId, String loginPwd, int flag) throws Exception;

    /**
     * 设置支付密码
     */
    public boolean setPayPwd(String userId, String oldPwd, String newPwd) throws Exception;

    /**
     * 修改支付密码
     */
    public boolean modifyPayPwd(String userId, String oldPwd, String newPwd) throws Exception;

    /**
     * 修改联系人信息
     * 
     * @return
     * @throws Exception
     */
    public boolean modifyContactsInfo(String userId, String contactName, String jobTitle, String mobileNum, String mail)
	    throws Exception;

    /**
     * 修改个人基本信息
     */
    public boolean modifyPersonalInfo(String userId, String contactName, String jobTitle, String mobileNum, String mail)
	    throws Exception;

    /**
     * 修改证件图片
     * 
     * @param userId
     * @param orgMediaId
     * @param busMediaId
     * @param taxMediaId
     * @return
     * @throws Exception
     */
    public boolean modifyMediaId(String userId, String orgMediaId, String busMediaId, String taxMediaId)
	    throws Exception;

    /**
     * 验证支付密码是否正确
     * 
     * @param masterId
     * @param payPwd
     * @return
     * @throws Exception
     */
    public boolean validatePayPwd(String masterId, String payPwd) throws Exception;

    public boolean updateUserGuideStatus(String masterId, UserGuideStatus oldGuideStatus, UserGuideStatus guideStatus) throws Exception;

    /**
     * 获取个人中心基本信息
     */
    public PersonalDetailDTO queryPersonalDetailInfo(String id) throws Exception;

    /**
     * 重置支付密码
     * 
     * @param userId
     * @throws Exception
     */
    public boolean resetPayPassword(String userId) throws Exception;

    /**
     * 新建企业用户、如果用户已存在则更新（授信系统专用）
     * 
     * @param customerApply4MemberDTO
     *            创建用户时的必要信息
     * @param taskId 重试任务Id
     * @return 返回是否创建成功
     */
    public boolean createCustomerByCredit(CustomerApply4MemberDTO customerApply4MemberDTO, String taskId);

    /**
     * 获取所有的已激活的保理商和融资商帐户
     * 
     * @return
     */
    public List<User> getAllFactorAndFinanceUsers();
    
    /**
     * 更新用户dn
     * @param certSubjectDN
     */
    public void updateCustomerCertDN(String userId, String certSubjectDN);
    
    /**
	 * 获取融资客户ID如果不存在则生成一个用户ID
	 * @param companyName 公司名称，当individualIdentityCardNo有值时，则companyName为个人姓名
	 */
	public String queryOrGenerateUserId(String companyName, String individualIdentityCardNo);

	/**
	 * 韵达项目-客户申请审核通过，创建用户
	 * @param ydCustomerApply4MemberUserDTO
	 * @param taskId
	 * @return
	 */
	public boolean createYundaexCustomerByCredit(YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO,
			String taskId);
	
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
	 * 更新用户联系人手机号
	 * @param memberId
	 * @param contactTel
	 */
	public void updateContactTel(String memberId, String contactTel);

	/**
	 * 更新用户联系人姓名
	 * @param memberId
	 * @param contactName
	 */
	public void updateContactName(String memberId, String contactName);

	/**
	 * 更新用户联系人邮箱
	 * @param memberId
	 * @param contactMail
	 */
	public void updateContactMail(String memberId, String contactMail);

	/**
	 * 更新用户联系人职称
	 * @param memberId
	 * @param jobTitle
	 */
	public void updateJobTitle(String memberId, String jobTitle);
	
	/**
	 * 根据customer获取用户id
	 * @param companyName
	 * @return
	 */
	public String queryUserIdByCompanyName(String companyName);
	
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
