package com.cana.asset.service.transaction;

import com.cana.vbam.common.customer.dto.EnterpriseInfoListDTO;

public interface IAssetEnterpriseInfoTransactionService {
	
	/**
	 * 保存企业材料信息
	 * @param customerId
	 * @param dataJason
	 */
	public void saveEnterpriseInfo(EnterpriseInfoListDTO enterpriseInfoDTOList);
	
	/**
	 * 更新企业材料信息
	 * @param customerId
	 * @param dataJson
	 */
	public void saveEnterpriseInfoTemp(EnterpriseInfoListDTO enterpriseInfoDTOList);
}
