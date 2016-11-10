package com.cana.repayment.service.transaction.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentRuleMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-repayment-service-*.xml")
public class RepaymentTransactionServiceTest {
	
	@Resource
	private IRepaymentTransactionService service;
	
	@Resource
	private RepaymentRuleMapper ruleMapper;
	
	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	private IRepositoryService repositoryService;

	@Test
	public void addTravelzenFinanceLoan() throws Exception{
		CreateLoanRequest request = preparePayInfo();
		service.addTravelzenFinanceLoan(request);
		service.addTravelzenFinanceLoan(request);
	}

	private CreateLoanRequest preparePayInfo() {
		CreateLoanRequest payInfo = new CreateLoanRequest();
		payInfo.setAccountNo("3110210003631012694");
		payInfo.setAccountSupervisionId("160407174856101");
		payInfo.setFactorCompany("Cana资金");
		payInfo.setFactorId("cana-baoli");
		payInfo.setFinanceCompany("finance company");
		payInfo.setFinanceId("201604070003");
		payInfo.setFinanceAmount(100000);
		payInfo.setLoanDate(DateTimeUtil.date10());
		
		payInfo.setInterestRateUnit(InterestRateUnit.DAY);
		payInfo.setInterestRate(new BigDecimal("0.0005"));
		
		return payInfo;
	}
	
	@Test
	public void refundByTravelzenFinancier() throws Exception{
		TravelzenUserRefundInfo	refundInfo = prepareRefundInfo();
		System.out.println(new Gson().toJson(service.refundByTravelzenFinancier(refundInfo)));
	}
	
	@Test
	public void deleteLoanInfos() throws Exception{
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andFactorIdEqualTo("factorId")
		                        .andFinanceIdEqualTo("finance id");
		loanInfoMapper.deleteByExample(example);
	}
	
	private TravelzenUserRefundInfo prepareRefundInfo(){
		TravelzenUserRefundInfo refundInfo = new TravelzenUserRefundInfo();
		refundInfo.setFinanceId("finance id");
		refundInfo.setTxnId(RandomStringUtils.randomAlphanumeric(10));
		refundInfo.setRefundAmount(200000);
		return refundInfo;
	}
	
	@Test
	public void createRepaymentRule(){
		RepaymentRule rule = new RepaymentRule();
		rule.setId(RandomStringUtils.randomAlphanumeric(10));
		rule.setFactorId("factorId");
		rule.setFactorTransferInAccountNo("3110210003631009624");
		rule.setDeductionTime("20:00");
		rule.setDeductionRule("PART");
		rule.setExtensionDays(1);
		rule.setExtensionRatio(new BigDecimal("0.01"));
		rule.setPenaltyRate(new BigDecimal("0.01"));
		rule.setEarlyRepaymentChargeRatio(new BigDecimal("0.03"));
		rule.setCreateTime(new Date());
		ruleMapper.insertSelective(rule);
	}
	
	

}
