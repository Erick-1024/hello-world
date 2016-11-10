<#import "/common/htmlBase.ftl" as hb>
<#if lastMenu = "审核列表">
	<#assign reviewAuthKey = "AM_APPLY_REVIEW">
</#if>
<#if lastMenu = "待审核列表">
	<#assign reviewAuthKey = "AM_PENDING_AUDIT_REVIEW">
</#if>
<#if pageType=="pageDetail">
	<#assign pageDesc = "开户详情">
<#else>
	<#assign pageDesc = "开户审核">
</#if>
	<@hb.htmlBase title=pageDesc jsFiles=["page/account/apply/detail.js","common/cana.util.js"] cssFiles=["css/account.css"] localCssFiles=[] 
		curTopMenu = "账户管理" curSubMenu = lastMenu removeExtHeader = false removeExtFooter = false>
<#macro printAuditItem radioId pageType applyStatus checkResult>
	<#if radioId =="checkAuthorizationLetter" || radioId =="checkUserType">
		<#assign validMessage = "有效"/>
		<#assign invalidMessage = "无效"/>
	<#else>
		<#assign validMessage = "一致"/>
		<#assign invalidMessage = "不一致"/>
	</#if>
    <#if pageType=="pageReview">
    	<div class="radioContent">
    		<input name="${radioId!}" value="false" type="hidden" id="${radioId!}">
        	<label class="radio" id="${radioId!}True">
        		<span class="radioIcon"></span>
            	<span class="labelFonts">${validMessage!}</span>
        	</label>
        	<label class="radio active" id="${radioId!}False">
           		<span class="radioIcon"></span>
            	<span class="labelFonts">${invalidMessage!}</span>
        	</label>
    	</div>
    <#else>
    	<#if applyStatus != "PENDINGAUDIT">
    		<span>${checkResult?string(validMessage,invalidMessage)}</span>
    	</#if>
	</#if>
</#macro>

