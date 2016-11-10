package com.cana.asset.service;

import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.CounterpartySearchDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessSearchDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;

public interface IAssetFactorBusinessService {

	/**
	 * 查询保理业务信息
	 * @param factorBusinessId
	 * @return
	 */
	public FactorBusinessDTO queryFactorBusinessInfo(String factorBusinessId);
	
	/**
	 * 查询业务列表
	 * @param factorBusinessSearchDTO
	 * @return
	 */
	public ListResult<FactorBusinessDTO> getFactorBusinessList(FactorBusinessSearchDTO factorBusinessSearchDTO);
	
	/**
	 * 查询交易对手信息(业务合同号、交易对手名称)
	 * @param searchDTO
	 * @return
	 */
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(CounterpartySearchDTO searchDTO);
	
	/**
	 * 查询保理业务数量(校验用)
	 * @param businessContractNo
	 * @param factorId
	 * @return
	 */
	public int countBusinessContract(String businessContractNo, String id);
	
	/**
	 * 保存业务信息
	 * @param factorBusinessDTO
	 * @return
	 */
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO);
	
}
