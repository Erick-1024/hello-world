package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportAccountFundCount;
import com.cana.report.dao.po.ReportAccountFundCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportAccountFundCountMapper {
    int countByExample(ReportAccountFundCountExample example);

    int deleteByExample(ReportAccountFundCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportAccountFundCount record);

    int insertSelective(ReportAccountFundCount record);

    List<ReportAccountFundCount> selectByExample(ReportAccountFundCountExample example);

    ReportAccountFundCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportAccountFundCount record, @Param("example") ReportAccountFundCountExample example);

    int updateByExample(@Param("record") ReportAccountFundCount record, @Param("example") ReportAccountFundCountExample example);

    int updateByPrimaryKeySelective(ReportAccountFundCount record);

    int updateByPrimaryKey(ReportAccountFundCount record);

    ReportAccountFundCount lockByPrimaryKey(@Param("id") Integer id);

    List<ReportAccountFundCount> lockByExample(ReportAccountFundCountExample example);
}