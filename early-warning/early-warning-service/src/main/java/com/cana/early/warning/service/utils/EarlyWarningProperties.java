package com.cana.early.warning.service.utils;

import java.util.HashMap;
import java.util.Map;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.travelzen.framework.config.tops.TopsConfReader;

public class EarlyWarningProperties {

	// 预警配置文件
	public final static String EARLY_WARNING_PRPPERTIES_FILE = "properties/early-warning.properties";
	
	//---------------------------------------------------------------------------常规配置-------------------------------------------------------------------------------------
	// 统计累计逾期次数的月数
	public final static String OVERDUEPLANS_MONTH = "overdueplans.month";
	
	// 统计连续逾期次数的月数
	public final static String CONTINUEOVERDUEPLANS_MONTH = "continueoverdueplans.month";
	
	// 法院被执行（企业）统计月份
	public final static String COURTEXECUTIONCOMPANY_MONTH= "courtexecutioncompany.month";
	
	// 法院被执行（企业）次数阀值
	public final static String COURTEXECUTIONCOMPANY_COUNT_THRESHOLD = "courtexecutioncompany.count.threshold";
	
	// 法院被执行（企业）金额阀值
	public final static String COURTEXECUTIONCOMPANY_MONEY_THRESHOLD = "courtexecutioncompany.money.threshold";
	
	// 法院被执行（个人）统计月份
	public final static String COURTEXECUTIONINDIVIDUAL_MONTH= "courtexecutionindividual.month";
	
	// 法院被执行（个人）次数阀值
	public final static String COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD = "courtexecutionindividual.count.threshold";
	
	// 法院被执行（个人）金额阀值
	public final static String COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD = "courtexecutionindividual.money.threshold";

	//---------------------------------------------------------------------------黄色预警-------------------------------------------------------------------------------------
	// 黄色预警-质押反担保覆盖率统计天数
	public final static String YELLOW_COUNTERGURANTEERATE_DAY = "yellow.counterguranteerate.day";
	
	// 黄色预警-质押反担保覆盖率阀值（不包括）
	public final static String YELLOW_COUNTERGURANTEERATE_THRESHOLD = "yellow.counterguranteerate.threshold";
	
	// 黄色预警-销售变化率统计天数
	public final static String YELLOW_SALESCHANGERATE_DAY = "yellow.saleschangerate.day";
	
	// 黄色预警-销售变化率阀值（不包括）
	public final static String YELLOW_SALESCHANGERATE_THRESHOLD = "yellow.saleschangerate.threshold";
	
	// 黄色预警-销售回款率统计天数
	public final static String YELLOW_SALESREPAYMENTRATE_DAY = "yellow.salesrepaymentrate.day";
	
	// 黄色预警-销售回款率阀值（不包括）
	public final static String YELLOW_SALESREPAYMENTRATE_THRESHOLD = "yellow.salesrepaymentrate.threshold";
	
	// 黄色预警-累计逾期次数下限(不包括)
	public final static String YELLOW_OVERDUEPLANS_LOWERLIMIT = "yellow.overdueplans.lowerlimit";
	
	// 黄色预警-累计逾期次数上限（包括）
	public final static String YELLOW_OVERDUEPLANS_UPPERLIMIT = "yellow.overdueplans.upperlimit";
	
	// 黄色预警-连续逾期次数下限（不包括）
	public final static String YELLOW_CONTINUEOVERDUEPLANS_LOWERLIMIT = "yellow.continueoverdueplans.lowerlimit";
	
	// 黄色预警-连续逾期次数上限（包括）
	public final static String YELLOW_CONTINUEOVERDUEPLANS_UPPERLIMIT = "yellow.continueoverdueplans.upperlimit";
	
	//---------------------------------------------------------------------------橙色预警-------------------------------------------------------------------------------------
	// 橙色预警-质押反担保覆盖率统计天数
	public final static String ORANGE_COUNTERGURANTEERATE_DAY = "orange.counterguranteerate.day";
	
	// 橙色预警-质押反担保覆盖率阀值（不包括）
	public final static String ORANGE_COUNTERGURANTEERATE_THRESHOLD = "orange.counterguranteerate.threshold";
	
	// 橙色预警-销售变化率统计天数
	public final static String ORANGE_SALESCHANGERATE_DAY = "orange.saleschangerate.day";
	
	// 橙色预警-销售变化率阀值（不包括）
	public final static String ORANGE_SALESCHANGERATE_THRESHOLD = "orange.saleschangerate.threshold";
	
	// 橙色预警-销售回款率统计天数
	public final static String ORANGE_SALESREPAYMENTRATE_DAY = "orange.salesrepaymentrate.day";
	
	// 橙色预警-销售回款率阀值（不包括）
	public final static String ORANGE_SALESREPAYMENTRATE_THRESHOLD = "orange.salesrepaymentrate.threshold";
	
