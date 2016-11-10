package com.cana.vbam.common.asset.enums;


/**
 * @author jiangzhou.Ren
 * @time 2016年6月24日上午9:28:15
 * 项目付款方式枚举类
 */

public enum ProjectRepaymentMethodsEnum {
	
	ORDER("订单款回款还款"),
	MATURITY("到期一次性还本及支付收益"),
	MONTHLY("按月支付收益到期还本"),
	EQUALALL("等额本息"),
	EQUALPRINCIPAL("等额本金");
	
	private String desc;

	private ProjectRepaymentMethodsEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
}

