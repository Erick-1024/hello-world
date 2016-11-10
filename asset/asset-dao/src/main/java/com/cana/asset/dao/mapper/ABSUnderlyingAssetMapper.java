package com.cana.asset.dao.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cana.asset.dao.custom.po.SpecialProgramGrossResult;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetAmountSummary;
import com.cana.vbam.common.member.vo.UserVo;

public interface ABSUnderlyingAssetMapper {

	/**
	 * 根据专项计划查询正式池的基础资产的资产池规模
	 */
    public List<SpecialProgramGrossResult> queryGrossBySpecialProgramIds(@Param("specialProgramIds") Set<String> specialProgramIds);

    /**
     * 根据专项计划ID查询当日应还金额信息
     * @param specialProgramIds
     * @param date10 当前日期
     */
    public List<UnderlyingAssetAmountSummary> queryAccountAmountBySpecialProgramIds(
    		@Param("specialProgramIds") Set<String> specialProgramIds, @Param("date") String date10);

    /**
     * 根据专项计划ID查询当日已还金额信息
     * @param specialProgramIds
     * @param date10 当前日期
     */
    public List<UnderlyingAssetAmountSummary> queryPaidAmountBySpecialProgramIds(
    		@Param("specialProgramIds") Set<String> specialProgramIds, @Param("date") String date10);


    /**
     * 根据基础资产ID查询当日应还金额信息
     * @param underlyingAssetIds
     * @param date10 当前日期
     */
    public List<UnderlyingAssetAmountSummary> queryAccountAmountByUnderlyingAssetIds(
    		@Param("underlyingAssetIds") Set<String> underlyingAssetIds, @Param("date") String date10);

    /**
     * 根据基础资产ID查询当日已还金额信息
     * @param underlyingAssetIds
     * @param date10 当前日期
     */
    public List<UnderlyingAssetAmountSummary> queryPaidAmountByUnderlyingAssetIds(
    		@Param("underlyingAssetIds") Set<String> underlyingAssetIds, @Param("date") String date10);

    public List<LoanInfo> queryFactorLoanForUnderlyingAsset(
    		QueryFactorLoanForUnderlyingAssetRequest request);

    public int countFactorLoanForUnderlyingAsset(
    		QueryFactorLoanForUnderlyingAssetRequest request);
}
