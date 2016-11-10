package com.cana.asset.server.api.impl;

import javax.annotation.Resource;

import com.cana.asset.api.IABSLogApi;
import com.cana.asset.service.transaction.ISpecialProgramLogTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetLogTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogQuery;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

public class ABSLogApiImpl implements IABSLogApi {

	@Resource
	private IMemberQueryApi memberQueryApi;
	@Resource
	private ISpecialProgramLogTransactionService specialProgramLogTransactionService;
	@Resource
	private IUnderlyingAssetLogTransactionService underlyingAssetLogTransactionService;
	
	
	@Override
	public ListResult<SpecialProgramLogDTO> querySpecialProgramLogs(UserVo userVo, SpecialProgramLogQuery query) {
		return specialProgramLogTransactionService.querySpecialProgramLogs(userVo, query);
	}
	@Override
	public ListResult<UnderlyingAssetLogDTO> queryUnderlyingAssetLogs(UserVo userVo, UnderlyingAssetLogQuery query) {
		return underlyingAssetLogTransactionService.queryUnderlyingAssetLogs(userVo, query);
	}

	
}
