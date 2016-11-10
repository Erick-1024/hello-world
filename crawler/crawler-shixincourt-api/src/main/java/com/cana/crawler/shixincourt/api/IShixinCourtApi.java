package com.cana.crawler.shixincourt.api;

import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.cana.vbam.common.crawler.dto.GetShixinCourtResponse;

public interface IShixinCourtApi {

	/**
	 * 查询失信执行
	 * @param request
	 * @return
	 */
	public GetShixinCourtResponse getShixinCourt(GetShixinCourtRequest request);
	
}
