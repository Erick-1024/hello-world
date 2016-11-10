package com.cana.asset.service.transaction.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.asset.dto.ProjectDocumentDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.asset.enums.ProjectErrorFieldEnum;
import com.cana.vbam.common.asset.enums.ProjectFactorStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectTypeEnum;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * 项目新增、编辑校验类
 * @author XuMeng
 *
 */
public class AssetProjectPersistenceValidator {

	/**
	 * 校验项目管理参数是否合法
	 * @param userVo，当前登录用户
	 * @param errorInfos，校验请求之后存放的错误数据，key为错误的字段，value为错误提示文本
	 * @param projectRequest，新增编辑项目的请求数据
	 * @param dbStatus，如果为空，则表示新增项目，否则为修改项目
	 * @param coreCustomer，项目中的核心企业
	 * @param factorMaps，资金方Map，key为资金方企业名称
	 * @param accountMaps，账户Map，key为银行账号
	 */
	public static void checkProjectRequest(UserVo userVo, Map<ProjectErrorFieldEnum, String> errorInfos,
			ProjectRequestDTO projectRequest, ProjectStatusEnum dbStatus,
			CustomerVo coreCustomer, Map<String, CustomerVo> factorMaps,
			Map<String, AccountDTO> accountMaps){

		if (!checkProjectRequestBasicFeildsIsValid(projectRequest, errorInfos)) return;
		
		ProjectStatusEnum projectStatus = ProjectStatusEnum.valueOf(projectRequest.getStatus());

		if (!checkProjectRequestNextStatusIsValid(projectStatus, errorInfos, dbStatus)) return;

		if (!checkProjectFactorsIsNotNull(userVo, errorInfos, projectRequest)) return;

		if (projectStatus == ProjectStatusEnum.GOING
				|| projectStatus == ProjectStatusEnum.PAUSE
				|| projectStatus == ProjectStatusEnum.CLOSE) {

			// 核心企业名称及三证、银行卡
			if (!checkCoreCompanyIsValid(errorInfos, projectRequest, dbStatus, coreCustomer, accountMaps)) return;
			
			// 资金方企业名称及三证、银行卡
			Set<String> companyNames = Sets.newHashSet();
			for (ProjectFactorDTO factorDTO : projectRequest.getProjectFactors()) {
				boolean isValid = checkFactorCompanyIsValid(errorInfos, factorDTO, factorMaps, accountMaps);
				if (!isValid) return;
				
				if (companyNames.contains(factorDTO.getCompanyName())) {
					throw WebException.instance("资金方企业重复：" + factorDTO.getCompanyName());
				}
				companyNames.add(factorDTO.getCompanyName());
			}
		}
		
		// 基础交易信息必须存在且合法
		checkProjectTradeInfoFieldsIsValid(projectRequest);
		
		// 判断当前用户对合同有没有修改权限，有的话，合同模版如果存在，则必须合法，
		checkProjectDocumentsIsValid(projectRequest);
	}
	
	/**
	 * 校验资金方字段某些值不能为空
	 * @return
	 */
	private static boolean checkProjectFactorsIsNotNull(UserVo userVo, Map<ProjectErrorFieldEnum, String> errorInfos,
			ProjectRequestDTO projectRequest) {
		
		if (CollectionUtils.isEmpty(projectRequest.getProjectFactors())) {
			throw WebException.instance("资金方信息不能为空");
		}
		if (!StringUtils.equals(userVo.getCustomer().getCustomerName(), projectRequest.getProjectFactors().get(0).getCompanyName())) {
			throw WebException.instance("第一个资金方必须是当前客户");
		}
		projectRequest.getProjectFactors().get(0).setCompanyId(userVo.getCustomerId());
		for (ProjectFactorDTO factorDTO : projectRequest.getProjectFactors()) {
			if (StringUtils.isBlank(factorDTO.getCompanyName())) {
				errorInfos.put(ProjectErrorFieldEnum.companyName, "资金方企业名称不能为空");
				return false;
			}
			if (!EnumUtils.isValidEnum(ProjectFactorStatusEnum.class, factorDTO.getStatus())) {
				throw WebException.instance("资金方状态值不合法");
			}
		}
		return true;
	}
	
