package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoanInfoMapper {
    int countByExample(LoanInfoExample example);

    int deleteByExample(LoanInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);

    List<LoanInfo> selectByExample(LoanInfoExample example);

    LoanInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoanInfo record, @Param("example") LoanInfoExample example);

    int updateByExample(@Param("record") LoanInfo record, @Param("example") LoanInfoExample example);

    int updateByPrimaryKeySelective(LoanInfo record);

    int updateByPrimaryKey(LoanInfo record);

    LoanInfo lockByPrimaryKey(@Param("id") String id);

    List<LoanInfo> lockByExample(LoanInfoExample example);
}