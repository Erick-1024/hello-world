<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="企业审核" jsFiles=["page/user/review.js"] cssFiles=["css/userManage.css"] localCssFiles=[] 
	curTopMenu = "用户管理" curSubMenu = lastMenu removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="incUser-wrap">
            <div class="incUser-title">企业用户详情</div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="200px">
                        <col width="320px">
                        <col width="80px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="2">账号信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>用户名</td>
                        <td colspan="3">${customerReviewDTO.username!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>客户类型</td>
                        <td colspan="3">${customerReviewDTO.userType.desc()}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="200px">
                        <col width="320px">
                        <col width="80px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="5">联系人信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>联系人</td>
                        <td colspan="3">${customerReviewDTO.contactName!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>身份证</td>
                        <td></td>
                        <td>
                        	<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.contactIdentityCardFrontMediaId!}" target="_blank">正面</a>
                        	<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.contactIdentityCardBackMediaId!}" target="_blank">背面</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til">联系人职称</td>
                        <td colspan="3">${customerReviewDTO.jobTitle!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>手机号码</td>
                        <td colspan="3">${customerReviewDTO.contactTel!}</td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>电子邮箱</td>
                        <td colspan="3">${customerReviewDTO.contactMail!}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="200px">
                        <col width="320px">
                        <col width="80px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="6">企业信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>企业名称</td>
                        <td colspan="2">${customerReviewDTO.companyName!}</td>
                        <td>
                            <div class="radioContent">
                            	<input name="checkCompanyName" value="false" type="hidden" id="checkCompanyName">
                                <label class="radio" id="companyNameTrue">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">一致</span>
                                </label>
                                <label class="radio active" id="companyNameFalse">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不一致</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>组织机构代码</td>
                        <td>${customerReviewDTO.organizationCode!}</td>
                        <td><a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.organizationCodeCertificateMediaId!}" target="_blank">查看</a></td>
                        <td>
                            <div class="radioContent">
                            	<input name="checkOrganizationCode" value="false" type="hidden" id="checkOrganizationCode">
                                <label class="radio" id="organizationCodeTrue">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">一致</span>
                                </label>
                                <label class="radio active" id="organizationCodeFalse">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不一致</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>营业执照号码</td>
                        <td>${customerReviewDTO.businessLicenceCode!}</td>
                        <td><a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.businessLicenceMediaId!}" target="_blank">查看</a></td>
                        <td>
                            <div class="radioContent">
                            	<input name="checkBusinessLicenceCode" value="false" type="hidden" id="checkBusinessLicenceCode">
                                <label class="radio" id="businessLicenceCodeTrue">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">一致</span>
                                </label>
                                <label class="radio active" id="businessLicenceCodeFalse">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不一致</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>税务登记证号码</td>
                        <td>${customerReviewDTO.taxRegistrationCertificateCode!}</td>
                        <td><a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.taxRegistrationCertificateMediaId!}" target="_blank">查看</a></td>
                        <td>
                            <div class="radioContent">
                            	<input name="checkTaxRegistrationCertificateCode" value="false" type="hidden" id="checkTaxRegistrationCertificateCode">
                                <label class="radio" id="taxRegistrationCertificateCodeTrue">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">一致</span>
                                </label>
                                <label class="radio active" id="taxRegistrationCertificateCodeFalse">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不一致</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>法定代表人身份证</td>
                        <td></td>
                        <td>
                       		<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.legalPersonIdentityCardFrontMediaId!}" target="_blank">正面</a>
                       		<a class="incUser-link" href="${mediaserver}imageservice?mediaImageId=${customerReviewDTO.legalPersonIdentityCardBackMediaId!}" target="_blank">背面</a>
                        </td>
                        <td>
                            <div class="radioContent">
                            	<input name="checkLegalPersonIdentityCard" value="false" type="hidden" id="checkLegalPersonIdentityCard">
                                <label class="radio" id="legalPersonIdentityCardTrue">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">一致</span>
                                </label>
                                <label class="radio active" id="legalPersonIdentityCardFalse">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不一致</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til">其他</td>
                        <td colspan="2"><input type="text" name="auditMessage" id="auditMessage" style="width: 300px;"></td>
                        <td>
                            <div class="radioContent">
                            	<input name="checkOthers" value="false" type="hidden" id="checkOthers">
                                <label class="radio" id="othersTrue">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">一致</span>
                                </label>
                                <label class="radio active" id="othersFalse">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">不一致</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="incUser-content">
                <table class="incUser-table">
                    <colgroup>
                        <col width="150px">
                        <col width="200px">
                        <col width="320px">
                        <col width="80px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="3">审核信息</th>
                        <td class="incUser-table-til"><span class="redStar">*</span>审核结果</td>
                        <td colspan="3">
                       		<span id="auditResult">不通过</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="incUser-table-til"><span class="redStar">*</span>用户所属角色</td>
                        <td colspan="3">
                            <select id="roleId" name="roleId" class="selectWrap" style="width:180px" disabled="disabled">
                                <#list roles?keys as key>
-                               	<option value=${key}>${roles[key]}</option>
-                               </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span id="id" name="id" data-userid="${customerReviewDTO.id!}"></span></td>
                        <td colspan="3" style="padding-top:20px;">
                            <a class="default-link confirm-link submit-link" href="javascript:void(0);">提交</a>
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
		var detailAuth = false;
	if(curSubMenu == "全部列表"){
		detailAuth = ${authorizeKey("UM_TOTAL_DETAIL")?string("true", "false")};
	}else if(curSubMenu == "待审核列表"){
		detailAuth = ${authorizeKey("UM_UNAUDIT_DETAIL")?string("true", "false")};
	}
</script>