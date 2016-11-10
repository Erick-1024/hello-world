package com.cana.vbam.common.enums;

/**
 * 平台枚举
 * @since 2015-11-3 上午11:41:58
 * @author zhiwen.hu
 *
 */
public enum Platform {

	BIZ("biz", 'z'),
	WECHAT("wechat", 'w');

	private String value;
	private char flag;

	private Platform(String val, char flag) {
		this.value = val;
		this.flag = flag;
	}

	public String value() {
		return this.value;
	}

	public char flag() {
		return this.flag;
	}

	public static Platform getByValue(String value) {
	    for (Platform p : Platform.values()) {
	        if (p.value().equals(value)) {
	            return p;
	        }
	    }
	    return null;
	}

	public static Platform getByFlag(char flag) {
        for (Platform p : Platform.values()) {
            if (p.flag() == flag) {
                return p;
            }
        }
        return null;
	}

}
