package com.cana.yundaex.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;
import com.cana.yundaex.common.enums.StationInfoType;
import com.cana.yundaex.common.enums.YundaexStationDataStatus;
import com.cana.yundaex.response.api.IYundaexPullTstationInfoApi;
import com.cana.yundaex.response.api.impl.YundaexPullTstationInfoApiImpl;
import com.cana.yundaex.service.transaction.IYundaexTstationInfoTransactionService;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class YdStationPullHandler extends AbstractRetryTaskHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private IYundaexPullTstationInfoApi yundaexPullTstationInfoApi = SpringApplicationContext.getApplicationContext()
			.getBean(YundaexPullTstationInfoApiImpl.class);

	private IYundaexTstationInfoTransactionService ydStationInfo = SpringApplicationContext.getApplicationContext()
			.getBean(IYundaexTstationInfoTransactionService.class);

	private Gson gson = new Gson();

	private String notifyType = "拉取网点数据";

	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		String taskId = task.getTaskId();
		logger.info("即将发送{}，网点信息为：{}", notifyType, task.getData());
		Transaction t = Cat.newTransaction("scheduler", notifyType);
		t.addData("taskId", taskId);
		try {
			YundaexTstationInfoQueryDTO infoQueryDTO = gson.fromJson(task.getData(), YundaexTstationInfoQueryDTO.class);
			YundaexTstationInfoResponse returnData = yundaexPullTstationInfoApi.getTstationInfoByParam(infoQueryDTO);
			if (!ReturnCode.YD_SUCCESS_CODE.getRetMsg().equals(returnData.getRetCode())) {
				status.fail();
				logger.info(notifyType + "韵达返回处理异常，网点代号：{}", infoQueryDTO.getStationNo());
				Cat.logMetricForCount(notifyType + "韵达处理异常");
			} else {
				ReturnClass retData = ydStationInfo.saveTstationInfo(infoQueryDTO, returnData);
				if (ReturnCode.SUCCESS.equals(retData.getRetCode())) {
					Cat.logMetricForCount(notifyType + "成功");
					logger.info(notifyType + "处理成功");
					t.setStatus(Transaction.SUCCESS);
				} else {
					logger.info(notifyType + "接收成功处理失败，网点代号：{}", infoQueryDTO.getStationNo());
					status.fail();
				}
			}
		} catch (Exception e) {
			status.fail();
			logger.error(notifyType + "异常", e);
			Cat.logError(e);
			Cat.logMetricForCount(notifyType + "处理异常");
			t.setStatus(e);
		} finally {
			t.complete();
		}
	}

	/**
	 * 达到重试次数后，把拉取数据标记改为 失败
	 */
	@Override
	public void onFail(RetryTask task) throws Exception {
		logger.info("{}已达到重试次数，网点信息为：{}", notifyType, task.getData());
		Transaction t = Cat.newTransaction("scheduler", notifyType);
		try {
			YundaexTstationInfoQueryDTO infoQueryDTO = gson.fromJson(task.getData(), YundaexTstationInfoQueryDTO.class);
			Cat.logMetricForCount(notifyType + "处理失败");
			t.setStatus(Transaction.SUCCESS);
			//修改韵达数据记录表数据票房为fail，修改申请表中的拉取数据标记为失败(W)
			ydStationInfo.updateCustomerApplyAndStationRecord(infoQueryDTO.getEndDate(), infoQueryDTO.getStationNo(), YundaexStationDataStatus.FAIL, null, StationInfoType.W, "");
		} catch (Exception e) {
			logger.error(notifyType + "异常", e);
			Cat.logError(e);
			Cat.logMetricForCount(notifyType + "处理异常");
			t.setStatus(e);
		} finally {
			t.complete();
		}
	}
}
