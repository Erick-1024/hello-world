var mobileNoRule = {//联系电话
		required : "联系电话不能为空",
		pattern : "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$",
		message : "联系电话格式不正确"
}

var emailNoRule = {//联系人邮箱
		required : "邮箱不能为空",
		pattern : "\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*",
		message : "邮箱格式不正确"
}

var userIdRule = { // 员工id
	rule : function(input) {
		return input.is("[name=flightSegmentNumber]") ? $("#table tbody tr:visible").length > 1 : true;
	},
	message : "员工Id不存在"
};