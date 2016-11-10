package com.cana.credit.server.api.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.credit.api.ICreditSignApi;
import com.cana.vbam.common.service.IVbamCommonService;

public class CreditSignApiImpl implements ICreditSignApi {

	@Resource
	IVbamCommonService vbamCommonService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String sign(String plain, String institution, boolean usePublicKey) {
		if (!vbamCommonService.isProdEnv()) {
			try {
				return new String(vbamCommonService.sign(plain.getBytes(), institution, usePublicKey));
			} catch (Exception e) {
				logger.error("生成签名异常", e);
			}
		}
		return "机构不存在或者当前处于生产环境不允许生成签名";
	}


}
