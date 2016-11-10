package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportFactorFinanceCountMapper {
    int countByExample(ReportFactorFinanceCountExample example);

    int deleteByExample(ReportFactorFinanceCountExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportFactorFinanceCount record);

    int insertSelective(ReportFactorFinanceCount record);

    List<ReportFactorFinanceCount> selectByExample(ReportFactorFinanceCountExample example);

    ReportFactorFinanceCount selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportFactorFinanceCount record, @Param("example") ReportFactorFinanceCountExample example);

    int updateByExample(@Param("record") ReportFactorFinanceCount record, @Param("example") ReportFactorFinanceCountExample example);

    int updateByPrimaryKeySelective(ReportFactorFinanceCount record);

    int updateByPrimaryKey(ReportFactorFinanceCount record);

    ReportFactorFinanceCount lockByPrimaryKey(@Param("id") String id);

    List<ReportFactorFinanceCount> lockByExample(ReportFactorFinanceCountExample example);
}