package com.cana.signature.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.signature.dao.mapper.gen.SignatureContractMapper;
import com.cana.signature.dao.po.SignatureContract;
import com.cana.signature.service.ICertManageService;
import com.cana.signature.service.ISignatureService;
import com.cana.signature.service.transaction.ISignedContractTransactionService;
import com.cana.signature.utils.SigntureUtils;
import com.cana.vbam.common.signature.dto.CertDTO;
import com.cana.vbam.common.signature.enums.CertStatus;
import com.cana.vbam.common.signature.enums.SignType;
import com.travelzen.framework.core.exception.WebException;

import cfca.ra.toolkit.exception.RATKException;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @author hu
 *
 */
@Service
public class SignatureServiceImpl implements ISignatureService{
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private ICertManageService certManageService;
	
	@Resource
	private ISignedContractTransactionService signedContractTransactionServiceImpl;
	
	private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");
	
	@Override
	public void verifySignMessage(byte[] signData, String dn, byte[] source, SignType signType, String contractId) throws PKIException, RATKException {
		checkSignedParams(signData, dn, source, signType);
		Session session = SigntureUtils.initSignSession();
		final Signature signature = new Signature();
		//校验数据正确性
		if(SignType.ATTACH.equals(signType)){
			log.info("带原文消息签名!");
			boolean verifyResult = signature.p7VerifyMessageAttach(signData, session);
			
			if(!verifyResult || !Arrays.equals(source, signature.getSourceData())){
				throw WebException.instance("验签失败");
			}
		}else if(SignType.DETACH.equals(signType)){
			log.info("分离式消息签名!");
			boolean verifyResult = signature.p7VerifyMessageDetach(source, signData, session);
			if(!verifyResult){
				throw WebException.instance("验签失败");
			}
		}
		
		log.info("签名cfca校验结果: true");
		log.info("原文:"+source);
		X509Cert cert = signature.getSignerCert();
		log.info("证书信息:" + cert.getStringSerialNumber()+"; 颁发者DN:"+cert.getIssuer() +"; 主题DN"+cert.getSubject()+"; 公钥"+cert.getPublicKey());
		//校验用户正确性
		if(!StringUtils.equals(dn.replace(" ", ""), cert.getSubject().replace(" ", ""))){
			throw WebException.instance("签名证书与用户不一致");
		}
		//校验证书状态
		CertDTO certDTO = certManageService.queryCertUnique(dn, null);
		if(!CertStatus.ACTIVATION.number().equals(certDTO.getCertStatus())){
			log.info("证书状态不是激活状态:{}",certDTO.getCertStatus());
			throw WebException.instance("证书状态不是激活状态");
		}
		
		DateTime endTime = DateTime.parse(certDTO.getEndTime(), formatter);
		DateTime nowTime = new DateTime();
		if(endTime.isBefore(nowTime)){
			log.info("证书过期:{}",certDTO.getEndTime());
			throw WebException.instance("证书已过期");
		}
		
		//保存签名数据
		SignatureContract contractData = new  SignatureContract();
		contractData.setId(contractId);
		contractData.setContractSignedData(signData);
		contractData.setContractSourceData(source);
		signedContractTransactionServiceImpl.insertOrUpdateSignatureContract(contractData);
	}

	private void checkSignedParams(byte[] signData, String dn, byte[] source, SignType signType){
		if(StringUtils.isBlank(dn)){
			throw WebException.instance("该用户未申请证书!");
		}
		if(signData == null || source == null){
			throw WebException.instance("签名或原数据不能为空");
		}
		if(signType == null){
			throw WebException.instance("签名方式不能为空");
		}
	}
}
