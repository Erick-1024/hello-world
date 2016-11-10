package com.cana.asset.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetContractMapper;
import com.cana.asset.dao.po.AssetContract;
import com.cana.asset.dao.po.AssetContractExample;
import com.cana.asset.service.convertors.ContractConvertor;
import com.cana.asset.service.transaction.IAssetTransactionService;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractListReqDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class AssetTransactionServiceImpl implements IAssetTransactionService {
		private final Logger logger = LoggerFactory.getLogger(getClass());
	 	
		@Resource
	    private AssetContractMapper contractMapper;
	    
	    @Resource
		private SequenceGenerator seqGen;
	    
	    @Override
	    public ListResult<ContractInfoDTO> getCompanyContracts(ContractListReqDTO request,String masterId) {
	        AssetContractExample example=new AssetContractExample();
	        example.createCriteria().andMemberIdEqualTo(masterId).andDeletedEqualTo(Boolean.FALSE);
	        example.setOrderByClause("create_time desc");
	        example.setLimitStart((request.getPage()-1)*request.getPageSize());
	        example.setLimitEnd(request.getPageSize());
	        List<AssetContract> contracts=contractMapper.selectByExample(example);
	        List<ContractInfoDTO> contractDTOs=ContractConvertor.convertContractDao2ResDTO(contracts);
	        int count=contractMapper.countByExample(example);
	        return ListResult.success(contractDTOs,count);
	    }
	    
	    @Override
		public void updateContractByMemberIdAndProductId(ContractInfoDTO contractInfoDTO) throws Exception {
	    	AssetContractExample example = new AssetContractExample();
	    	example.createCriteria().andMemberIdEqualTo(contractInfoDTO.getMemberId()).andProductIdEqualTo(contractInfoDTO.getProductId()).andDeletedEqualTo(Boolean.FALSE);
	    	List<AssetContract> contracts = contractMapper.selectByExample(example);
	    	if(CollectionUtils.isNotEmpty(contracts)){
	    		if(contracts.size() > 1)
	    			throw WebException.instance(ReturnCode.TP3031);
	    		AssetContract contract = contracts.get(0);
	    		logger.info("删除合同信息:{}",new Gson().toJson(contract));
	    		contract.setDeleted(Boolean.TRUE);
	    		contractMapper.updateByPrimaryKey(contract);
	    	}
    		AssetContract contract = new AssetContract();
			contract.setId(contractInfoDTO.getContractId());
			contract.setProductId(contractInfoDTO.getProductId());
			contract.setMemberId(contractInfoDTO.getMemberId());
			contract.setFactorId(contractInfoDTO.getFactorId());
			contract.setMediaId(contractInfoDTO.getMediaId());
			contract.setFileName(contractInfoDTO.getFileName());
			contract.setFileSuffix(contractInfoDTO.getFileSuffix());
			contract.setAccountNo(contractInfoDTO.getAccountNo());
			contract.setAccountSupervisionId(contractInfoDTO.getAccountSupervisionId());
			contract.setEffectiveDate(contractInfoDTO.getEffectiveDate());
			contract.setDueDate(contractInfoDTO.getDueDate());
			contract.setCreateTime(new Date());
	    	contractMapper.insertSelective(contract);
		}
		
		@Override
		public String generateContractId() throws Exception {
			return DateTimeUtil.date8() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CONTRACT_ID, 4);
		}

		@Override
		public List<ContractInfoDTO> getContractsWithoutPaging(ContractQueryCriteria contractQueryCriteria) {
			AssetContractExample example = new AssetContractExample();
			AssetContractExample.Criteria criteria = example.createCriteria();
			ContractConvertor.getValidCriteria(criteria,contractQueryCriteria);
			List<AssetContract> contracts = contractMapper.selectByExample(example);
			List<ContractInfoDTO> contractInfoDTOs = ContractConvertor.convertContractDao2ResDTO(contracts);
			return contractInfoDTOs;
		}
		
	@Override
	public String getAccountNoByTravelzenFinanceId(String financeId) {
		AssetContractExample example = new AssetContractExample();
		example.createCriteria().andMemberIdEqualTo(financeId)
				.andProductIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID).andDeletedEqualTo(Boolean.FALSE);
		List<AssetContract> contracts = contractMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(contracts) || contracts.size() > 1) {
			return null;
		}
		return contracts.get(0).getAccountNo();
	}
	
	@Override
	public ContractInfoDTO getContractInfoByMemberId(String memberId, String productId) {
		AssetContractExample example = new AssetContractExample();
		AssetContractExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(memberId)) {
			criteria.andMemberIdEqualTo(memberId);
		}
		if (StringUtils.isNotBlank(productId)) {
			criteria.andProductIdEqualTo(productId);
		}
		criteria.andDeletedEqualTo(Boolean.FALSE);
		List<AssetContract> contracts = contractMapper.selectByExample(example);
		List<ContractInfoDTO> contractDTOs=ContractConvertor.convertContractDao2ResDTO(contracts);
		if (CollectionUtils.isEmpty(contractDTOs) || contractDTOs.size() > 1) {
			return null;
		}
		return contractDTOs.get(0);
	}

	@Override
	public boolean checkContractExistByTravelzenFinanceId(String financeId) {
		AssetContractExample example = new AssetContractExample();
		example.createCriteria().andMemberIdEqualTo(financeId)
				.andProductIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID).andDeletedEqualTo(Boolean.FALSE);
		List<AssetContract> contracts = contractMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(contracts);
	}

	/**
	 * 创建合同记录
	 */
	@Override
	public void createContract(ContractInfoDTO contractInfoDTO) {
		AssetContract contract = new AssetContract();
		contract.setId(contractInfoDTO.getContractId());
		contract.setProductId(contractInfoDTO.getProductId());
		contract.setMemberId(contractInfoDTO.getMemberId());
		contract.setFactorId(contractInfoDTO.getFactorId());
		contract.setMediaId(contractInfoDTO.getMediaId());
		contract.setFileName(contractInfoDTO.getFileName());
		contract.setFileSuffix(contractInfoDTO.getFileSuffix());
		contract.setAccountNo(contractInfoDTO.getAccountNo());
		contract.setAccountSupervisionId(contractInfoDTO.getAccountSupervisionId());
		contract.setEffectiveDate(contractInfoDTO.getEffectiveDate());
		contract.setDueDate(contractInfoDTO.getDueDate());
		contract.setCreateTime(new Date());
    	contractMapper.insertSelective(contract);
	}

	/**
	 * 根据合同ID获取韵达合同信息
	 */
	@Override
	public ContractInfoDTO getContractInfoByProtocolNo(String protocolNo) {
		AssetContractExample example = new AssetContractExample();
		AssetContractExample.Criteria criteria = example.createCriteria();
		
		criteria.andIdEqualTo(protocolNo); // 合同号
		criteria.andDeletedEqualTo(Boolean.FALSE);
		List<AssetContract> contracts = contractMapper.selectByExample(example);
		List<ContractInfoDTO> contractDTOs=ContractConvertor.convertContractDao2ResDTO(contracts);
		if (CollectionUtils.isEmpty(contractDTOs) || contractDTOs.size() > 1) {
			return null;
		}
		return contractDTOs.get(0);
	}
	
	
}
