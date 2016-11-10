package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractListReqDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.dto.ListResult;

public interface IAssetApi {

    /**
     * 获取企业合同列表（有分页）
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
     * @throws Exception
     */
	public void updateContractByMemberIdAndProductId(ContractInfoDTO contractInfoDTO)throws Exception;
	
	/**
	 * 生成合同表id
	 */
	public String generateContractId() throws Exception;
	
	/**
	 * 获取真旅合同信息
	 * @param financeId
	 * @return
	 */
	public String getAccountNoByTravelzenFinanceId(String financeId);

	/**
	 * 获取韵达合同信息
	 * @param memberId 用户ID
	 * @param productId 产品ID
	 * @return
	 */
	public ContractInfoDTO getContractInfoByMemberId(String memberId,String productId);
	
	/**
	 * 创建韵达单笔融资合同
	 * @param contractInfoDTO
	 * @throws Exception
	 */
	public void createContract(ContractInfoDTO contractInfoDTO) throws Exception;
	
	/**
	 * 检查某一个融资上是否已经签署过合同了
	 */
	public boolean checkContractExistByTravelzenFinanceId(String financeId);

	/**
	 * 根据合同ID获取韵达合同信息
	 * @param protocolNo 合同ID
	 * @return
	 */
	public ContractInfoDTO getContractInfoByProtocolNo(String protocolNo);

}
