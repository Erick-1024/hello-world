<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="编辑还款计划规则" jsFiles=["page/repayment/rule/repaymentRuleModify.js", "page/repayment/rule/repaymentRuleformValidatorRules.js", "common/formValidator.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划规则" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="whiteBg">
        <section class="repayRule-wrap">
        	<form id="ruleEdit-form" name="ruleEdit-form">
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
	                                <span id="ruleId" name="ruleId" style="width:180px;">${repaymentRule.id}</span>
	                            </td>
	                            <td class="repayDetail-til">规则范围</td>
	                            <td>
	                            	<span id="scopeOfApplication" name="scopeOfApplication">
	                    		        <#if (repaymentRule.fianceCustomerIds)??>
	                            			部分融资客户
	                            		<#else>
	                            			全部融资客户
	                            		</#if>
	                            	</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">回款账户</td>
	                            <td>
	                            	<#if (repaymentRule.fianceCustomerIds)??>
	                            		<span id="factorTransferInAccountNo" name="factorTransferInAccountNo">${formatBankAccountNo(account)}</span>
	                            	<#else>
		                                <select class="selectWrap" id="factorTransferInAccountNo" name="factorTransferInAccountNo" data-role="dropdownlist" style="width:180px">
		                                    <#list accounts as account>
		                                    	<#if account.accountNo == repaymentRule.factorTransferInAccountNo>
		                                    		<option value=${account.accountNo} selected="selected">${formatBankAccountNo(account.accountNo)}</option>
		                                    	<#else>
		                                    		<option value=${account.accountNo}>${formatBankAccountNo(account.accountNo)}</option>
		                                    	</#if>
		                                    </#list>
		                                </select>
	                            	</#if>
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
	                                <input id="deductionTime" name="deductionTime" type="text" value=${repaymentRule.deductionTime} style="width:180px;">
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">扣款规则</td>
	                            <td>
	                                <div class="radioContent" id="deductionRule" name="deductionRule">
                                        <label class="radio <#if repaymentRule.deductionRule == "PART">active</#if>" data-deductionrule="PART">
                                        	<span class="radioIcon"></span>
                                        	<span class="labelFonts">可部份扣款</span>
                                    	</label>
                                    	<label class="radio <#if repaymentRule.deductionRule != "PART">active</#if>" data-deductionrule="ALL">
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
	                                <input type="text" id="extensionDays" name="extensionDays" value=${repaymentRule.extensionDays} style="width:180px;text-align:right;">天
	                                <span data-for="extensionDays" class="k-invalid-msg"></span>
	                            </td>
	                            <td class="repayDetail-til">展期费率</td>
	                            <td>
	                                <input type="text" id="extensionRatio" name="extensionRatio" value=${repaymentRule.extensionRatio?substring(0, repaymentRule.extensionRatio?length - 1)} style="width:180px;text-align:right;">%
	                                <span data-for="extensionRatio" class="k-invalid-msg"></span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="repayDetail-til">逾期管理费率</td>
	                            <td>
	                                <input type="text" id="penaltyRate" name="penaltyRate" value=${repaymentRule.penaltyRate?substring(0, repaymentRule.penaltyRate?length - 1)} style="width:180px;text-align:right;">%<span style="color:#0f8aba;cursor:pointer" title="逾期管理费 = 本息 * 逾期管理费率" class="common-tooltip">计算公式</span>
	                                <span data-for="penaltyRate" class="k-invalid-msg"></span>
	                            </td>
	                            <td class="repayDetail-til">提前还款手续费率</td>
	                            <td>
	                                <input type="text" id="earlyRepaymentChargeRatio" name="earlyRepaymentChargeRatio" value=${repaymentRule.earlyRepaymentChargeRatio?substring(0, repaymentRule.earlyRepaymentChargeRatio?length - 1)} style="width:180px;text-align:right;">%<span style="color:#0f8aba;cursor:pointer" title="提前还款手续费 = 提前归还的本金 * 提前还款手续费率" class="common-tooltip">计算公式</span>
	                                <span data-for="earlyRepaymentChargeRatio" class="k-invalid-msg"></span>
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
	                                <input type="text" id="accountAccumulationTime" name="accountAccumulationTime" value=${repaymentRule.accountAccumulationTime} style="width:180px;">
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
				<#if (repaymentRule.fianceCustomerIds)??>
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
		                        <#list repaymentRule.fianceCustomerCompanys?split(",") as fianceCustomerCompany>
		                        	<tr>
			                            <td>${fianceCustomerCompany_index + 1}</td>
			                            <td>${fianceCustomerCompany}</td>
			                            <td>
	                						<!--<a class="repayment-link editCustom-link" data-id=${repaymentRule.fianceCustomerIds?split(",")[fianceCustomerCompany_index]} data-companyName=${fianceCustomerCompany} href="javascript:void(0);">修改</a> -->
	                						<a class="repayment-link delCustom-link" data-id=${repaymentRule.fianceCustomerIds?split(",")[fianceCustomerCompany_index]} data-companyName=${fianceCustomerCompany} href="javascript:void(0);">删除</a>
	                					</td>
		                       		</tr>
		                        </#list>
		                        </tbody>
		                    </table>
		                </div>
		            </div>
	            </#if>
	            <div class="dlg-wrapper-foot">
	                <a class="default-link confirm-link" id="modifyRule-button" href="javascript:void(0);">确定</a><a class="default-link back-link " href="${basepath}/repayment/rule/list">返回</a>
	            </div>
	    	</form>
        </section>
    </div>
</div>
</@hb.htmlBase>
<script>
	<#if (repaymentRule.fianceCustomerIds)??>
		var repaymentRuleType = "COMMON";
		var ids = "${repaymentRule.fianceCustomerIds}";
		var companyNames = "${repaymentRule.fianceCustomerCompanys}";
		var supervisorSum = ${supervisors?size} - ${repaymentRule.fianceCustomerCompanys?split(",")?size}
	<#else>
		var repaymentRuleType = "DEFAULT";
		var ids = "";
		var companyNames = "";
	</#if>
</script>
<#if (repaymentRule.fianceCustomerIds)??>
	<!--遮罩层-->
	<div class="window-overlay hidden"></div>
	<!--新建融资客户弹窗模板-->
	<div class="template-manualAdd customDlg hidden" id="template-addCustom">
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
	</div
</#if>
