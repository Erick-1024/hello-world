package com.cana.asset.api;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.customer.dto.EnterpriseInfoDTO;
import com.cana.vbam.common.customer.dto.EnterpriseInfoListDTO;

public interface IAssetEnterpriseInfoApi {
	
	/**
	 * 获取企业材料
	 * @param customerId
	 * @return
	 */
	public Map<String, Map<String, List<EnterpriseInfoDTO>>> queryEnterpriseMaterialByUserId(String customerId);

	/**
	 * 保存企业材料信息
	 * @param customerId
	 * @param dataJason
	 */
	public void saveEnterpriseInfo(EnterpriseInfoListDTO enterpriseInfoDTOList) throws Exception;
	
	/**
	 * 更新企业材料信息
	 * @param customerId
	 * @param dataJson
	 */
	public void saveEnterpriseInfoTemp(EnterpriseInfoListDTO enterpriseInfoDTOList) throws Exception;
}