<div class="main-container">
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">${pageDesc!}</div>
	            <div class="accountList-content">
	                <table class="accountList-table">
	                    <colgroup>
	                        <col width="150px">
	                        <col width="250px">
	                        <col width="250px">
	                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT">
	                       		<col width="100px">
	                       	<#else>
	                       		<col width="200px">
	                       	</#if>
	                        <col width="120px">
	                    </colgroup>
	                    <tbody>
	                    <tr>
	                        <th>账户信息</th>
	                        <td class="accountList-table-til">用户名</td>
	                        <td>${(accountApplyDTO.username)!''}</td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
            <div class="accountList-content">
                <table class="accountList-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT">
                       		<col width="100px">
                       	<#else>
                       		<col width="200px">
                       	</#if>
                        <col width="120px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="5">联系人信息</th>
                        <td class="accountList-table-til"><span class="redStar">*</span>联系人</td>
                        <td>${(accountApplyDTO.contactName)!''}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>身份证</td>
                        <td></td>
                        <td>
                            <a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.contactIdentityCardFrontMediaId)!''}" target="_blank">正面</a>
                        	<a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.contactIdentityCardBackMediaId)!''}" target="_blank">背面</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til">联系人职称</td>
                        <td>${(accountApplyDTO.contactJobTitle)!''}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>手机号码</td>
                        <td>${(accountApplyDTO.contactTel)!''}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>电子邮箱</td>
                        <td>${(accountApplyDTO.contactMail)!''}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="accountList-content">
                <table class="accountList-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT">
                       		<col width="100px">
                       	<#else>
                       		<col width="200px">
                       	</#if>
                        <col width="120px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="4">开户信息</th>
                        <td class="accountList-table-til"><span class="redStar">*</span>授权书</td>
                        <td></td>
                        <#if (accountApplyDTO.authorizationLetterId)??>
                        	<#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
		                        <td>
		                        	<@printAuditItem radioId="checkAuthorizationLetter" pageType=pageType applyStatus=accountApplyDTO.applyStatus
		                        	checkResult=(accountApplyDTO.auditDetail.checkAuthorizationLetter)! />
		                        </td>
	                        </#if>
	                        <td>
	                        	<a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.authorizationLetterId)!''}" target="_blank">查看</a>
	                        </td>
	                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
	                        	<td></td>
	                        </#if>
	                    </#if>
                    </tr>
                    <tr>
                        <td class="accountList-table-til">代办企业</td>
                        <td>${(accountApplyDTO.agentCompanyName)!''}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <#if accountApplyDTO.accountType == "GENERAL">
                    	<#if accountApplyDTO.supervisionAccountId?exists>
                    		<tr>
	                        <td class="accountList-table-til">一般账户户名</td>
	                        <td>${(accountApplyDTO.supervisionAccountName)!''}</td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td class="accountList-table-til">一般账户账号</td>
	                        <td>${(accountApplyDTO.supervisionAccountNo)!''}</td>
	                        <td></td>
	                        <td></td>
	                    </tr>
                    	<#else>
	                    <tr>
	                        <td class="accountList-table-til">一般账户数</td>
	                        <td>${(accountApplyDTO.accountNumber)!''}</td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    </#if>
	                </#if>
	                <#if accountApplyDTO.accountType == "SPECIAL">
	                	<tr>
	                        <td class="accountList-table-til">专用账户数</td>
	                        <td>
		                        <#if accountApplyDTO.buyerNames?? >
									${accountApplyDTO.buyerNames?size}
								<#else>
									${(accountApplyDTO.accountNumber)!''}
								</#if>
							</td>
							<td></td>
							<td></td>
	                    </tr>
	                    <tr>
	                        <td class="accountList-table-til" style="vertical-align: text-top">买方企业名单</td>
	                        <td style="vertical-align: text-top">
	                        	<div id="clients" class="clientList">
	                        	<#if (accountApplyDTO.buyerNames)?? >
									<#list accountApplyDTO.buyerNames as buyerName>
                        				<div>${buyerName!''}</div>
									</#list>
								</#if>
								</div>
							</td>
	                        <td style="vertical-align: text-top">
								<a id="switchBuyers" class="accountList-link checkAll" href="javascript:void(0);">查看更多</a>
	                        </td>
	                        <td style="vertical-align: text-top"></td>
	                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <div class="accountList-content">
                <table class="accountList-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT">
                       		<col width="100px">
                       	<#else>
                       		<col width="200px">
                       	</#if>
                        <col width="120px">
                    </colgroup>
                    <tbody>
                    <tr>
						<th rowspan="7">企业信息</th>
                        <td class="accountList-table-til"><span class="redStar">*</span>客户类型</td>
                        <td>${(accountApplyDTO.userTypeDesc)!''}</td>
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
	                        <td>
	                        	<@printAuditItem radioId="checkUserType" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        	checkResult=(accountApplyDTO.auditDetail.checkUserType)! />
	                        </td>
	                    </#if>
                        <td></td>
                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
							<td></td>
						</#if>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>企业名称</td>
                        <td>${(accountApplyDTO.companyName)!''}</td>
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
	                        <td>
	                        	<@printAuditItem radioId="checkCompanyName" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        	checkResult=(accountApplyDTO.auditDetail.checkCompanyName)! />
	                        </td>
                        </#if>
                        <td></td>
                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
							<td></td>
						</#if>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>组织机构代码</td>
                        <td>${(accountApplyDTO.organizationCode)!''}</td>
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
	                        <td>
	                        	<@printAuditItem radioId="checkOrganizationCode" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        		checkResult=(accountApplyDTO.auditDetail.checkOrganizationCode)! />
	                        </td>
	                    </#if>
                        <td>
                        	<a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.organizationCodeCertificateMediaId)!''}" target="_blank">查看</a>
                        </td>
                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
							<td></td>
						</#if>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>营业执照号码</td>
                        <td>${(accountApplyDTO.businessLicenceCode)!''}</td>
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
	                        <td>
	                        	<@printAuditItem radioId="checkBusinessLicenceCode" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        		checkResult=(accountApplyDTO.auditDetail.checkBusinessLicenceCode)! />
	                        </td>
                        </#if>
                        <td>
                            <a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.businessLicenceMediaId)!''}" target="_blank">查看</a>
                        </td>
                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
							<td></td>
						</#if>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>税务登记证号码</td>
                        <td>${(accountApplyDTO.taxRegistrationCertificateCode)!''}</td>
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
	                        <td>
	                        	<@printAuditItem radioId="checkTaxRegistrationCertificateCode" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        		checkResult=(accountApplyDTO.auditDetail.checkTaxRegistrationCertificateCode)! />
	                        </td>
	                    </#if>
                        <td>
                            <a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.taxRegistrationCertificateMediaId)!''}" target="_blank">查看</a>
                        </td>
                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
							<td></td>
						</#if>
                    </tr>
                    <tr>
                        <td class="accountList-table-til"><span class="redStar">*</span>法定代表人身份证</td>
                        <td></td>
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT" || pageType != "pageDetail">
	                        <td>
	                        	<@printAuditItem radioId="checkLegalPersonIdentityCard" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        		checkResult=(accountApplyDTO.auditDetail.checkLegalPersonIdentityCard)! />
	                        </td>
                        </#if>
                        <td>
                            <a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.legalPersonIdentityCardFrontMediaId)!''}" target="_blank">正面</a>
                        	<a class="accountList-link" href="${mediaserver}imageservice?mediaImageId=${(accountApplyDTO.legalPersonIdentityCardBackMediaId)!''}" target="_blank">背面</a>
                        </td>
                        <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageDetail">
							<td></td>
						</#if>
                    </tr>
                    <#if pageType !="pageDetail" || accountApplyDTO.applyStatus != "PENDINGAUDIT">
	                    <tr>
	                        <td class="accountList-table-til">其他</td>
	                        <td>
	                        	<#if pageType=="pageReview">
	                        		<input type="text" name="auditMessage" id="auditMessage" style="width: 180px;">
	                        	<#else>
									${(accountApplyDTO.auditDetail.auditMessage)!''}
								</#if>
							</td>
	                        <td>
	                        	<@printAuditItem radioId="checkOthers" pageType=pageType applyStatus=accountApplyDTO.applyStatus
	                        		checkResult=(accountApplyDTO.auditDetail.checkOthers)! />
	                        </td>
							<td></td>
	                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <div class="accountList-content">
                <table class="accountList-table">
                    <colgroup>
                        <col width="150px">
                        <col width="250px">
                        <col width="250px">
                        <#if accountApplyDTO.applyStatus != "PENDINGAUDIT">
                       		<col width="100px">
                       	<#else>
                       		<col width="200px">
                       	</#if>
                        <col width="120px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="4">审核信息</th>
                        <td class="accountList-table-til" id="auditResultDesc"><span class="redStar">*</span>审核结果</td>
                        <td>
							<#if accountApplyDTO.applyStatus == "PENDINGAUDIT">
								<#if pageType == "pageDetail">
                    				<span id="auditResult">待审核</span>
                    			<#else>
                    				<span id="auditResult">不通过</span>
                    			</#if>
                    		<#else>
								${(accountApplyDTO.auditResultDesc)!''}
							</#if>
							
						</td>
						<td></td>
						<td></td>
                    </tr>
                    <#if accountApplyDTO.applyStatus == "PENDINGAUDIT" && pageType == "pageReview" && roles??>
		                    <tr>
		                        <td class="accountList-table-til"><span class="redStar">*</span>用户所属角色</td>
		                        <td>
		                            <select id="roleId" name="roleId" class="selectWrap" style="width:180px" disabled="disabled">
		                                <#list roles?keys as key>
			                             	<option value=${key}>${roles[key]}</option>
		                            	</#list>
		                            </select>
		                        </td>
		                        <td></td>
		                        <td></td>
		                    </tr>
					</#if>					
					<#if accountApplyDTO.applyStatus == "ACCEPTED" && pageType == "pageDetail" && accountApplyDTO.auditDetail.roleName??>
						<tr>
	                        <td class="accountList-table-til"><span class="redStar">*</span>用户所属角色</td>
	                        <td>
	                            ${(accountApplyDTO.auditDetail.roleName)!}
	                        </td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                </#if>
                    <#if accountApplyDTO.applyStatus == "ACCEPTED">
	                    <tr>
	                        <td class="accountList-table-til"><span class="redStar">*</span>银行账户数</td>
	                        <td>
	                        	<#if (accountApplyDTO.accounts)??>
									${(accountApplyDTO.accounts)?size}
								</#if>
							</td>
							<td></td>
							<td></td>
	                    </tr>
	                    <tr>
	                        <td colspan="4">
	                        	<table id="applyTheadSnd">
	                                <colgroup>
	                                    <col width="40">
	                                    <col width="70">
	                                    <col width="150">
	                                    <col width="160">
	                                    <col width="100">
	                                    <col width="10">
	                                </colgroup>
	                                <thead>
	                                <tr>
	                                    <th>序号</th>
	                                    <th>账户类型</th>
	                                    <th>账户名称</th>
	                                    <th>银行账号</th>
	                                    <th>买方企业名称</th>
	                                    <th></th>
	                                </tr>
	                                </thead>
	                            </table>
	                            <div class="account-auditTabSnd">
	                                <table>
	                                    <colgroup>
	                                        <col width="40">
	                                        <col width="70">
	                                        <col width="150">
	                                        <col width="160">
	                                        <col width="100">
	                                    </colgroup>
	                                    <tbody>
		                                    <#if accountApplyDTO.accounts??>
	                                    		<#list accountApplyDTO.accounts as account>
	                                    			<tr>
		                                        		<td style="text-align:center">${account_index?if_exists+1}</td>
		                                        		<td>${(account.accountTypeDesc)!''}</td>
		                                        		<td>${(account.accountName)!''}</td>
		                                        		<td id="accountNo" style="text-align:center">${(account.accountNo)!''}</td>
		                                        		<td>${(account.buyerName)!''}</td>
		                                    		</tr>
	                                    		</#list>
	                                    	</#if>
		                                </tbody>
	                                </table>
	                            </div>
	                        </td>
	                    </tr>
                    </#if>
                    <tr>
                    	<td><span id="id" name="id" data-applyid="${accountApplyDTO.id!}"></span></td>
                        <#if accountApplyDTO.applyStatus=="PENDINGAUDIT" && authorizeKey(reviewAuthKey)>
                        	<#if pageType == "pageReview">
		                        <td colspan="3" style="padding-top:20px;">
		                            <a class="default-link confirm-link submitReviewButton" href="javascript:submitReview();">提交</a>
		                        </td>
		                    <#else>
		                    	<td colspan="3" style="padding-top:20px;">
		                            <a class="default-link confirm-link" href="${basepath}/account/apply/review?applyId=${accountApplyDTO.id!}&curSubMenu=${lastMenu}">审核</a>
		                        </td>
		                    </#if>
	                    </#if>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>
<#include "../tipBoxTemplate.ftl" />
</@hb.htmlBase>
<script>
	var curSubMenu = "${lastMenu}";
</script>