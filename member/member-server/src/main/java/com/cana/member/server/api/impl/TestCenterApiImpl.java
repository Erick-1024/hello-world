package com.cana.member.server.api.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;

import com.cana.member.api.ITestCenterApi;
import com.cana.vbam.common.service.IVbamCommonService;

public class TestCenterApiImpl implements ITestCenterApi{
	
	@Resource
	private IVbamCommonService commonService;

	@Override
	public void saveVirtualDateTime(String virtualDate, String hourOffset) {
		commonService.saveVirtualDateTime(virtualDate, hourOffset);
	}

	@Override
	public Pair<String, String> getVirtualDateTime() {
		return commonService.getVirtualDateTime();
	}

	@Override
	public void saveVirtualBalance(String userName, Long balance, Integer expireTime) {
		commonService.saveVirtualBalance(userName, balance, expireTime);
	}

	@Override
	public Long getVirtualBalance(String accountNo) {
		return commonService.getVirtualBalance(accountNo);
	}
	
	@Override
	public void saveWithdrawState(String businessSeq, String state) {
		commonService.saveWithdrawState(businessSeq, state);
	}

	@Override
	public boolean isProdEnv() {
		return commonService.isProdEnv();
	}

	@Override
	public void saveVirtualBalanceForAllAccount(Integer expireTime) {
		commonService.setAllAccountVirtualBalanceFlag(expireTime);
	}

	@Override
	public void cancelVirtualBalanceForAllAccount() {
		commonService.cancelVirtualBalanceForAllAccount();
	}
}
