package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetInvoiceInfoMapper {
    int countByExample(AssetInvoiceInfoExample example);

    int deleteByExample(AssetInvoiceInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssetInvoiceInfo record);

    int insertSelective(AssetInvoiceInfo record);

    List<AssetInvoiceInfo> selectByExample(AssetInvoiceInfoExample example);

    AssetInvoiceInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssetInvoiceInfo record, @Param("example") AssetInvoiceInfoExample example);

    int updateByExample(@Param("record") AssetInvoiceInfo record, @Param("example") AssetInvoiceInfoExample example);

    int updateByPrimaryKeySelective(AssetInvoiceInfo record);

    int updateByPrimaryKey(AssetInvoiceInfo record);
}