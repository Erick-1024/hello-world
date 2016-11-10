<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="监控" jsFiles=["page/report/monitor/list.js"] cssFiles=["/css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "监控" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="70">
                    <col width="220">
                    <col width="70">
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
                </tr>
                </tbody>
            </table>
        </form>
        <div id="monitorList-grid" class="monitor-grid k-grid k-widget" data-role="grid" style=""></div>
    </section>
</div>

<script>
	var detailAuth = ${authorizeKey("TZ_CREDIT_MONITOR_DETAIL")?string("true", "false")};
</script>

</@hb.htmlBase>
