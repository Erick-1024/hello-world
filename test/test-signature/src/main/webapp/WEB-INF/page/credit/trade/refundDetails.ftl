<#import "/common/htmlBase.ftl" as hb>
<#import "/page/credit/CreditToolsBar.ftl" as ctb/>
<@hb.htmlBase title="退款明细" jsFiles=["page/credit/trade/refundDetails.js","common/dateutil.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
	curTopMenu = "真旅项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "refundDetails"/>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="300">
                    <col width="300">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th>订单编号</th>
                    <td>
                        <input id="orderNum" type="text" style="width:120px;">
                    </td>
                    <td></td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th>汇总编号</th>
                    <td>
                        <input id="summaryId" type="text" style="width:120px;">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>客户名称</th>
                    <td>
                        <input id="companyName" type="text" style="width:120px;">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>交易日期</th>
                    <td>
                        <input type="text" readonly="" class="datepicker startDate hasIcon" id="tradeStartDate">
                        <em> 至 </em>
                        <input type="text" readonly="" class="datepicker endDate hasIcon" id="tradeEndDate">
                    </td>
                    <td>
                        <span style="margin-right:20px;">最近</span>
                        <a href="javascript:void(0);" class="trade-date status-normal status-default" value="oneWeek">1周</a>
                        <a href="javascript:void(0);" class="trade-date status-normal status-default" value="oneMonth">1个月</a>
                        <a href="javascript:void(0);" class="trade-date status-normal status-default" value="threeMonths">3个月</a>
                    </td>
                </tr>
                <tr>
                    <th>退款日期</th>
                    <td>
                        <input type="text" readonly="" class="datepicker startDate hasIcon" id="refundStartDate">
                        <em> 至 </em>
                        <input type="text" readonly="" class="datepicker endDate hasIcon" id="refundEndDate">
                    </td>
                    <td>
                        <span style="margin-right:20px;">最近</span>
                        <a href="javascript:void(0);" class="refund-date status-normal status-default" value="oneWeek">1周</a>
                        <a href="javascript:void(0);" class="refund-date status-normal status-default" value="oneMonth">1个月</a>
                        <a href="javascript:void(0);" class="refund-date status-normal status-default" value="threeMonths">3个月</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="monitorLimit-grid" class="monitor-grid k-grid k-widget" data-role="grid" style=""></div>
    </section>
</div>
</@hb.htmlBase>