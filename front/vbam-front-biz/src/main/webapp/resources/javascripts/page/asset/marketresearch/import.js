$(function(){
	//初始化成功提示弹窗
    tipPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
	
	$("#photos").kendoUpload({
		async: {
	        saveUrl: "importProjectExcel",
	        autoUpload: true
	    },
		multiple: true,
		showFileList: false,
		upload: onUpload,
		success:onSuccess,
		error:onError
	});
	
	var blag = true;
	//提交按钮点击事件
	$("#buttonConfirm").on("click", function() {
		if(blag){
			blag = false;
			$.ajax({
				type: 'post',
				url: "importProjectList",
				data: {rediskey:$("#rediskey").val()},
				success: function(data){
					
					if("SUCCESS" == data.status){
						
						tipPopWindow.open().center();
						$("#tip-box-window .dlg-notice").addClass("notice-icon02");
						$("#tip-box-window .notice-content").text(data.message);
						setTimeout("gotoView()",1300);
					}		
					else{
						tipPopWindow.open().center();
						$("#tip-box-window .dlg-notice").addClass("notice-icon03");
						$("#tip-box-window .notice-content").text(data.message);
					}
					blag = true;
				},
				error: function(data){
					showAlertWin("网络异常："+data.responseText);
					blag = true;
				}
			});
		}
	});
	
});

function gotoView(){
	location.href = basepath + "/asset/marketResearch/marketDataSummary";
}

function onSuccess(e){
	$("#uploadMarketDataExcel").text("上传附件");
	if(e.response=="FAILED"){
		$.each(e.files, function () {
			tipPopWindow.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon03");
			$("#tip-box-window .notice-content").text(this.name+"数据为空，请重新上传！");
			
	    });
	}else{
		$.each(e.files, function () {
		   $(".import-success-box").append('<span>'+this.name+'</span>');
		});
		setDataNum(e.response);
	}
	
}

function onError(e){
	$("#uploadMarketDataExcel").text("上传附件");
	tipPopWindow.open().center();
    $("#tip-box-window .dlg-notice").addClass("notice-icon01");
    $("#tip-box-window .notice-content").text("上传失败。");
}

function onUpload(e) {
    var files = e.files;
    $("#uploadMarketDataExcel").text("上传中...");
    $.each(files, function () {
    	 e.data = { rediskey: $("#rediskey").val() };
        if (this.extension.toLowerCase() != ".xlsx" && this.extension.toLowerCase() != ".xls") {
        	$("#uploadMarketDataExcel").text("上传附件");
        	//$(".import-success-box").append('<span>'+this.name+'&nbsp;&nbsp不是excel文件</span>');
        	$(".import-success-box").append('<span style="color:red">'+this.name+'&emsp;不是excel文件</span>');
            e.preventDefault();
        }
    });
}

function uploadSubmit(url,uploadFileId){
	　　var file = $('#uploadMarketDataExcel').val();
	  var key = $("#rediskey").val();
	  if(key == ""){
		  $(".import-success-box").append('<span style="color:red">导入失败，请刷新页面!</span>');
		  return;
	  }
	　　//检查是否已选择上传文件
	　　if (file != '') {
	　　　　var filename = file.replace(/.*(\/|\\)/, '');
	　　　　var fileext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
	　　　　//检查文件格式
	　　　　if (fileext == 'xlsx' || fileext == 'xls'){
		　　　　//上传excel文件
			ajaxFileUpload(url, uploadFileId, key);
	　　　　}
	　　　　else {
			$(".import-success-box").append('<span style="color:red">文件格式必须是*.xls或*.xlsx</span>');
	　　　　}
	　　}
	　　else {
		$(".import-success-box").append('<span style="color:red">请选择excel文件！</span>');
	　　}
}

function setDataNum(num){
	$("#totalNum").text(num);
}
