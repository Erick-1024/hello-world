package com.cana.credit.service.impl;


import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.service.IRetryTaskService;
import com.cana.flight.finance.common.dto.AsyncNotifyTzCustomerApplyResult;
import com.cana.flight.finance.common.dto.AsyncNotifyTzCustomerCreditLimitResult;
import com.cana.flight.finance.common.dto.CreditTradeNotifyResult;
import com.cana.flight.finance.common.dto.NotifyTzResultRetryTaskData;
import com.cana.vbam.common.credit.dto.apply.CustomerApply4MemberDTO;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskBackOffPolicy;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskType;

@Service
public class RetryTaskServiceImpl implements IRetryTaskService {

	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@Override
	public void createAuditResultNotify(Institution institution, String customerId, String auditCode, String auditInfo, String notifyUrl) {
		NotifyTzResultRetryTaskData<AsyncNotifyTzCustomerApplyResult> data = new NotifyTzResultRetryTaskData<>(); data = new NotifyTzResultRetryTaskData<>();
		AsyncNotifyTzCustomerApplyResult canaAuditResult = new AsyncNotifyTzCustomerApplyResult();
		canaAuditResult.setRetCode(auditCode);
		canaAuditResult.setRetMsg(auditInfo);
		canaAuditResult.setCustomerId(customerId);
		data.setData(canaAuditResult);
		data.setUrl(notifyUrl);
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.AUDIT_RESULT.name());
		task.setTaskId(institution.name() + UUID.randomUUID().toString());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24 * 60 * 20L);
		task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(data));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
	}

	@Override
	public void createCreditLimiteffect(Institution institution, String customerId, Long totalLimit, String code, String info, String notifyUrl) {
		NotifyTzResultRetryTaskData<AsyncNotifyTzCustomerCreditLimitResult> data = new NotifyTzResultRetryTaskData<>();
		AsyncNotifyTzCustomerCreditLimitResult creditLimitResult = new AsyncNotifyTzCustomerCreditLimitResult();
		creditLimitResult.setRetCode(code);
		creditLimitResult.setCustomerId(customerId);
		creditLimitResult.setLimit(totalLimit);
		creditLimitResult.setRetMsg(info);
		data.setData(creditLimitResult);
		data.setUrl(notifyUrl);
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.CREDIT_LIMIT_EFFECT.name());
		task.setTaskId(institution.name() + customerId);
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24 * 60 * 20L);
		task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(data));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
	}

	@Override
	public void createCreditTradeResult(String notifyUrl, String tradeNo, String tranSeq, String tradeType, String tradeStatus, String tradeSuccessTime, String sign) {
		if (StringUtils.isBlank(notifyUrl)) {
			return;
		}
		CreditTradeNotifyResult creditTradeNotifyResult = new CreditTradeNotifyResult();
		creditTradeNotifyResult.setNotifyUrl(notifyUrl);
		creditTradeNotifyResult.setSign(sign);
		creditTradeNotifyResult.setTradeNo(tradeNo);
		creditTradeNotifyResult.setTradeStatus(tradeStatus);
		creditTradeNotifyResult.setTradeSuccessTime(tradeSuccessTime);
		creditTradeNotifyResult.setTradeType(tradeType);
		creditTradeNotifyResult.setTranSeq(tranSeq);
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.CREDIT_TRADE_RESULT.name());
		task.setTaskId(tranSeq);
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24 * 60 * 20L);
		task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(creditTradeNotifyResult));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
	}

	@Override
	public void createCreateCustomer(CustomerApply4MemberDTO customerApply4MemberDTO, String customerApplyId) {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.CREDIT_CREATE_USER.name());
		task.setTaskId(customerApplyId);
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24 * 60 * 20L);
		task.setData(new Gson().toJson(customerApply4MemberDTO));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
	}

	@Override
	public void createUpdateUserRole(UserUpdateDTO userUpdateDTO) {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.CREDIT_UPDATE_USER_ROLE.name());
		task.setTaskId(UUID.randomUUID().toString());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24 * 60 * 20L);
		task.setData(new Gson().toJson(userUpdateDTO));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
	}

}
