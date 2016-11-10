package com.cana.vbam.common.dto;

import java.io.Serializable;

/**
 * 公司资料审核状态详细信息，此类供用户注册审核和账户代开户审核同时使用
 * @author XuMeng
 */
public class CompanyInfoAuditDetail implements Serializable {
    private static final long serialVersionUID = -8322251209856254318L;

    protected String auditorId;                               //审核人员工ID
    protected String auditMessage;                            //审核意见
    protected boolean checkCompanyName;                       //公司名称
    protected boolean checkOrganizationCode;                  //组织机构代码
    protected boolean checkBusinessLicenceCode;               //营业执照
    protected boolean checkTaxRegistrationCertificateCode;    //税务登记证
    protected boolean checkLegalPersonIdentityCard;           //法人身份证
    protected boolean checkOthers;                            //审核其他是否通过

    public final void setIntUserAuditStatus(int status) {
        this.checkCompanyName                       = (binary1 & status) > 0;
        this.checkOrganizationCode                  = (binary2 & status) > 0;
        this.checkBusinessLicenceCode               = (binary3 & status) > 0;
        this.checkTaxRegistrationCertificateCode    = (binary4 & status) > 0;
        this.checkLegalPersonIdentityCard           = (binary5 & status) > 0;
        this.checkOthers                            = (binary6 & status) > 0;
    }

    /** 获取整型的审核状态 */
    public final int getIntUserAuditStatus() {
        int status = 0;
        status += this.checkCompanyName                     ? binary1 : 0;
        status += this.checkOrganizationCode                ? binary2 : 0;
        status += this.checkBusinessLicenceCode             ? binary3 : 0;
        status += this.checkTaxRegistrationCertificateCode  ? binary4 : 0;
        status += this.checkLegalPersonIdentityCard         ? binary5 : 0;
        status += this.checkOthers                          ? binary6 : 0;
        return status;
    }

    /** 是否审核通过 */
    public final boolean isUserAuditPassed() {
        if (this.checkOthers
                && this.checkCompanyName
                && this.checkOrganizationCode
                && this.checkBusinessLicenceCode
                && this.checkTaxRegistrationCertificateCode
                && this.checkLegalPersonIdentityCard) {
            return true;
        } else {
            return false;
        }
    }

    // 最低10位为用户和账户公用的位，其中只使用了最低六位
    protected static final int binary1 = Integer.parseInt("1", 2);
    protected static final int binary2 = Integer.parseInt("10", 2);
    protected static final int binary3 = Integer.parseInt("100", 2);
    protected static final int binary4 = Integer.parseInt("1000", 2);
    protected static final int binary5 = Integer.parseInt("10000", 2);
    protected static final int binary6 = Integer.parseInt("100000", 2);
    // 从第11位开始，为各业务私有的使用的位
    protected static final int binary11 = Integer.parseInt("10000000000", 2);
    protected static final int binary12 = Integer.parseInt("100000000000", 2);

    public boolean isCheckOthers() {
        return checkOthers;
    }

    public void setCheckOthers(boolean checkOthers) {
        this.checkOthers = checkOthers;
    }

    public boolean isCheckCompanyName() {
        return checkCompanyName;
    }

    public void setCheckCompanyName(boolean checkCompanyName) {
        this.checkCompanyName = checkCompanyName;
    }

    public boolean isCheckOrganizationCode() {
        return checkOrganizationCode;
    }

    public void setCheckOrganizationCode(boolean checkOrganizationCode) {
        this.checkOrganizationCode = checkOrganizationCode;
    }

    public boolean isCheckBusinessLicenceCode() {
        return checkBusinessLicenceCode;
    }

    public void setCheckBusinessLicenceCode(boolean checkBusinessLicenceCode) {
        this.checkBusinessLicenceCode = checkBusinessLicenceCode;
    }

    public boolean isCheckTaxRegistrationCertificateCode() {
        return checkTaxRegistrationCertificateCode;
    }

    public void setCheckTaxRegistrationCertificateCode(
            boolean checkTaxRegistrationCertificateCode) {
        this.checkTaxRegistrationCertificateCode = checkTaxRegistrationCertificateCode;
    }

    public boolean isCheckLegalPersonIdentityCard() {
        return checkLegalPersonIdentityCard;
    }

    public void setCheckLegalPersonIdentityCard(
            boolean checkLegalPersonIdentityCard) {
        this.checkLegalPersonIdentityCard = checkLegalPersonIdentityCard;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditMessage() {
        return auditMessage;
    }
    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }
}
