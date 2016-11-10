package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.OutCustomer;
import com.cana.credit.dao.po.OutCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutCustomerMapper {
    int countByExample(OutCustomerExample example);

    int deleteByExample(OutCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OutCustomer record);

    int insertSelective(OutCustomer record);

    List<OutCustomer> selectByExample(OutCustomerExample example);

    OutCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OutCustomer record, @Param("example") OutCustomerExample example);

    int updateByExample(@Param("record") OutCustomer record, @Param("example") OutCustomerExample example);

    int updateByPrimaryKeySelective(OutCustomer record);

    int updateByPrimaryKey(OutCustomer record);

    OutCustomer lockByPrimaryKey(@Param("id") Integer id);

    List<OutCustomer> lockByExample(OutCustomerExample example);
}