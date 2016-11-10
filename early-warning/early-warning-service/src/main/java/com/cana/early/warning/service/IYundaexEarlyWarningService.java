package com.cana.early.warning.service;

import java.util.List;

import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;

public interface IYundaexEarlyWarningService {

	/**
	 * 一个事件类型一行，将系统事件再根据子类型分开
	 * @param state 事件状态
	 * @param productId 产品ID
	 * @param memberId 用户ID
	 * @param outCustomerId 外部平台客户ID
	 * @return
	 */
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId);
}
