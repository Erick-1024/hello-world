<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划调整" jsFiles=["page/repayment/active/repaymentPlanAdjustment.js","common/formValidator.js","page/repayment/active/repaymentPlanAdjustmentRules.js"] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "融资信息管理" removeExtHeader = false removeExtFooter = false>
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
                        1.输入本次还款金额，点击自动分配，金额将自动填充到已还信息列表。<br/>
                        2.如有疑问可将列表滚动到最右方，点击“调整”。<br/>
                        3.手工录入系统不调整未来期还款计划，请在“新还款计划”中调整每期还款计划。<br/>
                        4.如需还费用，请点击“费用列表”进行修改。<br/>
                        5.确认还款计划、费用列表信息，点击“提交”完成录入。
                    </div>
                </div>
            </div>
            <#include "/page/repayment/loanInfoElements.ftl">
            <div class="repayment-nav">
                <ul>
                    <li class="repayment-nav-active">
                        <a class="repayment-navlink" href="javascript:void(0);">还款计划</a>
                    </li>
                    <li>
                        <a class="repayment-navlink" href="javascript:void(0);">费用列表</a>
                    </li>
                </ul>
            </div>
            <div class="repayment-grid" id="repayment-plan">
                <div class="finance-head">已还信息</div>
                <div class="finance-item">
                    <label>本次还款总金额：</label>
                    <span class="acut-change">${accountTotalMoney!''}</span>
                </div>
                <div id="manualGrid-planWrap">
	                <div class="finance-grid" id="manualGrid-plan"></div>
                </div>
                <div class="finance-head">新还款计划</div>
                <div id="manualGrid-newPlanWrap">
	                <div class="finance-grid" id="manualGrid-newPlan"></div>
                </div>
            </div>
            <div class="repayment-grid hidden" id="repayment-cost">
                <div class="finance-head">已还费用</div>
                <div id="manualGrid-costWrap">
	                <div class="finance-grid" id="manualGrid-cost"></div>
                </div>
                <div class="finance-head">新费用</div>
                <div id="manualGrid-newCostWrap">
	                <div class="finance-grid" id="manualGrid-newCost"></div>
                </div>
            </div>
            <div class="foot-empty">
            <div class="dlg-wrapper-foot" id="foot-fixBar">
				<input type="hidden" name="loanInfoIdForAutoAllocation" value="${loanInfoId}">
				<form id="repaymentPlanAndExpense" action="savePlanAndExpense" method="post">
					<input type="hidden" name="loanInfoId" value="${loanInfoId}">
					<input type="hidden" name="redisKey" value="${redisKey}">
					<input type="hidden" name="flag" value="0">
					<input type="hidden" name="changeType" value="active_repayment">
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
<div class="template-manualAdd hidden" id="template-newCost" style="margin-top:-90px;">
    <div class="manualAdd-head">
        <span>本次还费用</span>
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
        <div class="repayDetail-content">
        	<form id="newExpenseEdit" name="newExpenseEdit">
	            <table class="repayDetail-table">
	                <colgroup>
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="repayDetail-til">费用名目</td>
	                    <td>
	                        <span name="subjectItemForNewPay"></span>
	                    </td>
	                    <td class="repayDetail-til">应还金额</td>
	                    <td>
	                    <span>
	                        <input name="accountAmount" type="text" class="moneyNum" placeholder="￥" style="text-align: right; width: 180px;">
	                    </span>
	                    </td>
	                    <td class="repayDetail-til">结清状态</td>
	                    <td>
	                        <span name="settleStatusForNewExpense"></span>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </form>
            <div class="dlg-wrapper-foot">
                <a class="default-link confirm-link"  id="editRepaymentExpenseConfirm" href="javascript:void(0);">确定</a>
                <a class="default-link back-link" href="javascript:void(0);">返回</a>
            </div>
        </div>
    </div>
</div>
<!--本次还费用模板-->
<div class="template-manualAdd hidden" id="template-editRepayed" style="margin-top:-100px;">
    <div class="manualAdd-head">
        <span>本次还费用</span>
        <div class="manual-closeBar">
            <a class="closeHref autoCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
		<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForExpense">
			<span class="k-icon k-warning"> </span>
		</span>
    	<form id="newExpenseRepayment" name="newExpenseRepayment">
	        <div class="repayDetail-content">
	            <table class="repayDetail-table">
	                <colgroup>
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="repayDetail-til">费用名目</td>
	                    <td>
	                        <span name="subjectItemForPay"></span>
	                    </td>
	                    <td class="repayDetail-til">本次还款金额</td>
	                    <td>
	                        <span>
	                            <input name="paidAmout" type="text" placeholder="￥" style="text-align: right; width: 180px;" class="moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">线下还款日</td>
	                    <td>
	                        <input name="offlineRepaymentDate" type="text" class="datepicker default" readonly>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            <div class="dlg-wrapper-foot">
	                <a id="currentRepaymentFeeConfirm" class="default-link confirm-link" href="javascript:void(0);" data-id="">确定</a>
	                <a class="default-link back-link" href="javascript:void(0);">返回</a>
	            </div>
	        </div>
    	</form>
    </div>
