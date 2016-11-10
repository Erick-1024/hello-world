package com.cana.repayment.service.bo;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.travelzen.framework.core.util.NumberUtils;

public class RepaymentSingleDistributeDetailBO extends RepaymentSingleDistributeDetail{
	
	private static final long serialVersionUID = 54774053705834899L;

	public RepaymentSingleDistributeDetailBO(RepaymentSingleDistributeDetail po){
		BeanUtils.copyProperties(po, this);
	}

	/**
	 * 计算总的还款额
	 * @return
	 */
	public long total() {
		return NumberUtils.getValue(getPayNormalPrincipal()) + NumberUtils.getValue(getPayNormalInterest()) + NumberUtils.getValue(getPayNormalServiceCharge())
		       + NumberUtils.getValue(getPayExtensionCharge()) + NumberUtils.getValue(getPayExpense()) + NumberUtils.getValue(getEarlyRepaymentCharge())
		       + NumberUtils.getValue(getPayOverduePrincipal()) + NumberUtils.getValue(getPayOverdueInterest()) + NumberUtils.getValue(getPayOverdueServiceCharge())
		       + NumberUtils.getValue(getPayOverduePrincipalPenalty()) + NumberUtils.getValue(getPayOverdueInterestPenalty()) + NumberUtils.getValue(getPayOverdueServiceChargePenalty())
		       + NumberUtils.getValue(getPayOtherPenalty());
	}

	/**
	 * 总算总的还款本金金额
	 * @return
	 */
	public long totalPrincipal() {
		return NumberUtils.getValue(getPayNormalPrincipal()) + NumberUtils.getValue(getPayOverduePrincipal());
	}

	
}
