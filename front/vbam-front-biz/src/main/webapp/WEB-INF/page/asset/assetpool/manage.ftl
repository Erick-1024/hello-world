<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="资产池管理" jsFiles=["common/dateutil.js","common/cana.util.js","page/asset/assetpool/manage.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "资产池管理">
<div class="main-container">
    <section class="whiteBg">
	<div class="pro-box-bg">
		<input type="hidden" id = "specialProgramId" value = ${assetpoolListDTO.id!} />
		<input type="hidden" id = "status" value = ${assetpoolListDTO.status!} />
		<input type="hidden" id = "cyclePurchaseStructure" value = ${assetpoolListDTO.cyclePurchaseStructure?string('true','false')} />
		<#include "specialprogram.ftl">
	    <div style="height:45px;border-top:1px solid #dadada;margin-top:10px;padding-top:10px;">
	        <a class="form-search-btn" name="assetpoolEnter" href="javascript:void(0);" style="float:right;display:none;">入池</a>
	    </div>
	    <div class="monitor-grid" id="monitorList-grid"></div>
	    <a href="javascript:void(0);" class="open-history-btn" style="display:none;"></a>
	    <a href="javascript:void(0);" class="open-paid-btn" style="display:none;"></a>
	    <a class="open-message-btn" href="javascript:void(0);" style="display:none;"></a>
	 </div>
  </section>
</div>
<!--还款计划弹窗-->
<script id="template-paid" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <div class="monitor-grid" id="paid-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>

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
<!--提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<#include '../../confirmBoxTemplate.ftl'/>

<!--权限配置-->
<script>
	var enterAuth = ${authorizeKey("SIM_PM_ENTER")?string("true","false")};
	var redeemAuth = ${authorizeKey("SIM_PM_REDEEM")?string("true","false")};
	var keepAuth = ${authorizeKey("SIM_PM_KEEP")?string("true","false")};
	var outAuth = ${authorizeKey("SIM_PM_OUT")?string("true","false")};
	var paidAuth = ${authorizeKey("SIM_PM_PAID")?string("true","false")};
	var historyAuth = ${authorizeKey("SIM_PM_HISTORY")?string("true","false")};
</script>
</@hb.htmlBase>