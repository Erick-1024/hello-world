package com.cana.credit.openapi.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetApi;
import com.cana.credit.api.ICreditApi;
import com.cana.credit.openapi.utils.ExceptionHandler;
import com.cana.member.api.IUserApi;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.credit.openapi.CreditCustomerFinanceInfoRequest;
import com.cana.vbam.common.credit.openapi.CreditCustomerFinanceInfoResponse;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryResponseDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.WebEnv;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
public class CreditCustomerFinanceInfoController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	@Resource
	private ICreditApi creditApi;
	
	@Resource
	private IFinanceReportApi financeReportApi;
	
	@Resource
	private IUserApi userApi;
	@Resource
	private IAssetApi assetApi;
	
	@RequestMapping(value = "/credit/getCustomerFinanceInfo" ,method=RequestMethod.POST)
	@ResponseBody
	public CreditCustomerFinanceInfoResponse getCreditLoanList(@RequestBody CreditCustomerFinanceInfoRequest request) {
		logger.info("请求getCustomerFinanceInfo接口参数：{}", gson.toJson(request));
		CreditCustomerFinanceInfoResponse response = new CreditCustomerFinanceInfoResponse(); 
		try{
			
			QueryCreditLimitResponse limit = creditApi.queryCreditLimitBalance(getQueryCreditLimitParam(request));
			
			String financeId = creditApi.getCanaFinanceIdByOutCustomerId(request.getInstitution(), request.getCustomerId());
			QueryRepaymentSummaryRequestDTO summaryRequest = getRepaymentSummaryRequest(request, financeId);
			QueryRepaymentSummaryResponseDTO summary = financeReportApi.queryRepaymentSummary(summaryRequest);
			
			CustomerDetailDTO customer = userApi.queryCustomerDetail(financeId);
			
			if (UserStatus.PENDINGACTIVATE == customer.getUserStatus()) {
				response.setCanaUrlName("去激活");
				response.setCanaUrl(userApi.generateActivacationURL(financeId));
			} else if (!CreditLimitStatus.INACTIVE.name().equals(limit.getStatus())) {
				String accountNo = assetApi.getAccountNoByTravelzenFinanceId(financeId);
				response.setCanaUrlName("还款");
				response.setCanaUrl(WebEnv.getVBAMPlatformPath() + "/credit/repaymentNotice?accountNo=" + accountNo + "&customerId=" + financeId);
			} else {
				response.setCanaUrlName("去激活");
				response.setCanaUrl(WebEnv.getVBAMPlatformPath());
			}
			response.setStatus(limit.getStatus());
			response.setTotalLimit(limit.getTotalLimit());
			response.setUnusedLimit(limit.getUnusedLimit());
			response.setRepaymentAmount(summary.getRepaymentAmount());
			response.setRetCode(ReturnCode.SUCCESS.getRetCode());
			response.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e){
			ExceptionHandler.handleException(e, "查询放款信息列表发生异常", response);
		}
		return response;
	}
	
	private QueryRepaymentSummaryRequestDTO getRepaymentSummaryRequest(CreditCustomerFinanceInfoRequest request,
			String financeId) {
		QueryRepaymentSummaryRequestDTO repaymentSummaryRequest = new QueryRepaymentSummaryRequestDTO();
		repaymentSummaryRequest.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		repaymentSummaryRequest.setFinanceId(financeId);
		repaymentSummaryRequest.setOutCustomerId(request.getCustomerId());
		return repaymentSummaryRequest;
	}
	private QueryCreditLimitDTO getQueryCreditLimitParam(CreditCustomerFinanceInfoRequest request) {
		QueryCreditLimitDTO queryDTO = new QueryCreditLimitDTO();
		queryDTO.setInstitution(request.getInstitution());
		queryDTO.setCustomerId(request.getCustomerId());
		queryDTO.setSign(request.getSign());
		return queryDTO;
	}
}
