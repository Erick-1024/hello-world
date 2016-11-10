package com.cana.yundaex.service;

import java.util.List;

import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexRepaymentRecordDTO;

public interface IYundaexLoanInfoService {
	
	/**
	 * 还款信息查询
	 * @param loanInfoQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<YundaexRepaymentRecordDTO> yundaexRepaymentRecordQuery(YundaexLoanInfoQueryDTO loanInfoQueryDTO) throws Exception;
}
