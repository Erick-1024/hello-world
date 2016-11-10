package com.cana.signature.server.api.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.signature.api.ISignatureApi;
import com.cana.signature.service.ISignatureService;
import com.cana.vbam.common.signature.enums.SignType;
import com.travelzen.framework.core.exception.WebException;

import cfca.ra.toolkit.exception.RATKException;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.util.Base64;

/**
 * @author hu
 *
 */
public class SignatureApiImpl implements ISignatureApi{

	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Resource
	ISignatureService signatureAerviceImpl;
	
	@Override
	public void verifySignMessage(byte[] signData, String dn, byte[] soruceData, SignType signType, String contractId) {
		
		try {
			signatureAerviceImpl.verifySignMessage(signData, dn, Base64.encode(soruceData), signType, contractId);
		} catch (PKIException | RATKException e) {
			logger.error("验签失败!",e);
			throw WebException.instance("验签失败:"+e.getMessage());
		}
	}

}
