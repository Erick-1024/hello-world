package com.cana.asset.api;

import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogQuery;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 资产池管理日志
 * @author yihong.tang
 *
 */
public interface IABSLogApi {

	/**
	 * 查询当前客户能访问到的所有的基础资产操作日志
	 * <p>需要到ABS数据权限模块获取当前客户能访问的所有的专项计划编号列表，通过该编号列表查询所有的操作日志。
	 */
	public ListResult<SpecialProgramLogDTO> querySpecialProgramLogs(UserVo userVo,SpecialProgramLogQuery query);
	
	/**
	 * 查询当前客户能访问到的所有的专项计划操作日志
	 * <p>对于保理商查询，需要查询日志表中factor_id字段是保理商企业客户ID的所有日志。
	 * <p>对于非保理商查询，需要到ABS数据权限模块获取当前客户能访问的所有的专项计划编号列表，通过该编号列表查询所有的操作日志。
	 */
	public ListResult<UnderlyingAssetLogDTO> queryUnderlyingAssetLogs(UserVo userVo,UnderlyingAssetLogQuery query);
}
