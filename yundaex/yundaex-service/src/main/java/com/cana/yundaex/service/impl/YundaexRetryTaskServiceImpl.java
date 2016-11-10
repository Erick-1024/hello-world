package com.cana.yundaex.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.vbam.common.member.dto.user.UserUpdateDTO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.yundaex.common.dto.YundaexLoanInfoResult;
import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApply4MemberUserDTO;
import com.cana.yundaex.common.dto.limit.YdLimitAuditResultRequest;
import com.cana.yundaex.common.enums.Institution;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.dianping.cat.Cat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskBackOffPolicy;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskType;

@Service
public class YundaexRetryTaskServiceImpl implements IYundaexRetryTaskService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;

	
	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	@Override
	public void createAuditResultNotify(Institution institution, String stationNo, String applyResult, Long totalLimit,
			String canaUrl) {

		YdLimitAuditResultRequest ydLimitAuditResultRequest = new YdLimitAuditResultRequest();
		ydLimitAuditResultRequest.setStationNo(stationNo);
		ydLimitAuditResultRequest.setApplyResult(applyResult);
		ydLimitAuditResultRequest.setTotalLimit(totalLimit);
		ydLimitAuditResultRequest.setCanaUrl(canaUrl);
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.YD_AUDIT_RESULT.name());
		task.setTaskId(institution.name() + UUID.randomUUID().toString());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(30L);
		task.setData(gson.toJson(ydLimitAuditResultRequest));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
	}

	@Override
	public void createYdCreateCustomer(YdCustomerApply4MemberUserDTO ydCustomerApply4MemberUserDTO, String id) {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.YD_CREDIT_CREATE_USER.name());
		task.setTaskId(id);
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24*60*20L);
		task.setData(new Gson().toJson(ydCustomerApply4MemberUserDTO));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
		
	}
	
	public void createCreditTradeResult(String financeId, RepaymentLoanInfo info, String taskeId, YdCustomerApplyDetailDTO customerApplyDetailDTO) {
		String notifyUrl = Constants.YUNDAEX_LOAN_RESULT_URL_PREFIX;
		YundaexLoanInfoResult yundaexLoanInfoResult = new YundaexLoanInfoResult();
		yundaexLoanInfoResult.setNotifyUrl(notifyUrl);
		yundaexLoanInfoResult.setStationNo(customerApplyDetailDTO.getStationNo());
		yundaexLoanInfoResult.setStationName(customerApplyDetailDTO.getStationName());
		yundaexLoanInfoResult.setCustName(customerApplyDetailDTO.getCustName());
		yundaexLoanInfoResult.setCustIdno(customerApplyDetailDTO.getCustIdno());
		yundaexLoanInfoResult.setPutoutno(info.getLoanNo());
		yundaexLoanInfoResult.setPutoutamt(info.getFinanceAmount());
		yundaexLoanInfoResult.setBegindate(info.getLoanDate());
		yundaexLoanInfoResult.setEnddate(info.getDueDate());
		StringBuffer signStrBuf = new StringBuffer();
		signStrBuf.append(yundaexLoanInfoResult.getStationNo()).append(yundaexLoanInfoResult.getPutoutno())
				.append(yundaexLoanInfoResult.getPutoutamt()).append(yundaexLoanInfoResult.getBegindate())
				.append(yundaexLoanInfoResult.getEnddate());
		yundaexLoanInfoResult.setSign(getSign(signStrBuf.toString()));

		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.YD_CREDIT_TRADE_RESULT.name());
		task.setTaskId(taskeId);
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(30L);
		task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(yundaexLoanInfoResult));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));

		try {
			retryTaskMapper.insertSelective(task);
		} catch (Exception e) {
			Cat.logMetricForCount("生成放款通知信息重试任务失败");
			logger.error("生成放款通知信息重试任务失败", e);
		}
	}

	
	@Override
	public void createUpdateUserRole(UserUpdateDTO userUpdateDTO) {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.YD_CREDIT_UPDATE_USER_ROLE.name());
		task.setTaskId(userUpdateDTO.getId());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setMaxAttempts(24 * 60 * 20L);
		task.setData(new Gson().toJson(userUpdateDTO));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);
		
	}
	
	/**
	 * 拉取网数据 70秒重试一次
	 */
	@Override
	public void createStationPullTask(List<YundaexTstationInfoQueryDTO> queryDTOs) {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.YD_STATION_PULL.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(70L);
		task.setMaxAttempts(10L);
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		
		for(YundaexTstationInfoQueryDTO queryDTO : queryDTOs) {
			task.setTaskId(queryDTO.getStationNo() + UUID.randomUUID().toString());
			task.setData(new Gson().toJson(queryDTO));
			retryTaskMapper.insertSelective(task);
		}
	}
	
	/**
	 * 同步网数据 1小时重试一次，使用新的线程池
	 */
	@Override
	public void createStationSynTask(List<YundaexTstationInfoQueryDTO> queryDTOs) {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.YD_STATION_SYN.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(3600L);
		task.setMaxAttempts(3L);
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		task.setUseIsolatedThreadPool(true); // 使用新的线程池
		task.setThreadPoolSize(20L); 
		
		for(YundaexTstationInfoQueryDTO queryDTO : queryDTOs) {
			task.setTaskId(queryDTO.getStationNo() + UUID.randomUUID().toString());
			task.setData(new Gson().toJson(queryDTO));
			retryTaskMapper.insertSelective(task);
		}
	}
	
	/**
	 * 私钥签名
	 * @param signStr
	 * @return
	 */
	private String getSign(String signStr) {
		String sign ="";
		try {
			sign = new String(vbamCommonServiceImpl.sign(signStr.getBytes(), com.cana.vbam.common.credit.enums.Institution.yd.name(), false));
		} catch (Exception e) {
			logger.error("签名操作失败！", e);
		}
		return sign;
	}
}
