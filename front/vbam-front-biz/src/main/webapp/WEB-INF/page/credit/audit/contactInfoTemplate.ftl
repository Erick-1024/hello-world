<#--联系人信息弹框-->
<script id="template-contacter" type="text/x-kendo-template">
    <div class="moniteDetail-wrap">
        <table>
            <colgroup>
                <col width="45%">
                <col width="55%">
            </colgroup>
            <tr class="moniteDetail-caption">
                <th>联系人信息</th>
                <th></th>
            </tr>
            <tr>
                <td class="moniteDetail-til">联系人姓名</td>
                <td>${customerApplyDetailDTO.contactName!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">手机号码</td>
                <td>${customerApplyDetailDTO.phoneNumber!}</td>
            </tr>
            <tr>
                <td class="moniteDetail-til">电子邮箱</td>
                <td>${customerApplyDetailDTO.email!}</td>
            </tr>
        </table>
    </div>
</script>