	/**
	 * 校验基础交易信息各字段是否合法，因页面也会有校验，所以一般此方法都会校验通过。
	 * 如果校验不通过，则很可能是非正常流程提交的请求，因此会直接向上抛异常
	 * @param projectRequest
	 * @return
	 */
	private static boolean checkProjectTradeInfoFieldsIsValid(ProjectRequestDTO projectRequest) {
		if (StringUtils.isBlank(projectRequest.getFinanceApplicant())) {
			throw WebException.instance("融资申请人不能为空");
		}
		
		if (StringUtils.isBlank(projectRequest.getSingleLoanLimitLower())
				|| MoneyUtil.yuan2Cent(projectRequest.getSingleLoanLimitLower()) < 0) {
			throw WebException.instance("请填写正确的单笔贷款下限");
		}
		if (StringUtils.isBlank(projectRequest.getSingleLoanLimitUpper())
				|| MoneyUtil.yuan2Cent(projectRequest.getSingleLoanLimitUpper())
					< MoneyUtil.yuan2Cent(projectRequest.getSingleLoanLimitLower())) {
			throw WebException.instance("请填写正确的单笔贷款上限");
		}

		if (!EnumUtils.isValidEnum(InterestRateUnit.class, projectRequest.getInterestRateUnit())) {
			throw WebException.instance("利率单位不合法");
		}
		if (StringUtils.isBlank(projectRequest.getInterestRateLower())
				|| MoneyArithUtil.convertStringToInterestRate(projectRequest.getInterestRateLower()).doubleValue() < 0) {
			throw WebException.instance("请填写正确的利率区间下限");
		}
		if (StringUtils.isBlank(projectRequest.getInterestRateUpper())
				|| MoneyArithUtil.convertStringToInterestRate(projectRequest.getInterestRateUpper()).doubleValue()
					< MoneyArithUtil.convertStringToInterestRate(projectRequest.getInterestRateLower()).doubleValue()) {
			throw WebException.instance("请填写正确的利率区间上限");
		}

		if (!EnumUtils.isValidEnum(DateUnit.class, projectRequest.getLoanPeriodUnit())) {
			throw WebException.instance("期限单位不合法");
		}
		if (projectRequest.getLoanPeriodLower() == null
				|| projectRequest.getLoanPeriodLower() < 1) {
			throw WebException.instance("请填写正确的期限区间下限");
		}
		if (projectRequest.getLoanPeriodUpper() == null
				|| projectRequest.getLoanPeriodUpper() < projectRequest.getLoanPeriodLower()) {
			throw WebException.instance("请填写正确的期限区间上限");
		}

		if (StringUtils.isEmpty(projectRequest.getRepaymentMethods())) {
			throw WebException.instance("请至少选择一种还款方式");
		}
		String[] repaymentMethods = projectRequest.getRepaymentMethods().split(",");
		//需要先转换格式
		
		for(String repaymentMethod : repaymentMethods) {
			if (!EnumUtils.isValidEnum(RepaymentType.class, repaymentMethod)) {
				throw WebException.instance("[" + repaymentMethod + "]不是合法的还款方式");
			}
		}

		if (StringUtils.isEmpty(projectRequest.getEarlyRepaymentChargeRatio())
				|| MoneyArithUtil.convertStringToInterestRate(projectRequest.getEarlyRepaymentChargeRatio()).doubleValue() < 0) {
			throw WebException.instance("请填写正确的提前还款手续费率");
		}

		if (projectRequest.getExtensionDays() == null
				|| projectRequest.getExtensionDays() < 0) {
			throw WebException.instance("展期天数不合法");
		}

		if (!EnumUtils.isValidEnum(ChargeMethod.class, projectRequest.getExtensionRatioMethod())) {
			throw WebException.instance("展期收息方式不合法");
		}
		if (StringUtils.isEmpty(projectRequest.getExtensionRatio())
				|| MoneyArithUtil.convertStringToInterestRate(projectRequest.getExtensionRatio()).doubleValue() < 0) {
			throw WebException.instance("展期利率/展期上浮比例不合法");
		}
		if (!EnumUtils.isValidEnum(ChargeMethod.class, projectRequest.getPenaltyRateMethod())) {
			throw WebException.instance("逾期管理费罚息方式不合法");
		}
		if (StringUtils.isEmpty(projectRequest.getPenaltyRate())
				|| MoneyArithUtil.convertStringToInterestRate(projectRequest.getPenaltyRate()).doubleValue() < 0) {
			throw WebException.instance("逾期利率/逾期上浮比例不合法");
		}
		
		if (StringUtils.isEmpty(projectRequest.getDeductionTime())
				|| !projectRequest.getDeductionTime().matches("^(([01]?[0-9])|(2[0-4])):([0-5][0-9])$")) {
			throw WebException.instance("扣款时点不合法");
		}
		
		if (!EnumUtils.isValidEnum(DeductionRule.class, projectRequest.getDeductionRule())) {
			throw WebException.instance("扣款规则不合法");
		}
		return true;
	}
	
