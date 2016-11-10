package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;

/**
 * 账户交易申请搜索条件
 * @author XuMeng
 *
 */
public class AccountTradeApplyQueryCriteria implements Serializable {
    private static final long serialVersionUID = -1828755545595777751L;

    private int page = 1;
    private int pageSize = 10;
    private String tradeApplyId;    //申请ID
    private String accountNo;
    private String accountName;
    private String applyCompanyName;
    private AccountTradeApplyType applyType;
    private AccountTradeApplyStatus applyStatus;
    private String startTime;  //申请开始时间，yyyy-MM-dd
    private String endTime;    //申请结束时间yyyy-MM-dd
    private boolean applyBySelf;    //只看由自己申请的
    private boolean auditBySelf;    //只看需要由自己审核的

    //以下为mybatis拼装sql时专用的字段
    private String companyId;       //查找和此客户有关的所有申请
    private String applyCompanyId;  //查找由此客户申请的
    private String auditCompanyId;  //查找由此客户审核的

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public String getApplyCompanyName() {
        return applyCompanyName;
    }
    public void setApplyCompanyName(String applyCompanyName) {
        this.applyCompanyName = applyCompanyName;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public AccountTradeApplyType getApplyType() {
        return applyType;
    }
    public void setApplyType(AccountTradeApplyType applyType) {
        this.applyType = applyType;
    }
    public AccountTradeApplyStatus getApplyStatus() {
        return applyStatus;
    }
    public void setApplyStatus(AccountTradeApplyStatus applyStatus) {
        this.applyStatus = applyStatus;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getApplyCompanyId() {
        return applyCompanyId;
    }
    public void setApplyCompanyId(String applyCompanyId) {
        this.applyCompanyId = applyCompanyId;
    }
    public String getAuditCompanyId() {
        return auditCompanyId;
    }
    public void setAuditCompanyId(String auditCompanyId) {
        this.auditCompanyId = auditCompanyId;
    }
    public boolean isApplyBySelf() {
        return applyBySelf;
    }
    public void setApplyBySelf(boolean applyBySelf) {
        this.applyBySelf = applyBySelf;
    }
    public boolean isAuditBySelf() {
        return auditBySelf;
    }
    public void setAuditBySelf(boolean auditBySelf) {
        this.auditBySelf = auditBySelf;
    }
    public String getTradeApplyId() {
        return tradeApplyId;
    }
    public void setTradeApplyId(String tradeApplyId) {
        this.tradeApplyId = tradeApplyId;
    }
    
}
