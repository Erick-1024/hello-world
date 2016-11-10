<#if type == 'history'>
	<#assign titleName = "预警历史">
<#else>
	<#assign titleName = "解除预警事件">
</#if>
<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/earlywarning/EarlyWarningToolsBar.ftl" as ctb/>
<@hb.htmlBase title=titleName jsFiles=["page/earlywarning/event/EarlyWarningEventHistory.js", "common/dateutil.js", "common/cana.util.js", "page/earlywarning/EventDetailContent.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "预警" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<div class="main-container">
    <section class="whiteBg">
        <@ctb.toolsBar thirdItem/>
        <div class="monitor-wrap-til">${titleName}</div>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="130">
                    <col width="200">
                    <col width="100">
                    <col width="400">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td memberId=${memberId} id="companyName">${companyName}</td>
                    <th style="text-align: right;">审核通过时间</th>
                    <td>
                        <input type="text" class="time-one data-style hasIcon" name="entryReviewTimeStart" readonly>
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" name="entryReviewTimeEnd" readonly>
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">外部客户名称</th>
                    <td ouCustomerId=${outCustomerId} id="outCustomerName">${outCustomerName}</td>
                    <#if currentEarlywarningEventCategory == 'SYSTEM' && type == 'history'>
	                    <th style="text-align: right;">事件状态</th>
	                    <td>
	                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
	                        <a class="war-out" href="javascript:void(0);" enum="false">未解除</a>
	                        <a class="war-out" href="javascript:void(0);" enum="true">已解除</a>
	                        <input type="hidden" value class="war-check-one" name="isCancel">
	                    </td>
	                <#elseif currentEarlywarningEventCategory == 'SYSTEM' && type != 'history'>
                    <#else>
                    	<th style="text-align: right;">事件发生时间</th>
                    	<td>
                        	<input type="text" class="time-one data-style hasIcon" name="occurTimeStart" readonly>
                        	<em> 至 </em>
                        	<input type="text" class="datepicker endDate hasIcon" name="occurTimeEnd" readonly>
                    	</td>
                    </#if>
                </tr>
                <tr>
                	<th style="text-align: right;">预警种类</th>
                	<td earlywarningType=${currentEarlywarningEventCategory} type=${type}>${EarlywarningEventCategory[currentEarlywarningEventCategory]}</td>
                	<#if currentEarlywarningEventCategory != 'SYSTEM' && type == 'history'>
	                    <th style="text-align: right;">事件状态</th>
	                    <td>
	                        <a class="war-out war-on" href="javascript:void(0);" enum>全部</a>
	                        <a class="war-out" href="javascript:void(0);" enum="false">未解除</a>
	                        <a class="war-out" href="javascript:void(0);" enum="true">已解除</a>
	                        <input type="hidden" value class="war-check-one" name="isCancel">
	                    </td>
                    <#else>
                	</#if>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
    </section>
</div>

<#include 'EarlyWarningEventDetailTemplate.ftl'/>
<#include '../../tipBoxTemplate.ftl'/>

</@hb.htmlBase>
