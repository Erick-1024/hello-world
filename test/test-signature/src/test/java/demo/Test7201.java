package demo;

import java.util.List;

import cfca.ra.common.vo.request.QueryRequestVO;
import cfca.ra.common.vo.response.QueryResponseListVO;
import cfca.ra.common.vo.response.StatisticResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书统计
public class Test7201 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String certType = "1";
        String customerType = "2";
        // String keyAlg = "RSA";
        String branchCode = "1;11";
        String startTimeFrom = "20140716000000";
        String startTimeTo = "20150717235959";
        String endTimeFrom = "20150108000000";
        String endTimeTo = "20150716235959";
        try {
            RAClient client = TestConfig.getRAClient();

            QueryRequestVO queryRequestVO = new QueryRequestVO();
            queryRequestVO.setTxCode("7201");
            // queryRequestVO.setLocale(locale);
            // queryRequestVO.setCertType(certType);
            // queryRequestVO.setCustomerType(customerType);
            // // queryRequestVO.setKeyAlg(keyAlg);
             queryRequestVO.setBranchCode(branchCode);
            // queryRequestVO.setStartTimeFrom(startTimeFrom);
            // queryRequestVO.setStartTimeTo(startTimeTo);
            // queryRequestVO.setEndTimeFrom(endTimeFrom);
            // queryRequestVO.setEndTimeTo(endTimeTo);

            QueryResponseListVO queryResponseListVO = (QueryResponseListVO) client.process(queryRequestVO);

            System.out.println(queryResponseListVO.getResultCode());
            System.out.println(queryResponseListVO.getResultMessage());
            List<StatisticResponseVO> statisticResponseVOList = queryResponseListVO.getStatisticResponseVOList();
            if (RAClient.SUCCESS.equals(queryResponseListVO.getResultCode()) && statisticResponseVOList != null && statisticResponseVOList.size() > 0) {
                System.out.println(statisticResponseVOList.size());

                // 字段值不存在时为null
                StatisticResponseVO statisticResponseVO = statisticResponseVOList.get(0);
                System.out.println(statisticResponseVO.getBranchName());
                System.out.println(statisticResponseVO.getBranchCode());
                System.out.println(statisticResponseVO.getCertTypeId());
                System.out.println(statisticResponseVO.getCertTypeName());
                System.out.println(statisticResponseVO.getCertStatus());
                System.out.println(statisticResponseVO.getCertStatusName());
                System.out.println(statisticResponseVO.getCount());
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}
