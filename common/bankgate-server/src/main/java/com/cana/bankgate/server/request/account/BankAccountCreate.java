/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.account;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankAccountCreate extends BankBaseRequest{

  private static final long serialVersionUID = 9047079894755865233L;

  @XStreamAlias("userName")
  private String bankUserName;// 登录名varchar(30)
  
  @XStreamAlias("mainAccNo")
  private String mainAccountNo; // 主体账号char(19)
  
  @XStreamAlias("appFlag")
  private String appFlag;// 应用系统char(1)， 2：B2B电子商务；3：投标保证金
  
  @XStreamAlias("accGenType")
  private String accountGenerateType;// 附属账户生成方式char(1) ，0：自动输入 ；1：手动生成
  
  @XStreamAlias("subAccNo")
  private String accountNo;// 附属账号 char(14) ，在accGenType生成方式为1：手动输入时，必输；为0：自动生成时可空
  
  @XStreamAlias("subAccNm")
  private String accountName; // 附属账户名称 varchar(100)，可空，appFlag为2时必输，appFlag为3时可空，若不为空则其值必须为客户名称
  
  @XStreamAlias("accType")
  private String accountType;// 附属账户类型 char(2)，03：一般交易账号；04：保证金账号；11：招投标保证金
  
  @XStreamAlias("calInterestFlag")
  private String calculateInterestFlag;// 计算利息标志char(1)，0：不计息；1：不分段计息；2：分段计息；当appFlag为3时，是否计算利息标志必须为0
  
  @XStreamAlias("interestRate")
  private String interestRate;// 默认计息利率 decimal(9.7)，calInterestFlag为 0时，可空；appFlag为3时，必须为0
  
  @XStreamAlias("overFlag")
  private String overdraftFlag; // 是否允许透支char(1)，0：不允许；1：限额透支；2：全额透支 ；appFlag为3时，必须为0
  
  @XStreamAlias("overAmt")
  private String overdraft;// 透支额度decimal(15.2)，当overFlag为 0时，可空；appFlag为3时，必须为空
  
  @XStreamAlias("overRate")
  private String overdraftRate; // 透支利率decimal(9.7)，当overFlag为 0时，可空；appFlag为3时，必须为空
  
  @XStreamAlias("autoAssignInterestFlag")
  private String autoAssignInterestFlag; // 自动分配利息标示char(1)，0：否；1：是；appFlag为3时，必须为0
  
  @XStreamAlias("autoAssignTranFeeFlag")
  private String autoAssignTranFeeFlag;// 自动分配转账手续费标char(1)，0：否；1：是；appFlag为3时，必须为0
  
  @XStreamAlias("feeType")
  private String feeType;// 手续费收取方式 char(1)，0：不收取；1：实时收取；2：月末累计；appFlag为3时，必须为0
  
  @XStreamAlias("realNameParm")
  private String realNameFlag;// 实名制更换char(1) ，0：账户名与账号全不换；1：账户名与账号全换；2：换账户名；3：换账号；appFlag为3时，必须为0
  
  @XStreamAlias("subAccPrintParm")
  private String accountPrintFlag;// 附属账户凭证打印更换char(1)，0：全部显示；1：显示附属账户名和账号；2：显示实体账户名和账号；3：显示附属账户名和实体账号；4：显示实体账户名和附属账号；appFlag为3时，必须为0
  
  @XStreamAlias("mngNode")
  private String memberConfirmCenter;// 会员确认中心char(6)
  
  public BankAccountCreate(BankBizType bankBizType) {
	super(bankBizType);
  }
  
  public String getBankUserName() {
    return bankUserName;
  }
  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }
  public String getMainAccountNo() {
    return mainAccountNo;
  }
  public void setMainAccountNo(String mainAccountNo) {
    this.mainAccountNo = mainAccountNo;
  }
  public String getAppFlag() {
    return appFlag;
  }
  public void setAppFlag(String appFlag) {
    this.appFlag = appFlag;
  }
  public String getAccountGenerateType() {
    return accountGenerateType;
  }
  public void setAccountGenerateType(String accountGenerateType) {
    this.accountGenerateType = accountGenerateType;
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
  public String getAccountType() {
    return accountType;
  }
  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
  public String getCalculateInterestFlag() {
    return calculateInterestFlag;
  }
  public void setCalculateInterestFlag(String calculateInterestFlag) {
    this.calculateInterestFlag = calculateInterestFlag;
  }
  public String getInterestRate() {
    return interestRate;
  }
  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }
  public String getOverdraftFlag() {
    return overdraftFlag;
  }
  public void setOverdraftFlag(String overdraftFlag) {
    this.overdraftFlag = overdraftFlag;
  }
  public String getOverdraft() {
    return overdraft;
  }
  public void setOverdraft(String overdraft) {
    this.overdraft = overdraft;
  }
  public String getOverdraftRate() {
    return overdraftRate;
  }
  public void setOverdraftRate(String overdraftRate) {
    this.overdraftRate = overdraftRate;
  }
  public String getAutoAssignInterestFlag() {
    return autoAssignInterestFlag;
  }
  public void setAutoAssignInterestFlag(String autoAssignInterestFlag) {
    this.autoAssignInterestFlag = autoAssignInterestFlag;
  }
  public String getAutoAssignTranFeeFlag() {
    return autoAssignTranFeeFlag;
  }
  public void setAutoAssignTranFeeFlag(String autoAssignTranFeeFlag) {
    this.autoAssignTranFeeFlag = autoAssignTranFeeFlag;
  }
  public String getFeeType() {
    return feeType;
  }
  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }
  public String getRealNameFlag() {
    return realNameFlag;
  }
  public void setRealNameFlag(String realNameFlag) {
    this.realNameFlag = realNameFlag;
  }
  public String getAccountPrintFlag() {
    return accountPrintFlag;
  }
  public void setAccountPrintFlag(String accountPrintFlag) {
    this.accountPrintFlag = accountPrintFlag;
  }
  public String getMemberConfirmCenter() {
    return memberConfirmCenter;
  }
  public void setMemberConfirmCenter(String memberConfirmCenter) {
    this.memberConfirmCenter = memberConfirmCenter;
  }
}
