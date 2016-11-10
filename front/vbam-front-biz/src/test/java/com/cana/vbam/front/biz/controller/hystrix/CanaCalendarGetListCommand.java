/*package com.cana.vbam.front.biz.controller.hystrix;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.cana.setting.api.ISettingApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;
import com.netflix.hystrix.HystrixCommand;

public class CanaCalendarGetListCommand extends HystrixCommand<ListResult<?>> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ISettingApi settingApiImpl = ContextLoader.getCurrentWebApplicationContext().getBean(ISettingApi.class);
	
	private CanaCalendarRequest canaCalendarRequest;
	
	public CanaCalendarGetListCommand(Setter setter, CanaCalendarRequest canaCalendarRequest) {
		super(setter);
		this.canaCalendarRequest = canaCalendarRequest;
	}

	@Override
	protected ListResult<?> run() throws Exception {
		logger.info("开始调用服务");
		Thread.sleep(5000);
		return settingApiImpl.getList(canaCalendarRequest);
	}

	@Override
	protected ListResult<?> getFallback() {
		logger.info("服务调用失败");
		return ListResult.fail("失败，降级");
	}

}
*/