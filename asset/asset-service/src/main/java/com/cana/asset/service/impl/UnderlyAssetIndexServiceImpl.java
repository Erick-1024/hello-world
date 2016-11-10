package com.cana.asset.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.UnderlyingAssetMapper;
import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetExample;
import com.cana.asset.service.IUnderlyingAssetIndexService;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;

/**
 * @author hu
 *
 */
@Service
public class UnderlyAssetIndexServiceImpl implements IUnderlyingAssetIndexService {

	@Resource
	private UnderlyingAssetMapper assetMapper;
	
	@Override
	public String getAssetIdByContractNo(String businessContractNo, UnderlyingAssetPoolStatus status) {
		UnderlyingAssetExample example = new UnderlyingAssetExample();
		UnderlyingAssetExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessContractNoEqualTo(businessContractNo);
		if(null != status)
			criteria.andAssetPoolStatusEqualTo(status.name());
		List<UnderlyingAsset> assets = assetMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(assets))
			return null;
		return assets.get(0).getId();
	}

	@Override
	public Set<String> queryAssetIdByCustomerId(String customerId, UnderlyingAssetPoolStatus status) {
		Set<String> assetIds = new HashSet<>();
		UnderlyingAssetExample example = new UnderlyingAssetExample();
		UnderlyingAssetExample.Criteria criteria = example.createCriteria();
		UnderlyingAssetExample.Criteria criteria1 = example.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		criteria1.andCounterpartyIdEqualTo(customerId);
		if(null != status){
			criteria.andAssetPoolStatusEqualTo(status.name());
			criteria1.andAssetPoolStatusEqualTo(status.name());
		}
		example.or(criteria1);
		List<UnderlyingAsset> assets = assetMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(assets))
			return null;
		for(UnderlyingAsset asset : assets)
			assetIds.add(asset.getId());
		return assetIds;
	}

}
