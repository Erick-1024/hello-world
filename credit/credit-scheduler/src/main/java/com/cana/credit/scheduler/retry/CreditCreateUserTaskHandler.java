package com.cana.credit.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.dto.apply.CustomerApply4MemberDTO;
import com.google.gson.Gson;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class CreditCreateUserTaskHandler extends AbstractRetryTaskHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private IUserApi userApiImpl = SpringApplicationContext.getApplicationContext().getBean(IUserApi.class);
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		String data = task.getData();
		logger.info("发送授信创建用户请求:{}", data);
		CustomerApply4MemberDTO customerApply4MemberDTO = new Gson().fromJson(data, CustomerApply4MemberDTO.class);
		if(!userApiImpl.createCustomerByCredit(customerApply4MemberDTO, task.getTaskId()))
			status.fail();
		else{
			logger.info("授信创建用户成功");
		}
	}
	

}
