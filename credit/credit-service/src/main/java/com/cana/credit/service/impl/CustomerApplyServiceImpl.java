/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.credit.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.CustomerApplyExample;
import com.cana.credit.service.ICustomerApplyService;
import com.cana.credit.service.convertors.CustomerApplyConvertor;
import com.cana.credit.service.transaction.ICustomerApplyTransactionService;
import com.cana.flight.finance.common.dto.CustomerSaleDataVO;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.flight.finance.service.utils.IFlightFinanceServiceHelper;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyDetailDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyListQueryDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyMinDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerSaleDataDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
@Service
public class CustomerApplyServiceImpl implements ICustomerApplyService {

	@Resource
	private ICustomerApplyTransactionService customerApplyTransactionService;
	
	@Resource
	private CustomerApplyMapper customerApplyMapper;
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;
	
	@Resource
	private IUserApi userApiImpl;
	
	@Resource
	private IFlightFinanceServiceHelper flightFinanceServiceHelper;
	
	@Override
	public void auditTravelzenCustomer(TravelzenCustomerAuditResultDTO resultDTO) {
		CustomerApply apply = customerApplyMapper.selectByPrimaryKey(resultDTO.getCustomerApplyId());
		if(apply == null)
			throw WebException.instance("当前申请不存在");
		if (!EnumUtils.isValidEnum(ApplyApplicantType.class, resultDTO.getApplicantType()))
			throw WebException.instance("请选择申请人类型");

		String individualIdentityCardNo = null;
		if (ApplyApplicantType.individual.name().equals(resultDTO.getApplicantType())) {
			if (StringUtils.isBlank(apply.getBusinessLicenceNo())
					|| !StringUtils.equals(apply.getBusinessLicenceNo(), apply.getOrganizationNo())
					|| !StringUtils.equals(apply.getBusinessLicenceNo(), apply.getTaxRegistrationCertificateNo()))
				throw WebException.instance("个人身份证号码必须一致");
			individualIdentityCardNo = apply.getBusinessLicenceNo();
		}
		String userId = userApiImpl.queryOrGenerateUserId(apply.getCompanyName(), individualIdentityCardNo);
		customerApplyTransactionService.auditTravelzenCustomer(resultDTO, userId);
	}
	
