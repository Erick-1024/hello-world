<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资汇总表" jsFiles=["page/report/repayment/annualReport.js"] cssFiles=["css/reports.css"] localCssFiles=[] curTopMenu = "统计报表" curSubMenu = "融资汇总表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <form class="reprots-form">
            <table class="reprots-table">
                <colgroup>
                    <col width="40">
                    <col width="150">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>年份</th>
                    <td>
                        <select id="startTime" class="selectWrap" data-role="dropdownlist" style="width:140px">
                            <option value="2016">2016年</option>
                            <option value="2015">2015年</option>
                            <option value="2014">2014年</option>
                            <option value="2013">2013年</option>
                            <option value="2012">2012年</option>
                        </select>
                    </td>
                    <td>
                        <a id="queryAnnualReport" class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <a class="reportsBtn-link export-btn" href="javascript:void(0);">导出</a>
        </form>
        <div id="annualReportGridWrap">
	        <div class="reprots-grid" id="reprots-sumGrid" style="margin-top:15px;"></div>
        </div>
        
        
    </div>
</div>
</@hb.htmlBase>
