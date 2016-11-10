package com.cana.yundaex.service;

/**
 * @author jiangzhou.Ren
 * @time 2016年9月27日下午4:11:36
 */
public interface IYundaexMonitorService {

	/**
	 * 韵达监控定时任务
	 */
	void monitorScheduler();

	/**
	 * 韵达授信审核定时驳回任务
	 */
	void creditAuditScheduler();
	
	
}
