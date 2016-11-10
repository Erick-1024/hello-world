package com.cana.test.dao1.mapper.gen;

import com.cana.test.dao1.po.Properties1;
import com.cana.test.dao1.po.Properties1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Properties1Mapper {
    int countByExample(Properties1Example example);

    int deleteByExample(Properties1Example example);

    int deleteByPrimaryKey(String id);

    int insert(Properties1 record);

    int insertSelective(Properties1 record);

    List<Properties1> selectByExample(Properties1Example example);

    Properties1 selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Properties1 record, @Param("example") Properties1Example example);

    int updateByExample(@Param("record") Properties1 record, @Param("example") Properties1Example example);

    int updateByPrimaryKeySelective(Properties1 record);

    int updateByPrimaryKey(Properties1 record);
}