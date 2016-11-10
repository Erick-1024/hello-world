package com.cana.member.authorization.common;

public enum AuthResult {

	UNKOWN_ERROR(false, 0),

	SUCCESS(true, 1),

	USERNAME_OR_PASSWORD_ERROR(false, 2),

	LOCKED(false, 3),

	EXPIRED(false, 4),

	CAPTCHA_ERROR(false, 5);


	private boolean isSuccessful;
	private int code;

	private AuthResult(boolean b, int code) {
		isSuccessful = b;
		this.code = code;
	}

	public boolean isSuccessful() {
		return this.isSuccessful;
	}

	public int code() {
		return this.code;
	}

	public static AuthResult valueOf(int code) {
		AuthResult [] all = AuthResult.values();
		for (AuthResult ar : all) {
			if (code == ar.code()) {
				return ar;
			}
		}
		return null;
	}

}
