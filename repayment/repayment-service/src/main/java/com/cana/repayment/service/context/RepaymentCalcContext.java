package com.cana.repayment.service.context;

import com.cana.vbam.common.repayment.enums.RepaymentMethod;

/**
 * 在还款方式计算过程中，传递上下文信息
 * 
 * @author XuMeng
 *
 */
public class RepaymentCalcContext {

	private String repaymentDate;
	private String repaymentCertificate; // 还款凭证，记录在还款明细汇总表中，如果是真旅退款还款时，传入的是真旅退订单ID
	private long appendFinanceAmount; // 追加融资金额，仅真旅项目使用
	private boolean prepareRepayment = false; // 预还款，当为true时，不进行任何持久化操作
	private RepaymentMethod repaymentMethod; // 还款方式，调用还款接口时不能为空
	private boolean useHolidayPolicy; // 是否使用节假日政策

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getRepaymentCertificate() {
		return repaymentCertificate;
	}

	public void setRepaymentCertificate(String repaymentCertificate) {
		this.repaymentCertificate = repaymentCertificate;
	}

	public long getAppendFinanceAmount() {
		return appendFinanceAmount;
	}

	public void setAppendFinanceAmount(long appendFinanceAmount) {
		this.appendFinanceAmount = appendFinanceAmount;
	}

	public boolean isPrepareRepayment() {
		return prepareRepayment;
	}

	public void setPrepareRepayment(boolean prepareRepayment) {
		this.prepareRepayment = prepareRepayment;
	}

	public RepaymentMethod getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(RepaymentMethod repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public boolean isUseHolidayPolicy() {
		return useHolidayPolicy;
	}

	public void setUseHolidayPolicy(boolean useHolidayPolicy) {
		this.useHolidayPolicy = useHolidayPolicy;
	}

}
