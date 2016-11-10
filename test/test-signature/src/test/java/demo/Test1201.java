package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书更新并下载
public class Test1201 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String dn = "CN=051@testName@Z1234567890@53,OU=Individual-3,OU=Local RA,O=CFCA TEST CA,C=CN";
        // String keyAlg = "RSA";
        // String keyLength = "2048";
        // String duration = "24";
        // String endTime = "20150101000000"; // endTime与duration同时非空时，证书截止时间以endTime为准，duration作为证书默认有效期记入数据库
        // String useOldKey = "true";
        String p10 = "MIGJAoGBALL/T8cvxMkdrmhmACA2IVC5GjKbEpy9WeuEcxjACJf0ue+sJhVOPSdoPnGnz9+aRnSMAEfJITDu8lHOXskfb80DhODHxxmqfvLT8kpTOB4RG6jSmmARrhjFPNaX90bMdkOCwwkkMnjmwRFfgeazyLdbMFcaF2kA1U3bGzaVrs/tAgMBAAE=";
        String p10Sub = "ALQAAAABAADGitN5jEQ1wOHs+T2+On065n4Nzj81IG4IKZwjUZEMcgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAV3YWO/1+JZ+OiX0EYDood9uublsm9nixLZBhkLN1BQoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("1201");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setDn(dn);
            // certServiceRequestVO.setKeyLength(keyLength);
            // certServiceRequestVO.setKeyAlg(keyAlg);
            // certServiceRequestVO.setDuration(duration);
            // certServiceRequestVO.setEndTime(endTime);
            // certServiceRequestVO.setUseOldKey(useOldKey);
            certServiceRequestVO.setP10(p10);
            certServiceRequestVO.setP10Sub(p10Sub);

            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

            System.out.println(certServiceResponseVO.getResultCode());
            System.out.println(certServiceResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
                System.out.println(certServiceResponseVO.getSerialNo());
                System.out.println(certServiceResponseVO.getStartTime());
                System.out.println(certServiceResponseVO.getEndTime());
                System.out.println(certServiceResponseVO.getSignatureCert());
                System.out.println(certServiceResponseVO.getEncryptionCert());
                System.out.println(certServiceResponseVO.getEncryptionPrivateKey());
                System.out.println(certServiceResponseVO.getSignatureCertSub());
                System.out.println(certServiceResponseVO.getEncryptionCertSub());
                System.out.println(certServiceResponseVO.getEncryptionPrivateKeySub());
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}
