package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.AccessRule;
import com.cana.credit.dao.po.AccessRuleExample;
import com.cana.credit.dao.po.AccessRuleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessRuleMapper {
    int countByExample(AccessRuleExample example);

    int deleteByExample(AccessRuleExample example);

    int deleteByPrimaryKey(AccessRuleKey key);

    int insert(AccessRule record);

    int insertSelective(AccessRule record);

    List<AccessRule> selectByExample(AccessRuleExample example);

    AccessRule selectByPrimaryKey(AccessRuleKey key);

    int updateByExampleSelective(@Param("record") AccessRule record, @Param("example") AccessRuleExample example);

    int updateByExample(@Param("record") AccessRule record, @Param("example") AccessRuleExample example);

    int updateByPrimaryKeySelective(AccessRule record);

    int updateByPrimaryKey(AccessRule record);

    AccessRule lockByPrimaryKey(@Param("batchNo") Integer batchNo, @Param("fitObject") String fitObject);

    List<AccessRule> lockByExample(AccessRuleExample example);
}