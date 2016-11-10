<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/yundaex/earlywarning/toolsBar.ftl" as ctb/>
<@hb.htmlBase title="预警" jsFiles=["page/yundaex/earlywarning/reviewList.js", "common/cana.util.js", "common/dateutil.js", "page/yundaex/earlywarning/eventDetailContent.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "预警" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<div class="main-container">
    <section class="whiteBg">
        <@ctb.toolsBar "review"/>
        <form class="monitor-form">
            <table class="monitor-table" style="width:1200px;">
                <colgroup>
                    <col width="100">
                    <col width="400">
                    <col width="120">
                    <col width="450">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td>
                        <input type="text" style="width:261;" name="companyName">
                    </td>
                    <th style="text-align: right;">预警状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
                        <a class="war-out" href="javascript:void(0);" enum="normal">正常</a>
                        <#list earlywarningLevels as earlywarningLevel>
                        	<a class="war-out" href="javascript:void(0);" enum=${earlywarningLevel}>${earlywarningLevel.desc()}</a>
                    	</#list>
                        <input type="hidden" value class="war-check-one" name="earlyWarningLevel">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                	<th style="text-align: right;">申请时间</th>
                    <td>
                        <input type="text" class="time-one data-style hasIcon" readonly name="auditTimeStart">
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly name="auditTimeEnd">
                    </td>
                    <th style="text-align: right;">新增/解除事件审核</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
                        <#list earlywarningEventActions as earlywarningEventAction>
                        	<a class="war-out" href="javascript:void(0);" enum=${earlywarningEventAction}>${earlywarningEventAction.desc()}</a>
                        </#list>
                        <input type="hidden" value class="war-check-one" name="earlywarningEventAction">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <th style="text-align: right;">审核状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
                        <#list earlywarningReviewStates as earlywarningReviewState>
                        	<a class="war-out" href="javascript:void(0);" enum=${earlywarningReviewState}>${earlywarningReviewState.desc()}</a>
                        </#list>
                        <input type="hidden" value class="war-check-one" name="earlywarningReviewState">
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
    </section>
</div>

<script>
	var auditAuth = ${authorizeKey("YD_EARLYWARNING_REVIEW_REVIEW")?string("true", "false")};
</script>

<#include 'reviewDetailTemplate.ftl'/>
<#include '../../tipBoxTemplate.ftl'/>

</@hb.htmlBase>
