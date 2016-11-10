package com.cana.member.server.scheduler;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.member.service.transaction.IPermissionTransactionService;
/**
 *
 * @since Nov 4, 201511:44:50 AM
 * @author zhiwen.hu
 *
 */
@Component
public class PermissionDefinitionInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionDefinitionInitializer.class);
	
	@Resource(name =  "permissionTransactionService")
	private IPermissionTransactionService permissionTransactionService;
	
	@PostConstruct
	public void initPermissionDefinitions() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					permissionTransactionService.loadPermissionDefinitions();
				} catch (Exception e) {
					LOGGER.error("failed to handle permission definitions.", e);
				}
			}
		}, 5000);
	}



}
