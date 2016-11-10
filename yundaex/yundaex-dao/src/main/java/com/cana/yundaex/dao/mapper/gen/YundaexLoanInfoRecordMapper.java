package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexLoanInfoRecord;
import com.cana.yundaex.dao.po.YundaexLoanInfoRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexLoanInfoRecordMapper {
    int countByExample(YundaexLoanInfoRecordExample example);

    int deleteByExample(YundaexLoanInfoRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(YundaexLoanInfoRecord record);

    int insertSelective(YundaexLoanInfoRecord record);

    List<YundaexLoanInfoRecord> selectByExample(YundaexLoanInfoRecordExample example);

    YundaexLoanInfoRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YundaexLoanInfoRecord record, @Param("example") YundaexLoanInfoRecordExample example);

    int updateByExample(@Param("record") YundaexLoanInfoRecord record, @Param("example") YundaexLoanInfoRecordExample example);

    int updateByPrimaryKeySelective(YundaexLoanInfoRecord record);

    int updateByPrimaryKey(YundaexLoanInfoRecord record);
}