package com.cana.yundaex.service;

import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;

public interface IYundaexAuditResultService {

	/**
	 * 保存韵达预审核结果信息
	 */
	public void saveYundaexAuditResult(YdCustomerApplyRequestDTO ydCustomerApplyDTO)throws Exception ;

}
