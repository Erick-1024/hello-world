<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="业务详情" jsFiles=["js/common/jquery.nav.js", "js/common/business.js", "common/cana.util.js", "common/init.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "业务管理" removeExtHeader = false removeExtFooter = true>

<#include '/common/enumcommon.ftl'/>

<div class="pro-bg" style="background: #fff;">
    <form class="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">收费信息&ensp;▶</a></li>
                <li><a href="#nav-three">约定信息&ensp;▶</a></li>
                <li><a href="#nav-four">额度信息&ensp;▶</a></li>
                <li><a href="#nav-five">费用信息&ensp;▶</a></li>
                <li><a href="#nav-six">担保信息&ensp;▶</a></li>
                <li><a href="#nav-seven">账户信息&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--基本信息-->
            <div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>客户名称</th>
                        <td>${(factorBusinessDTO.customerName)!"暂无"}<input name="id" type="hidden" value="${(factorBusinessDTO.id)!''}"></td>
                        <th>业务合同号</th>
                        <td id="businessContractNo" customerId="${factorBusinessDTO.customerId}">${(factorBusinessDTO.businessContractNo)!"暂无"}</td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>币种</th>
                        <td>${((factorBusinessDTO.currency)??)?string(factorBusinessDTO.currency.desc(),"暂无")}</td>
                        <th>项目名称</th>
                        <td>${(factorBusinessDTO.projectName)!"暂无"}</td>
                        <th>业务产品</th>
                        <td>${((factorBusinessDTO.businessProduct)??)?string(factorBusinessDTO.businessProduct.desc(),"暂无")}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>业务模式</th>
                        <td>${((factorBusinessDTO.businessMode)??)?string(factorBusinessDTO.businessMode.desc(),"暂无")}</td>
                        <th>合同起始日</th>
                        <td>${(factorBusinessDTO.contractStartDate)!"暂无"}</td>
                        <th>合同到期日</th>
                        <td>${(factorBusinessDTO.contractEndDate)!"暂无"}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--收费信息-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">收费信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>利率</th>
                        <td>${(factorBusinessDTO.interestRate)!"暂无"}</td>
                        <th>计息方式</th>
                        <td>
                        	<#if (factorBusinessDTO.interestType)?? >
	                        	${factorBusinessDTO.interestType.desc()}
                            <#else>
	                        	暂无
                        	</#if>
                        </td>
                        <th>手续费率</th>
                        <td>${(factorBusinessDTO.feeRate)!"暂无"}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>罚息利率</th>
                        <td>${(factorBusinessDTO.penaltyRate)!"暂无"}</td>
                        <th>宽限期(天)</th>
                        <td>${((factorBusinessDTO.extensionDays)??)?string(factorBusinessDTO.extensionDays + "     ","暂无")}</td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--约定信息-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">约定信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>付款期限(天)</th>
                        <td>${((factorBusinessDTO.paymentPeriod)??)?string(factorBusinessDTO.paymentPeriod + "     ","暂无")}</td>
                        <th>单证类型</th>
                        <td>${((factorBusinessDTO.receiptType)??)?string(factorBusinessDTO.receiptType.desc(),"暂无")}</td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>还款来源</th>
                        <td>${(factorBusinessDTO.repaymentSource)!"暂无"}</td>
                        <th>还款安排</th>
                        <td>${(factorBusinessDTO.repaymentArrangement)!"暂无"}</td>
                        <th>资金用途</th>
                        <td>${(factorBusinessDTO.fundUsage)!"暂无"}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监控方案</th>
                        <td>${(factorBusinessDTO.monitoringSolution)!"暂无"}</td>
                        <th>增信措施</th>
                        <td>${(factorBusinessDTO.increaseTrustMeasures)!"暂无"}</td>
                        <th>约束性条款</th>
                        <td>${(factorBusinessDTO.bindingProvisions)!"暂无"}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                <div class="min-box-width">
                <table class="m-table fact-table">
                    <colgroup>
                        <col width="10%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <td>序号</td>
                        <td>交易对手</td>
                        <td>融资比例</td>
                        <td>保理类型</td>
                        <td>是否查询子额度</td>
                        <td>子额度</td>
                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table" style="border:none;margin-top:0;" id="appoint-tb">
                        <colgroup>
                            <col width="10%">
                            <col width="15%">
                            <col width="15%">
                            <col width="15%">
                            <col width="15%">
                            <col width="">
                        </colgroup>
                        <tbody>
	                        <#if (factorBusinessDTO.counterpartyList)??>
		                        <#list factorBusinessDTO.counterpartyList as counterparty>
		                        	<tr class="client-add-tr">
			                            <th>${((counterparty.sequence)??)?string((counterparty.sequence)?number + 1,"暂无")}</th>
			                            <td>${(counterparty.counterparty)!"暂无"}</td>
			                            <td>${(counterparty.financingRatio)!"暂无"}</td>
			                            <td>${((counterparty.factoringType)??)?string(counterparty.factoringType.desc(),"暂无")}</td>
			                            <td>${((counterparty.querySubLimitFlag)??)?string((counterparty.querySubLimitFlag)?string("是", "否"),"暂无")}</td>
			                            <td>
			                            	<#if counterparty.querySubLimitFlag==true>
					                            ${((counterparty.subLimit)??)?string("￥" + counterparty.subLimit,"0.00")}
											<#else>
					                            暂无
			                            	</#if>
			                            </td>
			                        </tr>
		                        </#list>
		                    </#if>
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
            <!--额度信息-->
            <div class="pro-title" id="nav-four"><span class="pro-title-left">额度信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="min-box-width">
                <table class="m-table fact-table">
                    <colgroup>
                        <col width="12%">
                        <col width="12%">
                        <col width="14%">
                        <col width="14%">
                        <col width="14%">
                        <col width="14%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <td>额度种类</td>
                        <td>币种</td>
                        <td>授信额度</td>
                        <td>占用额度</td>
                        <td>可用余额</td>
                        <td>额度到期日</td>
                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table" style="border:none;margin-top:0;">
                        <colgroup>
                            <col width="12%">
                            <col width="12%">
                            <col width="14%">
                            <col width="14%">
                            <col width="14%">
                            <col width="14%">
                            <col width="">
                        </colgroup>
                        <tbody id="limitBody">
                        	<tr class="client-add-tr">
	                            <td>${creditDTO.creditModeDesc}</td>
                                <td>${Currency[creditDTO.currency]}</td>
                                <td>￥${formatCentMoney(creditDTO.totalLimit)}</td>
                                <td>￥${formatCentMoney(creditDTO.usedLimit)}</td>
                                <td>￥${formatCentMoney(creditDTO.availableLimit)}</td>
                                <td>${creditDTO.dueDate}</td>
	                        </tr>
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
            <!--费用信息-->
            <div class="pro-title" id="nav-five"><span class="pro-title-left">费用信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="min-box-width">
                <table class="m-table fact-table">
                    <colgroup>
                        <col width="10%">
                        <col width="26%">
                        <col width="26%">
                        <col width="">
                    </colgroup>
                    <thead>
	                	<tr class="top-list-one">
	                        <td>序号</td>
	                        <td>费用科目</td>
	                        <td>金额</td>
	                        <td></td>
	                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table" style="border:none;margin-top:0;" id="cost-tb">
                        <colgroup>
                            <col width="10%">
                            <col width="26%">
                            <col width="26%">
                            <col width="">
                        </colgroup>
                        <tbody>
                        <#if (factorBusinessDTO.expenseList)??>
	                    	<#list factorBusinessDTO.expenseList as expense>
		                        <tr class="client-add-tr">
		                            <th>${((expense.sequence)??)?string((expense.sequence)?number + 1,"暂无")}</th>
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
            <!--担保信息-->
            <div class="pro-title" id="nav-six"><span class="pro-title-left">担保信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="min-box-width">
                <table class="m-table fact-table">
                    <colgroup>
                        <col width="10%">
                        <col width="26%">
                        <col width="26%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <td>序号</td>
                        <td>保证合同号</td>
                        <td>担保方信息</td>
                        <td>担保类型</td>
                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table" style="border:none;margin-top:0;" id="assure-tb">
                        <colgroup>
                            <col width="10%">
                            <col width="26%">
                            <col width="26%">
                            <col width="">
                        </colgroup>
                        <tbody>
                        <#if (factorBusinessDTO.guaranteeInfoList)?? >
                        	<#list factorBusinessDTO.guaranteeInfoList as guaranteeInfo>
		                        <tr class="client-add-tr">
		                            <th>${((guaranteeInfo.sequence)??)?string((guaranteeInfo.sequence)?number + 1,"暂无")}</th>
		                            <td>${(guaranteeInfo.guaranteedContractNo)!"暂无"}</td>
		                            <td>${(guaranteeInfo.guarantorInfo)!"暂无"}</td>
		                            <td>${(guaranteeInfo.guaranteeType)!"暂无"}</td>
		                        </tr>
		                    </#list>
		                </#if>
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
            <!--账户信息-->
            <div class="pro-title" id="nav-seven"><span class="pro-title-left">账户信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="160px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>保理专户户名</th>
                        <td>${(factorBusinessDTO.factoringAccountName)!"暂无"}</td>
                        <th>开户行</th>
                        <td>${(factorBusinessDTO.factoringAccountBankAddress)!"暂无"}</td>
                        <th>账号</th>
                        <td>${(factorBusinessDTO.factoringAccount)!"暂无"}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>结算账户户名</th>
                        <td>${(factorBusinessDTO.settlementAccountName)!"暂无"}</td>
                        <th>开户行</th>
                        <td>${(factorBusinessDTO.settlementAccountBankAddress)!"暂无"}</td>
                        <th>账号</th>
                        <td>${(factorBusinessDTO.settlementAccount)!"暂无"}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div class="client-foot">
                <a class="form-search-link" href="${vbamWebServer}asset/factorBusiness/goto/list">返回</a>
            </div>
        </div>
    </form>
</div>

<#include '../../tipBoxTemplate.ftl'/>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
</@hb.htmlBase>