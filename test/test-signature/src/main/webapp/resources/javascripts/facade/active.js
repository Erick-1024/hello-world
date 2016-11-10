$(function() {
	setValidator($("#username"), "required", true, usernameRule.required);
	setValidator($("#username"), "pattern", usernameRule.pattern, usernameRule.message);
	setValidator($("#password"), "required", true, passwordRule.required);
	setValidator($("#password"), "pattern", passwordRule.pattern, passwordRule.message);
	setValidator($("#passwordSecondForActive"), "required", true, passwordRule.required);
	inputCheck();
	
	var readed = "";
	if(location.search != ""){
		readed = location.search.split("=").pop();
	}
	
	$(".readLink").click(function(){
		$("#usernameForRead").val($("input[name=username]").val());
		$("#passwordForContact").val($("input[name=password]").val());
		$("#passwordForContactSecond").val($("input[name=passwordSecondForActive]").val());
		$("#read").submit();
//		location.href = basepath + "/facade/gotoContact?userId="+userId+"&securityCode="+securityCode;
	});
	
    // 激活表单提交
	$("body").delegate("#confirmBtnForActive", "click", function() {
		if($("#username").length>0){
			var validator = $("#username").kendoValidator({
				rules: {
					usernameIsExistRule: usernameRule.rule
				},
				messages: {
					usernameIsExistRule: usernameRule.ruleMessage
				},
				needRuleAttrbute : false 
			}).data("kendoValidator");
			if(!validator.validate()) {
				return;
			};
		}
		
		validator = $("#password").kendoValidator({
				rules: {
					passwordRule: passwordRule.rule
				},
				messages: {
					passwordRule: passwordRule.ruleMessage
				},
				needRuleAttrbute : false 
		}).data("kendoValidator");
		
		if(!validator.validate()) {
			return;
		};
		var validator = $("#passwordSecondForActive").kendoValidator({
			rules: {
				confirmPasswordRule: confirmPasswordRule.rule
			},
			messages: {
				confirmPasswordRule: confirmPasswordRule.ruleMessage
			},
			needRuleAttrbute : false 
		}).data("kendoValidator");
		if(!validator.validate()) {
			return;
		};
		if($(".checkboxInfo").length>0 && !$(".checkboxInfo").hasClass("active")){
			showAlertWin("请同意协议！");
			return;
		}
		if($(".readLink").length>0 && readed != "true"){
			showAlertWin("请先阅读协议！");
			return;
		}
		$("#activeFrom").submit();
	});
});

function isValueExist(value, url) {
	var result = $.ajax({
		url: basepath + "/facade/" + url,
		async: false,
		type: 'post',
		data: {
			value: value
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};

function inputCheck(){
	$("body").on("blur", "#username", function() {
		var validator = $("#username").kendoValidator({
			rules: {
				usernameIsExistRule: usernameRule.rule
			},
			messages: {
				usernameIsExistRule: usernameRule.ruleMessage
			},
			needRuleAttrbute : false
		}).data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
	});
	$("body").on("blur", "#password", function() {
		var validator = $("#password").kendoValidator({
				rules: {
					passwordRule: passwordRule.rule
				},
				messages: {
					passwordRule: passwordRule.ruleMessage
				},
				needRuleAttrbute : false 
		}).data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
	});
	$("body").on("blur", "#passwordSecondForActive", function() {
		var validator = $("#passwordSecondForActive").kendoValidator({
			rules: {
				confirmPasswordRule: confirmPasswordRule.rule
			},
			messages: {
				confirmPasswordRule: confirmPasswordRule.ruleMessage
			},
			needRuleAttrbute : false
		}).data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
	});
}

function checkPasswordMatch(rePassword){
	if($.trim($("#password").val()) == rePassword){
		return true;
	}
	return false;
}
