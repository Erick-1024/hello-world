package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.CustomerMag;
import com.cana.asset.dao.po.CustomerMagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMagMapper {
    int countByExample(CustomerMagExample example);

    int deleteByExample(CustomerMagExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerMag record);

    int insertSelective(CustomerMag record);

    List<CustomerMag> selectByExample(CustomerMagExample example);

    CustomerMag selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerMag record, @Param("example") CustomerMagExample example);

    int updateByExample(@Param("record") CustomerMag record, @Param("example") CustomerMagExample example);

    int updateByPrimaryKeySelective(CustomerMag record);

    int updateByPrimaryKey(CustomerMag record);
}