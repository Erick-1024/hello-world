package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetUnderlyingAssetLog;
import com.cana.asset.dao.po.AssetUnderlyingAssetLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetUnderlyingAssetLogMapper {
    int countByExample(AssetUnderlyingAssetLogExample example);

    int deleteByExample(AssetUnderlyingAssetLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetUnderlyingAssetLog record);

    int insertSelective(AssetUnderlyingAssetLog record);

    List<AssetUnderlyingAssetLog> selectByExample(AssetUnderlyingAssetLogExample example);

    AssetUnderlyingAssetLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetUnderlyingAssetLog record, @Param("example") AssetUnderlyingAssetLogExample example);

    int updateByExample(@Param("record") AssetUnderlyingAssetLog record, @Param("example") AssetUnderlyingAssetLogExample example);

    int updateByPrimaryKeySelective(AssetUnderlyingAssetLog record);

    int updateByPrimaryKey(AssetUnderlyingAssetLog record);
}