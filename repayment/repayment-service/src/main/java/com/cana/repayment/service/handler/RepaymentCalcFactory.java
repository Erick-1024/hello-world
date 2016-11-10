package com.cana.repayment.service.handler;

import org.apache.commons.lang3.EnumUtils;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.utils.Constants;

public class RepaymentCalcFactory {

	/**
	 * 获得一个可能包含手工录入放款的核心算法计算实例
	 */
	public static IRepaymentCalc getRepaymentCalc(RepaymentLoanInfoBO loanInfoBO) throws Exception {

		if (loanInfoBO.containNonAutoRepaymentPlans())
			return new NonAutoRepaymentCalc();

		if (!EnumUtils.isValidEnum(RepaymentType.class, loanInfoBO.getRepaymentMethod()))
			throw new Exception("放款[id=" + loanInfoBO.getId() + "]中的还本付息方式不合法");

		return getRepaymentCalc(RepaymentType.valueOf(loanInfoBO.getRepaymentMethod()), loanInfoBO.getBusinessProductId());
	}

	/**
	 * @deprecated 限定真旅项目和VJ项目可以使用此方法，因这两个项目中只存在订单回款方式
	 */
	public static IRepaymentCalc getRepaymentCalc(String productId) throws Exception {
		return getRepaymentCalc(RepaymentType.ORDER, productId);
	}

	/**
	 * 获得自动生成放款与还款的核心算法计算实例，不支持手工录入的放款与还款
	 */
	public static IRepaymentCalc getRepaymentCalc(RepaymentType repaymentType, String productId) throws Exception {

		if (repaymentType == null)
			throw new Exception("还款方式不能为空");

		switch (repaymentType) {
		case ORDER: {
			if (Constants.TRAVELZEN_FINANCE_PRODUCT_ID.equals(productId))
				return new TravelzenFinanceRepaymentCalc();
			if (Constants.VJ_PRODUCT_ID.equals(productId))
				return new VJRepaymentCalc();
			return new OrderRepaymentCalc();
		}
		case MONTHLY:
			return new MonthlyRepaymentCalc();
		case EQUALALL:
			return new EqualAllRepaymentCalc();
		case EQUALPRINCIPAL:
			return new EqualPrincipalRepaymentCalc();
		default:
		}
		throw new Exception("还款方式[" + repaymentType.name() + "]、产品ID[" + productId + "]找不到合适的还款计算实例");

	}
}
