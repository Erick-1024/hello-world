<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="资金汇总表" 
			  jsFiles=["page/report/account/fundYearReport.js","common/cana.util.js"] 
			  cssFiles=["css/reports.css"] 
              localCssFiles=[] 
              curTopMenu = "统计报表" curSubMenu = "资金汇总表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
	<div class="whiteBg">
		<input type="hidden" class="userType" value="${(userType)!}"/>
        <form class="reprots-form" method="post">
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
                        <select class="selectWrap" name="year" data-role="dropdownlist" style="width:140px">
                            <#list years as year>
                            	<option value="${(year)!}">${(year)!}年</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <a class="reportsBtn-link export-btn" href="javascript:void(0);">导出</a>
        </form>
        <div class="reprots-grid" id="reprots-finSumGrid" style="margin-top:15px;"></div>
    </div>
</div>
</@hb.htmlBase>