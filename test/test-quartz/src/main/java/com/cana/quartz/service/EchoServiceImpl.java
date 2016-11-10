package com.cana.quartz.service;

import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements IEchoService{

	@Override
	public void echo() {
		
		System.out.println("hello guy");
		
	}

}
