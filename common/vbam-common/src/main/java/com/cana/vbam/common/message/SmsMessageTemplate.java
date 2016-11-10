package com.cana.vbam.common.message;

public class SmsMessageTemplate {
	
	public static final String  matchcharacters = "{}";
	
	
	public static final String tz_repayment_remind_before_repaymentdate_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，"
			+ "您的信旅宝" + matchcharacters + "账单已出，应还金额为" + matchcharacters + "元，请在" + matchcharacters + "17点前进行还款，请及时还款，"
			+ "以免影响您的信用记录。注：如已还款，无需理会。[信旅宝]";

	public static final String tz_repayment_remind_on_repaymentdate_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，"
			+ "您于" + matchcharacters + "应还金额为" + matchcharacters + "元，请及时还款，以免发生逾期，影响您的信用记录。注：如已还款，无需理会。[信旅宝]";

	public static final String tz_overdue_repayment_remind_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，"
			+ "您融资编号"+matchcharacters+"的信旅宝融资申请欠"+matchcharacters+"元，即日起逾期率为"+matchcharacters+"/日，请及时还款，以免影响您的信用"
			+ "记录或被追究法律责任。注：如已还款，无需理会[信旅宝]";

	public static final String tz_repayment_success_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，您已经于"+matchcharacters+"月"+matchcharacters+"日归还信旅宝欠款"+matchcharacters+"元。[信旅宝]";

	public static final String tz_refund_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，您的"+matchcharacters+"账户存入"+matchcharacters+"元——真旅网退款，请及时查询账户余额。[信旅宝]";
	
	//真旅代客户还款成功的短信模板
	public static final String tz_account_repayment_success_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，您的天地行账户已经于"+matchcharacters+"月"+matchcharacters+"日归还信旅宝欠款"+matchcharacters+"元。[信旅宝]";
}
