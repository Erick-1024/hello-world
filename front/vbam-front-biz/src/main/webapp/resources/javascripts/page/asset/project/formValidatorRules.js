var userType = "";
var flag_companyName = true;
var factorCompanyName　="";
var isImageTypeOk = false;
var isImageSizeOk = false;

var factorCompanyNameRule={
		required : "资金方企业名称不能为空",
		rule: function(input) {
			if(input.is("[name=factorCompanyName]")){
				var value = $("#factorCompanyName").val();
				var url = "verifyFactorCompanyName";
				if(value ==factorCompanyName){
					return flag_companyName;
				}else {
					factorCompanyName = value;
					return flag_companyName = isCompanyNameExist(name, url);
				}
			}
			return true;
		},
		ruleMessage: "第一个资金方必须是当前登陆用户"
	};


var nameRule={
	required : "项目名称不能为空"
};

var coreCompanyNameRule={
	required : "核心企业名称不能为空"
};

var coreAccountNoRule={
		required : "银行账号不能为空",
};

var coreOrganizationCodeRule = {
		required : "组织机构代码不能为空"
};

var coreBusinessLicenceCodeRule = {
		required : "营业执照号码不能为空"
};

var coreTaxRegistrationCertificateCodeNoRule = {
		required : "税务登记号码不能为空"
};
var coreTaxRegistrationCertificateCodeNoRule = {
		required : "税务登记号码不能为空"
};

var extensionDaysRule = {
		required : "展期天数不能为空",
};
var deductionTimeRule = {
		required : "扣款时间不能为空",
		pattern : "^(([01]?[0-9])|(2[0-4])):([0-5][0-9])$",
		message : "扣款时间格式不正确"
};
var earlyRepaymentChargeRatioRule = {
		required : "提前还款手续费率不能为空",
		//pattern : "^\d+(?=\.{0,1}\d+$|$)",
		message : "提前还款手续费率格式不正确"
};
/*var extensionRatioRule = {
		required : "展期利率/展期上浮比例不能为空",
		pattern : "^\d+(?=\.{0,1}\d+$|$)",
		message : "展期利率/展期上浮比例格式不正确"
};


var penaltyRateRule = {
		required : "逾期利率/逾期上浮比例不能为空",
		pattern : "^\d+(?=\.{0,1}\d+$|$)",
		message : "逾期利率/逾期上浮比例格式不正确"
};*/

var financeApplicantRule = {
		required : "融资申请人不能为空"
};

var financeApplicantRule = {
		required : "融资申请人不能为空"
};

var repaymentMethodsRule = {
		required : "付款方式至少选一种"
};

var imageRule = {
	ruleNotNull: function(input) {
		if(input.is(".image") && input.parent().find(".image").val()==""){
			var value = input.parent().find(".imageUpload").val();
			if(value == ""){
				return false;
			}
		}
		return true;
	},
	ruleNotNullMessage: "图片必须上传",
	ruleNotRight: function(input){
		if(input.is(".image") && input.parent().find(".image").val()==""){
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
		if(input.is(".image") && input.parent().find(".image").val()==""){
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

