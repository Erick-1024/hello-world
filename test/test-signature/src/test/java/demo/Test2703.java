package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书换发
public class Test2703 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String dn = "CN=051@testName@Z1234567890@28,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN";
        // String keyAlg = "RSA";
        // String keyLength = "2048";
        // String duration = "24";
        // String endTime = "20150101000000"; // endTime与duration同时非空时，证书截止时间以endTime为准，duration作为证书默认有效期记入数据库
        // String useOldKey = "true";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("2703");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setDn(dn);
            // certServiceRequestVO.setKeyLength(keyLength);
            // certServiceRequestVO.setKeyAlg(keyAlg);
            // certServiceRequestVO.setDuration(duration);
            // certServiceRequestVO.setEndTime(endTime);
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
