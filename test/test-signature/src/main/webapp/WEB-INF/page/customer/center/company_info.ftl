<div class="userCenter-content">
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="250">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <#if userType!='CANA'>
                    <tr>
                        <td>组织机构代码</td>
                        <td>
                            <span>${companyInfo.organizationCode!''}</span>
                        </td>
                        <td>
                        	<a class="userCenter-link ori_look"  href="${mediaserver}imageservice?mediaImageId=${companyInfo.organizationCodeCertificateMediaId!}" target="_blank">查看</a>
                            <#if authorizeKey('PC_COMPANY_INFO_UPDATE')><a class="userCenter-link upState-link" id="update_origan" href="javascript:void(0);">更新</a></#if>
                        </td>
                    </tr>
                    <tr>
                        <td>营业执照号码</td>
                        <td>
                            <span>${companyInfo.businessLicenceCode!''}</span>
                        </td>
                        <td>
                        	<a class="userCenter-link bus_look"  href="${mediaserver}imageservice?mediaImageId=${companyInfo.businessLicenceMediaId!}" target="_blank">查看</a>
                            <#if authorizeKey('PC_COMPANY_INFO_UPDATE')><a class="userCenter-link upLicense-link" href="javascript:void(0);" id="update_business">更新</a></#if>
                        </td> 
                    </tr>
                    <tr>
                        <td>税务登记证号码</td>
                        <td>
                            <span>${companyInfo.taxRegistrationCertificateCode!''}</span>
                        </td>
                        <td>
                        	<a class="userCenter-link tax_look"  href="${mediaserver}imageservice?mediaImageId=${companyInfo.taxRegistrationCertificateMediaId!}" target="_blank">查看</a>
                            <#if authorizeKey('PC_COMPANY_INFO_UPDATE')> <a class="userCenter-link updateTax-link" href="javascript:void(0);" id="update_tax">更新</a></#if>
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <td>客户类型</td>
                        <td>
                            <span>${(companyInfo.userType.desc())!}</span>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>所属角色</td>
                        <td>
                            <span>${companyInfo.roleName!''}</span>
                        </td>
                        <td>
                            <a class="userCenter-link" href="${basepath}/user/gotoCompanyRoleDetails?roleId=${companyInfo.roleId!''}&type=company" target="_blank">详情</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
 </div>