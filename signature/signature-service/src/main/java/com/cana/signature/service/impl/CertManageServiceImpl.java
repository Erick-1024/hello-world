package com.cana.signature.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.signature.service.ICertManageService;
import com.cana.signature.utils.RAClientConfig;
import com.cana.vbam.common.signature.dto.CertApplyDTO;
import com.cana.vbam.common.signature.dto.CertDTO;
import com.cana.vbam.common.signature.dto.CertResultDTO;
import com.cana.vbam.common.signature.enums.CertOperationType;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.request.QueryRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.common.vo.response.QueryResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

/**
 * @author hu
 *
 */
@Service
public class CertManageServiceImpl implements ICertManageService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public CertResultDTO applyAndDownloadCert(CertApplyDTO certApplyDTO) throws RATKException {
		verifyCertApplyDTO(certApplyDTO);
        
        RAClient client = RAClientConfig.getRAClient();
        CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
        certServiceRequestVO.setTxCode(CertOperationType.APPLY_AND_DOWNLOAD.number());
        certServiceRequestVO.setCertType(certApplyDTO.getCertType());
        certServiceRequestVO.setCustomerType(certApplyDTO.getCustomerType());
        certServiceRequestVO.setUserName(certApplyDTO.getUserName());
        certServiceRequestVO.setIdentType(certApplyDTO.getIdentType());
        certServiceRequestVO.setIdentNo(certApplyDTO.getIdentNo());
        certServiceRequestVO.setKeyLength(certApplyDTO.getKeyLength());
        certServiceRequestVO.setKeyAlg(certApplyDTO.getKeyAlg());
        certServiceRequestVO.setBranchCode(certApplyDTO.getBranchCode());
        certServiceRequestVO.setEmail(certApplyDTO.getEmail());
        certServiceRequestVO.setP10(certApplyDTO.getP10());
        CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

