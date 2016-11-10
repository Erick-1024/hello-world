<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/yundaex/earlywarning/toolsBar.ftl" as ctb/>
<@hb.htmlBase title="预警信息" jsFiles=["page/yundaex/earlywarning/informationList.js", "common/dateutil.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "预警" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <@ctb.toolsBar "information"/>
        <form class="monitor-form" onsubmit="return false;">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="280">
                    <col width="100">
                    <col width="350">
                    <col width="150">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td>
                        <input type="text" style="width:240px;" name="companyName">
                    </td>
					<th style="text-align: right;">当前预警状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
                        <#list earlywarningLevels as earlywarningLevel>
                        	<a class="war-out" href="javascript:void(0);" enum=${earlywarningLevel}>${earlywarningLevel.desc()}</a>
                    	</#list>
                        <input type="hidden" value class="war-check-one" name="earlyWarningLevel">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorInfo-grid"></div>
    </section>
</div>

<script>
	var adjuestAuth = ${authorizeKey("YD_EARLYWARNING_INFORMATION_ADJUST")?string("true", "false")};
</script>

</@hb.htmlBase>
