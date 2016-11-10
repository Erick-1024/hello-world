package com.cana.credit.server.test;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.flight.finance.dao.utils.IDGenerator;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class TestNewTzCustomerAndInWhite {
	
	private String customerId = "562cc67d45ce2e4192ea4b16";	//真旅客户ID
	private String customerName = "国森";
	private String rule_batch_no = "2";

	/**
	 * 生成白名单所需要的sql
	 */
	@Test
	public void generateSql() {
		System.out.println();
		System.out.println("生成白名单所需要的sql");
		System.out.println("第一步：检查该客户是否是真旅已经存在的正式客户，即如果下面这条sql返回的数量不是0，则需要真旅重新提供新的客户");
		System.out.println("select count(*) from tz_customer_info where tz_customer_id = '" + customerId + "';");

		System.out.println();
		System.out.println("第二步：生成客户最早交易日期记录");
		System.out.println("INSERT INTO `tz_customer_info` (`tz_customer_id`, `customer_names`, `first_business_time`)");
		System.out.println("VALUES ('" + customerId + "', '" + customerName + "', '2014-01-06');");

		System.out.println();
		System.out.println("第三步：查询出该客户的tz_short_id字段值，并记录，最后一步的sql要用到该值");
		System.out.println("select * from tz_customer_info where tz_customer_id = '" + customerId + "';");
		
		System.out.println();
		System.out.println("第四步：生成该客户的两年的交易数据");
		System.out.println("INSERT INTO `daily_bill` (`id`, `date`, `customer_id`, `price`, `create_time`, `update_time`)");
		System.out.println("VALUES");
		DateTime datetime = new DateTime().minusYears(2).minusMonths(2);
		long limit = 10000;
		String createTime = DateTimeUtil.format(new Date(), DateTimeUtil.DATE_TIME_PATTERN);
		int i = 0;
		for (DateTime date = datetime; date.isBeforeNow();) {
			System.out.print("	('" + IDGenerator.generateDailyBillIdPrefix() + "', '"
					+ DateTimeUtil.format(date, DateTimeUtil.DATE_PATTERN)
					+ "', '" + customerId + "', " + limit + ", '" + createTime + "', '" + createTime + "')");
			
			if (i > 12) {
				limit = 12000;
			}
			date = date.plusMonths(1);
			if (date.isBeforeNow())
				System.out.println(",");
			else
				System.out.println(";");
			i ++;
		}

		System.out.println();
		System.out.println("第五步：将从第三步查询出来的tz_short_id替换此sql中的tz_short_id字段值，并执行");
		System.out.println("INSERT INTO `credit_white_customer` (`id`, `tz_short_id`, `tz_customer_id`, `customer_name`, `cooperation_period`, `purchase_order_growth_rate`, `overdue_rate`, `overdue_times`, `rule_batch_no`, `create_time`, `update_time`)");
		System.out.println("VALUES ('" + IDGenerator.generateWhiteCustomerId() + "', tz_short_id, '" + customerId + "', '" + customerName + "', 27, 0.19075, 0.00000, 0, " + rule_batch_no + ", '" + createTime + "', '" + createTime + "');");

		System.out.println();
	}
}
