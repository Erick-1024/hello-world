package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetMarketDataProject;
import com.cana.asset.dao.po.AssetMarketDataProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetMarketDataProjectMapper {
    int countByExample(AssetMarketDataProjectExample example);

    int deleteByExample(AssetMarketDataProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetMarketDataProject record);

    int insertSelective(AssetMarketDataProject record);

    List<AssetMarketDataProject> selectByExample(AssetMarketDataProjectExample example);

    AssetMarketDataProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetMarketDataProject record, @Param("example") AssetMarketDataProjectExample example);

    int updateByExample(@Param("record") AssetMarketDataProject record, @Param("example") AssetMarketDataProjectExample example);

    int updateByPrimaryKeySelective(AssetMarketDataProject record);

    int updateByPrimaryKey(AssetMarketDataProject record);
}