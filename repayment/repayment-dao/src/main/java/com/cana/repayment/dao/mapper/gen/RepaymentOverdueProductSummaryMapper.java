package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentOverdueProductSummary;
import com.cana.repayment.dao.po.RepaymentOverdueProductSummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentOverdueProductSummaryMapper {
    int countByExample(RepaymentOverdueProductSummaryExample example);

    int deleteByExample(RepaymentOverdueProductSummaryExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentOverdueProductSummary record);

    int insertSelective(RepaymentOverdueProductSummary record);

    List<RepaymentOverdueProductSummary> selectByExample(RepaymentOverdueProductSummaryExample example);

    RepaymentOverdueProductSummary selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentOverdueProductSummary record, @Param("example") RepaymentOverdueProductSummaryExample example);

    int updateByExample(@Param("record") RepaymentOverdueProductSummary record, @Param("example") RepaymentOverdueProductSummaryExample example);

    int updateByPrimaryKeySelective(RepaymentOverdueProductSummary record);

    int updateByPrimaryKey(RepaymentOverdueProductSummary record);
}