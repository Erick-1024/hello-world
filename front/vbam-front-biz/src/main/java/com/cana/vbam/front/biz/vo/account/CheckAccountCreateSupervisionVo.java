package com.cana.vbam.front.biz.vo.account;

public class CheckAccountCreateSupervisionVo {

    private boolean result; //当前账户是否可以创建监管关系
    private String reason;  //不可以时的原因
    private String accountId;   //可以时的账户Id
    private String accountName; //可以时的账户名称
    private String buyerName;   //可以时的买方名称

    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    
}
