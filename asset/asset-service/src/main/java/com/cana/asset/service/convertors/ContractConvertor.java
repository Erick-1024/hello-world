package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.cana.asset.dao.po.AssetContract;
import com.cana.asset.dao.po.AssetContractExample;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.member.enums.user.ContractFileType;
import com.travelzen.framework.util.DateUtils;

public class ContractConvertor {
    
    public static List<ContractInfoDTO> convertContractDao2ResDTO(List<AssetContract> contracts){
        List<ContractInfoDTO> contractListDTOs=new ArrayList<ContractInfoDTO>();
        for(AssetContract contract:contracts){
        	ContractInfoDTO response=new ContractInfoDTO();
        	response.setContractId(contract.getId());
        	response.setProductId(contract.getProductId());
        	response.setMemberId(contract.getMemberId());
        	response.setFactorId(contract.getFactorId());
        	response.setMediaId(contract.getMediaId());
        	response.setFileName(contract.getFileName()+"."+contract.getFileSuffix());
        	response.setFileSuffix(contract.getFileSuffix());
        	response.setAccountNo(contract.getAccountNo());
        	response.setAccountSupervisionId(contract.getAccountSupervisionId());
        	response.setEffectiveDate(contract.getEffectiveDate());
        	response.setDueDate(contract.getDueDate());
            response.setFileType(ContractFileType.valueOf(contract.getFileSuffix()).desc());
            response.setCreateDate(DateUtils.format(contract.getCreateTime(), 19));
            contractListDTOs.add(response);
        }
        return contractListDTOs;
    }
    
    public static void getValidCriteria(AssetContractExample.Criteria criteria,ContractQueryCriteria contractQueryCriteria){
    	if(StringUtils.isNotBlank(contractQueryCriteria.getMemberId()))
    		criteria.andMemberIdEqualTo(contractQueryCriteria.getMemberId());
    	if(StringUtils.isNotBlank(contractQueryCriteria.getProductId()))
    		criteria.andProductIdEqualTo(contractQueryCriteria.getProductId());
    	criteria.andDeletedEqualTo(Boolean.FALSE);
    }

}
