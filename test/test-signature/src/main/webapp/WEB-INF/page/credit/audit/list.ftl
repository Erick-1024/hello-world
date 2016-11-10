<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="审核列表" jsFiles=["page/credit/audit/list.js", "common/dateutil.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="330">
                    <col width="80">
                    <col width="300">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>申请时间</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly="">
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly="">
                    </td>
                    <th>客户名称</th>
                    <td>
                        <input id="customerName" type="text" style="width:180px;">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th>审核状态</th>
                    <td>
                        <a class="status-normal status-chk" href="javascript:void(0);" value="WAIT">待审核</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="ACCESS">已通过</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="NOTACCESS">未通过</a>
                        <a class="status-normal status-default" href="javascript:void(0);">全部</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid k-grid k-widget" id="manualChk-grid" data-role="grid"></div>
     </section>
</div>

<script>
	var detailAuth = ${authorizeKey("TZ_CREDIT_AUDIT_DETAIL")?string("true", "false")};
	var auditAuth = ${authorizeKey("TZ_CREDIT_AUDIT_AUDIT")?string("true", "false")};
</script>
</@hb.htmlBase>
