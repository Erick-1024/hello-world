package com.cana.vbam.common.customer.enums;

public enum BusinessMaterial {
	
	BUSINESS_LICENCE("营业执照", true),
	ORGANIZATION_CREDENTIAL("组织机构代码证", true),
	TAX_REGISTRATION_CERTIFICATE_CREDENTIAL("税务登记证", true),
	ORGANIZATION_CREDIT_CREDENTIAL("机构信用代码证", true),
	CBK_LICENCE("开户许可证", true),
	COMPANY_POLICY("公司章程", true),
	VERIFICATION_REPORT("验资报告或实收资本会计凭证及原始附件", false),
	LEGAL_PERSON_IDENTITY_CARD_FRONT_AND_BACK("法定代表身份证正反面", true),
	COMPANY_INTRODUCE("公司简介", true),
	MAIN_MANGER_RESUME("主要管理者简历", false),
	CONCESSION_TRADE_LICENSE("特许行业经营许可证", false),
	QUALIFICATION_CERTIFICATE("资质认证", false),
	OWNERSHIP_REGISTRATION_CERTIFICATE("所有权登记证", false),
	OTHER("其他", false),
	;
	

	private String desc;
	
	private boolean isMust;
	
	private BusinessMaterial(String desc, boolean isMust) {
		this.desc = desc;
		this.isMust = isMust;
	}
	
	public String desc() {
		return desc;
	}
	
	public boolean isMust() {
		return isMust;
	}
}
