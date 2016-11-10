<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="业务列表" jsFiles=["page/asset/factorbusiness/list.js", "common/cana.util.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "业务管理" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <form onsubmit="return false;">
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="250px">
                </colgroup>
                <tbody>
                <tr>
                    <th>业务合同号</th>
                    <td><input id="businessContractNo" type="text" style="width:200px;"></td>
                    <th>客户名称</th>
                    <td><input id="customerName" type="text" style="width:200px;"></td>
                    <th>业务产品</th>
                    <td>
                        <select style="width:200px;" id="businessProduct" data-role="dropdownlist">
                            <option value="">全部</option>
							<#list BusinessProduct?keys as key>
								<option value="${key}">${BusinessProduct[key]}</option>
							</#list>
                        </select>
                    </td>
                    <th>状态</th>
                    <td>
                        <select style="width:200px;" id="loanState" data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list LoanState?keys as key>
                            	<option value="${key}">${LoanState[key]}</option>
							</#list>
                        </select>
                    </td>
                    <td style="padding-left:50px;">
                        <a class="form-search-btn" id="listSearch" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a>
                        <#if authorizeKey("BAI_FB_CREATE") >
                        	<a class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">申请保理</a>
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
            <form class="out-new-form" onsubmit="return false;">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:100px;text-align: right;">客户名称</td>
                        <td style="width:200px;"><input type="text" id="innerCustomerName" style="width:160px;"></td>
                        <td><a class="form-search-btn" id="customerSearch" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            </form>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="client-out-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="customerOk" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

<#include '../../tipBoxTemplate.ftl'/>
<#include '../../confirmBoxTemplate.ftl'/>

<script>
	var detailAuth = ${authorizeKey("BAI_FB_DETAIL")?string("true", "false")};
	var deleteAuth = ${authorizeKey("BAI_FB_DELETE")?string("true", "false")};
	var editAuth = ${authorizeKey("BAI_FB_EDIT")?string("true", "false")};
</script>

</@hb.htmlBase>