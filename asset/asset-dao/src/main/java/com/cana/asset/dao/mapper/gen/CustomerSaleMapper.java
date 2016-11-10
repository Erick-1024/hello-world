package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.CustomerSale;
import com.cana.asset.dao.po.CustomerSaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerSaleMapper {
    int countByExample(CustomerSaleExample example);

    int deleteByExample(CustomerSaleExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerSale record);

    int insertSelective(CustomerSale record);

    List<CustomerSale> selectByExample(CustomerSaleExample example);

    CustomerSale selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerSale record, @Param("example") CustomerSaleExample example);

    int updateByExample(@Param("record") CustomerSale record, @Param("example") CustomerSaleExample example);

    int updateByPrimaryKeySelective(CustomerSale record);

    int updateByPrimaryKey(CustomerSale record);
}