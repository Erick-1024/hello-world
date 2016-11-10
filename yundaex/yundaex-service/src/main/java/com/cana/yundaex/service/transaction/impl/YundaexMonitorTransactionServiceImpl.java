package com.cana.yundaex.service.transaction.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.repayment.service.transaction.IFinanceReportTransactionService;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.yundaex.common.dto.YundaexGradeScoreDTO;
import com.cana.yundaex.common.dto.monitor.YundaexMonitorImportDTO;
import com.cana.yundaex.common.enums.TimeUnit;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexGrade;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.common.enums.YundaexLoanType;
import com.cana.yundaex.common.enums.YundaexMonitor;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.YundaexCompositeCostMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCreditMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerGradeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexGradeModelMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexTstationInfoMapper;
import com.cana.yundaex.dao.po.YundaexCompositeCost;
import com.cana.yundaex.dao.po.YundaexCompositeCostExample;
import com.cana.yundaex.dao.po.YundaexCreditMonitor;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitor;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitorExample;
import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexCustomerGradeExample;
import com.cana.yundaex.dao.po.YundaexGradeModel;
import com.cana.yundaex.dao.po.YundaexGradeModelExample;
import com.cana.yundaex.dao.po.YundaexStationOperation;
import com.cana.yundaex.dao.po.YundaexTstationInfo;
import com.cana.yundaex.dao.po.YundaexTstationInfoExample;
import com.cana.yundaex.dao.utils.IDGenerator;
import com.cana.yundaex.service.transaction.IYundaexInterestRateTransactionService;
import com.cana.yundaex.service.transaction.IYundaexMonitorTransactionService;
import com.cana.yundaex.service.utils.NewestStationOperationHolder;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

