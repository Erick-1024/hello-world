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
		<tr>
		<tr>
			<td class="acu-title"><span class="redFalg">*</span><#if accountTradeType == "TRANSFER_FUND">转账金额<#else>提现金额</#if></td>
			<td>
			    <input class="withdraw-align moneyNum" name="amount" id="amount" type="text" style="width: 250px;" placeholder="￥" transferFundRule required data-required-msg="金额不能为空">
			</td>
			<td style="padding-left: 5px;">元<span data-for="amount" class="k-invalid-msg"></span></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">大写：<span id="transfer-ch"></span></td>
		</tr>
		<#if accountTradeType == "WITHDRAW_FUND">
		<tr>
			<td class="acu-title">提现手续费</td>
			<td class="withdraw-align" style="text-align:left;">
				<span class="commissionCharge" style="padding-right:5px;">#=data.commissionCharge#</span>元
				<a style="margin-left:20px" class="common-tooltip" title="此费用为银行提现手续费,规则如下：\r\n一万以下：5.50\r\n一万至十万：10.50\r\n十万至五十万：15.50\r\n五十万至一百万：20.50\r\n一百万以上：总金额的万分之0.2加0.5不超过200.00" href="javascript: void(0);">说明</a>
			</td>
			<td style="padding-left: 5px;"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">大写：<span id="commission-ch"></span></td>
		</tr>
		</#if>
		<tr>
			<td class="acu-title">备注</td>
			<td>
			    <input class="" name="remark" id="remark" type="text" style="width: 250px;">
			</td>
			<td></td>
		</tr>
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