package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetMarketDataProduct;
import com.cana.asset.dao.po.AssetMarketDataProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetMarketDataProductMapper {
    int countByExample(AssetMarketDataProductExample example);

    int deleteByExample(AssetMarketDataProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetMarketDataProduct record);

    int insertSelective(AssetMarketDataProduct record);

    List<AssetMarketDataProduct> selectByExample(AssetMarketDataProductExample example);

    AssetMarketDataProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetMarketDataProduct record, @Param("example") AssetMarketDataProductExample example);

    int updateByExample(@Param("record") AssetMarketDataProduct record, @Param("example") AssetMarketDataProductExample example);

    int updateByPrimaryKeySelective(AssetMarketDataProduct record);

    int updateByPrimaryKey(AssetMarketDataProduct record);
}