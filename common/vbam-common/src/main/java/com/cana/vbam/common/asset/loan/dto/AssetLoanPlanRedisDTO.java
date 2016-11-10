package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hu
 *
 */
public class AssetLoanPlanRedisDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5081758047936439308L;

	private List<AssetLoanPlanExcelDTO> loanPlanExcelList;
	
	private List<AssetLoanPlanExcelDTO> passLoanPlanExcelList;
	
	private List<AssetLoanPlanExcelDTO> NotPassLoanPlanExcelList;

	public List<AssetLoanPlanExcelDTO> getLoanPlanExcelList() {
		return loanPlanExcelList;
	}

	public void setLoanPlanExcelList(List<AssetLoanPlanExcelDTO> loanPlanExcelList) {
		this.loanPlanExcelList = loanPlanExcelList;
	}

	public List<AssetLoanPlanExcelDTO> getPassLoanPlanExcelList() {
		return passLoanPlanExcelList;
	}

	public void setPassLoanPlanExcelList(List<AssetLoanPlanExcelDTO> passLoanPlanExcelList) {
		this.passLoanPlanExcelList = passLoanPlanExcelList;
	}

	public List<AssetLoanPlanExcelDTO> getNotPassLoanPlanExcelList() {
		return NotPassLoanPlanExcelList;
	}

	public void setNotPassLoanPlanExcelList(List<AssetLoanPlanExcelDTO> notPassLoanPlanExcelList) {
		for(AssetLoanPlanExcelDTO loanPlanExcelDTO : notPassLoanPlanExcelList){
			if(StringUtils.isBlank(loanPlanExcelDTO.getCheckFailedMessage()))
				loanPlanExcelDTO.setCheckFailedMessage("同一批次的还款计划中某一期存在错误");
		}
		NotPassLoanPlanExcelList = notPassLoanPlanExcelList;
	}
}
