package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.CustomerPurchase;
import com.cana.asset.dao.po.CustomerPurchaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerPurchaseMapper {
    int countByExample(CustomerPurchaseExample example);

    int deleteByExample(CustomerPurchaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerPurchase record);

    int insertSelective(CustomerPurchase record);

    List<CustomerPurchase> selectByExample(CustomerPurchaseExample example);

    CustomerPurchase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerPurchase record, @Param("example") CustomerPurchaseExample example);

    int updateByExample(@Param("record") CustomerPurchase record, @Param("example") CustomerPurchaseExample example);

    int updateByPrimaryKeySelective(CustomerPurchase record);

    int updateByPrimaryKey(CustomerPurchase record);
}