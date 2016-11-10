package com.cana.report.server.api.impl;

import com.cana.report.api.ISayHelloReportApi;

public class SayHelloReportApiImpl implements ISayHelloReportApi {

	@Override
	public void sayHello() {
		System.out.println("启动成功");
	}

}
