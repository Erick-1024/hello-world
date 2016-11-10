package com.cana.yundaex.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.google.gson.Gson;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class YdUpdateUserRoleTaskHandler extends AbstractRetryTaskHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private IUserApi userApiImpl = SpringApplicationContext.getApplicationContext().getBean(IUserApi.class);
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		String data = task.getData();
		logger.info("发送更新用户角色通知：{}", data);
		if(!userApiImpl.updateRoleOfUser(new Gson().fromJson(data, UserUpdateDTO.class)))
			status.fail();
		logger.info("更新用户角色成功");
	}

}
