package com.cana.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.AssetLoanCustomMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.mapper.gen.LoanPaidMapper;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import com.cana.asset.dao.po.LoanInfoExample.Criteria;
import com.cana.asset.service.IAssetLoanInfoService;
import com.cana.asset.service.convertors.LoanInfoConvertor;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanListRequest;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.dto.ListResult;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetLoanInfoServiceImpl implements IAssetLoanInfoService {

	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	@Resource
	private LoanInfoConvertor loanInforConvertor;
	
	@Resource
	private LoanInfoMapper loanInfoMapper;
	
	@Resource
	private LoanPaidMapper loanPaidMapper;
	
	@Resource
	private AssetLoanCustomMapper assetLoanCustomMapper;
	
	@Resource
	private LoanInfoConvertor loanInfoConvertor;
	
	@Override
	public ListResult<AssetLoanDTO> getLoanList(AssetLoanListRequest assetLoanListRequest) {
		LoanInfoExample loanInfoExample = new LoanInfoExample();
		Criteria criteria = loanInfoExample.createCriteria();
		if(StringUtils.isNotBlank(assetLoanListRequest.getCustomerName()))
			criteria.andCustomerNameLike("%" + assetLoanListRequest.getCustomerName() + "%");
		if(StringUtils.isNotBlank(assetLoanListRequest.getBusinessContractNo()))
			criteria.andBusinessContractNoLike("%" + assetLoanListRequest.getBusinessContractNo() + "%");
		if(assetLoanListRequest.getBusinessProduct() != null)
			criteria.andBusinessProductEqualTo(assetLoanListRequest.getBusinessProduct().name());
		if(assetLoanListRequest.getSettleStatus() != null)
			criteria.andSettleStatusEqualTo(assetLoanListRequest.getSettleStatus().name());
		if(StringUtils.isNotBlank(assetLoanListRequest.getLoanStartDate()))
			criteria.andLoanDateGreaterThanOrEqualTo(assetLoanListRequest.getLoanStartDate());
		if(StringUtils.isNotBlank(assetLoanListRequest.getLoanEndDate()))
			criteria.andLoanDateLessThanOrEqualTo(assetLoanListRequest.getLoanEndDate());
		if(StringUtils.isNotBlank(assetLoanListRequest.getDueStartDate()))
			criteria.andDueDateGreaterThanOrEqualTo(assetLoanListRequest.getDueStartDate());
		if(StringUtils.isNotBlank(assetLoanListRequest.getDueEndDate()))
			criteria.andDueDateLessThanOrEqualTo(assetLoanListRequest.getDueEndDate());
		if(StringUtils.isBlank(assetLoanListRequest.getFactorId()))
			criteria.andCustomerIdIn(assetLoanListRequest.getCustomerIds());
		else
			criteria.andFactorIdEqualTo(assetLoanListRequest.getFactorId());
		loanInfoExample.setOrderByClause("update_time desc");
		loanInfoExample.setLimitStart((assetLoanListRequest.getPage() -1) * assetLoanListRequest.getPageSize());
		loanInfoExample.setLimitEnd(assetLoanListRequest.getPageSize());
		return ListResult.success(loanInforConvertor.convertLoanInfo2AssetLoanDTO(loanInfoMapper.selectByExample(loanInfoExample), false), loanInfoMapper.countByExample(loanInfoExample));
	}

	@Override
	public AssetLoanDTO getLoanDetail(String id) {
		LoanInfo loan = getLoanInfo(id);
		if (loan == null)
			throw WebException.instance("放款不存在");
		return loanInfoConvertor.convertLoanInfo2AssetLoanDTO(Lists.newArrayList(loan), true).get(0);
	}
	
	@Override
	public ListResult<LoanPaidDTO> getLoanPaidList(AssetLoanPaidListRequest assetLoanPaidListRequest) {
		List<LoanPaidDTO> loanPaidDTOs = assetLoanCustomMapper.getLoanPaidDTOList(assetLoanPaidListRequest);
		for (LoanPaidDTO loanPaidDTO : loanPaidDTOs)
			if(StringUtils.isNotBlank(loanPaidDTO.getRepaymentDate())) {
				String diffInDayStr = String.valueOf(loanInforConvertor.diffInDay(loanPaidDTO.getRepaymentDate(), loanPaidDTO.getPaidDate()));
				if(loanPaidDTO.getRepaymentDate().compareTo(loanPaidDTO.getPaidDate()) > 0)
					loanPaidDTO.setForwardDays(diffInDayStr);
				else if(loanPaidDTO.getRepaymentDate().compareTo(loanPaidDTO.getPaidDate()) < 0)
					loanPaidDTO.setOverdueDays(diffInDayStr);
			}
		return ListResult.success(loanPaidDTOs, assetLoanCustomMapper.countLoanPaidDTOList(assetLoanPaidListRequest));
	}

	@Override
	public LoanInfo getLoanInfo(String id) {
		LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(id);
		if(loanInfo == null)
			throw WebException.instance("融资信息不存在");
		return loanInfo;
	}

}
