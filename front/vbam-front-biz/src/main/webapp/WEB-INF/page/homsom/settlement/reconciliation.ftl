<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="还款核销" jsFiles=["page/homsom/settlement/reconciliation.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "恒顺国旅项目" curSubMenu = "核销对账" removeExtHeader = false removeExtFooter = false>
<div class="main-container" style="padding:15px;">
    <div class="">
        <div class="first-title">核销信息</div>
        <table class="client-tb" style="padding:10px 0 15px 0;border-bottom:3px solid #F1F1F1;">
            <colgroup>
                <col width="120">
                <col width="170">
                <col width="120">
                <col width="170">
                <col width="120">
                <col width="170">
                <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>核销日期</th>
                <td name="settlementDate">${summaryData.date!''}</td>
                <th>应核销总金额</th>
                <td>${summaryData.settleAmount!''}</td>
                <th>实际核销总金额</th>
                <td>${summaryData.actualSettleAmount!''}</td>
                <td></td>
            </tr>
            <tr>
                <th>应归还总金额</th>
                <td>${summaryData.refundAmount!''}</td>
                <th>应回购总金额</th>
                <td>${summaryData.buybackAmount!''}</td>
                <th></th>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
        <div style="height:40px;padding-top:10px;"><a href="${basepath}/homsom/settlement/export/settlement/${channel}" style="float:right;" class="form-search-btn">对账单</a></div>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a href="javaScript:void(0);" class="openOnePop" style="display:none;"></a>
        <a href="javaScript:void(0);" class="openTwoPop" style="display:none;"></a>
        <a href="javaScript:void(0);" class="openThreePop" style="display:none;"></a>
    </div>
</div>
<!--弹窗1-->
<script id="tipBox_Pop" type="text/x-kendo-template">
    <div class="dlg-wrapper">
    	<form id="actualMoneyInputForm">
	        <div class="dlg-del-row" style="margin-bottom: 0;">
	            <table class="client-tb">
	                <colgroup>
	                    <col width="220">
	                    <col width="">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th>交易对手</th>
	                    <td name='settlementCounterpartyNameTD1'></td>
	                </tr>
	                <tr>
	                    <th>到账总金额</th>
	                    <td>
	                    	<input class="moneyNum" type="text" style="width:180px;" name="actualSettlementAmount" validationMessage="请输到账总金额" required>
	                    	<input type="hidden" name="settlementCounterpartyName">
	                    	<input type="hidden" name="settlementCounterpartyId">
	                    	<input type="hidden" name="counterpartyId">
	                    	<input type="hidden" name="settlementAmount">
	                    	<input type="hidden" name="settlementDate">
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="dlg-del-row">
	            <a class="default-link confirm-link go-next" href="javascript:void(0);">提交</a>
	            <a class="default-link back-link" href="javascript:void(0);">返回</a>
	        </div>
        </form>
    </div>
</script>
<!--弹窗2-->
<script id="tipBox_repo" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row" style="margin-bottom: 0;">
            <table class="client-tb">
                <colgroup>
                    <col width="120">
                    <col width="160">
                    <col width="120">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易对手</th>
                    <td name="settlementCounterpartyName"></td>
                    <th></th>
                    <td><input type="hidden" name="settlementCounterpartyIdSubmit"><input type="hidden" name="counterpartyIdSubmit"></td>
                </tr>
                <tr>
                    <th>核销日期</th>
                    <td name="settlementDateTD"></td>
                    <th>放款总金额</th>
                    <td name="loanTotalAmount">0.00</td>
                </tr>
                <tr>
                    <th>应核销总金额</th>
                    <td name="settlementAmount"></td>
                    <th>实际核销总金额</th>
                    <td name="actualSettlementAmount"></td>
                </tr>
                <tr>
                    <th>应归还总金额</th>
                    <td name="refundAmount">0.00</td>
                    <th>应回购总金额</th>
                    <td name="buybackAmount">0.00</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="grid-repo" style="margin-top: 0;"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link confirm-settlement" href="javascript:void(0);">提交</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--弹窗3-->
<script id="tipBox_back" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row" style="margin-bottom: 0;">
            <table class="client-tb">
                <colgroup>
                    <col width="120">
                    <col width="160">
                    <col width="120">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>交易对手</th>
                    <td name="settlementCounterpartyNameTD"></td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th>核销日期</th>
                    <td name="settlementDateTD2"></td>
                    <th>放款总金额</th>
                    <td name="loanTotalAmountTD"></td>
                </tr>
                <tr>
                    <th>应核销总金额</th>
                    <td name="settlementAmountTD"></td>
                    <th>实际核销总金额</th>
                    <td name="actualSettlementAmountTD"></td>
                </tr>
                <tr>
                    <th>应归还总金额</th>
                    <td name="refundAmountTD"></td>
                    <th>应回购总金额</th>
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
</@hb.htmlBase>