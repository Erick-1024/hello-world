<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="公司员工用户列表" curTopMenu = "权限管理" curSubMenu = "员工列表" jsFiles=["facade/formValidatorRules.js","common/formValidator.js","page/employee/employeeManage.js"] cssFiles=["css/roleManage.css","css/common.css"] localCssFiles=[] removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>员工姓名</th>
                            <td>
                                <input type="text" name="contactNameForSearch">
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
                            <th>用 户 名</th>
                            <td>
                                <input type="text" name="usernameForSearch">
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
                            <th>角 色</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:180px" id="roleForSearch">
                                    <option value="ALL">全部</option>
                                	<#if rolesIdAndName?exists>
                						<#list rolesIdAndName?keys as key> 
		                                    <option value=${key!''}>${rolesIdAndName[key]!''}</option>
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
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>激活状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:180px" id="activeStatusForSearch">
									<option value="ALL">全部</option>
									<#if accountActivateStatus??>
										<#list accountActivateStatus as activateStatus>
											<option value="${activateStatus.name()!''}">${activateStatus.desc()!''}</option>
										</#list>
									</#if>
                                </select>
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
        <div style="margin-top:15px;">
        	<#assign auth=authorizeKey("PM_EMPLOYEE_ADD")?string("true", "false")>
        		<#if auth=="true" >
	            	<a class="form-add-link addStaff-link" href="javascript:void(0);"><i class="AddIcon"></i>新增员工</a>
        		</#if>
        </div>
        <div id="staffListGridWrap" class="">
         	<div class="staffListGrid"></div>
         </div>
    </section>
</div>

<!--新建员工弹窗模板-->
<script id="template-createStaff" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form method="post" action="" id="addEmployeeForm" name="addEmployeeForm">
            <ul class="dlg-addStaff-content clearfix">
                <li>
                    <table class="dlg-addStaff-table">
                        <colgroup>
                            <col width="80">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>用户编号</th>
                            <td>
                                <span name="idForAdd"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redFalg">*</span>电子邮箱</th>
                            <td>
                                <input type="text" name="contactMailForAdd">
                            </td>
                        </tr>
                        <tr>
                            <th>员工工号</th>
                            <td>
                                <input type="text" name="jobNoForAdd">
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redFalg">*</span>所属角色</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width: 180px;" id="roleForAdd" name="roleForAdd">
                                    <#if rolesIdAndName?exists>
                						<#list rolesIdAndName?keys as key> 
		                                    <option value=${key!''}>${rolesIdAndName[key]!''}</option>
                						</#list>
            						</#if>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="dlg-addStaff-table">
                        <colgroup>
                            <col width="80">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th><span class="redFalg">*</span>员工姓名</th>
                            <td>
                                <input type="text" name="realNameForAdd">
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redFalg">*</span>联系方式</th>
                            <td>
                                <input type="text" name="contactTelForAdd">
                            </td>
                        </tr>
                        <tr>
                            <th>职位</th>
                            <td>
                                <input type="text" name="jobTitleForAdd">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
            </ul>
            <div class="dlg-addStaff-foot">
                <a class="default-link confirm-link" id= "saveBtnForAdd" href="javascript:void(0);">提交</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
            </div>
        </form>
    </div>
</script>

<!--员工详情弹窗模板-->
<script id="template-staffDetail" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form method="post" action="">
            <ul class="dlg-addStaff-content clearfix">
                <li>
                    <table class="dlg-addStaff-table">
                        <colgroup>
                            <col width="80">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>用户编号</th>
                            <td>
                                <span name="idForShow"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>电子邮箱</th>
                            <td>
                                <span name="contactMailForShow"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>员工工号</th>
                            <td>
                                <span name="jobNoForShow"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>所属角色</th>
                            <td>
                                <span name="roleNameForShow"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="dlg-addStaff-table">
                        <colgroup>
                            <col width="80">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>员工姓名</th>
                            <td>
                                <span name="realNameForShow"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>联系方式</th>
                            <td>
                                <span name="contactTelForShow"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>职位</th>
                            <td>
                                <span name="jobTitleForShow"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>激活状态</th>
                            <td>
                                <span name="accountStatusForShow"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
            </ul>
            <div class="dlg-addStaff-foot">
                <a class="default-link back-link" href="javascript:void(0);">关闭</a>
            </div>
        </form>
    </div>
