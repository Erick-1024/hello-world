<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/credit/CreditToolsBar.ftl" as ctb/>
<@hb.htmlBase title="退款流水" jsFiles=["page/credit/trade/refundFlow.js", "common/dateutil.js","page/credit/trade/tradeCommon.js","facade/formValidatorRules.js","common/formValidator.js"] cssFiles=["css/monitor.css","css/popup.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "transferRefund"/>
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="140">
                    <col width="300">
                    <col width="300">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align:right;">CANA放款平台流水号</th>
                    <td>
                        <input type="text" style="width:120px;" class="summaryId">
                    </td>
                    <td></td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">交易日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <td>
                        <span style="margin-right:20px;">最近</span>
                        <a class="status-normal status-default last-data" href="javascript:void(0);" value="oneWeek">1周</a>
                        <a class="status-normal status-default last-data" href="javascript:void(0);" value="oneMonth">1个月</a>
                        <a class="status-normal status-default last-data" href="javascript:void(0);" value="threeMonth">3个月</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">退款方名称</th>
                    <td>
                        <a class="status-normal status-chk refund" href="javascript:void(0);" value="">全部</a>
                        <a class="status-normal status-default refund" href="javascript:void(0);" value="REFUND2FACTOR">真旅网</a>
                        <a class="status-normal status-default refund" href="javascript:void(0);" value="REFUND2CUSTOMER">资金方</a>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align:right;">交易状态</th>
                    <td>
                        <a class="status-normal status-chk trade-status" href="javascript:void(0);" value="">全部</a>
                        <a class="status-normal status-default trade-status" href="javascript:void(0);" value="SUCCESS">成功</a>
                        <a class="status-normal status-default trade-status" href="javascript:void(0);" value="FAIL">失败</a>
                        <a class="status-normal status-default trade-status" href="javascript:void(0);" value="HANDING">交易中</a>
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorTrade-grid"></div>
    </section>
</div>

<#include "../tipBoxTemplate.ftl" />

<script>
var userRoot=${authorizeKey("TZ_MANUAL_OPERATE")?string("true", "false")};
</script>

<!--消息提示框-->
<div class="popup-box" style="display: none;">
    <div class="popup-out-ch"></div>
    <div class="popup-in-ch">
        <!--提示标题-->
        <div class="popup-header">提示标题</div>
        <div class="popup-list-box">
            <div class="popup-content">
                <!--<i class="popup-img notice-icon01"></i><span class="pop-con">提示内容</span>-->
                <i class="popup-img" style="display:none;"></i><span class="pop-con">提示内容</span>
            </div>
            <div class="popup-foot">
                <a class="popup-btn" href="javascript:void(0);" onclick="closePop();">返回</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function openPop(title,content){
    $(".popup-header").html(title);
    $(".pop-con").html(content);
//判断数据已确定显示的图标
//  $(".popup-img").addClass("notice-icon01").show();
    $(".popup-box").show();
}
function closePop(){
    $(".popup-box").hide();
    $(".popup-header").html("");
    $(".pop-con").html("");
    $(".popup-img").removeClass("notice-icon01").hide();
}

</script>

<!--遮罩层-->
<div class="window-overlay hidden"></div>
<!--新增录入信息弹窗模板-->
<div class="template-limitAct hidden" id="template-fundDetail" style="width:600px;margin-top: -200px;">
    <div class="manualAdd-head">
        <span>还款计划信息</span>
        <div class="manual-closeBar">
            <a class="closeHref planCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
        <table class="relieve-table">
            <colgroup>
                <col width="40%">
                <col>
            </colgroup>
            <tbody>
            <tr>
                <th>退款账户</th>
                <td class="fromAccountNo">--</td>
            </tr>
            <tr>
                <th>退款账户名</th>
                <td class="fromAccountName">上海凯拿资产管理有限公司</td>
            </tr>
            <tr>
                <th>退款账户余额</th>
                <td><span class="fromAccountBalance">--</span>
                    <a class="repayment-link" href="javascript:void(0);" class="refresh_balance">刷新</a>
                </td>
            </tr>
            <tr>
                <th>收款方账户</th>
                <td class="toAccountNo">--</td>
            </tr>
            <tr>
                <th>收款方户名</th>
                <td class="toAccountName">--</td>
            </tr>
            <tr>
                <th>退款金额</th>
                <td class="toFee">--</td>
            </tr>
            <tr>
                <th>支付密码</th>
                <td>
                    <input name="payPassword" type="password" id="payPassword">
                    <a class="repayment-link" href="javascript:void(0);">忘记密码</a><br>
                     <span ><font color="red" class="error-text" ></font></span >
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot" style="margin-top:30px;">
            <a class="default-link confirm-link" href="javascript:void(0);">确定</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</div>
</@hb.htmlBase>