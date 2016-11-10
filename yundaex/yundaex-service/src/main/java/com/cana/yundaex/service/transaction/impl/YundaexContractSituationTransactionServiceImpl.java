package com.cana.yundaex.service.transaction.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.yundaex.common.dto.contract.ContractSituationRequestDTO;
import com.cana.yundaex.common.enums.ContractSignState;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.dao.mapper.gen.ContractSignSituationMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexPersonalInfoMapper;
import com.cana.yundaex.dao.po.ContractSignSituation;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;
import com.cana.yundaex.service.transaction.IYundaexContractSituationTransactionService;
import com.cana.yundaex.service.transaction.IYundaexCreditLimitTransactionService;

/**
 * @author hu
 *
 */
@Service
public class YundaexContractSituationTransactionServiceImpl implements IYundaexContractSituationTransactionService{

	@Resource
	private ContractSignSituationMapper contractSignSituationMapper;
	
	@Resource
	private YundaexPersonalInfoMapper personalInfoMapper;
	
	@Resource
	private IYundaexCreditLimitTransactionService creditTransactionService;
	@Override
	public void createOrUpdateContractSituation(ContractSituationRequestDTO requestDTO) {
		ContractSignSituation signSituation;
		if(StringUtils.isNotBlank(requestDTO.getId())){
			signSituation = contractSignSituationMapper.lockByPrimaryKey(requestDTO.getId());

			convertContractSituationDTO2Po(requestDTO, signSituation);
			contractSignSituationMapper.updateByPrimaryKeySelective(signSituation);
		}else{
			signSituation = new ContractSignSituation();
			if(StringUtils.isNotBlank(requestDTO.getCustomerId())) {
				signSituation.setId(requestDTO.getCustomerId());
			}
			convertContractSituationDTO2Po(requestDTO, signSituation);
			signSituation.setCreateTime(new Date());
			contractSignSituationMapper.insertSelective(signSituation);
		}
	}

	private void convertContractSituationDTO2Po(ContractSituationRequestDTO requestDTO, ContractSignSituation signSituation){
		if(StringUtils.isNotBlank(requestDTO.getStationName())) {
			signSituation.setStationName(requestDTO.getStationName());
		}
		if(StringUtils.isNotBlank(requestDTO.getFinanceContractSignState())) {
			signSituation.setFinanceContractSignState(requestDTO.getFinanceContractSignState());
		}
		if(StringUtils.isNotBlank(requestDTO.getCreditContractSignState())) {
			signSituation.setCreditContractSignState(requestDTO.getCreditContractSignState());
		}
		if(StringUtils.isNotBlank(requestDTO.getDutyContractSignState())) {
			signSituation.setDutyContractSignState(requestDTO.getDutyContractSignState());
		}
		if(StringUtils.isNotBlank(requestDTO.getProtocolNo())) {
			signSituation.setProtocolNo(requestDTO.getProtocolNo());
		}
		if(StringUtils.isNotBlank(requestDTO.getPayAccountName())) {
			signSituation.setPayAccountName(requestDTO.getPayAccountName());
		}
		if(StringUtils.isNotBlank(requestDTO.getPayAccountNo())) {
			signSituation.setPayAccountNo(requestDTO.getPayAccountNo());
		}
		if(StringUtils.isNotBlank(requestDTO.getPayAccountBank())) {
			signSituation.setPayAccountBank(requestDTO.getPayAccountBank());
		}
		if(StringUtils.isNotBlank(requestDTO.getPayLianHangNo())) {
			signSituation.setPayLianHangNo(requestDTO.getPayLianHangNo());
		}
		if(null != requestDTO.getSignCompleteTime()) {
			signSituation.setSignCompleteTime(requestDTO.getSignCompleteTime());;
		}
	}

	@Override
	public void signContract(String personalId, String customerId) {
		YundaexPersonalInfo infoPo = personalInfoMapper.lockByPrimaryKey(personalId);
		ContractSignSituation situationPo = contractSignSituationMapper.lockByPrimaryKey(customerId);
		if(!PersonalInfoType.CONTROLLER.name().equals(infoPo.getType())){
			situationPo.setCreditContractSignState(ContractSignState.SIGNED.name());
		}
		if(!PersonalInfoType.ACCOUNT_HOLDER.name().equals(infoPo.getType())){
			situationPo.setDutyContractSignState(ContractSignState.SIGNED.name());
		}
		if(equalsAnyParam(ContractSignState.SIGNED.name(), 
				situationPo.getFinanceContractSignState(), 
				situationPo.getCreditContractSignState(), 
				situationPo.getDutyContractSignState())){
			situationPo.setSignCompleteTime(new Date());
			creditTransactionService.activateCreditLimit(customerId);//激活额度
		}
		contractSignSituationMapper.updateByPrimaryKeySelective(situationPo);
		infoPo.setSecurityCode("");
		personalInfoMapper.updateByPrimaryKeySelective(infoPo);
	}
	
	private boolean equalsAnyParam(String first, String...params){
		for (String param : params) {
			if(StringUtils.isNotBlank(param)){
				if(!first.equals(param))
					return false;		
			}
		}
		return true;
	}
	
}
