package com.cana.credit.service.transaction;


import java.util.List;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.enums.CreditLimitAuditType;

public interface ICreditLimitTransactionService {

	/**
	 * 激活用户的额度
	 * @param memberId 用户ID
	 * @deprecated
	 */
	public List<CreditLimit> activateCreditLimit(String memberId);
	
	public CreditLimit activateCreditLimitByLimitId(String limitId);
	public CreditLimit getCreditLimit(String limitId);
	
	/**
	 * 计算某条申请的额度
	 * @param customerApply
	 */
	public CalculateLimitResult calculateApplyCreditLimit(CustomerApply customerApply);
	
	/**
	 * 插入credit_limit_audit记录
	 * @param limit 改变的额度记录
	 * @param type 额度变化类型
	 * @param newUsedLimit 变化后的已使用额度
	 * @param tradeId 对应的交易表id
	 */
	public void insertCreditLimitAudit(CreditLimit limit,CreditLimitAuditType creditLimitAuditType,Long newUsedLimit,String tradeId);
	
	/**
	 * 获取客户的额度信息
	 * @param memberId
	 * @param outCustomerId
	 * @return
	 */
	public CreditLimit getCreditLimitByMemberId(String memberId, String outCustomerId);

}
