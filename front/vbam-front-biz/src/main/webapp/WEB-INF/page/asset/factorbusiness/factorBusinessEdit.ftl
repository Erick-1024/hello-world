<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="业务管理" jsFiles=["js/common/jquery.nav.js","js/common/business.js","page/asset/factorbusiness/factorBusinessEdit.js","common/formValidator.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "业务管理" removeExtHeader = false removeExtFooter = true>
<div class="pro-bg" style="background: #fff;">
    <form id="businessFactorForm" action="${basepath}/asset/factorBusiness/save" method="post" class="">
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
                        <#if customer ??>
	                        <td>${(customer.customerName)!''}<input name="customerId" type="hidden" style="width:200px;" class="" value="${(customer.id)!''}"></td>
                        <#else>
	                        <td>${(factorBusinessDTO.customerName)!''}<input name="customerId" type="hidden" style="width:200px;" class="" value="${(factorBusinessDTO.customerId)!''}"></td>
                        </#if>
                        <th><span class="redFalg">*</span>业务合同号</th>
                        <td><input name="businessContractNo" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.businessContractNo)!''}"></td>
                        <input id="creditVersion" name="creditVersion" type="hidden" value="">
                        <input id="creditId" name="creditId" type="hidden" value="">
                        <#if factorBusinessDTO ??>
                    		<input name="id" type="hidden" value="${(factorBusinessDTO.id)!''}">
                    	</#if>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>币种</th>
                        <td>
                            <select name="currency" class="" style="width:200px;"  data-role="dropdownlist">
                            	<#list currencys as currency>
	                                <option 
	                                <#if factorBusinessDTO ??>
	                                	<#if factorBusinessDTO.currency.name()==currency.name() >
	                                		selected="selected"
	                                	</#if>
                                	</#if>
	                                value="${currency.name()!}">${currency.desc()!}</option>
                            	</#list>
                            </select>
                        </td>
                        <th><span class="redFalg">*</span>项目名称</th>
                        <td><input name="projectName" type="text" style="width:200px;" value="${(factorBusinessDTO.projectName)!''}"></td>
                        <th><span class="redFalg">*</span>业务产品</th>
                        <td>
                            <select name="businessProduct" class="" style="width:200px;"  data-role="dropdownlist">
                            	<#list businessProducts as businessProduct>
	                                <option 
	                                <#if factorBusinessDTO ??>
	                                	<#if factorBusinessDTO.businessProduct==businessProduct >
	                                		selected="selected"
	                                	</#if>
                                	</#if>
	                                value="${businessProduct.name()}">${businessProduct.desc()}</option>
                            	</#list>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>业务模式</th>
                        <td>
                            <select name="businessMode" class="" style="width:200px;"  data-role="dropdownlist">
                            	<#list businessModes as businessMode>
	                                <option 
	                                <#if factorBusinessDTO ??>
	                                	<#if factorBusinessDTO.businessMode==businessMode >
	                                		selected="selected"
	                                	</#if>
                                	</#if>
	                                value="${businessMode.name()}">${businessMode.desc()}</option>
                            	</#list>
                            </select>
                        </td>
                        <th><span class="redFalg">*</span>合同起始日</th>
                        <td><input name="contractStartDate" type="text" class="datepicker startDate hasIcon" style="width:200px;" value="${(factorBusinessDTO.contractStartDate)!''}"></td>
                        <th><span class="redFalg">*</span>合同到期日</th>
                        <td><input name="contractEndDate" type="text" class="datepicker endDate hasIcon" style="width:200px;" value="${(factorBusinessDTO.contractEndDate)!''}"></td>
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
                        <td><input name="interestRate" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.interestRate)!''}"></td>
                        <th>计息方式</th>
                        <td>
                            <select name="interestType" class="" style="width:200px;"  data-role="dropdownlist">
                                <option value="">空</option>
                                <#list interestTypes as interestType>
	                                <option 
                                	<#if factorBusinessDTO ??>
	                                	<#if (factorBusinessDTO.interestType)?? >
		                                	<#if factorBusinessDTO.interestType==interestType >
		                                		selected="selected"
		                                	</#if>
	                                	</#if>
                                	</#if>
	                                value="${interestType.name()}">${interestType.desc()}</option>
                            	</#list>
                            </select>
                        </td>
                        <th>手续费率</th>
                        <td><input name="feeRate" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.feeRate)!''}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>罚息利率</th>
                        <td><input name="penaltyRate" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.penaltyRate)!''}"></td>
                        <th>宽限期(天)</th>
                        <td style="position:relative;">
                            <input name="extensionDays" type="text" style="width:200px;padding-right: 40px;" class="" value="${(factorBusinessDTO.extensionDays)!''}">
                            <div class="client-unit-two"></div>
                        </td>
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
                        <td style="position:relative;">
                            <input name="paymentPeriod" type="text" style="width:200px;padding-right: 40px;" class="" value="${(factorBusinessDTO.paymentPeriod)!''}">
                            <div class="client-unit-two"></div>
                        </td>
                        <th><span class="redFalg">*</span>单证类型</th>
                        <td>
                            <select name="receiptType" class="" style="width:200px;"  data-role="dropdownlist">
                                <#list receiptTypes as receiptType>
	                                <option 
	                                <#if factorBusinessDTO ??>
	                                	<#if factorBusinessDTO.receiptType==receiptType >
	                                		selected="selected"
	                                	</#if>
                                	</#if>
	                                value="${receiptType.name()}">${receiptType.desc()}</option>
                            	</#list>
                            </select>
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>还款来源</th>
                        <td><input name="repaymentSource" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.repaymentSource)!''}" maxlength="50"></td>
                        <th>还款安排</th>
                        <td><input name="repaymentArrangement" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.repaymentArrangement)!''}" maxlength="50"></td>
                        <th>资金用途</th>
                        <td><input name="fundUsage" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.fundUsage)!''}" maxlength="50"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监控方案</th>
                        <td><input name="monitoringSolution" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.monitoringSolution)!''}" maxlength="50"></td>
                        <th>增信措施</th>
                        <td><input name="increaseTrustMeasures" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.increaseTrustMeasures)!''}" maxlength="50"></td>
                        <th>约束性条款</th>
                        <td><input name="bindingProvisions" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.bindingProvisions)!''}" maxlength="50"></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                <div style="margin-top:15px;">
                    <a class="status-normal status-chk add-appoint-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk ch-appoint-btn" href="javascript:void(0);">修改</a>
                    <a class="status-normal status-chk del-appoint-btn" href="javascript:void(0);">删除</a>
                    <a class="open-appoint-btn" href="javascript:void(0);" style="display:none;"></a>
                    <a class="open-sec-btn" href="javascript:void(0);" style="display:none;"></a>
                </div>
                <div class="min-box-width">
                <table class="m-table fact-table" style="border-bottom:none;">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <th>选择</th>
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
                <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="appoint-tb">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <#if factorBusinessDTO??>
    	                <#if factorBusinessDTO.counterpartyList??>
							<#list factorBusinessDTO.counterpartyList as counterparty>
			                    <tr class="client-add-tr">
			                        <th><input type="checkbox" class="input-new" name="appoint-tb"></th>
			                        <th>${counterparty_index+1}</th>
			                        <td>${counterparty.counterparty}</td>
			                        <td>${counterparty.financingRatio}</td>
			                        <td>${counterparty.factoringType.desc()}</td>
		                        	<#if counterparty.querySubLimitFlag==true>
				                        <td>是</td>
				                        <td>￥${counterparty.subLimit!''}</td>
		                        	<#else>
				                        <td>否</td>
				                        <td>-</td>
		                        	</#if>
		                        	<input class="needAlterIndex" type="hidden" name="counterpartyList[${counterparty_index}].counterparty" value="${counterparty.counterparty!''}" data-left="counterpartyList[" data-right="].counterparty">
              						<input class="needAlterIndex counterpartyId" type="hidden" name="counterpartyList[${counterparty_index}].counterpartyId" value="${counterparty.counterpartyId!''}" data-left="counterpartyList[" data-right="].counterpartyId">
              						<input class="needAlterIndex" type="hidden" name="counterpartyList[${counterparty_index}].counterpartyType" value="${counterparty.counterpartyType!''}" data-left="counterpartyList[" data-right="].counterpartyType">
              						<input type="hidden" name="custTypeDesc" value="${counterparty.counterpartyType.desc()!''}" >
            					 	<input class="needAlterIndex financingRatio" type="hidden" name="counterpartyList[${counterparty_index}].financingRatio" value="${counterparty.financingRatio!''}" data-left="counterpartyList[" data-right="].financingRatio">
            					 	<input class="needAlterIndex" type="hidden" name="counterpartyList[${counterparty_index}].querySubLimitFlag" value="${counterparty.querySubLimitFlag?string("true","false")}" data-left="counterpartyList[" data-right="].querySubLimitFlag">
              						<input class="needAlterIndex" type="hidden" name="counterpartyList[${counterparty_index}].subLimit" value="${counterparty.subLimit!''}" data-left="counterpartyList[" data-right="].subLimit">
              						<input class="needAlterIndex" type="hidden" name="counterpartyList[${counterparty_index}].factoringType" value="${counterparty.factoringType.name()!''}" data-left="counterpartyList[" data-right="].factoringType">
              						<input class="needAlterIndex sequence" type="hidden" name="counterpartyList[${counterparty_index}].sequence" value="${counterparty.sequence!''}" data-left="counterpartyList[" data-right="].sequence">
			                    </tr>
							</#list>    	                	
	                    </#if>
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
                <table class="m-table fact-table" style="border-bottom:none;">
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
                    <table class="m-table fact-table-two" style="border:none;margin-top:0;">
                        <colgroup>
                            <col width="12%">
                            <col width="12%">
                            <col width="14%">
                            <col width="14%">
                            <col width="14%">
                            <col width="14%">
                            <col width="">
                        </colgroup>
                    <tbody>
                    <tr class="client-add-tr">
                        <td><span name="creditModeDesc"></span></td>
                        <td><span name="currencyDesc"></span></td>
                        <td><span name="total"></span></td>
                        <td><span name="used"></span></td>
                        <td><span name="available"></span></td>
                        <td><span name="due"></span></td>
                    </tr>
                    </tbody>
                </table>
                </div>
                </div>
            </div>
            <!--费用信息-->
            <div class="pro-title" id="nav-five"><span class="pro-title-left">费用信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="">
                    <a class="status-normal status-chk add-cost-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk ch-cost-btn" href="javascript:void(0);">修改</a>
                    <a class="status-normal status-chk del-cost-btn" href="javascript:void(0);">删除</a>
                    <a class="open-cost-btn" href="javascript:void(0);" style="display:none;"></a>
                    <a class="open-message-btn" href="javascript:void(0);" style="display:none;"></a>
                </div>
                <div class="min-box-width">
                <table class="m-table fact-table" style="border-bottom:none;">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="26%">
                        <col width="26%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <th>选择</th>
                        <td>序号</td>
                        <td>费用科目</td>
                        <td>金额</td>
                        <td></td>
                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="cost-tb">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="26%">
                            <col width="26%">
                            <col width="">
                        </colgroup>
                    <tbody>
                    <#if factorBusinessDTO??>
    	                <#if factorBusinessDTO.expenseList??>
							<#list factorBusinessDTO.expenseList as expense>
			                    <tr class="client-add-tr">
			                        <th><input type="checkbox" class="input-new" name="cost-tb"></th>
			                        <th>${expense_index+1}</th>
			                        <td>${(expense.expenseSubject)!}</td>
			                        <td>￥${(expense.amountStr)!}</td>
			                        <td></td>
			                        <input class="needAlterIndex" type="hidden" name="expenseList[${expense_index}].expenseSubject" value="${expense.expenseSubject!''}" data-left="expenseList[" data-right="].expenseSubject">
              						<input class="needAlterIndex" type="hidden" name="expenseList[${expense_index}].amountStr" value="${expense.amountStr!''}" data-left="expenseList[" data-right="].amountStr">
              						<input class="needAlterIndex sequence" type="hidden" name="expenseList[${expense_index}].sequence" value="${expense.sequence!''}" data-left="expenseList[" data-right="].sequence">
			                    </tr>
							</#list>    	                	
	                    </#if>
                    </#if>
                    </tbody>
                </table>
                </div>
                </div>
            </div>
            <!--担保信息-->
            <div class="pro-title" id="nav-six"><span class="pro-title-left">担保信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="">
                    <a class="status-normal status-chk add-assure-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk ch-assure-btn" href="javascript:void(0);">修改</a>
                    <a class="status-normal status-chk del-assure-btn" href="javascript:void(0);">删除</a>
                    <a class="open-assure-btn" href="javascript:void(0);" style="display:none;"></a>
                </div>
                <div class="min-box-width">
                <table class="m-table fact-table" style="border-bottom:none;">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="26%">
                        <col width="26%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <th>选择</th>
                        <td>序号</td>
                        <td>保证合同号</td>
                        <td>担保方信息</td>
                        <td>担保类型</td>
                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table-two" style="border:0;margin-top:0;" id="assure-tb">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="26%">
                            <col width="26%">
                            <col width="">
                        </colgroup>
                    <tbody>
                    <#if factorBusinessDTO??>
    	                <#if factorBusinessDTO.guaranteeInfoList??>
							<#list factorBusinessDTO.guaranteeInfoList as guaranteeInfo>
			                    <tr class="client-add-tr">
			                        <th><input type="checkbox" class="input-new" name="assure-tb"></th>
			                        <th>${guaranteeInfo_index+1}</th>
			                        <td>${(guaranteeInfo.guaranteedContractNo)!}</td>
			                        <td>${(guaranteeInfo.guarantorInfo)!}</td>
			                        <td>${(guaranteeInfo.guaranteeType)!}</td>
			                        <input class="needAlterIndex" type="hidden" name="guaranteeInfoList[${guaranteeInfo_index}].guaranteedContractNo" value="${guaranteeInfo.guaranteedContractNo!''}" data-left="guaranteeInfoList[" data-right="].guaranteedContractNo">
              						<input class="needAlterIndex" type="hidden" name="guaranteeInfoList[${guaranteeInfo_index}].guarantorInfo" value="${guaranteeInfo.guarantorInfo!''}" data-left="guaranteeInfoList[" data-right="].guarantorInfo">
              						<input class="needAlterIndex" type="hidden" name="guaranteeInfoList[${guaranteeInfo_index}].guaranteeType" value="${guaranteeInfo.guaranteeType!''}" data-left="guaranteeInfoList[" data-right="].guaranteeType">
              						<input class="needAlterIndex sequence" type="hidden" name="guaranteeInfoList[${guaranteeInfo_index}].sequence" value="${guaranteeInfo.sequence!''}" data-left="guaranteeInfoList[" data-right="].sequence">
			                    </tr>
							</#list>    	                	
	                    </#if>
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
                        <th><span class="redFalg">*</span>保理专户户名</th>
                        <td><input name="factoringAccountName" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.factoringAccountName)!''}"></td>
                        <th><span class="redFalg">*</span>开户行</th>
                        <td><input name="factoringAccountBankAddress" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.factoringAccountBankAddress)!''}"></td>
                        <th><span class="redFalg">*</span>账号</th>
                        <td><input name="factoringAccount" type="text" style="width:200px;" class="bankCard" value="${(factorBusinessDTO.factoringAccount)!''}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>结算账户户名</th>
                        <td><input name="settlementAccountName" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.settlementAccountName)!''}"></td>
                        <th><span class="redFalg">*</span>开户行</th>
                        <td><input name="settlementAccountBankAddress" type="text" style="width:200px;" class="" value="${(factorBusinessDTO.settlementAccountBankAddress)!''}"></td>
                        <th><span class="redFalg">*</span>账号</th>
                        <td><input name="settlementAccount" type="text" style="width:200px;" class="bankCard" value="${(factorBusinessDTO.settlementAccount)!''}"></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div class="client-foot">
                <a class="form-search-link sc-btn" href="javascript:void(0);">提交</a>
                <a class="form-search-link" href="javascript: history.back(-1);">返回</a>
            </div>
        </div>
    </form>
