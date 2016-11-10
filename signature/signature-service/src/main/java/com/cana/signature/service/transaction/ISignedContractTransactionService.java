package com.cana.signature.service.transaction;

import com.cana.signature.dao.po.SignatureContract;

/**
 * @author hu
 *
 */
public interface ISignedContractTransactionService {

	/**
	 * 保存或更新签名合同
	 * @param SignatureContract
	 */
	public void insertOrUpdateSignatureContract(SignatureContract signatureContract);
}
