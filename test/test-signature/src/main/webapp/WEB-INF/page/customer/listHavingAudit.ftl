<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="已审核列表" jsFiles=["page/user/customerListHavingAudit.js","common/dateutil.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = "用户管理" curSubMenu = "已审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="300">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>用 户 名</th>
                            <td>
                                <input type="text" name="username" id="username" style="width: 264px;">
                            </td>
                        </tr>
                        <tr>
                            <th>申请时间</th>
                            <td>
                                <input type="text" name="beginTime" id="beginTime" class="datepicker startDate hasIcon" readonly>
                                <em> 至 </em>
                                <input type="text" name="endTime" id="endTime" class="datepicker endDate hasIcon" readonly>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                	<table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>客户类型</th>
                            <td>
                                <select class="selectWrap" name="userType" id="userType" data-role="dropdownlist">
                                    <option value="ALL">全部</option>
                                    <#list userTypes as userType>
                                    	<#if userType.name()!="CANA">
											<option value=${userType.name()}>${userType.desc()}</option>
										</#if>
									</#list>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>审核结果</th>
                            <td>
                                <div class="radioContent" style="padding:5px 0;" id="accountAuditResult">
                                    <label class="radio active" name="ALL">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">全部</span>
                                    </label>
                                    <label class="radio" name="PASSAUDIT">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">通过</span>
                                    </label>
                                    <label class="radio" name="REJECTED">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">未通过</span>
                                    </label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                	<table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>企业名称</th>
                            <td>
                                <input type="text" name="companyName" id="companyName">
                            </td>
                        </tr>
                        <tr>
                            <th>账号状态</th>
                            <td>
                                <div class="radioContent" style="padding:5px 0;" id="accountActivateStatus">
                                    <label class="radio active" name="ALL">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">全部</span>
                                    </label>
                                    <label class="radio" name="ACTIVATED">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">激活</span>
                                    </label>
                                    <label class="radio" name="UNACTIVATE">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">未激活</span>
                                    </label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link comAcu-search"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
       	<div id="companyAcutGridWrap">
	        <div class="companyAcutGrid"></div>
        </div>
    </section>
</div>

<!--重发邮件弹窗模板-->
<script id="template-resend" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">确认要执行操作吗？</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="confirmResend" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

</@hb.htmlBase>
<script>
	var curSubMenu = "已审核列表"
	var detailAuth = ${authorizeKey("UM_AUDITED_DETAIL")?string("true", "false")};
	var resendAuth = ${authorizeKey("UM_AUDITED_RETRY_MAIL")?string("true", "false")};
</script>