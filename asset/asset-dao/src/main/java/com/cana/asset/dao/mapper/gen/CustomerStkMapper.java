package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.CustomerStk;
import com.cana.asset.dao.po.CustomerStkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerStkMapper {
    int countByExample(CustomerStkExample example);

    int deleteByExample(CustomerStkExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerStk record);

    int insertSelective(CustomerStk record);

    List<CustomerStk> selectByExample(CustomerStkExample example);

    CustomerStk selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerStk record, @Param("example") CustomerStkExample example);

    int updateByExample(@Param("record") CustomerStk record, @Param("example") CustomerStkExample example);

    int updateByPrimaryKeySelective(CustomerStk record);

    int updateByPrimaryKey(CustomerStk record);
}