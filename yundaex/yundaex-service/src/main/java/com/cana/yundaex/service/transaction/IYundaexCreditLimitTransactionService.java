package com.cana.yundaex.service.transaction;

import com.cana.yundaex.dao.po.YundaexCustomerApply;

public interface IYundaexCreditLimitTransactionService {

	/**
	 * 计算额度
	 * @param ydCustomerApply
	 */
	void calculateApplyCreditLimit(YundaexCustomerApply ydCustomerApply);
	
	/**
	 * 激活额度
	 * @param memberId
	 */
	void activateCreditLimit(String memberId);

	/**
	 * 授信审核 驳回
	 * @param id
	 */
	void creditAuditReject(String id);

	/**
	 * 授信审核 通过
	 * @param id
	 */
	void creditAuditPass(String id);

}
