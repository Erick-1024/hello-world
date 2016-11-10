<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="市场数据导入" jsFiles=["page/asset/marketresearch/import.js","js/common/ajaxfileupload.js", "common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "ABS市场研究" curSubMenu = "市场数据导入" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
        <div class="pro-box-bg">
        <div class="pro-title" id="nav-one"><span class="pro-title-left">ABS市场数据Excel导入</span></div>
        <div class="import-box">
            <table class="import-tb">
                <colgroup>
                    <col width="200px">
                    <col width="550px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <th valign="top" style="">ABS市场数据导入</th>
                    <td>
                        <div class="import-up">
                             <input type="file" class="input-file" name="excel" id="photos" style="top:0;width:100px;z-index:10;">
                          
                            <a class="default-link confirm-link" id="uploadMarketDataExcel" href="javascript:void(0);" style="position:absolute;left:0;">上传附件</a>
                        </div>
                        <div class="import-success-box">
                        <input type="hidden" id="rediskey" name="rediskey" value="${rediskey}"/>
                        </div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="im-bottom-bor" style="border:none;">&emsp;共录<span id="totalNum" class="import-msg">0</span>条</div>
        <div style="padding-top:10px;">
            <table class="import-tb">
                <colgroup>
                    <col width="200px">
                    <col width="550px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <td></td>
                    <td>
                        <a class="default-link confirm-link" href="javascript:void(0);" id="buttonConfirm">提交</a>
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>
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
</@hb.htmlBase>