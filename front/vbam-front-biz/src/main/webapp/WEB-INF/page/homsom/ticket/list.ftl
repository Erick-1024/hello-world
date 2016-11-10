<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="客票查询" jsFiles=["page/homsom/ticket/list.js", "common/cana.util.js", "common/init.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "${title}" curSubMenu = "客票查询" removeExtHeader = false removeExtFooter = false>

<div class="main-container" style="padding:15px;">
    <div class="">
        <div class="first-title">查询条件</div>
        <form id="exportForm" onsubmit="return false;" action="${vbamWebServer}homsom/ticket/export/${channel}" method="post">
        <table class="client-tb" style="padding:10px 0 15px 0;border-bottom:3px solid #F1F1F1;">
            <colgroup>
                <col width="100">
                <col width="220">
                <col width="100">
                <col width="320">
                <col width="150">
                <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>代理商名称</th>
                <td><input type="text" id="agentName" name="agentName" style="width:180px;"></td>
                <th>出票日期</th>
                <td>
                    <input type="text" style="width:120px;" id="issueDateStart" name="issueDateStart" class="datepicker startDate hasIcon">
                    <em> - </em>
                    <input type="text" style="width:120px;" id="issueDateEnd" name="issueDateEnd" class="datepicker endDate hasIcon">
                </td>
                <td><a href="javaScript:void(0);" id="search" class="form-search-btn"><i class="searchIcon"></i>搜索</a></td>
                <#if authorizeKey("HS_TICKET_EXPORT")>
                	<td><a href="javaScript:void(0);" class="form-search-btn" id="exportButton" style="float:right;">导出</a></td>
                </#if>
            </tr>
            </tbody>
        </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>

<script>
	var channel = "${channel}";
</script>

</@hb.htmlBase>