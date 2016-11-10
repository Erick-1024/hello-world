<script id="template-traveler" type="text/x-kendo-template">
    <div class="moniteDetail-wrap">
        <table>
            <colgroup>
                <col width="55%">
                <col width="45%">
            </colgroup>
            <tr class="moniteDetail-caption">
                <th>真旅网评价</th>
                <th></th>
            </tr>
            <tr>
                <td class="moniteDetail-til">当地同行业地位</td>
                <td>${customerApplyDetailDTO.sameIndustryPlaceOnLocalDesc!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">真旅同类型采购商中地位</td>
                <td>${customerApplyDetailDTO.sameTypePlaceDesc!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">合作配合度</td>
                <td>${customerApplyDetailDTO.cooperationDegree!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">借款期内有无中断采购的可能</td>
                <td>${customerApplyDetailDTO.maybeInterruptPurchaseDesc!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">借款期内我们增加采购的可能</td>
                <td>${customerApplyDetailDTO.maybeIncreasePurchaseDesc!}</td>
            </tr>
        </table>
    </div>
</script>