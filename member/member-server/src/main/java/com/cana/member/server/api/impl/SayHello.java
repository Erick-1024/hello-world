package com.cana.member.server.api.impl;

import com.cana.member.api.ISayHello;
import com.travelzen.framework.core.exception.WebException;

public class SayHello implements ISayHello{

	@Override
	public String sayHello(String friendName) throws Exception{
		throw WebException.instance("exception occured");
//		return "hello " + friendName;
	}

}
