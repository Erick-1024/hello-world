package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.cana.asset.dao.po.Originator;
import com.cana.asset.dao.po.ServiceAgency;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.vbam.common.asset.dto.AssetInPoolDTO;
import com.cana.vbam.common.asset.dto.AssetpoolListDTO;
import com.cana.vbam.common.asset.dto.OriginatorDTO;
import com.cana.vbam.common.asset.dto.ServiceAgencyDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetAmountSummary;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.utils.MoneyArithUtil;

public class AssetPoolConvertor {

	public static List<AssetpoolListDTO> specialProgram2AssetpoolListDTO(List<SpecialProgram> specialPrograms,
			Map<String, UnderlyingAssetAmountSummary> specialProgramMoney, Map<String, Long> originAssetpoolScales) {
		List<AssetpoolListDTO> list = new ArrayList<>();
		for(SpecialProgram  specialProgram:specialPrograms){
			String id = specialProgram.getId(); 
			UnderlyingAssetAmountSummary underlyingSummary = specialProgramMoney.get(id);
			AssetpoolListDTO assetpoolListDTO = new AssetpoolListDTO();
			assetpoolListDTO.setId(id);
			assetpoolListDTO.setSpecialProgramName(specialProgram.getSpecialProgramName());
			assetpoolListDTO.setUnderlyingAssetType(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()));
			assetpoolListDTO.setUnderlyingAssetTypeDesc(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()).desc());
			assetpoolListDTO.setManagerId(specialProgram.getManagerId());
			assetpoolListDTO.setManagerName(specialProgram.getManagerName());
			assetpoolListDTO.setEstimateEstablishmentDate(specialProgram.getEstimateEstablishmentDate());
			assetpoolListDTO.setOriginAssetpoolScale(MoneyArithUtil.convertMoneyToString(originAssetpoolScales.get(id)));
			assetpoolListDTO.setStatus(SpecialProgramStatus.valueOf(specialProgram.getStatus()));
			assetpoolListDTO.setStatusDesc(SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc());
			assetpoolListDTO.setAccountIncome(MoneyArithUtil.convertMoneyToString(underlyingSummary.getAccountIncome()));
			assetpoolListDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(underlyingSummary.getAccountPrincipal()));
			assetpoolListDTO.setAccountAmount(MoneyArithUtil.convertMoneyToString(underlyingSummary.getAccountAmount()));
			assetpoolListDTO.setPaidIncome(MoneyArithUtil.convertMoneyToString(underlyingSummary.getPaidIncome()));
			assetpoolListDTO.setPaidPrincipal(MoneyArithUtil.convertMoneyToString(underlyingSummary.getPaidPrincipal()));
			assetpoolListDTO.setPaidAmount(MoneyArithUtil.convertMoneyToString(underlyingSummary.getPaidAmount()));
			assetpoolListDTO.setUnpaidAmount(MoneyArithUtil.convertMoneyToString(underlyingSummary.getUnpaidAmount()));
			list.add(assetpoolListDTO);
		}
		return list;
	}

	public static AssetpoolListDTO SpecialProgram2AssetpoolListDTO(SpecialProgram specialProgram,long originAssetpoolScale) {
		AssetpoolListDTO assetpoolListDTO = new AssetpoolListDTO();
		assetpoolListDTO.setId(specialProgram.getId());
		assetpoolListDTO.setSpecialProgramName(specialProgram.getSpecialProgramName());
		assetpoolListDTO.setUnderlyingAssetType(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()));
		assetpoolListDTO.setUnderlyingAssetTypeDesc(BasicAssetType.valueOf(specialProgram.getUnderlyingAssetType()).desc());
		assetpoolListDTO.setEstimateEstablishmentDate(specialProgram.getEstimateEstablishmentDate());
		assetpoolListDTO.setStatus(SpecialProgramStatus.valueOf(specialProgram.getStatus()));
		assetpoolListDTO.setStatusDesc(SpecialProgramStatus.valueOf(specialProgram.getStatus()).desc());
		assetpoolListDTO.setManagerId(specialProgram.getManagerId());
		assetpoolListDTO.setManagerName(specialProgram.getManagerName());
		assetpoolListDTO.setLawFirmName(specialProgram.getLawFirmName());
		assetpoolListDTO.setAccountingFirmName(specialProgram.getAccountingFirmName());
		assetpoolListDTO.setSupervisionBank(specialProgram.getSupervisionBank());
		assetpoolListDTO.setCustodianBank(specialProgram.getCustodianBank());
		assetpoolListDTO.setRatingAgency(specialProgram.getRatingAgency());
		assetpoolListDTO.setAssetEvaluationAgency(specialProgram.getAssetEvaluationAgency());
		assetpoolListDTO.setOriginAssetpoolScale(MoneyArithUtil.convertMoneyToString(originAssetpoolScale));
		return assetpoolListDTO;
	}

	public static List<OriginatorDTO> Originator2OriginatorDTO(List<Originator> originators) {
		List<OriginatorDTO> originatorDTOs = new ArrayList<>();
		for(Originator originator : originators){
			OriginatorDTO originatorDTO = new OriginatorDTO();
			BeanUtils.copyProperties(originator, originatorDTO);
			originatorDTOs.add(originatorDTO);
		}
		return originatorDTOs;
	}

	public static List<ServiceAgencyDTO> ServiceAgency2ServiceAgencyDTO(List<ServiceAgency> serviceAgencys) {
		List<ServiceAgencyDTO> serviceAgencyDTOs = new ArrayList<>();
		for(ServiceAgency serviceAgency : serviceAgencys){
			ServiceAgencyDTO serviceAgencyDTO = new ServiceAgencyDTO();
			BeanUtils.copyProperties(serviceAgency, serviceAgencyDTO);
			serviceAgencyDTOs.add(serviceAgencyDTO);
		}
		return serviceAgencyDTOs;
	}

	public static AssetpoolListDTO specialProgram2AssetpoolDTO(SpecialProgramDTO specialProgramDTO,
			Map<String, UnderlyingAssetAmountSummary> specialProgramMoney, Map<String, Long> originAssetpoolScales) {
		String id = specialProgramDTO.getId(); 
		UnderlyingAssetAmountSummary underlyingSummary = specialProgramMoney.get(id);
		AssetpoolListDTO assetpoolListDTO = new AssetpoolListDTO();
		assetpoolListDTO.setId(id);
		assetpoolListDTO.setCyclePurchaseStructure(specialProgramDTO.getCyclePurchaseStructure());
		assetpoolListDTO.setSpecialProgramName(specialProgramDTO.getSpecialProgramName());
		assetpoolListDTO.setUnderlyingAssetType(specialProgramDTO.getUnderlyingAssetType());
		assetpoolListDTO.setUnderlyingAssetTypeDesc(specialProgramDTO.getUnderlyingAssetType().desc());
		assetpoolListDTO.setManagerId(specialProgramDTO.getManagerId());
		assetpoolListDTO.setManagerName(specialProgramDTO.getManagerName());
		assetpoolListDTO.setEstimateEstablishmentDate(specialProgramDTO.getEstimateEstablishmentDate());
		assetpoolListDTO.setOriginAssetpoolScale(MoneyArithUtil.convertMoneyToString(originAssetpoolScales.get(id)));
		assetpoolListDTO.setStatus(specialProgramDTO.getStatus());
		assetpoolListDTO.setStatusDesc(specialProgramDTO.getStatus().desc());
		assetpoolListDTO.setAccountIncome(MoneyArithUtil.convertMoneyToString(underlyingSummary.getAccountIncome()));
		assetpoolListDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(underlyingSummary.getAccountPrincipal()));
		assetpoolListDTO.setAccountAmount(MoneyArithUtil.convertMoneyToString(underlyingSummary.getAccountAmount()));
		assetpoolListDTO.setPaidIncome(MoneyArithUtil.convertMoneyToString(underlyingSummary.getPaidIncome()));
		assetpoolListDTO.setPaidPrincipal(MoneyArithUtil.convertMoneyToString(underlyingSummary.getPaidPrincipal()));
		assetpoolListDTO.setPaidAmount(MoneyArithUtil.convertMoneyToString(underlyingSummary.getPaidAmount()));
		assetpoolListDTO.setUnpaidAmount(MoneyArithUtil.convertMoneyToString(underlyingSummary.getUnpaidAmount()));
		return assetpoolListDTO;
	}

	public static List<AssetInPoolDTO> underlyingAsset2AssetInPoolDTO(List<UnderlyingAssetDTO> assetDTOs,
			Map<String, UnderlyingAssetAmountSummary> underlyingAssetAmountSummary) {
		List<AssetInPoolDTO> assetInPoolDTOs = new ArrayList<>();
		for (UnderlyingAssetDTO assetDTO : assetDTOs) {
			AssetInPoolDTO assetInPoolDTO = new AssetInPoolDTO();
			BeanUtils.copyProperties(assetDTO, assetInPoolDTO);
			String loanNo = assetDTO.getLoanNo();
			UnderlyingAssetAmountSummary summary = underlyingAssetAmountSummary.get(loanNo);
			assetInPoolDTO.setAccountInterest(MoneyArithUtil.convertMoneyToString(summary.getAccountInterest())); // 应还利息
			assetInPoolDTO.setAccountOverdue(MoneyArithUtil.convertMoneyToString(summary.getAccountOverdue())); // 应还逾期
			assetInPoolDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(summary.getAccountPrincipal())); // 应还本金
			assetInPoolDTO.setPaidInterest(MoneyArithUtil.convertMoneyToString(summary.getPaidInterest())); // 已还利息
			assetInPoolDTO.setPaidOverdue(MoneyArithUtil.convertMoneyToString(summary.getPaidOverdue())); // 已还逾期
			assetInPoolDTO.setPaidPrincipal(MoneyArithUtil.convertMoneyToString(summary.getPaidPrincipal())); // 已还本金
			
			assetInPoolDTO.setAccountIncome(MoneyArithUtil.convertMoneyToString(summary.getAccountIncome())); // 应还收入
			assetInPoolDTO.setAccountAmount(MoneyArithUtil.convertMoneyToString(summary.getAccountAmount())); // 应还总额
			assetInPoolDTO.setPaidIncome(MoneyArithUtil.convertMoneyToString(summary.getPaidIncome())); // 已还收入
			assetInPoolDTO.setPaidAmount(MoneyArithUtil.convertMoneyToString(summary.getPaidAmount())); // 已还总额
			assetInPoolDTO.setUnpaidAmount(MoneyArithUtil.convertMoneyToString(summary.getUnpaidAmount())); // 未偿总额
			assetInPoolDTOs.add(assetInPoolDTO);
		}
		return assetInPoolDTOs;
	}

}
