package com.cana.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.EnterpriseInfoMapper;
import com.cana.asset.dao.po.EnterpriseInfo;
import com.cana.asset.dao.po.EnterpriseInfoExample;
import com.cana.asset.service.IAssetEnterpriseInfoService;
import com.cana.vbam.common.customer.dto.EnterpriseInfoDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class AssetEnterpriseInfoServiceImpl implements IAssetEnterpriseInfoService{

	@Resource
	private EnterpriseInfoMapper enterpriseInfoMapper;
	
	@Override
	public Map<String, Map<String, List<EnterpriseInfoDTO>>> queryEnterpriseMaterialByUserId(String customerId) {
		EnterpriseInfoExample example = new EnterpriseInfoExample();
		example.createCriteria().andCustomerIdEqualTo(customerId);
		List<EnterpriseInfo> enterpriseInfoList = enterpriseInfoMapper.selectByExample(example);
		return enterpriseInfoConvert(enterpriseInfoList);
	}

	private Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseInfoConvert(List<EnterpriseInfo> enterpriseInfoList){
		Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseMap = Maps.newHashMap();
		for(EnterpriseInfo enterpriseInfo:enterpriseInfoList){
			Map<String, List<EnterpriseInfoDTO>> itemList = enterpriseMap.get(enterpriseInfo.getCateglory());
			if(itemList == null){
				itemList = Maps.newHashMap();
			}
			List<EnterpriseInfoDTO> items = itemList.get(enterpriseInfo.getItemType());
			if(null == items){
				items = Lists.newArrayList();
			}
			items.add(propertiesConvert(enterpriseInfo));
			itemList.put(enterpriseInfo.getItemType(), items);
			enterpriseMap.put(enterpriseInfo.getCateglory(), itemList);
		}
		return enterpriseMap;
	}
	
	private EnterpriseInfoDTO propertiesConvert(EnterpriseInfo enterpriseInfo){
		EnterpriseInfoDTO enterpriseInfoDTO = new EnterpriseInfoDTO();
		BeanUtils.copyProperties(enterpriseInfo, enterpriseInfoDTO);
		enterpriseInfoDTO.setSequence(enterpriseInfo.getSequence().toString());
		return enterpriseInfoDTO;
	}
}
