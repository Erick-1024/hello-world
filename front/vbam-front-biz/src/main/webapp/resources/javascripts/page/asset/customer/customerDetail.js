var PAGESIZE = 10;
$(function() {
	//初始化页面
	$("body").on("keydown", function() {
		var evt = window.event || arguments.callee.caller.arguments[0];
		// 键盘回车事件
		if (evt.keyCode == 13) {
			$('.form-search-link').click();
		}
	})
	
});