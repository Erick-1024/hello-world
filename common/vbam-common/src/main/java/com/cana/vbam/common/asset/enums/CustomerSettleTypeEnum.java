package com.cana.vbam.common.asset.enums;

/**
 * 客户采购结算方式
 * @author jiangzhou.Ren
 * @time 2016年7月29日下午4:55:57
 */
public enum CustomerSettleTypeEnum {
	COD("货到付款"),//cash on delivery 
	IP("现款现货"),//instant payment
	PAD("先款后货");//payment after delivery
	
	private String desc;
	
	CustomerSettleTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
