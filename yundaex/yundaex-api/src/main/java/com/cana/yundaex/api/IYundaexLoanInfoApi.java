package com.cana.yundaex.api;

import java.util.List;

import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexRepaymentRecordDTO;

public interface IYundaexLoanInfoApi {
	
	/**
	 * 还款信息查询
	 * @param loanInfoQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<YundaexRepaymentRecordDTO> repaymentRecord(YundaexLoanInfoQueryDTO loanInfoQueryDTO) throws Exception;
	
}
