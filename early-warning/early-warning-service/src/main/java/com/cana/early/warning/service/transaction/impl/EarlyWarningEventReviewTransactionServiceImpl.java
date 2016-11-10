package com.cana.early.warning.service.transaction.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventReviewMapper;
import com.cana.early.warning.dao.po.EarlywarningCustomerExample;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventReview;
import com.cana.early.warning.service.transaction.IEarlyWarningEventReviewTransactionService;
import com.cana.early.warning.service.utils.IEarlyWarningServiceHelper;
import com.cana.vbam.common.early.warning.dto.AuditEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlywarningLockCustomerRequest;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class EarlyWarningEventReviewTransactionServiceImpl implements IEarlyWarningEventReviewTransactionService {

	@Resource
	private IEarlyWarningServiceHelper earlywarningServiceHelper;
	
	@Resource
	private EarlywarningCustomerMapper earlywarningCustomerMapper;
	
	@Resource
	private EarlywarningEventReviewMapper earlywarningEventReviewMapper;
	
	@Resource
	private EarlywarningEventMapper earlywarningEventMapper;
	
	@Override
	public void auditEarlyWarningEventReview(AuditEarlyWarningEventRequest auditEarlyWarningEventRequest) {
		EarlywarningEventReview earlywarningEventReview = checkAndLockEarlyWarningEventReview(auditEarlyWarningEventRequest.getEarlywarningEventReviewId());
		EarlywarningLockCustomerRequest earlywarningLockCustomerRequest = earlywarningServiceHelper.generateEarlywarningLockCustomerRequest(earlywarningEventReview.getProductId(), earlywarningEventReview.getFinanceId(), earlywarningEventReview.getFinanceCompany(), earlywarningEventReview.getOutCustomerId());
		if(auditEarlyWarningEventRequest.isResult())
			earlywarningServiceHelper.lockCustomer(earlywarningLockCustomerRequest);
		updateEarlywarningEventReview(earlywarningEventReview, auditEarlyWarningEventRequest);
		updateEarlywarningEvent(earlywarningEventReview.getEventId(), earlywarningEventReview.getApplyType(), auditEarlyWarningEventRequest);
		if(auditEarlyWarningEventRequest.isResult())
			earlywarningServiceHelper.updateCustomerEarlywarningLevel(earlywarningLockCustomerRequest);
	}	

	/**
	 * 检查预警审核表
	 * @param earlywarningEventReviewId
	 * @return
	 */
	private EarlywarningEventReview checkAndLockEarlyWarningEventReview(String earlywarningEventReviewId) {
		EarlywarningEventReview earlywarningEventReview = earlywarningEventReviewMapper.lockByPrimaryKey(earlywarningEventReviewId);
		if(earlywarningEventReview == null)
			throw WebException.instance(ReturnCode.TP4008);
		if (!EarlywarningReviewState.wait_for_review.name().equals(earlywarningEventReview.getState()))
			throw WebException.instance(ReturnCode.TP4009);
		return earlywarningEventReview;
	}
	
	/**
	 * 更新预警审核记录
	 * @param earlywarningEventReview
	 * @param auditEarlyWarningEventRequest
	 */
	private void updateEarlywarningEventReview(EarlywarningEventReview earlywarningEventReview, AuditEarlyWarningEventRequest auditEarlyWarningEventRequest) {
		EarlywarningCustomerExample example = new EarlywarningCustomerExample();
		example.createCriteria().andProductIdEqualTo(earlywarningEventReview.getProductId()).andFinanceIdEqualTo(earlywarningEventReview.getFinanceId());
		earlywarningEventReview.setPrevLevel(earlywarningCustomerMapper.lockByExample(example).get(0).getLevel());
		earlywarningEventReview.setReviewerUserId(auditEarlyWarningEventRequest.getUserId());
		earlywarningEventReview.setReviewerRealName(auditEarlyWarningEventRequest.getRealName());
		earlywarningEventReview.setReviewTime(new Date());
		earlywarningEventReview.setUpdateTime(earlywarningEventReview.getReviewTime());
		if(auditEarlyWarningEventRequest.isResult())
			earlywarningEventReview.setState(EarlywarningReviewState.review_pass.name());
		else
			earlywarningEventReview.setState(EarlywarningReviewState.review_fail.name());
		earlywarningEventReviewMapper.updateByPrimaryKeySelective(earlywarningEventReview);
	}
	
	/**
	 * 更新对应的预警事件
	 * @param earlywarningEventId 预警事件ID
	 * @param applyType 审核类型（新增or解除）
	 * @param auditEarlyWarningEventRequest 审核请求
	 */
	private void updateEarlywarningEvent(String earlywarningEventId, String applyType, AuditEarlyWarningEventRequest auditEarlyWarningEventRequest) {
		EarlywarningEvent earlywarningEvent = earlywarningEventMapper.selectByPrimaryKey(earlywarningEventId);
		earlywarningEvent.setUpdateTime(new Date());
		
		if(EarlywarningEventAction.add.name().equals(applyType))
			earlywarningEvent.setEntryReviewTime(earlywarningEvent.getUpdateTime());
		else {
			earlywarningEvent.setCancelReviewTime(earlywarningEvent.getUpdateTime());
			earlywarningEvent.setCancelUserId(auditEarlyWarningEventRequest.getUserId());
			earlywarningEvent.setCancelRealName(auditEarlyWarningEventRequest.getRealName());
		}
		
		boolean result = auditEarlyWarningEventRequest.isResult();
		
		if(EarlywarningEventAction.add.name().equals(applyType) && !result)
			earlywarningEvent.setState(EarlywarningEventState.add_review_fail.name());
		else if(EarlywarningEventAction.cancel.name().equals(applyType) && result)
			earlywarningEvent.setState(EarlywarningEventState.cancel.name());
		else
			earlywarningEvent.setState(EarlywarningEventState.effective.name());
		
		earlywarningEventMapper.updateByPrimaryKeySelective(earlywarningEvent);
	}
	
}
