<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="员工角色详情" jsFiles=["js/vendor/jquery.ztree.core-3.5.js","js/vendor/jquery.ztree.excheck-3.5.js","page/role/roleCommon.js","page/role/employeeRoleDetails.js"] cssFiles=["css/roleManage.css","css/zTree.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = "员工角色">
<div class="main-container">
    <section class="whiteBg">
        <div class="createRole-wrap">
        	<div class="createRole-title">员工角色详情</div>
            <form id="createRole" method="post" action="">
                <table class="createRole-form">
                    <colgroup>
                        <col width="120">
                        <col width="300">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>角色名称：</td>
                        <td><span id="roleName" data-roleId=${role.id} class="incRole-val">${role.roleName}</span></td>
                    </tr>
                    <tr>
                        <td class="createRole-th">权限选择：</td>
                        <td>
                            <div id="roletree" class="ztree"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </section>
</div>
</@hb.htmlBase>