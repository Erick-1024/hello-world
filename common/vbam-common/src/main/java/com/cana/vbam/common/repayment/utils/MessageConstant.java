package com.cana.vbam.common.repayment.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hu
 *
 */
public class MessageConstant {

	public final static String matchcharacters = "{}";
	
	public final static String matchCharactersRegex = "\\{\\}";
	
	public final static String AdjustMessage = "保理商"+matchcharacters+"已调整放款编号"+matchcharacters+"项下的还款计划，请及时查看最新还款信息。"; 
	
	public final static String DEDUCT_MESSAGE = "您" + matchcharacters + "监管账户已成功扣款" + matchcharacters + "元，用于归还放款编号为" + matchcharacters + "项下的还款"; 
	
	public final static String REFUND_REPAYMENT_MESSAGE = matchcharacters + "发起的" + matchcharacters + "元退款至" + matchcharacters + "监管账户，用于归还放款编号为" + matchcharacters + "项下的还款"; 
	
	public final static String ACTIVE_REPAYMENT_MESSAGE = "融资客户" + matchcharacters + "，已完成主动还款，请及时更新还款计划，"; 

	public final static String LoanInfoDetailURL = "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=";
	
	public final static String LoanInfoAdjustURL = "/repayment/active/toAdjustment?loanInfoId=";
	
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
}
