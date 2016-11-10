<#import "/common/htmlBase.ftl" as hb>
<#import "../trade/detailTradeRecordAndLoan.ftl" as tradeRecordAndLoan>

<#if applyByCurrentCustomer>
	<#assign curTopMenu = "我的申请"/>
	<#assign curSubMenu = "我的申请"/>
	<#assign titleContent = "申请详情"/>
<#else>
	<#assign curTopMenu = "账户管理"/>
	<#assign curSubMenu = "审核列表"/>
	<#if showAuditButtons>
		<#assign titleContent = "申请审核"/>
	<#else>
		<#assign titleContent = "申请详情"/>
	</#if>
	
</#if>

<@hb.htmlBase title=titleContent jsFiles=["page/account/balance.js","common/cana.util.js","common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = curTopMenu curSubMenu = curSubMenu>

<div class="main-container">
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">
            <#if applyDTO.applyType == "CREATE_SUPERVISION">
            	<#if showAuditButtons>新建监管关系申请审核<#else>新建监管关系申请详情</#if>
            <#elseif applyDTO.applyType == "REMOVE_SUPERVISION">
            	<#if showAuditButtons>解除监管关系申请审核<#else>解除监管关系申请详情</#if>
            </#if>
            </div>
            <div class="accountList-content">
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="180">
                        <col width="200">
                        <col width="120">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="acu-title">银行账户名称</td>
                        <td>
                            <span>${(applyDTO.accountName)!}</span>
                        	<input id="accountSupervisionId" type="hidden" value="${applyDTO.accountSupervisionId!''}"/>
                        </td>
                        <td class="acu-title">银行账号</td>
                        <td>
                            <span id="accountNo">${(applyDTO.accountNo)!}</span>
                            <input id="accountId" type="hidden" value="${applyDTO.accountId!''}"/>
                        </td>
                    </tr>
                    <#if showAccountBalance>
                    <tr>
                        <td class="acu-title">账户余额</td>
                        <td class="bankAcuDetail-row">
                            <span class="accountBalance"></span>
                            <a class="bankAcuDetail-link accountBalanceButton" href="javascript:void(0);">刷新</a>
                        </td>
                        <td class="acu-title">币别</td>
                        <td>
                            <span>人民币</span>
                        </td>
                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <#if !isRemoveApply || isPendingAudit>
            <div class="accountList-content">
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="180">
                        <col width="200">
                        <col width="120">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <#if isPendingAudit>
                    <tr>
                        <td class="acu-title">账户类型</td>
                        <td>
                            <span>${(applyDTO.accountTypeDesc)!}</span>
                        </td>
                        <td class="acu-title">监管状态</td>
                        <td>
                            <span>${(applyDTO.supervisionStatusDesc)!}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">账户状态</td>
                        <td>
                            <span>${(applyDTO.accountStatusDesc)!}</span>
                        </td>
                        <td class="acu-title">归集状态</td>
                        <td>
                            <span>${(applyDTO.accumulationStatusDesc)!}</span>
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <td class="acu-title">默认还款账户</td>
                        <td>
                            <span>${(applyDTO.defaultRepayment?string('是','否'))!}</span>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </#if>
            <div class="accountList-content">
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="180">
                        <col width="200">
                        <col width="120">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                    	<#if userType == "FACTOR">
                    	<td class="acu-title">融资客户</td>
                        <td>
                            <span>${(applyDTO.finaceName)!}</span>
                        </td>
                        <#else>
                        <td class="acu-title">资金方</td>
                        <td>
                            <span>${(applyDTO.factorName)!}</span>
                        </td>
                        </#if>
                    	<#if userType == "CANA">
                    	<td class="acu-title">融资客户</td>
                        <td>
                            <span>${(applyDTO.finaceName)!}</span>
                        </td>
                    	<#else>
                    	<td></td>
                    	<td></td>
                    	</#if>
                    </tr>
                    </tbody>
                </table>
                <#if (applyDTO.specialAccounts)??>
                <div class="superive-privateAcu">
                    <div class="privateAcu-head">专用账户</div>
                    <table>
                        <colgroup>
                            <col width="50">
                            <col width="150">
                            <col width="150">
                            <#if isPendingAudit>
                            <col width="100">
                            </#if>
                            <col width="200">
                            <#if isPendingAudit>
                            <col width="100">
                            </#if>
                        </colgroup>
                        <thead>
                        <tr>
                            <td>序号</td>
                            <td>账户名称</td>
                            <td>银行账号</td>
                            <#if showSpecialAccountBalance>
                            <td>余额</td>
                            </#if>
                            <td>买方名称</td>
                            <#if isPendingAudit>
                            <td>账户状态</td>
                            </#if>
                        </tr>
                        </thead>
                        <tbody>
                        <#list applyDTO.specialAccounts as specialAccount>
                        <tr>
                            <td>${specialAccount_index + 1}</td>
                            <td>${specialAccount.accountName!}</td>
                            <td>${specialAccount.accountNo!}</td>
                            <#if showSpecialAccountBalance>
                            <td class="specialBalance"></td>
                            </#if>
                            <td>${specialAccount.buyerName!}</td>
                            <#if isPendingAudit>
                            <td>${specialAccount.accountStatusDesc!}</td>
                            </#if>
                        </tr>
                        </#list>
                        </tbody>

                    </table>
                </div>
                </#if>
                <#if showSpecialAccountBalance || showTotalBalance || showFinaceBalance>
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="180">
                        <col width="200">
                        <col width="120">
                        <col width="200">
                    </colgroup>
                    <tbody>
                	<#if showSpecialAccountBalance>
	                    <tr>
	                        <td class="acu-title">专用账户总余额</td>
	                        <td class="bankAcuDetail-row">
	                            <span class="specialBalances"></span>
	                            <a class="bankAcuDetail-link specialAccountBalancesButton" href="javascript:void(0);">刷新</a>
	                        </td>
	                        <td></td><td></td>
	                    </tr>
                    </#if>
                    <#if showTotalBalance>
	                    <tr>
	                        <td class="acu-title">账户总余额</td>
	                        <td>
	                            <span class="accountTotalBalance"></span>
	                        </td>
	                        <#if showFinaceBalance>
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
	                        </#if>
	                    </tr>
	                <#else>
	                	<#if showFinaceBalance>
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
	                    </#if>
                    </#if>
                    </tbody>
                </table>
                
            </div>
            </#if>
			<#if (applyDTO.removeSupervisionAccounts)??>
            <div class="accountList-content">
                <div class="superive-privateAcu">
                    <div class="privateAcu-head">解除监管关系</div>
                    <table>
                        <colgroup>
                            <col width="50">
                            <col width="100">
                            <col width="120">
                            <col width="150">
                            <col width="200">
                        </colgroup>
                        <thead>
                        <tr>
                            <td>序号</td>
                            <td>账户类型</td>
                            <td>账户名称</td>
                            <td>银行账号</td>
                            <td>买方名称</td>
                        </tr>
                        </thead>
                        <tbody>
                        <#list applyDTO.removeSupervisionAccounts as specialAccount>
                        <tr>
                            <td>${specialAccount_index + 1}</td>
                            <td>${specialAccount.accountTypeDesc!}</td>
                            <td>${specialAccount.accountName!}</td>
                            <td>${specialAccount.accountNo!}</td>
                            <td>${specialAccount.buyerName!}</td>
                        </tr>
                        </#list>
                        </tbody>

                    </table>
                </div>
            </div>
            </#if>
            <div class="accountList-content">
                <table class="bankAcuDetail">
                    <colgroup>
                        <col width="180">
                        <col width="200">
                        <col width="120">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <#if isPendingAudit && showAuditButtons && applyDTO.allowAudit>
                    <tr>
                        <td class="acu-title">审核结果</td>
                        <td>
                            <div class="radioContent" style="padding:5px 0;">
                                <label class="radio active">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">同意</span>
                                </label>
                                <label class="radio">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不同意</span>
                                </label>
                            </div>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="acu-title" style="vertical-align: text-top">备注</td>
                        <td colspan="3">
                            <textarea name="auditMessage" maxlength="100"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3" style="padding-top:30px;">
                            <a class="default-link confirm-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
                        </td>
                    </tr>
                    <script>
	                    $(function(){
	                    	$('.confirm-link').click(function() {
	                    		var isAgree = "同意" == $('.radioContent .active .labelFonts').text();
	                    		var auditMessage = $('textarea[name=auditMessage]').val();
	                    		$.ajax({
	                    			url:basepath + "/account/supervision/audit",
	                    			type:"post",
	                    			data:{
	                    				applyId : "${applyDTO.id!}",
	                    				isAgree : isAgree,
	                    				message : auditMessage
	                    			},
	                    			success : function(data) {
	                    				if (data == "success") {
		                    				location.reload();
	                    				} else {
	                    					alert("审核失败：" + data);
	                    				}
	                    			}
	                    		});
	                    	});
	                    	$('.back-link').click(function() {
	                    		window.location.href = basepath + "/account/audit/list";
	                    	});
	                    });
                    </script>
                    <#else>
                    <tr>
                        <td class="acu-title">审核结果</td>
                        <td style="font-size:15px">
                            <span>${(applyDTO.applyStatusDesc)!}</span>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="acu-title">备注</td>
                        <td colspan="3" style="font-size:15px">${(applyDTO.auditMessage)!}</td>
                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <#if isPendingAudit>
				<@tradeRecordAndLoan.tradeRecordAndLoan showLoan=isRemoveApply />
			</#if>
        </div>
    </section>
</div>
</@hb.htmlBase>