	/**
	 * 校验项目名称是否为空，
	 * 校验类型、状态、行业、经济种类四个枚举类是否合法
	 */
	private static boolean checkProjectRequestBasicFeildsIsValid(ProjectRequestDTO projectRequest,
			Map<ProjectErrorFieldEnum, String> errorInfos) {

		if (StringUtils.isBlank(projectRequest.getName())) {
			errorInfos.put(ProjectErrorFieldEnum.name, "项目名称不能为空");
		}
		if (!ProjectTypeEnum.platform.name().equals(projectRequest.getType())) {
			errorInfos.put(ProjectErrorFieldEnum.type, "业务类型不正确");
		}
		if (!EnumUtils.isValidEnum(ProjectStatusEnum.class, projectRequest.getStatus())) {
			errorInfos.put(ProjectErrorFieldEnum.status, "项目状态不合法");
		}
		if (!EnumUtils.isValidEnum(IndustryTypeEnum.class, projectRequest.getCoreIndustry())) {
			errorInfos.put(ProjectErrorFieldEnum.coreIndustry, "行业字段不合法");
		}
		if (!EnumUtils.isValidEnum(EconomicCategoryEnum.class, projectRequest.getCoreEconomicCategory())) {
			errorInfos.put(ProjectErrorFieldEnum.coreEconomicCategory, "经济类型字段不合法");
		}
		return errorInfos.isEmpty();
	}
	
	/**
	 * 判断项目修改后的状态是不是被允许
	 * @param projectStatus，本次编辑的状态
	 * @param dbStatus，编辑之前项目所处的状态，如果是新增项目，则为null
	 */
	private static boolean checkProjectRequestNextStatusIsValid(ProjectStatusEnum projectStatus,
			Map<ProjectErrorFieldEnum, String> errorInfos, ProjectStatusEnum dbStatus) {

		List<ProjectStatusEnum> validStatus = ProjectStatusEnum.getNextValidStatus(dbStatus);
		if (!validStatus.contains(projectStatus)) {
			String statusDescs = "";
			for (int i = 0; i < validStatus.size();) {
				statusDescs += validStatus.get( i++ ).desc();

				if (i < validStatus.size()) {
					statusDescs += "或者";
				}
			}
			errorInfos.put(ProjectErrorFieldEnum.status, "项目状态必须是" + statusDescs);
		}
		return errorInfos.isEmpty();
	}

	/**
	 * 判断合同是否合法，如果当前用户没有对合同的编辑权限，则忽略请求的合同内容。
	 * 如果合同内容不合法，则抛异常
	 * @param projectRequest
	 */
	private static boolean checkProjectDocumentsIsValid(ProjectRequestDTO projectRequest) {
		if (BooleanUtils.isTrue(projectRequest.getHavePermissionModifyDocument())
				&& CollectionUtils.isNotEmpty(projectRequest.getProjectDocuments())) {

			List<ProjectDocumentDTO> docs = projectRequest.getProjectDocuments();
			for (ProjectDocumentDTO doc : docs) {
				if (StringUtils.isBlank(doc.getMediaId())) {
					throw WebException.instance("合同文件ID为空");
				}
				if (StringUtils.isBlank(doc.getName())) {
					throw WebException.instance("合同名称为空");
				}
			}
		}
		return true;
	}

