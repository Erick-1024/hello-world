package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentLoanInfoMapper {
    int countByExample(RepaymentLoanInfoExample example);

    int deleteByExample(RepaymentLoanInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentLoanInfo record);

    int insertSelective(RepaymentLoanInfo record);

    List<RepaymentLoanInfo> selectByExample(RepaymentLoanInfoExample example);

    RepaymentLoanInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentLoanInfo record, @Param("example") RepaymentLoanInfoExample example);

    int updateByExample(@Param("record") RepaymentLoanInfo record, @Param("example") RepaymentLoanInfoExample example);

    int updateByPrimaryKeySelective(RepaymentLoanInfo record);

    int updateByPrimaryKey(RepaymentLoanInfo record);

    RepaymentLoanInfo lockByPrimaryKey(@Param("id") String id);

    List<RepaymentLoanInfo> lockByExample(RepaymentLoanInfoExample example);
}