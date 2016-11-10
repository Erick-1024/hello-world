package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetMarketDataReport;
import com.cana.asset.dao.po.AssetMarketDataReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetMarketDataReportMapper {
    int countByExample(AssetMarketDataReportExample example);

    int deleteByExample(AssetMarketDataReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetMarketDataReport record);

    int insertSelective(AssetMarketDataReport record);

    List<AssetMarketDataReport> selectByExample(AssetMarketDataReportExample example);

    AssetMarketDataReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetMarketDataReport record, @Param("example") AssetMarketDataReportExample example);

    int updateByExample(@Param("record") AssetMarketDataReport record, @Param("example") AssetMarketDataReportExample example);

    int updateByPrimaryKeySelective(AssetMarketDataReport record);

    int updateByPrimaryKey(AssetMarketDataReport record);
}