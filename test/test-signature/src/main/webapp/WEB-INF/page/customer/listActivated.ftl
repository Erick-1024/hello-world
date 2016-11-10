<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="正式用户列表" jsFiles=["page/user/customerListActivated.js","common/dateutil.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = "用户管理" curSubMenu = "正式用户列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="200">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>用 户 名</th>
                            <td>
                                <input type="text" name="username" id="username" style="width: 160px;">
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
                                <select class="selectWrap"  name="userType" id="userType" data-role="dropdownlist">
                                	<option value="ALL">全部</option>
                                    <#list userTypes as userType>
                                    	<#if userType.name()!="CANA">
											<option value=${userType.name()}>${userType.desc()}</option>
										</#if>
									</#list>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="200">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>企业名称</th>
                            <td>
                                <input type="text" name="companyName" id="companyName" style="width: 160px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="390">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>申请时间</th>
                            <td>
                                <input type="text" name="beginTime" id="beginTime" style="width: 160px;" class="datepicker startDate hasIcon" readonly>
                                <em> 至 </em>
                                <input type="text" name="endTime" id="endTime" style="width: 160px;" class="datepicker endDate hasIcon" readonly>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li style="margin-top:8px;">
                    <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <div id="companyAcutGridWrap">
	        <div class="companyAcutGrid"></div>
        </div>
    </section>
</div>

<!--重置密码弹窗模板-->
<script id="template-reset" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right">
			            <span class="notice-content"></span>确认要重置登录密码吗？<br/>
			            <span class="notice-content" name="customerName"></span>
                    </td>
                </tr>
	         </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="confirmReset" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

<!--重置支付密码弹窗模板-->
<script id="template-resetPayPassword" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right">
			            <span class="notice-content"></span>确认要重置支付密码吗？<br/>
			            <span class="notice-content" name="customerName"></span>
                    </td>
                </tr>
	         </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="confirmResetPayPassword" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
</@hb.htmlBase>
<script>
	var curSubMenu = "正式用户列表"
	var detailAuth = ${authorizeKey("UM_NORMAL_DETAIL")?string("true", "false")};
	var resetAuth = ${authorizeKey("UM_NORMAL_RESET_PASSWORD")?string("true", "false")};
	var resetPayPasswordAuth = ${authorizeKey("UM_NORMAL_RESET_PAYPASSWORD")?string("true", "false")};
</script>
