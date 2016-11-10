package com.cana.asset.api;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackRequestDTO;

/**
 * @author jiangzhou.Ren
 * @time 2016年10月18日下午4:49:48
 */
public interface IHomsomSettlementApi {
	
	/**
	 * 核销履历列表查询
	 * @param homsomSettlementTrackRequestDTO
	 * @return
	 */
	public ListResult<HomsomSettlementTrackDTO> getSettlementTrackList(HomsomSettlementTrackRequestDTO request);
}
