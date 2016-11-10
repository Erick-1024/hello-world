<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="Excel导入放款信息" jsFiles=["js/common/ajaxfileupload.js","page/repayment/input/loanInfoFromExcel.js"] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "放款信息录入">
<div class="main-container">
    <div class="whiteBg">
        <section class="repayment-excel">
        	 <div class="tooltip-wrap">
                <a class="default-tooltip" href="javascript:void(0);">操作指引</a>▼
                <div class="tooltip-contain">
                    <div class="tooltip-flag">
                        <span class="flag01"></span>
                        <span class="flag02"></span>
                    </div>
                    <div class="tooltip-content">
                        1.点击“放款信息模板下载”。（上传信息要点，在Excel中查看）<br/>
                        2.打开下载好的Execl，根据要求填好信息。<br/>
                        3.点击“上传文件”，将填好的Excel上传。<br/>
                        4.确认下方列表中信息，点击“提交”完成录入。
                    </div>
                </div>
            </div>
            <div class="finance-wrap">
                <div class="finance-title">放款信息Excel导入</div>
                <table class="repayment-input">
                    <colgroup>
                        <col width="400px">
                        <col width="580px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td colspan="2" style="text-align: right;">
                            <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=loaninfotemplatedownload&mediaType=download">放款信息模板下载</a>
                        </td>
                    </tr>
                    <tr>
                        <td>放款信息导入</td>
                        <td class="fileBox">
							<a class="frontage-link" href="javascript:void(0);">上传文件</a><label id="choiceResult" data-key=${key!} class="tageNotice"></label>
							<input id="uploadFileId" class="frontage" type="file" name="excel" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="padding-top:30px;">
                            <!-- <a id="buttonUpload" class="default-link confirm-link" style="width: 200px;" href="javascript:void(0);">导 入</a> -->
                            <label id="importResult" class="tageNotice" style="color:red"></label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="input-notice">共录<span id="totalNum" class="inputFont">0</span>条，检查通过<span id="legalNum" class="inputFont">0</span>条，检查未通过<span id="illegalNum" class="inputFont">0</span>条</div>
            <div class="finance-head">检查通过放款信息</div>
            <div id="legalLoanInfoGridDiv"><div class="finance-grid" id="finance-pass"></div></div>
            <div class="finance-head">检查未通过放款信息</div>
            <div id="illegalLoanInfoGridDiv"><div class="finance-grid" id="finance-unpass"></div></div>
        </section>
        <div class="foot-empty">
	        <div class="dlg-wrapper-foot" id="foot-fixBar">
	            <a id="buttonConfirm" class="default-link confirm-link repayment-entry" href="javascript:void(0);">提交</a><a class="default-link back-link redirect" href="${basepath}/loanInfo/input">返回</a>
	        </div>
        </div>
    </div>
</div>
<!--确认保存数据库成功弹窗模板-->
<script id="template-entryExcel" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span><span class="notice-content"></span>
        </div>
    </div>
</script>
<!--设置弹窗模板-->
<script id="template-setAccount" type="text/x-kendo-template">
    <div id="setAccount" class="dlg-wrapper hidden">
        <table class="dlg-table">
            <colgroup>
                <col width="200px">
                <col width="400px">
            </colgroup>
            <tbody>
            <tr>
                <td>资金方<span id="loanno" hidden></span></td>
                <td id="factorCompany"></td>
            </tr>
            <tr>
                <td>融资客户</td>
                <td id="financeCompany"></td>
            </tr>
            <tr>
                <td>监管账号</td>
                <td>
                    <select class="selectWrap" data-role="dropdownlist" name="accountNo" id="accountNo" style="width:180px">
	               	</select>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a id="saveAccount" class="default-link confirm-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
    <!--没有可用的监管账号-->
    <div id="noAccount" class="dlg-wrapper hidden">
        <div class="dlg-wrapper-content">
            <div>没有可用的监管账号。解决方法：</div>
            <div>1.取消导入放款信息，建立监管账号，再次导入放款信息。<a target="_blank" class="repayment-link back-link" href="${basepath}/account/supervision/create">建立监管账号</a></div>
            <div>2.导入放款信息，建立监管账号，在还款信息管理中设置监管账号。</div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link" href="javascript:void(0);">关闭</a>
        </div>
    </div>
</script>
<input type="hidden" name="" id="" class="template-leave">
<script id="template-leave" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">当前放款信息已上传，数据尚未提交，请确认是否离开？点击"确认"离开该页面，点击"取消"留在该页面。</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmLeave" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
</@hb.htmlBase>
