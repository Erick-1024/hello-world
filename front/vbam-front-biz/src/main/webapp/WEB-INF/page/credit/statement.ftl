<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/credit/CreditToolsBar.ftl" as ctb/>
<@hb.htmlBase title="对账单下载" jsFiles=["page/credit/statement.js", "common/dateutil.js","page/credit/trade/tradeCommon.js","facade/formValidatorRules.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "statement"/>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="300">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <a class="form-search-link" id="searchBtn" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
			            <a class="form-search-link" id="exportBtn" href="javascript:void(0);">导出</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorTrade-grid"></div>
    </section>
</div>
</@hb.htmlBase>