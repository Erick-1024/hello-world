package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.dao.po.Project;
import com.cana.asset.dao.po.ProjectBlobTextWithBLOBs;
import com.cana.asset.dao.po.ProjectDocument;
import com.cana.asset.dao.po.ProjectFactor;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectDocumentDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.asset.enums.ProjectFactorStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectRepaymentMethodsEnum;
import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectTypeEnum;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author jiangzhou.Ren
 * @time 2016年5月18日上午10:18:53 项目列表查询数据转换
 */
@Component
public class ProjectConvertor {
	public static List<ProjectListResponseDTO> convertProjectDao2ResDTO(List<Project> projects) {

		List<ProjectListResponseDTO> projectListDTOs = new ArrayList<ProjectListResponseDTO>();
		for (Project project : projects) {
			ProjectListResponseDTO response = new ProjectListResponseDTO();
			// 参数校验
			response.setId(project.getId());
			response.setName(project.getName());
			response.setCoreCompanyName(project.getCoreCompanyName());
			if (StringUtils.isNotBlank(project.getType())) {
				response.setTypeDesc(ProjectTypeEnum.valueOf(project.getType()).desc());
			}
			if (StringUtils.isNotBlank(project.getCoreIndustry())) {
				response.setCoreIndustry(IndustryTypeEnum.valueOf(project.getCoreIndustry()).desc());
			}
			if (StringUtils.isNotBlank(project.getStatus())) {
				response.setStatusDesc(ProjectStatusEnum.valueOf(project.getStatus()).desc());
			}
			//项目状态枚举英文标识
			response.setProjectStatus(project.getStatus());
			projectListDTOs.add(response);
		}
		return projectListDTOs;
	}

