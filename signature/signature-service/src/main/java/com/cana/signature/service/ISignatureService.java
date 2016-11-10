package com.cana.signature.service;

import com.cana.vbam.common.signature.enums.SignType;

import cfca.ra.toolkit.exception.RATKException;
import cfca.sadk.algorithm.common.PKIException;

/**
 * @author hu
 *
 */
public interface ISignatureService {

	public void verifySignMessage(byte[] signData, String dn, byte[] soruceData, SignType signType, String contract)throws PKIException,RATKException; ;
}
