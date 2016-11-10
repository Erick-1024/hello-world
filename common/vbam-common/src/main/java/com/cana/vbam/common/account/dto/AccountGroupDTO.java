/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;

import com.cana.vbam.common.account.enums.AccountSupervisionStatus;

/**
 * 主账号和附属账号属性组成一个账号组，现在这个是为了方便账户二期需求扩展
 * 
 * @author ducer
 *
 */
public class AccountGroupDTO implements Serializable {

	private static final long serialVersionUID = 8410941612509091306L;

	private String customerId; // 企业ID
	private String mainAccountNo; // 主账号
	private String bankUserName; // 主账号登录名
	private String accountNo; // 附属账号
	private List<Pair<Date, Date>> supervisionDates; // 本日监管关系的存活起止时间，如果本日任何时段都没有监管状态，则为空

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMainAccountNo() {
		return mainAccountNo;
	}

	public void setMainAccountNo(String mainAccountNo) {
		this.mainAccountNo = mainAccountNo;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public List<Pair<Date, Date>> getSupervisionDates() {
		return supervisionDates;
	}

	public void setSupervisionDates(List<Pair<Date, Date>> supervisionDates) {
		this.supervisionDates = supervisionDates;
	}

	public AccountSupervisionStatus isSupervisionWhen(Date when) {
		if (!CollectionUtils.isEmpty(this.supervisionDates)) {
			for (Pair<Date, Date> minMax : supervisionDates) {
				if (minMax.getLeft() == null) {
					return AccountSupervisionStatus.NO_SUPERVISION;
				}
				if (minMax.getRight() == null) {
					return AccountSupervisionStatus.HAVE_SUPERVISION;
				}
				if (when.after(minMax.getLeft()) && when.before(minMax.getRight())) {
					return AccountSupervisionStatus.HAVE_SUPERVISION;
				}
			}
		}
		return AccountSupervisionStatus.NO_SUPERVISION;
	}
}
