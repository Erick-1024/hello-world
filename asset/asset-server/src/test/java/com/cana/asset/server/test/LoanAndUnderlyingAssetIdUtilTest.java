package com.cana.asset.server.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.service.transaction.util.LoanAndUnderlyingAssetIdUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-service-*.xml"})
public class LoanAndUnderlyingAssetIdUtilTest {

	@Test
	public void testId() {
		String contractNo = UUID.randomUUID().toString().substring(24);
		String loanInfoId = LoanAndUnderlyingAssetIdUtils.generateLoanInfoId(contractNo);
		String underlyingAssetId = LoanAndUnderlyingAssetIdUtils.generateUnderlyingAssetId(contractNo);
		System.out.println(loanInfoId);
		System.out.println(underlyingAssetId);
		LoanAndUnderlyingAssetIdUtils.updateLoanInfoIdSequance(loanInfoId);
		LoanAndUnderlyingAssetIdUtils.updateUnderlyingAssetIdSequance(underlyingAssetId);

		loanInfoId = LoanAndUnderlyingAssetIdUtils.generateLoanInfoId(contractNo);
		underlyingAssetId = LoanAndUnderlyingAssetIdUtils.generateUnderlyingAssetId(contractNo);
		System.out.println(loanInfoId);
		System.out.println(underlyingAssetId);

		LoanAndUnderlyingAssetIdUtils.checkUnderlyingAssetIdIsValid(contractNo, underlyingAssetId);
	}

}
