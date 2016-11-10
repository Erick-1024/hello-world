<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="个人信息确认" jsFiles=["js/common/yunda.js","common/jquery.multiDownload.js","common/signature.js","page/yundaex/personal/comfirmInfomation.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = false showMenu=false>

<div class="main-container" style="margin-top:50px">
    <div class="bg-box">
        <div class="pro-title"><span class="pro-title-left">基本信息</span></div>
        <div class="tb-box" style="position:relative;">
            <table class="check-table" style="table-layout:fixed;">
                <tbody>
                <tr>
                    <th>网点名称</th>
                    <td>${(cusApplyDTO.stationName)!}</td>
                    <th>站点编号</th>
                    <td>${(cusApplyDTO.stationNo)!}</td>
                    <th>经营地址</th>
                    <td>${(cusApplyDTO.detailAddress)!}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>组织机构代码证</th>
                    <td>
                        <span class="width60">${(cusApplyDTO.organizationNo)!}</span>
                        <a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(cusApplyDTO.organizationMediaId)!}">查看</a>
                    </td>
                    <th>营业执照证</th>
                    <td>
                        <span class="width60">${(cusApplyDTO.businessLicenceNo)!}</span>
                        <a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(cusApplyDTO.businessLicenceMediaId)!}">查看</a>
                    </td>
                    <th>税务登记证</th>
                    <td>
                        <span class="width60">${(cusApplyDTO.taxRegistrationCertificateNo)!}</span>
                        <a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(cusApplyDTO.taxRegistrationCertificateMediaId)!""}">查看</a>
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">个人信息</span></div>
        <div class="tb-box" style="">
            <table class="check-table" style="table-layout:fixed;">
                <tbody>
                <!--只签订授权委托书-->
                <tr>
                    <th><#if personDTO.type == "CONTROLLER">实际控制人<#elseif personDTO.type == "ACCOUNT_HOLDER">开户人</#if>姓名</th>
                    <td>${(personDTO.realName)!}</td>
                    <th><#if personDTO.type == "CONTROLLER">实际控制人<#elseif personDTO.type == "ACCOUNT_HOLDER">开户人</#if>身份证号码</th>
                    <td>${(personDTO.residentIdentityCardNo)!}</td>
                    <th><#if personDTO.type == "CONTROLLER">实际控制人<#elseif personDTO.type == "ACCOUNT_HOLDER">开户人</#if>身份证照片</th>
                    <td>
                        <div class="look-box"><a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(personDTO.residentIdentityCardFrontMediaId)!""}">正面</a></div>
                        <div class="look-box"><a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(personDTO.residentIdentityCardBackMediaId)!""}">反面</a></div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th><#if personDTO.type == "CONTROLLER">实际控制人<#elseif personDTO.type == "ACCOUNT_HOLDER">开户人</#if>邮箱</th>
                    <td>${(personDTO.mail)!}</td>
                    <th><#if personDTO.type == "CONTROLLER">实际控制人<#elseif personDTO.type == "ACCOUNT_HOLDER">开户人</#if>手机号</th>
                    <td>${(personDTO.cellphone)!}</td>
                    <th></th>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">签订合同</span></div>

        <div class="btn-out-box">
            <div class="btn-box">
            	<div style="display:none">
                	<a class="btn-bto-a" href="javascript:void(0);" id="signatureSoftwateInstall">需要安装安全控件</a><i style="color:#ff0000;">安装完成后需要刷新页面</i>
                </div>
                <br>
                <br>
                <input type="hidden" value="${(personDTO.residentIdentityCardNo)!}" name="residentIdentityCardNo"/>
                <form id="confirmForm" action="${basepath}/yundaex/personal/facade/confirmInfomation" method="post">
                	<input type="hidden" value="${id}" name="id"/>
                	<input type="hidden" value="${code}" name="code"/>
                </form>
                <a class="btn-bto-a" href="javascript:void(0);" id="confirmBtn">确认</a><i style="color:#ff0000;">确认后如没有证书，系统会自动安装证书到您的浏览器</i>
            </div>
        </div>
    </div>
</div>

<#include "../../tipBoxTemplate.ftl" />
<style>
	.confirm-link{cursor:pointer}
</style>
</@hb.htmlBase>