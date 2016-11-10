package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexStationOperation;
import com.cana.yundaex.dao.po.YundaexStationOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexStationOperationMapper {
    int countByExample(YundaexStationOperationExample example);

    int deleteByExample(YundaexStationOperationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YundaexStationOperation record);

    int insertSelective(YundaexStationOperation record);

    List<YundaexStationOperation> selectByExample(YundaexStationOperationExample example);

    YundaexStationOperation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YundaexStationOperation record, @Param("example") YundaexStationOperationExample example);

    int updateByExample(@Param("record") YundaexStationOperation record, @Param("example") YundaexStationOperationExample example);

    int updateByPrimaryKeySelective(YundaexStationOperation record);

    int updateByPrimaryKey(YundaexStationOperation record);
}