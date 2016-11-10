package com.cana.wechat.service.converters;

import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.wechat.account.AccountWechatDetailDTO;

public class WechatAccountConverter {

	public static AccountWechatDetailDTO converterAccountDTO2WechatDetail(AccountDTO account) {
		AccountWechatDetailDTO accountDetailDTO = new AccountWechatDetailDTO();
		accountDetailDTO.setAccountId(account.getAccountId());
		accountDetailDTO.setAccountNo(account.getAccountNo());
		accountDetailDTO.setAccountName(account.getAccountName());
		if(null != account.getAccountType()){
			accountDetailDTO.setAccountType(account.getAccountType());
			accountDetailDTO.setAccountTypeDesc(account.getAccountType().desc());
		}
		accountDetailDTO.setCompanyId(account.getCompanyId());
		accountDetailDTO.setFactorId(account.getFactorId());
		accountDetailDTO.setFactorName(account.getFactorName());
		accountDetailDTO.setFinaceId(account.getFinaceId());
		accountDetailDTO.setFinaceName(account.getFinaceName());
		if(null != account.getAccountStatus()){
			accountDetailDTO.setAccountStatus(account.getAccountStatus());
			accountDetailDTO.setAccountStatusDesc(account.getAccountStatus().desc());
		}
		if(null != account.getSupervisionStatus()){
			accountDetailDTO.setSupervisionStatus(account.getSupervisionStatus());
			accountDetailDTO.setSupervisionStatusDesc(account.getSupervisionStatus().desc());
		}
		return accountDetailDTO;
	}
	
}
