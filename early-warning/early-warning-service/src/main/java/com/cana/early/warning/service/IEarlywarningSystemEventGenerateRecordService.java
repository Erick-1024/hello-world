package com.cana.early.warning.service;

import java.util.Date;

public interface IEarlywarningSystemEventGenerateRecordService {

	/**
	 * 某天某个各户的系统预警是否已经生成
	 * @param productId 产品ID
	 * @param financeId 融资客户ID
	 * @param outCustomerId 外部客户ID
	 * @param date 时间
	 * @return
	 */
	public boolean isExistRecord(String productId, String financeId, String outCustomerId, Date date);
	
	/**
	 * 某月某个各户的系统预警是否已经生成
	 * @param productId 产品ID
	 * @param financeId 融资客户ID
	 * @param outCustomerId 外部客户ID
	 * @param date 时间
	 * @return
	 */
	public boolean isExistRecordWithMonth(String productId, String financeId, String outCustomerId, Date date);
}
