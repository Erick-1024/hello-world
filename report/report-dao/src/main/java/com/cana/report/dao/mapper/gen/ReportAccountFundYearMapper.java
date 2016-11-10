package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportAccountFundYear;
import com.cana.report.dao.po.ReportAccountFundYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportAccountFundYearMapper {
    int countByExample(ReportAccountFundYearExample example);

    int deleteByExample(ReportAccountFundYearExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportAccountFundYear record);

    int insertSelective(ReportAccountFundYear record);

    List<ReportAccountFundYear> selectByExample(ReportAccountFundYearExample example);

    ReportAccountFundYear selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportAccountFundYear record, @Param("example") ReportAccountFundYearExample example);

    int updateByExample(@Param("record") ReportAccountFundYear record, @Param("example") ReportAccountFundYearExample example);

    int updateByPrimaryKeySelective(ReportAccountFundYear record);

    int updateByPrimaryKey(ReportAccountFundYear record);

    ReportAccountFundYear lockByPrimaryKey(@Param("id") Integer id);

    List<ReportAccountFundYear> lockByExample(ReportAccountFundYearExample example);
}