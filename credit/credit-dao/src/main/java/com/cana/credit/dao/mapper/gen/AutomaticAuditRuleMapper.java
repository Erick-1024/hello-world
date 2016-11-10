package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.AutomaticAuditRule;
import com.cana.credit.dao.po.AutomaticAuditRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AutomaticAuditRuleMapper {
    int countByExample(AutomaticAuditRuleExample example);

    int deleteByExample(AutomaticAuditRuleExample example);

    int deleteByPrimaryKey(Integer batchNo);

    int insert(AutomaticAuditRule record);

    int insertSelective(AutomaticAuditRule record);

    List<AutomaticAuditRule> selectByExample(AutomaticAuditRuleExample example);

    int updateByExampleSelective(@Param("record") AutomaticAuditRule record, @Param("example") AutomaticAuditRuleExample example);

    int updateByExample(@Param("record") AutomaticAuditRule record, @Param("example") AutomaticAuditRuleExample example);
}