	/*
	 * 查询项目，资金方，合同
	 * @param project
	 * @param projectFactors
	 *  @param projectDocuments
	 */
	public static ProjectDTO convertToProjectDTO(Project project, List<ProjectFactor> projectFactors,
			List<ProjectDocument> projectDocuments,ProjectBlobTextWithBLOBs blobText) {
		// 项目
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setName(project.getName());
		projectDTO.setId(project.getId());
		projectDTO.setType(ProjectTypeEnum.valueOf(project.getType()));
		projectDTO.setStatus(ProjectStatusEnum.valueOf(project.getStatus()));
		projectDTO.setCoreCompanyId(project.getCoreCompanyId());
		projectDTO.setCoreCompanyName(project.getCoreCompanyName());
		projectDTO.setCoreOrganizationCode(project.getCoreOrganizationCode());
		projectDTO.setCoreBusinessLicenceCode(project.getCoreBusinessLicenceCode());
		projectDTO.setCoreTaxRegistrationCertificateCode(project.getCoreTaxRegistrationCertificateCode());
		projectDTO.setCoreIndustry(IndustryTypeEnum.valueOf(project.getCoreIndustry()));
		projectDTO.setCoreEconomicCategory(EconomicCategoryEnum.valueOf(project.getCoreEconomicCategory()));
		//添加账户号
        String regex ="(.{4})";
		projectDTO.setCoreAccountNo(project.getCoreAccountNo().replaceAll (regex, "$1 "));
		projectDTO.setFinanceApplicant(project.getFinanceApplicant());
		//单笔金额
		projectDTO.setSingleLoanLimitLower(project.getSingleLoanLimitLower());
		projectDTO.setSingleLoanLimitUpper(project.getSingleLoanLimitUpper());
		//页面展示转换过的值
		projectDTO.setSingleLoanLimitLowerAdd(MoneyUtil.cent2Yuan(project.getSingleLoanLimitLower()));
		projectDTO.setSingleLoanLimitUpperAdd(MoneyUtil.cent2Yuan(project.getSingleLoanLimitUpper()));
		projectDTO.setInterestRateUnit(InterestRateUnit.valueOf(project.getInterestRateUnit()));
		//利率区间MoneyArithUtil
		projectDTO.setInterestRateLower(project.getInterestRateLower());
		projectDTO.setInterestRateUpper(project.getInterestRateUpper());
		int lenOne = MoneyArithUtil.convertInterestRateToString(project.getInterestRateLower()).length();
		projectDTO.setInterestRateLowerAdd(MoneyArithUtil.convertInterestRateToString(project.getInterestRateLower()).substring(0, lenOne-1));
		int lenTwo = MoneyArithUtil.convertInterestRateToString(project.getInterestRateUpper()).length();
		projectDTO.setInterestRateUpperAdd(MoneyArithUtil.convertInterestRateToString(project.getInterestRateUpper()).substring(0, lenTwo-1));
		projectDTO.setLoanPeriodUnit(DateUnit.valueOf(project.getLoanPeriodUnit()));
		projectDTO.setLoanPeriodLower(project.getLoanPeriodLower());
		projectDTO.setLoanPeriodUpper(project.getLoanPeriodUpper());
		projectDTO.setRepaymentMethods(project.getRepaymentMethods());
		getRepaymentMethods(project, projectDTO);
		//提前还款手续费率
		projectDTO.setEarlyRepaymentChargeRatio(project.getEarlyRepaymentChargeRatio());
		int len = MoneyArithUtil.convertInterestRateToString(project.getEarlyRepaymentChargeRatio()).length();
		projectDTO.setEarlyRepaymentChargeRatioAdd(MoneyArithUtil.convertInterestRateToString(project.getEarlyRepaymentChargeRatio()).substring(0, len-1));
		projectDTO.setExtensionDays(project.getExtensionDays());
		projectDTO.setExtensionRatioMethod(ChargeMethod.valueOf(project.getExtensionRatioMethod()));
		//展期上浮比率
		projectDTO.setExtensionRatio(project.getExtensionRatio());
		int length = MoneyArithUtil.convertInterestRateToString(project.getExtensionRatio()).length();
		projectDTO.setExtensionRatioAdd(MoneyArithUtil.convertInterestRateToString(project.getExtensionRatio()).substring(0, length-1));
		projectDTO.setUseHolidayPolicy(BooleanUtils.isTrue(project.getUseHolidayPolicy()));
		projectDTO.setPenaltyRateMethod(ChargeMethod.valueOf(project.getPenaltyRateMethod()));
		//逾期上浮比例
		projectDTO.setPenaltyRate(project.getPenaltyRate());
		int lengthTwo = MoneyArithUtil.convertInterestRateToString(project.getPenaltyRate()).length();
		projectDTO.setPenaltyRateAdd(MoneyArithUtil.convertInterestRateToString(project.getPenaltyRate()).substring(0, lengthTwo-1));
		projectDTO.setDeductionTime(project.getDeductionTime());
		projectDTO.setDeductionRule(DeductionRule.valueOf(project.getDeductionRule()));

		// 资金方
		List<ProjectFactorDTO> projectFactorDTOs = new ArrayList<ProjectFactorDTO>();
		for (ProjectFactor projectFactor : projectFactors) {
			ProjectFactorDTO factorDTO = new ProjectFactorDTO();
			factorDTO.setAccountNo(projectFactor.getAccountNo());
			factorDTO.setBusinessLicenceCode(projectFactor.getBusinessLicenceCode());
			factorDTO.setCompanyId(projectFactor.getCompanyId());
			factorDTO.setCompanyName(projectFactor.getCompanyName());
			factorDTO.setId(projectFactor.getId());
			factorDTO.setOrganizationCode(projectFactor.getOrganizationCode());
			factorDTO.setProjectId(projectFactor.getProjectId());
			factorDTO.setStatus(ProjectFactorStatusEnum.valueOf(projectFactor.getStatus()).desc());
			factorDTO.setTaxRegistrationCertificateCode(projectFactor.getTaxRegistrationCertificateCode());
			projectFactorDTOs.add(factorDTO);
		}
		// 合同
		List<ProjectDocumentDTO> documentDTOs = new ArrayList<ProjectDocumentDTO>();
		for (ProjectDocument projectDocument : projectDocuments) {
			ProjectDocumentDTO documentDTO = new ProjectDocumentDTO();
			documentDTO.setId(projectDocument.getId());
			documentDTO.setName(projectDocument.getName());
			documentDTO.setProjectId(projectDocument.getProjectId());
			documentDTO.setVersion(projectDocument.getVersion());
			documentDTO.setMediaId(projectDocument.getMediaId());
			documentDTOs.add(documentDTO);
		}
		//资金方和合同数据
			projectDTO.setProjectFactors(projectFactorDTOs);
			projectDTO.setProjectDocument(documentDTOs);
		//项目产品介绍和类型描述
		if (null != blobText) {
			projectDTO.setProductIntroduction(blobText.getProductIntroduction());
			projectDTO.setProductTypeDesc(blobText.getProductTypeDesc());
		}
		return projectDTO;
	}

	/**
	 * 付款方式中英文处理
	 * @param project
	 * @param projectDTO
	 */
	private static void getRepaymentMethods(Project project, ProjectDTO projectDTO) {
		
		List<ProjectRepaymentMethodsEnum> repaymentMethodTypeList = new ArrayList<ProjectRepaymentMethodsEnum>();
		 repaymentMethodTypeList.add(ProjectRepaymentMethodsEnum.valueOf("ORDER"));
		 repaymentMethodTypeList.add(ProjectRepaymentMethodsEnum.valueOf("MATURITY"));
		 repaymentMethodTypeList.add(ProjectRepaymentMethodsEnum.valueOf("MONTHLY"));
		 repaymentMethodTypeList.add(ProjectRepaymentMethodsEnum.valueOf("EQUALALL"));
		 repaymentMethodTypeList.add(ProjectRepaymentMethodsEnum.valueOf("EQUALPRINCIPAL"));
		//付款方式的处理
		  String[] strings = project.getRepaymentMethods().split(",");
		List<ProjectRepaymentMethodsEnum> repaymentMethodList = new ArrayList<ProjectRepaymentMethodsEnum>();
		if(strings == null||strings.length == 0){
			WebException.instance("付款方式为空");
		}else{
			for (String string : strings) {
				repaymentMethodList.add(ProjectRepaymentMethodsEnum.valueOf(string));
			}
		}
		projectDTO.setRepaymentMethodTypeList(repaymentMethodTypeList);
		projectDTO.setRepaymentMethodList(repaymentMethodList);
	}
}
