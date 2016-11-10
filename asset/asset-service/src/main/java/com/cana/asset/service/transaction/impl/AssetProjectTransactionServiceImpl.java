package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetOperateLogMapper;
import com.cana.asset.dao.mapper.gen.ProjectBlobTextMapper;
import com.cana.asset.dao.mapper.gen.ProjectDocumentMapper;
import com.cana.asset.dao.mapper.gen.ProjectFactorMapper;
import com.cana.asset.dao.mapper.gen.ProjectMapper;
import com.cana.asset.dao.po.Project;
import com.cana.asset.dao.po.ProjectBlobTextWithBLOBs;
import com.cana.asset.dao.po.ProjectDocument;
import com.cana.asset.dao.po.ProjectDocumentExample;
import com.cana.asset.dao.po.ProjectExample;
import com.cana.asset.dao.po.ProjectFactor;
import com.cana.asset.dao.po.ProjectFactorExample;
import com.cana.asset.service.convertors.ProjectConvertor;
import com.cana.asset.service.transaction.IAssetProjectTransactionService;
import com.cana.vbam.common.asset.dto.FactorInfo;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.asset.dto.ProjectListRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.asset.enums.ProjectFactorStatusEnum;
import com.cana.vbam.common.asset.enums.ProjectStatusEnum;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * 项目管理接口实现类
 * 
 * @author jiangzhou.Ren
 * @time 2016年5月18日上午9:57:02
 */
