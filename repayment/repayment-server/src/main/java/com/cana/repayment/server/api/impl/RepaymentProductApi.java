package com.cana.repayment.server.api.impl;

import javax.annotation.Resource;

import com.cana.repayment.api.IRepaymentProductApi;
import com.cana.repayment.service.IRepositoryService;

public class RepaymentProductApi implements IRepaymentProductApi {
	
	@Resource
	private IRepositoryService repositoryServiceImpl;

	@Override
	public boolean isExistProduct(String productId) {
		return repositoryServiceImpl.isExistProduct(productId);
	}
}
