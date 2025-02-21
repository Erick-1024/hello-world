<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资计数表" jsFiles=["page/report/repayment/countReport.js"] cssFiles=["css/reports.css"] localCssFiles=[] curTopMenu = "统计报表" curSubMenu = "融资计数表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <form class="reprots-form">
            <table class="reprots-table">
                <colgroup>
                    <col width="80">
                    <col width="320">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>日期范围</th>
                    <td>
                        <input type="text" id="startTime" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" id="endTime" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <a id="queryCountReport" class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <a class="reportsBtn-link export-btn" href="javascript:void(0);">导出</a>
        </form>
        <div id="countReportGridWrap">
        	<div class="reprots-grid" id="reprots-countGrid" style="margin-top:15px;"></div>
        </div>
    </div>
</div>
</@hb.htmlBase>