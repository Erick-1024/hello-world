package com.cana.asset.server.api.impl;

import javax.annotation.Resource;

import com.cana.asset.api.IHomsomSettlementApi;
import com.cana.asset.service.IHomsomSettlementService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackRequestDTO;

/**
 * @author jiangzhou.Ren
 * @time 2016年10月18日下午4:57:50
 */
public class HomsomSettlementApiImpl implements IHomsomSettlementApi{

	@Resource
	private IHomsomSettlementService homsomSettlementService;
	/**
	 * 核销履历列表查询
	 * @param request
	 * @return
	 */
	@Override
	public ListResult<HomsomSettlementTrackDTO> getSettlementTrackList(HomsomSettlementTrackRequestDTO request) {
		
		return homsomSettlementService.getSettlementTrackList(request);
	}

}
