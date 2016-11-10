<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="提现" jsFiles=["facade/formValidatorRules.js","common/formValidator.js","page/account/trade/trade.js","common/cana.util.js","common/jquery.form.min.js"] cssFiles=["css/account.css"] localCssFiles=[] 
curTopMenu = "账户管理" curSubMenu = "提现申请" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <form action="withdrawFund" id="trade-form" method="post" >
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">提现</div>
            <div class="accountList-content">
                <table class="withdraw">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td align="center" colspan="2">
                        	<span class="redFalg">提现时间: 9: 00-17: 00, 其余时间不能提现</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">
                        	<span class="redFalg">*</span>输入提现银行账号
                        </td>
                        <td>
                            <input id="accountNo" name = "accountNo" class="bankCard withdraw-input" type="text" style="width: 250px;" placeholder="请输入提现账号">
                            <span data-for="accountNo" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">提现银行账户名称</td>
                        <td>
                            <span class="accountName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a class="default-link confirm-link next-link commonBtn-link withdraw-button" style="width:200px;margin-top:20px;" href="javascript:void(0);">下一步</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="withdraw-wrap trade-wrap">
            <div class="accountList-title">提现</div>
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
                        <td class="acu-title"><span class="redFalg">*</span>收款银行账号</td>
                        <td>
                            <input type="text" style="width: 250px;" class="bankCard" id="receiveAccountNo" name="receiveAccountNo" placeholder="请输入银行账号">
                        </td>
                        <td>
                            <span data-for="receiveAccountNo" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">收款银行户名</td>
                        <td colspan="2">
                            <span class="accountName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>收款银行名称</td>
                        <td colspan="2">
                            <input type="hidden" style="width: 250px;" value="中信银行上海支行" id="receiveBankName" name="receiveBankName" placeholder="">
                            <span>中信银行</span>
                            <a style="margin-left:20px" class="common-tooltip" title="暂只支持中信银行，更多银行后期逐步开放！" href="javascript: void(0);">提示</a>
                            <span data-for="receiveBankName" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <!--
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>收款银行地址</td>
                        <td colspan="2">
                            <input type="text" style="width: 250px;" id="receiveBankAddress" name="receiveBankAddress">
                            <span data-for="receiveBankAddress" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    -->
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>支付密码</td>
                        <td>
                            <input type="password" style="width: 250px;" id="payPassword" name="payPassword">
                        </td>
                        <td>
                            <span data-for="payPassword" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <!--
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>验证码</td>
                        <td colspan="2">
                            <input type="text" style="width: 100px;" name="verifyCode" id="verifyCode" >
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
                            <a class="default-link confirm-link withdrawNotice submit" href="javascript:void(0);">提交</a><a class="default-link back-link cancel back" href="javascript:void(0);">返回</a>
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
