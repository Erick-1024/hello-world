package com.cana.credit.scheduler.retry;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.flight.finance.common.dto.CreditTradeNotifyResult;
import com.cana.flight.finance.common.dto.CreditTradeResult;
import com.cana.flight.finance.common.dto.TravelzenBaseResponse;
import com.cana.flight.finance.dataaccess.travelzen.api.impl.TravelzenDataApiImpl;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.gson.Gson;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class CreditTradeResultRetryTaskHandler extends AbstractRetryTaskHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TravelzenDataApiImpl travelzenDataApiImpl = SpringApplicationContext.getApplicationContext().getBean(TravelzenDataApiImpl.class);
	
	private Gson gson = new Gson();
	
	private String notifyType = "交易结果通知";
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		String taskId = task.getTaskId();
		logger.info("即将发送{}，授信交易记录为：{}", notifyType, taskId);
		Transaction t = Cat.newTransaction("scheduler", notifyType);
		t.addData("taskId", taskId);
		try {
			CreditTradeNotifyResult creditTradeNotifyResult = gson.fromJson(task.getData(), CreditTradeNotifyResult.class);
			CreditTradeResult creditTradeResult = new CreditTradeResult();
			BeanUtils.copyProperties(creditTradeResult, creditTradeNotifyResult);
			TravelzenBaseResponse result = travelzenDataApiImpl.sendCreditTradeResult(creditTradeNotifyResult.getNotifyUrl(), creditTradeResult);
			if(!"SUCCESS".equals(result.getRetCode())) {
				status.fail();
				Cat.logMetricForCount(notifyType + "失败（真旅）");					
			} else {
				Cat.logMetricForCount(notifyType + "成功");
				t.setStatus(Transaction.SUCCESS);
			}
		} catch (Exception e) {
			status.fail();
			logger.error(notifyType +"异常", e);
			Cat.logError(e);
			Cat.logMetricForCount(notifyType + "失败(CANA)");
			t.setStatus(e);
		} finally {
			t.complete();
		}
	}

}
