<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="融资列表" jsFiles=["page/asset/loan/loanList.js", "common/cana.util.js", "common/init.js", "common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "融资管理" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <form>
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="250px">
                    <col width="120px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>融资方</th>
                    <td><input type="text" id="customerName" style="width:222px;"></td>
                    <th>业务合同号</th>
                    <td><input type="text" id="businessContractNo" style="width:222px;"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>业务产品</th>
                    <td>
                        <select id="businessProduct" style="width:222px;" data-role="dropdownlist">
                        	<option value="">全部</option>
							<#list BusinessProduct?keys as key>
								<option value="${key}">${BusinessProduct[key]}</option>
							</#list>
                        </select>
                    </td>
                    <th>状态</th>
                    <td>
                        <select id="settleStatus" style="width:222px;" data-role="dropdownlist">
                        	<option value="">全部</option>
                            <#list SettleStatus?keys as key>
                            	<option value="${key}">${SettleStatus[key]}</option>
							</#list>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>放款日</th>
                    <td>
                        <input class="datepicker startDate hasIcon" id="loanStartDate" type="text" style="width:100px;">
                        <em> 至 </em>
                        <input class="datepicker endDate hasIcon" id="loanEndDate" type="text" style="width:100px;">
                    </td>
                    <th>到期日</th>
                    <td>
                        <input class="datepicker startDate hasIcon" id="dueStartDate" type="text" style="width:100px;">
                        <em> 至 </em>
                        <input class="datepicker endDate hasIcon" id="dueEndDate" type="text" style="width:100px;">
                    </td>
                    <td style="padding-left:50px;">
                        <a class="form-search-btn" id="search" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a>
                        <#if authorizeKey("BAI_LM_RP_IMPEXCEL")>
                        	<a class="form-search-btn limit-add" id='planImport' href="javascript:void(0);" style="float:right;">还款计划导入</a>
                        </#if>
                        <#if authorizeKey("BAI_LM_LI_MANNUAL") && authorizeKey("BAI_LM_LI_IMPEXCEL")>
                        	<a class="form-search-btn limit-add" id="import" href="javascript:void(0);" style="float:right;">放款申请</a>
                        <#elseif authorizeKey("BAI_LM_LI_MANNUAL")>
                        	<a class="form-search-btn limit-add" href="${basepath!}/asset/loan/createAssetLoan" style="float:right;">放款申请</a>
                        <#elseif authorizeKey("BAI_LM_LI_IMPEXCEL")>
                        	<a class="form-search-btn limit-add" href="${basepath!}/asset/loan/gotoLoanInfoImport" style="float:right;">放款申请</a>
                        </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <a href="javascript:void(0);" class="open-history-btn" style="display:none;"></a>
        <span id="deleteId">
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>
<!--历史明细弹窗-->
<script id="template-history" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="cost-form">
            <div class="dlg-del-row">
                <table class="client-tb">
                    <colgroup>
                        <col width="100px">
                        <col width="130px">
                        <col width="100px">
                        <col width="160px">
                        <col width="100px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>放款编号</th>
                        <td id="popWindowLoanId"></td>
                        <th>融资客户</th>
                        <td id="popWindowCustomerName"></td>
                        <th>交易对手</th>
                        <td id="popWindowCounterparty"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="dlg-del-row">
                <div class="monitor-grid" id="history-grid"></div>
            </div>
            <div class="dlg-del-row">
                <a class="default-link back-link" href="javascript:void(0);">返回</a>
            </div>
        </form>
    </div>
</script>

<!--选择放款信息录入方式-->
<script id="template-import" type="text/x-kendo-template">
    <div>
        <table class="client-tb">
            <colgroup>
                <col width="35%">
                <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>录入方式</th>
                <td class="radioContent busInfo-look">
                    <label id="excelImport" class="radio" style="margin-right:30px;">
                        <span class="radioIcon"></span>
                        <span class="labelFonts">文件导入</span>
                    </label>
                    <label class="radio active">
                        <span class="radioIcon"></span>
                        <span class="labelFonts">手工输入</span>
                    </label>
                </td>
            </tr>
            <tr>
                <th></th>
                <td><a class="default-link confirm-link" id="entryCreateLoanPageButton" href="javascript:void(0);">下一步</a></td>
            </tr>
            </tbody>
        </table>
    </div>
</script>

<#include '../../tipBoxTemplate.ftl'/>
<#include '../../confirmBoxTemplate.ftl'/>

<!--权限配置-->
<script>
	var asset_loan_detail_auth = ${authorizeKey("BAI_LM_DETAIL")?string("true","false")};
	var asset_loan_modify_auth = ${authorizeKey("BAI_LM_MODIFY")?string("true","false")};
	var asset_loan_delete_auth = ${authorizeKey("BAI_LM_DELETE")?string("true","false")};
	var asset_loan_paid_history_auth = ${authorizeKey("BAI_LM_PAID_HISTORY")?string("true","false")};
	var asset_loan_paid_auth = ${authorizeKey("BAI_LM_PAID")?string("true","false")};
</script>
</@hb.htmlBase>