</div>
<!--本次还款信息-->
<div class="template-manualAdd hidden" id="template-theRepay">
    <div class="manualAdd-head">
        <span>本次还款信息</span>
        <div class="manual-closeBar">
            <a class="closeHref autoCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
		<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForPlan">
			<span class="k-icon k-warning"></span>
		</span>
	    <div class="repayDetail-content">
    		<form name="frmForAdjustment" id="frmForAdjustment">
	            <table class="repayDetail-table">
	                <colgroup>
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="repayDetail-til">放款编号</td>
	                    <td>
	                        <span name="loanInfoIdForPaid"></span>
	                    </td>
	                    <td class="repayDetail-til">期数</td>
	                    <td>
	                        <span name="repaymentPeriodForPaid"></span>
	                    </td>
	                    <td class="repayDetail-til">收益计算日</td>
	                    <td>
	                        <span name="valueDateForPaid"></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">收益分配日</td>
	                    <td>
	                        <span name="settleInterestDateForPaid"></span>
	                    </td>
	                    <td class="repayDetail-til">计划还款日</td>
	                    <td>
	                        <span name="repaymentDateForPaid"></span>
	                    </td>
	                    <td class="repayDetail-til">线下还款日</td>
	                    <td>
	                        <input name="offlineRepaymentDateForPaid" type="text" class="datepicker default calculateFlag" style="width:160px;" readonly>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">本金</td>
	                    <td>
	                        <span>
	                            <input name="paidNormalPrincipalForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="calculateFlag moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">收益</td>
	                    <td>
	                        <span>
	                            <input name="paidNormalInterestForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">服务费</td>
	                    <td>
	                        <span>
	                            <input name="paidNormalServiceChargeForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">逾期本金</td>
	                    <td>
	                        <span>
	                            <input name="paidOverduePrincipalForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">逾期收益</td>
	                    <td>
	                        <span>
	                            <input name="paidOverdueInterestForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">逾期服务费</td>
	                    <td>
	                        <span>
	                            <input name="paidOverdueServiceChargeForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">展期费用</td>
	                    <td>
	                        <span>
	                            <input name="paidExtensionChargeForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">逾期管理费</td>
	                    <td>
	                        <span>
	                            <input name="paidOverdueManagerFeeForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">提前还款手续费</td>
	                    <td>
	                        <span>
	                            <input name="paidEarlyRepaymentChargeForPaid" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="moneyNum">
	                            <input type="hidden" name="settleStatusForPlanAdjustmentForPage">
	                            <input type="hidden" name="loanInfoIdForPlan">
	                        </span>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            <div class="dlg-wrapper-foot">
	                <a class="default-link confirm-link" href="javascript:void(0);" id="paidRepaymentPlanAdjusetConfirm" name="paidRepaymentPlanAdjusetConfirm">确定</a>
	                <a class="default-link back-link" href="javascript:void(0);" name="back">返回</a>
	            </div>
    		</form>
	    </div>
    </div>
</div>
<!--新还款信息模板-->
<div class="template-manualAdd hidden" id="template-newRepayPlan">
    <div class="manualAdd-head">
        <span>新还款计划</span>
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
	            <table class="repayDetail-table">
	                <colgroup>
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                    <col width="150px">
	                    <col width="200px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="repayDetail-til">还款编号</td>
	                    <td>
	                        <span name="loanNoForNewPlan">1</span>
	                    </td>
	                    <td class="repayDetail-til">期数</td>
	                    <td>
	                        <span name="repaymentPeriodForNewPlan"></span>
	                    </td>
	                    <td class="repayDetail-til">融资余额</td>
	                    <td>
	                        <span name="financeBalanceForNewPlan"></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">收益计算日</td>
	                    <td>
	                        <span name="valueDateForNewPlan"></span>
	                    </td>
	                    <td class="repayDetail-til">收益分配日</td>
	                    <td>
	                        <span name="settleInterestDateForNewPlan"></span>
	                    </td>
	                    <td class="repayDetail-til">还款日</td>
	                    <td>
	                        <span name="repaymentDateForNewPlan"></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">应还本金</td>
	                    <td>
	                        <span>
	                            <input name="accountPrincipalForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">应还收益</td>
	                    <td>
	                        <span>
	                            <input name="accountInterestForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">应还服务费</td>
	                    <td>
	                        <span>
	                            <input name="accountServiceChargeForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">逾期本金</td>
	                    <td>
	                        <span>
	                            <input name="overduePrincipalForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">逾期收益</td>
	                    <td>
	                        <span>
	                            <input name="overdueInterestForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">逾期服务费</td>
	                    <td>
	                        <span>
	                            <input name="overdueServiceChargeForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">展期费用</td>
	                    <td>
	                        <span>
	                            <input name="extensionChargeForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">逾期管理费</td>
	                    <td>
	                        <span>
	                            <input name="overdueManagerFeeForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                    <td class="repayDetail-til">提前还款手续费</td>
	                    <td>
	                        <span>
	                            <input name="paidEarlyRepaymentChargeForNewPlan" type="text" placeholder="￥" style="text-align: right; width: 160px;" class="forEditNewPlan moneyNum">
	                        </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="repayDetail-til">结清状态</td>
	                    <td>
	                        <span name="settleStatusForNewPlan"></span>
	                        <input type="hidden" name="settleStatusForNewPlanForPage">
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
