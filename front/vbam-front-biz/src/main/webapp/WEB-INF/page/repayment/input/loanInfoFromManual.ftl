<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="手动录入放款信息" jsFiles=["page/repayment/input/loanInfoFromManual.js","page/repayment/input/loanInfoValidatorRules.js","common/formValidator.js", "common/dateutil.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "放款信息录入" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <div style="margin-bottom:15px;">
            <a class="form-add-link" href="javascript:void(0);"><i class="AddIcon"></i>新建放款信息</a>
        </div>
        <div class="manualGridWrap">
        <div class="manualGrid"></div>
        </div>
        <div class="foot-empty">
		    <div class="dlg-wrapper-foot" id="foot-fixBar">
	        	<a class="default-link confirm-link" id="confirmSave" href="javascript:void(0);">提交</a><a class="default-link back-link redirect" href="${basepath}/loanInfo/input">返回</a>
	       	</div>
       	</div>
    </div>
</div>

<!--确认删除弹窗模板-->
<script id="template-del" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要执行操作吗？</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmDel" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<input type="hidden" name="" id="" class="template-leave">
<script id="template-leave" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">当前已录入放款信息，数据尚未提交，请确认是否离开？点击"确认"离开该页面，点击"取消"留在该页面。</span>
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
<div class="template-manualAdd hidden">
    <div class="manualAdd-head">
        <span>放款信息</span>
        <div class="manual-closeBar">
            <a class="closeHref" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
        <span id="failMessage" class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" role="alert" style="position: relative; top: 0px; left: 0px; display: none;">
			<span class="k-icon k-warning"> </span>
		</span>
    	<form id="loanInfoAdd-form" name="loanInfoAdd-form" >
	        <table class="dlg-manualTable">
	            <colgroup>
	                <col width="110px">
	                <col width="200px">
	                <col width="110px">
	                <col width="220px">
	                <col width="100px">
	                <col width="200px">
	            </colgroup>
	            <tbody>
	            <tr>
	                <td class="manual-title">放款编号</td>
	                <td>
	                    <input type="text" name ="loanNo" id="loanNo"></span>
	                    <span class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="loanNo" role="alert" style="position: relative; top: 0px; left: 0px; display: none;">
							<span class="k-icon k-warning"> </span>
						</span>
	                </td>
	                <td class="manual-title">业务合同号</td>
	                <td>
	                    <input type="text" name="businessContractNo" id="businessContractNo" style="width:180px">
	                    <span data-for="businessContractNo" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">币别</td>
	                <td>
	                    <select class="selectWrap" data-role="dropdownlist" name="currency" id="currency" style="width:200px">
	                        <#list currencys as currency>
								<option value=${currency.desc()}>${currency.desc()}</option>
							</#list>
	                    </select>
	                    <span data-for="currency" class="k-invalid-msg"></span>
	                </td>
	            </tr>
	            <tr>
	                <td class="manual-title">融资客户</td>
	                <td>
	                    <input type="text" name="financeCompany" id="financeCompany">
	                    <span data-for="financeCompany" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">核心企业</td>
	                <td>
	                    <input type="text" name="coreCompanyName" id="coreCompanyName">
	                    <span data-for="coreCompanyName" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">凭证号码</td>
	                <td>
	                    <input type="text" name="voucherNo" id="voucherNo" style="width:200px">
	                    <span data-for="voucherNo" class="k-invalid-msg"></span>
	                </td>
	            </tr>
	            <tr>
	            	<td class="manual-title">业务产品</td>
	                <td>
	                    <input type="text" name="businessProduct" id="businessProduct">
	                    <span data-for="businessProduct" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">应收账款金额</td>
	                <td>
	                    <input type="text" name="receivablesAmount" id="receivablesAmount"  placeholder="￥" class="cashTxt moneyNum">
	                    <span data-for="receivablesAmount" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">应收账款余额</td>
	                <td>
	                    <input type="text" name="receivablesBalance" id="receivablesBalance"  placeholder="￥" class="cashTxt moneyNum" style="width:200px">
	                    <span data-for="receivablesBalance" class="k-invalid-msg"></span>
	                </td>
	            </tr>
	            <tr>
	            	<td class="manual-title">融资金额</td>
	                <td>
	                    <input type="text" name="financeAmount" id="financeAmount"  placeholder="￥" class="cashTxt moneyNum">
	                    <span data-for="financeAmount" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">融资余额</td>
	                <td>
	                    <input type="text" name="financeBalance" id="financeBalance"  placeholder="￥" class="cashTxt moneyNum">
	                    <span data-for="financeBalance" class="k-invalid-msg"></span>
	                </td>
	                <td class="manual-title">费率单位</td>
	                <td>
	                    <select class="selectWrap" data-role="dropdownlist" name="interestRateUnit" id="interestRateUnit" style="width:200px">
	                        <#list interestRateUnits as interestRateUnit>
								<option value=${interestRateUnit.desc()}>${interestRateUnit.desc()}</option>
							</#list>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	            	<td class="manual-title">费率</td>
	                <td>
	                    <input type="text" name="interestRate" id="interestRate">%
	                    <span data-for="interestRate" class="k-invalid-msg"></span>
	                </td>
					<td class="manual-title">还款方式</td>
	                <td>
	                    <select class="selectWrap" data-role="dropdownlist" name="repaymentMethod" id="repaymentMethod" style="width:180px">
	                        <#list repaymentTypes as repaymentType>
								<option value=${repaymentType.desc()}>${repaymentType.desc()}</option>
							</#list>
	                    </select>
	                </td>
	                <td class="manual-title">还款账号</td>
	                <td id="accountNoTd">
	                    <select class="selectWrap" data-role="dropdownlist" name="accountNo" id="accountNo" style="width:200px">
	                    </select>
	                </td>
	            </tr>
	            <tr>
	            	<td class="manual-title">放款日</td>
	                <td>
	                    <input type="text" class="datepicker default" name="loanDate" id="loanDate" readonly>
	                </td>
	                <td class="manual-title">期限单位</td>
	                <td>
	                    <select class="selectWrap" data-role="dropdownlist" name="loanPeriodUnit" id="loanPeriodUnit" style="width:180px">
	                        <#list dateUnits as dateUnit>
								<option value=${dateUnit.desc()}>${dateUnit.desc()}</option>
							</#list>
	                    </select>
	                </td>
	                <td class="manual-title">贷款期限</td>
	                <td>
	                    <input type="text" name="loanPeriod" id="loanPeriod" style="width:200px">
	                    <span data-for="loanPeriod" class="k-invalid-msg"></span>
	                </td>
	            </tr>
	            <tr>
	                <td class="manual-title">到期日</td>
	                <td>
	                    <span name="dueDate" id="dueDate"></span>
	                </td>
	            </tr>
	            </tbody>
	        </table>
	        <div class="dlg-wrapper-foot add-button">
	            <a class="default-link confirm-link" id="confirmAdd" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
	        </div>
	       	<div class="dlg-wrapper-foot edit-button hidden">
	            <a class="default-link confirm-link" id="confirmEdit" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
	        </div>
        </form>
    </div>
</div>