<#assign index=0 />

<#-- 用户管理 -->
<#assign um_top_menu_key = "UM"/>
<#assign um_names = ["全部列表", "待审核列表", "已审核列表", "正式用户列表"] />
<#assign um_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
<#assign um_paths = [["/customer/listAll"],
["/customer/listPendingAudit"],
["/customer/listHavingAudit"],
["/customer/listActivated"]] />

<#macro printUMMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="用户管理" index=index+1 moduleKey=um_top_menu_key moduleNames=um_names moduleHosts=um_hosts modulePaths=um_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 账户管理 -->
<#assign am_top_menu_key = "AM"/>
<#assign am_names = ["账户列表", "审核列表", "待审核列表", "已审核列表","我的账户","转账申请", "提现申请","开户申请", "代开户申请", "新建监管关系", "审核列表","流水明细"] />
<#assign am_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
<#assign am_paths = [
["/account/list"],
["/account/apply/list"],
["/account/apply/listPendingAudit"],
["/account/apply/listHavingAudit"],
["/account/listSelf"],
["/account/trade/transferFund"],
["/account/trade/withdrawFund"],
["/account/apply/self"],
["/account/apply/agent"],
["/account/supervision/create"],
["/account/audit/list"],
["/account/trade/listTradeRecord"]
] />

<#macro printAMMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="账户管理" index=index+1 moduleKey=am_top_menu_key moduleNames=am_names moduleHosts=am_hosts modulePaths=am_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 融资管理 -->
<#assign rm_top_menu_key = ""/>
<#assign rm_names = ["融资信息管理", "放款信息录入", "还款计划录入", "还款计划规则","还款计划","逾期列表","7日应还"] />
<#assign rm_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer,vbamWebServer,vbamWebServer] />
<#assign rm_paths = [["/loanInfo/manage/gotoLoanInfoManage"],
["/loanInfo/input"],
["/repayment/plan/input/planSelect"],
["/repayment/rule/list"],
["/repayment/active/gotoActiveRepaymentPlan"],
["/repayment/active/gotoOverdueRepaymentPlan"],
["/repayment/active/goto7DaysRepaymentPlan"]] />

<#macro printRMMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="融资管理" index=index+1 moduleKey=rm_top_menu_key moduleNames=rm_names moduleHosts=rm_hosts modulePaths=rm_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 权限管理 -->
<#assign pm_top_menu_key = "PM"/>
<#assign pm_names = ["企业角色", "企业列表", "员工角色", "员工列表"] />
<#assign pm_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
<#assign pm_paths = [["/role/gotoCompanyRoleList"],
["/customer/list"],
["/role/gotoEmployeeRoleList"],
["/employee/list"]] />

<#macro printPMMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="权限管理" index=index+1 moduleKey=pm_top_menu_key moduleNames=pm_names moduleHosts=pm_hosts modulePaths=pm_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 企业 -->
<#assign pc_top_menu_key="PC"/>
<#assign pc_names=["企业信息"]/>
<#assign pc_hosts=[vbamWebServer]/>
<#assign pc_paths=[["/user/company"]]/>

<#macro printPCMenu curTopMenu curSubMenu onlyShowMainMenu>
	<@printMenu name="企业信息" index=index+1 moduleKey=pc_top_menu_key moduleNames=pc_names moduleHosts=pc_hosts modulePaths=pc_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 我的申请 -->
<#assign ma_top_menu_key = "MA"/>
<#assign ma_names = ["我的申请"] />
<#assign ma_hosts = [vbamWebServer] />
<#assign ma_paths = [["/account/audit/applys"]] />

