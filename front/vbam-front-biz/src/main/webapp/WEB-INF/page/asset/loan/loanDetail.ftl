<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="融资详情" jsFiles=["js/common/jquery.nav.js", "js/common/business.js", "common/cana.util.js", "common/init.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "融资管理" removeExtHeader = false removeExtFooter = true>

<#include '/common/enumcommon.ftl'/>

<div class="pro-bg" style="background: #fff;min-width: 1800px;">
    <form class="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">放款信息&ensp;▶</a></li>
                <li><a href="#nav-three">应收账款&ensp;▶</a></li>
                <li><a href="#nav-four">额度信息&ensp;▶</a></li>
                <li><a href="#nav-six">还款计划&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <#include "components/loanBasicInfo.ftl"/>

            <#include "components/loanInfo.ftl"/>

            <!--应收账款-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">应收账款</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">

                <div class="min-box-width" style="width:970px;">
                    <!--应收账款信息table title-->
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="60px">
                            <col width="220px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>序号</td>
                            <td>交易对手</td>
                            <td>单证号码</td>
                            <td>单证面额</td>
                            <td>应收金额</td>
                            <td>融资比例</td>
                            <td>开票日</td>
                            <td>到期日</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <!--应收账款信息table tbody-->
                    <div style="width:100%;min-height:40px;">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="receivables-tb">
                            <colgroup>
                                <col width="60px">
                                <col width="220px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="">
                            </colgroup>
                            <tbody>
                            <#if (assetLoanDTO.invoiceInfoList)??>
		                    	<#list assetLoanDTO.invoiceInfoList as invoiceInfo>
			                        <tr class="client-add-tr">
		                                <th>${invoiceInfo_index+1}</th>
		                                <td>${(invoiceInfo.counterparty)!"暂无"}</td>
		                                <td>${(invoiceInfo.invoiceNo)!"暂无"}</td>
		                                <td>${((invoiceInfo.nominvoiceAmt)??)?string("￥" + invoiceInfo.nominvoiceAmt, "暂无")}</td>
		                                <td>${((invoiceInfo.invoiceAmt)??)?string("￥" + invoiceInfo.invoiceAmt, "暂无")}</td>
		                                <td>${((invoiceInfo.financingRatio)??)?string(invoiceInfo.financingRatio + "%","暂无")}</td>
		                                <td>${(invoiceInfo.invoiceDate)!"暂无"}</td>
		                                <td>${(invoiceInfo.dueDate)!"暂无"}</td>
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
                <div class="min-box-width" style="width:970px;">
                    <!--额度信息table title-->
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="150px">
                            <col width="150px">
                            <col width="150px">
                            <col width="150px">
                            <col width="150px">
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>额度种类</td>
                            <td>币种</td>
                            <td>授信额度</td>
                            <td>占用额度</td>
                            <td>可用余额</td>
                            <td>额度到期日</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <!--额度信息table tbody-->
                    <div style="width:100%;min-height:40px;">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="limit-tb">
                            <colgroup>
                                <col width="150px">
                                <col width="150px">
                                <col width="150px">
                                <col width="150px">
                                <col width="150px">
                                <col width="">
                            </colgroup>
                            <tbody>
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

            <!--还款计划-->
            <div class="pro-title" id="nav-six"><span class="pro-title-left">还款计划</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="big-table-box" style="width:1520px;">
                    <table class="big-table-title" style="width:1500px">
                        <colgroup>
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="">

                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>融资余额</td>
                            <td>起息日</td>
                            <td>结息日</td>
                            <td>还款日</td>
                            <td>应还本金</td>
                            <td>应还利息</td>
                            <td>逾期费</td>
                            <td>应还总金额</td>
                            <td>已还本金</td>
                            <td>已还利息</td>
                            <td>入账日期</td>
                            <td>提前天数</td>
                            <td>逾期天数</td>
                            <td>已还逾期费</td>
                            <td>结清栏</td>

                        </tr>
                        </thead>
                    </table>
                    <div style="width:100%;min-height:40px;">
                        <table class="big-table-tbody" style="width:1500px;">
                            <colgroup>
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="100">
                                <col width="">
                            </colgroup>
                            <tbody>
                            	<tr class="client-add-tr">
	                                <td>${((assetLoanDTO.financeAmount)??)?string("￥" + formatCentMoney(assetLoanDTO.financeAmount), "-")}</td>
	                                <td>${(assetLoanDTO.loanDate)!"-"}</td>
	                                <td>-</td>
	                                <td>${(assetLoanDTO.loanDate)!"-"}</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                                <td>-</td>
	                            </tr>
								<#if (assetLoanDTO.loanPlanList)??>
			                    	<#list assetLoanDTO.loanPlanList as loanPlan>
				                        <tr class="client-add-tr">
			                                <td>${((loanPlan.financeBalance)??)?string("￥" + loanPlan.financeBalance, "-")}</td>
			                                <td>${(loanPlan.valueDate)!"-"}</td>
			                                <td>${(loanPlan.settleInterestDate)!"-"}</td>
			                                <td>${(loanPlan.repaymentDate)!"-"}</td>
			                                <td>${((loanPlan.accountPrincipal)??)?string("￥" + loanPlan.accountPrincipal, "-")}</td>
			                                <td>${((loanPlan.accountInterest)??)?string("￥" + loanPlan.accountInterest, "-")}</td>
			                                <td>${((loanPlan.accountOverdue)??)?string("￥" + loanPlan.accountOverdue, "-")}</td>
			                                <td>${((loanPlan.accountAmount)??)?string("￥" + loanPlan.accountAmount, "-")}</td>
			                                <td>${((loanPlan.paidPrincipal)??)?string("￥" + formatCentMoney(loanPlan.paidPrincipal), "-")}</td>
			                                <td>${((loanPlan.paidInterest)??)?string("￥" + formatCentMoney(loanPlan.paidInterest), "-")}</td>
			                                <td>${(loanPlan.lastPaidDate)!"-"}</td>
			                                <td>${(loanPlan.forwardDays)!"-"}</td>
			                                <td>${(loanPlan.overdueDays)!"-"}</td>
			                                <td>${((loanPlan.paidOverdue)??)?string("￥" + formatCentMoney(loanPlan.paidOverdue), "-")}</td>
			                                <td>${(loanPlan.settleStatusDesc)!"-"}</td>
			                            </tr>
		                        	</#list>
			                    </#if>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="client-foot">
                <a class="form-search-link" href="${vbamWebServer}asset/loan/goto/loanList">关闭</a>
            </div>
        </div>
    </form>
</div>

<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>

</@hb.htmlBase>