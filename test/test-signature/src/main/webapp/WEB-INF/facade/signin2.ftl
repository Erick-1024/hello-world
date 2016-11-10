<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="登录" jsFiles=["facade/signin.js"] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true removeExtFooter = true>

<form id="fmSignin" target="signinresult" method="post" action="${basepath}/signIn">
	<div class="login-wrapper">
	    <div class="login-area">
	        <div class="login-box">
	            <div class="login-header">
	                <a class="login-img" href="#"></a>
	            </div>
	            <div class="login-header-title">CANA后台管理系统</div>
	            <div class="login-row">
	                <div class="errorBox"><i class="errorIcon"></i><span>用户名或密码错误</span></div>
	            </div>
	            <div class="login-row userName-field">
	                <label class="userIcon"></label>
	                <i class="delIcon"></i>
	                <input class="userName" type="text" placeholder="用户名" name="username">
	            </div>
	            <div class="login-row pwd-field">
	                <label class="pwdIcon"></label>
	                <input class="userPwd" type="password" placeholder="密码" name="password">
	            </div>
	            <div class="login-row">
	                <label class="authCodeImg"><a class="authcode" id="refreshCode" style="width:99px;height:40px;" 
	                href="javascript:void(0);" onclick="$(this).children().hide().attr('src', basepath + '/captcha/gen?' + Math.floor(Math.random()*100) ).fadeIn();" 
	                title="点击刷新" alt="点击刷新">
						<img id="imgCaptcha" style="width:94px;height:38px;" src="${basepath}/captcha/gen">
					</a></label>
	                <input type="text" class="authCode" placeholder="请输入验证码" name="captcha">
	            </div>
	            <div class="autoLogin">
	                
	                <a class="forgetPwd" href="${basepath}/facade/forget/password" target="_blank">忘记密码？</a>
	                <a class="registion" href="${basepath}/facade/gotoRegister">立即注册</a>
	            
	            </div>
	            <div class="login-row">
	                <a class="login-btn" href="javascript:void(0);" id="sbmSignin">登录</a>
	            </div>
	        </div>
	    </div>
	
	</div>
	<input type="hidden" name="auth_result_only" value="true">
    <input type="hidden" id="origin_host" name="origin_host" value="">
    <iframe id="signinresult" name="signinresult" style="overflow:hidden;border:0;height:0px;width:0px;"></iframe>
</form>
</script>
</@hb.htmlBase>