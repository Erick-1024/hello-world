package com.cana.wechat.server.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.vbam.common.wechat.account.AccountWechatDTO;
import com.cana.vbam.common.wechat.member.user.CustomerWechatDetailDTO;
import com.cana.wechat.api.IWeChatApi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class AccountTest {

	@Resource
	private IWeChatApi wechatApi;
	
	@Test
	public void queryqueryCustomerDetail(){
		CustomerWechatDetailDTO queryCustomerDetail = wechatApi.queryCustomerDetail("201606300210");
		System.out.println(queryCustomerDetail.getContactName());
		System.out.println(queryCustomerDetail.getContactMail());
		System.out.println(queryCustomerDetail.getJobTitle());
		System.out.println(queryCustomerDetail.getContactTel());
	}
	
	@Test
	public void updateContactTel(){
		try {
			wechatApi.updateContactTel("201606300210", "13585934620");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAccountInfo(){
		try {
			AccountWechatDTO accountInfo = wechatApi.getAccountInfo("201606300210");
			System.out.println("总金额："+accountInfo.getAccountBalanceTotal());
			System.out.println("未监管总金额："+accountInfo.getNoSupervisionAccountBalanceTotal());
			System.out.println("未监管账户数量："+accountInfo.getNoSupervisionAccountNumber());
			System.out.println("监管总金额："+accountInfo.getHaveSupervisionAccountBalanceTotal());
			System.out.println("监管数量："+accountInfo.getHaveSupervisionAccountNumbe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
