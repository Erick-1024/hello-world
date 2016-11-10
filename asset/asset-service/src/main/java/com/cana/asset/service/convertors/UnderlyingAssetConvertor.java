package com.cana.asset.service.convertors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.dao.mapper.gen.AssetInvoiceBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.mapper.gen.BusinessBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.CreditMapper;
import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.mapper.gen.LoanPlanMapper;
import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.po.AssetInvoiceBasicInfo;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import com.cana.asset.dao.po.BusinessBasicInfo;
import com.cana.asset.dao.po.BusinessBasicInfoExample;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.CreditExample;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.CustomerExample;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import com.cana.asset.dao.po.LoanPlan;
import com.cana.asset.dao.po.LoanPlanExample;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramExample;
import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetLog;
import com.cana.vbam.common.asset.enums.CustomerCityTypeEnum;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogDTO;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class UnderlyingAssetConvertor {

	@Resource
	private SpecialProgramMapper specialProgramMapper;
	
	@Resource
	private LoanInfoMapper loanInfoMapper;
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Resource
	private AssetInvoiceBasicInfoMapper assetInvoiceBasicInfoMapper;
	
	@Resource
	private LoanPlanMapper loanPlanMapper;
	
	@Resource
	private CreditMapper creditMapper;
	
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;
	
	@Resource
	private VbamCommonService commonService;
	
	@Resource
	private BusinessBasicInfoMapper businessBasicInfoMapper;
	
	private List<String> loanNoList;
	
	private List<String> businessContractNoList;
	
	private Map<String, LoanInfo> loanInfoMap;
	
	private Map<String, SpecialProgram> specialProgramMap;

	private List<BusinessBasicInfo>	businessBasicInfoList;

	private Map<String, List<LoanPlan>>	loanPlanMap;
	
	public List<UnderlyingAsset> queryUnderlyingAssetData(List<UnderlyingAsset> underlyingAssetList){
		// 筛选出手工录入的基础资产
//		List<UnderlyingAsset> manualTypeUnderlyingAssetList = getUnderlyingAssetOfManualType(underlyingAssetList);
		
		// 筛选出保理业务的基础资产
		List<UnderlyingAsset> factorTypeUnderlyingAssetList = getUnderlyingAssetOfFactorType(underlyingAssetList);
		
		// 获取所有放款编号
		loanNoList = getLoanNoList(factorTypeUnderlyingAssetList);
		
		// 获取所有业务合同号
		businessContractNoList = getBusinessContractNoList(underlyingAssetList);
		
		// 获取放款信息
		loanInfoMap = queryLoanInfoList(loanNoList);
		
		specialProgramMap = querySpecialProgram(getSpecialProgramIdList(underlyingAssetList));
		
		// 获取所有客户信息
		List<String> customerIdList = getAllCustomerId(underlyingAssetList, loanInfoMap);
		List<Customer> customerInfoList = queryBasicInfo(customerIdList);
		
		// 获取额度信息
		List<Credit> creditList = getCreditList(businessContractNoList);
		
		// 获取应收账款信息
		List<AssetInvoiceBasicInfo> assetInvoiceBasicList = queryAssetInvoiceBasicList(businessContractNoList);
		
		// 保理业务-基础资产数据组装
		assembleUnderlyingAsset(factorTypeUnderlyingAssetList, loanInfoMap, customerInfoList, creditList, assetInvoiceBasicList);
		
		// 获取保理业务信息
		businessBasicInfoList = queryFactorBusinessInfo(businessContractNoList);
		
		// 获取还款计划
		loanPlanMap = loanPlanSort(queryLoanPlanList(loanNoList));
		
		return underlyingAssetList;
	}
	
	public List<UnderlyingAssetDTO> convertToUnderlyingAssetDTO(List<UnderlyingAsset> underlyingAssetList, boolean loadExtraData){
		return convertToDTO(underlyingAssetList, loadExtraData);
	}
	
	/**
	 * 获取基础资产-专项计划信息
	 * @param loanInfoNoList
	 * @return
	 */
	private Map<String, SpecialProgram> querySpecialProgram(List<String> specialProgramIdList){
		SpecialProgramExample example = new SpecialProgramExample();
		Map<String, SpecialProgram> specialProgramMap = Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(specialProgramIdList)){
			example.createCriteria().andIdIn(specialProgramIdList);
			List<SpecialProgram> specialProgramList = specialProgramMapper.selectByExample(example);
			for(SpecialProgram specialProgram:specialProgramList){
				specialProgramMap.put(specialProgram.getId(), specialProgram);
			}
		}
		return specialProgramMap;
	}
	
	/**
	 * 获取基础资产-放款信息
	 * @param loanInfoNoList
	 * @return
	 */
	private Map<String, LoanInfo> queryLoanInfoList(List<String> loanInfoNoList){
		Map<String, LoanInfo> loanInfoMap = Maps.newHashMap();
		LoanInfoExample example = new LoanInfoExample();
		if(CollectionUtils.isNotEmpty(loanInfoNoList)){
			example.createCriteria().andIdIn(loanInfoNoList);
			List<LoanInfo> loanInfoList = loanInfoMapper.selectByExample(example);
			for(LoanInfo loanInfo:loanInfoList){
				loanInfoMap.put(loanInfo.getId(), loanInfo);
			}
		}
		return loanInfoMap;
	}
	
	/**
	 * 获取基础资产-还款计划信息
	 * @param loanInfoNoList
	 * @return
	 */
	private List<LoanPlan> queryLoanPlanList(List<String> loanInfoNoList){
		List<LoanPlan> loanPlanList = Lists.newArrayList();
		LoanPlanExample example = new LoanPlanExample();
		if(CollectionUtils.isNotEmpty(loanInfoNoList)){
			example.createCriteria().andLoanInfoIdIn(loanInfoNoList);
			loanPlanList = loanPlanMapper.selectByExample(example);
		}
		return loanPlanList;
	}
	
	/**
	 * 获取基础资产-基础信息
	 * @param customerIdList
	 * @return
	 */
	private List<Customer> queryBasicInfo(List<String> customerIdList){
		CustomerExample example = new CustomerExample();
		List<Customer> customerList  = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(customerIdList)){
			example.createCriteria().andIdIn(customerIdList);
			customerList = customerMapper.selectByExample(example);
		}
		return customerList;
	}
	
	
	/**
	 * 获取基础资产-应收账款基础信息
	 * @return
	 */
	private List<AssetInvoiceBasicInfo> queryAssetInvoiceBasicList(List<String> businessContractNoList){
		AssetInvoiceBasicInfoExample example = new AssetInvoiceBasicInfoExample();
		List<AssetInvoiceBasicInfo> AssetInvoiceBasicInfoList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(businessContractNoList)){
			example.createCriteria().andBusinessContractNoIn(businessContractNoList);
			AssetInvoiceBasicInfoList = assetInvoiceBasicInfoMapper.selectByExample(example);
		}
		return AssetInvoiceBasicInfoList;
	}

	/**
	 * 获取基础资产-应收账款基础信息
	 * @return
	 */
	private List<AssetInvoiceInfo> queryAssetInvoiceList(AssetInvoiceBasicInfo assetInvoiceBasicInfo){
		AssetInvoiceInfoExample invoiceExample = new AssetInvoiceInfoExample();
		invoiceExample.createCriteria().andInvoiceBaseIdEqualTo(assetInvoiceBasicInfo.getId());
		List<AssetInvoiceInfo> assetInvoiceInfoList = assetInvoiceInfoMapper.selectByExample(invoiceExample);
		return assetInvoiceInfoList;
	}
	
	
	/**
	 * 获取基础资产-额度信息
	 * @param getBusinessContractNoList
	 * @return
	 */
	private List<Credit> getCreditList(List<String> businessContractNoList){
		CreditExample example = new CreditExample();
		List<Credit> creditList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(businessContractNoList)){
			example.createCriteria().andBusinessContractNoIn(businessContractNoList);
			creditList = creditMapper.selectByExample(example);
		}
		return creditList;
	}
	
	/**
	 * 获取业务产品
	 * @param businessContractNoList
	 * @return
	 */
	private List<BusinessBasicInfo> queryFactorBusinessInfo(List<String> businessContractNoList){
		List<BusinessBasicInfo> businessBasicInfoList = Lists.newArrayList();
		BusinessBasicInfoExample example = new BusinessBasicInfoExample();
		if(CollectionUtils.isNotEmpty(businessContractNoList)){
			example.createCriteria().andBusinessContractNoIn(businessContractNoList);
			businessBasicInfoList = businessBasicInfoMapper.selectByExample(example);			
		}
		return businessBasicInfoList;
	}
	
	/**
	 * 筛选出手工录入的基础资产
	 * @param underlyingAssetList
	 * @return
	 */
