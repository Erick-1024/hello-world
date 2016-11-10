/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.scheduler.test.fund;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.report.dao.mapper.gen.ReportAccountFundDailyMapper;
import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.service.IAccountFundPullDataService;
import com.cana.report.service.IAccountFundReportService;
import com.cana.report.service.bo.BalanceReportBO;
import com.cana.report.service.bo.TradeReportBO;
import com.cana.vbam.common.account.dto.AccountCustomerTradeRecordQueryDTO;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/report-scheduler-*.xml")
public class FundReportTest {

	@Resource
	private IUserApi userApi;
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IAccountFundPullDataService accountFundPullDataService;
	@Resource
	private IVbamCommonService commonService;
	@Resource
	private ReportAccountFundDailyMapper reportAccountFundDailyMapper;
	@Resource
	private IAccountFundReportService accountFundReportService;
	
	
	public void test(){
		List<AccountGroupDTO> groups = accountApi.getOwnAccountGroups("201511070610");
		System.out.println(groups.size());
		for(AccountGroupDTO group : groups){
			System.out.println(group.getAccountNo());
		}
	}
	
	
	public void testTradeReport(){
		System.out.println("start");
		DateTime date = DateTimeUtil.parseDate10("2015-12-28");
		TradeReportBO bo = accountFundPullDataService.pullCustomerTradeRecord("201512211525", date);
		System.out.println(bo.getFundReportState().name());
		System.out.println(JSON.toJSON(bo));
		System.out.println(DateTimeUtil.datetime14(DateTime.now().withTime(0, 0, 0, 0)));
		System.out.println(DateTimeUtil.datetime14(DateTime.now().plusDays(1).withTime(0, 0, 0, 0)));
	}
	
	
	public void testTradeRecordQuery(){
		DateTime date = DateTimeUtil.parseDate10("2015-12-28");
		List<AccountTradeRecordDTO> result = accountApi.getCustomerTradeRecord(getParam("201512211525",date,1,100));
		System.out.println(result.size());
		System.out.println(JSON.toJSON(result));
	}
	
	private AccountCustomerTradeRecordQueryDTO getParam(String customerId,DateTime reportDate, int page, int pageSize) {
		AccountCustomerTradeRecordQueryDTO query = new AccountCustomerTradeRecordQueryDTO();
		query.setCustomerId(customerId);
		query.setAccountTradeTypes(Lists.newArrayList(
				AccountTradeType.TRANSFER_FUND,
				AccountTradeType.WITHDRAW_FUND,
				AccountTradeType.FREEZE,
				AccountTradeType.UNFREEZE));
		query.setMinEndDate(reportDate.withTime(0, 0, 0, 0).toDate());
		query.setMaxEndDate(reportDate.plusDays(1).withTime(0 , 0, 0, 0).toDate());
		query.setPage(page);
		query.setPageSize(pageSize);
		return query;
	}
	
	
	public void testUniqueIndexInDailyAccountFundReport(){
		ReportAccountFundDaily report = new ReportAccountFundDaily();
		report.setCustomerId("111111");
		report.setReportDate(DateTimeUtil.date10());
		report.setFundReportState(2);
		report.setCreateTime(new Date());
		try{
			reportAccountFundDailyMapper.insertSelective(report);
		}catch(Exception e){
			if(ExceptionUtils.indexOfThrowable(e, DuplicateKeyException.class)!=-1){
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			}else{
				System.out.println("ccccccccccccccccccccc");
			}
		}
	}
	
	
	public void testPullLastBalance(){
		System.out.println(pullLastBalance("201512211517",DateTimeUtil.parseDate10("2015-12-29")));
	}
	
	private Long pullLastBalance(String customerId,DateTime reportDate){
		AccountFundDailyReportQueryDTO query = new AccountFundDailyReportQueryDTO();
		query.setCustomerId(customerId);
		query.setStartTime(DateTimeUtil.date10(reportDate.minusDays(1)));
		query.setEndTime(DateTimeUtil.date10(reportDate));
		ListResult<ReportAccountFundDTO> report = accountFundReportService.queryAccountFundDailyReport(query);
		if (!CollectionUtils.isEmpty(report.getData())) {
			String lastBalance = report.getData().get(0).getCurrentBalance();
			if (StringUtils.isNotBlank(lastBalance)) {
				return MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(lastBalance));
			}
		}
		return 0L;
	}
	
	@Test
	public void testBalanceReport(){
	    BalanceReportBO bo = accountFundPullDataService.pullCustomerBalanceReport("201511070610", DateTime.now());
	    System.out.println(bo.getFundReportState().toString());
	}
}
