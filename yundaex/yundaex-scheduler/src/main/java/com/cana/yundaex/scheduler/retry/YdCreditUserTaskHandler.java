package com.cana.yundaex.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.member.api.IUserApi;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;
import com.google.gson.Gson;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class YdCreditUserTaskHandler extends AbstractRetryTaskHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private IUserApi userApiImpl = SpringApplicationContext.getApplicationContext().getBean(IUserApi.class);
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {

		String data = task.getData();
		logger.info("发送韵达授信创建用户请求:{}", data);
		if(!userApiImpl.createYundaexCustomerByCredit(new Gson().fromJson(data, YdCustomerApply4MemberUserDTO.class), task.getTaskId()))
			status.fail();
		logger.info("授信创建韵达申请用户成功");
	}

}