@Service
public class AssetProjectTransactionServiceImpl implements IAssetProjectTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
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

	// 查询项目列表实现类 
	@Override
	public ListResult<ProjectListResponseDTO> getProjectList(UserVo userDetail, ProjectListRequestDTO request) {
		if (userDetail == null) {
			throw WebException.instance("用户不存在");
		}
		ProjectExample example = new ProjectExample();
		ProjectExample.Criteria criteria = example.createCriteria();
		if (userDetail.getCustomer().getUserType().equals(UserType.CORECOMPANY)) {
			criteria.andCoreCompanyIdEqualTo(userDetail.getCustomer().getCustomerId());
		} else if (userDetail.getCustomer().getUserType().equals(UserType.FACTOR)) {
			List<String> projectIds = getProjectIdsBy(userDetail);
			if(CollectionUtils.isEmpty(projectIds))
				return ListResult.success(new ArrayList<ProjectListResponseDTO>(), 0);
			else criteria.andIdIn(projectIds);			
		} else if (userDetail.getCustomer().getUserType().equals(UserType.FINACE)) {
			throw WebException.instance("当前用户无权限访问");
		}
		// 判断查询条件和sql条件拼接
		if (StringUtils.isNotBlank(request.getName())) {//项目名字
			criteria.andNameLike("%" + request.getName().trim() + "%");
		}
		if (StringUtils.isNotBlank(request.getCoreCompanyName())) {//核心企业
			criteria.andCoreCompanyNameLike("%" + request.getCoreCompanyName().trim() + "%");
		}
		if (StringUtils.isNotBlank(request.getType())) {//业务品种
			criteria.andTypeEqualTo(request.getType());
		}
		if (StringUtils.isNotBlank(request.getCoreIndustry())) {//所处行业
			criteria.andCoreIndustryEqualTo(request.getCoreIndustry());
		}
		example.setOrderByClause("create_time desc");
		// 分页参数
		example.setLimitStart((request.getPage() - 1) * request.getPageSize());
		example.setLimitEnd(request.getPageSize());
		List<Project> projects = projectMapper.selectByExample(example);
		int count = projectMapper.countByExample(example);
		List<ProjectListResponseDTO> ProjectDTOs = ProjectConvertor.convertProjectDao2ResDTO(projects);
		return ListResult.success(ProjectDTOs, count);
	}

	/**
	 * 查询跟传入资金方关联的项目id列表
	 * @param factorUserDetail 资金方角色的用户
	 * @param criteria
	 */
	public List<String> getProjectIdsBy(UserVo factorUserDetail) {
		ProjectFactorExample factorExample = new ProjectFactorExample();
		// 根据资金方id查询项目id
		factorExample.createCriteria().andCompanyIdEqualTo(factorUserDetail.getCustomer().getCustomerId());
		List<ProjectFactor> lists = projectFactorMapper.selectByExample(factorExample);
		List<String> ids = Lists.newArrayList();
		for (ProjectFactor projectFactor : lists) {
			ids.add(projectFactor.getProjectId());
		}
		return ids;
	}
	
	// 查询项目详情实现类
	@Override
	public ProjectDTO getProjectDetail(UserVo userDetail, String projectId) {
		// 判断传递参数是否为空
		checkGetProjectDetailRequest(userDetail, projectId);

		// 权限校验
		if (userDetail.getCustomer().getUserType().equals(UserType.CANA)) {
			// 返回查询封装project、factor 、document、blobText
			ProjectDTO projectDTO = queryProjectAndFactorAndDocument(projectId);
			return projectDTO;
		} else if (userDetail.getCustomer().getUserType().equals(UserType.FACTOR)) {
			// 查询项目关联的资金方id
			ProjectFactorExample factorExample = new ProjectFactorExample();
			factorExample.createCriteria().andProjectIdEqualTo(projectId);
			List<ProjectFactor> projectFactors = projectFactorMapper.selectByExample(factorExample);
			for (ProjectFactor projectFactor : projectFactors) {
				projectFactor.getProjectId();
				//资金方企业id和当前登录customerId()作比较
				if (StringUtils.equals(projectFactor. getCompanyId(), userDetail.getCustomerId())) {
					ProjectDTO projectDTO = queryProjectAndFactorAndDocument(projectId);
					return projectDTO;
				}
			}
		}
		return null;
	}

	/**
	 * 查询项目、资金方、合同、产品简介和类型描述
	 * @param projectId
	 * @return
	 */
	private ProjectDTO queryProjectAndFactorAndDocument(String projectId) {
		//查询项目
		Project project = projectMapper.selectByPrimaryKey(projectId);
		// 查询资金方
		ProjectFactorExample factorExample = new ProjectFactorExample();
		factorExample.createCriteria().andProjectIdEqualTo(projectId);
		List<ProjectFactor> projectFactors = projectFactorMapper.selectByExample(factorExample);
		// 查询合同
		ProjectDocumentExample documentExample = new ProjectDocumentExample();
		//添加合同查询projectId和合同是否删除字段（deleted=false）条件
		documentExample.createCriteria().andProjectIdEqualTo(projectId).andDeletedEqualTo(false);
		List<ProjectDocument> projectDocuments = projectDocumentMapper.selectByExample(documentExample);
		//查询项目产品介绍和产品类型描述
		ProjectBlobTextWithBLOBs blobText = projectBlobTextMapper.selectByPrimaryKey(projectId);
		 ProjectDTO projectDTO = ProjectConvertor.convertToProjectDTO(project,projectFactors,projectDocuments,blobText);
		return projectDTO;
	}


	/**
	 * 校验获取项目详情请求参数的合法性
	 * @param userDetail
	 * @param projectId
	 * @throws WebException
	 */
	private void checkGetProjectDetailRequest(UserVo userDetail, String projectId) throws WebException {
		//校验请求参数
		if(userDetail == null){
			throw WebException.instance("获取不到当前登陆用户的信息");
		}
		if (StringUtils.isBlank(userDetail.getUserId())) {
			throw WebException.instance("用户UserId为空");
		}
		if (StringUtils.isBlank(userDetail.getCustomerId())){
			throw WebException.instance("CustomerId为空");
		}
		if (StringUtils.isBlank(projectId)) {
			throw WebException.instance("项目projectId为空");
		}
		if(!Arrays.asList(UserType.CANA, UserType.FACTOR).contains(userDetail.getCustomer().getUserType())){
			throw WebException.instance("无访问项目详情的权限");
		}
	}
	/**
	 * projectId
	 * 根据项目id查询项目详情
	 * 不需要权限去查询项目详情
	 */
	public ProjectDTO getProjectDetail(String projectId) {
		if (StringUtils.isNotBlank(projectId)) {
			ProjectDTO projectDTO = queryProjectAndFactorAndDocument(projectId);
			return projectDTO;
		} else {
			throw WebException.instance("项目projectId为空");
		}
	}

	@Override
	public ProjectInfo getProjectInfo(String projectId) {
		//查询项目
		Project project = projectMapper.selectByPrimaryKey(projectId);
		// 查询资金方
		ProjectFactorExample factorExample = new ProjectFactorExample();
		factorExample.createCriteria().andProjectIdEqualTo(projectId);
		List<ProjectFactor> projectFactors = projectFactorMapper.selectByExample(factorExample);
		return convertToProjectInfo(project, projectFactors);
	}

	private ProjectInfo convertToProjectInfo(Project project, List<ProjectFactor> projectFactors) {
		List<FactorInfo> factorInfos = new ArrayList<>();
		for(ProjectFactor factor : projectFactors){
			FactorInfo factorInfo = new FactorInfo();
			factorInfo.setCompanyId(factor.getCompanyId());
			factorInfo.setCompanyName(factor.getCompanyName());
			factorInfo.setAccountNo(factor.getAccountNo());
			factorInfo.setStatus(ProjectFactorStatusEnum.valueOf(factor.getStatus()));
			factorInfos.add(factorInfo);
		}
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setFactors(factorInfos);
		projectInfo.setId(project.getId());
		projectInfo.setProjectName(project.getName());
		projectInfo.setStatus(ProjectStatusEnum.valueOf(project.getStatus()));
		projectInfo.setCoreCompanyId(project.getCoreCompanyId());
		projectInfo.setCoreCompanyName(project.getCoreCompanyName());
		projectInfo.setCoreAccountNo(project.getCoreAccountNo());
		projectInfo.setLoanPeriodUnit(DateUnit.valueOf(project.getLoanPeriodUnit()));
		projectInfo.setLoanPeriodLower(project.getLoanPeriodLower());
		projectInfo.setLoanPeriodUpper(project.getLoanPeriodUpper());
		projectInfo.setInterestRateUnit(InterestRateUnit.valueOf(project.getInterestRateUnit()));
		projectInfo.setInterestRateLower(project.getInterestRateLower());
		projectInfo.setInterestRateUpper(project.getInterestRateUpper());
		
		List<RepaymentType> repaymentTypes = new ArrayList<>(); 
		for(String repaymentTypeStr : project.getRepaymentMethods().split(",")){
			repaymentTypes.add(RepaymentType.valueOf(StringUtils.trimToEmpty(repaymentTypeStr)));
		}
		projectInfo.setRepaymentTypes(repaymentTypes);
		
		projectInfo.setEarlyRepaymentChargeRatio(project.getEarlyRepaymentChargeRatio());
		projectInfo.setPenaltyRatio(project.getPenaltyRate());
		projectInfo.setPenaltyChargeMethod(ChargeMethod.valueOf(project.getPenaltyRateMethod()));
		projectInfo.setExtensionRatio(project.getExtensionRatio());
		projectInfo.setExtensionChargeMethod(ChargeMethod.valueOf(project.getExtensionRatioMethod()));
		projectInfo.setExtensionDays(project.getExtensionDays());
		projectInfo.setUseHolidayPolicy(BooleanUtils.isTrue(project.getUseHolidayPolicy()));
		projectInfo.setDeductionRule(DeductionRule.valueOf(project.getDeductionRule()));
		projectInfo.setDeductionTime(project.getDeductionTime());
		
		return projectInfo; 
	}

	@Override
	public List<String> getUseHolidayPolicyProjectIds() {
		ProjectExample example = new ProjectExample();
		example.createCriteria().andUseHolidayPolicyEqualTo(true);
		List<Project> projects = projectMapper.selectByExample(example);
		List<String> projectIds = Lists.newArrayList();
		for (Project project : projects) {
			projectIds.add(project.getId());
		}
		return projectIds;
	}
}
