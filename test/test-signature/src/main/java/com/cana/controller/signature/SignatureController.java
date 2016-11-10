package com.cana.controller.signature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.controller.utils.RAClientConfig;
import com.cana.controller.utils.SigntureUtils;
import com.cana.vbam.common.dto.ObjectResult;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

@Controller
@RequestMapping("/facade")
public class SignatureController {

	@RequestMapping("/apply/cert/page")
	public String applyCertPage(){
		return "/facade/applyCertPage";
	}
	
	@RequestMapping("/apply/cert")
	@ResponseBody
	public ObjectResult<String> applyCert(String p10) throws RATKException{
		String certType = "1";
        String customerType = "1";
        String userName = "huzhiwen22sddd33";
        // String userNameInDn = "testName";
        // String userIdent = "Z1234567890";
        String identType = "Z";
        String identNo = "dgsdfgsdfgsss3sss43";
        String keyAlg = "RSA";
        String keyLength = "2048";
        String branchCode = "678";
        String email = "test@demo.com";
        
        RAClient client = RAClientConfig.getRAClient();
        CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
        certServiceRequestVO.setTxCode("1101");
        certServiceRequestVO.setCertType(certType);
        certServiceRequestVO.setCustomerType(customerType);
        certServiceRequestVO.setUserName(userName);
        certServiceRequestVO.setIdentType(identType);
        certServiceRequestVO.setIdentNo(identNo);
        certServiceRequestVO.setKeyLength(keyLength);
        certServiceRequestVO.setKeyAlg(keyAlg);
        certServiceRequestVO.setBranchCode(branchCode);
        certServiceRequestVO.setEmail(email);
        certServiceRequestVO.setP10(p10);
        CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

        System.out.println(certServiceResponseVO.getResultCode());
        System.out.println(certServiceResponseVO.getResultMessage());
        if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
            System.out.println(certServiceResponseVO.getDn());
            System.out.println(certServiceResponseVO.getSequenceNo());
            System.out.println(certServiceResponseVO.getSerialNo());
            System.out.println(certServiceResponseVO.getStartTime());
            System.out.println(certServiceResponseVO.getEndTime());
            System.out.println(certServiceResponseVO.getSignatureCert());
            return ObjectResult.success("成功!", certServiceResponseVO.getSignatureCert());
        }
		return ObjectResult.fail("失败!");
	}
	
	@RequestMapping("/sign/message")
	@ResponseBody
	public ObjectResult<String> signMessage(String signData, String signType, String source) throws PKIException {
		
		Session session = SigntureUtils.initSignSession();
		final Signature signature = new Signature();
		if("Attach".equals(signType)){
			System.out.println("带原文消息签名!");
			boolean verifyResult = signature.p7VerifyMessageAttach(signData.getBytes(), session);
			System.out.println("签名cfca校验结果:" +verifyResult);
			if(verifyResult){
				byte[] sourceData = signature.getSourceData();
				System.out.println("原文:"+new String(sourceData));
				X509Cert cert = signature.getSignerCert();
				System.out.println("证书信息:" + cert.getStringSerialNumber()+"; 颁发者DN:"+cert.getIssuer() +"; 主题DN"+cert.getSubject()+"; 公钥"+cert.getPublicKey());
			}
		}else if("Detach".equals(signType)){
			System.out.println("分离式消息签名!");
			boolean verifyResult = signature.p7VerifyMessageDetach(source.getBytes(), signData.getBytes(), session);
			System.out.println("签名cfca校验结果:" +verifyResult);
			if(verifyResult){
				System.out.println("原文:"+source);
				X509Cert cert = signature.getSignerCert();
				System.out.println("证书信息:" + cert.getStringSerialNumber()+"; 颁发者DN:"+cert.getIssuer() +"; 主题DN"+cert.getSubject()+"; 公钥"+cert.getPublicKey());
			}
		}else{
			return ObjectResult.fail("类型错误!");
		}
		return ObjectResult.success("提交成功, 后台验签成功!");
	}
	
}
