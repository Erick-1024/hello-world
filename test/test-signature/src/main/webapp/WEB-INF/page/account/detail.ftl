<#import "/common/htmlBase.ftl" as hb>
<#import "trade/detailTradeRecordAndLoan.ftl" as tradeRecordAndLoan>
<#if pageType=="pageDetail">
	<#assign pageDesc = "账户详情">
<#else>
	<#assign pageDesc = "解除监管关系">
</#if>
<@hb.htmlBase title=pageDesc jsFiles=["page/account/supervision/remove.js","page/account/balance.js","common/cana.util.js","common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "账户列表" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">账户信息</div>
            <div class="accountList-content">
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="120">
                        <col width="250">
                        <col width="100">
                        <col width="250">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="acu-title">银行账户名称</td>
                        <td>
                        	<input id="accountId" type="hidden" value="${accountDTO.accountId!''}"/>
                        	<input id="accountSupervisionId" type="hidden" value="${accountDTO.accountSupervisionId!''}"/>
                            <span>${accountDTO.accountName!''}</span>
                        </td>
                        <td class="acu-title">银行账号</td>
                        <td>
                            <span id="accountNo">${accountDTO.accountNo!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">账户余额</td>
                        <td class="superive-row">
                            <span class="accountBalance"></span>
                            <a class="superive-link accountBalanceButton" href="javascript:void(0);">刷新</a>
                        </td>
                        <td class="acu-title">币别</td>
                        <td>
                            <span>人民币</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="accountList-content">
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="120">
                        <col width="250">
                        <col width="100">
                        <col width="250">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="acu-title">账户类型</td>
                        <td>
                            <span>${accountDTO.accountTypeDesc!''}</span>
                        </td>
                        <td class="acu-title">监管状态</td>
                        <td>
                            <span>${accountDTO.supervisionStatusDesc!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">账户状态</td>
                        <td>
                            <span>${accountDTO.accountStatusDesc!''}</span>
                        </td>
                        <td class="acu-title">归集状态</td>
                        <td>
                            <span>${accountDTO.accumulationStatusDesc!''}</span>
                        </td>
                    </tr>
                    <#if accountDTO.accountType == "GENERAL" && accountDTO.supervisionStatus == "HAVE_SUPERVISION">
	                    <tr>
	                        <td class="acu-title">默认还款账户</td>
	                        <td>
	                            <span>${(accountDTO.defaultRepayment)?string("是","否")}</span>
	                        </td>
	                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <#if accountDTO.accountType != "GENERAL" || accountDTO.supervisionStatus != "NO_SUPERVISION">
	            <div class="accountList-content">
	                <table class="bankAcuDetail">
	                    <colgroup>
	                        <col width="120">
	                        <col width="250">
	                        <col width="100">
	                        <col width="250">
	                    </colgroup>
	                    <tbody>
		                    <#if accountDTO.accountType == "SPECIAL">
			                    <tr>
			                        <td class="acu-title">买方企业</td>
			                        <td>
			                            <span>${accountDTO.buyerName!''}</span>
			                        </td>
			                        <#if accountDTO.accumulationStatus == "BE_ACCUMULATED">
				                        <td class="acu-title">归集账户名称</td>
				                        <td>
				                            <span>${accountDTO.accumulationAccountName!''}</span>
				                        </td>
				                    </tr>
				                    <tr>
				                        <td class="acu-title">归集账号</td>
				                        <td>
				                            <span id="accumulationAccountNo">${accountDTO.accumulationAccountNo!''}</span>
				                        </td>
				                	</#if>
									<td></td>
									<td></td>
			                    </tr>
			                </#if>
							<#if accountDTO.accountType == "GENERAL" && accountDTO.supervisionStatus == "HAVE_SUPERVISION">
								<table class="bankAcuDetail">
				                    <colgroup>
				                        <col width="120">
				                        <col width="250">
				                        <col width="100">
				                        <col width="250">
				                    </colgroup>
				                    <tbody>
				                    <tr>
				                        <#if currentUserType != "FACTOR">
					                        <td class="acu-title">资金方</td>
					                        <td>
					                            <span>${accountDTO.factorName!''}</span>
					                        </td>
					                    </#if>
				                        <#if currentUserType != "FINACE">
					                        <td class="acu-title">融资客户</td>
					                        <td>
					                            <span>${accountDTO.finaceName!''}</span>
					                        </td>
					                	</#if>
				                        <#if currentUserType != "CANA">
					                        <td></td>
					                        <td>
					                        </td>
					                	</#if>
				                    </tr>
				                    </tbody>
				                </table>
			                    <#if accountDTO.accumulationStatus == "HAVE_ACCUMULATION">
				                    <div class="superive-privateAcu">
					                    <div class="privateAcu-head">专用账户</div>
					                    <table>
					                        <colgroup>
					                            <col width="50">
					                            <col width="150">
					                            <col width="150">
					                            <col width="100">
					                            <col width="200">
					                            <col width="100">
					                        </colgroup>
					                        <thead>
					                        <tr>
					                            <td>序号</td>
					                            <td>账户名称</td>
					                            <td>银行账号</td>
					                            <td>余额</td>
					                            <td>买方名称</td>
					                            <td>账户状态</td>
					                        </tr>
					                        </thead>
					                        <tbody>
					                        	<#if accountDTO.specialAccounts??>
													<#list accountDTO.specialAccounts as specialAccount>
														<tr>
															<td align="center">${specialAccount_index+1}</td>
															<td>${(specialAccount.accountName)!''}</td>
															<td id="specialAccountNo">${(specialAccount.accountNo)!''}</td>
															<td class="specialBalance"></td>
															<td>${(specialAccount.buyerName)!''}</td>
															<td>${(specialAccount.accountStatusDesc)!''}</td>
														</tr>
													</#list>
												</#if>
					                        </tbody>
					                    </table>
					                </div>
					                <table class="bankAcuDetail">
					                    <colgroup>
					                        <col width="120">
					                        <col width="250">
					                        <col width="100">
					                        <col width="250">
					                    </colgroup>
					                    <tbody>
					                    <tr>
					                        <td class="acu-title">专用账户总余额</td>
					                        <td class="superive-row">
					                            <span class="specialBalances"></span>
					                            <a class="superive-link specialAccountBalancesButton" href="javascript:void(0);">刷新</a>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="acu-title">账户总余额</td>
					                        <td>
					                            <span class="accountTotalBalance"></span>
					                        </td>
					                        <td class="acu-title">融资余额</td>
					                        <td>
					                            <span class="financingBalance"></span>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="acu-title">资金覆盖率</td>
					                        <td>
					                            <span class="fundCoverage"></span>
					                        </td>
					                    </tr>
					                    </tbody>
					                </table>
			                    </#if>
								<#if accountDTO.accumulationStatus == "NO_ACCUMULATION">
									<table class="bankAcuDetail">
					                    <colgroup>
					                        <col width="120">
					                        <col width="250">
					                        <col width="100">
					                        <col width="250">
					                    </colgroup>
					                    <tbody>
						                    <tr>
						                        <td class="acu-title">融资余额</td>
						                        <td>
						                            <span class="financingBalance"></span>
						                        </td>
						                        <td class="acu-title">资金覆盖率</td>
						                        <td>
						                            <span class="fundCoverage"></span>
						                        </td>
						                    </tr>
					                    </tbody>
					                </table>
			                    </#if>
							</#if>
	                    </tbody>
	                </table>
	            </div>
	        </#if>
			<#if pageType == "pageRemoveSupervision" && accountDTO.accountType == "GENERAL" && accountDTO.supervisionStatus == "HAVE_SUPERVISION">
				<#include "supervision/detailRemoveSupervision.ftl" />
			</#if>
			<#assign showLoan = (accountDTO.accountType == "GENERAL" && accountDTO.supervisionStatus == "HAVE_SUPERVISION") />
			<@tradeRecordAndLoan.tradeRecordAndLoan showLoan=showLoan />
        </div>
    </section>
</div>
<#include "tipBoxTemplate.ftl" />
<#include "confirmBoxTemplate.ftl" />
</@hb.htmlBase>
<script>
	$(function(){
		
		var formatAccountNo = $("#accountNo").text().formatBankAccountNo();
		$("#accountNo").text(formatAccountNo);
		
		var formatAccumulationAccountNo = $("#accumulationAccountNo").text().formatBankAccountNo();
		$("#accumulationAccountNo").text(formatAccumulationAccountNo);
		
		$("td#specialAccountNo").each(function(i,e){
		var formatSpecialAccountNo = $(e).text().formatBankAccountNo();
		$(e).text(formatSpecialAccountNo);
		})
	});
</script>