//	private List<UnderlyingAsset> getUnderlyingAssetOfManualType(List<UnderlyingAsset> underlyingAssetList){
//		List<UnderlyingAsset> manualTypeUnderlyingAssetList = Lists.newArrayList();
//		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
//			if(StringUtils.equals(underlyingAsset.getAssetType(), UnderlyingAssetType.MANUAL.name())){
//				manualTypeUnderlyingAssetList.add(underlyingAsset);
//			}
//		}
//		return manualTypeUnderlyingAssetList;
//	}
	
	/**
	 * 筛选出保理业务的基础资产
	 * @param underlyingAssetList
	 * @return
	 */
	private List<UnderlyingAsset> getUnderlyingAssetOfFactorType(List<UnderlyingAsset> underlyingAssetList){
		List<UnderlyingAsset> factorTypeUnderlyingAssetList = Lists.newArrayList();
		Set<UnderlyingAsset> factorTypeUnderlyingAssetSet = Sets.newHashSet();
		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
			if(StringUtils.equals(underlyingAsset.getAssetSource(), UnderlyingAssetSource.FACTOR.name())){
				factorTypeUnderlyingAssetSet.add(underlyingAsset);
			}
		}
		factorTypeUnderlyingAssetList.addAll(factorTypeUnderlyingAssetSet);
		return factorTypeUnderlyingAssetList;
	}
	
	/**
	 * 获取所有专项计划Id
	 * @param underlyingAssetList
	 * @return
	 */
	private List<String> getSpecialProgramIdList(List<UnderlyingAsset> underlyingAssetList){
		List<String> specialProgramIdList = Lists.newArrayList();
		Set<String> specialProgramIdSet = Sets.newHashSet();
		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
			if(StringUtils.isNotBlank(underlyingAsset.getSpecialProgramId())){
				specialProgramIdSet.add(underlyingAsset.getSpecialProgramId());
			}
		}
		specialProgramIdList.addAll(specialProgramIdSet);
		return specialProgramIdList;
	}
	
	/**
	 * 获取所有的放款编号
	 * @param underlyingAssetList
	 * @return
	 */
	private List<String> getLoanNoList(List<UnderlyingAsset> underlyingAssetList){
		List<String> loanNoList = Lists.newArrayList();
		Set<String> loanNoSet = Sets.newHashSet();
		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
			loanNoSet.add(underlyingAsset.getId());
		}
		loanNoList.addAll(loanNoSet);
		return loanNoList;
	}
	
	/**
	 * 获取所有的业务合同号
	 * @param underlyingAssetList
	 * @return
	 */
	private List<String> getBusinessContractNoList(List<UnderlyingAsset> underlyingAssetList){
		List<String> businessContractNoList = Lists.newArrayList();
		Set<String> businessContractNoSet = Sets.newHashSet();
		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
			businessContractNoSet.add(underlyingAsset.getBusinessContractNo());
		}
		businessContractNoList.addAll(businessContractNoSet);
		return businessContractNoList;
	}
	
	/**
	 * 获取所有的应收账款Id
	 * @param assetInvoiceBasicInfoList
	 * @return
	 */
