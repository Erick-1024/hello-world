package com.cana.signature.service.transaction.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.signature.dao.mapper.gen.SignatureContractMapper;
import com.cana.signature.dao.po.SignatureContract;
import com.cana.signature.service.transaction.ISignedContractTransactionService;

/**
 * @author hu
 *
 */
@Service
public class SignedContractTransactionServiceImpl implements ISignedContractTransactionService{

	@Resource
	private SignatureContractMapper signatureContractMapper;
	
	@Override
	public void insertOrUpdateSignatureContract(SignatureContract signatureContract) {
		SignatureContract signatureContractPo = signatureContractMapper.selectByPrimaryKey(signatureContract.getId());
		if(null == signatureContractPo){
			signatureContractMapper.insertSelective(signatureContract);
		}else{
			signatureContractMapper.updateByPrimaryKeySelective(signatureContract);
		}
		
	}

}
