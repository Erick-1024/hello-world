package com.cana.asset.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.asset.dao.po.UnderlyingAsset;

public interface UnderlyingAssetSearchMapper {

	@Select("SELECT * FROM asset_underlying_asset where flags & #{andOperationNum} = #{resultNum} and asset_pool_status = #{assetPoolStatus} limit 0, #{pageSize}")
	public List<UnderlyingAsset> getUnderlyingAssetForIndexUpdateAll(@Param("andOperationNum") int andOperationNum, @Param("resultNum") int resultNum, @Param("assetPoolStatus") String assetPoolStatus, @Param("pageSize") int pageSize);
}
