<#if (userType)??>
	<#if userType=="CANA">
		<div class="finance-wrap">
            <div class="finance-title">还款要素</div>
            <table class="repayment-element">
                <colgroup>
                    <col width="120">
                    <col width="200">
                    <col width="140">
                    <col width="200">
                    <col width="120">
                    <col width="250">
                </colgroup>
                <tbody>
                <tr>
                    <td class="manual-title">平台流水号</td>
                    <td><span id="loanId">${loanInfoElements.id!}</span></td>
                    <td class="manual-title">资金方</td>
                    <td><span>${loanInfoElements.factorCompany!}</span></td>
                    <td class="manual-title">融资客户</td>
                    <td><span>${loanInfoElements.financeCompany!}</span></td>
                </tr>
                <tr>
                	<td class="manual-title">核心企业</td>
                	<td>
                    	<span>${loanInfoElements.coreCompanyName!}</span>
               		</td>
                    <td class="manual-title">融资金额</td>
                    <td>
                        <span>${loanInfoElements.financeAmount!}</span>
                    </td>
                    <td class="manual-title">融资余额</td>
                    <td>
                        <span>${loanInfoElements.financeBalance!}</span>
                    </td>
                </tr>
                <tr>
                	<td class="manual-title">${loanInfoElements.interestRateUnit!}费率</td>
                    <td>
                         <span>${loanInfoElements.interestRate!}</span>
                    </td>
                    <td class="manual-title">逾期管理费率</td>
                    <td>
                        <span>${loanInfoElements.penaltyRate!}</span>
                    </td>
                    <td class="manual-title">展期费率</td>
                    <td>
                        <span>${loanInfoElements.extensionRatio!}</span>
                    </td>
                </tr>
                <tr>
                	<td class="manual-title">提前还款手续费率</td>
                    <td>
                        <span name="earlyRepaymentChargeRatio">${loanInfoElements.earlyRepaymentChargeRatio!}</span>
                    </td>
                    <td class="manual-title">放款日</td>
                    <td>
                        <span>${loanInfoElements.loanDate!}</span>
                    </td>
                    <td class="manual-title">到期日</td>
                    <td>
                        <span>${loanInfoElements.dueDate!}</span>
                    </td>
                </tr>
                <tr>
                	<td class="manual-title">还款方式</td>
                    <td class="autoRepayment-td">
                        <span>${loanInfoElements.repaymentMethod!}</span>
                    </td>
                    <td class="manual-title">期数</td>
                    <td>
                        <span>${loanInfoElements.repaymentPeriod!}</span>
                    </td>
                    <td class="manual-title">还款账号</td>
                    <td>
                        <span>${loanInfoElements.accountNo!}</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
	</#if>
	<#if userType=="FACTOR">
		<div class="finance-wrap">
        <div class="finance-title">还款要素</div>
        <table class="repayment-element">
            <colgroup>
                <col width="120">
                <col width="200">
                <col width="140">
                <col width="200">
                <col width="120">
                <col width="250">
            </colgroup>
            <tbody>
            <tr>
                <td class="manual-title">平台流水号</td>
                <td>
                    <span id="loanId">${loanInfoElements.id!}</span>
                </td>
            	<td class="manual-title">融资客户</td>
                <td><span>${loanInfoElements.financeCompany!}</span></td>
                <td class="manual-title">核心企业</td>
                <td>
                    <span>${loanInfoElements.coreCompanyName!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">融资金额</td>
                <td>
                    <span>${loanInfoElements.financeAmount!}</span>
                </td>
                <td class="manual-title">融资余额</td>
                <td>
                    <span>${loanInfoElements.financeBalance!}</span>
                </td>
                <td class="manual-title">${loanInfoElements.interestRateUnit!}费率</td>
                <td>
                    <span>${loanInfoElements.interestRate!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">逾期管理费率</td>
                <td>
                    <span>${loanInfoElements.penaltyRate!}</span>
                </td>
                <td class="manual-title">展期费率</td>
                <td>
                    <span>${loanInfoElements.extensionRatio!}</span>
                </td>
                <td class="manual-title">提前还款手续费率</td>
                <td>
                    <span name="earlyRepaymentChargeRatio">${loanInfoElements.earlyRepaymentChargeRatio!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">放款日</td>
                <td>
                    <span>${loanInfoElements.loanDate!}</span>
                </td>
                <td class="manual-title">到期日</td>
                <td>
                    <span>${loanInfoElements.dueDate!}</span>
                </td>
                <td class="manual-title">还款方式</td>
                <td class="autoRepayment-td">
                    <span>${loanInfoElements.repaymentMethod!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">期数</td>
                <td>
                    <span>${loanInfoElements.repaymentPeriod!}</span>
                </td>
                <td class="manual-title">还款账号</td>
                <td>
                    <span>${loanInfoElements.accountNo!}</span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
	</#if>
	<#if userType=="FINACE">
		<div class="finance-wrap">
        <div class="finance-title">还款要素</div>
        <table class="repayment-element">
            <colgroup>
                <col width="120">
                <col width="200">
                <col width="140">
                <col width="200">
                <col width="120">
                <col width="250">
            </colgroup>
            <tbody>
            <tr>
                <td class="manual-title">平台流水号</td>
                <td>
                    <span id="loanId">${loanInfoElements.id!}</span>
                </td>
            	<td class="manual-title">资金方</td>
                <td><span>${loanInfoElements.factorCompany!}</span></td>
                <td class="manual-title">核心企业</td>
                <td>
                    <span>${loanInfoElements.coreCompanyName!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">融资金额</td>
                <td>
                    <span>${loanInfoElements.financeAmount!}</span>
                </td>
                <td class="manual-title">融资余额</td>
                <td>
                    <span>${loanInfoElements.financeBalance!}</span>
                </td>
                <td class="manual-title">${loanInfoElements.interestRateUnit!}费率</td>
                <td>
                    <span>${loanInfoElements.interestRate!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">逾期管理费率</td>
                <td>
                    <span>${loanInfoElements.penaltyRate!}</span>
                </td>
                <td class="manual-title">展期费率</td>
                <td>
                    <span>${loanInfoElements.extensionRatio!}</span>
                </td>
                <td class="manual-title">提前还款手续费率</td>
                <td>
                    <span name="earlyRepaymentChargeRatio">${loanInfoElements.earlyRepaymentChargeRatio!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">放款日</td>
                <td>
                    <span>${loanInfoElements.loanDate!}</span>
                </td>
                <td class="manual-title">到期日</td>
                <td>
                    <span>${loanInfoElements.dueDate!}</span>
                </td>
                <td class="manual-title">还款方式</td>
                <td class="autoRepayment-td">
                    <span>${loanInfoElements.repaymentMethod!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">期数</td>
                <td>
                    <span>${loanInfoElements.repaymentPeriod!}</span>
                </td>
                <td class="manual-title">还款账号</td>
                <td>
                    <span>${loanInfoElements.accountNo!}</span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
	</#if>
	<#if userType=="CORECOMPANY">
		<div class="finance-wrap">
        <div class="finance-title">还款要素</div>
        <table class="repayment-element">
            <colgroup>
                <col width="120">
                <col width="200">
                <col width="140">
                <col width="200">
                <col width="120">
                <col width="250">
            </colgroup>
            <tbody>
            <tr>
                <td class="manual-title">平台流水号</td>
                <td>
                    <span id="loanId">${loanInfoElements.id!}</span>
                </td>
            	<td class="manual-title">资金方</td>
                <td><span>${loanInfoElements.factorCompany!}</span></td>
                <td class="manual-title">融资客户</td>
                <td><span>${loanInfoElements.financeCompany!}</span></td>
            </tr>
            <tr>
                <td class="manual-title">融资金额</td>
                <td>
                    <span>${loanInfoElements.financeAmount!}</span>
                </td>
                <td class="manual-title">融资余额</td>
                <td>
                    <span>${loanInfoElements.financeBalance!}</span>
                </td>
                <td class="manual-title">${loanInfoElements.interestRateUnit!}费率</td>
                <td>
                    <span>${loanInfoElements.interestRate!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">逾期管理费率</td>
                <td>
                    <span>${loanInfoElements.penaltyRate!}</span>
                </td>
                <td class="manual-title">展期费率</td>
                <td>
                    <span>${loanInfoElements.extensionRatio!}</span>
                </td>
                <td class="manual-title">提前还款手续费率</td>
                <td>
                    <span name="earlyRepaymentChargeRatio">${loanInfoElements.earlyRepaymentChargeRatio!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">放款日</td>
                <td>
                    <span>${loanInfoElements.loanDate!}</span>
                </td>
                <td class="manual-title">到期日</td>
                <td>
                    <span>${loanInfoElements.dueDate!}</span>
                </td>
                <td class="manual-title">还款方式</td>
                <td class="autoRepayment-td">
                    <span>${loanInfoElements.repaymentMethod!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">期数</td>
                <td>
                    <span>${loanInfoElements.repaymentPeriod!}</span>
                </td>
                <td class="manual-title">还款账号</td>
                <td>
                    <span>${loanInfoElements.accountNo!}</span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
	</#if>
<#else>
   <div class="finance-wrap">
        <div class="finance-title">还款要素</div>
        <table class="repayment-element">
            <colgroup>
                <col width="120">
                <col width="200">
                <col width="140">
                <col width="200">
                <col width="120">
                <col width="250">
            </colgroup>
            <tbody>
            <tr>
                <td class="manual-title">平台流水号</td>
                <td>
                    <span id="loanId">${loanInfoElements.id!}</span>
                </td>
            	<td class="manual-title">融资客户</td>
                <td><span>${loanInfoElements.financeCompany!}</span></td>
                <td class="manual-title">融资金额</td>
                <td>
                    <span>${loanInfoElements.financeAmount!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">融资余额</td>
                <td>
                    <span>${loanInfoElements.financeBalance!}</span>
                </td>
                <td class="manual-title">${loanInfoElements.interestRateUnit!}费率</td>
                <td>
                    <span>${loanInfoElements.interestRate!}</span>
                </td>
                <td class="manual-title">逾期管理费率</td>
                <td>
                    <span>${loanInfoElements.penaltyRate!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">展期费率</td>
                <td>
                    <span>${loanInfoElements.extensionRatio!}</span>
                </td>
                <td class="manual-title">提前还款手续费率</td>
                <td>
                    <span name="earlyRepaymentChargeRatio">${loanInfoElements.earlyRepaymentChargeRatio!}</span>
                </td>
                <td class="manual-title">放款日</td>
                <td>
                    <span>${loanInfoElements.loanDate!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">到期日</td>
                <td>
                    <span>${loanInfoElements.dueDate!}</span>
                </td>
                <td class="manual-title">还款方式</td>
                <td class="autoRepayment-td">
                    <span>${loanInfoElements.repaymentMethod!}</span>
                </td>
                <td class="manual-title">期数</td>
                <td>
                    <span>${loanInfoElements.repaymentPeriod!}</span>
                </td>
            </tr>
            <tr>
                <td class="manual-title">还款账号</td>
                <td>
                    <span>${loanInfoElements.accountNo!}</span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</#if>