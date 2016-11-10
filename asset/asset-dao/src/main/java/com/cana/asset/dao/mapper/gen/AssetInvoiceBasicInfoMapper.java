package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetInvoiceBasicInfo;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetInvoiceBasicInfoMapper {
    int countByExample(AssetInvoiceBasicInfoExample example);

    int deleteByExample(AssetInvoiceBasicInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetInvoiceBasicInfo record);

    int insertSelective(AssetInvoiceBasicInfo record);

    List<AssetInvoiceBasicInfo> selectByExample(AssetInvoiceBasicInfoExample example);

    AssetInvoiceBasicInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetInvoiceBasicInfo record, @Param("example") AssetInvoiceBasicInfoExample example);

    int updateByExample(@Param("record") AssetInvoiceBasicInfo record, @Param("example") AssetInvoiceBasicInfoExample example);

    int updateByPrimaryKeySelective(AssetInvoiceBasicInfo record);

    int updateByPrimaryKey(AssetInvoiceBasicInfo record);
}