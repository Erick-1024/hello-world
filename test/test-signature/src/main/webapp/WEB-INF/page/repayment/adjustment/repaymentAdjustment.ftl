<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="调账" jsFiles=["page/repayment/adjustment/repaymentAdjustment.js","common/formValidator.js","page/repayment/offline/repaymentPlanAdjustmentRules.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "融资信息管理" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <div class="whiteBg">
        <section class="repayment-manual">
        	<div class="tooltip-wrap">
                <a class="default-tooltip" href="javascript:void(0);">操作指引</a>▼
                <div class="tooltip-contain">
                    <div class="tooltip-flag">
                        <span class="flag01"></span>
                        <span class="flag02"></span>
                    </div>
                    <div class="tooltip-content">
                        1.点击新还款计划列表中”修改”。<br/>
                        2.在弹出框中输入调整后的金额，并点击“确定”。<br/>
                        3.如调整多期还款计划，请重复1、2步骤。<br/>
                        4.如有费用要调整，点击“新费用列表”，并点击“修改”。输入调整后的金额并点击“确定”。<br/>
                        5.确认列表信息，点击“提交”完成调账。
                    </div>
                </div>
            </div>
            <#include "/page/repayment/loanInfoElements.ftl">
            <div class="repayment-nav">
                <ul>
                    <li class="repayment-nav-active">
                        <a class="repayment-navlink" href="javascript:void(0);">新还款计划</a>
                    </li>
                    <li>
                        <a class="repayment-navlink " href="javascript:void(0);">新费用列表</a>
                    </li>
                </ul>
            </div>
            <div class="repayment-grid" id="repayment-plan">
                <div class="finance-grid" id="manualGrid-plan" style="margin-top:15px;"></div>
            </div>
            <div class="repayment-grid hidden" id="repayment-cost">
                <div class="finance-grid" id="manualGrid-cost" style="margin-top:15px;"></div>
               
            </div>
            <div class="foot-empty">
	             <div class="dlg-wrapper-foot"  id="foot-fixBar">
	                <input type="hidden" name="loanInfoIdForAutoAllocation" value="${loanInfoId}">
					<form id="repaymentPlanAndExpense" action="savePlanAndExpenseSummaryAndDetail" method="post">
						<input type="hidden" name="loanInfoId" value="${loanInfoId}">
						<input type="hidden" name="redisKey" value="${redisKey}">
						<input type="hidden" name="flag" value="0">
						<input type="hidden" name="changeType" value="adjust">
					</form>
	                <a id="repaymentConfirm" class="default-link confirm-link" href="javascript:void(0);">提交</a>
	            </div>
            </div>
        </section>

    </div>
</div>
<input type="hidden" name="" id="" class="template-leave">
<script id="template-leave" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">当前还款计划已调整，数据尚未提交，请确认是否离开？点击"确认"离开该页面，点击"取消"留在该页面。</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmLeave" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
</@hb.htmlBase>

<!--遮罩层-->
<div class="window-overlay hidden"></div>
<!--查看新费用-->
<div class="template-manualAdd hidden" id="template-editCost" style="width:800px;margin-top:-90px; margin-left:-400px;">
    <div class="manualAdd-head">
        <span>修改费用</span>
        <div class="manual-closeBar">
            <a class="closeHref autoCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
    	<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForNewExpense">
			<span class="k-icon k-warning"> </span>
		</span>
   	 	<form id="newExpenseEdit" name="newExpenseEdit">
	        <div class="repayDetail-content">
	            <table class="dlg-table">
	                <colgroup>
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="repayDetail-til">费用名目</td>
	                    <td>
	                        <span name='subjectItemForNewPay'>顾问咨询费</span>
	                    </td>
	                    <td class="repayDetail-til">应还金额</td>
	                    <td>
	                    <span>
	                        <input name='accountAmount' type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 180px;">
	                    </span>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            <div class="dlg-wrapper-foot">
	                <a class="default-link confirm-link" href="javascript:void(0);" id="cost-confirm">确定</a>
	                <a class="default-link back-link" href="javascript:void(0);">返回</a>
	            </div>
	        </div>
        </form>
    </div>
</div>

<!--还款计划信息弹窗-->
<div class="template-manualAdd hidden" id="template-repayPlanInfo">
    <div class="manualAdd-head">
        <span>还款计划信息</span>
        <div class="manual-closeBar">
            <a class="closeHref autoCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
    	<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForNewPlan">
			<span class="k-icon k-warning"></span>
		</span>
    	<form id="frmForEdit" name="frmForEdit">
	        <div class="repayDetail-content">
	            <table class="dlg-table">
	                <colgroup>
	                    <col width="150px">
	                    <col width="190px">
	                    <col width="160px">
	                    <col width="190px">
	                    <col width="160px">
	                    <col width="190px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="repayDetail-til">还款编号</td>
	                    <td>
	                        <span name="loanNoForNewPlan">1</span>
	                    </td>
	                    <td class="repayDetail-til">期数</td>
	                    <td>
	                        <span name="repaymentPeriodForNewPlan">2</span>
	                    </td>
	                    <td class="repayDetail-til">融资余额</td>
	                    <td>
	                        <span name="financeBalanceForNewPlan">应还本金+逾期本金</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">收益计算日</td>
	                    <td>
	                        <span name="valueDateForNewPlan">2015-10-20</span>
	                    </td>
	                    <td class="repayDetail-til">收益分配日</td>
	                    <td>
	                        <span name="settleInterestDateForNewPlan">2015-10-20</span>
	                    </td>
	                    <td class="repayDetail-til">还款日</td>
	                    <td>
	                        <span name="repaymentDateForNewPlan">2015-10-20</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">应还本金</td>
	                    <td>
	                        <span name="accountPrincipalForNewPlan">500.00</span>
	                    </td>
	                    <td class="repayDetail-til">应还收益</td>
	                    <td>
	                    <span>
	                        <input name="accountInterestForNewPlan" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 160px;">
	                    </span>
	                    </td>
	                    <td class="repayDetail-til">应还服务费</td>
	                    <td>
	                    <span>
	                        <input name="accountServiceChargeForNewPlan" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 160px;">
	                    </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">逾期本金</td>
	                    <td>
	                        <span name="overduePrincipalForNewPlan">500.00</span>
	                    </td>
	                    <td class="repayDetail-til">逾期收益</td>
	                    <td>
	                    <span>
	                        <input name="overdueInterestForNewPlan" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 160px;">
	                    </span>
	                    </td>
	                    <td class="repayDetail-til">逾期服务费</td>
	                    <td>
	                    <span>
	                        <input name="overdueServiceChargeForNewPlan" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 160px;">
	                    </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">展期费用</td>
	                    <td>
	                    <span>
	                        <input name="extensionChargeForNewPlan" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 160px;">
	                    </span>
	                    </td>
	                    <td class="repayDetail-til">逾期管理费</td>
	                    <td>
	                    <span>
	                        <input name="overdueManagerFeeForNewPlan" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 160px;">
	                    </span>
	                    </td>
	                    <td class="repayDetail-til">结清状态</td>
	                    <td>
	                        <span name="settleStatusForNewPlan"></span>
	                    	<input type="hidden" name="settleStatusForPage" value="">
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            <div class="dlg-wrapper-foot">
	                <a class="default-link confirm-link" href="javascript:void(0);" id="newPlanConfirm">确定</a>
	                <a class="default-link back-link" href="javascript:void(0);">返回</a>
	            </div>
	        </div>
        </form>
    </div>
</div>
