<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="基础资产Excel导入" jsFiles=["page/asset/underlyingAsset/underlyingAssetFromExcel.js","js/common/ajaxfileupload.js", "common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "证券化发行管理" curSubMenu = "基础资产管理" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
        <div class="pro-box-bg">
        <div class="pro-title" id="nav-one"><span class="pro-title-left">放款信息Excel导入</span></div>
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
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=underlyingassettemplatedownload&mediaType=download">基础资产模板下载</a>
                    </td>
                </tr>
                <tr>
                    <th valign="top" style="">基础资产Excel导入</th>
                    <td>
                        <div class="import-up">
                             <input type="file" class="input-file" name="excel" id="photos" style="top:0;width:100px;z-index:10;">
                          
                            <a class="default-link confirm-link" id="uploadExcel" href="javascript:void(0);" style="position:absolute;left:0;">上传附件</a>
                        </div>
                        <div class="import-success-box">
                        <input type="hidden" id="rediskey" name="rediskey" value="${rediskey}"/>
                        </div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a class="default-link confirm-link entryExcel" id="importExcel" style="width: 200px;" href="javascript:void(0);">导 入</a></td>
                    <td> <label id="importResult" class="tageNotice" style="color:red"></label></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="im-bottom-bor">&emsp;共录<span id="totalNum" class="import-msg">0</span>条，检查通过<span id="legalNum" class="import-msg">0</span>条，检查未通过<span id="illegalNum" class="import-msg">0</span>条</div>
        <div class="im-text-box">
            <div>&emsp;检查通过基础资产</div>
            <div class="monitor-grid" id="legalGridDiv"></div>
        </div>
        <div class="im-text-box">
            <div>&emsp;检查未通过基础资产</div>
            <div class="monitor-grid" id="illegalGridDiv"></div>
        </div>
        <div style="text-align: center;">
            <a class="default-link confirm-link repayment-entry" href="javascript:void(0);" id="buttonConfirm">提交</a>
            <#--<a class="default-link back-link" href="javascript:history.back();">取消</a>-->
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