	@Override
	public PageList<CustomerApplyMinDTO> getCustomerApplyList(CustomerApplyListQueryDTO customerApplyListQueryDTO){
		PageList<CustomerApplyMinDTO> result = new PageList<CustomerApplyMinDTO>();
		String startDateStr = customerApplyListQueryDTO.getStartDate();
		String endDateStr = customerApplyListQueryDTO.getEndDate();
		String customerName = customerApplyListQueryDTO.getCustomerName();
		AccessManualState auditState = customerApplyListQueryDTO.getAuditState();
		AccessAutomaticState automaticState = customerApplyListQueryDTO.getAutomaticState();
		Boolean inWhitelist = customerApplyListQueryDTO.getInWhitelist();
		ApplyApplicantType applicantType = customerApplyListQueryDTO.getApplicantType();
		CustomerApplyExample customerApplyExample = new CustomerApplyExample();
		CustomerApplyExample.Criteria criteria = customerApplyExample.createCriteria();
		try {
			if(StringUtils.isNotBlank(startDateStr))
				criteria.andApplyDateGreaterThanOrEqualTo(DateUtils.parseDate(startDateStr.trim(), "yyyy-MM-dd"));
			if(StringUtils.isNotBlank(endDateStr))
				criteria.andApplyDateLessThanOrEqualTo(DateUtils.parseDate(endDateStr.trim(), "yyyy-MM-dd"));
		} catch (ParseException e) {
			throw WebException.instance("查询时间格式错误");
		}
		if(StringUtils.isNotBlank(customerName))
			criteria.andCompanyNameLike("%" + customerName.trim() + "%");
		if(auditState!=null)
			criteria.andAccessManualStateEqualTo(auditState.name());
		if(automaticState!=null)
			criteria.andAccessAutomaticStateEqualTo(automaticState.name());
		criteria.andAccessAutomaticStateNotEqualTo(AccessAutomaticState.WAIT.name());
		if(applicantType!=null)
			criteria.andApplicantTypeEqualTo(applicantType.name());
		if(inWhitelist!=null)
			criteria.andInWhitelistEqualTo(inWhitelist);
		int pageSize = customerApplyListQueryDTO.getPageSize();
		customerApplyExample.setOrderByClause("update_time desc");
		customerApplyExample.setLimitStart((customerApplyListQueryDTO.getPage() -1) * pageSize);
		customerApplyExample.setLimitEnd(pageSize);
		List<CustomerApply> customerApplys = customerApplyMapper.selectByExample(customerApplyExample);
		if(customerApplys.size() == 0)
			return result;
		List<CustomerApplyMinDTO> customerApplyMinDTOs = CustomerApplyConvertor.convertCustomerApply2CustomerApplyMinDTO(customerApplys);
		TzCustomerInfoExample tzCustomerInfoExample = new TzCustomerInfoExample();
		for(int i = 0; i < customerApplyMinDTOs.size(); i++) {
			CustomerApplyMinDTO customerApplyMinDTO = customerApplyMinDTOs.get(i);
			CustomerApply customerApply = customerApplys.get(i);
			tzCustomerInfoExample.createCriteria().andTzCustomerIdEqualTo(customerApply.getTzCustomerId());
			List<TzCustomerInfo> tzCustomerInfos = tzCustomerInfoMapper.selectByExample(tzCustomerInfoExample);
			if(CollectionUtils.isNotEmpty(tzCustomerInfos))
				customerApplyMinDTO.setCanaId(tzCustomerInfos.get(0).getTzShortId().toString());
			tzCustomerInfoExample.clear();
			if(StringUtils.isNotBlank(customerApply.getAuditorId())) {
				try {
					customerApplyMinDTO.setAuditor(userApiImpl.queryEmployeeDetail(customerApply.getAuditorId()).getUsername());
				} catch (Exception e) {
					throw WebException.instance("无法获取审核人信息");
				}
			}
			if(StringUtils.isNotBlank(customerApply.getApproverId())) {
				try {
					customerApplyMinDTO.setApprover(userApiImpl.queryEmployeeDetail(customerApply.getApproverId()).getUsername());
				} catch (Exception e) {
					throw WebException.instance("无法获取审批人信息");
				}
			}
		}
		result.setRecords(customerApplyMinDTOs);
		result.setTotalRecords(customerApplyMapper.countByExample(customerApplyExample));
		return result;
	}

	@Override
	public CustomerApplyDetailDTO getCustomerApplyInfo(String id) {
		CustomerApply apply = customerApplyMapper.selectByPrimaryKey(id);
		CustomerApplyDetailDTO customerApplyDetailDTO = CustomerApplyConvertor.convertCustomerApply2CustomerApplyDetailDTO(apply);
//		if(CollectionUtils.isEmpty(customerApplyDetailDTO.getSaleDatas())){//对于以前旧的申请（没有保存销售数据）
			List<CustomerSaleDataVO> customerApplySaleDataVOs = flightFinanceServiceHelper.getMonthAverageSales(apply.getTzCustomerId(), apply.getApplyDate(),18);
			customerApplyDetailDTO.setSaleDatas(convertCustomerApplySaleDataVOs2DTOs(customerApplySaleDataVOs));
//		}
		return customerApplyDetailDTO;
	}
	
	//转换销售数据
	private List<CustomerSaleDataDTO> convertCustomerApplySaleDataVOs2DTOs(List<CustomerSaleDataVO> customerApplySaleDataVOs){
		if(CollectionUtils.isEmpty(customerApplySaleDataVOs))
			return null;
		List<CustomerSaleDataDTO> customerApplySaleDataDTOs = new ArrayList<>();
		for(CustomerSaleDataVO customerApplySaleDataVO : customerApplySaleDataVOs){
			CustomerSaleDataDTO customerApplySaleDataDTO = new CustomerSaleDataDTO();
			customerApplySaleDataDTO.setYear(customerApplySaleDataVO.getYear());
			customerApplySaleDataDTO.setMonth(customerApplySaleDataVO.getMonth());
			customerApplySaleDataDTO.setSaleMoneyStr(MoneyUtil.cent2Yuan(customerApplySaleDataVO.getSaleMoney()));
			customerApplySaleDataDTOs.add(customerApplySaleDataDTO);
		}
		return customerApplySaleDataDTOs;
	}
	
}
