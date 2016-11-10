package com.cana.account.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.cana.vbam.common.account.dto.BankBranchInfoDTO;
import com.cana.vbam.common.account.dto.BranchNameQueryCriteria;
import com.travelzen.framework.core.exception.WebException;

public interface IAccountService {

	/**
	 * 对一个公司名下进行批量开户，向网关一次性多次申请开户.<br>
	 * 创建失败的部分不会返回，所有可能得到的map的size比输入的num小<br>
	 * 如果没有账户被创建成功，则抛出{@link WebException}异常
	 * @param num
	 * @param companyName
	 * @return 返回一个bizSeq为键,accountNo为值的键值对集合
	 */
	public List<Pair<String, String>> batchCreateBankAccount(int num, String companyName);
	
	/**
	 * 查询所有符合条件的支行信息
	 * @param queryCriteria
	 * @return
	 */
	public List<BankBranchInfoDTO> queryqueryBranchInfo(BranchNameQueryCriteria queryCriteria);
}
