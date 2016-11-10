package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.dto.AssetInPoolDTO;
import com.cana.vbam.common.asset.dto.AssetPacketDTO;
import com.cana.vbam.common.asset.dto.AssetpoolListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.travelzen.framework.common.PageList;

public interface IAssetPoolApi {

	/**
	 * 资产池列表
	 * @param queryDTO
	 * @param userSessionDTO
	 * @return
	 */
	PageList<AssetpoolListDTO> getAssetpoolList(SpecialProgramQueryDTO queryDTO, String userId);

	/**
	 * 获取封包页面数据
	 * @param id
	 * @param userSessionDTO
	 * @return
	 */
	AssetpoolListDTO getAssetpoolPacket(String id, String userId);

	/**
	 * 封包
	 * @param packetDTO
	 */
	void packet(AssetPacketDTO packetDTO, String userId);
	
	/** 资产池专项计划
	 * @param id
	 * @param status 
	 * @param userSessionDTO
	 * @return 
	 */
 	public AssetpoolListDTO getAssetPoolDetails(String id, String status, String userId);

 	/**
	 * 管理页面列表
	 * @param queryDTO
	 * @param model
	 * @return
	 */
	public PageList<AssetInPoolDTO> getAssetpoolManageList(SpecialProgramQueryDTO queryDTO, String userId);

	/**
	 * 管理页面 赎回
	 * @param underlyingAssetId
	 * @return
	 */
	public void redeemAssetPool(String userId, String underlyingAssetId);

	/**
	 * 管理页面 待入池
	 * @param underlyingAssetId
	 */
	public void outAssetPoolAndKeepBind(String userId, String underlyingAssetId);

	/**
	 * 管理页面 出池
	 * @param userId
	 * @param underlyingAssetId
	 */
	public void outAssetPoolAndDelete(String userId, String underlyingAssetId);

	/**
	 * 管理页面 历史还款
	 * @param underlyingAssetId
	 * @param userId
	 * @return
	 */
	public ListResult<LoanPaidDTO> getLoanHistoryList(String underlyingAssetId, int page, int pageSize, String userId);

	/**
	 * 管理页面 还款计划
	 * @param userId
	 * @param underlyingAssetId
	 */
	public List<LoanPlanDTO> getLoanPaidList(String userId, String underlyingAssetId);
	
	/**
	 * 入池
	 * @param userId
	 * @param underlyingAssetIds
	 */
	public void enterAssetPool(String userId, List<String> underlyingAssetIds);


}
