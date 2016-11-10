<div class="monitorChk-content">
    <table class="monitorChk-tab">
        <colgroup>
            <col width="150px">
            <col width="150px">
            <col width="200px">
            <col width="150px">
        </colgroup>
        <tbody>
        <tr>
            <th rowspan="7">基本信息</th>
            <td class="registionRow-til">日期</td>
            <td id="applyDate">${customerApplyDetailDTO.applyDate!}</td>
            <td><input type="hidden" id="tzCustomerId" value="${customerApplyDetailDTO.tzCustomerId!}"></td>
        </tr>
        <tr>
            <td class="registionRow-til">申请公司</td>
            <td>${customerApplyDetailDTO.companyName!}</td>
        </tr>
        <tr>
            <td class="registionRow-til">实际控制人</td>
            <td>${customerApplyDetailDTO.realControlPerson!}</td>
            <td class="tageBox">
                <a class="manual-chk contacter-info" href="javascript:void(0);">联系人信息</a>
            </td>
        </tr>
        <tr>
            <td class="registionRow-til">申请金额</td>
            <td id="applyCreditLimit">${formatMoney(customerApplyDetailDTO.applyCreditLimit!'')}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">申请类型</td>
            <td>${customerApplyDetailDTO.applyTypeDesc!}</td>
            <td></td>
        </tr>
        <tr>
        	<td class="registionRow-til">是否在白名单内</td>
            <td id="inWhitelist">
            	<#if customerApplyDetailDTO.inWhitelist??>
            		<#if customerApplyDetailDTO.inWhitelist>
            			是
            		<#else>
            			否
            		</#if>
            	</#if>
            </td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<#if customerApplyDetailDTO.applicantTypeDesc != "个人">
	<div class="monitorChk-content">
	    <table class="monitorChk-tab">
	        <colgroup>
	            <col width="150px">
	            <col width="150px">
	            <col width="200px">
	            <col width="150px">
	        </colgroup>
	        <tbody>
	        <tr>
	        	<th rowspan="5">身份信息</th>
	            <td class="registionRow-til">法人是否与实际控制人一致</td>
	            <td>
	            	<#if customerApplyDetailDTO.samePersonOfLegalAndRealControl??>
	            		<#if customerApplyDetailDTO.samePersonOfLegalAndRealControl>
	            			是
	            		<#else>
	            			否
	            		</#if>
	            	</#if>
	            </td>
	            <td></td>
	        </tr>
	        <#if customerApplyDetailDTO.samePersonOfLegalAndRealControl?? && customerApplyDetailDTO.samePersonOfLegalAndRealControl>
	        <#else>
		        <tr>
		            <td class="registionRow-til">法人身份证号码</td>
		            <td>
		            	${customerApplyDetailDTO.legalPersonId!}
		            </td>
		            <td></td>
		        </tr>
		        <tr>
		            <td class="registionRow-til">法人手持身份证正面照</td>
		            <td>
		            	<#if customerApplyDetailDTO.legalPersonIdHandheldFrontMediaId??>
		            		<a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.legalPersonIdHandheldFrontMediaId!}&mediaType=video" target="_black">查看</a>
		            	</#if>
		            </td>
		            <td></td>
		        </tr>
	        </#if>
	        <tr>
	            <td class="registionRow-til">实际控制人身份证号码</td>
	            <td>
	            	${customerApplyDetailDTO.realControlPersonId!}	
	            </td>
	            <td></td>
	        </tr>
	        <tr>
	            <td class="registionRow-til">实际控制人手持身份证正面照</td>
	            <td>
	            	<#if customerApplyDetailDTO.realControlPersonIdHandheldFrontMediaId??>
	            		<a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.realControlPersonIdHandheldFrontMediaId!}&mediaType=video" target="_black">查看</a>
	            	</#if>
	            </td>
	            <td></td>
	        </tr>
	        </tbody>
	    </table>
	</div>
</#if>

<div class="monitorChk-content">
    <table class="monitorChk-tab">
        <colgroup>
            <col width="150px">
            <col width="150px">
            <col width="200px">
            <col width="150px">
        </colgroup>
        <tbody>
         <tr>
        	 <th rowspan="7">准入结果</th>
            <td class="registionRow-til">下游客户类型</td>
            <td>${customerApplyDetailDTO.downstreamCustomerTypeDesc!}</td>
            <td></td>
        </tr>
        <tr>
            <td class="registionRow-til">下游回款账期</td>
            <td>${customerApplyDetailDTO.downstreamRepaymentAccountPeriod!}</td>
            <td></td>
        </tr>
        <#if (customerApplyDetailDTO.automaticAuditData.cooperationPeriod)??>
	        <tr>
	            <td class="registionRow-til">与真旅合作月份数</td>
	            <td>${(customerApplyDetailDTO.automaticAuditData.cooperationPeriod)!}</td>
	            <td></td>
	        </tr>
        </#if>
        <#if (customerApplyDetailDTO.accessAutomaticState)?? && customerApplyDetailDTO.accessAutomaticState=="NOTACCESS">
	        <tr>
	            <td class="registionRow-til">准入结果</td>
	            <td><font color="red">${customerApplyDetailDTO.accessAutomaticStateDesc!}</font></td>
	            <td></td>
	        </tr>
        </#if>
        <#if (customerApplyDetailDTO.automaticAuditRemarkList)??>
        	<#list customerApplyDetailDTO.automaticAuditRemarkList as automaticAuditRemark>
		        <tr>
		            <td class="registionRow-til"><#if automaticAuditRemark_index==0>拒绝原因</#if></td>
		            <td>${automaticAuditRemark!}</td>
		            <td></td>
		        </tr>
	        </#list>
        </#if>
        </tbody>
    </table>
</div>