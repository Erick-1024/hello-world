package com.cana.bankgate.server.mapper.gen;

import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.po.BankgateTransExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankgateTransMapper {
    int countByExample(BankgateTransExample example);

    int deleteByExample(BankgateTransExample example);

    int deleteByPrimaryKey(String id);

    int insert(BankgateTrans record);

    int insertSelective(BankgateTrans record);

    List<BankgateTrans> selectByExample(BankgateTransExample example);

    BankgateTrans selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BankgateTrans record, @Param("example") BankgateTransExample example);

    int updateByExample(@Param("record") BankgateTrans record, @Param("example") BankgateTransExample example);

    int updateByPrimaryKeySelective(BankgateTrans record);

    int updateByPrimaryKey(BankgateTrans record);

    BankgateTrans lockByPrimaryKey(@Param("id") String id);

    List<BankgateTrans> lockByExample(BankgateTransExample example);
}