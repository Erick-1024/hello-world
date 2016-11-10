package com.cana.credit.server.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.cana.credit.dao.mapper.gen.WhiteCustomerMapper;
import com.cana.credit.dao.po.WhiteCustomer;
import com.cana.credit.dao.po.WhiteCustomerExample;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.flight.finance.dao.utils.IDGenerator;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class TestNewTzCustomerAndInWhite2 {
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;
	
	@Resource
	private WhiteCustomerMapper whiteCustomerMapper;
	
	@Resource
	private DailyBillMapper dailyBillMapper;
	
	private int rule_batch_no = 10;	//当前正在生效的白名单规则批次号
	
	@Test
	public void whiteTest() {
		addNewTzCustomerAndInWhite("580dbe33e4b029964d56ff51，580dbe8ce4b029964d56ff59");//白名单！！
		System.out.println("success");
	}
	
	@Test
	public void notWhiteTest() {
		addNewTzCustomerAndNotInWhite("57b3d48ee4b0ad122c4dfae3");//非非非！！
		System.out.println("success");
	}
	
	public void addNewTzCustomerAndInWhite(String tzCustomerIdStr){
		List<String> tzCustomerIds = Arrays.asList(tzCustomerIdStr.split("，"));
		for(String tzCustomerId : tzCustomerIds){
			TzCustomerInfoExample example = new TzCustomerInfoExample();
			example.createCriteria().andTzCustomerIdEqualTo(tzCustomerId);
			List<TzCustomerInfo> tzCustomer = tzCustomerInfoMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(tzCustomer)) {
				TzCustomerInfo tzCustomerInfo = new TzCustomerInfo();
				tzCustomerInfo.setCustomerNames("00000测试用户");
				tzCustomerInfo.setTzCustomerId(tzCustomerId);
				tzCustomerInfo.setFirstBusinessTime("2000-01-01");
				tzCustomerInfoMapper.insertSelective(tzCustomerInfo);
			}
			tzCustomer = tzCustomerInfoMapper.selectByExample(example);
	
			WhiteCustomerExample whiteEexample = new  WhiteCustomerExample();
			whiteEexample.createCriteria().andTzCustomerIdEqualTo(tzCustomerId);
			List<WhiteCustomer> whiteC = whiteCustomerMapper.selectByExample(whiteEexample);
			
			if (CollectionUtils.isEmpty(whiteC)) {
				WhiteCustomer whiteCustomer = new WhiteCustomer();
				whiteCustomer.setId(IDGenerator.generateWhiteCustomerId());
				whiteCustomer.setTzShortId(tzCustomer.get(0).getTzShortId());
				whiteCustomer.setTzCustomerId(tzCustomerId);
				whiteCustomer.setCustomerName("00000白名单用户");
				whiteCustomer.setCooperationPeriod(new Integer(100));
				whiteCustomer.setPurchaseOrderGrowthRate(new BigDecimal(100));
				whiteCustomer.setOverdueRate(new BigDecimal(0));
				whiteCustomer.setOverdueTimes(new Integer(0));
				whiteCustomer.setRuleBatchNo(new Integer(rule_batch_no));
				whiteCustomer.setCreateTime(new Date());
				whiteCustomer.setUpdateTime(new Date());
				whiteCustomerMapper.insertSelective(whiteCustomer);
			}
	
			DateTime datetime = new DateTime().minusYears(2).minusMonths(2);
			long limit = 10000;
			for (DateTime date = datetime; date.isBeforeNow(); date = date.plusMonths(1)) {
				DailyBill farDailyBill = new DailyBill();
				farDailyBill.setId(IDGenerator.generateDailyBillIdPrefix());
				farDailyBill.setCustomerId(tzCustomerId);
				farDailyBill.setDate(DateTimeUtil.format(date, DateTimeUtil.DATE_PATTERN));
				farDailyBill.setPrice(limit);
				farDailyBill.setCreateTime(new Date());
				dailyBillMapper.insertSelective(farDailyBill);
				limit += RandomUtils.nextLong(1000, 10000);
			}
		}
	}
	
	public void addNewTzCustomerAndNotInWhite(String tzCustomerIdStr){
		List<String> tzCustomerIds = Arrays.asList(tzCustomerIdStr.split("，"));
		for(String tzCustomerId : tzCustomerIds){
			TzCustomerInfoExample example = new TzCustomerInfoExample();
			example.createCriteria().andTzCustomerIdEqualTo(tzCustomerId);
			List<TzCustomerInfo> tzCustomer = tzCustomerInfoMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(tzCustomer)) {
				TzCustomerInfo tzCustomerInfo = new TzCustomerInfo();
				tzCustomerInfo.setCustomerNames("00000非白名单用户");
				tzCustomerInfo.setTzCustomerId(tzCustomerId);
				tzCustomerInfo.setFirstBusinessTime("2015-07-01");
				tzCustomerInfoMapper.insertSelective(tzCustomerInfo);
			}
			tzCustomer = tzCustomerInfoMapper.selectByExample(example);
	
			DateTime datetime = new DateTime().minusYears(1).minusMonths(2);
			long limit = 10000;
			for (DateTime date = datetime; date.isBeforeNow(); date = date.plusMonths(1)) {
				DailyBill farDailyBill = new DailyBill();
				farDailyBill.setId(IDGenerator.generateDailyBillIdPrefix());
				farDailyBill.setCustomerId(tzCustomerId);
				farDailyBill.setDate(DateTimeUtil.format(date, DateTimeUtil.DATE_PATTERN));
				farDailyBill.setPrice(limit);
				farDailyBill.setCreateTime(new Date());
				dailyBillMapper.insertSelective(farDailyBill);
				limit += RandomUtils.nextLong(1000, 10000);
			}
		}
	}
		
	
}
