package com.cana.signature.api;

import com.cana.vbam.common.signature.enums.SignType;

/**
 * @author hu
 *
 */
public interface ISignatureApi {

	/**
	 * 消息验签
	 * @param signData
	 * @param dn
	 * @param soruceData
	 * @param signType
	 */
	public void verifySignMessage(byte[] signData, String dn, byte[] soruceData, SignType signType, String contractId);
}
