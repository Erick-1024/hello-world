package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.repayment.dao.po.RepaymentPlanSnapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentPlanSnapshotMapper {
    int countByExample(RepaymentPlanSnapshotExample example);

    int deleteByExample(RepaymentPlanSnapshotExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentPlanSnapshot record);

    int insertSelective(RepaymentPlanSnapshot record);

    List<RepaymentPlanSnapshot> selectByExample(RepaymentPlanSnapshotExample example);

    RepaymentPlanSnapshot selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentPlanSnapshot record, @Param("example") RepaymentPlanSnapshotExample example);

    int updateByExample(@Param("record") RepaymentPlanSnapshot record, @Param("example") RepaymentPlanSnapshotExample example);

    int updateByPrimaryKeySelective(RepaymentPlanSnapshot record);

    int updateByPrimaryKey(RepaymentPlanSnapshot record);

    RepaymentPlanSnapshot lockByPrimaryKey(@Param("id") String id);

    List<RepaymentPlanSnapshot> lockByExample(RepaymentPlanSnapshotExample example);
}