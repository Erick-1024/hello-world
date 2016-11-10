/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.io.Serializable;

/**
 * 放款信息通知接口
 * 
 * @author guguanggong
 *
 */
public class YundaexLoanInfoResponse implements Serializable {

	private static final long serialVersionUID = 876805940208974685L;

	/**
	 * 站点编号
	 */
	private String stationNo;

	/**
	 * 站点名称
	 */
	private String stationName;
	/**
	 * 借款人姓名
	 */
	private String custName;
	/**
	 * 借款人身份证号
	 */
	private String custIdno;

	/**
	 * 借据号
	 */
	private String putoutno;

	/**
	 * 出账金额
	 */
	private Long putoutamt;

	/**
	 * 开始日期
	 */
	private String begindate;

	/**
	 * 结束日期
	 */
	private String enddate;
	/**
	 * 签名
	 */
	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

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

	public Long getPutoutamt() {
		return putoutamt;
	}

	public void setPutoutamt(Long putoutamt) {
		this.putoutamt = putoutamt;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}
