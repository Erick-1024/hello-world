<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="登录" jsFiles=["facade/signin.js"] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true removeExtFooter = true>

<form id="fmSignin" target="signinresult" method="post" action="${basepath}/signIn">
	<div class="login-containWrap">
	    <div class="login-wrapContent">
	        <div class="login-wrapHead">
	            <div class="login-logo" style="width: 105px;"></div>
	        </div>
	        <div class="login-bgOver"></div>
	        <img class="login-bg" src="${host}/images/login_bg.png">
	        <div class="login-slogn">
	            <div class="login-slogn-head"></div>
	            <div class="login-slogn-foot"></div>
	        </div>
	        <div class="login-formWrap">
	            <div class="login-wrapBox">
	                <div class="login-formHead"></div>
	                <div class="login-notice errorBox">
	                    <div ><i class="errorIcon"></i><span>用户名或密码错误</span></div>
	                </div>
	                <div class="login-formContent">
	                    <label class="login-comIcon login-userIcon"></label>
	                    <i class="deleteIcon hidden"></i>
	                    <input class="userName" type="text" placeholder="用户名" name="username">
	                </div>
	                <div class="login-formContent">
	                    <label class="login-comIcon login-pwdIcon"></label>
	                    <input type="password" placeholder="密码" name="password">
	                </div>
	                <div class="login-formContent login-row">
	                    <label class="authCodeImg" style="right:2px"><a class="authcode" id="refreshCode" style="width:99px;height:40px;" 
	                href="javascript:void(0);" onclick="$(this).children().hide().attr('src', basepath + '/captcha/gen?' + Math.floor(Math.random()*100) ).fadeIn();" 
	                title="点击刷新" alt="点击刷新">
						<img id="imgCaptcha" style="width:94px;height:40px;" src="${basepath}/captcha/gen">
						</a></label>
	                	<input type="text" class="" placeholder="请输入验证码" name="captcha">
	                </div>
	                <div class="login-formLink">
	                    <a class="login-link" href="${basepath}/facade/gotoRegister">注册</a>
	                    <a class="login-link login-forgetPwd" href="${basepath}/facade/forget/password" target="_blank" >忘记密码?</a>
	                </div>
	                <div class="login-foot">
	                    <a class="login-submit" href="javascript:void(0);" id="sbmSignin">登 录</a>
	                </div>
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