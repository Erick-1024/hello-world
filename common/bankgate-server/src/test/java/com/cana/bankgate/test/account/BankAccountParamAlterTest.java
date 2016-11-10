package com.cana.bankgate.test.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankAccountParamAlterDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountSignUpStateQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountSignUpStateResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class BankAccountParamAlterTest  extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private BankgateApi client;
	
	@Test
	public void test() throws Exception{
		BankAccountParamAlterDTO alterDTO = new BankAccountParamAlterDTO();
		alterDTO.setAccountNo("3110210003631006431");
		alterDTO.setFeeType("1");
		alterDTO.setAutoAssignInterestFlag("0");
		alterDTO.setCalculateInterestFlag("0");
		alterDTO.setAutoAssignTranFeeFlag("0");
		alterDTO.setInterestRate("0.0000000");
		BankBaseResultDTO result = client.alterAccountParam(alterDTO);
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void querySignUpState() throws Exception{
		BankAccountSignUpStateQueryDTO queryDTO = new BankAccountSignUpStateQueryDTO();
//		queryDTO.setAccountNo("3110210003631006431");
		queryDTO.setState("0");
		queryDTO.setStartTime("20161001");
		queryDTO.setEndTime("20161024");
		BankAccountSignUpStateResultDTO result = client.queryBankAccountSingUpState(queryDTO);
		System.out.println(JSON.toJSON(result));
	}
}
