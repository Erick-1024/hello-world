package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.LoanPaid;
import com.cana.asset.dao.po.LoanPaidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoanPaidMapper {
    int countByExample(LoanPaidExample example);

    int deleteByExample(LoanPaidExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoanPaid record);

    int insertSelective(LoanPaid record);

    List<LoanPaid> selectByExample(LoanPaidExample example);

    LoanPaid selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoanPaid record, @Param("example") LoanPaidExample example);

    int updateByExample(@Param("record") LoanPaid record, @Param("example") LoanPaidExample example);

    int updateByPrimaryKeySelective(LoanPaid record);

    int updateByPrimaryKey(LoanPaid record);

    LoanPaid lockByPrimaryKey(@Param("id") String id);

    List<LoanPaid> lockByExample(LoanPaidExample example);
}