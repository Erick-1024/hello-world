<!--放款信息-->
<div class="pro-title" id="nav-two"><span class="pro-title-left">放款信息</span><span class="pro-title-right">折叠</span></div>
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
            <th>融资金额</th>
            <td><span class="spanBw">${formatCentMoney(assetLoanDTO.financeAmount)}</span></td>
            <th>币种</th>
            <td><span class="spanBw">${assetLoanDTO.currency.desc()}</span></td>
            <th>还款方式</th>
            <td><span class="spanBw">${assetLoanDTO.repaymentMethod.desc()}</span></td>
        </tr>
        <tr>
            <th>利率</th>
            <td><span class="spanBw">${assetLoanDTO.interestRate?string("0.000%")}</span></td>
            <th>利率单位</th>
            <td><span class="spanBw">${assetLoanDTO.interestRateUnit.desc()}</span></td>
            <th>计息基准天数</th>
            <td><span class="spanBw">${assetLoanDTO.dayCountConvention}</span></td>
        </tr>
        <tr>
            <th>融资期限</th>
            <td><span class="spanBw">${assetLoanDTO.loanPeriod}</span></td>
            <th>期限单位</th>
            <td><span class="spanBw">${assetLoanDTO.loanPeriodUnit.desc()}</span></td>
            <th>还款账号</th>
            <td><span class="spanBw">${assetLoanDTO.accountNo}</span></td>
        </tr>
        <tr>
            <th>放款日</th>
            <td><span class="spanBw">${assetLoanDTO.loanDate}</span></td>
            <th>到期日</th>
            <td><span class="spanBw">${assetLoanDTO.dueDate}</span></td>
            <th>逾期费率(天)</th>
            <td><span class="spanBw">${assetLoanDTO.penaltyRate?string("0.000%")}</span></td>
        </tr>

        </tbody>
    </table>
    <div class="min-box-width" style="width:970px;">
        <!--费用管理table title-->
        <table class="m-table fact-table" style="border-bottom:none;">
            <colgroup>
                <col width="100px">
                <col width="200px">
                <col width="200px">
                <col width="">
                <col width="17px">
            </colgroup>
            <thead>
            <tr class="top-list-one">
                <td>序号</td>
                <td>费用科目</td>
                <td>金额</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
        <!--费用管理table tbody-->
        <div style="width:100%;min-height:40px;">
            <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="cost-tb">
                <colgroup>
                    <col width="100px">
                    <col width="200px">
                    <col width="200px">
                    <col width="">
                </colgroup>
                <tbody>
                <#if (assetLoanDTO.expenseList)??>
                	<#list assetLoanDTO.expenseList as expense>
                        <tr class="client-add-tr">
                            <th>${((expense.sequence)??)?string((expense.sequence)?number,"暂无")}</th>
                            <td>${(expense.expenseSubject)!"暂无"}</td>
                            <td>${((expense.amount)??)?string("￥" + formatCentMoney(expense.amount),"暂无")}</td>
                            <td></td>
                        </tr>
                	</#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>