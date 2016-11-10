<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="额度列表" jsFiles=["page/credit/limit/list.js", "common/dateutil.js"] cssFiles=["css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "额度列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="400">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户名称</th>
                    <td>
                        <input type="text" style="width:120px;" id="companyName">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                	<th>额度</th>
                    <td>
                        <input type="text" style="width:100px;" id="limitStart">
                        <em>&nbsp;-&nbsp;</em>
                        <input type="text" style="width:100px;" id="limitEnd">
                    </td>
                </tr>
                <tr>
                    <th>额度生效日</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly>
                    </td>
                </tr>
                <tr>
                    <th>额度状态</th>
                    <td>
                        <a class="status-normal status-default" href="javascript:void(0);" value="NORMAL">正常</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="FREEZE">冻结</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="DISABLED">停用</a>
                        <a class="status-normal status-default" href="javascript:void(0);" value="INACTIVE">未激活</a>
                        <a class="status-normal status-chk" href="javascript:void(0);" value="">全部</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorLimit-grid"></div>
    </section>
</div>
<!--遮罩层-->
<div class="window-overlay hidden"></div>
<!--弹窗模板-->
<div class="template-limitAct hidden" id="template-confirm">
    <div class="manualAdd-head">
        <span>提示</span>
        <div class="manual-closeBar">
            <a class="closeHref" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">您确定要激活吗？</span>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link template-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</div>
<!--弹窗失败模板-->
<div class="template-limitAct hidden" id="template-fail">
    <div class="manualAdd-head">
        <span>提示</span>
        <div class="manual-closeBar">
            <a class="closeHref" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon03"></span><span class="notice-content">激活失败！</span>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link close-window" href="javascript:void(0);">关闭</a>
        </div>
    </div>
</div>
<script>
var activeLimit=${authorizeKey("LL_CREDIT_LIMIT_ACTIVE")?string("true", "false")};
</script>
</@hb.htmlBase>