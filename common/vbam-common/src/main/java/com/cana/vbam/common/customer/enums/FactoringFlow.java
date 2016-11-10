package com.cana.vbam.common.customer.enums;

public enum FactoringFlow {

	// 评审材料
	CUSTOMER_APPLICATION("客户申请书", false),
	CREDIT_INVESTIGATION_RECORDS("授信调查记录", false),
	DUE_DILIGENCE_SAMPLE_REPORT("尽调报告", false),
	CORPORATE_CREDIT_RATING_FORM("企业信用评定表", false),
	CREDIT_TRIAL_RECORD_FORM("信审记录表", false),
	RATINGS_PRICING_REFERENCE_FROM("评级定价参考表", false),
	PRE_TRIAL_COMMENTS_FORM("预审会意见表", false),
	RISK_MANAGEMENT_DEPARTMENT_RESOLUTION_COMMENTS_FORM("风控委决议意见表", false),
	REVIEW_MATERIAL_OTHER("其他", false),
	// 放款前资料
	FACTORING_BUSINESS_MAIN_CONTRACT("保理业务主合同", true),
	FACTORING_BUSINESS_GUARANTEE_CONTRACT("保理业务担保合同", false),
	CREDIT_APPROVAL_NOTICE_AND_RECEIPT("额度核准通知书及回执", false),
	APPLICATION_FOR_TRANSFER_OF_ACCOUNTS_RECEIVABLE_AND_INVENTORY_DETAILS("应收账款转让申请书及清单明细", false),
	ASSIGNMENT_OF_ACCOUNTS_RECEIVABLE_APPLICATION_AND_RECEIPT("应收账款转让通知及回执", true),
	ACCOUNT_CHANGE_NOTICE_AND_RECEIPT("账号变更通知书及回执", false),
	PEOPLE_BANK_ACCOUNT_RECEIVABLE_TRANSFER_REGISTRATION("人民银行应收账款转让登记", true),
	LOAN_APPLICATION("放款申请书", false),
	BUSINESS_SIGN_STATEMENTS("业务签报表", false),
	MATERIAL_BEFORE_LOAN_OTHER("其他", false),
	// 放款后资料
	LENDERS_ARRIVAL_CREDENTIALS("放款到账凭证", false),
	BORROWER_IOU("借款借据", false),
	MATERIAL_AFTER_LOAN_OTHER("其他", false),
	;
	private String desc;
	
	private boolean isMust;
	
	private FactoringFlow(String desc, boolean isMust) {
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
