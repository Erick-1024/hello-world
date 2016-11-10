package com.cana.flight.finance.dao.mapper.gen;

import com.cana.flight.finance.dao.po.Repayment;
import com.cana.flight.finance.dao.po.RepaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentMapper {
    int countByExample(RepaymentExample example);

    int deleteByExample(RepaymentExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(Repayment record);

    int insertSelective(Repayment record);

    List<Repayment> selectByExample(RepaymentExample example);

    Repayment selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") Repayment record, @Param("example") RepaymentExample example);

    int updateByExample(@Param("record") Repayment record, @Param("example") RepaymentExample example);

    int updateByPrimaryKeySelective(Repayment record);

    int updateByPrimaryKey(Repayment record);
}