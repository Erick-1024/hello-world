<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="资金日报表" 
			  jsFiles=["page/report/account/fundDailyReport.js","common/cana.util.js"] 
			  cssFiles=["css/reports.css"] 
              localCssFiles=[] 
              curTopMenu = "统计报表" curSubMenu = "资金日报表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
	<div class="whiteBg">
		<input type="hidden" class="userType" value="${(userType)!}"/>
        <form class="reprots-form" method="post">
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
                        <input type="text" name="startTime" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" name="endTime" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <a class="reportsBtn-link export-btn" href="javascript:void(0);">导出</a>
        </form>
        <div class="reprots-grid" id="reprots-finDayGrid" style="margin-top:15px;"></div>
    </div>
</div>
</@hb.htmlBase>