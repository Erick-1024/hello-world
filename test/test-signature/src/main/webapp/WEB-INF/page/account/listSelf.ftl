<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="我的账户" jsFiles=["page/account/listSelf.js","common/dateutil.js","common/cana.util.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "我的账户" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="240">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>账户名称</th>
                            <td>
                            	<input type="text" name="accountName" id="accountName" style="width: 185px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="240">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>银行账号</th>
                            <td>
                            	<input type="text" name="accountNo" id="accountNo" class="bankCard" style="width: 185px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="240">
                        </colgroup>
                        <tbody>
                    	<tr>
                            <th>账户状态</th>
                            <td>
                                <select class="selectWrap" name="accountStatus" id="accountStatus" data-role="dropdownlist">
                                    <option value="">全部</option>
                                    <#if accountStatusList??>
	                                    <#list accountStatusList as accountStatus>
	                                    	<option value=${accountStatus.name()}>${accountStatus.desc()}</option>
										</#list>
									</#if>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a href="javascript:void(0);" class="form-search-link"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <div style="margin-top:15px;">
			<#if authorizeKey("AM_ACCOUNT_SELF_TRANSFER_FUND")>
            <a href="${basepath!}/account/trade/transferFund" class="form-add-link" target="_blank"><i class="AddIcon"></i>转账</a>
            </#if>
			<#if authorizeKey("AM_ACCOUNT_SELF_WITHDRAW_FUND")>
            <a href="${basepath!}/account/trade/withdrawFund" class="form-add-link" target="_blank"><i class="AddIcon"></i>提现</a>
            </#if>
        </div>
        <div id="accountGridWrap">
	       	<div class="accountManageGrid"></div>
    	</div>
    </section>
</div>
<script>
	var curSubMenu = "我的账户";
	var companyId = "${companyId}";
	var detailAuth = ${authorizeKey("AM_ACCOUNT_SELF_DETAIL")?string("true", "false")};
	var freezeAuth = ${authorizeKey("AM_ACCOUNT_SELF_FREEZE")?string("true", "false")};
	var unfreezeAuth = ${authorizeKey("AM_ACCOUNT_SELF_UNFREEZE")?string("true", "false")};
</script>
<#include "tipBoxTemplate.ftl" />
<#include "confirmBoxTemplate.ftl" />
</@hb.htmlBase>