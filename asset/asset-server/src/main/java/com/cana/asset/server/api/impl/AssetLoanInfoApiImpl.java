package com.cana.asset.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.asset.api.IAssetLoanInfoApi;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.service.IAssetLoanImportService;
import com.cana.asset.service.IAssetLoanInfoService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.util.AssetLoanPlanAutoGenerator;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.UserPrivilegeDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanInfoExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPlanExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetPaidPlanRequest;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.asset.loan.dto.GenerateLoanPlanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.PaginationUtils;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

public class AssetLoanInfoApiImpl implements IAssetLoanInfoApi {
	
	@Resource
	private IAssetLoanInfoService assetLoanInfoServiceImpl;
	
	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	@Resource 
	private IAssetLoanImportService loanImportService;

	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Override
	public void deleteLoanById(String userId, String loanInfoId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		loanInfoTransactionService.deleteLoanById(userVo, loanInfoId);
	}

	@Override
	public List<LoanPlanDTO> generateLoanPlanDTO(GenerateLoanPlanRequest request) throws Exception {
		return AssetLoanPlanAutoGenerator.generateLoanPlanDTO(request);
	}

	@Override
	public String createAssetLoan(String userId, EditAssetLoanRequest request) {
		if (request == null)
			throw WebException.instance("请求参数不能为空");
		request.setLoanInfoId(null);
//		Boolean redisCached = redisCache.get(GENERATE_LOAN_INFO_ID_CACHE_KEY + request.getLoanInfoId());
//		if (redisCached == null)
//			throw WebException.instance("无效的放款编号");

		UserVo userVo = memberQueryApi.findUserById(userId);
		return loanInfoTransactionService.createAssetLoan(userVo, request);
	}

	@Override
	public String generateAssetLoanInfoId(String contractNo) {
		return "自动生成";
//		if (StringUtils.isBlank(contractNo))
//			throw WebException.instance("合同号不能为空");
//		String loanInfoId = IDGenerator.generateAssetLoanInfoId(contractNo.trim());
//		redisCache.save(GENERATE_LOAN_INFO_ID_CACHE_KEY + loanInfoId, Boolean.TRUE, 60 * 60 * 24);
//		return loanInfoId;
	}

	@Override
	public String updateAssetLoan(String userId, EditAssetLoanRequest request) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		return loanInfoTransactionService.updateAssetLoan(userVo, request);
	}
	
	@Override
	public String getLoanInfoRediskey(){
		return generateLoanInfoRedisKey();
	}
	
	@Override
	public String getLoanPlanRediskey(){
		return generateLoanPlanRedisKey();
	}

	@Override
	public void importExcelLoanInfo2Redis(List<AssetLoanInfoExcelDTO> loanExcelList, String operatorId, String rediskey) {
		loanImportService.importLoanInfoExcel2Redis(loanExcelList, operatorId, rediskey);
	}

	@Override
	public ListResult<AssetLoanInfoExcelDTO> getLoanInfoFromRedis(String redisKey, String operatorId, boolean passed, int page, int pageSize) {
		return loanImportService.getLoanInfoFromRedis(redisKey, operatorId, passed, page, pageSize);
	}

	@Override
	public void importLoanInfoExcel2DB(String operatorId, String rediskey) {
		loanImportService.importLoanInfoExcel2DB(operatorId, rediskey);
	}

	@Override
	public void importExcelLoanPlan2Redis(List<AssetLoanPlanExcelDTO> loanPlanExcelList, String operatorId,
			String rediskey) {
		loanImportService.importLoanPlanExcel2Redis(loanPlanExcelList, operatorId, rediskey);
		
	}

	@Override
	public ListResult<AssetLoanPlanExcelDTO> getLoanPlanFromRedis(String redisKey, String operatorId, boolean passed,
			int page, int pageSize) {
		return loanImportService.getLoanPlanFromRedis(redisKey, operatorId, passed, page, pageSize);
	}

	@Override
	public void importLoanPlanExcel2DB(String operatorId, String rediskey) {
		loanImportService.importLoanPlanExcel2DB(operatorId, rediskey);
	}
	
	@Override
	public boolean paidAssetLoanPlan(String userId, AssetPaidPlanRequest request) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		return loanInfoTransactionService.paidAssetLoanPlan(userVo, request);
	}
	
	@Override
	public ListResult<AssetLoanDTO> getLoanList(AssetLoanListRequest assetLoanListRequest) {
		checkAssetLoanListRequest(assetLoanListRequest);
		UserPrivilegeDTO privilege = dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(assetLoanListRequest.getUserId()));
		if(StringUtils.isNotBlank(privilege.getFactorId()))
			assetLoanListRequest.setFactorId(privilege.getFactorId());
		else
			assetLoanListRequest.setCustomerIds(privilege.getCustomerIdsList());
		PaginationUtils.StandardizingPagination(assetLoanListRequest);
		return assetLoanInfoServiceImpl.getLoanList(assetLoanListRequest);
	}

	@Override
	public AssetLoanDTO getLoanDetail(String id, String userId) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("id不能为空");
		AssetLoanDTO assetLoanDTO = assetLoanInfoServiceImpl.getLoanDetail(id);
		dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(userId), assetLoanDTO.getCustomerId(), assetLoanDTO.getFactorId());
		return assetLoanDTO;
	}
	
	@Override
	public ListResult<LoanPaidDTO> getLoanPaidList(AssetLoanPaidListRequest assetLoanPaidListRequest) {
		if(StringUtils.isBlank(assetLoanPaidListRequest.getLoanId()))
			throw WebException.instance("loanInfoId不能为空");
		LoanInfo loanInfo = assetLoanInfoServiceImpl.getLoanInfo(assetLoanPaidListRequest.getLoanId());
		dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(assetLoanPaidListRequest.getUserId()), loanInfo.getCustomerId(), loanInfo.getFactorId());
		return assetLoanInfoServiceImpl.getLoanPaidList(assetLoanPaidListRequest);
	}
	
	private void checkAssetLoanListRequest(AssetLoanListRequest assetLoanListRequest) {
		checkDate10(assetLoanListRequest.getLoanStartDate());
		checkDate10(assetLoanListRequest.getLoanEndDate());
		checkDate10(assetLoanListRequest.getDueStartDate());
		checkDate10(assetLoanListRequest.getDueEndDate());
	}

	private void checkDate10(String date10) {
		if(StringUtils.isNotBlank(date10))
			DateTimeUtil.validateDate10(date10);
	}

	private String generateLoanInfoRedisKey() {
		return DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_LOAN_INFO_REDIS_KEY, 4);
	}
	private String generateLoanPlanRedisKey() {
		return DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_LOAN_PLAN_REDIS_KEY, 4);
	}
}
