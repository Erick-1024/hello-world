<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="添加员工角色" jsFiles=["js/vendor/jquery.ztree.core-3.5.js","js/vendor/jquery.ztree.excheck-3.5.js","page/role/roleCommon.js","page/role/addEmployeeRole.js","facade/formValidatorRules.js","common/formValidator.js"] cssFiles=["css/roleManage.css","css/zTree.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = "员工角色">
<div class="main-container">
    <section class="whiteBg">
        <div class="createRole-wrap">
        	<div class="createRole-title">新建员工角色</div>
            <form id="createRole" method="post" action="">
                <table class="createRole-form">
                    <colgroup>
                        <col width="120">
                        <col width="300">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td><span class="redFalg">*</span>角色名称：</td>
                        <td><input class="roleNameCheck" id="roleName" name="roleName" type="text" style="width:200px;"></td>
                    </tr>
                    <tr>
                        <td class="createRole-th"><span class="redFalg">*</span>权限选择</td>
                        <td>
                            <div id="roletree" class="ztree"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="dlg-rol-row">
                    <a class="default-link confirm-link" href="javascript:void(0);">提交</a>
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