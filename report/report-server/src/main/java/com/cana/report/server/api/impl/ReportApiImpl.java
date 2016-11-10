/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.server.api.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import com.cana.credit.api.ICreditApi;
import com.cana.report.api.IReportApi;
import com.cana.report.dao.mapper.IReportFactorFinanceSummarySearchMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceCountMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceDayMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceYearMapper;
import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceCountExample;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceDayExample;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.report.dao.po.ReportFactorFinanceYearExample;
import com.cana.report.service.IAccountFundReportService;
import com.cana.report.service.IReportMonitorService;
import com.cana.report.service.transaction.IFundReportTransactionService;
import com.cana.report.service.transaction.IMonitorReportTransactionService;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfo;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfoQueryCriteria;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitRequest;
import com.cana.vbam.common.credit.enums.CreditLimitUsedStatus;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;
import com.cana.vbam.common.report.dto.AccountFundYearReportQueryDTO;
import com.cana.vbam.common.report.dto.MonitorDataData;
import com.cana.vbam.common.report.dto.MonitorDataDataDTO;
import com.cana.vbam.common.report.dto.MonitorMetricAndDataQueryDTO;
import com.cana.vbam.common.report.dto.MonitorMetricAndDataQueryYundaDTO;
import com.cana.vbam.common.report.dto.MonitorMetricData;
import com.cana.vbam.common.report.dto.MonitorMetricDataDTO;
import com.cana.vbam.common.report.dto.MonitorMetricDataYunda;
import com.cana.vbam.common.report.dto.MonitorMetricDataYundaDTO;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryQueryDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryQueryYundaDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryYundaDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundCountDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceCountDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceDayDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceSearchCriteria;
import com.cana.vbam.common.report.dto.ReportFactorFinanceYearDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlyResultDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlySearchCriteria;
import com.cana.vbam.common.report.enums.BusinessProduct;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.yundaex.api.IYundaexMonitorApi;
import com.cana.yundaex.common.dto.monitor.YundaexmonitorGradeInfoDTO;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
public class ReportApiImpl implements IReportApi {

	@Resource
	private IAccountFundReportService accountFundReportService;
	
	@Resource
	private ReportFactorFinanceDayMapper reportFactorFinanceDayMapper;
	
	@Resource
	private ReportFactorFinanceYearMapper reportFactorFinanceYearMapper;
	
	@Resource
	private ReportFactorFinanceCountMapper reportFactorFinanceCountMapper;

	@Resource
	private IReportFactorFinanceSummarySearchMapper reportFactorFinanceSummarySearchMapper;

	@Resource
	private IReportMonitorService reportMonitorServiceImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@Resource
	private IFundReportTransactionService fundReportTransactionService;
	
	@Resource
	private IVbamCommonService commonService;
	
	@Resource
	private IMonitorReportTransactionService monitorReportTransactionServiceImpl;
	
	@Resource
	private IVbamCommonService vbamCommonService;
	
	@Resource
	private IYundaexMonitorApi yundaexMonitorApi;
	
	@Override
	public ListResult<ReportFactorFinanceDayDTO> queryRepaymentDailyReport(ReportFactorFinanceSearchCriteria searchCriteria) {
		if(StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			List<ReportFactorFinanceDay> reportFactorFinanceDays = reportFactorFinanceSummarySearchMapper.
					getSummaryReportDayByCondition(searchCriteria.getStartTime(), searchCriteria.getEndTime(), searchCriteria.getMasterId(), 
							searchCriteria.getPage(), searchCriteria.getPageSize());
			return ListResult.success(convertToReportFactorFinanceDayDTO(reportFactorFinanceDays), 
					reportFactorFinanceSummarySearchMapper.countSummaryReportDayByCondition(searchCriteria.getStartTime(), 
							searchCriteria.getEndTime(), searchCriteria.getMasterId()).size());
		}
		ReportFactorFinanceDayExample example = assembleDaySearchCriteria(searchCriteria);
		example.setLimitStart((searchCriteria.getPage() - 1) * searchCriteria.getPageSize());
		example.setLimitEnd(searchCriteria.getPageSize());
		example.setOrderByClause("report_date desc");
		List<ReportFactorFinanceDay> reportFactorFinanceDays = reportFactorFinanceDayMapper.selectByExample(example);
		int totalNum = reportFactorFinanceDayMapper.countByExample(example);
		return ListResult.success(convertToReportFactorFinanceDayDTO(reportFactorFinanceDays), totalNum);
	}
	
	@Override
	public List<ReportFactorFinanceDayDTO> queryRepaymentDailyReportNoPaging(ReportFactorFinanceSearchCriteria searchCriteria) {
		if(StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			List<ReportFactorFinanceDay> reportFactorFinanceDays = reportFactorFinanceSummarySearchMapper.
					countSummaryReportDayByCondition(searchCriteria.getStartTime(), searchCriteria.getEndTime(), searchCriteria.getMasterId());
			return convertToReportFactorFinanceDayDTO(reportFactorFinanceDays);
		}
		ReportFactorFinanceDayExample example = assembleDaySearchCriteria(searchCriteria);
		example.setOrderByClause("report_date desc");
		return convertToReportFactorFinanceDayDTO(reportFactorFinanceDayMapper.selectByExample(example));
	}
	
	@Override
	public ListResult<ReportFactorFinanceYearDTO> queryRepaymentAnnualReport(ReportFactorFinanceSearchCriteria searchCriteria) {
		if(StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			List<ReportFactorFinanceYear> reportFactorFinanceYears = reportFactorFinanceSummarySearchMapper.
					getSummaryReportYearByCondition(searchCriteria.getStartTime(), searchCriteria.getEndTime(), searchCriteria.getMasterId(), 
							searchCriteria.getPage(), searchCriteria.getPageSize());
			return ListResult.success(convertToReportFactorFinanceYearDTO(reportFactorFinanceYears), 
					reportFactorFinanceSummarySearchMapper.countSummaryReportYearByCondition(searchCriteria.getStartTime(), 
							searchCriteria.getEndTime(), searchCriteria.getMasterId()).size());
		}
		ReportFactorFinanceYearExample example = assembleYearSearchCriteria(searchCriteria);
		example.setLimitStart((searchCriteria.getPage() - 1) * searchCriteria.getPageSize());
		example.setLimitEnd(searchCriteria.getPageSize());
		example.setOrderByClause("report_date desc");
		example.setOrderByClause("id");
		List<ReportFactorFinanceYear> reportFactorFinanceYears = reportFactorFinanceYearMapper.selectByExample(example);
		int totalNum = reportFactorFinanceYearMapper.countByExample(example);
		return ListResult.success(convertToReportFactorFinanceYearDTO(reportFactorFinanceYears), totalNum);
	}

