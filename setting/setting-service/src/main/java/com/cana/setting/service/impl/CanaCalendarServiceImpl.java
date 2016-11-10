package com.cana.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.setting.dao.mapper.gen.CanaCalendarMapper;
import com.cana.setting.dao.po.CanaCalendarExample;
import com.cana.setting.dao.po.CanaCalendarExample.Criteria;
import com.cana.setting.service.ICanaCalendarService;
import com.cana.setting.service.convertors.CanaCalendarConvertor;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;

@Service
public class CanaCalendarServiceImpl implements ICanaCalendarService {

	@Resource
	private CanaCalendarMapper canaCalendarMapper;

	@Override
	public ListResult<CanaCalendarDTO> getList(CanaCalendarRequest canaCalendarRequest) {
		CanaCalendarExample canaCalendarExample = generateCanaCalendarExampleWhitoutPagination(canaCalendarRequest);
		canaCalendarExample.setLimitStart((canaCalendarRequest.getPage() - 1) * canaCalendarRequest.getPageSize());
		canaCalendarExample.setLimitEnd(canaCalendarRequest.getPageSize());
		return ListResult.success("获取成功", CanaCalendarConvertor.convertCanaCalendar2CanaCalendarDTO(canaCalendarMapper.selectByExample(canaCalendarExample)), canaCalendarMapper.countByExample(canaCalendarExample));
	}

	@Override
	public List<CanaCalendarExcelDTO> getExcelList(CanaCalendarRequest canaCalendarRequest) {
		return CanaCalendarConvertor.convertCanaCalendar2CanaCalendarExcelDTO(canaCalendarMapper.selectByExample(generateCanaCalendarExampleWhitoutPagination(canaCalendarRequest)));
	}

	private CanaCalendarExample generateCanaCalendarExampleWhitoutPagination(CanaCalendarRequest canaCalendarRequest) {
		CanaCalendarExample canaCalendarExample = new CanaCalendarExample();
		Criteria criteria = canaCalendarExample.createCriteria();
		if(StringUtils.isNotBlank(canaCalendarRequest.getStartDate()))
			criteria.andDateGreaterThanOrEqualTo(canaCalendarRequest.getStartDate());
		if(StringUtils.isNotBlank(canaCalendarRequest.getEndDate()))
			criteria.andDateLessThanOrEqualTo(canaCalendarRequest.getEndDate());
		canaCalendarExample.setOrderByClause("date");
		return canaCalendarExample;
	}
	
}
