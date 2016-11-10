package com.travelzen.framework.dao.rdbms;
import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.member.service.transaction.IMockTransactionService;
import com.cana.vbam.common.utils.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/member-server-*.xml")
public class SequenceGeneratorTest{
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IMockTransactionService service;
	
	
	@Test
	public void getNexSeq() throws Exception{
		System.out.println("next_seq:" + seqGen.getNextSeq(Constants.SEQUENCE_NAME_USER_ID));
		System.out.println("next_seq:" + seqGen.getNextSeq(Constants.SEQUENCE_NAME_USER_ID));
		System.out.println("next_seq:" + seqGen.getNextSeq(Constants.SEQUENCE_NAME_USER_ID));
		System.out.println("next_seq:" + seqGen.getNextSeq(Constants.SEQUENCE_NAME_USER_ID));
	}
	
	@Test
	public void requiredNewEffective() throws Exception{
		String propertyName = RandomStringUtils.randomNumeric(10);
		String propertyValue = "1";
		System.out.println("sequenceName:" + propertyName);
		try{
			service.requiredNewEffective(propertyName, propertyValue);
		}catch(Exception e){
			// ignore
		}
	}

}
