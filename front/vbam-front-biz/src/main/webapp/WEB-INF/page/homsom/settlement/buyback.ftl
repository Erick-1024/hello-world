<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="回购核销" jsFiles=["page/homsom/settlement/buyback.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "恒顺国旅项目" curSubMenu = "回购核销" removeExtHeader = false removeExtFooter = false>
<div class="main-container" style="padding:15px;">
    <div class="">
        <div class="first-title">回购信息</div>
        <table class="client-tb" style="padding:10px 0 15px 0;border-bottom:3px solid #F1F1F1;">
            <colgroup>
                <col width="120">
                <col width="220">
                <col width="120">
                <col width="220">
                <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>回购日期</th>
                <td name="buybackDate">${summaryData.date!''}</td>
                <td></td>
                <td></td>
                <th></th>
            </tr>
            <tr>
                <th>应回购总金额</th>
                <td>${summaryData.buybackAmount!''}</td>
                <th>已回购总金额</th>
                <td>${summaryData.actualBuybackAmount!''}</td>
                <td></td>
            </tr>
            </tbody>
        </table>
        <div style="height:40px;padding-top:10px;"><a href="${basepath}/homsom/settlement/export/buyback/${channel}" style="float:right;" class="form-search-btn">对账单</a></div>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a href="javaScript:void(0);" class="openRopePop" style="display:none;"></a>
        <a href="javaScript:void(0);" class="openBackPop" style="display:none;"></a>
        <a href="javaScript:void(0);" class="openExceptionPop" style="display:none;"></a>
    </div>
</div>
<!--弹窗1-->
<script id="tipBox_repo" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row" style="margin-bottom: 0;">
            <table class="client-tb">
                <colgroup>
                    <col width="120">
                    <col width="200">
                    <col width="120">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易对手</th>
                    <td name="buybackCounterpartyName"></td>
                    <td><input type="hidden" name="buybackCounterpartyId"><input type="hidden" name="counterpartyId"></td>
                    <td></td>
                    <th></th>
                </tr>
                <tr>
                    <th>回购日期</th>
                    <td name="buybackDateTD"></td>
                    <th>回购总金额</th>
                    <td name="buybackAmount"></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="grid-repo" style="margin-top: 0;"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link confirm-buyback" href="javascript:void(0);">提交</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--弹窗2-->
<script id="tipBox_back" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row" style="margin-bottom: 0;">
            <table class="client-tb">
                <colgroup>
                    <col width="120">
                    <col width="200">
                    <col width="120">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易对手</th>
                    <td name="buybackCounterpartyNameTD"></td>
                    <td></td>
                    <td></td>
                    <th></th>
                </tr>
                <tr>
                    <th>回购日期</th>
                    <td name="buybackDateTD2"></td>
                    <th>回购总金额</th>
                    <td name="buybackAmountTD"></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="grid-back" style="margin-top: 0;"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--弹窗3-->
<script id="tipBox_exception" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row" style="margin-bottom: 0;">
            <table class="client-tb">
                <colgroup>
                    <col width="20">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <td>客票号：</td>
                    <td name="ticketNoList"></td>
                </tr>
                <tr>
                    <td colspan="2">这些客票已被核销，不能进行回购，请重新确认回购金额。</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="grid-repo" style="margin-top: 0;"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link confirm-after-check" href="javascript:void(0);">提交</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
</@hb.htmlBase>