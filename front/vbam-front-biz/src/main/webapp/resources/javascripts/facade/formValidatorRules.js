var username = "";
var flag_username = true;
var companyName = "";
var userType = "";
var flag_companyName = true;
var isImageTypeOk = false;
var isImageSizeOk = false;
var usernameRule={//用户名
	required : "用户名不能为空",
	pattern: "^[0-9|a-z|A-Z|_|-]{6,20}$",
	message: "用户名格式不正确",
	rule: function(input) {
		if(input.is("[name=username]")){
			var value = $("#username").val();
			var url = "verifyUsername";
			if(value == username){
				return flag_username;
			}else {
				username = value;
				return flag_username = isValueExist(value, url);
			}
		}
		return true;
	},
	ruleMessage: "用户名已存在"
};

var companyNameRule={//用户名
	required : "企业名称不能为空",
	rule: function(input) {
		if(input.is("[name=companyName]")){
			var name = $("#companyName").val();
			var type = $("#userType").val();
			var url = "verifyCompanyName";
			if(name == companyName && type == userType){
				return flag_companyName;
			}else {
				companyName = name;
				userType = type;
				return flag_companyName = isCompanyNameExist(name, type, url);
			}
		}
		return true;
	},
	ruleMessage: "企业名称已存在"
};

var imageRule = {
	ruleNotNull: function(input) {
		if(input.is(".image")){
			var value = input.parent().find(".imageUpload").val();
			if(value == ""){
				return false;
			}
		}
		return true;
	},
	ruleNotNullMessage: "图片必须上传",
	ruleNotRight: function(input){
		if(input.is(".image")){
			if(verifyImageType(input) == null){
				isImageTypeOk = false;
				return false;
			}else{
				return true;
			}
		}
		return true;
	},
	ruleNotRightMessage: "格式不正确",
	ruleTooLarge: function(input) {
		if(input.is(".image")){
			if(verifyImageSize(input)){
				return true;
			}else{
				isImageSizeOk = false;
				return false;
			}
		}
		return true;
	},
	ruleTooLargeMessage: "大小超过限制"
};

var organizationCodeRule = {
	required : "组织机构代码不能为空"
};

var businessLicenceCodeRule = {
	required : "营业执照号码不能为空"
};

var taxRegistrationCertificateCodeRule = {
	required : "税务登记号码不能为空"
};

var contactNameRule = {
	required : "联系人不能为空"
};

var realNameRule = {
		required : "员工姓名不能为空"
	};


var mobileNoRule = {//联系电话
		required : "联系电话不能为空",
		pattern : "^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$",
		message : "联系电话格式不正确"
};

var emailNoRule = {//联系人邮箱
		required : "邮箱不能为空",
		pattern : "^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$",
		message : "邮箱格式不正确"
};

var userIdRule = { // 员工id
	rule : function(input) {
		return input.is("[name=flightSegmentNumber]") ? $("#table tbody tr:visible").length > 1 : true;
	},
	message : "员工Id不存在"
};

var roleNameRule = 
	{
		required : "角色名不能为空！",
		pattern: "/w{2}",
		message: "角色名格式不正确",
		rule: function(input) {
			if(input.is("[name=roleName]")){
				var is = isCompanyRoleNameExit();
				return is;
			}
			return true;
		},
		ruleMessage: "角色名已存在"
	};

var employeeRoleNameRule = 
{
	required : "角色名不能为空！",
	pattern: "/w{2}",
	message: "角色名格式不正确",
	rule: function(input) {
		if(input.is("[name=roleName]")){
			var is = isEmployeeRoleNameExit();
			return is;
		}
		return true;
	},
	ruleMessage: "角色名已存在"
};

var realNameRule = {//员工姓名
		required : "员工姓名不能为空"
};

var passwordRule = {
		required : "密码不能为空",
		pattern: "^[0-9a-zA-Z-_]{6,20}$",
		message: "密码格式不正确",
		rule: function(input) {
			if(input.is("[name=password]")){
				var password = $.trim($("#password").val());
				var username = "";
				if($("#username").length>0){
					username = $.trim($("#username").val());
				}else{
					username = $.trim($("#usernameForCheck").val());
				}
				if(username == password){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		ruleMessage: "密码和用户名不能相同"
};

var confirmPasswordRule = {
		required : "确认密码不能为空",
		rule: function(input) {
			if(input.is("[name=passwordSecondForActive]")){
				var rePassword = $.trim($("#passwordSecondForActive").val())
				return checkPasswordMatch(rePassword)
			}
		},
		ruleMessage: "两次输入密码不一致"
};

var accountRule = {
		required : '账号不能为空',
		pattern : '^[0-9]{19}$',
		message : '账号必须是19位数字'
};

var bankAccountRule = {
		required : '账号不能为空',
		pattern : '^[0-9]{16,19}$',
		message : '账号不正确'
};

var amountRule = {
		required : '转账金额不能为空',
		pattern : '^(([1-9]+)|([0-9]+\.[0-9]{2}))$',
		message : '金额格式不正确'
};

var payPasswordRule = { //deprecated
		required : '支付密码不能为空'
//		pattern : '^[0-9a-z]{6}$',
//		message : '密码必须为6位'
};

var accountNameRule = {
		required : '收款银行地址不能为空'
};

var verifyCodeRule = {
		required : '验证码不能为空',
		pattern : '^[0-9a-z]{4}$',
		message : '验证码必须为4位'
};

var bankNameRule = {
		required : '银行名称不能为空',
		pattern : '^[\u4e00-\u9fa5]{0,}$',
		message : '必须为中文'
};

function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
};
