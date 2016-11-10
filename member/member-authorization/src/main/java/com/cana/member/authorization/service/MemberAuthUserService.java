package com.cana.member.authorization.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.MemberAuthConstants;
import com.cana.member.authorization.common.RandomCodeGenerator;
import com.cana.member.authorization.common.RandomCodeGenerator.RandomCode;
import com.cana.member.authorization.exception.MemberException;
import com.cana.member.authorization.exception.MemberExceptionCode;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserStatus;

@Component("memberAuthUserService")
public class MemberAuthUserService {

	private static final Logger LGR = LoggerFactory.getLogger(MemberAuthUserService.class);
	
	@Resource(name = "userApiImpl")
	private IUserApi userService;
	
	public String makeUserLogin(String id, String name, String ipAddr, long timeout){
		String token = RandomCodeGenerator.gen(id, name, timeout);
		Date date = new Date();
		try {
			userService.updateUserLoginInfo(id, token, true, ipAddr, date);
		} catch (Exception e) {
			e.printStackTrace();
			LGR.error("user login info save fail");
			return null;
		}
		
		return token;
	}
	
	public UserSessionDTO authenticateUserToken(String token) {
		RandomCode ut = RandomCodeGenerator.deserialize(token);
		UserSessionDTO usr = null;
		try {
			usr = userService.getUserSession(ut.name);
		} catch (Exception e) {
			
		}
		if (usr == null) {
			throw new MemberException(MemberExceptionCode.USERNAME_ERROR);
		}
		if (usr.getSignedin() == null || !usr.getSignedin()) {
			throw new MemberException(MemberExceptionCode.TOKEN_INVALID);
		}
		if (StringUtils.isEmpty(usr.getToken()) || !usr.getToken().equals(token)) {
			throw new MemberException(MemberExceptionCode.TOKEN_MATCH_FAILED);
		}
		if (System.currentTimeMillis() > ut.time) {
			throw new MemberException(MemberExceptionCode.TOKEN_OUTDATED);
		}
		if (!usr.getUserStatus().equals(UserStatus.ACTIVATED)) {
			throw new MemberException(MemberExceptionCode.USER_FORBIDDEN);
		}
		return usr;
	}
	
	
	public boolean logoutUpateUserLoginInfo(String id){
		try {
			userService.updateUserLoginInfo(id, MemberAuthConstants.TOKEN_DEPRECATED_MARK, false, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			LGR.error("user loginout info save fail");
			return false;
		}
		return true;
	}
	
}
