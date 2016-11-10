package com.cana.yundaex.common.enums;

/**
 * @author hu
 *
 */
public enum RepaymentMethod {

	ORDER("订单款回款还款", 0.60, RepaymentDeadLine.one_month),
	MONTHLY("按月支付收益到期还本", 0.70, RepaymentDeadLine.three_month),
	EQUALALL("等额本息", 1.00, RepaymentDeadLine.six_month),
	EQUALPRINCIPAL("等额本金", 1.00, RepaymentDeadLine.six_month);
	
	public static final double WEIGHT = 0.25;
	
	private String desc;
	
	private double ratio;
	
	private RepaymentDeadLine deadLine;
	
	private RepaymentMethod(String desc, double ratio, RepaymentDeadLine deadLine){
		this.desc = desc;
		this.ratio = ratio;
		this.deadLine = deadLine;
	}
	
	public String desc(){
		return desc;
	}
	
	public double ratio(){
		return ratio;
	}
	
	public RepaymentDeadLine deadLine(){
		return deadLine;
	}
	
	public static RepaymentMethod getValues(String name) {
		if(ORDER.name().equals(name))
			return ORDER;
		if(MONTHLY.name().equals(name))
			return MONTHLY;
		if(EQUALALL.name().equals(name))
			return EQUALALL;
		if(EQUALPRINCIPAL.name().equals(name))
			return EQUALPRINCIPAL;
		return null;
	}
}
