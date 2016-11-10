package com.cana.early.warning.server.api.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.early.warning.service.IEarlyWarningCustomerService;
import com.cana.early.warning.service.IEarlyWarningEventReviewService;
import com.cana.early.warning.service.IEarlyWarningEventService;
import com.cana.early.warning.service.IEarlyWarningLevelChangeHistoryService;
import com.cana.early.warning.service.transaction.IEarlyWarningEventReviewTransactionService;
import com.cana.early.warning.service.transaction.IEarlyWarningEventTransactionService;
import com.cana.early.warning.service.utils.EarlyWarningProperties;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.AuditEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.CanelEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerExcelDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningSystemEventRepresent;
import com.cana.vbam.common.early.warning.dto.MonitorDataEarlyWarningExtra;
import com.cana.vbam.common.early.warning.dto.OverdueDataEarlyWarningExtra;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventSubCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

public class EarlyWarningApiImpl implements IEarlyWarningApi {

	@Resource
	private IEarlyWarningEventService earlyWarningEventServiceImpl;
	
	@Resource
	private IEarlyWarningEventReviewTransactionService earlyWarningEventReviewTransactionServiceImpl;
	
	@Resource
	private IEarlyWarningEventTransactionService earlyWarningEventTransactionServiceImpl;
	
	@Resource
	private IEarlyWarningCustomerService earlywarningCustomerServiceImpl;
	
	@Resource
	private IEarlyWarningEventReviewService earlyWarningEventReviewServiceImpl;
	
	@Resource
	private IEarlyWarningLevelChangeHistoryService earlyWarningLevelChangeHistoryServiceImpl;
	
