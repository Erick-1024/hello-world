/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankMainAccountBalanceQuery extends BankBaseRequest{

  private static final long serialVersionUID = 7626332857381111827L;

  @XStreamAlias("userName")
  private String bankUserName; // 登录名

  @XStreamAlias("list")
  private ListValue listValue = new ListValue();;

  public String getBankUserName() {
    return bankUserName;
  }

  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }
  
  public BankMainAccountBalanceQuery(BankBizType bankBizType) {
	super(bankBizType);
  }

  public List<String> getMainAccountNos() {
    List<String> mains = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(this.listValue.getMainAccountNos())) {
      for (MainAccountNo mainAccountNo : this.listValue.getMainAccountNos()) {
        mains.add(mainAccountNo.getMainAccountNo());
      }
    }
    return mains;
  }

  public void setMainAccountNos(List<String> mainAccountNos) {
    List<MainAccountNo> mains = new ArrayList<>();
    for (String mainAccountNo : mainAccountNos) {
      mains.add(new MainAccountNo(mainAccountNo));
    }
    this.listValue.setMainAccountNos(mains);
  }

  @XStreamAlias("list")
  public static class ListValue {

    @XStreamAsAttribute
    private String name = "userDataList";

    @XStreamImplicit(itemFieldName = "row")
    private List<MainAccountNo> mainAccountNos; // 主账号集合

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<MainAccountNo> getMainAccountNos() {
      return mainAccountNos;
    }

    public void setMainAccountNos(List<MainAccountNo> mainAccountNos) {
      this.mainAccountNos = mainAccountNos;
    }
  }

  @XStreamAlias("row")
  public static class MainAccountNo {
    @XStreamAlias("accountNo")
    private String mainAccountNo;

    public MainAccountNo(String mainAccountNo) {
      this.mainAccountNo = mainAccountNo;
    }

    public String getMainAccountNo() {
      return mainAccountNo;
    }

    public void setMainAccountNo(String mainAccountNo) {
      this.mainAccountNo = mainAccountNo;
    }
  }
}
