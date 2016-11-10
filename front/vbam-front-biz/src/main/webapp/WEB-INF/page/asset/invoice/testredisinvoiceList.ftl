<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="redis测试" jsFiles=["page/asset/invoice/testredisinvoiceList.js", "common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "应收账款管理" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <form>
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                   <input type="hidden" id="key" name="key" value="${key}"/>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid-test"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>
</@hb.htmlBase>