package com.dianping.cat.report.alert;

import java.util.ArrayList;
import java.util.List;

import org.unidal.tuple.Pair;

import com.dianping.cat.Cat;
import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.SubCondition;

public class DefaultDataChecker implements DataChecker {

	/**
	 * @param doubleList
	 * @param remainCount
	 * @return
	 * 取出靠近的持续时间应的值，因为doubleList为最大持续时间的值
	 */
	private double[] buildLastMinutesDoubleArray(double[] doubleList, int remainCount) {
		if (doubleList.length <= remainCount) {
			return doubleList;
		}

		double[] result = new double[remainCount];
		int startIndex = doubleList.length - remainCount;

		for (int i = 0; i < remainCount; i++) {
			result[i] = doubleList[startIndex + i];
		}

		return result;
	}

	public List<AlertResultEntity> checkData(double[] value, double[] baseline, List<Condition> conditions) {
		List<AlertResultEntity> alertResults = new ArrayList<AlertResultEntity>();

		for (Condition condition : conditions) {
			int conditionMinute = condition.getMinute();
			double[] valueValid = buildLastMinutesDoubleArray(value, conditionMinute);//取出当前持续时间的值
			double[] baselineValid = buildLastMinutesDoubleArray(baseline, conditionMinute);

			Pair<Boolean, String> condResult = checkDataByCondition(valueValid, baselineValid, condition);

			if (condResult.getKey() == true) {
				String alertType = condition.getAlertType();
				alertResults.add(new AlertResultEntity(condResult.getKey(), condResult.getValue(), alertType));
			}
		}

		return alertResults;
	}

/**
 * 监控值与监控条件作比较，返回告警内容
 * @param value transaction报表时间段的报表中符合传入条件的数据数组
 * @param conditions 监控条件
 * */

	public List<AlertResultEntity> checkData(double[] value, List<Condition> conditions) {
		List<AlertResultEntity> alertResults = new ArrayList<AlertResultEntity>();

		for (Condition condition : conditions) {
			int conditionMinute = condition.getMinute();
			double[] valueValid = buildLastMinutesDoubleArray(value, conditionMinute);
			Pair<Boolean, String> condResult = checkDataByCondition(valueValid, valueValid, condition);

			if (condResult.getKey() == true) {
				String alertType = condition.getAlertType();

				alertResults.add(new AlertResultEntity(condResult.getKey(), condResult.getValue(), alertType));
			}
		}

		return alertResults;
	}

	/**
	 * @param value
	 * @param baseline
	 * @param condition
	 * @return
	 * 验证规则
	 * Pair key 是否告警 value 告警message
	 */
	private Pair<Boolean, String> checkDataByCondition(double[] value, double[] baseline, Condition condition) {
		StringBuilder builder = new StringBuilder();

		for (SubCondition subCondition : condition.getSubConditions()) {
			try {
				String ruleType = subCondition.getType();
				RuleType rule = RuleType.getByTypeId(ruleType);
				//把计算方法放在枚举中，每个规则项目对应相应的计算方法，并生成相应的告警信息
				Pair<Boolean, String> subResult = rule.executeRule(value, baseline, subCondition.getText());

				if (!subResult.getKey()) {
					return new Pair<Boolean, String>(false, "");
				}
				builder.append(subResult.getValue()).append("<br/>");
			} catch (Exception ex) {
				Cat.logError(condition.toString(), ex);
				return new Pair<Boolean, String>(false, "");
			}
		}

		return new Pair<Boolean, String>(true, builder.toString());
	}

	public List<AlertResultEntity> checkDataForApp(double[] value, List<Condition> conditions) {
		List<AlertResultEntity> alertResults = new ArrayList<AlertResultEntity>();

		for (Condition condition : conditions) {
			int conditionMinute = condition.getMinute() / 5;
			double[] valueValid = buildLastMinutesDoubleArray(value, conditionMinute);

			Pair<Boolean, String> condResult = checkDataByCondition(valueValid, valueValid, condition);

			if (condResult.getKey() == true) {
				String alertType = condition.getAlertType();
				alertResults.add(new AlertResultEntity(condResult.getKey(), condResult.getValue(), alertType));
			}
		}

		return alertResults;
	}

}
