<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划详情" jsFiles=["page/repayment/manage/repaymentPlanDetails.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "融资信息管理" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <section class="repayment-manual">
            <#include "/page/repayment/loanInfoElements.ftl">
            <div class="repayment-nav">
                <ul>
                    <li class="repayment-nav-active">
                        <a class="repayment-navlink" href="javascript:void(0);">还款计划</a>
                    </li>
                    <li>
                        <a class="repayment-navlink" href="javascript:void(0);">费用列表</a>
                    </li>
                    <#if authorizeKey("FM_REPAYMENTINFO_MANAGE_REPAYMENTDETAILSHISTORY")>
                    	<a class="repayment-link repayOp-link" href="javascript:void(0);">历史还款明细</a>
                    </#if>
                </ul>
            </div>
            <div class="repayment-grid" id="repayment-plan">
            	<#if authorizeKey("FM_REPAYMENTINFO_MANAGE_PLANDETAIL_ACTIVE_REPAYMENT")>
            		<#if accessToActiveRepayment >
            			<a class="form-search-link" id="activeRepayAllPlan"  href="javascript:void(0);">合并还款</a>
            		</#if>
            	</#if>
            	 <#if authorizeKey("FM_REPAYMENTINFO_MANAGE_ACTIVEREPAYMENT") && !accessToActiveRepayment && (loanInfoElements.accountNo)?? && loanInfoElements.accountNo!=''>
					<a class="form-search-link" href="${basepath}/repayment/active/gotoActiveRepayment?loanInfoId=${loanInfoElements.id}">主动还款</a>
        		</#if>
                <div class="finance-grid" id="manualGrid-plan" style="margin-top:15px;"></div>
            </div>
            <div class="repayment-grid hidden" id="repayment-cost">
              	<#if authorizeKey("FM_REPAYMENTINFO_MANAGE_PLANDETAIL_ACTIVE_REPAYMENT")>
              		<#if accessToActiveRepayment >
            			<a class="form-search-link" id="activeRepayAllExpense"  href="javascript:void(0);">合并还款</a>
            		</#if>
            	</#if>
                <div class="finance-grid" id="manualGrid-cost" style="margin-top:15px;"></div>
            </div>
            <div class="dlg-wrapper-foot">
                <a class="default-link confirm-link" href="${basepath}/loanInfo/manage/gotoLoanInfoManage">返回</a>
            </div>
        </section>
    </div>
</div>

<script>
	var repaymentDetailAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_PLANDETAIL_INFO")?string("true","false")};
	var activeRepaymentAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_PLANDETAIL_ACTIVE_REPAYMENT")?string("true","false")};
