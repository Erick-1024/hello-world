package com.cana.yundaex.service;

import com.cana.yundaex.dao.po.ContractSignSituation;

/**
 * @author hu
 *
 */
public interface IYundaexContractService {

	public ContractSignSituation getContractSituationByCusId(String customerId);
}
