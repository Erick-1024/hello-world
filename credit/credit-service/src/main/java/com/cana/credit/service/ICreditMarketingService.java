package com.cana.credit.service;

import com.cana.vbam.common.credit.dto.marketing.CurrentActivityRequest;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityResponse;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductRequest;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductResponse;

public interface ICreditMarketingService {

	/**
	 * 在支付前获取当前客户可以使用的产品及促销信息
	 * @param currentActivityRequest
	 * @return
	 */
	public CurrentActivityResponse getCurrentActivity(CurrentActivityRequest currentActivityRequest);
	
	/**
	 * 在支付前获取当前客户可以使用的产品及促销信息
	 * @param prepayProductRequest
	 * @return
	 */
	public PrepayProductResponse getPrepayProduct(PrepayProductRequest prepayProductRequest);
	
}
