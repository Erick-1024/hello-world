<!-- 账户信息 -->
<input type="hidden" disabled="disabled" id="accountTradeType" name="accountTradeType" value="${(accountTradeType)!}"/>
<script id="template-account-info" type="text/x-kendo-template">
<table class="withdraw">
	<colgroup>
        <col width="157px">
        <col width="250px">
        <col width="193px">
	</colgroup>
	<tbody>
		<tr>
			<td class="acu-title"><#if accountTradeType == "TRANSFER_FUND">转出户名<#else>提现户名</#if></td>
			<td colspan="2"><span>#=data.accountName#</span></td>
		</tr>
		<tr>
			<td class="acu-title"><#if accountTradeType == "TRANSFER_FUND">转出账号<#else>提现账号</#if></td>
			<td colspan="2" id="sendAccountNo"><span>#=data.accountNo#</span></td>
		</tr>
		<tr>
			<td class="acu-title">账户类型</td>
			<td>
				<input id="accountType" disabled="disabled" type="hidden" value="#=data.accountType#"/>
				<span>#=data.accountTypeDesc#</span>
			</td>
		</tr>
		<tr>
			<td class="acu-title">监管状态</td>
			<td>
				<input id="accountSupervisionStatus" disabled="disabled" type="hidden" value="#=data.accountSupervisionStatus#"/>
				<input id="accountSupervisionId" disabled="disabled" type="hidden" value="#=data.accountSupervisionId#"/>
				<span>#=data.accountSupervisionStatusDesc#</span>
			</td>
		</tr>
		<tr>
			<td class="acu-title">归集状态</td>
			<td><span>#=data.accountAccumulationStatusDesc#</span></td>
		</tr>
		<tr>
			<td class="acu-title">账户余额</td>
			<td class="withdraw-align" style="text-align:left;">
				<span class="accountBalance"></span>
				元<a class="withdraw-link accountBalanceButton" href="javascript:void(0);">刷新</a>
			</td>
			<td style="padding-left: 5px;"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">大写：<span id="balance-ch"></span></td>
		</tr>
		#if(data.accountType == 'GENERAL' && data.accountSupervisionStatus == 'HAVE_SUPERVISION'){#
			<tr>
				<td class="acu-title">融资余额</td>
				<td class="withdraw-align"><span class="finaceBalance">#=data.financeBalance#</span></td>
				<td style="padding-left: 5px;">元</td>
			</tr>
			<tr>
				<td class="acu-title">资金覆盖率</td>
				<td><span class="fundCoverage"></span>%</td>
			</tr>
		#}#

</table>
</script>
<!--提示弹窗模板-->
<script id="template-withdrawNotice" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要执行操作吗？</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link confirm" href="javascript:void(0);">确认</a><a class="default-link back-link cancel" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>