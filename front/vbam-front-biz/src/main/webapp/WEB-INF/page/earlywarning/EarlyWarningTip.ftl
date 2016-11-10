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
    		<#if earlyWarningEventDetailDTO.subType?? && earlyWarningEventDetailDTO.subType =='COUNTER_GUARANTEE_RATE'>
    			<tr>
		            <td>${earlyWarningEventDetailDTO.represent.represent}</td>
		            <td>
		                <#list earlyWarningEventDetailDTO.extraData as extra>
		            		<span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${extra.metirc?string("00.00%")}（${extra.date}）</span><br>
		            	</#list>
		            </td>
		            <td>${earlyWarningEventDetailDTO.represent.standard}</td>
		        </tr>
    		</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
    		<#if earlyWarningEventDetailDTO.subType?? && earlyWarningEventDetailDTO.subType =='SALES_CHANGE_RATE'>
    			<tr>
            		<td>${earlyWarningEventDetailDTO.represent.represent}</td>
            		<td>
		                <#list earlyWarningEventDetailDTO.extraData as extra>
		            		<span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${extra.metirc?string("00.00%")}（${extra.date}）</span><br>
		            	</#list>
		            </td>
		            <td>${earlyWarningEventDetailDTO.represent.standard}</td>
		        </tr>
    		</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
    		<#if earlyWarningEventDetailDTO.subType?? && earlyWarningEventDetailDTO.subType =='SALES_REPAYMENT_RATE'>
    			<tr>
		            <td>${earlyWarningEventDetailDTO.represent.represent}</td>
		            <td>
		            	<#list earlyWarningEventDetailDTO.extraData as extra>
		            		<span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${extra.metirc?string("00.00%")}（${extra.date}）</span><br>
		            	</#list>
		            </td>
		            <td>${earlyWarningEventDetailDTO.represent.standard}</td>
		        </tr>
    		</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
    		<#if earlyWarningEventDetailDTO.subType?? && earlyWarningEventDetailDTO.subType =='CONTINUE_OVERDUE_NUM'>
    			<tr>
		            <td>${earlyWarningEventDetailDTO.represent.represent}</td>
		            <td>
		                <span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${earlyWarningEventDetailDTO.extraData.number}次</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.represent.standard}</td>
		        </tr>
    		</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
    		<#if earlyWarningEventDetailDTO.subType?? && earlyWarningEventDetailDTO.subType =='TOTAL_OVERDUE_NUM'>
    			<tr>
		            <td>${earlyWarningEventDetailDTO.represent.represent}</td>
		            <td>
		                <span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${earlyWarningEventDetailDTO.extraData.number}次</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.represent.standard}</td>
		        </tr>
    		</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
        	<#if earlyWarningEventDetailDTO.type =='COURT_EXECUTION_COMPANY'>
        		<tr>
		            <td>${earlyWarningEventDetailDTO.subType}</td>
		            <td>
		               <span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${formatCentMoney(earlyWarningEventDetailDTO.amount)}元，${earlyWarningEventDetailDTO.extraData['courtexecutioncompany.month']}月内${earlyWarningEventDetailDTO.represent}次</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.subType}金额<#if earlyWarningEventDetailDTO.level =='orange'>≤<#else>></#if>${earlyWarningEventDetailDTO.extraData['courtexecutioncompany.money.threshold']?string(",###.##")}元，或近${earlyWarningEventDetailDTO.extraData['courtexecutioncompany.month']}月次数<#if earlyWarningEventDetailDTO.level =='orange'><<#else>≥</#if>${earlyWarningEventDetailDTO.extraData['courtexecutioncompany.count.threshold']}次</td>
		        </tr>
        	</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
        	<#if earlyWarningEventDetailDTO.type =='COURT_EXECUTION_INDIVIDUAL'>
        		<tr>
		            <td>${earlyWarningEventDetailDTO.subType}</td>
		            <td>
		                <span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${formatCentMoney(earlyWarningEventDetailDTO.amount)}元，${earlyWarningEventDetailDTO.extraData['courtexecutionindividual.month']}月内${earlyWarningEventDetailDTO.represent}次</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.subType}金额<#if earlyWarningEventDetailDTO.level =='orange'>≤<#else>></#if>${earlyWarningEventDetailDTO.extraData['courtexecutionindividual.money.threshold']?string(",###.##")}元，或近${earlyWarningEventDetailDTO.extraData['courtexecutionindividual.month']}月次数<#if earlyWarningEventDetailDTO.level =='orange'><<#else>≥</#if>${earlyWarningEventDetailDTO.extraData['courtexecutionindividual.count.threshold']}次</td>
		        </tr>
        	</#if>
        </#list>
        <#list earlyWarningEventDetailDTOs as earlyWarningEventDetailDTO>
        	<#if earlyWarningEventDetailDTO.type =='NEGATIVE_REPORT'>
        		<tr>
		            <td>${earlyWarningEventDetailDTO.subType}</td>
		            <td>
		            	<span class=<#if earlyWarningEventDetailDTO.level =='yellow'> "monitor-yellow" <#elseif earlyWarningEventDetailDTO.level =='orange'> "monitor-orange" <#else> "monitor-red" </#if>>${earlyWarningEventDetailDTO.represent}</span>
		            </td>
		            <td>${earlyWarningEventDetailDTO.extraData}</td>
		        </tr>
        	</#if>
        </#list>
    </table>
</div>