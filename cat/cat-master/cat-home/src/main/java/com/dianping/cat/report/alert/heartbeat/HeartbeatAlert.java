package com.dianping.cat.report.alert.heartbeat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.unidal.lookup.annotation.Inject;
import org.unidal.lookup.util.StringUtils;
import org.unidal.tuple.Pair;

import com.dianping.cat.Cat;
import com.dianping.cat.Constants;
import com.dianping.cat.config.server.ServerFilterConfigManager;
import com.dianping.cat.consumer.company.model.entity.ProductLine;
import com.dianping.cat.consumer.heartbeat.HeartbeatAnalyzer;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;
import com.dianping.cat.consumer.transaction.TransactionAnalyzer;
import com.dianping.cat.consumer.transaction.model.entity.TransactionReport;
import com.dianping.cat.helper.TimeHelper;
import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.report.alert.AlertResultEntity;
import com.dianping.cat.report.alert.AlertType;
import com.dianping.cat.report.alert.BaseAlert;
import com.dianping.cat.report.alert.config.BaseRuleConfigManager;
import com.dianping.cat.report.alert.sender.AlertEntity;
import com.dianping.cat.report.page.heartbeat.config.HeartbeatDisplayPolicyManager;
import com.dianping.cat.report.service.ModelRequest;
import com.dianping.cat.report.service.ModelResponse;
import com.dianping.cat.report.service.ModelService;

public class HeartbeatAlert extends BaseAlert {

	@Inject(type = ModelService.class, value = HeartbeatAnalyzer.ID)
	private ModelService<HeartbeatReport> m_heartbeatService;

	@Inject(type = ModelService.class, value = TransactionAnalyzer.ID) //实现类为CompositeTransactionService
	private ModelService<TransactionReport> m_transactionService;

	@Inject
	private HeartbeatDisplayPolicyManager m_displayManager;

	@Inject
	private ServerFilterConfigManager m_serverFilterConfigManager;

	@Inject
	protected HeartbeatRuleConfigManager m_ruleConfigManager;

	/**
	 * @param periods
	 * @return
	 * Map key 是 group id：metric id 例如：System：LoadAverage
	 * Map value 是 doule[] 按时间排序 值是value/unit unit可能是1、k(1024)、M(1024*1024) ...
	 */
	private Map<String, double[]> buildArrayForExtensions(List<Period> periods) {
		Map<String, double[]> map = new LinkedHashMap<String, double[]>();

		for (Period period : periods) {
			List<Pair<String, String>> metrics = extractExtentionMetrics(period);
			int index = period.getMinute();

			for (Pair<String, String> metric : metrics) {
				String key = metric.getKey() + ":" + metric.getValue();
				double[] array = map.get(key);

				if (array == null) {
					array = new double[60];
					map.put(key, array);
				}
				try {
					String groupName = metric.getKey();
					String metricName = metric.getValue();
					int unit = m_displayManager.queryUnit(groupName, metricName);//心跳指标单位
					Detail detail = period.findOrCreateExtension(groupName).findOrCreateDetail(metricName);//根据detail id 获取detail

					array[index] = detail.getValue() / unit;
				} catch (Exception e) {
					array[index] = 0;
				}
			}
		}
		return map;
	}

	private int calMaxMinute(Map<String, List<Config>> configs) {
		int maxMinute = 0;

		for (List<Config> tmpConfigs : configs.values()) {
			for (Config config : tmpConfigs) {
				for (Condition condition : config.getConditions()) {
					int tmpMinute = condition.getMinute();

					if (tmpMinute > maxMinute) {
						maxMinute = tmpMinute;
					}
				}
			}
		}
		return maxMinute;
	}

	private double[] extract(double[] lastHourValues, double[] currentHourValues, int maxMinute, int alreadyMinute) {
		if (lastHourValues != null && currentHourValues != null) {
			int lastLength = maxMinute - alreadyMinute - 1;
			double[] result = new double[maxMinute];

			for (int i = 0; i < lastLength; i++) {
				result[i] = lastHourValues[60 - lastLength + i];
			}
			for (int i = lastLength; i < maxMinute; i++) {
				result[i] = currentHourValues[i - lastLength];
			}
			return result;
		} else {
			return null;
		}
	}

	private double[] extract(double[] values, int maxMinute, int alreadyMinute) {
		if (values != null) {
			double[] result = new double[maxMinute];

			for (int i = 0; i < maxMinute; i++) {
				result[i] = values[alreadyMinute + 1 - maxMinute + i];
			}
			return result;
		} else {
			return null;
		}
	}

