package com.cana.early.warning.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.EarlyWarningEventReviewCustomMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventReviewMapper;
import com.cana.early.warning.dao.po.EarlywarningEventReview;
import com.cana.early.warning.service.IEarlyWarningEventReviewService;
import com.cana.early.warning.service.convertors.EarlyWarningEventReviewConvertor;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class EarlyWarningEventReviewServiceImpl implements IEarlyWarningEventReviewService {

	@Resource
	private EarlyWarningEventReviewCustomMapper earlyWarningEventReviewCustomMapper;
	
	@Resource
	private EarlywarningEventReviewMapper earlywarningEventReviewMapper;
	
	@Override
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest) {
		List<EarlyWarningEventReviewListResponse> earlywarningEventReviewListResponses = earlyWarningEventReviewCustomMapper.queryEarlyWarningEventReview(earlyWarningEventReviewListRequest);
		for (EarlyWarningEventReviewListResponse earlyWarningEventReviewListResponse : earlywarningEventReviewListResponses) {
			String earlyWarningLevel = earlyWarningEventReviewListResponse.getEarlyWarningLevel();
			if(earlyWarningLevel != null)
				earlyWarningEventReviewListResponse.setEarlyWarningLevelDesc(EarlywarningLevel.valueOf(earlyWarningLevel).desc());
			earlyWarningEventReviewListResponse.setApplyTypeDesc(EarlywarningEventAction.valueOf(earlyWarningEventReviewListResponse.getApplyType()).desc());
			earlyWarningEventReviewListResponse.setStateDesc(EarlywarningReviewState.valueOf(earlyWarningEventReviewListResponse.getState()).desc());
		}
		return ListResult.success(earlywarningEventReviewListResponses, earlyWarningEventReviewCustomMapper.queryEarlyWarningEventReviewCount(earlyWarningEventReviewListRequest));
	}

	@Override
	public EarlyWarningEventReviewDTO queryEarlyWarningEventReview(String earlywarningEventReviewId) {
		EarlywarningEventReview earlywarningEventReview = earlywarningEventReviewMapper.selectByPrimaryKey(earlywarningEventReviewId);
		if(earlywarningEventReview == null)
			throw WebException.instance(ReturnCode.TP4008);
		return EarlyWarningEventReviewConvertor.convertEarlywarningEventReview2EarlyWarningEventReviewDTO(earlywarningEventReview);
	}

}
