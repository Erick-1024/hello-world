package com.cana.vbam.common.vj.template;

public class VJSmsMessageTemplate {
	
	public static final String  matchcharacters = "{}";

	public static final String  matchcharactersReg = "\\{\\}";

	public static final String  prefix = "【工资钱包】";
	
	//还款日前1-7天
	public static final String vj_before_repayment_date_remind_sms_message_content_template = "尊敬的"+matchcharacters+"，您在工资钱包的借款将于"
			+matchcharacters+"天后到期，本期需偿还"+matchcharacters+"元。请在还款当日登录APP及时还款，以免影响您的征信记录。客服热线021-53866655。[CANA金融]";
	//还款日当天
	public static final String vj_on_repayment_date_remind_sms_message_content_template = "尊敬的"+matchcharacters+"，您在“锦囊”的借款于今日到期，本期需偿还"
			+matchcharacters+"元。请于今日23:00前，及时充值至余额账户，进行还款操作。避免逾期影响您的征信记录。客服热线021-53866655。[CANA金融]";
	//逾期前1-3天
	public static final String vj_less_overdue_repayment_remind_sms_message_content_template = "尊敬的"+matchcharacters+"，您在“锦囊”的借款共计"
			+matchcharacters+"元应在"+matchcharacters+"还款，现已逾期" +matchcharacters+"天，逾期总金额为" +matchcharacters+"元，已产生罚息，请立即还款，以免影响您的征信记录。"
					+ "罚息详请见工资钱包“帮助中心”。客服热线021-53866655。[CANA金融]";
	//逾期3天以上
	public static final String vj_more_overdue_repayment_remind_sms_message_content_template = "尊敬的"+matchcharacters+"，您在“锦囊”借款现逾期共计"
			+matchcharacters+"元，逾期"+matchcharacters+"天。请尽快还款，否则我司将有权上传您的逾期信息至人行征信系统及征信黑名单。罚息详请见工资钱包“帮助中心”。客服热线021-53866655。[CANA金融]";
	
}
