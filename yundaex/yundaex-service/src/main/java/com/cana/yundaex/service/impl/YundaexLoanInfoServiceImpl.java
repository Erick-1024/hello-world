package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample.Criteria;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleCollectExample;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyQueryDTO;
import com.cana.yundaex.common.dto.YundaexRepaymentRecordDTO;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.service.IYundaexCustomerApplyService;
import com.cana.yundaex.service.IYundaexLoanInfoService;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class YundaexLoanInfoServiceImpl implements IYundaexLoanInfoService {
	
	@Resource
	private ILoanInfoService loanInfoService;
	
	@Resource
	private YundaexOutCustomerMapper yundaexOutCustomerMapper;
	
	@Resource
	private IYundaexCustomerApplyService yundaexCustomerApplyService;
	
	@Resource
	private RepaymentSingleCollectMapper repaymentSingleCollectMapper;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;

	@Override
	public List<YundaexRepaymentRecordDTO> yundaexRepaymentRecordQuery(YundaexLoanInfoQueryDTO loanInfoQueryDTO) throws Exception {
		if(StringUtils.isBlank(loanInfoQueryDTO.getStationNo())){
			throw WebException.instance(ReturnCode.YP7002);
		}
		if(StringUtils.isBlank(loanInfoQueryDTO.getSign())){
			throw WebException.instance(ReturnCode.TP3006);
		}
		StringBuffer sb = new StringBuffer();
		sb.append(loanInfoQueryDTO.getStationNo());
		sb.append(StringUtils.isBlank(loanInfoQueryDTO.getStartBeginDate()) ? "" : loanInfoQueryDTO.getStartBeginDate());
		sb.append(StringUtils.isBlank(loanInfoQueryDTO.getEndBeginDate()) ? "" : loanInfoQueryDTO.getEndBeginDate());
		sb.append(StringUtils.isBlank(loanInfoQueryDTO.getStartExpireDate()) ? "" : loanInfoQueryDTO.getStartExpireDate());
		sb.append(StringUtils.isBlank(loanInfoQueryDTO.getEndExpireDate()) ? "" : loanInfoQueryDTO.getEndExpireDate());
		vbamCommonServiceImpl.rsaVerify(sb.toString().getBytes(), Institution.cana.name(), loanInfoQueryDTO.getSign().getBytes(), true);
		YundaexCustomerApplyQueryDTO yundaexCustomerApplyQueryDTO = new YundaexCustomerApplyQueryDTO();
		yundaexCustomerApplyQueryDTO.setStationNo(loanInfoQueryDTO.getStationNo());
		List<YundaexCustomerApplyDTO> customerApplyDTO = yundaexCustomerApplyService.getYdCustApplyByParam(yundaexCustomerApplyQueryDTO);
		if(customerApplyDTO == null || customerApplyDTO.size() < 1){
			throw WebException.instance(ReturnCode.YP5149);
		}
		List<RepaymentLoanInfo> loanInfoList = loanInfoService.queryLoanInfoByCondition(convertQueryCondition(loanInfoQueryDTO));
		return convertRepaymentRecord(loanInfoList, customerApplyDTO.get(0));
	}
	
	private RepaymentLoanInfoExample convertQueryCondition(YundaexLoanInfoQueryDTO loanInfoQueryDTO){
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		if(StringUtils.isBlank(loanInfoQueryDTO.getStationNo())){
			throw WebException.instance(ReturnCode.YP7002);
		}
		YundaexOutCustomerExample yundaexExample = new YundaexOutCustomerExample();
		yundaexExample.createCriteria().andStationNoEqualTo(loanInfoQueryDTO.getStationNo());
		List<YundaexOutCustomer> customerList = yundaexOutCustomerMapper.selectByExample(yundaexExample);
		if(customerList == null || customerList.size() < 1){
			throw WebException.instance(ReturnCode.YP5018);
		}
		Criteria createCriteria = example.createCriteria();
		createCriteria.andFinanceIdEqualTo(customerList.get(0).getMemberId());
		// 设置放款日期范围
		if(StringUtils.isNotBlank(loanInfoQueryDTO.getStartBeginDate()) && StringUtils.isNotBlank(loanInfoQueryDTO.getEndBeginDate())){
			createCriteria.andLoanDateBetween(loanInfoQueryDTO.getStartBeginDate(), loanInfoQueryDTO.getEndBeginDate());
		} else if(StringUtils.isBlank(loanInfoQueryDTO.getStartBeginDate()) && StringUtils.isNotBlank(loanInfoQueryDTO.getEndBeginDate())){
			createCriteria.andLoanDateBetween(DateTime.parse(loanInfoQueryDTO.getEndBeginDate()).minusMonths(2).toString("yyyy-MM-dd"), loanInfoQueryDTO.getEndBeginDate());
		} else if(StringUtils.isNotBlank(loanInfoQueryDTO.getStartBeginDate()) && StringUtils.isBlank(loanInfoQueryDTO.getEndBeginDate())){
			createCriteria.andLoanDateBetween(loanInfoQueryDTO.getStartBeginDate(), DateTime.parse(loanInfoQueryDTO.getStartBeginDate()).plusMonths(2).toString("yyyy-MM-dd"));
		}
		// 设置到期日范围
		if(StringUtils.isNotBlank(loanInfoQueryDTO.getStartExpireDate()) && StringUtils.isNotBlank(loanInfoQueryDTO.getEndExpireDate())){
			createCriteria.andDueDateBetween(loanInfoQueryDTO.getStartExpireDate(), loanInfoQueryDTO.getEndExpireDate());
		} else if(StringUtils.isBlank(loanInfoQueryDTO.getStartExpireDate()) && StringUtils.isNotBlank(loanInfoQueryDTO.getEndExpireDate())){
			createCriteria.andDueDateBetween(DateTime.parse(loanInfoQueryDTO.getEndExpireDate()).minusMonths(2).toString("yyyy-MM-dd"), loanInfoQueryDTO.getEndExpireDate());
		} else if(StringUtils.isNotBlank(loanInfoQueryDTO.getStartExpireDate()) && StringUtils.isBlank(loanInfoQueryDTO.getEndExpireDate())){
			createCriteria.andDueDateBetween(loanInfoQueryDTO.getStartExpireDate(), DateTime.parse(loanInfoQueryDTO.getStartExpireDate()).plusMonths(2).toString("yyyy-MM-dd"));
		}
		return example;
	}
	
	private List<YundaexRepaymentRecordDTO> convertRepaymentRecord(List<RepaymentLoanInfo> loanInfoList, YundaexCustomerApplyDTO yundaexCustomerApplyDTO) throws Exception{
		List<YundaexRepaymentRecordDTO> yundaexRepaymentRecordDTOList = Lists.newArrayList();
		for(RepaymentLoanInfo repaymentLoanInfo:loanInfoList){
			RepaymentSingleCollectExample repaymentSingleCollectExample = new RepaymentSingleCollectExample();
			repaymentSingleCollectExample.createCriteria().andLoanInfoIdEqualTo(repaymentLoanInfo.getId());
			List<RepaymentSingleCollect> repaymentSingleCollectList = repaymentSingleCollectMapper.selectByExample(repaymentSingleCollectExample);
			if(repaymentSingleCollectList != null && repaymentSingleCollectList.size() > 0){
				for(RepaymentSingleCollect repaymentSingleCollect:repaymentSingleCollectList){
					YundaexRepaymentRecordDTO yundaexRepaymentRecordDTO = new YundaexRepaymentRecordDTO();
					yundaexRepaymentRecordDTO.setStationNo(yundaexCustomerApplyDTO.getStationNo());
					yundaexRepaymentRecordDTO.setStationName(yundaexCustomerApplyDTO.getStationName());
					yundaexRepaymentRecordDTO.setCustName(yundaexCustomerApplyDTO.getCustName());
					yundaexRepaymentRecordDTO.setCustIdNo(yundaexCustomerApplyDTO.getCustIdno());
					yundaexRepaymentRecordDTO.setPutOutNo(repaymentLoanInfo.getLoanNo());
					yundaexRepaymentRecordDTO.setPutOutAmt(repaymentLoanInfo.getFinanceAmount());
					yundaexRepaymentRecordDTO.setRepayMode(repaymentLoanInfo.getRepaymentMethod());
					yundaexRepaymentRecordDTO.setRepayAmt(repaymentSingleCollect.getRepaymentTotalAmount());
					yundaexRepaymentRecordDTO.setRepayDate(repaymentSingleCollect.getRepaymentDate());
					yundaexRepaymentRecordDTOList.add(yundaexRepaymentRecordDTO);
				}
			}
		}
		return yundaexRepaymentRecordDTOList;
	}
}
