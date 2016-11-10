<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="审核列表详情" jsFiles=["page/credit/audit/mouse.js","page/credit/audit/saleData.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
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
                        <th rowspan="18">审核信息</th>
                    <#if (customerApplyDetailDTO.accessAutomaticState)?? && customerApplyDetailDTO.accessAutomaticState=="ACCESS">
                        <td class="registionRow-til">法院被执行（个人）总金额（元）</td>
                        <td id="individualExecutionMoney">${formatMoney(customerApplyDetailDTO.individualExecutionMoney!'')}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（个人）</td>
                        <td>${customerApplyDetailDTO.individualExecutionTimes!}</td>
                        <td></td>
                    </tr>
                    <#if customerApplyDetailDTO.applicantTypeDesc != "个人">
	                    <tr>
	                        <td class="registionRow-til">法院被执行（企业）总金额（元）</td>
	                        <td id="enterpriseExecutionMoney">${formatMoney(customerApplyDetailDTO.enterpriseExecutionMoney!'')}</td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td class="registionRow-til">执行次数（企业）</td>
	                        <td>${customerApplyDetailDTO.enterpriseExecutionTimes!}</td>
	                        <td></td>
	                    </tr>
                    </#if>
                    <tr>
                        <td class="registionRow-til" style="vertical-align: text-top">网络负面</td>
                        <td>${customerApplyDetailDTO.negativeNetwork!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>工商信息</td>
                        <td class="registionRow-til" >${customerApplyDetailDTO.businessInfo!}</td>
                        <td></td>
                    </tr>
                    <tr>
                    </#if>
                        <td class="registionRow-til">申请人类型</td>
                        <td>${(customerApplyDetailDTO.applicantTypeDesc)!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til"><#if customerApplyDetailDTO.applicantTypeDesc != "个人">组织机构代码证<#else>申请人手持身份证正面</#if></td>
                        <td>${customerApplyDetailDTO.organizationNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.organizationMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="registionRow-til"><#if customerApplyDetailDTO.applicantTypeDesc != "个人">营业执照证<#else>申请人手持身份证反面</#if></td>
                        <td>${customerApplyDetailDTO.businessLicenceNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.businessLicenceMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="registionRow-til"><#if customerApplyDetailDTO.applicantTypeDesc != "个人">税务登记证<#else>身份证正面</#if></td>
                        <td>${customerApplyDetailDTO.taxRegistrationCertificateNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}&mediaType=video" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">销售数据</td>
                        <td>
                            <div class="item-link"><a class="manual-chk limit-add" id="saleDataDetail" href="javascript:void(0);">查看</a></div>
                            <div class="item-link"><a class="relieve-status" id="saleDataExport" href="javascript:void(0);">导出</a></div>
                        </td>
                    </tr>
                    <#if (customerApplyDetailDTO.accessAutomaticState)?? && customerApplyDetailDTO.accessAutomaticState=="ACCESS">
                    <#if customerApplyDetailDTO.applicantTypeDesc != "个人">
	                    <tr>
	                        <td class="registionRow-til">法人</td>
	                        <td>${customerApplyDetailDTO.legalPerson!}</td>
	                    </tr>
                    </#if>
                    <tr>
                        <td class="registionRow-til">其它</td>
                        <td>${customerApplyDetailDTO.manualAuditRemarks!}</td>
                    </tr>
                    <#else>
                    <tr>
                        <td></td>
                        <td colspan="2" style="padding-top:20px;">
                            <a class="default-link confirm-link" href="${basepath}/credit/audit/list">返回</a>
                        </td>
                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <#if (customerApplyDetailDTO.accessAutomaticState)?? && customerApplyDetailDTO.accessAutomaticState=="ACCESS">
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
		                        <td>${customerApplyDetailDTO.accessManualStateDesc!}</td>
		                        <td></td>
		                    </tr>
		                    <tr>
		                        <td></td>
		                        <td colspan="2" style="padding-top:20px;">
		                            <a class="default-link confirm-link" href="${basepath}/credit/audit/list">返回</a>
		                        </td>
		                    </tr>
                    	</tbody>
                	</table>
           	 	</div>
       		</#if>
        </div>
    </section>
</div>
</@hb.htmlBase>
<#include "contactInfoTemplate.ftl" />
<#include "saleDataTemplate.ftl" />