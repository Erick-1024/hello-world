package com.cana.flight.finance.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RepaymentCustomMapper {
	
	@Select("select count(*) from travelzen.repayment "
			+ "where customer_id=#{customerId} and repayment_date>=#{repaymentDateBegin} and repayment_date<#{repaymentDateEnd} "
			+ "and ((actual_repayment_date is not null and actual_repayment_date !='' and actual_repayment_date>late_repayment_date) or ((actual_repayment_date is null or actual_repayment_date ='') and now()>late_repayment_date))")
	int countOverdue(@Param("customerId") String customerId,@Param("repaymentDateBegin") String repaymentDateBegin,@Param("repaymentDateEnd") String repaymentDateEnd);

	@Select(
			"select (case when A.sumOverdue is null then 0 else A.sumOverdue end) + (case when B.sumOverdue is null then 0 else B.sumOverdue end) from "
			+ "("
			+ "select sum(datediff(actual_repayment_date,late_repayment_date)) as sumOverdue from travelzen.repayment "
			+ "where customer_id=#{customerId} and repayment_date>=#{repaymentDateBegin} and repayment_date<#{repaymentDateEnd} " 
			+ "and actual_repayment_date is not null and actual_repayment_date !='' and datediff(actual_repayment_date,late_repayment_date)>0"
			+ ") A"
			+ ","
			+ "("
			+ "select sum(datediff(now(),late_repayment_date)) as sumOverdue from travelzen.repayment " 
			+ "where customer_id=#{customerId} and repayment_date>=#{repaymentDateBegin} and repayment_date<#{repaymentDateEnd} " 
			+ "and (actual_repayment_date is null or actual_repayment_date ='') and datediff(now(),late_repayment_date)>0"
			+ ") B"
			)
	Integer sumOverdueDays(@Param("customerId") String customerId,@Param("repaymentDateBegin") String repaymentDateBegin,@Param("repaymentDateEnd") String repaymentDateEnd);
}