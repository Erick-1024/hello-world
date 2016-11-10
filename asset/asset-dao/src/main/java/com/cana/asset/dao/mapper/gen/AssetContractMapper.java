package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetContract;
import com.cana.asset.dao.po.AssetContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetContractMapper {
    int countByExample(AssetContractExample example);

    int deleteByExample(AssetContractExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetContract record);

    int insertSelective(AssetContract record);

    List<AssetContract> selectByExample(AssetContractExample example);

    AssetContract selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetContract record, @Param("example") AssetContractExample example);

    int updateByExample(@Param("record") AssetContract record, @Param("example") AssetContractExample example);

    int updateByPrimaryKeySelective(AssetContract record);

    int updateByPrimaryKey(AssetContract record);

    AssetContract lockByPrimaryKey(@Param("id") String id);

    List<AssetContract> lockByExample(AssetContractExample example);
}