package com.cana.asset.service.transaction;

import java.util.Map;
import java.util.Set;

import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetAmountSummary;

/**
 * 基础资产
 * @author XuMeng
 */
public interface IUnderlyingAssetCalcTransactionService {

	/**
	 * 查询专项计划的当日应还、当日已还金额信息
	 * @return key 为专项计划ID
	 */
	public Map<String, UnderlyingAssetAmountSummary> queryFinanceAmountBySpecialProgramIds(Set<String> specialProgramIds);

	/**
	 * 查询基础资产的的当日应还、当日已还金额信息
	 * @return key 为基础资产ID
	 */
	public Map<String, UnderlyingAssetAmountSummary> queryFinanceAmountByUnderlyingAssetIds(Set<String> underlyingAssetIds);

	/**
	 * 查询专项计划的资产池规模接口。
	 * <p> 此接口实际统计的是关联专项计划的所有已入正式池的基础资产的融资余额总和
	 * @return 返回map类型的结果，key为专项计划ID，value为该专项计划的资产池规模数值
	 */
	public Map<String, Long> queryGrossBySpecialProgramIds(Set<String> specialProgramIds);

	/**
	 * 查询专项计划的资产池规模接口。
	 */
	public long queryGrossBySpecialProgramId(String specialProgramId);

}
