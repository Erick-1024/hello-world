package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportFundMonthly;
import com.cana.report.dao.po.ReportFundMonthlyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportFundMonthlyMapper {
    int countByExample(ReportFundMonthlyExample example);

    int deleteByExample(ReportFundMonthlyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportFundMonthly record);

    int insertSelective(ReportFundMonthly record);

    List<ReportFundMonthly> selectByExample(ReportFundMonthlyExample example);

    ReportFundMonthly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportFundMonthly record, @Param("example") ReportFundMonthlyExample example);

    int updateByExample(@Param("record") ReportFundMonthly record, @Param("example") ReportFundMonthlyExample example);

    int updateByPrimaryKeySelective(ReportFundMonthly record);

    int updateByPrimaryKey(ReportFundMonthly record);

    ReportFundMonthly lockByPrimaryKey(@Param("id") String id);

    List<ReportFundMonthly> lockByExample(ReportFundMonthlyExample example);
}