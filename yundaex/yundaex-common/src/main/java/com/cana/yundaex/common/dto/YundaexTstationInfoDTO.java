/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 韵达项目 网点数据DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexTstationInfoDTO implements Serializable {
	private static final long serialVersionUID = 8437813053279051936L;

	/**
	 * 主键。记录ID。唯一。
	 */
	private Integer id;

	/**
	 * 网点id，唯一
	 */
	private String stationNo;

	/**
	 * 统计年月，yyyy-MM
	 */
	private String statmonth;

	/**
	 * 揽件总数
	 */
	private Integer receiveTotal;

	/**
	 * 日平均揽件数
	 */
	private BigDecimal avgReceiveNum;

	/**
	 * 已签收总数（揽件）
	 */
	private Integer receiveSumSigned;

	/**
	 * 未签收总数（揽件）
	 */
	private Integer receiveSumUnsign;

	/**
	 * 派件总数
	 */
	private Integer sendTotal;

	/**
	 * 日平均到件数
	 */
	private BigDecimal avgSendNum;

	/**
	 * 已签收总数（派件）
	 */
	private Integer sendSumSigned;

	/**
	 * 未签收总数（派件）
	 */
	private Integer sendSumUnsign;

	/**
	 * 揽件派件月差额
	 */
	private Integer recSendDif;

	/**
	 * 揽派比
	 */
	private BigDecimal recSendRatio;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStatmonth() {
		return statmonth;
	}

	public void setStatmonth(String statmonth) {
		this.statmonth = statmonth;
	}

	public Integer getReceiveTotal() {
		return receiveTotal;
	}

	public void setReceiveTotal(Integer receiveTotal) {
		this.receiveTotal = receiveTotal;
	}

	public Integer getReceiveSumSigned() {
		return receiveSumSigned;
	}

	public void setReceiveSumSigned(Integer receiveSumSigned) {
		this.receiveSumSigned = receiveSumSigned;
	}

	public Integer getReceiveSumUnsign() {
		return receiveSumUnsign;
	}

	public void setReceiveSumUnsign(Integer receiveSumUnsign) {
		this.receiveSumUnsign = receiveSumUnsign;
	}

	public Integer getSendTotal() {
		return sendTotal;
	}

	public void setSendTotal(Integer sendTotal) {
		this.sendTotal = sendTotal;
	}

	public Integer getSendSumSigned() {
		return sendSumSigned;
	}

	public void setSendSumSigned(Integer sendSumSigned) {
		this.sendSumSigned = sendSumSigned;
	}

	public Integer getSendSumUnsign() {
		return sendSumUnsign;
	}

	public void setSendSumUnsign(Integer sendSumUnsign) {
		this.sendSumUnsign = sendSumUnsign;
	}

	public Integer getRecSendDif() {
		return recSendDif;
	}

	public void setRecSendDif(Integer recSendDif) {
		this.recSendDif = recSendDif;
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

	public BigDecimal getAvgReceiveNum() {
		return avgReceiveNum;
	}

	public void setAvgReceiveNum(BigDecimal avgReceiveNum) {
		this.avgReceiveNum = avgReceiveNum;
	}

	public BigDecimal getRecSendRatio() {
		return recSendRatio;
	}

	public void setRecSendRatio(BigDecimal recSendRatio) {
		this.recSendRatio = recSendRatio;
	}

	public BigDecimal getAvgSendNum() {
		return avgSendNum;
	}

	public void setAvgSendNum(BigDecimal avgSendNum) {
		this.avgSendNum = avgSendNum;
	}

}
