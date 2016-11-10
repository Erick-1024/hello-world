/**
 * 
 */
package com.cana.vbam.common.repayment.enums;

/**
 * 利率单位枚举，年、月、日。
 * @author dev3
 *
 */
public enum InterestRateUnit
{
	YEAR("年"),
	MONTH("月"),
	DAY("日");
	
	private String desc;
	
	private InterestRateUnit(String desc)
	{
		this.desc = desc;
	}
	
	public String desc()
	{
		return desc;
	}
	
	public static InterestRateUnit getValue(String desc)
	{
		if(YEAR.desc.equals(desc))
			return YEAR;
		if(MONTH.desc.equals(desc))
			return MONTH;
		if(DAY.desc.equals(desc))
			return DAY;
		return null;
	}
	
}
