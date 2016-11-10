package com.cana.asset.service.transaction.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.asset.dao.mapper.gen.BusinessBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.BusinessCounterpartyMapper;
import com.cana.asset.dao.mapper.gen.BusinessGuaranteeInfoMapper;
import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.po.BusinessBasicInfo;
import com.cana.asset.dao.po.BusinessBasicInfoExample;
import com.cana.asset.dao.po.BusinessCounterparty;
import com.cana.asset.dao.po.BusinessCounterpartyExample;
import com.cana.asset.dao.po.BusinessGuaranteeInfo;
import com.cana.asset.dao.po.BusinessGuaranteeInfoExample;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.service.convertors.FactorBusinessConvertor;
import com.cana.asset.service.transaction.IAssetExpenseTransactionService;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class AssetFactorBusinessTransactionServiceImpl implements IAssetFactorBusinessTransactionService {

	@Resource
	private BusinessBasicInfoMapper businessBasicInfoMapper;
	
	@Resource 
	private BusinessCounterpartyMapper businessCounterpartyMapper;
	
	@Resource
	private BusinessGuaranteeInfoMapper businessGuaranteeInfoMapper;
	
	@Resource
	private FactorBusinessConvertor businessBasicInfoConvertor;
	
	@Resource
	private ExpenseMapper expenseMapper;
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IAssetExpenseTransactionService assetExpenseTransactionService;
	
	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	@Override
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO){
		// 客户信息校验
		Customer customer = customerMapper.selectByPrimaryKey(factorBusinessDTO.getCustomerId());
		if(null == customer){
			throw WebException.instance("客户信息不存在，保存失败");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getCustomerName())){
			factorBusinessDTO.setCustomerName(customer.getCustomerName());
		}
		// 基础信息保存
		String businessBasicInfoId = factorBusinessDTO.getId();
		BusinessBasicInfo businessBasicInfo = businessBasicInfoConvertor.convertBusinessBasicInfoDTO2FactorBusiness(factorBusinessDTO);
		if(StringUtils.isNotBlank(businessBasicInfoId)){
			BusinessBasicInfo basicInfo = businessBasicInfoMapper.selectByPrimaryKey(businessBasicInfoId);
			if(null == basicInfo){
				businessBasicInfoId = generateRecordBusinessBasicInfoId();
				businessBasicInfo.setId(businessBasicInfoId);
				businessBasicInfoMapper.insertSelective(businessBasicInfo);
			}else{
				businessBasicInfoMapper.updateByPrimaryKeySelective(businessBasicInfo);
			}
		}else{
			businessBasicInfoId = generateRecordBusinessBasicInfoId();
			businessBasicInfo.setId(businessBasicInfoId);
			businessBasicInfoMapper.insertSelective(businessBasicInfo);
		}
		
		// 删除已有的约定、担保和费用信息
		deleteFactorBusinessRelevant(businessBasicInfoId);
		
		// 保存约定信息
		for(BusinessCounterparty businessCounterparty:businessBasicInfoConvertor.
				convertBusinessCounterpartyDTO2BusinessCounterparty(factorBusinessDTO.getCounterpartyList())){
			businessCounterparty.setId(generateRecordBusinessCounterpartyId());
			businessCounterparty.setBusinessInfoId(businessBasicInfoId);
			businessCounterpartyMapper.insertSelective(businessCounterparty);
		}
		
		// 保存担保信息
		for(BusinessGuaranteeInfo businessGuaranteeInfo:businessBasicInfoConvertor.
				convertBusinessGuaranteeInfoDTO2BusinessGuaranteeInfo(factorBusinessDTO.getGuaranteeInfoList())){
			businessGuaranteeInfo.setId(generateRecordBusinessGuaranteeInfoId());
			businessGuaranteeInfo.setBusinessInfoId(businessBasicInfoId);
			businessGuaranteeInfoMapper.insertSelective(businessGuaranteeInfo);
		}
		
		// 保存新费用
		for(Expense expense:businessBasicInfoConvertor.convertAssetExpenseDTO2AssetExpense(factorBusinessDTO.getExpenseList())){
			expense.setRefid(businessBasicInfoId);
			assetExpenseTransactionService.savaExpense(expense);
		}
		return businessBasicInfoId;
	}

	@Override
	public void deleteFactorBusiness(String id, UserVo userVo) {
		BusinessBasicInfo businessBasicInfo = businessBasicInfoMapper.lockByPrimaryKey(id);
		if(businessBasicInfo == null)
			throw WebException.instance(ReturnCode.NOT_FOUND);
		dataPermissionValidator.checkDataPermissions(userVo, businessBasicInfo.getCustomerId(), businessBasicInfo.getFactorId());
		businessBasicInfoMapper.deleteByPrimaryKey(id);
		deleteFactorBusinessRelevant(id);
	}
	
	@Override
	public FactorBusinessDTO queryFactorBusinessInfoByBusinessContractNo(String businessContractNo, String factorId) {
		if(StringUtils.isBlank(businessContractNo)){
			throw WebException.instance("业务合同号不能为空");
		}
		BusinessBasicInfoExample businessBasicInfoExample = new BusinessBasicInfoExample();
		businessBasicInfoExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo).andFactorIdEqualTo(factorId);
		List<FactorBusinessDTO> factorBusinessDTOList = businessBasicInfoConvertor.convertBusinessBasicInfo2FactorBusinessDTO
				(businessBasicInfoMapper.selectByExample(businessBasicInfoExample), true);
		if(CollectionUtils.isEmpty(factorBusinessDTOList)){
			return null;
		}
		if(factorBusinessDTOList.size() > 1){
			return null;
		}
		return factorBusinessDTOList.get(0);
	}
	
	private void deleteFactorBusinessRelevant(String businessBasicInfoId) {
		BusinessCounterpartyExample businessCounterpartyExample = new BusinessCounterpartyExample();
		businessCounterpartyExample.createCriteria().andBusinessInfoIdEqualTo(businessBasicInfoId);
		businessCounterpartyMapper.deleteByExample(businessCounterpartyExample);
		BusinessGuaranteeInfoExample businessGuaranteeInfoExample = new BusinessGuaranteeInfoExample();
		businessGuaranteeInfoExample.createCriteria().andBusinessInfoIdEqualTo(businessBasicInfoId);
		businessGuaranteeInfoMapper.deleteByExample(businessGuaranteeInfoExample);
		ExpenseExample example = new ExpenseExample();
		example.createCriteria().andRefidEqualTo(businessBasicInfoId).andReftypeEqualTo(ExpenseType.FACTOR_BUSINESS.name());
		expenseMapper.deleteByExample(example);
	}

	@Override
	public void updateLoanState(Set<String> contractNoSet, LoanState loanState) {
		for(String contractNo : contractNoSet){
			BusinessBasicInfoExample example = new BusinessBasicInfoExample();
			example.createCriteria().andBusinessContractNoEqualTo(contractNo);
			List<BusinessBasicInfo> businessBasicInfoList = businessBasicInfoMapper.lockByExample(example);
			for(BusinessBasicInfo businessBasicInfo:businessBasicInfoList){
				businessBasicInfo.setLoanState(loanState.name());
				businessBasicInfoMapper.updateByPrimaryKeySelective(businessBasicInfo);
			}
		}
	}
	
	private String generateRecordBusinessBasicInfoId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_BUSINESS_BASICE_INFO_ID, 4);
	}
	
	private String generateRecordBusinessCounterpartyId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_BUSINESS_COUNTERPARTY_ID, 4);
	}
	
	private String generateRecordBusinessGuaranteeInfoId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_BUSINESS_GUARANTEE_INFO_ID, 4);
	}

}
