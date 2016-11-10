package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.YundaexCreditTransfer;
import com.cana.yundaex.dao.po.YundaexCreditTransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YundaexCreditTransferMapper {
    int countByExample(YundaexCreditTransferExample example);

    int deleteByExample(YundaexCreditTransferExample example);

    int deleteByPrimaryKey(String id);

    int insert(YundaexCreditTransfer record);

    int insertSelective(YundaexCreditTransfer record);

    List<YundaexCreditTransfer> selectByExample(YundaexCreditTransferExample example);

    YundaexCreditTransfer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YundaexCreditTransfer record, @Param("example") YundaexCreditTransferExample example);

    int updateByExample(@Param("record") YundaexCreditTransfer record, @Param("example") YundaexCreditTransferExample example);

    int updateByPrimaryKeySelective(YundaexCreditTransfer record);

    int updateByPrimaryKey(YundaexCreditTransfer record);
}