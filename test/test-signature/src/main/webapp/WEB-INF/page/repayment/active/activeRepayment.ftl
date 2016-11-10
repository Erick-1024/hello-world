<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="主动还款" jsFiles=["page/repayment/active/activeRepayment.js", "page/repayment/active/activeRepaymentRules.js", "common/formValidator.js"] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "融资信息管理">
<div class="main-container">
    <div class="whiteBg">
        <div class="finance-options">
            <div class="finance-title">还款计划录入选择</div>
            <form id="activeRepayment-form" name="activeRepayment-form">
            	<table class="repayment-options">
                <colgroup>
                    <col width="200px">
                    <col width="400px">
                    <col width="400px">
                </colgroup>
                <tbody>
                <tr>
                    <td>最大还款金额</td>
                    <td><span id="maxAmount">${maxAccountTotalMoney!}</span></td>
                    <td><span></span></td>
                </tr>
                <tr>
                    <td>银行账号</td>
                    <td colspan='2'>
                        <select id="accountNo" name="accountNo" class="selectWrap" data-role="dropdownlist" style="width:180px">
                        <#if accountMap??>
                        	<#list accountMap?keys as key>
                            <option value=<#if accountMap[key] == "未监管">一般<#else>${accountMap[key]}</#if>>${key}</option>
                        	</#list>
            								</#if>
                        </select>
                        <span id="accountType"></span>
                    </td>
                </tr>
                <tr>
                    <td>账户余额</td>
                    <td colspan='2'>
                        <span id="balance"></span>
                        <a class="repayment-link" id="refreshBalance" href="javascript:void(0);">刷新</a>
                    </td>
                </tr>
                <tr>
                    <td>还款金额</td>
                    <td colspan='2'>
                        <input type="text" id="amount" name="amount" placeholder="￥" class="cashTxt moneyNum" style="width:180px;">
                    </td>
                </tr>
                <tr>
                    <td>支付密码</td>
                    <td colspan='2'>
                        <input type="password" id="password" name="password" style="width:180px;">
                        <a class="repayment-link" id="forgetPsw" href="javascript:void(0);">忘记密码</a>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan='2' style="padding-top:30px;">
                        <a class="default-link confirm-link" data-activeRepaymentType = ${activeRepaymentType} data-loanInfoId="${loanInfoId}" href="javascript:void(0);">确认还款</a>
                        <a class="default-link back-link" href="${basepath}/loanInfo/manage/gotoRepaymentPlanDetails?loanId=${loanInfoId}">取消</a>
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
    </div>
</div>
</@hb.htmlBase>
