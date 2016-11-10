<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="监控" jsFiles=["page/yundaex/monitor/list.js"] cssFiles=["css/project.css","/css/monitor.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "监控" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <form class="monitor-form" onsubmit="return false;">
            <table class="monitor-table">
                <colgroup>
                    <col width="70">
                    <col width="220">
                    <col width="120">
                    <col width="350">
                    <col width="150">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户名称</th>
                    <td>
                        <input type="text" style="width:180px;" id="customerName">
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link"><i class="searchIcon"></i>查询</a>
                    </td>
                    <td>
                    <#if authorizeKey("YD_MONITOR_MONITORIMPORT")>
                        <a id="form-excel" class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">导入监控信息</a>
                    </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="monitorList-grid" class="monitor-grid k-grid k-widget" data-role="grid" style=""></div>
    </section>
</div>

<script>
	var detailAuth = ${authorizeKey("YD_MONITOR_LIST_DETAIL")?string("true", "false")};
</script>

</@hb.htmlBase>
