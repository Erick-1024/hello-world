<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="企业基础资料列表" jsFiles=["common/dateutil.js","page/asset/enterpriseInfo/enterpriseInfoList.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "企业信息">
<div class="pro-bg">
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
                    <td><input type="text" class="customerName" style="width:200px;"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>客户类型</th>
                    <td>
                        <select class="customerType" style="width:200px;"  data-role="dropdownlist">
                        	<option value="">全部</option>
                        	<#list customerTypes as type>
                        		<option value="${type.name()!}">${type.desc()!}</option>
                        	</#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>资料提交状态</th>
                    <td>
                        <select class="submitState" style="width:200px;"  data-role="dropdownlist">
                        	<option value="">全部</option>
                        	<#list submitState as state>
                        		<option value="${state.name()!}">${state.desc()!}</option>
                        	</#list>
                        </select>
                    </td>
                    <td style="padding-left:50px;">
                        <a class="form-search-btn" href="javascript:void(0);" id="seachBtn"><i class="searchIcon"></i>搜索</a>
                    </td>
                    <td>
                    	<#if authorizeKey("BAI_EI_CREATE")>
                        	<a class="form-search-btn add-client-popup" href="javascript:void(0);" style="float:right">新建</a>
						</#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
<!--消息提示框-->
<div class="client-popup" style="display:none;">
    <div class="out-ch"></div>
    <div class="client-out-min">
        <div class="out-header txt-title">客户名称</div>
        <div class="list-box">
            <table>
                <tbody>
                <tr>
                    <td>客户名称</td>
                    <td style="width:200px;"><input name="customerChooseWindow" type="text" class="customerNameWindow" style="width:160px;"></td>
                    <td><a class="form-search-btn" href="javascript:void(0);" id="searchBtnWindow"><i class="searchIcon"></i>搜索</a></td>
                </tr>
                </tbody>
            </table>
            <div class="monitor-grid" id="client-out-grid"></div>
            <div style="padding-top:15px;">
                <a class="default-link confirm-link" href="javascript:void(0);" id="createBtn">确定</a>
                <a class="default-link confirm-link client-back" href="javascript:void(0);">返回</a>
            </div>
        </div>
    </div>
</div>
<!--权限配置-->
<script>
	var enterprise_info_detail = ${authorizeKey("BAI_EI_DETAIL")?string("true","false")};
	var enterprise_info_modify = ${authorizeKey("BAI_EI_EDIT")?string("true","false")};
</script>
</@hb.htmlBase>
