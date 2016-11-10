package com.cana.yundaex.service;

import com.cana.member.dao.po.User;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;

/**
 * @author hu
 *
 */
public interface IYundaexMessageService {

	/**
	 * 完成融资合同签署
	 */
	public void sendFinanceContractSignedMailAndSMS(String customer, String mail, String phone);
	
	/**
	 * 个人提交资料消息发送
	 * @param info
	 */
	public void sendPersonalInfoCommitMailAndSMS(YundaexPersonalInfo info);
	
	/**
	 * 个人合同签署消息发送
	 * @param info
	 */
	public void sendPersonalSignContractMailAndSMS(YundaexPersonalInfo info);

	/**
	 * 审核失败，发送短信邮件通知
	 * @param apply
	 */
	public void sendYundaexAuditResultMessageAndMail(YundaexCustomerApply apply);

	/**
	 * 额度计算成功，发送通知
	 * @param ydCustomerApply
	 */
	public void sendAuditSuccessMessageAndMail(YundaexCustomerApply ydCustomerApply,String cent2Yuan,
			String generateActivacationURL);

	/**
	 * 发送激活额度通知
	 * @param user
	 */
	public void sendCreditActiveMessageAndMail(User user);

	/**
	 * 用款申请消息发送
	 * @param customerApplyDetailDTO [
	 * @param info 
	 */
	public void sendLoanApplyMailAndSMS(RepaymentLoanInfoBO info, YdCustomerApplyDetailDTO customerApplyDetailDTO, CustomerDetailDTO customerDetailDTO);
}
