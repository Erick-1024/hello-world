package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexOutCustomerMapper {
    int countByExample(YundaexOutCustomerExample example);

    int deleteByExample(YundaexOutCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexOutCustomer record);

    int insertSelective(YundaexOutCustomer record);

    List<YundaexOutCustomer> selectByExample(YundaexOutCustomerExample example);

    YundaexOutCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexOutCustomer record, @Param("example") YundaexOutCustomerExample example);

    int updateByExample(@Param("record") YundaexOutCustomer record, @Param("example") YundaexOutCustomerExample example);

    int updateByPrimaryKeySelective(YundaexOutCustomer record);

    int updateByPrimaryKey(YundaexOutCustomer record);
}