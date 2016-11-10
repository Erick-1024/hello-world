package com.cana.early.warning.service.convertors;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningSystemEventRepresent;
import com.cana.vbam.common.early.warning.dto.MonitorDataEarlyWarningExtra;
import com.cana.vbam.common.early.warning.dto.OverdueDataEarlyWarningExtra;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventSubCategory;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class EarlyWarningEventConvertor {
	
	private static Gson gson = new Gson();

	public static List<EarlyWarningEventDetailDTO> convertEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(List<EarlywarningEvent> earlywarningEvents) {
		List<EarlyWarningEventDetailDTO> returnValue = new ArrayList<>();
		for (EarlywarningEvent earlywarningEvent : earlywarningEvents)
			returnValue.add(convertEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(earlywarningEvent));
		return returnValue;
	}
	
	public static EarlyWarningEventDetailDTO convertEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(EarlywarningEvent earlywarningEvent) {
		EarlyWarningEventDetailDTO earlyWarningEventDetailDTO = new EarlyWarningEventDetailDTO();
		BeanUtils.copyProperties(earlywarningEvent, earlyWarningEventDetailDTO);
		String represent = earlywarningEvent.getRepresent();
		String extraData = earlywarningEvent.getExtraData();
		// 支持换行
		JsonReader reader = new JsonReader(new StringReader(represent));
		reader.setLenient(true);
		switch (EarlywarningEventCategory.valueOf(earlywarningEvent.getType())) {
		case SYSTEM:
			earlyWarningEventDetailDTO.setRepresent(gson.fromJson(reader, EarlyWarningSystemEventRepresent.class));
			break;
		default:
			earlyWarningEventDetailDTO.setRepresent(gson.fromJson(reader, Object.class));
			break;
		}
		reader = new JsonReader(new StringReader(extraData));
		reader.setLenient(true);
		switch (EarlywarningEventCategory.valueOf(earlywarningEvent.getType())) {
		case SYSTEM:
			switch (EarlywarningEventSubCategory.valueOf(earlywarningEvent.getSubType())) {
			case CONTINUE_OVERDUE_NUM:
			case TOTAL_OVERDUE_NUM:
				earlyWarningEventDetailDTO.setExtraData(gson.fromJson(reader, OverdueDataEarlyWarningExtra.class));
				break;
			default:
				earlyWarningEventDetailDTO.setExtraData(gson.fromJson(reader, new TypeToken<List<MonitorDataEarlyWarningExtra>>(){}.getType()));
				break;
			}
			break;
		default:
			earlyWarningEventDetailDTO.setExtraData(gson.fromJson(reader, Object.class));
			break;
		}
		return earlyWarningEventDetailDTO;
	}
	
	
	public static List<EarlyWarningEventDetailDTO> convertYundaexEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(List<EarlywarningEvent> earlywarningEvents) {
		List<EarlyWarningEventDetailDTO> returnValue = new ArrayList<>();
		for (EarlywarningEvent earlywarningEvent : earlywarningEvents)
			returnValue.add(convertYundaexEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(earlywarningEvent));
		return returnValue;
	}
	
	public static EarlyWarningEventDetailDTO convertYundaexEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(EarlywarningEvent earlywarningEvent) {
		EarlyWarningEventDetailDTO earlyWarningEventDetailDTO = new EarlyWarningEventDetailDTO();
		BeanUtils.copyProperties(earlywarningEvent, earlyWarningEventDetailDTO);
		String represent = earlywarningEvent.getRepresent();
		String extraData = earlywarningEvent.getExtraData();
		// 支持换行
		JsonReader reader = new JsonReader(new StringReader(represent));
		reader.setLenient(true);
		switch (YundaexEarlywarningEventCategory.valueOf(earlywarningEvent.getType())) {
		case SYSTEM:
			earlyWarningEventDetailDTO.setRepresent(gson.fromJson(reader, EarlyWarningSystemEventRepresent.class));
			break;
		default:
			earlyWarningEventDetailDTO.setRepresent(gson.fromJson(reader, Object.class));
			break;
		}
		reader = new JsonReader(new StringReader(extraData));
		reader.setLenient(true);
		switch (YundaexEarlywarningEventCategory.valueOf(earlywarningEvent.getType())) {
		case SYSTEM:
			earlyWarningEventDetailDTO.setExtraData(gson.fromJson(reader, Object.class));
			break;
		default:
			earlyWarningEventDetailDTO.setExtraData(gson.fromJson(reader, Object.class));
			break;
		}
		return earlyWarningEventDetailDTO;
	}
	
	public static EarlywarningEvent convertEarlyWarningEventDetailDTO2EarlyWarningEvent(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO) {
		EarlywarningEvent earlywarningEvent  = new EarlywarningEvent();
		BeanUtils.copyProperties(earlyWarningEventDetailDTO, earlywarningEvent);
		return earlywarningEvent;
	}
	
	public static List<EarlyWarningEventHistoryResponse> convertEarlyWarningEvent2EarlyWarningEventHistoryResponse(List<EarlywarningEvent> earlywarningEvents) {
		List<EarlyWarningEventHistoryResponse> returnValue = new ArrayList<>();
		for (EarlywarningEvent earlywarningEvent : earlywarningEvents) {
			EarlyWarningEventHistoryResponse earlyWarningEventHistoryResponse = new EarlyWarningEventHistoryResponse();
			BeanUtils.copyProperties(earlywarningEvent, earlyWarningEventHistoryResponse);
			earlyWarningEventHistoryResponse.setStateDesc(EarlywarningEventState.valueOf(earlywarningEvent.getState()).desc());
			String represent = earlywarningEvent.getRepresent();
			// 支持换行
			JsonReader reader = new JsonReader(new StringReader(represent));
			reader.setLenient(true);
			earlyWarningEventHistoryResponse.setRepresent(gson.fromJson(reader, Object.class));
			returnValue.add(earlyWarningEventHistoryResponse);
		}
		return returnValue;
	}
	
}
