$(function (){
	
    //初始化成功提示弹窗
    tipPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
    
	//权限确认点击事件
	$("body").on("click","#addPrivilegeBnt",function(){
		
		var postData = {};
		var customerIdList = [];
		masterId = $("#masterId").val();
		customerNameQuery = $("#customerNameQuerySting").val();
		currentAll = $("#currentAll").val();
		all = $("#all-authority").val();
		if (currentAll == "true") {
			customerIdList = outData;
		} else {
			customerIdList = inData;
		}
		postData.masterId = masterId;
		postData.currentAll = currentAll;
		postData.all = all;
		postData.customerNameQuery = customerNameQuery;
		postData.customerIdList = customerIdList;
			
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/privilege/add",
			data :  JSON.stringify(postData),
			dataType : 'json',
			contentType:'application/json;charset=utf-8',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					closeThePop();
					tipPopWindow.open().center();
		            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
		            $("#tip-box-window .notice-content").text("权限添加成功");
		            setTimeout(function(){  
       					window.location.reload();
       					},1000);
				}else {
					closeThePop();
					tipPopWindow.open().center();
		            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		            $("#tip-box-window .notice-content").text(data.message);
				}
			},
		});
	});
});
//关闭弹窗
function closeThePop(){
    $(".k-overlay").hide();
    $(".k-window").hide();
}
