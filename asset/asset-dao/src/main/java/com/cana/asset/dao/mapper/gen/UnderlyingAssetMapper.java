package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnderlyingAssetMapper {
    int countByExample(UnderlyingAssetExample example);

    int deleteByExample(UnderlyingAssetExample example);

    int deleteByPrimaryKey(String id);

    int insert(UnderlyingAsset record);

    int insertSelective(UnderlyingAsset record);

    List<UnderlyingAsset> selectByExample(UnderlyingAssetExample example);

    UnderlyingAsset selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UnderlyingAsset record, @Param("example") UnderlyingAssetExample example);

    int updateByExample(@Param("record") UnderlyingAsset record, @Param("example") UnderlyingAssetExample example);

    int updateByPrimaryKeySelective(UnderlyingAsset record);

    int updateByPrimaryKey(UnderlyingAsset record);

    UnderlyingAsset lockByPrimaryKey(@Param("id") String id);

    List<UnderlyingAsset> lockByExample(UnderlyingAssetExample example);
}