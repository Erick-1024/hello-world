package com.cana.asset.service;

import com.cana.asset.dao.po.LoanInfo;
import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.dto.ListResult;

public interface IAssetLoanInfoService {

	/**
	 * 查询放款列表
	 * @param assetLoanListRequest
	 * @return
	 */
	public ListResult<AssetLoanDTO> getLoanList(AssetLoanListRequest assetLoanListRequest);
	
	/**
	 * 查询历史还款明细列表
	 * @param assetLoanPaidListRequest
	 * @return
	 */
	public ListResult<LoanPaidDTO> getLoanPaidList(AssetLoanPaidListRequest assetLoanPaidListRequest);
	
	/**
	 * 获取放款详情
	 * @param id
	 * @return
	 */
	public AssetLoanDTO getLoanDetail(String id);
	
	/**
	 * 获取放款详情
	 * @param id
	 * @return
	 */
	public LoanInfo getLoanInfo(String id);
	
}
