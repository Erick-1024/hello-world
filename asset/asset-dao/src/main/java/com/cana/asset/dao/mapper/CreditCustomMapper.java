package com.cana.asset.dao.mapper;

import java.util.List;

import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;

/**
 * @author hu
 *
 */
public interface CreditCustomMapper {

	public int countCustomerByCondition(CustomerCreditQueryCriteria crieria);
	
	public List<CustomerCreditDTO> searchCustomerByCondition(CustomerCreditQueryCriteria crieria);
	
	public int countAllCustomerOrderByCreditLimit(CustomerCreditQueryCriteria crieria);
	
	public List<CustomerCreditDTO> searchAllCustomerOrderByCreditLimit(CustomerCreditQueryCriteria crieria);
}
