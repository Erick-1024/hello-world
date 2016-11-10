package demo;

import cfca.ra.common.vo.request.QueryRequestVO;
import cfca.ra.common.vo.response.QueryResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 唯一证书查询
public class Test7102 {
    public static void main(String[] args) {
//         String locale = "zh_CN";
//        String serialNo = "2000631224";
         String dn = "CN=051@testName@Z1234567890@34,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN";
        try {
            RAClient client = TestConfig.getRAClient();

            QueryRequestVO queryRequestVO = new QueryRequestVO();
            queryRequestVO.setTxCode("7102");
//            queryRequestVO.setSerialNo(serialNo);
             queryRequestVO.setDn(dn);
//             queryRequestVO.setLocale(locale);

            QueryResponseVO queryResponseVO = (QueryResponseVO) client.process(queryRequestVO);
            // queryRequestVO.setLocale(locale);

            System.out.println(queryResponseVO.getResultCode());
            System.out.println(queryResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(queryResponseVO.getResultCode())) {
                System.out.println(queryResponseVO.getCertType());
                System.out.println(queryResponseVO.getCustomerType());
                System.out.println(queryResponseVO.getDn());
                System.out.println(queryResponseVO.getSequenceNo());
                System.out.println(queryResponseVO.getSerialNo());
                System.out.println(queryResponseVO.getCertStatus());
                System.out.println(queryResponseVO.getDuration());
                System.out.println(queryResponseVO.getApplyTime());
                System.out.println(queryResponseVO.getSendcodeTime());
                System.out.println(queryResponseVO.getStartTime());
                System.out.println(queryResponseVO.getEndTime());
                System.out.println(queryResponseVO.getBranchCode());
                System.out.println(queryResponseVO.getKeyAlg());
                System.out.println(queryResponseVO.getKeyLength());
                System.out.println(queryResponseVO.getEmail());
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}