</script>

<!--编辑员工弹窗模板-->
<script id="template-editStaff" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form method="post" action="" id="editEmployeeForm" name="editEmployeeForm">
            <ul class="dlg-addStaff-content clearfix">
                <li>
                    <table class="dlg-addStaff-table">
                        <colgroup>
                            <col width="80">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                        	<th name="title">用户名</th>
                            <td>
                                <span name="content"></span>
                                <input type="hidden" name="idForUpdate">
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redFalg">*</span>电子邮箱</th>
                            <td>
                                <input type="text" name="contactMailForEdit" value="">
                            </td>
                        </tr>
                        <tr>
                            <th>员工工号</th>
                            <td>
                                <input type="text" name="jobNoForEdit" value="" >
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redFalg">*</span>所属角色</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width: 180px;" id="roleForEdit" name="roleForEdit">
									<#if rolesIdAndName?exists>
                						<#list rolesIdAndName?keys as key> 
		                                    <option value=${key!''}>${rolesIdAndName[key]!''}</option>
                						</#list>
            						</#if>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="dlg-addStaff-table">
                        <colgroup>
                            <col width="80">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th><span class="redFalg">*</span>员工姓名</th>
                            <td>
                            	<input type="text" name="contactNameForEdit" value="">
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redFalg">*</span>联系方式</th>
                            <td>
                                <input type="text" name="contactTelForEdit" value="">
                            </td>
                        </tr>
                        <tr>
                            <th>职位</th>
                            <td>
                                <input type="text" name="jobTitleForEdit" value="">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
            </ul>
            <div class="dlg-addStaff-foot">
                <a class="default-link confirm-link" id="saveBtnForEdit" href="javascript:void(0);">提交</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
            </div>
        </form>
    </div>
</script>

<!--删除弹窗模板-->
<script id="template-delStaff" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-staff-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要删除吗？</span>
        </div>
        <div class="dlg-staff-row">
            <a class="default-link confirm-link" href="javascript:void(0);" id="confirmBtnForDelete">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
            <input type="hidden" id="idForDelete">
        </div>
    </div>
</script>

<!--重置密码弹窗模板-->
<script id="template-resetStaff" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-staff-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要重置密码吗？</span>
        </div>
        <div class="dlg-staff-row">
            <a class="default-link confirm-link" id="confirmBtnForRestPassword" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
            <input type="hidden" id="idForRestPassword">
        </div>
    </div>
</script>

<!--重发邮件弹窗模板-->
<script id="template-resendMail" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-staff-row">
	        <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right">
			            <span class="notice-content"></span>是否确认重发激活邮件至以下邮箱:<span class="notice-content" name="mailAddr"></span>
                    </td>
                </tr>
	         </table>
        </div>
        <div class="dlg-staff-row">
            <a class="default-link confirm-link" id="confirmBtnForResendMail" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
            <input type="hidden" id="idForResendMail">
        </div>
    </div>
</script>
</@hb.htmlBase>
<script>
	var employeeDetailAuth = ${authorizeKey("PM_EMPLOYEE_LIST_DETAIL")?string("true", "false")};
	var employeeEditAuth = ${authorizeKey("PM_EMPLOYEE_LIST_UPDATE")?string("true", "false")};
	var employeeDeleteAuth = ${authorizeKey("PM_EMPLOYEE_LIST_DELETE")?string("true", "false")};
	var employeeResetPasswordAuth = ${authorizeKey("PM_EMPLOYEE_LIST_RESET_PASSWORD")?string("true", "false")};
	var employeeRetryMailAuth = ${authorizeKey("PM_EMPLOYEE_LIST_RETRY_MAIL")?string("true", "false")};
</script>