	/**
	 * @param period
	 * @return
	 * 获取扩展指标pair<id, detail>
	 */
	private List<Pair<String, String>> extractExtentionMetrics(Period period) {
		List<Pair<String, String>> metrics = new ArrayList<Pair<String, String>>();

		for (Extension extension : period.getExtensions().values()) {
			Map<String, Detail> details = extension.getDetails();

			for (Entry<String, Detail> detail : details.entrySet()) {
				metrics.add(new Pair<String, String>(extension.getId(), detail.getKey()));
			}
		}
		return metrics;
	}

	private Map<String, double[]> buildBaseValue(Machine machine) {
		Map<String, double[]> map = buildArrayForExtensions(machine.getPeriods());//获取指标每分钟值

		for (String id : map.keySet()) {
			String[] str = id.split(":");//str[0]:group、str[1]:metric

			//delta?不知道什么意思 初步理解当前值对上一个值有依赖
			if (m_displayManager.isDelta(str[0], str[1])) {
				double[] sources = map.get(id);
				double[] targets = new double[60];

				for (int i = 1; i < 60; i++) {
					if (sources[i - 1] > 0) {
						double delta = sources[i] - sources[i - 1];

						if (delta >= 0) {
							targets[i] = delta;
						}
					}
				}
				map.put(id, targets);
			}
		}

		return map;
	}

	private HeartbeatReport generateCurrentReport(String domain, int start, int end) {
		long currentMill = System.currentTimeMillis();
		long currentHourMill = currentMill - currentMill % TimeHelper.ONE_HOUR;

		return generateReport(domain, currentHourMill, start, end);
	}

	private HeartbeatReport generateLastReport(String domain, int start, int end) {
		long currentMill = System.currentTimeMillis();
		long lastHourMill = currentMill - currentMill % TimeHelper.ONE_HOUR - TimeHelper.ONE_HOUR;

		return generateReport(domain, lastHourMill, start, end);
	}

	/*
	 * domin 域名
	 * date 精确到小时的时间long
	 * start 开始的分钟
	 * end 结束的分钟  
	 * 
	 */
	private HeartbeatReport generateReport(String domain, long date, int start, int end) {
		ModelRequest request = new ModelRequest(domain, date).setProperty("min", String.valueOf(start))
		      .setProperty("max", String.valueOf(end)).setProperty("ip", Constants.ALL).setProperty("requireAll", "true");

		if (m_heartbeatService.isEligable(request)) {
			ModelResponse<HeartbeatReport> response = m_heartbeatService.invoke(request);

			if (response != null) {
				return response.getModel();
			} else {
				return null;
			}
		} else {
			throw new RuntimeException("Internal error: no eligable ip service registered for " + request + "!");
		}
	}

	@Override
	public String getName() {
		return AlertType.HeartBeat.getName();
	}

	@Override
	protected Map<String, ProductLine> getProductlines() {
		throw new RuntimeException("get productline is not support by heartbeat alert");
	}

