<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资客户引导页" jsFiles=["common/jquery.multiDownload.js","common/signature.js","page/guide/travelzen/resign/comfirmInfomation.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = false showMenu=false>

<div class="main-registion">
    <div class="registion-wrap">
        <div class="registion-title">确认信息以及签署合同</div>
        <div class="finaLead-content">
            <table class="finaLead-tab">
                <colgroup>
                    <col width="200px">
                    <col width="200px">
                    <col width="400px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="7">基本规则</th>
                    <td class="registionRow-til">申请公司</td>
                    <td>
                        <span>${customerDTO.companyName!}</span>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til"><#if (customerDTO.identityCardNo)??>身份证正面照<#else>组织机构代码证</#if></td>
                    <td>
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=${(customerDTO.organizationCodeCertificateMediaId)!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til"><#if (customerDTO.identityCardNo)??>身份证反面照<#else>营业执照证</#if></td>
                    <td>
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=${(customerDTO.businessLicenceMediaId)!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til"><#if (customerDTO.identityCardNo)??>身份证手持照<#else>税务登记证</#if></td>
                    <td>
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=${(customerDTO.taxRegistrationCertificateMediaId)!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til">联系人姓名</td>
                    <td>${customerDTO.contactName!}</td>
                </tr>
                <tr>
                    <td class="registionRow-til">手机号码</td>
                    <td>${customerDTO.contactTel!}</td>
                </tr>
                <tr>
                    <td class="registionRow-til">电子邮箱</td>
                    <td>${customerDTO.contactMail!}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="finaLead-content">
            <table class="finaLead-tab">
                <colgroup>
                    <col width="200px">
                    <col width="200px">
                    <col width="400px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="8">还款信息</th>
                    <td class="registionRow-til">费率</td>
                    <td>0.05% / 日</td>
                </tr>
                <tr>
                    <td class="registionRow-til">逾期管理费率</td>
                    <td>在日费率基础上加收50.00%</td>
                </tr>
                <tr>
                    <td class="registionRow-til">额度</td>
                    <td colspan="2">以CANA给定额度为准</td>
                </tr>
                <tr>
                    <td class="registionRow-til">还款方式</td>
                    <td>账户回款还款</td>
                </tr>
                <tr>
                    <td style="vertical-align: top; padding-top:10px;" class="registionRow-til">监管账户</td>
                    <td>
                    	${supervisionAccountNo!}
                    </td>
                </tr>
                <tr style="display:none">
                    <td></td>
                    <td style="padding-top:20px;">
                        <a class="default-link confirm-link" id="signatureSoftwateInstall">需要安装安全控件
                        </a>
                        <span><font color=red>安装完成后需要刷新页面</font></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding-top:20px;">
                        <a class="default-link confirm-link" id="confirmBtn">确认</a>
                        <span><font color=red>确认后如没有证书，系统会自动安装证书到您的系统</font></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<#include "../../tipBoxTemplate.ftl" />
<style>
	.confirm-link{cursor:pointer}
</style>
</@hb.htmlBase>