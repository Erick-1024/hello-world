<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="授信列表" jsFiles=["page/yundaex/limit/creditList.js", "common/dateutil.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "授信列表" removeExtHeader = false removeExtFooter = false>
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
                        <input type="text" id="stationName" name="stationName" style="width:261px;">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">审核日期</th>
                    <td>
                        <!--新的时间规则修改start-->
                        <input type="text" class="datepicker startDate hasIcon" name="startDate" readonly>
                        <!--新的时间规则修改end-->
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" name="endDate" readonly>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">授信状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);">全部</a>
                        <a class="war-out" href="javascript:void(0);" value="WAIT">待生成</a>
                        <a class="war-out" href="javascript:void(0);" value="FINISH">已生成</a>
                        <a class="war-out" href="javascript:void(0);" value="NOTACCESS">未通过</a>
                        <input type="hidden" value="0" class="war-check-one" name="">
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
</@hb.htmlBase>
