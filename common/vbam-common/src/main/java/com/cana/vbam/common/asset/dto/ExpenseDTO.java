package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.ExpenseType;

/**
 * @author hu
 *
 */
public class ExpenseDTO implements Serializable{
	
	private static final long serialVersionUID = 5903328497716094247L;

	private String id;

	private String refid;

	private ExpenseType reftype;

	private String expenseSubject;

	private Long amount;
	
	private String amountStr;

	private Date createTime;

	private Date updateTime;

	private Integer sequence;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRefid() {
		return refid;
	}
	
	public void setRefid(String refid) {
		this.refid = refid;
	}
	
	public ExpenseType getReftype() {
		return reftype;
	}
	
	public void setReftype(ExpenseType reftype) {
		this.reftype = reftype;
	}
	
	public String getExpenseSubject() {
		return expenseSubject;
	}
	
	public void setExpenseSubject(String expenseSubject) {
		this.expenseSubject = expenseSubject;
	}
	
	public Long getAmount() {
		return amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getSequence() {
		return sequence;
	}
	
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

}
