package com.cana.test.dao2.mapper.gen;

import com.cana.test.dao2.po.Properties2;
import com.cana.test.dao2.po.Properties2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Properties2Mapper {
    int countByExample(Properties2Example example);

    int deleteByExample(Properties2Example example);

    int deleteByPrimaryKey(String id);

    int insert(Properties2 record);

    int insertSelective(Properties2 record);

    List<Properties2> selectByExample(Properties2Example example);

    Properties2 selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Properties2 record, @Param("example") Properties2Example example);

    int updateByExample(@Param("record") Properties2 record, @Param("example") Properties2Example example);

    int updateByPrimaryKeySelective(Properties2 record);

    int updateByPrimaryKey(Properties2 record);
}