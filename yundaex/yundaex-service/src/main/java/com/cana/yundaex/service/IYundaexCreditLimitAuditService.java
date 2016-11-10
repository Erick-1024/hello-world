/**
 * 
 */
package com.cana.yundaex.service;

import com.cana.credit.limit.dao.po.CreditLimitAudit;

/**
 * 韵达项目-额度变化
 * 
 * @author guguanggong
 *
 */
public interface IYundaexCreditLimitAuditService {
	/**
	 * 插入额度变化信息
	 * @param yundaexCreditLimitAudit
	 */
	public void insertYunadexCreditLimitAudit(CreditLimitAudit yundaexCreditLimitAudit);
}
