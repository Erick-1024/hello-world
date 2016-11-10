package com.cana.asset.service;

import java.util.List;
import java.util.Map;

import com.cana.asset.dao.po.SpecialProgram;
import com.cana.vbam.common.asset.dto.AssetInPoolDTO;
import com.cana.vbam.common.asset.dto.AssetpoolListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.common.PageList;

public interface IAssetPoolService {

	/**
	 * 资产池列表
	 * @param queryDTO
	 * @param userVo
	 * @return
	 */
	PageList<AssetpoolListDTO> getAssetpoolList(SpecialProgramQueryDTO queryDTO, UserVo userDetail);

	/**
	 * 查询资产池规模("立项"查专项计划融资余额,其它查专项计划"总规模")
	 * @param assetpoolListDTOs (专项计划编号id, 状态status 不能为空)
	 * @return
	 */
	public Map<String, Long> queryGrossBySpecialProgramIds(List<SpecialProgram> specialPrograms);

	/**
	 * 获取封包页面数据
	 * @param id
	 * @param userVo
	 * @return
	 */
	AssetpoolListDTO getAssetpoolPacket(String id, UserVo userVo);

	/**
	 * 资产池管理专项计划
	 * @param id
	 * @param status 
	 * @param userVo
	 * @return 
	 */
	public AssetpoolListDTO getAssetPoolDetails(String id, String status, UserVo userVo);

	/**
	 * 资产池管理入池信息
	 * @param queryDTO
	 * @param userVo
	 * @return
	 */
	public PageList<AssetInPoolDTO> getAssetpoolManageList(SpecialProgramQueryDTO queryDTO, UserVo userVo);

	/**
	 * 管理页面 历史还款
	 * @param underlyingAssetId
	 * @param userId
	 * @return
	 */
	public ListResult<LoanPaidDTO> getLoanHistoryList(String underlyingAssetId, int page, int pageSize, UserVo userVo);

	/**
	 * 管理页面 还款计划
	 * @param userId
	 * @param underlyingAssetId
	 * @param pageSize 
	 * @param page 
	 */
	public List<LoanPlanDTO> getLoanPaidList(String underlyingAssetId, UserVo userVo);

}