</div>
<!--新增约定弹窗-->
<script id="template-resetPwd-new" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:100px;text-align: right;">客户名称</td>
                        <td style="width:200px;"><input type="text" class="customerNameWindow" style="width:160px;"></td>
                        <td><a class="form-search-btn" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            </form>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="client-out-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link go-appoint-pop" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--新增/修改约定信息弹窗-->
<script id="template-resetPwd-arr" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="appoint-form">
        <div class="dlg-del-row">
            <table class="client-tb">
                <colgroup>
                    <col width="40%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易对手</th>
                    <td class="ap-name"><span name="apParty"></span></td>
                    <input type="hidden" name="custId" style="width:160px;">
                    <input type="hidden" name="custType" style="width:160px;">
                </tr>
                <tr>
                    <th>客户类型</th>
                    <td><span name="custType"></span></td>
                </tr>
                <tr>
                    <th>融资比例</th>
                    <td><input type="text" name="exa-ratio" style="width:200px;" class="ap-ratio">%</td>
                </tr>
                <tr>
                    <th>保理类型</th>
                    <td>
                        <select class="ap-type" style="width:200px;" data-role="dropdownlist" name="exa-ap-type">
	                        <#list factoringTypes as factoringType>
	                            <option value="${factoringType.name()}">${factoringType.desc()}</option>
	                    	</#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <label>
                            <input type="checkbox" class="input-new checked-btn" name="exa-yes">&ensp;是否查询子额度
                        </label>
                    </td>
                </tr>
                <tr>
                    <th>子额度</th>
                    <td><input type="text" class="for-check-one moneyNum" name="exa-son" style="width:200px;" disabled="disabled"></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="appoint-btn" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
        </form>
        <input type="hidden" value="" class="appoint-ch-id">
    </div>
