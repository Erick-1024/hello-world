package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 资产池管理日志——资产日志
 * @author yihong.tang
 *
 */
public interface IUnderlyingAssetLogTransactionService {

	/**
	 * 插入基础资产操作日志
	 * <p> 插入操作日志需要保存当前操作用户的企业名称和用户名。
	 * <p> 需要调用方传入操作类型，包括 新建、入备选池、出备选池、入池、待入池、出池、赎回
	 * <p> 日志内容为上述操作后的最新的基础资产内容，因userVo不一定是保理商企业，所以日志中的factor_id字段的值取的是基础资产中的factor_id字段值。
	 */
	public void insertUnderlyingAssetLog(UnderlyingAsset underlyingAsset, UnderlyingAssetOperateTypeEnum operate, UserVo userVo);
	
	/**
	 * 批量插入基础资产操作日志
	 */
	public void insertUnderlyingAssetLog(List<UnderlyingAsset> underlyingAssets, UnderlyingAssetOperateTypeEnum operate, UserVo userVo);
	
	/**
	 * 查询当前客户能访问到的所有的基础资产操作日志
	 * <p>对于保理商查询，需要查询日志表中factor_id字段是保理商企业客户ID的所有日志。
	 * <p>对于非保理商查询，需要到ABS数据权限模块获取当前客户能访问的所有的专项计划编号列表，通过该编号列表查询所有的操作日志。
	 */
	public ListResult<UnderlyingAssetLogDTO> queryUnderlyingAssetLogs(UserVo userVo,UnderlyingAssetLogQuery query);
}
