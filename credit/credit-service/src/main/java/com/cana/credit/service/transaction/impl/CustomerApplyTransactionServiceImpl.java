/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.credit.service.transaction.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.CreditTableLockMapper;
import com.cana.credit.dao.mapper.gen.AccessRuleMapper;
import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.AccessRule;
import com.cana.credit.dao.po.AccessRuleExample;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.OutCustomer;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.service.ICreditMessageService;
import com.cana.credit.service.IRetryTaskService;
import com.cana.credit.service.transaction.ICustomerApplyTransactionService;
import com.cana.credit.service.utils.NewestAccessRuleHolder;
import com.cana.flight.finance.service.utils.IFlightFinanceServiceHelper;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.dto.apply.AccessRuleDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApply4MemberDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.AccessRuleFitObject;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;
import com.cana.vbam.common.credit.enums.CreditLimitGenerateState;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.credit.enums.NoticeScene;
import com.cana.vbam.common.credit.openapi.CreditNoticeParam;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author ducer
 *
 */
@Service
public class CustomerApplyTransactionServiceImpl implements ICustomerApplyTransactionService {

	@Resource
	private CreditTableLockMapper creditTableLockMapper;
	
	@Resource
	private CustomerApplyMapper customerApplyMapper;
	
	@Resource
	private OutCustomerMapper outCustomerMapper;
	
	@Resource
	private IRetryTaskService retryTaskServiceImpl;
	
	@Resource
	private AccessRuleMapper accessRuleMapper;
	
	@Resource
	private CreditLimitMapper creditLimitMapper;
	
	@Resource
	private CreditLimitAuditMapper creditLimitAuditMapper;
	
	@Resource
	private ICreditMessageService creditMessageServiceImpl; 
	
	@Resource
	private IFlightFinanceServiceHelper flightFinanceServiceHelper;
    
    @Resource
	private IUserApi userApi;
    
	@Override
	public void auditTravelzenCustomer(TravelzenCustomerAuditResultDTO resultDTO, String userId) {
		checkAuditResultDTO(resultDTO);
		CustomerApply apply = creditTableLockMapper.lockCustomerApplyById(resultDTO.getCustomerApplyId());
		checkApply(apply);
		
		long enterpriseExecutionMoney = StringUtils.isNotBlank(resultDTO.getEnterpriseExecutionMoney()) ? MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(resultDTO.getEnterpriseExecutionMoney().trim())) : 0;
		Integer enterpriseExecutionTimes = resultDTO.getEnterpriseExecutionTimes();
		long individualExecutionMoney = StringUtils.isNotBlank(resultDTO.getIndividualExecutionMoney()) ? MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(resultDTO.getIndividualExecutionMoney().trim())) : 0;
		Integer individualExecutionTimes = resultDTO.getIndividualExecutionTimes();
		String tzCustomerId = apply.getTzCustomerId();
		ReturnClass retClass;
		if(!AccessManualState.WAIT_APPROVE.name().equals(apply.getAccessManualState())){
			AccessRule accessRule = getAccessRule(apply.getInWhitelist());

			apply.setEnterpriseExecutionMoney(enterpriseExecutionMoney);
			if(enterpriseExecutionTimes != null)
				apply.setEnterpriseExecutionTimes(enterpriseExecutionTimes);
			apply.setIndividualExecutionMoney(individualExecutionMoney);
			if(individualExecutionTimes != null)
				apply.setIndividualExecutionTimes(individualExecutionTimes);
			apply.setAuditorId(resultDTO.getAuditorId());
			apply.setManualAuditRemarks(resultDTO.getManualAuditRemarks());
			apply.setConsistencyCheck(resultDTO.getConsistencyCheck());
			apply.setNegativeNetwork(resultDTO.getNegativeNetwork());
			apply.setManualAuditRuleBatchNo(accessRule.getBatchNo());
			retClass = checkTravelzenCustomer(resultDTO.getConsistencyCheck(), resultDTO.getManualAuditRemarks(), enterpriseExecutionMoney, enterpriseExecutionTimes, individualExecutionMoney, individualExecutionTimes, resultDTO.getNegativeNetwork(), accessRule,apply.getInWhitelist());
		}else{
			retClass = new ReturnClass(ReturnCode.SUCCESS);
			apply.setApproverId(resultDTO.getAuditorId());
		}
		ReturnCode retCode = retClass.getRetCode();
		apply.setApplicantType(resultDTO.getApplicantType());
		
		//对于以前旧的申请（没有保存销售数据）
