package com.cana.asset.service;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackRequestDTO;

/**
 * @author jiangzhou.Ren
 * @time 2016年10月18日下午5:00:48
 */

public interface IHomsomSettlementService {

	/**
	 * 核销履历列表查询
	 * @param request
	 * @return
	 */
	ListResult<HomsomSettlementTrackDTO> getSettlementTrackList(HomsomSettlementTrackRequestDTO request);

}
