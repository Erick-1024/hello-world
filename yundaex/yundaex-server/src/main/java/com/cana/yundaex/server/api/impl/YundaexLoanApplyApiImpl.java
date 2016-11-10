/**
 * 
 */
package com.cana.yundaex.server.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexProductDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowListDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowQueryDTO;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.api.IYundaexLoanApplyApi;
import com.cana.yundaex.common.dto.YundaexLoanInfoListRequest;
import com.cana.yundaex.common.dto.YundaexLoanInfoListResponse;
import com.cana.yundaex.common.enums.RepaymentMethod;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.po.InterestRate;
import com.cana.yundaex.service.IYundaexCreditService;
import com.cana.yundaex.service.IYundaexInterestRateService;
import com.cana.yundaex.service.IYundaexLoanInfoRecordService;
import com.cana.yundaex.service.transaction.IYundaexLoanApplyService;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

/**
 * 韵达项目-用款申请api实现
 * @author guguanggong
 *
 */
public class YundaexLoanApplyApiImpl implements IYundaexLoanApplyApi {

	@Resource
	private IYundaexLoanApplyService yundaexLoanApplyService;
	
	@Resource
	private IYundaexCreditService yundaexCreditService;
	
	@Resource
	private IYundaexAuditApi  yundaexAuditApi;
	
	@Resource
	private IAssetProjectManageApi assetProjectManageApi;
	
	@Resource
	private IYundaexInterestRateService yundaexInterestRateService;
	
	@Resource
	private IYundaexLoanInfoRecordService yundaexLoanInfoRecordService;

	/**
	 * 根据韵达客户信息 页面展示放款信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	@Override
	public YundaexLoanApplyDTO getLoanApplyDetails(String memberId) {
		// 页面基础信息
		YdCustomerApplyDetailDTO ydCustomerApplyDetailDTO = getYdCustomerApplyDetail(memberId);
		
		// 额度信息
		CreditLimit creditLimit = yundaexCreditService.getCreditLimitByMemberId(memberId);
		long totalLimi = creditLimit.getTotalLimit(); // 总额度
		long notUsedLimit = creditLimit.getUsedLimit(); // 已使用额度
		
		YundaexLoanApplyDTO yundaexLoanApplyDTO = new YundaexLoanApplyDTO();
		yundaexLoanApplyDTO.setStationNo(ydCustomerApplyDetailDTO.getStationNo());
		yundaexLoanApplyDTO.setStationName(ydCustomerApplyDetailDTO.getStationName());
		yundaexLoanApplyDTO.setStationMgr(ydCustomerApplyDetailDTO.getStationMgr());
		yundaexLoanApplyDTO.setLegalPerson(ydCustomerApplyDetailDTO.getLegalPerson());
		yundaexLoanApplyDTO.setDetailAddress(ydCustomerApplyDetailDTO.getDetailAddress());
		yundaexLoanApplyDTO.setTotalLimit(MoneyUtil.formatMoney(totalLimi));
		yundaexLoanApplyDTO.setNotUsedLimit(MoneyUtil.formatMoney(totalLimi - notUsedLimit));
		yundaexLoanApplyDTO.setApplyDate(DateUtils.formatDate(new Date(), ""));
		yundaexLoanApplyDTO.setLoanDate(DateUtils.formatDate(new Date()));
		
		// 项目信息
		List<YundaexProductDTO> yundaexProductDTOs = getYundaexProject(memberId);
		yundaexLoanApplyDTO.setYundaexProductDTOs(yundaexProductDTOs);
		
		return yundaexLoanApplyDTO;
	}
	
	/**
	 * 创建融资申请
	 * @throws Exception 
	 */
	@Override
	public void creditLoanApply(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO, UserSessionDTO userSessionDTO ) throws Exception {
		yundaexLoanApplyService.creditLoanApply(yundaexLoanApplyQueryDTO, userSessionDTO);
	}

	/**
	 * 获取申请用款流水列表
	 */
	@Override
	public PageResult<YundaexLoanFlowListDTO> queryLoanFlowList(YundaexLoanFlowQueryDTO loanFlowQueryDTO, String memberId) {
		return yundaexLoanApplyService.queryCreditFlowList(loanFlowQueryDTO, memberId);
	}
	
	
	/**
	 * 获取产品信息
	 * @return
	 */
	private List<YundaexProductDTO> getYundaexProject(String memberId) {
		ProjectDTO projectDTO = assetProjectManageApi.getProjectDetail(Constants.YUNDAEX_ASSET_PROJECT_ID);
		String repaymentMethods = projectDTO.getRepaymentMethods();
		if (repaymentMethods == null) {
			throw WebException.instance("还款方式不能为空");
		}
		List<InterestRate> interestRates = yundaexInterestRateService.getInterestRateByCustId(memberId);
		if (interestRates == null) {
			throw WebException.instance("利率不能为空");
		}
		List<YundaexProductDTO> yundaexProductDTOs = new ArrayList<YundaexProductDTO>();
		for (InterestRate  rate: interestRates) {
			if (repaymentMethods.contains(rate.getRepaymentMethod())) {
				YundaexProductDTO yundaexProductDTO = new YundaexProductDTO();
				yundaexProductDTO.setId(rate.getId());
				yundaexProductDTO.setLoanPeriod(RepaymentMethod.valueOf(rate.getRepaymentMethod()).deadLine().desc());
				yundaexProductDTO.setLoanPeriodUnit(RepaymentMethod.valueOf(rate.getRepaymentMethod()).deadLine().unit());
				BigDecimal intRate = rate.getInterestRate();
				yundaexProductDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(intRate));
				yundaexProductDTO.setInterestRateUnit(InterestRateUnit.DAY.desc());
				yundaexProductDTO.setRepaymentType(RepaymentMethod.valueOf(rate.getRepaymentMethod()).desc());
				yundaexProductDTOs.add(yundaexProductDTO);
			}
		}
		
		return yundaexProductDTOs;
	}
	
	/**
	 * 获取网点申请信息
	 * @param masterId
	 * @return
	 */
	private YdCustomerApplyDetailDTO getYdCustomerApplyDetail(String masterId) {
		YdCustomerApplyDetailDTO ydCustomerApplyDetailDTO = yundaexAuditApi.getUserBaseInfo(masterId);
		return ydCustomerApplyDetailDTO;
	}

	/**
	 * 韵达 放款信息查询接口
	 * @return
	 * @throws Exception 
	 */
	@Override
	public YundaexLoanInfoListResponse getYundaexLoanInfoList(YundaexLoanInfoListRequest yundaexLoanInfoListRequest) throws Exception {
		return yundaexLoanApplyService.getYundaexLoanInfoList(yundaexLoanInfoListRequest);
	}

	/**
	 * 额度检查
	 */
	@Override
	public void checkLimitBalanceEnough(String memberId, Long fee) {
		yundaexLoanApplyService.checkLimitBalanceEnough(memberId, fee);
	}

	/**
	 * 根据融资客户ID查询放款记录条数
	 * @param financeId
	 * @return
	 */
	@Override
	public int countYundaexLoanInfoRecord(String financeId) {
		return yundaexLoanInfoRecordService.countYundaexLoanInfoRecord(financeId);
	}
}
