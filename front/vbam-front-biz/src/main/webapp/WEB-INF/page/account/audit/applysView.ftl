<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="我的申请" jsFiles=["page/account/audit/list.js", "common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "我的申请" curSubMenu = "我的申请">
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
        	<input type="hidden" id="applyBySelf" value="true"/>
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>申请类型</th>
                            <td>
                                <select id="applyType" class="selectWrap" data-role="dropdownlist" style="width:180px">
                                    <option value="">全部</option>
                                    <#if applyTypeEnums??>
                                    	<#list applyTypeEnums as applyType>
                                    		<option value="${applyType}">${applyType.desc()}</option>
                                    	</#list>
                                    </#if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>审核状态</th>
                            <td>
                                <select id="applyStatus" class="selectWrap" data-role="dropdownlist" style="width:180px">
                                    <option value="">全部</option>
                                    <#if applyStatusEnums??>
                                    	<#list applyStatusEnums as applyStatus>
                                    		<option value="${applyStatus}">${applyStatus.getDesc()}</option>
                                    	</#list>
                                    </#if>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="300">
                        </colgroup>
                        <tbody>
                        <tr>
                        	<th>账户名称</th>
                            <td>
                                <input id="accountName" type="text" style="width:264px;">
                            </td>
                        </tr>
                        <tr>
                            <th>申请时间</th>
                            <td>
                                <input id="startTime" type="text" class="datepicker startDate hasIcon" readonly>
                                <em> 至 </em>
                                <input id="endTime" type="text" class="datepicker endDate hasIcon" readonly>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>银行账号</th>
                            <td>
                                <input id="accountNo" type="text" class="bankCard">
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <div class="accountManageGrid"></div>
    </section>
</div>
<script>
	var supervisionApplyAuditAuth = false;
	var supervisionApplyDetailAuth = ${authorizeKey("MA_ACCOUNT_SUPERVISION_APPLY_DETAIL_SELF")?string("true", "false")};
	var tradeApplyAuditAuth = false;
	var tradeApplyDetailAuth = ${authorizeKey("MA_ACCOUNT_TRADE_APPLY_DETAIL_SELF")?string("true", "false")};
</script>
</@hb.htmlBase>