/*var Rule = {
		required : "",
		pattern : "",
		message : ""
};
var businessContractNoRule = {
		required : "业务合同号不能为空"
};
var totalLimitRule = {
		required : "申请金额不能为空"
};
var businessContractNoRule = {
		required : "生效日期不能为空"
};
var businessContractNoRule = {
		required : "到期日期不能为空"
};

function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
};*/
/*var financingRatioRule = {//联系人邮箱
		pattern : "^[0-9]{2}|[0-1]{3}$",
		message : "融资比率格式不正确"
};*/

//formateRule : function(input) {
//	if (input.is("#applyAmt")) {
//		var value = $("#applyAmt").val().parseMoney();
//		var result = value.match(amountPattern);
//		if (null == result) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//	return true;
//}\
/*
 
 {
			rules: {
				dateRule: dateRule.rule,
			},
			messages: {
				dateRule: dateRule.ruleMessage,
			},
			needRuleAttrbute : false
		}
 
 * var dateRule={
		rule: function(input) {
				var invDate = new Date(input.find(".time-one").val());
				var dueDate = new Date(input.find(".time-two").val());
				if(invDate >= dueDate){
					return false;
				}
			return true;
		},
		ruleMessage: "日期不合法"
};
function addValidator(obj,rule){
	if(rule.required != undefined){
		setValidator(obj, 'required', true, rule.required);
	}
	if(rule.pattern != undefined && rule.message != undefined){
		setValidator(obj, 'pattern', rule.pattern, rule.message);
	}
}
*/
//}


/**
 * 弹框新增 业务合同号+交易对手+单证号码 唯一
 */
var invNoAddMsg = {
	rule : function(input) {
		if (input.is(".ap-num")) {
			var flag = true;
			$("#appoint-tb").find("tr").each(function(){
				if ($(this).find("td[name='invoiceNo']").text() == $(".ap-num").val() && $(this).find("td[name='counterpartyId']").text() == $("#invCustId").val()) {
					flag = false;
					return false;
				}
			})
			return flag;
		}
		return true;
	},
	ruleMessage : "重复输入"
};


/**
 * 修改弹框 业务合同号+交易对手+单证号码 唯一
 * 注意修改时，单证号码可以不修改
 */
var invNoUpateMsg = {
	rule : function(input) {
		if (input.is(".ap-num")) {
			var flag = true;
			$("#appoint-tb").find("tr").each(function(){
				if ($(this).find("td[name='invoiceNo']").text() == $(".ap-num").val() && $(this).find("td[name='counterpartyId']").text() == $("#invCustId").val() && $(".ap-num").val() != $("#invNo").val()) {
					flag = false;
					return false;
				}
			})
			return flag;
		}
		return true;
	},
	ruleMessage : "重复输入"
}

/**
 * 单证号码检验不为空
 */
var invNoNullMsg = {
		formateRule : function(input) {
			if (input.is(".ap-num")) {
				var invNo = $(".ap-num").val();
				if ($.trim(invNo) == "") {
					return false;
				} else {
					return true;
				}
			}
			return true;
		},
		formateRuleMessage : "不能为空"
}

/**
 * 到期日应大于开票日
 */
var dateMsg = {
	formateRule : function(input) {
		if (input.is(".time-two")) {
			var startDate = $(".time-one").val();
			var endDate = $(".time-two").val();
			var date1 = new Date(Date.parse(startDate.replace(/-/g,"\/")));
			var date2 = new Date(Date.parse(endDate.replace(/-/g,"\/")));
			return date1 < date2;
		}
		return true;
	},
	formateRuleMessage : "到期日应大于开票日"
}

/**
 * 相同业务合同号、交易对手的融资比例要相同
 */
var financingRatio = {
	rule : function(input){
		if(input.is(".ap-scale")){
			var flag = true;
			$("#appoint-tb").find("tr").each(function(){
				if ($(this).find("td[name='counterpartyId']").text() == $("#invCustId").val() && parseFloat($(".ap-scale").val()) != parseFloat($(this).find("td[name='financingRatio']").text())) {
					flag = false;
					return false;
				}
			})
			return flag;
		}
		return true;
	},
	ruleMessage : "融资比例不合法"
}

/**
 * 相同业务合同号、交易对手的融资比例要相同(修改)
 */
var financingRatioUpdate = {
	rule : function(input){
		if(input.is(".ap-scale")){
			var flag = true;
			$("#appoint-tb").find("tr").each(function(){
				if ($(this).find("td[name='counterpartyId']").text() == $("#invCustId").val() && $(this).find("td[name='invoiceNo']").text() != $("#invNo").val() && parseFloat($(".ap-scale").val()) != parseFloat($(this).find("td[name='financingRatio']").text())) {
					flag = false;
					return false;
				}
			})
			return flag;
		}
		return true;
	},
	ruleMessage : "融资比例不合法"
}


