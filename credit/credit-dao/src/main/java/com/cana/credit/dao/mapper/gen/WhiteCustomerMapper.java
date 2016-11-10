package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.WhiteCustomer;
import com.cana.credit.dao.po.WhiteCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhiteCustomerMapper {
    int countByExample(WhiteCustomerExample example);

    int deleteByExample(WhiteCustomerExample example);

    int deleteByPrimaryKey(String id);

    int insert(WhiteCustomer record);

    int insertSelective(WhiteCustomer record);

    List<WhiteCustomer> selectByExample(WhiteCustomerExample example);

    WhiteCustomer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WhiteCustomer record, @Param("example") WhiteCustomerExample example);

    int updateByExample(@Param("record") WhiteCustomer record, @Param("example") WhiteCustomerExample example);

    int updateByPrimaryKeySelective(WhiteCustomer record);

    int updateByPrimaryKey(WhiteCustomer record);

    WhiteCustomer lockByPrimaryKey(@Param("id") String id);

    List<WhiteCustomer> lockByExample(WhiteCustomerExample example);
}