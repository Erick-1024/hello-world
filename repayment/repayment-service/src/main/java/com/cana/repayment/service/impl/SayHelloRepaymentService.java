package com.cana.repayment.service.impl;

import org.springframework.stereotype.Service;

import com.cana.repayment.service.ISayHelloRepaymentService;

@Service
public class SayHelloRepaymentService implements ISayHelloRepaymentService{

	@Override
	public String sayHello(String friendName) throws Exception{
		return "hello " + friendName;
	}

}
