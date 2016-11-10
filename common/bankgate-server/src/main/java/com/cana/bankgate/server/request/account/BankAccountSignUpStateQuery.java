package com.cana.bankgate.server.request.account;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stream")
public class BankAccountSignUpStateQuery extends BankBaseRequest{

	private static final long serialVersionUID = 4410798612966414520L;
	
	@XStreamAlias("mainAccNo")
	private String mainAccountNo; // 主账户

	@XStreamAlias("subAccNo")
	private String accountNo; //账号

	@XStreamAlias("userName")
	private String bankUserName; //银行主账号用户名
	
	@XStreamAlias("stt")
	private String state; //状态
	
	@XStreamAlias("startDate")
	private String startTime; //开始时间
	
	@XStreamAlias("endDate")
	private String endTime; //结束时间
	
	public BankAccountSignUpStateQuery(BankBizType bankBizType) {
		super(bankBizType);
	}	

	public String getMainAccountNo() {
		return mainAccountNo;
	}

	public void setMainAccountNo(String mainAccountNo) {
		this.mainAccountNo = mainAccountNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
}
