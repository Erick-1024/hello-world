<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划录入" jsFiles=["page/repayment/input/planSelect.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划录入" removeExtHeader = false removeExtFooter = false>
	<div class="main-container">
	    <div class="whiteBg">
	        <div class="finance-options">
	            <div class="finance-title">还款计划录入选择</div>
	            <table class="repayment-options">
	                <colgroup>
	                    <col width="200px">
	                    <col width="400px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td>业务模式</td>
	                    <td>
	                        <div class="radioBox01" id="businessMode">
	                            <label class="radio active" name="FACTORANDFINACE">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">融资客户监管</span>
	                            </label>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td>信息录入方式</td>
	                    <td>
	                        <div class="radioBox02" id="inputMethod">
	                            <label class="radio active" name="EXCEL">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">Excel导入</span>
	                            </label>
	                            <!--<label class="radio" name="AUTO">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">自动计算</span>
	                            </label>-->
	                            <label class="radio" name="MANUAL">
	                                <span class="radioIcon"></span>
	                                <span class="labelFonts">手工录入</span>
	                            </label>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td></td>
	                    <td style="padding-top:30px;">
	                        <a class="default-link confirm-link repayment-next" href="javascript:void(0);">下一步</a>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            <form method="post" action="${basepath}/repayment/plan/input/planInput" id="planSelect-form">
            		<input type="hidden" name="businessMode" value="">
            		<input type="hidden" name="inputMethod" value="">
            	</form>
	        </div>
	    </div>
	</div>
</@hb.htmlBase>