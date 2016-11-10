package com.cana.credit.server.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cana.credit.dao.mapper.gen.WhiteCustomerRuleMapper;
import com.cana.credit.dao.po.WhiteCustomerRule;
import com.cana.credit.dao.po.WhiteCustomerRuleExample;
import com.cana.credit.service.IWhiteListService;
import com.cana.credit.service.utils.CreditLimitCalculateUtil3;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerParamDTO;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.util.MoneyUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class TestWhiteGenerateLimit {

	@Resource
	private IWhiteListService whiteListService;

	@Resource
	private WhiteCustomerRuleMapper whiteCustomerRuleMapper;
	
	@Resource
	private DailyBillMapper dailyBillMapper;
	
	@Test
	@Transactional
	public void tyhtest() {
		Integer newestBatchNo = null;
		WhiteCustomerRuleExample example = new WhiteCustomerRuleExample(); 
		example.setOrderByClause("batch_no desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		List<WhiteCustomerRule> whiteCustomerRules = whiteCustomerRuleMapper.selectByExample(example);
		if (whiteCustomerRules == null || whiteCustomerRules.isEmpty())
			newestBatchNo = new Integer(0);
		else
			newestBatchNo = whiteCustomerRules.get(0).getBatchNo();
		WhiteCustomerParamDTO param = new WhiteCustomerParamDTO();
		param.setPageSize(1000);
		param.setBatchNo(newestBatchNo);
		PageList<WhiteCustomerDTO> whiteCustomers = null;
		try{
			whiteCustomers = whiteListService.getWhiteCustomers(param);
		}catch(Exception e){
			System.out.println(e);
		}
		
		List <WhiteCustomerDTO> wcs = whiteCustomers.getRecords();
			for (WhiteCustomerDTO whiteCustomer : wcs) {
				CalculateLimitResult finalLimit = CreditLimitCalculateUtil3.calculateCreditLimit(whiteCustomer.getTzCustomerId());
//				CalculateLimitResult finalLimit = CreditLimitCalculateUtil2.calculateCreditLimit(whiteCustomer.getTzCustomerId());
//				CalculateLimitResult finalLimit = CreditLimitCalculateUtil.calculateCreditLimit(whiteCustomer.getTzCustomerId(), 1000000000l);
//				CalculateLimitResult finalLimit = CreditLimitCalculateUtil.calculateCreditLimit(whiteCustomer.getTzCustomerId());
				System.out.println(whiteCustomer.getTzCustomerId()+","+whiteCustomer.getCustomerName()+","+MoneyUtil.cent2Yuan(finalLimit.getFinalLimit()));
			}
	}
	
	private String[] customerIds = {
			"5271bb8445ce9ad7b9caab7b",
			"5270de4b45ce9ad7b9caab30",
			"5270dcad45ce917554cb9145",
			"5270d9a145ce9ad7b9caab1c",
			"5270823b45ce917554cb909c",
			"52707fa845ce917554cb9088",
			"52707cf445ce04ba2b2f46ec",
			"52707b6845ce9ad7b9caaa86",
			"526e380445ce0a07954a5b5f",
			"526b3c1345ce7d14b0483014",
			"526b27a545ce32f9b8092b64",
			"526b249d45ce32f9b8092b5a",
			"526b239645ce32f9b8092b50",
			"526b1a1a45ce7d14b0482fc4",
			"526b18a645ce32f9b8092b23",
			"526b167345ce7d14b0482fba",
			"526b11b745ce1296e705dd0f",
			"526b10ae45ce7d14b0482fab",
			"526b098545ce1296e705dcf6",
			"5268d6f245ce53aa938538f9",
			"5268d66045ce53aa938538f4",
			"5268bdb145ce53aa938538d6",
			"5268bc3245ce53aa938538c4",
			"5268bbcd45ce53aa938538be",
			"5268bb0a45ce92c1ee4f9b0c"
	};
	
	
	@Test
	@Transactional
	public void test() {
		for (String id : customerIds) {
//			CalculateLimitResult limit = CreditLimitCalculateUtil2.calculateCreditLimit(id);
			CalculateLimitResult limit = CreditLimitCalculateUtil3.calculateCreditLimit(id);
			System.out.println(MoneyUtil.cent2Yuan(limit.getFinalLimit()));
		}
	}
	
	@Test
	@Transactional
	public void testLimit() {
		String customerId = "57bd6987e4b005789be63b69";
		CalculateLimitResult limit = CreditLimitCalculateUtil3.calculateCreditLimit(customerId);
		System.out.println(MoneyUtil.cent2Yuan(limit.getFinalLimit()));
	}
	
}
