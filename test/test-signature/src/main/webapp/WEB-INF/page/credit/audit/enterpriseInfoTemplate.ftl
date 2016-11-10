<script id="template-incInfo" type="text/x-kendo-template">
    <div class="moniteDetail-wrap">
        <table>
            <colgroup>
                <col width="45%">
                <col width="55%">
            </colgroup>
            <tr class="moniteDetail-caption">
                <th>企业信息</th>
                <th></th>
            </tr>
            <tr>
                <td class="moniteDetail-til">企业性质</td>
                <td>${customerApplyDetailDTO.companyNatureDesc!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">上市公司</td>
                <td>${customerApplyDetailDTO.isListing!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">企业上年销售额</td>
                <td id="annualSales">${customerApplyDetailDTO.annualSales!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">主要合作产品</td>
                <td>${customerApplyDetailDTO.majorCooperativeProducts!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">是否存在淡旺季</td>
                <td>${customerApplyDetailDTO.existPeakSlackPeriod!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">具体淡旺季时间</td>
                <td>${customerApplyDetailDTO.slackAndPeakPeriod!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">经营模式</td>
                <td>${customerApplyDetailDTO.businessModelDesc!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">资质信息</td>
                <td>${customerApplyDetailDTO.qualificationsDesc!}</td>
            </tr>
        </table>
    </div>
</script>