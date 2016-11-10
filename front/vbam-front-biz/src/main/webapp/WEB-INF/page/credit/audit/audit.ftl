<#macro selectHtml labelId messageType>
	<#if messageType =="consistency">
		<#assign validMessage = "一致"/>
		<#assign invalidMessage = "不一致"/>
	<#else>
		<#assign validMessage = "真实有效"/>
		<#assign invalidMessage = "虚假材料"/>
	</#if>
	<td>
		<div class="radioContent">
			<input name="${labelId!}" value="false" type="hidden" id="${labelId!}">
	        <label class="radio" id="${labelId!}True">
	            <span class="radioIcon"></span>
	            <span class="labelFonts">${validMessage!}</span>
	        </label>
	        <label class="radio active" id="${labelId!}False">
	            <span class="radioIcon"></span>
	            <span class="labelFonts">${invalidMessage!}</span>
	        </label>
	    </div>
    </td>
</#macro>

<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="审核列表审核" jsFiles=["page/credit/audit/mouse.js", "page/credit/audit/audit.js","page/credit/audit/saleData.js", "common/cana.util.js", "js/common/ajaxfileupload.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=["credit/upload.css"] 
curTopMenu = "真旅项目" curSubMenu = "审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="relieve-wrap">
            <div class="monitor-wrap-til">预审核信息</div>
            <#include "customerApplyBaseInfo.ftl" />
            <div class="monitorChk-content">
                <table class="monitorChk-tab">
                    <colgroup>
                        <col width="150px">
                        <col width="230px">
                        <col width="140px">
                        <col width="60px">
                        <col width="150px">
                    </colgroup>
                    <tbody>
                    <tr>
                    	<th rowspan="14">审核信息</th>
                        <td class="registionRow-til">法院被执行（个人）总金额（元）</td>
                        <td>
                            <input type="text" id="individualExecutionMoney" class="checkText moneyNum" style="ime-mode:disabled;" onpaste="return false;" limit="${courtExecuteIndividualAmount!}">
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（个人）</td>
                        <td>
                            <input type="text" id="individualExecutionTimes" class="checkText onlyNum" style="ime-mode:disabled;" onpaste="return false;" limit="${courtExecuteIndividualTimes!}">
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <#if customerApplyDetailDTO.applicantTypeDesc != "个人">
	                    <tr>
	                        <td class="registionRow-til">法院被执行（企业）总金额（元）</td>
	                        <td>
	                            <input type="text" id="enterpriseExecutionMoney" class="checkText moneyNum" style="ime-mode:disabled;" onpaste="return false;" limit="${courtExecuteCompanyAmount!}">
	                        </td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td class="registionRow-til">执行次数（企业）</td>
	                        <td>
	                            <input type="text" id="enterpriseExecutionTimes" class="checkText onlyNum" style="ime-mode:disabled;" onpaste="return false;" limit="${courtExecuteCompanyTimes!}">
	                        </td>
	                        <td></td>
	                        <td></td>
	                    </tr>
                    </#if>
                    <tr>
                        <td class="registionRow-til" style="vertical-align: text-top">网络负面</td>
                        <td colspan="2">
                            <textarea id="negativeNetwork" class="checkText"></textarea>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>工商信息</td>
                        <@selectHtml labelId="businessInfo" messageType="other"/>
						<td></td>
                    </tr>
                    <tr>
                        <td>申请人类型</td>
                        <td>
                        	<#if customerApplyDetailDTO.applicantTypeDesc == "不确定">
								<div class="radioContent">
							        <label class="radio active" id="applicantTypeCompany">
							            <span class="radioIcon"></span>
							            <span class="labelFonts">企业</span>
							        </label>
							        <label class="radio" id="applicantTypeIndividual">
							            <span class="radioIcon"></span>
							            <span class="labelFonts">个人</span>
							        </label>
							    </div>
						    <#else>
						    	${customerApplyDetailDTO.applicantTypeDesc!}
						    </#if>
					    </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><#if customerApplyDetailDTO.applicantTypeDesc != "个人">组织机构代码证<#else>身份证正面</#if></td>
                        <td>${customerApplyDetailDTO.organizationNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.organizationMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                        <@selectHtml labelId="organizationMediaId" messageType="consistency"/>
                    </tr>
                    <tr>
                        <td><#if customerApplyDetailDTO.applicantTypeDesc != "个人">营业执照证<#else>申请人手持身份证正面</#if></td>
                        <td>${customerApplyDetailDTO.businessLicenceNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.businessLicenceMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                        <@selectHtml labelId="businessLicenceMediaId" messageType="consistency"/>
                    </tr>
                    <tr>
                        <td><#if customerApplyDetailDTO.applicantTypeDesc != "个人">税务登记证<#else>申请人手持身份证反面</#if></td>
                        <td>${customerApplyDetailDTO.taxRegistrationCertificateNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                        <@selectHtml labelId="taxRegistrationCertificateMediaId" messageType="consistency"/>
                    </tr>
                    <tr>
                        <td>销售数据</td>
                        <td>
                            <div class="item-link"><a class="manual-chk limit-add" id="saleDataDetail" href="javascript:void(0);">查看</a></div>
                            <div class="item-link"><a class="relieve-status" id="saleDataExport" href="javascript:void(0);">导出</a></div>
                        </td>
                    </tr>
                    <#if customerApplyDetailDTO.applicantTypeDesc != "个人">
	                    <tr>
	                        <td>法人</td>
	                        <td>
	                            <input type="text" id="legalPerson">
	                        </td>
	                        <td></td>
	                        <td></td>
	                    </tr>
                    </#if>
                    <tr>
                        <td>其它</td>
                        <td>
                            <input type="text" id="manualAuditRemarksText">
                        </td>
                        <td></td>
                        <@selectHtml labelId="manualAuditRemarks" messageType="consistency"/>
                    </tr>
                    </tbody>
                </table>
            </div>
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
                        <th rowspan="2">审核结果</th>
                        <td class="registionRow-til">审核结果</td>
                        <td id="auditResult"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2" style="padding-top:20px;">
                            <a class="default-link confirm-link " href="javascript:confirmSubmitReview();" name="${customerApplyDetailDTO.id!}" canaId="${customerApplyDetailDTO.canaId!}">提交</a>
                            <a class="default-link back-link" href="${basepath}/credit/audit/list">返回</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>
<script language="JavaScript" type="text/javascript">
function clearNoNum(obj){   
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
</script>
<#include "contactInfoTemplate.ftl" />
<#include "saleDataTemplate.ftl" />
<#include "../tipBoxTemplate.ftl" />
<#include "../../confirmBoxTemplate.ftl" />
</@hb.htmlBase>
