package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportLoanInfoChangeTrace;
import com.cana.report.dao.po.ReportLoanInfoChangeTraceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportLoanInfoChangeTraceMapper {
    int countByExample(ReportLoanInfoChangeTraceExample example);

    int deleteByExample(ReportLoanInfoChangeTraceExample example);

    int deleteByPrimaryKey(String loanInfoId);

    int insert(ReportLoanInfoChangeTrace record);

    int insertSelective(ReportLoanInfoChangeTrace record);

    List<ReportLoanInfoChangeTrace> selectByExample(ReportLoanInfoChangeTraceExample example);

    ReportLoanInfoChangeTrace selectByPrimaryKey(String loanInfoId);

    int updateByExampleSelective(@Param("record") ReportLoanInfoChangeTrace record, @Param("example") ReportLoanInfoChangeTraceExample example);

    int updateByExample(@Param("record") ReportLoanInfoChangeTrace record, @Param("example") ReportLoanInfoChangeTraceExample example);

    int updateByPrimaryKeySelective(ReportLoanInfoChangeTrace record);

    int updateByPrimaryKey(ReportLoanInfoChangeTrace record);
}