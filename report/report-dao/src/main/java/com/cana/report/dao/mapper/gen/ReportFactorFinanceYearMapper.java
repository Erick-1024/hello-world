package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.report.dao.po.ReportFactorFinanceYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportFactorFinanceYearMapper {
    int countByExample(ReportFactorFinanceYearExample example);

    int deleteByExample(ReportFactorFinanceYearExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportFactorFinanceYear record);

    int insertSelective(ReportFactorFinanceYear record);

    List<ReportFactorFinanceYear> selectByExample(ReportFactorFinanceYearExample example);

    ReportFactorFinanceYear selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportFactorFinanceYear record, @Param("example") ReportFactorFinanceYearExample example);

    int updateByExample(@Param("record") ReportFactorFinanceYear record, @Param("example") ReportFactorFinanceYearExample example);

    int updateByPrimaryKeySelective(ReportFactorFinanceYear record);

    int updateByPrimaryKey(ReportFactorFinanceYear record);

    ReportFactorFinanceYear lockByPrimaryKey(@Param("id") String id);

    List<ReportFactorFinanceYear> lockByExample(ReportFactorFinanceYearExample example);
}