	/**
	 * 校验核心企业是否存在，且三证是否正确，银行账号是否正确
	 * @param errorInfos
	 * @param projectRequest
	 * @param dbStatus
	 * @param coreCustomer
	 * @param accountMaps
	 */
	private static boolean checkCoreCompanyIsValid(Map<ProjectErrorFieldEnum, String> errorInfos,
			ProjectRequestDTO projectRequest, ProjectStatusEnum dbStatus,
			CustomerVo coreCustomer, Map<String, AccountDTO> accountMaps) {

		if (StringUtils.isBlank(projectRequest.getCoreCompanyName())) {
			errorInfos.put(ProjectErrorFieldEnum.coreCompanyName, "核心企业名称不能为空");
		} else {
			if (coreCustomer == null) {
				errorInfos.put(ProjectErrorFieldEnum.coreCompanyName, "核心企业不存在");
			} else {
				if (!StringUtils.equals(coreCustomer.getOrganizationCode(), projectRequest.getCoreOrganizationCode())) {
					errorInfos.put(ProjectErrorFieldEnum.coreOrganizationCode, "核心企业组织机构代码不正确");
				}
				if (!StringUtils.equals(coreCustomer.getBusinessLicenceCode(), projectRequest.getCoreBusinessLicenceCode())) {
					errorInfos.put(ProjectErrorFieldEnum.coreBusinessLicenceCode, "核心企业营业执照号码不正确");
				}
				if (!StringUtils.equals(coreCustomer.getTaxRegistrationCertificateCode(), projectRequest.getCoreTaxRegistrationCertificateCode())) {
					errorInfos.put(ProjectErrorFieldEnum.coreTaxRegistrationCertificateCode, "核心企业税务登记证号码不正确");
				}
				
				//银行账号
				if (StringUtils.isNotEmpty(projectRequest.getCoreAccountNo())) {
					AccountDTO accountDTO = accountMaps.get(projectRequest.getCoreAccountNo());
					if (accountDTO == null) {
						errorInfos.put(ProjectErrorFieldEnum.coreAccountNo, "核心企业银行账号不存在");
					} else {
						if (!StringUtils.equals(coreCustomer.getCustomerId(), accountDTO.getCompanyId())) {
							errorInfos.put(ProjectErrorFieldEnum.coreAccountNo, "核心企业银行账号不属于当前客户");
						}
					}
				}
				projectRequest.setCoreCompanyId(coreCustomer.getCustomerId());
			}
		}

		return errorInfos.isEmpty();
	}

	private static boolean checkFactorCompanyIsValid(Map<ProjectErrorFieldEnum, String> errorInfos,
			ProjectFactorDTO factorDTO, Map<String, CustomerVo> factorMaps,
			Map<String, AccountDTO> accountMaps) {
		
		CustomerVo factorCustomer = factorMaps.get(factorDTO.getCompanyName());
		if (factorCustomer == null) {
			errorInfos.put(ProjectErrorFieldEnum.companyName, "资金方企业名称不存在");
		} else {
			if (!StringUtils.equals(factorCustomer.getOrganizationCode(), factorDTO.getOrganizationCode())) {
				errorInfos.put(ProjectErrorFieldEnum.organizationCode, "资金方企业组织机构代码不正确");
			}
			if (!StringUtils.equals(factorCustomer.getBusinessLicenceCode(), factorDTO.getBusinessLicenceCode())) {
				errorInfos.put(ProjectErrorFieldEnum.businessLicenceCode, "资金方企业营业执照号码不正确");
			}
			if (!StringUtils.equals(factorCustomer.getTaxRegistrationCertificateCode(), factorDTO.getTaxRegistrationCertificateCode())) {
				errorInfos.put(ProjectErrorFieldEnum.taxRegistrationCertificateCode, "资金方企业税务登记证号码不正确");
			}
			
			// 银行账户校验
			if (StringUtils.isNotEmpty(factorDTO.getAccountNo())) {
				AccountDTO accountDTO = accountMaps.get(factorDTO.getAccountNo());
				if (accountDTO == null) {
					errorInfos.put(ProjectErrorFieldEnum.accountNo, "资金方银行账号不存在");
				} else {
					if (!StringUtils.equals(factorCustomer.getCustomerId(), accountDTO.getCompanyId())) {
						errorInfos.put(ProjectErrorFieldEnum.accountNo, "资金方银行账号不属于当前客户");
					}
				}
			}
			
			factorDTO.setCompanyId(factorCustomer.getCustomerId());
		}
		
		return errorInfos.isEmpty();
	}
	
}
