package com.cana.early.warning.service.convertors;

import org.springframework.beans.BeanUtils;

import com.cana.early.warning.dao.po.EarlywarningEventReview;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewDTO;

public class EarlyWarningEventReviewConvertor {

	public static EarlyWarningEventReviewDTO convertEarlywarningEventReview2EarlyWarningEventReviewDTO(EarlywarningEventReview earlywarningEventReview) {
		EarlyWarningEventReviewDTO earlyWarningEventReviewDTO = new EarlyWarningEventReviewDTO();
		BeanUtils.copyProperties(earlywarningEventReview, earlyWarningEventReviewDTO);
		return earlyWarningEventReviewDTO;
	}
	
}
