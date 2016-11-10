package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;

/**
 * 流水明细查询条件
 * @author TangYihong
 *
 */
public class AccountTradeRecordCriteria implements Serializable{

	private static final long serialVersionUID = 7954298036497783163L;
	
	private int pageSize;  //每页显示行数
	private int page = 1;  //页码
	private String accountName;  //账户名称
	private String accountNo;  //银行账号
	private AccountTradeType tradeType;  //交易类型
	private String oppositeAccountName;  //交易户名
	private String oppositeAccountNo;  //交易账号
	private AccountType accountType;  //账户类型
	private String startTime;  //交易开始时间，yyyy-MM-dd
    private String endTime; //交易结束时间，yyyy-MM-dd
    private String businessSeq;//业务流水号
    //收支明细need
    private String accountId;//账户id
    // 以下字段是account－dao生成sql时需要的
    // 前端调account－api接口时不需要传
    private String factorId;  //保理商
    private String finaceId;  //融资商
    private String coreCompanyId; //核心企业
    private List<AccountTradeType> tradeTypes;  //交易类型
    
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public AccountTradeType getTradeType() {
		return tradeType;
	}
	public void setTradeType(AccountTradeType tradeType) {
		this.tradeType = tradeType;
	}
	public String getOppositeAccountName() {
		return oppositeAccountName;
	}
	public void setOppositeAccountName(String oppositeAccountName) {
		this.oppositeAccountName = oppositeAccountName;
	}
	public String getOppositeAccountNo() {
		return oppositeAccountNo;
	}
	public void setOppositeAccountNo(String oppositeAccountNo) {
		this.oppositeAccountNo = oppositeAccountNo;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
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
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getFinaceId() {
		return finaceId;
	}
	public void setFinaceId(String finaceId) {
		this.finaceId = finaceId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public List<AccountTradeType> getTradeTypes() {
		return tradeTypes;
	}
	public void setTradeTypes(List<AccountTradeType> tradeTypes) {
		this.tradeTypes = tradeTypes;
	}
	public String getCoreCompanyId() {
		return coreCompanyId;
	}
	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}
	public String getBusinessSeq() {
		return businessSeq;
	}
	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}

}
