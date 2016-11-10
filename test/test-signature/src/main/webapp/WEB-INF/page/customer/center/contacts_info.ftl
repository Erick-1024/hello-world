 <div class="userCenter-content hidden">
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="250">
                        <col width="250">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>联系人</td>
                        <td>
                            <span class='cName'>${companyInfo.contactName!''}</span>
                        </td>
                         <#if authorizeKey('PC_CONTACTS_INFO_MODIFY')>
                        <td >
                        	<a class="userCenter-link editName-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    <#--
                    <tr>
                        <td>身份证</td>
                        <td>
                        	<a class="userCenter-link" href="${mediaserver}imageservice?mediaImageId=${companyInfo.contactIdentityCardFrontMediaId!}" target="_blank">正面</a>
                            <a class="userCenter-link" href="${mediaserver}imageservice?mediaImageId=${companyInfo.contactIdentityCardBackMediaId!}" target="_blank;">背面</a>
                        </td>
                    </tr>
                    -->
                    <tr>
                        <td>联系人职称</td>
                        <td class="jobTitle">${companyInfo.jobTitle!''}</td>
                         <#if authorizeKey('PC_CONTACTS_INFO_MODIFY')>
                        <td >
                        	<a class="userCenter-link editJob-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    <tr>
                        <td>手机号码</td>
                        <td class="tel">${companyInfo.contactTel!''}</td>
                        <#if authorizeKey('PC_CONTACTS_INFO_MODIFY')>
                        <td >
                        	<a class="userCenter-link editCellphone-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    <tr>
                        <td>电子邮箱</td>
                        <td class="email">${companyInfo.contactMail!''}</td>
                         <#if authorizeKey('PC_CONTACTS_INFO_MODIFY')>
                        <td >
                        	<a class="userCenter-link editEmail-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    </tbody>
                </table>
</div>