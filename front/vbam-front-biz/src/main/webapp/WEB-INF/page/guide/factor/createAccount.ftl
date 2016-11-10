<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="回款账户申请" jsFiles=["common/cana.util.js"] cssFiles=["css/login.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "账户列表" removeExtHeader = false removeExtFooter = false showMenu=false>

<#assign imagePath="${mediaserver}imageservice?mediaImageId=" />
<div class="main-registion">
    <div class="registion-wrap">
        <div class="registion-title">回款账户申请（第一步）</div>
        <div class="firstEntry-content">
            <table class="registion-tab">
                <colgroup>
                    <col width="150px">
                    <col width="250px">
                    <col width="250px">
                    <col width="250px">
                </colgroup>
                <tbody>
                <tr>
                    <th rowspan="7">&nbsp;</th>
                    <td class="registionRow-til">
                        <span class="redFalg">*</span>企业名称
                    </td>
                    <td>${(customerDTO.companyName)!}</td>
                    <td></td>
                </tr>
                <tr>
                    <td class="registionRow-til"><span class="redFalg">*</span>组织机构代码</td>
                    <td>
                        <span>${(customerDTO.organizationCode)!}</span>
                    </td>
                    <td>
                        <a class="readLink" target="_blank" href="${imagePath+customerDTO.organizationCodeCertificateMediaId!}">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til"><span class="redFalg">*</span>营业执照号码</td>
                    <td>
                        <span>${(customerDTO.businessLicenceCode)!}</span>
                    </td>
                    <td>
                        <a class="readLink" target="_blank" href="${imagePath+customerDTO.businessLicenceMediaId!}">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til"><span class="redFalg">*</span>税务登记证号码</td>
                    <td>
                        <span>${(customerDTO.taxRegistrationCertificateCode)!}</span>
                    </td>
                    <td>
                        <a class="readLink" target="_blank" href="${imagePath+customerDTO.taxRegistrationCertificateMediaId!}">查看</a>
                    </td>
                </tr>
                <tr>
                    <td class="registionRow-til"><span class="redFalg">*</span>法定代表人身份证</td>
                    <td>

                    </td>
                    <td>
                        <a class="readLink" target="_blank" href="${imagePath+customerDTO.legalPersonIdentityCardFrontMediaId!}">查看正面</a>
                        <a class="readLink" target="_blank" href="${imagePath+customerDTO.legalPersonIdentityCardBackMediaId!}">查看背面</a>
                    </td>
                </tr>
                <tr>
					<td class="registionRow-til"><span class="redFalg">*</span>申请账户数</td>
					<td colspan="2">
						<span name="accountNumber">1</span>
					</td>
				</tr>
                <tr>
                    <td></td>
                    <td colspan="2" style="padding-top:30px;">
                        <a class="reg-submit" href="javascript:void(0);"style="width: 180px;" id="createAccount">立即开户</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
$(function(){
	$("#createAccount").click(createAccount);
});
function createAccount() {
	$(this).unbind();
	$object = $(this);
	$.ajax({
    	url: basepath + "/guide/createAccount",
    	method: "POST",
    	data : {},
    	success : function(data) {
    		if (data.status == "SUCCESS") {
    			cana.success(data.message)
    			setTimeout("$('.btn').click()", 500)
				location.reload();
    		} else {
    			$object.bind("click", createAccount);
    			cana.alert(data.message);
    		}
    	}
    });
}
</script>
</@hb.htmlBase>