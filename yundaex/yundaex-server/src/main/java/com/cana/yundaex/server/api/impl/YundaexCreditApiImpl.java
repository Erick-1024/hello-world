/**
 * 
 */
package com.cana.yundaex.server.api.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexAccessCreditLimitCustomerInfoDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditAuditListDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditLimitDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListMinDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListQueryDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditQueryDTO;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.cana.yundaex.api.IYundaexCreditApi;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.cana.yundaex.common.enums.Institution;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.service.IYundaexAuditResultService;
import com.cana.yundaex.service.IYundaexCreditService;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.transaction.IYundaexCreditLimitTransactionService;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * 韵达项目 网点信息同步API实现类
 * @author guguanggong
 *
 */
public class YundaexCreditApiImpl implements IYundaexCreditApi {

	@Resource
	 private IYundaexCreditService yundaexCreditService; 
	
	@Resource
	private IYundaexAuditResultService yundaexAuditResultService;
	
	@Resource
    private IUserApi userApi;
	
	@Resource
	private IYundaexAuditApi  yundaexAuditApi;
	
	@Resource
	private IYundaexRetryTaskService ydRetryTaskService;
	
	@Resource
	private IYundaexMessageService messageService;
	
	@Resource
	private IYundaexCreditLimitTransactionService yundaexCreditLimitTransactionService;
	
	@Resource
	private IYundaexAutomaticRulesTransactionService yundaexAutomaticRulesTransactionService;
	
	/**
	 * 查询韵达申请额度列表
	 */
	@Override
	public PageList<YundaexCreditLimitDTO> getYundaexCreditList(YundaexCreditQueryDTO yundaexCreditQueryDTO) {
		return yundaexCreditService.getYundaexCreditList(yundaexCreditQueryDTO);
	}

	/**
	 * 保存韵达客户申请信息
	 */
	@Override
	public void saveYundaexAuditResult(YdCustomerApplyRequestDTO ydCustomerApplyRequestDTO) throws Exception {
		yundaexAuditResultService.saveYundaexAuditResult(ydCustomerApplyRequestDTO);
	}

	/**
	 * 已审核额度信息查询
	 */
	@Override
	public YdQueryCreditLimitResponse queryCreditLimit(YdQueryCreditLimitDTO ydQueryCreditLimitDTO) {
		return yundaexCreditService.queryCreditLimit(ydQueryCreditLimitDTO);
	}

	@Override
	public PageList<YundaexCreditListMinDTO> getYundaexCreditStateList(YundaexCreditListQueryDTO creditQueryDTO) {
		return yundaexCreditService.getYundaexCreditStateList(creditQueryDTO);
	}

	/**
	 * 线下数据发送激活链接
	 */
	@Override
	public void sendActivationLink(String userId) throws Exception {
		String generateActivacationURL = userApi.generateActivacationURL(userId);
		
		// 查询额度信息
		CreditLimit limit = yundaexCreditService.getCreditLimitByMemberId(userId);
		// 查询网点信息
		YdCustomerApplyDetailDTO ydCustomerApplyDetailDTO =  yundaexAuditApi.getUserBaseInfo(userId);
		
		
		if (StringUtils.isNotBlank(generateActivacationURL) && limit != null && limit.getTotalLimit() >= 0 || ydCustomerApplyDetailDTO != null) {
			YundaexCustomerApply ydCustomerApply = new YundaexCustomerApply();
			ydCustomerApply.setCustEmail(ydCustomerApplyDetailDTO.getCustEmail());
			ydCustomerApply.setCustPhone(ydCustomerApplyDetailDTO.getCustPhone());
			ydCustomerApply.setStationName(ydCustomerApplyDetailDTO.getStationName());
			
			ydRetryTaskService.createAuditResultNotify(Institution.yundaex, ydCustomerApplyDetailDTO.getStationNo(),
					YundaexAuditState.ACCESS.name(), limit.getTotalLimit(), generateActivacationURL);
			messageService.sendAuditSuccessMessageAndMail(ydCustomerApply, MoneyUtil.cent2Yuan(limit.getTotalLimit()),
					generateActivacationURL);
		} else {
			throw WebException.instance("激活链接为空");
		}
	}

	@Override
	public PageList<YundaexCreditAuditListDTO> getCreditAuditList(YundaexCreditListQueryDTO creditQueryDTO) {
		return yundaexCreditService.getCreditAuditList(creditQueryDTO);
	}

	@Override
	public void creditAuditReject(String id) {
		yundaexCreditLimitTransactionService.creditAuditReject(id);
	}

	@Override
	public void creditAuditPass(String id) {
		yundaexCreditLimitTransactionService.creditAuditPass(id);
	}
	
	@Override
	public List<YundaexAccessCreditLimitCustomerInfoDTO> getYundaexAccessCreditLimitCustomerInfoDTO() {
		return yundaexAutomaticRulesTransactionService.getYundaexAccessCreditLimitCustomerInfoDTO();
	}
	
	@Override
	public String getYundaexGradeByScore(BigDecimal score) {
		return yundaexAutomaticRulesTransactionService.getYundaexGradeInfoByScore(score).getGrade();
	}
}
