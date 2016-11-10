/*package com.cana.vbam.front.biz.controller.hystrix;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.cana.setting.api.ISettingApi;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;
import com.netflix.hystrix.HystrixCommand;

public class CanaCalendarGetExcelListCommand extends HystrixCommand<List<CanaCalendarExcelDTO>> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ISettingApi settingApiImpl = ContextLoader.getCurrentWebApplicationContext().getBean(ISettingApi.class);
	
	private CanaCalendarRequest canaCalendarRequest;
	
	public CanaCalendarGetExcelListCommand(Setter setter, CanaCalendarRequest canaCalendarRequest) {
		super(setter);
		this.canaCalendarRequest = canaCalendarRequest;
	}

	@Override
	protected List<CanaCalendarExcelDTO> run() throws Exception {
		logger.info("开始调用服务");
		return settingApiImpl.getExcelList(canaCalendarRequest);
	}

	@Override
	protected List<CanaCalendarExcelDTO> getFallback() {
		logger.info("失败，降级");
		return null;
	}

}
*/