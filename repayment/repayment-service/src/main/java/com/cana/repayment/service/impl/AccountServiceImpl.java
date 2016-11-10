package com.cana.repayment.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.repayment.service.IAccountService;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;

@Service
public class AccountServiceImpl implements IAccountService
{
	@Resource
	private IAccountApi accountApi;
	@Override
	public List<AccountDTO> queryRepaymentAccounts(String factorId, String finaceName){
		List<AccountDTO> accounts = accountApi.queryRepaymentAccounts(factorId, finaceName);
		Iterator<AccountDTO> iterator = accounts.iterator();
		while (iterator.hasNext()) {
			AccountDTO accountDTO = iterator.next();
			if(AccountAccumulationStatus.NO_ACCUMULATION == accountDTO.getAccumulationStatus()){
				continue;
			}else {
				iterator.remove();
			}
		}
		return accounts;
	}
	
	@Override
	public AccountDTO getDefaultAccount(String factorId, String finaceName)
	{
		List<AccountDTO> accountDTOs = accountApi.queryRepaymentAccounts(factorId, finaceName);
		if(null != accountDTOs)
		{
			for(AccountDTO accountDTO : accountDTOs)
			{
				if(accountDTO.isDefaultRepayment() && AccountAccumulationStatus.NO_ACCUMULATION == accountDTO.getAccumulationStatus())
					return accountDTO;
			}
		}
		return null;
	}

}
