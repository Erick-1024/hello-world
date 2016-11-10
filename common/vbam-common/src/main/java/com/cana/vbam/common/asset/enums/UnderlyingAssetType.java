package com.cana.vbam.common.asset.enums;

import org.apache.commons.lang3.StringUtils;

public enum UnderlyingAssetType {

	COMPANY_LOAN("企业贷款"),
	PROFESSION_LOAN("专业贷款"),
	CAR_MORTGAGE_LOAN("汽车抵押贷款"),
	HOUSE_MORTGAGE_LOAN("住房抵押贷款"),
	NONPERFORMING_ASSET_RESTRUCTURING("不良资产重组"),
	SMALL_LOAN("小额贷款"),
	FINANCIAL_LEASING("金融租赁"),
	FINANCE_LEASING("融资租赁"),
	USUFRUCT_CHARGE("收费收益权"),
	PERSONAL_CONSUMPTION_LOAN("个人消费贷款"),
	HOUSING_FUND("住房公积金"),
	INVOICE("应收账款"),
	ENTRUST_LOAN("委托贷款"),
	REITS("REITs"),
	FACTOR_FINANCE("保理融资"),
	TRUST_USUFRUCT("信托受益权"),
	BT_PROJECT_REPURCHASE("BT项目回购"),
	TWO_FINANCE_DEBTS("两融债权"),
	STOCK_PLEDGE_REPURCHASE("股票质押回购"),
	POLICY_PLEDGE_REPURCHASE("保单质押贷款"),
	EQUIPMENT_MORTGAGE_LOAN("设备按揭贷款"),
	BILL_USUFRUCT("票据收益权"),
	COMMERCIAL_REAL_ESTATE_MORTGAGE_LOAN("商业房地产抵押贷款"),
	OTHER("其他");
	
    private String desc;
	
	private UnderlyingAssetType(String desc){
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
	public static UnderlyingAssetType getEnum(String enumDesc){
		if(StringUtils.isBlank(enumDesc))
			return null;
		UnderlyingAssetType[] types = UnderlyingAssetType.values();
		for(int i =0 ;i<types.length;i++){
			if(types[i].desc().equals(enumDesc))
				return types[i];
		}
		//没有匹配的desc
		return null;
	}
}
