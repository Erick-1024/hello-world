package com.cana.yundaex.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.yundaex.dao.mapper.gen.ContractSignSituationMapper;
import com.cana.yundaex.dao.po.ContractSignSituation;
import com.cana.yundaex.service.IYundaexContractService;

/**
 * @author hu
 *
 */
@Service
public class YundaexContractServiceImpl implements IYundaexContractService{

	@Resource
	private ContractSignSituationMapper contractMapper;
	
	@Override
	public ContractSignSituation getContractSituationByCusId(String customerId) {
		
		return contractMapper.selectByPrimaryKey(customerId);
	}

}
