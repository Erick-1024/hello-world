package com.cana.repayment.service;

import java.util.List;

import com.cana.vbam.common.account.dto.AccountDTO;

public interface IAccountService
{
	/**
	 * 查询融资管理还款账户，账户类型是一般，账户状态是正常,归集状态是非归集
	 * @param factorId，保理商ID
	 * @param finaceName，融资商企业名称
	 */
	public List<AccountDTO> queryRepaymentAccounts(String factorId, String finaceName);
	
	/**
	 * 获取默认的监管账户
	 * @param factorId
	 * @param finaceName
	 * @return
	 */
	public AccountDTO getDefaultAccount(String factorId, String finaceName);
}
