package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentRuleMapper {
    int countByExample(RepaymentRuleExample example);

    int deleteByExample(RepaymentRuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentRule record);

    int insertSelective(RepaymentRule record);

    List<RepaymentRule> selectByExample(RepaymentRuleExample example);

    RepaymentRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentRule record, @Param("example") RepaymentRuleExample example);

    int updateByExample(@Param("record") RepaymentRule record, @Param("example") RepaymentRuleExample example);

    int updateByPrimaryKeySelective(RepaymentRule record);

    int updateByPrimaryKey(RepaymentRule record);

    RepaymentRule lockByPrimaryKey(@Param("id") String id);

    List<RepaymentRule> lockByExample(RepaymentRuleExample example);
}