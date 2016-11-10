var userType = "";
var isImageTypeOk = false;
var isImageSizeOk = false;
var payAccountRule={//打款账户账号
	required : "打款账户账号不能为空"
};

var payAccountNameRule={//账户人名称
	required : "账户人名称不能为空"
};
var payAccountAddressRule={
		required : "开户行地址不能为空"
};
var custEmailRule = {//联系人邮箱
		required : "联系人邮箱不能为空",
		pattern : "^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$",
		message : "联系人邮箱格式不正确"
};

var controllerRule = {
		required : "实际控制人名称不能为空"
}
var controllerOriginRule = {
		required : "实际控制人籍贯不能为空"
}
var controllerIdnoRule = {//实际控制人身份证号
		required : "实际控制人身份证号不能为空",
		pattern : "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$",
		message : "实际控制人身份证号格式不正确"
};
var controllerEmailRule = {
		required : "实际控制人邮箱不能为空",
		pattern : "^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$",
		message : "实际控制人邮箱格式不正确"
};
var controllerPhoneRule = {//联系电话
		required : "实际控制人手机号码不能为空",
		pattern : "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$",
		message : "实际控制人电话格式不正确"
};
var legalPersonRule={
		required : "法人代表名称不能为空"
};
var legalEmailRule = {
		required : "法人代表邮箱不能为空",
		pattern : "^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$",
		message : "法人代表邮箱格式不正确"
};
var legalPhoneRule = {//联系电话
		required : "法人代表手机号码不能为空",
		pattern : "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$",
		message : "法人代表电话格式不正确"
};
var accountOwnerNameRule={
		required : "开户人名称不能为空"
};
var accountOwnerEmailRule = {
		required : "开户人邮箱不能为空",
		pattern : "^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$",
		message : "开户人邮箱格式不正确"
};
var accountOwnerPhoneRule = {//联系电话
		required : "开户人手机号码不能为空",
		pattern : "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$",
		message : "开户人电话格式不正确"
};
var stationAmountRule={
		required : "网点数量不能为空"
};
var agentQualificationRule={
		required : "代理资质不能为空"
};
var organizationNoRule = {
		required : "组织机构代码不能为空"
};

var businessLicenceNoRule = {
		required : "营业执照号码不能为空"
};

var taxRegistrationCertificateNoRule = {
		required : "税务登记号码不能为空"
};
var legalIdnoRule = {//身份证号
		required : "法人代表身份证号不能为空",
		pattern : "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$",
		message : "法人代表身份证号格式不正确"
};
/*var additionInformationMediaIdRule = {
		required : "补充资料附件必须上传"
};*/
var tbOrderRatioRule = {
		required : "天猫、淘宝订单占比不能为空"
};

var additionInformationMediaIdRule = {
		ruleNotNull: function(input) {
			if(input.is(".file") && input.parent().find(".echo").val()!="2"){
				var value = input.parent().find(".fileUpload").val();
				if(value == ""){
					return false;
				}
			}
			return true;
		},
		ruleNotNullMessage: "文件必须上传",
		ruleNotRight: function(input){
			if(input.is(".file") && input.parent().find(".echo").val()!="2"){
				if(!verifyFileType(input)){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		ruleNotRightMessage: "格式不正确",
};

var imageRule = {
	ruleNotNull: function(input) {
		if(input.is(".image") && input.parent().find(".echo").val()!="2"){
			var value = input.parent().find(".imageUpload").val();
			if(value == ""){
				return false;
			}
		}
		return true;
	},
	ruleNotNullMessage: "图片必须上传",
	ruleNotRight: function(input){
		if(input.is(".image") && input.parent().find(".echo").val()!="2"){
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
		if(input.is(".image") && input.parent().find(".echo").val()!="2"){
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

function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
};
var fileRegex = "(.docx|.doc|.xls|.xlsx)$";
function verifyFileType(input) {
	var fileName = input.parent().find(".fileUpload").val();
	return (new RegExp(fileRegex)).test(fileName.toLowerCase());
};
