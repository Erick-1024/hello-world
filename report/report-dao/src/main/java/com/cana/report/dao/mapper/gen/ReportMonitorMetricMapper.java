package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportMonitorMetric;
import com.cana.report.dao.po.ReportMonitorMetricExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportMonitorMetricMapper {
    int countByExample(ReportMonitorMetricExample example);

    int deleteByExample(ReportMonitorMetricExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportMonitorMetric record);

    int insertSelective(ReportMonitorMetric record);

    List<ReportMonitorMetric> selectByExample(ReportMonitorMetricExample example);

    ReportMonitorMetric selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportMonitorMetric record, @Param("example") ReportMonitorMetricExample example);

    int updateByExample(@Param("record") ReportMonitorMetric record, @Param("example") ReportMonitorMetricExample example);

    int updateByPrimaryKeySelective(ReportMonitorMetric record);

    int updateByPrimaryKey(ReportMonitorMetric record);

    ReportMonitorMetric lockByPrimaryKey(@Param("id") String id);

    List<ReportMonitorMetric> lockByExample(ReportMonitorMetricExample example);
}