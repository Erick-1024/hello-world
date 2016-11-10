<!--还款计划信息弹框-->
<script id="template-resetPwd-arr" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="repayment-form">
            <div class="dlg-del-row">
                <table class="client-tb">
                    <colgroup>
                        <col width="100px">
                        <col width="300px">
                        <col width="100px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>融资余额</th>
                        <td style="position:relative;">
                        	<input type="text" style="width:200px;" id="financeBalance" class="repayment-balance moneyNum" name="verify-repayment01" data-required-msg="不能为空" required>
                        	<div class="client-unit">元</div>
                        </td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>起息日</th>
                        <td> 
                        	<input type="text" style="width:200px;" class="repayment-valueDate datepicker time-one hasIcon" name="verify-repayment02" data-required-msg="不能为空" required>
                        </td>
                        <th>结息日</th>
                        <td><input type="text" style="width:200px;" class="repayment-expiryDate datepicker time-two hasIcon" name="verify-repayment03" data-required-msg="不能为空" required></td>
                    </tr>
                    <tr>
                        <th>还款日</th>
                        <td><input type="text" style="width:200px;" class="repayment-date datepicker time-three hasIcon" name="verify-repayment04" data-required-msg="不能为空" required></td>
                        <th>应还本金</th>
                        <td style="position:relative;">
                        	<input id="accountPrincipal" type="text" style="width:200px;" class="repayment-should moneyNum" name="verify-repayment05" data-required-msg="不能为空" required>
                        	<div class="client-unit">元</div>
                        </td>
                    </tr>
                    <tr>
                        <th>应还利息</th>
                        <td style="position:relative;">
                        	<input type="text" style="width:200px;" id="accountInterest" class="repayment-interest moneyNum" name="verify-repayment06" data-required-msg="不能为空" required>
                        	<div class="client-unit">元</div>	
                        </td>
                        <th>逾期费</th>
                        <td style="position:relative;">
                        	<input type="text" style="width:200px;" id="accountOverdue" class="repayment-overdueInterest moneyNum" name="verify-repayment07" data-required-msg="不能为空" required>
                        	<div class="client-unit">元</div>
                        	<#if isEditLoan>
                        		<div class="maxMoneyNum" style="top:-8px;">最低逾期费：<span id="minOverdueInterest">0.00</span>元</div>
                        	</#if>
                        </td>
                    </tr>
                    <tr>
                        <th>应还总金额</th>
                        <td style="position:relative;">
                        	<input type="text" style="width:200px;" class="repayment-all moneyNum" id="accountAmount" value="0.00" name="verify-repayment08">
                        	<div class="client-unit">元</div>
                        </td>
                        <th>结清栏</th>
                        <td>
                        	<select class="" style="width:200px;" name="dateUnit" id="settleStatus" data-role="dropdownlist">
                                <#if settleStatusList??>
                                    <#list settleStatusList as settleStatus>
                                    	<option value=${settleStatus.name()}>${settleStatus.desc()}</option>
									</#list>
								</#if>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="repayment-btn" href="javascript:void(0);">确定</a>
                <a class="default-link back-link close-repaymentPop" href="javascript:void(0);">取消</a>
            </div>
        </form>
        <input type="hidden" value="" class="repayment-id">
    </div>
</script>