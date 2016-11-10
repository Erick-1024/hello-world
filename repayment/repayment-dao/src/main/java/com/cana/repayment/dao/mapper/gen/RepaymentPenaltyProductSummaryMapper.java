package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentPenaltyProductSummary;
import com.cana.repayment.dao.po.RepaymentPenaltyProductSummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentPenaltyProductSummaryMapper {
    int countByExample(RepaymentPenaltyProductSummaryExample example);

    int deleteByExample(RepaymentPenaltyProductSummaryExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentPenaltyProductSummary record);

    int insertSelective(RepaymentPenaltyProductSummary record);

    List<RepaymentPenaltyProductSummary> selectByExample(RepaymentPenaltyProductSummaryExample example);

    RepaymentPenaltyProductSummary selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentPenaltyProductSummary record, @Param("example") RepaymentPenaltyProductSummaryExample example);

    int updateByExample(@Param("record") RepaymentPenaltyProductSummary record, @Param("example") RepaymentPenaltyProductSummaryExample example);

    int updateByPrimaryKeySelective(RepaymentPenaltyProductSummary record);

    int updateByPrimaryKey(RepaymentPenaltyProductSummary record);
}