package com.cana.account.dao.mapper.gen;

import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.dao.po.AccountTradeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountTradeRecordMapper {
    int countByExample(AccountTradeRecordExample example);

    int deleteByExample(AccountTradeRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccountTradeRecord record);

    int insertSelective(AccountTradeRecord record);

    List<AccountTradeRecord> selectByExample(AccountTradeRecordExample example);

    AccountTradeRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountTradeRecord record, @Param("example") AccountTradeRecordExample example);

    int updateByExample(@Param("record") AccountTradeRecord record, @Param("example") AccountTradeRecordExample example);

    int updateByPrimaryKeySelective(AccountTradeRecord record);

    int updateByPrimaryKey(AccountTradeRecord record);

    AccountTradeRecord lockByPrimaryKey(@Param("id") String id);

    List<AccountTradeRecord> lockByExample(AccountTradeRecordExample example);
}