<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="用款申请流水" jsFiles=["page/yundaex/loan/loanFlow.js", "common/dateutil.js","page/credit/trade/tradeCommon.js",
         "facade/formValidatorRules.js","common/formValidator.js"] cssFiles=["css/monitor.css","css/popup.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "用款申请流水" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
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
                    <th style="text-align:right;">业务流水号</th>
                    <td>
                        <input type="text" style="width:120px;" id="businessSeq">
                    </td>
                    <td></td>
                     <td>
                        <a class="form-search-link" href="javascript:showList();"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">客户名称</th>
                    <td>
                        <input type="text" style="width:120px;" id="customerName">
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">交易日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" id = "startDate" readonly>
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" id = "endDate" readonly>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">交易状态</th>
                    <td>
                        <a class="status-normal status-chk trade-status" href="javascript:void(0);" value="">全部</a>
                        <a class="status-normal status-default trade-status" href="javascript:void(0);" value="SUCCESS">成功</a>
                        <a class="status-normal status-default trade-status" href="javascript:void(0);" value="FAIL">失败</a>
                        <a class="status-normal status-default trade-status" href="javascript:void(0);" value="HANDING">交易中</a>
                    </td>
                    <td></td>
                </tr>
                
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorTrade-grid"></div>
    </section>
</div>
</@hb.htmlBase>