package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetOperateLog;
import com.cana.asset.dao.po.AssetOperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetOperateLogMapper {
    int countByExample(AssetOperateLogExample example);

    int deleteByExample(AssetOperateLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetOperateLog record);

    int insertSelective(AssetOperateLog record);

    List<AssetOperateLog> selectByExampleWithBLOBs(AssetOperateLogExample example);

    List<AssetOperateLog> selectByExample(AssetOperateLogExample example);

    AssetOperateLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetOperateLog record, @Param("example") AssetOperateLogExample example);

    int updateByExampleWithBLOBs(@Param("record") AssetOperateLog record, @Param("example") AssetOperateLogExample example);

    int updateByExample(@Param("record") AssetOperateLog record, @Param("example") AssetOperateLogExample example);

    int updateByPrimaryKeySelective(AssetOperateLog record);

    int updateByPrimaryKeyWithBLOBs(AssetOperateLog record);

    int updateByPrimaryKey(AssetOperateLog record);
}