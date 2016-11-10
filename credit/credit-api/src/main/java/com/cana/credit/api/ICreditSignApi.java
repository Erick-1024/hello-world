package com.cana.credit.api;

public interface ICreditSignApi {

	/**
	 * 辅助测试使用的生成签名接口，生产环境拒绝生成
	 */
	public String sign(String plain, String institution, boolean usePublicKey);
}
