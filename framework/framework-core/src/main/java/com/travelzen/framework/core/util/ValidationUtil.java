/**
 *
 */
package com.travelzen.framework.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.core.exception.BizException;

/**
 * @author shuiren
 *
 */
public class ValidationUtil {
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String MOBILENO_PATTERN = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$";
	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	private static Pattern mobileNoPattern = Pattern.compile(MOBILENO_PATTERN);
	private static Map<String, Pattern> cachedPattern = new HashMap<String, Pattern>();
    
/**
     * 验证value是否匹配正则表达式
     * @param regexp
     * @param value
     * @param validationMessage
     * @throws BizException
     */
    public static void validatePattern(String regexp, String value, String validationMessage) throws BizException {
        if(StringUtils.isBlank(value))
            return;
        Pattern pattern = cachedPattern.get(regexp);
        if(pattern == null){
            pattern = Pattern.compile(regexp);
            cachedPattern.put(regexp, pattern);
        }
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches())
            throw BizException.instance(validationMessage);
    }
    
	/**
	 * 验证value是否匹配正则表达式
	 * @param regexp
	 * @param value
	 * @return
	 * @throws BizException
	 */
	public static boolean isValidatePattern(String regexp, String value) throws BizException {
		Pattern pattern = cachedPattern.get(regexp);
		if(pattern == null){
			pattern = Pattern.compile(regexp);
			cachedPattern.put(regexp, pattern);
		}
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	/***/
	public static boolean isValidEmail(final String emailAddress) {
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}
	
	
	//手机号码正则匹配
	public static boolean isValidMobileNo(final String mobileNoPattern) {
		Matcher matcher = pattern.matcher(mobileNoPattern);
		return matcher.matches();
	}

	public static boolean isNull(Object... objects){
		boolean isNull = false;
		if(objects == null || objects.length == 0){
			isNull = true;
			return isNull;
		}
		for(Object object:objects){
			if(object == null){
				isNull = true;
				break;
			}
			if(object instanceof String){
				String objStr = (String)object;
				if(StringUtils.isBlank(objStr)){
					isNull = true;
					break;
				}
			}

			if(object instanceof Collection){
				Collection objColl = (Collection)object;
				if(objColl.isEmpty()){
					isNull = true;
					break;
				}
			}

			if(object instanceof Map){
				Map objMap = (Map)object;
				if(objMap.isEmpty()){
					isNull = true;
					break;
				}
			}
		}
		return isNull;
	}
	public static boolean isNotNull(Object... objects){
		return !isNull(objects);
	}

	/**
	 * 判断两个字符串对象是否相同，
	 * 相同条件是同为null，或者满足equals
	 */
	public static boolean isEquals(String str1, String str2){
		boolean isEquals = false;
		if (str1 == null) {
			if (str2 == null) isEquals = true;
		} else if (str1.equals(str2)) {
			isEquals = true;
		}
		return isEquals;
	}

	/**
	 * 判断两个字符串对象是否相同，
	 * 其中对象为null时当成空字符串处理
	 */
	public static boolean isEqualsIgnoreNull(String str1, String str2) {
		return isEquals(str1 == null ? "" : str1, str2 == null ? "" : str2);
	}
}
