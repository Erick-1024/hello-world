package com.cana.vbam.common.yundaex.templete;

public class YundaexMailMessageTemplate {
	
	public static final String  matchcharacters = "{}";

	public static final String  matchcharactersReg = "\\{\\}";
	
	public static final String cana_phone = "021-53866655-8051";
	
	public static final String yd_repayment_remind_mail_message_subject_template = "信韵融-还款提醒";

	public static final String yd_overdue_repayment_remind_mail_message_subject_template = "信韵融-逾期提醒";

	public static final String yd_repayment_success_mail_message_subject_template = "信韵融-还款结果";
	
	public static final String yd_repayment_remind_before_repaymentdate_mail_message_content_template = "【CANA金融】尊敬的"+matchcharacters+"用户："
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，您的信韵融" + matchcharacters + "账单已出，应还金额为" + matchcharacters + "元，"
			+ "请在" + matchcharacters + "17点前进行还款，请及时还款，以免出现逾期，影响您的信用记录。注：如已还款，无需理会（<font color='red'>银行对大额"
			+ "实时支付业务有操作时间限制，规定时间为工作日9：00-17:00，请合理安排时间，以免发生逾期</font>）<br>&nbsp;&nbsp;&nbsp;"
			+ "&nbsp;详情请见CANA平台连接："+matchcharacters+" 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。请知悉！";

	public static final String yd_repayment_remind_on_repaymentdate_mail_message_content_template = "【CANA金融】尊敬的"+matchcharacters+"用户："
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，您于" + matchcharacters + "应还金额为" + matchcharacters + "元，"
			+ "请在今日17点前进行还款，请及时还款，以免出现逾期，影响您的信用记录。注：如已还款，无需理会（<font color='red'>银行对大额"
			+ "实时支付业务有操作时间限制，规定时间为工作日9：00-17:00，请合理安排时间，以免发生逾期</font>）<br>&nbsp;&nbsp;&nbsp;"
			+ "&nbsp;详情请见CANA平台连接："+matchcharacters+" 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。请知悉！";

	public static final String yd_overdue_repayment_remind_mail_message_content_template = "【CANA金融】尊敬的"+matchcharacters+"用户："
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，您" + matchcharacters + "编号的信韵融融资申请截止"+matchcharacters+"已归还金额"+matchcharacters+"元，"
			+ "尚欠" + matchcharacters + "元，未还清本期账务，即日起逾期率为"+matchcharacters+"/日，请及时还款，以免影响您的信用记录或被追究其他法律责任。"
			+ "注：如已还款，无需理会。(<font color='red'>银行对大额实时支付业务有操作时间限制，规定时间为工作日9：00-17:00，请合理安排时间，请合理安排时间</font>)"
			+ "<br/>"+ matchcharacters + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;还款帐号：" + matchcharacters + "<br>&nbsp;&nbsp;&nbsp;&nbsp;详情请见CANA平台连接："+matchcharacters+" 如"
			+ "有疑问，请与CANA金融联系，电话："+matchcharacters+"。请知悉！";

	public static final String yd_repayment_success_mail_message_content_template = "【CANA金融】尊敬的"+matchcharacters+"用户："
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，您" + matchcharacters + "编号已成功还款，还款金额"+matchcharacters+"元"
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;详情请见CANA平台连接："+matchcharacters+" 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。请知悉！"; 
	
	public static final String law_policy_v1 = "&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>若贵司未能按照上述要求支付相关款项，则本公司有权按照业务合同的约定，对贵司采取如下措施："
			+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;1、将贵司的违约情况通报中国人民银行征信系统、依法设立的信用信息数据库或其他社会征信机构，使贵司进入征信系统黑名单。<br/>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "2、通知韵达降级、暂停或终止贵司的特许经营（加盟）资质。<br/>&nbsp;&nbsp;&nbsp;&nbsp;3、依法采取其他违约救济措施。<br/>&nbsp;&nbsp;&nbsp;&nbsp;因此，为避免因不"
			+ "良征信记录影响贵司今后在金融机构的贷款申请以及与韵达的业务合作、加盟代理资质等，请贵司尽快按照本函内容履行相关义务。<br/>&nbsp;&nbsp;&nbsp;&nbsp;若贵司在收到本函前已"
			+ "清偿上述全部欠款，则无须理会本函。</font>";
	
	public static final String law_policy_v2 = "&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>若贵司未能按照上述要求支付相关款项，则本公司有权按照业务合同的约定，对贵司采取如下措施：<br/>&nbsp;"
			+ "&nbsp;&nbsp;&nbsp;1、停止办理贵司在本公司的全部业务，宣布业务合同项下未到期的款项立即全部到期应付。<br/>&nbsp;&nbsp;&nbsp;&nbsp;2、对贵司的财产采取留置、冻结、"
			+ "扣押、查封等限制措施。<br/>&nbsp;&nbsp;&nbsp;&nbsp;3、将贵司及相关担保人诉至法院，通过法律程序确认由贵司清偿全部应付款项，并通过强制执行程序执行贵司及相关担保人的财产。"
			+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;因此，为避免不必要的讼累，同时避免因诉讼而增加贵司的费用赔偿以及信誉损失，请贵司立即按照本函内容履行相关义务。&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "若贵司在收到本函前已清偿上述全部欠款，则无须理会本函。</font>";
}