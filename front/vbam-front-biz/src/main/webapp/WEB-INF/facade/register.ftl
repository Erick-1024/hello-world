<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="注册" jsFiles=["facade/formValidatorRules.js", "common/formValidator.js", "common/imageUpload.js","js/common/ajaxfileupload.js", "facade/register.js"] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true>
<header class="main-header">
    <div class="stairMenuContent">
        <div class="wrap clearfix">
            <div class="navLeft">
                <a class="logo" href="${basepath}">CANA</a>
            </div>
            <div class="headRightOperate">
                <span class="userContent">Hi<a class="goLogin" href="${basepath}/facade/signin">请登录</a></span>
            </div>
        </div>
    </div>
</header>
<div class="main-registion">
    <div class="registion-wrap">
        <div class="registion-title">欢迎注册</div>
        <form method="post" action="register" id="register-form">
	        <div class="registion-content">
		        <div class="noticeInfo-wrap" style="font-size:12px;">
                    <p>注：1、请准确填写以上信息，以免审核不通过；</p>
                	<p style="text-indent: 2em;">2、名称要求6-20位字符，支持字母、数字及“-”、“_”组合</p>
					<p style="text-indent: 2em;">3、仅支持jpg、jpeg、png、pdf格式的图片上传，且图片大小不超过5M</p>
		        </div>
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="260px">
	                    <col width="150px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th rowspan="2">账号信息</th>
	                    <td class="registionRow-til"><span class="redFalg">*</span>用 户 名</td>
	                    <td colspan="2">
	                        <input type="text" name="username" id="username">
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>客户类型</td>
	                    <td colspan="2">
	                        <select class="selectWrap" name="userType" id="userType" data-role="dropdownlist" style="width: 250px;">
                                <#list userTypes as userType>
                                	<#if userType.name()!="CANA">
										<option value=${userType.name()}>${userType.desc()}</option>
									</#if>
								</#list>
                        	</select>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="registion-content">
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="260px">
	                    <col width="150px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th rowspan="8">企业信息</th>
	                    <td class="registionRow-til"><span class="redFalg">*</span>企业名称</td>
	                    <td colspan="2">
	                        <input type="text" name="companyName" id="companyName">
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>组织机构代码</td>
	                    <td>
	                        <input type="text" name="organizationCode" id="organizationCode">
	                    </td>
	                    <td class="tageBox">
	                        <input type="file" class="frontage imageUpload" name="image" id="organizationCodeCertificateMedia" accept="image/jpeg, image/png, application/pdf">
	                        <a class="frontage-link" href="javascript:void(0);">上传图片</a>
	                        <input class="image" name="organizationCodeCertificateMediaId" value="" type="hidden">
	                        <span class="tageNotice"><span class="k-invalid-msg" data-for="organizationCodeCertificateMediaId"></span></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>营业执照号</td>
	                    <td>
	                        <input type="text" name="businessLicenceCode" id="businessLicenceCode">
	                    </td>
	                    <td class="tageBox">
	                        <input type="file" class="frontage imageUpload" name="image" id="businessLicenceMedia" accept="image/jpeg, image/png, application/pdf">
	                        <a class="frontage-link" href="javascript:void(0);">上传图片</a>
	                        <input class="image" name="businessLicenceMediaId" value="" type="hidden">
	                        <span class="tageNotice"><span class="k-invalid-msg" data-for="businessLicenceMediaId"></span></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>税务登记号码</td>
	                    <td>
	                        <input type="text" name="taxRegistrationCertificateCode" id="taxRegistrationCertificateCode">
	                    </td>
	                    <td class="tageBox">
	                        <input type="file" class="frontage imageUpload" name="image" id="taxRegistrationCertificateMedia" accept="image/jpeg, image/png, application/pdf">
	                        <a class="frontage-link" href="javascript:void(0);">上传图片</a>
	                        <input class="image" name="taxRegistrationCertificateMediaId" value="" type="hidden">
	                        <span class="tageNotice"><span class="k-invalid-msg" data-for="taxRegistrationCertificateMediaId"></span></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>上传法定代表人身份证</td>
	                    <td colspan="2">
	                        <div class="reditCard clearfix">
	                            <div class="reditCard-left">
	                                <input type="file" class="frontage imageUpload" name="image" id="legalPersonIdentityCardFrontMedia" accept="image/jpeg, image/png, application/pdf">
	                                <a class="frontage-link" href="javascript:void(0);">上传正面</a>
	                                <input class="image" name="legalPersonIdentityCardFrontMediaId" value="" type="hidden">
	                                <span class="tageNotice"><span class="k-invalid-msg" data-for="legalPersonIdentityCardFrontMediaId"></span></span>
	                            </div>
	                            <div class="reditCard-right">
	                                <input type="file" class="frontageRt imageUpload" name="image" id="legalPersonIdentityCardBackMedia" accept="image/jpeg, image/png, application/pdf">
	                                <a class="frontage-link" href="javascript:void(0);">上传背面</a>
	                                <input class="image" name="legalPersonIdentityCardBackMediaId" value="" type="hidden">
	                                <span class="tageNotice"><span class="k-invalid-msg" data-for="legalPersonIdentityCardBackMediaId"></span></span>
	                            </div>
	                        </div>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="registion-content">
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="260px">
	                    <col width="150px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <th rowspan="5">联系人信息</th>
	                    <td class="registionRow-til"><span class="redFalg">*</span>联系人</td>
	                    <td>
	                        <input type="text" name="contactName" id="contactName">
	                    </td>
	                    <td>
	                        <span></span>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>上传身份证</td>
	                    <td colspan="2">
	                        <div class="reditCard clearfix">
	                            <div class="reditCard-left">
	                                <input type="file" class="frontage imageUpload"  name="image" id="contactIdentityCardFrontMedia" accept="image/jpeg, image/png, application/pdf">
	                                <a class="frontage-link" href="javascript:void(0);">上传正面</a>
	                                <input class="image" name="contactIdentityCardFrontMediaId" value="" type="hidden">
	                                <span class="tageNotice"><span class="k-invalid-msg" data-for="contactIdentityCardFrontMediaId"></span></span>
	                            </div>
	                            <div class="reditCard-right">
	                                <input type="file" class="frontageRt imageUpload" name="image" id="contactIdentityCardBackMedia" accept="image/jpeg, image/png, application/pdf">
	                                <a class="frontage-link" href="javascript:void(0);">上传背面</a>
	                                <input class="image" name="contactIdentityCardBackMediaId" value="" type="hidden">
	                                <span class="tageNotice"><span class="k-invalid-msg" data-for="contactIdentityCardBackMediaId"></span></span>
	                            </div>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til">联系人职称</td>
	                    <td colspan="2">
	                        <input type="text" name="jobTitle" id="jobTitle">
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>手机号码</td>
	                    <td colspan="2">
	                        <input type="text" name="contactTel" id="contactTel">
	                    </td>
	                </tr>
	                <tr>
	                    <td class="registionRow-til"><span class="redFalg">*</span>电子邮箱</td>
	                    <td colspan="2">
	                        <input type="email" name="contactMail" id="contactMail">
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2"></td>
	                    <td colspan="2">
	                        <a class="reg-submit" href="javascript:void(0);">提交审核</a>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
        </form>
    </div>
</div>
</@hb.htmlBase>
