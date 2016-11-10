package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.dao.po.ReportAccountFundDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportAccountFundDailyMapper {
    int countByExample(ReportAccountFundDailyExample example);

    int deleteByExample(ReportAccountFundDailyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportAccountFundDaily record);

    int insertSelective(ReportAccountFundDaily record);

    List<ReportAccountFundDaily> selectByExample(ReportAccountFundDailyExample example);

    ReportAccountFundDaily selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportAccountFundDaily record, @Param("example") ReportAccountFundDailyExample example);

    int updateByExample(@Param("record") ReportAccountFundDaily record, @Param("example") ReportAccountFundDailyExample example);

    int updateByPrimaryKeySelective(ReportAccountFundDaily record);

    int updateByPrimaryKey(ReportAccountFundDaily record);

    ReportAccountFundDaily lockByPrimaryKey(@Param("id") Integer id);

    List<ReportAccountFundDaily> lockByExample(ReportAccountFundDailyExample example);
}