	@Override
	public void addEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		checkManualEvent(earlyWarningManualEventDTO);
		earlyWarningEventServiceImpl.addEarlyWarningManualEvent(earlyWarningManualEventDTO);
	}

	@Override
	public void canaelEarlyWarningEvent(CanelEarlyWarningEventRequest canelEarlyWarningEventRequest) {
		earlyWarningEventTransactionServiceImpl.cancelEarlyWarningEvent(canelEarlyWarningEventRequest);
	}

	@Override
	public void auditEarlyWarningEventReview(AuditEarlyWarningEventRequest auditEarlyWarningEventRequest) {
		if(StringUtils.isBlank(auditEarlyWarningEventRequest.getEarlywarningEventReviewId()))
			throw WebException.instance(ReturnCode.TP4008);
		earlyWarningEventReviewTransactionServiceImpl.auditEarlyWarningEventReview(auditEarlyWarningEventRequest);
	}
	
	@Override
	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		earlyWarningCommonRequest.setEarlyWarningLevel(TransferearlyWarningLevel(earlyWarningCommonRequest.getEarlyWarningLevel()));
		return earlywarningCustomerServiceImpl.queryEarlyWarningCustomer(earlyWarningCommonRequest);
	}

	@Override
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		earlyWarningCommonRequest.setEarlyWarningLevel(TransferearlyWarningLevel(earlyWarningCommonRequest.getEarlyWarningLevel()));
		return earlywarningCustomerServiceImpl.queryEarlyWarningCustomerInformation(earlyWarningCommonRequest);
	}
	
	@Override
	public Map<String, Long> queryEarlyWarningStandard(String earlywarningLevelStr) {
		return earlywarningCustomerServiceImpl.queryEarlyWarningStandard(checkEarlyWarningLevel(earlywarningLevelStr));
	}
	
	@Override
	public Map<EarlywarningLevel, String> getPredictEarlyWarningLevel(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO, String type, String companyName, String outCustomerName) {
		return earlyWarningEventServiceImpl.getPredictEarlyWarningLevel(earlyWarningEventDetailDTO, checkEarlyWarningAction(type), companyName, outCustomerName);
	}

	@Override
	public EarlyWarningEventDetailDTO queryEarlyWarningEventDetail(String earlywarningEventId) {
		if(StringUtils.isBlank(earlywarningEventId))
			throw WebException.instance(ReturnCode.TP4008);
		return earlyWarningEventServiceImpl.queryEarlyWarningEventDetail(earlywarningEventId);
	}
	
	@Override
	public EarlyWaringEventTypeListResponse queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		if((StringUtils.isBlank(earlyWarningCommonRequest.getCompanyName()) || StringUtils.isBlank(earlyWarningCommonRequest.getOutCustomerName())) && (StringUtils.isBlank(earlyWarningCommonRequest.getMemberId()) || StringUtils.isBlank(earlyWarningCommonRequest.getOutCustomerId())))
			throw WebException.instance(ReturnCode.TP4013);
		return earlyWarningEventServiceImpl.queryEarlyWarningTypeList(earlyWarningCommonRequest);
	}
	
	@Override
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest) {
		if(StringUtils.isBlank(earlyWarningEventHistoryRequest.getMemberId()))
			throw WebException.instance(ReturnCode.TP4011);
		String earlywarningType = earlyWarningEventHistoryRequest.getEarlywarningType();
		if(StringUtils.isNotBlank(earlywarningType))
			checkEarlywarningEventCategory(earlywarningType);
		checkDate(earlyWarningEventHistoryRequest.getEntryReviewTimeStart(), earlyWarningEventHistoryRequest.getEntryReviewTimeEnd());
		checkDate(earlyWarningEventHistoryRequest.getOccurTimeStart(), earlyWarningEventHistoryRequest.getOccurTimeEnd());
		return earlyWarningEventServiceImpl.queryEarlyWarningEventHistory(earlyWarningEventHistoryRequest);
	}

	@Override
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest) {
		earlyWarningEventReviewListRequest.setCompanyName(StringUtils.trimToNull(earlyWarningEventReviewListRequest.getCompanyName()));
		checkDate(earlyWarningEventReviewListRequest.getAuditTimeStart(), earlyWarningEventReviewListRequest.getAuditTimeEnd());
		String earlywarningLevel = StringUtils.trimToNull(earlyWarningEventReviewListRequest.getEarlywarningLevel());
		if(earlywarningLevel != null && !earlywarningLevel.equals("normal"))
			checkEarlyWarningLevel(earlywarningLevel);
		earlyWarningEventReviewListRequest.setEarlywarningLevel(earlywarningLevel);
		String earlywarningEventAction = StringUtils.trimToNull(earlyWarningEventReviewListRequest.getEarlywarningEventAction());
		if(earlywarningEventAction != null)
			checkEarlyWarningAction(earlywarningEventAction);
		earlyWarningEventReviewListRequest.setEarlywarningEventAction(earlywarningEventAction);
		String earlywarningReviewState = StringUtils.trimToNull(earlyWarningEventReviewListRequest.getEarlywarningReviewState());
		if(earlywarningReviewState != null)
			checkEarlyWarningReviewState(earlywarningReviewState);
		earlyWarningEventReviewListRequest.setEarlywarningReviewState(earlywarningReviewState);
		return earlyWarningEventReviewServiceImpl.queryEarlyWarningEventReview(earlyWarningEventReviewListRequest);
	}

	@Override
	public EarlyWarningEventReviewDTO queryEarlyWarningEventReview(String earlywarningEventReviewId) {
		if(StringUtils.isBlank(earlywarningEventReviewId))
			throw WebException.instance(ReturnCode.TP4008);
		return earlyWarningEventReviewServiceImpl.queryEarlyWarningEventReview(earlywarningEventReviewId);
	}
	
	@Override
	public ListResult<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest) {
		checkDate(earlyWarningLevelChangeHistoryRequest.getInTimeStart(), earlyWarningLevelChangeHistoryRequest.getInTimeEnd());
		checkDate(earlyWarningLevelChangeHistoryRequest.getOutTimeStart(), earlyWarningLevelChangeHistoryRequest.getOutTimeEnd());
		String earlywarningLevel = StringUtils.trimToNull(earlyWarningLevelChangeHistoryRequest.getEarlywarningLevel());
		if(earlywarningLevel != null)
			checkEarlyWarningLevel(earlywarningLevel);
		earlyWarningLevelChangeHistoryRequest.setEarlywarningLevel(earlywarningLevel);
		return earlyWarningLevelChangeHistoryServiceImpl.queryEarlyWarningLevelChangeHistory(earlyWarningLevelChangeHistoryRequest);
	}

	@Override
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId) {
		if(StringUtils.isBlank(memberId) || StringUtils.isBlank(outCustomerId))
			throw WebException.instance(ReturnCode.TP4011);
		if(state != null)
			for (String s : state)
				checkEarlyWarningEventState(s);
		return earlyWarningEventServiceImpl.getSingleEarlyWarningEventCollect(state, productId, memberId, outCustomerId);
	}
	
	@Override
	public List<EarlyWarningCustomerExcelDTO> getEarlyWarningCustomerExcel(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		earlyWarningCommonRequest.setEarlyWarningLevel(TransferearlyWarningLevel(earlyWarningCommonRequest.getEarlyWarningLevel()));
		earlyWarningCommonRequest.setPage(0);
		List<EarlyWarningCustomerResponse> earlyWarningCustomerResponses = earlywarningCustomerServiceImpl.queryEarlyWarningCustomer(earlyWarningCommonRequest).getData();
		List<EarlyWarningCustomerExcelDTO> returnValue = new ArrayList<EarlyWarningCustomerExcelDTO>();
		for (EarlyWarningCustomerResponse earlyWarningCustomerResponse : earlyWarningCustomerResponses) {
			EarlyWarningCustomerExcelDTO earlyWarningCustomerExcelDTO = new EarlyWarningCustomerExcelDTO();
			BeanUtils.copyProperties(earlyWarningCustomerResponse, earlyWarningCustomerExcelDTO);
			earlyWarningCustomerExcelDTO.setLimit(earlyWarningCustomerResponse.getLimit() == null ? null : MoneyUtil.formatMoney(earlyWarningCustomerResponse.getLimit()));
			earlyWarningCustomerExcelDTO.setResidualLimit(earlyWarningCustomerResponse.getResidualLimit() == null ? null : MoneyUtil.formatMoney(earlyWarningCustomerResponse.getResidualLimit()));
			List<EarlyWarningEventDetailDTO> earlyWarningEventDetailDTOs = getSingleEarlyWarningEventCollect(Arrays.asList(new String[]{EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()}), earlyWarningCommonRequest.getProductId(), earlyWarningCustomerResponse.getMemberId(), earlyWarningCustomerResponse.getOutCustomerId());
			for (EarlyWarningEventDetailDTO earlyWarningEventDetailDTO : earlyWarningEventDetailDTOs) {
				EarlywarningEventCategory earlywarningEventCategory = EarlywarningEventCategory.valueOf(earlyWarningEventDetailDTO.getType());
				switch (earlywarningEventCategory) {
				case SYSTEM:
					EarlywarningEventSubCategory earlywarningEventSubCategory = EarlywarningEventSubCategory.valueOf(earlyWarningEventDetailDTO.getSubType());
					switch (earlywarningEventSubCategory) {
					case COUNTER_GUARANTEE_RATE:
						earlyWarningCustomerExcelDTO.setActualCounterGuaranteeRate(generateSystemRateStr4Excel(earlyWarningEventDetailDTO.getExtraData()));
						earlyWarningCustomerExcelDTO.setStandardCounterGuaranteeRate(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case SALES_CHANGE_RATE:
						earlyWarningCustomerExcelDTO.setActualSalesChangeRate(generateSystemRateStr4Excel(earlyWarningEventDetailDTO.getExtraData()));
						earlyWarningCustomerExcelDTO.setStandardSalesChangeRate(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case SALES_REPAYMENT_RATE:
						earlyWarningCustomerExcelDTO.setActualSalesRepaymentRate(generateSystemRateStr4Excel(earlyWarningEventDetailDTO.getExtraData()));
						earlyWarningCustomerExcelDTO.setStandardSalesRepaymentRate(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case CONTINUE_OVERDUE_NUM:
						OverdueDataEarlyWarningExtra overdueContinueDataEarlyWarningExtra = (OverdueDataEarlyWarningExtra)earlyWarningEventDetailDTO.getExtraData();
						earlyWarningCustomerExcelDTO.setActualContinueOverdueNum(overdueContinueDataEarlyWarningExtra.getNumber() + "次");
						earlyWarningCustomerExcelDTO.setStandardContinueOverdueNum(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard() + "(近" + overdueContinueDataEarlyWarningExtra.getMonth() + "月)(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case TOTAL_OVERDUE_NUM:
						OverdueDataEarlyWarningExtra overdueTotalDataEarlyWarningExtra = (OverdueDataEarlyWarningExtra)earlyWarningEventDetailDTO.getExtraData();
						earlyWarningCustomerExcelDTO.setActualTotalOverdueNum(overdueTotalDataEarlyWarningExtra.getNumber() + "次");
						earlyWarningCustomerExcelDTO.setStandardTotalOverdueNum(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard() + "(近" + overdueTotalDataEarlyWarningExtra.getMonth() + "月)(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					default:
						break;
					}
					break;
				case NEGATIVE_REPORT:
					earlyWarningCustomerExcelDTO.setActualNegativeReport((String)earlyWarningEventDetailDTO.getRepresent());
					earlyWarningCustomerExcelDTO.setStandardNegativeReport((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case COURT_EXECUTION_COMPANY:
					@SuppressWarnings("unchecked") Map<String, Double> companyEarlywarningStandard = (Map<String, Double>) earlyWarningEventDetailDTO.getExtraData();
					earlyWarningCustomerExcelDTO.setActualCourtExecutionCompany(MoneyUtil.formatMoney(earlyWarningEventDetailDTO.getAmount()) + "元，" + companyEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONTH).longValue() + "月内" + ((Double)earlyWarningEventDetailDTO.getRepresent()).longValue() + "次");
					earlyWarningCustomerExcelDTO.setStandardCourtExecutionCompany(EarlywarningEventCategory.COURT_EXECUTION_COMPANY.desc() + (EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()) == EarlywarningLevel.orange ? "≤" : ">") + MoneyUtil.formatMoney(companyEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONEY_THRESHOLD).longValue() * 100) + "元，或近" + companyEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONTH).longValue() + "月次数" + ((EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()) == EarlywarningLevel.orange ? "<" : "≥")) + companyEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONCOMPANY_COUNT_THRESHOLD).longValue() + "次");
					break;
				case COURT_EXECUTION_INDIVIDUAL:
					@SuppressWarnings("unchecked") Map<String, Double> individualEarlywarningStandard = (Map<String, Double>) earlyWarningEventDetailDTO.getExtraData();
					earlyWarningCustomerExcelDTO.setActualCourtExecutionIndividual(MoneyUtil.formatMoney(earlyWarningEventDetailDTO.getAmount()) + "元，" + individualEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONTH).longValue() + "月内" + ((Double)earlyWarningEventDetailDTO.getRepresent()).longValue() + "次");
					earlyWarningCustomerExcelDTO.setStandardCourtExecutionIndividual(EarlywarningEventCategory.COURT_EXECUTION_INDIVIDUAL.desc() + (EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()) == EarlywarningLevel.orange ? "≤" : ">") + MoneyUtil.formatMoney(individualEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD).longValue() * 100) + "元，或近" + individualEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONTH).longValue() + "月次数" + ((EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()) == EarlywarningLevel.orange ? "<" : "≥")) + individualEarlywarningStandard.get(EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD).longValue() + "次");
					break;
				default:
					break;
				}
			}
			returnValue.add(earlyWarningCustomerExcelDTO);
		}
		return returnValue;
	}
	
	private String generateSystemRateStr4Excel(Object extraData) {
		StringBuilder returnValue = new StringBuilder();
		@SuppressWarnings("unchecked")
		List<MonitorDataEarlyWarningExtra> monitorDataEarlyWarningExtras = (List<MonitorDataEarlyWarningExtra>)extraData;
		NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
		for (MonitorDataEarlyWarningExtra monitorDataEarlyWarningExtra : monitorDataEarlyWarningExtras)
			returnValue.append(percent.format(monitorDataEarlyWarningExtra.getMetirc().doubleValue()) + "(" + monitorDataEarlyWarningExtra.getDate() + ")\n");
		return returnValue.toString();
	}
	
	private void checkManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		EarlywarningEventCategory earlywarningEventCategory = checkEarlywarningEventCategory(earlyWarningManualEventDTO.getEarlywarningEventCategory());
		if(earlywarningEventCategory == EarlywarningEventCategory.NEGATIVE_REPORT || earlywarningEventCategory == EarlywarningEventCategory.OTHER)
			try {
				EarlywarningLevel.valueOf(earlyWarningManualEventDTO.getEarlywarningLevel());
			} catch (Exception e) {
				throw WebException.instance(ReturnCode.TP4005);
			}
		if(StringUtils.isBlank(earlyWarningManualEventDTO.getCompanyName()) || StringUtils.isBlank(earlyWarningManualEventDTO.getOutCustomerName()))
			throw WebException.instance(ReturnCode.TP4025);
	}
	
	private EarlywarningEventCategory checkEarlywarningEventCategory(String earlywarningEventCategoryStr) {
		try {
			return EarlywarningEventCategory.valueOf(earlywarningEventCategoryStr);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP4003);
		}
	}
	
	private String TransferearlyWarningLevel(String earlyWarningLevelStr) {
		String trimed = StringUtils.trimToNull(earlyWarningLevelStr);
		if(trimed != null)
			checkEarlyWarningLevel(trimed);
		return trimed;
	}
	
	private EarlywarningLevel checkEarlyWarningLevel(String earlyWarningLevelStr) {
		try {
			return EarlywarningLevel.valueOf(earlyWarningLevelStr);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP4010);
		}
	}
	
	private EarlywarningEventState checkEarlyWarningEventState(String state) {
		try {
			return EarlywarningEventState.valueOf(state);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP4012);
		}
	}

	private EarlywarningEventAction checkEarlyWarningAction(String action) {
		try {
			return EarlywarningEventAction.valueOf(action);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP4019);
		}
	}
	
	private EarlywarningReviewState checkEarlyWarningReviewState(String earlywarningReviewState) {
		try {
			return EarlywarningReviewState.valueOf(earlywarningReviewState);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP4022);
		}
	}

	private void checkDate(Date start, Date end) {
		if(start != null) {
			if(start.after(new Date()))
				throw WebException.instance(ReturnCode.TP4020);
			if(end != null && start.after(end))
				throw WebException.instance(ReturnCode.TP4021);
		}
	}

}
