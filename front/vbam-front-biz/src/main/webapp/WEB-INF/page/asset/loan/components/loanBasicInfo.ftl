<!--基本信息-->
<div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
<div class="client-hide-bg">
    <table class="client-tb">
        <colgroup>
            <col width="120px">
            <col width="250px">
            <col width="120px">
            <col width="250px">
            <col width="120px">
            <col width="">
        </colgroup>
        <tbody>
        <tr>
            <th>放款编号</th>
            <td><span class="spanBw">${assetLoanDTO.id}</span></td>
            <th>业务合同号</th>
            <td><span class="spanBw">${assetLoanDTO.businessContractNo}</span></td>
            <th>融资客户</th>
            <td><span class="spanBw">${assetLoanDTO.customerName}</span></td>
        </tr>
        <tr>
            <th>交易对手</th>
            <td><span class="spanBw">${(assetLoanDTO.invoiceInfoList[0].counterparty)!}</span></td>
            <th>项目名称</th>
            <td><span class="spanBw">${assetLoanDTO.projectName}</span></td>
            <th>业务产品</th>
            <td><span class="spanBw">${assetLoanDTO.businessProduct.desc()}</span></td>
        </tr>

        </tbody>
    </table>

</div>