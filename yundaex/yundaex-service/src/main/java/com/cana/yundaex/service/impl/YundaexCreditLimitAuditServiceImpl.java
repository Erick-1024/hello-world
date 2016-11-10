/**
 * 
 */
package com.cana.yundaex.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.yundaex.service.IYundaexCreditLimitAuditService;

/**
 * 韵达项目-额度变化
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexCreditLimitAuditServiceImpl implements IYundaexCreditLimitAuditService {

	@Resource
	private CreditLimitAuditMapper yundaexCreditLimitAuditMapper;
	
	/**
	 * 插入额度变化信息
	 * @param yundaexCreditLimitAudit
	 */
	@Override
	public void insertYunadexCreditLimitAudit(CreditLimitAudit yundaexCreditLimitAudit) {
		yundaexCreditLimitAuditMapper.insertSelective(yundaexCreditLimitAudit);
	}

}
