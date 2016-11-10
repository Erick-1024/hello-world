package com.cana.vbam.common.vj.dto;

import java.util.Date;

import com.cana.vbam.common.dto.Pagination;
import com.cana.vbam.common.vj.enums.TranState;
import com.cana.vbam.common.vj.enums.TranType;
import com.travelzen.framework.spring.web.format.annotation.DateFormat;

/**
 * VJ交易明细查询条件
 * @author sugar
 *
 */
public class VJTranQueryDTO extends Pagination {

	private static final long serialVersionUID = -7202549967335999755L;
	
	private TranType tranType; //交易类型
	
	private String vjTranSeq; //vj的交易流水号
	
	private String canaTranSeq; //cana的交易流水号
	
	private String loanId; //放款信息ID
	
	private String customerName; //客户名称

	private String identityCardNo;//身份证号
	
	@DateFormat
	private Date tranStartDate; //交易开始日期
	
	@DateFormat
	private Date tranEndDate; //交易结束日期
	
	private TranState tranState;//交易状态

	private TranState payState; // 打款状态
	
	public TranType getTranType() {
		return tranType;
	}

	public void setTranType(TranType tranType) {
		this.tranType = tranType;
	}

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	public Date getTranStartDate() {
		return tranStartDate;
	}

	public void setTranStartDate(Date tranStartDate) {
		this.tranStartDate = tranStartDate;
	}

	public Date getTranEndDate() {
		return tranEndDate;
	}

	public void setTranEndDate(Date tranEndDate) {
		this.tranEndDate = tranEndDate;
	}

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

	public TranState getTranState() {
		return tranState;
	}

	public void setTranState(TranState tranState) {
		this.tranState = tranState;
	}

	public TranState getPayState() {
		return payState;
	}

	public void setPayState(TranState payState) {
		this.payState = payState;
	}
	
}
