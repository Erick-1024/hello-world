package com.cana.yundaex.service.transaction.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.early.warning.api.IYundaexEarlyWarningApi;
import com.cana.member.api.IUserApi;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.member.dao.po.UserExample;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.transaction.IFinanceReportTransactionService;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.yundaex.common.enums.Institution;
import com.cana.yundaex.common.enums.TimeUnit;
import com.cana.yundaex.common.enums.YundaexApplyType;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexCreditLimitAuditType;
import com.cana.yundaex.common.enums.YundaexCreditLimitGenerateState;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.common.enums.YundaexLimitStatus;
import com.cana.yundaex.common.enums.YundaexMode;
import com.cana.yundaex.common.enums.YundaexMonitor;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.YundaexCompositeCostMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCreditMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMonitorMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerGradeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexTstationInfoMapper;
import com.cana.yundaex.dao.po.YundaexCompositeCost;
import com.cana.yundaex.dao.po.YundaexCompositeCostExample;
import com.cana.yundaex.dao.po.YundaexCreditMonitor;
import com.cana.yundaex.dao.po.YundaexCreditMonitorExample;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitor;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitorExample;
import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexCustomerGradeExample;
import com.cana.yundaex.dao.po.YundaexGradeInfo;
import com.cana.yundaex.dao.po.YundaexStationOperation;
import com.cana.yundaex.dao.po.YundaexTstationInfo;
import com.cana.yundaex.dao.po.YundaexTstationInfoExample;
import com.cana.yundaex.dao.utils.IDGenerator;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.transaction.IYundaexCreditLimitTransactionService;
import com.cana.yundaex.service.transaction.IYundaexInterestRateTransactionService;
import com.cana.yundaex.service.utils.NewestStationOperationHolder;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

@Service
public class YundaexCreditLimitTransactionServiceImpl implements IYundaexCreditLimitTransactionService{

	private  Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private YundaexOutCustomerMapper ydOutCustomerMapper;
	
	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;
	
	@Resource
	private YundaexTstationInfoMapper ydTstationInfoMapper;
	
	@Resource
	private CreditLimitMapper ydCreditLimitMapper;
	
	@Resource
	private CreditLimitAuditMapper ydCreditLimitAuditMapper;
	
	@Resource
	private YundaexCompositeCostMapper ydCompositeCostMapper;
	
	@Resource
	private YundaexCreditMonitorMapper yundaexCreditMonitorMapper;
	
	@Resource
	private YundaexCustomerGradeMapper yundaexCustomerGradeMapper;
	
	@Resource
	private YundaexCustomerApplyMonitorMapper yundaexCustomerApplyMonitorMapper;
	
	@Resource
	private IYundaexRetryTaskService ydRetryTaskService;
	
	@Resource
    private IUserApi userApi;
	
	@Resource
	private IYundaexMessageService messageService;
	
	@Resource
	private IRepositoryService repositoryServiceImpl;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private IYundaexInterestRateTransactionService rateTransactionService;
	
	@Resource
	private IReportApi reportApi;
	
	@Resource
	private IYundaexEarlyWarningApi yundaexEarlyWarningApi;
	
	@Resource
	private IFinanceReportTransactionService financeReportTransactionService;
	
	@Resource
	private IYundaexAutomaticRulesTransactionService yundaexAutomaticRulesTransactionService;
	
