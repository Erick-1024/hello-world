package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书申请并下载
public class Test1101 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String certType = "1";
        String customerType = "1";
        String userName = "huzhiwen";
        // String userNameInDn = "testName";
        // String userIdent = "Z1234567890";
        String identType = "Z";
        String identNo = "dgsdfgsdfgs";
        String keyAlg = "RSA";
        String keyLength = "2048";
        String branchCode = "678";
        String email = "test@demo.com";
        // String phoneNo = "12345678";
        // String address = "address";
        // String duration = "24";
        // String endTime = "sdfs"; // endTime与duration同时非空时，证书截止时间以endTime为准，duration作为证书默认有效期记入数据库
        // String addIdentNoExt = "false";
        // String selfExtValue = "extValue";
        String p10 = "MIICpzCCAY8CAQAwZDELMAkGA1UEBhMCQ04xFTATBgNVBAoMDENGQ0EgVEVTVCBD"+
"QTENMAsGA1UECwwEQ0FOQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMRgwFgYDVQQD"+
"DA9jZXJ0UmVxdWlzaXRpb24wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB"+
"AQC/2GjHTn9lUDt5GNzMlUj3CZlp6iwVx7gDgBW8QODm6oexpSsvXw85UnOjBhor"+
"JHAGdUnLCeCCsBrimPm5WHUodb+21GvsmesiZpItQLTaA1TqcsbNqPsr0iQa1hmQ"+
"1tpVbK29Gqzy6WyqtHCfh+rmeuLXmrbHK27JTa1eMQO3S5Q/M/ZwgsQx/qM+R2/i"+
"Zol4/VxF602dxe16gRbOXnIrr7RiAqwULfPjIW2WLGny2IXZKgrle7uWkmkS5LWu"+
"Kfc0rYS0JqleUUnBYAn+AcR4LUbIRzhftgAuKV5L8JjEOocyFb7r0xZFaYZ1eA/0"+
"3/T7OlfATbt53VvwQXPrEkm1AgMBAAEwDQYJKoZIhvcNAQEFBQADggEBACj/disf"+
"FmKlfLrY/NVp59tMM0DJw80XYlJ/Myq0DQPVdXBKr4zCEPtO6uKEA012HCkhswiT"+
"2bPuRc3mM5tKhsadQgwSDYcUZVBKi/Rq0Ubnvqgz9VQm1K1pc7oawsz/GZ5N5PF+"+
"G6qr701jjWr6qTPY4sI6XQ2G4byHOvuZyGZr/0YrT1jjFVyCWf0aH0XbXoshteeu"+
"87cZf8FPVuJP0c1FXOxKYStjuRX9HapbcEsdEQ0aOFKzriPJMj3RzBv6SbwiTIKk"+
"1K1aeTWwkHEIMatdjN9dq+OTAEwXCfqob6K8/Op7FsP7TuXaX6GsfR5ltGJlK6+B"+
"1/dU6pjV1J2SzUY=";
//        String p10 = "MIICgTCCAWkCAQAwPjELMAkGA1UEBhMCQ04xFTATBgNVBAoMDENGQ0EgVEVTVCBDQTEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxJ5enmSfMtapzZLEjZYdK/CoHkczM4Ggbc5H3KVC8xZUcSdMbzcxWvbEqEEPotbDS7xdlgxwWLqRJwy9geK8I/g+HZ9/PavWwErQkCuQiTnWF3uaUGdcL1fmD5QEDr9K2BTAooIH4OsDZ9KIi6BCN1Y7nyxomugNU5ryWwUXFOvO5/ayGAX+7HTLjCEjO9qR7GNYwA+kSNjllla3qTg37IZU9ipIzBv9ha39gc0We11DmcI2XVbeAW6mnQD6kpCRvaFqxxhM4BOTYYMZrgBPqrHrABjlvo/MKdBrFKa1SOvUax12DCqys5aPTqi2c6+FwgEQ5WYOnhjVtoOt6kCBVwIDAQABMA0GCSqGSIb3DQEBBQUAA4IBAQAPfxyQDN5gDKTWSGLaMPKR4a3hICrxvRCDqkw8z5yTqy7GzQDuyokw7CCfEs5M2ZZAXY+3noRZvVkwLd2H1vSeTMBDPvzmq6esrDs6pHuIKKgrH9iYGYzll3wIAAJ9XEkmUkyIQ6LvhIE6J0ufJ9YlHBPNbIx0d3DywI1YLJZnuX738WbRii1QcqWQw28CH8MECap8pnR8OJrIXeoYobO6OryKQirakvIk/ugaAmQF8BY6e4LVt0wLvwTcGiHXP+vcOZNhYyb0NsB39d5uX9qV/Vyjowzu5g82SGkv2S7UeNXlun6RyLoZSSek30pgDqJAOPanOEmQhVRD1ZkQ3OV5";
//        String p10Sub = "MIIBtDCCAVgCAQAwPjELMAkGA1UEBhMCQ04xFTATBgNVBAoMDENGQ0EgVEVTVCBDQTEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEsD7nwqQKQWe1ghN+Zy4FbByQPxg3Y/aHMqiz4rDz0liG8Fq5250T5mKYh1leWulAZps2wPaLFIUMqZ7+eT1qyqCBtzATBgkqhkiG9w0BCQcTBjExMTExMTCBnwYJKoZIhvcNAQk/BIGRMIGOAgEBBIGIALQAAAABAACiwcr2l/0TmUagstfNwWb8O4cJdD1Wac1Yy9gkf8rttAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGXhheoBu6FadqKQNDP7+GOw7xXD4FNQA3NYnu55mBIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADAMBggqgRzPVQGDdQUAA0gAMEUCIEpASo/MKXIIETdxurjmuracNklJbHt+n6Olus12HFHlAiEAwAX1CfamNX0DhMysjkwan9ASmZsdinEQEs59kJJGSuQ=";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("1101");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setCertType(certType);
            certServiceRequestVO.setCustomerType(customerType);
            certServiceRequestVO.setUserName(userName);
            // certServiceRequestVO.setUserNameInDn(userNameInDn);
            // certServiceRequestVO.setUserIdent(userIdent);
            certServiceRequestVO.setIdentType(identType);
            certServiceRequestVO.setIdentNo(identNo);
            certServiceRequestVO.setKeyLength(keyLength);
            certServiceRequestVO.setKeyAlg(keyAlg);
            certServiceRequestVO.setBranchCode(branchCode);
            certServiceRequestVO.setEmail(email);
            // certServiceRequestVO.setPhoneNo(phoneNo);
            // certServiceRequestVO.setAddress(address);
            // certServiceRequestVO.setDuration(duration);
            // certServiceRequestVO.setEndTime(endTime);
            // certServiceRequestVO.setAddIdentNoExt(addIdentNoExt);
            // certServiceRequestVO.setSelfExtValue(selfExtValue);
            certServiceRequestVO.setP10(p10);
//            certServiceRequestVO.setP10Sub(p10Sub);

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
