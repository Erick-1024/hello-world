package com.cana.yundaex.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hu
 *
 */
public class MessageContentUtils {

	public final static String matchcharacters = "{}";
	
	public final static String matchCharactersRegex = "\\{\\}";
	
	public final static String smsPrefix = "【CANA金融】";
	
	//邮件联系电话
	public final static String mailContactPhone = "021-53866655-8051";
	
	//申请融资
	public final static String mailApplySubject = "信韵融-申请融资";
	public final static String smsApplyFinancing = "尊敬的"+matchcharacters+"用户：您好，您的信韵融申请已受理，还需提交其他补充资料，资料详情已发送至您的邮箱，请查实。[信韵融]";
	public final static String mailApplyFinancing = "【CANA金融】尊敬的"+matchcharacters+"用户：<br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 您好，您的信韵融申请已受理，业务申请您需要提交如下资料：<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1、《业务申请表》见附件；<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2、三证（营业执照、组织机构代码证、税务登记证）或信用代码证（三证合一）电子版；<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3、法人身份证正反面电子版；<br> 申请资料提交后，我们将在1-3个工作日内将审核结果发送至您的指定邮箱<br>资料发送邮箱："+matchcharacters+" <br>联系电话："+matchcharacters+" <br> 联系QQ："+matchcharacters;
	
	public final static String mailAuditSubject = "信韵融-审核结果";
	//审核通过
	public final static String smsAuditThrough = "尊敬的"+matchcharacters+"用户：您本次信韵融申请已通过审核，请登录CANA平台激活，详情见邮件。[信韵融]";
	public final static String mailAuditThrough = "【CANA金融】尊敬的"+matchcharacters+"用户：<br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 您本次信韵融申请已通过审核，相关信息如下：可申请用款金额："+matchcharacters+"元<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 请点击链接以下链接激活 激活链接"+matchcharacters+" <br><br> 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。";
	
	//审核不通过
	public final static String smsAuditReject ="尊敬的"+matchcharacters+"用户：您的资质暂不符合申请信韵融要求，如您符合条件后，我们将及时通知您！谢谢！[信韵融]";
	public final static String mailAuditReject = "【CANA金融】尊敬的"+matchcharacters+"用户：<br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 您的资质暂不符合申请要求，如您符合条件后，我们将及时通知您！<br><br> 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。";
	
	//个人资料提交
	public final static String mailcommitSubject = "信韵融-资料提交";
	public final static String mailcommitUrl = matchcharacters+"yundaex/personal/facade/put?id="+matchcharacters+"&code="+matchcharacters;
	public final static String smsAccountInconformity = "尊敬的"+matchcharacters+"用户：经过审核您的企业名称与用款账户名称不一致，需要提供相关资料，详情见邮箱，感谢您的配合。[信韵融]";
	public final static String mailAccountInconformity = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，根据您提供的材料我们看到企业名称与您提供的用款账户名称不一致，所以现在需要您提供一份开户人手拿身份证的照片（<font color='red'>分别拿身份证正反面，并能够清晰看到身份证信息</font>）<br>&nbsp;&nbsp;&nbsp;&nbsp;资料上传链接："+matchcharacters+" 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。";
	
	public final static String smsControllerInconformity = "尊敬的"+matchcharacters+"用户：经过审核您的法人代表与企业实际控制人不一致，需要提供相关资料，详情见邮箱，感谢您的配合。[信韵融]";
	public final static String mailControllerInconformity = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，根据您提供的材料我们看到企业法人代表与企业实际控制人不一致，所以现在需要您提供一份企业实际控制人手拿身份证的照片（<font color='red'>分别拿身份证正反面，并能够清晰看到身份证信息</font>）<br>&nbsp;&nbsp;&nbsp;&nbsp;资料上传链接："+matchcharacters+" 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。";
	
	public final static String smsAccountAndControllerInconformity = "尊敬的"+matchcharacters+"用户：经过审核您的法人代表与企业实际控制人不一致，企业名称与用款账户名称不一致，需要提供相关资料，详情见邮箱，感谢您的配合。[信韵融]";
	public final static String mailAccountAndControllerInconformity = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，根据您提供的材料我们看到企业法人代表与企业实际控制人以及企业名称与用款账户名不一致，所以现在需要您提供一份企业实际控制人手拿身份证的照片（<font color='red'>分别拿身份证正反面，并能够清晰看到身份证信息</font>）<br>&nbsp;&nbsp;&nbsp;&nbsp;资料上传链接："+matchcharacters+" 如有疑问，请与CANA金融联系，电话："+matchcharacters+"。";
	
