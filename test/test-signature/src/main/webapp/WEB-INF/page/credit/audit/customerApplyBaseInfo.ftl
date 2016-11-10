<div class="monitorChk-content">
    <table class="monitorChk-tab">
        <colgroup>
            <col width="150px">
            <col width="150px">
            <col width="200px">
            <col width="150px">
        </colgroup>
        <tbody>
        <tr>
            <th rowspan="6">基本信息</th>
            <td class="registionRow-til">日期</td>
            <td>${customerApplyDetailDTO.applyDate!}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">申请公司</td>
            <td>${customerApplyDetailDTO.companyName!}</td>
            <td>
                <a class="manual-chk inc-info" href="javascript:void(0);">企业信息</a>
            </td>
        </tr>
        <tr>
            <td class="registionRow-til">实际控制人</td>
            <td>${customerApplyDetailDTO.realControlPerson!}</td>
            <td class="tageBox">
                <a class="manual-chk contacter-info" href="javascript:void(0);">联系人信息</a>
            </td>
        </tr>
        <tr>
            <td class="registionRow-til">申请金额</td>
            <td id="applyCreditLimit">${customerApplyDetailDTO.applyCreditLimit!}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">真旅建议额度</td>
            <td id="proposalCreditLimit">${customerApplyDetailDTO.proposalCreditLimit!}</td>
            <td>
                <a class="manual-chk traveler-jadge" href="javascript:void(0);">真旅网评价</a>
            </td>
        </tr>
        <tr>
            <td class="registionRow-til">申请类型</td>
            <td>${customerApplyDetailDTO.applyTypeDesc!}</td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="monitorChk-content">
    <table class="monitorChk-tab">
        <colgroup>
            <col width="150px">
            <col width="150px">
            <col width="200px">
            <col width="150px">
        </colgroup>
        <tbody>
        <tr>
            <th rowspan="4">系统审核信息</th>
            <td class="registionRow-til">真旅授信意见</td>
            <td>${customerApplyDetailDTO.proposalAuditResult!}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">与我真旅合作月份数</td>
            <td>${customerApplyDetailDTO.cooperationPeriod!}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">下游客户类型</td>
            <td>${customerApplyDetailDTO.downstreamCustomerTypeDesc!}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">下游回款有无账期</td>
            <td>${customerApplyDetailDTO.downstreamRepaymentAccountPeriod!}</td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>