/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Customer implements Serializable {
    /**
     *客户ｉｄ
     */
    private String id;

    /**
     *客户名称			

     */
    private String customerName;

    /**
     *客户类型(融资申请人，交易对手，担保方)
     */
    private String customerType;

    /**
     *联系人名字
     */
    private String contactName;

    /**
     *手机号码
     */
    private String mobileNo;

    /**
     *电子邮箱
     */
    private String mail;

    /**
     *公司地址
     */
    private String companyAddress;

    /**
     *经济类型
     */
    private String economicCategory;

    /**
     *行业
     */
    private String industry;

    /**
     *所属地区
     */
    private String city;

    /**
     *营业执照号码
     */
    private String businessLicenceCode;

    /**
     *营业执照有效日期
     */
    private Date businessLicenceCodeExpiryDate;

    /**
     *税务登记证号码
     */
    private String taxRegistrationCertificateCode;

    /**
     *税务登记证有效日期
     */
    private Date taxRegistrationCertificateCodeExpiryDate;

    /**
     *组织机构代码
     */
    private String organizationCode;

    /**
     *组织机构证有效日期
     */
    private Date organizationCodeExpiryDate;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private Date updateTime;

    /**
     *企业资料提交状态
     */
    private String enterpriseMaterialState;

    /**
     *保理商Id
     */
    private String factorId;

    /**
     *法定代表人
     */
    private String legalRepresentative;

    /**
     *注册资本
     */
    private BigDecimal registeredCapital;

    private static final long serialVersionUID = 1L;

    /**
     *客户ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *客户ｉｄ
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *客户名称			

     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *客户名称			

     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *客户类型(融资申请人，交易对手，担保方)
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     *客户类型(融资申请人，交易对手，担保方)
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     *联系人名字
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *联系人名字
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     *手机号码
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *手机号码
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     *电子邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     *电子邮箱
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    /**
     *公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     *公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     *经济类型
     */
    public String getEconomicCategory() {
        return economicCategory;
    }

    /**
     *经济类型
     */
    public void setEconomicCategory(String economicCategory) {
        this.economicCategory = economicCategory == null ? null : economicCategory.trim();
    }

    /**
     *行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     *行业
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     *所属地区
     */
    public String getCity() {
        return city;
    }

    /**
     *所属地区
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     *营业执照号码
     */
    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    /**
     *营业执照号码
     */
    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode == null ? null : businessLicenceCode.trim();
    }

    /**
     *营业执照有效日期
     */
    public Date getBusinessLicenceCodeExpiryDate() {
        return businessLicenceCodeExpiryDate;
    }

    /**
     *营业执照有效日期
     */
    public void setBusinessLicenceCodeExpiryDate(Date businessLicenceCodeExpiryDate) {
        this.businessLicenceCodeExpiryDate = businessLicenceCodeExpiryDate;
    }

    /**
     *税务登记证号码
     */
    public String getTaxRegistrationCertificateCode() {
        return taxRegistrationCertificateCode;
    }

    /**
     *税务登记证号码
     */
    public void setTaxRegistrationCertificateCode(String taxRegistrationCertificateCode) {
        this.taxRegistrationCertificateCode = taxRegistrationCertificateCode == null ? null : taxRegistrationCertificateCode.trim();
    }

    /**
     *税务登记证有效日期
     */
    public Date getTaxRegistrationCertificateCodeExpiryDate() {
        return taxRegistrationCertificateCodeExpiryDate;
    }

    /**
     *税务登记证有效日期
     */
    public void setTaxRegistrationCertificateCodeExpiryDate(Date taxRegistrationCertificateCodeExpiryDate) {
        this.taxRegistrationCertificateCodeExpiryDate = taxRegistrationCertificateCodeExpiryDate;
    }

    /**
     *组织机构代码
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     *组织机构代码
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    /**
     *组织机构证有效日期
     */
    public Date getOrganizationCodeExpiryDate() {
        return organizationCodeExpiryDate;
    }

    /**
     *组织机构证有效日期
     */
    public void setOrganizationCodeExpiryDate(Date organizationCodeExpiryDate) {
        this.organizationCodeExpiryDate = organizationCodeExpiryDate;
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *记录更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *记录更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *企业资料提交状态
     */
    public String getEnterpriseMaterialState() {
        return enterpriseMaterialState;
    }

    /**
     *企业资料提交状态
     */
    public void setEnterpriseMaterialState(String enterpriseMaterialState) {
        this.enterpriseMaterialState = enterpriseMaterialState == null ? null : enterpriseMaterialState.trim();
    }

    /**
     *保理商Id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商Id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *法定代表人
     */
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     *法定代表人
     */
    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
    }

    /**
     *注册资本
     */
    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    /**
     *注册资本
     */
    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerType() == null ? other.getCustomerType() == null : this.getCustomerType().equals(other.getCustomerType()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getMobileNo() == null ? other.getMobileNo() == null : this.getMobileNo().equals(other.getMobileNo()))
            && (this.getMail() == null ? other.getMail() == null : this.getMail().equals(other.getMail()))
            && (this.getCompanyAddress() == null ? other.getCompanyAddress() == null : this.getCompanyAddress().equals(other.getCompanyAddress()))
            && (this.getEconomicCategory() == null ? other.getEconomicCategory() == null : this.getEconomicCategory().equals(other.getEconomicCategory()))
            && (this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getBusinessLicenceCode() == null ? other.getBusinessLicenceCode() == null : this.getBusinessLicenceCode().equals(other.getBusinessLicenceCode()))
            && (this.getBusinessLicenceCodeExpiryDate() == null ? other.getBusinessLicenceCodeExpiryDate() == null : this.getBusinessLicenceCodeExpiryDate().equals(other.getBusinessLicenceCodeExpiryDate()))
            && (this.getTaxRegistrationCertificateCode() == null ? other.getTaxRegistrationCertificateCode() == null : this.getTaxRegistrationCertificateCode().equals(other.getTaxRegistrationCertificateCode()))
            && (this.getTaxRegistrationCertificateCodeExpiryDate() == null ? other.getTaxRegistrationCertificateCodeExpiryDate() == null : this.getTaxRegistrationCertificateCodeExpiryDate().equals(other.getTaxRegistrationCertificateCodeExpiryDate()))
            && (this.getOrganizationCode() == null ? other.getOrganizationCode() == null : this.getOrganizationCode().equals(other.getOrganizationCode()))
            && (this.getOrganizationCodeExpiryDate() == null ? other.getOrganizationCodeExpiryDate() == null : this.getOrganizationCodeExpiryDate().equals(other.getOrganizationCodeExpiryDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnterpriseMaterialState() == null ? other.getEnterpriseMaterialState() == null : this.getEnterpriseMaterialState().equals(other.getEnterpriseMaterialState()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getLegalRepresentative() == null ? other.getLegalRepresentative() == null : this.getLegalRepresentative().equals(other.getLegalRepresentative()))
            && (this.getRegisteredCapital() == null ? other.getRegisteredCapital() == null : this.getRegisteredCapital().equals(other.getRegisteredCapital()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerType() == null) ? 0 : getCustomerType().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getMail() == null) ? 0 : getMail().hashCode());
        result = prime * result + ((getCompanyAddress() == null) ? 0 : getCompanyAddress().hashCode());
        result = prime * result + ((getEconomicCategory() == null) ? 0 : getEconomicCategory().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getBusinessLicenceCode() == null) ? 0 : getBusinessLicenceCode().hashCode());
        result = prime * result + ((getBusinessLicenceCodeExpiryDate() == null) ? 0 : getBusinessLicenceCodeExpiryDate().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateCode() == null) ? 0 : getTaxRegistrationCertificateCode().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateCodeExpiryDate() == null) ? 0 : getTaxRegistrationCertificateCodeExpiryDate().hashCode());
        result = prime * result + ((getOrganizationCode() == null) ? 0 : getOrganizationCode().hashCode());
        result = prime * result + ((getOrganizationCodeExpiryDate() == null) ? 0 : getOrganizationCodeExpiryDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnterpriseMaterialState() == null) ? 0 : getEnterpriseMaterialState().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getLegalRepresentative() == null) ? 0 : getLegalRepresentative().hashCode());
        result = prime * result + ((getRegisteredCapital() == null) ? 0 : getRegisteredCapital().hashCode());
        return result;
    }
}