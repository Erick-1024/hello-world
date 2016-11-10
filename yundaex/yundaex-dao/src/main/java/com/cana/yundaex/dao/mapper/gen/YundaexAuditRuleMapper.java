package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexAuditRule;
import com.cana.yundaex.dao.po.YundaexAuditRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexAuditRuleMapper {
    int countByExample(YundaexAuditRuleExample example);

    int deleteByExample(YundaexAuditRuleExample example);

    int deleteByPrimaryKey(Integer batchNo);

    int insert(YundaexAuditRule record);

    int insertSelective(YundaexAuditRule record);

    List<YundaexAuditRule> selectByExample(YundaexAuditRuleExample example);

    YundaexAuditRule selectByPrimaryKey(Integer batchNo);

    int updateByExampleSelective(@Param("record") YundaexAuditRule record, @Param("example") YundaexAuditRuleExample example);

    int updateByExample(@Param("record") YundaexAuditRule record, @Param("example") YundaexAuditRuleExample example);

    int updateByPrimaryKeySelective(YundaexAuditRule record);

    int updateByPrimaryKey(YundaexAuditRule record);
}