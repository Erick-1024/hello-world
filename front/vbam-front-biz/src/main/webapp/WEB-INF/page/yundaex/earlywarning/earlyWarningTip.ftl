<#include '/common/enumcommon.ftl'/>
<div class="moniteDetail-wrap">
    <table>
        <colgroup>
            <col width="35%">
            <col width="30%">
            <col width="35%">
        </colgroup>
        <tr class="moniteDetail-caption">
            <th></th>
            <th>预警值</th>
            <th>预警标准</th>
        </tr>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
        	<#if earlyWarningEventDetailDTO.type !='SYSTEM'>
        		<tr>
		            <td>${YundaexEarlywarningEventCategory[earlyWarningEventDetailDTO.type]}</td>
		            <td>
		            	<span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${earlyWarningEventDetailDTO.represent}</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.extraData}</td>
		        </tr>
		    <#else>
		    	<tr>
		            <td>${earlyWarningEventDetailDTO.represent.represent}</td>
		            <td>
		            	<span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${earlyWarningEventDetailDTO.extraData}</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.represent.standard}</td>
		        </tr>
        	</#if>
        </#list>
    </table>
</div>