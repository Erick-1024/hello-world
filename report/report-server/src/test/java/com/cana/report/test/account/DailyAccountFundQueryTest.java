/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.test.account;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.report.dao.mapper.gen.ReportAccountFundDailyMapper;
import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.service.IAccountFundReportService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration("classpath*:META-INF/spring/*.xml")
public class DailyAccountFundQueryTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IAccountFundReportService accountFundReportService;
	@Resource
	private ReportAccountFundDailyMapper rportAccountFundDailyMapper;
	
	public void queryDailyFundReport(){
		AccountFundDailyReportQueryDTO dto = new AccountFundDailyReportQueryDTO();
		dto.setCustomerId("201512081397");
		dto.setPage(1);
		dto.setPageSize(10);
		ListResult<ReportAccountFundDTO> result = accountFundReportService.queryAccountFundDailyReport(dto);
		System.out.println(result.getData().size());
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void insert(){
		ReportAccountFundDaily fund = new ReportAccountFundDaily();
		fund.setCustomerId("201512081397");
		fund.setReportDate(DateTimeUtil.date8());
		fund.setCreateTime(new Date());
		rportAccountFundDailyMapper.insertSelective(fund);
		System.out.println(fund.getId());
	}
}
