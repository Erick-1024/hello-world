package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentPlanMapper {
    int countByExample(RepaymentPlanExample example);

    int deleteByExample(RepaymentPlanExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentPlan record);

    int insertSelective(RepaymentPlan record);

    List<RepaymentPlan> selectByExample(RepaymentPlanExample example);

    RepaymentPlan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentPlan record, @Param("example") RepaymentPlanExample example);

    int updateByExample(@Param("record") RepaymentPlan record, @Param("example") RepaymentPlanExample example);

    int updateByPrimaryKeySelective(RepaymentPlan record);

    int updateByPrimaryKey(RepaymentPlan record);

    RepaymentPlan lockByPrimaryKey(@Param("id") String id);

    List<RepaymentPlan> lockByExample(RepaymentPlanExample example);
}