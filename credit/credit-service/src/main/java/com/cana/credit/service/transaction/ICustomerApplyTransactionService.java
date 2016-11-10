/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.credit.service.transaction;

import java.util.List;

import com.cana.vbam.common.credit.dto.apply.AccessRuleDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.cana.vbam.common.credit.enums.AccessRuleFitObject;

/**
 * @author ducer
 *
 */
public interface ICustomerApplyTransactionService {

	/**
	 * 人工审核（白名单，非白名单都有），决定了最终的审核通不通过
	 * 人工审批（非白名单），只是审批前一步审核人员填写的额度和质押率，不影响最终的审核通不通过
	 * 白名单的额度是审核通过后，通过定时器跑出来的，生成完额度后发送邮件给客户
	 * 白名单的额度是人工审批人员填写的，人工审核->人工审批->直接保存额度，发送邮件给客户
	 * @param resultDTO
	 * @param userId
	 */
	public void auditTravelzenCustomer(TravelzenCustomerAuditResultDTO resultDTO, String userId);
	
	/**
	 * 获取所有的准入规则（对batch_no降序）
	 * @return
	 */
	public List<AccessRuleDTO> queryAccessRule(AccessRuleFitObject fitObject);
}
