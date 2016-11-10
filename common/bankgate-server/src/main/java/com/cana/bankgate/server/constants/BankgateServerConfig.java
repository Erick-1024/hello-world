/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.constants;

/**
 * @author ducer
 *
 */
public class BankgateServerConfig {

  /*----------------     以下为请求配置项        ----------------------*/

  private String requestCharset = "gbk";// XML请求编码

  private String responseCharset = "gb2312";// 响应结果编码

  private String contentType = "text/xml"; //

  private String url;// 银行服务器转发私服地址

  private long connectionTimeout = 5000;// 连接超时

  private int timeout = 5000; // 响应超时

  private int scheduleTime = 1000 * 60 * 2;// 定时任务定时时间

  /*---------         以下为银行参数配置项             ------------------*/

  private String bankUserName; // 登录名

  private String mainAccountNo;// 主帐号

  private String memberConfirmCenter;// 会员确认中心

  private String appFlag;// 应用系统， 2：B2B电子商务；3：投标保证金

  private String accountGenerateType;// 附属账户生成方式 ，0：自动输入 ；1：手动生成

  private String accountType; // 附属账户类型，03：一般交易账号；04：保证金账号；11：招投标保证金

  private String calculateInterestFlag; // 计算利息标志， 0：不计息；1：不分段计息；2：分段计息；当appFlag为3时，是否计算利息标志必须为0

  private String overdraftFlag;// 是否允许透支，0：不允许；1：限额透支；2：全额透支 ；appFlag为3时，必须为0

  private String autoAssignInterestFlag;// 自动分配利息标示，0：否；1：是；appFlag为3时，必须为0

  private String autoAssignTranFeeFlag;// 自动分配转账手续费标，0：否；1：是；appFlag为3时，必须为0

  private String feeType;// 手续费收取方式，0：不收取；1：实时收取；2：月末累计；appFlag为3时，必须为0

  private String realNameFlag;// 实名制更换，0：账户名与账号全不换；1：账户名与账号全换；2：换账户名；3：换账号；appFlag为3时，必须为0

  private String accountPrintFlag; // 附属账户凭证打印更换，0：全部显示；1：显示附属账户名和账号；2：显示实体账户名和账号；3：显示附属账户名和实体账号；4：显示实体账户名和附属账号；appFlag为3时，必须为0

  private String tranFlag;// 转账时效标识char (1)，0：异步交易；1：同步交易

  public String getRequestCharset() {
    return requestCharset;
  }
  public void setRequestCharset(String requestCharset) {
    this.requestCharset = requestCharset;
  }
  public String getResponseCharset() {
    return responseCharset;
  }
  public void setResponseCharset(String responseCharset) {
    this.responseCharset = responseCharset;
  }
  public String getContentType() {
    return contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public long getConnectionTimeout() {
    return connectionTimeout;
  }
  public void setConnectionTimeout(long connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }
  public int getTimeout() {
    return timeout;
  }
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }
  public int getScheduleTime() {
    return scheduleTime;
  }
  public void setScheduleTime(int scheduleTime) {
    this.scheduleTime = scheduleTime;
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
  public String getMemberConfirmCenter() {
    return memberConfirmCenter;
  }
  public void setMemberConfirmCenter(String memberConfirmCenter) {
    this.memberConfirmCenter = memberConfirmCenter;
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
  public String getOverdraftFlag() {
    return overdraftFlag;
  }
  public void setOverdraftFlag(String overdraftFlag) {
    this.overdraftFlag = overdraftFlag;
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
  public String getTranFlag() {
    return tranFlag;
  }
  public void setTranFlag(String tranFlag) {
    this.tranFlag = tranFlag;
  }
}
