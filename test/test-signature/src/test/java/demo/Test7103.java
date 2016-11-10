package demo;

import cfca.ra.common.vo.request.QueryRequestVO;
import cfca.ra.common.vo.response.QueryResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书公钥查询
public class Test7103 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String serialNo = "2000631224";
        try {
            RAClient client = TestConfig.getRAClient();

            QueryRequestVO queryRequestVO = new QueryRequestVO();
            queryRequestVO.setTxCode("7103");
            queryRequestVO.setSerialNo(serialNo);

            QueryResponseVO queryResponseVO = (QueryResponseVO) client.process(queryRequestVO);
            // queryRequestVO.setLocale(locale);

            System.out.println(queryResponseVO.getResultCode());
            System.out.println(queryResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(queryResponseVO.getResultCode())) {
                System.out.println(queryResponseVO.getSignatureCert());
                System.out.println(queryResponseVO.getEncryptionCert());
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}
