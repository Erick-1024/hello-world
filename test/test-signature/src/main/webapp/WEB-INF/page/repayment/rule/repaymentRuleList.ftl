<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划规则列表" jsFiles=["page/repayment/rule/repaymentRuleList.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划规则" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form class="repayRule-form">
            <table class="repayRule-table">
                <colgroup>
                    <col width="70">
                    <col width="220">
                    <col width="70">
                    <col width="300">
                    <col width="150">
                </colgroup>
                <tbody>
                <tr>
                    <th>规则编号</th>
                    <td>
                        <input id="ruleId" name="ruleId" type="text" style="width:180px;">
                    </td>
                    <th>规则范围</th>
                    <td>
                        <select class="selectWrap" id="scopeOfApplication" name="scopeOfApplication" data-role="dropdownlist" style="width:180px">
                            <option value="ALL">所有</option>
                            <#list scopeOfApplications as scopeOfApplication>
                            	<option value=${scopeOfApplication.name()}>${scopeOfApplication.desc()}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <a class="form-search-link" id="search-button"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div style="margin-top:15px;">
            <a class="form-add-link addRule-link" href="javascript:void(0);"><i class="AddIcon"></i>新增</a>
        </div>
        <div id="repayRule-gridWrap">
	        <div class="finance-grid repayRule-grid" id="repayRule-grid"></div>
        </div>
    </section>
</div>
</@hb.htmlBase>