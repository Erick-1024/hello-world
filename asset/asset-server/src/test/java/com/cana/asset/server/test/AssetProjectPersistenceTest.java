package com.cana.asset.server.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.service.IAssetProjectPersistenceService;
import com.cana.vbam.common.asset.dto.ProjectDocumentDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.asset.enums.ProjectFactorStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectTypeEnum;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class AssetProjectPersistenceTest {

	@Resource
	private IAssetProjectPersistenceService assetProjectPersistenceService;
	
	private String coreCompanyName = "上海长寿国际旅行社有限公司";
	private ProjectStatusEnum STATUS = ProjectStatusEnum.GOING;
	private String project_name = "单元测试添加的项目";

	@Test
	public void testAdd() {
		ProjectRequestDTO projectRequest = new ProjectRequestDTO();
		addBasicFeilds(projectRequest);
		addCoreCompany(projectRequest);
		addFactors(projectRequest);
		addProjectTradeInfo(projectRequest);
		addDocuments(projectRequest);
		
		ProjectStatusResponseDTO res = assetProjectPersistenceService.addProject("cana-baoli", projectRequest);
		System.out.println(new Gson().toJson(res));
	}
	
	@Test
	public void testUpdate() {
		ProjectRequestDTO projectRequest = new ProjectRequestDTO();
		projectRequest.setId("160526101805201");
		addBasicFeilds(projectRequest);
		addCoreCompany(projectRequest);
		addFactors(projectRequest);
		projectRequest.getProjectFactors().get(0).setId("160526101805201");
		addProjectTradeInfo(projectRequest);
		addDocuments(projectRequest);
		
		ProjectStatusResponseDTO res = assetProjectPersistenceService.updateProject("cana-baoli", projectRequest);
		System.out.println(new Gson().toJson(res));
	}

	
	private void addDocuments(ProjectRequestDTO projectRequest) {
		List<ProjectDocumentDTO> projectDocuments = Lists.newArrayList();
		
		ProjectDocumentDTO doc = new ProjectDocumentDTO();
		doc.setVersion("2015-01-22");
		doc.setName("框架合同1");
		doc.setMediaId("568e04cb900841a658df4e7a");
		projectDocuments.add(doc);
		
		doc = new ProjectDocumentDTO();
		doc.setVersion("2015-01-23");
		doc.setName("框架合同2");
		doc.setMediaId("568e04cb900841a658df4e7a");
		projectDocuments.add(doc);
		
		projectRequest.setProjectDocuments(projectDocuments);
	}
	
	private void addCoreCompany(ProjectRequestDTO projectRequest) {
		projectRequest.setCoreCompanyName(coreCompanyName);
		projectRequest.setCoreOrganizationCode("91310113630729373A");
		projectRequest.setCoreBusinessLicenceCode("91310113630729373A");
		projectRequest.setCoreTaxRegistrationCertificateCode("91310113630729373A");
		projectRequest.setCoreAccountNo("3110210003631006431");
	}

	private void addFactors(ProjectRequestDTO projectRequest) {
		List<ProjectFactorDTO> projectFactors = Lists.newArrayList();
		ProjectFactorDTO factor = new ProjectFactorDTO();
		factor.setCompanyName("上海凯拿资产管理有限公司");
		factor.setOrganizationCode("111111");
		factor.setBusinessLicenceCode("111111");
		factor.setTaxRegistrationCertificateCode("123456");
		factor.setAccountNo("3110210003631006466");
		factor.setStatus(ProjectFactorStatusEnum.NORMAL.name());
		projectFactors.add(factor);
		projectRequest.setProjectFactors(projectFactors);
	}
	
	private void addProjectTradeInfo(ProjectRequestDTO projectRequest) {
		projectRequest.setFinanceApplicant("真旅融资客户二级采购商");
		projectRequest.setSingleLoanLimitLower("324.24");
		projectRequest.setSingleLoanLimitUpper("2345.23");
		projectRequest.setInterestRateUnit(InterestRateUnit.YEAR.name());
		projectRequest.setInterestRateLower("0.05%");
		projectRequest.setInterestRateUpper("0.05");
		projectRequest.setLoanPeriodUnit(DateUnit.MONTH.name());
		projectRequest.setLoanPeriodLower(1);
		projectRequest.setLoanPeriodUpper(1);
		projectRequest.setRepaymentMethods(RepaymentType.ORDER.name() + ",");
		projectRequest.setEarlyRepaymentChargeRatio("0.01");
		projectRequest.setExtensionDays(0);
		projectRequest.setExtensionRatioMethod(ChargeMethod.RATIO.name());
		projectRequest.setExtensionRatio("0");
		projectRequest.setUseHolidayPolicy(true);
		projectRequest.setPenaltyRateMethod(ChargeMethod.RATIO.name());
		projectRequest.setPenaltyRate("0");
		projectRequest.setDeductionTime("20:00");
		projectRequest.setDeductionRule(DeductionRule.ALL.name());
		projectRequest.setProductIntroduction("产品介绍产品介绍产品介绍");
		projectRequest.setProductTypeDesc("产品类型描述产品类型描述产品类型描述");
	}
	
	private void addBasicFeilds(ProjectRequestDTO projectRequest) {
		projectRequest.setName(project_name);
		projectRequest.setType(ProjectTypeEnum.platform.name());
		projectRequest.setStatus(STATUS.name());
		projectRequest.setHavePermissionModifyDocument(true);
		projectRequest.setCoreIndustry(IndustryTypeEnum.APPLIANCE.name());
		projectRequest.setCoreEconomicCategory(EconomicCategoryEnum.HMT.name());
	}
}
