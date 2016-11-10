package com.cana.asset.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.asset.api.IAssetCreditApi;
import com.cana.asset.dao.mapper.CreditCustomMapper;
import com.cana.asset.dao.mapper.gen.CreditAuditMapper;
import com.cana.asset.dao.mapper.gen.CreditMapper;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.CreditAudit;
import com.cana.asset.dao.po.CreditAuditExample;
import com.cana.asset.dao.po.CreditExample;
import com.cana.asset.service.IAssetCreditService;
import com.cana.asset.service.convertors.CreditConvertor;
import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.util.CreditUtil;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.CreditAuditDTO;
import com.cana.vbam.common.asset.dto.CreditAuditQueryCriteria;
import com.cana.vbam.common.asset.dto.CreditCheckModifyResultDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CreditQueryCriteria;
import com.cana.vbam.common.asset.dto.CreditRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.asset.dto.UserPrivilegeDTO;
import com.cana.vbam.common.asset.enums.CreditStatus;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.PaginationUtils;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
public class AssetCreditApiImpl implements IAssetCreditApi {

	@Resource
	private CreditMapper creditMapper;
	
	@Resource
	private CreditCustomMapper creditCustomMapper;
	
	@Resource
	private CreditAuditMapper creditAuditMapper;
	
	@Resource
	private IAssetCreditService assetCreditServiceImpl;
	
	@Resource
	private IAssetCreditTransactionService creditTransactionService;
	
	@Resource
	private CreditConvertor creditConvertor;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;
	
	@Override
	public ListResult<CustomerCreditDTO> searchCreditCustomerByCondition(CustomerCreditQueryCriteria queryCriteria){
		UserVo userVo = memberQueryApi.findUserById(queryCriteria.getUserId());
		UserPrivilegeDTO privilege = dataPermissionValidator.checkDataPermissions(userVo, null);
		if(StringUtils.isNotBlank(privilege.getFactorId())){
			queryCriteria.setFactorId(privilege.getFactorId());
		}
		if(CollectionUtils.isNotEmpty(privilege.getCustomerIds())){
			queryCriteria.setCustomerIds(privilege.getCustomerIdsList());
		}
		if(StringUtils.isBlank(queryCriteria.getCustomerName()))
			queryCriteria.setCustomerName(null);
		else
			queryCriteria.setCustomerName("%"+queryCriteria.getCustomerName()+"%");
		PaginationUtils.StandardizingPagination(queryCriteria);
		int count = creditCustomMapper.countCustomerByCondition(queryCriteria);
		List<CustomerCreditDTO> customerList = creditCustomMapper.searchCustomerByCondition(queryCriteria);
		if(CollectionUtils.isNotEmpty(customerList)){
			for(CustomerCreditDTO customerDTO : customerList){
				customerDTO.setCustomerTypeDesc(null == customerDTO.getCustomerType() ? null : customerDTO.getCustomerType().desc());
				
				if(queryCriteria.isApplyCredit()){
					CreditExample example = new CreditExample();
					example.createCriteria().andCustomerIdEqualTo(customerDTO.getId()).andStatusEqualTo(CreditStatus.NORMAL.name());
					
					List<Credit> creditList = creditMapper.selectByExample(example);
					long availableLimit = 0;
					for(Credit credit : creditList){
						if(CreditStatus.NORMAL.name().equals(credit.getStatus()) && ((CreditMode.SINGLE.name().equals(credit.getCreditMode()) && 
								!loanInfoTransactionService.checkContractNoHasLoan(credit.getBusinessContractNo())) || CreditMode.SYNTHETICAL.name().equals(credit.getCreditMode())))
						availableLimit += CreditUtil.getAvailableLimit(credit);
					}
					customerDTO.setAvailableLimit(availableLimit);
					customerDTO.setAvailableLimitDesc(MoneyArithUtil.convertMoneyToString(availableLimit));
				}
			}
		}
		return ListResult.success(customerList, count);
	}
	
	@Override
	public ListResult<CustomerCreditDTO> getCustomers(CustomerCreditQueryCriteria crieria) {
		UserVo userVo = memberQueryApi.findUserById(crieria.getUserId());
		crieria.setFactorId(dataPermissionValidator.checkDataPermissions(userVo, null).getFactorId());
		crieria.setCustomerName(StringUtils.trimToNull(crieria.getCustomerName()));
		if(crieria.getCustomerName() != null)
			crieria.setCustomerName("%" + crieria.getCustomerName() + "%");
		PaginationUtils.StandardizingPagination(crieria);
		return assetCreditServiceImpl.getCustomers(crieria);
	}
	
