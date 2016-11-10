package com.cana.member.authorization.common;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.cana.member.authorization.exception.MemberException;
import com.cana.member.authorization.exception.MemberExceptionCode;
import com.cana.vbam.common.utils.PasswordEncoderUtil;

public class RandomCodeGenerator {

    private static final String DELIMITER = "<|>";
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public static String gen(String key, String name, long time) {
        String s = key + DELIMITER + name + DELIMITER + time + DELIMITER + UUID.randomUUID();
        String token = newString(PasswordEncoderUtil.simpleConvert(Base64.encodeBase64(PasswordEncoderUtil.simpleConvert(s.getBytes()))));
        return token;
    }

    public static RandomCode deserialize(String code) throws MemberException {
        String s = newString(PasswordEncoderUtil.simpleRevert(Base64.decodeBase64(PasswordEncoderUtil.simpleRevert(code.getBytes()))));
        String[] arr = s.split(Pattern.quote(DELIMITER));
        if (arr.length != 4) {
            throw new MemberException(MemberExceptionCode.TOKEN_DESERIALIZE_FAILED);
        }
        long time = 0L;
        try {
            time = Long.parseLong(arr[2]);
        } catch (NumberFormatException e) {
            throw new MemberException(MemberExceptionCode.TOKEN_DESERIALIZE_FAILED);
        }
        return new RandomCode(arr[0], arr[1], time, arr[3]);
    }

    public static String newString(byte[] b) {
        return new String(b, CHARSET);
    }

    public static class RandomCode {
    	public final String key;
        public final String name;
        public final long time;
        public final String code;

        public RandomCode(String key, String username, long time, String random) {
        	this.key = key;
            this.name = username;
            this.time = time;
            this.code = random;
        }

    }

    public static void main(String[] args) throws MemberException {
        String str = "DwDwDwDxGzT+z3TwDjj1jtzhyiz0mwDwDwDwD8m0nxDzD3DiztT0y2T2jjQ=MAMAMAMAf1ZwMAOgP5ZkMIYlY1NIYQMAMAMAMAPFcwOgOUflYcYgN0NYYZM=";
        RandomCode ut = deserialize(str);
        System.out.println(ut);
    }


}
