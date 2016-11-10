package com.cana.asset.service;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.customer.dto.EnterpriseInfoDTO;

public interface IAssetEnterpriseInfoService {

	/**
	 * 获取企业材料
	 * @param customerId
	 * @return
	 */
	public Map<String, Map<String, List<EnterpriseInfoDTO>>> queryEnterpriseMaterialByUserId(String customerId);
}
