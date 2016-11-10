package com.cana.account.server.api.impl;

import com.cana.account.api.ISayAccountHello;

public class SayAccountHello implements ISayAccountHello{

	@Override
	public String sayAccountHello(String friendName) {
		// TODO Auto-generated method stub
		return "hello " + friendName;
	}

}
