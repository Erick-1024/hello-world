package com.cana.vbam.common.message.enums;

/**
 * 通知消息类型
 * @author XuMeng
 *
 */
public enum NotificationType {
    CUSTOMER_REGISTRY("企业用户审批"),
    CREATE_ACCOUNT("开户账户审核"),
    CREATE_SUPERVISION("新建监管审批"),
    REMOVE_SUPERVISION("解除监管审批"),
    TRANSFER_FUND("转账审批"),
    WITHDRAW_FUND("提现审批"),
    AUTO_DEDUCT_FUND("自动扣款"),
    REFUND_REPAYMENT("退款还款"),
    ACTIVE_REPAYMENT("主动还款"),
    ADJUST_FUND("调账")
    ;

    private String desc;
    NotificationType(String desc) {
        this.desc = desc;
    }
    public String desc() {
        return desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
