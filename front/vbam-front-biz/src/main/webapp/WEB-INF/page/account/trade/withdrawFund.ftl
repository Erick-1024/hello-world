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
                        <td>
                            <select class="selectWrap" name="receiveBankName" id="receiveBankName" data-role="dropdownlist" style="width: 250px;">
								<option value="中国农业银行">中国农业银行</option>
                            	<option value="中国工商银行">中国工商银行</option>
								<option value="中国建设银行">中国建设银行</option>
								<option value="城市商业银行">城市商业银行</option>
								<option value="中国银行">中国银行</option>
								<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
								<option value="农村商业银行">农村商业银行</option>
								<option value="交通银行">交通银行</option>
								<option value="招商银行">招商银行</option>
								<option value="中信银行">中信银行</option>
								<option value="兴业银行">兴业银行</option>
								<option value="中国光大银行">中国光大银行</option>
								<option value="上海浦东发展银行">上海浦东发展银行</option>
								<option value="中国民生银行">中国民生银行</option>
								<option value="广东发展银行">广东发展银行</option>
								<option value="华夏银行">华夏银行</option>
								<option value="平安银行">平安银行</option>
								<option value="恒丰银行">恒丰银行</option>
								<option value="渤海银行">渤海银行</option>
                            </select>
                        </td>
                        <td>
                            <span data-for="receiveBankName" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">
                        	<span class="redFalg">*</span>省份
                        </td>
                        <td>
                            <select class="selectWrap" name="province" id="province" data-role="dropdownlist" style="width: 250px;">
                                <option value="北京市">北京市</option>
								<option value="天津市">天津市</option>
								<option value="河北省">河北省</option>
								<option value="山西省">山西省</option>
								<option value="内蒙古自治区">内蒙古自治区</option>
								<option value="辽宁省">辽宁省</option>
								<option value="吉林省">吉林省</option>
								<option value="黑龙江省">黑龙江省</option>
								<option value="上海市">上海市</option>
								<option value="江苏省">江苏省</option>
								<option value="浙江省">浙江省</option>
								<option value="安徽省">安徽省</option>
								<option value="福建省">福建省</option>
								<option value="江西省">江西省</option>
								<option value="山东省">山东省</option>
								<option value="河南省">河南省</option>
								<option value="湖北省">湖北省</option>
								<option value="湖南省">湖南省</option>
								<option value="广东省">广东省</option>
								<option value="广西壮族自治区">广西壮族自治区</option>
								<option value="海南省">海南省</option>
								<option value="四川省">四川省</option>
								<option value="重庆市">重庆市</option>
								<option value="贵州省">贵州省</option>
								<option value="云南省">云南省</option>
								<option value="西藏自治区">西藏自治区</option>
								<option value="陕西省">陕西省</option>
								<option value="甘肃省">甘肃省</option>
								<option value="青海省">青海省</option>
								<option value="宁夏回族自治区">宁夏回族自治区</option>
								<option value="新疆维吾尔族自治区">新疆维吾尔族自治区</option>
								<option value="台湾">台湾</option>
								<option value="香港">香港</option>
								<option value="澳门">澳门</option>
                            </select>
                        </td>
                        <td>
                            <span data-for="province" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">
                        	<span class="redFalg">*</span>城市
                        </td>
                        <td>
                            <select class="selectWrap" name="city" id="city" data-role="dropdownlist" style="width: 250px;">
                            </select>
                        </td>
                        <td>
                            <span data-for="city" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">
                        	<span class="redFalg">*</span>收款银行地址
                        </td>
                        <td>
                            <select class="selectWrap" name="receiveAccountName" id="receiveAccountName" data-role="dropdownlist" style="width: 300px;">
                            </select>
                        </td>
                        <td>
                            <span data-for="branchName" class="k-invalid-msg"></span>
                        </td>
                    </tr>
                    <tr>
						<td class="acu-title"><span class="redFalg">*</span>提现金额</td>
						<td>
						    <input class="withdraw-align moneyNum" name="amount" id="amount" type="text" style="width: 250px;" placeholder="￥" transferFundRule required data-required-msg="金额不能为空">
						</td>
						<td style="padding-left: 5px;">元<span data-for="amount" class="k-invalid-msg"></span></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2">大写：<span id="transfer-ch"></span></td>
					</tr>
					<#if accountTradeType == "WITHDRAW_FUND">
					<tr>
						<td class="acu-title">提现手续费</td>
						<td class="withdraw-align" style="text-align:left;">
							<span class="commissionCharge" style="padding-right:5px;">0.00</span>元
							<a style="margin-left:20px;color: #0f8aba;" title="此费用为银行提现手续费,规则如下：&#10;一万以下：5.00&#10;一万至十万：10.00&#10;十万至五十万：15.00&#10;五十万至一百万：20.00&#10;一百万以上：总金额的万分之0.2不超过200.00" href="javascript: void(0);">说明</a>
						</td>
						<td style="padding-left: 5px;"></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2">大写：<span id="commission-ch"></span></td>
					</tr>
					</#if>
					<tr>
						<td class="acu-title">备注</td>
						<td>
						    <input class="" name="remark" id="remark" type="text" style="width: 250px;">
						</td>
						<td></td>
					</tr>
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
<#include 'withdrawFundTemplate.ftl'/>
<#include "../tipBoxTemplate.ftl" />
</@hb.htmlBase>
