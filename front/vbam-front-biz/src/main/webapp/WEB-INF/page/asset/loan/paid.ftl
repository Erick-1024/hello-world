<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="还款冲销" jsFiles=["js/common/jquery.nav.js", "js/common/business.js", "page/asset/loan/paid.js", "common/cana.util.js", "common/init.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "融资管理" removeExtHeader = false removeExtFooter = true>

<div class="pro-bg" style="background: #fff;">
    <form class="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">放款信息&ensp;▶</a></li>
                <li><a href="#nav-six">还款计划&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
	        <!--触发按钮-->
            <a href="javascript:void(0);" class="open-costPop" style="display:none;">触发还款冲销弹窗</a>
            <a href="javascript:void(0);" class="open-repaymentPop" style="display:none;">触发还款修改弹窗</a>
            <a href="javascript:void(0);" class="open-message-btn" style="display:none;">触发消息弹窗</a>
            <!--触发按钮-->

			<input type="hidden" id = "paidLoanInfoId" value="${assetLoanDTO.id}"/>
            <#include "components/loanBasicInfo.ftl"/>

            <#include "components/loanInfo.ftl"/>

            <!--还款计划-->
            <div class="pro-title" id="nav-six"><span class="pro-title-left">还款计划</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div style="margin-top:15px;text-align: left;">
                    <a class="status-normal status-chk add-repayment-btn" href="javascript:void(0);" style="width:90px;">还款冲销</a>
                    <a class="status-normal status-chk ch-repayment-btn" href="javascript:void(0);">修改</a>
                </div>
                <div class="big-table-box" style="width:100%;max-width: 1450px;overflow-x: scroll;">
				    <div style="width:1800px;overflow-y: scroll;">
						<table class="big-table-title" style="width:100%;">
	                        <colgroup>
	                            <col width="50">
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
	
	                        </colgroup>
	                        <thead>
	                        <tr class="top-list-one">
	                            <th>选择</th>
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
				    </div>
				    <div style="width:1800px;height:auto;max-height:200px;overflow-y: scroll;">
						<table class="big-table-tbody" style="width:100%;" id="refund-tb">
                            <colgroup>
                                <col width="50">
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
                            </colgroup>
                            <tbody>
                            	<tr>
	                                <th></th>
	                                <td>${formatCentMoney(assetLoanDTO.financeBalance)}</td>
	                                <td>${(assetLoanDTO.loanDate)!}</td>
	                                <td></td>
	                                <td>
	                                	${(assetLoanDTO.loanDate)!}
	                                </td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                            <#list assetLoanDTO.loanPlanList as loanPlan>
		                            <tr>
		                                <th>
		                                	<#if "UNSETTLE" == loanPlan.settleStatus>
		                                		<input type="checkbox" name="refund-btn" class="input-new" />
		                                		<input type="hidden" name="loanPlanId" value="${(loanPlan.id)!}" />
		                                	</#if>
		                                </th>
		                                <td>${(loanPlan.financeBalance)!}</td>
		                                <td>${(loanPlan.valueDate)!}</td>
		                                <td>${(loanPlan.settleInterestDate)!}</td>
		                                <td class="repaymentDate">
		                                	${(loanPlan.repaymentDate)!}
		                                </td>
		                                <td class="accountPrincipal">
		                                	${(loanPlan.accountPrincipal)!}
		                                </td>
		                                <td class="accountInterest">
		                                	${(loanPlan.accountInterest)!}
		                                </td>
		                                <td class="accountOverdue">
		                                	${(loanPlan.accountOverdue)!}
		                                </td>
		                                <td class="accountAmount">
		                                	${(loanPlan.accountAmount)!}
		                                </td>
		                                <td class="paidPrincipal" originPaidPrincipal="${((loanPlan.paidPrincipal)??)?string(formatCentMoney(loanPlan.paidPrincipal), "")}">
		                                	${((loanPlan.paidPrincipal)??)?string(formatCentMoney(loanPlan.paidPrincipal), "")}
		                                </td>
		                                <td class="paidInterest" originPaidInterest="${((loanPlan.paidInterest)??)?string(formatCentMoney(loanPlan.paidInterest), "")}">
		                                	${((loanPlan.paidInterest)??)?string(formatCentMoney(loanPlan.paidInterest), "")}
		                                </td>
		                                <td class="lastPaidDate">
		                                	${(loanPlan.lastPaidDate)!}
		                                </td>
		                                <td class="forwardDays">
		                                	${(loanPlan.forwardDays)!}
		                                </td>
		                                <td class="overdueDays">
		                                	${(loanPlan.overdueDays)!}
		                                </td>
		                                <td class="paidOverdue" originPaidOverdue="${((loanPlan.paidOverdue)??)?string(formatCentMoney(loanPlan.paidOverdue), "")}">
		                                	${((loanPlan.paidOverdue)??)?string(formatCentMoney(loanPlan.paidOverdue), "")}
		                                </td>
		                                <td class="settleStatusDesc">
		                                	${(loanPlan.settleStatusDesc)!}
		                                </td>
		                            </tr>
	                            </#list>
                            </tbody>
                        </table>
				    </div>
				</div>

            </div>
            <div class="client-foot">
                <a id="submit-paids-btn" class="form-search-link sc-btn" href="javascript:void(0);">提交</a>
                <a class="form-search-link" href="${vbamWebServer}asset/loan/goto/loanList">关闭</a>
            </div>
        </div>
    </form>
</div>

<!--还款冲销弹窗-->
<script id="template-resetPwd-cost" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="cost-form">
            <div class="dlg-del-row">

                <table class="client-tb">
                    <colgroup>
                        <col width="40%">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>入账冲销项目</th>
                        <td>
                            <label><input type="checkbox" class="input-new item-btn principalChecked" name="item" value="1">本金</label><em>&emsp;&emsp;</em>
                            <label><input type="checkbox" class="input-new item-btn interestChecked" name="item" value="2">利息</label><em>&emsp;&emsp;</em>
                            <label><input type="checkbox" class="input-new item-btn overdueChecked" name="item" value="3">逾期费</label>
                            <label><input type="hidden" class="check-item-btn" name="item4" value="" validationMessage="请选择则项目" required></label>

                        </td>
                    </tr>
                    <tr>
                        <th>入账日期</th>
                        <td><input type="text" readonly style="width:200px;" class="time-one data-style hasIcon" name="in-date" validationMessage="不能为空" required></td>
                    </tr>
                    <tr>
                        <th>本次入账金额</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
                        		<input type="text" style="width:200px;" class="moneyNum" name="in-money" data-required-msg="不能为空" required>
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="refund-pop-btn" href="javascript:void(0);">确定</a>
                <a class="default-link back-link close-costPop" href="javascript:void(0);">取消</a>
            </div>
        </form>
        <input type="hidden" value="" class="cost-ch-id">
    </div>
</script>
<!--修改弹窗-->
<script id="template-resetPwd-arr" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="repayment-form">
            <div class="dlg-del-row">
                <table class="client-tb">
                    <colgroup>
                        <col width="40%">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>已还本金</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
                        		<input type="text" style="width:200px;" class="refund-capital moneyNum" name="refund1" data-required-msg="不能为空" required/>
                        		<div class="client-unit-two">元</div>
                        	</label>
                    	</td>
                    </tr>
                    <tr>
                        <th>已还利息</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
                        		<input type="text" style="width:200px;" class="refund-accrual moneyNum" name="refund2" data-required-msg="不能为空" required/>
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                    </tr>
                    <tr>
                        <th>已还逾期费</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
                        		<input type="text" style="width:200px;" class="refund-demurrage moneyNum" name="refund3" data-required-msg="不能为空" required/>
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                    </tr>
                    <tr>
                        <th>结清栏</th>
                        <td>
                            <select style="width:200px;"  data-role="dropdownlist" id="refund-status" class="refund-status" name="refund4">
                                <option value="未结清">未结清</option>
                                <option value="已结清">已结清</option>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="repayment-btn" href="javascript:void(0);">确定</a>
                <a class="default-link back-link close-repaymentPop" href="javascript:void(0);">取消</a>
            </div>
        </form>
        <input type="hidden" value="" class="repayment-id">
    </div>
</script>
<!--提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>

<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>

</@hb.htmlBase>