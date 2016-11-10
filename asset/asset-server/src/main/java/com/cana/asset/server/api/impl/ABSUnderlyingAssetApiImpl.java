package com.cana.asset.server.api.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.cana.asset.api.IABSUnderlyingAssetApi;
import com.cana.asset.service.IUnderlyingAssetImportService;
import com.cana.asset.service.transaction.IUnderlyingAssetQueryTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.underlyingasset.dto.ConvertToUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetResponse;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.enums.RequestDirection;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;

public class ABSUnderlyingAssetApiImpl implements IABSUnderlyingAssetApi {

	@Resource
	private IMemberQueryApi memberQueryApi;
	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;
	@Resource
	private IUnderlyingAssetImportService underlyingAssetImportService;
	@Resource
	private IUnderlyingAssetQueryTransactionService underlyingAssetQueryTransactionService;

	@Override
	public void createUnderlyingAssetByFactorLoan(String userId, ConvertToUnderlyingAssetRequest request) {
		if (request == null)
			throw WebException.instance("参数不能为空");

		UserVo userVo = memberQueryApi.findUserById(userId);
		if (false == request.isConvertAllMode()) {
			underlyingAssetTransactionService.createUnderlyingAssetByFactorLoan(userVo, request.getLoanInfoIds());
		} else { // 全选模式

			QueryFactorLoanForUnderlyingAssetRequest queryParam = new QueryFactorLoanForUnderlyingAssetRequest();
			queryParam.setPageSize(Integer.MAX_VALUE);
			queryParam.setLoanInfoId(request.getLoanInfoId());
			queryParam.setFinanceBalanceLower(request.getFinanceBalanceLower());
			queryParam.setFinanceBalanceUpper(request.getFinanceBalanceUpper());
			queryParam.setDueDateBegin(request.getDueDateBegin());
			queryParam.setDueDateEnd(request.getDueDateEnd());
			ListResult<QueryFactorLoanForUnderlyingAssetResponse> loans = underlyingAssetTransactionService
					.queryFactorLoanForUnderlyingAsset(userVo, queryParam);

			Set<String> loanInfoIds = Sets.newHashSet();
			for (QueryFactorLoanForUnderlyingAssetResponse loan : loans.getData()) {
				loanInfoIds.add(loan.getLoanInfoId());
			}
			if (CollectionUtils.isNotEmpty(request.getExcludeLoanInfoIds())) {
				for (String excludeLoanInfoId : request.getExcludeLoanInfoIds()) {
					loanInfoIds.remove(excludeLoanInfoId);
				}
			}
			if (CollectionUtils.isEmpty(loanInfoIds))
				throw WebException.instance("请至少选择一条记录");
			underlyingAssetTransactionService.createUnderlyingAssetByFactorLoan(userVo, Lists.newArrayList(loanInfoIds));
		}
	}

	@Override
	public ListResult<QueryFactorLoanForUnderlyingAssetResponse> queryFactorLoanForUnderlyingAsset(
			String userId, QueryFactorLoanForUnderlyingAssetRequest request) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		return underlyingAssetTransactionService.queryFactorLoanForUnderlyingAsset(userVo, request);
	}

	@Override
	public void deleteUnderlyingAsset(String userId, String underlyingAssetId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.deleteUnderlyingAsset(userVo, underlyingAssetId);
	}

	@Override
	public void bindSpecialProgram(String userId, EnterAssetPoolRequest request) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.bindSpecialProgram(userVo, request);
	}

	@Override
	public void unbindSpecialProgram(String userId, String underlyingAssetId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.unbindSpecialProgram(userVo, underlyingAssetId);
	}

	@Override
	public void enterAssetPool(String userId, List<String> underlyingAssetIds) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.enterAssetPool(userVo, underlyingAssetIds);
	}

	@Override
	public void outAssetPoolAndDelete(String userId, String underlyingAssetId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.outAssetPoolAndDelete(userVo, underlyingAssetId);
	}

	@Override
	public void outAssetPoolAndKeepBind(String userId, String underlyingAssetId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.outAssetPoolAndKeepBind(userVo, underlyingAssetId);
	}

	@Override
	public void redeemAssetPool(String userId, String underlyingAssetId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.redeemAssetPool(userVo, underlyingAssetId);
	}

	@Override
	public void updateUnderlyingAsset(String userId, EditUnderlyingAssetRequest request) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		underlyingAssetTransactionService.updateUnderlyingAsset(userVo, request);
	}

	@Override
	public String generateUnderlyingAssetRedisKey() {
		return underlyingAssetImportService.generateUnderlyingAssetRedisKey();
	}

	@Override
	public void importUnderlyingAssetExcel2Redis(List<UnderlyingAssetExcelDTO> loanExcelList, String operatorId,
			String rediskey) {
		underlyingAssetImportService.importUnderlyingAssetExcel2Redis(loanExcelList, operatorId, rediskey);
	}

	@Override
	public ListResult<UnderlyingAssetExcelDTO> getUnderlyingAssetFromRedis(String redisKey, String operatorId,
			boolean passed, int page, int pageSize) {
		return underlyingAssetImportService.getUnderlyingAssetFromRedis(redisKey, operatorId, passed, page, pageSize);
	}

	@Override
	public void importUnderlyingAssetExcel2DB(String operatorId, String rediskey) {
		underlyingAssetImportService.importUnderlyingAssetExcel2DB(operatorId, rediskey);
	}

	@Override
	public ListResult<UnderlyingAssetDTO> queryUnderlyingAssets(UserVo userVo, UnderlyingAssetQueryDTO queryDTO, RequestDirection requestDirection) {
		return underlyingAssetQueryTransactionService.queryUnderlyingAssets(userVo, queryDTO, requestDirection);
	}

	@Override
	public UnderlyingAssetDTO getUnderlyingAssetDetail(UserVo userVo, String underlyingAssetId) {
		return underlyingAssetQueryTransactionService.getUnderlyingAssetDetail(userVo, underlyingAssetId);
	}
}
