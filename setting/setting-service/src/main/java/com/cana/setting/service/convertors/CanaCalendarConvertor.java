package com.cana.setting.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.setting.dao.po.CanaCalendar;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;

public class CanaCalendarConvertor {

	public static List<CanaCalendarDTO> convertCanaCalendar2CanaCalendarDTO(List<CanaCalendar> canaCalendars) {
		List<CanaCalendarDTO> returnValue = new ArrayList<>();
		for (CanaCalendar canaCalendar : canaCalendars) {
			CanaCalendarDTO canaCalendarDTO = new CanaCalendarDTO();
			BeanUtils.copyProperties(canaCalendar, canaCalendarDTO);
			returnValue.add(canaCalendarDTO);
		}
		return returnValue;
	}

	public static List<CanaCalendarExcelDTO> convertCanaCalendar2CanaCalendarExcelDTO(List<CanaCalendar> canaCalendars) {
		List<CanaCalendarExcelDTO> returnValue = new ArrayList<>();
		for (CanaCalendar canaCalendar : canaCalendars) {
			CanaCalendarExcelDTO canaCalendarExcelDTO = new CanaCalendarExcelDTO();
			BeanUtils.copyProperties(canaCalendar, canaCalendarExcelDTO);
			returnValue.add(canaCalendarExcelDTO);
		}
		return returnValue;
	}
	
}
