package com.cana.repayment.server.api.impl;

import javax.annotation.Resource;

import com.cana.repayment.service.ISayHelloRepaymentService;
import com.cana.repayment.api.ISayHelloRepayment;

public class SayHelloRepayment implements ISayHelloRepayment{

	@Resource
	private ISayHelloRepaymentService sayHelloRepaymentService;
	
	
	@Override
	public String sayHello(String friendName) throws Exception{
		return sayHelloRepaymentService.sayHello(friendName);
	}

}
