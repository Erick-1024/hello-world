package com.cana.credit.service;

import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;

public interface IAuditResultService {

	/**
	 * 保存真旅网预审核结果信息
	 */
	public void saveTravelzenAuditResult(CustomerApplyRequestDTO customerApplyDTO)throws Exception ;
	
	/**
	 * 根据真旅客户id检查真旅客户是否存在cana平台
	 * @param tzCustomerId
	 * @return
	 */
	public boolean checkTzCustomerInfoExist(String tzCustomerId);

}
