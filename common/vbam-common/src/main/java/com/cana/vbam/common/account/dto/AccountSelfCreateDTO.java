package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

public class AccountSelfCreateDTO implements Serializable {
    private static final long serialVersionUID = 8997120454580238858L;

    private String customerId; //用户企业Id
    private int accountNumber;  //申请账户数量
    private List<String> buyerNames;    //融资商开户时的买方名称列表

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public List<String> getBuyerNames() {
        return buyerNames;
    }
    public void setBuyerNames(List<String> buyerNames) {
        this.buyerNames = buyerNames;
    }
}
