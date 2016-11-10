package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.asset.dto.AssetPacketDTO;
import com.cana.vbam.common.member.vo.UserVo;

public interface IAssetPoolTransactionService {

	/**
	 * 封包
	 * @param packetDTO
	 */
	void packet(AssetPacketDTO packetDTO, UserVo userVo);

	/**
	 * 管理页面 赎回
	 * @param underlyingAssetId
	 * @return
	 */
	public void redeemAssetPool(UserVo userVo, String underlyingAssetId);

	/**
	 * 管理页面 待入池
	 * @param underlyingAssetId
	 */
	public void outAssetPoolAndKeepBind(UserVo userVo, String underlyingAssetId);

	/**
	 * 管理页面 出池
	 * @param userId
	 * @param underlyingAssetId
	 */
	public void outAssetPoolAndDelete(UserVo userVo, String underlyingAssetId);
	
	/**
	 * 入池
	 * @param userId
	 * @param underlyingAssetIds
	 */
	public void enterAssetPool(UserVo userVo, List<String> underlyingAssetIds);

}
