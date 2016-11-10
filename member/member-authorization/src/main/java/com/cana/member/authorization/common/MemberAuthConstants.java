package com.cana.member.authorization.common;

public class MemberAuthConstants {

	/**
	 * 登陆后改变SID时，存储到HttpServletRequest中用于标记SID已经发生改变,对应的值为新生成的redis key
	 */
	public static final String NEW_REDIS_KEY = "NEW_REDIS_SESSION_KEY";

	/**
	 * 每个会话已经访问的次数
	 */
	public static final String REQUEST_COUNTER_PER_SESSION = "REQUEST_COUNTER_PER_SESSION";

	/**
	 * 是否从redis中访问过session
	 */
	public static final String LOADED_FROM_REDIS = "LOADED_FROM_REDIS";

	/**
	 * 登陆地地址
	 */
	public static final String LOGIN_URL = "/signIn";

	/**
	 * 采购商登陆后返回验证结果的path
	 */
	public static final String AUTH_RESULT_PRINTING_REQUEST_PATH = "/printAuthResult";

	/**
	 * 上传时传送SID的名字
	 */
	public static final String UPLOAD_SID = "SID";
	
	/**
	 * 图形验证码session key
	 */
	public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";
	
	/**
	 * token 废弃
	 */
	public static final String TOKEN_DEPRECATED_MARK = "[token-deprecated]";
	
	public static final String COOKIE_KEY_TOKEN = "membertoken";
	
	public static final String FORCE_HTTPS = "forceHttps";
}
