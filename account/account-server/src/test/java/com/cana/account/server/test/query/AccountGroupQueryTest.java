/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.account.server.test.query;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.api.IAccountApi;
import com.cana.vbam.common.account.dto.AccountGroupDTO;

/**
 * @author ducer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class AccountGroupQueryTest {

	@Resource
	private IAccountApi accountApi;
	
	//@Test
	public void testOwnQueryAccountGroups(){
		List<AccountGroupDTO> groups = accountApi.getOwnAccountGroups("201512301674");
		System.out.println(groups.size());
		for(AccountGroupDTO group : groups){
			System.out.println(group.getAccountNo());
		}
	}
	
	@Test
	public void testQueryOtherAccountGroups(){
		List<AccountGroupDTO> groups = accountApi.getOtherSupervisionAccountGroups("201512081403");
		System.out.println(groups.size());
		for(AccountGroupDTO group : groups){
			System.out.println(group.getAccountNo());
		}
	}
}
