package com.cana.credit.scheduler.schedulers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.cana.credit.dao.mapper.WhiteCustomerAndRuleCustomMapper;
import com.cana.credit.dao.mapper.gen.WhiteCustomerMapper;
import com.cana.credit.dao.mapper.gen.WhiteCustomerRuleMapper;
import com.cana.credit.dao.po.WhiteCustomer;
import com.cana.credit.dao.po.WhiteCustomerExample;
import com.cana.credit.dao.po.WhiteCustomerRule;
import com.cana.credit.dao.po.WhiteCustomerRuleExample;
import com.cana.credit.service.convertors.WhiteCustomerRuleConvertor;
import com.cana.credit.service.utils.CreditDateUtil;
import com.cana.flight.finance.common.enums.ProduceType;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.flight.finance.dao.utils.IDGenerator;
import com.cana.flight.finance.service.IRepaymentService;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 每月一日，系统根据规则生成白名单
 * @author tangyihong
 * @author XuMeng
 */
@Service
public class GenerateWhitesScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateWhitesScheduler.class);
	
	@Resource
	private WhiteCustomerRuleMapper whiteCustomerRuleMapper;
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;
	
	@Resource
	private WhiteCustomerMapper whiteCustomerMapper;
	
	@Resource 
	private WhiteCustomerAndRuleCustomMapper whiteCustomerAndRuleCustomMapper;
	
	@Resource
	private IRepaymentService repaymentServiceImpl;

	private String configFilePath = "properties/generate_whites_rule.properties";

	/**	
	 * 发布后执行一次
	*/
	@Scheduled(fixedDelay = 1000 * 60 * 60 * 24 * 30 * 12 * 10)	
	public void generateWhiteCustomersIm() {
		if (DateTime.now().isBefore(DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, "2016-09-09 20:00:00"))) {
			printLog("进入启动第一次真旅白名单生成任务");
			generateWhiteCustomers();
		} else {
			printLog("不再启动第一次真旅白名单生成任务");
		}
	}

	/**
	 * 每天凌晨5点进入此方法，如果当天是每月的第一天，则刷新白名单，如果不是第一天，则检查当天是否有在配置文件中强制执行
	 */
	@Scheduled(cron = "0 0 5 * * ?")
	public void generateWhiteCustomers(){

		if (DateTime.now().getDayOfMonth() != 1) {
			String forceDate10 = TopsConfReader.getConfContent(configFilePath, "forceDate10s", ConfScope.G);
			printLog("配置文件中的日期有："+forceDate10);
			if (StringUtils.isNotBlank(forceDate10) && forceDate10.contains(DateTimeUtil.date10()))
				printLog("配置文件中配置今天强制生成白名单");
			else {
				printLog("今天无需重新生成白名单");
				return;
			}
		}

		printLog("开始系统生成白名单！");

		StopWatch stopWatch = new StopWatch();
		
		WhiteCustomerRule whiteCustomerRule = getNewestRule();
		if(whiteCustomerRule == null)
			return;
		whiteCustomerRule.setBatchNo(whiteCustomerRule.getBatchNo() + 1);
		whiteCustomerRule.setCreateTime(new Date());
		whiteCustomerRule.setUpdateTime(new Date());
		whiteCustomerRule.setProduceType(ProduceType.ALL.name());
		String rule = TopsConfReader.getConfContent(configFilePath, "rule", ConfScope.G);
		if (StringUtils.isNotBlank(rule))
			whiteCustomerRule.setRule(rule);

		stopWatch.start("获取满足合作期限的客户");
		WhiteCustomerRuleDTO ruleDTO = WhiteCustomerRuleConvertor.PO2DTO4WhiteCustomerRule(whiteCustomerRule); 
		List<TzCustomerInfo> newestTzCustomerInfos = getTzCustomerByCooperationPeriod(ruleDTO.getCooperationPeriod());
		if(CollectionUtils.isEmpty(newestTzCustomerInfos)){
			printLog("没有符合合作期限的真旅客户");
			return;
		}
		
		printLog("共有" + newestTzCustomerInfos.size() + "个客户满足合作期限条件");
		
		//规则生成类型是“增量”
		if(ProduceType.PART.name().equals(whiteCustomerRule.getProduceType())) {
			newestTzCustomerInfos = removeWhiteCustomerExist(newestTzCustomerInfos);
			printLog("增量生成，去除掉已在白名单中的客户后，剩余" + newestTzCustomerInfos.size() + "个客户需要判断后续条件");
		}
		stopWatch.stop();
		
		deleteWhiteCustomerByBatchNo(whiteCustomerRule.getBatchNo());
		
		//符合规定合作期限中订单是连续的，增长率，逾期率，逾期次数的客户插入白名单表
		int whiteCustomerNumber = 0;
		int i = 0;
		stopWatch.start("判断客户满足其他白名单规则");
		for(TzCustomerInfo tz : newestTzCustomerInfos){
			i++;
			if(!repaymentServiceImpl.checkBillContinuous(tz.getTzCustomerId(), ruleDTO.getCooperationPeriod())) {
				printLog("第" + i + "个客户不满足白名单规则中的" + ruleDTO.getCooperationPeriod() + "个月订单连续，客户ID为：" + tz.getTzCustomerId());
				continue;
			}
			BigDecimal growthRate = null;
			if(ruleDTO.getPurchaseOrderGrowthRate() != null){
				growthRate = repaymentServiceImpl.getGrowthRateByCustomerId(tz.getTzCustomerId());
				if(growthRate == null || growthRate.compareTo(ruleDTO.getPurchaseOrderGrowthRate()) < 0) {
					printLog("第" + i + "个客户不满足白名单规则，客户ID为：" + tz.getTzCustomerId() + "，增长率为：" + growthRate);
					continue;
				}
			}
			BigDecimal overdueRate = repaymentServiceImpl.getOverdueRateBycustomerId(tz.getTzCustomerId(),3);
			if(overdueRate == null || overdueRate.compareTo(ruleDTO.getOverdueRate()) > 0) {
				printLog("第" + i + "个客户不满足白名单规则，客户ID为：" + tz.getTzCustomerId() + "，逾期率为：" + overdueRate);
				continue;
			}
			Integer overdueTimes = repaymentServiceImpl.getOverdueTimesBycustomerId(tz.getTzCustomerId(),3);
			if(overdueTimes == null || overdueTimes.compareTo(ruleDTO.getOverdueTimes()) > 0) {
				printLog("第" + i + "个客户不满足白名单规则，客户ID为：" + tz.getTzCustomerId() + "，逾期次数为：" + overdueTimes);
				continue;
			}

			whiteCustomerNumber++;
			WhiteCustomer white = convert2WhiteCustomer(tz,growthRate,overdueRate,overdueTimes,whiteCustomerRule.getBatchNo());
			whiteCustomerMapper.insertSelective(white);
			
			printLog("第" + i + "个客户满足白名单规则，客户ID为：" + tz.getTzCustomerId());
		}
		stopWatch.stop();

		whiteCustomerRule.setWhiteCustomerNumber(whiteCustomerNumber);
		updateWhiteCustomerRule(whiteCustomerRule);
		
		printLog("结束系统生成白名单！生成白名单客户" + whiteCustomerNumber + "位");
		printLog(stopWatch.prettyPrint());
		
	}

	private void updateWhiteCustomerRule(WhiteCustomerRule whiteCustomerRule) {
		WhiteCustomerRuleExample example = new WhiteCustomerRuleExample();
		example.createCriteria().andEnableEqualTo(true);
		List<WhiteCustomerRule> rules = whiteCustomerRuleMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(rules))
			for (WhiteCustomerRule rule : rules) {
				rule.setEnable(false);
				whiteCustomerRuleMapper.updateByPrimaryKeySelective(rule);
			}

		whiteCustomerRule.setEnable(true);
		whiteCustomerRuleMapper.insert(whiteCustomerRule);
	}
	
	private void printLog(String log) {
		logger.info(log);
	}
	
	/**
	 * 获取最新的规则
	 * @return 
	 * WhiteCustomerRule
	 */
	private WhiteCustomerRule getNewestRule(){
		WhiteCustomerRuleExample example = new WhiteCustomerRuleExample(); 
		example.setOrderByClause("batch_no desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		List<WhiteCustomerRule> whiteCustomerRules = whiteCustomerRuleMapper.selectByExample(example);
		if (whiteCustomerRules == null || whiteCustomerRules.isEmpty()){
			logger.error("生成白名单规则为空");
            return null;
		}
		return whiteCustomerRules.get(0);
	}
	
	/**
	 * 获取符合指定期限的真旅客户
	 * @param cooperationPeriod
	 * @return
	 */
	private List<TzCustomerInfo> getTzCustomerByCooperationPeriod(Integer cooperationPeriod){
		Date validDate = getVaildDateByPeriod(cooperationPeriod);
		if(validDate == null)
			return null;
		
		TzCustomerInfoExample example = new TzCustomerInfoExample();
		example.createCriteria().andFirstBusinessTimeLessThan(DateTimeUtil.format(validDate,"yyyy-MM-dd"));
		List<TzCustomerInfo> tzCustomerInfos = tzCustomerInfoMapper.selectByExample(example);
		return tzCustomerInfos;
	}
	
	/**
	 * 获取合法时间 = 当前时间 - 规则设定的合作期限
	 * 2016年1月16日 - 2个月 = 2016年1月1日
	 * @param period
	 * @return
	 */
	private Date getVaildDateByPeriod(Integer cooperationPeriod){
		if(cooperationPeriod<2){
			logger.error("生成白名单规则合作月份小于2");
			return null;
		}
		DateTime vaildDate = new DateTime().minusMonths(cooperationPeriod-2);
		return DateTimeUtil.truncate(vaildDate.toDate(), Calendar.MONTH);
	}
	
	/**
	 * 规则是增量的情况下，本次筛选的真旅客户列表中去除白名单表中已有的客户
	 * @param newestTzCustomerInfos
	 * @return
	 */
	private List<TzCustomerInfo> removeWhiteCustomerExist(List<TzCustomerInfo> newestTzCustomerInfos){
		List<String> whiteCustomerIds = whiteCustomerAndRuleCustomMapper.getAvailableWhiteCustomerIds();
	    for(int i=newestTzCustomerInfos.size()-1; i>=0; i--) {
	    	if(whiteCustomerIds.contains(newestTzCustomerInfos.get(i).getTzCustomerId())){
	    		newestTzCustomerInfos.remove(i);
	    	}
	    }
	    return newestTzCustomerInfos;
	}
	
	/**
	 * 删除白名单表中是这次批次号的数据
	 * @param batchNo 批次号
	 */
	private void deleteWhiteCustomerByBatchNo(Integer batchNo){
		WhiteCustomerExample whiteCustomerExample = new WhiteCustomerExample();
		whiteCustomerExample.createCriteria().andRuleBatchNoEqualTo(batchNo);
		whiteCustomerMapper.deleteByExample(whiteCustomerExample);
	}
			
	/**
	 * 真旅客户对象 转换成 白名单客户对象
	 * @param tz 真旅客户基本信息
	 * @param growthRate 增长率
	 * @param overdueRate 逾期率
	 * @param overdueTimes 逾期次数
	 * @param batchNo 规则批次号
	 * @return
	 */
	private WhiteCustomer convert2WhiteCustomer(TzCustomerInfo tz,BigDecimal growthRate,BigDecimal overdueRate,Integer overdueTimes,Integer batchNo){
		WhiteCustomer white = new WhiteCustomer();
		white.setId(IDGenerator.generateWhiteCustomerId());
		white.setTzShortId(tz.getTzShortId());
		white.setTzCustomerId(tz.getTzCustomerId());
		white.setCustomerName(tz.getCustomerNames());
		white.setCooperationPeriod(CreditDateUtil.calculatePeriodMonth(tz.getFirstBusinessTime()));
		white.setPurchaseOrderGrowthRate(growthRate);
		white.setOverdueRate(overdueRate);
		white.setOverdueTimes(overdueTimes);
		white.setRuleBatchNo(batchNo);
		white.setCreateTime(new Date());
		white.setUpdateTime(new Date());
		return white;
	}
	
}
