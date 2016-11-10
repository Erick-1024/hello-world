package com.cana.asset.dao.mapper;

import java.util.List;

import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;

public interface AssetLoanCustomMapper {

	public List<LoanPaidDTO> getLoanPaidDTOList(AssetLoanPaidListRequest assetLoanPaidListRequest);
	
	public int countLoanPaidDTOList(AssetLoanPaidListRequest assetLoanPaidListRequest);
	
}