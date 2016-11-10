package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexTstationInfoRecord;
import com.cana.yundaex.dao.po.YundaexTstationInfoRecordExample;
import com.cana.yundaex.dao.po.YundaexTstationInfoRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexTstationInfoRecordMapper {
    int countByExample(YundaexTstationInfoRecordExample example);

    int deleteByExample(YundaexTstationInfoRecordExample example);

    int deleteByPrimaryKey(YundaexTstationInfoRecordKey key);

    int insert(YundaexTstationInfoRecord record);

    int insertSelective(YundaexTstationInfoRecord record);

    List<YundaexTstationInfoRecord> selectByExample(YundaexTstationInfoRecordExample example);

    YundaexTstationInfoRecord selectByPrimaryKey(YundaexTstationInfoRecordKey key);

    int updateByExampleSelective(@Param("record") YundaexTstationInfoRecord record, @Param("example") YundaexTstationInfoRecordExample example);

    int updateByExample(@Param("record") YundaexTstationInfoRecord record, @Param("example") YundaexTstationInfoRecordExample example);

    int updateByPrimaryKeySelective(YundaexTstationInfoRecord record);

    int updateByPrimaryKey(YundaexTstationInfoRecord record);
}