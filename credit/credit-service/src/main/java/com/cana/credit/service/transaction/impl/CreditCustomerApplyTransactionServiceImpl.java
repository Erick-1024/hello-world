package com.cana.credit.service.transaction.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.WhiteCustomerAndRuleCustomMapper;
import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.CustomerApplyExample;
import com.cana.credit.dao.utils.IDGenerator;
import com.cana.credit.service.convertors.CustomerApplyConvertor;
import com.cana.credit.service.transaction.ICreditCustomerApplyTransactionService;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.flight.finance.service.utils.IFlightFinanceServiceHelper;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class CreditCustomerApplyTransactionServiceImpl implements ICreditCustomerApplyTransactionService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;
	
	@Resource
	private CustomerApplyMapper customerApplyMapper;
	
	@Resource
	private WhiteCustomerAndRuleCustomMapper whiteCustomerAndRuleCustomMapper;
	
	@Resource
	private IFlightFinanceServiceHelper flightFinanceServiceHelper;
	
	@Override
	public CustomerApply saveTravelzenAuditResult(CustomerApplyRequestDTO customerApplyDTO) throws Exception{
		TzCustomerInfo tzCustomerInfo = lockTzCustomerInfo(customerApplyDTO.getCustomerId());
		if(tzCustomerInfo == null)
			throw WebException.instance(ReturnCode.TP1146);
		CustomerApply customerApply = new CustomerApply();
		CustomerApplyConvertor.convertCustomerApplyDTO2DAO(customerApplyDTO, customerApply);
		CustomerApply beforeApply = getNewestCustomerApply(customerApplyDTO.getCustomerId());
		if (beforeApply != null)
			checkAuditStatus(customerApplyDTO, beforeApply);
		customerApply.setId(IDGenerator.generateCustomerApplyId());
		boolean inWhitelist = whiteCustomerAndRuleCustomMapper.getAvailableWhiteCustomerIds().contains(customerApplyDTO.getCustomerId());
		logger.info("真旅申请保存,真旅客户id为{},是白名单吗？{}",customerApplyDTO.getCustomerId(),inWhitelist);
		customerApply.setInWhitelist(inWhitelist);
		//跑准入规则
		customerApply.setAccessAutomaticState(AccessAutomaticState.WAIT.name());
		customerApply.setTzCustomerName(getTzCustomerName(customerApply));
		customerApply.setSaleData(new Gson().toJson(flightFinanceServiceHelper.getMonthAverageSales(customerApply.getTzCustomerId(), customerApply.getApplyDate(),18)));
		logger.info("插入customer_apply记录");
		customerApplyMapper.insertSelective(customerApply);
		return customerApply;
	}

	private TzCustomerInfo lockTzCustomerInfo(String tzCustomerId){
		TzCustomerInfoExample example = new TzCustomerInfoExample();
		example.createCriteria().andTzCustomerIdEqualTo(tzCustomerId);
		return tzCustomerInfoMapper.lockByExample(example).get(0);
	}
	
	/**
	 * 获取最近更新的客户资料
	 */
	private CustomerApply getNewestCustomerApply(String tzCustomerId) {
		CustomerApply customerApply = null;
		CustomerApplyExample example = new CustomerApplyExample();
		example.createCriteria().andTzCustomerIdEqualTo(tzCustomerId);
		example.setOrderByClause("update_time desc");
		List<CustomerApply> customerApplies = customerApplyMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(customerApplies)) {
			customerApply = customerApplies.get(0);
		}
		return customerApply;
	}
	
	/**
	 * 检查已存在客户审核状态
	 * 
	 * @param customerId
	 * @throws ParseException
	 */
	private void checkAuditStatus(CustomerApplyRequestDTO customerApplyDTO, CustomerApply customerApply) throws Exception {
		String tzCustomerId = customerApply.getTzCustomerId();
		String automaticStatus = customerApply.getAccessAutomaticState();
		Date curApplyDate = DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, customerApplyDTO.getApplyTime()).toDate();
		if (AccessAutomaticState.NOTACCESS.name().equals(automaticStatus)) {
			checkTimeSpanInThreeMonths(customerApply.getApplyDate(), curApplyDate);
			logger.info("tzCustomerId:{} 准入审核未通过，但已超过三个月，允许插入", tzCustomerId);
		} else if (AccessAutomaticState.ACCESS.name().equals(automaticStatus)) {
			String manualStatus = customerApply.getAccessManualState();
			if (AccessManualState.ACCESS.name().equals(manualStatus)) {
				logger.info("tzCustomerId:{} 审核已通过", tzCustomerId);
				throw WebException.instance(ReturnCode.TP1129);
			} else if (AccessManualState.NOTACCESS.name().equals(manualStatus)) {
				checkTimeSpanInThreeMonths(customerApply.getApplyDate(), curApplyDate);
				logger.info("tzCustomerId:{} 人工审核未通过，但已超过三个月，允许插入", tzCustomerId);
			}else{
				logger.info("tzCustomerId:{} 待人工审核", tzCustomerId);
				throw WebException.instance(ReturnCode.TP1127);
			} 
		} else {
			logger.info("tzCustomerId:{} 待准入审核", tzCustomerId);
			throw WebException.instance(ReturnCode.TP1126);
		}
	}
	
	/**
	 * 判断审核资料未通过的客户申请时间间隔是否在三个月内
	 */
	public void checkTimeSpanInThreeMonths(Date oriApplyDate, Date curApplyDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oriApplyDate);
		calendar.add(Calendar.MONTH, 3);
		if (calendar.getTime().getTime() >= curApplyDate.getTime()) {
			throw WebException.instance(ReturnCode.TP1128);
		}
	}
	
	private String getTzCustomerName(CustomerApply customerApply) {
		if (StringUtils.isNotEmpty(customerApply.getTzCustomerName()))
			return customerApply.getTzCustomerName();

		TzCustomerInfoExample example = new TzCustomerInfoExample();
		example.createCriteria().andTzCustomerIdEqualTo(customerApply.getTzCustomerId());
		List<TzCustomerInfo> customerInfos = tzCustomerInfoMapper.selectByExample(example);

		if (CollectionUtils.isNotEmpty(customerInfos)) {
			String names = customerInfos.get(0).getCustomerNames();
			if (StringUtils.isNotBlank(names)) {
				String[] nameArr = names.split("\\|\\|");
				for (int i = nameArr.length - 1; i >=0; --i)
					if (StringUtils.isNotBlank(nameArr[i]))
						return nameArr[i];
			}
		}
		return customerApply.getCompanyName();
	}
	
}
