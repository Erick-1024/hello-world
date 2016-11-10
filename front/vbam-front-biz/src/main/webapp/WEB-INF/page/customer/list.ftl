<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="企业列表" jsFiles=["page/user/customerList.js","common/dateutil.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = "权限管理" curSubMenu = "企业列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="200">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>用 户 名</th>
                            <td>
                                <input type="text" name="username" id="username" style="width: 160px;">
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
                            <th>客户类型</th>
                            <td>
                                <select class="selectWrap"  name="userType" id="userType" data-role="dropdownlist">
                                	<option value="ALL">全部</option>
                                    <#list userTypes as userType>
                                    	<#if userType.name()!="CANA">
											<option value=${userType.name()}>${userType.desc()}</option>
										</#if>
									</#list>
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
                            <col width="200">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>企业名称</th>
                            <td>
                                <input type="text" name="companyName" id="companyName" style="width: 160px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="390">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>申请时间</th>
                            <td>
                                <input type="text" name="beginTime" id="beginTime" style="width: 160px;" class="datepicker startDate hasIcon" readonly>
                                <em> 至 </em>
                                <input type="text" name="endTime" id="endTime" style="width: 160px;" class="datepicker endDate hasIcon" readonly>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li style="margin-top:8px;">
                    <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <div id="companyAcutGridWrap">
	        <div class="companyAcutGrid"></div>
        </div>
    </section>
</div>

<script>
	var curSubMenu = "企业列表";
	var detailAuth = ${authorizeKey("PM_COMPANY_LIST_DETAIL")?string("true", "false")};
	var roleAssignAuth = ${authorizeKey("PM_COMPANY_LIST_ROLE_ASSIGN")?string("true", "false")};
	var permissionsAuth = ${authorizeKey("PM_COMPANY_LIST_PERMISSIONS")?string("true", "false")};
</script>
</@hb.htmlBase>