	// 橙色预警-累计逾期次数下限(不包括)
	public final static String ORANGE_OVERDUEPLANS_LOWERLIMIT = "orange.overdueplans.lowerlimit";
	
	// 橙色预警-累计逾期次数上限（包括）
	public final static String ORANGE_OVERDUEPLANS_UPPERLIMIT = "orange.overdueplans.upperlimit";
	
	// 橙色预警-连续逾期次数下限（不包括）
	public final static String ORANGE_CONTINUEOVERDUEPLANS_LOWERLIMIT = "orange.continueoverdueplans.lowerlimit";
	
	// 橙色预警-连续逾期次数上限（包括）
	public final static String ORANGE_CONTINUEOVERDUEPLANS_UPPERLIMIT = "orange.continueoverdueplans.upperlimit";

	//---------------------------------------------------------------------------红色预警-------------------------------------------------------------------------------------
	// 红色预警-质押反担保覆盖率统计天数
	public final static String RED_COUNTERGURANTEERATE_DAY = "red.counterguranteerate.day";
	
	// 红色预警-质押反担保覆盖率阀值（不包括）
	public final static String RED_COUNTERGURANTEERATE_THRESHOLD = "red.counterguranteerate.threshold";
	
	// 红色预警-销售变化率统计天数
	public final static String RED_SALESCHANGERATE_DAY = "red.saleschangerate.day";
	
	// 红色预警-销售变化率阀值（不包括）
	public final static String RED_SALESCHANGERATE_THRESHOLD = "red.saleschangerate.threshold";
	
	// 红色预警-销售回款率统计天数
	public final static String RED_SALESREPAYMENTRATE_DAY = "red.salesrepaymentrate.day";
	
	// 红色预警-销售回款率阀值（不包括）
	public final static String RED_SALESREPAYMENTRATE_THRESHOLD = "red.salesrepaymentrate.threshold";
	
	// 红色预警-累计逾期次数下限（不包括）
	public final static String RED_OVERDUEPLANS_LOWERLIMIT = "red.overdueplans.lowerlimit";
	
	// 红色预警-连续逾期次数下限（不包括）
	public final static String RED_CONTINUEOVERDUEPLANS_LOWERLIMIT = "red.continueoverdueplans.lowerlimit";


	public final static int getIntFromEarlyWarningProperties(String key) {
		return TopsConfReader.getConfContentForInt(EarlyWarningProperties.EARLY_WARNING_PRPPERTIES_FILE, key, ConfScope.R);
	}
	
	public final static long getLongFromEarlyWarningPrperties(String key) {
		return TopsConfReader.getConfContentForLong(EarlyWarningProperties.EARLY_WARNING_PRPPERTIES_FILE, key, ConfScope.R);
	}

