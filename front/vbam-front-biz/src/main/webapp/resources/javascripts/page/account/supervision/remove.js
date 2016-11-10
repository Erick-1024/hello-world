$(function(){
		
	//初始化操作弹窗
	popWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	//初始化确认解除弹窗
    noticeWindow = new PopWindow({
        title: "确认解除",
        width: 680,
        reload: true,
        template: "#template-notice"
    }).init();

    init();
});

//解除监管关系--列表初始化
function init(){
	
	//如果只有一个一般账户，没有专用账户，则该一般账户默认选中，并置灰
	var accountCheckboxs = $("#table-remove .checkboxInfo");
	if(accountCheckboxs.size() == 1){
		$(".checkboxGeneral .checkboxInfo").addClass("active");
	}
		
	//“确认”按钮 
    $("body").on("click", "#confirm-remove", function(e){
    	var accountCheckeds = $("#table-remove .active");
    	if(accountCheckeds.size() == 0){
    		popWindow.open().center();
    		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
    		$("#tip-box-window .notice-content").text("请至少选择一个账户！");
    		return;
    	}
    	if($(".checkboxGeneral .checkboxInfo").hasClass("active") && accountCheckeds.size() != accountCheckboxs.size()){
    		popWindow.open().center();
    		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
    		$("#tip-box-window .notice-content").text("主账户解除监管，您需选中所有专用账户！");
    		return;
    	}
    	noticeWindow.open().center();
    	$("#confirm-box-window .notice-content").text("是否确认解除与" + $('#supervisorName').val() + "的账户监管关系？一旦解除将影响扣款，请谨慎操作");
    });
    
  //提交弹窗确认按钮
    $("body").on("click", "#confirm-box-window .confirm-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
        confirmRemove();
    });
    
  //提交弹窗关闭按钮
    $("body").on("click", "#confirm-box-window .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
	//解除监管关系 一般账户（主账户）复选框
	$("body").on("click", ".checkboxGeneral .checkboxInfo", function(e){
		if(accountCheckboxs.size() == 1)
			return;
		$(this).toggleClass("active");
		if($(this).hasClass("active"))
			$(".checkboxWrap").children(".checkboxInfo").addClass("active");
	});
	
	//解除监管关系 专用账户复选框
    $("body").on("click", ".checkboxSpecial .checkboxInfo", function(e){
        $(this).toggleClass("active");
    });
}

//确认 解除监管关系
function confirmRemove(){
	var accountCheckeds = $("#table-remove .active");
	var accountIdString = "";
	for (var i = 0; i < accountCheckeds.size(); i++)
		accountIdString = accountIdString + $(accountCheckeds[i]).attr("account-id") + ";";
	cana.post(
			basepath + "/account/supervision/remove",
			{
				accountIdString: accountIdString
			},
			function(data){
				popWindow.open().center();
	    		$("#tip-box-window .dlg-notice").addClass("notice-icon02");
				$("#tip-box-window .notice-content").text("提交成功！");
				setTimeout(function(){
					window.location.href = basepath + "/account/supervision/detail?applyId=" + data.result;
				},100);
			},
			function(data){
					popWindow.open().center();
		    		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
					$("#tip-box-window .notice-content").text(data);
				});
}
