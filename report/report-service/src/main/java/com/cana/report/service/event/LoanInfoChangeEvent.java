package com.cana.report.service.event;

import com.cana.repayment.service.bo.RepaymentLoanInfoSnapshotBO;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;

public class LoanInfoChangeEvent {

	// 变更类型
	private LoanInfoChangeType type;
	// 变更之前的放款信息快照
	private RepaymentLoanInfoSnapshotBO oldSnapshot;
	// 变更之后的放款信息快照
	private RepaymentLoanInfoSnapshotBO newSnapshot;
	//变更当天的日期
	private String curDay;
	//变更当年的年份
	private String curYear;
	//变更前一天的日期
	private String lastDay;
	//变更前一年的年份
	private String lastYear;
	// 判定是否是自动生成的还款计划
	private boolean auto;

	public LoanInfoChangeType getType() {
		return type;
	}

	public void setType(LoanInfoChangeType type) {
		this.type = type;
	}

	public RepaymentLoanInfoSnapshotBO getOldSnapshot() {
		return oldSnapshot;
	}

	public void setOldSnapshot(RepaymentLoanInfoSnapshotBO oldSnapshot) {
		this.oldSnapshot = oldSnapshot;
	}

	public RepaymentLoanInfoSnapshotBO getNewSnapshot() {
		return newSnapshot;
	}

	public void setNewSnapshot(RepaymentLoanInfoSnapshotBO newSnapshot) {
		this.newSnapshot = newSnapshot;
	}

	public String getCurDay() {
		return curDay;
	}

	public void setCurDay(String curDay) {
		this.curDay = curDay;
	}

	public String getCurYear() {
		return curYear;
	}

	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}

	public String getLastYear() {
		return lastYear;
	}

	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
}
