package com.cana.member.authorization.common;

import com.cana.vbam.common.utils.PropertiesUtils;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class MemberAuthConfig {

	private static final String NAMESPACE = "member-auth";

	static {
		PropertiesUtils.register(NAMESPACE, "properties/member-auth.properties", ConfScope.G);
	}

	public static String get(String key) {
		return PropertiesUtils.get(NAMESPACE, key);
	}

}
