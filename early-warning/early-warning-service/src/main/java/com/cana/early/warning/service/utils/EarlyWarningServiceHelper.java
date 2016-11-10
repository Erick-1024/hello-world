package com.cana.early.warning.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningLevelChangeHistoryMapper;
import com.cana.early.warning.dao.po.EarlywarningCustomer;
import com.cana.early.warning.dao.po.EarlywarningCustomerExample;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventExample;
import com.cana.early.warning.dao.po.EarlywarningLevelChangeHistory;
import com.cana.early.warning.dao.po.EarlywarningLevelChangeHistoryExample;
import com.cana.vbam.common.early.warning.dto.CourtExecutionLevelResult;
import com.cana.vbam.common.early.warning.dto.EarlywarningLockCustomerRequest;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class EarlyWarningServiceHelper implements IEarlyWarningServiceHelper {

	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private EarlywarningCustomerMapper earlywarningCustomerMapper;
	
	@Resource
	private EarlywarningEventMapper earlywarningEventMapper;
	
	@Resource
	private EarlywarningLevelChangeHistoryMapper earlywarningLevelChangeHistoryMapper;
	
	@Override
	public EarlywarningCustomer addOrlockCustomer(EarlywarningLockCustomerRequest request) {
		checkEarlywarningLockCustomerRequest(request);
		EarlywarningCustomer customer = lockCustomer(request);
		if(customer == null)
			customer = addCustomer(request);
		return customer;

	}
	@Override
	public EarlywarningCustomer lockCustomer(EarlywarningLockCustomerRequest request) {
		EarlywarningCustomerExample example = new EarlywarningCustomerExample();
		example.createCriteria().andProductIdEqualTo(request.getProductId()).andFinanceIdEqualTo(request.getFinanceId()).andOutCustomerIdEqualTo(request.getOutCustomerId());
		List<EarlywarningCustomer> customerList = earlywarningCustomerMapper.lockByExample(example);
		if(customerList.size() == 0)
			return null;
		else
			return customerList.get(0);
	}

	@Override
	public EarlywarningCustomer addCustomer(EarlywarningLockCustomerRequest request) {
		EarlywarningCustomer earlywarningCustomer = new EarlywarningCustomer();
		earlywarningCustomer.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EARLYWARNING_CUSTOMER_ID, 4));
		earlywarningCustomer.setFinanceCompany(request.getFinanceCompany());
		earlywarningCustomer.setProductId(request.getProductId());
		earlywarningCustomer.setFinanceId(request.getFinanceId());
		earlywarningCustomer.setOutCustomerId(request.getOutCustomerId());
		earlywarningCustomer.setCreateTime(new Date());
		earlywarningCustomer.setUpdateTime(earlywarningCustomer.getCreateTime());
		earlywarningCustomerMapper.insertSelective(earlywarningCustomer);
		return earlywarningCustomer;
	}
	
	@Override
	public EarlywarningLockCustomerRequest generateEarlywarningLockCustomerRequest(String productId, String financeId, String financeCompany, String outCustomerId) {
		EarlywarningLockCustomerRequest earlywarningLockCustomerRequest = new EarlywarningLockCustomerRequest();
		earlywarningLockCustomerRequest.setFinanceCompany(financeCompany);
		earlywarningLockCustomerRequest.setFinanceId(financeId);
		earlywarningLockCustomerRequest.setProductId(productId);
		earlywarningLockCustomerRequest.setOutCustomerId(outCustomerId);
		return earlywarningLockCustomerRequest;
	}

	@Override
	public EarlywarningCustomer updateCustomerEarlywarningLevel(EarlywarningLockCustomerRequest request) {
		EarlywarningCustomer customer = addOrlockCustomer(request);
		
		List<EarlywarningEvent> allEvents = getEarlywarningEvents(request.getProductId(), request.getFinanceId(), request.getOutCustomerId());
		
		EarlywarningLevel oldLevel = StringUtils.isNotBlank(customer.getLevel()) ? EarlywarningLevel.valueOf(customer.getLevel()) : null;
		
		EarlywarningLevel newLevel = calcNewCustomerEarlywarningLevel(request.getProductId(),allEvents);
		
		boolean changed = generateEarlywarningLevelChangeHistory(customer, oldLevel, newLevel);
		
		if (changed) {
			customer.setLevel(newLevel == null ? null : newLevel.name());
			customer.setUpdateTime(new Date());
			earlywarningCustomerMapper.updateByPrimaryKey(customer);
		}
		
		return customer;
	}
	
	@Override
	public EarlywarningLevel preCalcCustomerEarlywarningLevelWhenAdd(EarlywarningEvent event) throws Exception {
		if(StringUtils.isBlank(event.getProductId()))
			throw new Exception("productId不能为空");
		
		if(StringUtils.isBlank(event.getFinanceId()))
			throw new Exception("financeId不能为空");
		
		if(StringUtils.isBlank(event.getOutCustomerId()))
			throw new Exception("outCustomerId不能为空");
		
		List<EarlywarningEvent> allEvents = getEarlywarningEvents(event.getProductId(), event.getFinanceId(), event.getOutCustomerId());
		allEvents.add(event);
		
		return calcNewCustomerEarlywarningLevel(event.getProductId(),allEvents);
	}
	
	@Override
	public EarlywarningLevel preCalcCustomerEarlywarningLevelWhenCancel(String eventId) throws Exception {
		EarlywarningEvent event = earlywarningEventMapper.selectByPrimaryKey(eventId);
		
		if(event == null)
			throw new Exception("预警事件不存在");
		
		List<EarlywarningEvent> allEvents = getEarlywarningEvents(event.getProductId(), event.getFinanceId(), event.getOutCustomerId());
		
		Iterator<EarlywarningEvent> iter = allEvents.iterator();
		
		while(iter.hasNext()){
			EarlywarningEvent item = iter.next();
			if(event.getId().equals(item.getId())){
				iter.remove();
			}
		}
		
		return calcNewCustomerEarlywarningLevel(event.getProductId(),allEvents);
	}	
	
	@Override
	public CourtExecutionLevelResult getHighestCourtExecutionLevel(List<EarlywarningEvent> events, String courtexecutionMonth, String courtExecutionCountThreshold, String courtExecutionMoneyThreshold){
		
		CourtExecutionLevelResult returnValue = new CourtExecutionLevelResult();
		
		if(CollectionUtils.isEmpty(events))
			return returnValue;
		
		long totalExecutionAmount = 0L;
		long count = 0;
		
		for(EarlywarningEvent event : events){
			totalExecutionAmount += event.getAmount();
			if(event.getOccurTime().compareTo(DateTimeUtil.addMonth(new Date(), -EarlyWarningProperties.getIntFromEarlyWarningProperties(courtexecutionMonth)).toDate()) > 0)
				++count;
		}
		
		returnValue.setCount(count);
		returnValue.setTotalExecutionAmount(totalExecutionAmount);
		
		if(count >= EarlyWarningProperties.getIntFromEarlyWarningProperties(courtExecutionCountThreshold)) {
			returnValue.setEarlywarningLevel(EarlywarningLevel.red);
			return returnValue;
		}
		
		if(count > 0)
			returnValue.setEarlywarningLevel(EarlywarningLevel.orange);
		
		if(totalExecutionAmount > EarlyWarningProperties.getLongFromEarlyWarningPrperties(courtExecutionMoneyThreshold) * 100) {
			returnValue.setEarlywarningLevel(EarlywarningLevel.red);
			return returnValue;
		}
		
		if(totalExecutionAmount > 0)
			returnValue.setEarlywarningLevel(EarlywarningLevel.orange);
		
		return returnValue;
		
	}
	
	/**
	 * 校验加锁请求
	 * @param request
	 * @throws Exception
	 */
	private void checkEarlywarningLockCustomerRequest(EarlywarningLockCustomerRequest request) {
		StringUtil.trimObjectFields(request);
		if(StringUtils.isBlank(request.getProductId()))
			throw WebException.instance("productId不能为空");
		if(StringUtils.isBlank(request.getFinanceId()))
			throw WebException.instance("financeId不能为空");
		if(StringUtils.isBlank(request.getFinanceCompany()))
			throw WebException.instance("financeCompany不能为空");
		if(StringUtils.isBlank(request.getOutCustomerId()))
			throw WebException.instance("outCustomerId不能为空");
	}

	/**
	 * 获取用户的所有预警事件
	 * @param productId
	 * @param financeId
	 * @param outCustomerId
	 * @return
	 */
	private List<EarlywarningEvent> getEarlywarningEvents(String productId, String financeId, String outCustomerId) {
		EarlywarningEventExample example = new EarlywarningEventExample();
		example.createCriteria().andProductIdEqualTo(productId)
								.andFinanceIdEqualTo(financeId)
								.andOutCustomerIdEqualTo(outCustomerId)
								.andStateIn(Arrays.asList(EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()));
		return earlywarningEventMapper.selectByExample(example);
	}
	
	/**
	 * 计算新的预警级别
	 * @param events
	 * @return
	 */
	private EarlywarningLevel calcNewCustomerEarlywarningLevel(String productId, List<EarlywarningEvent> events){
		
		if(StringUtils.isBlank(productId) || CollectionUtils.isEmpty(events))
			return null;
		
		EarlywarningLevel highestLevel = null;
		if(Constants.TRAVELZEN_FINANCE_PRODUCT_ID.equals(productId)){
			highestLevel = getHighestLevelAmongEvents(filterSystemEvents(events, EarlywarningEventCategory.SYSTEM, EarlywarningEventCategory.NEGATIVE_REPORT, EarlywarningEventCategory.OTHER));
			
			EarlywarningLevel highestCourtExecutionCompanyLevel = getHighestCourtExecutionLevel(filterSystemEvents(events, EarlywarningEventCategory.COURT_EXECUTION_COMPANY), EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONTH, EarlyWarningProperties.COURTEXECUTIONCOMPANY_COUNT_THRESHOLD, EarlyWarningProperties.COURTEXECUTIONCOMPANY_MONEY_THRESHOLD).getEarlywarningLevel();
			
			if(highestLevel == null || (highestCourtExecutionCompanyLevel != null && highestLevel.compareTo(highestCourtExecutionCompanyLevel) < 0))
				highestLevel = highestCourtExecutionCompanyLevel;
			
			EarlywarningLevel highestCourtExecutionIndividualLevel = getHighestCourtExecutionLevel(filterSystemEvents(events, EarlywarningEventCategory.COURT_EXECUTION_INDIVIDUAL), EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONTH, EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_COUNT_THRESHOLD, EarlyWarningProperties.COURTEXECUTIONINDIVIDUAL_MONEY_THRESHOLD).getEarlywarningLevel();
			
			if(highestLevel == null || (highestCourtExecutionIndividualLevel != null && highestLevel.compareTo(highestCourtExecutionIndividualLevel) < 0))
				highestLevel = highestCourtExecutionIndividualLevel;
		}else if(Constants.YUNDAEX_FINANCE_PRODUCT_ID.equals(productId)){
			highestLevel = getHighestLevelAmongEvents(events);
		}
		return highestLevel;
	}
	
	/**
	 * 过滤出指定类型的事件
	 * @param events
	 * @return
	 */
	private List<EarlywarningEvent> filterSystemEvents(List<EarlywarningEvent> events, EarlywarningEventCategory... categoryList){
		List<EarlywarningEvent> result = new ArrayList<>();
		if(CollectionUtils.isEmpty(events))
			return result;
		for(EarlywarningEvent event : events){
			for (EarlywarningEventCategory category : categoryList) {
				if (category == EarlywarningEventCategory.valueOf(event.getType())) {
					result.add(event);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取传入参数中最高的预警级别
	 * @param events
	 * @return
	 */
	private EarlywarningLevel getHighestLevelAmongEvents(List<EarlywarningEvent> events){
		
		if(CollectionUtils.isEmpty(events))
			return null;
		
		EarlywarningLevel highestLevel = null;
		
		for(EarlywarningEvent event : events){
			if(StringUtils.isNotBlank(event.getLevel())){
				EarlywarningLevel level = EarlywarningLevel.valueOf(event.getLevel());
				if(highestLevel == null || level.compareTo(highestLevel) > 0){
					highestLevel = level;
				}
			}
		}
		
		return highestLevel;
		
	}
	
	/**
	 * 生成预警级别的变化历史, 如果预警级别有变化返回true,否则返回false
	 * @param customer
	 * @param oldLevel
	 * @param newLevel
	 */
	private boolean generateEarlywarningLevelChangeHistory(EarlywarningCustomer customer, EarlywarningLevel oldLevel, EarlywarningLevel newLevel) {
		
		if(oldLevel == null && newLevel != null){
			generateTransistinRecord(customer, newLevel);
			return true;
		}
		
		if(oldLevel != null && newLevel == null){
			updateLevelChangeOutTime(customer, oldLevel);
			return true;
		}
		
		if(oldLevel != null && newLevel != null && oldLevel != newLevel){
			updateLevelChangeOutTime(customer, oldLevel);
			generateTransistinRecord(customer, newLevel);	
			return true;
		}
		return false;
	}
	
	/**
	 * 生成转入记录
	 * @param customer
	 * @param newLevel
	 */
	private void generateTransistinRecord(EarlywarningCustomer customer, EarlywarningLevel newLevel){
		EarlywarningLevelChangeHistory record = new EarlywarningLevelChangeHistory();
		record.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EARLYWARNING_LEVEL_CHANGE_ID, 4));
		record.setProductId(customer.getProductId());
		record.setFinanceId(customer.getFinanceId());
		record.setFinanceCompany(customer.getFinanceCompany());
		record.setOutCustomerId(customer.getOutCustomerId());
		record.setInTime(new Date());
		record.setLevel(newLevel.name());
		record.setCreateTime(record.getInTime());
		record.setUpdateTime(record.getUpdateTime());
		earlywarningLevelChangeHistoryMapper.insertSelective(record);
	}
	
	/**
	 * 更新转出时间
	 * @param customer
	 * @param oldLevel
	 */
	private void updateLevelChangeOutTime(EarlywarningCustomer customer, EarlywarningLevel oldLevel) {
		
		EarlywarningLevelChangeHistoryExample example = new EarlywarningLevelChangeHistoryExample();
		example.createCriteria().andProductIdEqualTo(customer.getProductId())
		                        .andFinanceIdEqualTo(customer.getFinanceId())
		                        .andOutCustomerIdEqualTo(customer.getOutCustomerId())
		                        .andLevelEqualTo(oldLevel.name())
		                        .andOutTimeIsNull();
		
		EarlywarningLevelChangeHistory newValue = new EarlywarningLevelChangeHistory();
		newValue.setOutTime(new Date());
		newValue.setUpdateTime(newValue.getOutTime());
		
		earlywarningLevelChangeHistoryMapper.updateByExampleSelective(newValue, example);
		
	}

}
