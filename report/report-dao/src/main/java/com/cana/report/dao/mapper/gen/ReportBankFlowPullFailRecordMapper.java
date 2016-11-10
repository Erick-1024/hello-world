package com.cana.report.dao.mapper.gen;

import com.cana.report.dao.po.ReportBankFlowPullFailRecord;
import com.cana.report.dao.po.ReportBankFlowPullFailRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportBankFlowPullFailRecordMapper {
    int countByExample(ReportBankFlowPullFailRecordExample example);

    int deleteByExample(ReportBankFlowPullFailRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportBankFlowPullFailRecord record);

    int insertSelective(ReportBankFlowPullFailRecord record);

    List<ReportBankFlowPullFailRecord> selectByExample(ReportBankFlowPullFailRecordExample example);

    ReportBankFlowPullFailRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportBankFlowPullFailRecord record, @Param("example") ReportBankFlowPullFailRecordExample example);

    int updateByExample(@Param("record") ReportBankFlowPullFailRecord record, @Param("example") ReportBankFlowPullFailRecordExample example);

    int updateByPrimaryKeySelective(ReportBankFlowPullFailRecord record);

    int updateByPrimaryKey(ReportBankFlowPullFailRecord record);

    ReportBankFlowPullFailRecord lockByPrimaryKey(@Param("id") Integer id);

    List<ReportBankFlowPullFailRecord> lockByExample(ReportBankFlowPullFailRecordExample example);
}