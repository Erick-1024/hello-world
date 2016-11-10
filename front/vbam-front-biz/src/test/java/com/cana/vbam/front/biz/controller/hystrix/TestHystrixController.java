/*package com.cana.vbam.front.biz.controller.hystrix;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.setting.api.ISettingApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;
import com.cana.vbam.common.utils.FrontExceptionHandler;

@Controller
@RequestMapping(value="/hystrix")
public class TestHystrixController {

	@Resource
	private ISettingApi settingApiImpl;
	
	@Resource
	HystrixUtil hystrixUtil;
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String gotoTest(){
		return "page/hystrix/hystrix";
	}
	
	@RequestMapping(value = "/get/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(CanaCalendarRequest canaCalendarRequest) {
		try {
			return settingApiImpl.getList(canaCalendarRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/normal/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> normal(CanaCalendarRequest canaCalendarRequest) {
		try {
			return hystrixUtil.getCanaCalendarList(canaCalendarRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/normal/excel", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> normalExcel(CanaCalendarRequest canaCalendarRequest) {
		try {
			canaCalendarRequest.setStartDate("2016-12-12");
			canaCalendarRequest.setEndDate("2016-12-14");
			return ListResult.success(hystrixUtil.getCanaCalendarExcelList(canaCalendarRequest), 3);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/annotation/list", method = { RequestMethod.POST })
	@ResponseBody
	@ExecuteByHystrix(commandKey = "list")
	public ListResult<?> annotation(CanaCalendarRequest canaCalendarRequest) {
		try {
			return settingApiImpl.getList(canaCalendarRequest);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
}
*/