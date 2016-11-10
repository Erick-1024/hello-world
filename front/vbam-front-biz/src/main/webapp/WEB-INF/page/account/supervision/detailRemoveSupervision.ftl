<div class="accountList-content">
    <div class="superive-privateAcu">
        <div class="privateAcu-head">解除监管关系</div>
        <table id="table-remove">
            <colgroup>
                <col width="50">
                <col width="70">
                <col width="170">
                <col width="160">
                <col width="170">
                <col width="70">
            </colgroup>
            <thead>
            <tr>
                <td style="text-align:center">选择</td>
                <td>账户类型</td>
                <td>账户名称</td>
                <td>银行账号</td>
                <td>买方名称</td>
                <td>账户状态</td>
            </tr>
            </thead>
            <tbody>
            	<tr>
					<td style="text-align:center">
	                    <div class="checkboxWrap checkboxGeneral">
	                        <label class="checkboxInfo" account-id="${accountDTO.accountId!}">
	                            <span class="checkboxIcon"></span>
	                        </label>
	                    </div>
	                </td>
	                <td>${(accountDTO.accountTypeDesc)!''}</td>
					<td>${(accountDTO.accountName)!''}</td>
					<td id="accountNo" style="text-align:center">${formatBankAccountNo((accountDTO.accountNo)!)}</td>
					<td>${(accountDTO.buyerName)!''}</td>
					<td>${(accountDTO.accountStatusDesc)!''}</td>
				</tr>
            	<#if accountDTO.specialAccounts??>
					<#list accountDTO.specialAccounts as specialAccount>
						<tr>
							<td style="text-align:center">
			                    <div class="checkboxWrap checkboxSpecial">
			                        <label class="checkboxInfo" account-id="${specialAccount.accountId!}">
			                            <span class="checkboxIcon"></span>
			                        </label>
			                    </div>
			                </td>
			                <td>${(specialAccount.accountTypeDesc)!''}</td>
							<td>${(specialAccount.accountName)!''}</td>
							<td style="text-align:center">${formatBankAccountNo((specialAccount.accountNo)!)}</td>
							<td>${(specialAccount.buyerName)!''}</td>
							<td>${(specialAccount.accountStatusDesc)!''}</td>
						</tr>
					</#list>
				</#if>
            </tbody>
        </table>
    </div>
    <div class="removeSu-foot">
        <a href="javascript:void(0);" class="default-link confirm-link" id="confirm-remove">确认</a>
    </div>
    <#if currentUserType="FACTOR">
    	<input type="hidden" id="supervisorName" value="${(accountDTO.finaceName)!}"/>
    </#if>
	<#if currentUserType="FINACE">
    	<input type="hidden" id="supervisorName" value="${(accountDTO.factorName)!}"/>
    </#if>
</div>

<script>
	$(function(){
		$("td#accountNo").each(function(i,e){
		var formatAccountNo = $(e).text().formatBankAccountNo();
		$(e).text(formatAccountNo);
		})
	});
</script>