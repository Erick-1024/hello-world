<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="企业角色列表" jsFiles=["page/role/companyRoleList.js","common/dateutil.js"] cssFiles=["css/roleManage.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = "企业角色">
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="190">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>角色名称</th>
                            <td>
                                <input id="roleName" type="text" style="width: 150px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="190">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>所属类型</th>
                            <td>
                                <select id="userType" class="selectWrap" data-role="dropdownlist" style="width:150px">
                                	<option value="" selected="selected">全部</option>
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
                            <col width="300">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>创建日期</th>
                            <td>
                                <input id="beginTime" type="text" class="datepicker startDate hasIcon" readonly>
                                <em> 至 </em>
                                <input id="endTime" type="text" class="datepicker endDate hasIcon" readonly>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <#if authorizeKey("PM_COMPANY_ROLE_ADD")>
        <div style="margin-top:15px;">
	        <a class="form-add-link" href="gotoAddCompanyRole" target="_blank"><i class="AddIcon"></i>新建</a>   
        </div>
        </#if>
        <div id="companyRoleGrid">
	        <div class="roleManageGrid"></div>
        </div>
    </section>
</div>
<script>
	var companyDetailsAuth = ${authorizeKey("PM_COMPANY_ROLE_DETAIL")?string("true","false")};
	var companyEditAuth = ${authorizeKey("PM_COMPANY_ROLE_UPDATE")?string("true","false")};
</script>
</@hb.htmlBase>