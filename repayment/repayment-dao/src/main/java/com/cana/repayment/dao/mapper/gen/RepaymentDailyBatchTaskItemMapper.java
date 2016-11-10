package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentDailyBatchTaskItemMapper {
    int countByExample(RepaymentDailyBatchTaskItemExample example);

    int deleteByExample(RepaymentDailyBatchTaskItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentDailyBatchTaskItem record);

    int insertSelective(RepaymentDailyBatchTaskItem record);

    List<RepaymentDailyBatchTaskItem> selectByExample(RepaymentDailyBatchTaskItemExample example);

    RepaymentDailyBatchTaskItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentDailyBatchTaskItem record, @Param("example") RepaymentDailyBatchTaskItemExample example);

    int updateByExample(@Param("record") RepaymentDailyBatchTaskItem record, @Param("example") RepaymentDailyBatchTaskItemExample example);

    int updateByPrimaryKeySelective(RepaymentDailyBatchTaskItem record);

    int updateByPrimaryKey(RepaymentDailyBatchTaskItem record);
}