I<#assign index=0 />

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
<#assign am_names = ["账户列表", "审核列表", "待审核列表", "已审核列表","我的账户","转账申请", "提现申请","开户申请", "代开户申请", "新建监管关系", "审核列表","流水明细" , "打印码"] />
<#assign am_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
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
["/account/trade/listTradeRecord"],
["/account/printcode/printCodeList"]
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
<#assign pm_names = ["企业角色", "企业列表", "员工角色", "员工列表","ABS员工设置"] />
<#assign pm_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer,vbamWebServer] />
<#assign pm_paths = [["/role/gotoCompanyRoleList"],
["/customer/list"],
["/role/gotoEmployeeRoleList"],
["/employee/list"],
["/asset/privilege/customerList"]] />

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
<#assign tz_names = ["白名单列表","审核列表","准入规则","额度列表","交易明细","监控","预警"] />
<#assign tz_hosts = [vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer] />
<#assign tz_paths = [["/credit/white/list"],["/credit/audit/list"],["/credit/audit/accessRule"],["/credit/limit/customerList"],["/credit/trade/loan/details","/credit/trade/refund/details","/credit/transfer/payment","/credit/transfer/refund","/credit/statement"],["/report/monitor/list"],["/earlywarning/customer/list","/earlywarning/customer/information/list","/earlywarning/review/list","/earlywarning/levelchange/list","/earlywarning/event/addList"]] />

<#macro printTZMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="真旅项目" index=index+1 moduleKey=tz_top_menu_key moduleNames=tz_names moduleHosts=tz_hosts modulePaths=tz_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 韵达项目 -->
<#assign yd_top_menu_key = "YD"/>
<#assign yd_names = ["审核列表","授信列表","授信审核","额度列表","身份审核","合同签约情况","用款申请","用款申请流水","线下数据导入","监控","预警"] />
<#assign yd_hosts = [vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer] />
<#assign yd_paths = [["/yundaex/audit/list"],["/yundaex/limit/creditList"],["/yundaex/limit/creditAuditList"],["/yundaex/limit/customerList"],["/yundaex/personal/gotoListPage"],["/yundaex/contract/gotoSituationListPage"],["/yundaex/loan/loanDetail"],["/yundaex/loan/loanFlow"],["/test/custApplyExcel"],["/yundaex/monitor/list"],["/yundaex/earlywarning/postLoanlist","/yundaex/earlywarning/informationList","/yundaex/earlywarning/reviewList","/yundaex/earlywarning/changeList","/yundaex/earlywarning/addList"]] />

<#macro printYDMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="韵达项目" index=index+1 moduleKey=yd_top_menu_key moduleNames=yd_names moduleHosts=yd_hosts modulePaths=yd_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- VJ项目 -->
<#assign vj_top_menu_key = "VJ"/>
<#assign vj_names = ["额度列表","交易明细"] />
<#assign vj_hosts = [vbamWebServer,vbamWebServer] />
<#assign vj_paths = [["/vj/limit/list"],["/vj/tran/loan/detailList","/vj/tran/repayment/detailList","/vj/tran/account/balance"]] />

<#macro printVJMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="VJ项目" index=index+1 moduleKey=vj_top_menu_key moduleNames=vj_names moduleHosts=vj_hosts modulePaths=vj_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 报表 -->
<#assign rp_top_menu_key = "SR"/>
<#assign rp_names = ["融资日报表", "融资汇总表", "融资计数表", "资金日报表", "资金汇总表", "资金计数表", "资金月报表"] />
<#assign rp_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
<#assign rp_paths = [["/report/repayment/repaymentDailyReportPage"],
["/report/repayment/repaymentAnnualReportPage"],
["/report/repayment/repaymentCountReportPage"],
["/report/account/fundDailyReport"],
["/report/account/fundYearReport"],
["/report/account/fundDailyCountReport"],
["/report/account/fundMonthlyReportPage"]] />

