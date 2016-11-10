package com.cana.asset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.cana.asset.service.IAssetPoolService;
import com.cana.asset.service.convertors.AssetPoolConvertor;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IABSSpecialProgramTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetCalcTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetQueryTransactionService;
import com.cana.vbam.common.asset.dto.AssetInPoolDTO;
import com.cana.vbam.common.asset.dto.AssetpoolListDTO;
import com.cana.vbam.common.asset.dto.OriginatorDTO;
import com.cana.vbam.common.asset.dto.ServiceAgencyDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetAmountSummary;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.enums.RequestDirection;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetPoolServiceImpl implements IAssetPoolService {

	@Resource
	private IABSDataPrivilegeTransactionService aBSDataPrivilegeTransactionService;
	
	@Resource
	private SpecialProgramMapper specialProgramMapper;
	
	@Resource
	private OriginatorMapper originatorMapper;
	
	@Resource
	private ServiceAgencyMapper serviceAgencyMapper;
	
	@Resource
	private IABSSpecialProgramTransactionService specialProgramTransactionService;
	
	@Resource
	private IUnderlyingAssetCalcTransactionService underlyingAssetCalcTransactionService;
	
	@Resource
	private IUnderlyingAssetQueryTransactionService underlyingAssetQueryTransactionService;
	
	@Override
	public PageList<AssetpoolListDTO> getAssetpoolList(SpecialProgramQueryDTO queryDTO, UserVo userVo) {
		PageList<AssetpoolListDTO> result = new PageList<>();
		PageList<SpecialProgram> pageList = specialProgramTransactionService.getSpecialProgramList(queryDTO, userVo);
		List<SpecialProgram> specialPrograms = pageList.getRecords();
		Set<String> specialProgramIds = new HashSet<>();
		for(SpecialProgram specialProgram : specialPrograms){
			specialProgramIds.add(specialProgram.getId());
		}
		Map<String, UnderlyingAssetAmountSummary> specialProgramMoney = underlyingAssetCalcTransactionService.queryFinanceAmountBySpecialProgramIds(specialProgramIds);
		Map<String, Long> originAssetpoolScales = queryGrossBySpecialProgramIds(specialPrograms);
		List<AssetpoolListDTO> assetpoolListDTOs = AssetPoolConvertor.specialProgram2AssetpoolListDTO(specialPrograms,specialProgramMoney,originAssetpoolScales);
		result.setRecords(assetpoolListDTOs);
		result.setTotalRecords(pageList.getTotalRecords());
		return result;
	}
	

	/**
	 * 查询资产池规模("立项"查专项计划融资余额,其它查专项计划"总规模")
	 * @param specialPrograms (专项计划编号id, 状态status 不能为空)
	 * @return
	 */
	@Override
	public Map<String, Long> queryGrossBySpecialProgramIds(List<SpecialProgram> specialPrograms) {
		checkIdsAndStatusIsNotBlank(specialPrograms);
		Set<String> setUpSpecialProgramIds = new HashSet<>(); // 立项的专项计划
		Set<String> noSetUpSpecialProgramIds = new HashSet<>(); // 其它的专项计划
		Map<String, Long> returnMap = new HashMap<>(); 
		// 循环
		for (SpecialProgram specialProgram : specialPrograms) {
			if (SpecialProgramStatus.CREATE.name().equals(specialProgram.getStatus())) {
				setUpSpecialProgramIds.add(specialProgram.getId());
			} else {
				noSetUpSpecialProgramIds.add(specialProgram.getId());
			}
		}
		
		// 如果是“立项”
		if (CollectionUtils.isNotEmpty(setUpSpecialProgramIds)) {
			Map<String, Long> setUpMap = underlyingAssetCalcTransactionService.queryGrossBySpecialProgramIds(setUpSpecialProgramIds);
			returnMap.putAll(setUpMap);
		}
		// 如果非立项
		if (CollectionUtils.isNotEmpty(noSetUpSpecialProgramIds)) {
			Map<String, Long> noSetUpMap = getNoSetUpSpecialProgram(noSetUpSpecialProgramIds);
			returnMap.putAll(noSetUpMap);
		}
		return returnMap;
	}

	/**
	 * 检验非必须项
	 * @param assetpoolListDTOs
	 */
	private void checkIdsAndStatusIsNotBlank(List<SpecialProgram> specialPrograms) {
		if (CollectionUtils.isEmpty(specialPrograms)) {
			throw WebException.instance("资产池列表不能为空");
		}
		
		for(SpecialProgram specialProgram : specialPrograms) {
			if (StringUtils.isBlank(specialProgram.getId()) || specialProgram.getStatus() == null) {
				throw WebException.instance("专项计划ID和状态不能为空");
			}
		}
	}

	/**
	 * 拿到非立项计划的资产规模
	 * @param noSetUpSpecialProgramIds
	 * @return
	 */
	private Map<String, Long> getNoSetUpSpecialProgram(Set<String> noSetUpSpecialProgramIds) {
		List<String> noSetUpSpecialProgramIdList = new ArrayList<String>(noSetUpSpecialProgramIds);
		SpecialProgramExample example = new SpecialProgramExample();
		SpecialProgramExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(noSetUpSpecialProgramIdList);
		List<SpecialProgram> specialPrograms = specialProgramMapper.selectByExample(example);
		Map<String, Long> noSetUpMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(specialPrograms)) {
			for(SpecialProgram specialProgram : specialPrograms) {
				noSetUpMap.put(specialProgram.getId(), specialProgram.getGross());
			}
		}
		return noSetUpMap;
	}

	@Override
	public AssetpoolListDTO getAssetpoolPacket(String id, UserVo userVo) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("专项计划编号为空");
		SpecialProgram specialProgram = specialProgramMapper.selectByPrimaryKey(id); 
		if(specialProgram == null || specialProgram.getDeleted())
			throw WebException.instance("专项计划编号异常");
		boolean flag = aBSDataPrivilegeTransactionService.allow(userVo.getCustomer().getUserType(), userVo.getCustomer().getCustomerName(), id);
		if(!flag)
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		long originAssetpoolScale = underlyingAssetCalcTransactionService.queryGrossBySpecialProgramId(id);
		if(originAssetpoolScale <=0)
			throw WebException.instance("该资产池规模不能进行封包");
		AssetpoolListDTO assetpoolListDTO = AssetPoolConvertor.SpecialProgram2AssetpoolListDTO(specialProgram,originAssetpoolScale);
		//原始权益人
		OriginatorExample originatorExample = new OriginatorExample();
		originatorExample.createCriteria().andSpecialProgramIdEqualTo(id);
		List<Originator> originators = originatorMapper.selectByExample(originatorExample);
		List<OriginatorDTO> originatorDTOs = AssetPoolConvertor.Originator2OriginatorDTO(originators);
		//资产服务机构
		ServiceAgencyExample serviceAgencyExample = new ServiceAgencyExample();
		serviceAgencyExample.createCriteria().andSpecialProgramIdEqualTo(id);
		List<ServiceAgency> serviceAgencys = serviceAgencyMapper.selectByExample(serviceAgencyExample);
		List<ServiceAgencyDTO> serviceAgencyDTOs = AssetPoolConvertor.ServiceAgency2ServiceAgencyDTO(serviceAgencys);
		assetpoolListDTO.setOriginators(originatorDTOs);
		assetpoolListDTO.setServiceAgencys(serviceAgencyDTOs);
		return assetpoolListDTO;
	}

	/**
	 * 资产池管理专项计划
	 * 
	 * @param id
	 * @param userSessionDTO
	 */
	@Override
	public AssetpoolListDTO getAssetPoolDetails(String id, String status, UserVo userDetail) {
		validateData(id, status);
		// 专项计划详情
		SpecialProgramDTO specialProgramDTO = specialProgramTransactionService.getSpecialProgramById(id, userDetail);

		// 查询专项计划的应还、已还金额信息
		Set<String> specialProgramIds = new HashSet<>();
		specialProgramIds.add(id);
		Map<String, UnderlyingAssetAmountSummary> specialProgramMoney = underlyingAssetCalcTransactionService.queryFinanceAmountBySpecialProgramIds(specialProgramIds);

		// 专项计划规模
		List<SpecialProgram> specialPrograms = new ArrayList<>();
		SpecialProgram program = new SpecialProgram();
		program.setId(id);
		program.setStatus(status);
		specialPrograms.add(program);
		Map<String, Long> originAssetpoolScales = queryGrossBySpecialProgramIds(specialPrograms);
		
		AssetpoolListDTO assetpoolListDTO = AssetPoolConvertor.specialProgram2AssetpoolDTO(specialProgramDTO, specialProgramMoney, originAssetpoolScales);
		return assetpoolListDTO;
	}

	/**
	 * 资产池管理入池信息
	 * @param queryDTO
	 * @param userSessionDTO
	 * @return
	 */
	@Override
	public PageList<AssetInPoolDTO> getAssetpoolManageList(SpecialProgramQueryDTO queryDTO, UserVo userDetail) {
		if (queryDTO == null || StringUtils.isBlank(queryDTO.getId())) {
			throw WebException.instance("专项计划编号为空");
		}
		PageList<AssetInPoolDTO> pageList = new PageList<>();
		
		// 根据专项计划ID查询基础资产
		UnderlyingAssetQueryDTO assetQueryDTO = new UnderlyingAssetQueryDTO();
		assetQueryDTO.setSpecialProgramId(queryDTO.getId()); // 专项计划ID
		assetQueryDTO.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERED); // 已入池
		assetQueryDTO.setPage(queryDTO.getPage());
		assetQueryDTO.setPageSize(queryDTO.getPageSize());
		ListResult<UnderlyingAssetDTO> assetDTOs = underlyingAssetQueryTransactionService.queryUnderlyingAssets(userDetail, assetQueryDTO, RequestDirection.ASSET_POOL);
		if (assetDTOs == null || CollectionUtils.isEmpty(assetDTOs.getData())) {
			return pageList;
		}
		
		// 查询专项计划的应还、已还金额信息
		Set<String> underlyingAssetIds = new HashSet<>();
		for(UnderlyingAssetDTO assetDTO : assetDTOs.getData()) {
			underlyingAssetIds.add(assetDTO.getLoanNo());
		}
		Map<String, UnderlyingAssetAmountSummary> underlyingAssetAmountSummary = underlyingAssetCalcTransactionService.queryFinanceAmountByUnderlyingAssetIds(underlyingAssetIds);
		
		List<AssetInPoolDTO> assetpoolListDTO = AssetPoolConvertor.underlyingAsset2AssetInPoolDTO(assetDTOs.getData(), underlyingAssetAmountSummary);
		pageList.setRecords(assetpoolListDTO);
		pageList.setTotalRecords(assetDTOs.getTotalNum());
		return pageList;
	}


	@Override
	public ListResult<LoanPaidDTO> getLoanHistoryList(String underlyingAssetId, int page, int pageSize, UserVo userVo) {
		if (StringUtils.isBlank(underlyingAssetId)) {
			throw WebException.instance("放款编号为空");
		}
		return underlyingAssetQueryTransactionService.getLoanPaids(userVo, underlyingAssetId, page, pageSize);
	}


	@Override
	public List<LoanPlanDTO> getLoanPaidList(String underlyingAssetId, UserVo userVo) {
		if (StringUtils.isBlank(underlyingAssetId)) {
			throw WebException.instance("放款编号为空");
		}
		return underlyingAssetQueryTransactionService.getLoanPlans(userVo, underlyingAssetId);
	}
	
	/**
	 * 检验数据
	 * @param id
	 * @param status
	 */
	private void validateData(String id, String status) {
		if (StringUtils.isBlank(id)) {
			throw WebException.instance("专项计划编号为空");
		}
		
		if (StringUtils.isBlank(status)) {
			throw WebException.instance("专项计划状态为空");
		}
	}
	
}