	@Override
	public Long[] getCustomerLimit(String customerId) {
		if(StringUtils.isBlank(customerId))
			throw WebException.instance("参数有误!");
		long totalLimit = 0, availableLimit = 0;
		CreditExample example = new CreditExample();
		example.createCriteria().andCustomerIdEqualTo(customerId);
		List<Credit> creditList = creditMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(creditList)){
			for(Credit credit: creditList){
				totalLimit += credit.getTotalLimit();
				if(CreditStatus.NORMAL.name().equals(credit.getStatus()) && ((CreditMode.SINGLE.name().equals(credit.getCreditMode()) && 
						!loanInfoTransactionService.checkContractNoHasLoan(credit.getBusinessContractNo())) || CreditMode.SYNTHETICAL.name().equals(credit.getCreditMode())))
				availableLimit += CreditUtil.getAvailableLimit(credit);
			}
		}
		return new Long[]{totalLimit, availableLimit};
	}

	@Override
	public Long getCustomerAvailableLimit(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListResult<CreditDTO> getCreditsByCustomerId(CreditQueryCriteria queryCriteria) {
		if(StringUtils.isBlank(queryCriteria.getCustomerId()))
			throw WebException.instance("参数有误!");
		UserVo userVo = memberQueryApi.findUserById(queryCriteria.getUserId());
		UserPrivilegeDTO privilege = dataPermissionValidator.checkDataPermissions(userVo);
		CreditExample example = new CreditExample();
		CreditExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(privilege.getFactorId())){
			criteria.andFactorIdEqualTo(privilege.getFactorId());
		}else if(CollectionUtils.isEmpty(privilege.getCustomerIdsList()) || !privilege.getCustomerIdsList().contains(queryCriteria.getCustomerId())){
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
		criteria.andCustomerIdEqualTo(queryCriteria.getCustomerId());
		int count = creditMapper.countByExample(example);
		example.setOrderByClause("create_time desc");
		// 分页参数
		example.setLimitStart((queryCriteria.getPage() - 1) * queryCriteria.getPageSize());
		example.setLimitEnd(queryCriteria.getPageSize());
		
		List<Credit> creditList = creditMapper.selectByExample(example);
		List<CreditDTO> creditDTOList = creditConvertor.convertorCreditditList2DTO(creditList);
		return ListResult.success(creditDTOList, count);
	}

	@Override
	public CreditDTO getCreditById(String creditId, String userId) {
		if(StringUtils.isBlank(creditId))
			throw WebException.instance("参数有误!");
		UserVo userVo = memberQueryApi.findUserById(userId);
		Credit credit = creditMapper.selectByPrimaryKey(creditId);
		dataPermissionValidator.checkDataPermissions(userVo, credit.getCustomerId(), credit.getFactorId());
		return creditConvertor.convertorCredit2DTO(credit);
	}

	@Override
	public boolean checkBusinessContactNoExist(String businessContractNo, String creditId){
		CreditExample example = new CreditExample();
		CreditExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessContractNoEqualTo(businessContractNo);
		if(StringUtils.isNotBlank(creditId))
			criteria.andIdNotEqualTo(creditId);
		List<Credit> credit = creditMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(credit))
			return true;
		return false;
	}
	
	@Override
	public void applyCredit(CreditRequestDTO request) {
		UserVo userVo = memberQueryApi.findUserById(request.getUserId());
		creditTransactionService.applyCredit(request, userVo);
	}

	@Override
	public CreditCheckModifyResultDTO checkCreditForModify(String creditId){
		return creditTransactionService.checkCreditForModify(creditId);
	}
	
	@Override
	public void modifyCredit(CreditRequestDTO request) {
		UserVo userVo = memberQueryApi.findUserById(request.getUserId());
		creditTransactionService.modifyCredit(request, userVo);
	}

	@Override
	public void freezeCredit(String creditId, String userId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		creditTransactionService.freezeCredit(creditId, userVo);
	}

	@Override
	public void unfreezeCredit(String creditId, String userId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		creditTransactionService.unfreezeCredit(creditId, userVo);
	}

	@Override
	public void revokeCredit(String creditId, String userId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		creditTransactionService.revokeCredit(creditId, userVo);
	}

	@Override
	public void cancelCredit(String creditId, String userId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		creditTransactionService.cancelCredit(creditId, userVo);
	}

	@Override
	public ListResult<CreditAuditDTO> searchCreditAuditByCondition(CreditAuditQueryCriteria criteria) {
		PaginationUtils.StandardizingPagination(criteria);
		CreditAuditExample example = new CreditAuditExample();
		CreditAuditExample.Criteria auditCriteria = example.createCriteria();
		if(StringUtils.isNotBlank(criteria.getCreditMode()))
			auditCriteria.andCreditModeEqualTo(criteria.getCreditMode());
		if(StringUtils.isNotBlank(criteria.getCreditOperateType()))
			auditCriteria.andTypeEqualTo(criteria.getCreditOperateType());
		if(StringUtils.isNotBlank(criteria.getCustomerId()))
			auditCriteria.andCustomerIdEqualTo(criteria.getCustomerId());
		if(StringUtils.isNotBlank(criteria.getCreditId()))
			auditCriteria.andCreditIdEqualTo(criteria.getCreditId());
		int count = creditAuditMapper.countByExample(example);
		example.setOrderByClause("create_time desc");
		// 分页参数
		example.setLimitStart((criteria.getPage() - 1) * criteria.getPageSize());
		example.setLimitEnd(criteria.getPageSize());
		List<CreditAudit> creditAuditList = creditAuditMapper.selectByExample(example);
		List<CreditAuditDTO> creditAuditDTOList = creditConvertor.convertorCreditAuditList2DTO(creditAuditList);
		return ListResult.success(creditAuditDTOList, count);
	}

	@Override
	public CreditDTO getCreditByBusinessContractNo(String businessContractNo, String customerId, String userId) {
		if(StringUtils.isAnyBlank(customerId, businessContractNo))
			throw WebException.instance("参数不能为空");
		CreditDTO creditDTO = assetCreditServiceImpl.getCreditByBusinessContractNo(businessContractNo, customerId);
		if(creditDTO != null)
			dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(userId), customerId, creditDTO.getFactorId());
		return creditDTO;
	}
}
