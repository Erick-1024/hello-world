package com.cana.early.warning.dao.mapper.gen;

import com.cana.early.warning.dao.po.EarlywarningLevelChangeHistory;
import com.cana.early.warning.dao.po.EarlywarningLevelChangeHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EarlywarningLevelChangeHistoryMapper {
    int countByExample(EarlywarningLevelChangeHistoryExample example);

    int deleteByExample(EarlywarningLevelChangeHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(EarlywarningLevelChangeHistory record);

    int insertSelective(EarlywarningLevelChangeHistory record);

    List<EarlywarningLevelChangeHistory> selectByExample(EarlywarningLevelChangeHistoryExample example);

    EarlywarningLevelChangeHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EarlywarningLevelChangeHistory record, @Param("example") EarlywarningLevelChangeHistoryExample example);

    int updateByExample(@Param("record") EarlywarningLevelChangeHistory record, @Param("example") EarlywarningLevelChangeHistoryExample example);

    int updateByPrimaryKeySelective(EarlywarningLevelChangeHistory record);

    int updateByPrimaryKey(EarlywarningLevelChangeHistory record);

    EarlywarningLevelChangeHistory lockByPrimaryKey(@Param("id") String id);

    List<EarlywarningLevelChangeHistory> lockByExample(EarlywarningLevelChangeHistoryExample example);
}