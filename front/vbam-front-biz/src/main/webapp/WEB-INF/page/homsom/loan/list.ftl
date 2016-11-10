<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="放款审核" jsFiles=["page/homsom/loan/list.js", "common/cana.util.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "${title}" curSubMenu = "放款审核" removeExtHeader = false removeExtFooter = false>

<div class="main-container" style="padding:15px;">
    <div class="">
        <div class="first-title">查询条件</div>
        <table class="client-tb" style="padding:10px 0 15px 0;border-bottom:3px solid #F1F1F1;">
            <colgroup>
                <col width="100">
                <col width="520">
                <col width="150">
                <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>放款日期</th>
                <td>
                    <input type="text" style="width:180px;" id="startDate" class="datepicker startDate hasIcon">
                    <em> - </em>
                    <input type="text" style="width:180px;" id="endDate" class="datepicker endDate hasIcon">
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>状态</th>
                <td>
                    <div class="hs-all-in">全部</div>
                    <#list loanStates as loanState>
                    	<div class="hs-list" enum="${loanState}">${loanState.desc()}</div>
                    </#list>
                </td>
                <td><a id="search" href="javaScript:void(0);" class="form-search-btn"><i class="searchIcon"></i>搜索</a></td>
                <#if authorizeKey("HS_LOAN_AUDIT_EXPORT")>
                	<td><a href="javaScript:void(0);" class="form-search-btn" id="exportExcel" style="float:right;">导出</a></td>
                </#if>
            </tr>
            </tbody>
        </table>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
<!--弹窗-->
<script id="loan_detail_tipBox_template" type="text/x-kendo-template">
    <div id="loan_detail_tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <table class="client-tb">
                <colgroup>
                    <col width="100">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>放款日期</th>
                    <td id="loanDate"></td>
                </tr>
                </tbody>
            </table>
            <div class="monitor-grid" id="detail-grid" style="margin-top:0;"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>

<!--权限配置-->
<script>
	var hs_loan_audit_detail = ${authorizeKey("HS_LOAN_AUDIT_DETAIL")?string("true","false")};
	var hs_loan_audit_loan = ${authorizeKey("HS_LOAN_AUDIT_LOAN")?string("true","false")};
	var channel = "${channel}";
</script>

<#include '../../tipBoxTemplate.ftl'/>
<#include '../../confirmBoxTemplate.ftl'/>

</@hb.htmlBase>