<#macro printRPMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="统计报表" index=index+1 moduleKey=rp_top_menu_key moduleNames=rp_names moduleHosts=rp_hosts modulePaths=rp_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 项目管理 -->
<#assign pml_top_menu_key = "ASSET"/>
<#assign pml_names = ["所有列表"] />
<#assign pml_hosts = [vbamWebServer] />
<#assign pml_paths = [["/asset/project/projectList"]]/>

<#macro printPMLMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="项目管理" index=index+1 moduleKey=pml_top_menu_key moduleNames=pml_names moduleHosts=pml_hosts modulePaths=pml_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 基础资产信息 -->
<#assign bai_top_menu_key = "BAI"/>
<#assign bai_names = ["客户信息", "企业信息", "额度管理", "业务管理", "应收账款管理", "融资管理"] />
<#assign bai_hosts = [vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer] />
<#assign bai_paths = [["/asset/customer/customerListPage"],["/asset/enterpriseInfo/listPage"],["/asset/credit/customerList"],["/asset/factorBusiness/goto/list"],["/asset/invoice/invoiceList"],["/asset/loan/goto/loanList"]] />

<#macro printBAIMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="基础资产信息" index=index+1 moduleKey=bai_top_menu_key moduleNames=bai_names moduleHosts=bai_hosts modulePaths=bai_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 证券化发行管理 -->
<#assign sim_top_menu_key = "SIM"/>
<#assign sim_names = ["专项计划管理","档案管理","基础资产管理","资产池管理","发行后管理","管理日志"] />
<#assign sim_hosts = [vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer,vbamWebServer] />
<#assign sim_paths = [["/asset/specialprogram/specialProgram/list"],["/asset/archivesManagement/goto/list"],["/asset/underlyingAsset/underlyingAssetList"],["/asset/pool/assetpoolList"],["/asset/specialprogram/specialProgramIssue/list"],["/asset/log/list"]] />

<#macro printSIMMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="证券化发行管理" index=index+1 moduleKey=sim_top_menu_key moduleNames=sim_names moduleHosts=sim_hosts modulePaths=sim_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- ABS市场研究 -->
<#assign mr_top_menu_key = "MR"/>
<#assign mr_names = ["市场数据导入","市场数据总览"] />
<#assign mr_hosts = [vbamWebServer, vbamWebServer] />
<#assign mr_paths = [["/asset/marketData/gotoImport"],["/asset/marketResearch/marketDataSummary"]] />

<#macro printMRMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="ABS市场研究" index=index+1 moduleKey=mr_top_menu_key moduleNames=mr_names moduleHosts=mr_hosts modulePaths=mr_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 恒顺国旅项目 -->
<#assign hs_top_menu_key = "HS"/>
<#assign hs_names = ["保理业务配置","客票查询","放款审核","还款核销","核销对账","账款回购","回购核销","核销履历"] />
<#assign hs_hosts = [vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer, vbamWebServer] />
<#assign hs_paths = [["/homsom/config/goto/list/HOMSOM"],["/homsom/ticket/goto/list/HOMSOM"],["/homsom/loan/goto/list/HOMSOM"],["/homsom/settlement/goto/settlement/import/HOMSOM"],["/homsom/settlement/goto/reconciliation/HOMSOM"],["/homsom/settlement/goto/buyBack/import/HOMSOM"],["/homsom/settlement/goto/buyback/HOMSOM"],["/homsom/settlement/track/list/HOMSOM"]] />

<#macro printHSMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="恒顺国旅项目" index=index+1 moduleKey=hs_top_menu_key moduleNames=hs_names moduleHosts=hs_hosts modulePaths=hs_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
</#macro>

<#-- 设置 -->
<#assign set_top_menu_key = "SET"/>
<#assign set_names = ["节假日"] />
<#assign set_hosts = [vbamWebServer] />
<#assign set_paths = [["/setting/calendar/list"]] />

<#macro printSETMenu curTopMenu curSubMenu onlyShowMainMenu>
    <@printMenu name="设置" index=index+1 moduleKey=set_top_menu_key moduleNames=set_names moduleHosts=set_hosts modulePaths=set_paths curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
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
