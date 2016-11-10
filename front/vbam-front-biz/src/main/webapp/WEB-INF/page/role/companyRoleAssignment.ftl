<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="企业角色分配" jsFiles=["js/vendor/jquery.ztree.core-3.5.js","js/vendor/jquery.ztree.excheck-3.5.js","page/role/companyRoleAssignment.js"] cssFiles=["css/roleManage.css","css/zTree.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = "企业列表">
<div class="main-container">
    <section class="whiteBg">
        <div class="createRole-wrap">
            <div class="createRole-title">角色分配</div>
            <form id="createRole" method="post" action="">
                <table class="createRole-form">
                    <colgroup>
                        <col width="130">
                        <col width="300">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td><span id="user" data-id=${userId} class="redFalg">*</span>用户所属角色</td>
                        <td>
                            <select id="multiSelect-box" multiple="multiple">
                                <#list roles?keys as key>
	                            	<#assign flag = true>
	                                <#list originRoles as originRole>
	                                	<#if originRole.id == key>
			                               	<option value=${key} selected>${roles[key]}</option>
			                               	<#assign flag = false>
			                               	<#break>
	                                	</#if>
	                                </#list>
	                                <#if flag == true >
			                        	<option value=${key}>${roles[key]}</option>
                                	</#if>
                                </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="createRole-th"><span class="redFalg">*</span>所分配的权限</td>
                        <td>
                            <div id="roletree" class="ztree"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="dlg-rol-row">
                    <a class="default-link confirm-link" href="javascript:void(0);">保存</a><a class="default-link back-link" href="javascript:window.close();">取消</a>
                </div>
            </form>
        </div>
    </section>
</div>
<!--保存成功提示弹窗模板-->
<script id="template-saveFinished" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-staff-row">
            <span class="dlg-notice"></span><span class="notice-content"></span>
        </div>
    </div>
</script>
</@hb.htmlBase>