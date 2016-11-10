package com.cana.account.dao.mapper.gen;

import com.cana.account.dao.po.AccountTradeApply;
import com.cana.account.dao.po.AccountTradeApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountTradeApplyMapper {
    int countByExample(AccountTradeApplyExample example);

    int deleteByExample(AccountTradeApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccountTradeApply record);

    int insertSelective(AccountTradeApply record);

    List<AccountTradeApply> selectByExample(AccountTradeApplyExample example);

    AccountTradeApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountTradeApply record, @Param("example") AccountTradeApplyExample example);

    int updateByExample(@Param("record") AccountTradeApply record, @Param("example") AccountTradeApplyExample example);

    int updateByPrimaryKeySelective(AccountTradeApply record);

    int updateByPrimaryKey(AccountTradeApply record);

    AccountTradeApply lockByPrimaryKey(@Param("id") String id);

    List<AccountTradeApply> lockByExample(AccountTradeApplyExample example);
}