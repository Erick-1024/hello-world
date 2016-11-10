package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportMonitorData;
import com.cana.report.dao.po.ReportMonitorDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportMonitorDataMapper {
    int countByExample(ReportMonitorDataExample example);

    int deleteByExample(ReportMonitorDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportMonitorData record);

    int insertSelective(ReportMonitorData record);

    List<ReportMonitorData> selectByExample(ReportMonitorDataExample example);

    ReportMonitorData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportMonitorData record, @Param("example") ReportMonitorDataExample example);

    int updateByExample(@Param("record") ReportMonitorData record, @Param("example") ReportMonitorDataExample example);

    int updateByPrimaryKeySelective(ReportMonitorData record);

    int updateByPrimaryKey(ReportMonitorData record);

    ReportMonitorData lockByPrimaryKey(@Param("id") String id);

    List<ReportMonitorData> lockByExample(ReportMonitorDataExample example);
}