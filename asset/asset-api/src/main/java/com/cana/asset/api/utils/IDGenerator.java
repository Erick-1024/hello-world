package com.cana.asset.api.utils;

import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class IDGenerator {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	//专项计划id
	public static String generateAssetSpecialProgramId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_SPECIAL_PROGRAM_ID,3);
	}
}
