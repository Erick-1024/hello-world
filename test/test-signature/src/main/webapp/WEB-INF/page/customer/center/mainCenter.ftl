<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="企业信息" jsFiles=["facade/formValidatorRules.js","common/formValidator.js","js/common/ajaxfileupload.js","page/user/editInfoRule.js","page/user/company.js","page/user/popwindow.js","page/user/payPwd.js","page/user/mycontract.js"] cssFiles=["css/userCenter.css"] localCssFiles=[] 
	curTopMenu = "企业信息" curSubMenu = "企业信息" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg clearfix">
        <aside class="leftMenu">
            <ul>
                <li class="leftMenu-current">
                    <a class="leftMenu-link" href="javascript:void(0);">企业信息</a>
                    <i class="rightArrow"></i>
                </li>
                
                <#if userType!='CANA'>
                <li>
                    <a class="leftMenu-link" href="javascript:void(0);">融资信息</a>
                    <i class="rightArrow"></i>
                </li>
                </#if>
                <li>
                    <a class="leftMenu-link tab_account" href="javascript:void(0);">账户信息</a>
                    <i class="rightArrow"></i>
                </li>
                <li>
                    <a class="leftMenu-link" href="javascript:void(0);">联系人信息</a>
                    <i class="rightArrow"></i>
                </li>
                <#if userType=='FINACE'&&authorizeKey('PC_CONTACTS')>
                <li>
                	 <a class="leftMenu-link tab_contract" href="javascript:void(0);">我的合同</a>
                    <i class="rightArrow"></i>
                </li>
                </#if>
            </ul>
        </aside>
         <div class="userCenter-wrap">
         <input class="company_userId" style="display:none" value="${companyInfo.id!''}"/>
         	<#include "/page/customer/center/company_info.ftl"/>
         	<#if userType!='CANA'>
         	<#include "/page/customer/center/financing_info.ftl"/>
         	</#if>	
         	<#include "/page/customer/center/account_number_info.ftl"/>
         	<#include "/page/customer/center/contacts_info.ftl"/>
         	<#if userType=='FINACE'&&authorizeKey('PC_CONTACTS')>
         	<#include "/page/customer/center/company_contract.ftl"/>
         	</#if>
         </div>
     </div >
</div >
<#include "/page/customer/center/popWindow_template.ftl"/>
</@hb.htmlBase>
