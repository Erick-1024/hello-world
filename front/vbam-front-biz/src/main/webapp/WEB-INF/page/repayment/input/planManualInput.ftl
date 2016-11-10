<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="手动录入还款计划" jsFiles=["page/repayment/input/planFromManual.js","page/repayment/input/planValidatorRules.js","common/formValidator.js","common/dateutil.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划录入" removeExtHeader = false removeExtFooter = false>
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
                        1.点击“新增还款计划”。<br/>
                        2.填写弹出框中信息，并点击“确认”。<br/>
                        3.按期录入还款计划，如有多期，重复1、2步骤。<br/>
                        4.如有费用，点击“费用列表”新增还款费用。
                    </div>
                </div>
            </div>
            <div class="finance-wrap">
                <div class="finance-title">还款要素</div>
                <table class="repayment-element">
                <colgroup>
                    <col width="120">
                    <col width="200">
                    <col width="120">
                    <col width="250">
                    <col width="120">
                    <col width="200">
                </colgroup>
                    <tbody>
                    <tr>
                        <td class="manual-title">融资客户</td>
                        <td>
                            <span id="financeCompanyForShow" name="financeCompanyForShow">${(loanInfoElements.financeCompany)!''}</span>
                            <input type="hidden" id="redisKey" name='redisKey' value="${redisKey!''}">
                            <input type="hidden" id="loanInfoId" name='loanInfoId' value="${loanInfoId!''}">
                            <input type="hidden" id="id" name="id" value="${loanInfoElements.id!}">
                            <input type="hidden" id="loanNo" name="loanNo" value="${loanInfoElements.loanNo!}">
                        </td>
                        <td class="manual-title">核心企业</td>
                        <td>
                            <span id="coreCompanyNameForShow" name="coreCompanyNameForShow">${(loanInfoElements.coreCompanyName)!''}</span>
                        </td>
                        <td class="manual-title">融资金额</td>
                        <td>
                            ￥<span id="financeAmountForShow" name="financeAmountForShow">${(loanInfoElements.financeAmount)!''}</span>
                            <input type="hidden" id="financeBalance" value=${loanInfoElements.financeBalance!}>
                        </td>
                        
                    </tr>
                    <tr>
                    	<td class="manual-title">${(loanInfoElements.interestRateUnit)!''}费率</td>
                        <td>
                            <span>${(loanInfoElements.interestRate)!''}</span>
                        </td>
                        <td class="manual-title">逾期管理费率</td>
                        <td>
                            <span>${(loanInfoElements.penaltyRate)!''}</span>
                        </td>
                        <td class="manual-title">放款日</td>
                        <td>
                            <span name="loanDateForShow">${(loanInfoElements.loanDate)!''}</span>
                        </td>
                    </tr>
                    <tr>
                    	<td class="manual-title">到期日</td>
                        <td>
                            <span>${(loanInfoElements.dueDate)!''}</span>
                        </td>
                        <td class="manual-title">还款方式</td>
                        <td>
                            <span>${(loanInfoElements.repaymentMethod)!''}</span>
                        </td>
                        <td class="manual-title">期数</td>
                        <td>
                            <span name="repaymentPeriodForShow">${(loanInfoElements.repaymentPeriod)!'0'}</span>
                        </td>
                    </tr>
                    <tr>
                    	<td class="manual-title">还款账号</td>
                        <td>
                            <span>${(loanInfoElements.accountNo)!''}</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
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
                <div style="margin-top:15px;">
                    <a class="form-add-link addRepayPlan" href="javascript:void(0);"><i class="AddIcon"></i>新增还款计划</a>
                </div>
                <div id="manualGrid-planWrap">
	                <div class="finance-grid" id="manualGrid-plan" style="margin-top:15px;"></div>
                </div>
            </div>
            <div class="repayment-grid hidden" id="repayment-cost">
                <div style="margin-top:15px;">
                    <a class="form-add-link addRepayCost" href="javascript:void(0);"><i class="AddIcon"></i>新增还款费用</a>
                </div>
                <div id="manualGrid-costWrap">
	                <div class="finance-grid" id="manualGrid-cost" style="margin-top:15px;"></div>
                </div>
            </div>
            <div class="foot-empty">
	            <div class="dlg-wrapper-foot" id="foot-fixBar">
					<form id="saveInfo" action="saveCorrectRepaymentPlanAndExpense" method="post">
	    				<input type="hidden" name="redisKey" value="${redisKey}"> 
	    				<input type="hidden" id="loanInfoIdForSave" name="loanInfoIdForSave" value="${loanInfoElements.id!}">
	    			</form>
	                <a class="default-link confirm-link" href="javascript:void(0);" id="savePlanAndExpenseFromManual">提交</a><a class="default-link back-link redirect" href="${basepath}/repayment/plan/input/planSelect">返回</a>
	            </div>
            </div>
        </section>

    </div>
</div>

<!--确认删除弹窗模板-->
<script id="template-del" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要执行操作吗？</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmDel" href="javascript:void(0);" single="" >确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

<script id="template-del-expense" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要执行操作吗？</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmDelExpense" href="javascript:void(0);" single="" >确认</a><a class="default-link back-link" href="javascript:void(0);" style="display:none">取消</a>
        </div>
    </div>
</script>
<input type="hidden" name="" id="" class="template-leave">
<script id="template-leave" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">当前还款计划已上传，数据尚未提交，请确认是否离开？点击"确认"离开该页面，点击"取消"留在该页面。</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmLeave" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
</@hb.htmlBase>
<!--遮罩层-->
<div class="window-overlay hidden"></div>
<!--新增录入信息弹窗模板-->
<div class="template-manualAdd hidden" id="template-manualPlan">
    <div class="manualAdd-head">
        <span>还款计划信息</span>
        <div class="manual-closeBar">
            <a class="closeHref planCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
		<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForPlan">
			<span class="k-icon k-warning"> </span>
		</span>
		<form name="planAdd" id="planAdd">
	        <table class="dlg-manualTable">
	            <colgroup>
	                <col width="100px">
	                <col width="200px">
	                <col width="100px">
	                <col width="200px">
	                <col width="100px">
	                <col width="200px">
	            </colgroup>
	            <tbody>
		            <tr>
		                <td class="manual-title">放款编号</td>
		                <td>
		                	<span name="loanNoForAdd">${(loanInfoElements.loanNo)!''}</span>
		                </td>
		                <td class="manual-title">期数</td>
		                <td>
		                	<span name="repaymentPeriodForAdd"></span>
		                </td>
		                <td class="manual-title">到期日</td>
		                <td>
		                    <span name="dueDateForAdd">${(loanInfoElements.dueDate)!''}</span>
		                </td>
		            </tr>
		            <tr>
		                <td class="manual-title">融资余额</td>
		                <td>
		                    <input type="text" placeholder="￥" class="cashTxt moneyNum" name="financeBalanceForAdd" >
		                </td>
		                <td class="manual-title">收益计算日</td>
		                <td>
		                    <input type="text" class="datepicker default" readonly name="valueDateForAdd">
		                </td>
		                <td class="manual-title">收益分配日</td>
		                <td>
		                    <input type="text" class="datepicker default" readonly name="settleInterestDateForAdd">
		                </td>
		            </tr>
		            <tr>
		                <td class="manual-title">还款日</td>
		                <td>
		                    <input type="text" class="datepicker default" readonly name="repaymentDateForAdd">
		                </td>
		                <td class="manual-title">应还本金</td>
		                <td>
		                    <input type="text" placeholder="￥" class="cashTxt moneyNum" name="accountRepaymentPrincipalForAdd">
		                </td>
		                <td class="manual-title">应还收益</td>
		                <td>
		                    <input type="text" placeholder="￥" class="cashTxt moneyNum" name="accountRepaymentInterestForAdd">
		                </td>
		            </tr>
		            <tr>
		                <td class="manual-title">应还服务费</td>
		                <td>
		                    <input type="text" placeholder="￥" class="cashTxt moneyNum" name="accountRepaymentServiceChargeForAdd">
		                </td>
		                <td class="manual-title">应还总金额</td>
		                <td>
		                    <span name="accountRepaymentTotalForAdd"></span>
		                </td>
		                <td class="manual-title">结清状态</td>
		                <td>
		                    <div class="radioContent" name="settleStatusForAdd">
		                        <label class="radio active" id="unSettle">
		                            <span class="radioIcon"></span>
		                            <span class="labelFonts">未结清</span>
		                        </label>
		                        <label class="radio" id="settled">
		                            <span class="radioIcon"></span>
		                            <span class="labelFonts">已结清</span>
		                        </label>
		                    </div>
		                </td>
		            </tr>
	            </tbody>
	        </table>
	        <div class="dlg-wrapper-foot save-button" >
	            <a id="confirmForAdd" class="default-link confirm-link" href="javascript:void(0);" singleId="">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
	        </div>
	        <div class="dlg-wrapper-foot edit-button hidden">
	            <a class="default-link confirm-link" id="confirmForEdit" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
	        </div>
	    </form>
    </div>
</div>
<!--新增还款费用模板-->
<div class="template-manualAdd hidden" id="template-manualCost" style="margin-left: -600px;width: 1250px;">
   	<form id="costAdd">
		<div class="manualAdd-head">
		    <span>还款计划费用</span>
		    <div class="manual-closeBar">
		        <a class="closeHref costCloseBar" href="javascript:void(0);">
		            <span class="closeIcon"></span>
		        </a>
		    </div>
		</div>
	    <div class="manualAddCost-content clearfix">
        	<div class="manualAddCost-left" style="width:">
				<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForExpense">
					<span class="k-icon k-warning"></span>
				</span>
	            <table class="dlg-manualTable">
	                <colgroup>
	                <col width="20%">
	                <col width="80%">
	                </colgroup>
	                <tbody id="manualTbody">
	                <tr>
	                    <td class="manual-title">费用名目</td>
	                    <td>
	                        <input type="text" id="costName" name="costName">
	                    </td>
	                </tr>
	                <tr>
	                    <td class="manual-title">计费方式</td>
	                    <td>
	                        <div class="radioContent" id="chargeMethod" name="chargeMethod"v>
	                            <label class="radio active" data-chargeMethod="比例">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">比例</span>
	                            </label>
	                            <label class="radio" data-chargeMethod="定额">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">定额</span>
	                            </label>
	                        </div>
	                    </td>
	                </tr>
	                <tr id="chargeStandardTr">
	                    <td class="manual-title">计费基准</td>
	                    <td>
	                        <div class="radioContent" id="chargeStandard" name="chargeStandard" style="width:500px">
	                            <label class="radio active" data-chargeStandard="融资金额">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">融资金额</span>
	                            </label>
	                            <label class="radio" data-chargeStandard="融资余额">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">融资余额</span>
	                            </label>
	                            <label class="radio" data-chargeStandard="其他">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">其他</span>
		                            <input type="text" id="othersChargeStandard" name="othersChargeStandard" style="width:100px; height:30px;" class="moneyNum">
		                            <span data-for="othersChargeStandard" class="k-invalid-msg"></span>
	                            </label>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="manual-title">计费值</td>
	                    <td>
	                        <input type="text" id="chargeVal" name="chargeVal" class="moneyNum" style="text-align:right"><span id="chargeValUnit">%</span>
	                    </td>
	                </tr>
	                <tr id="chargeCount">
	                
	                	<td class="manual-title">计费次数</td>
	                    <td>
	                        <div class="checkboxWrap" id="count" name="count">
	                            <label class="radio active" data-count="1">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">单次</span>
	                            </label>
	                            <label class="radio">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">多次</span>
	                                <input type="number" id="chargeCnt" name="chargeCnt" min="1" value="1" style="width:60px; height:25px;">
	                            </label>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="manual-title">结清状态</td>
	                    <td>
	                        <div class="radioContent" id="settleStatus" name="settleStatus">
	                            <label class="radio active" data-settlestatus="未结清">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">未结清</span>
	                            </label>
	                            <label class="radio" data-settlestatus="已结清">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">已结清</span>
	                            </label>
	                        </div>
	                    </td>
                	</tr>
	                <tr class="date-row">
	                    <td class="manual-title">还款日</td>
	                    <td>
	                        <input type="text" class="datepicker default" id="repaymentDate" name="repaymentDate" readonly>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="manualAddCost-right">
	            <table class="maunalCost-calc">
	                <colgroup>
	                    <col width="30%">
	                    <col width="70%">
	                </colgroup>
	                <tbody>
	                <th colspan="2">计算器</th>
	                <tr>
	                    <td class="manual-title">费用名目</td>
	                    <td>
	                        <span id="costNameRight"></span>
	                    </td>
	                </tr>
	                <tr id="chargeStandardRightTr">
	                    <td class="manual-title">计费基准值</td>
	                    <td>
	                        <span id="chargeStandardRight">${(loanInfoElements.financeAmount)!}</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="manual-title">总费用</td>
	                    <td>
	                        <span id="totalCostRight"></span>
	                    </td>
	                </tr>
	                <tr id="chargeCountRight">
	                    <td class="manual-title">计费次数</td>
	                    <td>
	                        <span id="countRight">1</span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="manual-title">每期费用</td>
	                    <td>
	                        <span id="singleCostRight"></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="manual-title">还款日期</td>
	                    <td>
	                        <span id="repaymentDateRight"></span>
	                    </td>
	                </tr>
	            	</tbody>
	        	</table>
	   		</div>
	    </div>
	    <div class="manualAddCost-foot add-button">
	        <a class="default-link confirm-link" id="comfirmAddExpense" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
	    </div>
    </form>
</div>
<!--修改还款费用模板-->
<div class="template-manualAdd hidden" id="template-editManualCost" style="margin-top:-113px;">
    <div class="manualAdd-head">
        <span>修改还款费用</span>
        <div class="manual-closeBar">
            <a class="closeHref editCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
    	<span class="k-tooltip-validation" role="alert" style="position: relative; top: 0px; left: 10px; visibility:hidden" name="errorPromptForExpenseEdit">
			<span class="k-icon k-warning"></span>
		</span>
        <table class="dlg-manualTable">
            <colgroup>
                <col width="100px">
                <col width="200px">
                <col width="100px">
                <col width="200px">
                <col width="100px">
                <col width="200px">
            </colgroup>
            <tbody>
            <tr>
                <td class="manual-title">费用名目</td>
                <td>
                    <input type="text" id="costNameForEdit">
                </td>
                <td class="manual-title">还款日</td>
                <td>
                    <input type="text" class="datepicker default" id="repaymentDateForEdit" readonly>
                </td>
                <td class="manual-title">应还金额</td>
                <td>
                    <input type="text" placeholder="￥" id="repaymentAmountForEdit" class="cashTxt moneyNum">
                </td>
            </tr>
            <tr>
                <td class="manual-title">结清状态</td>
                <td>
                    <div class="radioContent" id="settleStatusForEdit">
                        <label class="radio active" data-settlestatus="未结清">
                            <span class="radioIcon"></span>
                            <span class="labelFonts">未结清</span>
                        </label>
                        <label class="radio" data-settlestatus="已结清">
                            <span class="radioIcon"></span>
                            <span class="labelFonts">已结清</span>
                        </label>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link" id="comfirmEditExpense" href="javascript:void(0);">提交</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</div>