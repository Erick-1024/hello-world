<div class="pro-title" id=""><span class="pro-title-left">专项计划信息</span></div>
        <div class="">
            <table class="client-tb">
                <colgroup>
                    <col width="150">
                    <col width="150">
                    <col width="150">
                    <col width="150">
                    <col width="150">
                    <col width="150">
                    <col width="150">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>专项计划编号</th>
                    <td>${assetpoolListDTO.id!}</td>
                    <th>专项计划名称</th>
                    <td>${assetpoolListDTO.specialProgramName!}</td>
                    <th>基础资产类型</th>
                    <td>${assetpoolListDTO.underlyingAssetTypeDesc!}</td>
                    <th>管理人名称</th>
                    <td>${assetpoolListDTO.managerName!}</td>
                </tr>
                <tr>
                    <th>预计成立日期</th>
                    <td>${assetpoolListDTO.estimateEstablishmentDate!}</td>
                    <th>初始资产池规模</th>
                    <td>${assetpoolListDTO.originAssetpoolScale!}</td>
                    <th>状态</th>
                    <td>${assetpoolListDTO.statusDesc!}</td>
                    <th>当日未尝总额</th>
                    <td>${assetpoolListDTO.unpaidAmount!}</td>
                </tr>
                <tr>
                    <th>当日应还收入总额</th>
                    <td>${assetpoolListDTO.accountIncome!}</td>
                    <th>当日应还本金总额</th>
                    <td>${assetpoolListDTO.accountPrincipal!}</td>
                    <th>当日应还总金额</th>
                    <td>${assetpoolListDTO.accountAmount!}</td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th>当日实际收入总额</th>
                    <td>${assetpoolListDTO.paidIncome!}</td>
                    <th>当日实际本金总额</th>
                    <td>${assetpoolListDTO.paidPrincipal!}</td>
                    <th>当日实际还款总额</th>
                    <td>${assetpoolListDTO.paidAmount!}</td>
                    <th></th>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>