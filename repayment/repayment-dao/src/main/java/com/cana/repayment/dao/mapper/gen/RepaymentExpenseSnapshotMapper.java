package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;
import com.cana.repayment.dao.po.RepaymentExpenseSnapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentExpenseSnapshotMapper {
    int countByExample(RepaymentExpenseSnapshotExample example);

    int deleteByExample(RepaymentExpenseSnapshotExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentExpenseSnapshot record);

    int insertSelective(RepaymentExpenseSnapshot record);

    List<RepaymentExpenseSnapshot> selectByExample(RepaymentExpenseSnapshotExample example);

    RepaymentExpenseSnapshot selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentExpenseSnapshot record, @Param("example") RepaymentExpenseSnapshotExample example);

    int updateByExample(@Param("record") RepaymentExpenseSnapshot record, @Param("example") RepaymentExpenseSnapshotExample example);

    int updateByPrimaryKeySelective(RepaymentExpenseSnapshot record);

    int updateByPrimaryKey(RepaymentExpenseSnapshot record);
}