<#macro printMAMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="我的申请" index=index+1 moduleKey=ma_top_menu_key moduleNames=ma_names moduleHosts=ma_hosts modulePaths=ma_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 真旅项目 -->
<#assign tz_top_menu_key = "TZ"/>
<#assign tz_names = ["白名单列表","审核列表","准入规则","额度列表","交易明细","监控"] />
<#assign tz_hosts = [vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer] />
<#assign tz_paths = [["/credit/white/list"],["/credit/audit/list"],["/credit/audit/accessRule"],["/credit/limit/customerList"],["/credit/trade/loan/details","/credit/trade/refund/details","/credit/transfer/payment","/credit/transfer/refund"],["/report/monitor/list"]] />

<#macro printTZMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="真旅项目" index=index+1 moduleKey=tz_top_menu_key moduleNames=tz_names moduleHosts=tz_hosts modulePaths=tz_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 报表 -->
<#assign rp_top_menu_key = "SR"/>
<#assign rp_names = ["融资日报表", "融资汇总表", "融资计数表", "资金日报表", "资金汇总表", "资金计数表"] />
<#assign rp_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
<#assign rp_paths = [["/report/repayment/repaymentDailyReportPage"],
["/report/repayment/repaymentAnnualReportPage"],
["/report/repayment/repaymentCountReportPage"],
["/report/account/fundDailyReport"],
["/report/account/fundYearReport"],
["/report/account/fundDailyCountReport"]] />

<#macro printRPMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="统计报表" index=index+1 moduleKey=rp_top_menu_key moduleNames=rp_names moduleHosts=rp_hosts modulePaths=rp_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#macro printMenu name index moduleKey moduleNames moduleHosts modulePaths curTopMenu curSubMenu onlyShowMainMenu>
    <#assign firstAuthorizedPage = ""/>
    <#assign firstAuthorizedPageSetted = false/>
    <#assign firstAuthorizedSubModuleIndex = 0/>
    <#assign firstAuthorizedPageIndex = 0/>
    <#if authorizeKey(moduleKey)>
	    <#list modulePaths as mp>
	        <#list mp as p>
	            <#if authorizeUrl(p?replace("^.*?\\|","","r"))>
	                <#assign firstAuthorizedPage = p/>
	                <#assign firstAuthorizedPageIndex = p_index/>
	                <#assign firstAuthorizedPageSetted = true/>
	                <#break/>
	            </#if>
	        </#list>
	        <#if firstAuthorizedPageSetted>
	            <#assign firstAuthorizedSubModuleIndex = mp_index/>
	            <#break/>
	        </#if>
	    </#list>
    </#if>
    <#if firstAuthorizedPageSetted>
    <li id="top_menu_${index}" class="first-level-nav <#if curTopMenu == name>currentStairMenu</#if>">
        <a href="${printLink(moduleHosts[firstAuthorizedSubModuleIndex], modulePaths[firstAuthorizedSubModuleIndex][firstAuthorizedPageIndex])}">${name}</a>
        <#if !onlyShowMainMenu>
        	<div class="second-level-nav" style="display: <#if curTopMenu == name>block<#else>none</#if>;">
            	<ul id="top_menu_${index}_submenu" class="secondLevelList clearfix">
                    <#assign submenuIndex = 1/>
                    <#list modulePaths as mp>
                        <#if (mp_index >= firstAuthorizedSubModuleIndex)>
                            <#list mp as p>
                                <#if authorizeUrl(p)>
                                    <li id="top_menu_${index}_submenu_${submenuIndex}" <#if curSubMenu==moduleNames[mp_index]>class="currentSecondLevelMenu"</#if>>
                                        <a href="${printLink(moduleHosts[mp_index], p)}" >${moduleNames[mp_index]}</a>
                                    </li>
                                    <#assign submenuIndex = submenuIndex + 1/>
                                    <#break/>
                                </#if>
                            </#list>
                        </#if>
                    </#list>
                </ul>
        	</div>
        </#if>
    </li>
    </#if>
</#macro>

<#function printLink host path>
    <#if path?lower_case?starts_with("http:")>
        <#return path/>
    <#elseif host?ends_with("/") && path?starts_with("/")>
        <#return host + path?substring(1)/>
    <#else>
        <#return host + path/>
    </#if>
</#function>
