// 表单验证添加验证条件
function setValidator(obj, ruleName, ruleValue, ruleMessage) {
	var obj = obj.jquery ? obj : $(obj);
	obj.attr(ruleName, ruleValue);
	obj.attr("data-" + ruleName + "-msg", ruleMessage);
};
// 表单验证条件的取消
function delValidator(obj, ruleName) {
	if (typeof ($(obj).attr(ruleName)) != "undefined") {
		$(obj).removeAttr(ruleName);
	}
	if (typeof ($(obj).attr("data-" + ruleName + "-msg")) != "undefined") {
		$(obj).removeAttr("data-" + ruleName + "-msg");
	}
	if ($("span[data-for='" + $(obj).attr('name') + "']")) {
		$("span[data-for='" + $(obj).attr('name') + "']").remove();
	}
	if ($(obj).hasClass("k-invalid")) {
		$(obj).removeClass("k-invalid");
	}
	if (typeof ($(obj).attr("aria-invalid")) != "undefined") {
		$(obj).removeAttr("aria-invalid");
	}
};
// 自动限制只能输入数字
function LimitInputNum(obj) {
	obj.attr("onfocus", "this.style.imeMode='disabled'");
	// obj.attr("onkeyup","this.value=this.value.replace(/\D/g,'')");
	obj.keypress(function(event) {
		var keyCode = event.which;
		if (keyCode >= 48 && keyCode <= 57 || keyCode == 8 || keyCode == 13)
			return true;
		return false;
	});
}

// 限定输入的只能是钱
function LimitInputMoney(obj) {
	obj.keydown(function(event) {
		obj.attr("onfocus", " this.style.imeMode='disabled'");
		var code = event.keyCode;
		if ((code >= 48 && code <= 57) || code == 110 || code == 190 || code == 8 || code == 13 || code == 37 || code == 39 || code == 46 || (code >= 96 && code <= 105)) {
			return true;
		} else {
			event.keyCode = 0;
			return false;
		}
	});
}

function keyClick(obj, event) {
	obj.attr("onfocus", " this.style.imeMode='disabled'");
	var code = event.keyCode;
	if ((code >= 48 && code <= 57) || code == 110 || code == 190 || code == 8 || code == 13) {
		if ((code == 110 || code == 190) && $(this).val().indexOf(".") >= 0) {
			return false;
		}
		return true;
	} else {
		event.keyCode = 0;
		return false;
	}
};

// ruleBean,用于Rule的存放
function ruleBean(name, meassge, functionBool) {
	var ruleName;
	var ruleMessage;
	var ruleFunctionBool;
	this.ruleName = name;
	this.ruleMessage = meassge;
	this.ruleFunctionBool = functionBool;
};
// 用户自定义规则的父类
// 用于用户自定义验证，（ruleFunctionBool为一个FUNCTION，ruleMessage也可为一个FUNCTION）返回Validator
function customerRule(BaseObj, ruleArray) {
	var ruleObj = {};
	var messageObj = {};
	var kendoValueObj = {};
	for ( var i = 0; i < ruleArray.length; i++) {
		ruleObj["" + ruleArray[i].ruleName + ""] = ruleArray[i].ruleFunctionBool;
		messageObj["" + ruleArray[i].ruleName + ""] = ruleArray[i].ruleMessage;
	}
	kendoValueObj["" + 'rules' + ""] = ruleObj;
	kendoValueObj["" + 'messages' + ""] = messageObj;
	return $(BaseObj).kendoValidator(kendoValueObj).data("kendoValidator");
};
/** 用法* */
// var ruleArray = new Array();
// ruleArray[0] = new ruleBean("privateRemark", "私有长度不能超过2000",
// function(textarea) {
// if (textarea.is("textarea[name='privateRemark']")) {
// var length = textarea.val().length;
// if (length > parseInt(3)) {
// return false;
// }
// }
// return true;
// });
// ruleArray[1] = new ruleBean("publicRemark", "公有长度不能超过2000",
// function(textarea) {
// if (textarea.is("textarea[name='publicRemark']")) {
// var length = textarea.val().length;
// if (length > parseInt(3)) {
// return false;
// }
// }
// return true;
// });
// var validator = customerRule("#updateRemarkForm", ruleArray);
