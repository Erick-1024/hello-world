package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.OriginatorMapper;
import com.cana.asset.dao.mapper.gen.ServiceAgencyMapper;
import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.po.Originator;
import com.cana.asset.dao.po.OriginatorExample;
import com.cana.asset.dao.po.ServiceAgency;
import com.cana.asset.dao.po.ServiceAgencyExample;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramExample;
import com.cana.asset.dao.po.SpecialProgramExample.Criteria;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.asset.service.convertors.SpecialProgramCovert;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IABSSpecialProgramTransactionService;
import com.cana.asset.service.transaction.ISpecialProgramLogTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.asset.service.transaction.util.AssetSpecialProgramPersistenceValidator;
import com.cana.vbam.common.asset.dto.OriginatorDTO;
import com.cana.vbam.common.asset.dto.ServiceAgencyDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramIssueListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestIssueDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramRequestDTO;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.consts.MemberConstants;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.StringUtil;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月30日上午10:13:57
 */
@Service
public class ABSSpecialProgramTransactionServiceImpl implements IABSSpecialProgramTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private SpecialProgramMapper specialProgramMapper;

	@Resource
	private ServiceAgencyMapper serviceAgencyMapper;

	@Resource
	private OriginatorMapper originatorMapper;

	@Resource
	private IABSDataPrivilegeTransactionService aBSDataPrivilegeTransactionService;
	
	@Resource
	private ISpecialProgramLogTransactionService specialProgramLogTransactionService;
	
	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;
	
	@Resource
	private SpecialProgramCovert specialProgramCovert;
	
	/**
	 * 专项计划列表
	 */
	@Override
	public ListResult<SpecialProgramListDTO> querySpecialProgramList(SpecialProgramListRequestDTO request,
			UserVo userDetail) {

		StringUtil.trimObjectFields(request);
		// 判断那种类型用户可以查询到专项计划列表
		Set<String> idList = aBSDataPrivilegeTransactionService.allowedProgramIdList(
				userDetail.getCustomer().getUserType(), userDetail.getCustomer().getCustomerName());
		if (CollectionUtils.isEmpty(idList)) {
			return ListResult.success(new ArrayList<SpecialProgramListDTO>(), 0);
		}
	
		SpecialProgramExample specialProgramExample = new SpecialProgramExample();
		SpecialProgramExample.Criteria criteria = specialProgramExample.createCriteria();
		convertProgramExampleCriteria(request, idList, criteria);

		// 基础资产列表页入池时查询，需要排除已关闭的、成立状态下非循环购买的
		if (request.isForUnderlyingAssetEnter()) {
			criteria.andStatusIn(Lists.newArrayList(
					SpecialProgramStatus.CREATE.name(),
					SpecialProgramStatus.PACKAGE.name(),
					SpecialProgramStatus.ISSUE.name()
					));

			SpecialProgramExample.Criteria criteria2 = specialProgramExample.or();
			convertProgramExampleCriteria(request, idList, criteria2);
			criteria2.andStatusEqualTo(SpecialProgramStatus.ESTABLISH.name())
				.andCyclePurchaseStructureEqualTo(true);
		}

		specialProgramExample.setOrderByClause("create_time desc");
		specialProgramExample.setLimitStart((request.getPage() - 1) * request.getPageSize());
		specialProgramExample.setLimitEnd(request.getPageSize());
		logger.info("开始查询专项计划列表");
		List<SpecialProgram> specialPrograms = specialProgramMapper.selectByExample(specialProgramExample);
		int count = specialProgramMapper.countByExample(specialProgramExample);
		List<SpecialProgramListDTO> specialProgramDTOs = specialProgramCovert.
				covertSpecialProgramDao2ResDTO(specialPrograms,userDetail);
		return ListResult.success(specialProgramDTOs, count);

	}

	private void convertProgramExampleCriteria(SpecialProgramListRequestDTO request, Set<String> allowedProgramIds,
			SpecialProgramExample.Criteria criteria) {

		List<String> Ids = new ArrayList<String>(allowedProgramIds);
		criteria.andIdIn(Ids);
		// 搜索条件
		if (StringUtils.isNoneBlank(request.getSpecialProgramName())) {
			criteria.andSpecialProgramNameLike("%" + request.getSpecialProgramName() + "%");
		}
		if (StringUtils.isNoneBlank(request.getUnderlyingAssetType())) {
			criteria.andUnderlyingAssetTypeEqualTo(request.getUnderlyingAssetType());
		}
		if (StringUtils.isNoneBlank(request.getManagerName())) {
			criteria.andManagerNameLike("%" + request.getManagerName() + "%");
		}
		if (StringUtils.isNoneBlank(request.getStartEstimateEstablishmentDate())) {
			criteria.andEstimateEstablishmentDateGreaterThanOrEqualTo(request.getStartEstimateEstablishmentDate());
		}
		if (StringUtils.isNotBlank(request.getEndEstimateEstablishmentDate())) {
			criteria.andEstimateEstablishmentDateLessThanOrEqualTo(request.getEndEstimateEstablishmentDate());
		}
		if (null != request.getStatus()) {
			criteria.andStatusEqualTo(request.getStatus().name());
		}
		criteria.andDeletedEqualTo(false);
	}

	/**
	 * 专项计划详情
	 */
	@Override
	public SpecialProgramDTO getSpecialProgramById(String specialProgramId, UserVo userDetail) {
		checkGetSpecialProgramDetail(specialProgramId, userDetail);
		// 判断那种用户可以查询专项计划列表TD
		boolean allow = aBSDataPrivilegeTransactionService.allow(userDetail.getCustomer().getUserType(),
				userDetail.getCustomer().getCustomerName(), specialProgramId);
		if (allow != true) {
			throw WebException.instance("没有权限查询专项计划详情");
		}
		// 查询专项计划表
		SpecialProgram specialProgram = specialProgramMapper.selectByPrimaryKey(specialProgramId);
		if (specialProgram.getDeleted() == true) {
			return null;
		}

		// 查询原始权益人
		OriginatorExample originatorExample = new OriginatorExample();
		originatorExample.createCriteria().andSpecialProgramIdEqualTo(specialProgramId);
		List<Originator> originators = originatorMapper.selectByExample(originatorExample);
		// 查询资产服务机构
		ServiceAgencyExample serviceAgencyExample = new ServiceAgencyExample();
		serviceAgencyExample.createCriteria().andSpecialProgramIdEqualTo(specialProgramId);
		List<ServiceAgency> serviceAgencys = serviceAgencyMapper.selectByExample(serviceAgencyExample);
		SpecialProgramDTO specialProgramDTO = specialProgramCovert.covertorToSpecialProgramDTO(specialProgram,
				originators, serviceAgencys);
		logger.info("查询专项计划详情");
		return specialProgramDTO;
	}

	/**
	 * 新增专项计划
	 */
	@Override
	public void addSpecialProgram(SpecialProgramRequestDTO request, UserVo userDetail) {
		StringUtil.trimObjectFields(request);
		AssetSpecialProgramPersistenceValidator.checkSpecialProgramInfoFieldsIsValid(request, userDetail);
		SpecialProgram specialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
		if (specialProgram != null) {
			throw WebException.instance("专项计划编号已存在");
		}
		saveAddSpecialProgram(request, userDetail);
		saveServiceAgency(request, request.getId());
		saveOriginator(request, request.getId());
		logger.info("新增专项计划");
	}

	/**
	 * 修改专项计划
	 */
	@Override
	public void updateSpecialProgram(SpecialProgramRequestDTO request, UserVo userDetail) {
		StringUtil.trimObjectFields(request);
		AssetSpecialProgramPersistenceValidator.checkSpecialProgramInfoFieldsIsValid(request, userDetail);
		SpecialProgram specialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
		// 状态转换
		specialProgramStatusCheck(request, specialProgram);
		// 判断修改权限只能是当前管理人才能修改
		if (!userDetail.getCustomer().getCustomerName().equals(specialProgram.getManagerName())) {
			throw WebException.instance("只有管理人才能修改专项计划");
		}
		SpecialProgram oldSpecialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
		if (oldSpecialProgram == null) {
			throw WebException.instance("专项计划编号不存在");
		}
		deleteOriginatorAndServiceAgencyData(request);
		saveUpdateSpecialProgram(request, userDetail);
		saveServiceAgency(request, oldSpecialProgram.getId());
		saveOriginator(request, oldSpecialProgram.getId());
		logger.info("修改专项计划");
	}

	/**
	 * 修改时候状态转移判断
	 * @param request
	 * @param specialProgram
	 * @throws WebException
	 */
	private void specialProgramStatusCheck(SpecialProgramRequestDTO request, SpecialProgram specialProgram)
			throws WebException {
		if (specialProgram.getStatus().equals("CREATE")) {
			if (!request.getStatus().name().equals("CREATE")) {
				throw WebException.instance("立项状态不能变更为其它状态");
			}
		} else if (specialProgram.getStatus().equals("PACKAGE")) {
			if (!request.getStatus().name().equals("CREATE") && !request.getStatus().name().equals("PACKAGE") && !request.getStatus().name().equals("ISSUE")) {
				throw WebException.instance("封包状态状态只能变更为立项或发行状态");
			}
		} else if (specialProgram.getStatus().equals("ISSUE")) {
			if (!request.getStatus().name().equals("PACKAGE") && !request.getStatus().name().equals("ISSUE")) {
				throw WebException.instance("发行状态状态只能变更为封包状态");
			}
		} else if (specialProgram.getStatus().equals("CLOSE")) {
			if (!request.getStatus().name().equals("CLOSE")) {
				throw WebException.instance("结束状态不能变更为其它状态");
			}
		} else if (specialProgram.getStatus().equals("ESTABLISH")) {
			if (!request.getStatus().name().equals("PACKAGE") && !request.getStatus().name().equals("ISSUE")
					&& !request.getStatus().name().equals("ESTABLISH")) {
				throw WebException.instance("成立状态只能变更为封包或发行状态");
			}
		}
	}

	/**
	 * 删除专项计划
	 */
	@Override
	public void deleteSpecialProgramById(String specialProgramId, UserVo userDetail) {
		checkGetSpecialProgramDetail(specialProgramId, userDetail);
		//删除专项计划判断资产池是否可以被删除
		   underlyingAssetTransactionService.checkAndUnbindForDeleteProgram(userDetail, specialProgramId);
		// 判断是那种客户类型可以删除todo
		SpecialProgram oldSpecialProgram = specialProgramMapper.selectByPrimaryKey(specialProgramId);
		
		if(!oldSpecialProgram.getStatus().equals("CREATE")){
			throw WebException.instance("只有立项状态才能删除专项计划");
		}
		if (!userDetail.getCustomer().getCustomerName().equals(oldSpecialProgram.getManagerName())) {
			throw WebException.instance("只有管理人才能删除专项计划");
		}
		try {
			// 删除专项计划
			SpecialProgram specialProgram = new SpecialProgram();
			specialProgram.setId(specialProgramId);
			specialProgram.setDeleted(true);
			specialProgramMapper.updateByPrimaryKeySelective(specialProgram);
			// 删除资产服务机构数据
			ServiceAgencyExample serviceAgencyExample = new ServiceAgencyExample();
			serviceAgencyExample.createCriteria().andSpecialProgramIdEqualTo(specialProgramId);
			serviceAgencyMapper.deleteByExample(serviceAgencyExample);
			// 删除原始权益人数据
			OriginatorExample originatorExample = new OriginatorExample();
			originatorExample.createCriteria().andSpecialProgramIdEqualTo(specialProgramId);
			originatorMapper.deleteByExample(originatorExample);
		} catch (Exception e) {
			logger.error("专项计划删除错误", e.getMessage());
			e.printStackTrace();
		}
		logger.info("删除专项计划");
	}

	/**
	 * 新增保存专项计划数据
	 * 
	 * @param request
	 */
	private void saveAddSpecialProgram(SpecialProgramRequestDTO request, UserVo userDetail) {
		SpecialProgram specialProgram = new SpecialProgram();
		specialProgram.setId(request.getId());
		specialProgram.setSpecialProgramName(request.getSpecialProgramName());
		specialProgram.setUnderlyingAssetType(request.getUnderlyingAssetType());
		specialProgram.setManagerName(request.getManagerName());
		specialProgram.setManagerId(request.getManagerId());
		specialProgram.setLawFirmName(request.getLawFirmName());
		specialProgram.setAccountingFirmName(request.getAccountingFirmName());
		specialProgram.setSupervisionBank(request.getSupervisionBank());
		specialProgram.setRatingAgency(request.getRatingAgency());
		specialProgram.setAssetEvaluationAgency(request.getAssetEvaluationAgency());
		specialProgram.setEstimateEstablishmentDate(request.getEstimateEstablishmentDate());
		specialProgram.setEstablishmentDate(request.getEstablishmentDate());
		specialProgram.setEstimateDueDate(request.getEstimateDueDate());
		specialProgram.setStatutoryDueDate(request.getStatutoryDueDate());
		specialProgram.setStatus((request.getStatus()).name());
		specialProgram.setRenewalPeriod(request.getRenewalPeriod());
		specialProgram.setGross(MoneyArithUtil.convertStringToMoney(request.getGross()));
		specialProgram.setPreferA(MoneyArithUtil.convertStringToMoney(request.getPreferA()));
		specialProgram.setPreferB(MoneyArithUtil.convertStringToMoney(request.getPreferB()));
		specialProgram.setDefer(MoneyArithUtil.convertStringToMoney(request.getDefer()));
		specialProgram.setTrustMethod(request.getTrustMethod());
		specialProgram.setAssetPoolPrincipalBalance(MoneyArithUtil.convertStringToMoney(request.getAssetPoolPrincipalBalance()));
		specialProgram.setContractNum(request.getContractNum());
		specialProgram.setWeightedAverageContractPeriod(request.getWeightedAverageContractPeriod());
		specialProgram.setFinanceNum(request.getFinanceNum());
		specialProgram.setWeightedAverageInterestRate(request.getWeightedAverageInterestRate());
		specialProgram.setCyclePurchaseStructure(request.getCyclePurchaseStructure());
		specialProgram.setCreaterId(userDetail.getUserId());
		specialProgram.setCreaterName(userDetail.getUsername());
		specialProgram.setCustodianBank(request.getCustodianBank());
		specialProgram.setCreateTime(request.getCreateTime());
		specialProgram.setUpdateTime(request.getUpdateTime());

		specialProgram.setCyclePeriod(request.getCyclePeriod());
		specialProgram.setCapitalAccumulationDate(request.getCapitalAccumulationDate());
		specialProgram.setCyclePurchaseDate(request.getCyclePurchaseDate());
		specialProgram.setIncomeRecoveryDate(request.getIncomeRecoveryDate());
		specialProgram.setRecyclingPaymentDate(request.getRecyclingPaymentDate());
		specialProgram.setExcessFund(MoneyArithUtil.convertStringToMoney(request.getExcessFund()));
		specialProgram.setCyclePurchaseTerm(request.getCyclePurchaseTerm());
		specialProgram.setUnderlyingAssetQualityStandard(request.getUnderlyingAssetQualityStandard());
		specialProgram.setCycleWeightedAverageInterestRate(request.getCycleWeightedAverageInterestRate());

		specialProgramMapper.insertSelective(specialProgram);
		//添加操作日志
		specialProgramLogTransactionService.insertSpecialProgramLog(specialProgram, 0l, request.getStatus(), userDetail);
		logger.info("专项计划基本信息入库");
	}

	/**
	 * 修改专项计划数据
	 * 
	 * @param request
	 */
	private void saveUpdateSpecialProgram(SpecialProgramRequestDTO request, UserVo userDetail) {
		SpecialProgram specialProgramTo = new SpecialProgram();
		specialProgramTo.setId(request.getId());
		specialProgramTo.setSpecialProgramName(request.getSpecialProgramName());
		specialProgramTo.setUnderlyingAssetType(request.getUnderlyingAssetType());
		specialProgramTo.setManagerName(request.getManagerName());
		specialProgramTo.setManagerId(request.getManagerId());
		specialProgramTo.setLawFirmName(request.getLawFirmName());
		specialProgramTo.setAccountingFirmName(request.getAccountingFirmName());
		specialProgramTo.setSupervisionBank(request.getSupervisionBank());
		specialProgramTo.setRatingAgency(request.getRatingAgency());
		specialProgramTo.setAssetEvaluationAgency(request.getAssetEvaluationAgency());
		specialProgramTo.setEstimateEstablishmentDate(request.getEstimateEstablishmentDate());
		specialProgramTo.setEstablishmentDate(request.getEstablishmentDate());
		specialProgramTo.setEstimateDueDate(request.getEstimateDueDate());
		specialProgramTo.setStatutoryDueDate(request.getStatutoryDueDate());
		specialProgramTo.setStatus((request.getStatus()).name());
		specialProgramTo.setRenewalPeriod(request.getRenewalPeriod());
		specialProgramTo.setGross(MoneyArithUtil.convertStringToMoney(request.getGross()));
		specialProgramTo.setPreferA(MoneyArithUtil.convertStringToMoney(request.getPreferA()));
		specialProgramTo.setPreferB(MoneyArithUtil.convertStringToMoney(request.getPreferB()));
		specialProgramTo.setDefer(MoneyArithUtil.convertStringToMoney(request.getDefer()));
		specialProgramTo.setTrustMethod(request.getTrustMethod());
		specialProgramTo.setAssetPoolPrincipalBalance(MoneyArithUtil.convertStringToMoney(request.getAssetPoolPrincipalBalance()));
		specialProgramTo.setContractNum(request.getContractNum());
		specialProgramTo.setWeightedAverageContractPeriod(request.getWeightedAverageContractPeriod());
		specialProgramTo.setFinanceNum(request.getFinanceNum());
		specialProgramTo.setWeightedAverageInterestRate(request.getWeightedAverageInterestRate());
		specialProgramTo.setCyclePurchaseStructure(request.getCyclePurchaseStructure());
		specialProgramTo.setCreaterId(userDetail.getUserId());
		specialProgramTo.setCreaterName(userDetail.getUsername());
		specialProgramTo.setCustodianBank(request.getCustodianBank());
		specialProgramTo.setCreateTime(request.getCreateTime());
		specialProgramTo.setUpdateTime(request.getUpdateTime());

		specialProgramTo.setCyclePeriod(request.getCyclePeriod());
		specialProgramTo.setCapitalAccumulationDate(request.getCapitalAccumulationDate());
		specialProgramTo.setCyclePurchaseDate(request.getCyclePurchaseDate());
		specialProgramTo.setIncomeRecoveryDate(request.getIncomeRecoveryDate());
		specialProgramTo.setRecyclingPaymentDate(request.getRecyclingPaymentDate());
		specialProgramTo.setExcessFund(MoneyArithUtil.convertStringToMoney(request.getExcessFund()));
		specialProgramTo.setCyclePurchaseTerm(request.getCyclePurchaseTerm());
		specialProgramTo.setUnderlyingAssetQualityStandard(request.getUnderlyingAssetQualityStandard());
		specialProgramTo.setCycleWeightedAverageInterestRate(request.getCycleWeightedAverageInterestRate());

		specialProgramMapper.updateByPrimaryKeySelective(specialProgramTo);
		specialProgramLogTransactionService.insertSpecialProgramLog(specialProgramTo, 0l, request.getStatus(), userDetail);
		logger.info("修改专项计划数据");
	}

	/**
	 * 保存资产服务机构
	 * 
	 * @param request
	 */
	private void saveServiceAgency(SpecialProgramRequestDTO request, String specialProgramId) {
		if (CollectionUtils.isNotEmpty(request.getServiceAgencys())) {
			for (ServiceAgencyDTO serviceAgencyDTO : request.getServiceAgencys()) {
				ServiceAgency serviceAgency = new ServiceAgency();
				serviceAgency.setId(serviceAgencyDTO.getId());
				serviceAgency.setSpecialProgramId(request.getId());
				serviceAgency.setServiceAgencyName(serviceAgencyDTO.getServiceAgencyName());
				serviceAgency.setCreateTime(serviceAgencyDTO.getCreateTime());
				serviceAgency.setUpateTime(serviceAgencyDTO.getUpateTime());
				if (StringUtils.isBlank(serviceAgencyDTO.getId())) {
					String serviceAgencyId = IDGenerator.generateAssetServiceAgencyId();
					serviceAgency.setId(serviceAgencyId);
				} else {
					serviceAgency.setId(serviceAgencyDTO.getId());
				}
				serviceAgencyMapper.insertSelective(serviceAgency);
			}
		}
		logger.info("资产服务机构数据保存到数据库");
	}

	/**
	 * 保存原始权益人
	 * 
	 * @param request
	 */
	private void saveOriginator(SpecialProgramRequestDTO request, String specialProgramId) {
		if (CollectionUtils.isNotEmpty(request.getOriginators())) {
			for (OriginatorDTO originatorDTO : request.getOriginators()) {
				Originator originator = new Originator();
				originator.setId(originatorDTO.getId());
				originator.setOriginatorName(originatorDTO.getOriginatorName());
				originator.setSpecialProgramId(request.getId());
				originator.setCreateTime(originatorDTO.getCreateTime());
				originator.setUpateTime(originatorDTO.getUpateTime());
				if (StringUtils.isBlank(originatorDTO.getId())) {
					String originatorId = IDGenerator.generateAssetOriginatorId();
					originator.setId(originatorId);
				} else {
					originator.setId(originatorDTO.getId());
				}
				originatorMapper.insertSelective(originator);
			}
		}
		logger.info("原始权益人数据保存");
	}

	/**
	 * 删除原始权益人和资产服务机构数据
	 */
	private void deleteOriginatorAndServiceAgencyData(SpecialProgramRequestDTO request) {
		OriginatorExample originatorExample = new OriginatorExample();
		originatorExample.createCriteria().andSpecialProgramIdEqualTo(request.getId());
		originatorMapper.deleteByExample(originatorExample);
		ServiceAgencyExample serviceAgencyExample = new ServiceAgencyExample();
		serviceAgencyExample.createCriteria().andSpecialProgramIdEqualTo(request.getId());
		serviceAgencyMapper.deleteByExample(serviceAgencyExample);
		logger.info("删除原始权益人和资产服务机构数据");
	}

	/**
	 * 检查查询专项计划id和用户是否为空
	 * 
	 * @param id
	 * @param userDetail
	 * @throws WebException
	 */
	private void checkGetSpecialProgramDetail(String id, UserVo userDetail) throws WebException {
		if (StringUtils.isBlank(id)) {
			throw WebException.instance("专项计划id为空");
		}
		if (userDetail == null) {
			throw WebException.instance("userDetail为空");
		}
		logger.info("检查查询专项计划id和用户是否为空");
	}

	@Override
	public PageList<SpecialProgram> getSpecialProgramList(SpecialProgramQueryDTO specialProgramQueryDTO,
			UserVo userVo) {
		PageList<SpecialProgram> result = new PageList<>();
		SpecialProgramExample specialProgramExample = new SpecialProgramExample();
		Criteria criteria = specialProgramExample.createCriteria();
		// 资产池列表数据权限
		Set<String> idList = aBSDataPrivilegeTransactionService.allowedProgramIdList(userVo.getCustomer().getUserType(),
				userVo.getCustomer().getCustomerName());
		if (CollectionUtils.isNotEmpty(idList)) {
			List<String> Ids = Lists.newArrayList(idList);
			criteria.andIdIn(Ids);
		} else {
			return result;
		}
		specialProgramCondition(specialProgramQueryDTO, criteria);
		specialProgramExample.setOrderByClause("create_time desc");
		specialProgramExample
				.setLimitStart((specialProgramQueryDTO.getPage() - 1) * specialProgramQueryDTO.getPageSize());
		specialProgramExample.setLimitEnd(specialProgramQueryDTO.getPageSize());
		List<SpecialProgram> specialPrograms = specialProgramMapper.selectByExample(specialProgramExample);
		result.setRecords(specialPrograms);
		result.setTotalRecords(specialProgramMapper.countByExample(specialProgramExample));
		return result;
	}

	/**
	 * 搜索条件
	 * 
	 * @param queryDTO
	 * @param criteria
	 */
	private void specialProgramCondition(SpecialProgramQueryDTO queryDTO, Criteria criteria) {
		if (StringUtils.isNotBlank(queryDTO.getSpecialProgramName())) {
			criteria.andSpecialProgramNameLike("%" + queryDTO.getSpecialProgramName().trim() + "%");
		}
		if (StringUtils.isNotBlank(queryDTO.getUnderlyingAssetType())) {
			criteria.andUnderlyingAssetTypeEqualTo(queryDTO.getUnderlyingAssetType());
		}
		if (StringUtils.isNotBlank(queryDTO.getManagerName())) {
			criteria.andManagerNameLike("%" + queryDTO.getManagerName().trim() + "%");
		}
		if (StringUtils.isNotBlank(queryDTO.getStartDate())) {
			criteria.andEstimateEstablishmentDateGreaterThanOrEqualTo(queryDTO.getStartDate());
		}
		if (StringUtils.isNotBlank(queryDTO.getEndDate())) {
			criteria.andEstimateEstablishmentDateLessThanOrEqualTo(queryDTO.getEndDate());
		}
		if (StringUtils.isNotBlank(queryDTO.getStatus()))
			criteria.andStatusEqualTo(queryDTO.getStatus());
		criteria.andDeletedEqualTo(false);
	}

	/**
	 * 查询发行后专项计划列表
	 */
	@Override
	public ListResult<SpecialProgramIssueListDTO> querySpecialProgramIssueList(
			SpecialProgramListRequestIssueDTO request, UserVo userDetail) {
		StringUtil.trimObjectFields(request);
		SpecialProgramExample specialProgramExample = new SpecialProgramExample();
		SpecialProgramExample.Criteria criteria = specialProgramExample.createCriteria();
		// 判断那种类型用户可以查询到专项计划列表
		Set<String> idList = aBSDataPrivilegeTransactionService.allowedProgramIdList(
				userDetail.getCustomer().getUserType(), userDetail.getCustomer().getCustomerName());
		if (CollectionUtils.isNotEmpty(idList)) {
			List<String> Ids = new ArrayList<String>(idList);
			criteria.andIdIn(Ids);
		} else {
			return ListResult.success(new ArrayList<SpecialProgramIssueListDTO>(), 0);
		}
		queryCriteria(request, criteria);
		specialProgramExample.setOrderByClause("create_time desc");
		specialProgramExample.setLimitStart((request.getPage() - 1) * request.getPageSize());
		specialProgramExample.setLimitEnd(request.getPageSize());
		logger.info("开始查询成立后专项计划列表");
		List<SpecialProgram> specialProgramLists = specialProgramMapper.selectByExample(specialProgramExample);
		int count = specialProgramMapper.countByExample(specialProgramExample);
		List<SpecialProgramIssueListDTO> specialProgramListDTOs = SpecialProgramCovert
				.covertSpecialProgramIssueDTO(specialProgramLists,userDetail);
		return ListResult.success(specialProgramListDTOs, count);
	}

	/**
	 * 发行后管理专项计划成立
	 */
	@Override
	public void addSpecialProgramIssue(SpecialProgramRequestDTO request, UserVo userDetail) {
		StringUtil.trimObjectFields(request);
		AssetSpecialProgramPersistenceValidator.checkIssueSpecialProgramRequest(request, userDetail);
		SpecialProgram specialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
	    if (specialProgram.getStatus().equals("CLOSE")){ 
			throw WebException.instance("结束状态不能变更为其它状态");
	    }
		if (!userDetail.getCustomer().getCustomerName().equals(specialProgram.getManagerName())) {
			throw WebException.instance("只有管理人才能修改发行后专项计划成立");
		}
		SpecialProgram oldSpecialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
		if (oldSpecialProgram == null) {
			throw WebException.instance("发行后专项计划编号不存在");
		}
		deleteOriginatorAndServiceAgencyData(request);
		saveIssueUpdateSpecialProgram(request, userDetail);
		saveServiceAgency(request, oldSpecialProgram.getId());
		saveOriginator(request, oldSpecialProgram.getId());
		specialProgramLogTransactionService.insertSpecialProgramLog(specialProgram, MoneyUtil.yuan2Cent(request.getGross()), 
				request.getStatus(), userDetail);
		logger.info("发行后专项计划成立");

	}

	/**
	 * 发行后专项计划成立管理
	 */
	@Override
	public void updateSpeicalProgramIssue(SpecialProgramRequestDTO request, UserVo userDetail) {
		StringUtil.trimObjectFields(request);
		SpecialProgram specialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
		//判断专项计划状态转移图
		specialProgramStatusCheck(request, specialProgram);
		if (!userDetail.getCustomer().getCustomerName().equals(specialProgram.getManagerName())) {
			throw WebException.instance("只有管理人才能修改发行后专项计划成立管理");
		}
		SpecialProgram oldSpecialProgram = specialProgramMapper.selectByPrimaryKey(request.getId());
		if (oldSpecialProgram == null) {
			throw WebException.instance("发行后专项计划编号不存在");
		}
		deleteOriginatorAndServiceAgencyData(request);
		saveIssueUpdateSpecialProgram(request, userDetail);
		saveServiceAgency(request, oldSpecialProgram.getId());
		saveOriginator(request, oldSpecialProgram.getId());
		specialProgramLogTransactionService.insertSpecialProgramLog(specialProgram, MoneyUtil.yuan2Cent(request.getGross()),
				request.getStatus(), userDetail);
		logger.info("发行后专项计划成立管理");
	}

	/**
	 * 查询条件抽取
	 * 
	 * @param request
	 * @param criteria
	 */
	private void queryCriteria(SpecialProgramListRequestIssueDTO request, SpecialProgramExample.Criteria criteria) {
		if (StringUtils.isNotBlank(request.getSpecialProgramName())) {
			criteria.andSpecialProgramNameLike("%" + request.getSpecialProgramName().trim() + "%");
		}
		if (StringUtils.isNotBlank(request.getUnderlyingAssetType())) {
			criteria.andUnderlyingAssetTypeLike("%" + request.getUnderlyingAssetType() + "%");
		}
		if (StringUtils.isNotBlank(request.getManagerName())) {
			criteria.andManagerNameLike("%" + request.getManagerName() + "%");
		}
		if (StringUtils.isNotBlank(request.getStartEstablishmentDate())) {
			criteria.andEstimateEstablishmentDateGreaterThanOrEqualTo(request.getStartEstablishmentDate());
		}
		if (StringUtils.isNotBlank(request.getEndEstablishmentDate())) {
			criteria.andEstimateEstablishmentDateLessThanOrEqualTo(request.getEndEstablishmentDate());
		}
		if (null != request.getStatus()) {
			criteria.andStatusEqualTo(request.getStatus().name());
		}
			criteria.andDeletedEqualTo(false);
		criteria.andStatusNotEqualTo("CREATE");
	}
	
	/**
	 * 成立后专项计划新增和修改set
	 * @param request
	 * @param userDetail
	 */
	private void saveIssueUpdateSpecialProgram(SpecialProgramRequestDTO request, UserVo userDetail) {
		SpecialProgram specialProgramTo = new SpecialProgram();
		specialProgramTo.setId(request.getId());
		specialProgramTo.setSpecialProgramName(request.getSpecialProgramName());
		specialProgramTo.setUnderlyingAssetType(request.getUnderlyingAssetType());
		specialProgramTo.setManagerName(request.getManagerName());
		specialProgramTo.setManagerId(request.getManagerId());
		specialProgramTo.setLawFirmName(request.getLawFirmName());
		specialProgramTo.setAccountingFirmName(request.getAccountingFirmName());
		specialProgramTo.setSupervisionBank(request.getSupervisionBank());
		specialProgramTo.setRatingAgency(request.getRatingAgency());
		specialProgramTo.setAssetEvaluationAgency(request.getAssetEvaluationAgency());
		specialProgramTo.setEstimateEstablishmentDate(request.getEstimateEstablishmentDate());
		specialProgramTo.setEstablishmentDate(request.getEstablishmentDate());
		specialProgramTo.setEstimateDueDate(request.getEstimateDueDate());
		specialProgramTo.setStatutoryDueDate(request.getStatutoryDueDate());
		specialProgramTo.setStatus((request.getStatus()).name());
		specialProgramTo.setRenewalPeriod(request.getRenewalPeriod());
		specialProgramTo.setGross(MoneyArithUtil.convertStringToMoney(request.getGross()));
		specialProgramTo.setPreferA(MoneyArithUtil.convertStringToMoney(request.getPreferA()));
		specialProgramTo.setPreferB(MoneyArithUtil.convertStringToMoney(request.getPreferB()));
		specialProgramTo.setDefer(MoneyArithUtil.convertStringToMoney(request.getDefer()));
		specialProgramTo.setTrustMethod(request.getTrustMethod());
		specialProgramTo.setAssetPoolPrincipalBalance(MoneyArithUtil.convertStringToMoney(request.getAssetPoolPrincipalBalance()));
		specialProgramTo.setContractNum(request.getContractNum());
		specialProgramTo.setWeightedAverageContractPeriod(request.getWeightedAverageContractPeriod());
		specialProgramTo.setFinanceNum(request.getFinanceNum());
		specialProgramTo.setWeightedAverageInterestRate(request.getWeightedAverageInterestRate());
		specialProgramTo.setCyclePurchaseStructure(request.getCyclePurchaseStructure());
		specialProgramTo.setCreaterId(userDetail.getUserId());
		specialProgramTo.setCreaterName(userDetail.getUsername());
		specialProgramTo.setCustodianBank(request.getCustodianBank());
		specialProgramTo.setCreateTime(request.getCreateTime());
		specialProgramTo.setUpdateTime(request.getUpdateTime());
		specialProgramTo.setCyclePeriod(request.getCyclePeriod());
		specialProgramTo.setCapitalAccumulationDate(request.getCapitalAccumulationDate());
		specialProgramTo.setCyclePurchaseDate(request.getCyclePurchaseDate());
		specialProgramTo.setIncomeRecoveryDate(request.getIncomeRecoveryDate());
		specialProgramTo.setRecyclingPaymentDate(request.getRecyclingPaymentDate());
		specialProgramTo.setExcessFund(MoneyArithUtil.convertStringToMoney(request.getExcessFund()));
		specialProgramTo.setCyclePurchaseTerm(request.getCyclePurchaseTerm());
		specialProgramTo.setUnderlyingAssetQualityStandard(request.getUnderlyingAssetQualityStandard());
		specialProgramTo.setCycleWeightedAverageInterestRate(request.getCycleWeightedAverageInterestRate());

		specialProgramMapper.updateByPrimaryKeySelective(specialProgramTo);
		logger.info("修改专项计划数据");
	}
	
	/**
	 * 法定到期日期更改状态为结束
	 */
	@Override
	public void updateSpecialProgramStatusByStatutoryDueDate(String currentDate) {
		//获取专项计划信息
		 SpecialProgramExample programExample = new SpecialProgramExample();
		programExample.createCriteria().andStatutoryDueDateEqualTo(currentDate).
			andStatusIn(Arrays.asList(SpecialProgramStatus.ESTABLISH.name()));
		List<SpecialProgram> specialPrograms = specialProgramMapper.lockByExample(programExample);
			
		for (SpecialProgram specialProgram : specialPrograms) {
			Long gross = specialProgram.getGross();
				specialProgram.setStatus(SpecialProgramStatus.CLOSE.name());
				specialProgram.setUpdateTime(new Date());
				specialProgramMapper.updateByPrimaryKey(specialProgram);
				UserVo userVo = getUserVo();
				specialProgramLogTransactionService.insertSpecialProgramLog(specialProgram,gross,
						SpecialProgramStatus.CLOSE, userVo);
				logger.info("专项计划状态法定到期日期变更为结束定时器执行完成");
		}
	}

	

	
	/**
	 * 定时器变更状态的时操作人是（ADMIN_USER_NAME,ADMIN_CUSTOMER_NAME）
	 * @return
	 */
	private UserVo getUserVo() {
		UserVo userVo = new UserVo();
		userVo.setUsername(MemberConstants.ADMIN_USER_NAME);
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomerName(MemberConstants.ADMIN_CUSTOMER_NAME);
		userVo.setCustomer(customerVo);
		return userVo;
	}
	
}
