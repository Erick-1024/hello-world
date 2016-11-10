package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractListReqDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.dto.ListResult;

public interface IAssetTransactionService {
	/**
     * 获取企业合同列表
     * @param request
     * @return
     */
    public ListResult<ContractInfoDTO> getCompanyContracts(ContractListReqDTO request,String masterId);

    /**
     * 获取合同列表（无分页）
     * @param contractQueryCriteria
     * @return
     */
    public List<ContractInfoDTO> getContractsWithoutPaging(ContractQueryCriteria contractQueryCriteria);
    
    /**
	 * 更新合同记录
	 * @param contractInfoDTO
	 * @return
	 * @throws Exception
	 */
	public void updateContractByMemberIdAndProductId(ContractInfoDTO contractInfoDTO) throws Exception;
	
	/**
	 * 生成合同表id
	 */
	public String generateContractId() throws Exception;
	
	public String getAccountNoByTravelzenFinanceId(String financeId);

	/**
	 * 根据用户ID、产品ID、合同类型 获取合同信息
	 * @param productId 
	 * @param memberId 
	 * @return
	 */
	public ContractInfoDTO getContractInfoByMemberId(String memberId, String productId);

	public boolean checkContractExistByTravelzenFinanceId(String financeId);

	/**
	 * 创建合同记录
	 * @param contractInfoDTO
	 */
	public void createContract(ContractInfoDTO contractInfoDTO);

	/**
	 * 根据合同ID获取韵达合同信息
	 * @param protocolNo
	 * @return
	 */
	public ContractInfoDTO getContractInfoByProtocolNo(String protocolNo);

}
