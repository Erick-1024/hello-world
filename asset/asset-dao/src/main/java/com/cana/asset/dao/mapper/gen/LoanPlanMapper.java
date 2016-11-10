package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.LoanPlan;
import com.cana.asset.dao.po.LoanPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoanPlanMapper {
    int countByExample(LoanPlanExample example);

    int deleteByExample(LoanPlanExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoanPlan record);

    int insertSelective(LoanPlan record);

    List<LoanPlan> selectByExample(LoanPlanExample example);

    LoanPlan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoanPlan record, @Param("example") LoanPlanExample example);

    int updateByExample(@Param("record") LoanPlan record, @Param("example") LoanPlanExample example);

    int updateByPrimaryKeySelective(LoanPlan record);

    int updateByPrimaryKey(LoanPlan record);

    LoanPlan lockByPrimaryKey(@Param("id") String id);

    List<LoanPlan> lockByExample(LoanPlanExample example);
}