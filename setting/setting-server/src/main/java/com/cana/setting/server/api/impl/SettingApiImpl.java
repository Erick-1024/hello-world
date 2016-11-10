package com.cana.setting.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.setting.api.ISettingApi;
import com.cana.setting.service.ICanaCalendarService;
import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;
import com.cana.vbam.common.utils.PaginationUtils;
import com.travelzen.framework.core.exception.WebException;

public class SettingApiImpl implements ISettingApi {

	@Resource
	private ICanaCalendarService canaCalendarServiceImpl;
	
	@Resource
	private ICanaCalendarTransactionService CanaCalendarTransactionServiceImpl;
	
	@Override
	public void saveCalendar(List<List<String>> calendarData, String userId) {
		CanaCalendarTransactionServiceImpl.saveCalendar(calendarData, userId);
	}

	@Override
	public ListResult<CanaCalendarDTO> getList(CanaCalendarRequest canaCalendarRequest) {
		PaginationUtils.StandardizingPagination(canaCalendarRequest);
		return canaCalendarServiceImpl.getList(canaCalendarRequest);
	}

	@Override
	public List<CanaCalendarExcelDTO> getExcelList(CanaCalendarRequest canaCalendarRequest) {
		return canaCalendarServiceImpl.getExcelList(canaCalendarRequest);
	}
	
	@Override
	public void modify(CanaCalendarDTO canaCalendarDTO, String uesrId) {
		if(StringUtils.isBlank(canaCalendarDTO.getDate()))
			throw WebException.instance("日期不能为空");
		if(canaCalendarDTO.getIsHoliday() == null)
			throw WebException.instance("是否假期不能为空");
		CanaCalendarTransactionServiceImpl.modify(canaCalendarDTO, uesrId);
	}

}