	@Resource
	private IYundaexInterestRateTransactionService yundaexInterestRateTransactionService;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	/**
	 * 计算授信额度
	 */
	@Override
	public void calculateApplyCreditLimit(YundaexCustomerApply ydCustomerApply) {
		 String stationNo = ydCustomerApply.getStationNo();
		 Transaction t = Cat.newTransaction("service", "额度计算");
		 t.addData("stationNo",stationNo);
		 
		 //最终额度
		try {
			BigDecimal dayBail = getDayRequirements(stationNo);
			BigDecimal netCashflow = getCompanyCashFlow(ydCustomerApply);
			Long finalLimit = getFinalLimit(netCashflow, dayBail, ydCustomerApply); 
			t.setStatus(Transaction.SUCCESS);
			Cat.logMetricForCount("额度计算成功");
			if(finalLimit>0 || ydCustomerApply.getApplyType().equals(YundaexApplyType.OFFLINE_APPLY.name())){
				String userId = userApi.queryUserIdByCompanyName(ydCustomerApply.getStationName());
				try {
					String generateActivacationURL = userApi.generateActivacationURL(userId);
					// 插入到授信额度表中
					insertYDCreditLimit(ydCustomerApply, userId, finalLimit);
					updateCustomerApply(ydCustomerApply, YundaexCreditLimitGenerateState.FINISH.name());
					// 监控数据保存
					insertIntoCreditMonitor(ydCustomerApply, userId, dayBail, netCashflow ,finalLimit);
					if (StringUtils.isNotBlank(generateActivacationURL)) {
						if(ydCustomerApply.getNotifyFlag()){
							ydRetryTaskService.createAuditResultNotify(Institution.yundaex, stationNo,
									YundaexAuditState.ACCESS.name(), finalLimit, generateActivacationURL);
							messageService.sendAuditSuccessMessageAndMail(ydCustomerApply, MoneyUtil.cent2Yuan(finalLimit),
									generateActivacationURL);
						}
					} else {
						throw WebException.instance("激活链接为空");
					}
				} catch (Exception e) {
					throw WebException.instance("获取激活链接异常" + e);
				}
			}else{
				updateCustomerApply(ydCustomerApply,YundaexCreditLimitGenerateState.NEGATIVE.name());
				if(ydCustomerApply.getNotifyFlag()){
					ydRetryTaskService.createAuditResultNotify(Institution.yundaex, stationNo, YundaexAuditState.NOTACCESS.name(), null, null);
					messageService.sendYundaexAuditResultMessageAndMail(ydCustomerApply);
				}
			}
		} catch (Exception e) {
			logger.error(stationNo + "额度计算失败", e);
			t.setStatus(e);
			Cat.logError(e);
			Cat.logMetricForCount("额度计算失败");
			throw e;
		}finally{
			t.complete();
		}
	}
	
	private void insertIntoCreditMonitor(YundaexCustomerApply ydCustomerApply, String userId, BigDecimal dayBail, BigDecimal netCashflow, Long finalLimit) throws Exception {
		String stationNo = ydCustomerApply.getStationNo();
		// 韵达评价
		YundaexJudge yundaexJudge = YundaexJudge.valueOf(ydCustomerApply.getYundaexJudge()); 
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
		insertIntoCreditMonitorByType(new BigDecimal(judge), userId, stationNo, YundaexMonitor.YUNDAEXJUDGE.name());
		// 揽派件增长率
		BigDecimal recandsendGrowthRate = ydCustomerApply.getRecandsendGrowthRate();
		insertIntoCreditMonitorByType(recandsendGrowthRate, userId, stationNo, YundaexMonitor.RECANDSEND_GROWTHRATE.name());
		// 日资金需求
//		BigDecimal dayBail = getDayRequirements(stationNo);
		insertIntoCreditMonitorByType(dayBail.multiply(new BigDecimal(100)), userId, stationNo, YundaexMonitor.DAY_REQUIREMENTS.name());
		// 韵达评分
		BigDecimal score = getYundaexScore(stationNo);
		insertIntoCreditMonitorByType(score, userId, stationNo, YundaexMonitor.YUNDAEXGRADE.name());
		// 保证金余额
		Long bailBalance = ydCustomerApply.getBailBalance()==null?0:ydCustomerApply.getBailBalance(); 
		insertIntoCreditMonitorByType(new BigDecimal(bailBalance), userId, stationNo, YundaexMonitor.BAILBALANCE.name());
		// 短期借款
		Long shortLoan = ydCustomerApply.getShortLoan()==null?0:ydCustomerApply.getShortLoan();
		insertIntoCreditMonitorByType(new BigDecimal(shortLoan), userId, stationNo, YundaexMonitor.SHORTLOAN.name());
		// 净现金流
//		BigDecimal netCashflow = getCompanyCashFlow(ydCustomerApply);
		insertIntoCreditMonitorByType(netCashflow.multiply(new BigDecimal(100)), userId, stationNo, YundaexMonitor.NET_CASHFLOW.name());
		// 最大授信金额
		insertIntoCreditMonitorByType(new BigDecimal(finalLimit), userId, stationNo, YundaexMonitor.CREDIT_LIMIT.name());
		// 逾期次数
		int overDues = getCountOverduePlans(userId);
		insertIntoCreditMonitorByType(new BigDecimal(overDues), userId, stationNo, YundaexMonitor.OVERDUES.name());
		// 首次申请 计算韵达系统预警事件
		yundaexEarlyWarningApi.reckonYundaexFirstApplyEarlyWarning(recandsendGrowthRate, dayBail, score, bailBalance, 
				shortLoan, netCashflow, finalLimit, overDues, userId, stationNo, ydCustomerApply.getStationName());
	}

	private int getCountOverduePlans(String userId) throws Exception{
		String currentDate = vbamCommonServiceImpl.getCurrentDate();
		QueryPlanListRequest request = new QueryPlanListRequest();
		request.setFinanceId(userId);
		request.setBusinessProductId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		request.setEndDate(DateTimeUtil.format(DateTimeUtil.truncate(DateUtils.getDate(currentDate, "yyyy-MM-dd"), Calendar.MONTH), "yyyy-MM-dd"));
		int countOverduePlans = financeReportTransactionService.countOverduePlans(request);
		return countOverduePlans;
	}

