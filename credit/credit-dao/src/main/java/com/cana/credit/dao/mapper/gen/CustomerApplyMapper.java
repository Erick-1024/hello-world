package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.CustomerApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerApplyMapper {
    int countByExample(CustomerApplyExample example);

    int deleteByExample(CustomerApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerApply record);

    int insertSelective(CustomerApply record);

    List<CustomerApply> selectByExampleWithBLOBs(CustomerApplyExample example);

    List<CustomerApply> selectByExample(CustomerApplyExample example);

    CustomerApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerApply record, @Param("example") CustomerApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") CustomerApply record, @Param("example") CustomerApplyExample example);

    int updateByExample(@Param("record") CustomerApply record, @Param("example") CustomerApplyExample example);

    int updateByPrimaryKeySelective(CustomerApply record);

    int updateByPrimaryKeyWithBLOBs(CustomerApply record);

    int updateByPrimaryKey(CustomerApply record);

    CustomerApply lockByPrimaryKey(@Param("id") String id);

    List<CustomerApply> lockByExample(CustomerApplyExample example);
}