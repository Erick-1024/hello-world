<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="额度列表" jsFiles=["page/yundaex/limit/list.js", "common/dateutil.js"] cssFiles=["css/yunda.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "额度列表" removeExtHeader = false removeExtFooter = false>
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
                    <th style="text-align: right;">客户名称</th>
                    <td>
                        <input type="text" id="companyName" name="companyName" style="width:261px;">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">额度</th>
                    <td>
                        <input type="text" value="" name="limitStart" id="limitStart" class="width120">
                        <em>--</em>
                        <input type="text" value="" name="limitEnd" id="limitEnd" class="width120">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">额度生效日</th>
                    <td>
                        <input type="text" name="effectiveDateStart" class="datepicker startDate hasIcon" readonly>
                        <em>--</em>
                        <input type="text" name="effectiveDateStart" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">额度状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);">全部</a>
                        <a class="war-out" href="javascript:void(0);" value="NORMAL">正常</a>
                        <a class="war-out" href="javascript:void(0);" value="FREEZE">冻结</a>
                        <a class="war-out" href="javascript:void(0);" value="DISABLED">停用</a>
                        <a class="war-out" href="javascript:void(0);" value="INACTIVE">未激活</a>
                        <input type="hidden" value="0" class="war-check-one" name="limitStatus" id="limitStatus">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
    </section>
</div>
<script>

</script>
</@hb.htmlBase>