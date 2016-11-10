package com.cana.account.dao.mapper;

import java.util.List;

import com.cana.account.dao.po.AccountTradeRecordPO;
import com.cana.vbam.common.account.dto.AccountTradeRecordCriteria;

public interface AccountTradeRecordCustomMapper {
	
	int count(AccountTradeRecordCriteria criteria);

    List<AccountTradeRecordPO> find(AccountTradeRecordCriteria criteria);
}