//	private List<String> getAllInvoiceBasicInfoId(List<AssetInvoiceBasicInfo> assetInvoiceBasicInfoList){
//		List<String> invoiceBasicInfoId = Lists.newArrayList();
//		for(AssetInvoiceBasicInfo assetInvoiceBasicInfo : assetInvoiceBasicInfoList){
//			invoiceBasicInfoId.add(assetInvoiceBasicInfo.getId());
//		}
//		return invoiceBasicInfoId;
//	}
	
	private List<String> getAllCustomerId(List<UnderlyingAsset> underlyingAssetList,Map<String, LoanInfo> loanInfoMap){
		List<String> customerIdList = Lists.newArrayList();
		Set<String> customerIdSet = Sets.newHashSet();
		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
			if(StringUtils.isNotBlank(underlyingAsset.getCustomerId())){
				customerIdSet.add(underlyingAsset.getCustomerId());
			}
			if(StringUtils.isNotBlank(underlyingAsset.getCounterpartyId())){
				customerIdSet.add(underlyingAsset.getCounterpartyId());
			}
		}
		for(LoanInfo loanInfo:loanInfoMap.values()){
			customerIdSet.add(loanInfo.getCounterpartyId());
		}
		customerIdList.addAll(customerIdSet);
		return customerIdList;
	}
	
	private void assembleUnderlyingAsset(List<UnderlyingAsset> factorTypeUnderlyingAssetList, Map<String, LoanInfo> loanInfoMap,
			List<Customer> customerInfoList, List<Credit> creditList, List<AssetInvoiceBasicInfo> assetInvoiceBasicInfoList){
		for(UnderlyingAsset underlyingAsset:factorTypeUnderlyingAssetList){
			LoanInfo loanInfo = loanInfoMap.get(underlyingAsset.getId());
			// 基础资产-融资信息
			if(null != loanInfo){
				underlyingAsset.setFinanceAmount(loanInfo.getFinanceAmount());
				underlyingAsset.setFinanceBalance(loanInfo.getFinanceBalance());
				underlyingAsset.setLoanDate(loanInfo.getLoanDate());
				underlyingAsset.setDueDate(loanInfo.getDueDate());
				underlyingAsset.setRepaymentMethod(loanInfo.getRepaymentMethod());
				underlyingAsset.setInterestRateUnit(loanInfo.getInterestRateUnit());
				underlyingAsset.setInterestRate(loanInfo.getInterestRate());
				underlyingAsset.setLoanPeriod(loanInfo.getLoanPeriod().toString());
				underlyingAsset.setSettleStatus(loanInfo.getSettleStatus());
				underlyingAsset.setForwardDays(loanInfo.getForwardDays());
				underlyingAsset.setOverdueDays(loanInfo.getOverdueDays());
			}
			for(Customer customer:customerInfoList){
				if(StringUtils.equals(underlyingAsset.getAssetSource(), UnderlyingAssetSource.FACTOR.name())){
					// 基础资产-借款人信息
					if(StringUtils.equals(customer.getId(), underlyingAsset.getCustomerId())){
						underlyingAsset.setCustomerName(customer.getCustomerName());
						underlyingAsset.setCustomerEconomicCategory(EconomicCategoryEnum.valueOf(customer.getEconomicCategory()).desc());
						underlyingAsset.setCustomerCity(CustomerCityTypeEnum.valueOf(customer.getCity()).desc());
						underlyingAsset.setCustomerIndustry(IndustryTypeEnum.valueOf(customer.getIndustry()).desc());
					}
					// 基础资产-交易对手信息
					if(StringUtils.equals(customer.getId(), loanInfo.getCounterpartyId())){
						underlyingAsset.setRepaymentMethod(RepaymentType.valueOf(underlyingAsset.getRepaymentMethod()).desc());
						underlyingAsset.setCounterpartyEconomicCategory((EconomicCategoryEnum.valueOf(customer.getEconomicCategory())).desc());
						underlyingAsset.setCounterpartyCity(CustomerCityTypeEnum.valueOf(customer.getCity()).desc());
						underlyingAsset.setCounterpartyIndustry(IndustryTypeEnum.valueOf(customer.getIndustry()).desc());
						underlyingAsset.setCounterparty(customer.getCustomerName());
					}
				}
			}
			
			for(Credit credit:creditList){
				// 基础资产-额度信息
				if(StringUtils.equals(credit.getBusinessContractNo(), underlyingAsset.getBusinessContractNo())){
					underlyingAsset.setCreditLimit(credit.getTotalLimit());
					underlyingAsset.setCreditBalance(credit.getTotalLimit() - credit.getUsedLimit());
					break;
				}
			}
			for(AssetInvoiceBasicInfo assetInvoiceBasicInfo:assetInvoiceBasicInfoList){
				// 基础资产-应收账款 
				if(StringUtils.equals(assetInvoiceBasicInfo.getBusinessContractNo(), underlyingAsset.getBusinessContractNo())){
					long invoiceAmount = 0;
					long invoiceBalance = 0;
					List<AssetInvoiceInfo> assetInvoiceList = queryAssetInvoiceList(assetInvoiceBasicInfo);
					for(AssetInvoiceInfo assetInvoiceInfo:assetInvoiceList){
						invoiceAmount += assetInvoiceInfo.getInvoiceAmt();
						if(commonService.getCurrentDate().compareTo(assetInvoiceInfo.getDueDate()) <= 0){
							invoiceBalance += assetInvoiceInfo.getInvoiceAmt();
						}
					}
					underlyingAsset.setInvoiceAmount(invoiceAmount);
					underlyingAsset.setInvoiceBalance(invoiceBalance);
					break;
				}
			}
		}
	}
	
	/**
	 * POJO to DTO 转化
	 * @param underlyingAssetList
	 * @return
	 */
	private List<UnderlyingAssetDTO> convertToDTO(List<UnderlyingAsset> underlyingAssetList, boolean loadExtraData){
		
		List<UnderlyingAssetDTO> underlyingAssetDTOList = Lists.newArrayList();
		for(UnderlyingAsset underlyingAsset:underlyingAssetList){
			UnderlyingAssetDTO underlyingAssetDTO = new UnderlyingAssetDTO();
			BeanUtils.copyProperties(underlyingAsset, underlyingAssetDTO);
			if(StringUtils.isNotBlank(underlyingAsset.getSpecialProgramId())){
				SpecialProgram specialProgram = specialProgramMap.get(underlyingAsset.getSpecialProgramId());
				if(null != specialProgram){
					underlyingAssetDTO.setSpecialProgramName(specialProgram.getSpecialProgramName());
				}
			}
			if(StringUtils.isNotBlank(underlyingAsset.getInterestRateUnit()))
				underlyingAssetDTO.setInterestRateUnit(InterestRateUnit.valueOf(underlyingAsset.getInterestRateUnit()));
			underlyingAssetDTO.setLoanNo(underlyingAsset.getId());
			underlyingAssetDTO.setCreditLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCreditLimit())));
			underlyingAssetDTO.setCreditBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCreditBalance())));
			if(underlyingAsset.getCounterpartyLimit() != null){
				underlyingAssetDTO.setCounterpartyLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCounterpartyLimit())));
			}
			if(underlyingAsset.getCounterpartyBalance() != null){
				underlyingAssetDTO.setCounterpartyBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCounterpartyBalance())));
			}
			underlyingAssetDTO.setInvoiceAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getInvoiceAmount())));
			underlyingAssetDTO.setInvoiceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getInvoiceBalance())));
			underlyingAssetDTO.setFinanceAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getFinanceAmount())));
			underlyingAssetDTO.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getFinanceBalance())));
			
			underlyingAssetDTO.setInterestRateNum(underlyingAsset.getInterestRate());
			underlyingAssetDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(underlyingAsset.getInterestRate()));
			if(StringUtils.equals(underlyingAsset.getAssetSource(), UnderlyingAssetSource.FACTOR.name())){
				underlyingAssetDTO.setInterestRateUnit(InterestRateUnit.valueOf(underlyingAsset.getInterestRateUnit()));
				underlyingAssetDTO.setSettleStatus(SettleStatus.valueOf(loanInfoMap.get(underlyingAsset.getId()).getSettleStatus()).desc());
//				if(StringUtils.isNoneBlank(loanInfoMap.get(underlyingAsset.getId()).getLoanPeriodUnit())){
				underlyingAssetDTO.setLoanPeriodUnit(DateUnit.valueOf(loanInfoMap.get(underlyingAsset.getId()).getLoanPeriodUnit()).desc());
//				}
			}else{
				underlyingAssetDTO.setSettleStatus(SettleStatus.valueOf(underlyingAsset.getSettleStatus()).desc());
			}
			underlyingAssetDTO.setForwardDays(underlyingAsset.getForwardDays().toString());
			underlyingAssetDTO.setOverdueDays(underlyingAsset.getOverdueDays().toString());
			underlyingAssetDTO.setAssetPoolStatus(UnderlyingAssetPoolStatus.valueOf(underlyingAsset.getAssetPoolStatus()));
			underlyingAssetDTO.setAssetSource(UnderlyingAssetSource.valueOf(underlyingAsset.getAssetSource()));
			if(null != underlyingAsset.getExtensionDays()){
				underlyingAssetDTO.setExtensionDays(underlyingAsset.getExtensionDays().toString());
			}else{
				underlyingAssetDTO.setExtensionDays("0");
			}
			if(loadExtraData){

					
				underlyingAssetDTO.setCreditLimitNum(underlyingAsset.getCreditLimit());
				underlyingAssetDTO.setCreditBalanceNum(underlyingAsset.getCreditBalance());
				if(StringUtils.equals(underlyingAsset.getAssetSource(), UnderlyingAssetSource.MANUAL.name())){
					underlyingAssetDTO.setCounterpartyLimitNum(underlyingAsset.getCounterpartyLimit());
					underlyingAssetDTO.setCounterpartyBalanceNum(underlyingAsset.getCounterpartyBalance());
				}
				if(StringUtils.equals(underlyingAsset.getAssetSource(), UnderlyingAssetSource.FACTOR.name())){
					underlyingAssetDTO.setDayCountConvention(loanInfoMap.get(underlyingAsset.getId()).getDayCountConvention());
				}
				underlyingAssetDTO.setUpdateTime(underlyingAsset.getUpdateTime());
				underlyingAssetDTO.setInvoiceAmountNum(underlyingAsset.getInvoiceAmount());
				underlyingAssetDTO.setInvoiceBalanceNum(underlyingAsset.getInvoiceBalance());
				underlyingAssetDTO.setFinanceAmountNum(underlyingAsset.getFinanceAmount());
				underlyingAssetDTO.setFinanceBalanceNum(underlyingAsset.getFinanceBalance());
				underlyingAssetDTO.setInterestRateNum(underlyingAsset.getInterestRate());
				if(CollectionUtils.isNotEmpty(businessBasicInfoList)){
					underlyingAssetDTO.setBusinessProduct(getBusinessProduct(businessBasicInfoList, underlyingAsset.getBusinessContractNo()));
				}
				if(null != loanPlanMap && !loanPlanMap.isEmpty() && StringUtils.equals(underlyingAsset.getAssetSource(), UnderlyingAssetSource.FACTOR.name())){
					List<LoanPlan> loanPlanList = loanPlanMap.get(underlyingAsset.getId());
					underlyingAssetDTO.setSettleInterestDate(loanPlanList.get(loanPlanList.size()-1).getSettleInterestDate());
					LoanInfo loanInfo = loanInfoMap.get(underlyingAsset.getId());
					if(null != loanInfo){
						underlyingAssetDTO.setPenaltyRate(loanInfo.getPenaltyRate());
					}
					underlyingAssetDTO.setTotalInterestAmount(getTotalInterestAmount(underlyingAsset.getId(), loanPlanMap));
					underlyingAssetDTO.setTotalAmount(getTotalAmount(underlyingAsset.getId(), loanPlanMap));
				}
			}
			underlyingAssetDTOList.add(underlyingAssetDTO);
		}
		return underlyingAssetDTOList;
	}

	public UnderlyingAssetLog convertUnderlyingAsset2UnderlyingAssetLog(UnderlyingAsset underlyingAsset) {
		UnderlyingAssetLog underlyingAssetLog = new UnderlyingAssetLog();
		underlyingAssetLog.setLoanInfoId(underlyingAsset.getId());
		underlyingAssetLog.setBusinessContractNo(underlyingAsset.getBusinessContractNo());
		underlyingAssetLog.setCustomerName(underlyingAsset.getCustomerName());
		underlyingAssetLog.setCustomerEconomicCategory(underlyingAsset.getCustomerEconomicCategory());
		underlyingAssetLog.setCounterparty(underlyingAsset.getCounterparty());
		underlyingAssetLog.setCounterpartyEconomicCategory(underlyingAsset.getCounterpartyEconomicCategory());
		underlyingAssetLog.setCustomerCity(underlyingAsset.getCustomerCity());
		underlyingAssetLog.setCustomerIndustry(underlyingAsset.getCustomerIndustry());
		underlyingAssetLog.setCounterpartyCity(underlyingAsset.getCounterpartyCity());
		underlyingAssetLog.setCounterpartyIndustry(underlyingAsset.getCounterpartyIndustry());
		underlyingAssetLog.setCreditLimit(underlyingAsset.getCreditLimit());
		underlyingAssetLog.setCreditBalance(underlyingAsset.getCreditBalance());
		underlyingAssetLog.setCounterpartyLimit(underlyingAsset.getCounterpartyLimit());
		underlyingAssetLog.setCounterpartyBalance(underlyingAsset.getCounterpartyBalance());
		underlyingAssetLog.setInvoiceAmount(underlyingAsset.getInvoiceAmount());
		underlyingAssetLog.setInvoiceBalance(underlyingAsset.getInvoiceBalance());
		underlyingAssetLog.setFinanceAmount(underlyingAsset.getFinanceAmount());
		underlyingAssetLog.setFinanceBalance(underlyingAsset.getFinanceBalance());
		underlyingAssetLog.setLoanDate(underlyingAsset.getLoanDate());
		underlyingAssetLog.setDueDate(underlyingAsset.getDueDate());
		underlyingAssetLog.setRepaymentMethod(underlyingAsset.getRepaymentMethod());
		underlyingAssetLog.setInterestRateUnit(underlyingAsset.getInterestRateUnit());
		underlyingAssetLog.setInterestRate(underlyingAsset.getInterestRate());
		underlyingAssetLog.setLoanPeriod(underlyingAsset.getLoanPeriod());
		underlyingAssetLog.setSpecialProgramId(underlyingAsset.getSpecialProgramId());
		underlyingAssetLog.setFactorId(underlyingAsset.getFactorId());
		underlyingAssetLog.setCreateTime(new Date());
		return underlyingAssetLog;
	}
	
	public List<UnderlyingAssetLogDTO> convertUnderlyingAssetLogs2UnderlyingAssetLogDTOs(List<UnderlyingAssetLog> underlyingAssetLogs){
		if(CollectionUtils.isEmpty(underlyingAssetLogs))
			return new ArrayList<>();
		List<UnderlyingAssetLogDTO> dtos = new ArrayList<>();
		for(UnderlyingAssetLog underlyingAssetLog : underlyingAssetLogs){
			UnderlyingAssetLogDTO dto = new UnderlyingAssetLogDTO();
			dto.setId(underlyingAssetLog.getId());
			dto.setLoanInfoId(underlyingAssetLog.getLoanInfoId());
			dto.setBusinessContractNo(underlyingAssetLog.getBusinessContractNo());
			dto.setCustomerName(underlyingAssetLog.getCustomerName());
			dto.setCustomerEconomicCategory(underlyingAssetLog.getCustomerEconomicCategory());
			dto.setCounterparty(underlyingAssetLog.getCounterparty());
			dto.setCounterpartyEconomicCategory(underlyingAssetLog.getCounterpartyEconomicCategory());
			dto.setCustomerCity(underlyingAssetLog.getCustomerCity());
			dto.setCustomerIndustry(underlyingAssetLog.getCustomerIndustry());
			dto.setCounterpartyCity(underlyingAssetLog.getCounterpartyCity());
			dto.setCounterpartyIndustry(underlyingAssetLog.getCounterpartyIndustry());
			if(underlyingAssetLog.getCreditLimit()!=null)
				dto.setCreditLimit(MoneyUtil.cent2Yuan(underlyingAssetLog.getCreditLimit()));
			if(underlyingAssetLog.getCreditBalance()!=null)
				dto.setCreditBalance(MoneyUtil.cent2Yuan(underlyingAssetLog.getCreditBalance()));
			if(underlyingAssetLog.getCounterpartyLimit()!=null)
				dto.setCounterpartyLimit(MoneyUtil.cent2Yuan(underlyingAssetLog.getCounterpartyLimit()));
			if(underlyingAssetLog.getCounterpartyBalance()!=null)
				dto.setCounterpartyBalance(MoneyUtil.cent2Yuan(underlyingAssetLog.getCounterpartyBalance()));
			if(underlyingAssetLog.getInvoiceAmount()!=null)
				dto.setInvoiceAmount(MoneyUtil.cent2Yuan(underlyingAssetLog.getInvoiceAmount()));
			if(underlyingAssetLog.getInvoiceBalance()!=null)
				dto.setInvoiceBalance(MoneyUtil.cent2Yuan(underlyingAssetLog.getInvoiceBalance()));
			if(underlyingAssetLog.getFinanceAmount()!=null)
				dto.setFinanceAmount(MoneyUtil.cent2Yuan(underlyingAssetLog.getFinanceAmount()));
			if(underlyingAssetLog.getFinanceBalance()!=null)
				dto.setFinanceBalance(MoneyUtil.cent2Yuan(underlyingAssetLog.getFinanceBalance()));
			dto.setLoanDate(underlyingAssetLog.getLoanDate());
			dto.setDueDate(underlyingAssetLog.getDueDate());
			dto.setRepaymentMethod(underlyingAssetLog.getRepaymentMethod());
			dto.setInterestRateUnit(underlyingAssetLog.getInterestRateUnit());
			if(underlyingAssetLog.getInterestRate()!=null)
				dto.setInterestRate(underlyingAssetLog.getInterestRate().multiply(new BigDecimal(100)).setScale(3,BigDecimal.ROUND_HALF_UP).toString()+"%");
			dto.setLoanPeriod(underlyingAssetLog.getLoanPeriod());
			dto.setSpecialProgramId(underlyingAssetLog.getSpecialProgramId());
			dto.setSpecialProgramName(underlyingAssetLog.getSpecialProgramName());
			dto.setOperateCompanyName(underlyingAssetLog.getOperateCompanyName());
			dto.setOperateUsername(underlyingAssetLog.getOperateUsername());
			dto.setOperateType(underlyingAssetLog.getOperateType());
			if(StringUtils.isNotBlank(underlyingAssetLog.getOperateType()))
				dto.setOperateTypeDesc(UnderlyingAssetOperateTypeEnum.valueOf(underlyingAssetLog.getOperateType()).desc());
			dto.setCreateTime(underlyingAssetLog.getCreateTime());
			dtos.add(dto);
		}
		return dtos;
	}
	
	/**
	 * 获取业务产品
	 * @param businessBasicInfoList
	 * @param businessContractNo
	 * @return
	 */
	private String getBusinessProduct(List<BusinessBasicInfo> businessBasicInfoList, String businessContractNo){
		for(BusinessBasicInfo businessBasicInfo:businessBasicInfoList){
			if(StringUtils.equals(businessBasicInfo.getBusinessContractNo(), businessContractNo)){
				return businessBasicInfo.getBusinessProduct();
			}
		}
		return null;
	}
	
	/**
	 * 根据放款编号分类并排序
	 * @param loanPlanList
	 * @return
	 */
	private Map<String, List<LoanPlan>> loanPlanSort(List<LoanPlan> loanPlanList){
		Map<String, List<LoanPlan>> loanPlanMap = Maps.newHashMap();
		for(LoanPlan loanPlan:loanPlanList){
			List<LoanPlan> list = loanPlanMap.get(loanPlan.getLoanInfoId());
			if(CollectionUtils.isNotEmpty(list)){
				list.add(loanPlan);
			}else{
				list = Lists.newArrayList();
				list.add(loanPlan);
			}
			loanPlanMap.put(loanPlan.getLoanInfoId(), list);
		}
		for(List<LoanPlan> tempList : loanPlanMap.values()){
			Collections.sort(tempList,new Comparator<LoanPlan>(){
	            public int compare(LoanPlan loanPlan1, LoanPlan loanPlan2) {
	                //比较每个ArrayList的第二个元素
	                if(loanPlan1.getRepaymentPeriod() == loanPlan2.getRepaymentPeriod()){
	                    return 0;
	                }else if(loanPlan1.getRepaymentPeriod() > loanPlan2.getRepaymentPeriod()){
	                    return 1;
	                }else{
	                    return -1;
	                }
	            }
	        });
		}
		return loanPlanMap;
	}
	
	/**
	 * 获取应收利息
	 * @param loanInfoId
	 * @param loanPlanMap
	 * @return
	 */
	private long getTotalInterestAmount(String loanInfoId, Map<String, List<LoanPlan>> loanPlanMap){
		long totalInterestAmount = 0;
		List<LoanPlan> loanPlanList = loanPlanMap.get(loanInfoId);
		if(CollectionUtils.isNotEmpty(loanPlanList)){
			for(LoanPlan loanPlan:loanPlanList){
				totalInterestAmount += loanPlan.getAccountInterest();
			}
		}
		return totalInterestAmount;
	}

	/**
	 * 获取应收利息
	 * @param loanInfoId
	 * @param loanPlanMap
	 * @return
	 */
	private long getTotalAmount(String loanInfoId, Map<String, List<LoanPlan>> loanPlanMap){
		long totalAmount = 0;
		List<LoanPlan> loanPlanList = loanPlanMap.get(loanInfoId);
		if(CollectionUtils.isNotEmpty(loanPlanList)){
			for(LoanPlan loanPlan:loanPlanList){
				totalAmount += loanPlan.getAccountPrincipal();
				totalAmount += loanPlan.getAccountInterest();
				totalAmount += loanPlan.getAccountOverdue();
			}
		}
		return totalAmount;
	}
	
}
