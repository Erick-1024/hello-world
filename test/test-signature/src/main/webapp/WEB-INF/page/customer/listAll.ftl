<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="全部列表" jsFiles=["page/user/customerListAll.js","common/dateutil.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = "用户管理" curSubMenu = "全部列表" removeExtHeader = false removeExtFooter = false>
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
                            <th>审核状态</th>
                            <td>
                                <div class="radioContent" style="padding:5px 0;"  id="accountAuditStatus">
                                    <label class="radio active" name="ALL">  
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">全部</span>
                                    </label>
                                    <label class="radio" name="PENDINGAUDIT">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">待审核</span>
                                    </label>
                                    <label class="radio" name="HAVINGAUDIT">
                                        <span class="radioIcon"></span>
                                        <span class="labelFonts">已审核</span>
                                    </label>
                                </div>
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
                            <col width="250">
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
                            <col width="250">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>企业名称</th>
                            <td>
                                <input type="text" name="companyName" id="companyName" style="width: 200px;">
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

<script>
	var curSubMenu = "全部列表"
	var detailAuth = ${authorizeKey("UM_TOTAL_DETAIL")?string("true", "false")};
	var reviewAuth = ${authorizeKey("UM_TOTAL_AUDIT")?string("true", "false")};
</script>
</@hb.htmlBase>
