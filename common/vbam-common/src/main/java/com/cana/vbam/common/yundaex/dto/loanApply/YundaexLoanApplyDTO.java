/**
 * 
 */
package com.cana.vbam.common.yundaex.dto.loanApply;

import java.io.Serializable;
import java.util.List;

/**
 * 韵达项目-韵达客户申请DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexLoanApplyDTO implements Serializable {

	private static final long serialVersionUID = 2344960384046047588L;

	/**
	 * 用款申请时间
	 */
	private String applyDate;

	/**
	 * 放款日期
	 */
	private String loanDate;

	/**
	 * 站点编号
	 */
	private String stationNo;

	/**
	 * 站点负责人/公司名称
	 */
	private String stationMgr;

	/**
	 * 站点名称
	 */
	private String stationName;

	/**
	 * 经营地址 （省+市+详细地址）
	 */
	private String detailAddress;

	/**
	 * 法人
	 */
	private String legalPerson;

	/**
	 * 页面产品信息
	 */
	private List<YundaexProductDTO> yundaexProductDTOs;

	/**
	 * 总额度，精确到分
	 */
	private String totalLimit;

	/**
	 * 未使用额度，精确到分
	 */
	private String notUsedLimit;

	public List<YundaexProductDTO> getYundaexProductDTOs() {
		return yundaexProductDTOs;
	}

	public void setYundaexProductDTOs(List<YundaexProductDTO> yundaexProductDTOs) {
		this.yundaexProductDTOs = yundaexProductDTOs;
	}

	public String getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getNotUsedLimit() {
		return notUsedLimit;
	}

	public void setNotUsedLimit(String notUsedLimit) {
		this.notUsedLimit = notUsedLimit;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationMgr() {
		return stationMgr;
	}

	public void setStationMgr(String stationMgr) {
		this.stationMgr = stationMgr;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

}
