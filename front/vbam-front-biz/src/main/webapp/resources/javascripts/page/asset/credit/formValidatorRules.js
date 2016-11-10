//申请额度
var businessContractNoValue = "";
var flag_businessContractNo = true;
var businessContractNoRule1={
		required : "业务合同号不能为空",
		pattern	 : "^[0-9a-zA-Z]{11}$",
		message : "业务合同号必须是十一位数字和英文字母",
		rule: function(input) {
			if(input.is("[name=businessContractNo]")){
				var businessContractNo = $("#businessContractNo").val();
				var creditId =$("#creditId").val();
				var url = "checkBusinessContactNo";
				if(businessContractNoValue == businessContractNo){
					return flag_businessContractNo;
				}else {
					businessContractNoValue = businessContractNo;
					return flag_businessContractNo = isValueExist(businessContractNo,creditId,url);
				}
			}
			return true;
		},
		ruleMessage: "业务合同号已存在"
	};
//修改额度
var businessContractNoValue = "";
var flag_businessContractNo = true;
var businessContractNoRule={
		required : "业务合同号不能为空",
		pattern	 : "^[0-9a-zA-Z]{11}$",
		message : "业务合同号必须是十一位数字和英文字母",
		rule: function(input) {
			if(input.is("[name=businessContractNo1]")){
				var businessContractNo = $("#businessC").val();
				var creditId =$("#creditId-m").val();
				var url = "checkBusinessContactNo";
				if(businessContractNoValue == businessContractNo){
					return flag_businessContractNo;
				}else {
					businessContractNoValue = businessContractNo;
					return flag_businessContractNo = isValueExist(businessContractNo,creditId,url);
				}
			}
			return true;
		},
		ruleMessage: "业务合同号已存在"
	};



function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
};