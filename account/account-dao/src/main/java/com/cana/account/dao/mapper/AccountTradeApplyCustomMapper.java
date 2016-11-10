package com.cana.account.dao.mapper;

import java.util.List;

import com.cana.account.dao.po.AccountTradeApply;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;

public interface AccountTradeApplyCustomMapper {

    int count(AccountTradeApplyQueryCriteria criteria);

    List<AccountTradeApply> find(AccountTradeApplyQueryCriteria criteria);

}