//		if(StringUtils.isBlank(apply.getSaleData()))
			apply.setSaleData(new Gson().toJson(flightFinanceServiceHelper.getMonthAverageSales(tzCustomerId, apply.getApplyDate(),18)));
		
		apply.setUpdateTime(new Date());
		if(retCode.equals(ReturnCode.SUCCESS)) {
			if(isExistCustomer(tzCustomerId, apply.getCompanyName()))
				throw WebException.instance("该客户已经通过了额度审核，不能通过审核，请将该申请设为不通过！");

			if(ApplyApplicantType.company == ApplyApplicantType.valueOf(resultDTO.getApplicantType())) {
				if(StringUtils.isBlank(resultDTO.getLegalPerson()))
					throw WebException.instance(ReturnCode.TP0020);
				else
					apply.setLegalPerson(resultDTO.getLegalPerson());
			}
			
//			if(inWhitelist || (!inWhitelist && AccessManualState.WAIT_APPROVE.name().equals(apply.getAccessManualState()))){
				apply.setAccessManualState(AccessManualState.ACCESS.name());
				saveUserComparison(userId, tzCustomerId, apply.getCompanyName());
				apply.setCreditLimitGenerateState(CreditLimitGenerateState.WAIT.name());
				//插入到用户表
				createCustomerByCredit(apply, userId);
//			}
			
//			if(!inWhitelist && AccessManualState.WAIT.name().equals(apply.getAccessManualState()))
//				apply.setAccessManualState(AccessManualState.WAIT_APPROVE.name());

			customerApplyMapper.updateByPrimaryKeySelective(apply);
		} else {
			if(AccessManualState.WAIT.name().equals(apply.getAccessManualState())){
				apply.setAccessManualState(AccessManualState.NOTACCESS.name());
				customerApplyMapper.updateByPrimaryKeySelective(apply);
				retryTaskServiceImpl.createAuditResultNotify(Institution.travelzen, tzCustomerId, retCode.getRetCode(), retClass.getMessage(), apply.getAuditNotifyUrl());
				//人工审核不通过
				sendNotice(apply);
			}
		}
	}
	
	private void checkAuditResultDTO(TravelzenCustomerAuditResultDTO resultDTO){
		if (StringUtils.isBlank(resultDTO.getCustomerApplyId()))
			throw WebException.instance("授信客户人工审核申请ID不能为空");
		if (!EnumUtils.isValidEnum(ApplyApplicantType.class, resultDTO.getApplicantType()))
			throw WebException.instance("请选择申请人类型");
	}

	private void checkApply(CustomerApply apply){
		if(apply == null)
			throw WebException.instance("当前申请不存在");
		if(!AccessAutomaticState.ACCESS.name().equals(apply.getAccessAutomaticState()))
			throw WebException.instance("当前申请未通过准入验证，不能进行人工审核");
		if (AccessManualState.ACCESS.name().equals(apply.getAccessManualState()) || AccessManualState.NOTACCESS.name().equals(apply.getAccessManualState()))
			throw WebException.instance("当前申请已被审核过，不能再次进行人工审核");	
	}
	
	private AccessRule getAccessRule(Boolean inWhitelist){
		AccessRule accessRule = null;
		if(inWhitelist!=null && !inWhitelist)
			accessRule = NewestAccessRuleHolder.nonWhiteCustomerNewestAccessRule;
		else
			accessRule = NewestAccessRuleHolder.whiteCustomerNewestAccessRule;	
		return accessRule;
	}
	
	private void sendNotice(CustomerApply apply){
		CreditNoticeParam creditNoticeParam = new CreditNoticeParam();
		creditNoticeParam.setInwhitelist(apply.getInWhitelist());
		creditNoticeParam.setNoticeScene(NoticeScene.GENERATE_LIMIT);
		creditNoticeParam.setCompanyName(apply.getCompanyName());
		creditNoticeParam.setEmail(apply.getEmail());
		creditNoticeParam.setPhoneNumber(apply.getPhoneNumber());
		creditNoticeParam.setAccessManualState(apply.getAccessManualState());
		creditMessageServiceImpl.sendMailForTzCustomerApply(creditNoticeParam);
		creditMessageServiceImpl.sendSmsMessageForTzCustomerApply(creditNoticeParam);
	}
	
	private ReturnClass checkTravelzenCustomer(Integer consistencyCheck, String manualAuditRemarks, long enterpriseExecutionMoney, Integer enterpriseExecutionTimes, long individualExecutionMoney, Integer individualExecutionTimes, String negativeNetwork, AccessRule accessRule,Boolean inWhitelist) {
		// 工商信息真实,检查组织机构代码证、营业执照证、税务登记证、其它检查一致
		if((consistencyCheck & 16) == 0)
			return new ReturnClass(ReturnCode.TP0015, manualAuditRemarks);
		else if((consistencyCheck & 31) !=31)
			return new ReturnClass(ReturnCode.TP0009);
		// 法院被执行(企业)金额≤3000元
		if(enterpriseExecutionMoney > accessRule.getCourtExecuteCompanyAmount())
			return new ReturnClass(ReturnCode.TP0010,MoneyUtil.cent2Yuan(accessRule.getCourtExecuteCompanyAmount()).toString());
		// 法院被执行(企业)近1年次数<3次
		if(enterpriseExecutionTimes != null && enterpriseExecutionTimes >= accessRule.getCourtExecuteCompanyTimes())
			return new ReturnClass(ReturnCode.TP0011,accessRule.getCourtExecuteCompanyTimes().toString());
		// 法院被执行(个人)金额≤3000元
		if(individualExecutionMoney > accessRule.getCourtExecuteIndividualAmount())
			return new ReturnClass(ReturnCode.TP0012,MoneyUtil.cent2Yuan(accessRule.getCourtExecuteIndividualAmount()).toString());
		// 法院被执行(个人)近1年次数<3次
		if(individualExecutionTimes != null && individualExecutionTimes >= accessRule.getCourtExecuteCompanyTimes())
			return new ReturnClass(ReturnCode.TP0013,accessRule.getCourtExecuteCompanyTimes().toString());
		// 无负面信息
		if(StringUtils.isNotBlank(negativeNetwork))
			return new ReturnClass(ReturnCode.TP0014);
		return new ReturnClass(ReturnCode.SUCCESS);
	}

	private void createCustomerByCredit(CustomerApply apply, String userId) {
		OutCustomerExample example = new OutCustomerExample();
		example.createCriteria().andInstitutionIdEqualTo(Institution.travelzen.name())
			.andMemberIdEqualTo(userId);
		List<OutCustomer> outCustomers = outCustomerMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(outCustomers)) {
			for (OutCustomer outCustomer : outCustomers)
				if (!outCustomer.getOutCustomerId().equals(apply.getTzCustomerId()))
					return;
		}

		CustomerApply4MemberDTO customerApply4MemberDTO = new CustomerApply4MemberDTO();
		customerApply4MemberDTO.setAuditorId(apply.getAuditorId());
		customerApply4MemberDTO.setBusinessLicenceNo(apply.getBusinessLicenceNo());
		customerApply4MemberDTO.setBusinessLicenceMediaId(apply.getBusinessLicenceMediaId());
		customerApply4MemberDTO.setCompanyName(apply.getCompanyName());
		customerApply4MemberDTO.setContactName(apply.getContactName());
		customerApply4MemberDTO.setEmail(apply.getEmail());
		customerApply4MemberDTO.setOrganizationNo(apply.getOrganizationNo());
		customerApply4MemberDTO.setOrganizationMediaId(apply.getOrganizationMediaId());
		customerApply4MemberDTO.setPhoneNumber(apply.getPhoneNumber());
		customerApply4MemberDTO.setTaxRegistrationCertificateNo(apply.getTaxRegistrationCertificateNo());
		customerApply4MemberDTO.setTaxRegistrationCertificateMediaId(apply.getTaxRegistrationCertificateMediaId());
		customerApply4MemberDTO.setUserId(userId);
		customerApply4MemberDTO.setLegalPerson(apply.getLegalPerson());
		boolean individual = ApplyApplicantType.individual.name().equals(apply.getApplicantType());
		String roleIdConfigKey = individual ? "tz_finance_individual_role_id" : "tz_finance_company_role_id";
		customerApply4MemberDTO.setFinanceRoleId(TopsConfReader.getConfContent("properties/credit.properties", roleIdConfigKey, ConfScope.R));
		customerApply4MemberDTO.setIndividual(individual);
		retryTaskServiceImpl.createCreateCustomer(customerApply4MemberDTO, apply.getId());
	}
	
	private void saveUserComparison(String memberId, String tzCustomerId, String companyName) {
		OutCustomer outCustomer = new OutCustomer();
		outCustomer.setMemberId(memberId);
		outCustomer.setOutCustomerId(tzCustomerId);
		outCustomer.setInstitutionId(Institution.travelzen.name());
		outCustomer.setCompanyName(companyName);
		outCustomerMapper.insert(outCustomer);
	}
	
	@Override
	public List<AccessRuleDTO> queryAccessRule(AccessRuleFitObject fitObject){
		AccessRuleExample example = new AccessRuleExample(); 
		example.setOrderByClause("batch_no desc");
		example.createCriteria().andFitObjectEqualTo(fitObject.name());
		List<AccessRule> accessRules = accessRuleMapper.selectByExample(example);
		List<AccessRuleDTO> accessRuleDTOs = convertAccessRules2AccessRuleDTOs(accessRules);
		return accessRuleDTOs;
	}
	
	private List<AccessRuleDTO> convertAccessRules2AccessRuleDTOs(List<AccessRule> accessRules){
		if(accessRules == null || accessRules.isEmpty())
			throw WebException.instance("不存在准入规则！");
		List<AccessRuleDTO> accessRuleDTOs = new ArrayList<AccessRuleDTO>();
		for(AccessRule accessRule : accessRules){
			AccessRuleDTO accessRuleDTO = new AccessRuleDTO();
			accessRuleDTO.setIsCheckWhiteCustomer(BooleanUtils.isTrue(accessRule.getIsCheckWhiteCustomer())?"是":"否");
			accessRuleDTO.setCooperationPeriod(accessRule.getCooperationPeriodMin().toString());
			accessRuleDTO.setOverdueRateTz(rateConvert(accessRule.getOverdueRateTz()));
			accessRuleDTO.setOverdueTimesTz(accessRule.getOverdueTimesTz().toString());
			accessRuleDTO.setOverdueDaysTz(accessRule.getOverdueDaysTz()==null?"无":accessRule.getOverdueDaysTz().toString());
			accessRuleDTO.setOverdueRateCana(rateConvert(accessRule.getOverdueRateCana()));
			accessRuleDTO.setOverdueTimesCana(accessRule.getOverdueTimesCana().toString());
			accessRuleDTO.setPurchaseOrderGrowthRate(accessRule.getPurchaseOrderGrowthRate()==null?"无":rateConvert(accessRule.getPurchaseOrderGrowthRate()));
			accessRuleDTO.setCourtExecuteCompanyAmount(MoneyUtil.cent2Yuan(accessRule.getCourtExecuteCompanyAmount()));
			accessRuleDTO.setCourtExecuteCompanyTimes(accessRule.getCourtExecuteCompanyTimes().toString());
			accessRuleDTO.setCourtExecuteIndividualAmount(MoneyUtil.cent2Yuan(accessRule.getCourtExecuteIndividualAmount()));
			accessRuleDTO.setCourtExecuteIndividualTimes(accessRule.getCourtExecuteIndividualTimes().toString());
			accessRuleDTOs.add(accessRuleDTO);
		}
		return accessRuleDTOs;
	}
	
	private String rateConvert(BigDecimal rate){
		DecimalFormat df=new DecimalFormat("0.00"); //格式化，保留两位小数 
		return df.format(rate.multiply(new BigDecimal(100)))+"%";
	}
	
	private boolean isExistCustomer(String tzCustomerId, String companyName) {
		OutCustomerExample outCustomerExample = new OutCustomerExample();
		outCustomerExample.createCriteria().andOutCustomerIdEqualTo(tzCustomerId).andInstitutionIdEqualTo(Institution.travelzen.name());
		return outCustomerMapper.selectByExample(outCustomerExample).size() != 0;
	}
}
