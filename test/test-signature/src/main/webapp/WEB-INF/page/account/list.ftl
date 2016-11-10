<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="账户列表" jsFiles=["page/account/list.js","common/dateutil.js","common/cana.util.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "账户列表" removeExtHeader = false removeExtFooter = false>
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
                        <#if userType.name() != "CORECOMPANY">
	                        <tr>
	                            <th>账户类型</th>
	                            <td>
	                                <select class="selectWrap" name="accountType" id="accountType" data-role="dropdownlist" style="width: 185px;">
	                                    <option value="">全部</option>
	                                    <#if accountTypeList??>
		                                    <#list accountTypeList as accountType>
		                                    	<option value=${accountType.name()}>${accountType.desc()}</option>
											</#list>
										</#if>
	                                </select>
	                            </td>
	                        </tr>
                        </#if>
                        <#if userType.name() == "FACTOR">
	                        <tr>
	                            <th>融资客户</th>
	                            <td>
	                            	<input type="text" name="finaceName" id="finaceName" style="width: 185px;">
	                            </td>
	                        </tr>
	                    <#else>
	                    	<#if userType.name() != "CORECOMPANY">
		                    	<tr>
		                            <th>资金方</th>
		                            <td>
		                            	<input type="text" name="factorName" id="factorName" style="width: 185px;">
		                            </td>
		                        </tr>
	                        </#if>
                        </#if>
						<#if userType.name() == "CANA">
							<tr>
	                            <th>核心企业</th>
	                            <td>
	                            	<input type="text" name="coreCompanyName" id="coreCompanyName" style="width: 185px;">
	                            </td>
	                        </tr>
                        </#if>
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
                        <#if userType.name() != "CORECOMPANY">
	                        <tr>
	                            <th>监管状态</th>
	                            <td>
	                                <select class="selectWrap" name="supervisionStatus" id="supervisionStatus" data-role="dropdownlist" style="width: 185px;">
	                                    <option value="">全部</option>
	                                    <#if accountSupervisionStatusList??>
	                                    	<#list accountSupervisionStatusList as accountSupervisionStatus>
	                                    		<option value=${accountSupervisionStatus.name()}>${accountSupervisionStatus.desc()}</option>
	                                    	</#list>
	                                    </#if>
	                                </select>
	                            </td>
	                        </tr>
	                    </#if>
                        <#if userType.name() == "CANA">
	                    	<tr>
	                            <th>融资客户</th>
	                            <td>
	                            	<input type="text" name="finaceName" id="finaceName" style="width: 185px;">
	                            </td>
	                        </tr>
                        </#if>
						<#if userType.name() == "FACTOR">
	                    	<tr>
	                            <th>回款账户</th>
	                            <td>
	                            	<select class="selectWrap" id="isTransferInAccount" data-role="dropdownlist" style="width: 185px;">
                                    <option value="">全部</option>
                            		<option value="true">是</option>
                            		<option value="false">否</option>
                                </select>
	                            </td>
	                        </tr>
                        </#if>
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
                        <#if userType.name() != "CORECOMPANY">
							<tr>
	                            <th>归集状态</th>
	                            <td>
	                                <select class="selectWrap" name="accumulationStatus" id="accumulationStatus" data-role="dropdownlist">
	                                    <option value="">全部</option>
	                                    <#if accountAccumulationStatusList??>
	                                    	<#list accountAccumulationStatusList as accountAccumulationStatus>
	                                    		<option value=${accountAccumulationStatus.name()}>${accountAccumulationStatus.desc()}</option>
	                                    	</#list>
	                                    </#if>
	                                </select>
	                            </td>
	                        </tr>
                        </#if>
                        <#if userType.name() == "CANA">
	                    	<tr>
	                            <th>回款账户</th>
	                            <td>
	                            	<select class="selectWrap" id="isTransferInAccount" data-role="dropdownlist">
                                    <option value="">全部</option>
                            		<option value="true">是</option>
                            		<option value="false">否</option>
                                </select>
	                            </td>
	                        </tr>
                        </#if>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a href="javascript:void(0);" class="form-search-link"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <div style="margin-top:15px;">
        	<#if authorizeKey("AM_ACCOUNT_SUPERVISION_CREATE")>
            	<a href="${basepath!}/account/supervision/create" class="form-add-link" target="_blank"><i class="AddIcon"></i>新建监管关系</a>
            </#if>
			<#if authorizeKey("AM_ACCOUNT_TRANSFER_FUND")>
            <a href="${basepath!}/account/trade/transferFund" class="form-add-link" target="_blank"><i class="AddIcon"></i>转账</a>
            </#if>
			<#if authorizeKey("AM_ACCOUNT_WITHDRAW_FUND")>
            <a href="${basepath!}/account/trade/withdrawFund" class="form-add-link" target="_blank"><i class="AddIcon"></i>提现</a>
            </#if>
        </div>
        <div id="accountGridWrap">
	       	<div class="accountManageGrid"></div>
    	</div>
    </section>
</div>
<script>
	var curSubMenu = "账户列表";
	var userType = "${userType}";
	var detailAuth = ${authorizeKey("AM_ACCOUNT_DETAIL")?string("true", "false")};
	var freezeAuth = ${authorizeKey("AM_ACCOUNT_FREEZE")?string("true", "false")};
	var unfreezeAuth = ${authorizeKey("AM_ACCOUNT_UNFREEZE")?string("true", "false")};
	var setDefaultAuth = ${authorizeKey("AM_ACCOUNT_SET_DEFAULT")?string("true", "false")};
	var removeSupersivionAuth = ${authorizeKey("AM_ACCOUNT_SUPERVISION_REMOVE")?string("true", "false")};
</script>
<#include "tipBoxTemplate.ftl" />
<#include "confirmBoxTemplate.ftl" />
</@hb.htmlBase>