	@Override
	protected BaseRuleConfigManager getRuleConfigManager() {
		return m_ruleConfigManager;
	}

	
	/**
	 * @param domain 项目名
	 * 
	 * 获取规则中持续时间最大的时间，然后当前的前一分钟到之前持续时间的心跳报表数据
	 * 当前小时的报表数据从实时中获取，前一小时的数据从历史数据中获取
	 * 用获取的数据匹配规则
	 */
	private void processDomain(String domain) {
		int minute = calAlreadyMinute(); //获取当前时间的分钟数-1
		Map<String, List<Config>> configsMap = m_ruleConfigManager.queryConfigsByDomain(domain);//根据项目名获取心跳规则
		int domainMaxMinute = calMaxMinute(configsMap);//获取规则中最大的持续时间
		HeartbeatReport currentReport = null;
		HeartbeatReport lastReport = null;
		boolean isDataReady = false;

		if (minute >= domainMaxMinute - 1) {
			int min = minute - domainMaxMinute + 1;
			int max = minute;

			currentReport = generateCurrentReport(domain, min, max);//获取项目当前小时的min-max分钟的心跳报表数据

			if (currentReport != null) {
				isDataReady = true;
			}
		} else if (minute < 0) {
			int min = minute + 60 - domainMaxMinute + 1;
			int max = minute + 60;

			lastReport = generateLastReport(domain, min, max);//获取项目上一小时的min-max分钟的心跳报表数据

			if (lastReport != null) {
				isDataReady = true;
			}
		} else {
			//持续时间跨小时的情况
			
			int lastLength = domainMaxMinute - minute - 1;
			int lastMin = 60 - lastLength;

			currentReport = generateCurrentReport(domain, 0, minute);
			lastReport = generateLastReport(domain, lastMin, 59);

			if (lastReport != null && currentReport != null) {
				isDataReady = true;
			}
		}

		//对报表数据进行规则验证
		if (isDataReady) {
			for (Entry<String, List<Config>> entry : configsMap.entrySet()) {
				String metric = entry.getKey();//监控指标
				List<Config> configs = entry.getValue();
				//获取开始、结束时间满足的condition、key为最大持续时间
				Pair<Integer, List<Condition>> conditionPair = m_ruleConfigManager.convertConditions(configs);

				if (conditionPair != null) {
					int maxMinute = conditionPair.getKey();//最大持续时间
					List<Condition> conditions = conditionPair.getValue();

					if (minute >= maxMinute - 1) {
						for (Machine machine : currentReport.getMachines().values()) {
							String ip = machine.getIp();
							double[] arguments = buildBaseValue(machine).get(metric);//size 60

							if (arguments != null) {
								double[] values = extract(arguments, maxMinute, minute);//取出持续分钟值

								processMeitrc(domain, ip, metric, conditions, maxMinute, values);
							}
						}
					} else if (minute < 0) {
						for (Machine machine : lastReport.getMachines().values()) {
							String ip = machine.getIp();
							double[] arguments = buildBaseValue(machine).get(metric);

							if (arguments != null) {
								double[] values = extract(arguments, maxMinute, 59);

								processMeitrc(domain, ip, metric, conditions, maxMinute, values);
							}
						}
					} else {
						for (Machine lastMachine : lastReport.getMachines().values()) {
							String ip = lastMachine.getIp();
							Machine currentMachine = currentReport.getMachines().get(ip);

							if (currentMachine != null) {
								Map<String, double[]> lastHourArguments = buildBaseValue(lastMachine);
								Map<String, double[]> currentHourArguments = buildBaseValue(currentMachine);

								if (lastHourArguments != null && currentHourArguments != null) {
									double[] values = extract(lastHourArguments.get(metric), currentHourArguments.get(metric),
									      maxMinute, minute);

									processMeitrc(domain, ip, metric, conditions, maxMinute, values);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param domain
	 * @param ip
	 * @param metric
	 * @param conditions
	 * @param maxMinute
	 * @param values
	 * 规则验证
	 */
	private void processMeitrc(String domain, String ip, String metric, List<Condition> conditions, int maxMinute,
	      double[] values) {
		try {
			if (values != null) {
				double[] baseline = new double[maxMinute];
				List<AlertResultEntity> alerts = m_dataChecker.checkData(values, baseline, conditions);//验证规则 baseline不起作业，只是占位

				for (AlertResultEntity alertResult : alerts) {
					AlertEntity entity = new AlertEntity();

					entity.setDate(alertResult.getAlertTime()).setContent(alertResult.getContent())
					      .setLevel(alertResult.getAlertLevel());
					entity.setMetric(metric).setType(getName()).setGroup(domain);
					entity.getParas().put("ip", ip);
					m_sendManager.addAlert(entity);
				}
			}
		} catch (Exception e) {
			Cat.logError(e);
		}
	}

	//通过获取tranaction报表数据获取所有项目名
	private Set<String> queryDomains() {
		Set<String> domains = new HashSet<String>();
		ModelRequest request = new ModelRequest("cat", System.currentTimeMillis());

		if (m_transactionService.isEligable(request)) {
			ModelResponse<TransactionReport> response = m_transactionService.invoke(request);
			domains.addAll(response.getModel().getDomainNames());
		}

		return domains;
	}

	@Override
	public void run() {
		boolean active = TimeHelper.sleepToNextMinute();

		while (active) {
			Transaction t = Cat.newTransaction("AlertHeartbeat", TimeHelper.getMinuteStr());
			long current = System.currentTimeMillis();

			try {
				Set<String> domains = queryDomains();

				for (String domain : domains) {
					if (m_serverFilterConfigManager.validateDomain(domain) && StringUtils.isNotEmpty(domain)) {
						try {
							processDomain(domain);
						} catch (Exception e) {
							Cat.logError(e);
						}
					}
				}
				t.setStatus(Transaction.SUCCESS);
			} catch (Exception e) {
				t.setStatus(e);
			} finally {
				t.complete();
			}
			long duration = System.currentTimeMillis() - current;

			try {
				if (duration < DURATION) {
					Thread.sleep(DURATION - duration);
				}
			} catch (InterruptedException e) {
				active = false;
			}
		}
	}

}
