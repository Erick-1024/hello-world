var amountRule={
		required : "还款金额不能为空",
		maxRule: function(input) {
			if(input.is("#amount")){
				if(parseFloat($("#amount").val().parseMoney()) <= parseFloat($("#maxAmount").text().parseMoney())){
					return true;
				}else {
					return false;
				}
			}
			return true;
		},
		maxRuleMessage: "还款金额不能大于最大还款金额",
		balanceRule: function(input) {
			if(input.is("#amount")){
				if(parseFloat($("#amount").val().parseMoney()) <= parseFloat($("#balance").text().parseMoney()) && parseFloat($("#amount").val().parseMoney()) > 0){
					return true;
				}else {
					return false;
				}
			}
			return true;
		},
		balanceRuleMessage: "还款金额不能大于账户余额且不能小于等于0"
	};

var payPswRule = {
		required : "支付密码不能为空"
};