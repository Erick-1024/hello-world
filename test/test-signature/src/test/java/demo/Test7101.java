package demo;

import java.util.List;

import cfca.ra.common.vo.request.QueryRequestVO;
import cfca.ra.common.vo.response.QueryResponseListVO;
import cfca.ra.common.vo.response.QueryResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书查询
public class Test7101 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String certType = "1";
        String customerType = "2";
         String userName = "银企直联用户";
        // String userNameInDn = "testName";
        // String userIdent = "Z1234567890";
        // String identType = "Z";
        // String identNo = "1234567890";
        // String keyAlg = "RSA";
        // String keyLength = "2048";
        // String dn =
        // "cn=041@7102030@1000000747$000001_20130106085359@00000001,ou=Enterprises,ou=ZJRCU,o=CFCA TEST CA,c=cn";
        // String serialNo = "2000631640";
         String certStatus = "3;6";//多个状态件用;分隔
        // String branchCode = "678";
        // String email = "test@test.test";
        // String startTimeFrom = "20130101000000";
        // String startTimeTo = "20141231235959";
        // String endTimeFrom = "20131231235959";
        // String endTimeTo = "20141231235959";
        // String exactly = "true"; //
        // 模糊查询仅对userName、userNameInDn、userIdent、identNo、dn、email有效
        try {
            RAClient client = TestConfig.getRAClient();

            QueryRequestVO queryRequestVO = new QueryRequestVO();
            queryRequestVO.setTxCode("7101");
            // queryRequestVO.setLocale(locale);
            queryRequestVO.setCertType(certType);
            queryRequestVO.setCustomerType(customerType);
             queryRequestVO.setUserName(userName);
            // queryRequestVO.setUserNameInDn(userNameInDn);
            // queryRequestVO.setUserIdent(userIdent);
            // queryRequestVO.setIdentType(identType);
            // queryRequestVO.setIdentNo(identNo);
            // queryRequestVO.setKeyAlg(keyAlg);
            // queryRequestVO.setKeyLength(keyLength);
            // queryRequestVO.setDn(dn);
            // queryRequestVO.setSerialNo(serialNo);
             queryRequestVO.setCertStatus(certStatus);
            // queryRequestVO.setBranchCode(branchCode);
            // queryRequestVO.setEmail(email);
            // queryRequestVO.setStartTimeFrom(startTimeFrom);
            // queryRequestVO.setStartTimeTo(startTimeTo);
            // queryRequestVO.setEndTimeFrom(endTimeFrom);
            // queryRequestVO.setEndTimeTo(endTimeTo);
            // queryRequestVO.setExactly(exactly);

            QueryResponseListVO queryResponseListVO = (QueryResponseListVO) client.process(queryRequestVO);

            System.out.println(queryResponseListVO.getResultCode());
            System.out.println(queryResponseListVO.getResultMessage());
            List<QueryResponseVO> queryResponseVOList = queryResponseListVO.getQueryResponseVOList();
            if (RAClient.SUCCESS.equals(queryResponseListVO.getResultCode()) && queryResponseVOList != null && queryResponseVOList.size() > 0) {
                System.out.println(queryResponseVOList.size());

                // 字段值不存在时为null
                QueryResponseVO queryResponseVO = queryResponseVOList.get(0);
                System.out.println(queryResponseVO.getCertType());
                System.out.println(queryResponseVO.getCustomerType());
                System.out.println(queryResponseVO.getUserName());
                System.out.println(queryResponseVO.getUserNameInDn());
                System.out.println(queryResponseVO.getUserIdent());
                System.out.println(queryResponseVO.getIdentType());
                System.out.println(queryResponseVO.getIdentNo());
                System.out.println(queryResponseVO.getDn());
                System.out.println(queryResponseVO.getSequenceNo());
                System.out.println(queryResponseVO.getSerialNo());
                System.out.println(queryResponseVO.getCertStatus());
                System.out.println(queryResponseVO.getDuration());
                System.out.println(queryResponseVO.getApplyTime());
                System.out.println(queryResponseVO.getSendcodeTime());
                System.out.println(queryResponseVO.getStartTime());
                System.out.println(queryResponseVO.getEndTime());
                System.out.println(queryResponseVO.getRevokeTime());
                System.out.println(queryResponseVO.getBranchCode());
                System.out.println(queryResponseVO.getKeyAlg());
                System.out.println(queryResponseVO.getKeyLength());
                System.out.println(queryResponseVO.getEmail());
                System.out.println(queryResponseVO.getPhoneNo());
                System.out.println(queryResponseVO.getAddress());
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}
