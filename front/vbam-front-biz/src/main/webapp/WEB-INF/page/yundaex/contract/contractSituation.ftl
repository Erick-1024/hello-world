<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="合同签约情况" jsFiles=["js/common/yunda.js","common/dateutil.js","page/yundaex/contract/contractSituation.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
	curTopMenu = "韵达项目" curSubMenu = "合同签约情况" removeExtHeader = false removeExtFooter = false showMenu=true>

<div class="main-container">
    <section class="whiteBg">
        <form class="monitor-form">
            <table class="monitor-table" style="width:1200px;">
                <colgroup>
                    <col width="80">
                    <col width="120">
                    <col width="300">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">网点客户名称</th>
                    <td>
                        <input type="text" style="width:261px;" name="stationName">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">签约完成时间</th>
                    <td>
                        <!--新的时间规则修改start-->
                        <input type="text" class="datepicker startDate hasIcon" readonly name="auditStartTime">
                        <!--新的时间规则修改end-->
                        <em>--</em>
                        <input type="text" class="datepicker endDate hasIcon" readonly name="auditEndTime">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">签约状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);">全部</a>
                        <a class="war-out" href="javascript:void(0);">已完成</a>
                        <a class="war-out" href="javascript:void(0);">未完成</a>
                        <input type="hidden" value="0" class="war-check-one" name="completeStatus">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="contract-grid"></div>
    </section>
</div>

<script id="template-moniteMiddle" type="text/x-kendo-template">
    <div class="moniteDetail-wrap">
        <a href="javascript:void(0);" class="min-a" id="FINANCE">国内保理业务合同</a>
        <a href="javascript:void(0);" class="min-a" id="CREDIT">授权委托书</a>
        <a href="javascript:void(0);" class="min-a" id="DUTY">个人连带担保责任书</a>
    </div>
</script>
<script>
var resendContractAuth = ${authorizeKey("YD_CONTRACT_List_RESEND")?string("true", "false")};
</script>
</@hb.htmlBase>