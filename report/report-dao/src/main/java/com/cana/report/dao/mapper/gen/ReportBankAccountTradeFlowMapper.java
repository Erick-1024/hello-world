package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportBankAccountTradeFlow;
import com.cana.report.dao.po.ReportBankAccountTradeFlowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportBankAccountTradeFlowMapper {
    int countByExample(ReportBankAccountTradeFlowExample example);

    int deleteByExample(ReportBankAccountTradeFlowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportBankAccountTradeFlow record);

    int insertSelective(ReportBankAccountTradeFlow record);

    List<ReportBankAccountTradeFlow> selectByExample(ReportBankAccountTradeFlowExample example);

    ReportBankAccountTradeFlow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportBankAccountTradeFlow record, @Param("example") ReportBankAccountTradeFlowExample example);

    int updateByExample(@Param("record") ReportBankAccountTradeFlow record, @Param("example") ReportBankAccountTradeFlowExample example);

    int updateByPrimaryKeySelective(ReportBankAccountTradeFlow record);

    int updateByPrimaryKey(ReportBankAccountTradeFlow record);

    ReportBankAccountTradeFlow lockByPrimaryKey(@Param("id") Integer id);

    List<ReportBankAccountTradeFlow> lockByExample(ReportBankAccountTradeFlowExample example);
}