package com.cana.vbam.front.biz.controller.credit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.common.dto.CreditTradeDTO;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.LoanInfoDetail;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListResponseDTO;
import com.cana.vbam.common.utils.Constants;

/**
 * @author tangyihong
 *
 */
@Controller
@RequestMapping("/credit/trade")
public class CreditTradeController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ICreditApi creditApi;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IFinanceReportApi financeApi;
	
	/**---------放款明细模块------------**/
	@RequestMapping(value="/loan/details")
	public String gotoLoanDetails(Model model){
		logger.info("进入放款明细列表页！");
		return "page/credit/trade/loanDetails";
	}
	
	@RequestMapping(value="/loan/details/searchList")
	@ResponseBody
	public PageResult<LoanInfoDetail> searchLoanDetailsList(CreditTradeQueryCriteria criteria){
		QueryLoanInfoListRequestDTO request = new QueryLoanInfoListRequestDTO();
		request.setFinanceId(getCustomerIdIfLoginUserIsFinace());
		request.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		request.setFinanceCompany(criteria.getCustomerName());
		request.setLoanDateStart(criteria.getTradeStartDate());
		request.setLoanDateEnd(criteria.getTradeEndDate());
		request.setPage(criteria.getPage());
		request.setPageSize(criteria.getPageSize());
		request.setLoanInfoId(criteria.getSummaryId());
		PageResult<LoanInfoDetail> data = new PageResult<>();
		try {
			QueryLoanInfoListResponseDTO response = financeApi.queryLoanInfoList(request);
			data.setData(response.getLoanInfoDetailList());
			data.setTotal(response.getTotalNum());
		} catch (Exception e) {
			logger.error("查询放款明细列表失败",e);
		}
		return data;
	}
	
	@RequestMapping(value="/loan/details/searchDetail")
	@ResponseBody
	public PageResult<CreditTradeDTO> searchLoanDetailsDetail(int page,int pageSize,String summaryId){
		String finaceId = getCustomerIdIfLoginUserIsFinace();
		PageResult<CreditTradeDTO> creditTradeDTOs = creditApi.queryCreditLoanDetailsDetail(page,pageSize,summaryId, finaceId);
		return creditTradeDTOs;
	}

	/**---------退款明细模块------------**/
	@RequestMapping(value="/refund/details")
	public String gotoRefundDetails(Model model){
		logger.info("进入退款明细列表页！");
		return "page/credit/trade/refundDetails";
	}
	
	@RequestMapping(value="/refund/details/searchList")
	@ResponseBody
	public PageResult<CreditTradeDTO> searchRefundDetailsList(CreditTradeQueryCriteria criteria){
		criteria.setFinanceId(getCustomerIdIfLoginUserIsFinace());
		PageResult<CreditTradeDTO> creditTradeDTOs = creditApi.queryCreditTrades(criteria);
		return creditTradeDTOs;
	}
	
	private String getCustomerIdIfLoginUserIsFinace() {
		UserSessionDTO user = SecurityContextUtils.getUserDTOFromSession();
		if (user.getUserType() == UserType.FINACE) {
			return SecurityContextUtils.getCustomerId();
		} else {
			return null;
		}
	}
}
