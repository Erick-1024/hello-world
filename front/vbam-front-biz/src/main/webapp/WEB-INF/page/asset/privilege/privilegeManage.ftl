<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="权限管理" jsFiles=["page/asset/privilege/privilegeManage.js","page/asset/privilege/privilegeList.js","page/asset/privilege/addPrivilege.js","common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css","css/valifrom.css"] localCssFiles=[] 
	curTopMenu = "权限管理" curSubMenu = "ABS员工设置" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="250px">
                    <col width="120px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户类型</th>
                    <td>
	                    <select class="s-mode" style="width:200px;" id ="userType" name="userType"  data-role="dropdownlist">
	                    	<option value="">全部</option>
	                        <option value="FINACE">融资客户</option>
	                    </select>
                    </td>        
                    <th>企业名称</th>
                    <td><input type="text" class="" style="width:200px;" id ="companyName" name ="companyName"></td>
                    <td style="padding-left:50px;">
                        <a class="form-search-btn" href="javascript:void(0);" id ="form-search-list"><i class="searchIcon"></i>搜索</a>
                        <#if authorizeKey("PM_PRIVILEGE_ADD") >
                        	<a class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">新增权限</a>
                        </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
        <a class="limit-next-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>
<!--新增权限弹窗1-->
<script id="template-resetPwd-new" type="text/x-kendo-template">
    <div class="dlg-wrapper" style="top:15%;">
        <div class="dlg-del-row">
            
                <table>
                    <tbody>
                    <tr>
                        <td style="width:100px;text-align: right;">企业名称</td>
                        <td style="width:200px;"><input type="text" class="" style="width:160px;" id ="companyNameId" name ="company"></td>
                        <td style="width:100px;text-align: right;">客户类型</td>
                        <td style="width:200px;">
                            <select class="" style="width:160px;" id ="userType-id" name ="userType" data-role="dropdownlist">
                            <option value="FINACE">融资客户</option>
                            </select>
                        </td>
                        <td><a class="form-search-btn" href="javascript:void(0);" id ="out-new-form"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <form class="out-new-form">
        <div class="dlg-del-row">
            <div class="monitor-grid" id="client-out-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="limits-next-btn" href="javascript:void(0);">下一步</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--新增权限弹窗2-->
<script id="template-resetPwd-limits" type="text/x-kendo-template">
    <div class="dlg-wrapper" style="top:15%;">
        <div class="dlg-del-row">
          
                <div class="all-aut-box">
                    <label class ="all-author"><input type='checkbox' name='all-authority' id ="all-authority" value='false' class='input-new all-authority'>全部权限</label>
                </div>
                <table style="margin-top:15px;">
                    <tbody>
                    <tr>
                        <td style="width:92px;text-align: right;">客户名称</td>
                        <td style="width:200px;"><input type="text" class="" name ="customerNameQuery" value ="" style="width:160px;" id ="customerNameQuery"></td>
                        <input type="hidden" class="" style="width:160px;" id ="masterId" name ="masterId" value ="">
                         <input type="hidden" class="" style="width:160px;" id ="customerNameQuerySting" name ="customerName" value ="">
                        <td><a class="form-search-btn" href="javascript:void(0);" id ="searchList-two"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
          <form class="out-new-form">
        <div class="dlg-del-row">
            <div class="monitor-grid" id="limits-out-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);" id ="addPrivilegeBnt">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--配置权限-->
<script>
	var addAuth = ${authorizeKey("PM_PRIVILEGE_CUSTOMER_LIST")?string("true","false")};
	var deleteAuth = ${authorizeKey("PM_PRIVILEGE_DELETE")?string("true","false")};
</script>
<!--成功提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<!--确认弹窗-->
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left-limit">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right-limit">
                        <span class="notice-content">确认要执行操作吗？</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link confirm-link-two" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
</@hb.htmlBase>



