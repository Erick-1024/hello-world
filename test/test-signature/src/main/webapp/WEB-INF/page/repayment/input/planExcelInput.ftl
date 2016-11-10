<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="Excel导入还款计划" jsFiles=["js/common/ajaxfileupload.js","page/repayment/input/planExcelInput.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划录入" removeExtHeader = false removeExtFooter = false>
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
                        1.点击“还款计划下载”。（上传信息要点，在Excel中查看）<br/>
                        2.打开下载好的Excel，根据要求填写还款计划、还款费用。（还款计划、还款费用为两张表格）<br/>
                        3.点击“上传文件”，将填好的Excel上传。<br/>
                        4.确认下方还款计划、还款费用列表中信息，点击“提交”完成录入。
                    </div>
                </div>
            </div>
            <div class="finance-wrap">
                <div class="finance-title">还款计划Excel导入</div>
                <table class="repayment-input">
                    <colgroup>
                        <col width="400px">
                        <col width="580px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td colspan="2" style="text-align: right;">
                            <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=repaymentplanandexpensetemplatedownload&mediaType=download">还款计划模板下载</a>
                        </td>
                    </tr>
                    <tr>
                        <td>还款计划导入</td>
                        <td class="fileBox">
                            <input id="uploadFileId" type="file" name="excel" data-key=${redisKey} accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" class="frontage"><a class="frontage-link" href="javascript:void(0);">选择文件</a>
                            <label class="tageNotice" id="importFlag">未选择</label>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="padding-top:30px;">
                            <a class="default-link confirm-link" style="width: 200px;" href="javascript:void(0);">导 入</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="repayment-nav">
                <ul>
                    <li class="repayment-nav-active">
                        <a class="repayment-navlink" href="javascript:void(0);">还款计划</a>
                    </li>
                    <li>
                        <a class="repayment-navlink" href="javascript:void(0);">还款费用</a>
                    </li>
                </ul>
            </div>
            <div class="repayment-grid" id="repayment-plan">
                <div class="repayment-backNote">共录<span class="inputFont" name="totalNumForPlan">0</span>条，检查通过<span class="inputFont" name="totalCorrectNumForPlan">0</span>条，检查未通过<span class="inputFont" name="totalIncorrectForPlan">0</span>条</div>
                <div class="finance-head">检查通过还款信息</div>
                <div id="finance-passRepayWrap">
	                <div class="finance-grid" id="finance-passRepay"></div>
                </div>
                <div class="finance-head">检查未通过的还款信息</div>
                <div id="finance-unpassRepayWrap">
	                <div class="finance-grid" id="finance-unpassRepay"></div>
                </div>
            </div>
            <div class="repayment-grid hidden" id="repayment-cost">
            	<div class="repayment-backNote">共录<span class="inputFont" name="totalNumForExpense">0</span>条，检查通过<span class="inputFont" name="totalCorrectNumForExpense">0</span>条，检查未通过<span class="inputFont" name="totalIncorrectForExpense">0</span>条</div>
                <div class="finance-head">检查通过费用列表</div>
                <div id="finance-passCostWrap">
	                <div class="finance-grid" id="finance-passCost"></div>
                </div>
                <div class="finance-head">检查未通过费用列表</div>
                <div id="finance-unpassCostWrap">
	                <div class="finance-grid" id="finance-unpassCost"></div>
                </div>
            </div>
        </section>
    </div>
    <div class="foot-empty">
	    <div class="dlg-wrapper-foot" id="foot-fixBar">
	    	<form id="saveInfo" action="saveCorrectRepaymentPlanAndExpense" method="post">
	    		<input type="hidden" name="redisKey" value="${redisKey}"> 
	    	</form>
			<a class="default-link confirm-link repayment-entry" href="javascript:void(0);">提交</a><a class="default-link back-link redirect" href="${basepath}/repayment/plan/input/planSelect">返回</a>
	    </div>
    </div>
</div>
<input type="hidden" name="" id="" class="template-leave">
<script id="template-leave" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span><span class="notice-content">当前已录入还款计划，数据尚未提交，请确认是否离开？点击"确认"离开该页面，点击"取消"留在该页面。</span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="comfirmLeave" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
</@hb.htmlBase>