	public final static Map<String, Long> queryEarlyWarningStandard(EarlywarningLevel earlywarningLevel) {
		Map<String, Long> returnValue = new HashMap<>();
		returnValue.put(OVERDUEPLANS_MONTH, getLongFromEarlyWarningPrperties(OVERDUEPLANS_MONTH));
		returnValue.put(CONTINUEOVERDUEPLANS_MONTH, getLongFromEarlyWarningPrperties(CONTINUEOVERDUEPLANS_MONTH));
		switch (earlywarningLevel) {
		case yellow:
			returnValue.put(YELLOW_COUNTERGURANTEERATE_DAY, getLongFromEarlyWarningPrperties(YELLOW_COUNTERGURANTEERATE_DAY));
			returnValue.put(YELLOW_COUNTERGURANTEERATE_THRESHOLD, getLongFromEarlyWarningPrperties(YELLOW_COUNTERGURANTEERATE_THRESHOLD));
			returnValue.put(YELLOW_SALESCHANGERATE_DAY, getLongFromEarlyWarningPrperties(YELLOW_SALESCHANGERATE_DAY));
			returnValue.put(YELLOW_SALESCHANGERATE_THRESHOLD, getLongFromEarlyWarningPrperties(YELLOW_SALESCHANGERATE_THRESHOLD));
			returnValue.put(YELLOW_SALESREPAYMENTRATE_DAY, getLongFromEarlyWarningPrperties(YELLOW_SALESREPAYMENTRATE_DAY));
			returnValue.put(YELLOW_SALESREPAYMENTRATE_THRESHOLD, getLongFromEarlyWarningPrperties(YELLOW_SALESREPAYMENTRATE_THRESHOLD));
			returnValue.put(YELLOW_OVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(YELLOW_OVERDUEPLANS_LOWERLIMIT));
			returnValue.put(YELLOW_OVERDUEPLANS_UPPERLIMIT, getLongFromEarlyWarningPrperties(YELLOW_OVERDUEPLANS_UPPERLIMIT));
			returnValue.put(YELLOW_CONTINUEOVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(YELLOW_CONTINUEOVERDUEPLANS_LOWERLIMIT));
			returnValue.put(YELLOW_CONTINUEOVERDUEPLANS_UPPERLIMIT, getLongFromEarlyWarningPrperties(YELLOW_CONTINUEOVERDUEPLANS_UPPERLIMIT));
			break;
		case orange:
			returnValue.put(COURTEXECUTIONCOMPANY_MONTH, getLongFromEarlyWarningPrperties(COURTEXECUTIONCOMPANY_MONTH));
			returnValue.put(COURTEXECUTIONCOMPANY_COUNT_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONCOMPANY_COUNT_THRESHOLD));
			returnValue.put(COURTEXECUTIONCOMPANY_MONEY_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONCOMPANY_MONEY_THRESHOLD));
			returnValue.put(COURTEXECUTIONINDIVIDUAL_MONTH, getLongFromEarlyWarningPrperties(COURTEXECUTIONINDIVIDUAL_MONTH));
			returnValue.put(COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD));
			returnValue.put(COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD));
			returnValue.put(ORANGE_COUNTERGURANTEERATE_DAY, getLongFromEarlyWarningPrperties(ORANGE_COUNTERGURANTEERATE_DAY));
			returnValue.put(ORANGE_COUNTERGURANTEERATE_THRESHOLD, getLongFromEarlyWarningPrperties(ORANGE_COUNTERGURANTEERATE_THRESHOLD));
			returnValue.put(ORANGE_SALESCHANGERATE_DAY, getLongFromEarlyWarningPrperties(ORANGE_SALESCHANGERATE_DAY));
			returnValue.put(ORANGE_SALESCHANGERATE_THRESHOLD, getLongFromEarlyWarningPrperties(ORANGE_SALESCHANGERATE_THRESHOLD));
			returnValue.put(ORANGE_SALESREPAYMENTRATE_DAY, getLongFromEarlyWarningPrperties(ORANGE_SALESREPAYMENTRATE_DAY));
			returnValue.put(ORANGE_SALESREPAYMENTRATE_THRESHOLD, getLongFromEarlyWarningPrperties(ORANGE_SALESREPAYMENTRATE_THRESHOLD));
			returnValue.put(ORANGE_OVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(ORANGE_OVERDUEPLANS_LOWERLIMIT));
			returnValue.put(ORANGE_OVERDUEPLANS_UPPERLIMIT, getLongFromEarlyWarningPrperties(ORANGE_OVERDUEPLANS_UPPERLIMIT));
			returnValue.put(ORANGE_CONTINUEOVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(ORANGE_CONTINUEOVERDUEPLANS_LOWERLIMIT));
			returnValue.put(ORANGE_CONTINUEOVERDUEPLANS_UPPERLIMIT, getLongFromEarlyWarningPrperties(ORANGE_CONTINUEOVERDUEPLANS_UPPERLIMIT));
			break;
		case red:
			returnValue.put(COURTEXECUTIONCOMPANY_MONTH, getLongFromEarlyWarningPrperties(COURTEXECUTIONCOMPANY_MONTH));
			returnValue.put(COURTEXECUTIONCOMPANY_COUNT_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONCOMPANY_COUNT_THRESHOLD));
			returnValue.put(COURTEXECUTIONCOMPANY_MONEY_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONCOMPANY_MONEY_THRESHOLD));
			returnValue.put(COURTEXECUTIONINDIVIDUAL_MONTH, getLongFromEarlyWarningPrperties(COURTEXECUTIONINDIVIDUAL_MONTH));
			returnValue.put(COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD));
			returnValue.put(COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD, getLongFromEarlyWarningPrperties(COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD));
			returnValue.put(RED_COUNTERGURANTEERATE_DAY, getLongFromEarlyWarningPrperties(RED_COUNTERGURANTEERATE_DAY));
			returnValue.put(RED_COUNTERGURANTEERATE_THRESHOLD, getLongFromEarlyWarningPrperties(RED_COUNTERGURANTEERATE_THRESHOLD));
			returnValue.put(RED_SALESCHANGERATE_DAY, getLongFromEarlyWarningPrperties(RED_SALESCHANGERATE_DAY));
			returnValue.put(RED_SALESCHANGERATE_THRESHOLD, getLongFromEarlyWarningPrperties(RED_SALESCHANGERATE_THRESHOLD));
			returnValue.put(RED_SALESREPAYMENTRATE_DAY, getLongFromEarlyWarningPrperties(RED_SALESREPAYMENTRATE_DAY));
			returnValue.put(RED_SALESREPAYMENTRATE_THRESHOLD, getLongFromEarlyWarningPrperties(RED_SALESREPAYMENTRATE_THRESHOLD));
			returnValue.put(RED_OVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(RED_OVERDUEPLANS_LOWERLIMIT));
			returnValue.put(RED_CONTINUEOVERDUEPLANS_LOWERLIMIT, getLongFromEarlyWarningPrperties(RED_CONTINUEOVERDUEPLANS_LOWERLIMIT));
			break;
		default:
			break;
		}
		return returnValue;
	}
	
}
