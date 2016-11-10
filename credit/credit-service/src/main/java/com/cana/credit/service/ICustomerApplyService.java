/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.credit.service;


import com.cana.vbam.common.credit.dto.apply.CustomerApplyDetailDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyListQueryDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyMinDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.travelzen.framework.common.PageList;

/**
 * @author ducer
 *
 */
public interface ICustomerApplyService {

	public void auditTravelzenCustomer(TravelzenCustomerAuditResultDTO resultDTO);
	
	public PageList<CustomerApplyMinDTO> getCustomerApplyList(CustomerApplyListQueryDTO customerApplyListQueryDTO);
	
	public CustomerApplyDetailDTO getCustomerApplyInfo(String id);
	
}
