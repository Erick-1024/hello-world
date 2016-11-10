<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="审核列表" jsFiles=["page/credit/audit/list.js", "common/dateutil.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="450">
                    <col width="80">
                    <col width="300">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                	<th style="text-align:right;">客户名称</th>
                    <td>
                        <input id="customerName" type="text" style="width:180px;">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">申请时间</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly="">
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly="">
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">客户类型</th>
                    <td>
                        <a class="status-normal status-chk applicant-type" href="javascript:void(0);">全部</a>
                        <#if applicantTypes??>
	                        <#list applicantTypes as applicantType>
	                        	<a class="status-normal status-default applicant-type" href="javascript:void(0);" value="${applicantType.name()}">${applicantType.desc()}</a>
	                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">客户属性</th>
                    <td>
                        <a class="status-normal status-chk in-whitelist" href="javascript:void(0);">全部</a>
                        <a class="status-normal status-default in-whitelist" href="javascript:void(0);" value="true">白名单</a>
                        <a class="status-normal status-default in-whitelist" href="javascript:void(0);" value="false">非白名单</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">审核状态</th>
                    <td  id="audit">
                        <a class="status-normal status-chk audit-status" id="all" href="javascript:void(0);">全部</a>
                        <#if auditStateList??>
	                        <#list auditStateList as auditState>
	                        	<a class="status-normal status-default audit-status" <#if auditState.name()=="WAIT_APPROVE">id="wait-approve"</#if> href="javascript:void(0);" value="${auditState.name()}">${auditState.desc()}</a>
	                        </#list>
                        </#if>
                    </td>
                </tr>
                 <tr>
                    <th style="text-align:right;">准入状态</th>
                    <td>
                        <a class="status-normal status-chk automatic-status" href="javascript:void(0);">全部</a>
                        <#if automaticStateList??>
	                        <#list automaticStateList as automaticState>
	                        	<a class="status-normal status-default automatic-status" href="javascript:void(0);" value="${automaticState.name()}">${automaticState.desc()}</a>
	                        </#list>
                        </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid k-grid k-widget" id="manualChk-grid" data-role="grid"></div>
     </section>
</div>

<script>
	var detailAuth = ${authorizeKey("TZ_CREDIT_AUDIT_DETAIL")?string("true", "false")};
	var auditAuth = ${authorizeKey("TZ_CREDIT_AUDIT_AUDIT")?string("true", "false")};
	var approveAuth = ${authorizeKey("TZ_CREDIT_AUDIT_APPROVE")?string("true", "false")};
</script>
</@hb.htmlBase>
