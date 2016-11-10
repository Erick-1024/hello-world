package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.LoanInfoConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoanInfoConfigMapper {
    int countByExample(LoanInfoConfigExample example);

    int deleteByExample(LoanInfoConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoanInfoConfig record);

    int insertSelective(LoanInfoConfig record);

    List<LoanInfoConfig> selectByExample(LoanInfoConfigExample example);

    LoanInfoConfig selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoanInfoConfig record, @Param("example") LoanInfoConfigExample example);

    int updateByExample(@Param("record") LoanInfoConfig record, @Param("example") LoanInfoConfigExample example);

    int updateByPrimaryKeySelective(LoanInfoConfig record);

    int updateByPrimaryKey(LoanInfoConfig record);

    LoanInfoConfig lockByPrimaryKey(@Param("id") String id);

    List<LoanInfoConfig> lockByExample(LoanInfoConfigExample example);
}