package com.cana.vbam.common.early.warning.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 韵达事件种类
 * @author sugar
 *
 */
public enum YundaexEarlywarningEventSubCategory {
	
	DEPARTMENTS_PUNISH(YundaexEarlywarningEventCategory.DEPARTMENTS_PUNISH,"收到税务、工商或质检等部门处罚"),
	MAIN_BUSINESS_FREQUENT_CHANGE(YundaexEarlywarningEventCategory.BUSINESS_NATURE_CHANGE,"主营业务频繁变化"),
	OWNERSHIP_STRUCTURE_CHANGE(YundaexEarlywarningEventCategory.OWNERSHIP_STRUCTURE_CHANGE,"客户主要股东向其他人转让或拟转让股权"),
	POLICY_OR_LAWS_CHANGE(YundaexEarlywarningEventCategory.NEGATIVE_NEWS,"政府对产业政策、税收政策、贸易政策等进行了调整，或相关的法律规定发生变化"),
	MISCONDUCT_BY_MEDIA(YundaexEarlywarningEventCategory.NEGATIVE_NEWS,"被公众媒体披露的其他不端行为"),
	SOCIAL_PUBLIC_REFLECT(YundaexEarlywarningEventCategory.NEGATIVE_NEWS,"社会公众对客户法定代表人或经营者个人品质、行为反映不良"),
	PERFORM_LOAN_CASE(YundaexEarlywarningEventCategory.LITIGATION_DISPUTE,"借款人被要求履行保证、保险责任及诉诸法律的借款案由"),
	OTHER_CREDITORS_SUED(YundaexEarlywarningEventCategory.LITIGATION_DISPUTE,"拖欠其他金融机构贷款本息，被其他债权人起诉"),
	ILLEGAL_MORALITY_BEHAVIOR(YundaexEarlywarningEventCategory.LITIGATION_DISPUTE,"有赌博、涉毒、嫖娼等违法或违反社会公德的行为"),
	ILLEGAL_BUSINESS(YundaexEarlywarningEventCategory.LITIGATION_DISPUTE,"存在违法经营问题"),
	LEGAL_SANCTION(YundaexEarlywarningEventCategory.LITIGATION_DISPUTE,"借款人有违法、违纪等不良行为，受到法律、行政、经济制裁或处罚"),
	DEBT_EXTRAORDINARY_INCREASE(YundaexEarlywarningEventCategory.SHORT_TERM_LOAN,"贷款需求增加，短期债务超常增加"),
	BORROW_NEW_REPAY_OLD(YundaexEarlywarningEventCategory.SHORT_TERM_LOAN,"客户改变主要贷款金融机构，向许多金融机构借款或不断在这些金融机构之间借新还旧"),
	REPAYMENT_NOT_NORMAL(YundaexEarlywarningEventCategory.SHORT_TERM_LOAN,"客户还款记录不正常，或被人民银行或其他金融机构宣布为信用不良客户"),
	REFUSE_PROVIDE_DOCUMENT(YundaexEarlywarningEventCategory.CUSTOMER_ATTITUDE,"客户拒绝提供与信用审核有关的文件"),
	CONCEAL_TRUTH(YundaexEarlywarningEventCategory.CUSTOMER_ATTITUDE,"客户隐瞒重要信息或提供虚假信息，如隐瞒资产、债务或抵(质)押品真实情况"),
	PERSON_CANNOT_CONTACT(YundaexEarlywarningEventCategory.CUSTOMER_ATTITUDE,"客户关键人员如经营决策人员、主要执行人员或技术人员失踪或无法联系"),
	OTHER(YundaexEarlywarningEventCategory.OTHER,"其它"),
	RECANDSEND_GROWTHRATE(YundaexEarlywarningEventCategory.SYSTEM, "揽派件增长率"),
	BAILBALANCE_DAY_REQUIREMENTS(YundaexEarlywarningEventCategory.SYSTEM, "保证金余额/日资金需求"),
	YUNDAEXGRADE(YundaexEarlywarningEventCategory.SYSTEM, "韵达评级"),
	BAILBALANCE(YundaexEarlywarningEventCategory.SYSTEM, "保证金余额"),
	NET_CASHFLOW_GROWTHRATE(YundaexEarlywarningEventCategory.SYSTEM, "净现金流增长量"),
	NET_CASHFLOW(YundaexEarlywarningEventCategory.SYSTEM, "净现金流"),
	CREDIT_LIMIT_GROWTH(YundaexEarlywarningEventCategory.SYSTEM, "最大授信金额增幅"),
	OVERDUES(YundaexEarlywarningEventCategory.SYSTEM, "逾期次数（CANA）");
	
	private YundaexEarlywarningEventCategory parentCategory;//对应的预警种类（父类）
	private String desc;
	
	private YundaexEarlywarningEventSubCategory(YundaexEarlywarningEventCategory parentCategory,String desc){
		this.parentCategory = parentCategory;
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public YundaexEarlywarningEventCategory parentCategory(){
		return parentCategory;
	}
	
	public List<YundaexEarlywarningEventSubCategory> getSubCategoryByParent(YundaexEarlywarningEventCategory parentCategory){
		if(parentCategory==null)
			return new ArrayList<>();
		List<YundaexEarlywarningEventSubCategory> subCategorys = new ArrayList<>();
		for(YundaexEarlywarningEventSubCategory subCategory : YundaexEarlywarningEventSubCategory.values()){
			if(subCategory.parentCategory().equals(parentCategory))
				subCategorys.add(subCategory);
		}
		return subCategorys;
	}
}