</script>
<!--详情模板-->
<script id="template-Detail" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="repayDetail-content">
            <table class="repayDetail-table" width=100%>
                <colgroup>
                    <col width="12.5%">
                    <col width="15%">
                    <col width="15%">
                    <col width="15%">
                    <col width="12.5%">
                    <col width="15%">
                    <col width="15%">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="2">还款信息</th>
                    <td class="repayDetail-til">还款编号</td>
                    <td>
                        <span id="detail-loanNo"></span>
                    </td>
                    <td class="repayDetail-til">期数</td>
                    <td>
                        <span id="detail-repaymentPeriod"></span>
                    </td>
                    <td class="repayDetail-til">融资余额</td>
                    <td>
                        <span id="detail-financeBalance"></span>
                    </td>
                </tr>
                <tr>
                    <td class="repayDetail-til">收益计算日</td>
                    <td>
                        <span id="detail-valueDate"></span>
                    </td>
                    <td class="repayDetail-til">结息日</td>
                    <td>
                        <span id="detail-settleInterestDate"></span>
                    </td>
                    <td class="repayDetail-til">还款日</td>
                    <td>
                        <span id="detail-repaymentDate"></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="repayDetail-content">
            <table class="repayDetail-table" width=100%>
                <colgroup>
                    <col width="12.5%">
                    <col width="15%">
                    <col width="15%">
                    <col width="15%">
                    <col width="12.5%">
                    <col width="15%">
                    <col width="15%">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="2">还款计划信息</th>
                    <td class="repayDetail-til">应还本金</td>
                    <td>
                        <span id="detail-accountPrincipal"></span>
                    </td>
                    <td class="repayDetail-til">应还收益</td>
                    <td>
                        <span id="detail-accountInterest"></span>
                    </td>
                    <td class="repayDetail-til">应还服务费</td>
                    <td>
                        <span id="detail-accountServiceCharge"></span>
                    </td>
                </tr>
                <tr>
                    <td class="repayDetail-til">展期费用</td>
                    <td>
                        <span id="detail-accountExtensionCharge"></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="repayDetail-content">
            <table class="repayDetail-table" width=100%>
                <colgroup>
                    <col width="12.5%">
                    <col width="15%">
                    <col width="15%">
                    <col width="15%">
                    <col width="12.5%">
                    <col width="15%">
                    <col width="15%">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="2">已还信息</th>
                    <td class="repayDetail-til">本金</td>
                    <td>
                        <span id="detail-paidNormalPrincipal"></span>
                    </td>
                    <td class="repayDetail-til">收益</td>
                    <td>
                        <span id="detail-paidNormalInterest"></span>
                    </td>
                    <td class="repayDetail-til">服务费</td>
                    <td>
                        <span id="detail-paidNormalServiceCharge"></span>
                    </td>
                </tr>
                <tr>
                    <td class="repayDetail-til">展期费用</td>
                    <td>
                        <span id="detail-paidExtensionCharge"></span>
                    </td>
                    <td class="repayDetail-til">提前还款手续费</td>
                    <td>
                        <span id="detail-paidEarlyRepaymentCharge"></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="repayDetail-content overdue hidden">
            <table class="repayDetail-table">
                <colgroup>
                    <col width="200px">
                    <col width="150px">
                    <col width="200px">
                    <col width="150px">
                    <col width="200px">
                    <col width="150px">
                    <col width="200px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="2">逾期信息</th>
                    <td class="repayDetail-til">逾期本金</td>
                    <td>
                        <span id="detail-overduePrincipal"></span>
                    </td>
                    <td class="repayDetail-til">逾期收益</td>
                    <td>
                        <span id="detail-overdueInterest"></span>
                    </td>
                    <td class="repayDetail-til">逾期服务费</td>
                    <td>
                        <span id="detail-overdueServiceCharge"></span>
                    </td>
                </tr>
                <tr>
                    <td class="repayDetail-til">逾期管理费</td>
                    <td>
                        <span id="detail-overdueManageCharge"></span>
                    </td>
                    <td class="repayDetail-til">展期费用</td>
                    <td>
                        <span id="detail-accountExtensionCharge2"></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="repayDetail-content overdue hidden">
            <table class="repayDetail-table">
                <colgroup>
                    <col width="200px">
                    <col width="150px">
                    <col width="200px">
                    <col width="150px">
                    <col width="200px">
                    <col width="150px">
                    <col width="200px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="2">逾期已还信息</th>
                    <td class="repayDetail-til">逾期本金</td>
                    <td>
                        <span id="detail-paidOverduePrincipal"></span>
                    </td>
                    <td class="repayDetail-til">逾期收益</td>
                    <td>
                        <span id="detail-paidOverdueInterest"></span>
                    </td>
                    <td class="repayDetail-til">逾期服务费</td>
                    <td>
                        <span id="detail-paidOverdueServiceCharge"></span>
                    </td>
                </tr>
                <tr>
                    <td class="repayDetail-til">逾期管理费</td>
                    <td>
                        <span id="detail-paidOverdueManageCharge"></span>
                    </td>
                    <td class="repayDetail-til">展期费用</td>
                    <td>
                        <span id="detail-paidExtensionCharge2"></span>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="dlg-wrapper-foot">
                <a class="default-link confirm-link close-window" href="javascript:void(0);">返回</a>
            </div>
        </div>
    </div>
</script>
<!--历史还款明细模板-->
<script id="template-repayDetail" type="text/x-kendo-template">
   <!--没有历史还款明细-->
    <div id="noRepaymentDetails" class="dlg-wrapper hidden">
        <div class="dlg-wrapper-content" style="text-align: center;">
            <div>没有历史还款明细</div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link" href="javascript:void(0);">关闭</a>
        </div>
    </div>
    <div id="haveRepaymentDetails" class="dlg-wrapper hidden">
        <div class="tableTpl-wrap">
            <div class="tableTpl-head">
                <div class="tableTpl-head-wrap">
                    <table class="tableTpl-grid">
                        <colgroup>
                            <col style="width:150px">
                            <col style="width:120px">
                            <col>
                            <col style="width:150px">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>
                                <span class="tabTpl-link">操作时间</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">还款方式</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">金额明细</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">线下还款日</span>
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tableTpl-content">
                <table class="tableTpl-grid">
                    <colgroup>
                        <col style="width:150px">
                        <col style="width:120px">
                        <col>
                        <col style="width:150px">
                    </colgroup>
                    <tbody id="repaymentDetailsHistory">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link close-window" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
</@hb.htmlBase>