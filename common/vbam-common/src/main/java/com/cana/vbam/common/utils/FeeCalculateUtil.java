/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 手续费计算工具
 * 
 * @author ducer
 */
public class FeeCalculateUtil {

	public static final Long ten_thousand = 10 * 1000 * 100L; // 一万元
	public static final Long hundred_thousand = 100 * 1000 *100L; //十万元
	public static final Long five_hundred_thousand = 500 * 1000 * 100L; //五十万
	public static final Long million = 1000 * 1000 * 100L; //一百万
	
	/**
	 * 提现手续费：出入都为分
	 */
	public static Long withdrawFee(Long amount){
		if(amount < 0)throw new RuntimeException("非法数据，无法计算手续费");
		if(amount < ten_thousand)return 500L;
		if(amount < hundred_thousand)return 1000L;
		if(amount < five_hundred_thousand)return 1500L;
		if(amount < million)return 2000L;
		Long value = new BigDecimal(amount).multiply(new BigDecimal(0.00002)).setScale(0, RoundingMode.HALF_UP).longValue();
		return value > 200 * 100L?200 * 100L:value;
	}
	
	/**
	 * 提现手续费：出入都为元
	 */
	public static String withdrawFee(String amount){
		Long value = withdrawFee(com.travelzen.framework.core.util.MoneyUtil.yuan2Cent(amount));
		return com.travelzen.framework.core.util.MoneyUtil.cent2Yuan(value);
	}
}
