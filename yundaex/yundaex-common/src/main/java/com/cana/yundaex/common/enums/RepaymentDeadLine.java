package com.cana.yundaex.common.enums;

/**
 * @author hu
 *
 */
public enum RepaymentDeadLine {

	one_month("1", "月", 1.00),
	three_month("3", "月", 0.85),
	six_month("6", "月", 0.70);
	
	public static final double WEIGHT = 0.25;
	
	private String desc;
	private String unit;
	
	private double ratio;
	
	private RepaymentDeadLine(String desc, String unit, double ratio){
		this.desc = desc;
		this.unit = unit;
		this.ratio = ratio;
	}
	
	public String desc(){
		return desc;
	}
	
	public String unit(){
		return unit;
	}
	
	public double ratio(){
		return ratio;
	}
}
