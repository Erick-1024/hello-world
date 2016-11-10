<#import "/common/htmlBase.ftl" as hb>
<#if type=="customer">
	<#assign subMenu= "企业列表">
<#else>
	<#assign subMenu= "员工列表">
</#if>
<@hb.htmlBase title="编辑个性权限" jsFiles=["js/vendor/jquery.ztree.core-3.5.js","js/vendor/jquery.ztree.excheck-3.5.js","page/role/roleCommon.js","page/permission/editUserPermissions.js","facade/formValidatorRules.js","common/formValidator.js"] cssFiles=["css/roleManage.css","css/zTree.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = subMenu>
<div class="main-container">
    <section class="whiteBg">
        <div class="createRole-wrap">
        	<div class="createRole-title">编辑个性权限</div>
            <form id="createRole" method="post" action="">
                <table class="createRole-form">
                    <colgroup>
                        <col width="120">
                        <col width="300">
                    </colgroup>
                    <tbody>
                    <tr>
                        <input name="userId" value=${userId} type="hidden"/>
                        <input name="type" value=${type} type="hidden"/>
                        
                        <td class="createRole-th"><span class="redFalg">*</span>权限选择</td>
                        <td>
                            <div id="permissionstree" class="ztree" style="height:520px"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="dlg-rol-row">
                    <a class="default-link confirm-link" href="javascript:void(0);">保存</a>
                </div>
            </form>
        </div>
    </section>
</div>
<!--提示弹窗模板-->
<script id="template-saveFinished" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-staff-row">
            <span class="dlg-notice"></span><span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
</@hb.htmlBase>