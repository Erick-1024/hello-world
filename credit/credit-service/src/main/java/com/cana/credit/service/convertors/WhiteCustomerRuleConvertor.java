/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.credit.service.convertors;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.credit.dao.po.WhiteCustomerRule;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
public class WhiteCustomerRuleConvertor {

	public static List<WhiteCustomerRuleDTO> PO2DTO4WhiteCustomerRule(List<WhiteCustomerRule> rules) {
		List<WhiteCustomerRuleDTO> whiteCustomerRuleDTOs = Lists.newArrayList();
		if (rules.isEmpty()) {
			return whiteCustomerRuleDTOs;
		}
		for (WhiteCustomerRule rule : rules) {
			WhiteCustomerRuleDTO whiteCustomerRuleDTO = PO2DTO4WhiteCustomerRule(rule);
			whiteCustomerRuleDTOs.add(whiteCustomerRuleDTO);
		}
		return whiteCustomerRuleDTOs;
	}

	public static WhiteCustomerRuleDTO PO2DTO4WhiteCustomerRule(WhiteCustomerRule rule) {
		WhiteCustomerRuleDTO whiteCustomerRuleDTO = new WhiteCustomerRuleDTO();
		BeanUtils.copyProperties(rule, whiteCustomerRuleDTO);
		RuleDTO ruleDTO = new Gson().fromJson(rule.getRule(), RuleDTO.class);
		whiteCustomerRuleDTO.setCooperationPeriod(ruleDTO.getCooperationPeriod());
		whiteCustomerRuleDTO.setPurchaseOrderGrowthRate(ruleDTO.getPurchaseOrderGrowthRate());
		whiteCustomerRuleDTO.setOverdueRate(ruleDTO.getOverdueRate());
		whiteCustomerRuleDTO.setOverdueTimes(ruleDTO.getOverdueTimes());
		whiteCustomerRuleDTO.setCreateTime(DateTimeUtil.format(rule.getCreateTime(), "yyyy-MM-dd"));
		whiteCustomerRuleDTO.setUpdateTime(DateTimeUtil.format(rule.getUpdateTime(), "yyyy-MM-dd"));
		return whiteCustomerRuleDTO;
	}

	class RuleDTO {
		/**
		 * 与客户来源公司的合作月份的最小值
		 */
		private Integer cooperationPeriod;
		/**
		 * 订单采购增长率
		 */
		private BigDecimal purchaseOrderGrowthRate;
		/**
		 * 逾期率
		 */
		private BigDecimal overdueRate;
		/**
		 * 逾期次数
		 */
		private Integer overdueTimes;

		public Integer getCooperationPeriod() {
			return cooperationPeriod;
		}

		public void setCooperationPeriod(Integer cooperationPeriod) {
			this.cooperationPeriod = cooperationPeriod;
		}

		public BigDecimal getPurchaseOrderGrowthRate() {
			return purchaseOrderGrowthRate;
		}

		public void setPurchaseOrderGrowthRate(BigDecimal purchaseOrderGrowthRate) {
			this.purchaseOrderGrowthRate = purchaseOrderGrowthRate;
		}

		public BigDecimal getOverdueRate() {
			return overdueRate;
		}

		public void setOverdueRate(BigDecimal overdueRate) {
			this.overdueRate = overdueRate;
		}

		public Integer getOverdueTimes() {
			return overdueTimes;
		}

		public void setOverdueTimes(Integer overdueTimes) {
			this.overdueTimes = overdueTimes;
		}
	}
}
