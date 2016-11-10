<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="新增/修改放款申请" jsFiles=["js/common/jquery.nav.js", "js/common/business.js", "page/asset/loan/addLoanByHand.js","page/asset/loan/loanFormValidatorRules.js","page/asset/loan/templateInit.js", "common/cana.util.js","common/dateutil.js","common/formValidator.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "融资管理" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<script>
	var isEditLoan = ${isEditLoan?string('true', 'false')};
	var loanFinanceAmount = '${(cent2yuan(assetLoanDTO.financeAmount))!"0.00"}';
	<#if (assetLoanDTO.counterpartyId)??>
		var counterpartyId = ${assetLoanDTO.counterpartyId};
	</#if>
	var existPaidInfo = 'false';
	<#if (assetLoanDTO.existPaidInfo)??>
		existPaidInfo = ${assetLoanDTO.existPaidInfo?string('true','false')};
	</#if>
	var invoiceInfoList = [];
	<#if (assetLoanDTO.invoiceInfoList)??>
		<#list assetLoanDTO.invoiceInfoList as invoiceInfo>
			var invoice = {};
			invoice.id = '${invoiceInfo.id}';
			invoice.counterparty = '${invoiceInfo.counterparty}';
			invoice.invoiceNo = '${invoiceInfo.invoiceNo}';
			invoice.nominvoiceAmt = '${formatMoney(invoiceInfo.nominvoiceAmt)}';
			invoice.invoiceAmt = '${formatMoney(invoiceInfo.invoiceAmt)}';
			invoice.financingRatio = '${invoiceInfo.financingRatio}';
			invoice.invoiceDate = '${invoiceInfo.invoiceDate}';
			invoice.dueDate = '${invoiceInfo.dueDate}';
			invoiceInfoList.push(invoice);
		</#list>
	</#if>
	var credit = {};
	<#if creditDTO??>
		credit.creditMode = '${creditDTO.creditMode}';
		credit.creditModeDesc = '${creditDTO.creditModeDesc}';
		credit.currencyDesc = '${creditDTO.currencyDesc}';
		credit.totalLimitStr = '${creditDTO.totalLimitStr}';
		credit.usedLimitStr = '${creditDTO.usedLimitStr}';
		credit.availableLimitStr = '${creditDTO.availableLimitStr}';
		credit.dueDate = '${creditDTO.dueDate}';
	</#if>
	var expenseList = [];
	<#if (assetLoanDTO.expenseList)??>
		<#list assetLoanDTO.expenseList as expense>
			var expense = {};
			expense.expenseSubject = '${expense.expenseSubject}';
			expense.amountStr = '${expense.amountStr}';
			expenseList.push(expense);
		</#list>
	</#if>
	var loanPlanList = [];
	<#if (assetLoanDTO.loanPlanList)??>
		<#list assetLoanDTO.loanPlanList as loanPlan>
			var loanPlan = {};
			loanPlan.financeBalance = '${loanPlan.financeBalance}';
			loanPlan.valueDate = '${loanPlan.valueDate}';
			loanPlan.settleInterestDate = '${loanPlan.settleInterestDate}';
			loanPlan.repaymentDate = '${loanPlan.repaymentDate}';
			loanPlan.accountPrincipal = '${loanPlan.accountPrincipal}';
			loanPlan.accountInterest = '${loanPlan.accountInterest}';
			loanPlan.accountOverdue = '${loanPlan.accountOverdue}';
			loanPlan.accountAmount = '${loanPlan.accountAmount}';
			loanPlan.settleStatusDesc = '${SettleStatus[loanPlan.settleStatus]}';
			loanPlan.settleStatus = '${loanPlan.settleStatus}';
			loanPlan.paidOverdue = '${cent2yuan(loanPlan.paidOverdue)}';
			loanPlan.existPaidInfo = ${loanPlan.existPaidInfo?string('true', 'false')};
			loanPlanList.push(loanPlan);
		</#list>
	</#if>
	
</script>

<div class="pro-bg" style="background: #fff;">
    <form id="addLoanByHandForm" class="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-three">应收账款&ensp;▶</a></li>
                <li><a href="#nav-four">额度信息&ensp;▶</a></li>
                <li><a href="#nav-two">放款信息&ensp;▶</a></li>
                <li><a href="#nav-five">费用信息&ensp;▶</a></li>
                <li><a href="#nav-six">还款计划&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--触发按钮-->
            <a href="javascript:void(0);" class="open-loanPop" style="display:none;">触发新建弹窗</a>
            <a href="javascript:void(0);" class="open-receivablesPop" style="display:none;">触发应收账款弹窗</a>
            <a href="javascript:void(0);" class="open-costPop" style="display:none;">触发费用弹窗</a>
            <a href="javascript:void(0);" class="open-repaymentPop" style="display:none;">触发还款计划弹窗</a>
            <a href="javascript:void(0);" class="open-message-btn" style="display:none;">触发还款计划弹窗</a>
            <!--触发按钮-->
            <!--基本信息-->
            <div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="350px">
                        <col width="120px">
                        <col width="350px">
                        <col width="120px">
                        <col width="">
                    </colgroup>
                    <tbody>
	                    <tr>
	                        <th><span class="redFalg">*</span>业务合同号</th>
	                        <td>
	                        	<input id="businessContractNo" type="text" style="width:200px;" name="businessContractNo" data-required-msg="不能为空" required <#if isEditLoan>value="${assetLoanDTO.businessContractNo!}" disabled="disabled"</#if>>
	                        </td>
	                        <th>放款编号</th>
	                        <td><input id="loanInfoId" type="text" style="width:200px;" class="" name="loanInfoId" disabled="disabled" <#if isEditLoan>value="${assetLoanDTO.id!}"</#if>></td>
	                        <th><span class="redFalg">*</span>交易对手</th>
	                        <td>
	                        	<select style="width:200px;" name="counterpartySelect" id="counterpartySelect" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
		                        	<#if isEditLoan && counterpartyDTOList??>
		                        		<#list counterpartyDTOList as counterpartyDTO>
	                                    	<#if counterpartyDTO.counterpartyId == assetLoanDTO.counterpartyId>
	                                    		<option value=${counterpartyDTO.counterpartyId} selected = "selected">${counterpartyDTO.counterparty}</option>
	                                    	<#else>
		                                    	<option value=${counterpartyDTO.counterpartyId}>${counterpartyDTO.counterparty}</option>
	                                    	</#if>
										</#list>
		                        	</#if>
	                            </select>
	                            <span class="new-manual-text" id="noInvoiceTip" style="display:none">没有可用的应收账款</span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>融资客户</th>
	                        <td><input id="financeCustomerName" type="text" style="width:200px;" class="" name="financeCustomerName" required disabled="disabled" <#if isEditLoan>value="${assetLoanDTO.customerName!}"</#if>></td>
	                        <th>项目名称</th>
	                        <td><input id="projectName" type="text" style="width:200px;" class="" name="projectName" disabled="disabled" <#if isEditLoan>value="${assetLoanDTO.projectName!}"</#if>></td>
	                        <th>业务产品</th>
	                        <td><input id="businessProduct" type="text" style="width:200px;" class="" name="businessProduct" disabled="disabled" <#if isEditLoan>value="${(assetLoanDTO.businessProduct.desc())!}"</#if>></td>
	                    </tr>
                    </tbody>
                </table>
            </div>
            <!--应收账款-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">应收账款</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
            	<#if !isEditLoan || ((assetLoanDTO.existPaidInfo)?? && !assetLoanDTO.existPaidInfo)><!--不存在还款-->
	                <div style="margin-top:15px;text-align: left;">
	                    <a class="status-normal status-chk add-receivables-btn" href="javascript:void(0);">新增</a>
	                    <!--<a class="status-normal status-chk ch-receivables-btn" href="javascript:void(0);">修改</a>-->
	                    <a class="status-normal status-chk del-receivables-btn" href="javascript:void(0);">删除</a>
	
	                    <a class="open-sec-btn" href="javascript:void(0);" style="display:none;"></a>
	                </div>
                </#if>
                <div class="min-box-width" style="width:970px;">
                    <!--应收账款信息table title-->
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="60px">
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
                            <th>选择</th>
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
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="receivables-tb">
                            <colgroup>
                                <col width="60px">
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
                    <div class="fact-tb-box">
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
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
             <!--放款信息-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">放款信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="350px">
                        <col width="120px">
                        <col width="350px">
                        <col width="120px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th><span class="redFalg">*</span>融资金额</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
	                        	<input id="loanAmount" name="loanAmount" type="text" style="width:200px;" class="moneyNum" data-required-msg="不能为空" required <#if isEditLoan>value="${formatCentMoney(assetLoanDTO.financeAmount)}" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if></#if>>
	                        	<div class="client-unit-two">元</div>
	                        	<#if !isEditLoan || ((assetLoanDTO.existPaidInfo)?? && !assetLoanDTO.existPaidInfo)><!--不存在还款-->
	                        		<div class="maxMoneyNum" style="top:-17px;">最高放款金额：<span id="maxLoanAmount">0.00</span>元</div>
	                        	</#if>
                        	</label>
                        </td>
                        <th><span class="redFalg">*</span>币种</th>
                        <td>
                            <select class="" style="width:200px;" name="currencyType" id="currencyType" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
                                <#if currencyTypeList??>
                                    <#list currencyTypeList as currencyType>
                                    	<#if isEditLoan && currencyType == assetLoanDTO.currency>
                                    		<option value=${currencyType.name()} selected = "selected">${currencyType.desc()}</option>
                                    	<#else>
	                                    	<option value=${currencyType.name()}>${currencyType.desc()}</option>
                                    	</#if>
									</#list>
								</#if>
                            </select>
                        </td>
                        <th><span class="redFalg">*</span>还款方式</th>
                        <td>
                        	<select class="" style="width:200px;" name="repaymentMethod" id="repaymentMethod" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
                                <#if repaymentMethodList??>
                                    <#list repaymentMethodList as repaymentMethod>
                                    	<#if isEditLoan && repaymentMethod == assetLoanDTO.repaymentMethod>
                                    		<option value=${repaymentMethod.name()} selected = "selected">${repaymentMethod.desc()}</option>
                                    	<#elseif repaymentMethod.name()!='EQUALALL' && repaymentMethod.name()!='EQUALPRINCIPAL'>
                                    		<option value=${repaymentMethod.name()}>${repaymentMethod.desc()}</option>
                                    	</#if>
									</#list>
								</#if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>利率</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
	                            <input id="interest" name="interest" type="text" style="width:200px;" data-required-msg="不能为空" required <#if isEditLoan>value="${(assetLoanDTO.interestRate*100)?string("0.000")}" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if></#if>>
	                            <div class="client-unit-two">%</div>
	                        </label>
                        </td>
                        <th><span class="redFalg">*</span>利率单位</th>
                        <td>
                            <select class="" style="width:200px;" name="interestRateUnit" id="interestRateUnit" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
		                        <#if interestRateUnitList??>
		                            <#list interestRateUnitList as interestRateUnit>
		                            	<#if isEditLoan && interestRateUnit == assetLoanDTO.interestRateUnit>
                                    		<option value=${interestRateUnit.name()} selected = "selected">${interestRateUnit.desc()}</option>
                                    	<#else>
			                            	<option value=${interestRateUnit.name()}>${interestRateUnit.desc()}</option>
                                    	</#if>
									</#list>
								</#if>
		                    </select>
                        </td>
                        <th><span class="redFalg">*</span>计息基准天数</th>
                        <td>
                            <select class="" style="width:200px;" name="interestBaseDays" id="interestBaseDays" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
		                        <#if interestBaseDaysList??>
		                            <#list interestBaseDaysList as interestBaseDays>
		                            	<#if isEditLoan && interestBaseDays == assetLoanDTO.dayCountConvention?string.number>
                                    		<option value=${interestBaseDays} selected = "selected">${interestBaseDays}</option>
                                    	<#else>
			                            	<option value=${interestBaseDays}>${interestBaseDays}</option>
                                    	</#if>
									</#list>
								</#if>
		                    </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>融资期限</th>
                        <td><input name="loanPeriod" id="loanPeriod" type="text" style="width:200px;" data-required-msg="不能为空" required <#if isEditLoan>value="${assetLoanDTO.loanPeriod!}" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if></#if>></td>
                        <th><span class="redFalg">*</span>期限单位</th>
                        <td>
                            <select class="" style="width:200px;" name="dateUnit" id="dateUnit" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
                                <#if dateUnitList??>
                                    <#list dateUnitList as dateUnit>
                                    	<#if isEditLoan && dateUnit == assetLoanDTO.loanPeriodUnit>
                                    		<option value=${dateUnit.name()} selected = "selected">${dateUnit.desc()}</option>
                                    	<#else>
                                    		<option value=${dateUnit.name()}>${dateUnit.desc()}</option>
                                    	</#if>
									</#list>
								</#if>
                            </select>
                        </td>
                        <th><span class="redFalg">*</span>还款账号</th>
                        <td>
                            <select class="" style="width:200px;" name="repaymentAccount" id="repaymentAccount" data-role="dropdownlist" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if>>
                            	<#if isEditLoan && accountNoList??>
	                        		<#list accountNoList as accountNo>
                                    	<#if formatBankAccountNo(accountNo) == formatBankAccountNo(assetLoanDTO.accountNo)>
                                    		<option value="${accountNo}" selected = "selected">${formatBankAccountNo(accountNo)}</option>
                                    	<#else>
	                                    	<option value="${accountNo}">${formatBankAccountNo(accountNo)}</option>
                                    	</#if>
									</#list>
	                        	</#if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>放款日</th>
                        <td><input name="loanDate" id="loanDate" type="text" style="width:200px;" class="datepicker time-onex hasIcon" data-required-msg="不能为空" required <#if isEditLoan>value="${assetLoanDTO.loanDate!}" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if></#if>></td>
                        <th><span class="redFalg">*</span>到期日</th>
                        <td><input name="overdueDate" id="overdueDate" type="text" style="width:200px;" class="datepicker time-twox hasIcon" data-required-msg="不能为空" required <#if isEditLoan>value="${assetLoanDTO.dueDate!}" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if></#if>></td>
                        <th><span class="redFalg">*</span>逾期费率(天)</th>
                        <td>
                        	<label class="client-unit-box" style="width: 350px;">
	                        	<input name="overdueInterest" id="overdueInterest" type="text" style="width:200px;" data-required-msg="不能为空" required <#if isEditLoan>value="${(assetLoanDTO.penaltyRate*100)?string("0.000")}" <#if (assetLoanDTO.existPaidInfo)?? && assetLoanDTO.existPaidInfo>disabled="disabled"</#if></#if>>
	                        	<div class="client-unit-two">%</div>
                        	</label>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <!--费用信息-->
            <div class="pro-title" id="nav-five"><span class="pro-title-left">费用信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
            	<#if !isEditLoan || ((assetLoanDTO.existPaidInfo)?? && !assetLoanDTO.existPaidInfo)><!--不存在还款-->
	                <div style="margin-top:15px;text-align: left;">
	                    <a class="status-normal status-chk add-cost-btn" href="javascript:void(0);">新增</a>
	                    <a class="status-normal status-chk ch-cost-btn" href="javascript:void(0);">修改</a>
	                    <a class="status-normal status-chk del-cost-btn" href="javascript:void(0);">删除</a>
	                    <a class="open-sec-btn" href="javascript:void(0);" style="display:none;"></a>
	                </div>
                </#if>
                <div class="min-box-width" style="width:970px;">
                    <!--费用管理table title-->
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="100px">
                            <col width="100px">
                            <col width="200px">
                            <col width="200px">
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <th>选择</th>
                            <td>序号</td>
                            <td>费用科目</td>
                            <td>金额</td>
                            <td></td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <!--费用管理table tbody-->
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="cost-tb">
                            <colgroup>
                                <col width="100px">
                                <col width="100px">
                                <col width="200px">
                                <col width="200px">
                                <col width="">
                            </colgroup>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--还款计划-->
            <div class="pro-title" id="nav-six"><span class="pro-title-left">还款计划</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div style="margin-top:15px;text-align: left;">
                	<#if !isEditLoan>
                		<a id="auto-generate-repayment-btn" class="status-normal status-chk" href="javascript:void(0);">自动生成</a>
                	</#if>
                    <a class="status-normal status-chk add-repayment-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk ch-repayment-btn" href="javascript:void(0);">修改</a>
                    <a class="status-normal status-chk del-repayment-btn" href="javascript:void(0);">删除</a>

                    <a class="open-sec-btn" href="javascript:void(0);" style="display:none;"></a>
                </div>
                <div class="min-box-width" style="width:970px;">
                    <!--还款计划table title-->
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="60px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
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
                            <th>选择</th>
                            <td>融资余额</td>
                            <td>起息日</td>
                            <td>结息日</td>
                            <td>还款日</td>
                            <td>应还本金</td>
                            <td>应还利息</td>
                            <td>逾期费</td>
                            <td>应还总金额</td>
                            <td>结清栏</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <!--还款计划table tbody-->
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="repayment-tb">
                            <colgroup>
                                <col width="60px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="">
                            </colgroup>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="client-foot">
                <a id="submitAddLoan" class="form-search-link sc-btn" href="javascript:void(0);">提交</a>
                <a class="form-search-link" href="${basepath}/asset/loan/goto/loanList">返回</a>
            </div>
        </div>
    </form>
</div>
<footer id="footTop">Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>

<#include 'templateInvoiceInfo.ftl'/>
<#include 'templateChargeInfo.ftl'/>
<#include 'templatePlanInfo.ftl'/>
<#include '../../tipBoxTemplate.ftl'/>
<#include '../../confirmBoxTemplate.ftl'/>

</@hb.htmlBase>