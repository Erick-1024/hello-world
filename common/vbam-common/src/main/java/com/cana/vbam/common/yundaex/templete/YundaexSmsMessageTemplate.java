package com.cana.vbam.common.yundaex.templete;

public class YundaexSmsMessageTemplate {

	public static final String  matchcharacters = "{}";

	public static final String  matchcharactersReg = "\\{\\}";
	
	public static final String yd_repayment_remind_before_repaymentdate_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，"
			+ "您的信韵融" + matchcharacters + "账单已出，应还金额为" + matchcharacters + "元，请在" + matchcharacters + "17点前进行还款，请及时还款，"
			+ "以免影响您的信用记录。注：如已还款，无需理会。[信韵融]";

	public static final String yd_repayment_remind_on_repaymentdate_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，"
			+ "您于" + matchcharacters + "应还金额为" + matchcharacters + "元，请及时还款，以免发生逾期，影响您的信用记录。注：如已还款，无需理会。[信韵融]";

	public static final String yd_overdue_repayment_remind_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，您的"+matchcharacters+"的信韵融融资申请欠"
			 + matchcharacters + "元，即日起逾期率为" + matchcharacters + "/日，请及时还款，以免影响您的信用记录或被追究其他法律责任。注：如已还款，无需理会。[信韵融]";

	public static final String yd_repayment_success_sms_message_content_template = "尊敬的"+matchcharacters+"用户：您好，您"+matchcharacters+"流水号已成功还款，还款金额为"
			+ matchcharacters + "元。[信韵融]";
}
