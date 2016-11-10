/**
 * Copyright (c) 2015, travelzen and/or its affiliates. All rights reserved.
 */
package com.cana.account.dao.mapper;

import java.util.List;

import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountTradeApply;
import com.cana.account.dao.po.AccountTradeRecord;

/**
 * 数据库表加锁接口select * for update 加锁的时候注明参数和意图
 *
 */
public interface AccountTableLockMapper {
	/**
	 * 通过id给account表加锁
	 */
	public Account lockAccountById(String id);
	
	/**
	 * 通过银行帐号加锁
	 */
	public Account lockAccountByAccountNo(String accountNo);
	
    /**
     * 通过ids给account表加锁
     */
    public List<Account> lockAccountByIds(List<String> ids);
    /**
     * 通过id给account表加锁
     */
    public List<Account> lockAccountByAccumulationId(String id);

	/**
	 * 通过id给account_apply表加锁
	 */
	public AccountApply lockAccountApplyById(String id);

	public AccountTradeApply lockAccountTradeApplyById(String id);
	
	public List<AccountTradeRecord> lockTradeRecordBySeq(String businessSeq);
	
}
