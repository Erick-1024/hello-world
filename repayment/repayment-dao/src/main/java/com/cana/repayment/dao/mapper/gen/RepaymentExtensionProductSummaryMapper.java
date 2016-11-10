package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentExtensionProductSummary;
import com.cana.repayment.dao.po.RepaymentExtensionProductSummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentExtensionProductSummaryMapper {
    int countByExample(RepaymentExtensionProductSummaryExample example);

    int deleteByExample(RepaymentExtensionProductSummaryExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentExtensionProductSummary record);

    int insertSelective(RepaymentExtensionProductSummary record);

    List<RepaymentExtensionProductSummary> selectByExample(RepaymentExtensionProductSummaryExample example);

    RepaymentExtensionProductSummary selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentExtensionProductSummary record, @Param("example") RepaymentExtensionProductSummaryExample example);

    int updateByExample(@Param("record") RepaymentExtensionProductSummary record, @Param("example") RepaymentExtensionProductSummaryExample example);

    int updateByPrimaryKeySelective(RepaymentExtensionProductSummary record);

    int updateByPrimaryKey(RepaymentExtensionProductSummary record);
}