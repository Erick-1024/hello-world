package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpenseMapper {
    int countByExample(ExpenseExample example);

    int deleteByExample(ExpenseExample example);

    int deleteByPrimaryKey(String id);

    int insert(Expense record);

    int insertSelective(Expense record);

    List<Expense> selectByExample(ExpenseExample example);

    Expense selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Expense record, @Param("example") ExpenseExample example);

    int updateByExample(@Param("record") Expense record, @Param("example") ExpenseExample example);

    int updateByPrimaryKeySelective(Expense record);

    int updateByPrimaryKey(Expense record);

    Expense lockByPrimaryKey(@Param("id") String id);

    List<Expense> lockByExample(ExpenseExample example);
}