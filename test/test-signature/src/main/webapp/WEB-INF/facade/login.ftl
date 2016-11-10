<#import "/common/htmlBase.ftl" as hb>
<#-- jsFiles 填写需要的js文件，以js开头的是静态服务器的js，其他是本项目的js，cssFiles 是静态服务器css，localCssFiles 是本项目的css文件（填写除公共路径以外的路径） -->
<@hb.htmlBase title="Cana" jsFiles=["common/formValidator.js","user/formValidatorRules.js"] cssFiles=[] localCssFiles=[] removeExtHeader = true>
 	<form id="fmSignin" target="signinresult" method="post" action="${basepath}/signIn">
	        <input type="hidden" name="user_mode" value="company_user">
	        <div class="login_panel">
	            <div class="input_area">
	                <h2 class="tt clearfix">登录Cana</h2>
	                <div class="row tips" style="display:none;">
	                    <i></i><span></span>
	                </div>

	                <div class="row large">
	                    <i class="username"></i>
	                    <input type="text" name="username" style="width:249px;">
	                    <span class="k-invalid-msg" data-for="username"></span>
	                    <label class="cover">用户名</label>
	                </div>
	                <div class="row large">
	                    <i class="psw"></i>
	                    <input type="password" autocomplete="off" name="password" value="" style="width:249px;">
	                    <label class="cover">密码</label>
	                   <!-- <div class="forget clearfix">
	                        <a href="javascript:void(0)" class="pull-right" >忘记密码?</a>
	                    </div>-->
	                </div>
	                <div class="row small" style="display:block;" id="divCaptcha">
	                    <input type="text" autocomplete="off" name="captcha" value="">
	                    <label class="cover" style="left:8px">验证码</label>
<a class="authcode" style="width:99px;height:40px;" href="javascript:void(0);" onclick="$(this).children().hide().attr('src', basepath + '/captcha/gen?' + Math.floor(Math.random()*100) ).fadeIn();" title="点击刷新" alt="点击刷新">
	<img id="imgCaptcha" style="width:99px;height:40px;" src="/vbam-front-biz/captcha/gen">
</a>
	                </div>

	                <div class="row" style="z-index:10;">
	                    <label class="autologin"><input type="checkbox" name="remember_me" style="vertical-align:middle;">&nbsp;下次自动登录</label>
						<label class="forget">
	                        <a href="/tops-front-purchaser/facade/retrieve-password-1">忘记密码?</a>
	                    </label>
	                </div>

					<div class="row" style="padding:0;">
					 <input type="button" name="login" id="sbmSignin" value="登&nbsp;&nbsp;录" class="login_btn">
					</div>

					<div class="registerRow">
						<a href="/tops-front-purchaser/facade/register">注册帐号</a>
					</div>
	            </div>
	        </div>
	        <input type="hidden" name="auth_result_only" value="true">
	        <input type="hidden" id="origin_host" name="origin_host" value="http://store.tdxinfo.com/tops-front-purchaser">
</form>
	        <iframe id="signinresult" name="signinresult" style="overflow:hidden;border:0;height:0px;width:0px;"></iframe>
	        <script>
	        	var url = new String(window.location);
	        	var contextPath = '/vbam-front-biz';
	        	var host = url.replace(/http:\/{2,}/i,'').replace(/\/.*/,'') + '/';
	        	$('#origin_host').val('http://' + (host+contextPath).replace(/\/{2,}/g,'/'));
	        	
	        	function signinCallback(responseText){
				var arr = responseText.match(/\[AUTHRESULT\[.*?\]\]/);
				var result = responseText;
				if (typeof(arr) == 'object' && arr instanceof Array && arr.length > 0) {
					result = arr[0];
					result = result.substring('[AUTHRESULT['.length, result.length - 2);
				}
				if (result == 'SUCCESS') {
					window.location = basepath;
			//	    _paq.push(['trackEvent', 'userLogin', 'signIn', $('input[name=username]').val()]);
					return;
				}
			
				var resultCode = result;
				if (result.indexOf(':') >= 0) {
					var index = result.indexOf(':');
					resultCode = result.substring(0, index);
//					getLoginFailCount();
//					if (isNaN(count)) {
//						count = 0;
//					}
//					if (count >= 3) {
//						$('#capchaDiv').show();
//						needCapcha = true;
//					}
				}
				if (resultCode == 'USERNAME_OR_PASSWORD_ERROR') {
					Error($('#fmSignin').find('input[name=username]'), '', true);
					Error($('#fmSignin').find('input[name=password]'), '用户名或密码错误', true);
				} else if (resultCode == 'LOCKED') {
					Error(null, '用户被锁定', true);
				} else if (resultCode == 'EXPIRED') {
					Error(null, '用户已过期', true);
				} else if (resultCode == 'CAPTCHA_ERROR') {
					Error($('#fmSignin').find('input[name=captcha]'), '验证码错误', true);
				} else {
					Error(null, '未知错误', true);
					$('body').append('<div style="display:none;"><pre>' + responseText + '</pre></div>');
				}
				$('#captchaImage').attr('src', basepath + '/captcha/gen?' + Math.floor(Math.random()*100) ).fadeIn();
			//	$('#sbmSignin').removeAttr('disabled').val("登  录");
			}
	        	function Error(obj,message,isShow){
				    $(".tips span").html(message);
				
				    if(isShow){
				        $(".tips").show();
				        var em = $(obj).parent("div").find("em");
				        em.removeClass();
				        em.addClass("error");
				    }else{
				        Success(obj);
				    }
				}
			setValidator("input[name=username]", "required", true, emailNoRule.required);
			setValidator("input[name=username]", "pattern", emailNoRule.pattern, emailNoRule.message);
			var validator = $('#fmSignin').kendoValidator().data("kendoValidator");
			$('#sbmSignin').click(function(){
				 event.preventDefault();
				alert(validator.validate());
	    		if(!validator.validate()) {
	    			
	        		return;
	   	 		}
			});
			
	        </script>

</@hb.htmlBase>