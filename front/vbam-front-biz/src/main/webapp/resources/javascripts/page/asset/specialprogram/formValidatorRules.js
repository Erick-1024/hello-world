//新增专项计划编号
var specialProgramIdValue = "";
var flag_specialProgramId = true;
var companyName = "";
var flag_companyName = true;
var specialProgramIdRule1={
		required : "专项计划编号不能为空",
		rule: function(input) {
			if(input.is("#specialProgramId")){
				var specialProgramId = $("#specialProgramId").val();
				var oldId =$("#oldId").val();
				var url = "checkSpecialProgramId";
				if(specialProgramIdValue == specialProgramId){
					return flag_specialProgramId;
				}else {
					specialProgramIdValue = specialProgramId;
					return flag_specialProgramId = isValueExist(specialProgramId,oldId,url);
				}
			}
			return true;
		},
		ruleMessage: "专项计划编号号已存在"
	};

//校验专项计划管理人是否存在
//var companyNameRule={
//		required : "管理人名称不能为空",
//		rule: function(input) {
//			if(input.is("#managerName")){
//				var manageName = $("#managerName").val();
//				var url = "checkManageName";
//				if(manageName == companyName){
//					return flag_companyName;
//				}else {
//					companyName = manageName;
//					return flag_companyName = isCompanyNameExist(manageName,url);
//				}
//			}
//			return true;
//		},
//		ruleMessage: "管理人名称不存在,请先注册"
//	};




//修改专项计划编号
var specialProgramIdValue = "";
var flag_specialProgramId = true;
var specialProgramIdRule={
		required : "专项计划编号不能为空",
		rule: function(input) {
			if(input.is("#specialProgramId")){
				var specialProgramId = $("#specialProgramId").val();
				var oldId =$("#oldId").val();
				var url = "checkSpecialProgramId";
				if(specialProgramIdValue == specialProgramId){
					return flag_specialProgramId;
				}else {
					specialProgramIdValue = specialProgramId;
					return flag_specialProgramId = isValueExist(specialProgramId,oldId,url);
				}
			}
			return true;
		},
		ruleMessage: "专项计划编号已存在"
	};



//专项计划名称
var specialProgramNameRule={
		required : "专项计划名称不能为空"
	};

//预计成立日期
var estimateEstablishmentDateRule={
		required : "预计成立日期不能为空"
	};

function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
};