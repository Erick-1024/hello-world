package com.cana.repayment.service;

import java.math.BigDecimal;

import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.vbam.common.repayment.dto.ChargeDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanAdjustmentIntegration;

public interface IRepaymentPlanService {
	
	/**
	 * 查询符合条件的还款计划的个数
	 * @return
	 * @throws Exception
	 */
	public int queryRepaymentPlanCount(RepaymentPlanExample repaymentPlanExample) throws Exception;
	
	/**
	 * 根据还款自动分配
	 * @param planList
	 * @param charge
	 * @return
	 * @throws Exception
	 */
	public void distributePlanWithRepayment(RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration,
			ChargeDTO chargeDTO, BigDecimal earlyRepaymentRatio) throws Exception;

	
	/**
	 * 根据当前时间判断计划是当前期, -1是往期，0是当期还款日，1是当期非还款日，2未来期
	 * @param plan
	 * @return
	 */
	public String judgePlanPeriodByNowDate(String valueDate, String repaymentDate, int extenseDays);
}
