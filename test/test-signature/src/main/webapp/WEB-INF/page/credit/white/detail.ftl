<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="白名单详情" jsFiles=["common/jquery.form.min.js","common/cana.util.js","page/credit/white/detail.js"] cssFiles=["/css/advanced.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "白名单列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
   <section class="whiteBg">
        <form class="advanced-form">
            <table class="advanced-table">
                <colgroup>
                    <col width="70">
                    <col width="120">
                    <col width="90">
                    <col width="200">
                    <col width="90">
                    <col width="200">
                    <col width="150">
                </colgroup>
                <tbody>
                <tr>
                    <th>批次编号</th>
                    <td>${(batchNo)!}<input type="hidden" name="batchNo" value="${(batchNo)!}" /></td>
                    <th>客户编号</th>
                    <td>
                        <input type="text" name="canaId">
                    </td>
                    <th>客户名称</th>
                    <td>
                        <input type="text" name="customerName">
                    </td>
                    <td>
                        <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="advanced-grid" id="advancedRule-grid"></div>
        <div class="advanced-foot">
            <a class="default-link confirm-link" href="javascript:history.back(-1);">返回</a>
        </div>
    </section>
</div>
</@hb.htmlBase>
<!--指标详情弹窗模板-->
<script id="template-advancedRule" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-table">
            <colgroup>
                <col width="50%">
                <col width="50%">
            </colgroup>
            <tbody>
            <tr>
                <td class="dlg-table-til">采购商名称</td>
                <td>#=data.customerName#</td>
            </tr>
            <tr>
                <td class="dlg-table-til">与真旅网合作月份</td>
                <td>#=data.cooperationPeriod#</td>
            </tr>
            <tr>
                <td class="dlg-table-til">订单采购增长率</td>
                <td>#=data.purchaseOrderGrowthRate#</td>
            </tr>
            <tr>
                <td class="dlg-table-til">逾期率</td>
                <td>#=data.overdueRate#</td>
            </tr>
            <tr>
                <td class="dlg-table-til">逾期次数</td>
                <td>#=data.overdueTimes#</td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link close-window" href="javascript:void(0);">关闭</a>
        </div>
    </div>
</script>