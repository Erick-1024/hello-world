<#import "/common/htmlBase.ftl" as hb>
<#if lastMenu = "企业列表">
	<#assign curTopMenu = "权限管理">
<#else>
	<#assign curTopMenu = "用户管理">
</#if>
<@hb.htmlBase title="企业详情" jsFiles=["page/user/detail.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = curTopMenu curSubMenu = lastMenu removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="incUser-wrap">
            <div class="incUser-title">企业用户详情</div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <col width="120px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="2">账号信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>用户名</td>
                        <td colspan="3">${customerDetailDTO.username!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>客户类型</td>
                        <td colspan="3">${customerDetailDTO.userType.desc()}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <col width="120px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="5">联系人信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>联系人</td>
                        <td colspan="3">${customerDetailDTO.contactName!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>身份证</td>
                        <td></td>
                        <td colspan="2">
                        	<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.contactIdentityCardFrontMediaId!}" target="_blank">正面</a>
                        	<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.contactIdentityCardBackMediaId!}" target="_blank">背面</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til">联系人职称</td>
                        <td colspan="3">${customerDetailDTO.jobTitle!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>手机号码</td>
                        <td colspan="3">${customerDetailDTO.contactTel!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>电子邮箱</td>
                        <td colspan="3">${customerDetailDTO.contactMail!}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <col width="120px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="6">企业信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>企业名称</td>
                        <td colspan="2">${customerDetailDTO.companyName!}</td>
                        <td>
                        	<span id="checkCompanyName" data-checkCompanyName=${customerDetailDTO.checkCompanyName?string}></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>组织机构代码</td>
                        <td>${customerDetailDTO.organizationCode!}</td>
                        <td><a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.organizationCodeCertificateMediaId!}" target="_blank">查看</a></td>
                    	<td>
                    		<span id="checkOrganizationCode" data-checkOrganizationCode=${customerDetailDTO.checkOrganizationCode?string}></span>
                    	</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>营业执照号码</td>
                        <td>${customerDetailDTO.businessLicenceCode!}</td>
                        <td><a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.businessLicenceMediaId!}" target="_blank">查看</a></td>
                    	<td>
                    		<span id="checkBusinessLicenceCode" data-checkBusinessLicenceCode=${customerDetailDTO.checkBusinessLicenceCode?string}></span>
                    	</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>税务登记证号码</td>
                        <td>${customerDetailDTO.taxRegistrationCertificateCode!}</td>
                        <td><a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.taxRegistrationCertificateMediaId!}" target="_blank">查看</a></td>
                    	<td>
                    		<span id="checkTaxRegistrationCertificateCode" data-checkTaxRegistrationCertificateCode=${customerDetailDTO.checkTaxRegistrationCertificateCode?string}></span>
				        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>法定代表人身份证</td>
                        <td></td>
                        <td>
                       		<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.legalPersonIdentityCardFrontMediaId!}" target="_blank">正面</a>
                       		<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerDetailDTO.legalPersonIdentityCardBackMediaId!}" target="_blank">背面</a>
                        </td>
                        <td>
                        	<span id="checkLegalPersonIdentityCard" data-checkLegalPersonIdentityCard=${customerDetailDTO.checkLegalPersonIdentityCard?string}></span>
                    	</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til">其他</td>
                        <td colspan="2">${customerDetailDTO.auditMessage!}</td>
                        <td>
                        <span id="checkOthers" data-checkOthers=${customerDetailDTO.checkOthers?string}></span>
                    	</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <col width="120px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="3">审核信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>审核结果</td>
                        <td colspan="3">
                        	<span id="userStatus" data-userStatus=${customerDetailDTO.userStatus.name()!}></span>
                        	<span id="auditResult"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>用户所属角色</td>
                        <td colspan="3"><span id="roleName">${customerDetailDTO.roleName!}</span></td>
                    </tr>
                    <tr>
                        <td><span id="id" name="id" data-userid="${customerDetailDTO.id!}"></span></td>
                        <td colspan="" style="padding-top:20px;">
                            <a class="default-link confirm-link review-link" href="javascript:void(0);">审核</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>
<script>
	var curSubMenu = "${lastMenu}";
	var reviewAuth = false;
	if(curSubMenu == "全部列表"){
		reviewAuth = ${authorizeKey("UM_TOTAL_AUDIT")?string("true", "false")};
	}else if(curSubMenu == "待审核列表"){
		reviewAuth = ${authorizeKey("UM_UNAUDIT_AUDIT")?string("true", "false")};
	}
</script>