        logger.info("证书申请并下载结果:{}", new Gson().toJson(certServiceResponseVO));
        CertResultDTO certResult = new CertResultDTO();
        certResult.setResultStatus(certServiceResponseVO.getResultCode());
        certResult.setResultMessage(certServiceResponseVO.getResultMessage());
        if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
            certResult.setSubjectDN(certServiceResponseVO.getDn());
            certResult.setSerialNo(certServiceResponseVO.getSerialNo());
            certResult.setStartTime(certServiceResponseVO.getStartTime());
            certResult.setEndTime(certServiceResponseVO.getEndTime());
            certResult.setSignatureCert(certServiceResponseVO.getSignatureCert());
            return certResult;
        }else{
        	logger.error("申请证书并下载请求失败:状态码{}, 错误{}", certResult.getResultStatus(), certResult.getResultMessage());
        	throw WebException.instance("申请证书并下载请求失败");
        }
	}

	@Override
	public CertResultDTO reissueCert(String dn, String useOldKey) throws RATKException {
		if(StringUtils.isBlank(dn)){
			throw WebException.instance("dn不能为空!");
		}
		RAClient client = RAClientConfig.getRAClient();
		CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
		certServiceRequestVO.setTxCode(CertOperationType.REISSUE.number());
		// certServiceRequestVO.setLocale(locale);
		certServiceRequestVO.setDn(dn);
		// certServiceRequestVO.setKeyLength(keyLength);
		// certServiceRequestVO.setKeyAlg(keyAlg);
		certServiceRequestVO.setUseOldKey(useOldKey);
		
		CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);
		
		logger.info("证书补发结果:{}", new Gson().toJson(certServiceResponseVO));
		CertResultDTO certResult = new CertResultDTO();
        certResult.setResultStatus(certServiceResponseVO.getResultCode());
        certResult.setResultMessage(certServiceResponseVO.getResultMessage());
		if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
		    certResult.setSerialNo(certServiceResponseVO.getSerialNo());
		    certResult.setAuthCode(certServiceResponseVO.getAuthCode());
		    certResult.setStartTime(certServiceResponseVO.getStartTime());
		    certResult.setEndTime(certServiceResponseVO.getEndTime());
		    return certResult;
		}else{
        	logger.error("证书补发请求失败:状态码{}, 错误{}", certResult.getResultStatus(), certResult.getResultMessage());
        	throw WebException.instance("证书补发请求失败");
        }
	}

	@Override
	public CertResultDTO downloadCert(String serialNo, String authCode, String p10) throws RATKException {
		RAClient client = RAClientConfig.getRAClient();

        CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
        certServiceRequestVO.setTxCode(CertOperationType.DOWNLOAD.number());
//        certServiceRequestVO.setLocale(locale);
        certServiceRequestVO.setSerialNo(serialNo);
        certServiceRequestVO.setAuthCode(authCode);
        certServiceRequestVO.setP10(p10);
//        certServiceRequestVO.setP10Sub(p10Sub);

        CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

        logger.info("证书制证结果:{}", new Gson().toJson(certServiceResponseVO));
        CertResultDTO certResult = new CertResultDTO();
        certResult.setResultStatus(certServiceResponseVO.getResultCode());
        certResult.setResultMessage(certServiceResponseVO.getResultMessage());
        if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
            certResult.setSignatureCert(certServiceResponseVO.getSignatureCert());
            return certResult;
        }else{
        	logger.error("制证请求失败:状态码{}, 错误{}", certResult.getResultStatus(), certResult.getResultMessage());
        	throw WebException.instance("制证请求失败");
        }
	}
	
	@Override
	public CertDTO queryCertUnique(String dn, String serialNo) throws RATKException {
		if(StringUtils.isBlank(dn) && StringUtils.isBlank(serialNo)){
			throw WebException.instance("dn和序列号不能同时为空!");
		}
		RAClient client = RAClientConfig.getRAClient();

        QueryRequestVO queryRequestVO = new QueryRequestVO();
        queryRequestVO.setTxCode(CertOperationType.SEARCH_UNIQUE.number());
        if(StringUtils.isNotBlank(serialNo)){
        	queryRequestVO.setSerialNo(serialNo);
        }
        if(StringUtils.isNotBlank(dn)){
        	queryRequestVO.setDn(dn);
        }

        QueryResponseVO queryResponseVO = (QueryResponseVO) client.process(queryRequestVO);

        logger.info("证书唯一查询结果:{}", new Gson().toJson(queryResponseVO));
        CertDTO certDTO = new CertDTO();
        certDTO.setResultStatus(queryResponseVO.getResultCode());
        certDTO.setResultMessage(queryResponseVO.getResultMessage());
        if (RAClient.SUCCESS.equals(queryResponseVO.getResultCode())) {
        	certDTO.setCertType(queryResponseVO.getCertType());
        	certDTO.setCustomerType(queryResponseVO.getCustomerType());
        	certDTO.setSubjectDN(queryResponseVO.getDn());
        	certDTO.setSerialNo(queryResponseVO.getSerialNo());
        	certDTO.setCertStatus(queryResponseVO.getCertStatus());
        	certDTO.setDuration(queryResponseVO.getDuration());
        	certDTO.setApplyTime(queryResponseVO.getApplyTime());
        	certDTO.setStartTime(queryResponseVO.getStartTime());
        	certDTO.setEndTime(queryResponseVO.getEndTime());
        	certDTO.setKeyAlg(queryResponseVO.getKeyAlg());
        	certDTO.setKeyLength(queryResponseVO.getKeyLength());
        	certDTO.setBranchCode(queryResponseVO.getBranchCode());
        }else{
        	logger.error("唯一查询证书请求失败:状态码{}, 错误{}", certDTO.getResultStatus(), certDTO.getResultMessage());
        	throw WebException.instance("唯一查询证书请求失败");
        }
		return certDTO;
	}

	private void verifyCertApplyDTO(CertApplyDTO certApplyDTO){
		if(null == certApplyDTO){
			throw WebException.instance("参数异常!");
		}
		if(StringUtils.isBlank(certApplyDTO.getCertType())){
			throw WebException.instance("申请证书类型不能为空!");
		}
		if(StringUtils.isBlank(certApplyDTO.getCustomerType())){
			throw WebException.instance("申请证书客户类型不能为空!");
		}
		if(StringUtils.isBlank(certApplyDTO.getUserName())){
			throw WebException.instance("申请用户名不能为空!");
		}
		if(StringUtils.isBlank(certApplyDTO.getKeyAlg())){
			throw WebException.instance("申请密钥类型不能为空!");
		}
		if(StringUtils.isBlank(certApplyDTO.getKeyLength())){
			throw WebException.instance("申请密钥长度不能为空!");
		}
		if(StringUtils.isBlank(certApplyDTO.getP10())){
			throw WebException.instance("申请书不能为空!");
		}
	}
}
