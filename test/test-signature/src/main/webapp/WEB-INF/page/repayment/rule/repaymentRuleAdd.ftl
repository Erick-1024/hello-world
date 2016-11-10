<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="新增还款计划规则" jsFiles=["page/repayment/rule/repaymentRuleAdd.js", "page/repayment/rule/repaymentRuleformValidatorRules.js", "common/formValidator.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划规则" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <section class="repayRule-wrap">
        	<form id="ruleAdd-form" name="ruleAdd-form">
	            <div class="repayRule-element">
	                <div class="finance-title">规则要素</div>
	                <div class="repayRule-content">
	                    <table class="repayDetail-table">
	                        <colgroup>
	                            <col width="120px">
	                            <col width="120px">
	                            <col width="250px">
	                            <col width="150px">
	                            <col width="250px">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th rowspan="2">基本规则</th>
	                            <td class="repayDetail-til">规则编号</td>
	                            <td>
	                                <span id="ruleId" name="ruleId" style="width:180px;">${ruleId}</span>
	                            </td>
	                            <td class="repayDetail-til">规则范围</td>
	                            <td>
	                            	<span id="scopeOfApplication" name="scopeOfApplication">
	                            		部分融资客户
	                            	</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">回款账户</td>
	                            <td>
	                                <span id="factorTransferInAccountNo" name="factorTransferInAccountNo">${formatBankAccountNo(account)}</span>
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="repayRule-content">
	                    <table class="repayDetail-table">
	                        <colgroup>
	                            <col width="120px">
	                            <col width="120px">
	                            <col width="250px">
	                            <col width="150px">
	                            <col width="280px">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th rowspan="4">扣款规则</th>
	                            <td class="repayDetail-til">扣款时点</td>
	                            <td>
	                                <input id="deductionTime" name="deductionTime" type="text" style="width:180px;">
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">扣款规则</td>
	                            <td>
	                                <div class="radioContent" id="deductionRule" name="deductionRule" style="padding:5px 0;">
	                                    <label class="radio active" data-deductionrule="PART">
	                                        <span class="radioIcon"></span>
	                                        <span class="labelFonts">可部份扣款</span>
	                                    </label>
	                                    <label class="radio" data-deductionrule="ALL">
	                                        <span class="radioIcon"></span>
	                                        <span class="labelFonts">足额后扣款</span>
	                                    </label>
	                                </div>
	                            </td>
	                            <td class="repayDetail-til">扣款顺序</td>
	                            <td>
	                            	逾期、其他费用、手续费、收益、本金
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">展期</td>
	                            <td>
	                                <input type="text" id="extensionDays" name="extensionDays" style="width:180px;text-align:right;">天
	                            </td>
	                            <td class="repayDetail-til">展期费率</td>
	                            <td>
	                                <input type="text" id="extensionRatio" name="extensionRatio" style="width:180px;text-align:right;">%
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">逾期管理费率</td>
	                            <td>
	                                <input type="text" id="penaltyRate" name="penaltyRate" style="width:180px;text-align:right;">%<span style="color:#0f8aba;cursor:pointer" title="逾期管理费 = 本息 * 逾期管理费率" class="common-tooltip">计算公式</span>
	                            </td>
	                            <td class="repayDetail-til">提前还款手续费率</td>
	                            <td>
	                                <input type="text" id="earlyRepaymentChargeRatio" name="earlyRepaymentChargeRatio" style="width:180px;text-align:right;">%<span style="color:#0f8aba;cursor:pointer" title="提前还款手续费 = 提前归还的本金 * 提前还款手续费率" class="common-tooltip">计算公式</span>
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="repayRule-content">
	                    <table class="repayDetail-table">
	                        <colgroup>
	                            <col width="120px">
	                            <col width="120px">
	                            <col width="250px">
	                            <col width="150px">
	                            <col width="280px">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th rowspan="1">账户归集规则</th>
	                            <td class="repayDetail-til">账户归集</td>
	                            <td>
	                                <input type="text" id="accountAccumulationTime" name="accountAccumulationTime" style="width:180px;">
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	            <div class="repayRule-item">
	                <div class="finance-title">融资客户列表</div>
	                <div class="repayRule-detail">
	                    <div style="margin-bottom:10px;">
	                        <a class="form-add-link addCustom-link" href="javascript:void(0);"><i class="AddIcon"></i>添加</a>
	                    </div>
	                    <table class="addCustom-table">
	                        <colgroup>
	                            <col width="20%">
	                            <col width="60%">
	                            <col width="20%">
	                        </colgroup>
	                        <thead>
	                        <tr>
	                            <td>序号</td>
	                            <td>融资客户</td>
	                            <td>操作</td>
	                        </tr>
	                        </thead>
	                        <tbody id="addTbody">
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	            <div class="dlg-wrapper-foot">
	                <a class="default-link confirm-link" id="addRule-button" href="javascript:void(0);">确定</a><a class="default-link back-link " href="${basepath}/repayment/rule/list">返回</a>
	            </div>
            </form>
        </section>
    </div>
</div>
</@hb.htmlBase>
<script>
	var supervisorSum = ${supervisors?size}
</script>
<!--遮罩层-->
<div class="window-overlay hidden"></div>
<!--新建融资客户弹窗模板-->
<div class="template-manualAdd customDlg hidden" id="template-addCustom">
    <div class="manualAdd-head">
        <span>新增融资客户</span>
        <div class="manual-closeBar">
            <a class="closeHref autoCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
        <div class="repayDetail-content">
            <table class="dlg-ruleTable">
                <colgroup>
                    <col width="40%">
                    <col width="60%">
                </colgroup>
                <tbody>
                <tr>
                    <td class="repayDetail-til">融资客户</td>
                    <td>
                		<select class="selectWrap" id="supervisor" name="supervisor" data-role="dropdownlist" style="width:180px">
                        	<#list supervisors as supervisor>
                        		<option value=${supervisor.id!}>${supervisor.companyName!}</option>
                        	</#list>
                    	</select>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="dlg-wrapper-foot">
                <a class="default-link confirm-link " href="javascript:void(0);">确定</a>
                <a class="default-link back-link" href="javascript:void(0);">返回</a>
            </div>
        </div>
    </div>
</div>
<!--修改融资客户弹窗模板-->
<div class="template-manualAdd customDlg hidden" id="template-eidtCustom">
    <div class="manualAdd-head">
        <span>本次还费用</span>
        <div class="manual-closeBar">
            <a class="closeHref autoCloseBar" href="javascript:void(0);">
                <span class="closeIcon"></span>
            </a>
        </div>
    </div>
    <div class="manualAdd-content">
        <div class="repayDetail-content">
            <table class="dlg-ruleTable">
                <colgroup>
                    <col width="40%">
                    <col width="60%">
                </colgroup>
                <tbody>
                <tr>
                    <td class="repayDetail-til">融资客户</td>
                    <td>
                        <input id="originName" type="text" style="width:180px;">
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="dlg-wrapper-foot">
                <a class="default-link confirm-link " href="javascript:void(0);">确定</a>
                <a class="default-link back-link" href="javascript:void(0);">返回</a>
            </div>
        </div>
    </div>
</div>