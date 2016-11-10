/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.flow;

import java.io.Serializable;

import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class BankAccountTradeFlowData implements Serializable {

  private static final long serialVersionUID = 846688312750031717L;
  @XStreamAlias("subAccNo")
  private String accountNo; // 附属账号
  
  @XStreamAlias("CRYTYPE")
  private String currencyType; //币种，文档中没有，返回001代码人民币，目前平台只支持人民币，并且支持美元的业务还很远，所以这个字段舍弃
  
  @XStreamAlias("TRANTYPE")
  @XStreamConverter(BankEnumConverter.class)
  private BankTradeType bankTradeType; // 交易类型varchar(2)，值域参见附录4.8
  
  @XStreamAlias("TRANDATE")
  private String tradeDate; // 交易日期char(8)
  
  @XStreamAlias("TRANTIME")
  private String tradeTime; // 交易时间char(6)
  
  @XStreamAlias("HOSTFLW")
  private String operatorNo; // 柜员交易号varchar(14)
  
  @XStreamAlias("HOSTSEQ")
  private String tradeSeq; // 交易序号varchar(13)
  
  @XStreamAlias("OPPACCNO")
  private String oppositeAccountNo; // 对方账号varchar(19)
  
  @XStreamAlias("OPPACCNAME")
  private String oppositeAccountName; // 对方账户名称varchar(60)
  
  @XStreamAlias("OPPBRANCHNAME")
  private String oppositeBankName; // 对方开户行名称varchar(60)
  
  @XStreamAlias("OPPBANKNO")
  private String oppositeBankNo; // 对方支付联行号char(14)
  
  @XStreamAlias("CDFG")
  @XStreamConverter(BankEnumConverter.class)
  private DebitCreditTag debitCreditTag;// 借贷标志varchar (1) D：借，C：贷
  
  @XStreamAlias("TRANAMT")
  @XStreamConverter(BankAmountConverter.class)
  private Long amount; // 交易金额decimal(15,2)
  
  @XStreamAlias("ACCBAL")
  @XStreamConverter(BankAmountConverter.class)
  private Long accountBalance; // 账户余额decimal(15,2)
  
  @XStreamAlias("XTSFAM")
  @XStreamConverter(BankAmountConverter.class)
  private Long fee; // 手续费金额decimal(15,2)
  
  @XStreamAlias("RESUME")
  private String memo; // 摘要varchar(22)
  
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  public BankTradeType getBankTradeType() {
    return bankTradeType;
  }
  public void setBankTradeType(BankTradeType bankTradeType) {
    this.bankTradeType = bankTradeType;
  }
  public String getTradeDate() {
    return tradeDate;
  }
  public void setTradeDate(String tradeDate) {
    this.tradeDate = tradeDate;
  }
  public String getTradeTime() {
    return tradeTime;
  }
  public void setTradeTime(String tradeTime) {
    this.tradeTime = tradeTime;
  }
  public String getOperatorNo() {
    return operatorNo;
  }
  public void setOperatorNo(String operatorNo) {
    this.operatorNo = operatorNo;
  }
  public String getTradeSeq() {
    return tradeSeq;
  }
  public void setTradeSeq(String tradeSeq) {
    this.tradeSeq = tradeSeq;
  }
  public String getOppositeAccountNo() {
    return oppositeAccountNo;
  }
  public void setOppositeAccountNo(String oppositeAccountNo) {
    this.oppositeAccountNo = oppositeAccountNo;
  }
  public String getOppositeAccountName() {
    return oppositeAccountName;
  }
  public void setOppositeAccountName(String oppositeAccountName) {
    this.oppositeAccountName = oppositeAccountName;
  }
  public String getOppositeBankName() {
    return oppositeBankName;
  }
  public void setOppositeBankName(String oppositeBankName) {
    this.oppositeBankName = oppositeBankName;
  }
  public DebitCreditTag getDebitCreditTag() {
    return debitCreditTag;
  }
  public void setDebitCreditTag(DebitCreditTag debitCreditTag) {
    this.debitCreditTag = debitCreditTag;
  }
  public Long getAmount() {
    return amount;
  }
  public void setAmount(Long amount) {
    this.amount = amount;
  }
  public Long getAccountBalance() {
    return accountBalance;
  }
  public void setAccountBalance(Long accountBalance) {
    this.accountBalance = accountBalance;
  }
  public Long getFee() {
    return fee;
  }
  public void setFee(Long fee) {
    this.fee = fee;
  }
  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }
  public String getOppositeBankNo() {
    return oppositeBankNo;
  }
  public void setOppositeBankNo(String oppositeBankNo) {
    this.oppositeBankNo = oppositeBankNo;
  }
}
