package com.cana.asset.service;

import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.dto.ListResult;

public interface IAssetCreditService {

	/**
	 * 获取所有客户，并根据额度信息进行排序
	 * @param crieria
	 * @return
	 */
	public ListResult<CustomerCreditDTO> getCustomers(CustomerCreditQueryCriteria crieria);
	
	/**
	 * 根据业务合同号获取额度
	 * @param businessContractNo
	 * @return
	 */
	public CreditDTO getCreditByBusinessContractNo(String businessContractNo, String customerId);
	
	/**
	 * 根据额度id获取最近一次额度
	 * @param creditId
	 * @return
	 */
	public String getLastCreditAuditByCreditId(String creditId);
}
