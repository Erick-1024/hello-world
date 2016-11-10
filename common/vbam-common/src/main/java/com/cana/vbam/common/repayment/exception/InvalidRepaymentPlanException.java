package com.cana.vbam.common.repayment.exception;

/**
 * 无效的还款计划
 * 
 * @author XuMeng
 *
 */
public class InvalidRepaymentPlanException extends RuntimeException {
	static final long serialVersionUID = -7034897190745766939L;

	public InvalidRepaymentPlanException() {
		super();
	}

	public InvalidRepaymentPlanException(String message) {
		super(message);
	}

	public InvalidRepaymentPlanException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRepaymentPlanException(Throwable cause) {
		super(cause);
	}

}
