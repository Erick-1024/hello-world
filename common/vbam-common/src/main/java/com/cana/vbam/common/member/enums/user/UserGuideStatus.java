package com.cana.vbam.common.member.enums.user;

/**
 * 客户引导状态
 * @author XuMeng
 * 
 * 注册的保理商客户首次登陆时，需要依次完成 APPLIED_ACCOUNT ==> COMFIRMED_RULE;
 * 绑定真旅客户的融资商客户，在之后的首次登陆时需要完成 COMFIRMED_CONTRACT;
 *
 */
public enum UserGuideStatus {
	APPLIED_ACCOUNT,   //保理商开户通过
	COMFIRMED_RULE,    //保理商确认规则通过

	NEED_GENERATE_CONTRACT,    //绑定真旅客户的融资商需要走引导流程生成合同
	COMFIRMED_CONTRACT,     //绑定真旅客户的融资商确认合同完成
	
	NEED_GENERATE_CONTRACT_YUNDAEX,   //绑定韵达的融资商需要走引导流程生成合同
	COMFIRMED_CONTRACT_YUNDAEX   //绑定韵达的融资商确认合同完成
	;

}
