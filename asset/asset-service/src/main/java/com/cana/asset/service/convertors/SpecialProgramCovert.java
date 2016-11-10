package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.dao.po.Originator;
import com.cana.asset.dao.po.ServiceAgency;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramLog;
import com.cana.asset.service.transaction.IUnderlyingAssetCalcTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.vbam.common.asset.dto.OriginatorDTO;
import com.cana.vbam.common.asset.dto.ServiceAgencyDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramIssueListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogDTO;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月30日上午10:25:48
 */
@Component
public class SpecialProgramCovert {
	/**
	 * 专项计划列表实体转换
	 * @param specialPrograms
	 * @return
	 */
	
	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;
	
	@Resource
	private IUnderlyingAssetCalcTransactionService underlyingAssetCalcTransactionService;
	
	public  List<SpecialProgramListDTO> covertSpecialProgramDao2ResDTO(List<SpecialProgram> specialPrograms,UserVo userDetail){
		 List<SpecialProgramListDTO> specialProgramListDTOs = new ArrayList<SpecialProgramListDTO>();
		 for (SpecialProgram specialProgram : specialPrograms) {
			SpecialProgramListDTO specialProgramListDTO = new SpecialProgramListDTO();
			specialProgramListDTO.setId(specialProgram.getId());
			specialProgramListDTO.setSpecialProgramName(specialProgram.getSpecialProgramName());
			specialProgramListDTO.setUnderlyingAssetTypeDesc(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()).desc());
			specialProgramListDTO.setManagerName(specialProgram.getManagerName());
			specialProgramListDTO.setEstimateEstablishmentDate(specialProgram.getEstimateEstablishmentDate());
			specialProgramListDTO.setStatusDesc(SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc());
			specialProgramListDTO.setUpdateTime(specialProgram.getUpdateTime());
			//是否有权限修改
			if(!specialProgram.getManagerName().equals(userDetail.getCustomer().getCustomerName()) || specialProgram.getStatus().equals("CLOSE")){
				specialProgramListDTO.setAllowUpdate(false);
			}
			//是否有权限删除
			boolean checkUnbindForDeleteProgram = underlyingAssetTransactionService.checkUnbindForDeleteProgram(specialProgram.getId());
			if(!specialProgram.getManagerName().equals(userDetail.getCustomer().getCustomerName()) || 
					!specialProgram.getStatus().equals("CREATE") || !checkUnbindForDeleteProgram == true){
				specialProgramListDTO.setAllowDelete(false);
			}
			specialProgramListDTOs.add(specialProgramListDTO);
		}
		return specialProgramListDTOs;
		
	}
	/**
	 * 专项计划详情实体转换
	 * @param specialProgram
	 * @param originators
	 * @param serviceAgencys
	 * @return
	 */
	public  SpecialProgramDTO covertorToSpecialProgramDTO(SpecialProgram specialProgram,List<Originator> originators,List<ServiceAgency> serviceAgencys){
		long queryGrossBySpecialProgramId = underlyingAssetCalcTransactionService.queryGrossBySpecialProgramId(specialProgram.getId());
		SpecialProgramDTO specialProgramDTO = toSpecialProgramDTO(specialProgram,queryGrossBySpecialProgramId);
		List<OriginatorDTO> originatorDTO = toOriginatorDTO(originators);
		List<ServiceAgencyDTO> serviceAgencyDTO = toServiceAgencyDTO(serviceAgencys);
		checkIsEmpty(specialProgramDTO, originatorDTO, serviceAgencyDTO);
		return specialProgramDTO;
	}
	
	public static SpecialProgramLog convertSpecialProgram2SpecialProgramLog(SpecialProgram specialProgram) {
		SpecialProgramLog specialProgramLog = new SpecialProgramLog();
		specialProgramLog.setSpecialProgramId(specialProgram.getId());
		specialProgramLog.setSpecialProgramName(specialProgram.getSpecialProgramName());
		specialProgramLog.setUnderlyingAssetType(specialProgram.getUnderlyingAssetType());
		specialProgramLog.setCreateTime(new Date());
		return specialProgramLog;
	}
	
	public static List<SpecialProgramLogDTO> convertSpecialProgramLogs2SpecialProgramLogDTOs(List<SpecialProgramLog> specialProgramLogs){
		if(CollectionUtils.isEmpty(specialProgramLogs))
			return new ArrayList<>();
		List<SpecialProgramLogDTO> dtos = new ArrayList<>();
		for(SpecialProgramLog specialProgramLog : specialProgramLogs){
			SpecialProgramLogDTO dto = new SpecialProgramLogDTO();
			dto.setId(specialProgramLog.getId());
			dto.setSpecialProgramId(specialProgramLog.getSpecialProgramId());
			dto.setSpecialProgramName(specialProgramLog.getSpecialProgramName());
			dto.setUnderlyingAssetType(specialProgramLog.getUnderlyingAssetType());
			dto.setUnderlyingAssetTypeDesc(UnderlyingAssetSource.valueOf(specialProgramLog.getUnderlyingAssetType()).desc());
			dto.setAssetPoolAmount(MoneyUtil.cent2Yuan(specialProgramLog.getAssetPoolAmount()));
			dto.setOperateCompanyName(specialProgramLog.getOperateCompanyName());
			dto.setOperateUsername(specialProgramLog.getOperateUsername());
			dto.setOperateType(specialProgramLog.getOperateType());
			dto.setOperateTypeDesc(SpecialProgramStatus.valueOf(specialProgramLog.getOperateType()).desc());
			dto.setCreateTime(specialProgramLog.getCreateTime());
			dtos.add(dto);
		}
		return dtos;
	}
	
	/**
	 * 专项计划基本信息
	 * @param specialProgram
	 * @return
	 */
	private static SpecialProgramDTO toSpecialProgramDTO(SpecialProgram specialProgram, Long queryGrossBySpecialProgramId) {
		SpecialProgramDTO specialProgramDTO = new SpecialProgramDTO();
		
		specialProgramDTO.setId(specialProgram.getId());
		specialProgramDTO.setSpecialProgramName(specialProgram.getSpecialProgramName());
		specialProgramDTO.setUnderlyingAssetType(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()));
		specialProgramDTO.setManagerName(specialProgram.getManagerName());
		specialProgramDTO.setManagerId(specialProgram.getManagerId());
		specialProgramDTO.setLawFirmName(specialProgram.getLawFirmName());
		specialProgramDTO.setAccountingFirmName(specialProgram.getAccountingFirmName());
		specialProgramDTO.setSupervisionBank(specialProgram.getSupervisionBank());
		specialProgramDTO.setRatingAgency(specialProgram.getRatingAgency());
		specialProgramDTO.setAssetEvaluationAgency(specialProgram.getAssetEvaluationAgency());
		specialProgramDTO.setEstimateEstablishmentDate(specialProgram.getEstimateEstablishmentDate());
		specialProgramDTO.setEstablishmentDate(specialProgram.getEstablishmentDate());
		specialProgramDTO.setEstimateDueDate(specialProgram.getEstimateDueDate());
		specialProgramDTO.setStatutoryDueDate(specialProgram.getStatutoryDueDate());
		specialProgramDTO.setStatus(SpecialProgramStatus.valueOf(specialProgram.getStatus()));
		specialProgramDTO.setRenewalPeriod(specialProgram.getRenewalPeriod());
		specialProgramDTO.setGross(MoneyUtil.cent2Yuan(specialProgram.getGross()));
		if(specialProgram.getPreferA()!= 0){
			specialProgramDTO.setPreferA(MoneyArithUtil.convertMoneyToString(specialProgram.getPreferA()));
		}
		if(specialProgram.getPreferB()!= 0){
			specialProgramDTO.setPreferB(MoneyArithUtil.convertMoneyToString(specialProgram.getPreferB()));
		}
		if(specialProgram.getDefer()!= 0){
			specialProgramDTO.setDefer(MoneyArithUtil.convertMoneyToString(specialProgram.getDefer()));
		}
		specialProgramDTO.setTrustMethod(specialProgram.getTrustMethod());
		specialProgramDTO.setAssetPoolPrincipalBalance(MoneyUtil.cent2Yuan(queryGrossBySpecialProgramId));
		specialProgramDTO.setContractNum(specialProgram.getContractNum());
		specialProgramDTO.setWeightedAverageContractPeriod(specialProgram.getWeightedAverageContractPeriod());
		specialProgramDTO.setFinanceNum(specialProgram.getFinanceNum());
		specialProgramDTO.setWeightedAverageInterestRate(specialProgram.getWeightedAverageInterestRate());
		specialProgramDTO.setCyclePurchaseStructure(specialProgram.getCyclePurchaseStructure());
		specialProgramDTO.setCreaterId(specialProgram.getCreaterId());
		specialProgramDTO.setCreaterName(specialProgram.getCreaterName());
		specialProgramDTO.setCustodianBank(specialProgram.getCustodianBank());
		specialProgramDTO.setCreateTime(specialProgram.getCreateTime());
		specialProgramDTO.setUpdateTime(specialProgram.getUpdateTime());
		specialProgramDTO.setDeleted(false);
		specialProgramDTO.setCyclePeriod(specialProgram.getCyclePeriod());
		specialProgramDTO.setCapitalAccumulationDate(specialProgram.getCapitalAccumulationDate());
		specialProgramDTO.setCyclePurchaseDate(specialProgram.getCyclePurchaseDate());
		specialProgramDTO.setIncomeRecoveryDate(specialProgram.getIncomeRecoveryDate());
		specialProgramDTO.setRecyclingPaymentDate(specialProgram.getRecyclingPaymentDate());
		specialProgramDTO.setExcessFund(MoneyUtil.cent2Yuan(specialProgram.getExcessFund()));
		specialProgramDTO.setCyclePurchaseTerm(specialProgram.getCyclePurchaseTerm());
		specialProgramDTO.setUnderlyingAssetQualityStandard(specialProgram.getUnderlyingAssetQualityStandard());
		specialProgramDTO.setCycleWeightedAverageInterestRate(specialProgram.getCycleWeightedAverageInterestRate());
		
		return specialProgramDTO;
	}
	
	/**
	 * 原始权益人数据
	 * @param originators
	 */
	private static List<OriginatorDTO> toOriginatorDTO(List<Originator> originators) {
		List<OriginatorDTO> originatorDTO = new ArrayList<OriginatorDTO>();
		for (Originator originator : originators) {
			OriginatorDTO originatorDTO2 = new OriginatorDTO();
			originatorDTO2.setSpecialProgramId(originator.getSpecialProgramId());
			originatorDTO2.setId(originator.getId());
			originatorDTO2.setOriginatorName(originator.getOriginatorName());
			originatorDTO2.setCreateTime(originator.getCreateTime());
			originatorDTO2.setUpateTime(originator.getUpateTime());
			originatorDTO.add(originatorDTO2);
		}
		return originatorDTO;
	}
	
	/**
	 * 资产服务机构
	 * @param serviceAgencys
	 * @return
	 */
	private static List<ServiceAgencyDTO> toServiceAgencyDTO(List<ServiceAgency> serviceAgencys){
		List<ServiceAgencyDTO> serviceAgencyDTO = new ArrayList<ServiceAgencyDTO>();
		for(ServiceAgency serviceAgency : serviceAgencys){
			ServiceAgencyDTO agencyDTO = new ServiceAgencyDTO();
			agencyDTO.setId(serviceAgency.getId());
			agencyDTO.setServiceAgencyName(serviceAgency.getServiceAgencyName());
			agencyDTO.setSpecialProgramId(serviceAgency.getSpecialProgramId());
			agencyDTO.setCreateTime(serviceAgency.getCreateTime());
			agencyDTO.setUpateTime(serviceAgency.getUpateTime());
			serviceAgencyDTO.add(agencyDTO);
		}
		return serviceAgencyDTO;
	}
	/**
	 * 检查原始权益人和资产服务机构是否为空
	 * @param specialProgramDTO
	 * @param originatorDTO
	 * @param serviceAgencyDTO
	 */
	private static void checkIsEmpty(SpecialProgramDTO specialProgramDTO, List<OriginatorDTO> originatorDTO,
			List<ServiceAgencyDTO> serviceAgencyDTO) {
		if (CollectionUtils.isNotEmpty(originatorDTO)) {
			specialProgramDTO.setOriginators(originatorDTO);
		}
		if (CollectionUtils.isNotEmpty(serviceAgencyDTO)) {
			specialProgramDTO.setServiceAgencys(serviceAgencyDTO);
		}
	}



