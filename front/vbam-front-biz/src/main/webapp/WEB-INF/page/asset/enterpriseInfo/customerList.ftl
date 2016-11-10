<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="客户列表" jsFiles=["common/dateutil.js","page/asset/enterpriseInfo/customerList.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "客户信息">
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
                    <td style="padding-left:50px;">
                    	<#if authorizeKey("BAI_CI_CREATE")>
                        	<a class="form-search-btn" href="${basepath}/asset/customer/customerCreate" style="float:right">新建</a>
						</#if>
                        <a class="form-search-btn" href="javascript:void(0);" id="seachBtn"><i class="searchIcon"></i>搜索</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
<!--权限配置-->
<script>
	var customer_info_detail = ${authorizeKey("BAI_CI_DETAIL")?string("true","false")};
	var customer_info_modify = ${authorizeKey("BAI_CI_EDIT")?string("true","false")};
</script>
</@hb.htmlBase>