	@Override
	public List<ReportFactorFinanceYearDTO> queryRepaymentAnnualReportNoPaging(ReportFactorFinanceSearchCriteria searchCriteria){
		if(StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			List<ReportFactorFinanceYear> reportFactorFinanceYears = reportFactorFinanceSummarySearchMapper.
					countSummaryReportYearByCondition(searchCriteria.getStartTime(), searchCriteria.getEndTime(), searchCriteria.getMasterId());
			return convertToReportFactorFinanceYearDTO(reportFactorFinanceYears);
		}
		ReportFactorFinanceYearExample example = assembleYearSearchCriteria(searchCriteria);
		example.setOrderByClause("report_date desc");
		return convertToReportFactorFinanceYearDTO(reportFactorFinanceYearMapper.selectByExample(example));
	}
	
	@Override
	public ListResult<ReportFactorFinanceCountDTO> queryRepaymentCountReport(ReportFactorFinanceSearchCriteria searchCriteria){
		if(StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			List<ReportFactorFinanceCount> reportFactorFinanceCounts = reportFactorFinanceSummarySearchMapper.
					getSummaryReportCountByCondition(searchCriteria.getStartTime(), searchCriteria.getEndTime(), searchCriteria.getMasterId(), 
							searchCriteria.getPage(), searchCriteria.getPageSize());
			return ListResult.success(convertToReportFactorFinanceCountDTO(reportFactorFinanceCounts), 
					reportFactorFinanceSummarySearchMapper.countSummaryReportCountByCondition(searchCriteria.getStartTime(), 
							searchCriteria.getEndTime(), searchCriteria.getMasterId()).size()); 
		}
		ReportFactorFinanceCountExample example = assembleCountSearchCriteria(searchCriteria);
		example.setLimitStart((searchCriteria.getPage() - 1) * searchCriteria.getPageSize());
		example.setLimitEnd(searchCriteria.getPageSize());
		example.setOrderByClause("report_date desc");
		List<ReportFactorFinanceCount> reportFactorFinanceCounts = reportFactorFinanceCountMapper.selectByExample(example);
		int totalNum = reportFactorFinanceCountMapper.countByExample(example);
		return ListResult.success(convertToReportFactorFinanceCountDTO(reportFactorFinanceCounts), totalNum);
	}

	@Override
	public List<ReportFactorFinanceCountDTO> queryRepaymentCountReportNoPaging(ReportFactorFinanceSearchCriteria searchCriteria){
		if(StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			List<ReportFactorFinanceCount> reportFactorFinanceCounts = reportFactorFinanceSummarySearchMapper.
					countSummaryReportCountByCondition(searchCriteria.getStartTime(), searchCriteria.getEndTime(), searchCriteria.getMasterId());
			return convertToReportFactorFinanceCountDTO(reportFactorFinanceCounts); 
		}
		ReportFactorFinanceCountExample example = assembleCountSearchCriteria(searchCriteria);
		example.setOrderByClause("report_date desc");
		return convertToReportFactorFinanceCountDTO(reportFactorFinanceCountMapper.selectByExample(example));
	}
	
	//组装日报表搜索条件
	private ReportFactorFinanceDayExample assembleDaySearchCriteria(ReportFactorFinanceSearchCriteria searchCriteria) {
		ReportFactorFinanceDayExample example = new ReportFactorFinanceDayExample();
		ReportFactorFinanceDayExample.Criteria criteria = example.createCriteria();
		criteria.andOwnerIdEqualTo(searchCriteria.getMasterId());
		if(StringUtils.isNotBlank(searchCriteria.getStartTime()))
			criteria.andReportDateGreaterThanOrEqualTo(searchCriteria.getStartTime());
		if(StringUtils.isNotBlank(searchCriteria.getEndTime()))
			criteria.andReportDateLessThanOrEqualTo(searchCriteria.getEndTime());
		if(StringUtils.isNotBlank(searchCriteria.getBusinessProduct()) && !StringUtils.equals(searchCriteria.getBusinessProduct(), "All") && !StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			criteria.andBusinessProductIdEqualTo(searchCriteria.getBusinessProduct());
		}
		return example;
	}
	
	//组装年报表搜索条件
	private ReportFactorFinanceYearExample assembleYearSearchCriteria(ReportFactorFinanceSearchCriteria searchCriteria) {
		ReportFactorFinanceYearExample example = new ReportFactorFinanceYearExample();
		ReportFactorFinanceYearExample.Criteria criteria = example.createCriteria();
		criteria.andOwnerIdEqualTo(searchCriteria.getMasterId());
		if(StringUtils.isNotBlank(searchCriteria.getStartTime()))
			criteria.andReportDateGreaterThanOrEqualTo(searchCriteria.getStartTime());
		if(StringUtils.isNotBlank(searchCriteria.getEndTime()))
			criteria.andReportDateLessThanOrEqualTo(searchCriteria.getEndTime());
		if(StringUtils.isNotBlank(searchCriteria.getBusinessProduct()) && !StringUtils.equals(searchCriteria.getBusinessProduct(), "All") && !StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			criteria.andBusinessProductIdEqualTo(searchCriteria.getBusinessProduct());
		}
		return example;
	}
	
	//组装计数报表搜索条件
	private ReportFactorFinanceCountExample assembleCountSearchCriteria(ReportFactorFinanceSearchCriteria searchCriteria) {
		ReportFactorFinanceCountExample example = new ReportFactorFinanceCountExample();
		ReportFactorFinanceCountExample.Criteria criteria = example.createCriteria();
		criteria.andOwnerIdEqualTo(searchCriteria.getMasterId());
		if(StringUtils.isNotBlank(searchCriteria.getStartTime()))
			criteria.andReportDateGreaterThanOrEqualTo(searchCriteria.getStartTime());
		if(StringUtils.isNotBlank(searchCriteria.getEndTime()))
			criteria.andReportDateLessThanOrEqualTo(searchCriteria.getEndTime());
		if(StringUtils.isNotBlank(searchCriteria.getBusinessProduct()) && !StringUtils.equals(searchCriteria.getBusinessProduct(), "All") && !StringUtils.equals(searchCriteria.getBusinessProduct(), "Summary")){
			criteria.andBusinessProductIdEqualTo(searchCriteria.getBusinessProduct());
		}
		return example;
	}
	
