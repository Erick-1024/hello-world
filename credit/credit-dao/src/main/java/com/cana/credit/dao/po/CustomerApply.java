/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CustomerApply implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *真旅的客户（采购商）Id
     */
    private String tzCustomerId;

    /**
     *真旅采购商名称，如果真旅没传，则取tz_customer_info表中的最新值，如果也没有则取company_name值
     */
    private String tzCustomerName;

    /**
     *申请时间
     */
    private Date applyDate;

    /**
     *申请公司的全称
     */
    private String companyName;

    /**
     *实际控制人
     */
    private String realControlPerson;

    /**
     *申请额度
     */
    private Long applyCreditLimit;

    /**
     *申请类型（"REPAYMENT"：订单回款，"FINANCING"：订单融资）
     */
    private String applyType;

    /**
     *组织机构证件号码（个人用户为该用户的身份证号码）
     */
    private String organizationNo;

    /**
     *组织机构证件Id（个人用户为用户的身份证正面照）
     */
    private String organizationMediaId;

    /**
     *营业执照证件号码（个人用户为该用户的身份证号码）
     */
    private String businessLicenceNo;

    /**
     *营业执照证件Id（个人用户为用户的手持身份证正面照）
     */
    private String businessLicenceMediaId;

    /**
     *税务登记证件号码（个人用户为该用户的身份证号码）
     */
    private String taxRegistrationCertificateNo;

    /**
     *税务登记证件Id（个人用户为用户的手持身份证反面照）
     */
    private String taxRegistrationCertificateMediaId;

    /**
     *实际控制人身份证号码
     */
    private String realControlPersonId;

    /**
     *实际控制人手持身份证正面照
     */
    private String realControlPersonIdHandheldFrontMediaId;

    /**
     *法人身份证号码
     */
    private String legalPersonId;

    /**
     *法人手持身份证正面照
     */
    private String legalPersonIdHandheldFrontMediaId;

    /**
     *联系人姓名
     */
    private String contactName;

    /**
     *手机号码
     */
    private String phoneNumber;

    /**
     *电子邮件
     */
    private String email;

    /**
     *下游客户类型（"ENTERPRISE"：企业，"INDIVIDUAL"：个人，"BOTH"：两者都有）
     */
    private String downstreamCustomerType;

    /**
     *下游回款账期（如不填则为无账期，固定账期格式：f5,f18（每月5号和18号）  e10(每10天为一个还款周期)）
     */
    private String downstreamRepaymentAccountPeriod;

    /**
     *申请人类型，详见ApplyApplicantType，当没有值时表示不确定
     */
    private String applicantType;

    /**
     *法院被执行（企业）金额，精确到分
     */
    private Long enterpriseExecutionMoney;

    /**
     *执行次数（企业）
     */
    private Integer enterpriseExecutionTimes;

    /**
     *法院被执行（个人）金额，精确到分
     */
    private Long individualExecutionMoney;

    /**
     *执行次数（个人）
     */
    private Integer individualExecutionTimes;

    /**
     *是否存在网络负面信息
     */
    private String negativeNetwork;

    /**
     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
     */
    private Integer consistencyCheck;

    /**
     *准入审核备注，准入审核不通过时填写不通过内容。
     */
    private String automaticAuditRemarks;

    /**
     *是否通过准入规则（"WAIT"：未审核，"ACCESS"：通过准入审核，"NOTACCESS"：不通过准入审核）
     */
    private String accessAutomaticState;

    /**
     *准入规则批次号
     */
    private Integer automaticAuditRuleBatchNo;

    /**
     *自动审核后的数据（例如：{"cooperationPeriod":26,"purchaseOrderGrowthRate":0.1,"overdueRate":0.05,"overdueTimes":2,"overdueDays":2}）
     */
    private String automaticAuditData;

    /**
     *人工审核时的备注
     */
    private String manualAuditRemarks;

    /**
     *是否通过人工审核（"WAIT"：未审核，"WAIT":待审批（非白名单客户），"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
     */
    private String accessManualState;

    /**
     *人工审核规则批次号
     */
    private Integer manualAuditRuleBatchNo;

    /**
     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
     */
    private String creditLimitGenerateState;

    /**
     *审核人Id
     */
    private String auditorId;

    /**
     *是否在白名单内
     */
    private Boolean inWhitelist;

    /**
     *审批人ID（非白名单客户）
     */
    private String approverId;

    /**
     *法人
     */
    private String legalPerson;

    /**
     *审核结果URL
     */
    private String auditNotifyUrl;

    /**
     *额度生效通知
     */
    private String limitNotifyUrl;

    /**
     *更新日期
     */
    private Date updateTime;

    /**
     *自动审核后的数据（合作月份，逾期率，逾期次数等）（例如：{"cooperationPeriod":26,"overdueRate":0.05,"overdueTimes":2,"overdueDays":2}）
     */
    private Date createTime;

    /**
     *客户在真旅的销售数据
     */
    private String saleData;

    private static final long serialVersionUID = 1L;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *真旅的客户（采购商）Id
     */
    public String getTzCustomerId() {
        return tzCustomerId;
    }

    /**
     *真旅的客户（采购商）Id
     */
    public void setTzCustomerId(String tzCustomerId) {
        this.tzCustomerId = tzCustomerId == null ? null : tzCustomerId.trim();
    }

    /**
     *真旅采购商名称，如果真旅没传，则取tz_customer_info表中的最新值，如果也没有则取company_name值
     */
    public String getTzCustomerName() {
        return tzCustomerName;
    }

    /**
     *真旅采购商名称，如果真旅没传，则取tz_customer_info表中的最新值，如果也没有则取company_name值
     */
    public void setTzCustomerName(String tzCustomerName) {
        this.tzCustomerName = tzCustomerName == null ? null : tzCustomerName.trim();
    }

    /**
     *申请时间
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     *申请时间
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     *申请公司的全称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *申请公司的全称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     *实际控制人
     */
    public String getRealControlPerson() {
        return realControlPerson;
    }

    /**
     *实际控制人
     */
    public void setRealControlPerson(String realControlPerson) {
        this.realControlPerson = realControlPerson == null ? null : realControlPerson.trim();
    }

    /**
     *申请额度
     */
    public Long getApplyCreditLimit() {
        return applyCreditLimit;
    }

    /**
     *申请额度
     */
    public void setApplyCreditLimit(Long applyCreditLimit) {
        this.applyCreditLimit = applyCreditLimit;
    }

    /**
     *申请类型（"REPAYMENT"：订单回款，"FINANCING"：订单融资）
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     *申请类型（"REPAYMENT"：订单回款，"FINANCING"：订单融资）
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    /**
     *组织机构证件号码（个人用户为该用户的身份证号码）
     */
    public String getOrganizationNo() {
        return organizationNo;
    }

    /**
     *组织机构证件号码（个人用户为该用户的身份证号码）
     */
    public void setOrganizationNo(String organizationNo) {
        this.organizationNo = organizationNo == null ? null : organizationNo.trim();
    }

    /**
     *组织机构证件Id（个人用户为用户的身份证正面照）
     */
    public String getOrganizationMediaId() {
        return organizationMediaId;
    }

    /**
     *组织机构证件Id（个人用户为用户的身份证正面照）
     */
    public void setOrganizationMediaId(String organizationMediaId) {
        this.organizationMediaId = organizationMediaId == null ? null : organizationMediaId.trim();
    }

    /**
     *营业执照证件号码（个人用户为该用户的身份证号码）
     */
    public String getBusinessLicenceNo() {
        return businessLicenceNo;
    }

    /**
     *营业执照证件号码（个人用户为该用户的身份证号码）
     */
    public void setBusinessLicenceNo(String businessLicenceNo) {
        this.businessLicenceNo = businessLicenceNo == null ? null : businessLicenceNo.trim();
    }

    /**
     *营业执照证件Id（个人用户为用户的手持身份证正面照）
     */
    public String getBusinessLicenceMediaId() {
        return businessLicenceMediaId;
    }

    /**
     *营业执照证件Id（个人用户为用户的手持身份证正面照）
     */
    public void setBusinessLicenceMediaId(String businessLicenceMediaId) {
        this.businessLicenceMediaId = businessLicenceMediaId == null ? null : businessLicenceMediaId.trim();
    }

    /**
     *税务登记证件号码（个人用户为该用户的身份证号码）
     */
    public String getTaxRegistrationCertificateNo() {
        return taxRegistrationCertificateNo;
    }

    /**
     *税务登记证件号码（个人用户为该用户的身份证号码）
     */
    public void setTaxRegistrationCertificateNo(String taxRegistrationCertificateNo) {
        this.taxRegistrationCertificateNo = taxRegistrationCertificateNo == null ? null : taxRegistrationCertificateNo.trim();
    }

    /**
     *税务登记证件Id（个人用户为用户的手持身份证反面照）
     */
    public String getTaxRegistrationCertificateMediaId() {
        return taxRegistrationCertificateMediaId;
    }

    /**
     *税务登记证件Id（个人用户为用户的手持身份证反面照）
     */
    public void setTaxRegistrationCertificateMediaId(String taxRegistrationCertificateMediaId) {
        this.taxRegistrationCertificateMediaId = taxRegistrationCertificateMediaId == null ? null : taxRegistrationCertificateMediaId.trim();
    }

    /**
     *实际控制人身份证号码
     */
    public String getRealControlPersonId() {
        return realControlPersonId;
    }

    /**
     *实际控制人身份证号码
     */
    public void setRealControlPersonId(String realControlPersonId) {
        this.realControlPersonId = realControlPersonId == null ? null : realControlPersonId.trim();
    }

    /**
     *实际控制人手持身份证正面照
     */
    public String getRealControlPersonIdHandheldFrontMediaId() {
        return realControlPersonIdHandheldFrontMediaId;
    }

    /**
     *实际控制人手持身份证正面照
     */
    public void setRealControlPersonIdHandheldFrontMediaId(String realControlPersonIdHandheldFrontMediaId) {
        this.realControlPersonIdHandheldFrontMediaId = realControlPersonIdHandheldFrontMediaId == null ? null : realControlPersonIdHandheldFrontMediaId.trim();
    }

    /**
     *法人身份证号码
     */
    public String getLegalPersonId() {
        return legalPersonId;
    }

    /**
     *法人身份证号码
     */
    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId == null ? null : legalPersonId.trim();
    }

    /**
     *法人手持身份证正面照
     */
    public String getLegalPersonIdHandheldFrontMediaId() {
        return legalPersonIdHandheldFrontMediaId;
    }

    /**
     *法人手持身份证正面照
     */
    public void setLegalPersonIdHandheldFrontMediaId(String legalPersonIdHandheldFrontMediaId) {
        this.legalPersonIdHandheldFrontMediaId = legalPersonIdHandheldFrontMediaId == null ? null : legalPersonIdHandheldFrontMediaId.trim();
    }

    /**
     *联系人姓名
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     *手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *手机号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     *电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     *电子邮件
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     *下游客户类型（"ENTERPRISE"：企业，"INDIVIDUAL"：个人，"BOTH"：两者都有）
     */
    public String getDownstreamCustomerType() {
        return downstreamCustomerType;
    }

    /**
     *下游客户类型（"ENTERPRISE"：企业，"INDIVIDUAL"：个人，"BOTH"：两者都有）
     */
    public void setDownstreamCustomerType(String downstreamCustomerType) {
        this.downstreamCustomerType = downstreamCustomerType == null ? null : downstreamCustomerType.trim();
    }

    /**
     *下游回款账期（如不填则为无账期，固定账期格式：f5,f18（每月5号和18号）  e10(每10天为一个还款周期)）
     */
    public String getDownstreamRepaymentAccountPeriod() {
        return downstreamRepaymentAccountPeriod;
    }

    /**
     *下游回款账期（如不填则为无账期，固定账期格式：f5,f18（每月5号和18号）  e10(每10天为一个还款周期)）
     */
    public void setDownstreamRepaymentAccountPeriod(String downstreamRepaymentAccountPeriod) {
        this.downstreamRepaymentAccountPeriod = downstreamRepaymentAccountPeriod == null ? null : downstreamRepaymentAccountPeriod.trim();
    }

    /**
     *申请人类型，详见ApplyApplicantType，当没有值时表示不确定
     */
    public String getApplicantType() {
        return applicantType;
    }

    /**
     *申请人类型，详见ApplyApplicantType，当没有值时表示不确定
     */
    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType == null ? null : applicantType.trim();
    }

    /**
     *法院被执行（企业）金额，精确到分
     */
    public Long getEnterpriseExecutionMoney() {
        return enterpriseExecutionMoney;
    }

    /**
     *法院被执行（企业）金额，精确到分
     */
    public void setEnterpriseExecutionMoney(Long enterpriseExecutionMoney) {
        this.enterpriseExecutionMoney = enterpriseExecutionMoney;
    }

    /**
     *执行次数（企业）
     */
    public Integer getEnterpriseExecutionTimes() {
        return enterpriseExecutionTimes;
    }

    /**
     *执行次数（企业）
     */
    public void setEnterpriseExecutionTimes(Integer enterpriseExecutionTimes) {
        this.enterpriseExecutionTimes = enterpriseExecutionTimes;
    }

    /**
     *法院被执行（个人）金额，精确到分
     */
    public Long getIndividualExecutionMoney() {
        return individualExecutionMoney;
    }

    /**
     *法院被执行（个人）金额，精确到分
     */
    public void setIndividualExecutionMoney(Long individualExecutionMoney) {
        this.individualExecutionMoney = individualExecutionMoney;
    }

    /**
     *执行次数（个人）
     */
    public Integer getIndividualExecutionTimes() {
        return individualExecutionTimes;
    }

    /**
     *执行次数（个人）
     */
    public void setIndividualExecutionTimes(Integer individualExecutionTimes) {
        this.individualExecutionTimes = individualExecutionTimes;
    }

    /**
     *是否存在网络负面信息
     */
    public String getNegativeNetwork() {
        return negativeNetwork;
    }

    /**
     *是否存在网络负面信息
     */
    public void setNegativeNetwork(String negativeNetwork) {
        this.negativeNetwork = negativeNetwork == null ? null : negativeNetwork.trim();
    }

    /**
     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
     */
    public Integer getConsistencyCheck() {
        return consistencyCheck;
    }

    /**
     *一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)
     */
    public void setConsistencyCheck(Integer consistencyCheck) {
        this.consistencyCheck = consistencyCheck;
    }

    /**
     *准入审核备注，准入审核不通过时填写不通过内容。
     */
    public String getAutomaticAuditRemarks() {
        return automaticAuditRemarks;
    }

    /**
     *准入审核备注，准入审核不通过时填写不通过内容。
     */
    public void setAutomaticAuditRemarks(String automaticAuditRemarks) {
        this.automaticAuditRemarks = automaticAuditRemarks == null ? null : automaticAuditRemarks.trim();
    }

    /**
     *是否通过准入规则（"WAIT"：未审核，"ACCESS"：通过准入审核，"NOTACCESS"：不通过准入审核）
     */
    public String getAccessAutomaticState() {
        return accessAutomaticState;
    }

    /**
     *是否通过准入规则（"WAIT"：未审核，"ACCESS"：通过准入审核，"NOTACCESS"：不通过准入审核）
     */
    public void setAccessAutomaticState(String accessAutomaticState) {
        this.accessAutomaticState = accessAutomaticState == null ? null : accessAutomaticState.trim();
    }

    /**
     *准入规则批次号
     */
    public Integer getAutomaticAuditRuleBatchNo() {
        return automaticAuditRuleBatchNo;
    }

    /**
     *准入规则批次号
     */
    public void setAutomaticAuditRuleBatchNo(Integer automaticAuditRuleBatchNo) {
        this.automaticAuditRuleBatchNo = automaticAuditRuleBatchNo;
    }

    /**
     *自动审核后的数据（例如：{"cooperationPeriod":26,"purchaseOrderGrowthRate":0.1,"overdueRate":0.05,"overdueTimes":2,"overdueDays":2}）
     */
    public String getAutomaticAuditData() {
        return automaticAuditData;
    }

    /**
     *自动审核后的数据（例如：{"cooperationPeriod":26,"purchaseOrderGrowthRate":0.1,"overdueRate":0.05,"overdueTimes":2,"overdueDays":2}）
     */
    public void setAutomaticAuditData(String automaticAuditData) {
        this.automaticAuditData = automaticAuditData == null ? null : automaticAuditData.trim();
    }

    /**
     *人工审核时的备注
     */
    public String getManualAuditRemarks() {
        return manualAuditRemarks;
    }

    /**
     *人工审核时的备注
     */
    public void setManualAuditRemarks(String manualAuditRemarks) {
        this.manualAuditRemarks = manualAuditRemarks == null ? null : manualAuditRemarks.trim();
    }

    /**
     *是否通过人工审核（"WAIT"：未审核，"WAIT":待审批（非白名单客户），"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
     */
    public String getAccessManualState() {
        return accessManualState;
    }

    /**
     *是否通过人工审核（"WAIT"：未审核，"WAIT":待审批（非白名单客户），"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）
     */
    public void setAccessManualState(String accessManualState) {
        this.accessManualState = accessManualState == null ? null : accessManualState.trim();
    }

    /**
     *人工审核规则批次号
     */
    public Integer getManualAuditRuleBatchNo() {
        return manualAuditRuleBatchNo;
    }

    /**
     *人工审核规则批次号
     */
    public void setManualAuditRuleBatchNo(Integer manualAuditRuleBatchNo) {
        this.manualAuditRuleBatchNo = manualAuditRuleBatchNo;
    }

    /**
     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
     */
    public String getCreditLimitGenerateState() {
        return creditLimitGenerateState;
    }

    /**
     *额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）
     */
    public void setCreditLimitGenerateState(String creditLimitGenerateState) {
        this.creditLimitGenerateState = creditLimitGenerateState == null ? null : creditLimitGenerateState.trim();
    }

    /**
     *审核人Id
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     *审核人Id
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId == null ? null : auditorId.trim();
    }

    /**
     *是否在白名单内
     */
    public Boolean getInWhitelist() {
        return inWhitelist;
    }

    /**
     *是否在白名单内
     */
    public void setInWhitelist(Boolean inWhitelist) {
        this.inWhitelist = inWhitelist;
    }

    /**
     *审批人ID（非白名单客户）
     */
    public String getApproverId() {
        return approverId;
    }

    /**
     *审批人ID（非白名单客户）
     */
    public void setApproverId(String approverId) {
        this.approverId = approverId == null ? null : approverId.trim();
    }

    /**
     *法人
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     *法人
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    /**
     *审核结果URL
     */
    public String getAuditNotifyUrl() {
        return auditNotifyUrl;
    }

    /**
     *审核结果URL
     */
    public void setAuditNotifyUrl(String auditNotifyUrl) {
        this.auditNotifyUrl = auditNotifyUrl == null ? null : auditNotifyUrl.trim();
    }

    /**
     *额度生效通知
     */
    public String getLimitNotifyUrl() {
        return limitNotifyUrl;
    }

    /**
     *额度生效通知
     */
    public void setLimitNotifyUrl(String limitNotifyUrl) {
        this.limitNotifyUrl = limitNotifyUrl == null ? null : limitNotifyUrl.trim();
    }

    /**
     *更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *自动审核后的数据（合作月份，逾期率，逾期次数等）（例如：{"cooperationPeriod":26,"overdueRate":0.05,"overdueTimes":2,"overdueDays":2}）
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *自动审核后的数据（合作月份，逾期率，逾期次数等）（例如：{"cooperationPeriod":26,"overdueRate":0.05,"overdueTimes":2,"overdueDays":2}）
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *客户在真旅的销售数据
     */
    public String getSaleData() {
        return saleData;
    }

    /**
     *客户在真旅的销售数据
     */
    public void setSaleData(String saleData) {
        this.saleData = saleData == null ? null : saleData.trim();
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
        CustomerApply other = (CustomerApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTzCustomerId() == null ? other.getTzCustomerId() == null : this.getTzCustomerId().equals(other.getTzCustomerId()))
            && (this.getTzCustomerName() == null ? other.getTzCustomerName() == null : this.getTzCustomerName().equals(other.getTzCustomerName()))
            && (this.getApplyDate() == null ? other.getApplyDate() == null : this.getApplyDate().equals(other.getApplyDate()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getRealControlPerson() == null ? other.getRealControlPerson() == null : this.getRealControlPerson().equals(other.getRealControlPerson()))
            && (this.getApplyCreditLimit() == null ? other.getApplyCreditLimit() == null : this.getApplyCreditLimit().equals(other.getApplyCreditLimit()))
            && (this.getApplyType() == null ? other.getApplyType() == null : this.getApplyType().equals(other.getApplyType()))
            && (this.getOrganizationNo() == null ? other.getOrganizationNo() == null : this.getOrganizationNo().equals(other.getOrganizationNo()))
            && (this.getOrganizationMediaId() == null ? other.getOrganizationMediaId() == null : this.getOrganizationMediaId().equals(other.getOrganizationMediaId()))
            && (this.getBusinessLicenceNo() == null ? other.getBusinessLicenceNo() == null : this.getBusinessLicenceNo().equals(other.getBusinessLicenceNo()))
            && (this.getBusinessLicenceMediaId() == null ? other.getBusinessLicenceMediaId() == null : this.getBusinessLicenceMediaId().equals(other.getBusinessLicenceMediaId()))
            && (this.getTaxRegistrationCertificateNo() == null ? other.getTaxRegistrationCertificateNo() == null : this.getTaxRegistrationCertificateNo().equals(other.getTaxRegistrationCertificateNo()))
            && (this.getTaxRegistrationCertificateMediaId() == null ? other.getTaxRegistrationCertificateMediaId() == null : this.getTaxRegistrationCertificateMediaId().equals(other.getTaxRegistrationCertificateMediaId()))
            && (this.getRealControlPersonId() == null ? other.getRealControlPersonId() == null : this.getRealControlPersonId().equals(other.getRealControlPersonId()))
            && (this.getRealControlPersonIdHandheldFrontMediaId() == null ? other.getRealControlPersonIdHandheldFrontMediaId() == null : this.getRealControlPersonIdHandheldFrontMediaId().equals(other.getRealControlPersonIdHandheldFrontMediaId()))
            && (this.getLegalPersonId() == null ? other.getLegalPersonId() == null : this.getLegalPersonId().equals(other.getLegalPersonId()))
            && (this.getLegalPersonIdHandheldFrontMediaId() == null ? other.getLegalPersonIdHandheldFrontMediaId() == null : this.getLegalPersonIdHandheldFrontMediaId().equals(other.getLegalPersonIdHandheldFrontMediaId()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getDownstreamCustomerType() == null ? other.getDownstreamCustomerType() == null : this.getDownstreamCustomerType().equals(other.getDownstreamCustomerType()))
            && (this.getDownstreamRepaymentAccountPeriod() == null ? other.getDownstreamRepaymentAccountPeriod() == null : this.getDownstreamRepaymentAccountPeriod().equals(other.getDownstreamRepaymentAccountPeriod()))
            && (this.getApplicantType() == null ? other.getApplicantType() == null : this.getApplicantType().equals(other.getApplicantType()))
            && (this.getEnterpriseExecutionMoney() == null ? other.getEnterpriseExecutionMoney() == null : this.getEnterpriseExecutionMoney().equals(other.getEnterpriseExecutionMoney()))
            && (this.getEnterpriseExecutionTimes() == null ? other.getEnterpriseExecutionTimes() == null : this.getEnterpriseExecutionTimes().equals(other.getEnterpriseExecutionTimes()))
            && (this.getIndividualExecutionMoney() == null ? other.getIndividualExecutionMoney() == null : this.getIndividualExecutionMoney().equals(other.getIndividualExecutionMoney()))
            && (this.getIndividualExecutionTimes() == null ? other.getIndividualExecutionTimes() == null : this.getIndividualExecutionTimes().equals(other.getIndividualExecutionTimes()))
            && (this.getNegativeNetwork() == null ? other.getNegativeNetwork() == null : this.getNegativeNetwork().equals(other.getNegativeNetwork()))
            && (this.getConsistencyCheck() == null ? other.getConsistencyCheck() == null : this.getConsistencyCheck().equals(other.getConsistencyCheck()))
            && (this.getAutomaticAuditRemarks() == null ? other.getAutomaticAuditRemarks() == null : this.getAutomaticAuditRemarks().equals(other.getAutomaticAuditRemarks()))
            && (this.getAccessAutomaticState() == null ? other.getAccessAutomaticState() == null : this.getAccessAutomaticState().equals(other.getAccessAutomaticState()))
            && (this.getAutomaticAuditRuleBatchNo() == null ? other.getAutomaticAuditRuleBatchNo() == null : this.getAutomaticAuditRuleBatchNo().equals(other.getAutomaticAuditRuleBatchNo()))
            && (this.getAutomaticAuditData() == null ? other.getAutomaticAuditData() == null : this.getAutomaticAuditData().equals(other.getAutomaticAuditData()))
            && (this.getManualAuditRemarks() == null ? other.getManualAuditRemarks() == null : this.getManualAuditRemarks().equals(other.getManualAuditRemarks()))
            && (this.getAccessManualState() == null ? other.getAccessManualState() == null : this.getAccessManualState().equals(other.getAccessManualState()))
            && (this.getManualAuditRuleBatchNo() == null ? other.getManualAuditRuleBatchNo() == null : this.getManualAuditRuleBatchNo().equals(other.getManualAuditRuleBatchNo()))
            && (this.getCreditLimitGenerateState() == null ? other.getCreditLimitGenerateState() == null : this.getCreditLimitGenerateState().equals(other.getCreditLimitGenerateState()))
            && (this.getAuditorId() == null ? other.getAuditorId() == null : this.getAuditorId().equals(other.getAuditorId()))
            && (this.getInWhitelist() == null ? other.getInWhitelist() == null : this.getInWhitelist().equals(other.getInWhitelist()))
            && (this.getApproverId() == null ? other.getApproverId() == null : this.getApproverId().equals(other.getApproverId()))
            && (this.getLegalPerson() == null ? other.getLegalPerson() == null : this.getLegalPerson().equals(other.getLegalPerson()))
            && (this.getAuditNotifyUrl() == null ? other.getAuditNotifyUrl() == null : this.getAuditNotifyUrl().equals(other.getAuditNotifyUrl()))
            && (this.getLimitNotifyUrl() == null ? other.getLimitNotifyUrl() == null : this.getLimitNotifyUrl().equals(other.getLimitNotifyUrl()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSaleData() == null ? other.getSaleData() == null : this.getSaleData().equals(other.getSaleData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTzCustomerId() == null) ? 0 : getTzCustomerId().hashCode());
        result = prime * result + ((getTzCustomerName() == null) ? 0 : getTzCustomerName().hashCode());
        result = prime * result + ((getApplyDate() == null) ? 0 : getApplyDate().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getRealControlPerson() == null) ? 0 : getRealControlPerson().hashCode());
        result = prime * result + ((getApplyCreditLimit() == null) ? 0 : getApplyCreditLimit().hashCode());
        result = prime * result + ((getApplyType() == null) ? 0 : getApplyType().hashCode());
        result = prime * result + ((getOrganizationNo() == null) ? 0 : getOrganizationNo().hashCode());
        result = prime * result + ((getOrganizationMediaId() == null) ? 0 : getOrganizationMediaId().hashCode());
        result = prime * result + ((getBusinessLicenceNo() == null) ? 0 : getBusinessLicenceNo().hashCode());
        result = prime * result + ((getBusinessLicenceMediaId() == null) ? 0 : getBusinessLicenceMediaId().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateNo() == null) ? 0 : getTaxRegistrationCertificateNo().hashCode());
        result = prime * result + ((getTaxRegistrationCertificateMediaId() == null) ? 0 : getTaxRegistrationCertificateMediaId().hashCode());
        result = prime * result + ((getRealControlPersonId() == null) ? 0 : getRealControlPersonId().hashCode());
        result = prime * result + ((getRealControlPersonIdHandheldFrontMediaId() == null) ? 0 : getRealControlPersonIdHandheldFrontMediaId().hashCode());
        result = prime * result + ((getLegalPersonId() == null) ? 0 : getLegalPersonId().hashCode());
        result = prime * result + ((getLegalPersonIdHandheldFrontMediaId() == null) ? 0 : getLegalPersonIdHandheldFrontMediaId().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getDownstreamCustomerType() == null) ? 0 : getDownstreamCustomerType().hashCode());
        result = prime * result + ((getDownstreamRepaymentAccountPeriod() == null) ? 0 : getDownstreamRepaymentAccountPeriod().hashCode());
        result = prime * result + ((getApplicantType() == null) ? 0 : getApplicantType().hashCode());
        result = prime * result + ((getEnterpriseExecutionMoney() == null) ? 0 : getEnterpriseExecutionMoney().hashCode());
        result = prime * result + ((getEnterpriseExecutionTimes() == null) ? 0 : getEnterpriseExecutionTimes().hashCode());
        result = prime * result + ((getIndividualExecutionMoney() == null) ? 0 : getIndividualExecutionMoney().hashCode());
        result = prime * result + ((getIndividualExecutionTimes() == null) ? 0 : getIndividualExecutionTimes().hashCode());
        result = prime * result + ((getNegativeNetwork() == null) ? 0 : getNegativeNetwork().hashCode());
        result = prime * result + ((getConsistencyCheck() == null) ? 0 : getConsistencyCheck().hashCode());
        result = prime * result + ((getAutomaticAuditRemarks() == null) ? 0 : getAutomaticAuditRemarks().hashCode());
        result = prime * result + ((getAccessAutomaticState() == null) ? 0 : getAccessAutomaticState().hashCode());
        result = prime * result + ((getAutomaticAuditRuleBatchNo() == null) ? 0 : getAutomaticAuditRuleBatchNo().hashCode());
        result = prime * result + ((getAutomaticAuditData() == null) ? 0 : getAutomaticAuditData().hashCode());
        result = prime * result + ((getManualAuditRemarks() == null) ? 0 : getManualAuditRemarks().hashCode());
        result = prime * result + ((getAccessManualState() == null) ? 0 : getAccessManualState().hashCode());
        result = prime * result + ((getManualAuditRuleBatchNo() == null) ? 0 : getManualAuditRuleBatchNo().hashCode());
        result = prime * result + ((getCreditLimitGenerateState() == null) ? 0 : getCreditLimitGenerateState().hashCode());
        result = prime * result + ((getAuditorId() == null) ? 0 : getAuditorId().hashCode());
        result = prime * result + ((getInWhitelist() == null) ? 0 : getInWhitelist().hashCode());
        result = prime * result + ((getApproverId() == null) ? 0 : getApproverId().hashCode());
        result = prime * result + ((getLegalPerson() == null) ? 0 : getLegalPerson().hashCode());
        result = prime * result + ((getAuditNotifyUrl() == null) ? 0 : getAuditNotifyUrl().hashCode());
        result = prime * result + ((getLimitNotifyUrl() == null) ? 0 : getLimitNotifyUrl().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSaleData() == null) ? 0 : getSaleData().hashCode());
        return result;
    }
}