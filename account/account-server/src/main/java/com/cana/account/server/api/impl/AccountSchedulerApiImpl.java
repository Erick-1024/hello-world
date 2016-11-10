package com.cana.account.server.api.impl;

import javax.annotation.Resource;

import com.cana.account.api.IAccountSchedulerApi;
import com.cana.account.service.IAccountAutoUpdateTradeRecordStatusTask;
import com.cana.account.service.IAccountAutoUpdateWithdrawFeeTask;

public class AccountSchedulerApiImpl implements IAccountSchedulerApi {

    @Resource
    private IAccountAutoUpdateTradeRecordStatusTask accountAutoUpdateTradeRecordStatusTask;
    
    @Resource
    private IAccountAutoUpdateWithdrawFeeTask accountAutoUpdateWithdrawFeeTask;

    @Override
    public String autoUpdateTradeRecordStatus() {
        return accountAutoUpdateTradeRecordStatusTask.runTask();
    }

	@Override
	public String autoUpdateWithdrawFee() {
		return accountAutoUpdateWithdrawFeeTask.runTask();
	}
    
}
