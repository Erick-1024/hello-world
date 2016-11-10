<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="主动还款" jsFiles=["page/repayment/active/activeRepayment.js", "page/repayment/active/activeRepaymentRules.js", "common/formValidator.js"] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "融资信息管理">
<div class="backstage-mainWrap">
    <div class="back-bg-box">
    <div class="commonTips-wrap" style="top:60px;">
        <form id="checkForm" autocomplete="off">
        <div class="commonTips-title">主动还款</div>
        <div class="commonTips-content">
            <form id="activeRepayment-form" name="activeRepayment-form" autocomplete="off">
            	<table class="back-tb">
                <colgroup>
                    <col width="360px">
                    <col width="600px">
                </colgroup>
                <tbody>
                <tr>
                    <th>最大还款金额</th>
                    <td><span id="maxAmount">${maxAccountTotalMoney!}</span></td>
                    <td><span></span></td>
                </tr>
                <#if autoRepaymentPlans>
                	<tr>
                        <th>最低还款金额</th>
                        <td><span id="minAmount">${minAccountTotalMoney!}</span></td>
                        <td><span></span></td>
                    </tr>
                </#if>
                <tr>
                    <th>还款银行账号</th>
                    <td colspan='2'>
                        <select id="accountNo" name="accountNo" class="selectWrap" data-role="dropdownlist" style="width:220px">
                        <#if accountMap??>
                        	<#list accountMap?keys as key>
                            <option value=${accountMap[key]}>${key}</option>
                        	</#list>
            								</#if>
                        </select>
                        <span id="accountType"></span>
                    </td>
                </tr>
                <tr>
                    <th>账户余额</th>
                    <td colspan='2'>
                        <span id="balance"></span>
                        <a class="repayment-link" id="refreshBalance" href="javascript:void(0);">刷新</a>
                    </td>
                </tr>
                <tr>
                    <th>还款金额</th>
                    <td colspan='2'>
                        <input type="text" id="amount" name="amount" placeholder="￥" class="cashTxt moneyNum" style="width:180px;" disableautocomplete autocomplete="off">
                        <span class="out-verify" style="display: none;"></span>
                        <#if autoRepaymentPlans>
	                        <label class="checkbox-btn-big">
	                            <span class="checkbox-btn-bg"></span><em>结清贷款</em>
	                        </label>
                        </#if>
                    </td>
                </tr>
                <#if autoRepaymentPlans>
	                <tr class="re-tr-col" style="display: none;">
	                    <th></th>
	                    <td>
	                        <span style="padding-right:6px;">归还本金</span>
	                        <span id="paid-principal">0.00</span>
	                        <em> </em>
	                        <span style="padding-right:6px;">归还利息</span>
	                        <span id="paid-interest">0.00</span>
	                        <em> </em>
	                        <span style="padding-right:6px;">归还逾期额</span>
	                        <span id="paid-overdueAmount">0.00</span>
	                        <em> </em>
	                        <span style="padding-right:6px;">服务费</span>
	                        <span id="paid-serviceCharge">0.00</span>
	                    </td>
	                </tr>
                </#if>    
                <tr>
                    <th>支付密码</th>
                    <td colspan='2'>
                        <input type="password" id="password" name="password" style="width:180px;">
                        <span class="out-verify" style="display: none;"></span>
                        <a class="repayment-link" id="forgetPsw" href="javascript:void(0);">忘记密码</a>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan='2' style="padding-top:30px;">
                        <a class="default-link confirm-link activeRepayment-link" data-autoRepaymentPlans = "${autoRepaymentPlans?string("true","false")}" data-activeRepaymentType = "${activeRepaymentType!''}" data-loanInfoId="${loanInfoId}" href="javascript:void(0);">确认还款</a>
                        <a class="default-link back-link" href="${basepath}/loanInfo/manage/gotoRepaymentPlanDetails?loanId=${loanInfoId}">取消</a>
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
        </form>
       </div>
    </div>
</div>
</@hb.htmlBase>

<!--主动还款确认弹框-->
<div class="popup-box pop-out-x" style="display: none;">
    <div class="popup-out-ch"></div>
    <div class="popup-in-ch-two">
        <!--提示标题-->
        <div class="popup-header">主动还款</div>
        <div class="popup-list-box">
            <div class="popup-content">
                <i class="popup-img notice-icon01"></i><span class="pop-con">是否确认还款？</span>
            </div>
            <div class="popup-foot-two">
                <a class="default-link confirm-link confirmActiveRepayment-link" href="javascript:void(0);">确定</a>
                <a class="default-link back-link close-btn-pop" href="javascript:void(0);">返回</a>
            </div>
        </div>
    </div>
</div>

<!--主动还款结果弹框-->
<div class="popup-box pop-out-c" style="display: none;">
    <div class="popup-out-ch"></div>
    <div class="popup-in-ch-two">
        <!--提示标题-->
        <div class="popup-header">还款结果</div>
        <div class="popup-list-box">
            <div class="popup-content">
                <i class="popup-img notice-icon02"></i><span class="pop-con">您已还款成功，请留意短信通知。</span>
            </div>
            <div class="popup-foot-two">
                <a class="default-link confirm-link close-btn-pop goto-list-link" href="javascript:void(0);">确定</a>
            </div>
        </div>
    </div>
</div>