@Service
public class YundaexMonitorTransactionServiceImpl implements IYundaexMonitorTransactionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Resource
	private YundaexCustomerApplyMonitorMapper yundaexCustomerApplyMonitorMapper;
	
	@Resource
	private YundaexCustomerApplyMapper yundaexCustomerApplyMapper;
	
	@Resource
	private YundaexCreditMonitorMapper yundaexCreditMonitorMapper;
	
	@Resource
	private YundaexCompositeCostMapper ydCompositeCostMapper;
	
	@Resource
	private YundaexGradeModelMapper ydGradeModelMapper;
	
	@Resource
	private YundaexTstationInfoMapper ydTstationMapper;
	
	@Resource
	private YundaexCustomerGradeMapper yundaexCustomerGradeMapper;
	
	@Resource
	private CreditLimitMapper creditLimitMapper;
	
	@Resource
	private IFinanceReportTransactionService financeReportTransactionService;
	
	/*@Resource
	private IMonitorReportTransactionService monitorReportTransactionServiceImpl;*/
	
	@Resource
	private IReportApi reportApi;
	
	@Resource
	private IYundaexInterestRateTransactionService yundaexInterestRateTransactionService;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Override
	public void importExcelToDB(List<YundaexMonitorImportDTO> passYundaexMonitorImportDTOs) {
		if(CollectionUtils.isNotEmpty(passYundaexMonitorImportDTOs)){
			for(YundaexMonitorImportDTO yundaexMonitorImportDTO : passYundaexMonitorImportDTOs){
				YundaexCustomerApplyMonitor monitor = new YundaexCustomerApplyMonitor();
				monitor.setId(IDGenerator.generateCustomerApplyMonitorId());
				monitor.setStationNo(yundaexMonitorImportDTO.getStationNo());
				monitor.setStationAmount(new BigDecimal(yundaexMonitorImportDTO.getStationAmount()).intValue());
				monitor.setStationMgr(yundaexMonitorImportDTO.getStationMgr());
				monitor.setStationName(yundaexMonitorImportDTO.getStationName());
				monitor.setBusiLimit(new BigDecimal(yundaexMonitorImportDTO.getBusiLimit()).longValue());
				monitor.setBailBalance(MoneyArithUtil.convertStringToMoney(yundaexMonitorImportDTO.getBailBalance()));
				if(StringUtils.isNotBlank(yundaexMonitorImportDTO.getShortLoan()))
					monitor.setShortLoan(MoneyArithUtil.convertStringToMoney(yundaexMonitorImportDTO.getShortLoan()));
				monitor.setLoanType(YundaexLoanType.getEnum(yundaexMonitorImportDTO.getLoanType()).name());
				if(StringUtils.isNotBlank(yundaexMonitorImportDTO.getLoanLimit()) && new BigDecimal(yundaexMonitorImportDTO.getLoanLimit()).intValue() >0)
					monitor.setLoanLimit(new BigDecimal(yundaexMonitorImportDTO.getLoanLimit()).intValue());
				monitor.setLimitUnit(TimeUnit.getEnum(yundaexMonitorImportDTO.getLimitUnit()).name());
				monitor.setYundaexJudge(YundaexJudge.getEnum(yundaexMonitorImportDTO.getYundaexJudge()).name());
				String currentDate = vbamCommonServiceImpl.getCurrentDate();
				String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
				monitor.setMonth(virtualDate);
//				monitor.setMonth(DateTimeUtil.month7());
				monitor.setStatus(YundaexAuditState.WAIT.name());
				monitor.setCreateTime(new Date());
				yundaexCustomerApplyMonitorMapper.insertSelective(monitor);
			}
		}
	}

	/**
	 * 韵达监控
	 */
	@Override
	public boolean monitorScheduler(YundaexCustomerApply yundaexCustomerApply , String memberId, String currentDate) {
		if(yundaexCustomerApply == null)
			return false;
		String stationNo = yundaexCustomerApply.getStationNo();
		Transaction t = Cat.newTransaction("service", "韵达监控");
		t.addData("stationNo", stationNo);
		try {
			// 韵达评价
			YundaexJudge yundaexJudge = YundaexJudge.valueOf(yundaexCustomerApply.getYundaexJudge()); 
			int judge = 0;
			switch (yundaexJudge) {
			case excellent:
				judge = 3;
				break;
			case fine:
				judge = 2;
				break;
			case bad:
				judge = 1;
				break;
			default:
				break;
			}
			insertIntoCreditMonitor(new BigDecimal(judge), memberId, stationNo, YundaexMonitor.YUNDAEXJUDGE.name(), currentDate);
			// 揽派件增长率
			BigDecimal rate = getRecSendRate(stationNo, currentDate);
			insertIntoCreditMonitor(rate, memberId, stationNo, YundaexMonitor.RECANDSEND_GROWTHRATE.name(), currentDate);
			// 日资金需求
			BigDecimal dayBail = getDayRequirements(stationNo, currentDate);
			insertIntoCreditMonitor(dayBail.multiply(new BigDecimal(100)), memberId, stationNo, YundaexMonitor.DAY_REQUIREMENTS.name(), currentDate);
			// 韵达评分
			BigDecimal score = getYundaexScore(yundaexCustomerApply, rate);
			insertIntoCreditMonitor(score, memberId, stationNo, YundaexMonitor.YUNDAEXGRADE.name(), currentDate);
			// 保证金余额
			Long bailBalance = yundaexCustomerApply.getBailBalance()==null?0:yundaexCustomerApply.getBailBalance(); 
			insertIntoCreditMonitor(new BigDecimal(bailBalance), memberId, stationNo, YundaexMonitor.BAILBALANCE.name(), currentDate);
			// 短期借款
			Long shortLoan = yundaexCustomerApply.getShortLoan()==null?0:yundaexCustomerApply.getShortLoan();
			insertIntoCreditMonitor(new BigDecimal(shortLoan), memberId, stationNo, YundaexMonitor.SHORTLOAN.name(), currentDate);
			// 净现金流
			BigDecimal netCashflow = getCompanyCashFlow(yundaexCustomerApply, currentDate);
			insertIntoCreditMonitor(netCashflow.multiply(new BigDecimal(100)), memberId, stationNo, YundaexMonitor.NET_CASHFLOW.name(), currentDate);
			// 最大授信金额
			BigDecimal creditLimit = getCreditLimit(netCashflow,dayBail);
			insertIntoCreditMonitor(creditLimit.multiply(new BigDecimal(100)), memberId, stationNo, YundaexMonitor.CREDIT_LIMIT.name(), currentDate);
			// 逾期次数
			int overDues = getCountOverduePlans(memberId, currentDate);
			insertIntoCreditMonitor(new BigDecimal(overDues), memberId, stationNo, YundaexMonitor.OVERDUES.name(), currentDate);
			// 利率
			String interestRate = yundaexInterestRateTransactionService.getInterestRate(memberId, score);
			
			insertIntoYDCreditMonitor(yundaexCustomerApply, memberId, rate, dayBail, score, netCashflow, creditLimit, overDues, interestRate, currentDate);
			updateYdCustomerApplyMonitor(stationNo, currentDate);
			
			t.setStatus(Transaction.SUCCESS);
			Cat.logMetricForCount("韵达监控成功");
		} catch (Exception e) {
			logger.error(stationNo + "-韵达监控失败", e);
			t.setStatus(e);
			Cat.logError(e);
			Cat.logMetricForCount("韵达监控失败");
			return false;
		}finally{
			t.complete();
		}
		return true;
	}

	private void updateYdCustomerApplyMonitor(String stationNo, String currentDate) {
		String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
		YundaexCustomerApplyMonitorExample applyMonitorExample = new YundaexCustomerApplyMonitorExample();
		applyMonitorExample.createCriteria().andStationNoEqualTo(stationNo).andMonthEqualTo(virtualDate).andStatusEqualTo(YundaexAuditState.WAIT.name());
		List<YundaexCustomerApplyMonitor> applyMonitors = yundaexCustomerApplyMonitorMapper.selectByExample(applyMonitorExample);
		if(CollectionUtils.isNotEmpty(applyMonitors)){
			YundaexCustomerApplyMonitor yundaexCustomerApplyMonitor = applyMonitors.get(0); 
			yundaexCustomerApplyMonitor.setStatus(YundaexAuditState.ACCESS.name());
			yundaexCustomerApplyMonitorMapper.updateByPrimaryKey(yundaexCustomerApplyMonitor);
		}
	}

	private void insertIntoYDCreditMonitor(YundaexCustomerApply yundaexCustomerApply, String memberId, BigDecimal rate,
			BigDecimal dayBail, BigDecimal score, BigDecimal netCashflowGrowth, BigDecimal creditLimit, int overDues, String interestRate, String currentDate) {
		YundaexCreditMonitor creditMonitor = new YundaexCreditMonitor();
		creditMonitor.setId(IDGenerator.generateCreditMonitorId());
		creditMonitor.setStationNo(yundaexCustomerApply.getStationNo());
		creditMonitor.setStationMgr(yundaexCustomerApply.getStationMgr());
		creditMonitor.setStationName(yundaexCustomerApply.getStationName());
		creditMonitor.setMemberId(memberId);
		creditMonitor.setRecandsendGrowthRate(rate);
		if(dayBail !=null)
			creditMonitor.setDayRequirements(MoneyUtil.yuan2Cent(dayBail));
		creditMonitor.setYundaexGrade(score);
		// 评级表中获取
		YundaexCustomerGrade customerGrade = getFirstGrade(yundaexCustomerApply.getStationNo());
		creditMonitor.setLastYundaexGrade(customerGrade.getPoints());
		if(yundaexCustomerApply.getBailBalance()!=null)
			creditMonitor.setBailBalance(yundaexCustomerApply.getBailBalance());
		if(netCashflowGrowth != null)
			creditMonitor.setNetCashflow(MoneyUtil.yuan2Cent(netCashflowGrowth));
		if(creditLimit != null)
			creditMonitor.setCreditLimit(MoneyUtil.yuan2Cent(creditLimit));
		// 额度表获取
		CreditLimit creditLimit1 = getFirstTotalLimit(memberId);
		if(creditLimit1 != null)
			creditMonitor.setLastCreditLimit(creditLimit1.getTotalLimit());
		creditMonitor.setOverdues(overDues);
		creditMonitor.setInterestRate(interestRate);
		String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
		creditMonitor.setMonth(virtualDate);
//		creditMonitor.setMonth(DateTimeUtil.month7());
		creditMonitor.setAuditStatus(YundaexAuditState.WAIT.name());
		creditMonitor.setCreateTime(new Date());
		creditMonitor.setUpdateTime(new Date());
		yundaexCreditMonitorMapper.insertSelective(creditMonitor);
		
	}

	private CreditLimit getFirstTotalLimit(String memberId) {
		CreditLimitExample creditLimitExample= new CreditLimitExample();
		creditLimitExample.createCriteria().andMemberIdEqualTo(memberId).andProjectIdEqualTo(Constants.YUNDAEX_ASSET_PROJECT_ID);
		List<CreditLimit> creditLimits = creditLimitMapper.selectByExample(creditLimitExample);
		if(CollectionUtils.isNotEmpty(creditLimits))
			return creditLimits.get(0);
		throw WebException.instance(memberId+":韵达客户额度信息异常");
	}

	private YundaexCustomerGrade getFirstGrade(String stationNo) {
		YundaexCustomerGradeExample gradeExample = new YundaexCustomerGradeExample();
		gradeExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexCustomerGrade> customerGrades = yundaexCustomerGradeMapper.selectByExample(gradeExample);
		if(CollectionUtils.isNotEmpty(customerGrades))
			return customerGrades.get(0);
		else
			throw WebException.instance(stationNo+":韵达客户评级信息异常");
	}
	
	private void insertIntoCreditMonitor(BigDecimal param, String memberId, String stationNo, String type, String currentDate) {
		/*ReportMonitorMetric reportMonitorMetric = new ReportMonitorMetric();
		reportMonitorMetric.setMemberId(memberId);
		reportMonitorMetric.setOutCustomerId(stationNo);
		reportMonitorMetric.setProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		reportMonitorMetric.setType(type);
		reportMonitorMetric.setProportion(param);
		reportMonitorMetric.setDate(DateTimeUtil.month7(DateTimeUtil.addMonth(new Date(), -1)));
		reportMonitorMetric.setCreateTime(new Date());*/
		reportApi.save(param, memberId, stationNo, Constants.YUNDAEX_ASSET_PROJECT_ID, type, currentDate);
	}

	private int getCountOverduePlans(String memberId, String currentDate) throws Exception {
		QueryPlanListRequest request = new QueryPlanListRequest();
		request.setFinanceId(memberId);
		request.setBusinessProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		request.setEndDate(DateTimeUtil.format(DateTimeUtil.truncate(DateUtils.getDate(currentDate, "yyyy-MM-dd"), Calendar.MONTH), "yyyy-MM-dd"));
		
		int countOverduePlans = financeReportTransactionService.countOverduePlans(request);
		return countOverduePlans;
		
	}

	private BigDecimal getCreditLimit(BigDecimal netCashflowGrowth, BigDecimal dayBail) {
		BigDecimal bailHalfMonth = dayBail.multiply(new BigDecimal(15));
		int n = netCashflowGrowth.compareTo(bailHalfMonth);
		if(n <= 0)
			return getMinCreditLimit(netCashflowGrowth);
		else 
			return getMinCreditLimit(bailHalfMonth);
	}

	private BigDecimal getMinCreditLimit(BigDecimal netCashflowGrowth) {
		BigDecimal creditLimit = netCashflowGrowth.divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(10000));
		if(creditLimit.compareTo(new BigDecimal(Constants.YUNDAEX_MAX_CREDIT_LIMIT))<=0)
			return creditLimit;
		else 
			return new BigDecimal(Constants.YUNDAEX_MAX_CREDIT_LIMIT);
	}

	private BigDecimal getCompanyCashFlow(YundaexCustomerApply yundaexCustomerApply, String currentDate) {
		String stationNo = yundaexCustomerApply.getStationNo();
		BigDecimal shortLoan = new BigDecimal(yundaexCustomerApply.getShortLoan()==null?0:yundaexCustomerApply.getShortLoan());
		BigDecimal bailBalance = new BigDecimal(yundaexCustomerApply.getBailBalance()==null?0:yundaexCustomerApply.getBailBalance());
	    shortLoan = shortLoan.divide(new BigDecimal(100));
	    bailBalance = bailBalance.divide(new BigDecimal(100));
	    YundaexStationOperation newestStaionOperation = NewestStationOperationHolder.newestStaionOperation;
	    if(newestStaionOperation == null)
			throw WebException.instance("站点经营状况信息为空");
	    //月度平均揽件数量
  		BigDecimal avgReceiveNo = getAvgReceiveOrSendNo(stationNo,1, currentDate);
  		//月度平均派件数量
  		BigDecimal avgSendNo = getAvgReceiveOrSendNo(stationNo,2, currentDate);
  		
  		//揽件预计增长率
  		BigDecimal receiveGrowthRate = getReceiveOrSendGrowthRate(stationNo,1, currentDate);
  		//派件预计增长率
  		BigDecimal sendGrowthRate = getReceiveOrSendGrowthRate(stationNo,2, currentDate);
  		
  		//预计月度揽件量
  		BigDecimal forecastRecNo = avgReceiveNo.multiply(new BigDecimal(1).add(receiveGrowthRate));
  		//预计月度派件量
  		BigDecimal forecastSendNo = avgSendNo.multiply(new BigDecimal(1).add(sendGrowthRate));
  		
  		//当月揽件收入金额
  		BigDecimal incomeRec = forecastRecNo.multiply(newestStaionOperation.getAverageProfit());
  		//当月派件收入金额
  		BigDecimal incomeSend = forecastSendNo.multiply(newestStaionOperation.getSendIncome());
  		
  		//当月揽件净收入
  		BigDecimal netIncomeRec = incomeRec.subtract(forecastRecNo.multiply(newestStaionOperation.getCostManual().add(newestStaionOperation.getCostReceipt()).add(newestStaionOperation.getCostPackage()).add(newestStaionOperation.getTransitFee()))).subtract(incomeRec.multiply(newestStaionOperation.getOtherMaterialsCostRate())).subtract(forecastRecNo.multiply(newestStaionOperation.getOppositeStationSendCost()));
  		//当月派件净收入
  		BigDecimal netIncomeSend = incomeSend.subtract(incomeSend.multiply(newestStaionOperation.getOtherMaterialsCostRateSend()).add(forecastSendNo.multiply(newestStaionOperation.getCourierFee())));
  		
  		//当月支出小计
  		BigDecimal payment = getPayment(yundaexCustomerApply);
  		
  		Integer shortLoanLimit = yundaexCustomerApply.getShortLoanLimit();
  		String limitUnit = yundaexCustomerApply.getLimitUnit();
  		BigDecimal monthShortLoan = getMonthShortLoan(shortLoan, shortLoanLimit, limitUnit, stationNo);
  		
  		//企业经营现金流 (当月揽件净收入+当月派件净收入-当月支出小计-短期借款/每月)
  		BigDecimal companyCash = netIncomeRec.add(netIncomeSend).subtract(payment).subtract(monthShortLoan);
  		
		return companyCash;
	}

	private BigDecimal getMonthShortLoan(BigDecimal shortLoan, Integer shortLoanLimit, String limitUnit, String stationNo) {
		if(shortLoan.compareTo(BigDecimal.ZERO) ==0)
			return BigDecimal.ZERO;
		if(TimeUnit.YEAR.name().equals(limitUnit)){
			return shortLoan.divide(new BigDecimal(shortLoanLimit).multiply(new BigDecimal(12)),2,BigDecimal.ROUND_HALF_UP);
		}
		if(TimeUnit.MONTH.name().equals(limitUnit)){
			return shortLoan.divide(new BigDecimal(shortLoanLimit), 2, BigDecimal.ROUND_HALF_UP);
		}
		if(TimeUnit.DAY.name().equals(limitUnit)){
			return shortLoan.multiply(new BigDecimal(30)).divide(new BigDecimal(shortLoanLimit), 2, BigDecimal.ROUND_HALF_UP);
		}
		throw WebException.instance(stationNo+"网点借款期限单位异常");
	}

	private BigDecimal getPayment(YundaexCustomerApply yundaexCustomerApply) {
		String city = yundaexCustomerApply.getCity(); 
		List<YundaexCompositeCost> ydCompositeCosts = getComposite(city);
		if(CollectionUtils.isNotEmpty(ydCompositeCosts)){
			YundaexCompositeCost ydCompositeCost = ydCompositeCosts.get(0);
			return ydCompositeCost.getRentalCost().add(ydCompositeCost.getTransportCost()).add(ydCompositeCost.getDefectCost());
		}else{
			List<YundaexCompositeCost> composite = getComposite("OTHER"); 
			return composite.get(0).getRentalCost().add(composite.get(0).getTransportCost()).add(composite.get(0).getDefectCost());
		}
	}

	private List<YundaexCompositeCost> getComposite(String city) {
		YundaexCompositeCostExample example = new YundaexCompositeCostExample();
		if (StringUtils.isNotBlank(city)) {
			example.createCriteria().andStationCityLike("%"+city+"%");
		}
		List<YundaexCompositeCost> ydCompositeCosts = ydCompositeCostMapper.selectByExample(example);
		return ydCompositeCosts;
	}

	private BigDecimal getYundaexScore(YundaexCustomerApply yundaexCustomerApply, BigDecimal rate) {
		BigDecimal score = BigDecimal.ZERO;
		//封装 评分模型DTO
	    YundaexGradeScoreDTO ydGradeScoreDTO = getGradeScoreDTO(yundaexCustomerApply, rate);
		// 计算客户分数
		try {
			score = getGradeScore(ydGradeScoreDTO);
			return score;
		} catch (Exception e) {
			throw WebException.instance(yundaexCustomerApply.getStationNo()+e.getMessage());
		} 
	}

	private BigDecimal getGradeScore(YundaexGradeScoreDTO ydGradeScoreDTO) throws JsonParseException, JsonMappingException, IOException {
		BigDecimal score = BigDecimal.ZERO;
		YundaexGradeModelExample gradleExample = new YundaexGradeModelExample();
		List<YundaexGradeModel> ydGradeModelList = ydGradeModelMapper.selectByExample(gradleExample);
		if(CollectionUtils.isNotEmpty(ydGradeModelList)){
			for (YundaexGradeModel ydGradeModel : ydGradeModelList) {
				//根据二级指标获取该对象中的值
				Map<String, Object> map = getSecondTargetValue(ydGradeModel.getSecondTarget(),ydGradeScoreDTO);
				
				if(ydGradeModel.getType().equals(YundaexGrade.INTERVAL.name())){
					score = getScore(ydGradeModel.getDetails(), new BigDecimal(map.get(ydGradeModel.getSecondTarget()).toString()), score, new BigDecimal(ydGradeModel.getWeight()));
					continue;
				}else if(ydGradeModel.getType().equals(YundaexGrade.SINGLE.name())){
					score = getSingleScore(ydGradeModel.getDetails(),map.get(ydGradeModel.getSecondTarget()).toString(), score, new BigDecimal(ydGradeModel.getWeight()));
					continue;
				}else{
					throw WebException.instance("错误的二级指标TYPE");
				}
			}
			return score;
		}else{
			throw WebException.instance("获取评级模型配置表失败");
		}
	}

	/**
	 * 获取 SINGLE("单值类型")的评级分数
	 * @param details
	 * @param param
	 * @param score
	 * @param weight
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
private BigDecimal getSingleScore(String details, String param, BigDecimal score, BigDecimal weight) throws JsonParseException, JsonMappingException, IOException {
	Map<String, String> readValue = MAPPER.readValue(details, Map.class);
	for(String key : readValue.keySet()){
		if(key.indexOf(param)!=-1){
			param = key;
			break;
		}else{
			if("OTHER".equals(key)){
				param = key;
				break;
			}else{
				continue;
			}
		}
	}
	return  new BigDecimal(readValue.get(param)).multiply(weight).add(score);
}
	
	/**
	 * 获取 INTERVAL("区间类型")的评级分数
	 * @param details
	 * @param param
	 * @param score
	 * @param weight
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private BigDecimal getScore(String details, BigDecimal param, BigDecimal score, BigDecimal weight) throws JsonParseException, JsonMappingException, IOException {
		if(StringUtils.isNotBlank(details)){
			Map<String, String> readValue = MAPPER.readValue(details, Map.class);
			for (String key : readValue.keySet()) {
				String[] split = key.split("-");
				// a-b 区间的key
				if (split.length == 2) {
					if (param.compareTo(new BigDecimal(split[0])) >= 0 && param.compareTo(new BigDecimal(split[1])) == -1) {
						// 得分 map.get(key)
						score = new BigDecimal(readValue.get(key)).multiply(weight).add(score);
						break;
					}
				} else {
					if (key.indexOf("<") != -1) {
						if (param.compareTo(new BigDecimal(key.split("<")[1])) == -1) {
							// 得分=map.get(key)
							score = new BigDecimal(readValue.get(key)).multiply(weight).add(score);
							break;
						} else {
							continue;
						}
					} else if (key.indexOf(">") != -1) {
						if (param.compareTo(new BigDecimal(key.split(">")[1])) == 1) {
							// 得分=map.get(key)
							score = new BigDecimal(readValue.get(key)).multiply(weight).add(score);
							break;
						} else {
							continue;
						}
					} else {
						// 没有此key值与参数匹配
						throw WebException.instance("无法解析此key值，key=" + key);
					}
				}
			}
			return score;
		}else{
			throw WebException.instance("此参数:"+param+"的指标信息不存在。");
		}
	}
	
	private Map<String, Object> getSecondTargetValue(String secondTarget, YundaexGradeScoreDTO apply) {
		Class clazz = apply.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object> ();
		for (int i = 0; i < fields.length; i++) {
			try {
				if(fields[i].getName().equals(secondTarget)){
					Object resultObject = invokeMethod(apply, fields[i].getName());
					map.put(fields[i].getName(), resultObject);
					break;
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return map;
	}
	
	private Object invokeMethod(YundaexGradeScoreDTO apply, String fieldname) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class ownerClass = apply.getClass();
		Method method = null;
		method = ownerClass.getMethod(new StringBuffer("get").append(fieldname.substring(0, 1).toUpperCase()).append(fieldname.substring(1)).toString());
		Object object = null;
		object = method.invoke(apply);
		return object;
    }
	
	private YundaexGradeScoreDTO getGradeScoreDTO(YundaexCustomerApply apply, BigDecimal rate) {
		YundaexGradeScoreDTO ydGradeScoreDTO = new YundaexGradeScoreDTO();
		BeanUtils.copyProperties(apply, ydGradeScoreDTO);
		String city = apply.getCity();
		YundaexCompositeCostExample costExample = new YundaexCompositeCostExample();
		costExample.createCriteria().andStationCityLike("%" + city + "%");
		List<YundaexCompositeCost> ydCompositeCosts = ydCompositeCostMapper.selectByExample(costExample);
		if(CollectionUtils.isNotEmpty(ydCompositeCosts)){
			YundaexCompositeCost ydCompositeCost = ydCompositeCosts.get(0);
			String cityLevel = ydCompositeCost.getCityLevel();   		// 城市划分
			ydGradeScoreDTO.setCityLevel(cityLevel);
		}else{
			ydGradeScoreDTO.setCityLevel("OTHER");
		}
		ydGradeScoreDTO.setRecAndSendGrowthRate(rate);
		return ydGradeScoreDTO;
	}

	private BigDecimal getDayRequirements(String stationNo, String currentDate) {
		YundaexStationOperation newestStaionOeration = NewestStationOperationHolder.newestStaionOperation;
		if(newestStaionOeration == null)
			throw WebException.instance("站点经营状况信息为空");
		BigDecimal avgReceiveNo = getAvgReceiveOrSendNo(stationNo,1, currentDate);
		BigDecimal receiveGrowthRate = getReceiveOrSendGrowthRate(stationNo,1, currentDate);
		BigDecimal forecastRecNo = avgReceiveNo.multiply(new BigDecimal(1).add(receiveGrowthRate));
		BigDecimal bail = forecastRecNo.multiply(newestStaionOeration.getSendIncome().add(newestStaionOeration.getTransitFeeBail()).add(newestStaionOeration.getOtherFeeBail()));
		return bail.divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 计算站点月平均揽派件量(本年揽派件量/12)
	 * @param stationNo
	 * @param i 1：揽件 2：派件
	 * @return
	 */
	private BigDecimal getAvgReceiveOrSendNo(String stationNo,Integer j, String currentDate) {
		YundaexTstationInfoExample example = new YundaexTstationInfoExample();
		String beginDate = dateCalculateAndFormat(0, currentDate);
		String endDate = dateCalculateAndFormat(12, currentDate);
		example.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(endDate).andStatmonthLessThan(beginDate);
		List<YundaexTstationInfo> ydTstationInfos = ydTstationMapper.selectByExample(example);
		int yearOfTotalRec = 0;
		int yearOfTotalSend = 0;
		for(YundaexTstationInfo ts : ydTstationInfos){
			yearOfTotalRec += ts.getReceiveTotal();
			yearOfTotalSend += ts.getSendTotal();
		}
		if(1 == j)
			return new BigDecimal(yearOfTotalRec).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
		else
			return new BigDecimal(yearOfTotalSend).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
		
		 
	}
	
	/**
	 * 计算揽派件增长率
	 * @param stationNo
	 * @param i 1：揽件 2：派件
	 * @return
	 */
	private BigDecimal getReceiveOrSendGrowthRate(String stationNo, int i, String currentDate) {
		YundaexTstationInfoExample example1 = new YundaexTstationInfoExample();
		YundaexTstationInfoExample example2 = new YundaexTstationInfoExample();
		String nearBeginDate = dateCalculateAndFormat(12, currentDate);
		String nearEndDate = dateCalculateAndFormat(0, currentDate);
		String farBeginDate = dateCalculateAndFormat(24, currentDate);
		String farEndDate = dateCalculateAndFormat(12, currentDate);
		example1.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(nearBeginDate).andStatmonthLessThan(nearEndDate);
		List<YundaexTstationInfo> nearYdTstationInfos = ydTstationMapper.selectByExample(example1);
		example2.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(farBeginDate).andStatmonthLessThan(farEndDate);
		List<YundaexTstationInfo> farYdTstationInfos = ydTstationMapper.selectByExample(example2);
		int nearOfTotalRec = 0;   //最近12个月的揽件总量
		int nearOfTotalSend = 0;  //最近12个月的派件总量
		for(YundaexTstationInfo ts : nearYdTstationInfos){
			nearOfTotalRec += ts.getReceiveTotal();
			nearOfTotalSend += ts.getSendTotal();
		}
		int farOfTotalRec = 0;    //上12个月的揽件总量
		int farOfTotalSend = 0;   //上12个月的派件总量 
		for(YundaexTstationInfo ts : farYdTstationInfos){
			farOfTotalRec += ts.getReceiveTotal();
			farOfTotalSend += ts.getSendTotal();
		}
		
		if(i == 1)
			return new BigDecimal(nearOfTotalRec).subtract(new BigDecimal(farOfTotalRec)).divide(new BigDecimal(farOfTotalRec==0?1:farOfTotalRec),5,BigDecimal.ROUND_HALF_UP);
		else
			return new BigDecimal(nearOfTotalSend).subtract(new BigDecimal(farOfTotalSend)).divide(new BigDecimal(farOfTotalSend==0?1:farOfTotalSend),5,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 计算站点最近12个月与上12个月揽派件总数的比例
	 * 
	 * @param stationNo
	 * @return
	 */
	private BigDecimal getRecSendRate(String stationNo, String currentDate) {
		String farBeginDate = dateCalculateAndFormat(24, currentDate);
		String farEndDate = dateCalculateAndFormat(12, currentDate);
		YundaexTstationInfoExample farExample = new YundaexTstationInfoExample();
		farExample.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(farBeginDate)
				.andStatmonthLessThan(farEndDate);
		List<YundaexTstationInfo> farStationInfos = ydTstationMapper.selectByExample(farExample);
		if (CollectionUtils.isEmpty(farStationInfos))
			return BigDecimal.ZERO;
		int farTotal = 0;
		for (YundaexTstationInfo stationIfo : farStationInfos) {
			farTotal += stationIfo.getReceiveTotal() + stationIfo.getSendTotal();
		}

		String nearBeginDate = dateCalculateAndFormat(12, currentDate);
		String nearEndDate = dateCalculateAndFormat(0, currentDate);
		YundaexTstationInfoExample nearExample = new YundaexTstationInfoExample();
		nearExample.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(nearBeginDate)
				.andStatmonthLessThan(nearEndDate);
		List<YundaexTstationInfo> nearStationInfos = ydTstationMapper.selectByExample(nearExample);
		if (CollectionUtils.isEmpty(nearStationInfos))
			return BigDecimal.ZERO;
		int nearTotal = 0;
		for (YundaexTstationInfo stationIfon : nearStationInfos) {
			nearTotal += stationIfon.getReceiveTotal() + stationIfon.getSendTotal();
		}
		BigDecimal far = new BigDecimal(farTotal==0?1:farTotal);
		BigDecimal near = new BigDecimal(nearTotal);
		BigDecimal rate = near.subtract(far).divide(far, 5, BigDecimal.ROUND_HALF_UP);
		return rate;
	}
	
	/**
	 * 当前时间 - 指定月份 并转换成当月的第一天，yyyy-MM格式 2016年1月16日 - 0 = 2016-01
	 * 2016年1月16日 - 1 = 2015-12
	 * 
	 * @param minusMonths
	 * @return
	 */
	private String dateCalculateAndFormat(int minusMonths, String currentDate) {
//		Date vaildDate = new DateTime().minusMonths(minusMonths).toDate();
		Date vaildDate = DateTimeUtil.getDate(currentDate, "yyyy-MM-dd").minusMonths(minusMonths).toDate();
		Date vaildDatetruncate = DateTimeUtil.truncate(vaildDate, Calendar.MONTH);
		String vaildDateFormat = DateTimeUtil.format(vaildDatetruncate, "yyyy-MM");
		return vaildDateFormat;
	}
}
