$(function(){
	var isRead = $("#isRead").val();
	$(".finaLeadCheck .checkbox").click(function () {
		$(this).toggleClass("active");
		if($(this).hasClass("active") && isRead == "true"){
			$(".commonBtn-link").removeClass("disable-btn");
		}else{
			$(".commonBtn-link").addClass("disable-btn");
		}
	}); 
	
	//初始化操作结果弹窗
	tipPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	//提示弹窗确认按钮
    $("body").on("click", "#tip-box-window .confirm-link", function(e){
    	window.location.href = encodeURI(basepath + "/welcome");
    });
    
	var supervisionAccountNo = $("#supervisionAccountNo").val();
	var contractId = $("#contractId").val();
	//阅读合同
	$("#readContract").click(function(){
		window.location.href = encodeURI(basepath + "/guide/readContract?supervisionAccountNo=" + supervisionAccountNo + "&contractId=" + contractId);
    });
	//下载合同
	$("#downloadContract").click(function(){
        window.location.href = encodeURI(basepath + "/guide/downloadContract?supervisionAccountNo=" + supervisionAccountNo + "&contractId=" + contractId);
    });
	//完成签署合同
	$("#completeContract").click(function(){
		if(isRead != "true"){
			showAlertWin("请先阅读合同！");
			return;
		}
		if(!$(".finaLeadCheck .checkbox").hasClass("active")){
			showAlertWin("请同意合同！");
			return;
		}
		$.ajax({
	        type: 'POST',  
	        url: basepath + '/guide/completeContract',
	        data: {
	        	supervisionAccountNo: supervisionAccountNo,
	        	contractId:contractId
	        },
	        success:function(data){
	        	tipPopWindow.open().center();
	    		if(data.status=='SUCCESS'){
//	    			$("#tip-box-window .dlg-notice").addClass("notice-icon02");
					$("#tip-box-window .notice-content").text(data.message);
				}else{
//					$("#tip-box-window .dlg-notice").addClass("notice-icon01");
					$("#tip-box-window .notice-content").text(data.message);
				}
	        }
	    });
//		window.location.href = encodeURI(basepath + "/guide/completeContract?supervisionAccountNo=" + supervisionAccountNo + "&contractId=" + contractId);
	});
});