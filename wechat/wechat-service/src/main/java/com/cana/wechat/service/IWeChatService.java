package com.cana.wechat.service;

import com.cana.vbam.common.wechat.account.AccountWechatDTO;
import com.cana.vbam.common.wechat.member.user.CustomerWechatDetailDTO;
import com.cana.vbam.common.wechat.repayment.LoanInfoRequest;
import com.cana.vbam.common.wechat.repayment.LoanInfoResponse;

public interface IWeChatService {

	public String sayHello(String name);

	/**
	 * 查询登陆用户的联系人信息
	 * @param customerId
	 * @return
	 */
	public CustomerWechatDetailDTO queryCustomerDetail(String customerId);

	public boolean modifyContactsInfo(String userId, String contactName, String jobTitle, String mobileNum,
			String mail) throws Exception;

	/**
	 * 获取登陆用户的账户信息
	 * @param customerId
	 * @return
	 */
	public AccountWechatDTO getAccountInfo(String customerId) ;

	/**
	 * 修改用户的联系人名称
	 * @param userId
	 * @param contactName
	 */
	public void updateContactName(String userId, String contactName);

	/**
	 * 修改用户的联系人手机号码
	 * @param userId
	 * @param mobileNum
	 */
	public void updateContactTel(String userId, String mobileNum);

	/**
	 * 修改用户的联系人邮箱
	 * @param userId
	 * @param mail
	 */
	public void updateContactMail(String userId, String mail);

	/**
	 * 修改用户的联系人职称
	 * @param userId
	 * @param jobTitle
	 */
	public void updateContactJobTitle(String userId, String jobTitle);
}