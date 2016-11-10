package com.cana.credit.service;

import com.cana.vbam.common.credit.dto.white.WhiteCustomerDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerParamDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleParamDTO;
import com.travelzen.framework.common.PageList;

public interface IWhiteListService {

	/**
	 * 判断客户是否是可用的白名单用户
	 * @param customerId 客户名称
	 * @return
	 */
	public boolean isAvailableWhiteCustomer(String customerId);
	
	/**
	 * 查询白名单规则列表
	 */
	public PageList<WhiteCustomerRuleDTO> getWhiteCustomerRules(WhiteCustomerRuleParamDTO param);
	/**
	 * 查询白名单详情
	 */
	public PageList<WhiteCustomerDTO> getWhiteCustomers(WhiteCustomerParamDTO param);
}
