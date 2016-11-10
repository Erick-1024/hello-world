package com.cana.early.warning.dao.mapper.gen;

import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EarlywarningEventMapper {
    int countByExample(EarlywarningEventExample example);

    int deleteByExample(EarlywarningEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(EarlywarningEvent record);

    int insertSelective(EarlywarningEvent record);

    List<EarlywarningEvent> selectByExample(EarlywarningEventExample example);

    EarlywarningEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EarlywarningEvent record, @Param("example") EarlywarningEventExample example);

    int updateByExample(@Param("record") EarlywarningEvent record, @Param("example") EarlywarningEventExample example);

    int updateByPrimaryKeySelective(EarlywarningEvent record);

    int updateByPrimaryKey(EarlywarningEvent record);

    EarlywarningEvent lockByPrimaryKey(@Param("id") String id);

    List<EarlywarningEvent> lockByExample(EarlywarningEventExample example);
}