package com.cana.repayment.service.transaction.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.repayment.dao.mapper.IRepaymentPlanHolidayMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.transaction.IHolidayPolicyTransactionService;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.google.common.collect.Lists;

@Service
public class HolidayPolicyTransactionServiceImpl implements IHolidayPolicyTransactionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IRepaymentServiceHelper repaymentServiceHelper;
	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	@Resource
	private IRepaymentPlanHolidayMapper repaymentPlanHolidayMapper;

	@Override
	public List<String> getAllEffectedLoanInfoIds(Set<String> effectDate10s, List<String> useHolidayPolicyProjectIds) {
		if (CollectionUtils.isEmpty(effectDate10s) || CollectionUtils.isEmpty(useHolidayPolicyProjectIds))
			return Lists.newArrayList();

		return repaymentPlanHolidayMapper.getAllEffectedLoanInfoIds(effectDate10s, useHolidayPolicyProjectIds);
	}

	@Override
	public void updateExtensionDays(String loanInfoId, Map<String, Integer> effectDateAndExtensionDaysMap) {

		RepaymentLoanInfoBO loanInfoBO = repaymentServiceHelper.lockLoanInfoById(loanInfoId);
		for (RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()) {
			Integer holidayExtensionDays = effectDateAndExtensionDaysMap.get(planBO.getRepaymentDate());
			if (holidayExtensionDays == null)
				continue;

			int extensionDays = loanInfoBO.lazyLoadRepaymentConfig().getExtensionDays();
			if (extensionDays < holidayExtensionDays)
				extensionDays = holidayExtensionDays;
			// 展期天数没有变化
			if (extensionDays == planBO.getExtensionDays())
				continue;

			logger.info("节假日变更，影响放款[{}]的第{}期还款计划展期天数由{}变更为{}", loanInfoId,
					planBO.getRepaymentPeriod(), planBO.getExtensionDays(), extensionDays);
			planBO.setExtensionDays(extensionDays);
			planBO.setUpateTime(new Date());
			repaymentPlanMapper.updateByPrimaryKeySelective(planBO);
		}

	}

}
