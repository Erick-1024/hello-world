package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.WhiteCustomerRule;
import com.cana.credit.dao.po.WhiteCustomerRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhiteCustomerRuleMapper {
    int countByExample(WhiteCustomerRuleExample example);

    int deleteByExample(WhiteCustomerRuleExample example);

    int deleteByPrimaryKey(Integer batchNo);

    int insert(WhiteCustomerRule record);

    int insertSelective(WhiteCustomerRule record);

    List<WhiteCustomerRule> selectByExample(WhiteCustomerRuleExample example);

    WhiteCustomerRule selectByPrimaryKey(Integer batchNo);

    int updateByExampleSelective(@Param("record") WhiteCustomerRule record, @Param("example") WhiteCustomerRuleExample example);

    int updateByExample(@Param("record") WhiteCustomerRule record, @Param("example") WhiteCustomerRuleExample example);

    int updateByPrimaryKeySelective(WhiteCustomerRule record);

    int updateByPrimaryKey(WhiteCustomerRule record);

    WhiteCustomerRule lockByPrimaryKey(@Param("batchNo") Integer batchNo);

    List<WhiteCustomerRule> lockByExample(WhiteCustomerRuleExample example);
}