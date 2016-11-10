<div class="userCenter-content">
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="200">
                        <col width="300">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>用户名</td>
                        <td>
                            <span>${individualInfo.username!''}</span>
                        </td>
                        <td><input class="modify_user_id" style="display:none" value="${individualInfo.id}"/></td>
                    </tr>
                     <#if (currentUser().userData.masterId)??>
                     <tr>
                     <td>员工姓名</td>
                     <td>
                            <span class="cName">${individualInfo.realName!''}</span>
                     </td>
                     <#if authorizeKey('PP_PERSONAL_INFO_MODIFY')>
                      <td >
                        	<a class="userCenter-link editName-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                     </tr>
                    <tr>
                        <td>员工工号</td>
                        <td>
                            <span>${individualInfo.jobNo!''}</span>
                        </td>
                    </tr>
                    <#else>
                    <tr>
                     <td>姓名</td>
                     <td>
                            <span class="cName">${individualInfo.realName!''}</span>
                     </td>
                     <#if authorizeKey('PP_PERSONAL_INFO_MODIFY')>
                     <td >
                        	<a class="userCenter-link editName-link" href="javascript:void(0);">修改</a>
                     </td>
                     </#if>
                     </tr>
                    </#if>
                    <tr>
                        <td>职位</td>
                        <td>
                            <span class='jobTitle'>${individualInfo.employeeJobTitle!''}</span>
                        </td>
                        <#if authorizeKey('PP_PERSONAL_INFO_MODIFY')>
                        <td>
                            <a class="userCenter-link editJob-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    <tr>
                        <td>手机号码</td>
                        <td class='tel'>${individualInfo.employeeTel!''}</td>
                        <#if authorizeKey('PP_PERSONAL_INFO_MODIFY')>
                        <td>
                            <a class="userCenter-link editCellphone-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    <tr>
                        <td>电子邮箱</td>
                        <td class='email'>${individualInfo.employeeMail!''}</td>
                        <#if authorizeKey('PP_PERSONAL_INFO_MODIFY')>
                        <td>
                            <a class="userCenter-link editEmail-link" href="javascript:void(0);">修改</a>
                        </td>
                        </#if>
                    </tr>
                    <tr>
                        <td valign="top">所属角色</td>
                        <td>
                        	<#assign roleId="">
                        	<#list individualInfo.roleDTOList as roleDTO>
                        		${(roleDTO.roleName)!''}<br/>
                        		<#if !roleDTO_has_next >
	                        		<#assign roleId = roleId + roleDTO.roleId>
                        		<#else>
	                        		<#assign roleId = roleId + roleDTO.roleId + ",">
                        		</#if>
                        	</#list>
                        </td>
                        <td valign="top">
                            <a class="userCenter-link" href="${basepath}/user/gotoCompanyRoleDetails?roleId=${roleId!''}&type=individual" target="_blank">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>登录密码</td>
                        <td></td>
                        <td>
                            <a class="userCenter-link editPwd" href="javascript:void(0);">修改登录密码</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>