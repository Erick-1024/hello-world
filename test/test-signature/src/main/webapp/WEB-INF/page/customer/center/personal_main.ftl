<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="个人中心" jsFiles=["facade/formValidatorRules.js","page/user/editInfoRule.js","common/formValidator.js","page/user/center.js","page/user/popwindow.js"] cssFiles=["css/userCenter.css"] localCssFiles=[] 
	curTopMenu = "个人中心" curSubMenu = "个人中心" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg clearfix">
        <aside class="leftMenu">
            <ul>
                <li class="leftMenu-current">
                    <a class="leftMenu-link" href="javascript:void(0);">基本信息</a>
                    <i class="rightArrow"></i>
                </li>
               <#-- 
                <li>
                    <a class="leftMenu-link" href="javascript:void(0);">账号和密码</a>
                    <i class="rightArrow"></i>
                </li>-->
            </ul>
        </aside>
         <div class="userCenter-wrap">
         	<#include "/page/customer/center/personal_base.ftl"/>
       <#--  	<#include "/page/customer/center/account_number_info.ftl"/>-->
         </div>
     </div >
</div >
<#include "/page/customer/center/popWindow_template.ftl"/>
</@hb.htmlBase>
