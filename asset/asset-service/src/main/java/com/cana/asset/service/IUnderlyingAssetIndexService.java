package com.cana.asset.service;

import java.util.Set;

import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;


/**
 * @author hu
 *
 */
public interface IUnderlyingAssetIndexService {

	/**
	 * 根据业务合同号获取资产id, 状态为空则为所有状态
	 * @param businessContractNo
	 * @param status
	 * @return
	 */
	public String getAssetIdByContractNo(String businessContractNo, UnderlyingAssetPoolStatus status);
	
	/**
	 * 根据客户id获取相关资产(包括交易对手和所属客户), 状态为空则为所有状态
	 * @param customerId
	 * @param status
	 * @return
	 */
	public Set<String> queryAssetIdByCustomerId(String customerId, UnderlyingAssetPoolStatus status);
}
