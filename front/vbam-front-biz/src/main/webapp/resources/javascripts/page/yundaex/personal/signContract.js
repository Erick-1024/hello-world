var completeSuccess = false;
var isRead;
var contractId;
$(function(){
	if($(".finaLeadCheck").length < 2){
		$(".finaConfirm-content").css("text-align","center");
	}
	
	isRead = $("#isRead").val();
	$(".finaLeadCheck .checkbox").click(function () {
		$(this).toggleClass("active");
		var flag = true;
		$(".finaLeadCheck .checkbox").each(function(){
			if(!$(this).hasClass("active")){
				flag = false;
			}
		});
		
		if(flag && (($(".finaLeadCheck .checkbox").length == 2 && isRead == "3")
				|| ($(".finaLeadCheck .checkbox").length == 1 && (isRead == "1" || isRead == "2")))){
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
    	if(completeSuccess)
    		window.location.href = encodeURI(basepath + "/facade/signin");
    	else
    		tipPopWindow.close();
    });
    
    Signature.onLoadSignatureSoftware();
    
	contractId = $("#contractId").val();
	
	//下载合同
	$("#downloadContract").click(function(){
        window.location.href = encodeURI(basepath + "/guide/yundaex/personal/downloadContract?contractId=" + contractId);
	});
	//完成签署合同
	$("#completeContract").click(function(){
		if(($(".finaLeadCheck .checkbox").length == 2 && isRead != "3")
				|| ($(".finaLeadCheck .checkbox").length == 1 && !(isRead == "1" || isRead == "2"))){
			showAlertWin("请先阅读合同！");
			return;
		}
		
		var flag = true;
		$(".finaLeadCheck .checkbox").each(function(){
			if(!$(this).hasClass("active")){
				flag = false;
			}
		});
		
		if(!flag){
			showAlertWin("请同意合同！");
			return;
		}

		$.ajax({
	        type: 'POST',  
	        url: basepath + '/yundaex/personal/facade/getContractData',
	        data: {
	        	id: $("input[name=id]").val(),
	        	code: $("input[name=code]").val(),
	        	contractId:contractId
	        },
	        success:function(data){
	        	tipPopWindow.open().center();
	        	$(".k-window-actions").hide()
	        	
	    		if(data.status=='SUCCESS'){
	    			if(data.totalNum == 0){
	    				$("#tip-box-window .notice-content").text("获取合同数据失败");
	    				return;
	    			}
	    			$("#tip-box-window .dlg-del-row a").hide();
	    			$("#tip-box-window .notice-content").text("合 同 签 名 中 ...");
	    			Signature.setDNFilter($.trim($("input[name=residentIdentityCardNo]").val()));
	    			var signature = [];
	    			for(i in data.data){
	    				signature[i] = Signature.signMessageOnClick(data.data[i], "Attach");
	    			}
					
					$.ajax({
				        type: 'POST',
				        url: basepath + '/yundaex/personal/facade/completeContract',
				        data: {
				        	signDatas: signature.toString(),
				        	id: $("input[name=id]").val(),
				        	code: $("input[name=code]").val(),
				        	contractId:contractId
				        },
				        success:function(data){
				        	$("#tip-box-window .dlg-del-row a").show();
				    		if(data.status=='SUCCESS'){
				    			completeSuccess = true;
//				    			$("#tip-box-window .dlg-notice").addClass("notice-icon02");
								$("#tip-box-window .notice-content").text(data.message);
							}else{
								completeSuccess = false;
//								$("#tip-box-window .dlg-notice").addClass("notice-icon01");
								$("#tip-box-window .notice-content").text(data.message);
							}
				        }
				    });
				}else{
					$("#tip-box-window .notice-content").text(data.message);
				}
	        }
	    });
		
//		window.location.href = encodeURI(basepath + "/guide/completeContract?supervisionAccountNo=" + supervisionAccountNo + "&contractId=" + contractId);
	});
});

//阅读合同
function readContract(type){
	window.location.href = encodeURI(basepath + "/yundaex/personal/facade/readContract?id="+$("input[name=id]").val()+"&code="+$("input[name=code]").val()+"&type="+type+"&contractId=" + contractId + "&isRead="+isRead);
};
