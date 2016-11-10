package com.cana.credit.service;

import com.cana.vbam.common.credit.dto.apply.CustomerApply4MemberDTO;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;

public interface IRetryTaskService {

	public void createAuditResultNotify(Institution institution, String CustomerId, String auditCode, String auditInfo, String notifyUrl);
	
	public void createCreditLimiteffect(Institution institution, String CustomerId, Long totalLimit, String code, String info, String notifyUrl);
	
	public void createCreditTradeResult(String notifyUrl, String tradeNo, String tranSeq, String tradeType, String tradeStatus, String tradeSuccessTime, String sign);

	public void createCreateCustomer(CustomerApply4MemberDTO customerApply4MemberDTO, String customerApplyId);
	
	public void createUpdateUserRole(UserUpdateDTO userUpdateDTO);
	
}
