var currentRepaymentTotalAmountRule = {
	required : "本次还款总金额不能为空",	
	checkRule:function(input) {
		if(input.is("input[name='chargeForAutoAllocation']")){
			var value = $("input[name='chargeForAutoAllocation']").val().parseMoney();
			var regex = "^[0-9]+(.[0-9]{1,2})?$";
			var result = value.match(regex);
			if(result==null){
				return false;
			}else{
				return true;
			}
		}
		return true;
	},
	checkMessage:"本次还款总金额格式不正确"
};

var offlineRepaymentDateForPaidRule = {
	required : "实际还款日不能为空"
};

var normalPrincipalForPaidRule = {
	required : "本金不能为空",
	checkRule:function(input) {
			if(input.is("input[name='paidNormalPrincipalForPaid']")){
				var value = $("input[name='paidNormalPrincipalForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"本金格式不正确"
};

var normalInterestForPaidRule = {
		required : "收益不能为空",
		checkRule:function(input) {
				if(input.is("input[name='paidNormalInterestForPaid']")){
					var value = $("input[name='paidNormalInterestForPaid']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"收益格式不正确"
};

var normalServiceChargeForPaidRule = {
		required : "服务费不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidNormalServiceChargeForPaid']")){
				var value = $("input[name='paidNormalServiceChargeForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"服务费格式不正确"
};

var overduePrincipalForPaidRule = {
		required : "逾期本金不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidOverduePrincipalForPaid']")){
				var value = $("input[name='paidOverduePrincipalForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"逾期本金格式不正确"	
};

var overdueInterestForPaidRule = {
		required : "逾期收益不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidOverdueInterestForPaid']")){
				var value = $("input[name='paidOverdueInterestForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"逾期收益格式不正确"
};

var overdueServiceChargeForPaidRule = {
		required : "逾期服务费不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidOverdueServiceChargeForPaid']")){
				var value = $("input[name='paidOverdueServiceChargeForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"逾期服务费格式不正确"
};

var extensionChargeForPaidRule = {
		required : "展期费用不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidExtensionChargeForPaid']")){
				var value = $("input[name='paidExtensionChargeForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"展期费用格式不正确"
};

var overdueManagerFeeForPaidRule = {
		required : "逾期管理费不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidOverdueManagerFeeForPaid']")){
				var value = $("input[name='paidOverdueManagerFeeForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"逾期管理费格式不正确"
};

var earlyRepaymentChargeForPaidRule = {
		required : "提前还款手续费不能为空",
		checkRule:function(input) {
			if(input.is("input[name='paidEarlyRepaymentChargeForPaid']")){
				var value = $("input[name='paidEarlyRepaymentChargeForPaid']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"提前还款手续费格式不正确"
};
//=======================================
var accountPrincipalForNewPlanRule = {
		required : "应还本金不能为空",
		checkRule:function(input) {
			if(input.is("input[name='accountPrincipalForNewPlan']")){
				var value = $("input[name='accountPrincipalForNewPlan']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"应还本金格式不正确"
};

var accountInterestForNewPlanRule = {
		required : "应还收益不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "应还收益格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='accountInterestForNewPlan']")){
					var value = $("input[name='accountInterestForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"应还收益格式不正确"
};

var accountServiceChargeForNewPlanRule = {
		required : "应还服务费不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "应还服务费格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='accountServiceChargeForNewPlan']")){
					var value = $("input[name='accountServiceChargeForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"应还服务费格式不正确"
};

var overduePrincipalForNewPlanRule = {
		required : "逾期本金不能为空",
		checkRule:function(input) {
			if(input.is("input[name='overduePrincipalForNewPlan']")){
				var value = $("input[name='overduePrincipalForNewPlan']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
	},
	checkMessage:"逾期本金格式不正确"
};

var overdueInterestForNewPlanRule = {
		required : "逾期收益不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "逾期收益格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='overdueInterestForNewPlan']")){
					var value = $("input[name='overdueInterestForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"逾期收益格式不正确"
};

var overdueServiceChargeForNewPlanRule = {
		required : "逾期服务费不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "逾期服务费格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='overdueServiceChargeForNewPlan']")){
					var value = $("input[name='overdueServiceChargeForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"逾期服务费格式不正确"
};

var extensionChargeForNewPlanRule = {
		required : "展期费用不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "展期费用格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='extensionChargeForNewPlan']")){
					var value = $("input[name='extensionChargeForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"展期费用格式不正确"
};

var overdueManagerFeeForNewPlanRule = {
		required : "逾期管理费不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "逾期管理费格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='overdueManagerFeeForNewPlan']")){
					var value = $("input[name='overdueManagerFeeForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"逾期管理费格式不正确"
};

var paidEarlyRepaymentChargeForNewPlanRule = {
		required : "提前还款手续费不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "提前还款手续费格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='paidEarlyRepaymentChargeForNewPlan']")){
					var value = $("input[name='paidEarlyRepaymentChargeForNewPlan']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"提前还款手续费格式不正确"
};

var accountAmountRule = {
		required : "应还金额不能为空",
//		pattern : "^[0-9]+(.[0-9]{1,2})?$",
//		message : "应还金额格式不正确"
		checkRule:function(input) {
				if(input.is("input[name='accountAmount']")){
					var value = $("input[name='accountAmount']").val().parseMoney();
					var regex = "^[0-9]+(.[0-9]{1,2})?$";
					var result = value.match(regex);
					if(result==null){
						return false;
					}else{
						return true;
					}
				}
				return true;
		},
		checkMessage:"应还金额格式不正确"
};

var currentRepaymentAmountRule = {
		required : "本次还款金额不能为空",	
		checkRule:function(input) {
			if(input.is("input[name='paidAmout']")){
				var value = $("input[name='paidAmout']").val().parseMoney();
				var regex = "^[0-9]+(.[0-9]{1,2})?$";
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		checkMessage:"本次还款金额格式不正确"
	};