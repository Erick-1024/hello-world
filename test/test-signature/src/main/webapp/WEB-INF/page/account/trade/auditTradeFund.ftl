<#import "/common/htmlBase.ftl" as hb>

<#if applyByCurrentCustomer>
	<#assign curTopMenu = "我的申请"/>
	<#assign curSubMenu = "我的申请"/>
<#else>
	<#assign curTopMenu = "账户管理"/>
	<#assign curSubMenu = "审核列表"/>
</#if>

<@hb.htmlBase title="${(title)!}" jsFiles=["common/cana.util.js","page/account/audit/trade.js","common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
curTopMenu = curTopMenu curSubMenu = curSubMenu removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <form action="audit" id="audit-trade-form" method="post" >
    <input type="hidden" name="applyType" value="${(applyDTO.applyType)!}"/>
    <input type="hidden" name="accountTradeApplyId" value="${(applyDTO.id)!}"/>
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">${(title)!}</div>
            <div class="accountList-content">
                <table class="applyAut-table">
                    <colgroup>
                        <col width="40%">
                        <col width="40%">
                        <col width="20%">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="applyAut-table-til"><#if applyDTO.applyType=="TRANSFER_FUND">转出户名<#else>提现户名</#if></td>
                        <td>${(applyDTO.accountName)!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><#if applyDTO.applyType=="TRANSFER_FUND">转出账号<#else>提现账号</#if></td>
                        <td colspan="2">
							${(applyDTO.accountNo)!}
							<input id="sendAccountNo" disabled="disabled" type="hidden" value="${applyDTO.accountNo!}"/>
                        </td>
                    </tr>
                    <#if isPendingAudit>
	                    <tr>
	                        <td class="accountList-table-til">账户类型</td>
	                        <td colspan="2">${(applyDTO.accountTypeDesc)!}</td>
	                    </tr>
	                    <tr>
	                        <td class="accountList-table-til">监管状态</td>
	                        <td colspan="2">${(applyDTO.supervisionStatusDesc)!}</td>
	                        
	                    </tr>
	                    <tr>
	                        <td class="accountList-table-til">归集状态</td>
	                        <td colspan="2">${(applyDTO.accumulationStatusDesc)!}</td>
	                    </tr>
	                    <tr>
	                        <td class="accountList-table-til">账户余额</td>
	                        <td>
								<span class="accountBalance"></span>元
								<a class="accountList-link accountBalanceButton" href="javascript:void(0);">刷新</a>
							</td>
							<td style="padding-left: 5px;">
							</td>
	                    </tr>
	                    <#if applyDTO.accountType == 'GENERAL' && applyDTO.supervisionStatus == 'HAVE_SUPERVISION'>
		                    <tr>
		                        <td class="accountList-table-til">融资余额</td>
		                        <td>
									<span class="finaceBalance">${applyDTO.finaceBalance!}</span>元
								</td>
								<td style="padding-left: 5px;">
								<input id="accountSupervisionId" disabled="disabled" type="hidden" value="${applyDTO.accountSupervisionId!}"/>
								</td>
		                    </tr>
		                    <tr>
		                        <td class="accountList-table-til">资金覆盖率</td>
		                        <td><span class="fundCoverage"></span>%</td>
		                    </tr>
	                    </#if>
                    </#if>
                    <tr>
                        <td class="accountList-table-til"><#if applyDTO.applyType=="WITHDRAW_FUND">提现金额<#else>转账金额</#if></td>
                        <td>${(applyDTO.amount)!}元</td>
                    </tr>
                    <#if applyDTO.applyType=="TRANSFER_FUND">
                    <tr>
                        <td class="accountList-table-til">备注</td>
                        <td>${(applyDTO.remark)!}</td>
                    </tr>
                    </#if>
                    <#if applyDTO.applyType=="WITHDRAW_FUND">
	                    <tr>
	                        <td class="accountList-table-til">提现手续费</td>
	                        <td>${(applyDTO.charge)!}元</td>
	                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <div class="accountList-content">
                <table class="applyAut-table">
                    <colgroup>
                        <col width="40%">
                        <col width="40%">
                        <col width="20%">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="applyAut-table-til">收款户名</td>
                        <td>${(applyDTO.oppositeAccountName)!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til">收款账号</td>
                        <td colspan="2">${(applyDTO.oppositeAccountNo)!}</td>
                    </tr>
                    <#if applyDTO.applyType=="WITHDRAW_FUND">
                    <tr>
                        <td class="applyAut-table-til">银行</td>
                        <td colspan="2">${(applyDTO.withdraw_bank)!}</td>
                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <#if isPendingAudit && showAuditButtons && applyDTO.allowAudit>
            <div class="accountList-content">
                <table class="applyAut-table">
                    <colgroup>
                        <col width="40%">
                        <col width="40%">
                        <col width="20%">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="applyAut-table-til">审核结果</td>
                        <td>
                            <div class="radioContent" id="audit-result" style="padding:5px 0;">
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
                    </tr>
                    <tr>
                        <td class="applyAut-table-til" style="vertical-align: text-top">备注</td>
                        <td colspan="2">
                            <textarea id="audit-remark" name="auditMessage" maxlength="100"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2" style="padding-top:30px;">
                            <a class="default-link confirm-link submit" href="javascript:void(0);">确认</a>
                            <a class="default-link back-link" href="javascript:void(0);">取消</a> 
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <#else>
            <div class="accountList-content">
                <table class="applyAut-table">
                    <colgroup>
                        <col width="40%">
                        <col width="40%">
                        <col width="20%">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="applyAut-table-til">审核结果</td>
                        <td style="font-size:15px">${(applyDTO.applyStatus.getDesc())!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til">备注</td>
                        <td colspan="2" style="font-size:15px">${(applyDTO.auditMessage)!}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </#if>
			<#if isPendingAudit>
	            <div class="accountList-content" style="padding:20px;">
	                <div class="news-head">放款信息</div>
	                <div class="accountDetail-wrap">
				        <div id="accountDetailGrid" class="accountDetail k-grid k-widget" data-role="grid" style=""></div>
				    </div>
	            </div>
	        </#if>
        </div>
    </section>
    </form>
</div>
<script>
$(function(){
    $('.submit').on('click',function(){
        var result = ($('#audit-result .active .labelFonts').text()=='同意'?'ACCEPTED':'REJECTED');
        $('#audit-result').append('<input type="hidden" name="auditStatus" value="'+result+'"/>');
        $('#audit-trade-form').submit();
    });
    $('.back-link').click(function() {
		window.location.href = basepath + "/account/audit/list";
	});
});
</script>
</@hb.htmlBase>