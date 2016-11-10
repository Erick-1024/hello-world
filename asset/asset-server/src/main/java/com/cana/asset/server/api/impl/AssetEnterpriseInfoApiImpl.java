package com.cana.asset.server.api.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cana.asset.api.IAssetEnterpriseInfoApi;
import com.cana.asset.service.IAssetEnterpriseInfoService;
import com.cana.asset.service.transaction.IAssetEnterpriseInfoTransactionService;
import com.cana.vbam.common.customer.dto.EnterpriseInfoDTO;
import com.cana.vbam.common.customer.dto.EnterpriseInfoListDTO;

public class AssetEnterpriseInfoApiImpl implements IAssetEnterpriseInfoApi {
	
	@Resource
	private IAssetEnterpriseInfoService assetEnterpriseInfoService;
	
	@Resource
	private IAssetEnterpriseInfoTransactionService assetEnterpriseInfoTransactionService;

	@Override
	public Map<String, Map<String, List<EnterpriseInfoDTO>>> queryEnterpriseMaterialByUserId(String customerId) {
		return assetEnterpriseInfoService.queryEnterpriseMaterialByUserId(customerId);
	}

	@Override
	public void saveEnterpriseInfo(EnterpriseInfoListDTO enterpriseInfoDTOList) throws Exception{
		assetEnterpriseInfoTransactionService.saveEnterpriseInfo(enterpriseInfoDTOList);
	}

	@Override
	public void saveEnterpriseInfoTemp(EnterpriseInfoListDTO enterpriseInfoDTOList) throws Exception{
		assetEnterpriseInfoTransactionService.saveEnterpriseInfoTemp(enterpriseInfoDTOList);
	}

}