	//合同签订
	public final static String mailSignContractSubject = "信韵融-签订合同";
	public final static String mailSignContractUrl = matchcharacters+"yundaex/personal/facade/informationConfirm?id="+matchcharacters+"&code="+matchcharacters;
	public final static String smsFinanceContractSign = "尊敬的"+matchcharacters+"用户：您好，您已成功开立银行账户，并签订融资合同，感谢您的配合。[信韵融]";
	public final static String mailFinanceContractSign = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，您已成功开立银行账号，并签订融资合同。<br>&nbsp;&nbsp;&nbsp;&nbsp;如有异常，请尽快与CANA金融联系，电话："+matchcharacters+"。请知悉！";
	
	public final static String smsDelegateContractSign = "尊敬的"+matchcharacters+"用户：您好，根据您提交的资料，现与您签订一份授权委托书，详情请见邮件，请审核无误后进行签约，感谢您的配合。[信韵融]";
	public final static String mailDelegateContractSign = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，根据您提供的资料，现与您签订一份授权委托书，点击以下链接进行签约（<font color=red>请在24个小时之内进行签约</font>）<br>&nbsp;&nbsp;&nbsp;&nbsp;如有异常，请尽快与CANA金融联系，电话："+matchcharacters+"。请知悉！<br>&nbsp;&nbsp;&nbsp;&nbsp;合同链接："+matchcharacters;
	
	public final static String smsGuaranteeContractSign = "尊敬的"+matchcharacters+"用户：您好，根据您提交的资料，现与您签订一份个人连带担保，详情请见邮件，请审核无误后进行签约，感谢您的配合。[信韵融]";
	public final static String mailGuaranteeContractSign = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，根据您提供的资料，现与您签订一份个人连带担保，点击以下链接进行签约（<font color=red>请在24个小时之内进行签约</font>）<br>&nbsp;&nbsp;&nbsp;&nbsp;如有异常，请尽快与CANA金融联系，电话："+matchcharacters+"。请知悉！<br>&nbsp;&nbsp;&nbsp;&nbsp;合同链接："+matchcharacters;
	
	public final static String smsDelegAndGuaraContractsSign = "尊敬的"+matchcharacters+"用户：您好，根据您提交的资料，现与您签订授权委托书和个人连带担保，详情请见邮件，请审核无误后进行签约，感谢您的配合。[信韵融]";
	public final static String mailDelegAndGuaraContractsSign = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，根据您提供的资料，现与您签订授权委托书和个人连带担保，点击以下链接进行签约（<font color=red>请在24个小时之内进行签约</font>）<br>&nbsp;&nbsp;&nbsp;&nbsp;如有异常，请尽快与CANA金融联系，电话："+matchcharacters+"。请知悉！<br>&nbsp;&nbsp;&nbsp;&nbsp;合同链接："+matchcharacters;
	
	//额度激活
	public final static String mailCreditActivatedSubject = "信韵融-额度生效";
	public final static String smsCreditActivated = "尊敬的"+matchcharacters+"用户：您好，即日起额度正式生效，可正常申请用款，感谢您的使用。[信韵融]"; 
	public final static String mailCreditActivated = "【CANA金融】尊敬的"+matchcharacters+"用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您好，您的信韵融已成效，可正常使用。申请用款流程：您需要点击用款申请，填写您的用款金额并进行提交。<br>&nbsp;&nbsp;&nbsp;&nbsp;如有疑问，请与CANA金融联系，电话："+matchcharacters+"。请知悉！ ";
	
	// 用款申请
	public final static String mailLoanApplySubject = "信韵融-用款申请";
	public final static String smsLoanApplyInfo = "尊敬的"+ matchcharacters +"用户：您好，您的用款申请已通过，请查询您的银行账户，谢谢!";
	public final static String mailLoanApplyInfo = "【CANA金融】尊敬的" + matchcharacters
			+ "用户：<br>&nbsp;&nbsp;&nbsp;&nbsp;您的编号" + matchcharacters
			+ "的用款申请已通过，请查询您的银行账户：<br>&nbsp;&nbsp;&nbsp;&nbsp;银行帐户名称：" + matchcharacters
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;银行帐号:" + matchcharacters
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;明细：<br>"
			+ "<table><colgroup><col width='80'><col width='100'><col width='100'><col width='100'></colgroup><tbody><tr><th>期限</th><th>应还本金</th><th>服务费</th><th>到期日</th></tr>" +matchcharacters+"</tbody></table>"
			+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;如有疑问，请与CANA金融联系，电话：" + matchcharacters + "。请知悉！CANA平台链接：" + matchcharacters;
	
	/**
	 * 生成完整消息内容
	 * @param template
	 * @param DataItems
	 * @return
	 */
	public static String generateContent(String template, String... DataItems){
		
		for(String item : DataItems){
			if(StringUtils.isNotBlank(item)){
				template = template.replaceFirst(matchCharactersRegex, item);
			}
		}
		return template;
	}
	
	public static String generateHtmlUrl(String url){
		return "<a href='"+url+"' target='_blank'>"+url+"</a>";
	}
}
