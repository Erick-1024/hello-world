package com.cana.asset.server.api.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.asset.api.IAssetFactorBusinessApi;
import com.cana.asset.service.IAssetCreditService;
import com.cana.asset.service.IAssetCustomerService;
import com.cana.asset.service.IAssetFactorBusinessService;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.CounterpartyListSearchDTO;
import com.cana.vbam.common.asset.dto.CounterpartySearchDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessInfoDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessSearchDTO;
import com.cana.vbam.common.asset.dto.UserPrivilegeDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.utils.PaginationUtils;
import com.travelzen.framework.core.exception.WebException;

public class AssetFactorBusinessApiImpl implements IAssetFactorBusinessApi {
	
	@Resource
	private IAssetFactorBusinessService assetFactorBusinessService;
	
	@Resource
	private IAssetFactorBusinessTransactionService assetFactorBusinessTransactionService;
	
	@Resource
	private IAssetCreditService assetCreditService;

	@Resource
	private IAssetCustomerService assetCustomerService;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private DataPermissionValidator dataPermissionValidator;

	@Override
	public FactorBusinessDTO queryFactorBusinessInfo(String factorBusinessId, String userId) {
		if(StringUtils.isBlank(factorBusinessId))
			throw WebException.instance("factorBusinessId不能为空");
		FactorBusinessDTO factorBusinessDTO = assetFactorBusinessService.queryFactorBusinessInfo(factorBusinessId);
		dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(userId), factorBusinessDTO.getCustomerId(), factorBusinessDTO.getFactorId());
		return factorBusinessDTO;
	}

	@Override
	public ListResult<FactorBusinessDTO> getFactorBusinessList(FactorBusinessSearchDTO factorBusinessSearchDTO) { 
		UserPrivilegeDTO privilege = dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(factorBusinessSearchDTO.getUserId()));
		if(StringUtils.isNotBlank(privilege.getFactorId()))
			factorBusinessSearchDTO.setFactorId(privilege.getFactorId());
		else
			factorBusinessSearchDTO.setCustomerIds(privilege.getCustomerIdsList());
		PaginationUtils.StandardizingPagination(factorBusinessSearchDTO);
		return assetFactorBusinessService.getFactorBusinessList(factorBusinessSearchDTO);
	}

	@Override
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO) {
		return assetFactorBusinessService.saveFactorBusinessInfo(factorBusinessDTO);
	}

	@Override
	public CreditDTO getCreditDTO(String businessContractNo, String customerId, String id) {
		if(assetFactorBusinessService.countBusinessContract(businessContractNo, id) > 0){
			throw WebException.instance("该业务合同号已被使用");
		}
		return assetCreditService.getCreditByBusinessContractNo(businessContractNo, customerId);
	}

	@Override
	public String getCreditVersion(String creditId) {
		return assetCreditService.getLastCreditAuditByCreditId(creditId);
	}
	
	@Override
	public void deleteFactorBusiness(String id, String userId) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("id不能为空");
		assetFactorBusinessTransactionService.deleteFactorBusiness(id, memberQueryApi.findUserById(userId));
	}
	
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(CounterpartySearchDTO searchDTO){
		return assetFactorBusinessService.queryBusinessCounterpartyDTO(searchDTO);
	}


	@Override
	public FactorBusinessInfoDTO queryFactorBusinessInfoDTO(String businessContractNo, String factorId) {
		return FactorBusinessInfoDTOAssemble(assetFactorBusinessTransactionService.queryFactorBusinessInfoByBusinessContractNo(businessContractNo, factorId));
	}

	private FactorBusinessInfoDTO FactorBusinessInfoDTOAssemble(FactorBusinessDTO factorBusinessDTO){
		FactorBusinessInfoDTO factorBusinessInfoDTO = null;
		if(factorBusinessDTO !=null){
			factorBusinessInfoDTO = new FactorBusinessInfoDTO();
			factorBusinessInfoDTO.setBusinessProduct(factorBusinessDTO.getBusinessProduct());
			factorBusinessInfoDTO.setCounterpartyDTOList(factorBusinessDTO.getCounterpartyList());
			factorBusinessInfoDTO.setFinanceCustomerId(factorBusinessDTO.getCustomerId());
			factorBusinessInfoDTO.setFinanceCustomerName(factorBusinessDTO.getCustomerName());
			factorBusinessInfoDTO.setCurrency(factorBusinessDTO.getCurrency());
			factorBusinessInfoDTO.setProjectName(factorBusinessDTO.getProjectName());
			factorBusinessInfoDTO.setFactoringAccount(factorBusinessDTO.getFactoringAccount());
			factorBusinessInfoDTO.setSettlementAccount(factorBusinessDTO.getSettlementAccount());
		}
		return factorBusinessInfoDTO;
	}

	@Override
	public ListResult<CustomerListResponseDTO> getCustomerList(String userId, CounterpartyListSearchDTO counterpartyListSearchDTO) {
		return assetCustomerService.queryCustomerListByCondition(memberQueryApi.findUserById(userId), counterpartyListSearchDTO);
	}
}
