package com.cana.yundaex.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanInfoQueryDTO;
import com.cana.yundaex.api.IYundaexLoanInfoApi;
import com.cana.yundaex.common.dto.YundaexRepaymentRecordDTO;
import com.cana.yundaex.service.IYundaexLoanInfoService;

public class YundaexLoanInfoApiImpl implements IYundaexLoanInfoApi {

	@Resource
	private IYundaexLoanInfoService yundaexLoanInfoService;

	@Override
	public List<YundaexRepaymentRecordDTO> repaymentRecord(YundaexLoanInfoQueryDTO loanInfoQueryDTO) throws Exception {
		return yundaexLoanInfoService.yundaexRepaymentRecordQuery(loanInfoQueryDTO);
	}

}
