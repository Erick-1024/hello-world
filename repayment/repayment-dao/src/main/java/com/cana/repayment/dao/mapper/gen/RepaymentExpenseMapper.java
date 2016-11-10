package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentExpenseMapper {
    int countByExample(RepaymentExpenseExample example);

    int deleteByExample(RepaymentExpenseExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentExpense record);

    int insertSelective(RepaymentExpense record);

    List<RepaymentExpense> selectByExample(RepaymentExpenseExample example);

    RepaymentExpense selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentExpense record, @Param("example") RepaymentExpenseExample example);

    int updateByExample(@Param("record") RepaymentExpense record, @Param("example") RepaymentExpenseExample example);

    int updateByPrimaryKeySelective(RepaymentExpense record);

    int updateByPrimaryKey(RepaymentExpense record);

    RepaymentExpense lockByPrimaryKey(@Param("id") String id);

    List<RepaymentExpense> lockByExample(RepaymentExpenseExample example);
}