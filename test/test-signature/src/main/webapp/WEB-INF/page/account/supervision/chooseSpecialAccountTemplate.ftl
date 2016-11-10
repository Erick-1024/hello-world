<!--选择专用账户弹窗模板-->
<script id="template-superive" type="text/x-kendo-template">
    <div class="superive-wrapper">
        <div class="tableTpl-wrap">
            <div class="tableTpl-head">
                <div class="tableTpl-head-wrap">
                    <table class="tableTpl-grid">
                        <colgroup>
                            <col style="width:60px">
                            <col style="width:150px">
                            <col style="width:200px">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>
                                <div class="checkboxContent">
                                    <label class="checkboxInfo">
                                        <span class="checkboxIcon"></span>
                                    </label>
                                </div>
                            </th>
                            <th>
                                <span class="tabTpl-link">账户名称</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">银行账号</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">买方名称</span>
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tableTpl-content">
                <table class="tableTpl-grid" id="superive-chooseBody">
                    <colgroup>
                        <col style="width:60px">
                        <col style="width:150px">
                        <col style="width:200px">
                        <col>
                    </colgroup>
                    <tbody>
	                <#if specialAccounts??>
	                	<#list specialAccounts as account>
			                <tr>
			                    <td>
			                        <div class="checkboxWrap">
			                            <label class="checkboxInfo"
			                                   account-id="${account.accountId!}"
			                                   account-name="${account.accountName!}"
			                                   account-no="${account.accountNo!}"
			                            	   buyer-name="${account.buyerName!}">
			                                <span class="checkboxIcon"></span>
			                            </label>
			                        </div>
			                    </td>
			                    <td>${account.accountName!}</td>
			                    <td>${account.accountNo!}</td>
			                    <td>${account.buyerName!}</td>
			                </tr>
	                	</#list>
	                </#if>
	                </tbody>
                </table>
            </div>
        </div>
        <div class="superive-foot" style="margin-top:30px;">
            <a class="default-link confirm-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
