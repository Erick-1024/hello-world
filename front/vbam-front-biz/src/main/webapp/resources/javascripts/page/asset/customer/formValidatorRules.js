var customerName = "";
var flag_customerName = true;
var customerNameRule={
		required : "客户名称不能为空",
		message : "客户名称格式不正确",
		rule: function(input) {
			if(input.is("[name=customerName]")){
				var value = $("#customerName").val();
				var id =$("#id").val();
				var url = "verifyCustomerName";
				if(value == customerName){
					return flag_customerName;
				}else {
					customerName = value;
					return flag_customerName = isValueExist(value, url,id);
				}
			}
			return true;
		},
		ruleMessage: "客户名称已存在"
	};


var magIdentityCardNoRule = {
		required : "身份证不能为空",
		pattern : "^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$",
		message : "身份证格式不正确"
};

function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
};