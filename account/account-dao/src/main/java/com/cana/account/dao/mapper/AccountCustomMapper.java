package com.cana.account.dao.mapper;

import java.util.List;

import com.cana.account.dao.po.Account;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;

public interface AccountCustomMapper {

    int count(AccountQueryCriteria criteria);

    List<Account> find(AccountQueryCriteria criteria);
    
    List<Account> findAll(AccountQueryCriteria criteria);

	List<Account> queryAccountsByCustomerId(AccountQueryCriteria Criteria);

}