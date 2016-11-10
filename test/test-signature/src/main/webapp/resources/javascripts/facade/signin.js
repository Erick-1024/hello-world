$(function(){
	$(".errorBox").hide();
    $(".autoLogin .checkedBox").click(function(){
        $(this).toggleClass("noCheckedBox");
    });
    
	var url = window.location.origin;
	var contextPath = basepath;
	$('#origin_host').val(url+contextPath);

	$('#sbmSignin').click(requestSignin);
	
	$(".delIcon").click(function(){
		$("input[name=username]").val("");
	});
    
    $("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('#sbmSignin').click();
    	}
    });
    
    $("#refreshCode").on("keyup", function (event) {
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//tab按钮事件
    	if(evt.keyCode == 9){
    		$("input[name='captcha']").focus();
    	}
    });
    
    if($("input[name=username]").val() == ""){
    	$("input[name=username]").prev().hide();
    }
    $("input[name=username]").on("change", function(){
    	if($(this).val() == ""){
    		$(this).prev().hide();
    	}else{
    		$(this).prev().show();
    	}
    });
    $("input[name=username]").on("keyup", function(){
    	if($(this).val() == ""){
    		$(this).prev().hide();
    	}else{
    		$(this).prev().show();
    	}
    });
    $("input[name=username]").on("mouseup", function(){
    	if($(this).val() == ""){
    		$(this).prev().hide();
    	}else{
    		$(this).prev().show();
    	}
    });
    $(".delIcon").on("click", function(){
    	if($(this).next().val() == ""){
    		$(this).hide();
    	}
    });
    
    $(".userName").bind("input propertychange", function(){
        var num = $(this).val().length;
        if(num == 0){
            if(!$(".deleteIcon").hasClass("hidden")){
                $(".deleteIcon").addClass("hidden");
            }
        }else{
            if($(".deleteIcon").hasClass("hidden")){
                $(".deleteIcon").removeClass("hidden");
            }
        }
    });

    $(".deleteIcon").bind("click", function(){
        $(".userName").val("");
        $(".deleteIcon").addClass("hidden");
    });
});

function keyDown(){
	
}
function signinCallback(responseText){
	var arr = responseText.match(/\[AUTHRESULT\[.*?\]\]/);
	var result = responseText;
	if (typeof(arr) == 'object' && arr instanceof Array && arr.length > 0) {
		result = arr[0];
		result = result.substring('[AUTHRESULT['.length, result.length - 2);
	}
	if (result.indexOf(";") > 0) {
		var results = result.split(";");
		resultcode = results[0];
		if(resultcode == "SUCCESS"){
			if(/http:\/\//.test(results[1]) || /https:\/\//.test(results[1]))
				window.location = results[1];
			else
				window.location = basepath + results[1];
		}
//		window.location = basepath;
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
	$('#imgCaptcha').attr('src', basepath + '/captcha/gen?' + Math.floor(Math.random()*100) ).fadeIn();
	$('#sbmSignin').removeAttr('disabled').val("登  录");
}

function Success($obj){
    $(".errorBox").hide();
    var row = $obj.closest(".row").removeClass("large_error").removeClass("small_error");
}

function Error(obj,message,isShow){
    $(".errorBox span").html(message);

    if(isShow){
        $(".errorBox").show();
        var em = $(obj).parent("div").find("em");
        em.removeClass();
        em.addClass("error");
    }else{
        Success(obj);
    }
}

function formValiation($form) {
	if (!($form instanceof jQuery)) {
		$form = $($form);
	}
	var $username = $form.find('input[name=username]');
	if ($username.val() == '') {
		Error($username, '用户名不能为空', true);
		$username.focus();
		return false;
	} else {
		Success($username, '');
	}
	var $password = $form.find('input[name=password]');
	if ($password.val() == '') {
		Error($password, '密码不能为空', true);
		$password.focus();
		return false;
	} else {
		Success($password, '');
	}
	var $captcha = $form.find('input[name=captcha]');
	if ($captcha.val() == '') {
		Error($captcha, '验证码不能为空', true);
		$captcha.focus();
		return false;
	} else if ($captcha.val().length != 4) {
		Error($captcha, '验证码为4位', true);
		$captcha.focus();
		return false;
	} else {
		Success($captcha, '');
	}
	return true;
}

function requestSignin(){
	var $form = $('#fmSignin');
	$.cookie('rememberMeChecked', ($(this).hasClass("noCheckedBox") ? 'off' : 'on'), { expires: 14 });
	
	if (!formValiation($form)) {
		return;
	}
	$('#sbmSignin').attr('disabled','disabled').val("登  录  中 ...");
	$form.submit();
}