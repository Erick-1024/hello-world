/**
 * 
 */
package com.cana.wechat.common.dto;

import java.io.Serializable;

/**
 * 放款信息查询接口 接收韵达请求
 * 
 * @author guguanggong
 *
 */
public class TokenRequest implements Serializable {

	private static final long serialVersionUID = -2306115510946672588L;

	/**
	 * 获取access_token填写client_credential
	 */
	private String grantType = "client_credential";

	/**
	 * 第三方用户唯一凭证
	 */
	private String appid;

	/**
	 * 第三方用户唯一凭证密钥，即appsecret
	 */
	private String secret;

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
