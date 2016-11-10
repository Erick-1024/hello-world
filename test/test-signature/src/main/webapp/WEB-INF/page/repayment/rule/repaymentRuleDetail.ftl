<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划规则详情" jsFiles=[] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划规则" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <section class="repayRule-wrap">
            <div class="repayRule-element">
                <div class="finance-title">规则要素</div>
                <div class="repayRule-content">
                    <table class="repayDetail-table">
                        <colgroup>
                            <col width="120px">
                            <col width="120px">
                            <col width="250px">
                            <col width="150px">
                            <col width="250px">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th rowspan="2">基本规则</th>
                            <td class="repayDetail-til">规则编号</td>
                            <td>
                                <span id="ruleId" name="ruleId" style="width:180px;">${repaymentRule.id}</span>
                            </td>
                            <td class="repayDetail-til">规则范围</td>
                            <td>
                            	<span id="scopeOfApplication" name="scopeOfApplication">
                    		        <#if (repaymentRule.fianceCustomerIds)??>
                            			部分融资客户
                            		<#else>
                            			全部融资客户
                            		</#if>
                            	</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="repayDetail-til">回款账户</td>
                            <td>
                            	<span id="factorTransferInAccountNo" name="factorTransferInAccountNo">${formatBankAccountNo(repaymentRule.factorTransferInAccountNo)}</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="repayRule-content">
                    <table class="repayDetail-table">
                        <colgroup>
                            <col width="120px">
                            <col width="120px">
                            <col width="250px">
                            <col width="150px">
                            <col width="280px">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th rowspan="4">扣款规则</th>
                            <td class="repayDetail-til">扣款时点</td>
                            <td>
                                <span id="deductionTime" name="deductionTime">${repaymentRule.deductionTime}</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="repayDetail-til">扣款规则</td>
                            <td>
                            	<span id="deductionRule" name="deductionRule">
                                	<#if repaymentRule.deductionRule == "PART">
                                		可部份扣款
                                	<#else>
                                		足额后扣款
                                	</#if>
                            	</span>
                            </td>
                            <td class="repayDetail-til">扣款顺序</td>
                            <td>
                            	逾期、其他费用、手续费、收益、本金
                            </td>
                        </tr>
                        <tr>
                            <td class="repayDetail-til">展期</td>
                            <td>
                                <span id="extensionDays" name="extensionDays">${repaymentRule.extensionDays}天</span>
                            </td>
                            <td class="repayDetail-til">展期费率</td>
                            <td>
                                <span id="extensionRatio" name="extensionRatio">${repaymentRule.extensionRatio}<span>
                            </td>
                        </tr>
                        <tr>
                            <td class="repayDetail-til">逾期管理费率</td>
                            <td>
                                <span id="penaltyRate" name="penaltyRate">${repaymentRule.penaltyRate}</span><span style="color:#0f8aba;margin-left:80px;cursor:pointer" title="逾期管理费 = 本息 * 逾期管理费率" class="common-tooltip">计算公式</span>
                            </td>
                            <td class="repayDetail-til">提前还款手续费率</td>
                            <td>
                                <span id="earlyRepaymentChargeRatio" name="earlyRepaymentChargeRatio">${repaymentRule.earlyRepaymentChargeRatio}</span><span style="color:#0f8aba;margin-left:80px;cursor:pointer"  title="提前还款手续费 = 提前归还的本金 * 提前还款手续费率" class="common-tooltip">计算公式</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="repayRule-content">
                    <table class="repayDetail-table">
                        <colgroup>
                            <col width="120px">
                            <col width="120px">
                            <col width="250px">
                            <col width="150px">
                            <col width="280px">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th rowspan="1">账户归集规则</th>
                            <td class="repayDetail-til">账户归集</td>
                            <td>
                                <span id="accountAccumulationTime" name="accountAccumulationTime">${repaymentRule.accountAccumulationTime}</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <#if (repaymentRule.fianceCustomerIds)??>
            	<div class="repayRule-item">
	                <div class="finance-title">融资客户列表</div>
	                <div class="repayRule-detail">
	                    <table class="addCustom-table">
	                        <colgroup>
	                            <col width="20%">
	                            <col width="80%">
	                        </colgroup>
	                        <thead>
	                        <tr>
	                            <td>序号</td>
	                            <td>融资客户</td>
	                        </tr>
	                        </thead>
	                        <tbody>
	                        <#assign var = 0 >
	                        <#list repaymentRule.fianceCustomerCompanys?split(",") as fianceCustomerCompany>
	                        	<tr>
		                            <td>${fianceCustomerCompany_index + 1}</td>
		                            <td>${fianceCustomerCompany}</td>
	                       		</tr>
	                        </#list>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
            </#if>
        </section>
    </div>
</div>
</@hb.htmlBase>
