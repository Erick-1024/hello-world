package com.cana.vbam.common.yundaex.dto.loanInfo;

import java.io.Serializable;

public class YundaexRepaymentResultDTO implements Serializable {

	private static final long serialVersionUID = -4313282169658937520L;

	// 站点编号
	private String stationNo;
	
	// 站点名称
	private String stationName;	
	
	// 借款人姓名
	private String custName;
	
	// 借款人身份证号
	private String custIdno;
	
	// 借据号 放款编号
	private String putoutno;
	
	// 出账金额 融资金额
	private long putoutamt;
	
	// 还款方式
	private String repaymode;
	
	// 还款金额
	private long repayamt;
	
	// 还款日期
	private String repaydate;
	
	// 签名
	private String sign;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdno() {
		return custIdno;
	}

	public void setCustIdno(String custIdno) {
		this.custIdno = custIdno;
	}

	public String getPutoutno() {
		return putoutno;
	}

	public void setPutoutno(String putoutno) {
		this.putoutno = putoutno;
	}

	public long getPutoutamt() {
		return putoutamt;
	}

	public void setPutoutamt(long putoutamt) {
		this.putoutamt = putoutamt;
	}

	public String getRepaymode() {
		return repaymode;
	}

	public void setRepaymode(String repaymode) {
		this.repaymode = repaymode;
	}

	public long getRepayamt() {
		return repayamt;
	}

	public void setRepayamt(long repayamt) {
		this.repayamt = repayamt;
	}

	public String getRepaydate() {
		return repaydate;
	}

	public void setRepaydate(String repaydate) {
		this.repaydate = repaydate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
