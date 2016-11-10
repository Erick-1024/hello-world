package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexCreditMonitor;
import com.cana.yundaex.dao.po.YundaexCreditMonitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexCreditMonitorMapper {
    int countByExample(YundaexCreditMonitorExample example);

    int deleteByExample(YundaexCreditMonitorExample example);

    int deleteByPrimaryKey(String id);

    int insert(YundaexCreditMonitor record);

    int insertSelective(YundaexCreditMonitor record);

    List<YundaexCreditMonitor> selectByExample(YundaexCreditMonitorExample example);

    YundaexCreditMonitor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YundaexCreditMonitor record, @Param("example") YundaexCreditMonitorExample example);

    int updateByExample(@Param("record") YundaexCreditMonitor record, @Param("example") YundaexCreditMonitorExample example);

    int updateByPrimaryKeySelective(YundaexCreditMonitor record);

    int updateByPrimaryKey(YundaexCreditMonitor record);
}