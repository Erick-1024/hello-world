/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.yundaex.scheduler.schedulers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationSynDTO;
import com.cana.yundaex.common.enums.YundaexPullStationType;
import com.cana.yundaex.service.IYundaexTstationInfoService;
import com.cana.yundaex.service.transaction.IYundaexTstationInfoTransactionService;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.util.DateUtils;

/**
 * 同步韵达网点数据
 * 
 * @author guguanggong
 */
@Service
public class YundaexStationPullScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IYundaexTstationInfoTransactionService yundaexTstationInfoTransactionService;

	@Resource
	private IYundaexTstationInfoService yundaexTstationInfoService;
	
	@Resource
	private VbamCommonService vbamCommonService;

	@Scheduled(fixedDelay = org.apache.commons.lang.time.DateUtils.MILLIS_PER_MINUTE * 2)
//	@Scheduled(cron = "0 42 9 10 * ?")
	public void doTask() throws Exception {
		logger.info("开始拉取韵达网点数据");
		// 1.拉取所有网点编号 以及最近一次 统计年月
		List<YundaexTstationSynDTO> yundaexTstationInfo = getAllTstationAndMaxStatmonth();
		if (CollectionUtils.isEmpty(yundaexTstationInfo)) {
			logger.info("没有网点信息需要同步");
			return;
		}

		// 2.整理数据
		List<YundaexTstationInfoQueryDTO> queryDTOs = getYundaexTstationInfoDTO(yundaexTstationInfo);

		// 3.插入重试任务
		yundaexTstationInfoTransactionService.createStationPull(queryDTOs, YundaexPullStationType.SYN);
		logger.info("生成重试任务完成");
	}

	/**
	 * 查找所有需要同步数据的站点
	 * @return
	 */
	private List<YundaexTstationSynDTO> getAllTstationAndMaxStatmonth() {
		String currentDate = getCurrentDate();
		String endDate = DateTimeUtil.month7(DateTimeUtil.addMonth(DateTimeUtil.getDate10(currentDate), -1));
		return yundaexTstationInfoService.getAllTstationAndMaxStatmonth(endDate);
	}
	
	/**
	 * 取韵达虚拟时间，如果未设置 则拿系统虚拟时间
	 * @return
	 */
	private String getCurrentDate() {
		String currentDate = vbamCommonService.getCurrentDate();
		return currentDate;
	}

	/**
	 * 整理网点信息
	 * 
	 * @return
	 */
	private List<YundaexTstationInfoQueryDTO> getYundaexTstationInfoDTO(List<YundaexTstationSynDTO> yundaexTstationInfo) {
		List<YundaexTstationInfoQueryDTO> dtos = new ArrayList<>();

		for (YundaexTstationSynDTO synDTO : yundaexTstationInfo) {
			String stationNo = synDTO.getStationNo();
			String maxStatmonth = synDTO.getMaxStatmonth();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtils.getDate(maxStatmonth, "yyyy-MM")); // 拉取网点数据最大日期是 2016-08
			calendar.add(Calendar.MONTH, 1); // 本次拉取时间为 2016-09 到 上月为至
			String startDate = DateUtils.getFirstDayOfMonth(calendar.getTime()); // 拉取日期起日 2016-09-01
			String currentDate = getCurrentDate();
			String endDate = DateUtils.getFirstDayOfMonth(DateUtils.getJustDate(currentDate)); // 拉取日期至日 

			YundaexTstationInfoQueryDTO tstationInfoQueryDTO = new YundaexTstationInfoQueryDTO();
			tstationInfoQueryDTO.setStationNo(stationNo);
			tstationInfoQueryDTO.setEndDate(endDate);
			tstationInfoQueryDTO.setStartDate(startDate);
			dtos.add(tstationInfoQueryDTO);
		}
		return dtos;
	}
}
