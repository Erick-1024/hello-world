package com.cana.member.server.xml.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.member.server.scheduler.PermissionDefinitionInitializer;
import com.cana.member.service.transaction.IPermissionTransactionService;

@ContextConfiguration(locations = "classpath:META-INF/spring/member-server-*.xml")
public class PermissionParseTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private PermissionDefinitionInitializer permissionDefinitionInitializer;
	
	@Resource(name = "permissionTransactionService")
	private IPermissionTransactionService PermissionTransactionService;
	@Test
	public void test() {
		System.out.println("--测试开始--");
//		PermissionDefinitionInitializer persion = new PermissionDefinitionInitializer();
//		persion.loadPermissionDefinitions();
//		permissionDefinitionInitializer.initPermissionDefinitions();
		PermissionTransactionService.loadPermissionDefinitions();
	}

}
