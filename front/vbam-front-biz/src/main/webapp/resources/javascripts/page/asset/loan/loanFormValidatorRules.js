//放款需要的校验规则
var loanAmountRule={
	rulePattern:function(input){
		if($(input).is("#loanAmount")){
			var value = $(input).val().parseMoney();
			var reg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
			if(!reg.test(value))
				return false;
			return true;
		}
		return true;
	},
	ruleMessagePattern: "格式不正确",
	ruleMin:function(input){
		if($(input).is("#loanAmount")){
			if(parseFloat($(input).val().parseMoney()) <= 0)
				return false;
			return true;
		}
		return true;
	},
	ruleMinMessage: "必须大于0",
	ruleMax:function(input){
		if($(input).is("#loanAmount")){
			if(parseFloat($(input).val().parseMoney()) > parseFloat($("#maxLoanAmount").text().parseMoney()))
				return false;
			return true;
		}
		return true;
	},
	ruleMaxMessage: "不能大于最高放款金额"
};

var rateRule={
	rulePattern:function(input){
		if($(input).is("#interest") || $(input).is("#overdueInterest")){
			var value = $(input).val();
			var reg = /^(([1-9]\d{0,9})|0)(\.\d{1,3})?$/;
			if(!reg.test(value))
				return false;
			return true;
		}
		return true;
	},
	ruleMessagePattern: "格式不正确",
	ruleMin:function(input){
		if($(input).is("#interest") || $(input).is("#overdueInterest")){
			if(parseFloat($(input).val()) <= 0)
				return false;
			return true;
		}
		return true;
	},
	ruleMinMessage: "必须大于0"
};

var periodRule={
	pattern : "^[0-9]*[1-9][0-9]*$",
	message : "必须为正整数"
};

var dateRule={
	pattern : "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$",
	message : "日期无效"
};

var amountRule={
	ruleMin:function(input){
		if($(input).is("#financeBalance") || $(input).is("#accountPrincipal") || $(input).is("#accountInterest") || $(input).is("#accountOverdue") || $(input).is("#accountAmount")){
			if(parseFloat($(input).val().parseMoney()) < 0)
				return false;
			return true;
		}
		return true;
	},
	ruleMinMessage: "必须大于等于0",
	ruleMax:function(input){
		if($(input).is("#accountOverdue")){
			if(parseFloat($(input).val().parseMoney()) < parseFloat($("#minOverdueInterest").text().parseMoney()))
				return false;
			return true;
		}
		return true;
	},
	ruleMaxMessage: "不能小于最低逾期费"
};

