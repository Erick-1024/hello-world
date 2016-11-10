package com.cana.repayment.dao.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface IRepaymentPlanHolidayMapper {

	public List<String> getAllEffectedLoanInfoIds(
			@Param("effectDates") Set<String> effectDate10s,
			@Param("useHolidayPolicyProjectIds") List<String> useHolidayPolicyProjectIds);
}
