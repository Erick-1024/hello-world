package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书下载
public class Test2401 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String serialNo = "1000171783";
        String authCode = "PKP5N4GXVA";
        String p10 = "MIICgTCCAWkCAQAwPjELMAkGA1UEBhMCQ04xFTATBgNVBAoMDENGQ0EgVEVTVCBDQTEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr0l3jGXcYzz55c16BHu0sN1ZbAatwHa/GfKqCIsm5kIAgLp8fBDi6P5TPx+JIvmUOjOsQggvZ8wqZSAxgMZPqQmDsmZxy5oiQ6U82Ei6L88eaQG3PgW2rhKNPG5Si9Va3TIT49JyAbt7omnceQdpNlHSFO9CbDt2gvMrVAPdGPW/Mf7I05v1J4r933Fy3cYgbTlcahwEKkXMtzRxKGn17JH+FAEuQ3mDp/l10M8JBokL0k/5dtDmmr9+gqnMxTwnVtxsf9PBnePU/3liVzG/AXWAMY4uOuzqRKubHo45APtVUyhGXbGQMgHP8IEgpdBWHqHkpi5sz0HxDmztGQm6BQIDAQABMA0GCSqGSIb3DQEBBQUAA4IBAQBHCWYCeIZ/Nxg4/o/YL+4nMKzKbXNOU3RioQUefYl2Th8j3AkMWMhdmbPQZm9pLk0N4Ao5MsomkutA5ZrF0mB3KdVrZy7dFBAvD5BQZuWwdFsrac4Mrr91L9FEb1Pqby+nQ/YhOIlKj9ng/DCdSK1THaodcfJhwrw7yuNysrhblh4/kSDj9NpBLfJD0n4ACf8js6GBrLbzoz88Ur0+qCGaeYf+mv/0W+a4xKiq4AFlOte4a8TjoeM1QuEgvJeWihx/jn7IyYFT2zAszV4XfApjs8R5NKZEpJjyDTodiAXDI1akekDimJBSsbMfb2Gwfb7MJFm1PGlMU6djPUyNo65D";
        String p10Sub = "MIH5MIGeAgEAMD4xCzAJBgNVBAYTAkNOMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExGDAWBgNVBAMMD2NlcnRSZXF1aXNpdGlvbjBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABP4bO1oH7uYhqW6uaY2vVXaGwR61l2ADvHaYtY6OeYWoiPMUedHgcNHWpsocNVjtFUdZfxOOcoHEOWai5dxDCLswDAYIKoEcz1UBg3UFAANIADBFAiBowU1D9v1pDlMH5aCnYzBti+oVyxpIPcWiGfg8vMkGxgIhAM79KsO0oYBaRYDxBoQCOCheJGfymjXoPPdTovnZDA/3";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("2401");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setSerialNo(serialNo);
            certServiceRequestVO.setAuthCode(authCode);
            certServiceRequestVO.setP10(p10);
            certServiceRequestVO.setP10Sub(p10Sub);

            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

            System.out.println(certServiceResponseVO.getResultCode());
            System.out.println(certServiceResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
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
