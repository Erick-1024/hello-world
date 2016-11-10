package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 账户申请
 * @author XuMeng
 *
 */
public class AccountApplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id; //主键
    private AccountApplyStatus applyStatus;    //申请状态（未审核、审核未通过、审核已通过）
    private UserType userType;    //客户类型
//    private String companyId;   //企业id
    private String companyName; //企业名称
//    private String agentCompanyId;   //代理企业id
    private String agentCompanyName; //代理企业名称
    private Integer accountNumber; //申请账户数量，若buyerNames值不为空，则账户数量以buyerNames为准
    private AccountType accountType;    //申请账户类型
    private String supervisionAccountId;    //录入的监管账户ID
    private String supervisionAccountNo;    //录入的监管账号
    private String supervisionAccountName;  //录入的监管账户名称
    private List<String> buyerNames;   //买方企业名称列表
    private String contactName;    //联系人
    private String contactJobTitle;    //联系人职称
    private String contactTel; //联系人电话
    private String contactMail;    //邮箱
    private String contactIdentityCardFrontMediaId;    //联系人身份证正面Id
    private String contactIdentityCardBackMediaId; //联系人身份证背面Id
    private String authorizationLetterId;  //企业授权书id
    private String organizationCode;   //组织机构代码
    private String organizationCodeCertificateMediaId; //组织机构代码证文件Id
    private String businessLicenceCode;    //营业执照号码
    private String businessLicenceMediaId; //营业执照扫描件Id
    private String legalPersonIdentityCardFrontMediaId;    //企业法人身份证正面id
    private String legalPersonIdentityCardBackMediaId; //企业法人身份证背面id
    private String taxRegistrationCertificateCode; //税务登记证号码
    private String taxRegistrationCertificateMediaId;  //税务登记证id
    private Date createTime;   //创建时间
    private Date updateTime;   //更新时间

    //------vbam-biz页面专用字段 start
    private String username;    // 用户名
    private String userTypeDesc;    // 客户类型描述
    private boolean showRoles;  //审核时是否显示分配角色列表

	private String auditResultDesc;  //审核结果描述
    private String auditStatusDesc;  //审核状态描述
    private String auditorName; // 审核人名字
    private AccountApplyAuditDetail auditDetail;    //审核详细信息

    private List<AccountDTO> accounts;  //审核通过时，生成的账号列表
    //------vbam-biz页面专用字段 end


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountApplyStatus getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(AccountApplyStatus applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactJobTitle() {
        return contactJobTitle;
    }

    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getContactIdentityCardFrontMediaId() {
        return contactIdentityCardFrontMediaId;
    }

    public void setContactIdentityCardFrontMediaId(
            String contactIdentityCardFrontMediaId) {
        this.contactIdentityCardFrontMediaId = contactIdentityCardFrontMediaId;
    }

    public String getContactIdentityCardBackMediaId() {
        return contactIdentityCardBackMediaId;
    }

    public void setContactIdentityCardBackMediaId(
            String contactIdentityCardBackMediaId) {
        this.contactIdentityCardBackMediaId = contactIdentityCardBackMediaId;
    }

    public String getAuthorizationLetterId() {
        return authorizationLetterId;
    }

    public void setAuthorizationLetterId(String authorizationLetterId) {
        this.authorizationLetterId = authorizationLetterId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationCodeCertificateMediaId() {
        return organizationCodeCertificateMediaId;
    }

    public void setOrganizationCodeCertificateMediaId(
            String organizationCodeCertificateMediaId) {
        this.organizationCodeCertificateMediaId = organizationCodeCertificateMediaId;
    }

    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode;
    }

    public String getBusinessLicenceMediaId() {
        return businessLicenceMediaId;
    }

    public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
        this.businessLicenceMediaId = businessLicenceMediaId;
    }

    public String getLegalPersonIdentityCardFrontMediaId() {
        return legalPersonIdentityCardFrontMediaId;
    }

    public void setLegalPersonIdentityCardFrontMediaId(
            String legalPersonIdentityCardFrontMediaId) {
        this.legalPersonIdentityCardFrontMediaId = legalPersonIdentityCardFrontMediaId;
    }

    public String getLegalPersonIdentityCardBackMediaId() {
        return legalPersonIdentityCardBackMediaId;
    }

    public void setLegalPersonIdentityCardBackMediaId(
            String legalPersonIdentityCardBackMediaId) {
        this.legalPersonIdentityCardBackMediaId = legalPersonIdentityCardBackMediaId;
    }

    public String getTaxRegistrationCertificateCode() {
        return taxRegistrationCertificateCode;
    }

    public void setTaxRegistrationCertificateCode(
            String taxRegistrationCertificateCode) {
        this.taxRegistrationCertificateCode = taxRegistrationCertificateCode;
    }

    public String getTaxRegistrationCertificateMediaId() {
        return taxRegistrationCertificateMediaId;
    }

    public void setTaxRegistrationCertificateMediaId(
            String taxRegistrationCertificateMediaId) {
        this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getBuyerNames() {
        return buyerNames;
    }

    public void setBuyerNames(List<String> buyerNames) {
        this.buyerNames = buyerNames;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public AccountApplyAuditDetail getAuditDetail() {
        return auditDetail;
    }

    public void setAuditDetail(AccountApplyAuditDetail auditDetail) {
        this.auditDetail = auditDetail;
    }

    public String getUserTypeDesc() {
        return userTypeDesc;
    }

    public void setUserTypeDesc(String userTypeDesc) {
        this.userTypeDesc = userTypeDesc;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAgentCompanyName() {
        return agentCompanyName;
    }

    public void setAgentCompanyName(String agentCompanyName) {
        this.agentCompanyName = agentCompanyName;
    }

    public String getAuditResultDesc() {
        return auditResultDesc;
    }

    public void setAuditResultDesc(String auditResultDesc) {
        this.auditResultDesc = auditResultDesc;
    }

    public String getAuditStatusDesc() {
        return auditStatusDesc;
    }

    public void setAuditStatusDesc(String auditStatusDesc) {
        this.auditStatusDesc = auditStatusDesc;
    }

    public boolean isShowRoles() {
		return showRoles;
	}

	public void setShowRoles(boolean showRoles) {
		this.showRoles = showRoles;
	}

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getSupervisionAccountId() {
        return supervisionAccountId;
    }

    public void setSupervisionAccountId(String supervisionAccountId) {
        this.supervisionAccountId = supervisionAccountId;
    }

    public String getSupervisionAccountNo() {
        return supervisionAccountNo;
    }

    public void setSupervisionAccountNo(String supervisionAccountNo) {
        this.supervisionAccountNo = supervisionAccountNo;
    }

    public String getSupervisionAccountName() {
        return supervisionAccountName;
    }

    public void setSupervisionAccountName(String supervisionAccountName) {
        this.supervisionAccountName = supervisionAccountName;
    }
}
