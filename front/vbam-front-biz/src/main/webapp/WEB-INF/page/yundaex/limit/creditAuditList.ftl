<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="授信审核" jsFiles=["page/yundaex/limit/creditAuditList.js","js/common/yunda.js", "common/dateutil.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "授信审核" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
   <section class="whiteBg">
        <form class="monitor-form">
            <table class="monitor-table" style="width:100%;">
                <colgroup>
                    <col width="100">
                    <col width="450">
                    <col width="100">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td>
                        <input type="text" id="stationName" name="stationName" style="width:261px;">
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <th style="text-align: right;">授信审核状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);">全部</a>
                        <a class="war-out" href="javascript:void(0);" value="ACCESS">已通过</a>
                        <a class="war-out" href="javascript:void(0);" value="NOTACCESS">未通过</a>
                        <a class="war-out" href="javascript:void(0);" value="WAIT">待审核</a>
                        <input type="hidden" value="0" class="war-check-one" name="">
                    </td>
                    <td>
                        <a class="form-search-link creditAuditSearch" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                    <td>
                        <a class="form-search-link passAll" href="javascript:void(0);" style="float:right;">全部通过</a>
                        <a class="form-search-link rejectAll" href="javascript:void(0);" style="float:right;">全部驳回</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
    </section>
</div>
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
</@hb.htmlBase>