	// 融资日报表转换
	private List<ReportFactorFinanceDayDTO> convertToReportFactorFinanceDayDTO(List<ReportFactorFinanceDay> reportFactorFinanceDays){
		List<ReportFactorFinanceDayDTO> reportFactorFinanceDayDTOs = new ArrayList<ReportFactorFinanceDayDTO>();
		for (ReportFactorFinanceDay reportFactorFinanceDay : reportFactorFinanceDays) {
			ReportFactorFinanceDayDTO reportFactorFinanceDayDTO = new ReportFactorFinanceDayDTO();
			BeanUtils.copyProperties(reportFactorFinanceDay, reportFactorFinanceDayDTO);
			if(StringUtils.isBlank(reportFactorFinanceDay.getBusinessProductId())){
				reportFactorFinanceDayDTO.setBusinessProduct("汇总");
			}else{
				reportFactorFinanceDayDTO.setBusinessProduct(BusinessProduct.valueOf(reportFactorFinanceDay.getBusinessProductId()).desc());
			}
			reportFactorFinanceDayDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getFinanceBalance()));
			reportFactorFinanceDayDTO.setFinanceAmount(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getFinanceAmount()));
			reportFactorFinanceDayDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountPrincipal()));
			//  应还费用 <--
			reportFactorFinanceDayDTO.setAccountInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountInterest()));
			reportFactorFinanceDayDTO.setAccountServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountServiceCharge()));
			reportFactorFinanceDayDTO.setAccountExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountExtensionCharge()));
			reportFactorFinanceDayDTO.setAccountOverduePrincipalPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverduePrincipalPenalty()));
			reportFactorFinanceDayDTO.setAccountOverdueInterestPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverdueInterestPenalty()));
			reportFactorFinanceDayDTO.setAccountOverdueServiceChargePenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverdueServiceChargePenalty()));
			reportFactorFinanceDayDTO.setAccountOtherPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOtherPenalty()));
			// -->
			reportFactorFinanceDayDTO.setAccountCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountCharge()));
			reportFactorFinanceDayDTO.setAccountExpense(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountExpense()));
			// 应还逾期金额 <--
			reportFactorFinanceDayDTO.setAccountOverduePrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverduePrincipal()));
			reportFactorFinanceDayDTO.setAccountOverdueInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverdueInterest()));
			reportFactorFinanceDayDTO.setAccountOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverdueServiceCharge()));
			reportFactorFinanceDayDTO.setAccountOverdueExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverdueExtensionCharge()));
			// -->
			reportFactorFinanceDayDTO.setAccountOverdue(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAccountOverdue()));
			reportFactorFinanceDayDTO.setPaidPrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidPrincipal()));
			//  已还费用 <--
			reportFactorFinanceDayDTO.setPaidInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidInterest()));
			reportFactorFinanceDayDTO.setPaidServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidServiceCharge()));
			reportFactorFinanceDayDTO.setPaidExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidExtensionCharge()));
			reportFactorFinanceDayDTO.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidEarlyRepaymentCharge()));
			reportFactorFinanceDayDTO.setPaidOverduePrincipalPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverduePrincipalPenalty()));
			reportFactorFinanceDayDTO.setPaidOverdueInterestPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverdueInterestPenalty()));
			reportFactorFinanceDayDTO.setPaidOverdueServiceChargePenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverdueServiceChargePenalty()));
			reportFactorFinanceDayDTO.setPaidOtherPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOtherPenalty()));
			// -->
			reportFactorFinanceDayDTO.setPaidCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidCharge()));
			reportFactorFinanceDayDTO.setPaidExpense(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidExpense()));
			// 已还逾期金额 <--
			reportFactorFinanceDayDTO.setPaidOverduePrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverduePrincipal()));
			reportFactorFinanceDayDTO.setPaidOverdueInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverdueInterest()));
			reportFactorFinanceDayDTO.setPaidOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverdueServiceCharge()));
			reportFactorFinanceDayDTO.setPaidOverdueExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverdueExtensionCharge()));
			// -->
			reportFactorFinanceDayDTO.setPaidOverdue(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getPaidOverdue()));
			reportFactorFinanceDayDTO.setAdjustPrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustPrincipal()));
			// 调账金额 <--
			reportFactorFinanceDayDTO.setAdjustInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustInterest()));
			reportFactorFinanceDayDTO.setAdjustServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustServiceCharge()));
			reportFactorFinanceDayDTO.setAdjustExtension(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustExtension()));
			reportFactorFinanceDayDTO.setAdjustExpense(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustExpense()));
			reportFactorFinanceDayDTO.setAdjustOverdueInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustOverdueInterest()));
			reportFactorFinanceDayDTO.setAdjustOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustOverdueServiceCharge()));
			reportFactorFinanceDayDTO.setAdjustPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustPenalty()));
			// -->
			reportFactorFinanceDayDTO.setAdjustAmount(MoneyArithUtil.convertMoneyToString(reportFactorFinanceDay.getAdjustAmount()));
			reportFactorFinanceDayDTOs.add(reportFactorFinanceDayDTO);
		}
		return reportFactorFinanceDayDTOs;
	}
	
	// 融资计数报表转换
	private List<ReportFactorFinanceCountDTO> convertToReportFactorFinanceCountDTO(List<ReportFactorFinanceCount> reportFactorFinanceCounts){
		List<ReportFactorFinanceCountDTO> reportFactorFinanceCountDTOs = new ArrayList<ReportFactorFinanceCountDTO>();
		for (ReportFactorFinanceCount reportFactorFinanceCount : reportFactorFinanceCounts) {
			ReportFactorFinanceCountDTO reportFactorFinanceCountDTO = new ReportFactorFinanceCountDTO();
			BeanUtils.copyProperties(reportFactorFinanceCount, reportFactorFinanceCountDTO);
			if(StringUtils.isBlank(reportFactorFinanceCount.getBusinessProductId())){
				reportFactorFinanceCountDTO.setBusinessProduct("汇总");
			}else{
				reportFactorFinanceCountDTO.setBusinessProduct(BusinessProduct.valueOf(reportFactorFinanceCount.getBusinessProductId()).desc());
			}
			reportFactorFinanceCountDTO.setLoanItems(reportFactorFinanceCount.getLoanItems().toString());
			reportFactorFinanceCountDTO.setOverdueItems(reportFactorFinanceCount.getOverdueItems().toString());
			reportFactorFinanceCountDTO.setExtensionItems(reportFactorFinanceCount.getExtensionItems().toString());
			reportFactorFinanceCountDTO.setRepaymentItems(reportFactorFinanceCount.getRepaymentItems().toString());
			reportFactorFinanceCountDTO.setAdjustItems(reportFactorFinanceCount.getAdjustItems().toString());
			reportFactorFinanceCountDTO.setOverdueCustomer(reportFactorFinanceCount.getOverdueCustomer().toString());
			reportFactorFinanceCountDTOs.add(reportFactorFinanceCountDTO);
		}
		return reportFactorFinanceCountDTOs;
	}
	
	// 融资年报表转换
	private List<ReportFactorFinanceYearDTO> convertToReportFactorFinanceYearDTO(List<ReportFactorFinanceYear> reportFactorFinanceYears){
		List<ReportFactorFinanceYearDTO> reportFactorFinanceYearDTOs = new ArrayList<ReportFactorFinanceYearDTO>();
		for (ReportFactorFinanceYear reportFactorFinanceYear : reportFactorFinanceYears) {
			ReportFactorFinanceYearDTO reportFactorFinanceYearDTO = new ReportFactorFinanceYearDTO();
			BeanUtils.copyProperties(reportFactorFinanceYear, reportFactorFinanceYearDTO);
			if(StringUtils.isBlank(reportFactorFinanceYear.getBusinessProductId())){
				reportFactorFinanceYearDTO.setBusinessProduct("汇总");
			}else{
				reportFactorFinanceYearDTO.setBusinessProduct(BusinessProduct.valueOf(reportFactorFinanceYear.getBusinessProductId()).desc());
			}
			reportFactorFinanceYearDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getFinanceBalance()));
			reportFactorFinanceYearDTO.setFinanceAmount(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getFinanceAmount()));
			reportFactorFinanceYearDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountPrincipal()));
			//  应还费用 <--
			reportFactorFinanceYearDTO.setAccountInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountInterest()));
			reportFactorFinanceYearDTO.setAccountServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountServiceCharge()));
			reportFactorFinanceYearDTO.setAccountExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountExtensionCharge()));
			reportFactorFinanceYearDTO.setAccountOverduePrincipalPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverduePrincipalPenalty()));
			reportFactorFinanceYearDTO.setAccountOverdueInterestPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverdueInterestPenalty()));
			reportFactorFinanceYearDTO.setAccountOverdueServiceChargePenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverdueServiceChargePenalty()));
			reportFactorFinanceYearDTO.setAccountOtherPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOtherPenalty()));
			// -->
			reportFactorFinanceYearDTO.setAccountCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountCharge()));
			reportFactorFinanceYearDTO.setAccountExpense(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountExpense()));
			// 应还逾期金额 <--
			reportFactorFinanceYearDTO.setAccountOverduePrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverduePrincipal()));
			reportFactorFinanceYearDTO.setAccountOverdueInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverdueInterest()));
			reportFactorFinanceYearDTO.setAccountOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverdueServiceCharge()));
			reportFactorFinanceYearDTO.setAccountOverdueExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverdueExtensionCharge()));
			// -->
			reportFactorFinanceYearDTO.setAccountOverdue(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAccountOverdue()));
			reportFactorFinanceYearDTO.setPaidPrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidPrincipal()));
			//  已还费用 <--
			reportFactorFinanceYearDTO.setPaidInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidInterest()));
			reportFactorFinanceYearDTO.setPaidServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidServiceCharge()));
			reportFactorFinanceYearDTO.setPaidExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidExtensionCharge()));
			reportFactorFinanceYearDTO.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidEarlyRepaymentCharge()));
			reportFactorFinanceYearDTO.setPaidOverduePrincipalPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverduePrincipalPenalty()));
			reportFactorFinanceYearDTO.setPaidOverdueInterestPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverdueInterestPenalty()));
			reportFactorFinanceYearDTO.setPaidOverdueServiceChargePenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverdueServiceChargePenalty()));
			reportFactorFinanceYearDTO.setPaidOtherPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOtherPenalty()));
			// -->
			reportFactorFinanceYearDTO.setPaidCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidCharge()));
			reportFactorFinanceYearDTO.setPaidExpense(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidExpense()));
			// 已还逾期金额 <--
			reportFactorFinanceYearDTO.setPaidOverduePrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverduePrincipal()));
			reportFactorFinanceYearDTO.setPaidOverdueInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverdueInterest()));
			reportFactorFinanceYearDTO.setPaidOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverdueServiceCharge()));
			reportFactorFinanceYearDTO.setPaidOverdueExtensionCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverdueExtensionCharge()));
			// -->
			reportFactorFinanceYearDTO.setPaidOverdue(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getPaidOverdue()));
			reportFactorFinanceYearDTO.setTotalOverdue(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getTotalOverdue()));
			reportFactorFinanceYearDTO.setTotalExtension(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getTotalExtension()));
			reportFactorFinanceYearDTO.setAdjustPrincipal(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustPrincipal()));
			// 调账金额 <--
			reportFactorFinanceYearDTO.setAdjustInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustInterest()));
			reportFactorFinanceYearDTO.setAdjustServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustServiceCharge()));
			reportFactorFinanceYearDTO.setAdjustExtension(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustExtension()));
			reportFactorFinanceYearDTO.setAdjustExpense(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustExpense()));
			reportFactorFinanceYearDTO.setAdjustOverdueInterest(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustOverdueInterest()));
			reportFactorFinanceYearDTO.setAdjustOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustOverdueServiceCharge()));
			reportFactorFinanceYearDTO.setAdjustPenalty(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustPenalty()));
			// -->
			reportFactorFinanceYearDTO.setAdjustAmount(MoneyArithUtil.convertMoneyToString(reportFactorFinanceYear.getAdjustAmount()));
			reportFactorFinanceYearDTOs.add(reportFactorFinanceYearDTO);
		}
		return reportFactorFinanceYearDTOs;
	}

	@Override
	public ListResult<ReportAccountFundDTO> queryAccountFundDailyReport(AccountFundDailyReportQueryDTO query) {
		return accountFundReportService.queryAccountFundDailyReport(query);
	}

	@Override
	public ListResult<ReportAccountFundDTO> queryAccountFundYearReport(AccountFundYearReportQueryDTO query) {
		return accountFundReportService.queryAccountFundYearReport(query);
	}

	@Override
	public ListResult<ReportAccountFundCountDTO> queryAccountFundDailyCountReport(AccountFundDailyReportQueryDTO query) {
		return accountFundReportService.queryAccountFundDailyCountReport(query);
	}
	
	@Override
	public List<ReportAccountFundDTO> exportAcountFundDailyReport(AccountFundDailyReportQueryDTO query) {
		return accountFundReportService.exportAcountFundDailyReport(query);
	}

	@Override
	public List<ReportAccountFundDTO> exportAcountFundYearReport(AccountFundYearReportQueryDTO query) {
		return accountFundReportService.exportAcountFundYearReport(query);
	}

	@Override
	public List<ReportAccountFundCountDTO> exportAcountFundDailyCountReport(AccountFundDailyReportQueryDTO query) {
		return accountFundReportService.exportAcountFundDailyCountReport(query);
	}

	@Override
	public ListResult<MonitorSummaryDTO> queryMonitorSummary(MonitorSummaryQueryDTO monitorSummaryQueryDTO) {
		String limitUsedStaus = StringUtils.trimToNull(monitorSummaryQueryDTO.getCreditLimitUsedStatus());
		if(limitUsedStaus != null) {
			try {
				CreditLimitUsedStatus.valueOf(monitorSummaryQueryDTO.getCreditLimitUsedStatus());
			} catch (Exception e) {
				throw WebException.instance(ReturnCode.TP5001);
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String yestdate10 = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(calendar.getTime(), -1));
		String customerNameTrim = StringUtils.trimToNull(monitorSummaryQueryDTO.getCustomerName());
		String productId = monitorSummaryQueryDTO.getProductId();
		CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria = new CreditUsedLimitInfoQueryCriteria();
		creditUsedLimitInfoQueryCriteria.setInstitution(Institution.travelzen.name());
		creditUsedLimitInfoQueryCriteria.setProductId(productId);
		creditUsedLimitInfoQueryCriteria.setCompanyName(customerNameTrim == null ? null : "%" + customerNameTrim + "%");
		creditUsedLimitInfoQueryCriteria.setLimitUsedStaus(limitUsedStaus);
		creditUsedLimitInfoQueryCriteria.setPage(monitorSummaryQueryDTO.getPage());
		creditUsedLimitInfoQueryCriteria.setPageSize(monitorSummaryQueryDTO.getPageSize());
		List<CreditUsedLimitInfo> creditUsedLimits = creditApiImpl.queryCreditUsedLimitInfo(creditUsedLimitInfoQueryCriteria);
		List<MonitorSummaryDTO> monitorSummaryDTOs = new ArrayList<>();
		for (CreditUsedLimitInfo creditUsedLimitInfo : creditUsedLimits) {
			MonitorSummaryDTO monitorSummaryDTO = new MonitorSummaryDTO();
			String memberId = creditUsedLimitInfo.getMemberId();
			String outCustomerId = creditUsedLimitInfo.getOutCustomerId();
			monitorSummaryDTO.setMemberId(memberId);
			monitorSummaryDTO.setCustomerName(creditUsedLimitInfo.getCompanyName());
			monitorSummaryDTO.setOutCustomerName(creditUsedLimitInfo.getOutCustomerName());
			monitorSummaryDTO.setUsedLimit(MoneyUtil.cent2Yuan(creditUsedLimitInfo.getUsedLimit()));
			monitorSummaryDTO.setOutCustomerId(outCustomerId);
			monitorSummaryDTO.setPledgeRage(new BigDecimal("0.8").multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%");
			List<MonitorMetricData> monitorMetricDatas = reportMonitorServiceImpl.queryMonitorMetric(memberId, outCustomerId, productId, yestdate10, yestdate10);
			if(CollectionUtils.isNotEmpty(monitorMetricDatas)) {
				MonitorMetricData monitorMetricData = monitorMetricDatas.get(0);
				BigDecimal bigDecimal = monitorMetricData.getSalesChangeRate();
				if(bigDecimal != null)
					monitorSummaryDTO.setSalesChangeRate(changeScale(bigDecimal, 2));
				bigDecimal = monitorMetricData.getSalesRepaymentRate();
				if(bigDecimal != null)
					monitorSummaryDTO.setSalesRepaymentRate(changeScale(bigDecimal, 2));
				bigDecimal = monitorMetricData.getCounterGuaranteeRate();
				if(bigDecimal != null)
					monitorSummaryDTO.setCounterGuaranteeRate(changeScale(bigDecimal, 2));
			}
			monitorSummaryDTOs.add(monitorSummaryDTO);
		}
		ListResult<MonitorSummaryDTO> returnValue = ListResult.success(monitorSummaryDTOs, creditApiImpl.queryCountCreditUsedLimitInfo(creditUsedLimitInfoQueryCriteria));
		return returnValue;
	}
	
	@Override
	public List<MonitorMetricDataDTO> queryMonitorMetricDataDTO(MonitorMetricAndDataQueryDTO monitorMetricDataQueryDTO) {
		String memberId = monitorMetricDataQueryDTO.getMemberId();
		String outCustomerId = monitorMetricDataQueryDTO.getOutCustomerId();
		String startDate = monitorMetricDataQueryDTO.getStartDate();
		String endDate = monitorMetricDataQueryDTO.getEndDate();
		checkQueryData(memberId, outCustomerId, startDate, endDate);
		List<MonitorMetricData> monitorMetricDatas = reportMonitorServiceImpl.queryMonitorMetric(memberId, outCustomerId, monitorMetricDataQueryDTO.getProductId(), startDate, endDate);
		List<MonitorMetricDataDTO> returnValue = new ArrayList<>();
		int i = 0;
		while (startDate.compareTo(endDate) <= 0) {
			MonitorMetricDataDTO monitorMetricDataDTO = new MonitorMetricDataDTO();
			monitorMetricDataDTO.setDate(startDate);
			if(i < monitorMetricDatas.size()) {
				MonitorMetricData monitorMetricData = monitorMetricDatas.get(i);
				if(monitorMetricData.getDate().equals(startDate)) {
					BigDecimal bigDecimal = monitorMetricData.getCounterGuaranteeRate();
					if(bigDecimal != null)
						monitorMetricDataDTO.setCounterGuaranteeRate(changeScale(bigDecimal, 2));
					bigDecimal = monitorMetricData.getSalesChangeRate();
					if(bigDecimal != null)
						monitorMetricDataDTO.setSalesChangeRate(changeScale(bigDecimal, 2));
					bigDecimal = monitorMetricData.getSalesRepaymentRate();
					if(bigDecimal != null)
						monitorMetricDataDTO.setSalesRepaymentRate(changeScale(bigDecimal, 2));
					i ++;
				}
			}
			returnValue.add(monitorMetricDataDTO);
			startDate = addDate(startDate, 1);
		}
		return returnValue;
	}
	
	@Override
	public List<MonitorDataDataDTO> queryMonitorDataDataDTO(MonitorMetricAndDataQueryDTO monitorMetricDataQueryDTO) {
		String memberId = monitorMetricDataQueryDTO.getMemberId();
		String outCustomerId = monitorMetricDataQueryDTO.getOutCustomerId();
		String startDate = monitorMetricDataQueryDTO.getStartDate();
		String endDate = monitorMetricDataQueryDTO.getEndDate();
		checkQueryData(memberId, outCustomerId, startDate, endDate);
		List<MonitorDataData> monitorDataDatas = reportMonitorServiceImpl.queryMonitorData(memberId, outCustomerId, monitorMetricDataQueryDTO.getProductId(), startDate, endDate);
		List<MonitorDataDataDTO> returnValue = new ArrayList<>();
		int i = 0;
		while (startDate.compareTo(endDate) <= 0) {
			MonitorDataDataDTO monitorDataDataDTO = new MonitorDataDataDTO();
			monitorDataDataDTO.setDate(startDate);
			if(i < monitorDataDatas.size()) {
				MonitorDataData monitorDataData = monitorDataDatas.get(i);
				if(monitorDataData.getDate().equals(startDate)) {
					Long amount = monitorDataData.getQualifiedAR();
					if(amount != null)
						monitorDataDataDTO.setQualifiedAR(MoneyUtil.cent2Yuan(amount));
					amount = monitorDataData.getTicketAllSales();
					if(amount != null)
						monitorDataDataDTO.setTicketAllSales(MoneyUtil.cent2Yuan(amount));
					amount = monitorDataData.getTicketRepayment();
					if(amount != null)
						monitorDataDataDTO.setTicketRepayment(MoneyUtil.cent2Yuan(amount));
					amount = monitorDataData.getTicketTakeOffSale();
					if(amount != null)
						monitorDataDataDTO.setTicketTakeOffSale(MoneyUtil.cent2Yuan(amount));
					amount = monitorDataData.getUsedLimitTheDay();
					if(amount != null)
						monitorDataDataDTO.setUsedLimitTheDay(MoneyUtil.cent2Yuan(amount));
					i++;
				}
			}
			returnValue.add(monitorDataDataDTO);
			startDate = addDate(startDate, 1);
		}
		return returnValue;
	}
	
	private void checkQueryData(String memberId, String outCustomerId, String startDate, String endDate) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("平台用户ID不能为空");
		if(StringUtils.isBlank(outCustomerId))
			throw WebException.instance("外部客户ID不能为空");
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		if(!startDate.matches(regex) || !endDate.matches(regex))
			throw WebException.instance("时间格式不正确");
		if(startDate.compareTo(endDate) > 0)
			throw WebException.instance("开始时间大于结束时间");
		if(endDate.compareTo(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(new Date(), -1))) > 0)
			throw WebException.instance("查询时间不得晚于昨天");
	}
	
	private String changeScale(BigDecimal bigDecimal, int scale) {
		return bigDecimal.multiply(new BigDecimal(100)).setScale(scale, BigDecimal.ROUND_HALF_UP) + "%";
	}
	
	private String addDate(String date, int number) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw WebException.instance("时间格式转化出错");
		}
		return simpleDateFormat.format(DateUtils.addDays(d, number));
	}
	/**
	 * 韵达监控详情时间格式转换
	 * @param date
	 * @param number
	 * @return
	 */
	private String addDateMonth(String date, int number) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Date d;
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw WebException.instance("时间格式转化出错");
		}
		return simpleDateFormat.format(DateUtils.addMonths(d, number));
	}

	@Override
	public List<MonitorMetricData> queryMonitorMetricData(String memberId, String outCustomerId, String productId, String startDate, String endDate) {
		return reportMonitorServiceImpl.queryMonitorMetric(memberId, outCustomerId, productId, startDate, endDate);
	}

	@Override
	public ListResult<ReportFundMonthlyResultDTO> queryFundMonthlyReportData(ReportFundMonthlySearchCriteria searchCriteria) {
		return fundReportTransactionService.queryFundMonthlyReportData(searchCriteria);
	}

	@Override
	public List<ReportFundMonthlyResultDTO> queryFundMonthlyReportDataWithoutPage(ReportFundMonthlySearchCriteria searchCriteria) {
		return fundReportTransactionService.queryFundMonthlyReportDataWithoutPage(searchCriteria);
	}

	@Override
	public String getLastMonthDate() {
		return DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1));
	}

	@Override
	public ObjectResult<String> getMonthlyBalanceSum(ReportFundMonthlySearchCriteria searchCriteria) {
		return fundReportTransactionService.getMonthlyBalanceSum(searchCriteria);
	}

	@Override
	public String getMainAccountBalance() {
		return fundReportTransactionService.getMainAccountBalance(DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)));
	}
	
	/**
	 * 韵达监控列表
	 * monitorSummaryQueryDTO 
	 */
	@Override
	public ListResult<MonitorSummaryYundaDTO> queryYundaexMonitorSummary(MonitorSummaryQueryYundaDTO monitorSummaryQueryDTO) {
		if (StringUtils.isBlank(monitorSummaryQueryDTO.getProductId()))
			throw WebException.instance("productId为空");
		//当前系统时间
//		Calendar calendar = Calendar.getInstance();
//		String CurMonth = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
//		String lastMonth = new SimpleDateFormat("yyyy-MM").format(DateUtils.addMonths(calendar.getTime(),-1));
		
		//获取当月虚拟时间
		String	currentDate = vbamCommonService.getCurrentDate();
		
		String productId = monitorSummaryQueryDTO.getProductId();
		String customerName = StringUtils.trimToNull(monitorSummaryQueryDTO.getCustomerName());
		CreditUsedLimitRequest creditUsedLimitRequest = new CreditUsedLimitRequest();
		creditUsedLimitRequest.setCustomerName(customerName);
		creditUsedLimitRequest.setProductId(productId);
		creditUsedLimitRequest.setPage(monitorSummaryQueryDTO.getPage());
		creditUsedLimitRequest.setPageSize(monitorSummaryQueryDTO.getPageSize());
		List<CreditUsedLimitDTO> creditUsedLimitInfos = creditApiImpl.getCreditUsedLimitInfo(creditUsedLimitRequest);
		
		List<MonitorSummaryYundaDTO> monitorSummaryYundaDTOs = new ArrayList<>();
		for (CreditUsedLimitDTO creditUsedLimitDTO : creditUsedLimitInfos) {
			String memberId = creditUsedLimitDTO.getMemberId();
			MonitorSummaryYundaDTO summaryYundaDTO = new MonitorSummaryYundaDTO();
			summaryYundaDTO.setMemberId(creditUsedLimitDTO.getMemberId());
			summaryYundaDTO.setCustomerName(creditUsedLimitDTO.getCompanyName());
			summaryYundaDTO.setUsedLimit(MoneyUtil.cent2Yuan(creditUsedLimitDTO.getUsedLimit()));
			//获取监控列表显示数据
			List<MonitorMetricDataYunda> yundaexMonitorMetrics = reportMonitorServiceImpl.queryYundaexMonitorMetric(memberId, productId, 
					DateTimeUtil.month7(DateTimeUtil.addMonth(DateTimeUtil.getDate10(currentDate),0)));
			List<MonitorMetricDataYunda> yundaexMonitorMetric = yundaexMonitorMetrics;
			if (CollectionUtils.isEmpty(yundaexMonitorMetrics)) {
				yundaexMonitorMetric = reportMonitorServiceImpl.queryYundaexMonitorMetric(memberId, productId,
						DateTimeUtil.month7(DateTimeUtil.addMonth(DateTimeUtil.getDate10(currentDate), -1)));
			}
			if(CollectionUtils.isNotEmpty(yundaexMonitorMetric))
			for (MonitorMetricDataYunda monitorMetricDataYunda : yundaexMonitorMetric) {
				summaryYundaDTO.setRecandsendGrowthRate(changeScale(monitorMetricDataYunda.getRecandsendGrowthrate(), 2));
				if(monitorMetricDataYunda.getYundaexJudge().compareTo(new BigDecimal("1")) == 0){
					summaryYundaDTO.setYundaexJudge("差");
				}
				if(monitorMetricDataYunda.getYundaexJudge().compareTo(new BigDecimal("2")) == 0){
					summaryYundaDTO.setYundaexJudge("良好");
				}
				if(monitorMetricDataYunda.getYundaexJudge().compareTo(new BigDecimal("3")) == 0){
					summaryYundaDTO.setYundaexJudge("优");
				}
				//查询评级显示
				YundaexmonitorGradeInfoDTO gradeInfoDTO = yundaexMonitorApi.getYundaexGradeInfoByScore(monitorMetricDataYunda.getYundaexGrade());
				summaryYundaDTO.setGrade(gradeInfoDTO.getGrade());
				if(monitorMetricDataYunda.getBailBalance() != null && monitorMetricDataYunda.getDayRequirements() != null){
					summaryYundaDTO.setBailBalanceDivideDayRequirements(changeScale((monitorMetricDataYunda.getBailBalance().divide(monitorMetricDataYunda.getDayRequirements(),2)),2));
				} else {
					summaryYundaDTO.setBailBalanceDivideDayRequirements("0");
				}
				if(monitorMetricDataYunda.getBailBalance() != null){
					summaryYundaDTO.setBailBalance(MoneyUtil.cent2Yuan(monitorMetricDataYunda.getBailBalance()));
				}
				if(monitorMetricDataYunda.getShortLoan() != null){
					summaryYundaDTO.setShortLoan(MoneyUtil.cent2Yuan(monitorMetricDataYunda.getShortLoan()));
				} 
			}
			monitorSummaryYundaDTOs.add(summaryYundaDTO);
		}
		ListResult<MonitorSummaryYundaDTO> returnValue = ListResult.success(monitorSummaryYundaDTOs,creditApiImpl.getCreditUsedLimitInfoCount(creditUsedLimitRequest));
		return returnValue;
	}
		
		
	
	/**
	 * 韵达监控详情
	 */
	@Override
	public List<MonitorMetricDataYundaDTO> queryYundaexMonitorMetricDataDTO(MonitorMetricAndDataQueryYundaDTO monitorMetricDataQueryDTO) {
		String memberId = monitorMetricDataQueryDTO.getMemberId();
		String startDate = monitorMetricDataQueryDTO.getStartDate();
		String endDate = monitorMetricDataQueryDTO.getEndDate();
		String productId = monitorMetricDataQueryDTO.getProductId();
		checkQueryMonitorData(memberId,productId, startDate, endDate);
		List<MonitorMetricDataYunda> monitorMetricDatas = reportMonitorServiceImpl.queryYundaMonitorMetric(memberId, productId, startDate, endDate);
		List<MonitorMetricDataYundaDTO> returnValue = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (startDate.compareTo(endDate) <= 0) {
			MonitorMetricDataYundaDTO monitorMetricDataDTO = new MonitorMetricDataYundaDTO();
			monitorMetricDataDTO.setDate(startDate);
			if(i < monitorMetricDatas.size()) {
				MonitorMetricDataYunda monitorMetricData = monitorMetricDatas.get(i);
				if(monitorMetricData.getDate().equals(startDate)) {
					//日资金需求
					if(monitorMetricData.getBailBalance() != null)
						monitorMetricDataDTO.setBailBalance(MoneyUtil.cent2Yuan(monitorMetricData.getBailBalance()));
					//揽派件增长率
					if(monitorMetricData.getRecandsendGrowthrate() != null)
						monitorMetricDataDTO.setRecandsendGrowthrate(changeScale(monitorMetricData.getRecandsendGrowthrate(), 2));
					//保证金余额除以日资金需求
					if(monitorMetricData.getBailBalance() != null && monitorMetricData.getDayRequirements() != null)
						monitorMetricDataDTO.setBailBalanceDivideDayRequirements(changeScale((monitorMetricData.getBailBalance().divide(monitorMetricData.getDayRequirements(),2)),2));
					//逾期次数
					if(monitorMetricData.getOverdues() != null)
						monitorMetricDataDTO.setOverdues(MoneyArithUtil.round(monitorMetricData.getOverdues(), 0).toString());
					//净现金增长量
					if (i >= 1) {
						if (monitorMetricData.getNetCashflowGrowth() != null)
							monitorMetricDataDTO.setNetCashflowGrowth(changeScale(monitorMetricDatas.get(j + 1).getNetCashflowGrowth().subtract(monitorMetricDatas.get(j).getNetCashflowGrowth()).divide(monitorMetricDatas.get(j).getNetCashflowGrowth(), 2), 2));
						j++;
					}
					//最大授信金额
					if(monitorMetricData.getCreditLimit() != null)
						monitorMetricDataDTO.setCreditLimit(MoneyUtil.cent2Yuan(monitorMetricData.getCreditLimit()));
					//韵达评级
					if(monitorMetricData.getYundaexGrade() != null) {
						 YundaexmonitorGradeInfoDTO infoDTO = yundaexMonitorApi.getYundaexGradeInfoByScore(monitorMetricData.getYundaexGrade());
						monitorMetricDataDTO.setYundaexGrade(infoDTO.getGrade());
					}
					i ++;
				}
			}
			returnValue.add(monitorMetricDataDTO);
			startDate = addDateMonth(startDate, 1);
		}
		return returnValue;
	}
	
	/**
	 * 判断韵达监控查询详情参数是否为空
	 * @param memberId
	 * @param productId
	 * @param startDate
	 * @param endDate
	 */
	private void checkQueryMonitorData(String memberId, String productId, String startDate, String endDate){
		if(StringUtils.isBlank(memberId)){
			throw WebException.instance("韵达监控用户id为空");
		}
		if(StringUtils.isBlank(productId)){
			throw WebException.instance("韵达监控项目id为空");
		}
		if(StringUtils.isBlank(startDate)){
			throw WebException.instance("韵达监控查询起始时间为空");
		}
		if(StringUtils.isBlank(endDate)){
			throw WebException.instance("韵达监控查询结束时间为空");
		}
	}
	
	
	/**
	 * 预警查询监控数据
	 */
	@Override
	public List<MonitorMetricYundaDTO> queryYundaMonitorMetricEarlyWarning(String memberId,String productId, String startDate,String endDate) {
		YundaexEarlyWarningParam(memberId,productId, startDate, endDate);
		List<MonitorMetricYundaDTO> queryYundaMonitorMetricEarlyWarnings = reportMonitorServiceImpl.queryYundaMonitorMetricEarlyWarning(memberId,productId, startDate, endDate);
		List<MonitorMetricYundaDTO> returnValue = new ArrayList<>();
		int i = 0;
		while (startDate.compareTo(endDate) <= 0) {
			MonitorMetricYundaDTO monitorMetricYundaDTO = new MonitorMetricYundaDTO();
			monitorMetricYundaDTO.setDate(startDate);
			if(i < queryYundaMonitorMetricEarlyWarnings.size()) {
				MonitorMetricYundaDTO monitorMetricData = queryYundaMonitorMetricEarlyWarnings.get(i);
				if(monitorMetricData.getDate().equals(startDate)) {
					if(monitorMetricData.getBailBalance() != null)
						monitorMetricYundaDTO.setBailBalance(monitorMetricData.getBailBalance());
					if(monitorMetricData.getRecandsendGrowthrate() != null)
						monitorMetricYundaDTO.setRecandsendGrowthrate(monitorMetricData.getRecandsendGrowthrate());
					if(monitorMetricData.getDayRequirements() != null)
						monitorMetricYundaDTO.setDayRequirements(monitorMetricData.getDayRequirements());
					if(monitorMetricData.getOverdues() != null)
						monitorMetricYundaDTO.setOverdues(monitorMetricData.getOverdues());
					if(monitorMetricData.getNetCashflow() != null)
						monitorMetricYundaDTO.setNetCashflow(monitorMetricData.getNetCashflow());
					if(monitorMetricData.getCreditLimit() != null)
						monitorMetricYundaDTO.setCreditLimit(monitorMetricData.getCreditLimit());
					if(monitorMetricData.getYundaexGrade() != null) {
						monitorMetricYundaDTO.setYundaexGrade(monitorMetricData.getYundaexGrade());
					}
					if(monitorMetricData.getShortLoan() != null)
						monitorMetricYundaDTO.setShortLoan(monitorMetricData.getShortLoan());
					i ++;
				}
			}
			returnValue.add(monitorMetricYundaDTO);
			startDate = addDateMonth(startDate, 1);
		}
		return returnValue;
	}

	/**
	 * 韵达预警时间判空
	 * @param productId
	 * @param startDate
	 * @param endDate
	 * @throws WebException
	 */
	private void YundaexEarlyWarningParam(String memberId,String productId, String startDate, String endDate) throws WebException {
		if(StringUtils.isBlank(productId))
			throw WebException.instance("韵达项目id不能为空");
		if(StringUtils.isBlank(startDate))
			throw WebException.instance("韵达项目监控数据起始时间不能为空");
		if(StringUtils.isBlank(endDate))
			throw WebException.instance("韵达项目监控数据截止时间不能为空");
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("韵达项目用户id不能为空");
	}
	
	
	
	@Override
	public void save(BigDecimal param, String memberId, String stationNo, String yundaexAssetProjectId, String type, String currentDate) {
		monitorReportTransactionServiceImpl.save(param,memberId,stationNo,yundaexAssetProjectId,type,currentDate);
	}

	
}
