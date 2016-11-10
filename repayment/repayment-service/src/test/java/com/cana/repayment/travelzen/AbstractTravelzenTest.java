package com.cana.repayment.travelzen;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;

import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentProductMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentRuleMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskExample;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.travelzen.framework.core.time.DateTimeUtil;

public class AbstractTravelzenTest {
	
	protected static final String FACTOR_ID = "201602270009";  
	
	protected static final String FINANCE_ID = "201602270008";  
	
	@Resource
	protected RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	protected RepaymentRuleMapper ruleMapper;
	
	@Resource
	protected RepaymentProductMapper productMapper;
	
	@Resource
	protected IRepaymentTransactionService service;
	
	@Resource
	protected IVbamCommonService commonService;
	
	@Resource
	protected RepaymentDailyBatchTaskMapper taskMapper;
	
	@Before
	public void before() throws Exception{
		createRepaymentRule();
		createRepaymentProduct();
		deleteLoanInfosAndBatchTasks();
	}
	
	private void createRepaymentRule() {
		
		RepaymentRuleExample example = new RepaymentRuleExample();
		example.createCriteria().andFactorIdEqualTo(FACTOR_ID);
		if(CollectionUtils.isNotEmpty(ruleMapper.selectByExample(example)))
			return;
		
		RepaymentRule rule = new RepaymentRule();
		rule.setId(RandomStringUtils.randomAlphanumeric(10));
		rule.setFactorId(FACTOR_ID);
		rule.setFactorTransferInAccountNo("42844049507154");
		rule.setDeductionTime("00:00");
		rule.setDeductionRule("PART");
		rule.setExtensionDays(0);
		rule.setExtensionRatio(new BigDecimal("0.00075"));
		rule.setPenaltyRate(new BigDecimal("0.00075"));
		rule.setEarlyRepaymentChargeRatio(new BigDecimal("0.03"));
		rule.setCreateTime(new Date());
		ruleMapper.insertSelective(rule);
		
	}

	
	public void deleteLoanInfosAndBatchTasks() throws Exception{
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andFactorIdEqualTo(FACTOR_ID)
		                        .andFinanceIdEqualTo(FINANCE_ID);
		List<RepaymentLoanInfo> loanInfos = loanInfoMapper.selectByExample(example);
		loanInfoMapper.deleteByExample(example);
		for(RepaymentLoanInfo loanInfo : loanInfos){
			RepaymentDailyBatchTaskExample taskExample = new RepaymentDailyBatchTaskExample();
			taskExample.createCriteria().andLoanInfoIdEqualTo(loanInfo.getId());
			taskMapper.deleteByExample(taskExample);
		}
	}
	
	public void createRepaymentProduct(){
		
//		RepaymentProductExample example = new RepaymentProductExample(); 
//		example.createCriteria().andIdEqualTo(Constants.TRAVLEZEN_FINANCE_PRODUCT_ID);
//		productMapper.deleteByExample(example);
//		
//		RepaymentProduct product = new RepaymentProduct();
//		product.setId(Constants.TRAVLEZEN_FINANCE_PRODUCT_ID);
//		product.setName("真旅金融产品");
//		TravelzenFinanceProduct detail = new TravelzenFinanceProduct();
//		detail.setAccountNo("xxxxxx");
//		detail.setAccountName("真旅网集团");
//		detail.setInterestRateUnit(InterestRateUnit.DAY);
//		detail.setInterestRate(new BigDecimal("0.0005"));
//		detail.setLoanPeriodUnit(DateUnit.MONTH);
//		detail.setLoanPeriod(1);
//		product.setDetail(new Gson().toJson(detail));
//		productMapper.insert(product);
	}	
	
	
	protected void executeBatchTask(String fromDate, String endDate) throws Exception{
		String curDate = fromDate;
		while(curDate.compareTo(endDate) <= 0){
			commonService.saveVirtualDateTime(curDate, "");
			Thread.sleep(TimeUnit.SECONDS.toMillis(60L));
			curDate = DateTimeUtil.addDay10(curDate, 1);
		}
	}

}
