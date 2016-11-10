package com.cana.signature.server.api.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.member.api.IUserApi;
import com.cana.signature.api.ICertApplyApi;
import com.cana.signature.service.ICertManageService;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.signature.dto.CertApplyDTO;
import com.cana.vbam.common.signature.dto.CertResultDTO;
import com.cana.vbam.common.signature.enums.BranchCode;
import com.cana.vbam.common.signature.enums.CertCustomerType;
import com.cana.vbam.common.signature.enums.IdentityType;
import com.travelzen.framework.core.exception.WebException;

import cfca.ra.toolkit.exception.RATKException;

/**
 * @author hu
 *
 */
public class CertApplyApiImpl implements ICertApplyApi {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	IUserApi userApiImpl;
	
	@Resource
	ICertManageService certManageServiceImpl;
	
	@Override
	public CertResultDTO applyOrReissueCert(String userId, String p10) {
		
		CustomerDetailDTO userDetail = userApiImpl.queryCustomerDetail(userId);
		
		CertResultDTO certResult = null;
		if(StringUtils.isNotBlank(userDetail.getCertSubjectDn())){
			logger.info("{} 证书已经申请过, 进行证书补发!", userId);
			try {
				CertResultDTO certReissueResult = certManageServiceImpl.reissueCert(userDetail.getCertSubjectDn(), "true");
				certResult = certManageServiceImpl.downloadCert(certReissueResult.getSerialNo(), certReissueResult.getAuthCode(), p10);
			} catch (RATKException e) {
				logger.error("证书补发异常!",e);
				throw WebException.instance("证书补发请求失败:"+e.getMessage());
			}
		}else{
			logger.info("{} 证书未申请过, 进行证书申请!", userId);
			CertApplyDTO certApplyDTO = new CertApplyDTO();
			if(StringUtils.isBlank(userDetail.getIdentityCardNo())){
				certApplyDTO.setCustomerType(CertCustomerType.ENTERPRISE.number());
				certApplyDTO.setIdentType(IdentityType.BUSINESS_LICENSE.number());
				certApplyDTO.setIdentNo(userDetail.getBusinessLicenceCode());
			}else{
				certApplyDTO.setIdentType(IdentityType.RESIDENT_IDENTITY_CARD.number());
				certApplyDTO.setIdentNo(userDetail.getIdentityCardNo());
			}
			certApplyDTO.setUserName(userDetail.getCompanyName());
			certApplyDTO.setBranchCode(BranchCode.CANABRANCHCODE.number());
			certApplyDTO.setP10(p10);
			
			try {
				certResult = certManageServiceImpl.applyAndDownloadCert(certApplyDTO);
			} catch (RATKException e) {
				logger.error("申请证书异常!",e);
				throw WebException.instance("申请证书请求失败:"+e.getMessage());
			}
			userApiImpl.updateCustomerCertDN(userId, certResult.getSubjectDN());
		}
		
		return certResult;
	}

	@Override
	public CertResultDTO applyCert(CertApplyDTO certApplyDTO) {
		logger.info("{} 进行证书申请!", certApplyDTO.getUserName());
		CertResultDTO certResult = null;
		try {
			certResult = certManageServiceImpl.applyAndDownloadCert(certApplyDTO);
		} catch (RATKException e) {
			logger.error("申请证书异常!",e);
			throw WebException.instance("申请证书请求失败:"+e.getMessage());
		}
		return certResult;
	}

	@Override
	public CertResultDTO reissueCert(String subjectDn, String p10) {
		logger.info("{} 进行证书补发!", subjectDn);
		CertResultDTO certResult = null;
		try {
			CertResultDTO certReissueResult = certManageServiceImpl.reissueCert(subjectDn, "true");
			certResult = certManageServiceImpl.downloadCert(certReissueResult.getSerialNo(), certReissueResult.getAuthCode(), p10);
		} catch (RATKException e) {
			logger.error("证书补发异常!",e);
			throw WebException.instance("证书补发请求失败:"+e.getMessage());
		}
		return certResult;
	}

}
