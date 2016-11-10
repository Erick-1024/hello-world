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

function isCompanyNameExist(name, type, url) {
	var result = $.ajax({
		url: basepath + "/facade/" + url,
		async: false,
		type: 'post',
		data: {
			name: name,
			userType: type
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};

function bindEvent() {
	setValidator($("#username"), "required", true, usernameRule.required);
	setValidator($("#username"), "pattern", usernameRule.pattern, usernameRule.message);
	setValidator($("#companyName"), "required", true, companyNameRule.required);
	setValidator($("#organizationCode"), "required", true, organizationCodeRule.required);
	setValidator($("#businessLicenceCode"), "required", true, businessLicenceCodeRule.required);
	setValidator($("#taxRegistrationCertificateCode"), "required", true, taxRegistrationCertificateCodeRule.required);
	setValidator($("#contactName"), "required", true, contactNameRule.required);
	setValidator($("#contactTel"), "required", true, mobileNoRule.required);
	setValidator($("#contactTel"), "pattern", mobileNoRule.pattern, mobileNoRule.message);
	setValidator($("#contactMail"), "required", true, emailNoRule.required);
	setValidator($("#contactMail"), "pattern", emailNoRule.pattern, emailNoRule.message);
	
	$("body").delegate(".imageUpload", "change", function() {
		var name = $(this).attr("id");
		$("#" + name + "Id").remove();
		isImageTypeOk = true;
		isImageSizeOk = true;
		$(this).parent().find(".image").blur();
		if(isImageTypeOk && isImageSizeOk)
			ajaxFileUpload(name);
	});
	
	var validator = $("#register-form").kendoValidator({
		rules: {
			usernameIsExistRule: usernameRule.rule,
			companyNameRuleIsExistRule: companyNameRule.rule,
			imageNotNullRule: imageRule.ruleNotNull,
			imageNotRightRule: imageRule.ruleNotRight
		},
		messages: {
			usernameIsExistRule: usernameRule.ruleMessage,
			companyNameRuleIsExistRule: companyNameRule.ruleMessage,
			imageNotNullRule: imageRule.ruleNotNullMessage,
			imageNotRightRule: imageRule.ruleNotRightMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	
	$("body").on("click", ".reg-submit", function() {
		if(!validator.validate()) {
			return;
		};
		$("#register-form").submit();
	});
	
	$("body").on("change", "#userType", function(){
		if($("#companyName").val() != ""){
			$("#companyName").blur();
		}
	});
}

$(function(){
	bindEvent();
});