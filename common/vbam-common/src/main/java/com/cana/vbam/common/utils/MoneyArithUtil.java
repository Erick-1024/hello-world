package com.cana.vbam.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
public class MoneyArithUtil
{
	/**
	 * 转化金钱，以分为单位
	 * @param money
	 * @return
	 */
	public static Long convertStringToMoney(String money)
	{
		if(StringUtils.isBlank(money))
			return null;
		BigDecimal bigMoney = round(mul(new BigDecimal(money),new BigDecimal(100)), 0);
		return bigMoney.longValue();
	}
	
	public static BigDecimal convertStringToMoneyBigDecimal(String money)
	{
		if(StringUtils.isBlank(money))
			return null;
		BigDecimal bigMoney = mul(new BigDecimal(money),new BigDecimal(100));
		return bigMoney;
	}
	
	public static String convertMoneyToString(Long money){
		String value = divide(new BigDecimal(money == null ? 0 : money), new BigDecimal(100), 2).toString();
//		if(value.length()-value.indexOf(".") < 3){
//			value = value + "0";
//		}
		return value;
	}
	
	/**
	 * 转化利率:
	 * 10.1% to 0.101
	 * 10.1 to 0.101
	 */
	public static BigDecimal convertStringToInterestRate(String interestRate){
		if(StringUtils.isBlank(interestRate)){
			return null;
		}
		double doubleIn;
		if(-1 != interestRate.indexOf("%")){
			doubleIn = Double.parseDouble(interestRate.substring(0,interestRate.length()-1));
		}else {
			doubleIn = Double.parseDouble(interestRate);
		}
		BigDecimal bigDecimal = new BigDecimal(doubleIn).divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_UP);
		return bigDecimal;
	}
	
	/**
	 * 转化利率
	 * 0.101 to 10.100%
	 * @param interestRate
	 */
	public static String convertInterestRateToString(BigDecimal interestRate){
		String value = round(mul(interestRate, new BigDecimal(100)), 3).toString();
		if(value.length()-value.indexOf(".") < 3){
			value = value + "0";
		}
		return value + "%";
	}
	
	/**
	 * 数相加
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal add(BigDecimal num1, BigDecimal num2){
		return num1.add(num2);
	}
	
	/**
	 * 数相加
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal add(long num1, BigDecimal num2){
		return add(new BigDecimal(num1), num2);
	}
	
	/**
	 * double 数相减
	 * @param minuend
	 * @param subtrahend
	 * @return
	 */
	public static BigDecimal sub(BigDecimal minuend, BigDecimal subtrahend){
		return minuend.subtract(subtrahend);
	}
	
	/**
	 * double 数相乘
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal num1, BigDecimal num2){
		return num1.multiply(num2);
	}
	
	/**
	 * 两数相乘
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal mul(Long num1, BigDecimal num2){
		return mul(new BigDecimal(num1), num2);
	}
	
	/**
	 * 两数相乘
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal num1, Long num2){
		return mul(num1, new BigDecimal(num2));
	}
	
	/**
	 * 两数相乘
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal mul(int num1, BigDecimal num2){
		return mul(new BigDecimal(num1), num2);
	}
	
	/**
	 * 除法, 四舍五入
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale){
		return dividend.divide(divisor, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 除法, 四舍五入
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static BigDecimal divide(long dividend, BigDecimal divisor, int scale){
		return divide(new BigDecimal(dividend), divisor, scale);
	}
	
	/**
	 * @param num
	 * @param len
	 * @return
	 */
	public static BigDecimal round(BigDecimal num, int len){
		return num.setScale(len, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * 四舍五入
	 * @param num
	 * @param len
	 * @return
	 */
	public static BigDecimal round(double num,int len) {     
		return round(new BigDecimal(num), len);
	}
	
	public static BigDecimal roundUp(BigDecimal num, int len){
		return num.setScale(len, BigDecimal.ROUND_UP);
	}
	
	public static BigDecimal roundUp(double num, int len){
		return roundUp(new BigDecimal(num), len);
	}
	
	/**
	 * Long 相加
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Long addLong(Long value1,Long value2)
	{
		Long sum = new Long(0);
		sum = (value1 == null ? 0 : value1) + (value2 == null ? 0 : value2);
		return sum;
	}
	
	/**
	 * Long 相减
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Long minusLong(Long value1,Long value2)
	{
		Long minus = new Long(0);
		minus = (value1 == null ? 0 : value1) - (value2 == null ? 0 : value2);
		return minus;
	}
	
	/**
	 * double数字格式化输出
	 * @param value
	 * @return
	 */
	public static String doubleFormat(double value) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");//格式化设置
		return decimalFormat.format(value);
	}
	
	/**
	 * 将利率精确到小数点后5位
	 * @param num
	 * @return
	 */
	public static BigDecimal roundInterestRate(BigDecimal num){
		return round(num, 5);
	}
	
	/**
	 * 对传入的利率除以100并四舍五入
	 * @param num
	 * @return
	 */
	public static BigDecimal divideInterestRateBy100AndRound(BigDecimal num){
		return roundInterestRate(num.divide(new BigDecimal(100), 16, RoundingMode.HALF_UP));
	}
	
}
