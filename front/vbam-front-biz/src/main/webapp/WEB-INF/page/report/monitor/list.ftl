<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="监控" jsFiles=["page/report/monitor/list.js"] cssFiles=["/css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "监控" removeExtHeader = false removeExtFooter = false>

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
                    	当前额度使用状态
                    </td>
                    <td>
                        <a class="status-normal status-chk" href="javascript:void(0);" value>全部</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="in_use">使用中</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="not_used">未使用</a>
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
