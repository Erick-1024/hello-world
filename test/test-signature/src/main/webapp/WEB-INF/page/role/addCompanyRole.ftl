<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="添加企业角色" jsFiles=["js/vendor/jquery.ztree.core-3.5.js","js/vendor/jquery.ztree.excheck-3.5.js","page/role/addCompanyRole.js","page/role/roleCommon.js","facade/formValidatorRules.js","common/formValidator.js"] cssFiles=["css/roleManage.css","css/zTree.css"] localCssFiles=[] curTopMenu = "权限管理" curSubMenu = "企业角色">
<div class="main-container">
    <section class="whiteBg">
        <div class="createRole-wrap">
        	<div class="createRole-title">新建企业角色</div>
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
                        <td><span class="redFalg">*</span>角色类型：</td>
                        <!-- <td>
                            <div  class="radioContent roleNameCheck">
                                <label data-usertype="FACTOR" class="radio active">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">资金方</span>
                                </label>
                                <label data-usertype="FINACE" class="radio">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">融资客户</span>
                                </label>
                            </div>
                        </td> -->
                    	<td>
                            <select id="userType" class="selectWrap" data-role="dropdownlist" style="width:180px">
                                <#list userTypes as userType>
	                                	<#if userType.name()!="CANA">
											<option value=${userType.name()}>${userType.desc()}</option>
										</#if>
									</#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span class="redFalg">*</span>角色授权：</td>
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