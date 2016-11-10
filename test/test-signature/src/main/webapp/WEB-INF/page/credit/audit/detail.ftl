<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="审核列表详情" jsFiles=["page/credit/audit/mouse.js", "page/credit/audit/detail.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
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
                        <th rowspan="10">审核信息</th>
                        <td class="registionRow-til">法院被执行（企业）总金额（元）</td>
                        <td id="enterpriseExecutionMoney">${customerApplyDetailDTO.enterpriseExecutionMoney!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（企业）</td>
                        <td>${customerApplyDetailDTO.enterpriseExecutionTimes!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">法院被执行（个人）总金额（元）</td>
                        <td id="individualExecutionMoney">${customerApplyDetailDTO.individualExecutionMoney!}</td>
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
                        <td>组织机构代码证</td>
                        <td>${customerApplyDetailDTO.organizationNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.organizationMediaId!}" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td>营业执照证</td>
                        <td>${customerApplyDetailDTO.businessLicenceNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.businessLicenceMediaId!}" target="_black">查看</a>
                        </td>
                    </tr>
                    <tr>
                        <td>税务登记证</td>
                        <td>${customerApplyDetailDTO.taxRegistrationCertificateNo!}</td>
                        <td>
                            <a class="manual-chk" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}" target="_black">查看</a>
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
        </div>
    </section>
</div>
</@hb.htmlBase>
<#include "contactInfoTemplate.ftl" />
<#include "travelzenEvaluationTemplate.ftl" />
<#include "enterpriseInfoTemplate.ftl" />
