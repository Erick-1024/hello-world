<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="身份详情" jsFiles=["js/common/yunda.js","page/yundaex/personal/auditList.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
	curTopMenu = "韵达项目" curSubMenu = "身份审核" removeExtHeader = false removeExtFooter = false showMenu=true>

<div class="main-container">
    <div class="bg-box">
        <form>
            <#include "baseInformation.ftl" />
            <div class="pro-title"><span class="pro-title-left">审核信息</span></div>
            <div class="tb-box" style="">
                <table class="check-table">
                    <colgroup>
                        <col width="120">
                        <col width="100">

                    </colgroup>
                    <tbody>
                    
                    <!--实际控制人审核信息-->
                    <tr>
                        <th><#if personalInfoDTO.type == "CONTROLLER">实际控制人姓名<#elseif personalInfoDTO.type == "ACCOUNT_HOLDER">开户人姓名<#else>姓名</#if></th>
                        <td>${(personalInfoDTO.realName)!}</td>
                    </tr>
                    <tr>
                        <th>身份证号码</th>
                        <td>${(personalInfoDTO.residentIdentityCardNo)!}</td>
                    </tr>
                    <tr>
                        <th>手持正反身份证照片</th>
                        <td>
                            <#if personalInfoDTO.residentIdentityCardFrontMediaId ??>
                            	<a href="${mediaserver}/imageservice?mediaImageId=${(personalInfoDTO.residentIdentityCardFrontMediaId)!}&mediaType=video" class="look-more" target='_blank'>查看正面</a>
                            </#if>
                            <#if personalInfoDTO.residentIdentityCardBackMediaId ??>
                            	<a href="${mediaserver}/imageservice?mediaImageId=${(personalInfoDTO.residentIdentityCardBackMediaId)!}&mediaType=video" class="look-more" target='_blank'>查看反面</a>
                        	</#if>
                        </td>
                    </tr>
                    <!--分割线-->
                    <tr></tr>
                    <tr>
                        <th class="bor-de" style="color:#000;text-align: left;">审核结果</th>
                        <td class="bor-de"></td>
                    </tr>
                    <!--分割线-->
                    <tr>
                        <th>审核结果</th>
                        <td><span class="result">${(personalInfoDTO.auditStateDesc)!}</span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="bottom-btn">
                <div class="b-btn-box">
                    <a class="form-search-link" href="javascript:history.back();">返回</a>
                </div>
            </div>
        </form>
    </div>
</div>
</@hb.htmlBase>