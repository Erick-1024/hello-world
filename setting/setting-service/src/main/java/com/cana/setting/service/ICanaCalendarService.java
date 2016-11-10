package com.cana.setting.service;

import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;

public interface ICanaCalendarService {

	public ListResult<CanaCalendarDTO> getList(CanaCalendarRequest canaCalendarRequest);
	
	public List<CanaCalendarExcelDTO> getExcelList(CanaCalendarRequest canaCalendarRequest);
	
}
