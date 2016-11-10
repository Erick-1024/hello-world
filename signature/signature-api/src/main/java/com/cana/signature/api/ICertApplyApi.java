package com.cana.signature.api;

import com.cana.vbam.common.signature.dto.CertApplyDTO;
import com.cana.vbam.common.signature.dto.CertResultDTO;

/**
 * @author hu
 *
 */
public interface ICertApplyApi {

	/**
	 * 申请企业证书
	 * @param userId
	 * @param p10
	 * @return
	 */
	public CertResultDTO applyOrReissueCert(String userId, String p10);
	
	/**
	 * 申请证书
	 * @param certApplyDTO
	 * @return
	 */
	public CertResultDTO applyCert(CertApplyDTO certApplyDTO);
	
	/**
	 * 补发证书
	 * @param subjectDn
	 * @param p10
	 * @return
	 */
	public CertResultDTO reissueCert(String subjectDn, String p10);
}
