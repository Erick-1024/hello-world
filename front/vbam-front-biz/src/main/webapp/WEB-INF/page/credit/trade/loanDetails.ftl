<#import "/common/htmlBase.ftl" as hb>
<#import "/page/credit/CreditToolsBar.ftl" as ctb/>
<@hb.htmlBase title="放款明细" jsFiles=["page/credit/trade/loanDetails.js","common/dateutil.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
	curTopMenu = "真旅项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "loanDetails"/>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="120">
                    <col width="300">
                    <col width="300">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align:right;">CANA平台流水号</th>
                    <td>
                        <input id="summaryId" type="text" style="width:120px;">
                    </td>
                    <td></td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">客户名称</th>
                    <td>
                        <input id="companyName" type="text" style="width:120px;">
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">交易日期</th>
                    <td>
                        <input type="text" readonly="" class="datepicker startDate hasIcon" id="tradeStartDate">
                        <em> 至 </em>
                        <input type="text" readonly="" class="datepicker endDate hasIcon" id="tradeEndDate">
                    </td>
                    <td>
                        <span style="margin-right:20px;">最近</span>
                        <a href="javascript:void(0);" class="status-normal status-default" value="oneWeek">1周</a>
                        <a href="javascript:void(0);" class="status-normal status-default" value="oneMonth">1个月</a>
                        <a href="javascript:void(0);" class="status-normal status-default" value="threeMonths">3个月</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="monitorLimit-grid" class="monitor-grid k-grid k-widget" data-role="grid" style=""></div>
    </section>
</div>

<script>
	var detailAuth = ${authorizeKey("TZ_LOAN_DETAILS_DETAIL")?string("true", "false")};
</script>

<!--遮罩层-->
<div class="window-overlay hidden"></div>

<!--弹窗模板-->
<div id="template-fundDetail" class="template-manualAdd hidden">
    <div class="manualAdd-head">
        <span>放款明细详情</span>
        <div class="manual-closeBar">
            <a href="javascript:void(0);" class="closeHref planCloseBar">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
    <div id="fundDetail-grid" class="monitor-grid"></div>
    </div>
</div>
</@hb.htmlBase>