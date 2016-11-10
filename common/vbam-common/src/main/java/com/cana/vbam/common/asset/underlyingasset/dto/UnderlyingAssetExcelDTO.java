package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;

/**
 * 基础资产excel导入类
 * 
 * @author XuMeng
 *
 */
public class UnderlyingAssetExcelDTO extends EditUnderlyingAssetRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String settleStatusFlag; // 结清标示
	private String interestRateUnitDesc; // 利率类型
	private String forwardFlag; // 提前还款标示
	private String overdueFlag; // 展期标示
	private String extensionFlag; // 违约标示
	private String forwardDaysStr;
	private String overdueDaysStr;
	private String extensionDaysStr;
	private String checkFailedMessage; // 失败原因

	public String getSettleStatusFlag() {
		return settleStatusFlag;
	}

	public void setSettleStatusFlag(String settleStatusFlag) {
		this.settleStatusFlag = settleStatusFlag;
	}

	public String getForwardFlag() {
		return forwardFlag;
	}

	public void setForwardFlag(String forwardFlag) {
		this.forwardFlag = forwardFlag;
	}

	public String getOverdueFlag() {
		return overdueFlag;
	}

	public void setOverdueFlag(String overdueFlag) {
		this.overdueFlag = overdueFlag;
	}

	public String getExtensionFlag() {
		return extensionFlag;
	}

	public void setExtensionFlag(String extensionFlag) {
		this.extensionFlag = extensionFlag;
	}

	public String getCheckFailedMessage() {
		return checkFailedMessage;
	}

	public void setCheckFailedMessage(String checkFailedMessage) {
		this.checkFailedMessage = checkFailedMessage;
	}

	public String getForwardDaysStr() {
		return forwardDaysStr;
	}

	public void setForwardDaysStr(String forwardDaysStr) {
		this.forwardDaysStr = forwardDaysStr;
	}

	public String getOverdueDaysStr() {
		return overdueDaysStr;
	}

	public void setOverdueDaysStr(String overdueDaysStr) {
		this.overdueDaysStr = overdueDaysStr;
	}

	public String getExtensionDaysStr() {
		return extensionDaysStr;
	}

	public void setExtensionDaysStr(String extensionDaysStr) {
		this.extensionDaysStr = extensionDaysStr;
	}

	public String getInterestRateUnitDesc() {
		return interestRateUnitDesc;
	}

	public void setInterestRateUnitDesc(String interestRateUnitDesc) {
		this.interestRateUnitDesc = interestRateUnitDesc;
	}

}
