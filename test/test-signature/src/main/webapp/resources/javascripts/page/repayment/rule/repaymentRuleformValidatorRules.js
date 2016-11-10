var ratioPattern = "^[0-9]+(.[0-9]{1,3})?$"
var deductionTimeRule = {
		required : "扣款时点不能为空",
		pattern : "^((1[5-9]):[0-5][0-9])|(20:00)$",
		message : "扣款时点格式不正确",
		rule: function(input) {
			if(input.is("#deductionTime")){
				if($("#deductionTime").val() <= $("#accountAccumulationTime").val())
					return false;
				return true;
			}
			return true;
		},
		ruleMessage : "扣款时间不能早于归集时间"
};

var accountAccumulationTimeRule = {
		required : "归集时点不能为空",
		pattern : "^((1[5-6]):[0-5][0-9])|(17:00)$",
		message : "归集时点格式不正确",
		rule: function(input) {
			if(input.is("#accountAccumulationTime")){
				if($("#deductionTime").val() <= $("#accountAccumulationTime").val())
					return false;
				return true;
			}
			return true;
		},
		ruleMessage : "扣款时间不能早于归集时间"
};

var extensionDaysRule = {
		required : "展期不能为空",
		pattern : "^[0-9]+$",
		message : "展期格式不正确"
};

var extensionRatioRule = {
		required : "展期费率不能为空",
		pattern: ratioPattern,
		message: "展期费率格式不正确"
};

var penaltyRateRule = {
		required : "逾期管理费率不能为空",
		pattern: ratioPattern,
		message: "逾期管理费率格式不正确"	
};

var earlyRepaymentChargeRatioRule = {
		required : "提前还款手续费率不能为空",
		pattern: ratioPattern,
		message: "提前还款手续费率格式不正确"	
};