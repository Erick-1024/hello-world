package com.cana.asset.service.transaction.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.mapper.gen.EnterpriseInfoMapper;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.EnterpriseInfo;
import com.cana.asset.dao.po.EnterpriseInfoExample;
import com.cana.asset.service.transaction.IAssetEnterpriseInfoTransactionService;
import com.cana.vbam.common.customer.dto.EnterpriseInfoDTO;
import com.cana.vbam.common.customer.dto.EnterpriseInfoListDTO;
import com.cana.vbam.common.customer.enums.BasicTransaction;
import com.cana.vbam.common.customer.enums.BusinessMaterial;
import com.cana.vbam.common.customer.enums.CustomerMaterialSubmitState;
import com.cana.vbam.common.customer.enums.EnterpriseInfoType;
import com.cana.vbam.common.customer.enums.FactoringFlow;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class AssetEnterpriseInfoTransactionServiceImpl implements IAssetEnterpriseInfoTransactionService{
	
	@Resource
	private EnterpriseInfoMapper enterpriseInfoMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private CustomerMapper customerMapper;

	@Override
	public void saveEnterpriseInfo(EnterpriseInfoListDTO enterpriseInfoDTOList){
		// 基础数据校验
		dataValidate(enterpriseInfoDTOList);
		// 更新客户表中资料提交状态
		Customer customer = customerMapper.selectByPrimaryKey(enterpriseInfoDTOList.getCustomerId());
		if(null == customer){
			throw WebException.instance("客户不存在，数据保存失败");
		}
		customer.setEnterpriseMaterialState(CustomerMaterialSubmitState.SUBMITTED.name());
		customerMapper.updateByPrimaryKeySelective(customer);
		// 历史数据删除
		EnterpriseInfoExample example = new EnterpriseInfoExample();
		example.createCriteria().andCustomerIdEqualTo(enterpriseInfoDTOList.getCustomerId());
		enterpriseInfoMapper.deleteByExample(example);
		// 数据写入
		for(EnterpriseInfoDTO enterpriseInfoDTO:enterpriseInfoDTOList.getInfoList()){
			EnterpriseInfo enterpriseInfo = propertiesConvert(enterpriseInfoDTOList.getCustomerId(), enterpriseInfoDTO);
			enterpriseInfo.setId(generateRecordId());
			enterpriseInfo.setCreateTime(new Date());
			enterpriseInfoMapper.insertSelective(enterpriseInfo);
		}
	}

	@Override
	public void saveEnterpriseInfoTemp(EnterpriseInfoListDTO enterpriseInfoDTOList){
		if(StringUtils.isBlank(enterpriseInfoDTOList.getCustomerId())){
			throw WebException.instance("客户Id不能为空");
		}
		// 更新客户表中资料提交状态
		Customer customer = customerMapper.selectByPrimaryKey(enterpriseInfoDTOList.getCustomerId());
		if(null == customer){
			throw WebException.instance("客户不存在，数据保存失败");
		}
		customer.setEnterpriseMaterialState(CustomerMaterialSubmitState.PARTSUBMIT.name());
		customerMapper.updateByPrimaryKeySelective(customer);
		// 历史数据删除
		EnterpriseInfoExample example = new EnterpriseInfoExample();
		example.createCriteria().andCustomerIdEqualTo(enterpriseInfoDTOList.getCustomerId());
		enterpriseInfoMapper.deleteByExample(example);
		// 数据写入
		for(EnterpriseInfoDTO enterpriseInfoDTO:enterpriseInfoDTOList.getInfoList()){
			EnterpriseInfo enterpriseInfo = propertiesConvert(enterpriseInfoDTOList.getCustomerId(), enterpriseInfoDTO);
			enterpriseInfo.setId(generateRecordId());
			enterpriseInfo.setCreateTime(new Date());
			enterpriseInfoMapper.insertSelective(enterpriseInfo);
		}
	}
	
	private void dataValidate(EnterpriseInfoListDTO enterpriseInfoDTOList){
		if(StringUtils.isBlank(enterpriseInfoDTOList.getCustomerId())){
			throw WebException.instance("客户Id不能为空");
		}
		Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseInfoMap = enterpriseInfoConvert(enterpriseInfoDTOList.getInfoList());
		// 工商资料校验
		if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()))){
			throw WebException.instance("工商资料不能为空");
		}else{
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.BUSINESS_LICENCE.name()))){
				throw WebException.instance("营业执照附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.ORGANIZATION_CREDENTIAL.name()))){
				throw WebException.instance("组织机构代码证附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.TAX_REGISTRATION_CERTIFICATE_CREDENTIAL.name()))){
				throw WebException.instance("税务登记证附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.ORGANIZATION_CREDIT_CREDENTIAL.name()))){
				throw WebException.instance("机构信用代码证附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.CBK_LICENCE.name()))){
				throw WebException.instance("开户许可证附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.COMPANY_POLICY.name()))){
				throw WebException.instance("公司章程附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.LEGAL_PERSON_IDENTITY_CARD_FRONT_AND_BACK.name()))){
				throw WebException.instance("法定代表身份证正反面附件不能为空");
			}
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BUSINESS_MATERIAL.name()).get(BusinessMaterial.COMPANY_INTRODUCE.name()))){
				throw WebException.instance("公司简介附件不能为空");
			}
			// 基础交易校验
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BASIC_TRANSACTION.name()))){
				throw WebException.instance("基础交易资料不能为空");
			}else{
				if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.BASIC_TRANSACTION.name()).get(BasicTransaction.BASIC_TRANSACTION_CONTRACT.name()))){
					throw WebException.instance("基础交易合同附件不能为空");
				}
			}
			// 保理流程校验
			if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.FACTORING_FLOW.name()))){
				throw WebException.instance("放款前资料不能为空");
			}else{
				if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.FACTORING_FLOW.name()).get(FactoringFlow.FACTORING_BUSINESS_MAIN_CONTRACT.name()))){
					throw WebException.instance("保理业务主合同附件不能为空");
				}
				if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.FACTORING_FLOW.name()).get(FactoringFlow.ASSIGNMENT_OF_ACCOUNTS_RECEIVABLE_APPLICATION_AND_RECEIPT.name()))){
					throw WebException.instance("应收账款转让申请书及回执附件不能为空");
				}
				if(CollectionUtils.isEmpty(enterpriseInfoMap.get(EnterpriseInfoType.FACTORING_FLOW.name()).get(FactoringFlow.PEOPLE_BANK_ACCOUNT_RECEIVABLE_TRANSFER_REGISTRATION.name()))){
					throw WebException.instance("人民银行应收账款转让登记附件不能为空");
				}
			}
		}
	}
	
	private Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseInfoConvert(List<EnterpriseInfoDTO> enterpriseInfoDTOList){
		Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseMap = Maps.newHashMap();
		for(EnterpriseInfoDTO enterpriseInfoDTO:enterpriseInfoDTOList){
			Map<String, List<EnterpriseInfoDTO>> itemList = enterpriseMap.get(enterpriseInfoDTO.getCateglory());
			if(itemList == null){
				itemList = Maps.newHashMap();
			}
			List<EnterpriseInfoDTO> items = itemList.get(enterpriseInfoDTO.getItemType());
			if(null == items){
				items = Lists.newArrayList();
			}
			items.add(enterpriseInfoDTO);
			itemList.put(enterpriseInfoDTO.getItemType(), items);
			enterpriseMap.put(enterpriseInfoDTO.getCateglory(), itemList);
		}
		return enterpriseMap;
	}
	
	private EnterpriseInfo propertiesConvert(String customerId, EnterpriseInfoDTO enterpriseInfoDTO){
		EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
		BeanUtils.copyProperties(enterpriseInfoDTO, enterpriseInfo);
		enterpriseInfo.setCustomerId(customerId);
		enterpriseInfo.setSequence(Integer.parseInt(enterpriseInfoDTO.getSequence()));
		return enterpriseInfo;
	}

	private String generateRecordId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_ENTERPRISE_INFO_ID, 4);
	}
}
