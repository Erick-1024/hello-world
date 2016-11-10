<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="应收账款导入" jsFiles=["page/asset/invoice/invoiceFromExcel.js","js/common/ajaxfileupload.js", "common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "应收账款管理" removeExtHeader = false removeExtFooter = true>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <div class="pro-title" id="nav-one"><span class="pro-title-left">应收账款导入</span></div>
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
                    <td style="text-align:right;">
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=assetInvoicetemplatedownload&mediaType=download">应收账款模板下载</a>
                    </td>
                </tr>
                <tr>
                    <th valign="top" style="">应收账款导入</th>
                    <td>
                        <div class="import-up">
                           <input type="file" class="input-file" name="excel" id="photos" style="top:0;width:100px;z-index:10;">
                            <a id="uploadInvoiceExcel" class="default-link confirm-link" href="javascript:void(0);" style="position:absolute;left:0;top:0;">上传附件</a>
                        </div>
                        <div class="import-success-box">
                        <input type="hidden" id="rediskey" name="rediskey" value="${key}"/>
                        <label id="importResult" class="tageNotice" style="color:red"></label>
                        </div>
                    </td>
                    <td></td>
                </tr>
     
                <tr>
                <td></td>
                    <td><a class="default-link confirm-link entryExcel" id="importExcel" style="width: 200px;" href="javascript:void(0);">导 入</a></td>
                   <#-- <td><a class="default-link confirm-link entryExcel" id="redisExcel" style="width: 200px;" href="javascript:void(0);">excel读取</a></td>-->
                    <td> </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="im-bottom-bor">&emsp;共录<span id="totalNum" class="import-msg">0</span>条，检查通过<span id="legalNum" class="import-msg">0</span>条，检查未通过<span id="illegalNum" class="import-msg">0</span>条</div>
        <div class="im-text-box">
            <div>&emsp;检查通过应收账款</div>
            <div class="monitor-grid" id="legalInvoiceInfoGridDiv"></div>
        </div>
        <div class="im-text-box">
            <div>&emsp;检查未通过应收账款</div>
            <div class="monitor-grid" id="illegalInvoiceInfoGridDiv"></div>
        </div>
        <div style="text-align: center;">
            <a class="default-link confirm-link repayment-entry" id="buttonConfirm" href="javascript:void(0);">提交</a>
            <a class="default-link back-link" href="${basepath}/asset/invoice/invoiceList">取消</a>
        </div>

    </div>
</div>
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</@hb.htmlBase>
