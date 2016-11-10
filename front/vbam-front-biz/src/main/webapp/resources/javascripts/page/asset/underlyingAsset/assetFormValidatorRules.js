var amountRule={
	rulePattern:function(input){
		if($(input).is("#creditLimit") || $(input).is("#creditBalance") 
				|| $(input).is("#counterpartyLimit") || $(input).is("#counterpartyBalance") 
				|| $(input).is("#invoiceAmount") || $(input).is("#invoiceBalance")
				|| $(input).is("#financeAmount") || $(input).is("#financeBalance")){
			if($(input).val()=="")
				return true;
			var value = $(input).val().parseMoney();
			var reg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
			if(!reg.test(value))
				return false;
			return true;
		}
		return true;
	},
	ruleMessage: "格式不正确",
	ruleMin:function(input){
		if($(input).is("#creditLimit") || $(input).is("#creditBalance") 
				|| $(input).is("#invoiceAmount") || $(input).is("#invoiceBalance")
				|| $(input).is("#financeAmount") || $(input).is("#financeBalance")){
			if($(input).val()=="")
				return true;
			if(parseFloat($(input).val().parseMoney()) <= 0)
				return false;
			return true;
		}
		return true;
	},
	ruleMinMessage: "必须大于0"
};

//授信余额
var creditBalanceRule={
	ruleMax:function(input){
		if($(input).is("#creditBalance")){
			if($(input).val()=="")
				return true;
			if($("#creditLimit").val()=="" || parseFloat($(input).val().parseMoney()) > parseFloat($("#creditLimit").val().parseMoney()))
				return false;
			return true;
		}
		return true;
	},
	ruleMaxMessage: "不能大于授信额度"
};

//交易对手非承保余额
var counterpartyBalanceRule={
		ruleMax:function(input){
			if($(input).is("#counterpartyBalance")){
				if($(input).val()=="")
					return true;
				if($("#counterpartyLimit").val()=="" || parseFloat($(input).val().parseMoney()) > parseFloat($("#counterpartyLimit").val().parseMoney()))
					return false;
				return true;
			}
			return true;
		},
		ruleMaxMessage: "不能大于交易对手非承保额度"
};

//应收账款余额
var invoiceBalanceRule={
	ruleMax:function(input){
		if($(input).is("#invoiceBalance")){
			if($(input).val()=="")
				return true;
			if($("#invoiceAmount").val()=="" || parseFloat($(input).val().parseMoney()) > parseFloat($("#invoiceAmount").val().parseMoney()))
				return false;
			return true;
		}
		return true;
	},
	ruleMaxMessage: "不能大于应收账款额度"
};

//融资余额
var financeBalanceRule={
	ruleMax:function(input){
		if($(input).is("#financeBalance")){
			if($(input).val()=="")
				return true;
			if($("#financeAmount").val()=="" || parseFloat($(input).val().parseMoney()) > parseFloat($("#financeAmount").val().parseMoney()))
				return false;
			return true;
		}
		return true;
	},
	ruleMaxMessage: "不能大于融资额度"
};

//利率
var rateRule={
	rulePattern:function(input){
		if($(input).is("#interestRate")){
			if($(input).val()=="")
				return true;
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
		if($(input).is("#interestRate")){
			if($(input).val()=="")
				return true;
			if(parseFloat($(input).val()) <= 0)
				return false;
			return true;
		}
		return true;
	},
	ruleMinMessage: "必须大于0"
};

//天数
var periodRule={
	pattern : "^([1-9]\\d*|0)$",
	message : "必须为0或正整数"
};

//提前还款天数
var forwardDaysRule={
	ruleMatch:function(input){
		if($(input).is("#forwardDays")){
			if($(input).val()=="")
				return true;
			if((parseFloat($(input).val()) > 0 && $("#forwardSelect").val()=="false") || (parseFloat($(input).val()) <= 0 && $("#forwardSelect").val()=="true"))
				return false;
			return true;
		}
		return true;
	},
	ruleMatchMessage: "与提前还款栏不符"
};

//展期天数
var extensionDaysRule={
	ruleMatch:function(input){
		if($(input).is("#extensionDays")){
			if($(input).val()=="")
				return true;
			if((parseFloat($(input).val()) > 0 && $("#extensionSelect").val()=="false") || (parseFloat($(input).val()) <= 0 && $("#extensionSelect").val()=="true"))
				return false;
			return true;
		}
		return true;
	},
	ruleMatchMessage: "与展期栏不符"
};

//违约天数
var overdueDaysRule={
	ruleMatch:function(input){
		if($(input).is("#overdueDays")){
			if($(input).val()=="")
				return true;
			if((parseFloat($(input).val()) > 0 && $("#overdueSelect").val()=="false") || (parseFloat($(input).val()) <= 0 && $("#overdueSelect").val()=="true"))
				return false;
			return true;
		}
		return true;
	},
	ruleMatchMessage: "与违约资产栏不符"
};