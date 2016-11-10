package com.cana.signature.service;

import com.cana.vbam.common.signature.dto.CertApplyDTO;
import com.cana.vbam.common.signature.dto.CertDTO;
import com.cana.vbam.common.signature.dto.CertResultDTO;

import cfca.ra.toolkit.exception.RATKException;

/**
 * @author hu
 *
 */
public interface ICertManageService {

	/**
	 * @param certApplyDTO
	 * @return
	 * 申请并下载证书
	 */
	public CertResultDTO applyAndDownloadCert(CertApplyDTO certApplyDTO) throws RATKException;
	
	/**
	 * @return
	 * 证书补发
	 */
	public CertResultDTO reissueCert(String dn, String useOldKey) throws RATKException;
	
	/**
	 * @param serialNo
	 * @param authCode
	 * @param p10
	 * @return
	 * @throws RATKException
	 * 制证
	 */
	public CertResultDTO downloadCert(String serialNo, String authCode, String p10) throws RATKException;
	
	/**
	 * @param dn
	 * @param serialNo
	 * @return
	 * 唯一查询证书
	 */
	public CertDTO queryCertUnique(String dn, String serialNo) throws RATKException;
	
}
