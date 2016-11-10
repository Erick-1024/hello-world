package com.cana.early.warning.dao.mapper.gen;

import com.cana.early.warning.dao.po.EarlywarningCustomer;
import com.cana.early.warning.dao.po.EarlywarningCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EarlywarningCustomerMapper {
    int countByExample(EarlywarningCustomerExample example);

    int deleteByExample(EarlywarningCustomerExample example);

    int deleteByPrimaryKey(String id);

    int insert(EarlywarningCustomer record);

    int insertSelective(EarlywarningCustomer record);

    List<EarlywarningCustomer> selectByExample(EarlywarningCustomerExample example);

    EarlywarningCustomer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EarlywarningCustomer record, @Param("example") EarlywarningCustomerExample example);

    int updateByExample(@Param("record") EarlywarningCustomer record, @Param("example") EarlywarningCustomerExample example);

    int updateByPrimaryKeySelective(EarlywarningCustomer record);

    int updateByPrimaryKey(EarlywarningCustomer record);

    EarlywarningCustomer lockByPrimaryKey(@Param("id") String id);

    List<EarlywarningCustomer> lockByExample(EarlywarningCustomerExample example);
}