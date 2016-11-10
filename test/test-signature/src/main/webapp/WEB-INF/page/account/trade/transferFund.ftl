<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="转账" jsFiles=["common/jquery.form.min.js","common/cana.util.js","facade/formValidatorRules.js","common/formValidator.js","page/account/trade/trade.js"] cssFiles=["css/account.css"] localCssFiles=[] 
curTopMenu = "账户管理" curSubMenu = "转账申请" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <form action="transferFund" id="trade-form" method="post" >
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">转账</div>
            <div class="accountList-content">
                <table class="withdraw">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="acu-title">
	                        <span class="redFalg">*</span>输入转出银行账号
                        </td>
                        <td>
                            <input id="accountNo" class="bankCard" name="accountNo" type="text" style="width: 250px;" placeholder="请输入转出账号">
                            <span data-for="accountNo" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">转出银行账户名称</td>
                        <td>
                            <span class="accountName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a class="default-link confirm-link next-link" style="width:200px;margin-top:20px;" href="javascript:void(0);">下一步</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="withdraw-wrap trade-wrap">
            <div class="accountList-title">转账</div>
            <div class="accountList-content account-info">
                
            </div>
            <div class="accountList-content">
                <table class="withdraw">
                    <colgroup>
                        <col width="157px">
                        <col width="250px">
                        <col width="193px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>收款账号</td>
                        <td>
                            <input type="text" style="width: 250px;" class="bankCard" name="receiveAccountNo" id="receiveAccountNo" placeholder="请输入收款账号">
                        </td>
                        <td><span data-for="receiveAccountNo" class="k-invalid-msg"></span></td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>收款户名</td>
                        <td>
                            <input type="text" name="receiveAccountName" id="receiveAccountName" style="width: 250px;">
                        </td>
                        <td>
                            <span data-for="receiveAccountName" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>支付密码</td>
                        <td>
                            <input type="password" name="payPassword" id="payPassword" style="width: 250px;">
                        </td>
                        <td>
                        	<span data-for="payPassword" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <!--
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>验证码</td>
                        <td colspan="2">
                            <input type="text" name="verifyCode" id="verifyCode" style="width: 100px;">
                            <span data-for="verifyCode" class="k-invalid-msg"></span>
                            <label>
                                <img class="validateImg refresh-code" src="/vbam-front-biz/captcha/gen" alt="验证码">
                                <a class="validate-link refresh-code" href="javascript:void(0);">换一个</a>
                            </label>
                        </td>
                    </tr>
                    -->
                    <tr>
                        <td></td>
                        <td colspan="2" style="padding-top:20px;">
                            <a class="default-link confirm-link withdrawNotice submit" href="javascript:void(0);">提交</a><a class="default-link back-link back" href="javascript:void(0);">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="loanInfo" class="accountList-content" style="display: none;">
                <div class="accountList-head">放款信息</div>
                <div class="accountDetail-wrap">
			        <div id="withdrawGrid" class="accountManageGrid k-grid k-widget" data-role="grid" style=""></div>
			    </div>
            </div>
        </div>
    </section>
    </form>
</div>
<#include 'tradeTemplate.ftl'/>
<#include "../tipBoxTemplate.ftl" />
</@hb.htmlBase>
