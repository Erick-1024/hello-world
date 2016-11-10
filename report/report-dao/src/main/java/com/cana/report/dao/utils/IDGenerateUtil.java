/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.dao.utils;

import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * @author ducer
 *
 */
public class IDGenerateUtil {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
}
