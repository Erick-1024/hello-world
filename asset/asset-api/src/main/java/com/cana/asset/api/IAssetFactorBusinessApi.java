package com.cana.asset.api;

import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.CounterpartyListSearchDTO;
import com.cana.vbam.common.asset.dto.CounterpartySearchDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessInfoDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessSearchDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;

public interface IAssetFactorBusinessApi {

	/**
	 * 查询业务列表
	 * @param factorBusinessSearchDTO
	 * @return
	 */
	public ListResult<FactorBusinessDTO> getFactorBusinessList(FactorBusinessSearchDTO factorBusinessSearchDTO);
	
	/**
	 * 查询保理业务信息
	 * @param factorBusinessId
	 * @param userId
	 * @return
	 */
	public FactorBusinessDTO queryFactorBusinessInfo(String factorBusinessId, String userId);
	
	/**
	 * 保存保理业务信息
	 * @param factorBusinessDTO
	 */
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO);


	/**
	 * 查询额度信息
	 * @param factorBusinessDTO
	 * @return
	 */
	public CreditDTO getCreditDTO(String businessContractNo, String customerId, String id);

	
	/**
	 * 额度Id
	 * @param creditId
	 * @return
	 */
	public String getCreditVersion(String creditId);
	
	/**
	 * 查询交易对手信息(业务合同号、交易对手名称)
	 * @param searchDTO
	 * @return
	 */
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(CounterpartySearchDTO searchDTO);
	
	/**
	 * 删除业务合同及相关信息
	 * @param id
	 * @param userId
	 */
	public void deleteFactorBusiness(String id, String userId);
	
	/**
	 * 查询保理业务部分信息
	 * @return
	 */
	public FactorBusinessInfoDTO queryFactorBusinessInfoDTO(String businessContractNo, String factorId);
	
	/**
	 * 查询客户信息
	 * @param exceptList
	 * @return
	 */
	public ListResult<CustomerListResponseDTO> getCustomerList(String userId, CounterpartyListSearchDTO counterpartyListSearchDTO);
	
}
