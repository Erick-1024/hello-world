<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="员工角色列表" jsFiles=["page/role/employeeRoleList.js","common/dateutil.js"] cssFiles=["css/roleManage.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = "员工角色">
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <table class="staffRole-table">
                <colgroup>
                    <col width="300px">
                    <col width="500px">
                    <col width="200px">
                </colgroup>
                <tbody>
                <tr>
                    <td>
                        <label class="staffRole-til">角色名称</label><input id="roleName" type="text">
                    </td>
                    <td>
                        <label class="staffRole-til">创建时间</label>
                        <input id="beginTime" type="text" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input id="endTime" type="text" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <a class="form-search-link staffRole-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <#if authorizeKey("PM_EMPLOYEE_ROLE_ADD")>
	        <div style="margin-top:15px;">
	            <a class="form-add-link addStaff-link" target="_blank" href="gotoAddEmployeeRole"><i class="AddIcon"></i>添加角色</a>
	        </div>
        </#if>
        <div id="companyRoleGrid">
	        <div class="staffRoleGrid"></div>
        </div>
    </section>
</div>
<script>
	var employeeDetailsAuth = ${authorizeKey("PM_EMPLOYEE_ROLE_DETAIL")?string("true","false")};
	var employeeEditAuth = ${authorizeKey("PM_EMPLOYEE_ROLE_UPDATE")?string("true","false")};
</script>
</@hb.htmlBase>