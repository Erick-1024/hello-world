<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="${subTitle}" jsFiles=["page/homsom/settlement/import.js", "common/cana.util.js", "common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "${title}" curSubMenu = "${subTitle}" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
        <div class="pro-box-bg">
        <div class="pro-title" id="nav-one"><span class="pro-title-left">${name}明细Excel导入</span></div>
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
                </tr>
                <tr>
                    <th valign="top" style="">${name}明细导入</th>
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
            <div>&emsp;检查通过${subTitle}明细</div>
            <div class="monitor-grid" id="legalGridDiv"></div>
        </div>
        <div class="im-text-box">
            <div>&emsp;检查未通过${subTitle}明细</div>
            <div class="monitor-grid" id="illegalGridDiv"></div>
        </div>
        <div style="text-align: center;">
            <a class="default-link confirm-link repayment-entry" href="javascript:void(0);" id="buttonConfirm">提交</a>
            <#--<a class="default-link back-link" href="javascript:history.back();">取消</a>-->
        </div>

    </div>
</div>

<#include '../../tipBoxTemplate.ftl'/>

<script>
	var urlParam = "${urlParam}";
	var redict = "${redict}";
	var channel = "${channel}";
</script>

</@hb.htmlBase>