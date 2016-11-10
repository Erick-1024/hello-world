package com.cana.account.dao.mapper.gen;

import com.cana.account.dao.po.BankBranchInfo;
import com.cana.account.dao.po.BankBranchInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankBranchInfoMapper {
    int countByExample(BankBranchInfoExample example);

    int deleteByExample(BankBranchInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(BankBranchInfo record);

    int insertSelective(BankBranchInfo record);

    List<BankBranchInfo> selectByExample(BankBranchInfoExample example);

    BankBranchInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BankBranchInfo record, @Param("example") BankBranchInfoExample example);

    int updateByExample(@Param("record") BankBranchInfo record, @Param("example") BankBranchInfoExample example);

    int updateByPrimaryKeySelective(BankBranchInfo record);

    int updateByPrimaryKey(BankBranchInfo record);
}