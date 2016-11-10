package com.cana.early.warning.dao.mapper;

import java.util.List;

import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;

public interface EarlyWarningLevelChangeHistoryAndCreditCustomMapper {

	public List<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest);

	public int queryEarlyWarningLevelChangeHistoryCount(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest);
	
}
