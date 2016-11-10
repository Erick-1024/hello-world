package com.cana.member.authorization.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cana.vbam.common.utils.PasswordEncoderUtil;

@Component("passwordEncoderHandler")
public class PasswordEncoderHandler implements PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return PasswordEncoderUtil.encrypt(rawPass);
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        if (StringUtils.isBlank(rawPass)) {
            return false;
        }
        return PasswordEncoderUtil.encrypt(rawPass).equals(encPass);
    }
}
