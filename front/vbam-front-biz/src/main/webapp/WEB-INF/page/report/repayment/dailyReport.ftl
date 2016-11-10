<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资日报表" jsFiles=["page/report/repayment/dailyReport.js"] cssFiles=["css/reports.css"] localCssFiles=[] curTopMenu = "统计报表" curSubMenu = "融资日报表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <form class="reprots-form">
            <table class="reprots-table">
                <colgroup>
                    <col width="50">
                    <col width="160">
                    <col width="80">
                    <col width="320">
                </colgroup>
                <tbody>
                <tr>
                    <th>项目</th>
                    <td>
                    	<select id="businessProduct" class="selectWrap" data-role="dropdownlist" style="width:140px">
                            <option value="All">所有</option>
                    		<#if businessProductMap?exists>
	                    		<#list businessProductMap?keys as key> 
		                            <option value="${key}">${businessProductMap[key]}</option>
	    			            </#list>
    			            </#if>
                            <option value="Summary">汇总</option>
                        </select>
                    </td>
                    <th>日期范围</th>
                    <td>
                        <input type="text" name="startTime" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" name="endTime" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <a id="queryDailyReport" class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <a class="reportsBtn-link export-btn" href="javascript:void(0);">导出</a>
        </form>
        <div id="dailyReportGridWrap">
	        <div class="reprots-grid" id="reprots-sumGrid" style="margin-top:15px;"></div>
        </div>
    </div>
</div>
</@hb.htmlBase>
