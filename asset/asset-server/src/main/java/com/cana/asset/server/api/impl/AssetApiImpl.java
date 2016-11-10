package com.cana.asset.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cana.asset.api.IAssetApi;
import com.cana.asset.service.transaction.IAssetTransactionService;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractListReqDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.dto.ListResult;

public class AssetApiImpl implements IAssetApi {

	@Resource
    IAssetTransactionService assetTransactionService;
    @Override
    public ListResult<ContractInfoDTO> getCompanyContracts(ContractListReqDTO request,String masterId) {
        return assetTransactionService.getCompanyContracts(request,masterId);
    }

    @Override
	public void updateContractByMemberIdAndProductId(ContractInfoDTO contractInfoDTO) throws Exception {
    	assetTransactionService.updateContractByMemberIdAndProductId(contractInfoDTO);
	}
    
	@Override
	public String generateContractId() throws Exception {
		return assetTransactionService.generateContractId();
	}

	@Override
	public List<ContractInfoDTO> getContractsWithoutPaging(ContractQueryCriteria contractQueryCriteria) {
		return assetTransactionService.getContractsWithoutPaging(contractQueryCriteria);
	}
	
	@Override
	public String getAccountNoByTravelzenFinanceId(String financeId) {
		return assetTransactionService.getAccountNoByTravelzenFinanceId(financeId);
	}

	@Override
	public ContractInfoDTO getContractInfoByMemberId(String memberId, String productId) {
		return assetTransactionService.getContractInfoByMemberId(memberId, productId);
	}
	
	@Override
	public boolean checkContractExistByTravelzenFinanceId(String financeId) {
		return assetTransactionService.checkContractExistByTravelzenFinanceId(financeId);
	}

	@Override
	public void createContract(ContractInfoDTO contractInfoDTO) throws Exception {
		assetTransactionService.createContract(contractInfoDTO);
	}

	@Override
	public ContractInfoDTO getContractInfoByProtocolNo(String protocolNo) {
		return assetTransactionService.getContractInfoByProtocolNo(protocolNo);
	}

}
