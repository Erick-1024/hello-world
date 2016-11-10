$(function() {
	
	// 初始化应收账款弹窗
	new PopWindow(".open-receivablesPop", {
		title : "新增应收账款信息",
		width : 1000,
		reload : true,
		template : "#template-receivables"
	}).init();
	
	// 初始化费用信息弹窗
	new PopWindow(".open-costPop", {
		title : "新增/修改费用信息",
		width : 800,
		reload : true,
		template : "#template-resetPwd-cost"
	}).init();
	
	// 初始化新增/修改还款计划弹窗
	new PopWindow(".open-repaymentPop", {
		title : "新增/修改还款计划",
		width : 870,
		reload : true,
		template : "#template-resetPwd-arr"
	}).init();
	
	// 初始化消息弹窗
	new PopWindow(".open-message-btn", {
		title : "提示",
		width : 400,
		reload : true,
		template : "#tipBox_template"
	}).init();
	
	// 点击取消关闭弹窗
	$("body").on("click",".dlg-wrapper .close-costPop,.dlg-wrapper .close-repaymentPop,.dlg-wrapper .close-receivablesPop",function(e) {
		var win = $(e.target).closest(".k-window");
		var overlay = win.prev(".k-overlay");
		if (win.is(":visible")) {
			win.css("display", "none");
			overlay.css("display", "none");
		}
	});
});