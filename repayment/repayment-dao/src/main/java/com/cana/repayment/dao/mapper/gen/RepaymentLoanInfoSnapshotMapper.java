package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;
import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentLoanInfoSnapshotMapper {
    int countByExample(RepaymentLoanInfoSnapshotExample example);

    int deleteByExample(RepaymentLoanInfoSnapshotExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentLoanInfoSnapshot record);

    int insertSelective(RepaymentLoanInfoSnapshot record);

    List<RepaymentLoanInfoSnapshot> selectByExample(RepaymentLoanInfoSnapshotExample example);

    RepaymentLoanInfoSnapshot selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentLoanInfoSnapshot record, @Param("example") RepaymentLoanInfoSnapshotExample example);

    int updateByExample(@Param("record") RepaymentLoanInfoSnapshot record, @Param("example") RepaymentLoanInfoSnapshotExample example);

    int updateByPrimaryKeySelective(RepaymentLoanInfoSnapshot record);

    int updateByPrimaryKey(RepaymentLoanInfoSnapshot record);

    RepaymentLoanInfoSnapshot lockByPrimaryKey(@Param("id") String id);

    List<RepaymentLoanInfoSnapshot> lockByExample(RepaymentLoanInfoSnapshotExample example);
}