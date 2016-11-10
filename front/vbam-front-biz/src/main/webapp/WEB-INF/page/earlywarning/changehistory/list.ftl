<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/earlywarning/EarlyWarningToolsBar.ftl" as ctb/>
<@hb.htmlBase title="预警流水" jsFiles=["page/earlywarning/changehistory/list.js", "common/dateutil.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "预警" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <@ctb.toolsBar "change_history"/>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="100">
                    <col width="320">
                    <col width="100">
                    <col width="320">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td>
                        <input type="text" style="width:220px;" name="companyName">
                    </td>
                    <th style="text-align: right;">转入时间</th>
                    <td>
                        <input type="text" class="time-one data-style hasIcon" readonly name="inTimeStart">
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly name="inTimeEnd">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">当前预警状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
                        <#list earlywarningLevels as earlywarningLevel>
                        	<a class="war-out" href="javascript:void(0);" enum=${earlywarningLevel}>${earlywarningLevel.desc()}</a>
                        </#list>
                        <input type="hidden" value class="war-check-one" name="earlywarningLevel">
                    </td>
                    <th style="text-align: right;">转出时间</th>
                    <td>
                        <input type="text" class="time-one data-style hasIcon" readonly name="outTimeStart">
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly name="outTimeEnd">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid k-grid k-widget" id="monitorSrl-grid" data-role="grid"></div>
    </section>
</div>

</@hb.htmlBase>
