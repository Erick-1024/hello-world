<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="规则确认" jsFiles=["page/guide/comfirmRule.js", "page/repayment/rule/repaymentRuleformValidatorRules.js", "common/formValidator.js"] cssFiles=["css/login.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "账户列表" removeExtHeader = false removeExtFooter = false showMenu=false>

<div class="main-registion">
    <div class="registion-wrap">
        <div class="registion-title">规则确认（第二步）</div>
        <form id="defaultRuleAdd-form" name="defaultRuleAdd-form">
	        <div class="firstEntry-content">
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="250px">
	                    <col width="250px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th rowspan="3">基本规则</th>
	                    <td class="registionRow-til">规则编号</td>
	                    <td>
	                        <span id="ruleId" name="ruleId" style="width:180px;">${ruleId!}</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til">规则范围</td>
	                    <td>
	                        <span>全部融资客户</span>
	                    </td>
	                </tr>
	                <tr>
	                	<td class="registionRow-til"><span class="redFalg">*</span>回款账户</td>
						<td colspan="2">
							<select class="selectWrap" id="factorTransferInAccountNo" name="factorTransferInAccountNo" data-role="dropdownlist" style="width:180px;">
							<#if accounts??>
								<#list accounts as account>
									<option value="${(account.accountNo)!}">${formatBankAccountNo(account.accountNo)!}</option>
								</#list>
							</#if>
							</select>
						</td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="firstEntry-content">
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="250px">
	                    <col width="250px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th rowspan="7">扣款规则</th>
	                    <td class="registionRow-til"><span class="redFalg">*</span>扣款时点</td>
	                    <td>
	                        <input id="deductionTime" name="deductionTime" value=${defaultRepaymentRule.deductionTime} type="text">
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>扣款规则</td>
	                    <td>
	                        <div class="radioContent" id="deductionRule" name="deductionRule" >
	                            <label class="radio active" data-deductionrule="PART">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">可部份扣款</span>
	                            </label>
	                            <label class="radio" data-deductionrule="ALL">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">足额后扣款</span>
	                            </label>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>扣款顺序</td>
	                    <td colspan="2">
	                    	<span>逾期、其他费用、手续费、收益、本金</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til">展期</td>
	                    <td>
	                        <input type="text" id="extensionDays" name="extensionDays" value=${defaultRepaymentRule.extensionDays} class="fontRight">天
	                        <span data-for="extensionDays" class="k-invalid-msg"></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til">展期费率</td>
	                    <td>
	                        <input type="text" id="extensionRatio" name="extensionRatio" value=${defaultRepaymentRule.extensionRatio} class="fontRight">%
	                    	<span data-for="extensionRatio" class="k-invalid-msg"></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til">逾期管理费率</td>
	                    <td>
	                        <input type="text" id="penaltyRate" name="penaltyRate" value=${defaultRepaymentRule.penaltyRate} class="fontRight">%
	                    	<span data-for="penaltyRate" class="k-invalid-msg"></span>
	                    </td>
	                    <td class="validate-info">
	                        <span>逾期管理费 =（本金+收益）*逾期管理费率</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til">提前还款手续费率</td>
	                    <td>
	                        <input type="text" id="earlyRepaymentChargeRatio" name="earlyRepaymentChargeRatio" value=${defaultRepaymentRule.earlyRepaymentChargeRatio} class="fontRight">%
	                    	<span data-for="earlyRepaymentChargeRatio" class="k-invalid-msg"></span>
	                    </td>
	                    <td class="validate-info">
	                        <span>提前还款手续费 = 本金*提前还款手续费率</span>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="firstEntry-content">
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="250px">
	                    <col width="250px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th rowspan="2">账户归集规则</th>
	                    <td class="registionRow-til">账户归集</td>
	                    <td>
	                        <input type="text" id="accountAccumulationTime" name="accountAccumulationTime" value=${defaultRepaymentRule.accountAccumulationTime}>
	                    </td>
	                </tr>
	                <tr>
	                    <td></td>
	                    <td colspan="2" style="padding-top:30px;">
	                        <a class="reg-submit" id="addDefaultRule" href="javascript:void(0);" style="width: 180px;">完成</a>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
        </form>
    </div>
</div>
</@hb.htmlBase>