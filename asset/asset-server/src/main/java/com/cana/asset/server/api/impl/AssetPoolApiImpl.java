package com.cana.asset.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.asset.api.IAssetPoolApi;
import com.cana.asset.service.IAssetPoolService;
import com.cana.asset.service.transaction.IAssetPoolTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.AssetInPoolDTO;
import com.cana.vbam.common.asset.dto.AssetPacketDTO;
import com.cana.vbam.common.asset.dto.AssetpoolListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

public class AssetPoolApiImpl implements IAssetPoolApi {

	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private IAssetPoolService assetPoolService;
	
	@Resource
	private IAssetPoolTransactionService assetPoolTransactionService;
	
	/**
	 * 资产池列表
	 */
	@Override
	public PageList<AssetpoolListDTO> getAssetpoolList(SpecialProgramQueryDTO queryDTO, String userId) {
		UserVo userVo = getUserDetail(userId);
		return assetPoolService.getAssetpoolList(queryDTO,userVo);
	}

	@Override
	public AssetpoolListDTO getAssetpoolPacket(String id, String userId) {
		UserVo userVo = getUserDetail(userId);
		return assetPoolService.getAssetpoolPacket(id,userVo);
	}

	@Override
	public void packet(AssetPacketDTO packetDTO, String userId) {
		UserVo userVo = getUserDetail(userId);
		assetPoolTransactionService.packet(packetDTO, userVo);
	}
	
	/**
	 * 资产池管理专项计划
	 */
	@Override
	public AssetpoolListDTO getAssetPoolDetails(String id, String status, String userId) {
		UserVo userVo = getUserDetail(userId);
		return assetPoolService.getAssetPoolDetails(id, status, userVo);
	}

	/**
	 * 管理页面列表
	 * @param queryDTO
	 * @param model
	 * @return
	 */
	@Override
	public PageList<AssetInPoolDTO> getAssetpoolManageList(SpecialProgramQueryDTO queryDTO, String userId) {
		UserVo userVo = getUserDetail(userId);
		return assetPoolService.getAssetpoolManageList(queryDTO, userVo);
	}

	/**
	 * 查询userDetail
	 * @param userId
	 * @return
	 */
	public UserVo getUserDetail(String userId) {
		if(StringUtils.isBlank(userId)){
			throw WebException.instance("userId为空");
		}
		//根据userId查询用户信息
		UserVo userDetail = memberQueryApi.findUserById(userId);
		if (userDetail == null) {
			throw WebException.instance("用户不存在");
		}
		return userDetail;
	}

	/**
	 * 管理页面 赎回
	 * @param underlyingAssetId
	 * @return
	 */
	@Override
	public void redeemAssetPool(String userId, String underlyingAssetId) {
		UserVo userVo = getUserDetail(userId);
		assetPoolTransactionService.redeemAssetPool(userVo, underlyingAssetId);
	}

	/**
	 * 管理页面 待入池
	 * @param underlyingAssetId
	 */
	@Override
	public void outAssetPoolAndKeepBind(String userId, String underlyingAssetId) {
		UserVo userVo = getUserDetail(userId);
		assetPoolTransactionService.outAssetPoolAndKeepBind(userVo, underlyingAssetId);
	}

	/**
	 * 管理页面 出池
	 * @param userId
	 * @param underlyingAssetId
	 */
	@Override
	public void outAssetPoolAndDelete(String userId, String underlyingAssetId) {
		UserVo userVo = getUserDetail(userId);
		assetPoolTransactionService.outAssetPoolAndDelete(userVo, underlyingAssetId);
	}

	/**
	 * 管理页面 历史还款
	 * @param underlyingAssetId
	 * @param userId
	 * @return
	 */
	@Override
	public ListResult<LoanPaidDTO> getLoanHistoryList(String underlyingAssetId, int page, int pageSize, String userId) {
		UserVo userVo = getUserDetail(userId);
		return assetPoolService.getLoanHistoryList(underlyingAssetId, page, pageSize, userVo);
	}

	/**
	 * 管理页面 还款计划
	 * @param userId
	 * @param underlyingAssetId
	 */
	@Override
	public List<LoanPlanDTO> getLoanPaidList(String userId, String underlyingAssetId) {
		UserVo userVo = getUserDetail(userId);
		return assetPoolService.getLoanPaidList(underlyingAssetId, userVo);
	}

	@Override
	public void enterAssetPool(String userId, List<String> underlyingAssetIds) {
		UserVo userVo = getUserDetail(userId);
		assetPoolTransactionService.enterAssetPool(userVo, underlyingAssetIds);
	}

}
