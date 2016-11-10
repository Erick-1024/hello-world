package com.cana.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.asset.dao.mapper.gen.BusinessBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.BusinessCounterpartyMapper;
import com.cana.asset.dao.po.BusinessBasicInfo;
import com.cana.asset.dao.po.BusinessBasicInfoExample;
import com.cana.asset.dao.po.BusinessBasicInfoExample.Criteria;
import com.cana.asset.dao.po.BusinessCounterpartyExample;
import com.cana.asset.service.IAssetCreditService;
import com.cana.asset.service.IAssetFactorBusinessService;
import com.cana.asset.service.convertors.FactorBusinessConvertor;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.CounterpartySearchDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessSearchDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetFactorBusinessServiceImpl implements IAssetFactorBusinessService {

	@Resource
	private BusinessBasicInfoMapper businessBasicInfoMapper;
	
	@Resource
	private FactorBusinessConvertor businessBasicInfoConvertor;
	
	@Resource
	private BusinessCounterpartyMapper businessCounterpartyMapper;
	
	@Resource
	private IAssetCreditService assetCreditService;
	
	@Resource
	private IAssetFactorBusinessTransactionService assetFactorBusinessTransactionService;
	
	@Override
	public FactorBusinessDTO queryFactorBusinessInfo(String factorBusinessId) {
		BusinessBasicInfo businessBasicInfo = businessBasicInfoMapper.selectByPrimaryKey(factorBusinessId);
		if(null == businessBasicInfo)
			throw WebException.instance("保理业务信息不存在");
		return businessBasicInfoConvertor.convertBusinessBasicInfo2FactorBusinessDTO(businessBasicInfo, true);
	}

	@Override
	public ListResult<FactorBusinessDTO> getFactorBusinessList(FactorBusinessSearchDTO factorBusinessSearchDTO) {
		BusinessBasicInfoExample businessBasicInfoExample = new BusinessBasicInfoExample();
		Criteria criteria = businessBasicInfoExample.createCriteria();
		if(StringUtils.isNotBlank(factorBusinessSearchDTO.getCustomerName()))
			criteria.andCustomerNameLike("%" + factorBusinessSearchDTO.getCustomerName() + "%");
		if(factorBusinessSearchDTO.getBusinessProduct() != null)
			criteria.andBusinessProductEqualTo(factorBusinessSearchDTO.getBusinessProduct().name());
		if(StringUtils.isNotBlank(factorBusinessSearchDTO.getBusinessContractNo()))
			criteria.andBusinessContractNoLike("%" + factorBusinessSearchDTO.getBusinessContractNo() + "%");
		if(factorBusinessSearchDTO.getLoanState() != null)
			criteria.andLoanStateEqualTo(factorBusinessSearchDTO.getLoanState().name());
		if(StringUtils.isNotBlank(factorBusinessSearchDTO.getFactorId()))
			criteria.andFactorIdEqualTo(factorBusinessSearchDTO.getFactorId());
		else
			criteria.andCustomerIdIn(factorBusinessSearchDTO.getCustomerIds());
		businessBasicInfoExample.setOrderByClause("update_time desc");
		businessBasicInfoExample.setLimitStart((factorBusinessSearchDTO.getPage() -1) * factorBusinessSearchDTO.getPageSize());
		businessBasicInfoExample.setLimitEnd(factorBusinessSearchDTO.getPageSize());
		return ListResult.success(businessBasicInfoConvertor.convertBusinessBasicInfo2FactorBusinessDTO(businessBasicInfoMapper.selectByExample(businessBasicInfoExample), false), businessBasicInfoMapper.countByExample(businessBasicInfoExample));
	}

	@Override
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(CounterpartySearchDTO searchDTO) {
		PageResult<BusinessCounterpartyDTO> result = new PageResult<>();
		if(StringUtils.isBlank(searchDTO.getBusinessContractNo())){
			throw WebException.instance("业务合同号不能为空");
		}
		BusinessBasicInfoExample businessBasicInfoExample = new BusinessBasicInfoExample();
		businessBasicInfoExample.createCriteria().andBusinessContractNoEqualTo(searchDTO.getBusinessContractNo());
		List<BusinessBasicInfo> businessBasicInfoList = businessBasicInfoMapper.selectByExample(businessBasicInfoExample);
		if(CollectionUtils.isEmpty(businessBasicInfoList)){
			return null;
		}
		BusinessCounterpartyExample businessCounterpartyExample = new BusinessCounterpartyExample();
		if(StringUtils.isNotBlank(searchDTO.getCustomerName())){
			businessCounterpartyExample.createCriteria().andBusinessInfoIdEqualTo(businessBasicInfoList.get(0).getId()).
				andCounterpartyLike("%" + searchDTO.getCustomerName() + "%");
		}else{
			businessCounterpartyExample.createCriteria().andBusinessInfoIdEqualTo(businessBasicInfoList.get(0).getId());
		}
		int totalNum = businessCounterpartyMapper.countByExample(businessCounterpartyExample);
		businessCounterpartyExample.setLimitStart((searchDTO.getPage() - 1) * searchDTO.getPageSize());
		businessCounterpartyExample.setLimitEnd(searchDTO.getPageSize());
		businessCounterpartyExample.setOrderByClause("update_time desc");
		result.setData(businessBasicInfoConvertor.convertBusinessCounterparty2BusinessCounterpartyDTO(
				businessCounterpartyMapper.selectByExample(businessCounterpartyExample)));
		result.setTotal(totalNum);
		return result;
	}

	@Override
	public int countBusinessContract(String businessContractNo, String id) {
		BusinessBasicInfoExample businessBasicInfoExample = new BusinessBasicInfoExample();
		Criteria createCriteria = businessBasicInfoExample.createCriteria();
		createCriteria.andBusinessContractNoEqualTo(businessContractNo);
		if(StringUtils.isNotBlank(id)){
			createCriteria.andIdNotEqualTo(id);
		}
		return businessBasicInfoMapper.countByExample(businessBasicInfoExample);
	}

	@Override
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO) {
		dataCheck(factorBusinessDTO);
		return assetFactorBusinessTransactionService.saveFactorBusinessInfo(factorBusinessDTO);
	}


	private void dataCheck(FactorBusinessDTO factorBusinessDTO){
		if(countBusinessContract(factorBusinessDTO.getBusinessContractNo(), factorBusinessDTO.getId()) > 0){
			throw WebException.instance("该业务合同号已被使用");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getCustomerId())){
			throw WebException.instance("客户Id不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getBusinessContractNo())){
			throw WebException.instance("业务合同号不能为空");
		}
		if(null == factorBusinessDTO.getCurrency()){
			throw WebException.instance("币种不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getProjectName())){
			throw WebException.instance("项目名称不能为空");
		}
		if(null == factorBusinessDTO.getBusinessProduct()){
			throw WebException.instance("业务产品不能为空");
		}
		if(null == factorBusinessDTO.getBusinessMode()){
			throw WebException.instance("业务模式不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getContractStartDate())){
			throw WebException.instance("合同起始日不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getContractEndDate())){
			throw WebException.instance("合同到期日不能为空");
		}
		if(CollectionUtils.isEmpty(factorBusinessDTO.getCounterpartyList())){
			throw WebException.instance("交易对手不能为空");
		}
//		BigDecimal radioPercent = new BigDecimal(0);
//		for(BusinessCounterpartyDTO businessCounterpartyDTO:factorBusinessDTO.getCounterpartyList()){
//			radioPercent.add(MoneyArithUtil.convertStringToInterestRate(businessCounterpartyDTO.getFinancingRatio()));
//		}
//		if(radioPercent.compareTo(new BigDecimal(1)) > 0){
//			throw WebException.instance("融资比例之和不能大于100%");
//		}
		if(null == factorBusinessDTO.getReceiptType()){
			throw WebException.instance("单证类型不能为空");
		}
		if(CollectionUtils.isEmpty(factorBusinessDTO.getCounterpartyList())){
			throw WebException.instance("交易对手列表不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getFactoringAccountName())){
			throw WebException.instance("保理专户户名不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getFactoringAccountBankAddress())){
			throw WebException.instance("保理专户开户行不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getFactoringAccount())){
			throw WebException.instance("保理专户账号不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getSettlementAccountName())){
			throw WebException.instance("结算账户户名不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getSettlementAccountBankAddress())){
			throw WebException.instance("结算账户开户行不能为空");
		}
		if(StringUtils.isBlank(factorBusinessDTO.getSettlementAccount())){
			throw WebException.instance("结算账户账号不能为空");
		}
		if(!StringUtils.equals(assetCreditService.getLastCreditAuditByCreditId(factorBusinessDTO.getCreditId()), factorBusinessDTO.getCreditVersion())){
			throw WebException.instance("额度信息发生变化，保存保理信息失败");
		}
	}
}
