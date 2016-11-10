$(function(){
	//回车事件
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	if(evt.keyCode==13){
    			$("#submit").click();
    	}
    });
	//点击提交
	$("body").on("click","#submit",function(){
		
		var validator = $("#credit-aplay-form").data("kendoValidator");
		if(!validator.validate()) {
			return;
		};
		var postData = {};
		postData.customerId =$('#customerId').val();
		postData.businessContractNo =$('#businessContractNo').val();
		postData.creditMode =$('#creditMode').val();
		postData.currency =$('#currency').val();
		postData.totalLimit =$('#totalLimit').val().parseMoney();
		postData.expense =$('#expense').val().parseMoney();
		postData.effectiveDate =$('#effectiveDate').val();
		postData.dueDate =$('#dueDate').val();
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/credit/add",
			data :  postData,
			dataType : 'json',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					closeThePop();
					successPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon01");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
		            $("#tip-box-window .notice-content").text("申请额度成功");
				}else {
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon02");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		            $("#tip-box-window .notice-content").text(data.message);
				}
			},
		});
	});
    
});
















