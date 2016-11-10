<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="待审核列表" jsFiles=["page/account/apply/listPendingAudit.js","common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "待审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
	<section class="whiteBg">
		<form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="300">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th>用 户 名</th>
	                            <td>
	                            	<input type="text" name="userName" id="username" style="width: 264px;">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>申请时间</th>
	                            <td>
	                            	<input type="text" name="beginTime" id="beginTime" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="endTime" id="endTime" class="datepicker endDate hasIcon" readonly>
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
                            <th>客户类型</th>
                            <td>
                                <select class="selectWrap" name="userType" id="userType" data-role="dropdownlist">
                                    <option value="ALL">全部</option>
                                    <#if userTypes??>
	                                    <#list userTypes as userType>
	                                    	<option value=${userType.name()}>${userType.desc()}</option>
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
                            <col width="240">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>企业名称</th>
                            <td>
                            	<input type="text" name="companyName" id="companyName" style="width: 200px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
		</form>
	    <div id="applyGridWrap">
	       	<div class="accountManageGrid"></div>
    	</div>
	</section>
</div>
<script>
	var curSubMenu = "待审核列表";
	var detailAuth = ${authorizeKey("AM_PENDING_AUDIT_DETAIL")?string("true", "false")};
	var reviewAuth = ${authorizeKey("AM_PENDING_AUDIT_REVIEW")?string("true", "false")};
</script>
</@hb.htmlBase>