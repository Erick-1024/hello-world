package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书申请
public class Test2101 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String certType = "1";
        String customerType = "1";
        String userName = "tes,tNa,,me";
        // String userNameInDn = "testName";
        // String userIdent = "Z1234567890";
        String identType = "Z";
        String identNo = "H09,358028f";
        // String keyAlg = "RSA";
        // String keyLength = "2048";
        String branchCode = "678";
         String email = "tdest";
        // String phoneNo = "12345678";
        // String address = "address";
        // String duration = "24";
        String endTime = "20160101000001"; // endTime与duration同时非空时，证书截止时间以endTime为准，duration作为证书默认有效期记入数据库
        // String addIdentNoExt = "false";
        // String addEmailExt = "false";
        // String selfExtValue = "extValue";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("2101");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setCertType(certType);
            certServiceRequestVO.setCustomerType(customerType);
            certServiceRequestVO.setUserName(userName);
            // certServiceRequestVO.setUserNameInDn(userNameInDn);
            // certServiceRequestVO.setUserIdent(userIdent);
            certServiceRequestVO.setIdentType(identType);
            certServiceRequestVO.setIdentNo(identNo);
            // certServiceRequestVO.setKeyLength(keyLength);
            // certServiceRequestVO.setKeyAlg(keyAlg);
            certServiceRequestVO.setBranchCode(branchCode);
           // certServiceRequestVO.setEmail(email);
            // certServiceRequestVO.setPhoneNo(phoneNo);
            // certServiceRequestVO.setAddress(address);
            // certServiceRequestVO.setDuration(duration);
            certServiceRequestVO.setEndTime(endTime);
            // certServiceRequestVO.setAddIdentNoExt(addIdentNoExt);
            // certServiceRequestVO.setAddEmailExt(addEmailExt);
            // certServiceRequestVO.setSelfExtValue(selfExtValue);

            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

            System.out.println(certServiceResponseVO.getResultCode());
            System.out.println(certServiceResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
                System.out.println(certServiceResponseVO.getDn());
                System.out.println(certServiceResponseVO.getSequenceNo());
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
