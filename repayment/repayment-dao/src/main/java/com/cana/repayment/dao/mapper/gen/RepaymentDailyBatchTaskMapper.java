package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentDailyBatchTaskMapper {
    int countByExample(RepaymentDailyBatchTaskExample example);

    int deleteByExample(RepaymentDailyBatchTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentDailyBatchTask record);

    int insertSelective(RepaymentDailyBatchTask record);

    List<RepaymentDailyBatchTask> selectByExample(RepaymentDailyBatchTaskExample example);

    RepaymentDailyBatchTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentDailyBatchTask record, @Param("example") RepaymentDailyBatchTaskExample example);

    int updateByExample(@Param("record") RepaymentDailyBatchTask record, @Param("example") RepaymentDailyBatchTaskExample example);

    int updateByPrimaryKeySelective(RepaymentDailyBatchTask record);

    int updateByPrimaryKey(RepaymentDailyBatchTask record);

    RepaymentDailyBatchTask lockByPrimaryKey(@Param("id") String id);

    List<RepaymentDailyBatchTask> lockByExample(RepaymentDailyBatchTaskExample example);
}