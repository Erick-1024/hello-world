package com.cana.repayment.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.repayment.dao.po.RepaymentPlan;

public interface IRepaymentRemindMapper {

	@Select("select plan.* from repayment_loan_info info,repayment_plan plan where info.business_product_id=#{businessProductId} and plan.repayment_date>=#{startdate} and plan.repayment_date<=#{enddate} and plan.settle_status='UNSETTLE' and info.id=plan.loan_info_id ")
	public List<RepaymentPlan> getAllRepaymentPlanBeforeRepaymentDate(@Param("businessProductId") String businessProductId, @Param("startdate") String startdate, @Param("enddate") String enddate);

	@Select("select plan.* from repayment_loan_info info,repayment_plan plan where info.business_product_id = #{businessProductId} and plan.repayment_date = #{startdate} and plan.settle_status='UNSETTLE' and info.id = plan.loan_info_id ")
	public List<RepaymentPlan> getAllRepaymentPlanOnRepaymentDate(@Param("businessProductId") String businessProductId, @Param("startdate") String startdate);

	@Select("select plan.* from repayment_loan_info info,repayment_plan plan where info.business_product_id = #{businessProductId} and plan.repayment_date < #{startdate} and plan.settle_status='UNSETTLE' and info.id = plan.loan_info_id ")
	public List<RepaymentPlan> getAllOverdueRepaymentPlan(@Param("businessProductId") String businessProductId, @Param("startdate") String startdate);
	
}
