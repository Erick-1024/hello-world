<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="代开户申请" jsFiles=["common/jquery.form.min.js","facade/formValidatorRules.js","page/account/apply/accountInput.js", "page/account/apply/agentApply.js", "common/formValidator.js", "js/common/ajaxfileupload.js","common/cana.util.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "代开户申请" removeExtHeader = false removeExtFooter = false>
	
<div class="main-container">
    <section class="whiteBg">
    	<div class="accountList-download">
            <a title="点击下载&mdash;融资客户开户.doc" download="融资客户开户" class="applyAut-link" target="_blank" href="${mediaserver}imageservice?mediaImageId=${finaceApplyFilesId!}&mediaType=download">下载融资客户开户文件</a>
            <i class="icon-download"></i>
        </div>
        <div class="accountList-wrap">
        <div class="accountList-title">代申请账户</div>
        <form method="post" action="agent" id="agent-apply-form" name="agent-apply-form">
            <div style="display: block;" class="applyAut-wrap01">
                <div class="accountList-content">
                    <div class="noticeInfo-wrap" style="font-size:11px;">
        				<p>注：1、请准确填写以下信息，以免审核不通过</p> 
        				<p style="text-indent: 2em;">2、仅支持jpg、jpeg、png、pdf格式的图片上传，且图片大小不超过5M</p>
       				</div>
                    <table class="applyAut-instead-table">
                        <colgroup>
                            <col width="150px">
                            <col width="250px">
                            <col width="230px">
                            <col width="150px">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th rowspan="11">企业信息</th>
                            <td class="applyAut-table-til"><span class="redFalg">*</span>授权书</td>
                            <td class="tageBox">
                                <input type="file" class="frontage image ignore" name="image" id="file1" accept="image/jpeg,image/png,application/pdf">
								<a class="frontage-link file" href="javascript:void(0);">上传图片</a>
								<a class="tageNotice registe-link" href="javascript:void(0);"></a>
								<a class="tageNotice download-link" target="_blank" href="${mediaserver}imageservice?mediaImageId=${authorizationLetterTemplateId!}&mediaName=template.doc&mediaType=download">下载模板</a>
								<input class="image-id" name="authorizationLetterId" value="" type="hidden">
								<br/>
								<span data-for="authorizationLetterId" class="k-invalid-msg"></span>
                            </td>
                            <td class="tageNote"></td>
                        </tr>
                        <tr>
                            <td class="applyAut-table-til"><span class="redFalg">*</span>客户类型</td>
                            <td>
                                <select class="selectWrap" name="userType" id="userType" data-role="dropdownlist">
                                    <#if userTypes??>
	                                    <#list userTypes as userType>
	                                    	<option value=${userType.name()}>${userType.desc()}</option>
										</#list>
									</#if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>企业名称</td>
                            <td colspan="1">
                                <input type="text" name="companyName" id="companyName">
                                <br/>
                                <span data-for="companyName" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>组织机构代码</td>
                            <td colspan="1">
                               <input type="text" name="organizationCode" id="organizationCode">
                               <br/>
                               <span data-for="organizationCode" class="k-invalid-msg"></span>
                            </td>
                            <td class="tageBox">
                                <input type="file" class="frontage image" name="image" id="file2" accept="image/jpeg,image/png,application/pdf">
	                        	<a class="frontage-link file" href="javascript:void(0);">上传图片</a>
	                        	<a class="tageNotice registe-link" href="javascript:void(0);"></a>
	                        	<input class="image-id" name="organizationCodeCertificateMediaId" value="" type="hidden">
	                        	<br/>
	                        	<span data-for="organizationCodeCertificateMediaId" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>营业执照号码</td>
                            <td colspan="1">
                                <input type="text" name="businessLicenceCode" id="businessLicenceCode">
                                <br/>
                               <span data-for="businessLicenceCode" class="k-invalid-msg"></span>
                            </td>
                             <td class="tageBox">
                                <input type="file" class="frontage image" name="image" id="file3" accept="image/jpeg,image/png,application/pdf">
	                        	<a class="frontage-link file" href="javascript:void(0);">上传图片</a>
	                        	<a class="tageNotice registe-link" href="javascript:void(0);"></a>
	                        	<input class="image-id" name="businessLicenceMediaId" value="" type="hidden">
	                        	<span data-for="businessLicenceMediaId" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>税务登记证号码</td>
                            <td colspan="1">
                                <input type="text" name="taxRegistrationCertificateCode" id="taxRegistrationCertificateCode">
                                <br/>
                                <span data-for="taxRegistrationCertificateCode" class="k-invalid-msg"></span>
                            </td>
                            <td class="tageBox">
                                <input type="file" class="frontage image" name="image" id="file4" accept="image/jpeg,image/png,application/pdf">
	                        	<a class="frontage-link file" href="javascript:void(0);" >上传图片</a>
	                        	<a class="tageNotice registe-link" href="javascript:void(0);"></a>
	                        	<input class="image-id" name="taxRegistrationCertificateMediaId" value="" type="hidden">
	                        	<br/>
	                        	<span data-for="taxRegistrationCertificateMediaId" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>上传法定代表人身份证</td>
                            <td class="tageBox" colspan="2">
                            	<div class="reditCard clearfix">
                                    <div class="reditCard-left">
                                        <input type="file" class="frontage image" name="image" id="file5" accept="image/jpeg,image/png,application/pdf">
	                        			<a class="frontage-link file" href="javascript:void(0);" >上传正面</a>
	                        			<label class="tageNotice">
                                            <a class="registe-link" href="javascript:void(0);"></a>
                                            <span data-for="legalPersonIdentityCardFrontMediaId" class="k-invalid-msg"></span>
                                        </label>
	                        			<input class="image-id" name="legalPersonIdentityCardFrontMediaId" value="" type="hidden">
                                    </div>
                                    <div class="reditCard-right">
                                        <input type="file" class="frontage image" name="image" id="file6" accept="image/jpeg,image/png,application/pdf">
	                        			<a class="frontage-link file" href="javascript:void(0);" >上传背面</a>
	                        			<label class="tageNotice">
                                            <a class="registe-link" href="javascript:void(0);"></a>
                                            <span data-for="legalPersonIdentityCardBackMediaId" class="k-invalid-msg"></span>
                                        </label>
	                        			<input class="image-id" name="legalPersonIdentityCardBackMediaId" value="" type="hidden">
                                    </div>
                                </div>
                            </td>
                            <td class="tageNote">
                                <span></span>
                            </td>
                        </tr>
                    </tbody></table>
                </div>
                <div class="accountList-content">
                    <table class="applyAut-instead-table">
                        <colgroup>
                            <col width="150px">
                            <col width="250px">
                            <col width="230px">
                            <col width="150px">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th rowspan="8">客户联系人信息</th>
                            <td class="applyAut-table-til"><span class="redFalg">*</span>联系人</td>
                            <td colspan="1">
                                <input type="text" name="contactName" id="contactName">
                                <br/>
                               <span data-for="contactName" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>上传身份证</td>
                            <td class="tageBox" colspan="2">
                            	<div class="reditCard clearfix">
                                    <div class="reditCard-left">
                                        <input type="file" class="frontage image" name="image" id="file7" accept="image/jpeg,image/png,application/pdf">
	                        			<a class="frontage-link file" href="javascript:void(0);" >上传正面</a>
	                        			<label class="tageNotice">
                                            <a class="registe-link" href="javascript:void(0);"></a>
                                            <span data-for="contactIdentityCardFrontMediaId" class="k-invalid-msg"></span>
                                        </label>
	                        			<input class="image-id" name="contactIdentityCardFrontMediaId" value="" type="hidden">
                                    </div>
                                    <div class="reditCard-right">
                                        <input type="file" class="frontage image" name="image" id="file8" accept="image/jpeg,image/png,application/pdf">
	                        			<a class="frontage-link file" href="javascript:void(0);" >上传背面</a>
	                        			<label class="tageNotice">
                                            <a class="registe-link" href="javascript:void(0);"></a>
                                            <span data-for="contactIdentityCardBackMediaId" class="k-invalid-msg"></span>
                                        </label>
	                        			<input class="image-id" name="contactIdentityCardBackMediaId" value="" type="hidden">
                                    </div>
                                </div>
                            </td>
                            <td class="tageNote">
                                <span></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til">联系人职称</td>
                            <td colspan="1">
                               <input type="text" name="jobTitle" id="jobTitle">
                               <br/>
                               <span data-for="jobTitle" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>手机号码</td>
                            <td colspan="1">
                                <input type="text" name="contactTel" id="contactTel">
                                <br/>
                                <span data-for="contactTel" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="accountList-table-til"><span class="redFalg">*</span>电子邮箱</td>
                            <td colspan="1">
                                <input type="email" name="contactMail" id="contactMail">
                                <br/>
                                <span data-for="contactMail" class="k-invalid-msg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2" style="padding-top:30px;">
                                <a class="default-link confirm-link applyAut-next" href="javascript:void(0);" style="width: 200px;">下一步</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <#assign labels = [[["开户类型","accountType"],["一般账户","GENERAL"],["专用账户","SPECIAL"]],
                               [["选择监管账户所属","supervisorType"],["资金方","FACTOR"],["融资客户","FINACE"]],
			                   [["选择监管账户",""],["新建",""],["录入",""]],
			                   [["设为默认还款账户","isDefaultRepayment"],["是","true"],["否","false"]],
			                   [["关联买方企业",""],["手动录入",""],["Excel导入",""]]] />
            <#include "accountInput.ftl" />
            </form>
        </div>
    </section>
</div>
</@hb.htmlBase>