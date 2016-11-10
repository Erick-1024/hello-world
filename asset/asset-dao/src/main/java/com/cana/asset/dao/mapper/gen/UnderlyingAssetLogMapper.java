package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.UnderlyingAssetLog;
import com.cana.asset.dao.po.UnderlyingAssetLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnderlyingAssetLogMapper {
    int countByExample(UnderlyingAssetLogExample example);

    int deleteByExample(UnderlyingAssetLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(UnderlyingAssetLog record);

    int insertSelective(UnderlyingAssetLog record);

    List<UnderlyingAssetLog> selectByExample(UnderlyingAssetLogExample example);

    UnderlyingAssetLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UnderlyingAssetLog record, @Param("example") UnderlyingAssetLogExample example);

    int updateByExample(@Param("record") UnderlyingAssetLog record, @Param("example") UnderlyingAssetLogExample example);

    int updateByPrimaryKeySelective(UnderlyingAssetLog record);

    int updateByPrimaryKey(UnderlyingAssetLog record);
}