</script>
<!--新增/修改费用信息弹窗-->
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
                    <th>费用科目</th>
                    <td><input type="text" style="width:200px;" class="co-class" name="exa-class"  validationMessage="不能为空" required></td>
                </tr>
                <tr>
                    <th>金额</th>
                    <td><input type="text" style="width:200px;" class="co-money moneyNum" name="exa-money"  validationMessage="不能为空" required><div class="client-unit-three">元</div>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="cost-btn" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
        </form>
        <input type="hidden" value="" class="cost-ch-id">
    </div>
</script>
<!--新增/修改担保信息弹窗-->
<script id="template-resetPwd-ass" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="assure-form">
        <div class="dlg-del-row">
            <table class="client-tb">
                <colgroup>
                    <col width="40%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>保证合同号</th>
                    <td><input type="text" style="width:200px;" class="as-num" name="exa-num" validationMessage="不能为空" required></td>
                </tr>
                <tr>
                    <th>担保方信息</th>
                    <td><input type="text" style="width:200px;" class="as-message" name="exa-message" validationMessage="不能为空" required></td>
                </tr>
                <tr>
                    <th>担保类型</th>
                    <td><input type="text" style="width:200px;" class="as-type" name="exa-type" validationMessage="不能为空" required></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="assure-btn" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
        </form>
        <input type="hidden" value="" class="assure-ch-id">
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
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left-limit">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right-limit">
                        <span class="notice-content">确认要执行操作吗？</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="delete-confirm" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
</@hb.htmlBase>
