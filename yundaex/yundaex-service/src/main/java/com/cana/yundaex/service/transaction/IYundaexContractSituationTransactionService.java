package com.cana.yundaex.service.transaction;

import com.cana.yundaex.common.dto.contract.ContractSituationRequestDTO;

/**
 * @author hu
 *
 */
public interface IYundaexContractSituationTransactionService {

	/**
	 * 创建或更新合同情况
	 * @param requestDTO
	 */
	public void createOrUpdateContractSituation(ContractSituationRequestDTO requestDTO);

	/**
	 * 完成个人合同签署状态更新
	 * @param personalId
	 * @param customerId
	 */
	public void signContract(String personalId, String customerId);
}
