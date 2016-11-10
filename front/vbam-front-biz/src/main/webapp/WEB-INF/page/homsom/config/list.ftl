<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="保理业务配置" jsFiles=["page/homsom/config/list.js","common/formValidator.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "恒顺国旅项目" curSubMenu = "保理业务配置" removeExtHeader = false removeExtFooter = false>
<div class="main-container" style="padding:15px;">
    <div style="width:1100px;margin:0 auto;">
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
<!--弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <form id="deployForm">
        <div class="dlg-del-row">
            <table class="client-tb">
                <colgroup>
                    <col width="30%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易对手名称<input type="hidden" style="width:160px;" name="counterpartyId"></th>
                    <td name="counterparty"></td>
                </tr>
                <tr>
                    <th>贷款期限（天）</th>
                    <td><input type="text" style="width:160px;" name="days" validationMessage="请输入贷款期限" required></td>
                </tr>
                <tr>
                    <th>回购期限（天）</th>
                    <td><input type="text" style="width:160px;" name="daysTwo" validationMessage="请输入回购期限" required></td>
                </tr>
                <tr>
                    <th>还款方式</th>
                    <td>
                        <select style="width:242px;" data-role="dropdownlist" name="repayment" class="repayment">
                        	<#list repaymentTypes as repaymentType>
		                        <option value="${repaymentType.name()}">${repaymentType.desc()}</option>
                        	</#list>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="deployForm-btn" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
        </form>
    </div>
</script>
</@hb.htmlBase>