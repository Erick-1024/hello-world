package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexCustomerApplyMapper {
    int countByExample(YundaexCustomerApplyExample example);

    int deleteByExample(YundaexCustomerApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(YundaexCustomerApply record);

    int insertSelective(YundaexCustomerApply record);

    List<YundaexCustomerApply> selectByExample(YundaexCustomerApplyExample example);

    YundaexCustomerApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YundaexCustomerApply record, @Param("example") YundaexCustomerApplyExample example);

    int updateByExample(@Param("record") YundaexCustomerApply record, @Param("example") YundaexCustomerApplyExample example);

    int updateByPrimaryKeySelective(YundaexCustomerApply record);

    int updateByPrimaryKey(YundaexCustomerApply record);
}