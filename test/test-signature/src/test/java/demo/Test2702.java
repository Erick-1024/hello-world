package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书补发
public class Test2702 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String dn = "CN=051@testName@Z1234567890@28,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN";
        // String keyAlg = "RSA";
        // String keyLength = "2048";
        // String useOldKey = "true";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("2702");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setDn(dn);
            // certServiceRequestVO.setKeyLength(keyLength);
            // certServiceRequestVO.setKeyAlg(keyAlg);
            // certServiceRequestVO.setUseOldKey(useOldKey);

            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

            System.out.println(certServiceResponseVO.getResultCode());
            System.out.println(certServiceResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
                System.out.println(certServiceResponseVO.getSerialNo());
                System.out.println(certServiceResponseVO.getAuthCode());
                System.out.println(certServiceResponseVO.getStartTime());
                System.out.println(certServiceResponseVO.getEndTime());
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}