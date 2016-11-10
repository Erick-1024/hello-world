package com.cana.vbam.common.message.util;

import java.util.Date;

import com.travelzen.framework.core.time.DateTimeUtil;

public class SmsMessageFilterUtil {
	
	public static boolean smsSendFlag(boolean isProdEnv, Date createTime){
		if(isProdEnv){
			return true;
		}
		if(null == createTime){
			return false;
		}
		return DateTimeUtil.isSameDay(new Date(), createTime);
	}
}
