package com.cana.early.warning.service.utils;

import java.util.List;

import com.cana.early.warning.dao.po.EarlywarningCustomer;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.vbam.common.early.warning.dto.CourtExecutionLevelResult;
import com.cana.vbam.common.early.warning.dto.EarlywarningLockCustomerRequest;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

public interface IEarlyWarningServiceHelper {

	/**
	 * 预警客户如果已经存在加锁，否则新增
	 * @param request
	 * @return
	 */
	public EarlywarningCustomer addOrlockCustomer(EarlywarningLockCustomerRequest request);
	
	/**
	 * 增加预警汇总记录
	 * @param request
	 */
	public EarlywarningCustomer addCustomer(EarlywarningLockCustomerRequest request);
	
	/**
	 * 对预警汇总表进行加锁
	 * @param request
	 * @return
	 */
	public EarlywarningCustomer lockCustomer(EarlywarningLockCustomerRequest request);
	
	/**
	 * 生成预警汇总所表请求类型
	 * @param productId
	 * @param financeId
	 * @param financeCompany
	 * @param outCustomerId
	 * @return
	 */
	public EarlywarningLockCustomerRequest generateEarlywarningLockCustomerRequest(String productId, String financeId, String financeCompany, String outCustomerId);
	
	/**
	 * 更新客户的预警级别
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public EarlywarningCustomer updateCustomerEarlywarningLevel(EarlywarningLockCustomerRequest request);
	
	/**
	 * 增加预警事件时预算预警等级
	 * @param event
	 * @return
	 * @throws Exception
	 */
	public EarlywarningLevel preCalcCustomerEarlywarningLevelWhenAdd(EarlywarningEvent event) throws Exception;
	
	/**
	 * 解除预警事件时预算预警等级
	 * @param eventId 事件id
	 * @return
	 * @throws Exception
	 */
	public EarlywarningLevel preCalcCustomerEarlywarningLevelWhenCancel(String eventId) throws Exception;
	
	/**
	 * 获取法院执行事件的最高级别
	 * @param events
	 * @return
	 */
	public CourtExecutionLevelResult getHighestCourtExecutionLevel(List<EarlywarningEvent> events, String courtexecutionMonth, String courtExecutionCountThreshold, String courtExecutionMoneyThreshold);
	
}
