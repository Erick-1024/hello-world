package com.cana.credit.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.vbam.common.report.dto.MonitorMoneyDTO;

public interface OutCustomerDataCustomMapper {

	/**
	 * 查询真旅客户的在某一天内的销售额
	 * @param date10 日期
	 * @return
	 */
	@Select("select l.out_customer_id outCustomerId, l.member_id memberId, r.price " + 
			"from vbam.credit_out_customer as l " +
			"left join (select * from travelzen.daily_bill where date =#{date10}) as r " + 
			"on l.out_customer_id = r.customer_id " +
			"where l.institution_id ='travelzen'")
	public List<MonitorMoneyDTO> getFlightTicketSales(@Param("date10") String date10);
	
	/**
	 * 批量计算真旅客户的合格AR余额(有价值客票票的（票面价+总税款）/总航班数×未起飞的航班数)
	 * @param startRecordId 搜索开始位置
	 * @return
	 */
	@Select("select l.out_customer_id outCustomerId, l.member_id memberId, r.price from vbam.credit_out_customer as l left join (SELECT customer_id, sum(ticket_price + total_tax) price " +
			"FROM travelzen.flight_ticket " + 
			"where record_id > #{startRecordId} and is_valuable = true and complete_Issue_time < date_format(now(), '%Y-%m-%d') " +
			"group by customer_id) as r on l.out_customer_id = r.customer_id")
	public List<MonitorMoneyDTO> getQualifiedAR(@Param("startRecordId") String startRecordId);

	/**
	 * 获取真旅日均销售额
	 * @param startMonth 开始年月（包含）
	 * @param endMonth 结束年月（不包含）
	 * @param dayNumber 天数
	 * @return
	 */
	@Select("select l.out_customer_id outCustomerId, l.member_id memberId, r.price from vbam.credit_out_customer as l left join " +
			"(SELECT customer_id, (sum(price)/#{dayNumber}) price " +
			"FROM travelzen.daily_bill " +
			"where date >= #{startMonth} and date < #{endMonth} " +
			"group by customer_id) as r " +
			"on l.out_customer_id = r.customer_id")
	public List<MonitorMoneyDTO> getDailySales(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth, @Param("dayNumber") int dayNumber);
	
}
