package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.CustomerMas;
import com.cana.asset.dao.po.CustomerMasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMasMapper {
    int countByExample(CustomerMasExample example);

    int deleteByExample(CustomerMasExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerMas record);

    int insertSelective(CustomerMas record);

    List<CustomerMas> selectByExample(CustomerMasExample example);

    CustomerMas selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerMas record, @Param("example") CustomerMasExample example);

    int updateByExample(@Param("record") CustomerMas record, @Param("example") CustomerMasExample example);

    int updateByPrimaryKeySelective(CustomerMas record);

    int updateByPrimaryKey(CustomerMas record);
}