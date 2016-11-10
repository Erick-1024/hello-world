<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="白名单列表" jsFiles=["common/jquery.form.min.js","common/cana.util.js","page/credit/white/list.js"] cssFiles=["/css/advanced.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "白名单列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
   <section class="whiteBg">
        <form class="advanced-form">
            <table class="advanced-table">
                <colgroup>
                    <col width="70">
                    <col width="220">
                    <col width="70">
                    <col width="350">
                    <col width="150">
                </colgroup>
                <tbody>
                <tr>
                    <th>批次编号</th>
                    <td>
                        <input type="text" name="batchNo">
                    </td>
                    <th>创建时间</th>
                    <td>
                        <input type="text" name="minCreateTime" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" name="maxCreateTime" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <a class="form-search-link"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="advanced-grid" id="advancedList-grid"></div>
    </section>
</div>
<!--系统规则弹窗模板-->
<script id="template-advancedRole" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-table">
            <colgroup>
                <col width="50%">
                <col width="50%">
            </colgroup>
            <tbody>
            <tr>
                <td class="dlg-table-til">批次编号</td>
                <td>#=data.batchNo#</td>
            </tr>
            <tr>
                <td class="dlg-table-til">与真旅网合作月份</td>
                <td>#=data.cooperationPeriod#</td>
            </tr>
            <#--
            <tr>
                <td class="dlg-table-til">订单采购增长率</td>
                <td>#=data.purchaseOrderGrowthRate#</td>
            </tr>
            -->
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
</@hb.htmlBase>
