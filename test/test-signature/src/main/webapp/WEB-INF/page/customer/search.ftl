<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="企业账号列表" jsFiles=["page/user/customer_search.js","page/user/dateutil.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = "用户管理" curSubMenu = "企业审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="80">
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
                            <th>联系电话</th>
                            <td>
                                <input type="text" name="contactTel" id="contactTel">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="80">
                            <col width="390">
						</colgroup>
                        <tbody>
                        <tr>
                            <th>用 户 名</th>
                            <td>
                                <input type="text" name="username" id="username" style="width: 160px;">
                            </td>
                        </tr>
                        <tr>
                            <th>提交日期</th>
                            <td>
                                <input type="text" name="beginTime" id="beginTime" style="width: 160px;" class="datepicker startDate hasIcon">
                                <em> 至 </em>
                                <input type="text" name="endTime" id="endTime" style="width: 160px;" class="datepicker endDate hasIcon">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="80">
                            <col width="240">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>审批状态</th>
                            <td>
                                <select name="accountProcessStatus" id="accountProcessStatus"　class="selectWrap" data-role="dropdownlist">
                                    	<option value="ALL">所有状态</option>
                                    <#list accountProcessStatus as processStatus>
										<option value=${processStatus.name()}>${processStatus.desc()}</option>
									</#list>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>账号状态</th>
                            <td>
                                <select name="accountActivateStatus" id="accountActivateStatus"　class="selectWrap" data-role="dropdownlist">
                                		<option value="ALL">所有状态</option>
                                	<#list accountActivateStatus as activateStatus>
										<option value=${activateStatus.name()}>${activateStatus.desc()}</option>
									</#list>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
        </form>
        <div class="companyAcutGrid"></div>
    </section>
</div>
</@hb.htmlBase>
