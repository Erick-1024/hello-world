package com.cana.vbam.common.early.warning.consts;

import java.util.Arrays;
import java.util.List;

import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventSubCategory;

public class EarlyWarningConsts {

	// 韵达红色预警对应的事件种类
	public static final List<YundaexEarlywarningEventSubCategory> redSubCategory = Arrays.asList(
			YundaexEarlywarningEventSubCategory.ILLEGAL_MORALITY_BEHAVIOR,YundaexEarlywarningEventSubCategory.ILLEGAL_BUSINESS,
			YundaexEarlywarningEventSubCategory.LEGAL_SANCTION,YundaexEarlywarningEventSubCategory.REPAYMENT_NOT_NORMAL
			);
	
	// 韵达黄色预警对应的事件种类
	public static final List<YundaexEarlywarningEventSubCategory> yellowSubCategory = Arrays.asList(
			YundaexEarlywarningEventSubCategory.DEPARTMENTS_PUNISH,YundaexEarlywarningEventSubCategory.MAIN_BUSINESS_FREQUENT_CHANGE,
			YundaexEarlywarningEventSubCategory.OWNERSHIP_STRUCTURE_CHANGE,YundaexEarlywarningEventSubCategory.POLICY_OR_LAWS_CHANGE,
			YundaexEarlywarningEventSubCategory.MISCONDUCT_BY_MEDIA,YundaexEarlywarningEventSubCategory.SOCIAL_PUBLIC_REFLECT,
			YundaexEarlywarningEventSubCategory.PERFORM_LOAN_CASE,YundaexEarlywarningEventSubCategory.OTHER_CREDITORS_SUED,
			YundaexEarlywarningEventSubCategory.DEBT_EXTRAORDINARY_INCREASE,YundaexEarlywarningEventSubCategory.BORROW_NEW_REPAY_OLD,
			YundaexEarlywarningEventSubCategory.REFUSE_PROVIDE_DOCUMENT,YundaexEarlywarningEventSubCategory.CONCEAL_TRUTH,
			YundaexEarlywarningEventSubCategory.PERSON_CANNOT_CONTACT
			);
	
	/**
	 * 事件种类是否绑定等级
	 * @param subCategory
	 * @return
	 */
	public static boolean isSubCategoryBindLevel(YundaexEarlywarningEventSubCategory subCategory){
		if(EarlyWarningConsts.redSubCategory.contains(subCategory) || EarlyWarningConsts.yellowSubCategory.contains(subCategory))
			return true;
		return false;
	}
	
	public static EarlywarningLevel getLevelBySubCategory(YundaexEarlywarningEventSubCategory yundaexEarlywarningEventSubCategory){
		if(EarlyWarningConsts.redSubCategory.contains(yundaexEarlywarningEventSubCategory))
			return EarlywarningLevel.red;
		if(EarlyWarningConsts.yellowSubCategory.contains(yundaexEarlywarningEventSubCategory))
			return EarlywarningLevel.yellow;
		return null;
	}

}