/**
 * 发行后专项计划列表转换
 * @param specialProgramLists
 * @return
 */

	public static List<SpecialProgramIssueListDTO> covertSpecialProgramIssueDTO(
			List<SpecialProgram> specialProgramLists,UserVo userDetail) {
		List<SpecialProgramIssueListDTO> specialProgramIssueListDTOs = new ArrayList<SpecialProgramIssueListDTO>();

		for (SpecialProgram specialProgram : specialProgramLists) {
			SpecialProgramIssueListDTO specialProgramIssueListDTO = new SpecialProgramIssueListDTO();
			specialProgramIssueListDTO.setId(specialProgram.getId());
			specialProgramIssueListDTO.setSpecialProgramName(specialProgram.getSpecialProgramName());
			specialProgramIssueListDTO.setUnderlyingAssetTypeDesc(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()).desc());
			specialProgramIssueListDTO.setManagerName(specialProgram.getManagerName());
			specialProgramIssueListDTO.setEstablishmentDate(specialProgram.getEstablishmentDate());
			specialProgramIssueListDTO.setStatusDesc(SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc());
			specialProgramIssueListDTO.setGross(MoneyArithUtil.convertMoneyToString(specialProgram.getGross()));
			specialProgramIssueListDTO.setStatutoryDueDate(specialProgram.getStatutoryDueDate());
			specialProgramIssueListDTO.setUpdateTime(specialProgram.getUpdateTime());
			if (SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc().equals("结束")
					|| SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc().equals("成立")
					|| !specialProgram.getManagerName().equals(userDetail.getCustomer().getCustomerName())) {
				specialProgramIssueListDTO.setAllowIssue(false);
			} else {
				if (SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc().equals("封包")
						|| SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc().equals("发行")
						|| !specialProgram.getManagerName().equals(userDetail.getCustomer().getCustomerName())) {
					specialProgramIssueListDTO.setAllowManage(false);

				}
			}
			if(SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc().equals("封包") || 
					SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc().equals("成立") || 
					!specialProgram.getManagerName().equals(userDetail.getCustomer().getCustomerName())){
				specialProgramIssueListDTO.setAllowManage(false);
			}
			specialProgramIssueListDTOs.add(specialProgramIssueListDTO);
		}
		return specialProgramIssueListDTOs;
	}
	
	
	
	
	
}


















