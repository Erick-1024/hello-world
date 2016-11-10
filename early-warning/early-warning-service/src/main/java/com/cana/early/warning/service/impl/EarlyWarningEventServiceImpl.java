package com.cana.early.warning.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.api.ICreditApi;
import com.cana.early.warning.dao.mapper.EarlyWarningAndCreditCustomMapper;
import com.cana.early.warning.dao.mapper.EarlyWarningEventCustomMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventMapper;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventExample;
import com.cana.early.warning.dao.po.EarlywarningEventExample.Criteria;
import com.cana.early.warning.service.IEarlyWarningEventService;
import com.cana.early.warning.service.convertors.EarlyWarningEventConvertor;
import com.cana.early.warning.service.transaction.IEarlyWarningEventTransactionService;
import com.cana.early.warning.service.utils.EarlyWarningProperties;
import com.cana.early.warning.service.utils.IEarlyWarningServiceHelper;
import com.cana.repayment.api.IRepaymentProductApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.CourtExecutionLevelResult;
import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class EarlyWarningEventServiceImpl implements IEarlyWarningEventService {

	@Resource
	private IEarlyWarningServiceHelper earlywarningServiceHelper;
	
	@Resource
	private IRepaymentProductApi repaymentProductApiImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@Resource
	private IEarlyWarningEventTransactionService earlywarningEventTransactionServiceImpl;
	
	@Resource
	private EarlywarningEventMapper earlywarningEventMapper;
	
	@Resource
	private EarlywarningCustomerMapper earlywarningCustomerMapper;
	
	@Resource
	private EarlyWarningEventCustomMapper earlywarningEventCustomerMapper;
	
	@Resource
	private EarlyWarningAndCreditCustomMapper earlyWarningAndCreditCustomMapper;
	
	@Override
	public void addEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		if(!repaymentProductApiImpl.isExistProduct(earlyWarningManualEventDTO.getProductId()))
			throw WebException.instance(ReturnCode.TP4001);
		Map<String, String> outCustomerAndMemberIdMap = getOutCustomerIdAndMemberId(earlyWarningManualEventDTO.getProductId(), earlyWarningManualEventDTO.getCompanyName(), earlyWarningManualEventDTO.getOutCustomerName());
		Set<Entry<String, String>> entrys = outCustomerAndMemberIdMap.entrySet();
		for (Entry<String, String> entry : entrys) {
			earlyWarningManualEventDTO.setOutCustomerId(entry.getKey());
			earlyWarningManualEventDTO.setFinanceId(entry.getValue());
		}
		earlywarningEventTransactionServiceImpl.submitEarlyWarningManualEvent(earlyWarningManualEventDTO);
	}
	
	@Override
	public EarlyWarningEventDetailDTO queryEarlyWarningEventDetail(String earlywarningEventId) {
		EarlywarningEvent earlywarningEvent = earlywarningEventMapper.selectByPrimaryKey(earlywarningEventId);
		if(earlywarningEvent == null)
			throw WebException.instance(ReturnCode.TP4006);
		return EarlyWarningEventConvertor.convertEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(earlywarningEvent);
	}
	
	@Override
	public EarlyWarningEventDetailDTO queryYundaexEarlyWarningEventDetail(String earlywarningEventId) {
		EarlywarningEvent earlywarningEvent = earlywarningEventMapper.selectByPrimaryKey(earlywarningEventId);
		if(earlywarningEvent == null)
			throw WebException.instance(ReturnCode.TP4006);
		return EarlyWarningEventConvertor.convertYundaexEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(earlywarningEvent);
	}

	@Override
	public EarlyWaringEventTypeListResponse queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		EarlyWaringEventTypeListResponse earlyWaringEventTypeListResponse = earlyWarningAndCreditCustomMapper.queryEarlyWarningTypeList(earlyWarningCommonRequest);
		if(earlyWaringEventTypeListResponse == null)
			throw WebException.instance(ReturnCode.TP4014);
		String earlywaringLevelStr = earlyWaringEventTypeListResponse.getEarlywaringLevel();
		earlyWaringEventTypeListResponse.setEralywaringLevelDesc(earlywaringLevelStr == null ? "正常" : EarlywarningLevel.valueOf(earlywaringLevelStr).desc());
		List<EarlyWaringEventTypeDTO> earlywaringEventTypeDTOs = earlywarningEventCustomerMapper.getEarlyWaringEventTypeGether(earlyWaringEventTypeListResponse.getMemberId(), earlyWaringEventTypeListResponse.getOutCustomerId(), earlyWaringEventTypeListResponse.getProductId(), Arrays.asList(EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()));
		earlyWaringEventTypeListResponse.setEarlyWaringEventTypeDTOs(earlywaringEventTypeDTOs);
		return earlyWaringEventTypeListResponse;
	}

	@Override
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest) {
		EarlywarningEventExample earlywarningEventExample = new EarlywarningEventExample();
		earlywarningEventExample.setOrderByClause(earlyWarningEventHistoryRequest.getOrder());
		int pageSize = earlyWarningEventHistoryRequest.getPageSize();
		earlywarningEventExample.setLimitStart((earlyWarningEventHistoryRequest.getPage() - 1) * pageSize);
		earlywarningEventExample.setLimitEnd(pageSize);
		Criteria criteria = earlywarningEventExample.createCriteria();
		criteria.andFinanceIdEqualTo(earlyWarningEventHistoryRequest.getMemberId()).andProductIdEqualTo(earlyWarningEventHistoryRequest.getProductId());
		Date date = earlyWarningEventHistoryRequest.getEntryReviewTimeStart();
		if(date != null)
			criteria.andEntryReviewTimeGreaterThanOrEqualTo(date);
		date = earlyWarningEventHistoryRequest.getEntryReviewTimeEnd();
		if(date != null)
			criteria.andEntryReviewTimeLessThanOrEqualTo(date);
		date = earlyWarningEventHistoryRequest.getOccurTimeStart();
		if(date != null)
			criteria.andOccurTimeGreaterThanOrEqualTo(date);
		date = earlyWarningEventHistoryRequest.getOccurTimeEnd();
		if(date != null)
			criteria.andOccurTimeLessThanOrEqualTo(date);
		String earlywaringEventType = earlyWarningEventHistoryRequest.getEarlywarningType();
		if(earlywaringEventType != null)
			criteria.andTypeEqualTo(earlywaringEventType);
		Boolean isCancel = earlyWarningEventHistoryRequest.getIsCancel();
		if(isCancel != null)
			if(isCancel)
				criteria.andStateIn(Arrays.asList(EarlywarningEventState.cancel.name()));
			else
				criteria.andStateIn(Arrays.asList(EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()));
		else
			criteria.andStateIn(Arrays.asList(EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name(), EarlywarningEventState.cancel.name()));
		return ListResult.success(EarlyWarningEventConvertor.convertEarlyWarningEvent2EarlyWarningEventHistoryResponse(earlywarningEventMapper.selectByExample(earlywarningEventExample)), earlywarningEventMapper.countByExample(earlywarningEventExample));
	}

	@Override
	public Map<EarlywarningLevel, String> getPredictEarlyWarningLevel(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO, EarlywarningEventAction earlywarningEventAction, String companyName, String outCustomerName) {
		if(earlywarningEventAction == EarlywarningEventAction.add) {
			if((StringUtils.isBlank(companyName) || StringUtils.isBlank(outCustomerName)) && (StringUtils.isBlank(earlyWarningEventDetailDTO.getFinanceId()) || StringUtils.isBlank(earlyWarningEventDetailDTO.getOutCustomerId())))
				throw WebException.instance(ReturnCode.TP4025);
			if(StringUtils.isNotBlank(companyName)) {
				Map<String, String> outCustomerAndMemberIdMap = getOutCustomerIdAndMemberId(earlyWarningEventDetailDTO.getProductId(), companyName, outCustomerName);
				Set<Entry<String, String>> entrys = outCustomerAndMemberIdMap.entrySet();
				for (Entry<String, String> entry : entrys) {
					earlyWarningEventDetailDTO.setOutCustomerId(entry.getKey());
					earlyWarningEventDetailDTO.setFinanceId(entry.getValue());
				}
			}
		}
		Map<EarlywarningLevel, String> returnValue = new HashMap<>();
		try {
			EarlywarningLevel earlywarningLevel = null;
			switch (earlywarningEventAction) {
			case add:
				earlywarningLevel = earlywarningServiceHelper.preCalcCustomerEarlywarningLevelWhenAdd(EarlyWarningEventConvertor.convertEarlyWarningEventDetailDTO2EarlyWarningEvent(earlyWarningEventDetailDTO));
				break;
			case cancel:
				earlywarningLevel = earlywarningServiceHelper.preCalcCustomerEarlywarningLevelWhenCancel(earlyWarningEventDetailDTO.getId());
				break;
			default:
				break;
			}
			returnValue.put(earlywarningLevel, earlywarningLevel == null ? null : earlywarningLevel.desc());
			return returnValue;
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.ERROR, e.getMessage());
		}
	}

	@Override
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId) {
		List<EarlywarningEvent> allEvents = getEventsWithState(state, productId, memberId, outCustomerId);
		Iterator<EarlywarningEvent> iterator = allEvents.iterator();
		List<EarlywarningEvent> otherEvents = new ArrayList<>();
		List<EarlywarningEvent> companyCourtExectionEvents = new ArrayList<>();
		List<EarlywarningEvent> individualCourtExectionEvents = new ArrayList<>();
		while (iterator.hasNext()) {
			EarlywarningEvent earlywarningEvent = iterator.next();
			if(!EarlywarningEventCategory.SYSTEM.name().equals(earlywarningEvent.getType())) {
				if(EarlywarningEventCategory.COURT_EXECUTION_COMPANY.name().equals(earlywarningEvent.getType()))
					companyCourtExectionEvents.add(earlywarningEvent);
				else if(EarlywarningEventCategory.COURT_EXECUTION_INDIVIDUAL.name().equals(earlywarningEvent.getType()))
					individualCourtExectionEvents.add(earlywarningEvent);
				else
					otherEvents.add(earlywarningEvent);
				iterator.remove();
			}
		}
		EarlywarningEvent companyEvent = getCollectEarlyWarningEvent(earlywarningServiceHelper.getHighestCourtExecutionLevel(companyCourtExectionEvents, EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONTH, EarlyWarningProperties.COURTEXECUTIONCOMPANY_COUNT_THRESHOLD, EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONEY_THRESHOLD), EarlywarningEventCategory.COURT_EXECUTION_COMPANY);
		if(companyEvent != null)
			allEvents.add(companyEvent);
		EarlywarningEvent individualEvent = getCollectEarlyWarningEvent(earlywarningServiceHelper.getHighestCourtExecutionLevel(individualCourtExectionEvents, EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONTH, EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD, EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD), EarlywarningEventCategory.COURT_EXECUTION_INDIVIDUAL);
		if(individualEvent != null)
			allEvents.add(individualEvent);
		if(otherEvents.size() != 0) {
			EarlywarningLevel otherHighestLevel = EarlywarningLevel.yellow;
			for (EarlywarningEvent earlywarningEvent : otherEvents) {
				EarlywarningLevel level = EarlywarningLevel.valueOf(earlywarningEvent.getLevel());
				if(level.compareTo(otherHighestLevel) > 0)
					otherHighestLevel = level;
			}
			EarlywarningEvent otherEearlyWarningEvent = new EarlywarningEvent();
			otherEearlyWarningEvent.setType(EarlywarningEventCategory.NEGATIVE_REPORT.name());
			otherEearlyWarningEvent.setLevel(otherHighestLevel.name());
			otherEearlyWarningEvent.setRepresent("有");
			otherEearlyWarningEvent.setExtraData("有");
			otherEearlyWarningEvent.setSubType(EarlywarningEventCategory.NEGATIVE_REPORT.desc());
			allEvents.add(otherEearlyWarningEvent);
		}
		return EarlyWarningEventConvertor.convertEarlyWarningEvent2EarlyEarlyWarningEventDetailDTO(allEvents);
	}

	private List<EarlywarningEvent> getEventsWithState(List<String> state, String productId, String memberId, String outCustomerId) {
		EarlywarningEventExample earlywarningEventExample = new EarlywarningEventExample();
		Criteria criteria = earlywarningEventExample.createCriteria();
		criteria.andProductIdEqualTo(productId).andFinanceIdEqualTo(memberId).andOutCustomerIdEqualTo(outCustomerId);
		if(state !=null)
			criteria.andStateIn(state);
		return earlywarningEventMapper.selectByExample(earlywarningEventExample);
	}
	
	private EarlywarningEvent getCollectEarlyWarningEvent(CourtExecutionLevelResult courtExecutionLevelResult, EarlywarningEventCategory earlywarningEventCategory) {
		EarlywarningEvent event = null;
		if(courtExecutionLevelResult.getEarlywarningLevel() != null) {
			event = new EarlywarningEvent();
			event.setType(earlywarningEventCategory.name());
			event.setLevel(courtExecutionLevelResult.getEarlywarningLevel().name());
			event.setAmount(courtExecutionLevelResult.getTotalExecutionAmount());
			event.setRepresent(String.valueOf(courtExecutionLevelResult.getCount()));
			event.setExtraData(new Gson().toJson(EarlyWarningProperties.queryEarlyWarningStandard(courtExecutionLevelResult.getEarlywarningLevel())));
			event.setSubType(earlywarningEventCategory.desc());
		}
		return event;
	}
	
	private Map<String, String> getOutCustomerIdAndMemberId(String productId, String companyName, String outCustomerName) {
		Map<String, String> returnValue = creditApiImpl.queryOutCustomerIdAndMemberId(productId, companyName, outCustomerName);
		if(returnValue.size() == 0)
			throw WebException.instance(ReturnCode.TP4002);
		return returnValue;
	}

}
