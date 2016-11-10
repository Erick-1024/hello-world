package com.cana.credit.dao.mapper.gen;

import com.cana.credit.dao.po.CreditTransfer;
import com.cana.credit.dao.po.CreditTransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditTransferMapper {
    int countByExample(CreditTransferExample example);

    int deleteByExample(CreditTransferExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreditTransfer record);

    int insertSelective(CreditTransfer record);

    List<CreditTransfer> selectByExample(CreditTransferExample example);

    CreditTransfer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreditTransfer record, @Param("example") CreditTransferExample example);

    int updateByExample(@Param("record") CreditTransfer record, @Param("example") CreditTransferExample example);

    int updateByPrimaryKeySelective(CreditTransfer record);

    int updateByPrimaryKey(CreditTransfer record);

    CreditTransfer lockByPrimaryKey(@Param("id") String id);

    List<CreditTransfer> lockByExample(CreditTransferExample example);
}