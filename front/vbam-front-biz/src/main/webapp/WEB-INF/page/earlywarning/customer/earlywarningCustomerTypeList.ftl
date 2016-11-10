<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/earlywarning/EarlyWarningToolsBar.ftl" as ctb/>
<@hb.htmlBase title="预警信息" jsFiles=["common/dateutil.js", "common/cana.util.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "预警" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<div class="main-container">
    <section class="whiteBg">
        <@ctb.toolsBar "information"/>
        <div class="monitor-wrap-til">调整预警信息</div>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="65">
                    <col width="280">
                    <col width="100">
                    <col width="280">
                    <col width="70">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户名称</th>
                    <td>${typeListResponse.companyName}</td>
                    <th>外部客户名称</th>
                    <td>${typeListResponse.outCustomerName}</td>
                    <th>预警状态</th>
                    <td>
                    <#if !typeListResponse.earlywaringLevel??>
                        <a class="monitor-Bg monitor-skyBg" href="javascript:void(0);">${typeListResponse.eralywaringLevelDesc}</a>
                    <#elseif typeListResponse.earlywaringLevel == 'yellow'>
                        <a class="monitor-Bg monitor-yellowBg" href="javascript:void(0);">${typeListResponse.eralywaringLevelDesc}</a>
                    <#elseif typeListResponse.earlywaringLevel == 'orange'>
                        <a class="monitor-Bg monitor-orangeBg" href="javascript:void(0);">${typeListResponse.eralywaringLevelDesc}</a>
                    <#else>
                        <a class="monitor-Bg monitor-redBg" href="javascript:void(0);">${typeListResponse.eralywaringLevelDesc}</a>
                    </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="new-war-table">
            <table>
                <colgroup>
                    <col width="300">
                    <col width="300">
                    <col width="300">
                    <col width="300">
                </colgroup>
                <tbody>
                <tr class="new-war-title">
                    <td>预警种类</td>
                    <td>预警数量(条)</td>
                    <td>最后修改时间</td>
                    <td>操作</td>
                </tr>
                <#list EarlywarningEventCategory?keys as key>
					<tr>
	                    <td>${EarlywarningEventCategory[key]}</td>
	                    <#if typeListResponse.earlyWaringEventTypeDTOs?? && typeListResponse.earlyWaringEventTypeDTOs?size != 0>
	                    	<#assign isShow=false />
	                    	<#list typeListResponse.earlyWaringEventTypeDTOs as earlyWaringEventTypeDTO>
	                    		<#if earlyWaringEventTypeDTO.earlywaringEventType == key>
	                    			<td>${earlyWaringEventTypeDTO.number}</td>
	                    			<td>${earlyWaringEventTypeDTO.updateTime?datetime}</td>
	                    			<td><a class='new-link' href='${basepath}/earlywarning/event/earlyWarningEventHistory/${key}/${typeListResponse.memberId}/${typeListResponse.outCustomerId}/cancel/information'>解除事件</a><a class='new-link' href='${basepath}/earlywarning/event/earlyWarningEventHistory/${key}/${typeListResponse.memberId}/${typeListResponse.outCustomerId}/history/information'>预警事件历史</a></td>
	                    			<#assign isShow=true />
	                    			<#break>
	                    		</#if>
	                    	</#list>
	                    	<#if !isShow>
	                    		<td>0</td>
	                    		<td>-</td>
	                    		<td><a class='new-link' href='${basepath}/earlywarning/event/earlyWarningEventHistory/${key}/${typeListResponse.memberId}/${typeListResponse.outCustomerId}/history/information'>预警事件历史</a></td>
                    		</#if>
	                    <#else>
	                    	<td>0</td>
	                    	<td>-</td>
	                    	<td><a class='new-link' href='${basepath}/earlywarning/event/earlyWarningEventHistory/${key}/${typeListResponse.memberId}/${typeListResponse.outCustomerId}/history/information'>预警事件历史</a></td>
	                    </#if>
	                </tr>
				</#list>
                </tbody>
            </table>
        </div>
    </section>
</div>

<#include '../../tipBoxTemplate.ftl'/>

</@hb.htmlBase>
