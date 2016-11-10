<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资客户引导页" jsFiles=["js/common/yunda.js","common/jquery.multiDownload.js","common/signature.js","page/guide/yundaex/comfirmInfomation.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = false showMenu=false>

<div class="main-container">
    <div class="bg-box">
        <div class="pro-title"><span class="pro-title-left">基础信息</span></div>
        <div class="tb-box" style="position:relative;">
            <table class="check-table">
                <colgroup>
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>网点名称</th>
                    <td>${(customerAppplyDTO.stationName)!""}</td>
                    <th>站点编号</th>
                    <td>${(customerAppplyDTO.stationNo)!""}</td>
                    <th>经营地址</th>
                    <td>${(customerAppplyDTO.detailAddress)!""}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>借款人姓名</th>
                    <td>${(customerAppplyDTO.custName)!""}</td>
                    <th>借款人手机号码</th>
                    <td>${(customerAppplyDTO.custPhone)!""}</td>
                    <th>电子邮箱</th>
                    <td>${(customerAppplyDTO.custEmail)!""}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>组织机构代码证</th>
                    <td>
                        <span class="width60">${(customerAppplyDTO.organizationNo)!""}</span>
                        <a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(customerAppplyDTO.organizationMediaId)!""}">查看</a>
                    </td>
                    <th>营业执照证</th>
                    <td>
                        <span class="width60">${(customerAppplyDTO.businessLicenceNo)!""}</span>
                        <a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(customerAppplyDTO.businessLicenceMediaId)!""}">查看</a>
                    </td>
                    <th>税务登记证</th>
                    <td>
                        <span class="width60">${(customerAppplyDTO.taxRegistrationCertificateNo)!""}</span>
                        <a class="look" target="_blank" href="${mediaserver}imageservice?mediaImageId=${(customerAppplyDTO.taxRegistrationCertificateMediaId)!""}">查看</a>
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">打款账户</span></div>
        <div class="tb-box" style="">
            <table class="check-table">
                <colgroup>
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>银行账户</th>
                    <td>${formatBankAccountNo((customerAppplyDTO.payAccount)!)}</td>
                    <th>银行账户户名</th>
                    <td>${(customerAppplyDTO.payAccountName)!""}</td>
                    <th>开户行名称和地址</th>
                    <td>${(customerAppplyDTO.payAccountAddress)!""}</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">还款账户</span></div>
        <div class="tb-box" style="">
            <table class="check-table">
                <colgroup>
                    <col width="80">
                    <col width="100">
                    <col width="100">
                    <col width="80">
                    <col width="80">
                    <col width="100">
                    <col width="100">
                </colgroup>
                <tbody>
                <tr>
                    <th>监管账户</th>
                    <td><label><input type="radio" value="true" name="nameOne" class="input-radio" checked />开立新账号</label></td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                    <td></td>
                </tr>
                <#if (accounts?size>0)>
                <tr>
                    <th></th>
                    <td><label><input type="radio" value="false" name="nameOne" class="input-radio"/>使用已有账号</label></td>
                    <td style="width:14%;">
                        <select class="sel-one" data-role="dropdownlist"　name="accountNo" id="accountNo">
                            <#list accounts as account>
                            	<option value=${account.accountNo!}>${formatBankAccountNo((account.accountNo)!)}</option>
							</#list>
                        </select>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </#if>
                </tbody>
            </table>
        </div>
        <div class="btn-out-box">
            <div class="btn-box">
            	<div style="display:none">
                	<a class="btn-bto-a" href="javascript:void(0);" id="signatureSoftwateInstall">需要安装安全控件</a><i style="color:#ff0000;">安装完成后需要刷新页面</i>
                </div>
                <br>
                <br>
                <a class="btn-bto-a" href="javascript:void(0);" id="confirmBtn">确认</a><i style="color:#ff0000;">确认后如没有证书，系统会自动安装证书到您的浏览器</i>
            </div>
        </div>
    </div>
</div>
<#include "../tipBoxTemplate.ftl" />
<style>
	.confirm-link{cursor:pointer}
</style>
</@hb.htmlBase>