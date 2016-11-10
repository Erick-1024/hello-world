package com.cana.asset.service.transaction.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetOperateLogMapper;
import com.cana.asset.dao.mapper.gen.ProjectBlobTextMapper;
import com.cana.asset.dao.mapper.gen.ProjectDocumentMapper;
import com.cana.asset.dao.mapper.gen.ProjectFactorMapper;
import com.cana.asset.dao.mapper.gen.ProjectMapper;
import com.cana.asset.dao.po.AssetOperateLog;
import com.cana.asset.dao.po.Project;
import com.cana.asset.dao.po.ProjectBlobTextWithBLOBs;
import com.cana.asset.dao.po.ProjectDocument;
import com.cana.asset.dao.po.ProjectDocumentExample;
import com.cana.asset.dao.po.ProjectFactor;
import com.cana.asset.dao.po.ProjectFactorExample;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.asset.service.transaction.IAssetProjectPersistenceTransactionService;
import com.cana.asset.service.transaction.util.AssetProjectPersistenceValidator;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.asset.dto.ProjectDocumentDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.asset.enums.AssetOperateLogType;
import com.cana.vbam.common.asset.enums.ProjectErrorFieldEnum;
import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

@Service
public class AssetProjectPersistenceTransactionServiceImpl implements IAssetProjectPersistenceTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Gson gson = new Gson();
	
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private ProjectBlobTextMapper projectBlobTextMapper;
	@Resource
	private ProjectDocumentMapper projectDocumentMapper;
	@Resource
	private AssetOperateLogMapper assetOperateLogMapper;
	@Resource
	private ProjectFactorMapper projectFactorMapper;

	@Override
	public ProjectStatusResponseDTO addProject(UserVo userVo, ProjectRequestDTO projectRequest,
			CustomerVo coreCustomer, Map<String, CustomerVo> factorMaps,
			Map<String, AccountDTO> accountMaps) {
		Map<ProjectErrorFieldEnum, String> errorInfos = Maps.newHashMap();

		//校验传入项目值，如果是立项阶段，则不校验
		AssetProjectPersistenceValidator.checkProjectRequest(userVo, errorInfos, projectRequest, null, coreCustomer, factorMaps, accountMaps);
		if (!errorInfos.isEmpty()) {
			return new ProjectStatusResponseDTO(errorInfos);
		}

		String projectId = IDGenerator.generateAssetProjectId();
		
		saveProject(projectId, userVo, projectRequest);
		saveProjectBlobText(projectId, projectRequest, false);
		saveProjectFactors(projectId, projectRequest);
		saveProjectDocuments(projectId, projectRequest);
		saveOperateLog(AssetOperateLogType.project, userVo, projectId, "创建项目");

		return new ProjectStatusResponseDTO(projectId, errorInfos);
	}

	@Override
	public ProjectStatusResponseDTO updateProject(UserVo userVo, ProjectRequestDTO projectRequest,
			CustomerVo coreCustomer, Map<String, CustomerVo> factorMaps,
			Map<String, AccountDTO> accountMaps) {
		Map<ProjectErrorFieldEnum, String> errorInfos = Maps.newHashMap();
		
		Project oldProject = projectMapper.lockByPrimaryKey(projectRequest.getId());
		if (oldProject == null)
			throw WebException.instance("项目ID不存在");
		if (!StringUtils.equals(userVo.getCustomerId(), oldProject.getCreateCustomerId()))
			throw WebException.instance("当前客户无权限修改该项目");

		ProjectStatusEnum dbProjectStatus = ProjectStatusEnum.valueOf(oldProject.getStatus());
		AssetProjectPersistenceValidator.checkProjectRequest(userVo, errorInfos, projectRequest, dbProjectStatus, coreCustomer, factorMaps, accountMaps);;
		if (!errorInfos.isEmpty()) {
			return new ProjectStatusResponseDTO(errorInfos);
		}
		
		saveProject(oldProject.getId(), userVo, projectRequest);
		saveProjectBlobText(oldProject.getId(), projectRequest, true);
		prepareDBFactorsBeforeUpdate(oldProject.getId(), projectRequest, dbProjectStatus);
		saveProjectFactors(oldProject.getId(), projectRequest);
		saveProjectDocuments(oldProject.getId(), projectRequest);
		saveOperateLog(AssetOperateLogType.project, userVo, oldProject.getId(), "修改项目");

		return new ProjectStatusResponseDTO(errorInfos);
	}

	private void saveProject(String projectId, UserVo userVo, ProjectRequestDTO dto){
		Project project = new Project();
		project.setName(dto.getName());
		project.setType(dto.getType());
		project.setStatus(dto.getStatus());
		project.setCoreCompanyId(dto.getCoreCompanyId());
		project.setCoreCompanyName(dto.getCoreCompanyName());
		project.setCoreOrganizationCode(dto.getCoreOrganizationCode());
		project.setCoreBusinessLicenceCode(dto.getCoreBusinessLicenceCode());
		project.setCoreTaxRegistrationCertificateCode(dto.getCoreTaxRegistrationCertificateCode());
		project.setCoreIndustry(dto.getCoreIndustry());
		project.setCoreEconomicCategory(dto.getCoreEconomicCategory());
		project.setCoreAccountNo(dto.getCoreAccountNo());
		project.setFinanceApplicant(dto.getFinanceApplicant());
		project.setSingleLoanLimitLower(MoneyUtil.yuan2Cent(dto.getSingleLoanLimitLower()));
		project.setSingleLoanLimitUpper(MoneyUtil.yuan2Cent(dto.getSingleLoanLimitUpper()));
		project.setInterestRateUnit(dto.getInterestRateUnit());
		project.setInterestRateLower(MoneyArithUtil.convertStringToInterestRate(dto.getInterestRateLower()));
		project.setInterestRateUpper(MoneyArithUtil.convertStringToInterestRate(dto.getInterestRateUpper()));
		project.setLoanPeriodUnit(dto.getLoanPeriodUnit());
		project.setLoanPeriodLower(dto.getLoanPeriodLower());
		project.setLoanPeriodUpper(dto.getLoanPeriodUpper());
		project.setRepaymentMethods(dto.getRepaymentMethods());
		project.setEarlyRepaymentChargeRatio(MoneyArithUtil.convertStringToInterestRate(dto.getEarlyRepaymentChargeRatio()));
		project.setExtensionDays(dto.getExtensionDays());
		project.setExtensionRatio(MoneyArithUtil.convertStringToInterestRate(dto.getExtensionRatio()));
		project.setExtensionRatioMethod(dto.getExtensionRatioMethod());
		project.setUseHolidayPolicy(dto.isUseHolidayPolicy());
		project.setPenaltyRateMethod(dto.getPenaltyRateMethod());
		project.setPenaltyRate(MoneyArithUtil.convertStringToInterestRate(dto.getPenaltyRate()));
		project.setDeductionTime(dto.getDeductionTime());
		project.setDeductionRule(dto.getDeductionRule());
		project.setUpdateTime(new Date());
		if (StringUtils.isEmpty(dto.getId())) {
			project.setId(projectId);
			project.setCreateTime(project.getUpdateTime());
			project.setCreateUserId(userVo.getUserId());
			project.setCreateCustomerId(userVo.getCustomerId());
			projectMapper.insertSelective(project);
		} else {
			project.setId(dto.getId());
			project.setUseHolidayPolicy(null); // 不可修改是否使用节假日政策
			projectMapper.updateByPrimaryKeySelective(project);
		}
	}

	

	/**
	 * 保存项目操作日志
	 * @param logType 日志类型
	 * @param user 操作人
	 * @param targetId 项目ID
	 * @param content 日志内容
	 */
	private void saveOperateLog(AssetOperateLogType logType, UserVo user, String targetId, String content) {
		AssetOperateLog operateLog = new AssetOperateLog();
		operateLog.setId(IDGenerator.generateAssetOperateLogId());
		operateLog.setLogType(logType.name());
		operateLog.setTargetId(targetId);
		operateLog.setUserId(user.getUserId());
		operateLog.setUsername(user.getUsername());
		operateLog.setRealName(user.getRealname());
		operateLog.setCreateTime(new Date());
		operateLog.setContent(content);
		assetOperateLogMapper.insertSelective(operateLog);
	}
	

	/**
	 * 保存合同
	 * @param projectRequest
	 */
	private void saveProjectDocuments(String projectId, ProjectRequestDTO projectRequest) {
		if (BooleanUtils.isNotTrue(projectRequest.getHavePermissionModifyDocument())) {
			return;
		}
		Set<String> savedDocumentIds = Sets.newHashSet();
		if (CollectionUtils.isNotEmpty(projectRequest.getProjectDocuments())) {
			for (ProjectDocumentDTO docDTO : projectRequest.getProjectDocuments()) {
				ProjectDocument doc = new ProjectDocument();
				doc.setProjectId(projectId);
				doc.setVersion(docDTO.getVersion());
				doc.setName(docDTO.getName());
				doc.setMediaId(docDTO.getMediaId());
				doc.setDeleted(false);
				doc.setUpdateTime(new Date());
				if (StringUtils.isBlank(docDTO.getId())) {
					String documentId = IDGenerator.generateAssetProjectFactorId();
					doc.setId(documentId);
					doc.setCreateTime(doc.getUpdateTime());
					projectDocumentMapper.insertSelective(doc);
				} else {
					doc.setId(docDTO.getId());
					projectDocumentMapper.updateByPrimaryKeySelective(doc);
				}
				savedDocumentIds.add(doc.getId());
			}
		}
		
		ProjectDocumentExample example = new ProjectDocumentExample();
		example.createCriteria().andProjectIdEqualTo(projectId);
		List<ProjectDocument> docs = projectDocumentMapper.selectByExample(example);
		for (ProjectDocument doc : docs) {
			if (!doc.getDeleted() && !savedDocumentIds.contains(doc.getId())) {
				doc.setDeleted(true);
				logger.info("delete project_document: {}", gson.toJson(doc));
				projectDocumentMapper.updateByPrimaryKeySelective(doc);
			}
		}
	}

	/**
	 * 预处理数据库中的资金方
	 */
	private void prepareDBFactorsBeforeUpdate(String projectId, ProjectRequestDTO projectRequest, ProjectStatusEnum dbProjectStatus) {
		if (dbProjectStatus != null) {
			ProjectFactorExample example = new ProjectFactorExample();
			example.createCriteria().andProjectIdEqualTo(projectId);
			example.setOrderByClause("id asc");
			List<ProjectFactor> dbFactors = projectFactorMapper.selectByExample(example);
			
			if (dbProjectStatus == ProjectStatusEnum.CREATE) {
				// 数据库中的资金方为立项时，先删除数据库中的自己房，然后再将页面传过来的资金方ID设null
				for (ProjectFactor dbFactor : dbFactors) {
					logger.info("delete project_factor: {}", gson.toJson(dbFactor));
					projectFactorMapper.deleteByPrimaryKey(dbFactor.getId());
				}
				for (ProjectFactorDTO factorDTO : projectRequest.getProjectFactors()) {
					factorDTO.setId(null);
				}
			} else {
				if (dbFactors.size() > projectRequest.getProjectFactors().size())
					throw WebException.instance("缺失资金方");
				for (int i = 0; i < dbFactors.size(); ++i) {
					ProjectFactor dbFactor = dbFactors.get(i);
					ProjectFactorDTO factorDTO = projectRequest.getProjectFactors().get(i);
					if (!StringUtils.equals(dbFactor.getId(), factorDTO.getId()))
						throw WebException.instance("资金方[" + factorDTO.getCompanyName() + "]的ID不正确");
					if (!StringUtils.equals(dbFactor.getCompanyName(), factorDTO.getCompanyName()))
						throw WebException.instance("资金方[" + factorDTO.getCompanyName() + "]的企业名称不允许修改");
				}
				for (int i = dbFactors.size(); i < projectRequest.getProjectFactors().size(); ++i) {
					projectRequest.getProjectFactors().get(i).setId(null);
				}
			}
		}
	}

	/**
	 * 持久化资金方
	 * @param projectId
	 * @param projectRequest
	 * @param dbProjectStatus，更新的时候传入此值。
	 */
	private void saveProjectFactors(String projectId, ProjectRequestDTO projectRequest) {

		for (ProjectFactorDTO factorDTO : projectRequest.getProjectFactors()) {
			ProjectFactor projectFactor = new ProjectFactor();
			projectFactor.setProjectId(projectId);
			projectFactor.setCompanyId(factorDTO.getCompanyId());
			projectFactor.setCompanyName(factorDTO.getCompanyName());
			projectFactor.setOrganizationCode(factorDTO.getOrganizationCode());
			projectFactor.setBusinessLicenceCode(factorDTO.getBusinessLicenceCode());
			projectFactor.setTaxRegistrationCertificateCode(factorDTO.getTaxRegistrationCertificateCode());
			projectFactor.setAccountNo(factorDTO.getAccountNo());
			projectFactor.setStatus(factorDTO.getStatus());
			projectFactor.setUpdateTime(new Date());
			if (StringUtils.isBlank(factorDTO.getId())) {
				String factorId = IDGenerator.generateAssetProjectFactorId();
				projectFactor.setId(factorId);
				projectFactor.setCreateTime(projectFactor.getUpdateTime());
				projectFactorMapper.insertSelective(projectFactor);
			} else {
				projectFactor.setId(factorDTO.getId());
				projectFactorMapper.updateByPrimaryKeySelective(projectFactor);
			}
		}
	}

	/**
	 * @param projectRequest
	 * @param isUpdate，为true，则更新，false则插入
	 */
	private void saveProjectBlobText(String projectId, ProjectRequestDTO projectRequest, boolean isUpdate) {
		ProjectBlobTextWithBLOBs blobText = new ProjectBlobTextWithBLOBs();
		blobText.setProjectId(projectId);
		blobText.setProductIntroduction(projectRequest.getProductIntroduction());
		blobText.setProductTypeDesc(projectRequest.getProductTypeDesc());
		blobText.setUpdateTime(new Date());
		if (isUpdate) {
			projectBlobTextMapper.updateByPrimaryKeySelective(blobText);
		} else {
			blobText.setCreateTime(blobText.getUpdateTime());
			projectBlobTextMapper.insertSelective(blobText);
		}
	}
}
