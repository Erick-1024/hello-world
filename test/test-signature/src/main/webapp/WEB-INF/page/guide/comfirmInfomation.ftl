<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资客户引导页" jsFiles=["page/guide/comfirmInfomation.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = false showMenu=false>

<div class="main-registion">
    <div class="registion-wrap">
        <div class="registion-title">确认信息以及签署合同</div>
        <div class="finaLead-content">
            <table class="finaLead-tab">
                <colgroup>
                    <col width="200px">
                    <col width="200px">
                    <col width="400px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="7">基本规则</th>
                    <td class="registionRow-til">申请公司</td>
                    <td>
                        <span>${customerDTO.companyName!}</span>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til">组织机构代码证</td>
                    <td>
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=${(customerDTO.organizationCodeCertificateMediaId)!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til">营业执照证</td>
                    <td>
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=${(customerDTO.businessLicenceMediaId)!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til">税务登记证</td>
                    <td>
                        <a class="repayment-link" href="${mediaserver}imageservice?mediaImageId=${(customerDTO.taxRegistrationCertificateMediaId)!''}" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til">联系人姓名</td>
                    <td>${customerDTO.contactName!}</td>
                </tr>
                <tr>
                    <td class="registionRow-til">手机号码</td>
                    <td>${customerDTO.contactTel!}</td>
                </tr>
                <tr>
                    <td class="registionRow-til">电子邮箱</td>
                    <td>${customerDTO.contactMail!}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="finaLead-content">
            <table class="finaLead-tab">
                <colgroup>
                    <col width="200px">
                    <col width="200px">
                    <col width="400px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="6">还款信息</th>
                    <td class="registionRow-til">费率</td>
                    <td>0.05% / 日</td>
                </tr>
                <tr>
                    <td class="registionRow-til">逾期管理费率</td>
                    <td>在日费率基础上加收50.00%</td>
                </tr>
                <tr>
                    <td class="registionRow-til">额度</td>
                    <td colspan="2">以CANA给定额度为准</td>
                </tr>
                <tr>
                    <td class="registionRow-til">还款方式</td>
                    <td>账户回款还款</td>
                </tr>
                <tr>
                    <td style="vertical-align: top; padding-top:10px;" class="registionRow-til">监管账户</td>
                    <td style="padding-top:10px;">
                        <div class="finaRadio" id="supervisionAccount">
                            <div class="finaRow">
                                <label class="radio active" name="true">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">开立新账号</span>
                                </label>
                            </div>
                            <#if (accounts?size>0)>
	                            <div class="finaRow">
	                                <label class="radio" name="false">
	                                    <span class="radioIcon"></span>
	                                    <span class="labelFonts">使用已有账号</span>
	                                </label>
	                                <select style="width: 180px; margin-left: 20px;" class="selectWrap" name="accountNo" id="accountNo" data-role="dropdownlist">
	                                    <#list accounts as account>
	                                    	<option value=${account.accountNo!}>${formatBankAccountNo((account.accountNo)!)}</option>
										</#list>
	                                </select>
	                            </div>
                            </#if>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding-top:20px;">
                        <a class="default-link confirm-link">确认</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@hb.htmlBase>