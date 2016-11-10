package com.cana.vbam.common.account.enums;

/**
 * 转账规则
 * @author XuMeng
 *
 */
public enum TradeRuleResult {
    REJECT,     //不可转
    NEED_AUDIT, //需要审核
    ACCEPT;     //可以立即转
}
