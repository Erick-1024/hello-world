package com.cana.setting.api;

import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;

public interface ISettingApi {

	public void saveCalendar(List<List<String>> calendarData, String userId);

	public ListResult<CanaCalendarDTO> getList(CanaCalendarRequest canaCalendarRequest);
	
	public List<CanaCalendarExcelDTO> getExcelList(CanaCalendarRequest canaCalendarRequest);
	
	public void modify(CanaCalendarDTO canaCalendarDTO, String uesrId);
	
}
