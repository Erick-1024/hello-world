package com.cana.yundaex.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.YundaexLoanInfoResponse;
import com.cana.yundaex.common.dto.YundaexLoanInfoResult;
import com.cana.yundaex.response.api.impl.YundaexResultDataApiImpl;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class YdLoanResultRetryTaskHandler extends AbstractRetryTaskHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private YundaexResultDataApiImpl yundaexResultDataApiImpl = SpringApplicationContext.getApplicationContext().getBean(YundaexResultDataApiImpl.class);
	
	private Gson gson = new Gson();
	
	private String notifyType = "放款结果通知";
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		String taskId = task.getTaskId();
		logger.info("即将发送{}，放款信息为：{}", notifyType, task.getData());
		Transaction t = Cat.newTransaction("scheduler", notifyType);
		t.addData("taskId", taskId);
		try {
			YundaexLoanInfoResult yundaexLoanInfoResult = gson.fromJson(task.getData(), YundaexLoanInfoResult.class);
			YundaexLoanInfoResponse yundaexLoanInfoResponse = new YundaexLoanInfoResponse();
			yundaexLoanInfoResponse.setStationNo(yundaexLoanInfoResult.getStationNo());
			yundaexLoanInfoResponse.setStationName(yundaexLoanInfoResult.getStationName());
			yundaexLoanInfoResponse.setCustName(yundaexLoanInfoResult.getCustName());
			yundaexLoanInfoResponse.setCustIdno(yundaexLoanInfoResult.getCustIdno());
			yundaexLoanInfoResponse.setBegindate(yundaexLoanInfoResult.getBegindate());
			yundaexLoanInfoResponse.setEnddate(yundaexLoanInfoResult.getEnddate());
			yundaexLoanInfoResponse.setPutoutamt(yundaexLoanInfoResult.getPutoutamt());
			yundaexLoanInfoResponse.setPutoutno(yundaexLoanInfoResult.getPutoutno());
			yundaexLoanInfoResponse.setSign(yundaexLoanInfoResult.getSign());
			YundaexBaseResponse returnData  = yundaexResultDataApiImpl.sendLoanInfoResult(yundaexLoanInfoResult.getNotifyUrl(), yundaexLoanInfoResponse);
			if(!ReturnCode.YD_SUCCESS_CODE.getRetMsg().equals(returnData.getRetCode())) {
				status.fail();
				Cat.logMetricForCount(notifyType + "失败");	
			} else {
				Cat.logMetricForCount(notifyType + "成功");
				t.setStatus(Transaction.SUCCESS);
			}
		} catch (Exception e) {
			status.fail();
			logger.error(notifyType +"异常", e);
			Cat.logError(e);
			Cat.logMetricForCount(notifyType + "失败");
			t.setStatus(e);
		} finally {
			t.complete();
		}
	}

}
