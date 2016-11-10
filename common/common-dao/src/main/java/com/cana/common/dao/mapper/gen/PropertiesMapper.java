package com.cana.common.dao.mapper.gen;

import com.cana.common.dao.po.Properties;
import com.cana.common.dao.po.PropertiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PropertiesMapper {
    int countByExample(PropertiesExample example);

    int deleteByExample(PropertiesExample example);

    int deleteByPrimaryKey(String name);

    int insert(Properties record);

    int insertSelective(Properties record);

    List<Properties> selectByExampleWithBLOBs(PropertiesExample example);

    List<Properties> selectByExample(PropertiesExample example);

    Properties selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") Properties record, @Param("example") PropertiesExample example);

    int updateByExampleWithBLOBs(@Param("record") Properties record, @Param("example") PropertiesExample example);

    int updateByExample(@Param("record") Properties record, @Param("example") PropertiesExample example);

    int updateByPrimaryKeySelective(Properties record);

    int updateByPrimaryKeyWithBLOBs(Properties record);

    int updateByPrimaryKey(Properties record);

    Properties lockByPrimaryKey(@Param("name") String name);

    List<Properties> lockByExample(PropertiesExample example);
}