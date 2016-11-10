package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportFactorFinanceDayMapper {
    int countByExample(ReportFactorFinanceDayExample example);

    int deleteByExample(ReportFactorFinanceDayExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportFactorFinanceDay record);

    int insertSelective(ReportFactorFinanceDay record);

    List<ReportFactorFinanceDay> selectByExample(ReportFactorFinanceDayExample example);

    ReportFactorFinanceDay selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportFactorFinanceDay record, @Param("example") ReportFactorFinanceDayExample example);

    int updateByExample(@Param("record") ReportFactorFinanceDay record, @Param("example") ReportFactorFinanceDayExample example);

    int updateByPrimaryKeySelective(ReportFactorFinanceDay record);

    int updateByPrimaryKey(ReportFactorFinanceDay record);

    ReportFactorFinanceDay lockByPrimaryKey(@Param("id") String id);

    List<ReportFactorFinanceDay> lockByExample(ReportFactorFinanceDayExample example);
}