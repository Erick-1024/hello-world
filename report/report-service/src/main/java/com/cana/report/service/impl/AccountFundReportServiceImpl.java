/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.report.dao.mapper.gen.ReportAccountFundCountMapper;
import com.cana.report.dao.mapper.gen.ReportAccountFundDailyMapper;
import com.cana.report.dao.mapper.gen.ReportAccountFundYearMapper;
import com.cana.report.dao.po.ReportAccountFundCount;
import com.cana.report.dao.po.ReportAccountFundCountExample;
import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.dao.po.ReportAccountFundDailyExample;
import com.cana.report.dao.po.ReportAccountFundYear;
import com.cana.report.dao.po.ReportAccountFundYearExample;
import com.cana.report.service.IAccountFundReportService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.report.dto.AccountFundYearReportQueryDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundCountDTO;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
@Service
public class AccountFundReportServiceImpl implements IAccountFundReportService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ReportAccountFundDailyMapper reportAccountFundDailyMapper;
	@Resource
	private ReportAccountFundYearMapper reportAccountFundYearMapper;
	@Resource
	private ReportAccountFundCountMapper reportAccountFundCountMapper;

	@Override
	public ListResult<ReportAccountFundDTO> queryAccountFundDailyReport(AccountFundDailyReportQueryDTO query) {
		ReportAccountFundDailyExample ex = getDailyAccountFundReportQueryEx(query);
		ex.setOrderByClause("report_date desc");
//		ex.setOrderByClause("id desc");
		ex.setLimitStart((query.getPage() - 1) * query.getPageSize());
		ex.setLimitEnd(query.getPageSize());
		List<ReportAccountFundDaily> dailyFunds = reportAccountFundDailyMapper.selectByExample(ex);
		int count = reportAccountFundDailyMapper.countByExample(ex);
		List<ReportAccountFundDTO> dtos = dailyAccountFundReport2DTO(dailyFunds);
		return ListResult.success(dtos, count);
	}

	private ReportAccountFundDailyExample getDailyAccountFundReportQueryEx(AccountFundDailyReportQueryDTO query){
		ReportAccountFundDailyExample ex = new ReportAccountFundDailyExample();
		ReportAccountFundDailyExample.Criteria criteria = ex.createCriteria();
		criteria.andCustomerIdEqualTo(query.getCustomerId());
		if (StringUtils.isNotBlank(query.getStartTime())) {
			criteria.andReportDateGreaterThanOrEqualTo(query.getStartTime());
		}
		if (StringUtils.isNotBlank(query.getEndTime())) {
			criteria.andReportDateLessThanOrEqualTo(query.getEndTime());
		}
		return ex;
	}
	
	private List<ReportAccountFundDTO> dailyAccountFundReport2DTO(List<ReportAccountFundDaily> dailyReports) {
		List<ReportAccountFundDTO> dailyDTOs = Lists.newArrayList();
		if (CollectionUtils.isEmpty(dailyReports)) {
			return dailyDTOs;
		}
		for (ReportAccountFundDaily report : dailyReports) {
			ReportAccountFundDTO dto = new ReportAccountFundDTO();
			dto.setReportDate(report.getReportDate());

			dto.setCurrentBalance(MoneyUtil.formatMoney(report.getCurrentBalance()));
			dto.setCurrentOwnSupervisionBalance(MoneyUtil.formatMoney(report.getCurrentOwnSupervisionBalance()));
			dto.setCurrentOtherSupervisionBalance(MoneyUtil.formatMoney(report.getCurrentOtherSupervisionBalance()));

			dto.setLastBalance(MoneyUtil.formatMoney(report.getLastBalance()));
			dto.setLastOwnSupervisionBalance(MoneyUtil.formatMoney(report.getLastOwnSupervisionBalance()));
			dto.setLastOtherSupervisionBalance(MoneyUtil.formatMoney(report.getLastOtherSupervisionBalance()));

			dto.setDepositFund(formatMoney(report.getDepositFund()));

			dto.setTransferFund(formatMoney(report.getTransferFund()));
			dto.setWithdrawFund(formatMoney(report.getWithdrawFund()));
			dto.setWithdrawFee(formatMoney(report.getWithdrawFee()));
			dto.setFreezeFund(formatMoney(report.getFreezeFund()));
			dto.setUnfreezeFund(formatMoney(report.getUnfreezeFund()));
			dto.setRefundFund(formatMoney(report.getRefundFund()));

			dto.setSupervisionDepositFund(formatMoney(report.getSupervisionDepositFund()));

			dto.setSupervisionTransferFund(formatMoney(report.getSupervisionTransferFund()));
			dto.setSupervisionWithdrawFund(formatMoney(report.getSupervisionWithdrawFund()));
			dto.setSupervisionWithdrawFee(formatMoney(report.getSupervisionWithdrawFee()));
			dto.setSupervisionFreezeFund(formatMoney(report.getSupervisionFreezeFund()));
			dto.setSupervisionUnfreezeFund(formatMoney(report.getSupervisionUnfreezeFund()));
			dto.setSupervisionRefundFund(formatMoney(report.getSupervisionRefundFund()));

			dailyDTOs.add(dto);
		}
		Collections.sort(dailyDTOs);
		return dailyDTOs;
	}

	@Override
	public ListResult<ReportAccountFundDTO> queryAccountFundYearReport(AccountFundYearReportQueryDTO query) {
		ReportAccountFundYearExample ex = getAccountFundYearReportQueryEx(query);
		ex.setOrderByClause("report_date desc");
//		ex.setOrderByClause("id desc");
		ex.setLimitStart((query.getPage() - 1) * query.getPageSize());
		ex.setLimitEnd(query.getPageSize());
		List<ReportAccountFundYear> dailyFunds = reportAccountFundYearMapper.selectByExample(ex);
		int count = reportAccountFundYearMapper.countByExample(ex);
		List<ReportAccountFundDTO> dtos = accountFundYearReport2DTO(dailyFunds);
		return ListResult.success(dtos, count);
	}

	private ReportAccountFundYearExample getAccountFundYearReportQueryEx(AccountFundYearReportQueryDTO query){
		ReportAccountFundYearExample ex = new ReportAccountFundYearExample();
		ReportAccountFundYearExample.Criteria criteria = ex.createCriteria();
		criteria.andCustomerIdEqualTo(query.getCustomerId());
		if (StringUtils.isNotBlank(query.getYear())) {
			criteria.andReportDateEqualTo(query.getYear());
		}
		return ex;
	}
	
	private List<ReportAccountFundDTO> accountFundYearReport2DTO(List<ReportAccountFundYear> annualReports) {
		List<ReportAccountFundDTO> annualDTOs = Lists.newArrayList();
		if (CollectionUtils.isEmpty(annualReports)) {
			return annualDTOs;
		}
		for (ReportAccountFundYear report : annualReports) {
			ReportAccountFundDTO dto = new ReportAccountFundDTO();

			dto.setReportDate(report.getReportDate());

			dto.setCurrentBalance(MoneyUtil.formatMoney(report.getCurrentBalance()));
			dto.setCurrentOwnSupervisionBalance(MoneyUtil.formatMoney(report.getCurrentOwnSupervisionBalance()));
			dto.setCurrentOtherSupervisionBalance(MoneyUtil.formatMoney(report.getCurrentOtherSupervisionBalance()));

			dto.setLastBalance(MoneyUtil.formatMoney(report.getLastBalance()));
			dto.setLastOwnSupervisionBalance(MoneyUtil.formatMoney(report.getLastOwnSupervisionBalance()));
			dto.setLastOtherSupervisionBalance(MoneyUtil.formatMoney(report.getLastOtherSupervisionBalance()));

			dto.setDepositFund(formatMoney(report.getDepositFund()));

			dto.setTransferFund(formatMoney(report.getTransferFund()));
			dto.setWithdrawFund(formatMoney(report.getWithdrawFund()));
			dto.setWithdrawFee(formatMoney(report.getWithdrawFee()));
			dto.setFreezeFund(formatMoney(report.getFreezeFund()));
			dto.setUnfreezeFund(formatMoney(report.getUnfreezeFund()));
			dto.setRefundFund(formatMoney(report.getRefundFund()));

			dto.setSupervisionDepositFund(formatMoney(report.getSupervisionDepositFund()));

			dto.setSupervisionTransferFund(formatMoney(report.getSupervisionTransferFund()));
			dto.setSupervisionWithdrawFund(formatMoney(report.getSupervisionWithdrawFund()));
			dto.setSupervisionWithdrawFee(formatMoney(report.getSupervisionWithdrawFee()));
			dto.setSupervisionFreezeFund(formatMoney(report.getSupervisionFreezeFund()));
			dto.setSupervisionUnfreezeFund(formatMoney(report.getSupervisionUnfreezeFund()));
			dto.setSupervisionRefundFund(formatMoney(report.getSupervisionRefundFund()));

			annualDTOs.add(dto);
		}
		Collections.sort(annualDTOs);
		return annualDTOs;
	}

	@Override
	public ListResult<ReportAccountFundCountDTO> queryAccountFundDailyCountReport(
			AccountFundDailyReportQueryDTO query) {
		ReportAccountFundCountExample ex = getAccountFundCountReportQueryEx(query);
		ex.setOrderByClause("report_date desc");
//		ex.setOrderByClause("id desc");
		ex.setLimitStart((query.getPage() - 1) * query.getPageSize());
		ex.setLimitEnd(query.getPageSize());
		List<ReportAccountFundCount> dailyCounts = reportAccountFundCountMapper.selectByExample(ex);
		int count = reportAccountFundCountMapper.countByExample(ex);
		List<ReportAccountFundCountDTO> countDTOs = accountFundDailyCountReport2DTO(dailyCounts);
		return ListResult.success(countDTOs, count);
	}

	private List<ReportAccountFundCountDTO> accountFundDailyCountReport2DTO(List<ReportAccountFundCount> reports){
		List<ReportAccountFundCountDTO> countDTOs = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(reports)) {
			for (ReportAccountFundCount report : reports) {
				ReportAccountFundCountDTO countDTO = new ReportAccountFundCountDTO();
				BeanUtils.copyProperties(report, countDTO);
				countDTOs.add(countDTO);
			}
		}
		Collections.sort(countDTOs);
		return countDTOs;
	}
	
	private ReportAccountFundCountExample getAccountFundCountReportQueryEx(AccountFundDailyReportQueryDTO query){
		ReportAccountFundCountExample ex = new ReportAccountFundCountExample();
		ReportAccountFundCountExample.Criteria criteria = ex.createCriteria();
		criteria.andCustomerIdEqualTo(query.getCustomerId());
		if (StringUtils.isNotBlank(query.getStartTime())) {
			criteria.andReportDateGreaterThanOrEqualTo(query.getStartTime());
		}
		if (StringUtils.isNotBlank(query.getEndTime())) {
			criteria.andReportDateLessThanOrEqualTo(query.getEndTime());
		}
		return ex;
	}
	
	@Override
	public List<ReportAccountFundDTO> exportAcountFundDailyReport(AccountFundDailyReportQueryDTO query) {
		ReportAccountFundDailyExample ex = getDailyAccountFundReportQueryEx(query);
		List<ReportAccountFundDaily> dailyFunds = reportAccountFundDailyMapper.selectByExample(ex);
		List<ReportAccountFundDTO> dtos = dailyAccountFundReport2DTO(dailyFunds);
		return dtos;
	}

	@Override
	public List<ReportAccountFundDTO> exportAcountFundYearReport(AccountFundYearReportQueryDTO query) {
		ReportAccountFundYearExample ex = getAccountFundYearReportQueryEx(query);
		List<ReportAccountFundYear> dailyFunds = reportAccountFundYearMapper.selectByExample(ex);
		List<ReportAccountFundDTO> dtos = accountFundYearReport2DTO(dailyFunds);
		return dtos;
	}

	@Override
	public List<ReportAccountFundCountDTO> exportAcountFundDailyCountReport(AccountFundDailyReportQueryDTO query) {
		ReportAccountFundCountExample ex = getAccountFundCountReportQueryEx(query);
		List<ReportAccountFundCount> dailyCounts = reportAccountFundCountMapper.selectByExample(ex);
		List<ReportAccountFundCountDTO> countDTOs = accountFundDailyCountReport2DTO(dailyCounts);
		return countDTOs;
	}

	private String formatMoney(Long value) {
		return MoneyUtil.formatMoney(Math.abs(value));
	}
}