	private BigDecimal getYundaexScore(String stationNo) {
		YundaexCustomerGradeExample example = new YundaexCustomerGradeExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexCustomerGrade> grades = yundaexCustomerGradeMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(grades))
			return grades.get(0).getPoints();
		throw WebException.instance("韵达评分异常");
	}

	private void insertIntoCreditMonitorByType(BigDecimal param, String memberId, String stationNo, String type) {
		String currentDate = vbamCommonServiceImpl.getCurrentDate();
		reportApi.save(param, memberId, stationNo, Constants.YUNDAEX_ASSET_PROJECT_ID, type, currentDate);
	}
	
	/**
	 * 更新yundaexCustomerApply表中的CreditLimitGenerateState
	 * @param ydCustomerApply
	 */
	private void updateCustomerApply(YundaexCustomerApply ydCustomerApply, String state) {
		ydCustomerApply.setCreditLimitGenerateState(state);
		ydCustomerApplyMapper.updateByPrimaryKeySelective(ydCustomerApply);
	}

	/**
	 * 插入到授信额度表中
	 * @param ydCustomerApply
	 * @param memberId
	 * @param finalLimit
	 */
	private void insertYDCreditLimit(YundaexCustomerApply ydCustomerApply, String memberId, Long finalLimit) {
		CreditLimit ydCreditmit = new CreditLimit();
		ydCreditmit.setId(IDGenerator.generateCreditLimitId());
		ydCreditmit.setMemberId(memberId);
		ydCreditmit.setProjectId(Constants.YUNDAEX_ASSET_PROJECT_ID);
		ydCreditmit.setCompanyName(ydCustomerApply.getStationName());
		ydCreditmit.setCreditMode(YundaexMode.SYNTHETICAL.name());
		ydCreditmit.setTotalLimit(finalLimit);
		ydCreditmit.setUsedLimit(0l);
		ydCreditmit.setStatus(YundaexLimitStatus.INACTIVE.name());
		ydCreditmit.setOutCustomerId(ydCustomerApply.getStationNo());
		ydCreditmit.setOutCustomerName(ydCustomerApply.getStationName());
		ydCreditmit.setCreateTime(new Date());
		ydCreditmit.setUpdateTime(ydCreditmit.getCreateTime());
		ydCreditLimitMapper.insertSelective(ydCreditmit);
		
		CreditLimitAudit ydCreditLimitAudit = new CreditLimitAudit();
		ydCreditLimitAudit.setId(IDGenerator.generateCreditLimitAuditId());
		ydCreditLimitAudit.setLimitId(ydCreditmit.getId());
		ydCreditLimitAudit.setType(YundaexCreditLimitAuditType.CREATE.name()); 
		ydCreditLimitAudit.setPrevTotalLimit(0L);
		ydCreditLimitAudit.setTotalLimit(ydCreditmit.getTotalLimit()); 
		ydCreditLimitAudit.setPrevUsedLimit(0L); 
		ydCreditLimitAudit.setUsedLimit(0L); 
		ydCreditLimitAudit.setCreateTime(new Date());
		ydCreditLimitAuditMapper.insertSelective(ydCreditLimitAudit);
	}

	private BigDecimal getDayRequirements(String stationNo) {
		YundaexStationOperation newestStaionOeration = NewestStationOperationHolder.newestStaionOperation;
		if(newestStaionOeration == null)
			throw WebException.instance("站点经营状况信息为空");
		BigDecimal avgReceiveNo = getAvgReceiveOrSendNo(stationNo,1);
		BigDecimal receiveGrowthRate = getReceiveOrSendGrowthRate(stationNo,1);
		BigDecimal forecastRecNo = avgReceiveNo.multiply(new BigDecimal(1).add(receiveGrowthRate));
		BigDecimal bail = forecastRecNo.multiply(newestStaionOeration.getSendIncome().add(newestStaionOeration.getTransitFeeBail()).add(newestStaionOeration.getOtherFeeBail()));
		return bail.divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal getCompanyCashFlow(YundaexCustomerApply yundaexCustomerApply) {
		BigDecimal shortLoan = new BigDecimal(yundaexCustomerApply.getShortLoan()==null?0:yundaexCustomerApply.getShortLoan());
		BigDecimal bailBalance = new BigDecimal(yundaexCustomerApply.getBailBalance()==null?0:yundaexCustomerApply.getBailBalance());
	    shortLoan = shortLoan.divide(new BigDecimal(100));
	    bailBalance = bailBalance.divide(new BigDecimal(100));
	    YundaexStationOperation newestStaionOperation = NewestStationOperationHolder.newestStaionOperation;
	    if(newestStaionOperation == null)
			throw WebException.instance("站点经营状况信息为空");
	    //月度平均揽件数量
  		BigDecimal avgReceiveNo = getAvgReceiveOrSendNo(yundaexCustomerApply.getStationNo(),1);
  		//月度平均派件数量
  		BigDecimal avgSendNo = getAvgReceiveOrSendNo(yundaexCustomerApply.getStationNo(),2);
  		
  		//揽件预计增长率
  		BigDecimal receiveGrowthRate = getReceiveOrSendGrowthRate(yundaexCustomerApply.getStationNo(),1);
  		//派件预计增长率
  		BigDecimal sendGrowthRate = getReceiveOrSendGrowthRate(yundaexCustomerApply.getStationNo(),2);
  		
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
  		BigDecimal monthShortLoan = getMonthShortLoan(shortLoan, shortLoanLimit, limitUnit);
  		
  		//企业经营现金流 (当月揽件净收入+当月派件净收入-当月支出小计-短期借款/每月)
  		BigDecimal companyCash = netIncomeRec.add(netIncomeSend).subtract(payment).subtract(monthShortLoan);
  		
		return companyCash;
	}
	
	
	/**
	 * 计算最终额度
	 * @param id
	 * @return
	 */
	private Long getFinalLimit(BigDecimal netCashflowGrowth, BigDecimal dayBail, YundaexCustomerApply ydCustomerApply) {
		/*if(ydCustomerApply == null)
			throw WebException.instance("授信客户的申请不存在");
		BigDecimal shortLoan = new BigDecimal(ydCustomerApply.getShortLoan()==null?0:ydCustomerApply.getShortLoan());
		BigDecimal bailBalance = new BigDecimal(ydCustomerApply.getBailBalance()==null?0:ydCustomerApply.getBailBalance());
		String id = ydCustomerApply.getId();
	    shortLoan = shortLoan.divide(new BigDecimal(100));
	    bailBalance = bailBalance.divide(new BigDecimal(100));
		YundaexStationOperation newestStaionOperation = NewestStationOperationHolder.newestStaionOperation;
		if(newestStaionOperation == null)
			throw WebException.instance("站点经营状况信息为空");
		//月度平均揽件数量
		BigDecimal avgReceiveNo = getAvgReceiveOrSendNo(ydCustomerApply.getStationNo(),1);
		//月度平均派件数量
		BigDecimal avgSendNo = getAvgReceiveOrSendNo(ydCustomerApply.getStationNo(),2);
		
		//揽件预计增长率
		BigDecimal receiveGrowthRate = getReceiveOrSendGrowthRate(ydCustomerApply.getStationNo(),1);
		//派件预计增长率
		BigDecimal sendGrowthRate = getReceiveOrSendGrowthRate(ydCustomerApply.getStationNo(),2);
		
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
		BigDecimal payment = getPayment(ydCustomerApply);
		
		Integer shortLoanLimit = ydCustomerApply.getShortLoanLimit();
  		String limitUnit = ydCustomerApply.getLimitUnit();
  		BigDecimal monthShortLoan = getMonthShortLoan(shortLoan, shortLoanLimit, limitUnit);
		
		//企业经营现金流 (当月揽件净收入+当月派件净收入-当月支出小计-短期借款/3)
		BigDecimal companyCash = netIncomeRec.add(netIncomeSend).subtract(payment).subtract(monthShortLoan);
		
		//保证金总需求（月）
		BigDecimal bail = forecastRecNo.multiply(newestStaionOperation.getSendIncome().add(newestStaionOperation.getTransitFeeBail()).add(newestStaionOperation.getOtherFeeBail()));
		
		//15天保证金需求 (/30*15)
		BigDecimal bailHalfMonth = (bail.subtract(bailBalance)).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
		
		//获取授信额度 min（企业经营净现金流，15天保证金需求），向下取整到千位，上限50W;
		BigDecimal finalLimit = getCreditLimit(companyCash,bailHalfMonth);*/
		BigDecimal bailHalfMonth = dayBail.multiply(new BigDecimal(15));
		int n = netCashflowGrowth.compareTo(bailHalfMonth);
		BigDecimal finalLimit = BigDecimal.ZERO;
		if(n <= 0)
			finalLimit = getMinCreditLimit(netCashflowGrowth);
		else 
			finalLimit = getMinCreditLimit(bailHalfMonth);
		
		StringBuffer limitInfoRemarks = new StringBuffer();
		limitInfoRemarks.append("companyCash="+netCashflowGrowth).append(",bailHalfMonth="+bailHalfMonth).append(",finalLimit="+MoneyUtil.yuan2Cent(finalLimit));
		ydCustomerApply.setLimitInfoRemarks(limitInfoRemarks.toString());
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andIdEqualTo(ydCustomerApply.getId());
		ydCustomerApplyMapper.updateByExampleSelective(ydCustomerApply, example);
		
		return MoneyUtil.yuan2Cent(finalLimit);
		
	}

	private BigDecimal getMonthShortLoan(BigDecimal shortLoan, Integer shortLoanLimit, String limitUnit) {
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
		throw WebException.instance("借款期限单位异常："+limitUnit);
	}

	/**
	 * 根据综合成本配置表，计算当月支出成本
	 * @param ydCustomerApply
	 * @return
	 */
	private BigDecimal getPayment(YundaexCustomerApply ydCustomerApply) {
		String city = ydCustomerApply.getCity(); 
		List<YundaexCompositeCost> ydCompositeCosts = getComposite(city);
		if(CollectionUtils.isNotEmpty(ydCompositeCosts)){
			YundaexCompositeCost ydCompositeCost = ydCompositeCosts.get(0);
			return ydCompositeCost.getRentalCost().add(ydCompositeCost.getTransportCost()).add(ydCompositeCost.getDefectCost());
		}else{
			List<YundaexCompositeCost> composite = getComposite("OTHER"); 
			return composite.get(0).getRentalCost().add(composite.get(0).getTransportCost()).add(composite.get(0).getDefectCost());
		}
	}

	private List<YundaexCompositeCost> getComposite(String pram) {
		YundaexCompositeCostExample example = new YundaexCompositeCostExample();
		if (StringUtils.isNotBlank(pram)) {
			example.createCriteria().andStationCityLike("%"+pram+"%");
		}
		List<YundaexCompositeCost> ydCompositeCosts = ydCompositeCostMapper.selectByExample(example);
		return ydCompositeCosts;
	}

	private BigDecimal getMinCreditLimit(BigDecimal param) {
		BigDecimal creditLimit = param.divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(10000));
		if(creditLimit.compareTo(new BigDecimal(Constants.YUNDAEX_MAX_CREDIT_LIMIT))<=0)
			return creditLimit;
		else 
			return new BigDecimal(Constants.YUNDAEX_MAX_CREDIT_LIMIT);
	}

	/**
	 * 计算揽派件增长率
	 * @param stationNo
	 * @param i 1：揽件 2：派件
	 * @return
	 */
	private BigDecimal getReceiveOrSendGrowthRate(String stationNo, int i) {
		YundaexTstationInfoExample example1 = new YundaexTstationInfoExample();
		YundaexTstationInfoExample example2 = new YundaexTstationInfoExample();
		String nearBeginDate = dateCalculateAndFormat(12);
		String nearEndDate = dateCalculateAndFormat(0);
		String farBeginDate = dateCalculateAndFormat(24);
		String farEndDate = dateCalculateAndFormat(12);
		example1.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(nearBeginDate).andStatmonthLessThan(nearEndDate);
		List<YundaexTstationInfo> nearYdTstationInfos = ydTstationInfoMapper.selectByExample(example1);
		example2.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(farBeginDate).andStatmonthLessThan(farEndDate);
		List<YundaexTstationInfo> farYdTstationInfos = ydTstationInfoMapper.selectByExample(example2);
		
		if(i == 1){
			int nearOfTotalRec = 0;   //最近12个月的揽件总量
			for(YundaexTstationInfo ts : nearYdTstationInfos){
				nearOfTotalRec += ts.getReceiveTotal();
			}
			int farOfTotalRec = 0;    //上12个月的揽件总量
			for(YundaexTstationInfo ts : farYdTstationInfos){
				farOfTotalRec += ts.getReceiveTotal();
			}
			return new BigDecimal(nearOfTotalRec).subtract(new BigDecimal(farOfTotalRec)).divide(new BigDecimal(farOfTotalRec==0?1:farOfTotalRec),5,BigDecimal.ROUND_HALF_UP);
		}
		else{
			int nearOfTotalSend = 0;  //最近12个月的派件总量
			for(YundaexTstationInfo ts : nearYdTstationInfos){
				nearOfTotalSend += ts.getSendTotal();
			}
			int farOfTotalSend = 0;   //上12个月的派件总量 
			for(YundaexTstationInfo ts : farYdTstationInfos){
				farOfTotalSend += ts.getSendTotal();
			}
			return new BigDecimal(nearOfTotalSend).subtract(new BigDecimal(farOfTotalSend)).divide(new BigDecimal(farOfTotalSend==0?1:farOfTotalSend),5,BigDecimal.ROUND_HALF_UP);
		}
	}

	/**
	 * 计算站点月平均揽派件量(本年揽派件量/12)
	 * @param stationNo
	 * @param i 1：揽件 2：派件
	 * @return
	 */
	private BigDecimal getAvgReceiveOrSendNo(String stationNo,Integer j) {
		YundaexTstationInfoExample example = new YundaexTstationInfoExample();
		String beginDate = dateCalculateAndFormat(0);
		String endDate = dateCalculateAndFormat(12);
		example.createCriteria().andStationNoEqualTo(stationNo).andStatmonthGreaterThanOrEqualTo(endDate).andStatmonthLessThan(beginDate);
		List<YundaexTstationInfo> ydTstationInfos = ydTstationInfoMapper.selectByExample(example);
		if(1 == j){
			int yearOfTotalRec = 0;
			for(YundaexTstationInfo ts : ydTstationInfos){
				yearOfTotalRec += ts.getReceiveTotal();
			}
			return new BigDecimal(yearOfTotalRec).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
		}
		else{
			int yearOfTotalSend = 0;
			for(YundaexTstationInfo ts : ydTstationInfos){
				yearOfTotalSend += ts.getSendTotal();
			}
			return new BigDecimal(yearOfTotalSend).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
		}
	}
	
	/**
	 * 当前时间 - 指定月份 并转换成当月的第一天，yyyy-MM格式
	 * 2016年1月16日 - 0 = 2016-01
	 * 2016年1月16日 - 1 = 2015-12
	 * @param minusMonths
	 * @return
	 */
	private String dateCalculateAndFormat(int minusMonths){
		Date vaildDate = new DateTime().minusMonths(minusMonths).toDate();
		Date vaildDatetruncate = DateTimeUtil.truncate(vaildDate, Calendar.MONTH);
		String vaildDateFormat = DateTimeUtil.format(vaildDatetruncate, "yyyy-MM");
		return vaildDateFormat;
	}

	/**
	 * 额度激活
	 */
	@Override
	public void activateCreditLimit(String memberId) {

		CreditLimit creditLimit = lockCreditLimit(memberId, YundaexMode.SYNTHETICAL);
		if(CreditLimitStatus.NORMAL.name().equals(creditLimit.getStatus()))
			return;
		if(!CreditLimitStatus.INACTIVE.name().equals(creditLimit.getStatus()))
			throw WebException.instance(ReturnCode.YP7030);
		creditLimit.setStatus(CreditLimitStatus.NORMAL.name());
		creditLimit.setEffectiveDate(new Date());
		creditLimit.setUpdateTime(creditLimit.getEffectiveDate());
		ydCreditLimitMapper.updateByPrimaryKey(creditLimit);
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andIdEqualTo(memberId);
		List<User> users = userMapper.selectByExample(userExample);
		
		UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
		userUpdateDTO.setId(memberId);
//		userUpdateDTO.setRoleId(repositoryServiceImpl.getYundaexFinanceProduct().getFinaceRoleId());
		userUpdateDTO.setRoleId(Constants.YUNDAEX_YUNDAEX_FINACE_ID);
		ydRetryTaskService.createUpdateUserRole(userUpdateDTO);
		
		rateTransactionService.computeAndSaveInterestRate(memberId);
		
		if(CollectionUtils.isNotEmpty(users)){
			User user = users.get(0);
			//发送激活额度通知
			messageService.sendCreditActiveMessageAndMail(user);
		}
		//通知韵达额度生效
		//YundaexOutCustomerExample ydOutCustomerExample = new YundaexOutCustomerExample();
		//ydOutCustomerExample.createCriteria().andMemberIdEqualTo(memberId);
		//ydRetryTaskService.createCreditLimiteffect(Institution.yundaex, ydOutCustomerMapper.selectByExample(ydOutCustomerExample).get(0).getStationNo(), creditLimit.getTotalLimit(), ReturnCode.SUCCESS.getRetCode(), ReturnCode.SUCCESS.getRetMsg());
	}

	private CreditLimit lockCreditLimit(String memberId, YundaexMode creditMode) {
		CreditLimitExample example = new CreditLimitExample();
		example.createCriteria().andProjectIdEqualTo(Constants.YUNDAEX_ASSET_PROJECT_ID)
				.andMemberIdEqualTo(memberId).andCreditModeEqualTo(creditMode.name());
		List<CreditLimit> creditLimits = ydCreditLimitMapper.lockByExample(example);
		if(creditLimits == null || creditLimits.isEmpty())
			throw WebException.instance(ReturnCode.YP7012);
		if(creditLimits.size() > 1)
			throw WebException.instance(ReturnCode.YP7014);
		return creditLimits.get(0);
	}

	/**
	 * 授信审核 驳回
	 */
	@Override
	public void creditAuditReject(String id) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("id为空");
		if("ALL".equals(id))
			rejectAll();
		else{
			rejectById(id);
		}
	}

	private void rejectById(String id) {
		YundaexCreditMonitor yundaexCreditMonitor = yundaexCreditMonitorMapper.selectByPrimaryKey(id);
		if(null == yundaexCreditMonitor)
			throw WebException.instance("该授信审核不存在");
		yundaexCreditMonitor.setAuditStatus(YundaexAuditState.NOTACCESS.name());
		yundaexCreditMonitorMapper.updateByPrimaryKeySelective(yundaexCreditMonitor);
	}

	private void rejectAll() {
		YundaexCreditMonitorExample creditMonitorExample = new YundaexCreditMonitorExample();
		creditMonitorExample.createCriteria().andAuditStatusEqualTo(YundaexAuditState.WAIT.name());
		YundaexCreditMonitor yundaexCreditMonitor = new YundaexCreditMonitor();
		yundaexCreditMonitor.setAuditStatus(YundaexAuditState.NOTACCESS.name());
		yundaexCreditMonitorMapper.updateByExampleSelective(yundaexCreditMonitor, creditMonitorExample);
		
		/*List<YundaexCreditMonitor> creditMonitors = yundaexCreditMonitorMapper.selectByExample(creditMonitorExample);
		if(CollectionUtils.isNotEmpty(creditMonitors)){
			for(YundaexCreditMonitor YundaexCreditMonitor : creditMonitors){
				YundaexCreditMonitor.setAuditStatus(YundaexAuditState.NOTACCESS.name());
				yundaexCreditMonitorMapper.updateByPrimaryKeySelective(YundaexCreditMonitor);
			}
		}*/
	}

	/**
	 * 授信审核 通过
	 */
	@Override
	public void creditAuditPass(String id) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("id为空");
		if("ALL".equals(id))
			passAll();
		else{
			passById(id);
		}
	}
	
	private void passAll() {
		YundaexCreditMonitorExample creditMonitorExample = new YundaexCreditMonitorExample();
		creditMonitorExample.createCriteria().andAuditStatusEqualTo(YundaexAuditState.WAIT.name());
		List<YundaexCreditMonitor> creditMonitors = yundaexCreditMonitorMapper.selectByExample(creditMonitorExample);
		if(CollectionUtils.isNotEmpty(creditMonitors)){
			for(YundaexCreditMonitor yundaexCreditMonitor : creditMonitors){
				// 修改申请表
				updateYundaexCustomerApply(yundaexCreditMonitor);
				// 修改客户评级表
				updateYundaexCustomerGrade(yundaexCreditMonitor);
				// 修改利率
				updateYundaexInterestRate(yundaexCreditMonitor);
				// 修改额度表
				updateCreditLimit(yundaexCreditMonitor);
				
				/*yundaexCreditMonitor.setAuditStatus(YundaexAuditState.ACCESS.name());
				yundaexCreditMonitorMapper.updateByPrimaryKeySelective(yundaexCreditMonitor);*/
			}
		}
		YundaexCreditMonitor yundaexCreditMonitor = new YundaexCreditMonitor();
		yundaexCreditMonitor.setAuditStatus(YundaexAuditState.ACCESS.name());
		yundaexCreditMonitorMapper.updateByExampleSelective(yundaexCreditMonitor, creditMonitorExample);
	}

	private void passById(String id) {
		YundaexCreditMonitor yundaexCreditMonitor = yundaexCreditMonitorMapper.selectByPrimaryKey(id);
		if(null == yundaexCreditMonitor)
			throw WebException.instance("该授信审核不存在");
		// 修改申请表
		updateYundaexCustomerApply(yundaexCreditMonitor);
		// 修改客户评级表
		updateYundaexCustomerGrade(yundaexCreditMonitor);
		// 修改利率
		updateYundaexInterestRate(yundaexCreditMonitor);
		// 修改额度表
		updateCreditLimit(yundaexCreditMonitor);
		
		yundaexCreditMonitor.setAuditStatus(YundaexAuditState.ACCESS.name());
		yundaexCreditMonitorMapper.updateByPrimaryKeySelective(yundaexCreditMonitor);
	}

	private void updateCreditLimit(YundaexCreditMonitor yundaexCreditMonitor) {
		String memberId = yundaexCreditMonitor.getMemberId(); 
		CreditLimitExample creditLimitExample = new CreditLimitExample();
		creditLimitExample.createCriteria().andMemberIdEqualTo(memberId).andProjectIdEqualTo(Constants.YUNDAEX_ASSET_PROJECT_ID);
		CreditLimit creditLimit = new CreditLimit();
		creditLimit.setTotalLimit(yundaexCreditMonitor.getCreditLimit());
		ydCreditLimitMapper.updateByExampleSelective(creditLimit, creditLimitExample);
		
	}

	private void updateYundaexInterestRate(YundaexCreditMonitor yundaexCreditMonitor) {
		yundaexInterestRateTransactionService.updateInterestRate(yundaexCreditMonitor.getMemberId(), yundaexCreditMonitor.getInterestRate());
	}

	private void updateYundaexCustomerGrade(YundaexCreditMonitor yundaexCreditMonitor) {
		YundaexGradeInfo yundaexGradeInfo = yundaexAutomaticRulesTransactionService.getYundaexGradeInfoByScore(yundaexCreditMonitor.getYundaexGrade());
		
		YundaexCustomerGradeExample gradeExample = new YundaexCustomerGradeExample();
		gradeExample.createCriteria().andStationNoEqualTo(yundaexCreditMonitor.getStationNo());
		List<YundaexCustomerGrade> customerGrades = yundaexCustomerGradeMapper.selectByExample(gradeExample);
		if(CollectionUtils.isEmpty(customerGrades))
			throw WebException.instance(yundaexCreditMonitor.getStationNo()+":申请客户的评级信息不存在");
		YundaexCustomerGrade yundaexCustomerGrade = customerGrades.get(0);
		yundaexCustomerGrade.setPoints(yundaexCreditMonitor.getYundaexGrade());
		yundaexCustomerGrade.setGrade(yundaexGradeInfo.getGrade());
		yundaexCustomerGrade.setBeta(yundaexGradeInfo.getBeta());
		yundaexCustomerGrade.setRaito(yundaexGradeInfo.getRatio());
		yundaexCustomerGradeMapper.updateByPrimaryKeySelective(yundaexCustomerGrade);
	}

	private void updateYundaexCustomerApply(YundaexCreditMonitor yundaexCreditMonitor) {
		String currentDate = vbamCommonServiceImpl.getCurrentDate();
		String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
		YundaexCustomerApplyMonitorExample customerApplyMonitorExample = new YundaexCustomerApplyMonitorExample();
		customerApplyMonitorExample.createCriteria().andStationNoEqualTo(yundaexCreditMonitor.getStationNo()).andMonthEqualTo(virtualDate).andStatusEqualTo(YundaexAuditState.ACCESS.name());
		List<YundaexCustomerApplyMonitor> customerApplyMonitors = yundaexCustomerApplyMonitorMapper.selectByExample(customerApplyMonitorExample);
		
		// 需要修改的申请信息
		YundaexCustomerApplyExample applyExample = new YundaexCustomerApplyExample();
		applyExample.createCriteria().andStationNoEqualTo(yundaexCreditMonitor.getStationNo());
		List<YundaexCustomerApply> applys = ydCustomerApplyMapper.selectByExample(applyExample);
		if(CollectionUtils.isEmpty(applys))
			throw WebException.instance(yundaexCreditMonitor.getStationNo()+":申请客户不存在");
		YundaexCustomerApply yundaexCustomerApply = applys.get(0);
		
		if(CollectionUtils.isNotEmpty(customerApplyMonitors)){
			yundaexCustomerApply.setStationAmount(customerApplyMonitors.get(0).getStationAmount());
			yundaexCustomerApply.setStationMgr(customerApplyMonitors.get(0).getStationMgr());
			yundaexCustomerApply.setStationName(customerApplyMonitors.get(0).getStationName());
			yundaexCustomerApply.setBusiLimit(customerApplyMonitors.get(0).getBusiLimit());
			yundaexCustomerApply.setBailBalance(customerApplyMonitors.get(0).getBailBalance());
			yundaexCustomerApply.setShortLoan(customerApplyMonitors.get(0).getShortLoan());
			yundaexCustomerApply.setLoanType(customerApplyMonitors.get(0).getLoanType());
			yundaexCustomerApply.setShortLoanLimit(customerApplyMonitors.get(0).getLoanLimit());
			yundaexCustomerApply.setLimitUnit(customerApplyMonitors.get(0).getLimitUnit());
			yundaexCustomerApply.setYundaexJudge(customerApplyMonitors.get(0).getYundaexJudge());
		}
		yundaexCustomerApply.setBailRatio(new BigDecimal(yundaexCreditMonitor.getBailBalance()).divide(new BigDecimal(yundaexCreditMonitor.getDayRequirements()), 4, BigDecimal.ROUND_HALF_UP));
		yundaexCustomerApply.setRecandsendGrowthRate(yundaexCreditMonitor.getRecandsendGrowthRate());
		yundaexCustomerApply.setUpdateTime(new Date());
		
		ydCustomerApplyMapper.updateByPrimaryKeySelective(yundaexCustomerApply);
	}

}
