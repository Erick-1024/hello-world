package com.cana.member.api;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 主要是提供辅助测试功能
 * @author renshui
 *
 */
public interface ITestCenterApi {
	/**
	 * 保存虚拟的日期和时间
	 * @param virtualDate
	 * @param hourOffset
	 */
	public void saveVirtualDateTime(String virtualDate, String hourOffset);
	
	/**
	 * 获取当前虚拟日期和时偏移
	 * @return
	 */
	public Pair<String, String> getVirtualDateTime();
	
	/**
	 * 保存当前用户的账户余额
	 * @param userName
	 * @param balance
	 * @param expireTime hours
	 */
	public void saveVirtualBalance(String userName, Long balance, Integer expireTime);
	
	/**
	 * 获取当前用户的账户余额
	 * @param userName
	 * @param amount
	 * @param expireTime
	 * @return 
	 */
	public Long getVirtualBalance(String accountNo);
	
	/**
	 * 保存提现状态
	 * @param businessSeq
	 * @param state
	 */
	public void saveWithdrawState(String businessSeq, String state);

	/**
	 * 设置全局随机虚拟余额标志
	 * @param expireTime
	 */
	public void saveVirtualBalanceForAllAccount(Integer expireTime);

	/**
	 * 取消设置全局随机虚拟余额标志
	 */
	public void cancelVirtualBalanceForAllAccount();
	
	/**  
     * 判断是否是生成环境
     * @return
     */
	public boolean isProdEnv();
	
}
