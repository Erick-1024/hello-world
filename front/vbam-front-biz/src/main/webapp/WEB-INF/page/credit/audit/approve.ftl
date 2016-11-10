<#macro selectHtml labelId>
	<#assign validMessage = "同意"/>
	<#assign invalidMessage = "不同意"/>
	<td>
		<div class="radioContent">
			<input name="${labelId!}" value="false" type="hidden" id="${labelId!}">
	        <label class="radio" id="${labelId!}True" style="margin-left:0;">
	            <span class="radioIcon" style="width:18px;"></span>
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
<@hb.htmlBase title="审核列表审批" jsFiles=["page/credit/audit/mouse.js", "page/credit/audit/approve.js", "common/cana.util.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
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
                        <col width="150px">
                        <col width="200px">
                        <col width="150px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="19">审核信息</th>
                        <td class="registionRow-til">法院被执行（企业）总金额（元）</td>
                        <td id="enterpriseExecutionMoney">${formatMoney(customerApplyDetailDTO.enterpriseExecutionMoney!'')}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（企业）</td>
                        <td>${customerApplyDetailDTO.enterpriseExecutionTimes!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">法院被执行（个人）总金额（元）</td>
                        <td id="individualExecutionMoney">${formatMoney(customerApplyDetailDTO.individualExecutionMoney!'')}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（个人）</td>
                        <td>${customerApplyDetailDTO.individualExecutionTimes!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til" style="vertical-align: text-top">网络负面</td>
                        <td>${customerApplyDetailDTO.negativeNetwork!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>工商信息</td>
                        <td>${customerApplyDetailDTO.businessInfo!}</td>
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
                        <td>组织机构代码证</td>
                        <td>${customerApplyDetailDTO.organizationNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.organizationMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td>营业执照证</td>
                        <td>${customerApplyDetailDTO.businessLicenceNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.businessLicenceMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td>税务登记证</td>
                        <td>${customerApplyDetailDTO.taxRegistrationCertificateNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td>法人</td>
                        <td>
                        	<input type="text" id="legalPerson" value="${customerApplyDetailDTO.legalPerson!}">
                        </td>
                    </tr>
                    <tr>
                        <td>其它</td>
                        <td>${customerApplyDetailDTO.manualAuditRemarks!}</td>
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
                    	<th rowspan="2"></th>
                        <td class="registionRow-til"></td>
                        <td colspan="2" style="padding-top:20px;">
                            <a class="default-link confirm-link " href="javascript:submitReview();" name="${customerApplyDetailDTO.id!}" canaId="${customerApplyDetailDTO.canaId!}">提交</a>
                            <a class="default-link back-link" href="${basepath}/credit/audit/list">返回</a>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
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
</@hb.htmlBase>
<#include "contactInfoTemplate.ftl" />
<#include "../tipBoxTemplate.ftl" />
