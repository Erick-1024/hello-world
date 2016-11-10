var loanNo = "";
var flag_loanNo = true;
var amountPattern = "^[0-9]+(.[0-9]{1,2})?$";
var digitalPattern = "^[0-9]+$";
var percentPattern = "^[0-9]+(.[0-9]{1,3})?$";
var loanNoRule = {
		required : "放款编号不能为空",
		rule : function(input) {
			if(input.is("#loanNo")){
				var value = $("#loanNo").val();
				var url = "verifyLoanNo";
				if(value == loanNo){
					return flag_loanNo;
				}else {
					loanNo = value;
					return flag_loanNo = isValueExist(value, url);
				}
			}
			return true;
		},
		ruleMessage : "放款编号在系统中存在"
};
var businessContractNoRule={
		required : "业务合同号不能为空"
	};

var currencyRule={
		rule: function(input) {
			if(input.is("#currency")){
				if($("#currency").val() != "人民币")
					return false;
			}
			return true;
		},
		ruleMessage: "币别必须是人民币"
	};

var financeCompanyRule={
		required : "融资客户公司名称不能为空"
	};

var businessProductRule={
		required : "业务产品不能为空"
	};

var receivablesAmountRule={
		formateRule : function(input) {
			if(input.is("#receivablesAmount")){
				if("" == $("#receivablesAmount").val())
					return true;
				var value = $("#receivablesAmount").val().parseMoney();
				var result = value.match(amountPattern);
				if(null == result){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		formateRuleMessage : "应收账款金额格式不正确",
		rule: function(input) {
			if(input.is("#receivablesAmount")){
				if("" != $("#receivablesBalance").val()){
					if(parseFloat($("#receivablesAmount").val().parseMoney()) >= parseFloat($("#receivablesBalance").val().parseMoney())){
						return true;
					}else {
						return false;
					}
				}else {
					return true;
				}
			}
			return true;
		},
		ruleMessage: "应收账款金额应大于等于应收账款余额"
	};

var receivablesBalanceRule={
		formateRule : function(input) {
			if(input.is("#receivablesBalance")){
				if("" == $("#receivablesBalance").val())
					return true;
				var value = $("#receivablesBalance").val().parseMoney();
				var result = value.match(amountPattern);
				if(null == result){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		formateRuleMessage : "应收账款余额格式不正确",
		rule: function(input) {
			if(input.is("#receivablesBalance")){
				if("" != $("#receivablesAmount").val()){
					if(parseFloat($("#receivablesAmount").val().parseMoney()) >= parseFloat($("#receivablesBalance").val().parseMoney())){
						return true;
					}else {
						return false;
					}
				}else {
					return true;
				}
			}
			return true;
		},
		ruleMessage: "应收账款金额应大于等于应收账款余额"
	};

var financeAmountRule={
		required : "融资金额不能为空",
		formateRule : function(input) {
			if(input.is("#financeAmount")){
				var value = $("#financeAmount").val().parseMoney();
				var result = value.match(amountPattern);
				if(null == result){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		formateRuleMessage : "融资金额格式不正确",
		rule: function(input) {
			if(input.is("#financeAmount")){
				if("" != $("#financeBalance").val()){
					if(parseFloat($("#financeAmount").val().parseMoney()) >= parseFloat($("#financeBalance").val().parseMoney())){
						return true;
					}else {
						return false;
					}
				}else {
					return true;
				}
			}
			return true;
		},
		ruleMessage: "融资金额应大于等于融资余额"
	};

var financeBalanceRule={
		required : "融资余额不能为空",
		formateRule : function(input) {
			if(input.is("#financeBalance")){
				var value = $("#financeBalance").val().parseMoney();
				var result = value.match(amountPattern);
				if(null == result){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		formateRuleMessage : "融资余额格式不正确",
		rule: function(input) {
			if(input.is("#financeBalance")){
				if("" != $("#financeAmount").val()){
					if(parseFloat($("#financeAmount").val().parseMoney()) >= parseFloat($("#financeBalance").val().parseMoney())){
						return true;
					}else {
						return false;
					}
				}else {
					return true;
				}
			}
			return true;
		},
		ruleMessage: "融资金额应大于等于融资余额"
	};

var interestRateRule={
		required : "费率不能为空",
		formateRule: function(input) {
			if(input.is("#interestRate")){
				var value = $("#interestRate").val().parseMoney();
				var result = value.match(percentPattern);
				if(null == result){
					return false;
				}else{
					return true;
				}
			}
			return true;
		},
		formateRuleMessage: "费率格式不正确"
	};

var loanPeriodRule={
		required : "贷款期限不能为空",
		pattern: digitalPattern,
		message: "贷款期限格式不正确"
	};

var loanDateRule={
		required: "放款日不能为空"
	};
