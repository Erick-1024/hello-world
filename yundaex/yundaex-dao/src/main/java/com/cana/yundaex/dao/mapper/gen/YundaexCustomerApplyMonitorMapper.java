package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitor;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexCustomerApplyMonitorMapper {
    int countByExample(YundaexCustomerApplyMonitorExample example);

    int deleteByExample(YundaexCustomerApplyMonitorExample example);

    int deleteByPrimaryKey(String id);

    int insert(YundaexCustomerApplyMonitor record);

    int insertSelective(YundaexCustomerApplyMonitor record);

    List<YundaexCustomerApplyMonitor> selectByExample(YundaexCustomerApplyMonitorExample example);

    YundaexCustomerApplyMonitor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YundaexCustomerApplyMonitor record, @Param("example") YundaexCustomerApplyMonitorExample example);

    int updateByExample(@Param("record") YundaexCustomerApplyMonitor record, @Param("example") YundaexCustomerApplyMonitorExample example);

    int updateByPrimaryKeySelective(YundaexCustomerApplyMonitor record);

    int updateByPrimaryKey(YundaexCustomerApplyMonitor record);
}