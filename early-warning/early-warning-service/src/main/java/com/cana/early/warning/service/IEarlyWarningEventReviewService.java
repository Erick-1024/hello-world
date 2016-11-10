package com.cana.early.warning.service;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;

public interface IEarlyWarningEventReviewService {

	/**
	 * 获取预警事件审核列表
	 * @param earlyWarningEventReviewListRequest
	 * @return
	 */
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest);
	
	/**
	 * 查询预警审核信息
	 * @param earlywarningEventReviewId 预警审核ID
	 * @return 预警审核信息
	 */
	public EarlyWarningEventReviewDTO queryEarlyWarningEventReview(String earlywarningEventReviewId);
	
}
