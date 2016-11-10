package com.cana.signature.server.transaction.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.signature.dao.po.SignatureContract;
import com.cana.signature.service.transaction.ISignedContractTransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class TransactionTest {

	@Resource
	ISignedContractTransactionService transactionService;
	
	@Test
	public void test(){
		SignatureContract signatureContract = new SignatureContract();
		signatureContract.setId("123");
		signatureContract.setContractSignedData("123".getBytes());
		signatureContract.setContractSourceData("1234".getBytes());
		transactionService.insertOrUpdateSignatureContract(signatureContract);
	}
}
