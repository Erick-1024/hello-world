/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.test.generate;

import java.lang.reflect.Field;

import org.apache.commons.lang3.RandomStringUtils;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.server.constants.BankgateConstant;
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
public class GeneratePOJO {

	public static void main(String[] args) throws Exception {
	  System.out.println(JSON.toJSONString(BankgateConstant.config));
	  System.out.println(DateTimeUtil.parseDate8("20160518").getDayOfYear() -  DateTimeUtil.parseDate8("20160203").getDayOfYear());
	  Field[] fields = UnfreezeFundDTO.class.getDeclaredFields();
	  for(Field field : fields){
	    System.out.println(field.getName());
	  }
	  System.out.println(RandomStringUtils.randomNumeric(14));
	  System.out.println(UnfreezeFundDTO.class.getSuperclass().getSuperclass());
	  System.out.println(UnfreezeFundDTO.class.getSuperclass().getSuperclass().getPackage().getName().contains("com.cana"));
	}
}
