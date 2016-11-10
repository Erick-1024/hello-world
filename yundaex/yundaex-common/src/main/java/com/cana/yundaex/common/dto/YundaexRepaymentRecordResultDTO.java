package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class YundaexRepaymentRecordResultDTO extends YundaexBaseResponse implements Serializable{

	private static final long serialVersionUID = 4824503282135426666L;
	
	/**
	 * 站点编号
	 */
	private String stationNo;
	
	/**
	 * 记录数
	 */
	private int count;
	
	/**
	 * 放款信息列表
	 */
	private List<YundaexRepaymentRecordDTO> loanInfo = Lists.newArrayList();
	
	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<YundaexRepaymentRecordDTO> getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(List<YundaexRepaymentRecordDTO> loanInfo) {
		this.loanInfo = loanInfo;
	}
}
