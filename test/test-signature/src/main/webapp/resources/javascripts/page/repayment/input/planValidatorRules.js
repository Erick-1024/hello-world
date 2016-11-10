var amountPattern = "^[0-9]+(.[0-9]{1,2})?$";
var digitalPattern = "^[0-9]+$";
var percentPattern = "^[0-9]+(.[0-9]{1,2})?$";
var loanNoRule={
	required : "放款编号不能为空"
};

var businessContractNoRule={
		required : "业务合同号不能为空"
};

var financeBalanceRule={
	required : "融资余额不能为空",
	balanceRule: function(input) {
		if(input.is("input[name='financeBalanceForAdd']")){
			var value = $("input[name='financeBalanceForAdd']").val().parseMoney();
			var regex = amountPattern;
			var result = value.match(regex);
			if(result==null){
				return false;
			}else{
				return true;
			}
		}
		return true;
	},
	balanceRuleMessage:"融资余额格式不正确"
};

var valueDateRule={
		required : "收益计算日不能为空"
};

var dueDateRule={
		required : "到期日不能为空"
};

var settleInterestDateRule={
		required : "收益分配日不能为空"
};

var repaymentDateRule={
		required : "还款日不能为空"
};

var accountRepaymentPrincipalRule={
	required : "应还本金不能为空",
	accountRepaymentPrincipalRule: function(input) {
		if(input.is("input[name='accountRepaymentPrincipalForAdd']")){
			var value = $("input[name='accountRepaymentPrincipalForAdd']").val().parseMoney();
			var regex = amountPattern;
			var result = value.match(regex);
			if(result==null){
				return false;
			}else{
				return true;
			}
		}
		return true;
	},
	accountRepaymentPrincipalRuleMessage:"应还本金格式不正确"
};

var accountRepaymentInterestRule={
		required : "应还收益不能为空",
		accountRepaymentInterestRule: function(input) {
			if(input.is("input[name='accountRepaymentInterestForAdd']")){
				var value = $("input[name='accountRepaymentInterestForAdd']").val().parseMoney();
				var regex = amountPattern;
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		accountRepaymentInterestRuleMessage:"应还收益格式不正确"
};

var accountRepaymentServiceChargeRule={
		required : "应还服务费不能为空",
		accountRepaymentServiceChargeRule: function(input) {
			if(input.is("input[name='accountRepaymentServiceChargeForAdd']")){
				var value = $("input[name='accountRepaymentServiceChargeForAdd']").val().parseMoney();
				var regex = amountPattern;
				var result = value.match(regex);
				if(result==null){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		accountRepaymentServiceChargeRuleMessage:"应还服务费格式不正确"
};

/*-------------------------------------还款计划费用-----------------------------------*/
var costNameRule={
		required: "费用名目不能为空"
};
var chargeValRule={
		required: "记费值不能为空",
		ratioRule: function(input) {
			if(input.is("#chargeVal") && $("#chargeMethod .active").data("chargemethod") == "比例"){
				var value = $("#chargeVal").val().parseMoney();
				var regex = percentPattern;
				var result = value.match(regex);
				if(result == null){
					return false;
				}else {
					return true;
				}
			}
			return true;
		},
		ratioRuleMessage: "计费比例值格式不正确",
		amountRule: function(input) {
			if(input.is("#chargeVal") && $("#chargeMethod .active").data("chargemethod") == "定额"){
				var value = $("#chargeVal").val().parseMoney();
				var regex = amountPattern;
				var result = value.match(regex);
				if(result == null){
					return false;
				}else {
					return true;
				}
			}
			return true;
		
		},
		amountRuleMessage: "计费定额值格式不正确"
};
var repaymentDateRule={
		required: "还款日不能为空",
		notRightRule: function(input) {
			var isRight = true;
			if(input.is("input[name='repaymentDate']")){
				if($("#settleStatus .active").data("settlestatus") == "未结清"){
					var today = new Date().format("yyyy-MM-dd");
					$("input[name='repaymentDate']").each(function() {
						if($(this).val() < today)
							isRight = isRight && false;
						isRight = isRight && true;
					});
					return isRight;
				}
			}
			return true;
		},
		notRightRuleMessage:"还款日不能小于当前营业日"
};
var othersChargeStandardRule={
		ruleNotNull: function(input) {
			if(input.is("#othersChargeStandard")){
				if($("#chargeStandard .active").data("chargestandard") == "其他"){
					if($("#othersChargeStandard").val() == "" && isChargeStandardOthers)
						return false;
				}
				isChargeStandardOthers = false;
				return true;
			}
			return true;
		},
		ruleNotNullMessage: "其他计费基准不能为空",
		ruleNotRight: function(input) {
			if(input.is("#othersChargeStandard")){
				if($("#chargeStandard .active").data("chargestandard") == "其他"){
					var value = $("#othersChargeStandard").val()
					var regex = amountPattern;
					var result = value.match(regex);
					if(result == null && isChargeStandardOthers){
						return false;
					}else {
						isChargeStandardOthers = false;
						return true;
					}
				}
			}
			return true;
		},
		ruleNotRightMessage: "其他计费基准格式不正确"
};
