package com.cana.member.authorization.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @since Nov 7, 20152:27:52 PM
 * @author dev1
 *
 */
public class CaptchaNotMatchException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455296164732097609L;

	public CaptchaNotMatchException(String msg) {
		super(msg);
	}

	public CaptchaNotMatchException(String msg, Throwable t) {
		super(msg, t);
	}

}
