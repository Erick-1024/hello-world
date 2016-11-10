<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="资金月报表" 
			  jsFiles=["page/report/account/fundMonthlyReport.js","common/formValidator.js","js/common/kendo.culture.zh-CN.min.js"] 
			  cssFiles=["css/reports.css"] 
              localCssFiles=[] 
              curTopMenu = "统计报表" curSubMenu = "资金月报表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <form class="reprots-form">
            <table class="reprots-table" style="width:100%;">
                <colgroup>
                    <col width="100">
                    <col width="200">
                    <col width="100">
                    <col width="200">
                    <col width="100">
                    <col width="200">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">银行账号</th>
                    <td><input type="text" style="width:180px;" name="accountNo" class="bankCard"></td>
                    <th style="text-align: right;">融资客户</th>
                    <td><input type="text" style="width:180px;" name="financeName"></td>
                    <th style="text-align: right;">账户名称</th>
                    <td><input type="text" style="width:180px;" name="accountName"></td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">保理商</th>
                    <td><input type="text" style="width:180px;" name="factorName"></td>
                    <th style="text-align: right;">账户类型</th>
                    <td>
                        <select class="" style="width:180px;"  data-role="dropdownlist" name="accountType">
                        	<option value="ALL">所有</option>
                        	<#list accountTypeList as accountType>
	                            <option value="${accountType.name()}">${accountType.desc()}</option>
                        	</#list>
                        </select>
                    </td>
                    <th style="text-align: right;">监管类型</th>
                    <td>
                        <select class="" style="width:180px;"  data-role="dropdownlist" name="supervisionStatus">
                        	<option value="ALL">所有</option>
                            <#list accountSupervisionStatusList as accountSupervisionStatus>
	                            <option value="${accountSupervisionStatus.name()}">${accountSupervisionStatus.desc()}</option>
                        	</#list>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">回款账户</th>
                    <td>
                        <select class="" style="width:180px;"  data-role="dropdownlist" name="transferInAccount">
                        	<option value="ALL">所有</option>
                            <option value="true">是</option>
                            <option value="false">否</option>
                        </select>
                    </td>
                    <th style="text-align: right;">账户状态</th>
                    <td>
                        <select class="" style="width:180px;"  data-role="dropdownlist" name="accountStatus">
                        	<option value="ALL">所有</option>
                            <#list accountStatusList as accountStatus>
	                            <option value="${accountStatus.name()}">${accountStatus.desc()}</option>
                        	</#list>
                        </select>
                        </select>
                    </td>
                    <th style="text-align: right;">获取结果</th>
                    <td>
                        <select class="" style="width:180px;"  data-role="dropdownlist" name="fundBalanceGetState">
                            <option value="ALL">所有</option>
                            <#list fundBalanceGetStateList as fundBalanceGetState>
	                            <option value="${fundBalanceGetState.name()}">${fundBalanceGetState.desc()}</option>
                        	</#list>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">日期</th>
                    <td><input type="text" class="" id="monthpicker" value="${lastMonthDate!''}" style="width:180px;" name="reportDate"></td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td><a class="form-search-link" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>查询</a></td>
                    <#if authorizeKey("SR_FUND_MONTHLY_REPORT_EXPORT") >
                    	<td><a class="reportsBtn-link export-btn" href="javascript:void(0);" style="float:right;">导出</a></td>
                    </#if>
                </tr>
                </tbody>
            </table>

        </form>
        <div class="reprots-grid" id="reprots-dayGrid" style="margin-top:15px;"></div>
        <div style="border:1px solid #eaeaea;border-top:none;height:40px;line-height: 40px;">
            <table style="width:600px;">
                <colgroup>
                    <col width="100">
                    <col width="200">
                    <col width="100">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <td style="color:#666;text-align: right;">主账户余额：</td>
                    <td id="mainAccountBalance" style="color:#000;">${mainAccountBalance!''}</td>
                    <td style="color:#666;text-align: right;">账户余额总额：</td>
                    <td id="accountBalanceSum" style="color:#000;"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@hb.htmlBase>