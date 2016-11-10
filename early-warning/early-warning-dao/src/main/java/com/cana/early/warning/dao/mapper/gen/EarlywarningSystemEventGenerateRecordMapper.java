package com.cana.early.warning.dao.mapper.gen;

import com.cana.early.warning.dao.po.EarlywarningSystemEventGenerateRecord;
import com.cana.early.warning.dao.po.EarlywarningSystemEventGenerateRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EarlywarningSystemEventGenerateRecordMapper {
    int countByExample(EarlywarningSystemEventGenerateRecordExample example);

    int deleteByExample(EarlywarningSystemEventGenerateRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(EarlywarningSystemEventGenerateRecord record);

    int insertSelective(EarlywarningSystemEventGenerateRecord record);

    List<EarlywarningSystemEventGenerateRecord> selectByExample(EarlywarningSystemEventGenerateRecordExample example);

    EarlywarningSystemEventGenerateRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EarlywarningSystemEventGenerateRecord record, @Param("example") EarlywarningSystemEventGenerateRecordExample example);

    int updateByExample(@Param("record") EarlywarningSystemEventGenerateRecord record, @Param("example") EarlywarningSystemEventGenerateRecordExample example);

    int updateByPrimaryKeySelective(EarlywarningSystemEventGenerateRecord record);

    int updateByPrimaryKey(EarlywarningSystemEventGenerateRecord record);

    EarlywarningSystemEventGenerateRecord lockByPrimaryKey(@Param("id") String id);

    List<EarlywarningSystemEventGenerateRecord> lockByExample(EarlywarningSystemEventGenerateRecordExample example);
}