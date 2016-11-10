package com.cana.yundaex.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.yundaex.api.IYundaexContractSituationApi;
import com.cana.yundaex.common.dto.contract.ContractSituationQueryCriteria;
import com.cana.yundaex.common.dto.contract.ContractSituationRequestDTO;
import com.cana.yundaex.common.dto.contract.ContractSituationResultDTO;
import com.cana.yundaex.common.enums.ContractCompleteState;
import com.cana.yundaex.common.enums.ContractSignState;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.dao.mapper.gen.ContractSignSituationMapper;
import com.cana.yundaex.dao.po.ContractSignSituation;
import com.cana.yundaex.dao.po.ContractSignSituationExample;
import com.cana.yundaex.dao.po.ContractSignSituationExample.Criteria;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.convertors.YundaexContractConvert;
import com.cana.yundaex.service.transaction.IYundaexContractSituationTransactionService;
import com.cana.yundaex.service.transaction.IYundaexPersonalInfoTransactionService;
import com.cana.yundaex.service.utils.YundaexIDGenerator;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
public class YundaexContractSituationApiImpl implements IYundaexContractSituationApi{

	@Resource
	private ContractSignSituationMapper contractSignSituationMapper;
	
	@Resource
	private IYundaexContractSituationTransactionService contractSituationTransactionService;
	
	@Resource 
	private YundaexContractConvert contractConvert;
	
	@Resource
	private IYundaexPersonalInfoTransactionService personalInfoTransactionService;
	
	@Resource
	private IYundaexMessageService messageService;
	
	@Resource
	private IUserApi useApi;
	
	@Override
	public void createOrUpdateContractSituation(ContractSituationRequestDTO requestDTO) {
		contractSituationTransactionService.createOrUpdateContractSituation(requestDTO);
	}

	@Override
	public PageResult<ContractSituationResultDTO> findContractSituationByCondition(ContractSituationQueryCriteria queryCriteria) {
		ContractSignSituationExample example = new ContractSignSituationExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(queryCriteria.getStationName())){
			criteria.andStationNameLike("%"+queryCriteria.getStationName()+"%");
		}
		if(StringUtils.isNotBlank(queryCriteria.getComleteStartTime())){
			criteria.andSignCompleteTimeGreaterThanOrEqualTo(new DateTime(queryCriteria.getComleteStartTime()).toDate());
		}
		if(StringUtils.isNotBlank(queryCriteria.getComleteEndTime())){
			criteria.andSignCompleteTimeLessThan(new DateTime(queryCriteria.getComleteEndTime()).toDate());
		}
		if(StringUtils.isNotBlank(queryCriteria.getContractSignState())){
			if(queryCriteria.getContractSignState().equals(ContractCompleteState.COMPLETE.name())){
				criteria.andSignCompleteTimeIsNotNull();
			}else{
				criteria.andSignCompleteTimeIsNull();
			}
		}
		int totelRecord = contractSignSituationMapper.countByExample(example);
		int page = queryCriteria.getPage() < 1 ? 1 : queryCriteria.getPage();
		example.setLimitStart((page-1)*queryCriteria.getPageSize());
		example.setLimitEnd(queryCriteria.getPageSize());
		example.setOrderByClause("create_time desc");
		List<ContractSignSituation> contractSignSituationList = contractSignSituationMapper.selectByExample(example);
		return new PageResult<>(contractConvert.convertContractSituationList2DTO(contractSignSituationList), totelRecord);
		
	}
	
	@Override
	public ListResult<String> resendContract(String id){
		ContractSignSituation situation = contractSignSituationMapper.selectByPrimaryKey(id);
		if(null == situation){
			throw WebException.instance("参数有误");
		}
		List<String> typeList = Lists.newArrayList();
		if(ContractSignState.UNSIGNED.name().equals(situation.getCreditContractSignState())){
			typeList.add(PersonalInfoType.ACCOUNT_HOLDER.name());
		}
		if(ContractSignState.UNSIGNED.name().equals(situation.getDutyContractSignState())){
			typeList.add(PersonalInfoType.CONTROLLER.name());
		}
		if(typeList.size() == 2){
			typeList.add(PersonalInfoType.ACCOUNT_HOLDER_AND_CONTROLLER.name());
		}
		return personalInfoTransactionService.resendSignContractLink(id, typeList);
	}

	@Override
	public ContractSituationResultDTO getContractSituationById(String id){
		ContractSignSituation situation = contractSignSituationMapper.selectByPrimaryKey(id);
		return contractConvert.convertPersonalInfo2DTO(situation);
	}
	
	@Override
	public String generateFinanceContractSerialNumber(String prefix) {
		
		return YundaexIDGenerator.generateContractSerialNumber(prefix);
	}

	@Override
	public void signContract(String personalId, String customerId) {
		
		contractSituationTransactionService.signContract(personalId, customerId);
	}

	@Override
	public void sendFinanceContractSignedMessage(String customerId) {
		CustomerDetailDTO detailDTO = useApi.queryCustomerDetail(customerId);
		messageService.sendFinanceContractSignedMailAndSMS(detailDTO.getCompanyName(), detailDTO.getContactMail(), detailDTO.getContactTel());
	}

	@Override
	public String generateSingleLoanNumber() {
		return YundaexIDGenerator.generateSingleLoanNumber();
	}

}
