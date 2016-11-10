package com.cana.early.warning.dao.mapper;

import java.util.List;

import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;

public interface EarlyWarningEventReviewCustomMapper {

	public List<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest);
	
	public int queryEarlyWarningEventReviewCount(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest);
	
}
