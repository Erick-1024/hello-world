package com.cana.asset.service.transaction.util;

import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * 专项计划日志ID生成工具类
 * @author yihong.tang
 */
public class SpecialProgramLogIdUtils {

	private static SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);

	public static String generateSpecialProgramLogId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SPECIAL_PROGRAM_LOG_ID, 4);
	} 

}
