package com.cana.early.warning.service.utils;

import java.util.HashMap;
import java.util.Map;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.travelzen.framework.config.tops.TopsConfReader;

public class YundaexEarlyWarningProperties {

	// 预警配置文件
	public final static String EARLY_WARNING_PRPPERTIES_FILE = "properties/yundaex-early-warning.properties";
	
	//---------------------------------------------------------------------------黄色预警-------------------------------------------------------------------------------------
	// 黄色预警-揽派件增长率闸值（不包含）
	public final static String YELLOW_RECANDSEND_GROWTHRATE = "yellow.recandsend_growthrate";
	
	// 黄色预警-保证金余额/日资金需求闸值（不包括）
	public final static String YELLOW_BAILBALANCE_DAY_REQUIREMENTS = "yellow.bailbalance_day_requirements";
	
	// 黄色预警-韵达评分闸值（不包含）
	public final static String YELLOW_YUNDAEXGRADE = "yellow.yundaexgrade";
	
	// 黄色预警-最大授信额度阀值（不包括）
	public final static String YELLOW_CREDIT_LIMIT_GROWTH = "yellow.credit_limit_growth";
	
	// 黄色预警-累计逾期次数下限(不包括)
	public final static String YELLOW_OVERDUEPLANS_LOWERLIMIT = "yellow.overdueplans.lowerlimit";
	
	// 黄色预警-累计逾期次数上限（不包括）
	public final static String YELLOW_OVERDUEPLANS_UPPERLIMIT = "yellow.overdueplans.upperlimit";
	
	//---------------------------------------------------------------------------红色预警-------------------------------------------------------------------------------------

	// 红色预警-累计逾期次数下限（包括）
	public final static String RED_OVERDUEPLANS_LOWERLIMIT = "red.overdueplans.lowerlimit";
	
	public final static int getIntFromEarlyWarningProperties(String key) {
		return TopsConfReader.getConfContentForInt(YundaexEarlyWarningProperties.EARLY_WARNING_PRPPERTIES_FILE, key, ConfScope.R);
	}
	
	public final static long getLongFromEarlyWarningPrperties(String key) {
		return TopsConfReader.getConfContentForLong(YundaexEarlyWarningProperties.EARLY_WARNING_PRPPERTIES_FILE, key, ConfScope.R);
	}

	public final static Map<String, Long> queryEarlyWarningStandard(EarlywarningLevel earlywarningLevel) {
		Map<String, Long> returnValue = new HashMap<>();
		switch (earlywarningLevel) {
		case yellow:
			returnValue.put(YELLOW_RECANDSEND_GROWTHRATE, getLongFromEarlyWarningPrperties(YELLOW_RECANDSEND_GROWTHRATE));
			returnValue.put(YELLOW_BAILBALANCE_DAY_REQUIREMENTS, getLongFromEarlyWarningPrperties(YELLOW_BAILBALANCE_DAY_REQUIREMENTS));
			returnValue.put(YELLOW_YUNDAEXGRADE, getLongFromEarlyWarningPrperties(YELLOW_YUNDAEXGRADE));
			returnValue.put(YELLOW_CREDIT_LIMIT_GROWTH, getLongFromEarlyWarningPrperties(YELLOW_CREDIT_LIMIT_GROWTH));
			returnValue.put(YELLOW_OVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(YELLOW_OVERDUEPLANS_LOWERLIMIT));
			returnValue.put(YELLOW_OVERDUEPLANS_UPPERLIMIT, getLongFromEarlyWarningPrperties(YELLOW_OVERDUEPLANS_UPPERLIMIT));
			break;
		case orange:
			break;
		case red:
			returnValue.put(RED_OVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(RED_OVERDUEPLANS_LOWERLIMIT));
			break;
		default:
			break;
		}
		return returnValue;
	}
}
