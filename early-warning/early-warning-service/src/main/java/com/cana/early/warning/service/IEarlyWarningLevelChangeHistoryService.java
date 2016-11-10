package com.cana.early.warning.service;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;

public interface IEarlyWarningLevelChangeHistoryService {

	/**
	 * 查询预警流水列表
	 * @param earlyWarningLevelChangeHistoryRequest
	 * @return
	 */
	public ListResult<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest);
	
}
