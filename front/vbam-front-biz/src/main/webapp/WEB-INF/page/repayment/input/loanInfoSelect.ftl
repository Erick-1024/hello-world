<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="放款信息录入" jsFiles=["page/repayment/input/loanInfoSelect.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "放款信息录入" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <div class="finance-options">
            <div class="finance-title">放款信息录入选择</div>
            <table class="repayment-options">
                <colgroup>
                    <col width="200px">
                    <col width="400px">
                </colgroup>
                <tbody>
                <tr>
                    <td>业务模式</td>
                    <td>
                        <div class="radioContent" id="businessMode">
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
                        <div class="radioContent" id="inputMethod">
                            <label class="radio active" name="EXCEL">
                                <span class="radioIcon"></span>
                                <span class="labelFonts">Excel导入放款信息</span>
                            </label>
                            <label class="radio" name="MANUAL">
                                <span class="radioIcon"></span>
                                <span class="labelFonts">手工录入放款信息</span>
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
            <form method="post" action="${basepath}/loanInfo/input/select" id="loanInfoSelect-form">
            	<input type="hidden" name="businessMode" value="">
            	<input type="hidden" name="inputMethod" value="">
            </form>
        </div>
    </div>
</div>
</@hb.htmlBase>