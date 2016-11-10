package com.cana.credit.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.api.IAssetApi;
import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.credit.service.ICreditMarketingService;
import com.cana.credit.service.IOutCustomerService;
import com.cana.marketing.api.IInterestRateApi;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityRequest;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityResponse;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductRequest;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductResponse;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscount;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountRequest;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountResponse;
import com.cana.vbam.common.repayment.dto.PreCalcInterestRequest;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class CreditMarketingServiceImpl implements ICreditMarketingService {

	@Resource
	private IOutCustomerService outCustomerServiceImpl;
	
	@Resource
	private IInterestRateApi intersetRateApiImpl;
	
	@Resource
	private IRepaymentTransactionService repaymentTransactionServiceImpl;

	@Resource
	private IAssetProjectManageApi projectApi;
	@Resource
	private IAssetApi assetApiImpl;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public CurrentActivityResponse getCurrentActivity(CurrentActivityRequest currentActivityRequest) {
		ProjectInfo projectInfo = projectApi.getProjectInfo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		InterestRateDiscount interestRateDiscount = intersetRateApiImpl.getInterestRateDiscountInfo(generateInterestRateDiscountRequest(projectInfo, currentActivityRequest));
		CurrentActivityResponse currentActivityResponse = new CurrentActivityResponse();;
		if(interestRateDiscount != null) {
			currentActivityResponse.setActivityId(interestRateDiscount.getActivityId());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			currentActivityResponse.setEndTime(simpleDateFormat.format(interestRateDiscount.getEndDate()));
			currentActivityResponse.setStartTime(simpleDateFormat.format(interestRateDiscount.getStartDate()));
			currentActivityResponse.setDiscount(interestRateDiscount.getRatio().multiply(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
		}
		return currentActivityResponse;
	}

	@Override
	public PrepayProductResponse getPrepayProduct(PrepayProductRequest prepayProductRequest) {
		long prepayAmount = prepayProductRequest.getPrepayAmount();
		ProjectInfo projectInfo = projectApi.getProjectInfo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);

		// 获取折扣
		InterestRateDiscountRequest interestRateDiscountRequest = generateInterestRateDiscountRequest(projectInfo, prepayProductRequest);
		InterestRateDiscountResponse interestRateDiscountResponse =intersetRateApiImpl.getInterestRateAfterDiscount(interestRateDiscountRequest);
		InterestRateUnit interestRateUnit = interestRateDiscountResponse.getInterestRateUnit();
		BigDecimal interestRate = interestRateDiscountResponse.getInterestRate();
		InterestRateDiscount interestRateDiscount = interestRateDiscountResponse.getDiscountInfo();

		PrepayProductResponse prepayProductResponse = new PrepayProductResponse();
		prepayProductResponse.setLoanPeriodUnit(projectInfo.getLoanPeriodUnit().name());
		prepayProductResponse.setLoanPeriod(projectInfo.getLoanPeriodLower());
		prepayProductResponse.setInterestRateUnit(interestRateUnit.name());
		prepayProductResponse.setInterestRate(interestRate.setScale(5, BigDecimal.ROUND_HALF_UP).toString());
		prepayProductResponse.setPrepayAmount(prepayAmount);

		if(interestRateDiscount != null) {
			prepayProductResponse.setActivityId(interestRateDiscount.getActivityId());
			prepayProductResponse.setOriginInterestRate(projectInfo.getInterestRateLower().setScale(5, BigDecimal.ROUND_HALF_UP).toString());
			prepayProductResponse.setOriginInterestRateUnit(projectInfo.getInterestRateUnit().name());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			prepayProductResponse.setEndTime(simpleDateFormat.format(interestRateDiscount.getEndDate()));
			prepayProductResponse.setStartTime(simpleDateFormat.format(interestRateDiscount.getStartDate()));
			prepayProductResponse.setDiscount(interestRateDiscount.getRatio().multiply(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
		}
		try {
			PreCalcInterestRequest preCalcInterestRequest = new PreCalcInterestRequest();
			preCalcInterestRequest.setFactorId(interestRateDiscountRequest.getFactorId());
			preCalcInterestRequest.setFinanceId(interestRateDiscountRequest.getFinanceId());
			preCalcInterestRequest.setInterestRate(interestRate);
			preCalcInterestRequest.setInterestRateUnit(interestRateUnit);
			preCalcInterestRequest.setLoanDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			preCalcInterestRequest.setPrincipal(prepayAmount);
			preCalcInterestRequest.setProductId(projectInfo.getId());
			preCalcInterestRequest.setLoanPeriodUnit(projectInfo.getLoanPeriodUnit());
			preCalcInterestRequest.setLoanPeriod(projectInfo.getLoanPeriodLower());
			prepayProductResponse.setInterestAmount(repaymentTransactionServiceImpl.preCalcInterest(preCalcInterestRequest).getRepaymentPlans().get(0).getInterest());
		} catch (Exception e) {
			logger.error("计算手续费金额出现错误", e);
			throw WebException.instance(ReturnCode.ERROR);
		}
		return prepayProductResponse;
	}

	private InterestRateDiscountRequest generateInterestRateDiscountRequest(ProjectInfo projectInfo, CurrentActivityRequest currentActivityRequest) {
		ContractQueryCriteria contractQueryCriteria = new ContractQueryCriteria();
		contractQueryCriteria.setMemberId(outCustomerServiceImpl.getCanaFinanceIdByOutCustomerId(currentActivityRequest.getInstitution(), currentActivityRequest.getCustomerId()));
		contractQueryCriteria.setProductId(projectInfo.getId());
		List<ContractInfoDTO> contractInfoDTOs = assetApiImpl.getContractsWithoutPaging(contractQueryCriteria);	
		if(CollectionUtils.isEmpty(contractInfoDTOs)){
			logger.error("合同表监管账户为空");
			throw WebException.instance(ReturnCode.ERROR, "该客户未签署合同");
		}
		if (contractInfoDTOs.size() > 1)
			throw WebException.instance(ReturnCode.ERROR, "该客户合同数量存在异常");

		InterestRateDiscountRequest interestRateDiscountRequest = new InterestRateDiscountRequest();
		interestRateDiscountRequest.setFactorId(contractInfoDTOs.get(0).getFactorId());
		interestRateDiscountRequest.setFinanceId(outCustomerServiceImpl.getCanaFinanceIdByOutCustomerId(currentActivityRequest.getInstitution(), currentActivityRequest.getCustomerId()));
		interestRateDiscountRequest.setOriginInterestRate(projectInfo.getInterestRateLower());
		interestRateDiscountRequest.setOriginInterestRateUnit(projectInfo.getInterestRateUnit());
		interestRateDiscountRequest.setProductId(projectInfo.getId());
		return interestRateDiscountRequest;
	}
	
}
