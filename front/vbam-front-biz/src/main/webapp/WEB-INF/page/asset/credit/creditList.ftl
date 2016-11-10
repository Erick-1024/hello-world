<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="额度管理" jsFiles=["page/asset/credit/creditList.js","common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
	curTopMenu = "基础资产信息" curSubMenu = "额度管理" removeExtHeader = false removeExtFooter = false>
	<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <form>
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户名称</th>
                    <td><input type="text" class="customerName" id ="customerName" name ="customerName" style="width:200px;"></td>
                    <td style="padding-left:50px;">
                        <a class="form-search-btn" href="javascript:void(0);" id="seachBtn"><i class="searchIcon"></i>搜索</a>
	                    <#if authorizeKey("BAI_CM_CREATE")>
	                        <a class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">新建</a>
	                    </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>
<!--新建-->
<script id="template-resetPwd-new" type="text/x-kendo-template">
    <div class="dlg-wrapper" style="top:15%;">
        <div class="dlg-del-row">
            <form class="out-new-form">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:100px;text-align: right;">客户名称</td>
                        <td style="width:200px;"><input type="text" id ="customer-name" class="customerName" style="width:160px;"></td>
                        <td><a class="form-search-btn" href="javascript:void(0);" id ="seachBtn-two"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            </form>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="client-out-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id ="createBnt" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--权限配置-->
<script>
	var credit_info_detail = ${authorizeKey("BAI_CM_DETAIL")?string("true","false")};
	var credit_info_modify = ${authorizeKey("BAI_CM_EDIT")?string("true","false")};
	var credit_info_history = ${authorizeKey("BAI_CM_HISTORY")?string("true","false")};
</script>
</@hb.htmlBase>
