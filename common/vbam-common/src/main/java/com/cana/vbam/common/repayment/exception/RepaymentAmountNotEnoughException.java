package com.cana.vbam.common.repayment.exception;

/**
 * 还款金额不足
 * 
 * @author XuMeng
 *
 */
public class RepaymentAmountNotEnoughException extends Exception {
	static final long serialVersionUID = -7034897190745766939L;

	private long differenceAmount; // 差额，正整数

	public RepaymentAmountNotEnoughException(long differenceAmount) {
		super();
		this.differenceAmount = differenceAmount;
	}

	public RepaymentAmountNotEnoughException(String message, long differenceAmount) {
		super(message);
		this.differenceAmount = differenceAmount;
	}

	public long getDifferenceAmount() {
		return differenceAmount;
	}

}