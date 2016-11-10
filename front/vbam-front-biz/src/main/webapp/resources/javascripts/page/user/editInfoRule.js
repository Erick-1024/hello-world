var secondPasswordRule = {
		required : "确认密码不能为空",
		rule: function(input) {
			if(!checkPasswordMatch(input))
				return false;
			return true;
		},
		ruleMessage: "两次输入密码不一致"
};
var loginPwdRule = {
		required : "密码不能为空",
		pattern: "^[0-9a-zA-Z-_]{6,20}$",
		message: "密码格式不正确",
		rule: function(input) {
			if(!isLoginSuccess(input))
				return false;
			return true;
		},
		ruleMessage: "密码错误"
};

var UpdateImageRule = {
		ruleNotNull: function(input) {
			if(input.is(".image")){
				var value = input.parent().find(".frontage").val();
				if(value == ""){
					return false;
				}
			}
			return true;
		},
		ruleNotNullMessage: "图片必须上传",
		ruleNotRight: function(input){
			if(input.is(".image")){
				var value = input.parent().find(".frontage").val();
				var regex = /(.jpg|.png|.jpeg)$/;
				var result = value.toLowerCase().match(regex);
				if(result == null){
					upload_type = false;
					return false;
				}else{
					var name = input.parent().find(".frontage").attr("id");
					if(upload_type)
						ajaxFileUpload(name);
					return true;
				}
			}
			return true;
		},
		ruleNotRightMessage: "图片格式不正确"
	};