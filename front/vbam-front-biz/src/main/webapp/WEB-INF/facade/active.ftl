<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="账号激活" jsFiles=["facade/formValidatorRules.js","common/formValidator.js","/facade/active.js"] cssFiles=[""] localCssFiles=["/facade/active.css"] removeExtHeader = true removeExtFooter = false>
<header class="main-header">
    <div class="stairMenuContent">
        <div class="wrap clearfix">
            <div class="navLeft">
                <a class="logo" href="${basepath}/facade/signin">CANA</a>
            </div>
            <div class="headRightOperate">
                <span class="userContent">Hi<a class="goLogin" href="${basepath}/facade/signin">请登录</a></span>
            </div>
        </div>
    </div>
</header>
<div class="main-staffAcu">
    <div class="staffAcu-wrap">
        <div class="staffAcu-title">账户激活</div>
        <div class="staffAcu-content">
            <table class="staffAcu-table">
                <colgroup>
                    <col width="200px">
                    <col width="120px">
                    <col width="350px">
                </colgroup>
                <tbody>
                	<form id="activeFrom" action="active" method="post">
                		<input type="hidden" name="usernameForCheck" id="usernameForCheck" value="${(userActivationResponseDTO.username)!''}">
		                <#if userActivationResponseDTO.username ??>
		                	<tr>
		                    	<td><span class="redFalg">*</span>用户名</td>
		                    	<td colspan="2"><span>${userActivationResponseDTO.username}</span></td>
		                	</tr>
		                <#else>
		               		<tr>
		                    	<td><span class="redFalg">*</span>用户名</td>
		                    	<td><input type="text" style="width:200px;" name="username" id="username" value="${usernameForRead!}"></td>
		                    	<td class="validate">
		                        	<span>6-20位字符，支持字母、数字及“-”、“_“组合</span>
		                    	</td>
		                	</tr>
		                </#if>
		                <tr>
		                    <td><span class="redFalg">*</span>密码</td>
		                    <td><input type="password" style="width:200px;" name="password" id="password" value="${passwordForContact!}"><input type="hidden" name="id" id="id" value="${userActivationResponseDTO.id}"><input type="hidden" name="securityCode" id="securityCode" value="${userActivationResponseDTO.securityCode}"></td>
		                    <td class="validate">
		                        <span>6-20位字符，支持字母、数字及“-”、“_“组合</span>
		                    </td>
		                </tr>
		                <tr>
		                    <td><span class="redFalg">*</span>确认密码</td>
		                    <td><input type="password" style="width:200px;" name="passwordSecondForActive" id="passwordSecondForActive" value="${passwordForContactSecond!}"></td>
		                    <td></td>
		                </tr>
		                <#if !userActivationResponseDTO.masterId ??>
			                <tr>
			                    <td></td>
			                    <td colspan="2">
			                        <div class="checkboxContent">
			                            <label class="checkboxInfo">
			                                <span class="checkboxIcon"></span>同意《Cana金融信息服务平台法律声明》
			                               
			                            </label>
			                             <a class="readLink" style="color: #0f8aba" href="javascript:void(0);">点击阅读</a>
			                        </div>
			                    </td>
			                </tr>
			            </#if>
		                <tr>
		                    <td></td>
		                    <td colspan="2">
		                        <a class="staffAcu-link" href="javascript:void(0);" id="confirmBtnForActive" style="margin-top:30px;">激活</a>
		                    </td>
		                </tr>
	            
	                </form>
	               	<form id="read" action="${basepath}/facade/gotoContract" method="post" >
	                	<input name="userId" id="userIdForContact" type="hidden" value="${userId}"/>
						<input name="securityCode" id="securityCodeForContact" type="hidden" value="${securityCode}"/>
						<input name="usernameForRead" id="usernameForRead" type="hidden" />
						<input name="passwordForContact" id="passwordForContact" type="hidden" />
						<input name="passwordForContactSecond" id="passwordForContactSecond" type="hidden" />
	                </form>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@hb.htmlBase>

