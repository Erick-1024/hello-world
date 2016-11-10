<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="开户申请" jsFiles=["js/common/ajaxfileupload.js","common/jquery.form.min.js","page/account/apply/selfApply.js","page/account/apply/accountInput.js","common/cana.util.js"] cssFiles=["css/account.css"] localCssFiles=[] 
curTopMenu = "账户管理" curSubMenu = "开户申请" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <#assign imagePath="${mediaserver}imageservice?mediaImageId=" />
    <form id="selfApply" action="self" method="post" />
	<section class="whiteBg">
		<div class="accountList-wrap">
			<div class="accountList-title">申请账户</div>
			<div class="applyAut-wrap01">
				<div class="accountList-content">
					<table class="applyAut-table">
						<colgroup>
							<col width="30%">
							<col width="40%">
							<col width="30%">
						</colgroup>
						<tbody>
							<tr>
								<td class="applyAut-table-til"><span class="redStar"></span>企业名称</td>
								<td colspan="2">${(customerDTO.companyName)!}</td>
							</tr>
							<tr>
								<td class="accountList-table-til"><span class="redStar"></span>组织机构代码</td>
								<td colspan="1">${(customerDTO.organizationCode)!}</td>
								<td>
								    <a class="accountList-link" target="_blank" href="${imagePath+customerDTO.organizationCodeCertificateMediaId!}">查看</a>
								    <img class="hidden" src="${imagePath+customerDTO.organizationCodeCertificateMediaId!}" />
								</td>
							</tr>
							<tr>
								<td class="accountList-table-til"><span class="redStar"></span>营业执照号码</td>
								<td colspan="1">${(customerDTO.businessLicenceCode)!}</td>
								<td>
								    <a class="accountList-link" target="_blank" href="${imagePath+customerDTO.businessLicenceMediaId!}">查看</a>
								    <img class="hidden" src="${imagePath+customerDTO.businessLicenceMediaId!}" />
								</td>
							</tr>
							<tr>
								<td class="accountList-table-til"><span class="redStar"></span>税务登记证号码</td>
								<td colspan="1">${(customerDTO.taxRegistrationCertificateCode)!}</td>
								<td>
								    <a class="accountList-link" target="_blank" href="${imagePath+customerDTO.taxRegistrationCertificateMediaId!}">查看</a>
								    <img class="hidden" src="${imagePath+customerDTO.taxRegistrationCertificateMediaId!}" />
								</td>
							</tr>
							<tr>
								<td class="accountList-table-til"><span class="redStar"></span>法定代表人身份证</td>
								<td>
								    <a class="accountList-link" target="_blank" href="${imagePath+customerDTO.legalPersonIdentityCardFrontMediaId!}">查看正面</a>
								    <a class="accountList-link" target="_blank" href="${imagePath+customerDTO.legalPersonIdentityCardBackMediaId!}">查看背面</a>
								    <img class="hidden" src="${imagePath+customerDTO.legalPersonIdentityCardFrontMediaId!}" />
								    <img class="hidden" src="${imagePath+customerDTO.legalPersonIdentityCardBackMediaId!}" />
								</td>
								<td><span class="applyAut-table-notice"></span>
								</td>
							</tr>
							<#if customerDTO.userType?? && (customerDTO.userType!="FINACE")>
							<tr>
								<td class="accountList-table-til"><span class="redStar">*</span>申请账户数</td>
								<td colspan="2">
									<select class="selectWrap" name="accountNumber" data-role="dropdownlist" id="factorApplyNumber">
										<option value="1">1</option>
										<#if customerDTO.userType?? && (customerDTO.userType=="FACTOR" || customerDTO.userType=="CANA") >
											<option value="2">2</option>
											<option value="3">3</option>
										</#if>
									</select>
								</td>
							</tr>
							</#if>
							<tr>
								<td></td>
								<td colspan="2" style="padding-top: 30px;">
								<#if customerDTO.userType?? && (customerDTO.userType!="FINACE") >
								<a class="default-link confirm-link createAccount" href="javascript:void(0);"
									id="factorCreateButton" style="width: 200px;">立即开户</a> 
								<#elseif customerDTO.userType?? && customerDTO.userType=="FINACE"><!--FINACE-->
								<a class="default-link confirm-link applyAut-next" href="javascript:void(0);" style="width: 200px;">下一步</a>
								<#else>
								
								</#if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<#if customerDTO.userType?? && customerDTO.userType=="FINACE">
			<#assign labels = [[["开户类型","accountType"],["一般账户","GENERAL"],["专用账户","SPECIAL"]],
			                   [["关联买方企业",""],["手动录入",""],["Excel导入",""]]] />
			<#include "accountInput.ftl" />
			</#if>
		</div>
	</section>
	</form>
</div>
</@hb.htmlBase>
