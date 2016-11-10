package com.cana.yundaex.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.limit.YdLimitAuditResultRequest;
import com.cana.yundaex.response.api.impl.YundaexPushAuditResultApiImpl;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * 韵达审核结果重试任务处理类
 * @author xiaoyu
 *
 */
public class YdAuditResultRetryTaskHandler extends AbstractRetryTaskHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Gson gson = new Gson();
	
	private String notifyType = "审核结果通知";
	
	private YundaexPushAuditResultApiImpl yundaexPushAuditResultApiImpl = SpringApplicationContext.getApplicationContext().getBean(YundaexPushAuditResultApiImpl.class);
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {

		String taskId = task.getTaskId();
		logger.info("即将发送{}，审核信息为：{}", notifyType, task.getData());
		Transaction t = Cat.newTransaction("scheduler", notifyType);
		t.addData("taskId", taskId);
		
		YundaexBaseResponse auditResult = yundaexPushAuditResultApiImpl.sendAuditResultToYD(gson.fromJson(task.getData(), YdLimitAuditResultRequest.class));
		try {
			if(!ReturnCode.YD_SUCCESS_CODE.getRetMsg().equals(auditResult.getRetCode())){
				status.fail();
				Cat.logMetricForCount(notifyType + "失败（韵达）");
			}else{
				Cat.logMetricForCount(notifyType + "成功");
				t.setStatus(Transaction.SUCCESS);
			}
		} catch (Exception e) {
			status.fail();
			logger.error(notifyType +"异常", e);
			Cat.logError(e);
			Cat.logMetricForCount(notifyType + "失败(CANA)");
			t.setStatus(e);
		}finally{
			t.complete();
		}
	}

}
