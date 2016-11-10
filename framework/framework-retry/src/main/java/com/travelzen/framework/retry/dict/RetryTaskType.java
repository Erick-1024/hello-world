package com.travelzen.framework.retry.dict;


public enum RetryTaskType {
	AUDIT_RESULT("审核结果通知"),
    CREDIT_LIMIT_EFFECT("额度生效通知"),
    CREDIT_TRADE_RESULT("授信交易通知"),
    CREDIT_LIMIT_RECOVERY("额度恢复通知"),
    REPAYMENT_SUCCESS_NOTIFY("还款成功通知"),
	ADJUST_SUCCESS_NOTIFY("调账成功通知"),
	CREDIT_CREATE_USER("授信创建用户通知"),
	@Deprecated /** 2016-7废弃使用 */CREDIT_UPDATE_USER_ROLE("额度激活更新用户角色通知"),
	REFUND2CUSTOMER_SUCCESS_NOTIFY("退款给客户成功通知"),
	REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY("还款成功短信&邮件通知"),
	TZ_ACCOUNT_REPAYMENT_SUCCESS_SMS_MAIL_NOTIFY("账户还款成功短信&邮件通知"),
	SYNC_VJ_DEDUCT_STATE("同步VJ扣款状态"),
	VJ_GET_CONTRACT("获取VJ合同"),
	ACCOUT_BALANCE_GET("余额获取"),

	//韵达项目
	YD_AUDIT_RESULT("审核结果通知"),
	YD_CREDIT_LIMIT_EFFECT("额度生效通知"),
	YD_CREDIT_TRADE_RESULT("放款信息通知"),
	YD_CREDIT_LIMIT_RECOVERY("额度恢复通知"),
	YD_REPAYMENT_SUCCESS_NOTIFY("还款成功通知"),
	YD_ADJUST_SUCCESS_NOTIFY("调账成功通知"),
	YD_CREDIT_CREATE_USER("授信创建用户通知"),
	YD_CREDIT_UPDATE_USER_ROLE("额度激活更新用户角色通知"),
	YD_STATION_PULL("拉取网点数据"),
	YD_STATION_SYN("同步网点数据");
	
	private String desc;

	private RetryTaskType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
